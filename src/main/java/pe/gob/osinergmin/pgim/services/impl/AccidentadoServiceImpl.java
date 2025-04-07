package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSegAccidentadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAccidentado;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaEvento;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimSegAccidentado;
import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AccidentadoRepository;
import pe.gob.osinergmin.pgim.models.repository.SeguroAccidentadoRepository;
import pe.gob.osinergmin.pgim.services.AccidentadoService;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Accidentado
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 01/06/2020
 * @fecha_de_ultima_actualización: 02/06/2020 
 */
@Service
@Transactional(readOnly = true)
public class AccidentadoServiceImpl implements AccidentadoService {

	@Autowired
	private AccidentadoRepository accidentadoRepository;

	@Autowired
	private SeguroAccidentadoRepository seguroAccidentadoRepository;

	@Autowired
	private PersonaService personaService;

	@Override
	public List<PgimAccidentadoDTO> listarAccidentado(Long idEvento) {
		List<PgimAccidentadoDTO> lista = new ArrayList<>();
		lista = accidentadoRepository.listarAccidentado(idEvento);

		return lista;
	}

	@Override
	public List<PgimSegAccidentadoDTO> listarSegurosAccidentado(Long idAccidentado) {
		List<PgimSegAccidentadoDTO> lista = new ArrayList<>();
		lista = seguroAccidentadoRepository.listar(idAccidentado);

		return lista;
	}

	@Override
	public PgimAccidentadoDTO obtenerAccidentado(Long idAccidentado) {
		PgimAccidentadoDTO pgimAccidentadoDTO = this.accidentadoRepository.obtenerAccidentado(idAccidentado);

		return pgimAccidentadoDTO;
	}

	@Override
	public PgimAccidentado getByIdAccidentado(Long idAccidentado) {
		return this.accidentadoRepository.findById(idAccidentado).orElse(null);
	}

