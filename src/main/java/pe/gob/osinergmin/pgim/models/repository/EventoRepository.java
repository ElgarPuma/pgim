package pe.gob.osinergmin.pgim.models.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEvento;

import java.util.List;

/**
 * Ésta interface EventoRepository incluye los metodos que nos permitira 
 * obtener las propiedades del evento por el identificador 
 * y listar las propiedades del evento.
 *
 * @descripción: Logica de negocio de la entidad Evento
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 01/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface EventoRepository extends JpaRepository<PgimEvento, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoDTOResultado( "
			+ "e.idEvento,e.pgimUnidadMinera.idUnidadMinera,e.tipoEvento.idValorParametro, "
			+ "e.agenteCausante.idValorParametro,e.tipoAccidenteMortal.idValorParametro,e.coEvento, "
			+ "e.fePresentacion,e.feEvento,e.deEvento,e.deLugarEvento,e.esNoContabilizar, "
			+ "e.deMotivoNocontabilizar,e.esRegistro,e.usCreacion,e.ipCreacion, "
			+ "e.feCreacion,e.usActualizacion,e.ipActualizacion,e.feActualizacion, "
			+ "TO_CHAR(e.fePresentacion,'dd/mm/yyyy')||'T'||TO_CHAR(e.fePresentacion,'hh24:mi'), "
			+ "TO_CHAR(e.feEvento,'dd/mm/yyyy')||'T'||TO_CHAR(e.feEvento,'hh24:mi'), "
			+ "e.tipoEvento.noValorParametro, e.tipoIncidentePeligro.idValorParametro, "
			+ "e.pgimUnidadMinera.pgimAgenteSupervisado.idAgenteSupervisado,"
			+ "(SELECT tpipx.nuExpedienteSiged FROM PgimInstanciaProces tpipx "
			+ "WHERE tpipx.idInstanciaProceso = e.pgimInstanciaProces.instanciaProcesoPadre.idInstanciaProceso )"
			+ ")"
			+ "FROM PgimEvento e "  
			+ "WHERE 1 = 1 "
			+ "AND (:idEvento IS NULL OR ( e.idEvento = :idEvento )   )")
	public PgimEventoDTO getEventoById(@Param("idEvento") Long idEvento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoDTOResultado( "
			+ "even.idEvento, even.coEvento, tpeven.noValorParametro, even.feEvento ) "
			+ "FROM PgimEvento even INNER JOIN even.tipoEvento tpeven WHERE even.esRegistro = '1' "
			+ "AND even.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera ORDER BY even.feEvento DESC")
	List<PgimEventoDTO> listarEvento(@Param("idUnidadMinera") Long idUnidadMinera);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTOResultado( "
			+ "even.pgimEvento.idEvento, even.coEvento, even.deEvento, even.noTipoEvento, even.feEvento, even.feAnioEvento, even.deLugarEvento, "
			+ "even.coUnidadMinera, even.noUnidadMinera, even.rucAs, even.noRazonSocialAs, even.noCortoAs, even.noAgenteCausante, even.noTipoAccidenteMortal ) "
			+ "FROM PgimEventoAux even "
			+ "WHERE even.esRegistro = '1' "			
			+ "AND (:feAnioEvento IS NULL OR even.feAnioEvento = :feAnioEvento) "
			+ "AND (:idTipoEvento IS NULL OR even.idTipoEvento = :idTipoEvento) "
			+ "AND (:coUnidadMinera IS NULL OR LOWER(even.coUnidadMinera) = LOWER(:coUnidadMinera)) "
			+ "AND (:noUnidadMinera IS NULL OR LOWER(even.coUnidadMinera) = LOWER(:noUnidadMinera) "
			+ "OR LOWER(even.noUnidadMinera) = LOWER(:noUnidadMinera)) ")
	Page<PgimEventoAuxDTO> listarEventosReporte(
			@Param("idTipoEvento") Long idTipoEvento,
			@Param("feAnioEvento") Long feAnioEvento,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("coUnidadMinera") String coUnidadMinera,
			Pageable paginador);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTOResultado( "
			+ "even.pgimEvento.idEvento, even.coEvento, even.deEvento, even.noTipoEvento, even.feEvento, even.feAnioEvento, even.deLugarEvento, "
			+ "even.coUnidadMinera, even.noUnidadMinera, even.rucAs, even.noRazonSocialAs, even.noCortoAs, even.noAgenteCausante, even.noTipoAccidenteMortal ) "
			+ "FROM PgimEventoAux even "
			+ "WHERE even.esRegistro = '1' "			
			+ "AND (:feAnioEvento IS NULL OR even.feAnioEvento = :feAnioEvento) "
			+ "AND (:idTipoEvento IS NULL OR even.idTipoEvento = :idTipoEvento) "
			+ "AND (:coUnidadMinera IS NULL OR LOWER(even.coUnidadMinera) = LOWER(:coUnidadMinera)) "
			+ "AND (:noUnidadMinera IS NULL OR LOWER(even.coUnidadMinera) = LOWER(:noUnidadMinera) "
			+ "OR LOWER(even.noUnidadMinera) = LOWER(:noUnidadMinera)) "			
			+ "ORDER BY even.feEvento DESC" 
			)
	List<PgimEventoAuxDTO> listarEventosTotal(
			@Param("idTipoEvento") Long idTipoEvento,
			@Param("feAnioEvento") Long feAnioEvento,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("coUnidadMinera") String coUnidadMinera);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTOResultado( "
			+ "even.pgimEvento.idEvento, even.coEvento, even.deEvento, even.noTipoEvento, even.feEvento, even.feAnioEvento, even.deLugarEvento, "
			+ "even.coUnidadMinera, even.noUnidadMinera, even.rucAs, even.noRazonSocialAs, even.noCortoAs, even.noAgenteCausante, even.noTipoAccidenteMortal ) "
			+ "FROM PgimEventoAux even "
			+ "WHERE even.esRegistro = '1' "			
			+ "AND (:feAnioEvento IS NULL OR even.feAnioEvento = :feAnioEvento) "
			+ "AND (:idTipoEvento IS NULL OR even.idTipoEvento = :idTipoEvento) "
			+ "AND (:coUnidadMinera IS NULL OR LOWER(even.coUnidadMinera) = LOWER(:coUnidadMinera)) "
			+ "AND (:noUnidadMinera IS NULL OR LOWER(even.coUnidadMinera) = LOWER(:noUnidadMinera) "
			+ "OR LOWER(even.noUnidadMinera) = LOWER(:noUnidadMinera)) "			
			)
	List<PgimEventoAuxDTO> listarEventosTotalExportar(
			@Param("idTipoEvento") Long idTipoEvento,
			@Param("feAnioEvento") Long feAnioEvento,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("coUnidadMinera") String coUnidadMinera,
			Sort sort);
	
	
//	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTOResultado( even.fe_anio_evento ) "
//			+ "FROM (SELECT DISTINCT fe_anio_evento FROM PgimEventoAux WHERE esRegistro = '1' ) even "
//			+ "ORDER BY 1 DESC")
//	List<PgimEventoAuxDTO> listarAniosEvento();
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoDTOResultado( "
			+ "e.idEvento, e.pgimUnidadMinera.idUnidadMinera, e.tipoEvento.idValorParametro, "
			+ "agca.idValorParametro, acmo.idValorParametro, e.coEvento, "
			+ "e.fePresentacion, e.feEvento, e.deEvento, e.deLugarEvento, e.esNoContabilizar, "
			+ "e.deMotivoNocontabilizar, e.tipoEvento.noValorParametro, inpe.idValorParametro, "
			+ "acmo.noValorParametro, inpe.noValorParametro "
			+ ")"
			+ "FROM PgimEvento e "
			+ "LEFT JOIN PgimValorParametro agca ON agca = e.agenteCausante "
			+ "LEFT JOIN PgimValorParametro acmo ON acmo = e.tipoAccidenteMortal "
			+ "LEFT JOIN PgimValorParametro inpe ON inpe = e.tipoIncidentePeligro "  
			+ "WHERE 1 = 1 "
			+ "AND e.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
			+ "AND e.tipoEvento.idValorParametro = :idTipoEvento ")
	public List<PgimEventoDTO> getEventosPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera,
			@Param("idTipoEvento") Long idTipoEvento);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEventoDTOResultado( "
			+ "e.idEvento ) "
			+ "FROM PgimEvento e "
			+ "WHERE e.esRegistro = '1' "
			+ "AND e.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera ")
	public List<PgimEventoDTO> listarEventoPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);

	
}
