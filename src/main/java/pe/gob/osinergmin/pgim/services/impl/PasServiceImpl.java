package pe.gob.osinergmin.pgim.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gob.osinergmin.common.util.EncripUtil;
import gob.osinergmin.sym.remote.enums.InvocationResult;
import gob.osinergmin.sym.remote.rest.ro.in.SymExpedienteReporteFiltersInRO;
import gob.osinergmin.sym.remote.rest.ro.in.SymInfraccionInRO;
import gob.osinergmin.sym.remote.rest.ro.out.bean.TokenOutRO;
import gob.osinergmin.sym.remote.rest.ro.out.list.ListSymExpedienteReporteOutRO;
import gob.osinergmin.sym.remote.rest.ro.out.list.ListSymInfraccionOutRO;
import gob.osinergmin.sym.rest.util.ExpedienteInvoker;
import gob.osinergmin.sym.rest.util.InfraccionInvoker;
import gob.osinergmin.sym.rest.util.SymSeguridadInvoker;
import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCorrelativoTablaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEspAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEvaluadorAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEstResolAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerfaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerpaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasPendientesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasdsuAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasespeMesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppastipactiAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppastipsustAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimCorrelativoTabla;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccion;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionContra;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.CorrelativoTablaRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.ExpPerfaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ExpPerpaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ExppasPendientesAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.PasAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PasRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.InfraccionService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.PasService;
import pe.gob.osinergmin.pgim.utils.ConstPasoProcesoPas;
import pe.gob.osinergmin.pgim.utils.ConstRelacionPasoPAS;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Procesos administrativos de
 *               sanción - PAS
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/10/2020
 * @fecha_de_ultima_actualización: 24/10/2020
 */

@Service
@Slf4j
@Transactional(readOnly = true)
public class PasServiceImpl implements PasService {

    @Autowired
    private PasRepository pasRepository;

    @Autowired
    private PasAuxRepository pasAuxRepository;

    @Autowired
    private ExppasPendientesAuxRepository exppasPendientesAuxRepository;

    @Autowired

    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private HechoConstatadoService hechoConstatadoService;

    @Autowired
    private InstanciaProcesRepository instanciaProcesRepository;
    
    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;  

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private RelacionPasoRepository relacionPasoRepository;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private ExpPerfaAuxRepository expPerfaAuxRepository;

    @Autowired
    private ExpPerpaAuxRepository expPerpaAuxRepository;

    @Autowired
    private InfraccionRepository infraccionRepository;
    
    @Autowired
    private InfraccionContraRepository infraccionContraRepository;       
    
    @Autowired
	private ValorParametroRepository valorParametroRepository;
    
    @Autowired
	private DocumentoService documentoService;    
    
    @Autowired
	private InfraccionService infraccionService;    
    
    @Autowired
	private DocumentoRepository documentoRepository; 
    
    @Autowired
	private SubcategoriaDocRepository subcategoriaDocRepository;   

    @Autowired
    private CorrelativoTablaRepository correlativoTablaRepository;
        
    @Autowired
    private SupervisionRepository supervisionRepository;

    @Autowired
    private AgenteSupervisadoRepository agenteSupervisadoRepository;

    @Override
    public Page<PgimPasAuxDTO> filtrar(PgimPasAuxDTO filtroPasAuxDTO, Pageable paginador, AuditoriaDTO auditoriaDTO) {

    	//Obtenemos permiso "Mi interés" y permiso "listar todas"	
    	boolean flagPermisoMiInteres = false;        	
    	boolean flagPermisoTodas = false;
    	for (String permiso : auditoriaDTO.getAuthorities()) {	    		 
    		
    		if(permiso.equals(ConstantesUtil.PERMISO_VER_PAS_MI_INTERES)) {
    			flagPermisoMiInteres = true;
    		}
    		
    		if(permiso.equals(ConstantesUtil.PERMISO_VER_PAS_TODAS)) {
    			flagPermisoTodas = true;
    		}
    	}
    	    	
    	
        String usuarioWindowsSesion = "";
        if (filtroPasAuxDTO.getDescFlagMiInteres().equals("1") && flagPermisoMiInteres ) {
            usuarioWindowsSesion = auditoriaDTO.getUsername();
        }
        
        if (filtroPasAuxDTO.getDescFlagMisAsignaciones().equals("2")) {
            filtroPasAuxDTO.setNoUsuarioOrigen(auditoriaDTO.getUsername());
        }
        
        
        //Si el usuario no tiene permiso para ver los registros de otros usaurios, o no tiene permiso para ver los registros de su interés 
        //se le setea el mismo usaurio para los filtros 
        if(
        	(
        		(( filtroPasAuxDTO.getDescUsuarioAsignado()==null || filtroPasAuxDTO.getDescUsuarioAsignado().trim().equals("") )
        		&& 	(filtroPasAuxDTO.getDescPersonaDestino()==null || filtroPasAuxDTO.getDescPersonaDestino().trim().equals("") ))
        		|| (filtroPasAuxDTO.getDescUsuarioAsignado()!=null && !filtroPasAuxDTO.getDescUsuarioAsignado().trim().equals(auditoriaDTO.getUsername()) ) 
        		)
        	&& ( (filtroPasAuxDTO.getDescFlagMiInteres().equals("1") && !flagPermisoMiInteres)
        		|| (filtroPasAuxDTO.getDescFlagMiInteres().equals("0") && !flagPermisoTodas ) )
        		) {                	
        	filtroPasAuxDTO.setDescUsuarioAsignado(auditoriaDTO.getUsername());
        }
        else if(
        		(filtroPasAuxDTO.getDescPersonaDestino()!=null && !filtroPasAuxDTO.getDescPersonaDestino().trim().equals("") )
        		&& ( (filtroPasAuxDTO.getDescFlagMiInteres().equals("1") && !flagPermisoMiInteres)
        				|| (filtroPasAuxDTO.getDescFlagMiInteres().equals("0") && !flagPermisoTodas ) )
        		) {
        	filtroPasAuxDTO.setDescUsuarioAsignado(auditoriaDTO.getUsername());
        	filtroPasAuxDTO.setDescPersonaDestino("");
        	
        }
        
        
        //Completando filtro de persona asignada
        if( (filtroPasAuxDTO.getDescUsuarioAsignado()==null || filtroPasAuxDTO.getDescUsuarioAsignado().trim().equals(""))
        		&& filtroPasAuxDTO.getDescPersonaDestino() != null
        		) {
        	filtroPasAuxDTO.setDescUsuarioAsignado(filtroPasAuxDTO.getDescPersonaDestino());
        }
        

        Page<PgimPasAuxDTO> pPgimPasAuxDTO = this.pasAuxRepository.filtrar(filtroPasAuxDTO.getCoSupervision(),
                filtroPasAuxDTO.getNoUnidadMinera(), 
                filtroPasAuxDTO.getAsNoRazonSocial(),
                filtroPasAuxDTO.getFeCreacionPasAnio(),
                filtroPasAuxDTO.getIdEspecialidad(),
                filtroPasAuxDTO.getIdFaseActual(),
                filtroPasAuxDTO.getIdPasoActual(),
                filtroPasAuxDTO.getNuExpedienteSigedPas(),
                filtroPasAuxDTO.getNoUsuarioOrigen(),
                filtroPasAuxDTO.getDescUsuarioAsignado(),
                filtroPasAuxDTO.getDescPersonaDestino(),
                usuarioWindowsSesion, 
                filtroPasAuxDTO.getDescFlagMisAsignaciones(), 
                filtroPasAuxDTO.getTextoBusqueda(),
                paginador);

        return pPgimPasAuxDTO;
    }

