package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimHistoriaAsUm;

/**
 * Ésta interface HistoriaAsUmRepository incluye los metodos que nos permitirá listar las historias 
 * del titular y validar las fechas de inicio de titularidad.
 * 
 * @descripción: Logica de negocio de la entidad HistoriaAsUm
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 26/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface HistoriaAsUmRepository extends JpaRepository<PgimHistoriaAsUm, Long> {

	/**
	 * Permite listar los titulares de agente supervisado de la unidad minera
	 * @param idUnidadMinera
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTOResultado( "
			+ "hist.idHistoriaAsUm, hist.agenteSupervisadoPrevio.pgimPersona.coDocumentoIdentidad, hist.agenteSupervisadoPrevio.pgimPersona.noRazonSocial, "
			+ "hist.feInicioTitularidad ) "
			+ "FROM PgimHistoriaAsUm hist "
			+ "WHERE hist.esRegistro = '1' AND hist.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
			+ "ORDER BY hist.feInicioTitularidad DESC NULLS LAST")
	List<PgimHistoriaAsUmDTO> listarHistoriasAS(@Param("idUnidadMinera") Long idUnidadMinera);

	/**
	 * Permite obtener los objetos de historias de titularidad de agente fiscalizado de la unidad minera
	 * @param idHistoriaAsUm
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTOResultado("
			+ "hist.idHistoriaAsUm, hist.agenteSupervisadoPrevio.idAgenteSupervisado, hist.agenteSupervisadoPrevio.pgimPersona.noRazonSocial,  "
			+ "hist.feInicioTitularidad, hist.esRegistro ) "
			+ "FROM PgimHistoriaAsUm hist "
			+ "WHERE hist.idHistoriaAsUm = :idHistoriaAsUm")
	PgimHistoriaAsUmDTO obtenerHistoriaAsUmPorId(@Param("idHistoriaAsUm") Long idHistoriaAsUm);

	/**
	 * Esta metodo permite validar la fecha de inicio del titular que sea posterior al 
	 * anterior titular de agente supervisado de una unidad minera
	 * @param idUnidadMinera
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTOResultado("
			+ "MAX(hist.feInicioTitularidad) ) "
			+ "FROM PgimHistoriaAsUm hist "
			+ "WHERE hist.esRegistro = '1' AND hist.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera")
	PgimHistoriaAsUmDTO validarFeInicioTitularidad(@Param("idUnidadMinera") Long idUnidadMinera);
}