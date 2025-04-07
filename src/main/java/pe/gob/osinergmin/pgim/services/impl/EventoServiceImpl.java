package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaEvento;
import pe.gob.osinergmin.pgim.models.entity.PgimEvento;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.EmpresaEventoRepository;
import pe.gob.osinergmin.pgim.models.repository.EventoRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.EventoService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Evento
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 30/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020
 */
@Service
@Transactional(readOnly = true)
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private UnidadMineraRepository unidadMineraRepository;

	@Autowired
	private ValorParametroRepository valorParametroRepository;

	@Autowired
	private EmpresaEventoRepository empresaEventoRepository;

	@Override
	public PgimEventoDTO obtenerEventoById(Long idEvento) {
		PgimEventoDTO evento = eventoRepository.getEventoById(idEvento);
		return evento;
	}

	@Transactional(readOnly = false)
	public PgimEventoDTO registrarEvento(PgimEventoDTO eventoDTO, AuditoriaDTO auditoriaDTO) {
		PgimEventoDTO pgimEventoDTO = null;
		PgimEvento evento = null;

		if (CommonsUtil.isNullOrZeroLong(eventoDTO.getIdEvento())) {
			evento = eventoRepository.findById(eventoDTO.getIdEvento()).orElse(null);
			if (evento != null) {
				/* Código: */
				evento.setCoEvento(eventoDTO.getCoEvento());
				/* Tipo*: */
				Optional<PgimValorParametro> tipoEvento = valorParametroRepository
						.findById(eventoDTO.getIdTipoEvento());
				evento.setTipoEvento(tipoEvento.get());
				/* Presentado el*: */
				evento.setFePresentacion(eventoDTO.getFePresentacion());
				/* Fec. del evento*: */
				evento.setFeEvento(eventoDTO.getFeEvento());
				/* Tipo de accidente*: */
				Optional<PgimValorParametro> tipoAccidenteMortal = valorParametroRepository
						.findById(eventoDTO.getIdTipoAccidenteMortal());
				evento.setTipoAccidenteMortal(tipoAccidenteMortal.get());
				/* Agente causante del accidente*: */
				Optional<PgimValorParametro> agenteCausante = valorParametroRepository
						.findById(eventoDTO.getIdAgenteCausante());
				evento.setAgenteCausante(agenteCausante.get());
				/* Lugar del accidente*: */
				evento.setDeLugarEvento(eventoDTO.getDeLugarEvento());
				/* Descripción*: */
				evento.setDeEvento(eventoDTO.getDeEvento());
				/* No contabilizar: */
				evento.setEsNoContabilizar(eventoDTO.getEsNoContabilizar());
				/* Motivo*: */
				evento.setDeMotivoNocontabilizar(eventoDTO.getDeMotivoNocontabilizar());

				evento.setFeActualizacion(auditoriaDTO.getFecha());
				evento.setUsActualizacion(auditoriaDTO.getUsername());
				evento.setIpActualizacion(auditoriaDTO.getTerminal());

				PgimEvento pgimEvento = eventoRepository.save(evento);
				pgimEventoDTO = obtenerEventoById(pgimEvento.getIdEvento());
			}
		} else {
			evento = new PgimEvento();

			PgimUnidadMinera unidadMinera = unidadMineraRepository.findById(eventoDTO.getIdUnidadMinera()).orElse(null);
			evento.setPgimUnidadMinera(unidadMinera);
			/* Código: */
			evento.setCoEvento(eventoDTO.getCoEvento());
			/* Tipo*: */
			Optional<PgimValorParametro> tipoEvento = valorParametroRepository.findById(eventoDTO.getIdTipoEvento());
			evento.setTipoEvento(tipoEvento.get());
			/* Presentado el*: */
			evento.setFePresentacion(eventoDTO.getFePresentacion());
			/* Fec. del evento*: */
			evento.setFeEvento(eventoDTO.getFeEvento());
			/* Tipo de accidente*: */
			Optional<PgimValorParametro> tipoAccidenteMortal = valorParametroRepository
					.findById(eventoDTO.getIdTipoAccidenteMortal());
			evento.setTipoAccidenteMortal(tipoAccidenteMortal.get());
			/* Agente causante del accidente*: */
			Optional<PgimValorParametro> agenteCausante = valorParametroRepository
					.findById(eventoDTO.getIdAgenteCausante());
			evento.setAgenteCausante(agenteCausante.get());
			/* Lugar del accidente*: */
			evento.setDeLugarEvento(eventoDTO.getDeLugarEvento());
			/* Descripción*: */
			evento.setDeEvento(eventoDTO.getDeEvento());
			/* No contabilizar: */
			evento.setEsNoContabilizar(eventoDTO.getEsNoContabilizar());
			/* Motivo*: */
			evento.setDeMotivoNocontabilizar(eventoDTO.getDeMotivoNocontabilizar());

			String anio = new SimpleDateFormat("yyyy").format(new Date());
			String coEvento = "";

			if (evento.getTipoEvento().getCoClaveTexto().equals(EValorParametro.EVENT_ACCIDNTE_MRTAL.toString())) {
				coEvento = "AM-" + anio + "-";
			} else if (evento.getTipoEvento().getCoClaveTexto().equals(EValorParametro.EVENT_INCDNTE_PLGRSO.toString())) {
				coEvento = "IP-" + anio + "-";
			}

			evento.setCoEvento(coEvento);
			evento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			evento.setFeCreacion(auditoriaDTO.getFecha());
			evento.setUsCreacion(auditoriaDTO.getUsername());
			evento.setIpCreacion(auditoriaDTO.getTerminal());

			eventoRepository.save(evento);

			String correlativo = String.format("%04d", evento.getIdEvento());

			evento.setCoEvento(coEvento + correlativo);
			PgimEvento pgimEvento = eventoRepository.save(evento);

			PgimEmpresaEvento empresaEvento = new PgimEmpresaEvento();

			empresaEvento.setPgimPersona(unidadMinera.getPgimAgenteSupervisado().getPgimPersona());
			empresaEvento.setPgimEvento(evento);
			empresaEvento.setTipoEmpresaInvolucrada(new PgimValorParametro());
			empresaEvento.getTipoEmpresaInvolucrada().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EMINV_TTLAR_MNRO.toString()));
			empresaEvento.setNuTrabajadoresM(new BigDecimal(0));
			empresaEvento.setNuTrabajadoresF(new BigDecimal(0));

			empresaEvento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			empresaEvento.setFeCreacion(auditoriaDTO.getFecha());
			empresaEvento.setUsCreacion(auditoriaDTO.getUsername());
			empresaEvento.setIpCreacion(auditoriaDTO.getTerminal());

			empresaEventoRepository.save(empresaEvento);

			pgimEventoDTO = obtenerEventoById(pgimEvento.getIdEvento());
		}
		return pgimEventoDTO;
	}

	@Transactional(readOnly = false)
	public Long eliminarEvento(Long idEvento, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimEvento evento = null;
		if (CommonsUtil.isNullOrZeroLong(idEvento)) {
			evento = eventoRepository.findById(idEvento).orElse(null);
			if (evento != null) {
				evento.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				evento.setFeActualizacion(auditoriaDTO.getFecha());
				evento.setUsActualizacion(auditoriaDTO.getUsername());
				evento.setIpActualizacion(auditoriaDTO.getTerminal());
				eventoRepository.save(evento);
				rpta = evento.getIdEvento();
			}
		}
		return rpta;
	}

	@Override
	public List<PgimEventoDTO> listarEvento(Long idUnidadMinera) {
		List<PgimEventoDTO> lista = new ArrayList<>();
		lista = eventoRepository.listarEvento(idUnidadMinera);
		return lista;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimEventoDTO crearEvento(PgimEventoDTO eventoDTO, AuditoriaDTO auditoriaDTO) {

		PgimEvento evento = new PgimEvento();

		PgimUnidadMinera unidadMinera = unidadMineraRepository.findById(eventoDTO.getIdUnidadMinera()).orElse(null);
		evento.setPgimUnidadMinera(unidadMinera);
		evento.setCoEvento(eventoDTO.getCoEvento());
		Optional<PgimValorParametro> tipoEvento = valorParametroRepository.findById(eventoDTO.getIdTipoEvento());
		evento.setTipoEvento(tipoEvento.get());
		evento.setFePresentacion(eventoDTO.getFePresentacion());
		evento.setFeEvento(eventoDTO.getFeEvento());

		if (eventoDTO.getIdTipoEvento().equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EVENT_ACCIDNTE_MRTAL.toString()))) {

			PgimValorParametro tipoAccidenteMortal = new PgimValorParametro();
			tipoAccidenteMortal.setIdValorParametro(eventoDTO.getIdTipoAccidenteMortal());
			evento.setTipoAccidenteMortal(tipoAccidenteMortal);

			PgimValorParametro agenteCausante = new PgimValorParametro();
			agenteCausante.setIdValorParametro(eventoDTO.getIdAgenteCausante());
			evento.setAgenteCausante(agenteCausante);

		} else if (eventoDTO.getIdTipoEvento().equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EVENT_INCDNTE_PLGRSO.toString()))) {

			PgimValorParametro tipoIncidentePeligro = new PgimValorParametro();
			tipoIncidentePeligro.setIdValorParametro(eventoDTO.getIdTipoIncidentePeligro());
			evento.setTipoIncidentePeligro(tipoIncidentePeligro);
		}

		evento.setDeLugarEvento(eventoDTO.getDeLugarEvento());
		evento.setDeEvento(eventoDTO.getDeEvento());
		evento.setEsNoContabilizar(eventoDTO.getEsNoContabilizar());
		evento.setDeMotivoNocontabilizar(eventoDTO.getDeMotivoNocontabilizar());

		String anio = new SimpleDateFormat("yyyy").format(new Date());
		String coEvento = "";

		if (evento.getTipoEvento().getCoClaveTexto().equals(EValorParametro.EVENT_ACCIDNTE_MRTAL.toString())) {
			coEvento = "AM-" + anio + "-";
		} else if (evento.getTipoEvento().getCoClaveTexto().equals(EValorParametro.EVENT_INCDNTE_PLGRSO.toString())) {
			coEvento = "IP-" + anio + "-";
		}

		evento.setCoEvento(coEvento);
		evento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		evento.setFeCreacion(auditoriaDTO.getFecha());
		evento.setUsCreacion(auditoriaDTO.getUsername());
		evento.setIpCreacion(auditoriaDTO.getTerminal());

		eventoRepository.save(evento);

		String correlativo = String.format("%04d", evento.getIdEvento());

		evento.setCoEvento(coEvento + correlativo);
		PgimEvento pgimEvento = eventoRepository.save(evento);

		if (eventoDTO.getIdTipoEvento() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EVENT_ACCIDNTE_MRTAL.toString())) {
			PgimEmpresaEvento empresaEvento = new PgimEmpresaEvento();

			empresaEvento.setPgimPersona(unidadMinera.getPgimAgenteSupervisado().getPgimPersona());
			empresaEvento.setPgimEvento(evento);
			empresaEvento.setTipoEmpresaInvolucrada(new PgimValorParametro());
			empresaEvento.getTipoEmpresaInvolucrada().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EMINV_TTLAR_MNRO.toString()));
			empresaEvento.setNuTrabajadoresM(new BigDecimal(0));
			empresaEvento.setNuTrabajadoresF(new BigDecimal(0));

			empresaEvento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			empresaEvento.setFeCreacion(auditoriaDTO.getFecha());
			empresaEvento.setUsCreacion(auditoriaDTO.getUsername());
			empresaEvento.setIpCreacion(auditoriaDTO.getTerminal());

			empresaEventoRepository.save(empresaEvento);
		}

		PgimEventoDTO pgimEventoDTO = obtenerEventoById(pgimEvento.getIdEvento());

		return pgimEventoDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimEventoDTO modificarEvento(PgimEventoDTO eventoDTO, AuditoriaDTO auditoriaDTO) {

		PgimEvento evento = eventoRepository.findById(eventoDTO.getIdEvento()).orElse(null);
		evento.setCoEvento(eventoDTO.getCoEvento());
		PgimValorParametro tipoEvento = new PgimValorParametro();
		tipoEvento.setIdValorParametro(eventoDTO.getIdTipoEvento());
		evento.setTipoEvento(tipoEvento);
		evento.setFePresentacion(eventoDTO.getFePresentacion());
		evento.setFeEvento(eventoDTO.getFeEvento());

		// HDT.Inicio: Ejemplo de modificación de fuentes para refactorizar el uso de los valores de párametro.

		// if
		// (eventoDTO.getIdTipoEvento().equals(ConstantesUtil.PARAM_TE_ACCIDENTE_MORTAL))
		// {
		// PgimValorParametro tipoAccidenteMortal = new PgimValorParametro();
		// tipoAccidenteMortal.setIdValorParametro(eventoDTO.getIdTipoAccidenteMortal());
		// evento.setTipoAccidenteMortal(tipoAccidenteMortal);

		// PgimValorParametro agenteCausante = new PgimValorParametro();
		// agenteCausante.setIdValorParametro(eventoDTO.getIdAgenteCausante());
		// evento.setAgenteCausante(agenteCausante);
		// } else if
		// (eventoDTO.getIdTipoEvento().equals(ConstantesUtil.PARAM_TE_INCIDENTE_PELIGROSO))
		// {
		// PgimValorParametro tipoIncidentePeligro = new PgimValorParametro();
		// tipoIncidentePeligro.setIdValorParametro(eventoDTO.getIdTipoIncidentePeligro());
		// evento.setTipoIncidentePeligro(tipoIncidentePeligro);
		// }

		if (eventoDTO.getIdTipoEvento().equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EVENT_ACCIDNTE_MRTAL.toString()))) {
			PgimValorParametro tipoAccidenteMortal = new PgimValorParametro();
			tipoAccidenteMortal.setIdValorParametro(eventoDTO.getIdTipoAccidenteMortal());
			evento.setTipoAccidenteMortal(tipoAccidenteMortal);

			PgimValorParametro agenteCausante = new PgimValorParametro();
			agenteCausante.setIdValorParametro(eventoDTO.getIdAgenteCausante());
			evento.setAgenteCausante(agenteCausante);

		} else if (eventoDTO.getIdTipoEvento().equals(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EVENT_INCDNTE_PLGRSO.toString()))) {
			PgimValorParametro tipoIncidentePeligro = new PgimValorParametro();
			tipoIncidentePeligro.setIdValorParametro(eventoDTO.getIdTipoIncidentePeligro());
			evento.setTipoIncidentePeligro(tipoIncidentePeligro);
		}

		// HDT.Fin

		evento.setDeLugarEvento(eventoDTO.getDeLugarEvento());
		evento.setDeEvento(eventoDTO.getDeEvento());
		evento.setEsNoContabilizar(eventoDTO.getEsNoContabilizar());
		evento.setDeMotivoNocontabilizar(eventoDTO.getDeMotivoNocontabilizar());

		evento.setFeActualizacion(auditoriaDTO.getFecha());
		evento.setUsActualizacion(auditoriaDTO.getUsername());
		evento.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimEvento pgimEvento = eventoRepository.save(evento);
		PgimEventoDTO pgimEventoDTO = obtenerEventoById(pgimEvento.getIdEvento());

		return pgimEventoDTO;
	}

	@Override
	public Page<PgimEventoAuxDTO> listarEventosReporte(PgimEventoAuxDTO filtroPgimEventoAuxDTO, Pageable paginador)
			throws Exception {
		Page<PgimEventoAuxDTO> lPgimEventoAuxDTO = this.eventoRepository
				.listarEventosReporte(filtroPgimEventoAuxDTO.getIdTipoEvento(),
						filtroPgimEventoAuxDTO.getFeAnioEvento(), filtroPgimEventoAuxDTO.getNoUnidadMinera(),
						filtroPgimEventoAuxDTO.getCoUnidadMinera(), paginador);
		return lPgimEventoAuxDTO;
	}

	@Override
	public List<PgimEventoAuxDTO> listarEventosTotal(PgimEventoAuxDTO filtroPgimEventoAuxDTO) throws Exception {
		List<PgimEventoAuxDTO> lPgimEventoAuxDTO = this.eventoRepository
				.listarEventosTotal(filtroPgimEventoAuxDTO.getIdTipoEvento(), filtroPgimEventoAuxDTO.getFeAnioEvento(),
						filtroPgimEventoAuxDTO.getNoUnidadMinera(), filtroPgimEventoAuxDTO.getCoUnidadMinera());
		return lPgimEventoAuxDTO;
	}

}