    @Override
    public List<PgimPasDTO> listarPorNuExpedienteSiged(String palabra) {
        List<PgimPasDTO> lPgimPasDTO = this.pasRepository.listarPorNuExpedienteSiged(palabra);

        return lPgimPasDTO;
    }

    @Override
    public PgimPasDTO obtenerPasPorId(Long idPas, Long idInstanciaPaso) {
        return this.pasRepository.obtenerPasPorId(idPas, idInstanciaPaso);
    }
    
    @Override
    public PgimPasDTO obtenerPasPorIdPas(Long idPas) {
        return this.pasRepository.obtenerPasPorIdPas(idPas);
    }

    @Override
    public void iniciarPAS(Long idPersonalOsi, Long coTablaInstancia, AuditoriaDTO auditoriaDTO) throws Exception {
    	
    	// Verificar si ya existe un PAS para dicha supervisión
    	
    	PgimPasDTO pgimPasDTO = this.pasRepository.obtenerPasPorIdSupervision(coTablaInstancia);
    	
    	if(pgimPasDTO == null) {
    		// Crear nuevo PAS
    	
	        PgimPas pgimPas = new PgimPas();
	
	        pgimPas.setPgimSupervision(new PgimSupervision());
	        pgimPas.getPgimSupervision().setIdSupervision(coTablaInstancia);
	        pgimPas.setFeCreacionPas(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            pgimPas.setCoPas(obtenerSiguienteCorrelativo("PAS",sdf.format(new Date()), auditoriaDTO));
	
	        pgimPas.setEsRegistro(ConstantesUtil.IND_ACTIVO);
	        pgimPas.setFeCreacion(auditoriaDTO.getFecha());
	        pgimPas.setUsCreacion(auditoriaDTO.getUsername());
	        pgimPas.setIpCreacion(auditoriaDTO.getTerminal());
	
	        this.pasRepository.save(pgimPas);
	
	        // Se asegura la instancia del proceso
	        List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(
	                ConstantesUtil.PARAM_PROCESO_FISCALIZACION, pgimPas.getIdPas(), auditoriaDTO);
	
	        PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);
	
	        PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
	                .findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);
	
	        // Se actualiza la instancia del proceso en el registro del PAS
	        this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);
	
