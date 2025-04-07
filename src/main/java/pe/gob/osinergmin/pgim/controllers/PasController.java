package pe.gob.osinergmin.pgim.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.osinergmin.sym.remote.rest.ro.in.SymExpedienteReporteFiltersInRO;
import gob.osinergmin.sym.remote.rest.ro.in.SymInfraccionInRO;
import gob.osinergmin.sym.remote.rest.ro.out.SymInfraccionOutRO;
import gob.osinergmin.sym.remote.rest.ro.out.list.ListSymExpedienteReporteOutRO;
import gob.osinergmin.sym.remote.rest.ro.out.list.ListSymInfraccionOutRO;
import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerfaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerpaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEspAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEstResolAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEvaluadorAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasPendientesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasdsuAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasespeMesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppastipactiAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppastipsustAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.PasRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PasService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a PAS
 * 
 * @descripción: PAS (Proceso administrativo de sanción)
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 18/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/procesoadminsancion")
public class PasController extends BaseController {

    @Autowired
    private PasService pasService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private InstanciaProcesService instanciaProcesService;
    
    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

    @Autowired
    private InstanciaProcesRepository instanciaProcesRepository;

    @Autowired
    private SupervisionService supervisionService;

    @Autowired
    private PasRepository pasRepository;

    @Autowired
    private MotivoSupervisionService motivoSupervisionService;
    
    @Autowired
	private PrgrmSupervisionService prgrmSupervisionService;

    @PreAuthorize("hasAnyAuthority('fc-lista_AC') or hasAnyAuthority('rep005_AC')" )
    @PostMapping("/filtrar")
    public ResponseEntity<Page<PgimPasAuxDTO>> filtrar(@RequestBody PgimPasAuxDTO filtroPas, Pageable paginador)
            throws Exception {

        Page<PgimPasAuxDTO> lPgimPasDTO = this.pasService.filtrar(filtroPas, paginador, this.obtenerAuditoria());

        return new ResponseEntity<Page<PgimPasAuxDTO>>(lPgimPasDTO, HttpStatus.OK);
    }

