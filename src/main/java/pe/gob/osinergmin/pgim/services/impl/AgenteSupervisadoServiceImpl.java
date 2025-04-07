package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimEstrato;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.PasRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.AgenteSupervisadoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Agente Supervisado
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 30/05/2020
 * @fecha_de_ultima_actualización: 20/06/2020 
 */
@Service
@Transactional(readOnly = true)
public class AgenteSupervisadoServiceImpl implements AgenteSupervisadoService {

    @Autowired
    private AgenteSupervisadoRepository agenteSupervisadoRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
	private ValorParametroRepository valorParametroRepository;
    
    @Autowired
    private InstanciaProcesRepository instanciaProcesRepository;
    
    @Autowired
    private SupervisionRepository supervisionRepository;
    
    @Autowired
    private PasRepository pasRepository;
    
    @Override
    public PgimAgenteSupervisadoDTO obtenerAgenteSupervisado(Long idAgenteSupervisado) {        
    	return this.agenteSupervisadoRepository.obtenerAgenteSupervisado(idAgenteSupervisado, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.UNMIN_CONCSION_BNFCIO.toString()));
    }

    @Override
    public List<PgimAgenteSupervisadoDTO> listarPorPalabraClave(String palabra) {
        List<PgimAgenteSupervisadoDTO> lPgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository
                .listarPorPalabraClave(palabra);

        return lPgimAgenteSupervisadoDTO;
    }
    
    @Override
    public PgimAgenteSupervisado getByIdAgenteSupervisado(Long idAgenteSupervisado) {
        return this.agenteSupervisadoRepository.findById(idAgenteSupervisado).orElse(null);
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimAgenteSupervisadoDTO crearAgenteSupervisado(PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO, AuditoriaDTO auditoriaDTO) {
        PgimAgenteSupervisado pgimAgenteSupervisado = new PgimAgenteSupervisado();

        PgimPersona pgimPersona = personaRepository.obtenerPorTipoYNumero(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.DOIDE_RUC.toString()), 
        		pgimAgenteSupervisadoDTO.getDescCoDocumentoIdentidad());

		if (pgimPersona == null) {
			pgimPersona = new PgimPersona();
			pgimPersona.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimPersona.setFeCreacion(auditoriaDTO.getFecha());
			pgimPersona.setUsCreacion(auditoriaDTO.getUsername());
			pgimPersona.setIpCreacion(auditoriaDTO.getTerminal());

		} else {
			pgimPersona.setFeActualizacion(auditoriaDTO.getFecha());
			pgimPersona.setUsActualizacion(auditoriaDTO.getUsername());
			pgimPersona.setIpActualizacion(auditoriaDTO.getTerminal());

		}

		pgimPersona.setCoDocumentoIdentidad(pgimAgenteSupervisadoDTO.getDescCoDocumentoIdentidad());
        pgimPersona.setNoRazonSocial(pgimAgenteSupervisadoDTO.getDescNoRazonSocial());
        pgimPersona.setDeTelefono(pgimAgenteSupervisadoDTO.getDescDeTelefono());
        pgimPersona.setDeTelefono2(pgimAgenteSupervisadoDTO.getDescDeTelefono2());
        pgimPersona.setDeCorreo(pgimAgenteSupervisadoDTO.getDescDeCorreo());
        pgimPersona.setDeCorreo2(pgimAgenteSupervisadoDTO.getDescDeCorreo2());
        pgimPersona.setDiPersona(pgimAgenteSupervisadoDTO.getDescDeDireccion());
        
        pgimPersona.setFlAfiliadoNtfccionElctrnca(pgimAgenteSupervisadoDTO.getFlAfiliadoNtfccionElctrnca());
        pgimPersona.setFeAfiliadoDesde(pgimAgenteSupervisadoDTO.getFeAfiliadoDesde());
        pgimPersona.setDeCorreoNtfccionElctrnca(pgimAgenteSupervisadoDTO.getDeCorreoNtfccionElctrnca());
        pgimPersona.setNoCorto(pgimAgenteSupervisadoDTO.getDescNoCorto());
        
        PgimValorParametro tipoDocIdentidad = new PgimValorParametro();
        tipoDocIdentidad.setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.DOIDE_RUC.toString()));
        pgimPersona.setTipoDocIdentidad(tipoDocIdentidad);
        
        PgimUbigeo pgimUbigeo = new PgimUbigeo();
        pgimUbigeo.setIdUbigeo(pgimAgenteSupervisadoDTO.getIdUbigeo());
        pgimPersona.setPgimUbigeo(pgimUbigeo);

        PgimPersona pgimPersonaCreada = personaRepository.save(pgimPersona);
        pgimAgenteSupervisado.setPgimPersona(pgimPersonaCreada);
        
        PgimEstrato pgimEstrato = new PgimEstrato();
        pgimEstrato.setIdEstrato(pgimAgenteSupervisadoDTO.getIdEstrato());
        pgimAgenteSupervisado.setPgimEstrato(pgimEstrato);
        
        PgimValorParametro tamanioEmpresa = new PgimValorParametro();
        tamanioEmpresa.setIdValorParametro(pgimAgenteSupervisadoDTO.getIdTamanioEmpresa());
        pgimAgenteSupervisado.setTamanioEmpresa(tamanioEmpresa);
        
        pgimAgenteSupervisado.setDePrincipalActividadEcnmca(pgimAgenteSupervisadoDTO.getDePrincipalActividadEcnmca());
        
        pgimAgenteSupervisado.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        /*pgimAgenteSupervisado.setFeCreacion(new Date());
        pgimAgenteSupervisado.setUsCreacion("admin");
        pgimAgenteSupervisado.setIpCreacion("127.0.0.1");*/
        pgimAgenteSupervisado.setFeCreacion(auditoriaDTO.getFecha());
        pgimAgenteSupervisado.setUsCreacion(auditoriaDTO.getUsername());
        pgimAgenteSupervisado.setIpCreacion(auditoriaDTO.getTerminal());
        PgimAgenteSupervisado pgimAgenteSupervisadoCreado = agenteSupervisadoRepository.save(pgimAgenteSupervisado);
        
        
        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTOCreado = this.obtenerAgenteSupervisadoPorId(pgimAgenteSupervisadoCreado.getIdAgenteSupervisado());

        return pgimAgenteSupervisadoDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimAgenteSupervisadoDTO modificarAgenteSupervisado(PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO,
                                                     PgimPersona pgimPersona,PgimAgenteSupervisado pgimAgenteSupervisado
                                                     , AuditoriaDTO auditoriaDTO) {
    	
    	pgimPersona.setCoDocumentoIdentidad(pgimAgenteSupervisadoDTO.getDescCoDocumentoIdentidad());
        pgimPersona.setNoRazonSocial(pgimAgenteSupervisadoDTO.getDescNoRazonSocial());
        pgimPersona.setDeTelefono(pgimAgenteSupervisadoDTO.getDescDeTelefono());
        pgimPersona.setDeTelefono2(pgimAgenteSupervisadoDTO.getDescDeTelefono2());
        pgimPersona.setDeCorreo(pgimAgenteSupervisadoDTO.getDescDeCorreo());
        pgimPersona.setDeCorreo2(pgimAgenteSupervisadoDTO.getDescDeCorreo2());
        pgimPersona.setDiPersona(pgimAgenteSupervisadoDTO.getDescDeDireccion());
        
        pgimPersona.setFlAfiliadoNtfccionElctrnca(pgimAgenteSupervisadoDTO.getFlAfiliadoNtfccionElctrnca());
        pgimPersona.setFeAfiliadoDesde(pgimAgenteSupervisadoDTO.getFeAfiliadoDesde());
        pgimPersona.setDeCorreoNtfccionElctrnca(pgimAgenteSupervisadoDTO.getDeCorreoNtfccionElctrnca());
        pgimPersona.setNoCorto(pgimAgenteSupervisadoDTO.getDescNoCorto());
      
        PgimUbigeo pgimUbigeo = new PgimUbigeo();
        pgimUbigeo.setIdUbigeo(pgimAgenteSupervisadoDTO.getIdUbigeo());
        pgimPersona.setPgimUbigeo(pgimUbigeo);
      
    	/*pgimPersona.setFeActualizacion(new Date());
    	pgimPersona.setUsActualizacion("admin");
    	pgimPersona.setIpActualizacion("127.0.0.1");*/
        pgimPersona.setFeActualizacion(auditoriaDTO.getFecha());
        pgimPersona.setUsActualizacion(auditoriaDTO.getUsername());
        pgimPersona.setIpActualizacion(auditoriaDTO.getTerminal());
        PgimPersona pgimPersonaModificada = this.personaRepository.save(pgimPersona);
        pgimAgenteSupervisado.setPgimPersona(pgimPersonaModificada);

        PgimEstrato pgimEstrato = new PgimEstrato();
        pgimEstrato.setIdEstrato(pgimAgenteSupervisadoDTO.getIdEstrato());
        pgimAgenteSupervisado.setPgimEstrato(pgimEstrato);
        
        PgimValorParametro tamanioEmpresa = new PgimValorParametro();
        tamanioEmpresa.setIdValorParametro(pgimAgenteSupervisadoDTO.getIdTamanioEmpresa());
        pgimAgenteSupervisado.setTamanioEmpresa(tamanioEmpresa);
        
        pgimAgenteSupervisado.setDePrincipalActividadEcnmca(pgimAgenteSupervisadoDTO.getDePrincipalActividadEcnmca());
        
        /*pgimAgenteSupervisado.setFeActualizacion(new Date());
        pgimAgenteSupervisado.setUsActualizacion("admin");
        pgimAgenteSupervisado.setIpActualizacion("127.0.0.1");*/
        pgimAgenteSupervisado.setFeActualizacion(auditoriaDTO.getFecha());
        pgimAgenteSupervisado.setUsActualizacion(auditoriaDTO.getUsername());
        pgimAgenteSupervisado.setIpActualizacion(auditoriaDTO.getTerminal());
        PgimAgenteSupervisado pgimAgenteSupervisadoModificado = agenteSupervisadoRepository.save(pgimAgenteSupervisado);

        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTOModificado = this.obtenerAgenteSupervisadoPorId(pgimAgenteSupervisadoModificado.getIdAgenteSupervisado());

        return pgimAgenteSupervisadoDTOModificado;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void eliminarAgenteSupervisado(PgimAgenteSupervisado pgimAgenteSupervisadoActual, AuditoriaDTO auditoriaDTO) {
        
    	pgimAgenteSupervisadoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	/*pgimAgenteSupervisadoActual.setFeActualizacion(new Date());
    	pgimAgenteSupervisadoActual.setUsActualizacion("admin");*/
    	pgimAgenteSupervisadoActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimAgenteSupervisadoActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimAgenteSupervisadoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.agenteSupervisadoRepository.save(pgimAgenteSupervisadoActual);
    }
    
    @Override
    public PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorId(Long idAgenteSupervisado) {
    	return this.agenteSupervisadoRepository.obtenerAgenteSupervisadoPorId(idAgenteSupervisado, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.UNMIN_CONCSION_BNFCIO.toString()));
    }

    @Override
    public Page<PgimAgenteSupervisadoDTO> filtrar(PgimAgenteSupervisadoDTO filtroAgenteSupervisado, Pageable paginador){
        
        Page<PgimAgenteSupervisadoDTO> pPgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.listar(
            filtroAgenteSupervisado.getDescCoDocumentoIdentidad(), filtroAgenteSupervisado.getDescNoRazonSocial(), 
            filtroAgenteSupervisado.getIdEstrato(),filtroAgenteSupervisado.getTextoBusqueda(), paginador);

        return pPgimAgenteSupervisadoDTO;
    }
    
    @Override
    public List<PgimAgenteSupervisadoDTO> existeAgenteSupervisado(Long idAgenteSupervisado, String coDocumentoIdentidad) {
        List<PgimAgenteSupervisadoDTO> pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.existeAgenteSupervisado(idAgenteSupervisado,
        		coDocumentoIdentidad);
        return pgimAgenteSupervisadoDTO;
    }
    
    @Override
    public PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorInstancProceso(Long idInstanciaProceso) {
    	
    	PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = null;
    	PgimSupervision pgimSupervision = null;
    	String noRazonSocialASpersistido = "";
    	
    	PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso).orElse(null);
    	
    	if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_SUPERVISION)) {
    		Long idSupervision = pgimInstanciaProces.getCoTablaInstancia();
        	pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);
        	
        	if(pgimSupervision.getNoRazonSocialAfiscalizado() != null && !pgimSupervision.getNoRazonSocialAfiscalizado().trim().equals("")) {
    			noRazonSocialASpersistido = pgimSupervision.getNoRazonSocialAfiscalizado();
    		}
        	
		} else if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_PAS)) {
			
			PgimPas pgimPas = this.pasRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);
			
			Long idSupervision = pgimPas.getPgimSupervision().getIdSupervision();
        	
        	pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);
        	
        	if(pgimPas.getNoRazonSocialAfiscalizado() != null && !pgimPas.getNoRazonSocialAfiscalizado().trim().equals("")) {
    			noRazonSocialASpersistido = pgimPas.getNoRazonSocialAfiscalizado();
    		}
		} 
    	
    	if(pgimSupervision == null) {
    		return null;
    	}
    	
    	if(pgimSupervision.getPgimAgenteSupervisado() != null) {
    		// Obtenemos el agente fiscalizado persistido en la fiscalización y añadimos la razón social persistida en el objeto de trabajo, de ser el caso.
    		pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.
    				obtenerAgenteSupervisadoPorIdAs(pgimSupervision.getPgimAgenteSupervisado().getIdAgenteSupervisado());
    		
    		if(!noRazonSocialASpersistido.trim().equals("")) {
    			pgimAgenteSupervisadoDTO.setDescNoRazonSocial(noRazonSocialASpersistido);
    		}
    			
        }else {
        	// Obtenemos el agente fiscalizado de la unidad fiscalizable.
        	pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.
        			obtenerAgenteSupervisadoPorIdUm(pgimSupervision.getPgimUnidadMinera().getIdUnidadMinera());
        	
        }  
    	
        return pgimAgenteSupervisadoDTO;
    }
    

}