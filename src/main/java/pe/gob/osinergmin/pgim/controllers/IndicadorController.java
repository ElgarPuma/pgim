package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.FiltroIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimKpiGeneralDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedicionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicador;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.IndicadorService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.MedicionService;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.SubTipoSupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los indicadores de la PGIM.
 * 
 * @descripción: Indicadores
 *
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 28/02/2023
 */

@RestController
@Slf4j
@RequestMapping("/indicadores")
public class IndicadorController extends BaseController {
	
	@Autowired
    private IndicadorService indicadorService;
	
	@Autowired
    private ParametroService parametroService;
	
	@Autowired
    private MotivoSupervisionService motivoSupervisionService;
	
	@Autowired
    private SubTipoSupervisionService subTipoSupervisionService;
	
    @Autowired
    private InstanciaProcesService instanciaProcesService;
	
    @Autowired
    private MedicionService medicionService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

	@GetMapping("/obtenerConfiguracionesPorTipoIndicador/{parametro}")
    public ResponseEntity<?> obtenerConfiguracionesPorTipoIndicador(@PathVariable String parametro) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimIndicadorDTO> lPgimIndicadorDTO = null;
        PgimValorParametroDTO tipoIndicador = null;

        try {
        	lPgimIndicadorDTO = this.indicadorService.listarIndicadorBycoClavetexto(parametro);
        	
        	List<PgimValorParametroDTO> lstParam = this.parametroService.listarParametrosBycoClaveTexto(parametro);
        	if(lstParam.size()>0) {
        		tipoIndicador  = lstParam.get(0);
        	}
        			
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de indicadores");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los indicadores"); 
        respuesta.put("lPgimIndicadorDTO", lPgimIndicadorDTO);
        respuesta.put("tipoIndicador",tipoIndicador);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
	
