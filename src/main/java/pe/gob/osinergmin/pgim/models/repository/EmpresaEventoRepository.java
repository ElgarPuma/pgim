package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaEvento;

/**
 * Esta interface EmpresaEventoRepository que me permitirá aplicar los metodos de listar las empresas de un evento,
 * listar por palabra de la empresa de un evento y obtener sus propiedades de la empresa de un evento.
 * 
 * @descripción: Logica de negocio de la entidad Empresa evento
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface EmpresaEventoRepository extends JpaRepository<PgimEmpresaEvento, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTOResultado("
			+ "emev.idEmpresaEvento, emev.pgimPersona.coDocumentoIdentidad, emev.pgimPersona.noRazonSocial, "
			+ "emev.tipoEmpresaInvolucrada.noValorParametro, emev.nuTrabajadoresF, emev.nuTrabajadoresM, emev.tipoEmpresaInvolucrada.idValorParametro)"
			+ "FROM PgimEmpresaEvento emev " + "WHERE emev.esRegistro = '1' AND emev.pgimEvento.idEvento = :idEvento "
			+ "ORDER BY emev.tipoEmpresaInvolucrada.noValorParametro DESC")
	List<PgimEmpresaEventoDTO> listarEmpresaEvento(@Param("idEvento") Long idEvento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noRazonSocial) " 
			+ "FROM PgimPersona pers "
			+ "WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.coClaveTexto = :DOIDE_RUC "
			+ "AND NOT EXISTS (SELECT 1 " 
			+ "FROM PgimEmpresaEvento emev " 
			+ "WHERE emev.esRegistro = '1' "
			+ "AND emev.pgimEvento.idEvento = :idEvento " 
			+ "AND emev.pgimPersona = pers) "
			+ "AND NOT EXISTS (SELECT 1 " 
			+ "FROM PgimAgenteSupervisado agsu " 
			+ "WHERE agsu.esRegistro = '1' "
			+ "AND agsu.pgimPersona = pers) "
			+ "AND (LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabra, '%')) "
			+ "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :palabra, '%')) )")
	List<PgimEmpresaEventoDTO> listarPorPalabraClave(@Param("idEvento") Long idEvento,
			@Param("palabra") String palabra, @Param("DOIDE_RUC") String DOIDE_RUC);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTOResultado("
			+ "emev.idEmpresaEvento, emev.pgimPersona.idPersona, emev.pgimPersona.coDocumentoIdentidad, emev.pgimPersona.noRazonSocial, "
			+ "emev.tipoEmpresaInvolucrada.noValorParametro, emev.nuTrabajadoresF, emev.nuTrabajadoresM, "
			+ "tiac.idValorParametro, emev.deActEconomicaPrincipal, emev.tipoEmpresaInvolucrada.idValorParametro)"
			+ "FROM PgimEmpresaEvento emev LEFT OUTER JOIN emev.tipoActvidadCiiu tiac "
			+ "WHERE emev.esRegistro = '1' AND emev.idEmpresaEvento = :idEmpresaEvento")
	PgimEmpresaEventoDTO obtenerEmpresaEvento(@Param("idEmpresaEvento") Long idEmpresaEvento);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTOResultado("
			+ "emev.idEmpresaEvento ) "
			+ "FROM PgimEmpresaEvento emev " 
			+ "WHERE emev.esRegistro = '1' "
			+ "AND emev.pgimPersona.idPersona = :idPersona ")
	List<PgimEmpresaEventoDTO> listarEmpresaEventoPorPersona(@Param("idPersona") Long idPersona);
	
	/**
	 * Permite listar las empresas asociadas a Eventos registrados como motivos de una supervisión, 
	 * empresas candidatas para ser seleccionables como responsable de una infracción; es decir,
	 * de tipo contratista y que aún no haya sido registrado como responsable de la infracción.
	 * 
	 * @param idSupervision
	 * @param idTipoEvento
	 * @param idInfraccion
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTOResultado("
			+ "emev.idEmpresaEvento, emev.pgimPersona.idPersona, emev.pgimPersona.coDocumentoIdentidad, emev.pgimPersona.noRazonSocial)"
			+ "FROM PgimEmpresaEvento emev " 
			+ "INNER JOIN emev.pgimEvento ev "
			+ "INNER JOIN PgimSupervisionMotivo sm ON (sm.idObjetoMotivoInicio = ev.idEvento "
			+ 		"AND sm.pgimTipoMotivoInicio.idValorParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.TIPO_MOTIVO_EVENTO) "
			+ "WHERE 1=1"
			+ "AND emev.esRegistro = '1' "
			+ "AND ev.esRegistro = '1' "
			+ "AND sm.esRegistro = '1' "
			+ "AND ev.tipoEvento.idValorParametro = :idTipoEvento "
			+ "AND sm.pgimSupervision.idSupervision = :idSupervision "
			// Empresas involucradas en el(los) evento(s) de la supervisión, de tipo Contratista
			+ "AND emev.tipoEmpresaInvolucrada.idValorParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TEI_CONTRATISTA "
			// Que aún no hayan sido registradas como responsable de la infracción
			+ "AND NOT EXISTS (SELECT 1 " 
			+ "		FROM PgimInfraccionContra infc " 
			+ "		WHERE 1=1 "
			+ "		AND infc.esRegistro = '1' "
			+ "		AND infc.flVigente = '1' "
			+ "		AND infc.pgimPersona.idPersona = emev.pgimPersona.idPersona "
			+ "		AND infc.pgimInfraccion.idInfraccion = :idInfraccion"
			+ ") "
			+ "ORDER BY emev.pgimPersona.noRazonSocial ")
	List<PgimEmpresaEventoDTO> listarEmpresaEventoSelecResponsableInfraccion(
			@Param("idSupervision") Long idSupervision, 
			@Param("idTipoEvento") Long idTipoEvento,
			@Param("idInfraccion") Long idInfraccion
			);
}
