package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPasoProceso;

import java.util.List;

/**
 * En ésta interface PasoProcesoRepository esta conformado pos sus metodos de
 * aplicacion de los filtros por nombres de paso proceso,
 * obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Paso proceso
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
@Repository
public interface PasoProcesoRepository extends JpaRepository<PgimPasoProceso, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
            + "papro.idPasoProceso, papro.noPasoProceso ) "
            + "FROM PgimPasoProceso papro WHERE papro.esRegistro = '1' ")
    List<PgimPasoProcesoDTO> filtrarPorNombrePasoProceso(@Param("nombre") String nombre);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
            + "papro.idPasoProceso, papro.noPasoProceso, papro.pgimFaseProceso.idFaseProceso "
            + ") "
            + "FROM PgimPasoProceso papro "
            + "WHERE papro.pgimFaseProceso.pgimProceso.idProceso = :idProceso "
            + "AND papro.esRegistro = '1' "
            //+ "AND papro.esRegistro = 1 " //elozano se comento xq se repite
            + "ORDER BY papro.noPasoProceso")
    List<PgimPasoProcesoDTO> obtenerPasosPorIdProceso(@Param("idProceso") Long idProceso);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
            + "papro.idPasoProceso, papro.noPasoProceso, papro.pgimFaseProceso.idFaseProceso, papro.coPasoProceso, papro.dePasoProceso "
            + ") "
            + "FROM PgimPasoProceso papro "
            + "WHERE papro.pgimFaseProceso.pgimProceso.idProceso = :idProceso "
            + "AND papro.esRegistro = '1' "
            )
    Page<PgimPasoProcesoDTO> listarPasosPorIdProceso(@Param("idProceso") Long idProceso, Pageable pageable);


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
    + "inpa.idInstanciaPaso, pade.idPasoProceso, pade.noPasoProceso, pade.pgimFaseProceso.idFaseProceso, pade.flEtiquetarNotificacion) " 
    + "FROM PgimInstanciaPaso inpa "
    + "INNER JOIN inpa.pgimRelacionPaso rela "
    + "INNER JOIN rela.pasoProcesoDestino pade "
    + "WHERE inpa.esRegistro = '1' "
    + "AND rela.esRegistro = '1' "
    + "AND pade.esRegistro = '1' "
    + "AND inpa.idInstanciaPaso = :idInstanciaPasoActual")
    PgimPasoProcesoDTO obtenerpgimPasoProcesoDTOetiquetar(@Param("idInstanciaPasoActual") Long idInstanciaPasoActual);


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
            + "papro.idPasoProceso, papro.noPasoProceso, papro.pgimFaseProceso.idFaseProceso, "
            + "papro.pgimFaseProceso.pgimProceso.idProceso, papro.pgimFaseProceso.noFaseProceso ) "
            + "FROM PgimPasoProceso papro "
            + "WHERE papro.esRegistro = '1' "
            + "ORDER BY papro.pgimFaseProceso.idFaseProceso, papro.noPasoProceso")
    List<PgimPasoProcesoDTO> obtenerPasosPorFase();
    
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
            + "papro.idPasoProceso, papro.noPasoProceso, papro.pgimFaseProceso.idFaseProceso "
            + ") "
            + "FROM PgimPasoProceso papro "
            + "WHERE papro.pgimFaseProceso.idFaseProceso = :idFaseProceso "
            + "AND papro.esRegistro = '1' "
            + "AND papro.pgimFaseProceso.esRegistro = '1' "
            + "ORDER BY papro.noPasoProceso")
    List<PgimPasoProcesoDTO> listarPasosPorFase(@Param("idFaseProceso") Long idFaseProceso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTOResultado( "
            + "papro.idPasoProceso, papro.noPasoProceso, papro.pgimFaseProceso.idFaseProceso "
            + ") "
            + "FROM PgimPasoProceso papro "
            + "WHERE papro.pgimRolProceso.idRolProceso = :idRolProceso "
            + "AND papro.esRegistro = '1' "
            + "AND papro.pgimRolProceso.esRegistro = '1' "
            + "ORDER BY papro.noPasoProceso")
    List<PgimPasoProcesoDTO> listarPasosPorRol(@Param("idRolProceso") Long idRolProceso);
}