	/***
     * Permite obtener las configuraciones necesarias para el dashboard de indicadores
     * 
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguracionesDashboard/{idIndicador}")
    public ResponseEntity<?> obtenerConfiguracionesDashboard(@PathVariable Long idIndicador) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;        
        List<PgimValorParametroDTO> lPgimDivisionSupervisoraDTO = null;
        List<PgimValorParametroDTO> lPgimTipoSupervisionDTO = null;
        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
        List<PgimSubtipoSupervisionDTO> lPgimSubtipoSupervisionDTO = null;
        PgimIndicadorDTO indicadorDTO = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            lPgimDivisionSupervisoraDTO = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);
            
            lPgimTipoSupervisionDTO = this.parametroService
                    .filtrarPorNombreTipoSupervision(ConstantesUtil.PARAM_TIPO_SUPERVISION);
            
            lPgimMotivoSupervisionDTO = this.motivoSupervisionService
            		.obtenerMotivoSupervision();
            
            lPgimSubtipoSupervisionDTO = this.subTipoSupervisionService
            		.obtenerSubTipoSupervision();
            
            indicadorDTO = this.indicadorService.obtenerIndicadorById(idIndicador);


        } catch (PgimException e) {
         
            log.error(e.getMensaje(), e);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimDivisionSupervisoraDTO", lPgimDivisionSupervisoraDTO);
        respuesta.put("lPgimTipoSupervisionDTO", lPgimTipoSupervisionDTO);
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);
        respuesta.put("lPgimSubtipoSupervisionDTO", lPgimSubtipoSupervisionDTO);        
        respuesta.put("indicadorDTO", indicadorDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /*
    @GetMapping("/listarMotivoSupervisionByTipoSupervision/{idTipoSupervison}")
    public ResponseEntity<?> listarMotivoSupervisionByTipoSupervision(@PathVariable Long idTipoSupervison) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
        
        try {
        	lPgimMotivoSupervisionDTO = motivoSupervisionService.listarMotivoSupervisionByTipoSupervision(idTipoSupervison);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los motivos de supervisión");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los motivos de supervisión");
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }*/
    
    
    /***
     * Permite obtener la data para el dashboard de indicadores de Aprobación de Informe.
     * 
     * @param filtroIndicadorDTO 
     * 
     * @return
     */
    @PostMapping("/filtrarIndicadoresAprobacion")
    public ResponseEntity<?> filtrarIndicadoresAprobacion(
            @RequestBody FiltroIndicadorDTO filtroIndicadorDTO) throws Exception {

    	Map<String, Object> respuesta = new HashMap<>();
    	
    	PgimKpiGeneralDTO pgimKpiGeneralDTO = null;
    	List<PgimGraficaResultadoDTO> dataEspecialidad = null;
    	List<PgimGraficaResultadoDTO> dataTipoFiscalizacion = null;
    	List<PgimGraficaResultadoDTO> dataContrato = null;
    	List<PgimGraficaResultadoDTO> dataAgenteFiscalizado = null;
    	List<PgimGraficaResultadoDTO> dataDivisionSupervisora = null;
    	List<PgimGraficaResultadoDTO> dataMotivoFiscalizacion = null;
    	List<PgimGraficaResultadoDTO> dataUnidadFiscalizable = null;
    	List<PgimGraficaResultadoDTO> dataSubTipoFiscalizacion = null;
    	List<PgimGraficaResultadoDTO> dataEmpresaSupervisora = null;
        
        try {
        	
        	if(filtroIndicadorDTO.getDescIdIndicadorTexto().equals(ConstantesUtil.INDTP_01)) {
	        	pgimKpiGeneralDTO = indicadorService.obtenerKpiAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataEspecialidad = indicadorService.obtenerDataPorEspecialidadAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataTipoFiscalizacion = indicadorService.obtenerDataPorTipoFiscalizacionAprobacionInformeFiscalizacion(filtroIndicadorDTO);        	
	        	dataContrato = indicadorService.obtenerDataPorContratoAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataAgenteFiscalizado = indicadorService.obtenerDataPorAgenteFiscalizadoAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataDivisionSupervisora = indicadorService.obtenerDataPorDivisionSupervisoraAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataMotivoFiscalizacion = indicadorService.obtenerDataPorMotivoFiscalizacionAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataUnidadFiscalizable = indicadorService.obtenerDataPorUnidadFiscalizableAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataSubTipoFiscalizacion = indicadorService.obtenerDataPorSubtipoFiscalizacionAprobacionInformeFiscalizacion(filtroIndicadorDTO);
	        	dataEmpresaSupervisora = indicadorService.obtenerDataPorEmpresaSupervisoraAprobacionInformeFiscalizacion(filtroIndicadorDTO);
        	}
        	else if(filtroIndicadorDTO.getDescIdIndicadorTexto().equals(ConstantesUtil.INDTP_02)) {
	        	pgimKpiGeneralDTO = indicadorService.obtenerKpiEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataEspecialidad = indicadorService.obtenerDataPorEspecialidadEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataTipoFiscalizacion = indicadorService.obtenerDataPorTipoFiscalizacionEmisionInformeInstruccion(filtroIndicadorDTO);        	
	        	dataContrato = indicadorService.obtenerDataPorContratoEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataAgenteFiscalizado = indicadorService.obtenerDataPorAgenteFiscalizadoEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataDivisionSupervisora = indicadorService.obtenerDataPorDivisionSupervisoraEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataMotivoFiscalizacion = indicadorService.obtenerDataPorMotivoFiscalizacionEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataUnidadFiscalizable = indicadorService.obtenerDataPorUnidadFiscalizableEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataSubTipoFiscalizacion = indicadorService.obtenerDataPorSubtipoFiscalizacionEmisionInformeInstruccion(filtroIndicadorDTO);
	        	dataEmpresaSupervisora = indicadorService.obtenerDataPorEmpresaSupervisoraEmisionInformeInstruccion(filtroIndicadorDTO);
        	}
        	
        	
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los datos del dashboard de indicadores de aprobación");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron los datos del dashboard de indicadores de aprobación");
        respuesta.put("pgimKpiGeneralDTO", pgimKpiGeneralDTO);
        respuesta.put("dataEspecialidad", dataEspecialidad);
        respuesta.put("dataTipoFiscalizacion", dataTipoFiscalizacion);
        respuesta.put("dataContrato", dataContrato);
        respuesta.put("dataAgenteFiscalizado", dataAgenteFiscalizado);
        respuesta.put("dataDivisionSupervisora", dataDivisionSupervisora);
        respuesta.put("dataMotivoFiscalizacion", dataMotivoFiscalizacion);
        respuesta.put("dataUnidadFiscalizable", dataUnidadFiscalizable);
        respuesta.put("dataSubTipoFiscalizacion", dataSubTipoFiscalizacion);
        respuesta.put("dataEmpresaSupervisora", dataEmpresaSupervisora);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /*---------------------------------------------------------
    	metodos para indicadores de flujos de trabajo
	--------------------------------------------------------- */

    /**
     * Permite obtener el listado de indicadores de flujos de trabajo
     * @param pgimIndicadorDTOFiltro
     * @param paginador
     * @return
     */
    @PreAuthorize("hasAnyAuthority('cfg009_AC')")
    @PostMapping("/listarIndicadoresFt")
    public ResponseEntity<Page<PgimIndicadorDTO>> listarIndicadoresFt(@RequestBody PgimIndicadorDTO pgimIndicadorDTOFiltro,
            final Pageable paginador) {

        final Page<PgimIndicadorDTO> pPgimIndicadorDTO = this.indicadorService.listarIndicadoresFt(pgimIndicadorDTOFiltro,
                paginador);
        return new ResponseEntity<Page<PgimIndicadorDTO>>(pPgimIndicadorDTO, HttpStatus.OK);
    	        
    }

    /**
     * Permite obtener las configuraciones necesarias para la creación de indicadores de flujos de trabajo
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerConfiguracionesIndicadoresFt")
    public ResponseEntity<ResponseDTO> obtenerConfiguracionesIndicadoresFt() throws Exception{

        Map<String, Object> respuesta = new HashMap<>();

        ResponseDTO responseDTO = new ResponseDTO();
        String mensajeExcepcionGeneral = "Ocurrió un problema para obtener las configuraciones de indicadores: ";
        
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoCaracterFisc = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoActorNegocio = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoAgrupadoPor = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoUnidadMedida = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoIndicado = null;
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = null;
        List<PgimProcesoDTO> lPgimProcesoDTO = null;
        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTO = null;
        
        try {
        	
        	lPgimValorParamDTOTipoCaracterFisc = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_CARACTER_FISC);
        	lPgimValorParamDTOTipoActorNegocio = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ACTORN_INDICADOR);
        	lPgimValorParamDTOTipoAgrupadoPor = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_AGRUPACION_INDICADOR);
        	lPgimValorParamDTOTipoUnidadMedida = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_UNIDAD_MEDIDA);
        	lPgimValorParamDTOTipoIndicado = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_INDICADOR);      	
        	// lPgimPasoProcesoDTO = this.instanciaProcesService.obtenerPasosPorFase();
        	lPgimProcesoDTO = this.instanciaProcesService.listarProcesosForIndicar();
        	lPgimRelacionPasoDTO = this.flujoTrabajoService.obtenerTodosRelacionPaso();
        	
        	//Excluir el tipo de indicador "Tiempos de procemiento" 
        	lPgimValorParamDTOTipoIndicado = lPgimValorParamDTOTipoIndicado.stream().filter(pgimValorParamDTOTipoIndicado -> {
    			return (!pgimValorParamDTOTipoIndicado.getCoClaveTexto().equals(EValorParametro.TIND_TIEMPOS_PROCESAMIENTO.toString()));
    		}).collect(Collectors.toList());
        	

        } catch (DataAccessException e) {
            mensajeExcepcionGeneral += e.getMostSpecificCause().getMessage();
			log.error(mensajeExcepcionGeneral); 
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral, 0));

        } catch (PgimException e) {
			
			TipoResultado tipoResultado = (e.getTipoResultado() == null) ? TipoResultado.WARNING : e.getTipoResultado();
			
			mensajeExcepcionGeneral += e.getMensaje();
			log.error(mensajeExcepcionGeneral);
			log.error(e.getMensaje(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, mensajeExcepcionGeneral));

		} catch (Exception e) {
			mensajeExcepcionGeneral += e.getMessage();
			log.error(mensajeExcepcionGeneral);
			log.error(e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral));
		}

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOTipoCaracterFisc", lPgimValorParamDTOTipoCaracterFisc);
        respuesta.put("lPgimValorParamDTOTipoActorNegocio", lPgimValorParamDTOTipoActorNegocio);
        respuesta.put("lPgimValorParamDTOTipoAgrupadoPor", lPgimValorParamDTOTipoAgrupadoPor);
        respuesta.put("lPgimValorParamDTOTipoUnidadMedida", lPgimValorParamDTOTipoUnidadMedida);
        respuesta.put("lPgimValorParamDTOTipoIndicado", lPgimValorParamDTOTipoIndicado);
        respuesta.put("lPgimPasoProcesoDTO", lPgimPasoProcesoDTO);
        respuesta.put("lPgimProcesoDTO", lPgimProcesoDTO);
        respuesta.put("lPgimRelacionPasoDTO", lPgimRelacionPasoDTO);
        
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Se encontraron las configuraciones.");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    
    /**
     * Permite validad sí el coClove ya existe registrado
     * @param pgimIndicadorDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/validarClaveUnica")
	public ResponseEntity<ResponseDTO> validarClaveUnica(@RequestBody PgimIndicadorDTO pgimIndicadorDTO) throws Exception {

    	List<PgimIndicadorDTO> lPgimIndicadorDTOCU = null;
		
		ResponseDTO responseDTO = null;
		String mensaje = "";


		try {
			lPgimIndicadorDTOCU = this.indicadorService.validarCoClaveUnicaIndicadorFt(pgimIndicadorDTO);
			
			
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al intentar buscar el indicador de flujos de trabajo: " + e.getMessage();
			log.error(mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
		 } catch (final PgimException e) {	            
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		} catch (Exception e) {
			mensaje = "Ocurrió un error al intentar buscar el indicador de flujos de trabajo: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		mensaje = "Indicadores de flujos de trabajo han sido encontrados";
				
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimIndicadorDTOCU, mensaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}

    /**
     * Permite crear un indicador del flujo de trabajo 
     * 
     * @param pgimInfraccionDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PostMapping("/crearIndicadoresFt")
	public ResponseEntity<ResponseDTO> crearIndicadoresFt(@Valid @RequestBody PgimIndicadorDTO pgimIndicadorDTO,
			BindingResult resultadoValidacion) throws Exception {

    	PgimIndicadorDTO pgimIndicadorDTOCU = null;
		
		ResponseDTO responseDTO = null;
		String mensaje = "";

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			mensaje = "Se han encontrado inconsistencias para crear el indicador de flujos de trabajo";
			log.error(mensaje);
			log.error(errores.toString());
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, errores.toString());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimIndicadorDTOCU = this.indicadorService.crearIndicadoresFt(pgimIndicadorDTO, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al intentar crear el indicador de flujos de trabajo: " + e.getMessage();
			log.error(mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
		 } catch (final PgimException e) {	            
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		} catch (Exception e) {
			mensaje = "Ocurrió un error al intentar crear el indicador de flujos de trabajo: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		mensaje = "Indicador de flujos de trabajo ha sido creada";
				
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimIndicadorDTOCU, mensaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}
    
    /**
     * Permite hacer modificaciones en el indicador de flujos de trabajo
     * @param pgimIndicadorDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */    
	@PutMapping("/modificarIndicadoresFt")
	public ResponseEntity<ResponseDTO> modificarIndicadoresFt(@Valid @RequestBody PgimIndicadorDTO pgimIndicadorDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimIndicador pgimIndicadorActual = null;
		PgimIndicadorDTO pgimIndicadorDTOOModificado = null;
		ResponseDTO responseDTO = null;
		String mensajeExcepcion;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			mensajeExcepcion = "Se han encontrado inconsistencias para modificar el indicador de flujo de trabajo: " + errores.toString();

			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimIndicadorActual = this.indicadorService.getByIdIndicadorFt(pgimIndicadorDTO.getIdIndicador());

			if (pgimIndicadorActual == null) {
				mensajeExcepcion = String.format("El indicador de flujo de trabajo %s que intenta actualizar no existe en la base de datos", pgimIndicadorDTO.getIdIndicador());

				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Ocurrió un error al intentar recuperar al indicador de flujo de trabajo a actualizar: " + e.getMostSpecificCause().getMessage();
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar eliminar el indicador de flujos de trabajo: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimIndicadorDTOOModificado = this.indicadorService.modificarIndicadoresFt(pgimIndicadorDTO, pgimIndicadorActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			mensajeExcepcion = "Se han encontrado inconsistencias para modificar el indicador de flujo de trabajo: " + e.getMostSpecificCause().getMessage();
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la persona accidentada");
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar eliminar el indicador de flujos de trabajo: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimIndicadorDTOOModificado, "El indicador de flujo de trabajo ha sido modificada");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

    /**
     * Permite eliminar logicamente un indicador de flujos de trabajo
     * @param indicador
     * @return
     * @throws Exception
     */
    @DeleteMapping("/eliminarIndicadorFt/{indicador}")
    public ResponseEntity<ResponseDTO> eliminarIndicadorFt(
    		@PathVariable Long indicador) throws Exception {

        ResponseDTO responseDTO = null;        
        PgimIndicador pgimIndicadorActual = null;
        String mensaje;
		
        try {
        	pgimIndicadorActual = this.indicadorService.getByIdIndicadorFt(indicador);
            
            if (pgimIndicadorActual == null) {
                mensaje = String.format("El indicador de flujo de trabajo con id %s que intenta eliminar no existe en la base de datos",
                		indicador);

                responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

		}  catch (DataAccessException e) {
            mensaje = "Ocurrió un error intentar eliminar el indicador de flujo de trabajo: " + e.getMessage();
            log.error(e.getMostSpecificCause().getMessage(), e);
            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar eliminar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

		try {
			this.indicadorService.eliminarIndicadorFt(pgimIndicadorActual, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al intentar eliminar el indicador de flujos de trabajo: " + e.getMessage();
			log.error(mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		} catch (Exception e) {
			mensaje = "Ocurrió un error al intentar eliminar el indicador de flujos de trabajo: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		
		
		mensaje = "El indicador de flujos de trabajo ha sido eliminado";          
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

    /**
     * Permite obtener el indicador del flujo de trabajo por su identificador interno
     * @param idIndicador
     * @return
     */
    @GetMapping("/obtenerIndicaPorId/{idIndicador}")
    public ResponseEntity<ResponseDTO> obtenerIndicaPorId(@PathVariable final Long idIndicador) {
        
    	PgimIndicadorDTO pgimIndicadorDTO = null;
        ResponseDTO responseDTO = null;

        try {
        	pgimIndicadorDTO = this.indicadorService.obtenerIndicadorPorId(idIndicador);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimIndicadorDTO, "El indicador de flujos de trabajo ha sido recuperada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Permite obtener el indicador del flujo de trabajo por filtro de nombre o codigo
     * @param textoBuscador
     * @return
     */
    @GetMapping("/listarIndicadoresByPalabra/{textoBuscador}")
    public ResponseEntity<ResponseDTO> listarIndicadoresByPalabra( @PathVariable String textoBuscador) {
        
    	List<PgimIndicadorDTO> pgimIndicadorDTO = null;
        ResponseDTO responseDTO = null;

        try {
        	pgimIndicadorDTO = this.indicadorService.listarIndicadoresByPalabra(textoBuscador);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimIndicadorDTO, "Indicadores encontrados");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Permite obtener el indicador del flujo de trabajo por su identificador interno
     * @param idIndicador
     * @return
     */
    @GetMapping("/verificarEliminacionIndicadorFt/{idIndicador}")
    public ResponseEntity<ResponseDTO> verificarEliminacionIndicadorFt(@PathVariable final Long idIndicador) {
        
    	List<PgimMedicionDTO> lPgimMedicionDTO = null;
        ResponseDTO responseDTO = null;

        try {
        	lPgimMedicionDTO = this.medicionService.obtenerMedicionesPorIdIndicador(idIndicador);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar validar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar validar el indicador de flujos de trabajo");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimMedicionDTO, "El indicador de flujos de trabajo ha sido validado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
