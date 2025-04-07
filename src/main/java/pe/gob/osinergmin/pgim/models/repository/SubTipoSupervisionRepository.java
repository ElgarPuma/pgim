package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;

/**
 * En ésta interface SubTipoSupervisionRepository esta conformado pos sus 
 * metodos de los filtros por Subtipo supervision.
 * 
 * @descripción: Lógica de negocio de la entidad Subtipo supervision
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface SubTipoSupervisionRepository extends JpaRepository<PgimSubtipoSupervision, Long> {
    
        /**
         * Me permite buscar por combo el subtipo de supervisión
         * @param nombre
         * @return
         */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
            + "subt.idSubtipoSupervision, subt.tipoSupervision.idValorParametro, subt.tipoSupervision.noValorParametro ) "
            + "FROM PgimSubtipoSupervision subt WHERE subt.esRegistro = '1' ")
    List<PgimSubtipoSupervisionDTO> filtrarPorNombreTipoSupervision(@Param("nombre") String nombre);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
            + "subt.idSubtipoSupervision, subt.deSubtipoSupervision, subt.tipoSupervision.idValorParametro, subt.pgimEspecialidad.idEspecialidad) "
            + "FROM PgimSubtipoSupervision subt WHERE subt.esRegistro = '1' ")
	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervision();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
            + "subt.idSubtipoSupervision, subt.deSubtipoSupervision, subt.tipoSupervision.idValorParametro, subt.pgimEspecialidad.idEspecialidad) "
            + "FROM PgimSubtipoSupervision subt WHERE subt.esRegistro = '1' AND subt.tipoSupervision.idValorParametro = :idTipoSupervision")
	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervision(@Param("idTipoSupervision") Long idTipoSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
            + "subt.idSubtipoSupervision, subt.deSubtipoSupervision, subt.tipoSupervision.idValorParametro, subt.pgimEspecialidad.idEspecialidad) "
            + "FROM PgimSubtipoSupervision subt WHERE subt.esRegistro = '1' ")
	List<PgimSubtipoSupervisionDTO> obtenerListaSubTipoSupervisionPorTipo();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
            + "subt.idSubtipoSupervision, subt.deSubtipoSupervision, subt.tipoSupervision.idValorParametro ) "
            + "FROM PgimSubtipoSupervision subt "
            + "WHERE subt.esRegistro = '1' "
            + "AND subt.pgimEspecialidad.idEspecialidad = :idEspecialidad ")
	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervisionPorIdEspecialidad(@Param("idEspecialidad") Long idEspecialidad);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
        + "subt.idSubtipoSupervision, subt.deSubtipoSupervision, subt.tipoSupervision.idValorParametro ) "
        + "FROM PgimSubtipoSupervision subt "
        + "WHERE subt.esRegistro = '1' "
        + "AND subt.idSubtipoSupervision = :idSubtipoSupervision ")
    PgimSubtipoSupervisionDTO obtenerSubTipoSupervisionById(@Param("idSubtipoSupervision") Long idSubtipoSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTOResultado( "
        + "subt.idSubtipoSupervision, subt.deSubtipoSupervision, subt.tipoSupervision.idValorParametro ) "
        + "FROM PgimSubtipoSupervision subt "
        + "WHERE subt.esRegistro = '1' "
        + "AND subt.flGenPrograma = '1' "
        + "AND subt.pgimEspecialidad.idEspecialidad = :idEspecialidad ")
	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSuperForGenPrg(@Param("idEspecialidad") Long idEspecialidad);

}