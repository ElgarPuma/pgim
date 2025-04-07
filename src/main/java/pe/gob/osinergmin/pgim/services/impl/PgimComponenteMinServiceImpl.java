package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoSubcomponenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCmineroSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoSubcomponente;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.CmineroSprvsionRepository;
import pe.gob.osinergmin.pgim.models.repository.ComponenteHcRepository;
import pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.TipoSubcomponenteRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.PgimComponenteMinService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Componente Minero
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 30/05/2020
 * @fecha_de_ultima_actualización: 30/05/2020
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class PgimComponenteMinServiceImpl implements PgimComponenteMinService {

	@Autowired
	private PgimComponenteMinRepository componenteMineroRepository;

	@Autowired
	private ValorParametroRepository valorParametroRepository;

	@Autowired
	private TipoSubcomponenteRepository tipoSubcomponenteRepository;
	
	@Autowired
	private SupervisionRepository supervisionRepository;
	
	@Autowired
	private CmineroSprvsionRepository cmineroSprvsionRepository;
	
	@Autowired
	private ComponenteHcRepository componenteHcRepository;

	@Override
	public List<PgimComponenteMineroDTO> listarComponenteMinero(Long idUnidadMinera, Sort sort) {

		List<PgimComponenteMineroDTO> lista = new ArrayList<>();

		lista = componenteMineroRepository.listarComponenteMinero(idUnidadMinera, sort);

		return lista;
	}

	@Override
	public List<PgimComponenteMinero> listarComponenteMineroAll() {
		// List<ComponenteMinero> listaCM = componenteMineroRepository.findAll();
		return componenteMineroRepository.findAll();

	}

	@Transactional(readOnly = false)
	@Override
	public PgimComponenteMineroDTO crearComponenteMinero(@Valid PgimComponenteMineroDTO pgimComponenteMineroDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

		PgimValorParametro pgimValorParametro = new PgimValorParametro();
		PgimComponenteMinero pgimComponenteMinero = new PgimComponenteMinero();
		this.configurarValores(pgimComponenteMineroDTO, pgimComponenteMinero);
		
		pgimValorParametro = this.valorParametroRepository
				.findById(pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro()).orElse(null);
		
		if(pgimValorParametro == null){
			pgimValorParametro = new PgimValorParametro();
		}
	
		if(pgimComponenteMinero.getCoComponente() == null) {

			String coComponente = "";
	
			if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_PLNTA_BNFICIO.toString())) {
				coComponente = "PLB" + "-";
			} else if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_TJO_ABIERTO.toString())) {
				coComponente = "TAJ" + "-";
			} else if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_RLVRA.toString())) {
				coComponente = "RLV" + "-";
			} else if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_PLA_LXVIACION.toString())) {
				coComponente = "LIX" + "-";
			} else if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_DSMNTRA.toString())) {
				coComponente = "DMT" + "-";
			} else if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_CNTRA.toString())) {
				coComponente = "CMT" + "-";
			} else if (pgimComponenteMinero.getTipoComponenteMinero().getIdValorParametro() == this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.COMIN_PIQUE.toString())) {
				coComponente = "PIQ" + "-";
			}
		
			pgimComponenteMinero.setCoComponente(coComponente);
	
			Long ultimaSecuencia = 0L;
	
			String correlativo = "";
	
			Long nuevaSecuencia = ultimaSecuencia + 1;
	
			if (pgimValorParametro.getNuValorNumerico() == null) {
				correlativo = String.format("%05d", Long.valueOf(0));
			} else {
				correlativo = String.format("%05d", Long.valueOf(pgimValorParametro.getNuValorNumerico().longValue() + nuevaSecuencia));
			}
	
			pgimComponenteMinero.setCoComponente(coComponente + correlativo);
	
			if (pgimValorParametro.getNuValorNumerico() == null) {
				pgimValorParametro.setNuValorNumerico(new BigDecimal(0));
			} else {
				pgimValorParametro.setNuValorNumerico(pgimValorParametro.getNuValorNumerico().add(new BigDecimal(nuevaSecuencia)));
			}
		
		}else {
			
			if (pgimValorParametro.getNuValorNumerico() == null || pgimValorParametro.getNuValorNumerico().longValue() < pgimComponenteMinero.getDescNuValorNumerico() ) {
				pgimValorParametro.setNuValorNumerico(new BigDecimal(pgimComponenteMinero.getDescNuValorNumerico()));
			}	
		}
		
		pgimComponenteMinero.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimComponenteMinero.setFeCreacion(auditoriaDTO.getFecha());
		pgimComponenteMinero.setUsCreacion(auditoriaDTO.getUsername());
		pgimComponenteMinero.setIpCreacion(auditoriaDTO.getTerminal());
		
		PgimComponenteMinero pgimComponenteMineroCreado = this.componenteMineroRepository.save(pgimComponenteMinero);

		PgimComponenteMineroDTO pgimComponenteMineroDTOCreado = this
				.obtenerComponenteMineroPorId(pgimComponenteMineroCreado.getIdComponenteMinero());

		return pgimComponenteMineroDTOCreado;
	}

	private void configurarValores(PgimComponenteMineroDTO pgimComponenteMineroDTO,
			PgimComponenteMinero pgimComponenteMinero) {

		pgimComponenteMinero.setPgimUnidadMinera(new PgimUnidadMinera());
		pgimComponenteMinero.getPgimUnidadMinera().setIdUnidadMinera(pgimComponenteMineroDTO.getIdUnidadMinera());

		pgimComponenteMinero.setTipoComponenteMinero(new PgimValorParametro());
		pgimComponenteMinero.getTipoComponenteMinero()
				.setIdValorParametro(pgimComponenteMineroDTO.getIdTipoComponenteMinero());
		
		if(pgimComponenteMineroDTO.getIdComponenteMineroPadre() != null) { 
			PgimComponenteMinero componenteMineroPadre = new PgimComponenteMinero();
	        componenteMineroPadre.setIdComponenteMinero(pgimComponenteMineroDTO.getIdComponenteMineroPadre());
	        pgimComponenteMinero.setComponenteMineroPadre(componenteMineroPadre);
		}	

		// subtipo de componente minero
		if(pgimComponenteMineroDTO.getIdTipoSubcomponente() != null) { 
			PgimTipoSubcomponente subtipo = new PgimTipoSubcomponente();
	        subtipo.setIdTipoSubcomponente(pgimComponenteMineroDTO.getIdTipoSubcomponente());
	        pgimComponenteMinero.setPgimTipoSubcomponente(subtipo);
		}	

		if(pgimComponenteMineroDTO.getCoComponente() != null) { 
			pgimComponenteMinero.setCoComponente(pgimComponenteMineroDTO.getCoComponente());
		}
		
		pgimComponenteMinero.setNoComponente(pgimComponenteMineroDTO.getNoComponente().trim());

		pgimComponenteMinero.setNuCapacidadPlanta(pgimComponenteMineroDTO.getNuCapacidadPlanta());
		
		pgimComponenteMinero.setDescNuValorNumerico(pgimComponenteMineroDTO.getDescNuValorNumerico());	

		pgimComponenteMinero.setNvlAceptacion(pgimComponenteMineroDTO.getNvlAceptacion());	

	}

	@Transactional(readOnly = false)
	@Override
	public PgimComponenteMineroDTO modificarComponenteMinero(@Valid PgimComponenteMineroDTO pgimComponenteMineroDTO,
			AuditoriaDTO auditoriaDTO) {
		PgimComponenteMinero pgimComponenteMinero = null;

		Optional<PgimComponenteMinero> componenteMinero = componenteMineroRepository
				.findById(pgimComponenteMineroDTO.getIdComponenteMinero());

		pgimComponenteMinero = componenteMinero.get();
		this.configurarValores(pgimComponenteMineroDTO, pgimComponenteMinero);

		pgimComponenteMinero.setFeActualizacion(auditoriaDTO.getFecha());
		pgimComponenteMinero.setUsActualizacion(auditoriaDTO.getUsername());
		pgimComponenteMinero.setIpActualizacion(auditoriaDTO.getTerminal());

		this.componenteMineroRepository.save(pgimComponenteMinero);

		PgimComponenteMineroDTO pgimComponenteMineroDTOModificada = obtenerComponenteMineroPorId(
				pgimComponenteMinero.getIdComponenteMinero());

		return pgimComponenteMineroDTOModificada;
	}

	@Override
	public PgimComponenteMineroDTO obtenerComponenteMineroPorId(Long idComponenteMinero) {
		return this.componenteMineroRepository.obtenerComponenteMineroPorId(idComponenteMinero);
	}

	@Override
	public PgimComponenteMinero getByIdComponenteMinero(Long idComponenteMinero) {
		return this.componenteMineroRepository.findById(idComponenteMinero).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarComponenteMinero(PgimComponenteMinero pgimComponenteMineroActual, AuditoriaDTO auditoriaDTO) {

		pgimComponenteMineroActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimComponenteMineroActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimComponenteMineroActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimComponenteMineroActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.componenteMineroRepository.save(pgimComponenteMineroActual);
	}

	@Override
	public List<PgimComponenteMineroDTO> listarComponentePadrePorUm(Long idUnidadMinera, Long idComponenteMinero ) {
		List<PgimComponenteMineroDTO> lPgimValorParametroDTO = this.componenteMineroRepository
                .listarComponentePadrePorUM(idUnidadMinera, idComponenteMinero);

        return lPgimValorParametroDTO;
	}
	
    @Override
    public Page<PgimComponenteMineroDTO> listarReporteComponenteUMPaginado(PgimComponenteMineroDTO filtroPgimComponenteUM,
            Pageable paginador) {

        Page<PgimComponenteMineroDTO> pPgimComponenteMineroDTO = this.componenteMineroRepository
                .listarReporteComponenteUMPaginado(filtroPgimComponenteUM.getIdTipoComponenteMinero(), filtroPgimComponenteUM.getDescAgenteFiscalizado(), paginador);

        return pPgimComponenteMineroDTO;
    }

	@Override
	public List<PgimTipoSubcomponenteDTO> listarSubTipoComponentes() {
		return this.tipoSubcomponenteRepository.listarSubTipoComponentes();
	}
	

	@Override
	public List<PgimComponenteMineroDTO> listarComponentes(){
		
		List<PgimComponenteMineroDTO> lista = new ArrayList<>();
	
		lista = componenteMineroRepository.listarComponentes();
	
		return lista;
		
	}
	
	@Override
    public List<PgimComponenteMineroDTO> listarPorPalabraClaveYUm(String palabra, Long idUnidadMinera) {
		idUnidadMinera = (idUnidadMinera == 0L) ? null : idUnidadMinera;
        List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO = this.componenteMineroRepository.listarPorPalabraClaveYUm(palabra,
                idUnidadMinera);

        return lPgimComponenteMineroDTO;
    }

	@Transactional(readOnly = false)
	@Override
	public String crearComponentesMineros(List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO, AuditoriaDTO auditoriaDTO) throws Exception {
		
		String mensajeError = "";
		String mensajeErrorLog = "";
		Integer totalRegistrado = 0;

		for (PgimComponenteMineroDTO pgimComponenteMineroDTO : lPgimComponenteMineroDTO) {
						
			try {
				this.crearComponenteMinero(pgimComponenteMineroDTO, auditoriaDTO);
				totalRegistrado ++;

			} catch (Exception e) {
				mensajeErrorLog += "Error al crear el componente \"" + pgimComponenteMineroDTO.getNoComponente() + "\": " + e.getMessage() + "\n ";
				log.error("Error al crear el componente \"" + pgimComponenteMineroDTO.getNoComponente() + "\": " + e.getMessage(), e);
				
				mensajeError += pgimComponenteMineroDTO.getNoComponente() + ", ";
			}
		}

		if(!mensajeError.equals("")){
			mensajeError = mensajeError.trim();
			mensajeError = (mensajeError.length() > 0) ? mensajeError.substring(0, mensajeError.length() - 1) : "";
			mensajeError = "Sin embargo hubo problemas al registrar los siguientes componentes mineros: " + mensajeError;
			
			log.error("Listado de errores ocurridos: " + mensajeErrorLog);
		}

		String descCantidad = totalRegistrado > 1 ? "s" : "";
		String mensaje = String.format("Se registró %d nuevo%s componente%s minero%s correctamente. \n %s", totalRegistrado, descCantidad, descCantidad, descCantidad, mensajeError);

		return mensaje;
	}

	@Override
	public List<PgimComponenteMineroDTO> obtenerNuevosComponentes(
			List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO) throws Exception {
		
		// Ordenar por codigo de fiscalización ascedente
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTOOrder = lPgimComponenteMineroDTO.stream()
			.sorted(Comparator.comparing(PgimComponenteMineroDTO::getDescCoSupervision))
			.collect(Collectors.toList());
				
		List<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionRepository.listarSupervisionConfirmarHvGeotecnia();
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTORegistrados = this.listarComponentes();
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTONuevos = new ArrayList<PgimComponenteMineroDTO>();
		String codSupervision = "";
		PgimSupervisionDTO pgimSupervisionDTOEncontrado = null;

		for (PgimComponenteMineroDTO pgimComponenteMineroDTO : lPgimComponenteMineroDTOOrder) {
			
			if(!codSupervision.equals(pgimComponenteMineroDTO.getDescCoSupervision())){
				codSupervision = pgimComponenteMineroDTO.getDescCoSupervision();
				pgimSupervisionDTOEncontrado = null;

				for (PgimSupervisionDTO pgimSupervisionDTO : lPgimSupervisionDTO) {
					if(pgimSupervisionDTO.getCoSupervision().equals(codSupervision)){
						pgimSupervisionDTOEncontrado = pgimSupervisionDTO;
						break;
					}
				}
			}

			if(pgimSupervisionDTOEncontrado != null){
				boolean encontre = false;

				for (PgimComponenteMineroDTO pgimComponenteMineroDTOAux : lPgimComponenteMineroDTORegistrados) {
				
					if(pgimComponenteMineroDTOAux.getNoComponente().equals(pgimComponenteMineroDTO.getNoComponente())
						&& pgimComponenteMineroDTOAux.getIdTipoComponenteMinero().toString().equals(pgimComponenteMineroDTO.getIdTipoComponenteMinero().toString())
						&& pgimComponenteMineroDTOAux.getDescCoUnidadMinera().equals(pgimSupervisionDTOEncontrado.getDescCoUnidadMinera())){
							encontre = true;
							break;
					}
				}

				if(!encontre){
					pgimComponenteMineroDTO.setIdUnidadMinera(pgimSupervisionDTOEncontrado.getIdUnidadMinera());
					lPgimComponenteMineroDTONuevos.add(pgimComponenteMineroDTO);
				}

			}

		}

		return lPgimComponenteMineroDTONuevos;
	}

	/**
	 * <!----------------------------------------------------------------------------------------------------------
	 * PGIM-11253: Agregar la sección “Componentes asociados al Hecho Verificado” en
	 * fase 2 Y 3 de la fiscalización
	 * ------------------------------------------------------------------------------------------------------------>
	 */
	@Override
	public List<PgimComponenteMineroDTO> listarComponenteMineroNoAsociadoHc(Long idUnidadMinera, List<PgimCmineroSprvsionDTO> descListaCmineroSprvsion, 
			List<PgimComponenteHcDTO> descListaComponenteHcDTO, String tipoObjeto, Long idHechoConstatado) {

		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO = null;
		List<PgimComponenteMineroDTO> lPgimComponenteMineroDTOGuardado = new ArrayList<>();
		if(tipoObjeto.equals("CMS")){

			lPgimComponenteMineroDTO = this.componenteMineroRepository.listarComponenteMineroNoAsociadoHc(idUnidadMinera);

			for(PgimComponenteMineroDTO pgimComponenteMineroDTO: lPgimComponenteMineroDTO){

				PgimCmineroSprvsionDTO pgimCmineroSprvsionDTO = this.cmineroSprvsionRepository.obtenerComponenteMineroSupervisionIdComponenteMinero(
						pgimComponenteMineroDTO.getIdComponenteMinero());

				if(pgimCmineroSprvsionDTO == null){
					lPgimComponenteMineroDTOGuardado.add(pgimComponenteMineroDTO);
				}
			}
		}else{

			lPgimComponenteMineroDTO = this.componenteMineroRepository.listarComponenteMineroNoAsociadoHc(idUnidadMinera);

			for (PgimComponenteMineroDTO pgimComponenteMineroDTO : lPgimComponenteMineroDTO) {

				PgimComponenteHcDTO pgimComponenteHcDTO = this.componenteHcRepository.obtenerComponenteMineroHcIdComponenteMinero(
						pgimComponenteMineroDTO.getIdComponenteMinero(), idHechoConstatado);

				if (pgimComponenteHcDTO == null) {
					lPgimComponenteMineroDTOGuardado.add(pgimComponenteMineroDTO);
				}
			}
		}

		return lPgimComponenteMineroDTOGuardado;
	}
	/**
	 * <!----------------------------------------------------------------------------------------------------------
	 * PGIM-11253: FIN
	 * ------------------------------------------------------------------------------------------------------------>
	 */

	@Transactional(readOnly = false)
	@Override
	public String crearComponentesMinerosSupervision(List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {
		
		String mensajeError = "";
		String mensajeErrorLog = "";
		Integer totalRegistrado = 0;
		
		Long idSupervision = lPgimCmineroSprvsionDTO.get(0).getIdSupervision();
		List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTODB = this.cmineroSprvsionRepository.listarComponenteMineroSupervision(idSupervision);
		
		try {
			
			if (lPgimCmineroSprvsionDTODB != null && lPgimCmineroSprvsionDTODB.size() > 0) {
				
				eliminarComponentesSupervision(lPgimCmineroSprvsionDTODB, auditoriaDTO);
				registrarComponentesSupervision(lPgimCmineroSprvsionDTO, auditoriaDTO);
				
			}else {
				registrarComponentesSupervision(lPgimCmineroSprvsionDTO, auditoriaDTO);
			}
			
		} catch (Exception e) {
			mensajeErrorLog += "Error al crear el componente minero por supervision" + e.getMessage() + "\n ";
			log.error("Error al crear el componente \": " + e.getMessage(), e);
		}
		
		String mensaje = String.format("Se registró nuevo componente minero por supervisión correctamente. \n %s", mensajeError);
		return mensaje;
	}
	
	void registrarComponentesSupervision( List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO, AuditoriaDTO auditoriaDTO) {
		
		List<PgimCmineroSprvsion> lPgimCmineroSprvsion = new ArrayList<>();
		
		for (PgimCmineroSprvsionDTO c :  lPgimCmineroSprvsionDTO) {
			PgimCmineroSprvsion pgimCmineroSprvsion = new PgimCmineroSprvsion();
			
			PgimComponenteMinero pgimComponenteMinero = new PgimComponenteMinero();
			pgimComponenteMinero.setIdComponenteMinero(c.getIdComponenteMinero());
			pgimCmineroSprvsion.setPgimComponenteMinero(pgimComponenteMinero);
			
			PgimSupervision pgimSupervision = new PgimSupervision();
			pgimSupervision.setIdSupervision(c.getIdSupervision());
			pgimCmineroSprvsion.setPgimSupervision(pgimSupervision);
			
			pgimCmineroSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimCmineroSprvsion.setFeCreacion(auditoriaDTO.getFecha());
			pgimCmineroSprvsion.setUsCreacion(auditoriaDTO.getUsername());
			pgimCmineroSprvsion.setIpCreacion(auditoriaDTO.getTerminal());
			lPgimCmineroSprvsion.add(pgimCmineroSprvsion);
		}
		
		this.cmineroSprvsionRepository.saveAll(lPgimCmineroSprvsion);
	}
	
	void eliminarComponentesSupervision( List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO, AuditoriaDTO auditoriaDTO) {
		
		List<PgimCmineroSprvsion> lPgimCmineroSprvsion = new ArrayList<>();
		
		for (PgimCmineroSprvsionDTO c :  lPgimCmineroSprvsionDTO) {
			
			PgimCmineroSprvsion pgimCmineroSprvsion = new PgimCmineroSprvsion();
			pgimCmineroSprvsion.setIdCmineroSprvsion(c.getIdCmineroSprvsion());
			
			PgimComponenteMinero pgimComponenteMinero = new PgimComponenteMinero();
			pgimComponenteMinero.setIdComponenteMinero(c.getIdComponenteMinero());
			pgimCmineroSprvsion.setPgimComponenteMinero(pgimComponenteMinero);
			
			PgimSupervision pgimSupervision = new PgimSupervision();
			pgimSupervision.setIdSupervision(c.getIdSupervision());
			pgimCmineroSprvsion.setPgimSupervision(pgimSupervision);
			
			pgimCmineroSprvsion.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimCmineroSprvsion.setFeCreacion(auditoriaDTO.getFecha());
			pgimCmineroSprvsion.setUsCreacion(auditoriaDTO.getUsername());
			pgimCmineroSprvsion.setIpCreacion(auditoriaDTO.getTerminal());
			pgimCmineroSprvsion.setFeActualizacion(auditoriaDTO.getFecha());
			pgimCmineroSprvsion.setUsActualizacion(auditoriaDTO.getUsername());
			pgimCmineroSprvsion.setIpActualizacion(auditoriaDTO.getTerminal());
			
			lPgimCmineroSprvsion.add(pgimCmineroSprvsion);
		}
		this.cmineroSprvsionRepository.saveAll(lPgimCmineroSprvsion);
	}
}
