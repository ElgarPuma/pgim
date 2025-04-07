package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguraRiesgoAux;

/**
 * @descripción: Lógica de negocio de la entidad configuraciones de riesgo
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface ConfiguraRiesgoAuxRepository extends JpaRepository<PgimConfiguraRiesgoAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoAuxDTOResultado(" 
	     + "cori.idConfiguraRiesgoAux, cori.idConfiguraRiesgoPadre, cori.idEspecialidad, "
             + "cori.noEspecialidad, cori.idTipoEstadoConfiguracion, cori.noValorParametro, "
             + "cori.noConfiguracion, cori.deConfiguracion, cori.feConfiguracion, "
             + "cori.feConfiguracionAnio, cori.idTipoConfiguracionRiesgo, cori.noTipoConfiguracionRiesgo " 
             + ") "
             + "FROM PgimConfiguraRiesgoAux cori "  
             + "WHERE cori.esRegistro = '1' "            
             + "AND (:feConfiguracionAnio IS NULL OR LOWER(cori.feConfiguracionAnio) LIKE LOWER(CONCAT('%', :feConfiguracionAnio, '%')) ) "
             + "AND (:idEspecialidad IS NULL OR LOWER(cori.idEspecialidad) LIKE LOWER(CONCAT('%', :idEspecialidad, '%')) ) "
             + "AND (:idTipoEstadoConfiguracion IS NULL OR LOWER(cori.idTipoEstadoConfiguracion) LIKE LOWER(CONCAT('%', :idTipoEstadoConfiguracion, '%')) ) "            
             + "AND (:textoBusqueda IS NULL OR LOWER(cori.noConfiguracion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
             + " ) ")
    Page<PgimConfiguraRiesgoAuxDTO> listarConfiguraRiesgoAux(
            @Param("feConfiguracionAnio") String feConfiguracionAnio,
            @Param("idEspecialidad") Long idEspecialidad,
            @Param("idTipoEstadoConfiguracion") Long idTipoEstadoConfiguracion,
            @Param("textoBusqueda") String textoBusqueda,
            Pageable paginador);
}
