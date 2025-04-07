package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTdrPlantilla;

/**
 * Ésta interface TdrPlantillaRepository incluye los metodos de obtener y listar.
 * 
 * @descripción: Lógica de negocio de la entidad TDR plantilla
 * 
 * @author hruiz
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 */
@Repository
public interface TdrPlantillaRepository extends JpaRepository<PgimTdrPlantilla, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTOResultado("
			+ "tdr.idTdrPlantilla, tdr.deTdrObjetivoTexto, tdr.deTdrAlcanceTexto, tdr.deTdrMetodologiaTexto, "
			+ "tdr.deTdrInformeSupervTexto, tdr.deTdrHonorariosProf, tdr.pgimConfiguracionBase.idConfiguracionBase, "
			+ "tdr.pgimConfiguracionBase.pgimEspecialidad.idEspecialidad, tdr.pgimConfiguracionBase.pgimEspecialidad.noEspecialidad, " 
			+ "tdr.pgimConfiguracionBase.noCofiguracionBase, tdr.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro, "
			+ "tdr.pgimConfiguracionBase.deCofiguracionBase "
			+ ") "
			+ "FROM PgimTdrPlantilla tdr "
			+ "WHERE tdr.esRegistro = '1' "
			+ "AND (:idEspecialidad IS NULL OR tdr.pgimConfiguracionBase.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
			+ "AND (:noCofiguracionBase IS NULL OR  LOWER(tdr.pgimConfiguracionBase.noCofiguracionBase) LIKE LOWER(CONCAT('%', :noCofiguracionBase, '%')) )"
			+ "AND (:textoBusqueda IS NULL OR (LOWER(tdr.pgimConfiguracionBase.pgimEspecialidad.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) "
			+ "OR LOWER (tdr.pgimConfiguracionBase.noCofiguracionBase) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) ) "
			+ "")
	Page<PgimTdrPlantillaDTO> listarTdrBase(@Param("idEspecialidad") Long idEspecialidad,
			@Param("noCofiguracionBase") String noCofiguracionBase,
			@Param("textoBusqueda") String textoBusqueda,
			Pageable paginador);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTOResultado("
			+ "tdr.idTdrPlantilla, tdr.deTdrObjetivoTexto, tdr.deTdrAlcanceTexto, tdr.deTdrMetodologiaTexto, " 
			+ "tdr.deTdrInformeSupervTexto, tdr.deTdrHonorariosProf, tdr.pgimConfiguracionBase.idConfiguracionBase, " 
			+ "tdr.pgimConfiguracionBase.pgimEspecialidad.idEspecialidad, tdr.pgimConfiguracionBase.pgimEspecialidad.noEspecialidad, " 
			+ "tdr.pgimConfiguracionBase.noCofiguracionBase, tdr.pgimConfiguracionBase.tipoConfiguracionBase.idValorParametro, "
			+ "tdr.pgimConfiguracionBase.deCofiguracionBase "
			+ ") "
			+ "FROM PgimTdrPlantilla tdr "
			+ "WHERE tdr.idTdrPlantilla = :idTdrPlantilla "
			+ "")
	PgimTdrPlantillaDTO obtenerTdrBasePorId(@Param("idTdrPlantilla") Long idTdrPlantilla);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTOResultado("			
			+ "tdr.idTdrPlantilla, tdr.pgimConfiguracionBase.idConfiguracionBase, "
			+ "tdr.deTdrObjetivoTexto, tdr.deTdrAlcanceTexto, tdr.deTdrMetodologiaTexto, tdr.deTdrInformeSupervTexto, tdr.deTdrHonorariosProf"
			+ ") "
            + "FROM PgimTdrPlantilla tdr "
            + "WHERE tdr.esRegistro = '1'"
            + "AND tdr.pgimConfiguracionBase.idConfiguracionBase = :idConfiguracionBase")
	PgimTdrPlantillaDTO obtenerTdrPlanilla(@Param("idConfiguracionBase") Long idConfiguracionBase);
	
	
	
}
