package pe.gob.osinergmin.pgim.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.dtos.Tarifario;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimCostoUnitario;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioRegla;
import pe.gob.osinergmin.pgim.models.repository.CostoUnitarioRepository;
import pe.gob.osinergmin.pgim.models.repository.TarifarioContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.TarifarioReglaRepository;
import pe.gob.osinergmin.pgim.services.CostoUnitarioService;
import pe.gob.osinergmin.pgim.services.TarifarioContratoService;
import pe.gob.osinergmin.pgim.services.TarifarioReglaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Tarifario contrato
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Transactional(readOnly = true)
public class TarifarioContratoServiceImpl implements TarifarioContratoService {

	@Autowired
	private TarifarioContratoRepository tarifarioContratoRepository;

	@Autowired
	private TarifarioReglaRepository tarifarioReglaRepository;

	@Autowired
	private TarifarioReglaService tarifarioReglaService;

	@Autowired
	private CostoUnitarioRepository costoUnitarioRepository;

	@Autowired
	private CostoUnitarioService costoUnitarioService;

	@Override
	public List<PgimTarifarioContratoDTO> listarTarifarioContrato(Long idContrato) {
		List<PgimTarifarioContratoDTO> lPgimTarifarioContratoDTO = this.tarifarioContratoRepository
				.listarTarifarioContrato(idContrato);

		return lPgimTarifarioContratoDTO;
	}

	@Override
	public PgimTarifarioContratoDTO obtenerTarifarioContratoPorId(Long idTarifarioContrato) {
		return this.tarifarioContratoRepository.obtenerTarifarioContratoPorId(idTarifarioContrato);
	}

	@Transactional(readOnly = false)
	@Override
	public Tarifario crearTarifario(Tarifario tarifario, AuditoriaDTO auditoriaDTO) {
		Tarifario tarifarioCreado = new Tarifario();

		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOCreado = new LinkedList<PgimTarifarioReglaDTO>();
		List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTOCreado = new LinkedList<PgimCostoUnitarioDTO>();

		PgimTarifarioContrato pgimTarifarioContrato = generarPgimTarifarioContrato(
				tarifario.getPgimTarifarioContratoDTO(), auditoriaDTO);
		PgimTarifarioContrato pgimTarifarioContratoCreado = tarifarioContratoRepository.save(pgimTarifarioContrato);
		PgimTarifarioContratoDTO pgimTarifarioContratoDTOCreado = this
				.obtenerTarifarioContratoPorId(pgimTarifarioContratoCreado.getIdTarifarioContrato());

		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = tarifario.getlPgimTarifarioReglaDTO();
		for (PgimTarifarioReglaDTO tarifarioReglaDTO : lPgimTarifarioReglaDTO) {
			tarifarioReglaDTO.setIdTarifarioContrato(pgimTarifarioContratoDTOCreado.getIdTarifarioContrato());
			PgimTarifarioRegla pgimTarifarioRegla = generarPgimTarifarioRegla(tarifarioReglaDTO, auditoriaDTO, false);
			PgimTarifarioRegla pgimTarifarioReglaCreado = tarifarioReglaRepository.save(pgimTarifarioRegla);
			PgimTarifarioReglaDTO pgimTarifarioReglaDTOCreado = tarifarioReglaService
					.obtenerTarifarioReglaPorId(pgimTarifarioReglaCreado.getIdTarifarioRegla());
			lPgimTarifarioReglaDTOCreado.add(pgimTarifarioReglaDTOCreado);
		}

		List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTO = tarifario.getlPgimCostoUnitarioDTO();
		for (PgimCostoUnitarioDTO costoUnitarioDTO : lPgimCostoUnitarioDTO) {
			costoUnitarioDTO.setIdTarifarioContrato(pgimTarifarioContratoDTOCreado.getIdTarifarioContrato());
			
			PgimCostoUnitario pgimCostoUnitario = generarPgimCostoUnitario(costoUnitarioDTO, auditoriaDTO, false);

			PgimCostoUnitario pgimCostoUnitarioCreado = costoUnitarioRepository.save(pgimCostoUnitario);
			PgimCostoUnitarioDTO pgimCostoUnitarioDTOCreado = costoUnitarioService
					.obtenerCostoUnitarioPorId(pgimCostoUnitarioCreado.getIdCostoUnitario());
			lPgimCostoUnitarioDTOCreado.add(pgimCostoUnitarioDTOCreado);
		}

		tarifarioCreado.setPgimTarifarioContratoDTO(pgimTarifarioContratoDTOCreado);
		tarifarioCreado.setlPgimTarifarioReglaDTO(lPgimTarifarioReglaDTOCreado);
		tarifarioCreado.setlPgimCostoUnitarioDTO(lPgimCostoUnitarioDTOCreado);

		return tarifarioCreado;
	}

