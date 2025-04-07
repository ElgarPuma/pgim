package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;
import pe.gob.osinergmin.pgim.models.entity.PgimReglaBase;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.SubTipoSupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;

/**
 * Controlador para la configuración base
 *
 * @descripción: Configuración de base
 *
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 04/04/2022
 */
@RestController
@Slf4j
@RequestMapping("/configuracion-base")
public class ConfiguracionBaseController extends BaseController {

    @Autowired
    private ConfiguracionBaseService configuracionBaseService;
    
    @Autowired
    private ParametroService parametroService;
    
    @Autowired
    private SubTipoSupervisionService subTipoSupervisionService;
    
    @Autowired
    private MotivoSupervisionService motivoSupervisionService;
    
    /**
     * permite obtener un listado de forma pagina de las configuraciones base
     * 
     * @param filtro
     * @param paginador
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('cfg001_AC')")
    @PostMapping("/listar")
    public ResponseEntity<Page<PgimConfiguracionBaseDTO>> filtrar(
            @RequestBody PgimConfiguracionBaseDTO filtro,
            Pageable paginador) throws Exception {

        Page<PgimConfiguracionBaseDTO> pPgimConfiguracionBaseDTO = this.configuracionBaseService.listar(filtro, paginador);

        return new ResponseEntity<Page<PgimConfiguracionBaseDTO>>(pPgimConfiguracionBaseDTO, HttpStatus.OK);
    }
    
    /**
     * permite obtener una configuración base por su identificador
     * @param idConfiguracionBase
     * @return
     */
    @GetMapping("/obtenerCfgBasePorId/{idConfiguracionBase}")
    public ResponseEntity<?> obtenerCfgBasePorId(@PathVariable Long idConfiguracionBase) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO = null;