    /**
     * Me permite filtrar por Número de expediente siged
     * 
     * @param palabra = NuExpedienteSiged
     * @return
     */
    @GetMapping("/filtrar/nuExpedienteSiged/{palabra}")
    public ResponseEntity<?> listarPorNuExpedienteSiged(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPasDTO> lPgimPasDTONuExpedienteSiged = null;

        if (palabra.equals("_vacio_")) {
            lPgimPasDTONuExpedienteSiged = new ArrayList<PgimPasDTO>();
            respuesta.put("mensaje", "No se encontraron los números de expediente siged");
            respuesta.put("lPgimPasDTONuExpedienteSiged", lPgimPasDTONuExpedienteSiged);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPasDTONuExpedienteSiged = this.pasService.listarPorNuExpedienteSiged(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los números de expediente siged");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los números de expediente siged");
        respuesta.put("lPgimPasDTONuExpedienteSiged", lPgimPasDTONuExpedienteSiged);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener las configuraciones necesarias para el listado de PAS. Acá se
     * incluyen configuraciones como: Tipo de especialidad.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        PgimPersonaDTO pgimPersonaDTO = null;
        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            lPgimFaseProcesoDTO = this.instanciaProcesService
                    .obtenerFasesPorIdProceso(ConstantesUtil.PARAM_PROCESO_FISCALIZACION);

            lPgimPasoProcesoDTO = this.instanciaProcesService
                    .obtenerPasosPorIdProceso(ConstantesUtil.PARAM_PROCESO_FISCALIZACION);

            pgimPersonaDTO = this.flujoTrabajoService
                    .obtenerPersonaPorNombreUsuario(this.obtenerAuditoria().getUsername());

            lPgimMotivoSupervisionDTO = this.motivoSupervisionService.obtenerMotivoSupervision();

   			lPgimPrgrmSupervisionDTO = this.prgrmSupervisionService.obtenerPrgrmSupervision();

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("pgimPersonaDTO", pgimPersonaDTO);
        respuesta.put("usuarioAutenticado", this.obtenerAuditoria().getUsername());
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);
		respuesta.put("lPgimPrgrmSupervisionDTO", lPgimPrgrmSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrar/listarProgramaPorPalabraClave/{palabra}")
	public ResponseEntity<?> listarProgramaPorPalabraClave(@PathVariable String palabra) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = null;

		if (palabra.equals("_vacio_")) {
			lPgimPrgrmSupervisionDTO = new ArrayList<PgimPrgrmSupervisionDTO>();
			respuesta.put("mensaje", "Se encontraron los agentes supervisados");
			respuesta.put("lPgimPrgrmSupervisionDTO", lPgimPrgrmSupervisionDTO);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		
		try {
			lPgimPrgrmSupervisionDTO = this.prgrmSupervisionService.obtenerProgramaAutocompletado(palabra);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del agente supervisado");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los agentes supervisados");
		respuesta.put("lPgimPrgrmSupervisionDTO", lPgimPrgrmSupervisionDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

    /**
     * Permite obtener el PAS usado en el modo consulta y edición del frontend
     * 
     * @param idPas
     * @return
     * @throws Exception
     */
    // @PreAuthorize("hasAnyAuthority('sp-lista_CO')")
    @GetMapping("/obtenerPasPorIdInstanciaPaso/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerPasPorIdInstanciaPaso(@PathVariable Long idInstanciaPaso) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

        PgimPasDTO pgimPasDTO = null;
        PgimInstanPasoAuxDTO pgimInstanPasoAuxDTOActual = null;
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
        PgimSupervisionDTO pgimSupervisionDTO = null;
        Long idPas = null;

        if(idInstanciaPaso != null){
            Long idInstanciaProces = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, idInstanciaPaso, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {

            PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);
            PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso()).orElse(null);

            idPas = pgimInstanciaProces.getCoTablaInstancia();

            pgimPasDTO = this.pasService.obtenerPasPorId(idPas, idInstanciaPaso);
            if (pgimPasDTO == null) {
                mensaje = String.format("El PAS con el id: %d y idInstanciaPaso: %d no existe en la base de datos", idPas, idInstanciaPaso);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606
    
                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }
            
            pgimInstanPasoAuxDTOActual = this.flujoTrabajoService
                    .obtenerInstanciaPasoAuxPorId(idInstanciaPaso); 

            lPgimFaseProcesoDTO = this.flujoTrabajoService
                    .obtenerFasesInstanciaProceso(pgimInstanPasoAuxDTOActual.getIdInstanciaProceso());

            pgimSupervisionDTO = this.supervisionService.obtenerSupervisionPorId(pgimPasDTO.getIdSupervision());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el PAS");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (final PgimException e) {
            // Manejo de logs
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el PAS");
            respuesta.put("error", e.getMensaje());
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (pgimInstanPasoAuxDTOActual == null) {
            mensaje = String.format(
                    "El PAS con el id: %d aún no registra un paso actual en el respectivo flujo de trabajo", idPas);

            respuesta.put("mensaje", mensaje);
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El PAS ha sido recuperado");
        respuesta.put("pgimPasDTO", pgimPasDTO);
        respuesta.put("pgimInstanPasoAuxDTOActual", pgimInstanPasoAuxDTOActual);
        respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerPasAuxPorId/{idInstanciaPaso}")
    public ResponseEntity<?> obtenerPasAuxPorId(@PathVariable Long idInstanciaPaso) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimPasAuxDTO pgimPasAuxDTO = null;

        if(idInstanciaPaso != null){
            Long idInstanciaProces = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, idInstanciaPaso, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {

            PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);
            PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso()).orElse(null);

            pgimPasAuxDTO = this.pasService.obtenerPasAuxPorIdInstanciaPaso(idInstanciaPaso);
            
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el PAS auxiliar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimPasAuxDTO == null) {
            String mensaje = String.format("El PAS con el idInstanciaPaso: %d no existe en la base de datos", idInstanciaPaso);
            respuesta.put("mensaje", mensaje);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // DATA

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El PAS auxiliar ha sido recuperado");
        respuesta.put("pgimPasAuxDTO", pgimPasAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite obtener la información básica de un PAS, usado en la tarjeta informativa del
     * frontend 
     * 
     * @param idPas
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerPasPorIdPas/{idPas}")
    public ResponseEntity<?> obtenerPasPorIdPas(@PathVariable Long idPas) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

        PgimPasDTO pgimPasDTO = null;
        PgimSupervisionDTO pgimSupervisionDTO = null;
        
		respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());

        try {
            pgimPasDTO = this.pasService.obtenerPasPorIdPas(idPas);

            if (pgimPasDTO == null) {
                mensaje = String.format("El PAS con el id: %d no existe en la base de datos", idPas);
                respuesta.put("mensaje", mensaje);
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); 

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
            }
            
            pgimSupervisionDTO = this.supervisionService.obtenerSupervisionPorId(pgimPasDTO.getIdSupervision());
            
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el PAS");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); 
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El PAS ha sido recuperado");
        respuesta.put("pgimPasDTO", pgimPasDTO);
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Me permite listar por cosupervision || noUnidadMinera de la lista de
     * Pas
     * 
     * @param palabra = coSupervision || noUnidadMinera
     * @return
     */
    @GetMapping("/listarPorCoSupervision/{coSupervision}")
    public ResponseEntity<?> listarPorCoSupervision(@PathVariable String coSupervision) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPasDTO> lPgimPasDTO = null;

        if (coSupervision.equals("_vacio_")) {
            lPgimPasDTO = new ArrayList<PgimPasDTO>();
            respuesta.put("mensaje", "No se encontraron la fiscalización");
            respuesta.put("lPgimPasDTO", lPgimPasDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPasDTO = this.pasService.listarPorCoSupervision(coSupervision);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de fiscalización");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron la fiscalización");
        respuesta.put("lPgimPasDTO", lPgimPasDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
   
    @GetMapping("/iniciarSym/{nroExpediente}")
    public ResponseEntity<?> iniciarSym(@PathVariable String nroExpediente) throws Exception {
        
    	Map<String, Object> respuesta = new HashMap<>();        
    	Map<String, Object> respuestaLog = new HashMap<>();        
    	
        Long idInstanciaProces = this.pasRepository.obtenerPasPorExpedienteSiged(nroExpediente).getIdInstanciaProceso();
        if(idInstanciaProces != null){
            respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }
        
    	//consultamos si el expediente ya existe en el SYM
    	SymExpedienteReporteFiltersInRO filtersInRO = new SymExpedienteReporteFiltersInRO();
    	filtersInRO.setNroExpediente(nroExpediente);
    	ListSymExpedienteReporteOutRO listSymExpedienteReporteOutRO = this.pasService.listarExpedienteSYM(filtersInRO);
        
    	if(!listSymExpedienteReporteOutRO.getResultCode().equals(1)) {    		    		      
            respuesta.put("resultado", "Error");
            respuesta.put("mensaje", listSymExpedienteReporteOutRO.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + listSymExpedienteReporteOutRO.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION

    	}
    	else {
    		
    		if(listSymExpedienteReporteOutRO.getTotalCount()>0) {    			
    			respuesta.put("resultado", "Alerta");
                respuesta.put("mensaje", "El expediente SYM ya existe, no se puede iniciar nuevamente");    			
			    log.error(respuestaLog.get("codigoObjeto").toString() + ".[El expediente SYM ya existe, no se puede iniciar nuevamente]" ); // STORY:PGIM-7606 // EXCEPTION
    		
            }else {    		
	    		String urlSym = this.pasService.generarUrlInicioSym(nroExpediente, this.obtenerAuditoria());      
	            respuesta.put("resultado", "OK");
	            respuesta.put("mensaje", "URL de inicio del SYM generada");
	            respuesta.put("urlSym", urlSym);
    		}
    		
    	}
    	
        

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    @GetMapping("/masInformacionSym/{nroExpediente}")
    public ResponseEntity<?> masInformacionSym(@PathVariable String nroExpediente) throws Exception {
        
    	Map<String, Object> respuesta = new HashMap<>();        
    	
    	//consultamos si el expediente ya existe en el SYM
    	SymExpedienteReporteFiltersInRO filtersInRO = new SymExpedienteReporteFiltersInRO();
    	filtersInRO.setNroExpediente(nroExpediente);
    	ListSymExpedienteReporteOutRO listSymExpedienteReporteOutRO = this.pasService.listarExpedienteSYM(filtersInRO);
        
    	if(!listSymExpedienteReporteOutRO.getResultCode().equals(1)) {    		    		      
            respuesta.put("resultado", "Error");
            respuesta.put("mensaje", listSymExpedienteReporteOutRO.getMessage());
    	}
    	else {
    		
    		if(listSymExpedienteReporteOutRO.getTotalCount()==0) {    			
    			respuesta.put("resultado", "Alerta");
                respuesta.put("mensaje", "El expediente SYM no existe, no se puede ingresar a la pantalla 'Más información del SYM'");    			
    		}else {    		
	    		String urlSym = this.pasService.generarUrlMasInformacionSym(nroExpediente, this.obtenerAuditoria());      
	            respuesta.put("resultado", "OK");
	            respuesta.put("mensaje", "URL de 'Más inforamción' del SYM generada");
	            respuesta.put("urlSym", urlSym);
    		}
    		
    	}
    	
        

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    @GetMapping("/irAlSym/{nroExpediente}")
    public ResponseEntity<?> irAlSym(@PathVariable String nroExpediente) throws Exception {
        
    	Map<String, Object> respuesta = new HashMap<>();        
    	Map<String, Object> respuestaLog = new HashMap<>();        
    	
        if(nroExpediente != null){
            Long idInstanciaProces = this.pasRepository.obtenerPasPorExpedienteSiged(nroExpediente).getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());

        }

    	//consultamos si el expediente ya existe en el SYM
    	SymExpedienteReporteFiltersInRO filtersInRO = new SymExpedienteReporteFiltersInRO();
    	filtersInRO.setNroExpediente(nroExpediente);
    	ListSymExpedienteReporteOutRO listSymExpedienteReporteOutRO = this.pasService.listarExpedienteSYM(filtersInRO);
        
    	if(!listSymExpedienteReporteOutRO.getResultCode().equals(1)) {    		    		      
            respuesta.put("resultado", "Error");
            respuesta.put("mensaje", listSymExpedienteReporteOutRO.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + listSymExpedienteReporteOutRO.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
    	}
    	else {
    		
    		if(listSymExpedienteReporteOutRO.getTotalCount()>0) {    			
    			//El expediente SYM ya existe --> llamar a servicio más información
    			String urlSym = this.pasService.generarUrlMasInformacionSym(nroExpediente, this.obtenerAuditoria());      
	            respuesta.put("resultado", "OK");
	            respuesta.put("mensaje", "URL de 'Más inforamción' del SYM generada");
	            respuesta.put("urlSym", urlSym);
                
    		}else {
    			//El expediente SYM no existe --> llamar a servicio iniciar sym
	    		String urlSym = this.pasService.generarUrlInicioSym(nroExpediente, this.obtenerAuditoria());      
	            respuesta.put("resultado", "OK");
	            respuesta.put("mensaje", "URL de inicio del SYM generada");
	            respuesta.put("urlSym", urlSym);
    		}
    		
    	}
    	
        

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    @GetMapping("/listarInfraccionesSym/{nroExpediente}")
    public ResponseEntity<?> listarInfraccionesSym(@PathVariable String nroExpediente) throws Exception {
           	
    	Map<String, Object> respuesta = new HashMap<>();        
    	Map<String, Object> respuestaLog = new HashMap<>();        
    	
        if(nroExpediente != null){
            Long idInstanciaProces = this.pasRepository.obtenerPasPorExpedienteSiged(nroExpediente).getIdInstanciaProceso();
            if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        }else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());

        }

    	//Obtenemos el expediente SYM
    	SymExpedienteReporteFiltersInRO filtersInRO = new SymExpedienteReporteFiltersInRO();
    	filtersInRO.setNroExpediente(nroExpediente);
    	ListSymExpedienteReporteOutRO listSymExpedienteReporteOutRO = this.pasService.listarExpedienteSYM(filtersInRO);
        
    	if(!listSymExpedienteReporteOutRO.getResultCode().equals(1)) {    		    		      
            respuesta.put("resultado", "Error");
            respuesta.put("mensaje", "Error al obtener el expediente SYM "+ nroExpediente+ ": " +listSymExpedienteReporteOutRO.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + nroExpediente+ ": " +listSymExpedienteReporteOutRO.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
    	
        }
    	else {
    		
    		if(listSymExpedienteReporteOutRO.getTotalCount()>0) {
 
    			SymInfraccionInRO symInfraccionInRO = new SymInfraccionInRO();
    			symInfraccionInRO.setIdExpediente(listSymExpedienteReporteOutRO.getExpedientes().get(0).getIdExpediente());
    			symInfraccionInRO.setEstadoRegistro("1");    			
    			ListSymInfraccionOutRO listSymInfraccionOutRO = this.pasService.buscarInfraccion(symInfraccionInRO);
    			
    			if (!listSymInfraccionOutRO.getResultCode().equals(1)) {
    				respuesta.put("resultado", "Error");
    	            respuesta.put("mensaje", "Error al obtener la lista de infracciones del expediente SYM "+ nroExpediente+ ": " +listSymInfraccionOutRO.getMessage());
			        log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + respuesta.get("mensaje").toString() + "]" ); // STORY:PGIM-7606 // EXCEPTION
				}
    			else {
    				respuesta.put("resultado", "OK");
    	            respuesta.put("mensaje", "Lista de infracciones obtenida exitosamente");    	            
    	            
    	            if(listSymInfraccionOutRO.getListaInfracciones().size()>0) {
    	            	//Calculamos los valores totales
    	            	List<SymInfraccionOutRO> lSymInfraccionOutRO = listSymInfraccionOutRO.getListaInfracciones();
    	            	SymInfraccionOutRO symInfraccionOutRO = this.obtenerValoresTotalizadosInraccion(lSymInfraccionOutRO);
    	            	lSymInfraccionOutRO.add(symInfraccionOutRO);        	            
        	            respuesta.put("listaInfracciones", lSymInfraccionOutRO);
    	            	
    	            }
    	            else
    	            	respuesta.put("listaInfracciones", listSymInfraccionOutRO.getListaInfracciones());
    	            	
    	                	            
    			}
    			
    			
    			
    		}else {    		
	    		      
	            respuesta.put("resultado", "Alerta");
	            respuesta.put("mensaje", "El nro. de expediente " + nroExpediente + " no se encuentra registrado en el SYM");	
                log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + respuesta.get("mensaje").toString() + "]" ); // STORY:PGIM-7606 // EXCEPTION

    		}
    		
    	}

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    private SymInfraccionOutRO obtenerValoresTotalizadosInraccion(List<SymInfraccionOutRO> lSymInfraccionOutRO) {
    	
    	SymInfraccionOutRO symInfraccionOutRO = new SymInfraccionOutRO();
    	
    	BigDecimal montoTotal = new BigDecimal("0.000");
    	BigDecimal montoDescuento = new BigDecimal("0.000");
    	BigDecimal montoPagado = new BigDecimal("0.000");
    	BigDecimal redondeo = new BigDecimal("0.000");
    	BigDecimal montoAjusteContable = new BigDecimal("0.000");
    	BigDecimal montoPendiente = new BigDecimal("0.000");
    	
    	for (SymInfraccionOutRO symInfraccionOutRO2 : lSymInfraccionOutRO) {
    		
			if(symInfraccionOutRO2.getMontoTotal()!=null)
				montoTotal = montoTotal.add(symInfraccionOutRO2.getMontoTotal());
						
			if(symInfraccionOutRO2.getMontoDescuento()!=null)
				montoDescuento = montoDescuento.add(symInfraccionOutRO2.getMontoDescuento());
			
			if(symInfraccionOutRO2.getMontoPagado()!=null)
				montoPagado = montoPagado.add(symInfraccionOutRO2.getMontoPagado());
			
			if(symInfraccionOutRO2.getRedondeo()!=null)
				redondeo = redondeo.add(symInfraccionOutRO2.getRedondeo());
			
			if(symInfraccionOutRO2.getMontoAjusteContable()!=null)
				montoAjusteContable = montoAjusteContable.add(symInfraccionOutRO2.getMontoAjusteContable());
			
			if(symInfraccionOutRO2.getMontoPendiente()!=null)
				montoPendiente = montoPendiente.add(symInfraccionOutRO2.getMontoPendiente());
			
		}    	
    	
    	
    	symInfraccionOutRO.setMontoTotal(montoTotal);
        symInfraccionOutRO.setMontoDescuento(montoDescuento);
        symInfraccionOutRO.setMontoPagado(montoPagado);
        symInfraccionOutRO.setRedondeo(redondeo);
        symInfraccionOutRO.setMontoAjusteContable(montoAjusteContable);
        symInfraccionOutRO.setMontoPendiente(montoPendiente);
        
    	return symInfraccionOutRO;
    	
    }
    
    
    /**
     * reportes
     * Permite obtener las configuraciones necesarias para el reporte relacionado con PAS
     * 
     * @return
     */
   @GetMapping("/obtenerConfiguracionesReporte")
   public ResponseEntity<?> obtenerConfiguracionesReporte() throws Exception {

       Map<String, Object> respuesta = new HashMap<>();

       List<PgimEspecialidadDTO> lPgimEspecialidadDTOEspecialidad = null;
       PgimPersonaDTO pgimPersonaDTO = null;
       List<PgimValorParametroDTO> lPgimDivisionDTO = null;
       List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;
       List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;

       try {
           lPgimEspecialidadDTOEspecialidad = this.parametroService
                   .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
           pgimPersonaDTO = this.flujoTrabajoService
                   .obtenerPersonaPorNombreUsuario(this.obtenerAuditoria().getUsername());
           
			lPgimDivisionDTO = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);
			
			lPgimFaseProcesoDTO = this.instanciaProcesService.obtenerFasesPorIdProceso(ConstantesUtil.PARAM_PROCESO_FISCALIZACION);
            lPgimPasoProcesoDTO = this.instanciaProcesService.obtenerPasosPorIdProceso(ConstantesUtil.PARAM_PROCESO_FISCALIZACION);
            
       } catch (DataAccessException e) {
           respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
           respuesta.put("error", e.getMostSpecificCause().getMessage());

           log.error(e.getMostSpecificCause().getMessage(), e);

           return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
       }

       respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
       respuesta.put("lPgimEspecialidadDTOEspecialidad", lPgimEspecialidadDTOEspecialidad);
       respuesta.put("pgimPersonaDTO", pgimPersonaDTO);
       respuesta.put("usuarioAutenticado", this.obtenerAuditoria().getUsername());
       respuesta.put("lPgimDivisionDTO", lPgimDivisionDTO);
       respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);
       respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);

       return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
   }
   
    
    /**
     * Permite obtener los años de PAS auxiliar para ser usado en los filtro
     * @return
     */
    @GetMapping("/obtenerAniosPasAux")
    public ResponseEntity<?> obtenerAniosPasAux() {

        Map<String, Object> respuesta = new HashMap<>();
        /**List<PgimPasAuxDTO> aniosPgimPasAuxDTO = null;**/
        List<String> aniosPgimPasAuxDTO = null;
        
        try {
        	aniosPgimPasAuxDTO = this.pasService.obtenerAniosPasAux();
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los años de PAS");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los años de PAS");
        respuesta.put("lAniosPgimPasAuxDTO", aniosPgimPasAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite obtener la lista preparada de expedientes detallados con PAS usado en reporte correspondiente 
     * @param filtroPgimPasAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/ListarExpPasAux")
    public ResponseEntity<Page<PgimPasAuxDTO>> ListarReporteExpPasAuxPaginado (@RequestBody PgimPasAuxDTO filtroPgimPasAuxDTO, Pageable paginador) throws Exception {
        
        Page<PgimPasAuxDTO> lPgimPasAuxDTO = this.pasService.ListarReporteExpPasPaginado(filtroPgimPasAuxDTO, paginador);

        return new ResponseEntity<Page<PgimPasAuxDTO>>(lPgimPasAuxDTO, HttpStatus.OK);
    }
   
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por año y fase usado en reporte correspondiente 
     * 
     * @param filtroPgimExppasAnioAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasAnio")
	public ResponseEntity<List<PgimExppasAnioAuxDTO>> listarReporteExppasAnio(
			@RequestBody PgimExppasAnioAuxDTO filtroPgimExppasAnioAuxDTO) throws Exception {
		
		List<PgimExppasAnioAuxDTO> lPgimExppasAnioAuxDTO = this.pasService.listarReporteExppasAnio(filtroPgimExppasAnioAuxDTO);
		
		return new ResponseEntity<List<PgimExppasAnioAuxDTO>>(lPgimExppasAnioAuxDTO, HttpStatus.OK);
	}
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por año y fase usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExppasAnioAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasAnioPaginado")
	public ResponseEntity<Page<PgimExppasAnioAuxDTO>> listarReporteExppasAnioPaginado(
			@RequestBody PgimExppasAnioAuxDTO filtroPgimExppasAnioAuxDTO, Pageable paginador) throws Exception {	
		
		Page<PgimExppasAnioAuxDTO> lPgimExppasAnioAuxDTO = this.pasService.listarReporteExppasAnioPaginado(filtroPgimExppasAnioAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimExppasAnioAuxDTO>>(lPgimExppasAnioAuxDTO, HttpStatus.OK);
	}
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por división supervisora y  año usado en reporte correspondiente 
     * 
     * @param filtroPgimExppasdsuAnioAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasdsuAnio")
	public ResponseEntity<List<PgimExppasdsuAnioAuxDTO>> listarReporteExppasdsuAnio(
			@RequestBody PgimExppasdsuAnioAuxDTO filtroPgimExppasdsuAnioAuxDTO) throws Exception {
		
		List<PgimExppasdsuAnioAuxDTO> lPgimExppasdsuAnioAuxDTO = this.pasService.listarReporteExppasdsuAnio(filtroPgimExppasdsuAnioAuxDTO);
		
		return new ResponseEntity<List<PgimExppasdsuAnioAuxDTO>>(lPgimExppasdsuAnioAuxDTO, HttpStatus.OK);
	}
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por división supervisora y año usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExppasdsuAnioAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasdsuAnioPaginado")
	public ResponseEntity<Page<PgimExppasdsuAnioAuxDTO>> listarReporteExppasdsuAnioPaginado(
			@RequestBody PgimExppasdsuAnioAuxDTO filtroPgimExppasdsuAnioAuxDTO, Pageable paginador) throws Exception {	
		
		Page<PgimExppasdsuAnioAuxDTO> lPgimExppasdsuAnioAuxDTO = this.pasService.listarReporteExppasdsuAnioPaginado(filtroPgimExppasdsuAnioAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimExppasdsuAnioAuxDTO>>(lPgimExppasdsuAnioAuxDTO, HttpStatus.OK);
	}
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por especialidad y mes usado en reporte correspondiente 
     * 
     * @param filtroPgimExppasespeMesAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasespeMes")
	public ResponseEntity<List<PgimExppasespeMesAuxDTO>> listarReporteExppasespeMes(
	      @RequestBody PgimExppasespeMesAuxDTO filtroPgimExppasespeMesAuxDTO) throws Exception {
	    
	    List<PgimExppasespeMesAuxDTO> lPgimExppasespeMesAuxDTO = this.pasService.listarReporteExppasespeMes(filtroPgimExppasespeMesAuxDTO);
	    
	    return new ResponseEntity<List<PgimExppasespeMesAuxDTO>>(lPgimExppasespeMesAuxDTO, HttpStatus.OK);
	}
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por especialidad y mes usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExppasespeMesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasespeMesPaginado")
    public ResponseEntity<Page<PgimExppasespeMesAuxDTO>> listarReporteExppasespeMesPaginado(
      @RequestBody PgimExppasespeMesAuxDTO filtroPgimExppasespeMesAuxDTO, Pageable paginador) throws Exception {  
    
    	Page<PgimExppasespeMesAuxDTO> lPgimExppasespeMesAuxDTO = this.pasService.listarReporteExppasespeMesPaginado(filtroPgimExppasespeMesAuxDTO, paginador);
    
    	return new ResponseEntity<Page<PgimExppasespeMesAuxDTO>>(lPgimExppasespeMesAuxDTO, HttpStatus.OK);
    }
    
    
    /**
     * Permite obtener la lista preparada de expedientes detallados con PAS por persona asiganda usado en reporte correspondiente 
     * paginado
     * @param filtroPgimExppasPendientesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarExpPersonaAsignadaPaginado")
    public ResponseEntity<Page<PgimExppasPendientesAuxDTO>> listarReporteExpPersonaAsignadaPaginado (@RequestBody PgimExppasPendientesAuxDTO filtroPgimExppasPendientesAuxDTO, Pageable paginador)
            throws Exception {

    	Page<PgimExppasPendientesAuxDTO> lPgimExppasPendientesAuxDTO = this.pasService.listarReporteExpPersonaAsignadaPaginado(filtroPgimExppasPendientesAuxDTO, paginador);
        
        return new ResponseEntity<Page<PgimExppasPendientesAuxDTO>>(lPgimExppasPendientesAuxDTO, HttpStatus.OK);
    }
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por persona asignada, fase y año usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExpPerfaAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarExpPasPerfaseAnioPaginado")
	public ResponseEntity<Page<PgimExpPerfaAuxDTO>> listarReporteExpPasPerfaseAnioPaginado(
			@RequestBody PgimExpPerfaAuxDTO filtroPgimExpPerfaAuxDTO, Pageable paginador) throws Exception {	
		
		Page<PgimExpPerfaAuxDTO> lPgimExpPerfaAuxDTO = this.pasService.listarReporteExpPasPerfaseAnioPaginado(filtroPgimExpPerfaAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimExpPerfaAuxDTO>>(lPgimExpPerfaAuxDTO, HttpStatus.OK);
	}
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por persona asignada, paso y año usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExpPerpaAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarExpPasPerPasoAnioPaginado")
	public ResponseEntity<Page<PgimExpPerpaAuxDTO>> listarReporteExpPasPerPasoAnioPaginado(
			@RequestBody PgimExpPerpaAuxDTO filtroPgimExpPerpaAuxDTO, Pageable paginador) throws Exception {	
		
		Page<PgimExpPerpaAuxDTO> lPgimExpPerpaAuxDTO = this.pasService.listarReporteExpPasPerPasoAnioPaginado(filtroPgimExpPerpaAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimExpPerpaAuxDTO>>(lPgimExpPerpaAuxDTO, HttpStatus.OK);
	}
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por tipo de sustancia de UM y año usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExppastipsustAnioAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasTiposustAnioPaginado")
    public ResponseEntity<Page<PgimExppastipsustAnioAuxDTO>> listarReporteExppasTiposustAnioPaginado(
        @RequestBody PgimExppastipsustAnioAuxDTO filtroPgimExppastipsustAnioAuxDTO, Pageable paginador) throws Exception {  
      
      Page<PgimExppastipsustAnioAuxDTO> lPgimExppastipsustAnioAuxDTO = this.pasService.listarReporteExppastipsustAnioPaginado(filtroPgimExppastipsustAnioAuxDTO, paginador);
      
      return new ResponseEntity<Page<PgimExppastipsustAnioAuxDTO>>(lPgimExppastipsustAnioAuxDTO, HttpStatus.OK);
    }
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por tipo de actividad y año usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExppastipactiAnioAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasTipoactiAnioPaginado")
    public ResponseEntity<Page<PgimExppastipactiAnioAuxDTO>> listarReporteExppasTipoactiAnioPaginado(
        @RequestBody PgimExppastipactiAnioAuxDTO filtroPgimExppastipactiAnioAuxDTO, Pageable paginador) throws Exception {  
      
      Page<PgimExppastipactiAnioAuxDTO> lPgimExppastipactiAnioAuxDTO = this.pasService.listarReporteExppastipactiAnioPaginado(filtroPgimExppastipactiAnioAuxDTO, paginador);
      
      return new ResponseEntity<Page<PgimExppastipactiAnioAuxDTO>>(lPgimExppastipactiAnioAuxDTO, HttpStatus.OK);
    }
        
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por estado de resolución, ds y especialidad usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimExppasEstResolAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExppasEstadoResolPaginado")
    public ResponseEntity<Page<PgimExppasEstResolAuxDTO>> listarReporteExppasEstadoResolPaginado(
        @RequestBody PgimExppasEstResolAuxDTO filtroPgimExppasEstResolAuxDTO, Pageable paginador) throws Exception {  
      
      Page<PgimExppasEstResolAuxDTO> lPgimExppasEstResolAuxDTO = this.pasService.listarReporteExppasEstResolPaginado(filtroPgimExppasEstResolAuxDTO, paginador);
      
      return new ResponseEntity<Page<PgimExppasEstResolAuxDTO>>(lPgimExppasEstResolAuxDTO, HttpStatus.OK);
    }

    
    /**
     * Permite obtener la lista preparada de expedientes con PAS por especialidad y  año usado en reporte correspondiente 
     * 
     * @param filtroPgimExppasEspAnioAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExpPasEspecAnio")
	public ResponseEntity<Page<PgimExppasEspAnioAuxDTO>> listarReporteExpPasEspecAnio(
			@RequestBody PgimExppasEspAnioAuxDTO filtroPgimExppasEspAnioAuxDTO, Pageable paginador) throws Exception {
		
		Page<PgimExppasEspAnioAuxDTO> lPgimExppasEspAnioAuxDTO = this.pasService.listarReporteExpPasEspecAnio(filtroPgimExppasEspAnioAuxDTO, paginador);
		
		return new ResponseEntity<Page<PgimExppasEspAnioAuxDTO>>(lPgimExppasEspAnioAuxDTO, HttpStatus.OK);
	}
    
    
    /**
     * Permite obtener la lista preparada de expedientes con PAS asigando a evaluador por DS y especialidad usado en reporte correspondiente 
     * 
     * @param filtroPgimExppasEspAnioAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExpPasPerDsEspecPaginado")
    public ResponseEntity<Page<PgimExppasEvaluadorAuxDTO>> listarReporteExpPasPerDsEspecPaginado(
    		@RequestBody PgimExppasEvaluadorAuxDTO filtroPgimExppasEvaluadorAuxDTO, Pageable paginador) throws Exception {
    	
    	Page<PgimExppasEvaluadorAuxDTO> pPgimExppasEvaluadorAuxDTO = this.pasService.listarReporteExpPasPerDsEspecPaginado(filtroPgimExppasEvaluadorAuxDTO, paginador);
    	
    	return new ResponseEntity<Page<PgimExppasEvaluadorAuxDTO>>(pPgimExppasEvaluadorAuxDTO, HttpStatus.OK);
    }

    
    /**
     * Permite obtener la lista preparada de expedientes detallados con PAS usado en reporte correspondiente 
     * @param filtroPgimPasAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/ListarReporteExpPasProcesoPaginado")
    public ResponseEntity<Page<PgimPasAuxDTO>> ListarReporteExpPasProcesoPaginado (@RequestBody PgimPasAuxDTO filtroPgimPasAuxDTO, Pageable paginador) throws Exception {
        
        Page<PgimPasAuxDTO> lPgimPasAuxDTO = this.pasService.ListarReporteExpPasProcesoPaginado(filtroPgimPasAuxDTO, paginador);

        return new ResponseEntity<Page<PgimPasAuxDTO>>(lPgimPasAuxDTO, HttpStatus.OK);
    }


    /**
     * Permite obtener la lista preparada de documentos por expediente usado en reporte correspondiente 
     * paginada
     * 
     * @param filtroPgimDocumentoAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteExpDocsPaginado")
    public ResponseEntity<Page<PgimDocumentoAuxDTO>> listarReporteExpDocsPaginado(
        @RequestBody PgimDocumentoAuxDTO filtroPgimDocumentoAuxDTO, Pageable paginador) throws Exception {  
      
      Page<PgimDocumentoAuxDTO> lPgimDocumentoAuxDTO = this.pasService.listarReporteExpDocsPaginado(filtroPgimDocumentoAuxDTO, paginador);
      
      return new ResponseEntity<Page<PgimDocumentoAuxDTO>>(lPgimDocumentoAuxDTO, HttpStatus.OK);
    }
    
     /**
     * Permite obtener el listado de las fiscalizaciones pertenecientes a una unidad minera
     * @param filtroPas
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/obtenerFiscalizacionPasPorUnidadMineraPaginado")
    public ResponseEntity<Page<PgimPasAuxDTO>> obtenerFiscalizacionPasPorUnidadMineraPaginado(
            @RequestBody PgimPasAuxDTO filtroPas, Pageable paginador) throws Exception {

        Page<PgimPasAuxDTO> lPgimPasAuxDTO = this.pasService.obtenerFiscalizacionPasPorUnidadMineraPaginado(filtroPas, paginador);

        return new ResponseEntity<Page<PgimPasAuxDTO>>(lPgimPasAuxDTO, HttpStatus.OK);
    }
}