	@Override
	public List<PgimAccidentadoDTO> existeAccidentado(Long idAccidentado, Long idTipoDocumento,
			String numeroDocumento) {
		List<PgimAccidentadoDTO> lPgimAccidentadoDTO = this.accidentadoRepository.existeAccidentado(idAccidentado,
				idTipoDocumento, numeroDocumento);

		return lPgimAccidentadoDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimAccidentadoDTO crearAccidentado(@Valid PgimAccidentadoDTO pgimAccidentadoDTO, AuditoriaDTO auditoriaDTO) {

		PgimAccidentado pgimAccidentado = new PgimAccidentado();

		PgimPersona pgimPersona = this.asegurarPersona(pgimAccidentadoDTO,auditoriaDTO);
		this.configurarValores(pgimAccidentadoDTO, pgimAccidentado, pgimPersona);

		pgimAccidentado.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		/*pgimAccidentado.setFeCreacion(new Date());
		pgimAccidentado.setUsCreacion("admin");
		pgimAccidentado.setIpCreacion("127.0.0.1");*/
		pgimAccidentado.setFeCreacion(auditoriaDTO.getFecha());
		pgimAccidentado.setUsCreacion(auditoriaDTO.getUsername());
		pgimAccidentado.setIpCreacion(auditoriaDTO.getTerminal());

		PgimAccidentado pgimAccidentadoCreado = this.accidentadoRepository.save(pgimAccidentado);

		List<PgimSegAccidentado> lPgimSegAccidentado2Create = new ArrayList<>();
		List<PgimSegAccidentado> lPgimSegAccidentado2Delete = new ArrayList<>();

		this.identificarCambiosSeguros(pgimAccidentadoDTO, pgimAccidentadoCreado, lPgimSegAccidentado2Create,
				lPgimSegAccidentado2Delete,auditoriaDTO);

		for (PgimSegAccidentado pgimSegAccidentado2Create : lPgimSegAccidentado2Create) {
			this.seguroAccidentadoRepository.save(pgimSegAccidentado2Create);
		}

		for (PgimSegAccidentado pgimSegAccidentado2Delete : lPgimSegAccidentado2Delete) {
			this.seguroAccidentadoRepository.save(pgimSegAccidentado2Delete);
		}

		PgimAccidentadoDTO pgimAccidentadoDTOCreado = this.obtenerAccidentado(pgimAccidentadoCreado.getIdAccidentado());

		return pgimAccidentadoDTOCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimAccidentadoDTO modificarAccidentado(@Valid PgimAccidentadoDTO pgimAccidentadoDTO,
			PgimAccidentado pgimAccidentado, AuditoriaDTO auditoriaDTO) {

		PgimPersona pgimPersona = this.asegurarPersona(pgimAccidentadoDTO,auditoriaDTO);

		this.configurarValores(pgimAccidentadoDTO, pgimAccidentado, pgimPersona);
		pgimAccidentado.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		/*pgimAccidentado.setFeActualizacion(new Date());
		pgimAccidentado.setUsActualizacion("admin");
		pgimAccidentado.setIpActualizacion("127.0.0.1");*/
		pgimAccidentado.setFeActualizacion(auditoriaDTO.getFecha());
		pgimAccidentado.setUsActualizacion(auditoriaDTO.getUsername());
		pgimAccidentado.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimAccidentado pgimAccidentadoModificado = this.accidentadoRepository.save(pgimAccidentado);

		List<PgimSegAccidentado> lPgimSegAccidentado2Create = new ArrayList<>();
		List<PgimSegAccidentado> lPgimSegAccidentado2Delete = new ArrayList<>();

		this.identificarCambiosSeguros(pgimAccidentadoDTO, pgimAccidentadoModificado, lPgimSegAccidentado2Create,
				lPgimSegAccidentado2Delete,auditoriaDTO);

		for (PgimSegAccidentado pgimSegAccidentado2Create : lPgimSegAccidentado2Create) {
			this.seguroAccidentadoRepository.save(pgimSegAccidentado2Create);
		}

		for (PgimSegAccidentado pgimSegAccidentado2Delete : lPgimSegAccidentado2Delete) {
			this.seguroAccidentadoRepository.save(pgimSegAccidentado2Delete);
		}

		PgimAccidentadoDTO pgimAccidentadoDTOResultado = this
				.obtenerAccidentado(pgimAccidentadoModificado.getIdAccidentado());

		return pgimAccidentadoDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarAccidentado(PgimAccidentado pgimAccidentadoActual, AuditoriaDTO auditoriaDTO) {
		pgimAccidentadoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		/*pgimAccidentadoActual.setFeActualizacion(new Date());
		pgimAccidentadoActual.setUsActualizacion("admin");
		pgimAccidentadoActual.setIpActualizacion("127.0.0.1");*/
		pgimAccidentadoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimAccidentadoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimAccidentadoActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.accidentadoRepository.save(pgimAccidentadoActual);
	}

	private PgimPersona asegurarPersona(PgimAccidentadoDTO pgimAccidentadoDTO, AuditoriaDTO auditoriaDTO) {

		PgimPersona pgimPersona = this.personaService.obtenerPorTipoYNumero(pgimAccidentadoDTO.getDescAccidentadoIdTipoDi(), pgimAccidentadoDTO.getDescAccidentadoDnice());

		if (pgimPersona == null) {
			pgimPersona = new PgimPersona();
			/*pgimPersona.setFeCreacion(new Date());
			pgimPersona.setUsCreacion("admin");
			pgimPersona.setIpCreacion("127.0.0.1");*/
			pgimPersona.setFeCreacion(auditoriaDTO.getFecha());
			pgimPersona.setUsCreacion(auditoriaDTO.getUsername());
			pgimPersona.setIpCreacion(auditoriaDTO.getTerminal());


		} else {
			/*pgimPersona.setFeActualizacion(new Date());
			pgimPersona.setUsActualizacion("admin");
			pgimPersona.setIpActualizacion("127.0.0.1");*/
			pgimPersona.setFeActualizacion(auditoriaDTO.getFecha());
			pgimPersona.setUsActualizacion(auditoriaDTO.getUsername());
			pgimPersona.setIpActualizacion(auditoriaDTO.getTerminal());

		}

		pgimPersona.setEsRegistro(ConstantesUtil.IND_ACTIVO);

		pgimPersona.setTipoDocIdentidad(new PgimValorParametro());
		pgimPersona.getTipoDocIdentidad().setIdValorParametro(pgimAccidentadoDTO.getDescAccidentadoIdTipoDi());
		pgimPersona.setCoDocumentoIdentidad(pgimAccidentadoDTO.getDescAccidentadoDnice());

		pgimPersona.setNoPersona(pgimAccidentadoDTO.getDescAccidentadoNombres());
		pgimPersona.setApPaterno(pgimAccidentadoDTO.getDescAccidentadoApPaterno());
		pgimPersona.setApMaterno(pgimAccidentadoDTO.getDescAccidentadoApMaterno());
		pgimPersona.setTiSexo(pgimAccidentadoDTO.getDescAccidentadoSexo());
		pgimPersona.setFeNacimiento(pgimAccidentadoDTO.getDescAccidentadoFeNacimiento());
		pgimPersona.setDeTelefono(pgimAccidentadoDTO.getDescAccidentadoTelefono());

		pgimPersona.setDiPersona(pgimAccidentadoDTO.getDescAccidentadoDomicilio());
		
		if(pgimAccidentadoDTO.getDescAccidentadoIdUbigeo()!=null) {
			pgimPersona.setPgimUbigeo(new PgimUbigeo());
			pgimPersona.getPgimUbigeo().setIdUbigeo(pgimAccidentadoDTO.getDescAccidentadoIdUbigeo());
		}
		else
			pgimPersona.setPgimUbigeo(null);

		pgimPersona = this.personaService.salvar(pgimPersona);

		return pgimPersona;
	}

	private void configurarValores(PgimAccidentadoDTO pgimAccidentadoDTO, PgimAccidentado pgimAccidentado,
			PgimPersona pgimPersona) {
		pgimAccidentado.setPgimEmpresaEvento(new PgimEmpresaEvento());
		pgimAccidentado.getPgimEmpresaEvento().setIdEmpresaEvento(pgimAccidentadoDTO.getIdEmpresaEvento());

		pgimAccidentado.setPgimPersona(pgimPersona);

		pgimAccidentado.setCategoriaOcupacional(new PgimValorParametro());
		pgimAccidentado.getCategoriaOcupacional().setIdValorParametro(pgimAccidentadoDTO.getIdCategoriaOcupacional());

		pgimAccidentado.setFeFallecimiento(pgimAccidentadoDTO.getFeFallecimiento());
	}

	/***
	 * Permite identificar los cambios ocurridos para la unidad minera en lo que a
	 * sustancias se refiere.
	 * 
	 * @param pgimAccidentadoDTO
	 * @param pgimAccidentado
	 * @param lPgimSegAccidentado2Create
	 * @param lPgimSegAccidentado2Delete
	 */
	private void identificarCambiosSeguros(PgimAccidentadoDTO pgimAccidentadoDTO, PgimAccidentado pgimAccidentado,
			List<PgimSegAccidentado> lPgimSegAccidentado2Create, List<PgimSegAccidentado> lPgimSegAccidentado2Delete, AuditoriaDTO auditoriaDTO) {

		List<PgimSegAccidentadoDTO> lPgimSegAccidentadoDTO = this
				.listarSegurosAccidentado(pgimAccidentadoDTO.getIdAccidentado());

		// Procesamos las creaciones
		PgimSegAccidentado pgimSegAccidentado2Create = null;
		boolean seguroExiste = false;

		if(pgimAccidentadoDTO.getAuxAccidentadoSeguros()!=null) {
			
		
			for (PgimValorParametroDTO pgimValorParametroDTO : pgimAccidentadoDTO.getAuxAccidentadoSeguros()) {
	
				for (PgimSegAccidentadoDTO pgimSegAccidentadoDTO : lPgimSegAccidentadoDTO) {
					if (pgimSegAccidentadoDTO.getIdTipoSeguro().equals(pgimValorParametroDTO.getIdValorParametro())) {
						seguroExiste = true;
					}
				}
	
				if (!seguroExiste) {
					pgimSegAccidentado2Create = new PgimSegAccidentado();
	
					pgimSegAccidentado2Create.setPgimAccidentado(pgimAccidentado);
	
					pgimSegAccidentado2Create.setTipoSeguro(new PgimValorParametro());
					pgimSegAccidentado2Create.getTipoSeguro()
							.setIdValorParametro(pgimValorParametroDTO.getIdValorParametro());
	
					pgimSegAccidentado2Create.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					/*pgimSegAccidentado2Create.setFeCreacion(new Date());
					pgimSegAccidentado2Create.setUsCreacion("admin");
					pgimSegAccidentado2Create.setIpCreacion("127.0.0.1");*/
					pgimSegAccidentado2Create.setFeCreacion(auditoriaDTO.getFecha());
					pgimSegAccidentado2Create.setUsCreacion(auditoriaDTO.getUsername());
					pgimSegAccidentado2Create.setIpCreacion(auditoriaDTO.getTerminal());
	
	
					lPgimSegAccidentado2Create.add(pgimSegAccidentado2Create);
				}
	
				seguroExiste = false;
			}
			
		}

		// - Procesamos las eliminaciones
		PgimSegAccidentado pgimSegAccidentado2Delete = null;
		seguroExiste = false;

		for (PgimSegAccidentadoDTO pgimSegAccidentadoDTO : lPgimSegAccidentadoDTO) {

			for (PgimValorParametroDTO pgimValorParametroDTO : pgimAccidentadoDTO.getAuxAccidentadoSeguros()) {
				if (pgimSegAccidentadoDTO.getIdTipoSeguro().equals(pgimValorParametroDTO.getIdValorParametro())) {
					seguroExiste = true;
					break;
				}
			}

			if (!seguroExiste) {
				pgimSegAccidentado2Delete = this.seguroAccidentadoRepository
						.findById(pgimSegAccidentadoDTO.getIdSeguroAccidentado()).orElse(null);
				pgimSegAccidentado2Delete.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				/*pgimSegAccidentado2Delete.setFeActualizacion(new Date());
				pgimSegAccidentado2Delete.setUsActualizacion("admin");
				pgimSegAccidentado2Delete.setIpActualizacion("127.0.0.1");*/
				pgimSegAccidentado2Delete.setFeActualizacion(auditoriaDTO.getFecha());
				pgimSegAccidentado2Delete.setUsActualizacion(auditoriaDTO.getUsername());
				pgimSegAccidentado2Delete.setIpActualizacion(auditoriaDTO.getTerminal());


				lPgimSegAccidentado2Delete.add(pgimSegAccidentado2Delete);
			}

			seguroExiste = false;
		}

	}

}
