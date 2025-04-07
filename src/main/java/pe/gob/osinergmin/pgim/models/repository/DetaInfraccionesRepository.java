package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDetaInfraccionesAux;

/**
 * @descripción: Logica de negocio de la entidad detalle de infracciones
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface DetaInfraccionesRepository extends JpaRepository<PgimDetaInfraccionesAux, Long> {

	/**
	 * Permite obtener la lista preparada de infracciones detalladas usado en reporte correspondiente de manera paginada
	 * @param feInicio
	 * @param feFin
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE 1 = 1 "
			+ "AND (:feInicio IS NULL OR ( infra.feInicioSupervisionReal BETWEEN :feInicio AND :feFin "
			+  "OR infra.feFinSupervisionReal BETWEEN :feInicio AND :feFin))")
    Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesPaginado(
    		@Param("feInicio") Date feInicio,
    		@Param("feFin") Date feFin,
    		Pageable paginador);
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas usado en reporte correspondiente
	 * @param feInicio
	 * @param feFin
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE 1 = 1"
			+ "AND (:feInicio IS NULL OR ( infra.feInicioSupervisionReal BETWEEN :feInicio AND :feFin "
			+  "OR infra.feFinSupervisionReal BETWEEN :feInicio AND :feFin)) "
			)
	List<PgimDetaInfraccionesAuxDTO> listarReporteInfracciones(@Param("feInicio") Date feInicio,
    		@Param("feFin") Date feFin, 
    		Sort sort);
	
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por agente supervisada usado en reporte correspondiente de namera paginada
	 * @param agenteSupervisado
	 * @param diAgenteSupervisado
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE ( LOWER(infra.diAgenteSupervisado) = LOWER(:diAgenteSupervisado) or LOWER(infra.diAgenteSupervisado) = LOWER(:agenteSupervisado) ) ")
	Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesASPaginado(@Param("agenteSupervisado") String agenteSupervisado, 
			@Param("diAgenteSupervisado") String diAgenteSupervisado,
			Pageable paginador);
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por agente supervisada usado en reporte correspondiente
	 * @param agenteSupervisado
	 * @param diAgenteSupervisado
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE ( LOWER(infra.diAgenteSupervisado) = LOWER(:diAgenteSupervisado) or LOWER(infra.diAgenteSupervisado) = LOWER(:agenteSupervisado) ) "
			)
	List<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesAS(@Param("agenteSupervisado") String agenteSupervisado,
			@Param("diAgenteSupervisado") String diAgenteSupervisado,
			Sort sort);
	
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por unidad minera usado en reporte correspondiente de namera paginada
	 * @param etiquetaUnidadMinera
	 * @param coUnidadMinera
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE ( LOWER(infra.coUnidadMinera) = LOWER(:coUnidadMinera) or LOWER(infra.coUnidadMinera) = LOWER(:etiquetaUnidadMinera) ) ")
	Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesUMPaginado(@Param("etiquetaUnidadMinera") String etiquetaUnidadMinera, 
			@Param("coUnidadMinera") String coUnidadMinera,
			Pageable paginador);

	/**
	 * Permite obtener la lista preparada de infracciones detalladas por unidad minera usado en reporte correspondiente
	 * @param etiquetaUnidadMinera
	 * @param coUnidadMinera
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE ( LOWER(infra.coUnidadMinera) = LOWER(:coUnidadMinera) or LOWER(infra.coUnidadMinera) = LOWER(:etiquetaUnidadMinera) ) "
			)
	List<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesUM(@Param("etiquetaUnidadMinera") String etiquetaUnidadMinera, 
			@Param("coUnidadMinera") String coUnidadMinera,
			Sort sort);

	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por division supervisora usado en reporte correspondiente de namera paginada
	 * @param idDivisionSupervisora
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE ( infra.idDivisionSupervisora = :idDivisionSupervisora )")
	Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesDSPaginado(@Param("idDivisionSupervisora") Long idDivisionSupervisora, Pageable paginador);
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por division supervisora usado en reporte correspondiente
	 * @param idDivisionSupervisora
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE ( infra.idDivisionSupervisora = :idDivisionSupervisora )"
			)
	List<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesDS(@Param("idDivisionSupervisora") Long idDivisionSupervisora, 
			Sort sort);
	
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por especialidad usado en reporte correspondiente de namera paginada
	 * @param especialidad
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE (:especialidad IS NULL OR LOWER(infra.noEspecialidad) = LOWER(:especialidad))")
	Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesEspecPaginado(@Param("especialidad") String especialidad, Pageable paginador);
	
	/**
	 * Permite obtener la lista preparada de infracciones detalladas por especialidad usado en reporte correspondiente
	 * @param especialidad
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTOResultado( "
			+ "infra.anioSupervision, infra.feInicioSupervision, infra.feFinSupervision, infra.feInicioSupervisionReal, "
			+ "infra.feFinSupervisionReal, infra.expSigedPas, infra.etiquetaAgenteSupervisado, infra.etiquetaUnidadMinera, infra.noEspecialidad, "
			+ "infra.fechaEmisionResolucion, infra.etiquetaInfraccion, infra.noDivisionSupervisora, infra.flPropia, infra.expSigedSupervision) "
			+ "FROM PgimDetaInfraccionesAux infra "
			+ "WHERE (:especialidad IS NULL OR LOWER(infra.noEspecialidad) = LOWER(:especialidad)) "
			)
	List<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesEspec(@Param("especialidad") String especialidad, 
			Sort sort);

	
	
}