	@Override
	public Tarifario obtenerTarifario(Long idTarifarioContrato) {
		Tarifario tarifario = new Tarifario();

		PgimTarifarioContratoDTO pgimTarifarioContratoDTO = this.obtenerTarifarioContratoPorId(idTarifarioContrato);

		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = tarifarioReglaService
				.obtenerTarifarioReglaPorIdTarifarioContrato(idTarifarioContrato);
				
		List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTO = costoUnitarioService
				.obtenerCostoUnitarioPorIdTarifarioContrato(idTarifarioContrato);

		tarifario.setPgimTarifarioContratoDTO(pgimTarifarioContratoDTO);
		tarifario.setlPgimTarifarioReglaDTO(lPgimTarifarioReglaDTO);
		tarifario.setlPgimCostoUnitarioDTO(lPgimCostoUnitarioDTO);

		return tarifario;
	}

	private PgimTarifarioContrato generarPgimTarifarioContrato(PgimTarifarioContratoDTO pgimTarifarioContratoDTO,
			AuditoriaDTO auditoriaDTO) {
		PgimTarifarioContrato pgimTarifarioContrato = new PgimTarifarioContrato();

		if (pgimTarifarioContratoDTO.getIdTarifarioContrato() != null) {
			pgimTarifarioContrato = tarifarioContratoRepository
					.findById(pgimTarifarioContratoDTO.getIdTarifarioContrato()).orElse(null);

			pgimTarifarioContrato.setFeActualizacion(auditoriaDTO.getFecha());
			pgimTarifarioContrato.setUsActualizacion(auditoriaDTO.getUsername());
			pgimTarifarioContrato.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {
			PgimContrato pgimContrato = new PgimContrato();
			pgimContrato.setIdContrato(pgimTarifarioContratoDTO.getIdContrato());
			pgimTarifarioContrato.setPgimContrato(pgimContrato);

			pgimTarifarioContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimTarifarioContrato.setFeCreacion(auditoriaDTO.getFecha());
			pgimTarifarioContrato.setUsCreacion(auditoriaDTO.getUsername());
			pgimTarifarioContrato.setIpCreacion(auditoriaDTO.getTerminal());
		}

		pgimTarifarioContrato.setNoTarifario(pgimTarifarioContratoDTO.getNoTarifario());
		pgimTarifarioContrato.setMoSupervisionFallida(pgimTarifarioContratoDTO.getMoSupervisionFallida());

		return pgimTarifarioContrato;
	}

	private PgimTarifarioRegla generarPgimTarifarioRegla(PgimTarifarioReglaDTO pgimTarifarioReglaDTO,
			AuditoriaDTO auditoriaDTO, boolean eliminar) {
		PgimTarifarioRegla pgimTarifarioRegla = new PgimTarifarioRegla();

		if (pgimTarifarioReglaDTO.getIdTarifarioRegla() != null) {
			pgimTarifarioRegla = tarifarioReglaRepository.findById(pgimTarifarioReglaDTO.getIdTarifarioRegla())
					.orElse(null);

			pgimTarifarioRegla.setFeActualizacion(auditoriaDTO.getFecha());
			pgimTarifarioRegla.setUsActualizacion(auditoriaDTO.getUsername());
			pgimTarifarioRegla.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {
			PgimTarifarioContrato tarifarioContrato = new PgimTarifarioContrato();
			tarifarioContrato.setIdTarifarioContrato(pgimTarifarioReglaDTO.getIdTarifarioContrato());
			pgimTarifarioRegla.setPgimTarifarioContrato(tarifarioContrato);

			pgimTarifarioRegla.setFeCreacion(auditoriaDTO.getFecha());
			pgimTarifarioRegla.setUsCreacion(auditoriaDTO.getUsername());
			pgimTarifarioRegla.setIpCreacion(auditoriaDTO.getTerminal());
		}

		if (eliminar) {
			pgimTarifarioRegla.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		} else {
			pgimTarifarioRegla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		}

		PgimSubtipoSupervision pgimSubtipoSupervision = new PgimSubtipoSupervision();
		pgimSubtipoSupervision.setIdSubtipoSupervision(pgimTarifarioReglaDTO.getIdSubtipoSupervision());
		pgimTarifarioRegla.setPgimSubtipoSupervision(pgimSubtipoSupervision);

		PgimMotivoSupervision pgimMotivoSupervision = new PgimMotivoSupervision();
		pgimMotivoSupervision.setIdMotivoSupervision(pgimTarifarioReglaDTO.getIdMotivoSupervision());
		pgimTarifarioRegla.setPgimMotivoSupervision(pgimMotivoSupervision);

		return pgimTarifarioRegla;
	}

	private PgimCostoUnitario generarPgimCostoUnitario(PgimCostoUnitarioDTO pgimCostoUnitarioDTO,
			AuditoriaDTO auditoriaDTO, boolean eliminar) {
		PgimCostoUnitario pgimCostoUnitario = new PgimCostoUnitario();

		if (pgimCostoUnitarioDTO.getIdCostoUnitario() != null) {
			pgimCostoUnitario = costoUnitarioRepository.findById(pgimCostoUnitarioDTO.getIdCostoUnitario())
					.orElse(null);

			pgimCostoUnitario.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCostoUnitario.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCostoUnitario.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {
			PgimTarifarioContrato tarifarioContrato = new PgimTarifarioContrato();
			tarifarioContrato.setIdTarifarioContrato(pgimCostoUnitarioDTO.getIdTarifarioContrato());
			pgimCostoUnitario.setPgimTarifarioContrato(tarifarioContrato);

			pgimCostoUnitario.setFeCreacion(auditoriaDTO.getFecha());
			pgimCostoUnitario.setUsCreacion(auditoriaDTO.getUsername());
			pgimCostoUnitario.setIpCreacion(auditoriaDTO.getTerminal());
		}

		if (eliminar) {
			pgimCostoUnitario.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		} else {
			pgimCostoUnitario.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		}

		pgimCostoUnitario.setUnDiaCostoUnitario(pgimCostoUnitarioDTO.getUnDiaCostoUnitario());
		pgimCostoUnitario.setMoCostoUnitario(pgimCostoUnitarioDTO.getMoCostoUnitario());
		pgimCostoUnitario.setMoCostoActa(pgimCostoUnitarioDTO.getMoCostoActa());
		pgimCostoUnitario.setMoCostoInformeSupervision(pgimCostoUnitarioDTO.getMoCostoInformeSupervision());
		pgimCostoUnitario.setMoCostoInformeGestion(pgimCostoUnitarioDTO.getMoCostoInformeGestion());

		return pgimCostoUnitario;
	}

	@Transactional(readOnly = false)
	@Override
	public Tarifario modificarTarifario(Tarifario tarifario, AuditoriaDTO auditoriaDTO) {
		Tarifario tarifarioModificado = new Tarifario();

		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOModificado = new LinkedList<PgimTarifarioReglaDTO>();
		List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTOModificado = new LinkedList<PgimCostoUnitarioDTO>();

		PgimTarifarioContrato pgimTarifarioContrato = generarPgimTarifarioContrato(
				tarifario.getPgimTarifarioContratoDTO(), auditoriaDTO);

		PgimTarifarioContrato pgimTarifarioContratoModificado = tarifarioContratoRepository.save(pgimTarifarioContrato);

		PgimTarifarioContratoDTO pgimTarifarioContratoDTOModificado = this
				.obtenerTarifarioContratoPorId(pgimTarifarioContratoModificado.getIdTarifarioContrato());

		// Crear o modificar
		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTO = tarifario.getlPgimTarifarioReglaDTO();

		for (PgimTarifarioReglaDTO tarifarioReglaDTO : lPgimTarifarioReglaDTO) {
			PgimTarifarioRegla pgimTarifarioRegla = generarPgimTarifarioRegla(tarifarioReglaDTO, auditoriaDTO, false);
			PgimTarifarioRegla pgimTarifarioReglaModificado = tarifarioReglaRepository.save(pgimTarifarioRegla);

			PgimTarifarioReglaDTO pgimTarifarioReglaDTOModificado = tarifarioReglaService
					.obtenerTarifarioReglaPorId(pgimTarifarioReglaModificado.getIdTarifarioRegla());

			lPgimTarifarioReglaDTOModificado.add(pgimTarifarioReglaDTOModificado);
		}

		// Eliminar registros tarifario regla
		List<PgimTarifarioReglaDTO> listaTotalPgimTarifarioReglaDTO = tarifarioReglaService
				.obtenerTarifarioReglaPorIdTarifarioContrato(pgimTarifarioContratoModificado.getIdTarifarioContrato());

		for (PgimTarifarioReglaDTO listaTotal : listaTotalPgimTarifarioReglaDTO) {
			boolean soloExisteEnTotal = true;

			for (PgimTarifarioReglaDTO listaModificada : lPgimTarifarioReglaDTOModificado) {
				if (listaTotal.getIdTarifarioRegla().equals(listaModificada.getIdTarifarioRegla())) {
					soloExisteEnTotal = false;
					break;
				}
			}

			if (soloExisteEnTotal) {
				PgimTarifarioRegla pgimTarifarioRegla = generarPgimTarifarioRegla(listaTotal, auditoriaDTO, true);
				tarifarioReglaRepository.save(pgimTarifarioRegla);
			}
		}

		// Crear o modificar
		List<PgimCostoUnitarioDTO> lPgimCostoUnitarioDTO = tarifario.getlPgimCostoUnitarioDTO();

		for (PgimCostoUnitarioDTO costoUnitarioDTO : lPgimCostoUnitarioDTO) {
			PgimCostoUnitario pgimCostoUnitario = this.generarPgimCostoUnitario(costoUnitarioDTO, auditoriaDTO, false);
			PgimCostoUnitario pgimCostoUnitarioModificado = this.costoUnitarioRepository.save(pgimCostoUnitario);

			PgimCostoUnitarioDTO pgimCostoUnitarioDTOModificado = costoUnitarioService
					.obtenerCostoUnitarioPorId(pgimCostoUnitarioModificado.getIdCostoUnitario());
			lPgimCostoUnitarioDTOModificado.add(pgimCostoUnitarioDTOModificado);
		}

		// Eliminar registros costo unitario
		List<PgimCostoUnitarioDTO> listaTotalPgimCostoUnitarioDTO = costoUnitarioService
				.obtenerCostoUnitarioPorIdTarifarioContrato(pgimTarifarioContratoModificado.getIdTarifarioContrato());

		for (PgimCostoUnitarioDTO listaTotal : listaTotalPgimCostoUnitarioDTO) {
			boolean soloExisteEnTotal = true;
			for (PgimCostoUnitarioDTO listaModificada : lPgimCostoUnitarioDTOModificado) {
				if (listaTotal.getIdCostoUnitario().intValue() == listaModificada.getIdCostoUnitario().intValue()) {
					soloExisteEnTotal = false;
					break;
				}
			}

			if (soloExisteEnTotal) {
				PgimCostoUnitario pgimCostoUnitario = this.generarPgimCostoUnitario(listaTotal, auditoriaDTO, true);
				costoUnitarioRepository.save(pgimCostoUnitario);
			}
		}

		tarifarioModificado.setPgimTarifarioContratoDTO(pgimTarifarioContratoDTOModificado);
		tarifarioModificado.setlPgimTarifarioReglaDTO(lPgimTarifarioReglaDTOModificado);
		tarifarioModificado.setlPgimCostoUnitarioDTO(lPgimCostoUnitarioDTOModificado);

		return tarifarioModificado;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarTarifario(PgimTarifarioContrato pgimTarifarioContrato, AuditoriaDTO auditoriaDTO) {

		pgimTarifarioContrato.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimTarifarioContrato.setFeActualizacion(auditoriaDTO.getFecha());
		pgimTarifarioContrato.setUsActualizacion(auditoriaDTO.getUsername());
		pgimTarifarioContrato.setIpActualizacion(auditoriaDTO.getTerminal());

		this.tarifarioContratoRepository.save(pgimTarifarioContrato);
	}

	@Override
	public PgimTarifarioContrato getByIdTarifarioContrato(Long idTarifarioContrato) {
		return this.tarifarioContratoRepository.findById(idTarifarioContrato).orElse(null);
	}

	@Override
	public List<PgimTarifarioReglaDTO> listarTarifariosCumplenRegla(Long idContrato, Long idSubtipoSupervision,
			Long idMotivoSupervision) {

		return this.tarifarioReglaRepository.listarTarifariosCumplenRegla(idContrato, idSubtipoSupervision,
				idMotivoSupervision);
	}

	@Override
	public List<PgimTarifarioReglaDTO> validarCrearTarifario(Tarifario tarifario) {
		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOResultado = new LinkedList<PgimTarifarioReglaDTO>();

		PgimTarifarioContratoDTO pgimTarifarioContratoDTO = tarifario.getPgimTarifarioContratoDTO();

		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOActual = tarifarioReglaRepository
				.obtenerTarifarioReglaPorIdContrato(pgimTarifarioContratoDTO.getIdContrato());
		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOValidar = tarifario.getlPgimTarifarioReglaDTO();

		for (PgimTarifarioReglaDTO tarifarioReglaDTOValidar : lPgimTarifarioReglaDTOValidar) {
			for (PgimTarifarioReglaDTO pgimTarifarioReglaDTOActual : lPgimTarifarioReglaDTOActual) {
				if (tarifarioReglaDTOValidar.getIdSubtipoSupervision().intValue() == pgimTarifarioReglaDTOActual
						.getIdSubtipoSupervision().intValue()
						&& tarifarioReglaDTOValidar.getIdMotivoSupervision().intValue() == pgimTarifarioReglaDTOActual
								.getIdMotivoSupervision().intValue()) {
					lPgimTarifarioReglaDTOResultado.add(tarifarioReglaDTOValidar);
				}
			}
		}

		return lPgimTarifarioReglaDTOResultado;
	}

	@Override
	public List<PgimTarifarioReglaDTO> validarModificarTarifario(Tarifario tarifario) {
		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOResultado = new LinkedList<PgimTarifarioReglaDTO>();

		PgimTarifarioContratoDTO pgimTarifarioContratoDTO = tarifario.getPgimTarifarioContratoDTO();

		// Lista que contiene todas las reglas que le pertenezcan a ese contrato
		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOActual = tarifarioReglaRepository
				.obtenerTarifarioReglaPorIdContrato(pgimTarifarioContratoDTO.getIdContrato());
		
				// Lista que viene del front end
		List<PgimTarifarioReglaDTO> lPgimTarifarioReglaDTOValidar = tarifario.getlPgimTarifarioReglaDTO();

		for (PgimTarifarioReglaDTO tarifarioReglaDTOValidar : lPgimTarifarioReglaDTOValidar) {

			if (tarifarioReglaDTOValidar.getIdTarifarioRegla() == null) {

				for (PgimTarifarioReglaDTO pgimTarifarioReglaDTOActual : lPgimTarifarioReglaDTOActual) {

					if (tarifarioReglaDTOValidar.getIdSubtipoSupervision().intValue() == pgimTarifarioReglaDTOActual
							.getIdSubtipoSupervision().intValue()
							&& tarifarioReglaDTOValidar.getIdMotivoSupervision()
									.intValue() == pgimTarifarioReglaDTOActual.getIdMotivoSupervision().intValue()) {
						lPgimTarifarioReglaDTOResultado.add(tarifarioReglaDTOValidar);
					}

				}

			}
		}

		return lPgimTarifarioReglaDTOResultado;
	}

}