        try {
        	pgimConfiguracionBaseDTO = this.configuracionBaseService.obtenerCfgBasePorId(idConfiguracionBase);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimConfiguracionBaseDTO == null) {
            mensaje = String.format("La configuraciópn base con el id: %d no existe en la base de datos", idConfiguracionBase);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La configuración base ha sido recuperada");
        respuesta.put("pgimConfiguracionBaseDTO", pgimConfiguracionBaseDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    
    /**
     * permite obtener un listado de configuraciones base según el tipo de configuración
     * @param idTipoConfiguracionBase
     * @return
     */
	@GetMapping("/listaCfgBasePorIdTipoCfgBase/{idTipoConfiguracionBase}")
	public ResponseEntity<List<PgimConfiguracionBaseDTO>> listaCfgBasePorIdTipoCfgBase(@PathVariable Long idTipoConfiguracionBase) throws Exception {
		
        List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseService.listaCfgBasePorIdTipoCfgBase(idTipoConfiguracionBase);
        
        return new ResponseEntity<List<PgimConfiguracionBaseDTO>>(lPgimConfiguracionBaseDTO, HttpStatus.OK);
	}
	
	
    /**
     * Permite la creación una configuración base
     * @param pgimConfiguracionBaseDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PostMapping("/crearCfgBase")
    public ResponseEntity<?> crearCfgBase(@Valid @RequestBody PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO, BindingResult resultadoValidacion)
            throws Exception {

    	PgimConfiguracionBaseDTO pgimConfiguracionBaseDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if(pgimConfiguracionBaseDTO.getIdConfiguracionBase() == 0)
            pgimConfiguracionBaseDTO.setIdConfiguracionBase(null);

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para crear la configuración base");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimConfiguracionBaseDTOCreado = this.configuracionBaseService.crearCfgBase(pgimConfiguracionBaseDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la configuración base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración base ha sido creado");
        respuesta.put("pgimConfiguracionBaseDTO", pgimConfiguracionBaseDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite la modificación una configuración base
     * @param pgimConfiguracionBaseDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarCfgBase")
    public ResponseEntity<?> modificarCfgBase(
            @Valid @RequestBody PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO, BindingResult resultadoValidacion)
            throws Exception {

    	PgimConfiguracionBaseDTO pgimConfiguracionBaseDTOModificado = null;
    	PgimConfiguracionBase pgimConfiguracionBaseActual = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para modificar la configuración base");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimConfiguracionBaseActual = this.configuracionBaseService.obtenerCfgBaseById(pgimConfiguracionBaseDTO.getIdConfiguracionBase());

            if (pgimConfiguracionBaseActual == null) {
                mensaje = String.format("La configuración base %s que intenta actualizar no existe en la base de datos",
                		pgimConfiguracionBaseDTO.getIdConfiguracionBase());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la configuración base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
        	pgimConfiguracionBaseDTOModificado = this.configuracionBaseService.modificarCfgBase(pgimConfiguracionBaseDTO,
        			pgimConfiguracionBaseActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la configuración base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La configuración base ha sido modificado");
        respuesta.put("pgimConfiguracionBaseDTO", pgimConfiguracionBaseDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite la eliminación una configuración base
     * @param idConfiguracionBase
     * @return
     * @throws Exception
     */
    @DeleteMapping("/eliminarCfgBase/{idConfiguracionBase}")
    public ResponseEntity<ResponseDTO> eliminarCfgBase(@PathVariable Long idConfiguracionBase) throws Exception {
        ResponseDTO responseDTO = null;
        String mensaje;

        PgimConfiguracionBase pgimConfiguracionBaseActual = null;

        try {
        	pgimConfiguracionBaseActual = this.configuracionBaseService.obtenerCfgBaseById(idConfiguracionBase);

            if (pgimConfiguracionBaseActual == null) {
                mensaje = String.format("La configuración base %s que intenta eliminar no existe en la base de datos",
                        idConfiguracionBase);
                responseDTO = new ResponseDTO("mensaje", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar la configuración base a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
        	this.configuracionBaseService.eliminarCfgBase(pgimConfiguracionBaseActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar eliminar la configuración base",
                    e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "La configuración base fue eliminada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
   
    @GetMapping("/obtenerConfiguracionesGenerales")
    public ResponseEntity<?> obtenerConfiguracionesGenerales() {

    	Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoCfg = null;
        
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoSupervision = null;
        List<PgimSubtipoSupervisionDTO> lPgimSubtipoSupervisionDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTODivSuper = null;
        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOMetodoMinado = null;
        List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = null;
        

        try {
        	lPgimValorParametroDTOTipoCfg = this.parametroService
        			.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_CONFIGURACION_BASE);
        	
            lPgimValorParametroDTOTipoSupervision = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SUPERVISION);
            lPgimValorParamDTODivSuper = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);
            lPgimSubtipoSupervisionDTO = this.subTipoSupervisionService.obtenerSubTipoSupervision();
            lPgimEspecialidadDTO = this.parametroService.filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
            lPgimValorParamDTOMetodoMinado = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_METODO_MINADO);
            lPgimMotivoSupervisionDTO = this.motivoSupervisionService.obtenerMotivoSupervision();
            
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la configuración base");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTOTipoCfg", lPgimValorParametroDTOTipoCfg);

        respuesta.put("lPgimTipoSupervisionDTO", lPgimValorParametroDTOTipoSupervision);
        respuesta.put("lPgimSubtipoSupervisionDTO", lPgimSubtipoSupervisionDTO);
        respuesta.put("lPgimValorParamDTODivSuper", lPgimValorParamDTODivSuper);
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
        respuesta.put("lPgimValorParamDTOMetodoMinado", lPgimValorParamDTOMetodoMinado);
        respuesta.put("lPgimMotivoSupervisionDTO", lPgimMotivoSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        
    }
    
    /**
     * Permite obtener un listado de reglas de una determinada configuración base
     * @param idConfiguracionBase
     * @return
     * @throws Exception
     */
	@GetMapping("/listarReglas/{idConfiguracionBase}")
	public ResponseEntity<List<PgimReglaBaseDTO>> listarReglas(@PathVariable Long idConfiguracionBase) throws Exception {
			
        List<PgimReglaBaseDTO> lPgimReglaBaseDTO = this.configuracionBaseService.listarReglasPorCfgBase(idConfiguracionBase);
        
        return new ResponseEntity<List<PgimReglaBaseDTO>>(lPgimReglaBaseDTO, HttpStatus.OK);
	}
	
	/**
	 * Permite la creación una regla 
	 * @param pgimReglaBaseDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/crearReglaCfgBase")
    public ResponseEntity<?> crearReglaCfgBase(@Valid @RequestBody PgimReglaBaseDTO pgimReglaBaseDTO, BindingResult resultadoValidacion)
            throws Exception {

    	PgimReglaBaseDTO pgimReglaBaseDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para crear la regla");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimReglaBaseDTOCreado = this.configuracionBaseService.crearReglaCfgBase(pgimReglaBaseDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la regla");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La regla ha sido creado");
        respuesta.put("pgimReglaBaseDTOCreado", pgimReglaBaseDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite modificación una regla 
     * @param pgimReglaBaseDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarReglaCfgBase")
    public ResponseEntity<?> modificarReglaCfgBase(
            @Valid @RequestBody PgimReglaBaseDTO pgimReglaBaseDTO, BindingResult resultadoValidacion)
            throws Exception {

    	PgimReglaBaseDTO pgimReglaBaseDTOModificado = null;
    	PgimReglaBase pgimReglaBaseActual = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje",
                    "Se han encontrado inconsistencias para modificar la regla");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	pgimReglaBaseActual = this.configuracionBaseService.obtenerReglaCfgBaseById(pgimReglaBaseDTO.getIdReglaBase());

            if (pgimReglaBaseActual == null) {
                mensaje = String.format("La regla %s que intenta actualizar no existe en la base de datos",
                		pgimReglaBaseDTO.getIdReglaBase());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la regla");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
        	pgimReglaBaseDTOModificado = this.configuracionBaseService.modificarReglaCfgBase(pgimReglaBaseDTO,
        			pgimReglaBaseActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la regla");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La regla base ha sido modificado");
        respuesta.put("pgimReglaBaseDTOModificado", pgimReglaBaseDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite eliminación una regla 
     * @param idReglaBase
     * @return
     * @throws Exception
     */
    @DeleteMapping("/eliminarReglaCfgBase/{idReglaBase}")
    public ResponseEntity<ResponseDTO> eliminarReglaCfgBase(@PathVariable Long idReglaBase) throws Exception {
        ResponseDTO responseDTO = null;
        String mensaje;

        PgimReglaBase pgimReglaBaseActual = null;

        try {
        	pgimReglaBaseActual = this.configuracionBaseService.obtenerReglaCfgBaseById(idReglaBase);

            if (pgimReglaBaseActual == null) {
                mensaje = String.format("La regla %s que intenta eliminar no existe en la base de datos",
                		idReglaBase);
                responseDTO = new ResponseDTO("mensaje", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar la regla a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
        	this.configuracionBaseService.eliminarReglaCfgBase(pgimReglaBaseActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar eliminar la regla",
                    e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "La regla base fue eliminada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    
    /**
     * Permite validar que las reglas de la configuración no se traslapen entre sí.
     * 
     * @param pgimConfiguraReglaDTO
     * @return
     */
    @PostMapping("/validarTraslapeRegla")
    public ResponseEntity<ResponseDTO> validarTraslapeRegla(@RequestBody PgimReglaBaseDTO pgimReglaBaseDTO) {

        if (pgimReglaBaseDTO.getIdReglaBase() == 0) {
        	pgimReglaBaseDTO.setIdReglaBase(null);
        }

        String mensaje = "";
        String mensajeDetalle = "";
        int nroTraslapes = 0;

        List<PgimReglaBaseDTO> lPgimReglaBaseDTO = null;

        try {

            lPgimReglaBaseDTO = this.configuracionBaseService.listarReglasTraslapadas(pgimReglaBaseDTO);

            nroTraslapes = lPgimReglaBaseDTO.size();

            if (nroTraslapes > 0) {
                for (PgimReglaBaseDTO pgimReglaBaseDTOAux : lPgimReglaBaseDTO) {
                    if (mensajeDetalle.equals("")) {
                        mensajeDetalle = String.format("'%s'", "CFGB-" + pgimReglaBaseDTOAux.getIdConfiguracionBase() + ". " + pgimReglaBaseDTOAux.getDescNoConfiguracionBase());
                    } else {
                        mensajeDetalle = mensajeDetalle + ", "
                                + String.format("'%s'", "CFGB-" + pgimReglaBaseDTOAux.getIdConfiguracionBase() + ". " + pgimReglaBaseDTOAux.getDescNoConfiguracionBase());
                    }
                }
                mensaje = "Ya existe una regla con esta combinación, por favor elija una combinación distinta. La regla en conflicto se encuentra en la Configuración: " + mensajeDetalle + ". Recuerde que la combinación no debe existir tampoco en otras configuraciones.";
            } else {
                mensaje = "No se han encontrado traslapes";
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de la regla";
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, nroTraslapes));
        } catch (Exception e) {
            mensaje = "Ocurrió un error al realizar la validacion de las reglas";
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("error", mensaje, nroTraslapes));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success", mensaje, nroTraslapes));
       

    }
    
    @GetMapping("/existeCfg/{noConfiguracionBase}/{idConfiguracionBase}")
    public ResponseEntity<?> existeCfg(@PathVariable String noConfiguracionBase, @PathVariable Long idConfiguracionBase) {

        if (idConfiguracionBase == 0) {
        	idConfiguracionBase = null;
        }

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = null;

        try {
        	
        	lPgimConfiguracionBaseDTO = this.configuracionBaseService.existeCfg(noConfiguracionBase, idConfiguracionBase);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite una configuración");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de configuración");
        respuesta.put("lPgimConfiguracionBaseDTO", lPgimConfiguracionBaseDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    

    /**
     * Permite validar que las reglas de la configuración base de tipo de cuadro de verifiación no se traslapen entre sí, 
     * cuando estas estan visnculado con un cuadro de verificación activo.
     * Al momento de poner como activo a un cuadro de verificación
     * 
     * @param pgimMatrizSupervisionDTO
     * @return
     */
    @PostMapping("/validarTraslapesReglasMatrizSupervision")
    public ResponseEntity<ResponseDTO> validarTraslapesReglasMatrizSupervision(@RequestBody PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO) {

        ResponseDTO responseDTO = null;
        String mensaje = "";
        String mensajeDetalle = "";
        int nroTraslapes = 0;

        List<PgimReglaBaseDTO> lPgimReglaBaseDTO = null;

        try {

            lPgimReglaBaseDTO = this.configuracionBaseService.validarTraslapesReglasMatrizSupervision(pgimMatrizSupervisionDTO);
            
            List<PgimReglaBaseDTO> listarReglasTraslapadasResultado = new ArrayList<PgimReglaBaseDTO>();
            Set<Long> uniqueValues = new HashSet<>();
            for (PgimReglaBaseDTO pgimReglaBaseDTO : lPgimReglaBaseDTO) {
                if (uniqueValues.add(pgimReglaBaseDTO.getIdConfiguracionBase())) {
                    listarReglasTraslapadasResultado.add(pgimReglaBaseDTO);
                }
            }

            nroTraslapes = lPgimReglaBaseDTO.size();

            if (nroTraslapes > 0) {
                for (PgimReglaBaseDTO pgimReglaBaseDTO : listarReglasTraslapadasResultado) {
                    if (mensajeDetalle.equals("")) {
                        mensajeDetalle = String.format("'%s'", "CFGB-" + pgimReglaBaseDTO.getIdConfiguracionBase() + ". " + pgimReglaBaseDTO.getDescNoConfiguracionBase());
                    } else {
                        mensajeDetalle = mensajeDetalle + ", "
                                + String.format("'%s'", "CFGB-" + pgimReglaBaseDTO.getIdConfiguracionBase() + ". " + pgimReglaBaseDTO.getDescNoConfiguracionBase());
                    }
                }
                
                mensaje = "Existe "+nroTraslapes+" reglas que se traslapan, estas se encuentran en las configuraciones siguientes: " + mensajeDetalle + 
                    ". Por favor modifique, con la finalidad de evitar traslapes, las reglas en las configuraciones mencionadas o en la configuración de este cuadro de verificación; " +
                    "por otro lado, si lo que desea es reemplazar un cuadro de verificación por el actual, entonces desactive el primero para que el segundo (actual) sea el que use en adelante.";
            } else {
                mensaje = "No se han encontrado traslapes";
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de la regla";
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje, nroTraslapes);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            mensaje = "Ocurrió un error al realizar la validacion de las reglas";
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje, nroTraslapes);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje, nroTraslapes);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
       

    }

    /**
     * Permite validar que las reglas de la configuración no se traslapen entre sí.
     * 
     * @param pgimConfiguraReglaDTO
     * @return
     */
    @PostMapping("/validarEliminacionCfgBase")
    public ResponseEntity<ResponseDTO> validarEliminacionCfgBase(@RequestBody PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO) {

        ResponseDTO responseDTO = null;
        String mensaje = "";
        int nroTraslapes = 0;

        List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = null;

        try {

            lPgimConfiguracionBaseDTO = this.configuracionBaseService.validarEliminacionCfgBase(pgimConfiguracionBaseDTO);
            
            nroTraslapes = lPgimConfiguracionBaseDTO.size();

            if (nroTraslapes > 0) {
                mensaje = lPgimConfiguracionBaseDTO.get(0).getDescNoObjetoHijo();
            } else {
                mensaje = "No se han encontrado traslapes";
            }

        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar la validacion de eliminación de configuración base.";
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje, nroTraslapes);
            responseDTO.setData(e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            mensaje = "Ocurrió un error al realizar la validacion de eliminación de configuración base.";
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje, nroTraslapes);
            responseDTO.setData(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje, nroTraslapes);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

}
