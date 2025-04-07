package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonaConsorcio;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsi;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsiCargo;
import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadOrganica;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.PersonaConsorcioRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiCargoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.siged.Usuario;
import pe.gob.osinergmin.pgim.siged.UsuarioSiged;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Persona
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 30/05/2020
 * @fecha_de_ultima_actualización: 30/05/2020
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	PersonaConsorcioRepository personaConsorcioRepository;

	@Autowired
	private SigedSoapService sigedSoapService;

	@Autowired
	private PersonalOsiRepository personalOsiRepository;

	@Autowired
	private PersonalOsiCargoRepository personalOsiCargoRepository;

	@Override
	public PgimPersona obtenerPorTipoYNumero(Long idTipoDocumentoIdentidad, String coDocumentoIdentidad) {
		return this.personaRepository.obtenerPorTipoYNumero(idTipoDocumentoIdentidad, coDocumentoIdentidad);
	}

	@Override
	public PgimPersona salvar(PgimPersona pgimPersona) {
		return this.personaRepository.save(pgimPersona);
	}

	@Override
	public PgimPersona obtenerPorTipoYRuc(String noRazonSocial) {
		return this.personaRepository.obtenerPorTipoYRuc(noRazonSocial);
	}

	@Override
	public PgimPersona getByIdPersona(Long idPersona) {
		return this.personaRepository.findById(idPersona).orElse(null);
	}

	@Override
	public List<PgimPersonaDTO> listarPersonaJuridicaPorPalabraClave(String palabraClave) {
		return this.personaRepository.listarPersonaJuridicaPorPalabraClave(palabraClave);
	}

	@Override
	public List<PgimPersonaDTO> listarPorPersona(String palabraClave) {
		return this.personaRepository.listarPorPersona(palabraClave);
	}

	@Override
	public UsuarioSiged obtenerUsuarioSiged(Usuario usuario) throws Exception {
		return sigedSoapService.obtenerUsuario(usuario);
	}

	@Override
	public Page<PgimPersonaDTO> listarPersonas(PgimPersonaDTO filtroPersona, Pageable paginador) {
		Page<PgimPersonaDTO> pPgimPersonaDTO = this.personaRepository.listarPersonas(filtroPersona.getNoRazonSocial(),
				filtroPersona.getCoDocumentoIdentidad(), filtroPersona.getIdTipoDocIdentidad(),
				filtroPersona.getFlConsorcio(), filtroPersona.getTextoBusqueda(), paginador);
		return pPgimPersonaDTO;
	}

	@Override
	public List<PgimPersonaDTO> listarPorCoDocumentoIdentidad(String palabra) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository.listarPorCoDocumentoIdentidad(palabra);

		return lPgimPersonaDTO;
	}

	@Override
	public List<PgimPersonaDTO> listarPorNoRazonSocial(String palabra) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository.listarPorNoRazonSocial(palabra);

		return lPgimPersonaDTO;
	}

	/**
	 * 
	 * @param palabra
	 * @return
	 */
	@Override
	public List<PgimPersonaDTO> listarPorRazonSocial(String palabra) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository.listarPorRazonSocial(palabra, EValorParametro.DOIDE_RUC.toString());

		return lPgimPersonaDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonaDTO crearPersona(PgimPersonaDTO pgimPersonaDTO, AuditoriaDTO auditoriaDTO) throws Exception {
		PgimPersona pgimPersona = new PgimPersona();

		this.configurarValores(pgimPersonaDTO, pgimPersona);

		pgimPersona.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimPersona.setFeCreacion(auditoriaDTO.getFecha());
		pgimPersona.setUsCreacion(auditoriaDTO.getUsername());
		pgimPersona.setIpCreacion(auditoriaDTO.getTerminal());

		// ¿Es consorcio?
		pgimPersona.setFlConsorcio(pgimPersonaDTO.getFlConsorcio());

		// Identificación
		pgimPersona.setNoRazonSocial(pgimPersonaDTO.getNoRazonSocial().toUpperCase());
		pgimPersona.setNoCorto(pgimPersonaDTO.getNoCorto());
		pgimPersona.setNoPrefijoPersona(pgimPersonaDTO.getNoPrefijoPersona());
		pgimPersona.setTiSexo(pgimPersonaDTO.getTiSexo());
		pgimPersona.setFeNacimiento(pgimPersonaDTO.getFeNacimiento());
		pgimPersona.setDeRestriccion(pgimPersonaDTO.getDeRestriccion());

		// Contacto
		pgimPersona.setDeTelefono(pgimPersonaDTO.getDeTelefono());
		pgimPersona.setDeTelefono2(pgimPersonaDTO.getDeTelefono2());
		pgimPersona.setDeCorreo(pgimPersonaDTO.getDeCorreo());
		pgimPersona.setDeCorreo2(pgimPersonaDTO.getDeCorreo2());

		// Ubicación
		pgimPersona.setDiPersona(pgimPersonaDTO.getDiPersona());

		// Notificaciones electrónicas
		pgimPersona.setFlAfiliadoNtfccionElctrnca(pgimPersonaDTO.getFlAfiliadoNtfccionElctrnca());
		pgimPersona.setDeCorreoNtfccionElctrnca(pgimPersonaDTO.getDeCorreoNtfccionElctrnca());

		// Otros
		pgimPersona.setCmNota(pgimPersonaDTO.getCmNota());

		// Ubicación
		if (pgimPersonaDTO.getIdUbigeo() != null) {
			PgimUbigeo pgimUbigeo = new PgimUbigeo();
			pgimUbigeo.setIdUbigeo(pgimPersonaDTO.getIdUbigeo());
			pgimPersona.setPgimUbigeo(pgimUbigeo);
		} else {
			pgimPersona.setPgimUbigeo(null);
		}

		// Notificaciones electrónicas
		pgimPersona.setFeAfiliadoDesde(pgimPersonaDTO.getFeAfiliadoDesde());
		pgimPersona.setFuentePersonaNatural(new PgimValorParametro());
		pgimPersona.getFuentePersonaNatural().setIdValorParametro(pgimPersonaDTO.getIdFuentePersonaNatural());

		PgimPersona pgimPersonaCreado = this.personaRepository.save(pgimPersona);

		PgimPersonaDTO pgimPersonaDTOCreado = this.obtenerPersonalNatuJuriPorId(pgimPersonaCreado.getIdPersona());

		return pgimPersonaDTOCreado;
	}

	@Transactional(readOnly = false)
	private PgimPersona configurarValores(PgimPersonaDTO pgimPersonaDTO, PgimPersona pgimPersona) throws Exception {

		// Identificación
		try {
			pgimPersona.setTipoDocIdentidad(new PgimValorParametro());
			pgimPersona.getTipoDocIdentidad().setIdValorParametro(pgimPersonaDTO.getIdTipoDocIdentidad());
			pgimPersona.setCoDocumentoIdentidad(pgimPersonaDTO.getCoDocumentoIdentidad());
			pgimPersona.setNoPersona(pgimPersonaDTO.getNoPersona().toUpperCase());
			pgimPersona.setApPaterno(pgimPersonaDTO.getApPaterno().toUpperCase());
			pgimPersona.setApMaterno(pgimPersonaDTO.getApMaterno().toUpperCase());
			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return pgimPersona;

	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonaDTO modificarPersona(PgimPersonaDTO pgimPersonaDTO, PgimPersona pgimPersona,
			AuditoriaDTO auditoriaDTO) throws Exception {

		pgimPersona = this.configurarValores(pgimPersonaDTO, pgimPersona);

		pgimPersona.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersona.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersona.setIpActualizacion(auditoriaDTO.getTerminal());

		// ¿Es consorcio?
		pgimPersona.setFlConsorcio(pgimPersonaDTO.getFlConsorcio());
	
		// Identificación
		if (pgimPersonaDTO.getNoRazonSocial() != null) {
			pgimPersona.setNoRazonSocial(pgimPersonaDTO.getNoRazonSocial().toUpperCase());
			pgimPersona.setNoCorto(pgimPersonaDTO.getNoCorto());
		}
		pgimPersona.setNoPrefijoPersona(pgimPersonaDTO.getNoPrefijoPersona());
		pgimPersona.setTiSexo(pgimPersonaDTO.getTiSexo());
		pgimPersona.setFeNacimiento(pgimPersonaDTO.getFeNacimiento());
		pgimPersona.setDeRestriccion(pgimPersonaDTO.getDeRestriccion());

		// Contacto
		pgimPersona.setDeTelefono(pgimPersonaDTO.getDeTelefono());
		pgimPersona.setDeTelefono2(pgimPersonaDTO.getDeTelefono2());
		pgimPersona.setDeCorreo(pgimPersonaDTO.getDeCorreo());
		pgimPersona.setDeCorreo2(pgimPersonaDTO.getDeCorreo2());

		// Ubicación
		pgimPersona.setDiPersona(pgimPersonaDTO.getDiPersona());

		// Notificaciones electrónicas
		pgimPersona.setFlAfiliadoNtfccionElctrnca(pgimPersonaDTO.getFlAfiliadoNtfccionElctrnca());
		pgimPersona.setDeCorreoNtfccionElctrnca(pgimPersonaDTO.getDeCorreoNtfccionElctrnca());

		// Otros
		pgimPersona.setCmNota(pgimPersonaDTO.getCmNota());

		// Ubicación
		if (pgimPersonaDTO.getIdUbigeo() != null) {
			PgimUbigeo pgimUbigeo = new PgimUbigeo();
			pgimUbigeo.setIdUbigeo(pgimPersonaDTO.getIdUbigeo());
			pgimPersona.setPgimUbigeo(pgimUbigeo);
		} else {
			pgimPersona.setPgimUbigeo(null);
		}

		// Notificaciones electrónicas
		pgimPersona.setFeAfiliadoDesde(pgimPersonaDTO.getFeAfiliadoDesde());
		pgimPersona.setFuentePersonaNatural(new PgimValorParametro());
		pgimPersona.getFuentePersonaNatural().setIdValorParametro(pgimPersonaDTO.getIdFuentePersonaNatural());

		PgimPersona pgimPersonaModificado = this.personaRepository.save(pgimPersona);
		PgimPersonaDTO pgimPersonaDTOResultado = this
				.obtenerPersonalNatuJuriPorId(pgimPersonaModificado.getIdPersona());

		return pgimPersonaDTOResultado;

	}

	@Override
	public PgimPersonaDTO obtenerPersonalNatuJuriPorId(Long idPersona) {
		return this.personaRepository.obtenerPersonalNatuJuriPorId(idPersona);
	}

	@Override
	public PgimPersonaDTO obtenerPersona(Long idPersona) {
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarPersona(PgimPersona pgimPersonaActual, AuditoriaDTO auditoriaDTO) {
		pgimPersonaActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

		pgimPersonaActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonaActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonaActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.personaRepository.save(pgimPersonaActual);

	}

	@Override
	public List<PgimPersonaDTO> existePersona(Long idPersona, Long idTipoDocumento, String numeroDocumento) {
		List<PgimPersonaDTO> lPgimAccidentadoDTO = this.personaRepository.existePersona(idPersona, idTipoDocumento,
				numeroDocumento);

		return lPgimAccidentadoDTO;
	}

	@Override
	public List<PgimPersonaDTO> listarPersonalNaturalPorNoRazonSocial(Long idAgenteSupervisado, String palabra) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository
				.listarPersonalNaturalPorNoRazonSocial(idAgenteSupervisado, palabra, EValorParametro.DOIDE_DNI.toString());
		return lPgimPersonaDTO;
	}

	@Override
	public List<PgimPersonaDTO> listarPersonalNaturalPorNoRazonSocialSuper(Long idEmpresaSupervisora, String palabra) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository
				.listarPersonalNaturalPorNoRazonSocialSuper(idEmpresaSupervisora, palabra, EValorParametro.DOIDE_DNI.toString());
		return lPgimPersonaDTO;
	}

	@Override
	public List<PgimPersonaDTO> listarPersonaNaturalPorPersonalOsi(String palabra) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository
				.listarPersonaNaturalPorPersonalOsi(palabra, EValorParametro.DOIDE_DNI.toString());
		return lPgimPersonaDTO;
	}

	@Override
	public Page<PgimPersonaConsorcioDTO> listarConsorcios(Long idPersonaJuridicaConsorcio,
			Pageable paginador) {
		Page<PgimPersonaConsorcioDTO> pPgimPersonaConsorcioDTO = this.personaConsorcioRepository
				.listarConsorcios(idPersonaJuridicaConsorcio, paginador);
		return pPgimPersonaConsorcioDTO;
	}

	@Override
	public PgimPersonaConsorcioDTO obtenerConsorcioPorId(Long idPersonaConsorcio) {
		return this.personaConsorcioRepository.obtenerConsorcioPorId(idPersonaConsorcio);
	}

	@Override
	public List<PgimPersonaConsorcioDTO> listarPorPersonaJuridica(Long idPersona, String palabraClave) {
		List<PgimPersonaConsorcioDTO> lPgimPersonaConsorcioDTO = this.personaConsorcioRepository
				.listarPorPersonaJuridica(idPersona, palabraClave);

		return lPgimPersonaConsorcioDTO;
	}

	@Override
	public PgimPersonaConsorcio getByIdPersonaConsorcio(Long idPersonaConsorcio) {
		return this.personaConsorcioRepository.findById(idPersonaConsorcio).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonaConsorcioDTO crearPersonaConsorcio(PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {
		PgimPersonaConsorcio pgimPersonaConsorcio = new PgimPersonaConsorcio();

		this.configurarValoresConsorcio(pgimPersonaConsorcioDTO, pgimPersonaConsorcio);

		pgimPersonaConsorcio.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimPersonaConsorcio.setFeCreacion(auditoriaDTO.getFecha());
		pgimPersonaConsorcio.setUsCreacion(auditoriaDTO.getUsername());
		pgimPersonaConsorcio.setIpCreacion(auditoriaDTO.getTerminal());

		// Consorcio
		pgimPersonaConsorcio.setPersonaJuridicaConsorcio(new PgimPersona());
		pgimPersonaConsorcio.getPersonaJuridicaConsorcio()
				.setIdPersona(pgimPersonaConsorcioDTO.getIdPersonaJuridicaConsorcio());
		pgimPersonaConsorcio.setFlEsPrincipal(pgimPersonaConsorcioDTO.getFlEsPrincipal());

		PgimPersonaConsorcio pgimPersonaConsorcioCreado = this.personaConsorcioRepository.save(pgimPersonaConsorcio);

		PgimPersonaConsorcioDTO pgimPersonaConsorcioDTOCreado = this
				.obtenerConsorcioPorId(pgimPersonaConsorcioCreado.getIdPersonaConsorcio());

		return pgimPersonaConsorcioDTOCreado;
	}

	@Transactional(readOnly = false)
	private void configurarValoresConsorcio(PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO,
			PgimPersonaConsorcio pgimPersonaConsorcio) throws Exception {

		// Persona jurídica
		if (pgimPersonaConsorcioDTO.getIdPersona() != null) {
			PgimPersona pgimPersona = new PgimPersona();
			pgimPersona.setIdPersona(pgimPersonaConsorcioDTO.getIdPersona());
			pgimPersonaConsorcio.setPgimPersona(pgimPersona);
		} else {
			pgimPersonaConsorcio.setPgimPersona(null);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarPersonaConsorcio(PgimPersonaConsorcio pgimPersonaConsorcioActual, AuditoriaDTO auditoriaDTO) {
		pgimPersonaConsorcioActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

		pgimPersonaConsorcioActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonaConsorcioActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonaConsorcioActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.personaConsorcioRepository.save(pgimPersonaConsorcioActual);
	}

	@Override
	public List<PgimPersonaDTO> existePersonaJuridica(Long idPersona, String noRazonSocial) {
		List<PgimPersonaDTO> lPgimPersonaDTO = this.personaRepository
				.existePersonaJuridica(idPersona, noRazonSocial.toUpperCase());
		return lPgimPersonaDTO;
	}

	@Override
	public List<PgimPersonalOsiDTO> existeUsuario(Long idPersonalOsi, String noUsuario) {
		List<PgimPersonalOsiDTO> lPgimPersonalOsiDTO = this.personalOsiRepository
				.existeUsuario(idPersonalOsi, noUsuario.toUpperCase());
		return lPgimPersonalOsiDTO;
	}

	@Override
	public Page<PgimPersonalOsiDTO> listarPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO, Pageable paginador)
			throws Exception {
		Page<PgimPersonalOsiDTO> pPgimPersonalOsiDTO = this.personalOsiRepository
				.listarPersonalOsi(pgimPersonalOsiDTO.getFlActivo(), pgimPersonalOsiDTO.getTextoBusqueda(), paginador);
		return pPgimPersonalOsiDTO;
	}

	@Override
	public PgimPersonalOsiDTO obtenerPersonalOsigPorId(Long idPersonalOsi) {
		return this.personalOsiRepository.obtenerPersonalOsigPorId(idPersonalOsi);
	}

	@Override
	public PgimPersonalOsi getByIdPersonalOsig(Long idPersonalOsi) {
		return this.personalOsiRepository.findById(idPersonalOsi).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonalOsiDTO crearPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

		PgimPersonalOsi pgimPersonalOsi = new PgimPersonalOsi();

		pgimPersonalOsi.setPgimPersona(new PgimPersona());
		pgimPersonalOsi.getPgimPersona().setIdPersona(pgimPersonalOsiDTO.getIdPersona());

		pgimPersonalOsi.setNoUsuario(pgimPersonalOsiDTO.getNoUsuario().toUpperCase());
		pgimPersonalOsi.setFlActivo("1");

		pgimPersonalOsi.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimPersonalOsi.setFeCreacion(auditoriaDTO.getFecha());
		pgimPersonalOsi.setUsCreacion(auditoriaDTO.getUsername());
		pgimPersonalOsi.setIpCreacion(auditoriaDTO.getTerminal());

		PgimPersonalOsi pgimPersonalOsiCreado = personalOsiRepository.save(pgimPersonalOsi);

		PgimPersonalOsiDTO pgimPersonalOsiDTOCreado = this
				.obtenerPersonalOsigPorId(pgimPersonalOsiCreado.getIdPersonalOsi());

		return pgimPersonalOsiDTOCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonalOsiDTO modificarPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO,
			PgimPersonalOsi pgimPersonalOsi, AuditoriaDTO auditoriaDTO) {

		pgimPersonalOsi.setPgimPersona(new PgimPersona());
		pgimPersonalOsi.getPgimPersona().setIdPersona(pgimPersonalOsiDTO.getIdPersona());

		pgimPersonalOsi.setNoUsuario(pgimPersonalOsiDTO.getNoUsuario().toUpperCase());

		pgimPersonalOsi.setFlActivo(pgimPersonalOsiDTO.getFlActivo());

		pgimPersonalOsi.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonalOsi.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonalOsi.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimPersonalOsi pgimPersonalOsiModificado = personalOsiRepository
				.save(pgimPersonalOsi);

		PgimPersonalOsiDTO pgimPersonalOsiDTOResultado = obtenerPersonalOsigPorId(
				pgimPersonalOsiModificado.getIdPersonalOsi());

		return pgimPersonalOsiDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonalOsiDTO desactivarPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO,
	PgimPersonalOsi pgimPersonalOsi, AuditoriaDTO auditoriaDTO) {

		pgimPersonalOsi.setFlActivo(pgimPersonalOsiDTO.getFlActivo());

		pgimPersonalOsi.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonalOsi.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonalOsi.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimPersonalOsi pgimPersonalOsiModificado = personalOsiRepository
				.save(pgimPersonalOsi);

		PgimPersonalOsiDTO pgimPersonalOsiDTOResultado = obtenerPersonalOsigPorId(
				pgimPersonalOsiModificado.getIdPersonalOsi());

		return pgimPersonalOsiDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonaConsorcioDTO modificarFlPrincipalConsorcio(PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO, PgimPersonaConsorcio pgimPersonaConsorcioActual,
			AuditoriaDTO auditoriaDTO) throws Exception {

		PgimPersonaConsorcioDTO pgimPersonaConsorcioDTOPrincipal = this.personaConsorcioRepository.obtenerConsorcioPrincipal(pgimPersonaConsorcioDTO.getIdPersonaJuridicaConsorcio());
		if(pgimPersonaConsorcioDTOPrincipal != null  && pgimPersonaConsorcioDTO.getFlEsPrincipal().equals(ConstantesUtil.IND_ACTIVO)){
			PgimPersonaConsorcio pgimPersonaConsorcioPrincipal = this.getByIdPersonaConsorcio(pgimPersonaConsorcioDTOPrincipal.getIdPersonaConsorcio());
			pgimPersonaConsorcioPrincipal.setFlEsPrincipal(ConstantesUtil.IND_INACTIVO);
			pgimPersonaConsorcioPrincipal.setFeActualizacion(auditoriaDTO.getFecha());
			pgimPersonaConsorcioPrincipal.setUsActualizacion(auditoriaDTO.getUsername());
			pgimPersonaConsorcioPrincipal.setIpActualizacion(auditoriaDTO.getTerminal());  
			this.personaConsorcioRepository.save(pgimPersonaConsorcioPrincipal);
			
		}

		pgimPersonaConsorcioActual.setFlEsPrincipal(pgimPersonaConsorcioDTO.getFlEsPrincipal());
		pgimPersonaConsorcioActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonaConsorcioActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonaConsorcioActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.personaConsorcioRepository.save(pgimPersonaConsorcioActual);

		PgimPersonaConsorcioDTO pgimPersonaConsorcioDTOModificado = this.obtenerConsorcioPorId(pgimPersonaConsorcioActual.getIdPersonaConsorcio());
		return pgimPersonaConsorcioDTOModificado;
	}

	@Override
	public List<PgimPersonalOsiCargoDTO> listarPersonalOsiCargo(Long idPersonalOsi) {
		List<PgimPersonalOsiCargoDTO> lPgimPersonalOsiCargoDTO = this.personalOsiCargoRepository.listarPersonalOsiCargo(idPersonalOsi);
		return lPgimPersonalOsiCargoDTO;
	}

	@Override
	public PgimPersonalOsiCargoDTO obtenerPersonalOsiCargoPorId(Long idPersonalOsiCargo) {
		PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO = this.personalOsiCargoRepository.obtenerPersonalOsiCargoPorId(idPersonalOsiCargo);
		return pgimPersonalOsiCargoDTO;
	}

	@Override
	public PgimPersonalOsiCargo getPersonalOsiCargoById(Long idPersonalOsiCargo) {
		PgimPersonalOsiCargo pgimPersonalOsiCargo = this.personalOsiCargoRepository.findById(idPersonalOsiCargo).orElse(null);
		return pgimPersonalOsiCargo;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonalOsiCargoDTO crearPersonalOsiCargo(PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO, AuditoriaDTO auditoriaDTO) throws Exception {

		PgimPersonalOsiCargo pgimPersonalOsiCargo = new PgimPersonalOsiCargo();

		pgimPersonalOsiCargo.setPgimPersonalOsi(new PgimPersonalOsi());
		pgimPersonalOsiCargo.getPgimPersonalOsi().setIdPersonalOsi(pgimPersonalOsiCargoDTO.getIdPersonalOsi());

		pgimPersonalOsiCargo.setPgimUnidadOrganica(new PgimUnidadOrganica());
		pgimPersonalOsiCargo.getPgimUnidadOrganica().setIdUnidadOrganica(pgimPersonalOsiCargoDTO.getIdUnidadOrganica());

		pgimPersonalOsiCargo.setNoCargo(pgimPersonalOsiCargoDTO.getNoCargo());
		pgimPersonalOsiCargo.setFeInicio(pgimPersonalOsiCargoDTO.getFeInicio());
		pgimPersonalOsiCargo.setFeFin(pgimPersonalOsiCargoDTO.getFeFin());
		pgimPersonalOsiCargo.setFlPrincipal(pgimPersonalOsiCargoDTO.getFlPrincipal());

		pgimPersonalOsiCargo.setNuExpedienteSiged(pgimPersonalOsiCargoDTO.getNuExpedienteSiged());
		pgimPersonalOsiCargo.setCoTipoDocumentoSiged(pgimPersonalOsiCargoDTO.getCoTipoDocumentoSiged());
		pgimPersonalOsiCargo.setNuDocumentoSiged(pgimPersonalOsiCargoDTO.getNuDocumentoSiged());

		pgimPersonalOsiCargo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimPersonalOsiCargo.setFeCreacion(auditoriaDTO.getFecha());
		pgimPersonalOsiCargo.setUsCreacion(auditoriaDTO.getUsername());
		pgimPersonalOsiCargo.setIpCreacion(auditoriaDTO.getTerminal());

		PgimPersonalOsiCargo pgimPersonalOsiCargoCreado = personalOsiCargoRepository.save(pgimPersonalOsiCargo);

		PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTOCreado = this.obtenerPersonalOsiCargoPorId(pgimPersonalOsiCargoCreado.getIdPersonalOsiCargo());

		return pgimPersonalOsiCargoDTOCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimPersonalOsiCargoDTO modificarPersonalOsiCargo(PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO, PgimPersonalOsiCargo pgimPersonalOsiCargo, AuditoriaDTO auditoriaDTO) throws Exception {

		pgimPersonalOsiCargo.setPgimPersonalOsi(new PgimPersonalOsi());
		pgimPersonalOsiCargo.getPgimPersonalOsi().setIdPersonalOsi(pgimPersonalOsiCargoDTO.getIdPersonalOsi());

		pgimPersonalOsiCargo.setPgimUnidadOrganica(new PgimUnidadOrganica());
		pgimPersonalOsiCargo.getPgimUnidadOrganica().setIdUnidadOrganica(pgimPersonalOsiCargoDTO.getIdUnidadOrganica());
		
		pgimPersonalOsiCargo.setNoCargo(pgimPersonalOsiCargoDTO.getNoCargo());
		pgimPersonalOsiCargo.setFeInicio(pgimPersonalOsiCargoDTO.getFeInicio());
		pgimPersonalOsiCargo.setFeFin(pgimPersonalOsiCargoDTO.getFeFin());
		pgimPersonalOsiCargo.setFlPrincipal(pgimPersonalOsiCargoDTO.getFlPrincipal());

		pgimPersonalOsiCargo.setNuExpedienteSiged(pgimPersonalOsiCargoDTO.getNuExpedienteSiged());
		pgimPersonalOsiCargo.setCoTipoDocumentoSiged(pgimPersonalOsiCargoDTO.getCoTipoDocumentoSiged());
		pgimPersonalOsiCargo.setNuDocumentoSiged(pgimPersonalOsiCargoDTO.getNuDocumentoSiged());
		
		pgimPersonalOsiCargo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonalOsiCargo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonalOsiCargo.setIpActualizacion(auditoriaDTO.getTerminal());


		PgimPersonalOsiCargo pgimPersonalOsiCargoModificado = personalOsiCargoRepository.save(pgimPersonalOsiCargo);

		PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTOModificado = this.obtenerPersonalOsiCargoPorId(pgimPersonalOsiCargoModificado.getIdPersonalOsiCargo());

		return pgimPersonalOsiCargoDTOModificado;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarPersonalOsiCargo(PgimPersonalOsiCargo pgimPersonalOsiCargo, AuditoriaDTO auditoriaDTO) {
		pgimPersonalOsiCargo.setEsRegistro(ConstantesUtil.IND_INACTIVO);

		pgimPersonalOsiCargo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimPersonalOsiCargo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimPersonalOsiCargo.setIpActualizacion(auditoriaDTO.getTerminal());

		this.personalOsiCargoRepository.save(pgimPersonalOsiCargo);

	}
}