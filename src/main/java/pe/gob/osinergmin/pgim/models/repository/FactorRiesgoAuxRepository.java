package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTO;

/**
 * @descripción: Logica de negocio de la entidad factor riesgo
 * 
 * @author: humbertoruiz90
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface FactorRiesgoAuxRepository {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFactorRiesgoDTOResultado( "
			+ "fari.idFactorRiesgoAux, fari.idGrupoRiesgo, fari.idEspecialidad, fari.idTipoOrigenDatoRiesgo, fari.noFactor, fari.deFactor, "
           // + "fari.descNoGrupo, fari.descNoEspecialidad, fari.descNoValorParametro) " //elozano columnas no validas
			+ "fari.noGrupo, fari.noEspecialidad, fari.noValorParametro) "
            + "FROM PgimFactorRiesgoAux fari "  
            + "WHERE fari.esRegistro = '1' "
            
			+ "AND (:idGrupoRiesgo IS NULL OR LOWER(fari.idGrupoRiesgo) LIKE LOWER(CONCAT('%', :idGrupoRiesgo, '%')) ) "
            + "AND (:idEspecialidad IS NULL OR LOWER(fari.idEspecialidad) LIKE LOWER(CONCAT('%', :idEspecialidad, '%')) ) "
            + "AND (:idTipoOrigenDatoRiesgo IS NULL OR LOWER(fari.idTipoOrigenDatoRiesgo) LIKE LOWER(CONCAT('%', :idTipoOrigenDatoRiesgo, '%')) ) "
            
            + "AND (:textoBusqueda IS NULL OR LOWER(fari.noFactor) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " ) ")
    Page<PgimFactorRiesgoDTO> listarFactorRiesgoAux(
    		@Param("idGrupoRiesgo") Long idGrupoRiesgo,
            @Param("idEspecialidad") Long idEspecialidad,
            @Param("idTipoOrigenDatoRiesgo") Long idTipoOrigenDatoRiesgo,
            @Param("textoBusqueda") String textoBusqueda,
            Pageable paginador);  //elozano este metodo no se utiliza
}