	        // Se crea la asignación
	        PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = this.flujoTrabajoService
	                .obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_FISCALIZACION);
	
	        PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
	        pgimInstanciaPasoDTO.setIdRelacionPaso(pgimRelacionPasoDTOInicial.getIdRelacionPaso());
	        pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(idPersonalOsi);
	        pgimInstanciaPasoDTO.setDeMensaje("Iniciar proceso administrativo sancionador");
	
	        PgimInstanciaPasoDTO pgimInstanciaPasoDTOInicial = this.flujoTrabajoService
	                .crearInstanciaPasoInicial(pgimInstanciaProcesActual, pgimInstanciaPasoDTO, auditoriaDTO);
	
	        // Copiamos las infracciones de la Fiscalización al PAS, manteniéndolas como vigentes, 
	        // de esta manera ambos objetos de trabajo tendrán sus infracciones vigentes
	        Long idSupervision = coTablaInstancia;
	        this.infraccionService.copiarInfraccionesSupervision(idSupervision, pgimInstanciaPasoDTOInicial, pgimPas, false, auditoriaDTO);
	        
    	}else {
    		// Habilitar PAS existente: transitar del paso de espera hacia el paso inicial
    		
    		List<PgimPasAuxDTO> lPgimPas = this.pasAuxRepository.listarTareasPasActivasPorId(pgimPasDTO.getIdPas(), 
    				ConstantesUtil.PARAM_RP_PREPARAR_DOC_PAS__CONFIRMAR_HC_INFRACCIONES_PAS);
    		
    		PgimPasAuxDTO pgimPasAuxDTOExistente = null;
    		
    		if(lPgimPas.size() > 0) {
    			pgimPasAuxDTOExistente = lPgimPas.get(0);
    		}else {
    			throw new PgimException(TipoResultado.ERROR, "No se encontró la tarea activa del PAS existente");
    		}
    		
    		PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService.obtenerInstanciaPasoActualPorIdInstanciaPaso(
    				pgimPasAuxDTOExistente.getIdInstanciaPaso());
    		
	        PgimInstanciaPasoDTO pgimInstanciaPasoDTONuevo = new PgimInstanciaPasoDTO();
	        pgimInstanciaPasoDTONuevo.setIdRelacionPaso(ConstantesUtil.PARAM_RP_CONFIRMAR_HC_INFRACCIONES_PAS__PREPARAR_DOC_PAS); 
	        pgimInstanciaPasoDTONuevo.setIdPersonaEqpOrigen(pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino()); // origen: La persona que tiene el PAS actualmente
	        pgimInstanciaPasoDTONuevo.setIdPersonaEqpDestino(pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino()); // destino: La persona que tiene el PAS actualmente
	        pgimInstanciaPasoDTONuevo.setDeMensaje("Retomar proceso administrativo sancionador");	     
	        pgimInstanciaPasoDTONuevo.setIdInstanciaProceso(pgimPasAuxDTOExistente.getIdInstanciaProcesoPas());
	        pgimInstanciaPasoDTONuevo.setIdInstanciaPasoPadre(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());
	
	        PgimInstanciaPaso pgimInstanciaPasoCreado = this.flujoTrabajoService
	                .asignarPasoProceso(pgimInstanciaPasoDTONuevo, ConstantesUtil.METODO_RETOMAR_PAS, auditoriaDTO);
	        
	        pgimInstanciaPasoDTONuevo.setIdInstanciaPaso(pgimInstanciaPasoCreado.getIdInstanciaPaso());
	        
	        // Copiamos las infracciones de la Fiscalización al PAS, manteniéndolas como vigentes, 
	        // de esta manera ambos objetos de trabajo tendrán sus infracciones vigentes
	        PgimPas pgimPas = this.pasRepository.findById(pgimPasDTO.getIdPas()).orElse(null);
	        Long idSupervision = coTablaInstancia;
	        
	        this.infraccionService.copiarInfraccionesSupervision(idSupervision, pgimInstanciaPasoDTONuevo, pgimPas, false, auditoriaDTO);    		
    	}
    }

    @Override
    public PgimPasAuxDTO obtenerPasAuxPorIdInstanciaPaso(Long idInstanciaPaso) {
        return this.pasAuxRepository.obtenerPasPorIdInstanciaPaso(idInstanciaPaso);
    }

    @Override
    public List<PgimRelacionPasoDTO> filtrarPasosSiguientes(List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
            PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual) {

        Long idPasoProceso = pgimRelacionPasoActual.getPasoProcesoDestino().getIdPasoProceso();

        Long idPasoInicioResponderEscrito = null;

        if (idPasoProceso.equals(ConstPasoProcesoPas.INICIOPAS_VERIFICAR_VALIDEZ_NOTFISICA)
                || idPasoProceso.equals(ConstPasoProcesoPas.INICIOPAS_VERIFICAR_VALIDEZ_NOTELECTRONICA)) {

            idPasoInicioResponderEscrito = ConstPasoProcesoPas.INICIOPAS_RESPONDER_ESCRITO;
        } else if (idPasoProceso.equals(ConstPasoProcesoPas.FORMULACIONIFI_VERIFICAR_VALIDEZ_NOTFISICA)
                || idPasoProceso.equals(ConstPasoProcesoPas.FORMULACIONIFI_VERIFICAR_VALIDEZ_NOTELECTRONICA)) {

            idPasoInicioResponderEscrito = ConstPasoProcesoPas.FORMULACIONIFI_RESPONDER_ESCRITO;
        } else if (idPasoProceso.equals(ConstPasoProcesoPas.RESOLSANCION_VERIFICAR_VALIDEZ_NOTFISICA)
                || idPasoProceso.equals(ConstPasoProcesoPas.RESOLSANCION_VERIFICAR_VALIDEZ_NOTELECTRONICA)) {

            idPasoInicioResponderEscrito = ConstPasoProcesoPas.RESOLSANCION_RESPONDER_ESCRITO;
        } else if (idPasoProceso.equals(ConstPasoProcesoPas.RECRECONSIDERACION_VERIFICAR_VALIDEZ_NOTFISICA)
                || idPasoProceso.equals(ConstPasoProcesoPas.RECRECONSIDERACION_VERIFICAR_VALIDEZ_NOTELECTRONICA)) {

            idPasoInicioResponderEscrito = ConstPasoProcesoPas.RECRECONSIDERACION_RESPONDER_ESCRITO;
        } else if (idPasoProceso.equals(ConstPasoProcesoPas.DERIVACIONCOACTIVA_VERIFICAR_VALIDEZ_NOTFISICA)
                || idPasoProceso.equals(ConstPasoProcesoPas.DERIVACIONCOACTIVA_VERIFICAR_VALIDEZ_NOTELECTRONICA)) {

            idPasoInicioResponderEscrito = ConstPasoProcesoPas.DERIVACIONCOACTIVA_RESPONDER_ESCRITO;
        } else {
            return lPgimRelacionPasoDTOSiguientes;
        }

        lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesRptaEscrito(pgimInstanciaPasoActual,
                idPasoInicioResponderEscrito, lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);
       
        return lPgimRelacionPasoDTOSiguientes;
    }

    /**
     * Filtra los pasos siguientes en el marco la respuesta de un escrito que ha
     * llegado y requiere ser respondido.
     * 
     * @param pgimInstanciaPasoActual
     * @param inicioResponderEscrito
     * @param lPgimRelacionPasoDTOSiguientes
     * @param pgimRelacionPasoActual
     * @return
     */
    private List<PgimRelacionPasoDTO> filtrarPasosSiguientesRptaEscrito(PgimInstanciaPaso pgimInstanciaPasoActual,
            Long inicioResponderEscrito, List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
            PgimRelacionPaso pgimRelacionPasoActual) {

        PgimInstanciaPaso pgimInstanciaPasoIter = pgimInstanciaPasoActual;
        Long idPasoProcesoOrigenInmediato = pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoOrigen()
                .getIdPasoProceso();
        Long idPasoProcesoOrigenInicial = null;
        List<Long> lIdPasoProceso = new ArrayList<Long>();

        do {
            pgimInstanciaPasoIter = pgimInstanciaPasoIter.getInstanciaPasoPadre();

            if (pgimInstanciaPasoIter.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso()
                    .equals(inicioResponderEscrito)) {
                idPasoProcesoOrigenInicial = pgimInstanciaPasoIter.getPgimRelacionPaso().getPasoProcesoOrigen()
                        .getIdPasoProceso();
            }
        } while (idPasoProcesoOrigenInicial == null);

        lIdPasoProceso.add(idPasoProcesoOrigenInmediato);
        lIdPasoProceso.add(idPasoProcesoOrigenInicial);

        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream().filter(pgimRelacionPasoDTO -> {

            PgimRelacionPaso pgimRelacionPasoAverificar = this.relacionPasoRepository
                    .findById(pgimRelacionPasoDTO.getIdRelacionPaso()).orElse(null);

            return lIdPasoProceso.contains(pgimRelacionPasoAverificar.getPasoProcesoDestino().getIdPasoProceso());
        }).collect(Collectors.toList());

        return lPgimRelacionPasoDTOSiguientes;
    }
    

    @Override
    public List<PgimPasDTO> listarPorCoSupervision(String coSupervision) {
        List<PgimPasDTO> lPgimPasDTO = this.pasRepository.listarPorCoSupervision(coSupervision);

        return lPgimPasDTO;
    }

    @Override
    public String generarUrlInicioSym(String nroExpediente, AuditoriaDTO auditoriaDTO) throws Exception {

        log.info("Iniciar generación de URL para inciciar SYM....");

        String urlSym = "";

        try {

            String urlToken = this.propertiesConfig.getUrlSymRest() + ConstantesUtil.PARAM_SYM_REST_TOKEN;
            String urlLinkSym = this.propertiesConfig.getUrlSymWeb() + ConstantesUtil.PARAM_SYM_WEB_INICIO_SYM;

            String usaurio = auditoriaDTO.getUsername();
            String clave = auditoriaDTO.getPasswordSiged();

            String appInvoker = ConstantesUtil.PARAM_SYM_INVOKER;
            String externCalling = "";
            String session = ConstantesUtil.PARAM_SYM_SESSION;

            TokenOutRO result = SymSeguridadInvoker.getToken(urlToken);
            Long token = result.getToken();
            log.info("Token SYM: " + token);
            String param = "login:" + usaurio + "&password:" + clave + "&appInvokes:" + appInvoker + "&token:" + token;

            // concatenando los parámetros adicionales
            param = param + "&otherParams:nroExpediente=" + nroExpediente;
            // System.out.println("param: "+param);

            // generando el encriptado
            externCalling = EncripUtil.encrypt(session, param);

            // generando el url
            urlSym = urlLinkSym + "?session=" + session + "&externCalling=" + externCalling;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PgimException("error", "Ocurrió un error al intentar obtener la URL para Iniciar el SYM");
        }

        log.info("urlSym: " + urlSym);
        log.info("Finalizar generación de URL para inciciar SYM....");

        return urlSym;

    }

    @Override
    public String generarUrlMasInformacionSym(String nroExpediente, AuditoriaDTO auditoriaDTO) throws Exception {

        log.info("Iniciar generación de URL para más información SYM....");

        String urlSym = "";

        try {

            String urlToken = this.propertiesConfig.getUrlSymRest() + ConstantesUtil.PARAM_SYM_REST_TOKEN;
            String urlLinkSym = this.propertiesConfig.getUrlSymWeb() + ConstantesUtil.PARAM_SYM_WEB_MAS_INFORMACION;

            String usaurio = auditoriaDTO.getUsername();
            String clave = auditoriaDTO.getPasswordSiged();

            String appInvoker = ConstantesUtil.PARAM_SYM_INVOKER;
            String externCalling = "";
            String session = ConstantesUtil.PARAM_SYM_SESSION;

            TokenOutRO result = SymSeguridadInvoker.getToken(urlToken);
            Long token = result.getToken();
            log.info("Token SYM: " + token);

            if (token != null) {

                String param = "login:" + usaurio + "&password:" + clave + "&appInvokes:" + appInvoker + "&token:"
                        + token;

                // concatenando los parámetros adicionales
                param = param + "&otherParams:nroExpediente=" + nroExpediente;

                // generando el encriptado
                externCalling = EncripUtil.encrypt(session, param);

            }

            // generando el url
            urlSym = urlLinkSym + "?session=" + session + "&externCalling=" + externCalling;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PgimException("error",
                    "Ocurrió un error al intentar obtener la URL para la pantalla Más información SYM");
        }

        log.info("urlSym: " + urlSym);
        log.info("Finalizar generación de URL para más información SYM....");

        return urlSym;

    }

    @Override
    public ListSymExpedienteReporteOutRO listarExpedienteSYM(SymExpedienteReporteFiltersInRO filtersInRO) {
        log.info("invocando al servicio SYM-REST listarExpedienteParaReporteBean");
        ListSymExpedienteReporteOutRO result = null;
        try {
            result = ExpedienteInvoker.listExpedientesForReport(
                    this.propertiesConfig.getUrlSymRest() + ConstantesUtil.PARAM_SYM_REST_LISTAR_EXP_REPORTE,
                    filtersInRO);
            log.info("servicio listarExpedienteParaReporteBean ejecutado satisfactoriamente");
        } catch (Exception ex) {
            log.debug("No hay conexión con el servicio rest listarExpedienteParaReporteBean", ex);
            result = new ListSymExpedienteReporteOutRO();
            result.setResultCode(InvocationResult.FAILED.getCode());
            result.setMessage("No hay conexión con el servicio rest listExpedientesForReport " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public ListSymInfraccionOutRO buscarInfraccion(SymInfraccionInRO symInfraccionInRO) {
        log.info("invocando al servicio buscarInfraccion");
        ListSymInfraccionOutRO result = null;
        try {
            // System.out.println("URL CLIENTE " + SYM_REST_SERVER_URL +
            // RELATIVE_URL_BUSCAR_INFRACCION);
            result = InfraccionInvoker.buscarInfraccion(
                    this.propertiesConfig.getUrlSymRest() + ConstantesUtil.PARAM_SYM_REST_BUSCAR_INFRACCION,
                    symInfraccionInRO);
            log.info("servicio buscarInfraccion ejecutado satisfactoriamente");
        } catch (Exception ex) {
            log.debug("No hay conexión con el servicio rest buscarInfraccion", ex);
            result = new ListSymInfraccionOutRO();
            result.setResultCode(InvocationResult.FAILED.getCode());
            result.setMessage("No hay conexión con el servicio rest buscarInfraccion " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public List<String> obtenerAniosPasAux() {

        List<String> lAnios = this.pasAuxRepository.obtenerAniosPasAux();
        return lAnios;
    }

    @Override
    public Page<PgimPasAuxDTO> ListarReporteExpPasPaginado(PgimPasAuxDTO filtroPasAux, Pageable paginador) {

        Page<PgimPasAuxDTO> pPgimPasAuxDTO = this.pasAuxRepository
                .listarReporteExpPasPaginado(filtroPasAux.getFeCreacionPasAnio(), paginador, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.REPAS_CNCLAR_FLJO.toString())
						, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.REPAS_FNLZAR_FLJO.toString()));

        return pPgimPasAuxDTO;
    }

    @Override
    public List<PgimExppasAnioAuxDTO> listarReporteExppasAnio(PgimExppasAnioAuxDTO filtroPgimExppasAnioAuxDTO)
            throws Exception {
        List<PgimExppasAnioAuxDTO> lPgimExppasAnioAuxDTO = this.pasAuxRepository.listarReporteExppasAnio();
        return lPgimExppasAnioAuxDTO;
    }

    @Override
    public Page<PgimExppasAnioAuxDTO> listarReporteExppasAnioPaginado(PgimExppasAnioAuxDTO filtroPgimExppasAnioAuxDTO,
            Pageable paginador) throws Exception {
        Page<PgimExppasAnioAuxDTO> lPgimExppasAnioAuxDTO = this.pasAuxRepository
                .listarReporteExppasAnioPaginado(paginador);
        return lPgimExppasAnioAuxDTO;
    }

    @Override
    public List<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnio(
            PgimExppasdsuAnioAuxDTO filtroPgimExppasdsuAnioAuxDTO) throws Exception {
        List<PgimExppasdsuAnioAuxDTO> lPgimExppasdsuAnioAuxDTO = this.pasAuxRepository.listarReporteExppasdsuAnio();
        return lPgimExppasdsuAnioAuxDTO;
    }

    @Override
    public Page<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnioPaginado(
            PgimExppasdsuAnioAuxDTO filtroPgimExppasdsuAnioAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppasdsuAnioAuxDTO> lPgimExppasdsuAnioAuxDTO = this.pasAuxRepository
                .listarReporteExppasdsuAnioPaginado(paginador);
        return lPgimExppasdsuAnioAuxDTO;
    }

    @Override
    public List<PgimExppasespeMesAuxDTO> listarReporteExppasespeMes(
            PgimExppasespeMesAuxDTO filtroPgimExppasespeMesAuxDTO) throws Exception {
        List<PgimExppasespeMesAuxDTO> lPgimExppasespeMesAuxDTO = this.pasAuxRepository.listarReporteExppasespeMes();
        return lPgimExppasespeMesAuxDTO;
    }

    @Override
    public Page<PgimExppasespeMesAuxDTO> listarReporteExppasespeMesPaginado(
            PgimExppasespeMesAuxDTO filtroPgimExppasespeMesAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppasespeMesAuxDTO> lPgimExppasespeMesAuxDTO = this.pasAuxRepository
                .listarReporteExppasespeMesPaginado(paginador);
        return lPgimExppasespeMesAuxDTO;
    }

    @Override
    public Page<PgimExppasPendientesAuxDTO> listarReporteExpPersonaAsignadaPaginado(
            PgimExppasPendientesAuxDTO filtroPgimExppasPendientesAuxDTO, Pageable paginador) throws Exception {

        Page<PgimExppasPendientesAuxDTO> pPgimExppasPendientesAuxDTO = this.exppasPendientesAuxRepository
                .listarReporteExpPersonaAsignadaPaginado(filtroPgimExppasPendientesAuxDTO.getEtiquetaPersonaAsignada(),
                        paginador);
        return pPgimExppasPendientesAuxDTO;
    }

    @Override
    public Page<PgimExpPerfaAuxDTO> listarReporteExpPasPerfaseAnioPaginado(
            PgimExpPerfaAuxDTO filtroPgimExppasdsuAnioAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExpPerfaAuxDTO> lPgimExpPerfaAuxDTO = this.expPerfaAuxRepository
                .listarReporteExpPasPerfaseAnioPaginado(filtroPgimExppasdsuAnioAuxDTO.getAnioSupervision(), paginador);
        return lPgimExpPerfaAuxDTO;
    }

    @Override
    public Page<PgimExpPerpaAuxDTO> listarReporteExpPasPerPasoAnioPaginado(PgimExpPerpaAuxDTO filtroPgimExpPerpaAuxDTO,
            Pageable paginador) throws Exception {

        Page<PgimExpPerpaAuxDTO> lPgimExpPerpaAuxDTO = this.expPerpaAuxRepository
                .listarReporteExpPasPerPasoAnioPaginado(filtroPgimExpPerpaAuxDTO.getAnioSupervision(), paginador);
        return lPgimExpPerpaAuxDTO;
    }

    @Override
    public Page<PgimExppastipsustAnioAuxDTO> listarReporteExppastipsustAnioPaginado(
            PgimExppastipsustAnioAuxDTO filtroPgimExppastipsustAnioAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppastipsustAnioAuxDTO> lPgimExppastipsustAnioAuxDTO = this.pasAuxRepository
                .listarReporteExppastipsustAnioPaginado(paginador);
        return lPgimExppastipsustAnioAuxDTO;
    }

    @Override
    public Page<PgimExppasEspAnioAuxDTO> listarReporteExpPasEspecAnio(
            PgimExppasEspAnioAuxDTO filtroPgimExppasEspAnioAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppasEspAnioAuxDTO> pPgimExppasEspAnioAuxDTO = this.pasAuxRepository
                .listarReporteExpPasEspecAnioPaginado(paginador);
        return pPgimExppasEspAnioAuxDTO;
    }

    @Override
    public Page<PgimExppastipactiAnioAuxDTO> listarReporteExppastipactiAnioPaginado(
            PgimExppastipactiAnioAuxDTO filtroPgimExppastipactiAnioAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppastipactiAnioAuxDTO> lPgimExppastipactiAnioAuxDTO = this.pasAuxRepository
                .listarReporteExppastipactiAnioPaginado(paginador);
        return lPgimExppastipactiAnioAuxDTO;
    }

    @Override
    public Page<PgimExppasEvaluadorAuxDTO> listarReporteExpPasPerDsEspecPaginado(
            PgimExppasEvaluadorAuxDTO filtroPgimExppasEvaluadorAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppasEvaluadorAuxDTO> pPgimExppasEvaluadorAuxDTO = this.pasAuxRepository
                .listarReporteExpPasPerDsEspecPaginado(filtroPgimExppasEvaluadorAuxDTO.getIdDivisionSupervisora(),
                        filtroPgimExppasEvaluadorAuxDTO.getIdEspecialidad(),
                        filtroPgimExppasEvaluadorAuxDTO.getNoEvaluador(), paginador);
        return pPgimExppasEvaluadorAuxDTO;
    }

    @Override
    public Page<PgimPasAuxDTO> ListarReporteExpPasProcesoPaginado(PgimPasAuxDTO filtroPasAux, Pageable paginador) {

        Page<PgimPasAuxDTO> pPgimPasAuxDTO = this.pasAuxRepository.listarReporteExpPasProcesoPaginado(
                filtroPasAux.getIdFaseActual(), filtroPasAux.getIdPasoActual(), paginador, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.REPAS_CNCLAR_FLJO.toString())
				, this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.REPAS_FNLZAR_FLJO.toString()));

        return pPgimPasAuxDTO;
    }

    public Page<PgimExppasEstResolAuxDTO> listarReporteExppasEstResolPaginado(
            PgimExppasEstResolAuxDTO filtroPgimExppasEstResolAuxDTO, Pageable paginador) throws Exception {
        Page<PgimExppasEstResolAuxDTO> lPgimExppasEstResolAuxDTO = this.pasAuxRepository
                .listarReporteExppasEstResolPaginado(filtroPgimExppasEstResolAuxDTO.getAnioSuper(), paginador);
        return lPgimExppasEstResolAuxDTO;
    }

    @Override
    public Page<PgimDocumentoAuxDTO> listarReporteExpDocsPaginado(PgimDocumentoAuxDTO filtroPgimDocumentoAuxDTO,
            Pageable paginador) throws Exception {
        Page<PgimDocumentoAuxDTO> lPgimDocumentoAuxDTO = this.pasAuxRepository
                .listarReporteExpDocsPaginado(filtroPgimDocumentoAuxDTO.getNuExpedienteSiged(), paginador);
        return lPgimDocumentoAuxDTO;
    }
    
    @Override
    public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso,
                    PgimInstanciaPaso pgimInstanciaPaso) {

        if (!pgimRelacionPaso.getIdRelacionPaso().equals(
                        ConstantesUtil.PARAM_RP_VERIF_PAGO_MULTA_INFRAC__FORMULAR_FICHA_RESUMEN_Y_MEMO_CONF)      
		        		&& !pgimRelacionPaso.getIdRelacionPaso()
		                	.equals(ConstantesUtil.PARAM_RP_VERIF_PAGO_MULTA_INFRAC__ARCHIVAR_INFRAC_PAGO)
		                && !pgimRelacionPaso.getIdRelacionPaso()
		                	.equals(ConstantesUtil.PARAM_RP_VERIF_PAGO_MULTA_INFRAC__RESPONDER_ESCRITO_DERIV_COACTIVA)
        	) {
                return;
        }

        String msjExcepcionControlada = null;

        Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
        PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso).orElse(null);

        // Tarea clave: "Verificar pago de multa de infracciones", 
        // no se debe permitir transitar si ambos sub-flujos (principal y secundario) han convergido en dicha tarea y hay más de 1 activa actualmente
        if (pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RP_VERIF_PAGO_MULTA_INFRAC__FORMULAR_FICHA_RESUMEN_Y_MEMO_CONF)
        		|| pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RP_VERIF_PAGO_MULTA_INFRAC__ARCHIVAR_INFRAC_PAGO)
        		|| pgimRelacionPaso.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RP_VERIF_PAGO_MULTA_INFRAC__RESPONDER_ESCRITO_DERIV_COACTIVA)
        	) {
        	
        	List<PgimInstanciaPasoDTO> lIntanciaPasoDTOActuales = this.instanciaPasoRepository.obtenerPasosActualesPorInstanciaProceso(idInstanciaProceso);
    		
        	// Verificamos que haya sido la única "tarea clave" activa, por lo que ya no debería quedar ninguna de estas. 
    		int cantTareasConvergidas = lIntanciaPasoDTOActuales.stream().filter(pgimInstanciaPasoDTO ->
    					                      pgimInstanciaPasoDTO.getIdPasoProcesoDestino().equals(ConstPasoProcesoPas.VERIFICAR_PAGO_MULTA_INFRACCIONES)
    					                ).collect(Collectors.toList()).size();
    		
    		if(cantTareasConvergidas > 0) {
    			msjExcepcionControlada = String.format("No se puede asignar porque actualmente existen %s tareas activas de la actual, por favor desactive previamente una de ellas, la que corresponde al sub-flujo de 'no apelación', esta tendrá habilitada dicha opción.",
    					cantTareasConvergidas+1);
    		}
        } 
            
        if (msjExcepcionControlada != null) {
                throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); 
        }
    }

    @Override
    public void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces,
            PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {
    	
    	PgimPas pgimPas = this.pasRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);
    	
    	this.pausarPas(pgimInstanciaProces, pgimInstanciaPaso, pgimPas, auditoriaDTO);
    	
    	this.asegurarArchivamientoSupervision(pgimInstanciaProces, pgimInstanciaPaso, pgimPas, auditoriaDTO);
    	
    	this.calificarRecursoImpugnativo(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
    	
    	this.persistirRazonSocialAgenteFiscalizadoPas(pgimPas, pgimInstanciaPaso, auditoriaDTO);

        
        if (!pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                .equals(ConstPasoProcesoPas.INICIOPAS_PREPARAR_DOC_PAS)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.INICIOPAS_FORMULAR_APROBAR_OIPAS)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.FORMULACIONIFI_FORMULAR_IFI)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.FORMULACIONIFI_ELABORAR_PROPUESTA_MULTA)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.FORMULACIONIFI_APROBAR_IFI_PARTE_TECNICA)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.FORMULACIONIFI_APROBAR_IFI_PARTE_LEGAL)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.FORMULACIONIFI_RECALCULAR_MULTA_POR_DESCARGOS)
                && !pgimInstanciaPaso.getPasoProcesoDestino().getIdPasoProceso()
                        .equals(ConstPasoProcesoPas.RESOLSANCION_DETERMINAR_SANCION_ARCHIVO_AMPLIACION)) {
            return;
        }

        PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService
                .obtenerInstanciaPasoActualPorIdInstanciaPaso(pgimInstanciaPaso.getIdInstanciaPaso());

        this.infraccionService.copiarInfraccionesPas(pgimInstanciaPasoDTOActual, pgimPas, auditoriaDTO);
    }

    private String obtenerSiguienteCorrelativo(String notabla, String anio, AuditoriaDTO auditoriaDTO) {

            Long bdAnio = new Long(anio);
            PgimCorrelativoTablaDTO pgimCorrelativoTablaDTO = pasRepository
                            .obtenerCorrelativoByAnio(notabla,bdAnio);
            PgimCorrelativoTabla pgimCorrelativoTablaActual;

            if (pgimCorrelativoTablaDTO == null) {
                    // Si el registro no existe para el año, lo creamos
                    PgimCorrelativoTabla pgimCorrelativoTabla = new PgimCorrelativoTabla();

                    pgimCorrelativoTabla.setNoTabla(notabla);
                    pgimCorrelativoTabla.setNuAnioCorrelativo(bdAnio);
                    pgimCorrelativoTabla.setSeUltimoCorrelativo(1L);
                    pgimCorrelativoTabla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                    pgimCorrelativoTabla.setFeCreacion(auditoriaDTO.getFecha());
                    pgimCorrelativoTabla.setUsCreacion(auditoriaDTO.getUsername());
                    pgimCorrelativoTabla.setIpCreacion(auditoriaDTO.getTerminal());
                    pgimCorrelativoTablaActual = correlativoTablaRepository.save(pgimCorrelativoTabla);
            } else {
                    // Si el registro existe, incrementamos en 1
                    PgimCorrelativoTabla pgimCorrelativoTabla = correlativoTablaRepository
                                    .findById(pgimCorrelativoTablaDTO.getIdCorrelativoTabla()).orElse(null);
                    Long incremento = 1L + pgimCorrelativoTabla.getSeUltimoCorrelativo();
                    pgimCorrelativoTabla.setSeUltimoCorrelativo(incremento);
                    pgimCorrelativoTabla.setFeActualizacion(auditoriaDTO.getFecha());
                    pgimCorrelativoTabla.setUsActualizacion(auditoriaDTO.getUsername());
                    pgimCorrelativoTabla.setIpActualizacion(auditoriaDTO.getTerminal());
                    pgimCorrelativoTablaActual = correlativoTablaRepository.save(pgimCorrelativoTabla);
            }

            String coTabla = "";

            coTabla = notabla+"-" + anio + "-" + String.format("%05d",
                            Long.valueOf(pgimCorrelativoTablaActual.getSeUltimoCorrelativo()));

            return coTabla;
    }


    @Transactional(readOnly = false)
    private void pausarPas(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso, 
    		PgimPas pgimPas, AuditoriaDTO auditoriaDTO) throws Exception {
    	
    	Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
        
        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_PREPARAR_DOC_PAS__CONFIRMAR_HC_INFRACCIONES_PAS)) {
        	
        	// Transitar su fiscalización desde "Fiscalización completada con inicio de PAS" hacia "Confirmar hechos verificados e infracciones"        	
        	 
        	PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByIdSupervision(pgimPas.getPgimSupervision().getIdSupervision());  
     		PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService.obtenerInstanciaPasoActualPorIdInstanciaPaso(
     				pgimSupervisionDTO.getDescIdInstanciaPaso());
     		
 	        PgimInstanciaPasoDTO pgimInstanciaPasoDTONuevo = new PgimInstanciaPasoDTO();
 	        pgimInstanciaPasoDTONuevo.setIdRelacionPaso(ConstantesUtil.PARAM_RP_FISC_COMPL_PAS__CONFIRMAR_HC_INFRACCIONES); 
 	        pgimInstanciaPasoDTONuevo.setIdPersonaEqpOrigen(pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino()); // origen: La persona que tiene la superv actualmente
 	        pgimInstanciaPasoDTONuevo.setIdPersonaEqpDestino(pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino()); // destino: La persona que tiene la superv actualmente
 	        pgimInstanciaPasoDTONuevo.setDeMensaje("Retorno por aviso de re-evaluación desde el PAS");	     
 	        pgimInstanciaPasoDTONuevo.setIdInstanciaProceso(pgimSupervisionDTO.getIdInstanciaProceso());
 	        pgimInstanciaPasoDTONuevo.setIdInstanciaPasoPadre(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());
 	
 	        PgimInstanciaPaso pgimInstanciaPasoCreado = this.flujoTrabajoService
 	                .asignarPasoProceso(pgimInstanciaPasoDTONuevo, ConstantesUtil.METODO_RETORNAR_FISC_REEVAL_HV, auditoriaDTO); 	      	     
 	        
 	        // Hacemos "no vigente" las infracciones del PAS
 	       
 	        List<PgimInfraccionAuxDTO> lPgimInfraccionAuxDTOPas = this.hechoConstatadoService
 	                .listarInfraccionPorIdPas(pgimPas.getIdPas());
 	       
 	        for (PgimInfraccionAuxDTO pgimInfraccionAuxDTO : lPgimInfraccionAuxDTOPas) {
	 	    	  PgimInfraccion pgimInfraccion = this.infraccionRepository.findById(pgimInfraccionAuxDTO.getIdInfraccion()).orElse(null);
	 	    	  pgimInfraccion.setFlVigente(ConstantesUtil.FL_IND_NO); 
	 	    	  pgimInfraccion.setFeActualizacion(auditoriaDTO.getFecha());
	        	  pgimInfraccion.setUsActualizacion(auditoriaDTO.getUsername());
	        	  pgimInfraccion.setIpActualizacion(auditoriaDTO.getTerminal());
	        	  
	 	    	  this.infraccionRepository.save(pgimInfraccion);	
	 	    	  
	 	    	  // asimismo, hacemos "no vigente" los contratistas responsables de la infracción
	 	          List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO = this.infraccionContraRepository.
	 	        		  listarContratistaByIdInfraccion(pgimInfraccionAuxDTO.getIdInfraccion());
	 	            
	 	          for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {
	 	        	  PgimInfraccionContra pgimInfraccionContra = this.infraccionContraRepository.findById(pgimInfraccionContraDTO.getIdInfraccionContra()).orElse(null);
	 	        	  pgimInfraccionContra.setFlVigente(ConstantesUtil.FL_IND_NO);
	 	        	  pgimInfraccionContra.setFeActualizacion(auditoriaDTO.getFecha());
	 	        	  pgimInfraccionContra.setUsActualizacion(auditoriaDTO.getUsername());
	 	        	  pgimInfraccionContra.setIpActualizacion(auditoriaDTO.getTerminal());
	 	        	  
	 	        	  this.infraccionContraRepository.save(pgimInfraccionContra);
	 	          }
 	        }
        }
    	
    }
    
    @Transactional(readOnly = false)
    private void asegurarArchivamientoSupervision(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso, 
    		PgimPas pgimPas, AuditoriaDTO auditoriaDTO) throws Exception {
    	
    	Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
        
        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTFISICA__ESPERAR_DESCARGOS_OIPAS) ||
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTIFELECTRONICA__ESPERAR_DESCARGOS_OIPAS)        		
        	) { 
        	
        	// Transitar su fiscalización desde "Fiscalización completada con inicio de PAS" hacia "Fiscalización completada con PAS"        	
        	 
        	PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByIdSupervision(pgimPas.getPgimSupervision().getIdSupervision());
     		PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService.obtenerInstanciaPasoActualPorIdInstanciaPaso(
     				pgimSupervisionDTO.getDescIdInstanciaPaso());
     		
 	        PgimInstanciaPasoDTO pgimInstanciaPasoDTONuevo = new PgimInstanciaPasoDTO();
 	        pgimInstanciaPasoDTONuevo.setIdRelacionPaso(ConstantesUtil.PARAM_RP_FISC_COMPL_INICIO_PAS__FISC_COMPLETADA_PAS);
 	        pgimInstanciaPasoDTONuevo.setIdPersonaEqpOrigen(pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino()); // origen: La persona que tiene la superv actualmente
 	        pgimInstanciaPasoDTONuevo.setIdPersonaEqpDestino(pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino()); // destino: La persona que tiene la superv actualmente
 	        pgimInstanciaPasoDTONuevo.setDeMensaje("Se archiva la fiscalización dado que se completó la notificación del OIPAS al agente fiscalizado");
 	        pgimInstanciaPasoDTONuevo.setIdInstanciaProceso(pgimSupervisionDTO.getIdInstanciaProceso());
	        pgimInstanciaPasoDTONuevo.setIdInstanciaPasoPadre(pgimInstanciaPasoDTOActual.getIdInstanciaPaso());
 	
 	        this.flujoTrabajoService.asignarPasoProceso(pgimInstanciaPasoDTONuevo, ConstantesUtil.METODO_REENVIAR_EXP_SIGED_PARA_ARCHIVAR, auditoriaDTO);
        }    	
    }
    
    @Transactional(readOnly = false)
    private void calificarRecursoImpugnativo(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso, 
    		AuditoriaDTO auditoriaDTO) throws Exception {
    	
    	Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
        
        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_VALID_NOTIFELECTRONICA_DERIV_TASTEM__CONTINUAR_TRAM_PAS) ||
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_VALID_NOTFISICA_DERIV_TASTEM__CONTINUAR_TRAM_PAS)        		
        	) { 
        	
        	// Cambiar SC de "Recurso impugnativo por calificar" a "Recurso de apelación" 
        	
        	List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
        			pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PARAM_SC_RECURSO_IMPUG_POR_CALIFICAR);
        	
        	for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO) {	
        		
        		Long idSubcategoriaNueva = ConstantesUtil.PARAM_SC_RECURSO_APELACION;
        		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = this.subcategoriaDocRepository
    					.obtenerSubcategoriaDocPorId(idSubcategoriaNueva);
        		
        		PgimDocumento pgimDocumento = this.documentoRepository.findById(pgimDocumentoDTO.getIdDocumento()).orElse(null);
        		
        		pgimDocumento.setPgimSubcategoriaDoc(new PgimSubcategoriaDoc());
        		pgimDocumento.getPgimSubcategoriaDoc().setIdSubcatDocumento(idSubcategoriaNueva);        		
        		pgimDocumento.setDeAsuntoDocumento(pgimSubcategoriaDocDTO.getCoSubcatDocumento() + " - "
						+ pgimSubcategoriaDocDTO.getNoSubcatDocumento());        		
        		
        		this.documentoService.modificarDocumentoEntity(pgimDocumento, auditoriaDTO);        		
        	}         	 
        }
    }
    	
    /**
     * Permite agregar personal por defecto al PAS.
     * Añade Asistente/a administrativo/a Tastem
     * Añade Asistente/a administrativo/a Ejecutoría Coactiva
     * 
     * @param pgimInstanciaProces
     * @param auditoriaDTO
     * @throws Exception
     */
    private void agregarPersonalEquipoPas(PgimInstanciaPaso pgimInstanciaPaso, PgimInstanciaProces pgimInstanciaProces, AuditoriaDTO auditoriaDTO) throws Exception {    
    	
        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
    		
        // Añadimos Asistente/a administrativo/a Tastem
        if(idRelacionPaso.equals(ConstRelacionPasoPAS.ESPERARRECIMPUGNATIVO_FORMULARRESTASTEM)){
            PgimValorParametroDTO paramUsuarioTastem  = this.valorParametroRepository.obtenerValorParametroPorId(ConstantesUtil.PARAM_NO_USUARIO_TASTEM);
            
            PgimPersonaosiAuxDTO pgimPersonaosiAuxDTO = this.flujoTrabajoService.obtenerPersonalOsiNombreUsuarioWindows(paramUsuarioTastem.getNoValorParametro()); 	
            
            if(pgimPersonaosiAuxDTO != null) {
                PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = new PgimEqpInstanciaProDTO();
                
                pgimEqpInstanciaProDTO.setIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());
                pgimEqpInstanciaProDTO.setIdRolProceso(ConstantesUtil.PROCESO_ROL_ADMINISTRATIVO_TASTEM);
                pgimEqpInstanciaProDTO.setIdPersonalOsi(pgimPersonaosiAuxDTO.getIdPersonalOsi());
                pgimEqpInstanciaProDTO.setFlEsResponsable("0");
    
                this.instanciaProcesService.seleccionarEquipoInstancia(pgimEqpInstanciaProDTO, auditoriaDTO);    
            }

        }
        
        // Añadimos Asistente/a administrativo/a Ejecutoría Coactiva
        if(idRelacionPaso.equals(ConstRelacionPasoPAS.ESPERAR3MESESVERIFACA_GENERARRECAUDOS) || 
            idRelacionPaso.equals(ConstRelacionPasoPAS.ANALIZARRECURSOACA_GENERARRECAUDOS)){

            PgimValorParametroDTO paramUsuarioCoactiva  = this.valorParametroRepository.obtenerValorParametroPorId(ConstantesUtil.PARAM_NO_USUARIO_COACTIVA);
            
            PgimPersonaosiAuxDTO pgimPersonaosiAuxDTOCoactiva = this.flujoTrabajoService.obtenerPersonalOsiNombreUsuarioWindows(paramUsuarioCoactiva.getNoValorParametro()); 	
            
            if(pgimPersonaosiAuxDTOCoactiva != null) {
                PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = new PgimEqpInstanciaProDTO();
                
                pgimEqpInstanciaProDTO.setIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());
                pgimEqpInstanciaProDTO.setIdRolProceso(ConstantesUtil.PROCESO_ROL_ADMINISTRATIVO_COACTIVA);
                pgimEqpInstanciaProDTO.setIdPersonalOsi(pgimPersonaosiAuxDTOCoactiva.getIdPersonalOsi());
                pgimEqpInstanciaProDTO.setFlEsResponsable("0");
    
                this.instanciaProcesService.seleccionarEquipoInstancia(pgimEqpInstanciaProDTO, auditoriaDTO);    
            }
        
        }

    }
    
    /**
     * Persiste la razón social actual del agente fiscalizado de la fiscalización relacionada al PAS
     * 
     * @param pgimInstanciaProces
     * @param pgimInstanciaPaso
     * @param auditoriaDTO
     * @throws Exception
     */
    @Transactional(readOnly = false)
    private void persistirRazonSocialAgenteFiscalizadoPas(PgimPas pgimPas,
            PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception {

        Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
        
        if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_ARCHIVAR_PAS_RESOL_ARCHIVO__PAS_ARCHIVADO_RESOL_ARCHIVO) ||
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_ARCHIVAR_INFRAC_NULIDAD_TOTAL_FUNDADO_TASTEM__INFRAC_ARCHIV_NULIDAD_TASTEM) || 
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_DERIVAR_EJEC_COACTIVA__EXPED_DERIVADO_CAOACTIVA) || 
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_ARCHIVAR_EXP_NULIDAD_TOTAL_FUNDADO_PJ__EXP_ARCHIV_NULIDAD_PJ) || 
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_ARCHIVAR_EXP_PAGO__EXP_ARCHIV_MULTA_PAGADA) || 
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_PREPARAR_DOC_PAS__CONFIRMAR_HC_INFRACCIONES_PAS) || 
        	idRelacionPaso.equals(ConstantesUtil.PARAM_RP_ARCHIVAR_INFRAC_RR_FUNDADO__EXP_ARCHIVADO_FUNDADO) 
        	) {
        	
        	Long idSupervision = pgimPas.getPgimSupervision().getIdSupervision();
        	
        	PgimSupervision pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);
 	        
 	        if(pgimSupervision == null) {
 	        	throw new PgimException(TipoResultado.ERROR, "No se pudo encontrar la fiscalización relacionada con id " + idSupervision + " para persistir la razón social del agente fiscalizado");
 	        	
 	        }else if(pgimSupervision.getPgimAgenteSupervisado() == null) {
 	        	throw new PgimException(TipoResultado.ERROR, "La fiscalización relacionada al PAS no tiene registrado el agente fiscalizado el cual se necesita para persistir su razón social");
 	        }
 	        
 	        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.obtenerAgenteSupervisadoPorIdAs(pgimSupervision.getPgimAgenteSupervisado().getIdAgenteSupervisado());
 	        
 	        if(pgimAgenteSupervisadoDTO == null) {
 	        	throw new PgimException(TipoResultado.ERROR, "No se pudo encontrar el agente fiscalizado para persistir su razón social");
 	        }
 	       
 	        pgimPas.setNoRazonSocialAfiscalizado(pgimAgenteSupervisadoDTO.getDescNoRazonSocial());	 	        
 	        pgimPas.setFeActualizacion(auditoriaDTO.getFecha());
 	        pgimPas.setUsActualizacion(auditoriaDTO.getUsername());
 	        pgimPas.setIpActualizacion(auditoriaDTO.getTerminal());
 	        
 	        this.supervisionRepository.save(pgimSupervision);
        	
        }     
    }  
    
    @Override
    public Integer contarPasPendientes(AuditoriaDTO auditoriaDTO) {
    	
    	Integer cantidadPendientes = this.pasAuxRepository.contarPasPendientes(auditoriaDTO.getUsername()); 
    	
    	return cantidadPendientes;    	
    }

    @Override
    public Page<PgimPasAuxDTO> obtenerFiscalizacionPasPorUnidadMineraPaginado(PgimPasAuxDTO filtroPasDTO, Pageable paginador) throws Exception {

       paginador = this.activarOrdenamiento(filtroPasDTO, paginador);

       Page<PgimPasAuxDTO> pPgimPasAuxDTO = this.pasAuxRepository.listarPasEnGeneralPaginado(
                                        filtroPasDTO.getIdUnidadMinera(),
                                        filtroPasDTO.getDescCoPas(),
		                        		filtroPasDTO.getIdEspecialidad(),
		                        		filtroPasDTO.getFeOrigenDocumento(),
		                        		filtroPasDTO.getIncumplimientos(),
		                        		filtroPasDTO.getInfracciones(),
                                        filtroPasDTO.getNoPersonaAsignada(),
                                        filtroPasDTO.getIdFaseActual(),
                                        filtroPasDTO.getIdPasoActual(),
                                        filtroPasDTO.getIdMotivoSupervision(),
                                        filtroPasDTO.getDescPrograma(),
                                        filtroPasDTO.getAsNoRazonSocial(),
                                        paginador);
		        
		return pPgimPasAuxDTO;
    }

    public Pageable activarOrdenamiento(PgimPasAuxDTO filtroPasDTO, Pageable paginador) throws Exception {


        if(filtroPasDTO.getSortColumna() != null && filtroPasDTO.getSortDirection() != null){

            if(filtroPasDTO.getSortColumna().equals("descCoPas") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "coPas"));
            } else if(filtroPasDTO.getSortColumna().equals("descCoPas") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "coPas"));
            }
            
            else if(filtroPasDTO.getSortColumna().equals("noEspecialidad") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noEspecialidad"));
            } else if(filtroPasDTO.getSortColumna().equals("noEspecialidad") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noEspecialidad"));
            }

            else if(filtroPasDTO.getSortColumna().equals("feOrigenDocumento") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "feOrigenDocumento"));
            } else if(filtroPasDTO.getSortColumna().equals("feOrigenDocumento") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "feOrigenDocumento"));
            }

            else if(filtroPasDTO.getSortColumna().equals("incumplimientos") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "incumplimientos"));
            } else if(filtroPasDTO.getSortColumna().equals("incumplimientos") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "incumplimientos"));
            }

            else if(filtroPasDTO.getSortColumna().equals("infracciones") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "infracciones"));
            } else if(filtroPasDTO.getSortColumna().equals("infracciones") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "infracciones"));
            }

            else if(filtroPasDTO.getSortColumna().equals("noPersonaAsignada") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noPersonaAsignada"));
            } else if(filtroPasDTO.getSortColumna().equals("noPersonaAsignada") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noPersonaAsignada"));
            }

            else if(filtroPasDTO.getSortColumna().equals("noFaseActual") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noFaseActual"));
            } else if(filtroPasDTO.getSortColumna().equals("noFaseActual") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noFaseActual"));
            }

            else if(filtroPasDTO.getSortColumna().equals("noPasoActual") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "noPasoActual"));
            } else if(filtroPasDTO.getSortColumna().equals("noPasoActual") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "noPasoActual"));
            }

            else if(filtroPasDTO.getSortColumna().equals("deMotivoSupervision") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "deMotivoSupervision"));
            } else if(filtroPasDTO.getSortColumna().equals("deMotivoSupervision") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "deMotivoSupervision"));
            }

            else if(filtroPasDTO.getSortColumna().equals("descPrograma") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "descPrograma"));
            } else if(filtroPasDTO.getSortColumna().equals("descPrograma") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "descPrograma"));
            }

            else if(filtroPasDTO.getSortColumna().equals("asNoRazonSocial") && filtroPasDTO.getSortDirection().equals("asc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.ASC, "asNoRazonSocial"));
            } else if(filtroPasDTO.getSortColumna().equals("asNoRazonSocial") && filtroPasDTO.getSortDirection().equals("desc")){
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Sort.Direction.DESC, "asNoRazonSocial"));
            }
            
            else{
                paginador = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize());
            }
           
        }
        return paginador;
    }
}
