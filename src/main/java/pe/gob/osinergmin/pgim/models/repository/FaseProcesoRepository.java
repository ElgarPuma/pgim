package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimFaseProceso;

import java.util.List;

/**
 * En ésta interface FaseProcesoRepository esta conformado pos sus metodos de 
 * aplicacion de los filtros por nombres de fase de proceso y
 * obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Fase de proceso
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface FaseProcesoRepository extends JpaRepository<PgimFaseProceso, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTOResultado( "
            + "fapro.idFaseProceso, fapro.pgimProceso.idProceso, fapro.noFaseProceso ) " + "FROM PgimFaseProceso fapro WHERE fapro.esRegistro = '1' ")
    List<PgimFaseProcesoDTO> filtrarPorNombreFaseProceso(@Param("nombre") String nombre);

    /**
     * Permite obtener los nombres de la fase de procesos
     * @param idProceso identificador del proceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTOResultado( "
            + "fapro.idFaseProceso, fapro.pgimProceso.idProceso, fapro.noFaseProceso ) " 
            + "FROM PgimFaseProceso fapro "
            + "WHERE fapro.pgimProceso.idProceso = :idProceso " 
            + "AND fapro.esRegistro = '1' "
            + "ORDER BY fapro.idFaseProceso")
    List<PgimFaseProcesoDTO> obtenerFasesPorIdProceso(@Param("idProceso") Long idProceso);
   
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTOResultado( "
            + "fapro.idFaseProceso, fapro.pgimProceso.idProceso, fapro.noFaseProceso, fapro.deFaseProceso ) " 
            + "FROM PgimFaseProceso fapro "
            + "WHERE fapro.idFaseProceso = :idFaseProceso " 
            + "AND fapro.esRegistro = '1' "
            )
    PgimFaseProcesoDTO obtenerFaseProcesoPorId(@Param("idFaseProceso") Long idFaseProceso);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTOResultado( "
            + "fapro.idFaseProceso, fapro.pgimProceso.idProceso, fapro.noFaseProceso ) " 
            + "FROM PgimFaseProceso fapro "
            + "WHERE fapro.esRegistro = '1' "
            + "ORDER BY fapro.idFaseProceso")
    List<PgimFaseProcesoDTO> obtenerFasesPorProceso();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTOResultado( "
            + "fapro.idFaseProceso, fapro.pgimProceso.idProceso, fapro.noFaseProceso, fapro.deFaseProceso ) " 
            + "FROM PgimFaseProceso fapro "
            + "WHERE fapro.pgimProceso.idProceso = :idProceso "
            + "AND fapro.esRegistro = '1' "
            )
    List<PgimFaseProcesoDTO> listarFasesProceso(@Param("idProceso") Long idProceso, Sort sort);

    /**
     * Permite obtener las fases de la instancia del proceso por las que transitó el flujo.
     * @param idInstanciaProceso Identificador interno de la instancia del proceso.
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTOResultado( "
            + "fapr.idFaseProceso, fapr.pgimProceso.idProceso, fapr.noFaseProceso ) " 
            + "FROM PgimFaseProceso fapr "
            + "WHERE EXISTS " 
            + "( " 
            + "SELECT 1 " 
            + "FROM PgimInstanciaPaso inpa " 
            + "INNER JOIN inpa.pgimInstanciaProces inpr " 
            + "INNER JOIN inpa.pgimRelacionPaso repa " 
            + "INNER JOIN repa.pasoProcesoOrigen ppor " 
            + "INNER JOIN repa.pasoProcesoDestino ppde " 
            + "WHERE inpr.idInstanciaProceso = :idInstanciaProceso "
            + "AND (ppor.pgimFaseProceso.idFaseProceso = fapr.idFaseProceso " 
            + "OR ppde.pgimFaseProceso.idFaseProceso = fapr.idFaseProceso) " 
            + "AND inpa.esRegistro = '1' " 
            + "AND inpr.esRegistro = '1' " 
            + "AND repa.esRegistro = '1' " 
            + "AND ppor.esRegistro = '1' " 
            + "AND ppde.esRegistro = '1' " 
            + ") " 
            + "AND fapr.esRegistro = '1' "
            + "ORDER BY fapr.noFaseProceso")    
    List<PgimFaseProcesoDTO> obtenerFasesInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);

}