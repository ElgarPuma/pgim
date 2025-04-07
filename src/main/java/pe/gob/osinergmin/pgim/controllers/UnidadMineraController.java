package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
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
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCopiaDetalleUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMtdoExplotacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubactividadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSustanciaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSustanciaUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository;
import pe.gob.osinergmin.pgim.models.repository.EventoRepository;
import pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.UnidadMineraService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * unidades mineras
 * 
 * @descripción: Unidad minera
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 */
@RestController
@Slf4j
@RequestMapping("/unidadesmineras")
public class UnidadMineraController extends BaseController {

    @Autowired
    private UnidadMineraService unidadMineraService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PgimComponenteMinRepository pgimComponenteMinRepository;

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    @Autowired
    private SupervisionRepository supervisionRepository;

    /***
     * Permite obtener las configuraciones necesarias para el listado de unidades
     * mineras. Acá se incluyen configuraciones como: Situaciones mineras, tipos de
     * unidad minera y división.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOSituacion = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoUM = null;
        List<PgimValorParametroDTO> lPgimValorParamDTODivSuper = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOMetodoMinado = null;

        try {
            lPgimValorParamDTOSituacion = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_SITUACION_UNIDAD_MINERA);
            lPgimValorParamDTOTipoUM = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_UNIDAD_MINERA);
            lPgimValorParamDTODivSuper = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);
            lPgimValorParamDTOMetodoMinado = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_METODO_MINADO);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOSituacion", lPgimValorParamDTOSituacion);
        respuesta.put("lPgimValorParamDTOTipoUM", lPgimValorParamDTOTipoUM);
        respuesta.put("lPgimValorParamDTODivSuper", lPgimValorParamDTODivSuper);
        respuesta.put("lPgimValorParamDTOMetodoMinado", lPgimValorParamDTOMetodoMinado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /***
     * Permite obtener un listado de unidades mineras en el contexto de la
     * paginación de resultados requerida en la lista den el frontend.
     * 
     * @param filtroUnidadMinera Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */
    // @Secured ({"ROLE_ADMIN", "ADMINISTRADOR"})
    // @PreAuthorize("hasRole('ADMINISTRADOR') AND hasRole('USER')")
    @PreAuthorize("hasAnyAuthority('um-lista_AC')")
    @PostMapping("/filtrar")
    public ResponseEntity<Page<PgimUnidadMineraDTO>> filtrarUnidadesMineras(
            @RequestBody PgimUnidadMineraDTO filtroUnidadMinera, Pageable paginador) {

        Page<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = this.unidadMineraService.filtrar(filtroUnidadMinera,
                paginador);
        return new ResponseEntity<Page<PgimUnidadMineraDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
    }

    /***
     * Permite obtener un listado de unidades mineras en el contexto de la
     * paginación de resultados requerida en la lista den el frontend.
     * 
     * @param filtroUnidadMinera Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @idAgenteSupervisado Objeto idAgenteSupervisado para la lista de unidades
     *                      mineras de un agente supervisado.
     * 
     * @return
     */
    @PreAuthorize("hasAnyAuthority('as-umlista_AC')")
    @PostMapping("/filtrarUnidadesMinerasAS/{idAgenteSupervisado}")
    public ResponseEntity<Page<PgimUnidadMineraDTO>> filtrarUnidadesMinerasAS(@PathVariable Long idAgenteSupervisado,
            @RequestBody PgimUnidadMineraDTO filtroUnidadMinera, Pageable paginador) {
        Page<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = this.unidadMineraService
                .filtrarUnidadMineraAS(idAgenteSupervisado, filtroUnidadMinera, paginador);
        return new ResponseEntity<Page<PgimUnidadMineraDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
    }

    @GetMapping("/filtrar/palabraClave/{palabra}")
    public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimUnidadMineraDTO = new ArrayList<PgimUnidadMineraDTO>();
            respuesta.put("mensaje", "No se encontraron unidades fiscalizables");
            respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimUnidadMineraDTO = this.unidadMineraService.listarPorPalabraClave(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las unidades fiscalizables");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las unidades fiscalizables");
        respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /**
     * Permite obtener la lista de unidades mineras de acuerdo con la palabra y division supervisora.
     * Esta palabra será buscada en su código o nombre.
     * @param palabra
     * @param idAgenteSupervisado
     * @return
     */
    @GetMapping("/filtrar/palabraClaveYAs/{palabra}/{idAgenteSupervisado}")
    public ResponseEntity<?> listarPorPalabraClaveYAs(@PathVariable String palabra,
            @PathVariable Long idAgenteSupervisado) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimUnidadMineraDTO = new ArrayList<PgimUnidadMineraDTO>();
            respuesta.put("mensaje", "No se encontraron unidades fiscalizables");
            respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (palabra.equalsIgnoreCase("todos")) {
            palabra = null;
        }

        try {
            lPgimUnidadMineraDTO = this.unidadMineraService.listarPorPalabraClaveYAs(palabra, idAgenteSupervisado);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las unidades fiscalizables");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las unidades fiscalizables");
        respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrar/palabraClave/{palabra}/{idDivisionSupervisora}")
    public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra,
            @PathVariable Long idDivisionSupervisora) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimUnidadMineraDTO = new ArrayList<PgimUnidadMineraDTO>();
            respuesta.put("mensaje", "No se encontraron unidades fiscalizables");
            respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (palabra.equals("todos")) {
            palabra = null;
        }

        try {
            lPgimUnidadMineraDTO = this.unidadMineraService.listarPorPalabraClaveYDs(palabra, idDivisionSupervisora);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las unidades fiscalizables");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las unidades fiscalizables");
        respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrarPlntas/palabraClave/{palabra}")
    public ResponseEntity<?> listarPlntasPorPalabraClave(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimUnidadMineraDTO = new ArrayList<PgimUnidadMineraDTO>();
            respuesta.put("mensaje", "No se encontraron plantas de beneficio");
            respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimUnidadMineraDTO = this.unidadMineraService.listarPlntasPorPalabraClave(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de las plantas de beneficio");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las plantas de beneficio");
        respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /***
     * Permite obtener las configuraciones necesarias para el formulario de
     * creación/edición de las unidades mineras. Acá se incluyen configuraciones
     * como: Situaciones mineras, tipos de unidad minera y división.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones/generales/{idUnidadMinera}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idUnidadMinera) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTODivSuper = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOSituacion = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOActividad = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoUM = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOMtdoMinado = null;
        List<PgimMtdoExplotacionDTO> lPgimValorParamDTOMtdoExplotacion = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoYacimiento = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoSustancia = null;
        List<PgimSustanciaDTO> lPgimSustanciaDTO = null;

        List<PgimSustanciaUmineraDTO> lPgimSustanciaUmineraDTO = null;

        List<PgimSubactividadDTO> lPgimSubActividadDTO = null;

        List<PgimValorParametroDTO> lPgimValorParamDTOEstadoUM = null;

        try {
            lPgimValorParamDTODivSuper = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

            lPgimValorParamDTOSituacion = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_SITUACION_UNIDAD_MINERA);

            lPgimValorParamDTOActividad = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ACTIVIDAD);

            lPgimValorParamDTOTipoUM = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_UNIDAD_MINERA);

            lPgimValorParamDTOMtdoMinado = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_METODO_MINADO);

            lPgimValorParamDTOMtdoExplotacion = this.unidadMineraService.listarMetodosExplotacion();

            lPgimValorParamDTOTipoYacimiento = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_YACIMIENTO);

            lPgimValorParamDTOTipoSustancia = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SUSTANCIA);

            lPgimSustanciaDTO = this.unidadMineraService.listarSustancias();

            lPgimSustanciaUmineraDTO = this.unidadMineraService.listarSustanciasUM(idUnidadMinera);

            lPgimSubActividadDTO = this.unidadMineraService.listarSubActividad();

            lPgimValorParamDTOEstadoUM = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_ESTADO_UM);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOSituacion", lPgimValorParamDTOSituacion);
        respuesta.put("lPgimValorParamDTOTipoUM", lPgimValorParamDTOTipoUM);
        respuesta.put("lPgimValorParamDTODivSuper", lPgimValorParamDTODivSuper);
        respuesta.put("lPgimValorParamDTOActividad", lPgimValorParamDTOActividad);
        respuesta.put("lPgimValorParamDTOMtdoMinado", lPgimValorParamDTOMtdoMinado);
        respuesta.put("lPgimValorParamDTOMtdoExplotacion", lPgimValorParamDTOMtdoExplotacion);
        respuesta.put("lPgimValorParamDTOTipoYacimiento", lPgimValorParamDTOTipoYacimiento);
        respuesta.put("lPgimValorParamDTOTipoSustancia", lPgimValorParamDTOTipoSustancia);
        respuesta.put("lPgimSustanciaDTO", lPgimSustanciaDTO);

        respuesta.put("lPgimSustanciaUmineraDTO", lPgimSustanciaUmineraDTO);
        respuesta.put("lPgimSubActividadDTO", lPgimSubActividadDTO);
        respuesta.put("lPgimValorParamDTOEstadoUM", lPgimValorParamDTOEstadoUM);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerSustanciasUM/{idUnidadMinera}")
    public ResponseEntity<?> obtenerSustanciasUM(@PathVariable Long idUnidadMinera) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimSustanciaUmineraDTO> lPgimSustanciaUmineraDTO = null;

        try {
            lPgimSustanciaUmineraDTO = this.unidadMineraService.listarSustanciasUM(idUnidadMinera);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las sustencias de la unidad fiscalizable");
        respuesta.put("lPgimSustanciaUmineraDTO", lPgimSustanciaUmineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @GetMapping("/existeUnidadMinera/{idUnidadMinera}/{coUnidadMinera}")
    public ResponseEntity<?> existeUnidadMinera(@PathVariable Long idUnidadMinera,
            @PathVariable String coUnidadMinera) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = null;

        if (idUnidadMinera == 0) {
            idUnidadMinera = null;
        }

        try {
            lPgimUnidadMineraDTO = this.unidadMineraService.existeUnidadMinera(idUnidadMinera, coUnidadMinera);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite una unidad fiscalizable");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de la unidad fiscalizable");
        respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('um-lista_CO')")
    @GetMapping("/obtenerUnidadMinera/{idUnidadMinera}")
    public ResponseEntity<?> obtenerUnidadMinera(@PathVariable Long idUnidadMinera) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimUnidadMineraDTO pgimUnidadMineraDTO = null;

        try {
            pgimUnidadMineraDTO = this.unidadMineraService.obtenerUnidadMinera(idUnidadMinera);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la unidad fiscalizable");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimUnidadMineraDTO == null) {
            mensaje = String.format("La unidad fiscalizable con el id: %d no existe en la base de datos", idUnidadMinera);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La unidad fiscalizable ha sido recuperada");
        respuesta.put("pgimUnidadMineraDTO", pgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /***
     * Permite crear la nueva unidad minera.
     * 
     * @param pgimUnidadMineraDTO Portador de los datos para la creación de la
     *                            unidad minera.
     * @param resultadoValidacion Resultado de la validación aplicada a nivel de la
     *                            entidad del modelo de datos.
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('um-lista_IN')")
    @PostMapping("/crearUnidadMinera")
    public ResponseEntity<?> crearUnidadMinera(@Valid @RequestBody PgimUnidadMineraDTO pgimUnidadMineraDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimUnidadMineraDTO pgimUnidadMineraDTOCreada = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la Unidad fiscalizable");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimUnidadMineraDTOCreada = this.unidadMineraService.crearUnidadMinera(pgimUnidadMineraDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la unidad fiscalizable");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La unidad fiscalizable ha sido creada");
        respuesta.put("pgimUnidadMineraDTO", pgimUnidadMineraDTOCreada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('um-lista_MO')")
    @PutMapping("/modificarUnidadMinera")
    public ResponseEntity<?> modificarUnidadMinera(@Valid @RequestBody PgimUnidadMineraDTO pgimUnidadMineraDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimUnidadMinera pgimUnidadMinerActual = null;
        PgimUnidadMineraDTO pgimUnidadMineraDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la unidad fiscalizable");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimUnidadMinerActual = this.unidadMineraService
                    .getByIdUnidadMinera(pgimUnidadMineraDTO.getIdUnidadMinera());

            if (pgimUnidadMinerActual == null) {
                mensaje = String.format("La unidad fiscalizable %s que intenta actualizar no existe en la base de datos",
                        pgimUnidadMineraDTO.getIdUnidadMinera());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la unidad fiscalizable a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimUnidadMineraDTOModificada = this.unidadMineraService.modificarUnidadMinera(pgimUnidadMineraDTO,
                    pgimUnidadMinerActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la unidad fiscalizable");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La unidad fiscalizable ha sido modificada");
        respuesta.put("pgimUnidadMineraDTO", pgimUnidadMineraDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('um-lista_EL')")
    @DeleteMapping("/eliminarUnidadMinera/{idUnidadMinera}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long idUnidadMinera) throws Exception {
        ResponseDTO responseDTO = null;
        String mensaje;

        PgimUnidadMinera pgimUnidadMinerActual = null;

        try {
            pgimUnidadMinerActual = this.unidadMineraService.getByIdUnidadMinera(idUnidadMinera);

            if (pgimUnidadMinerActual == null) {
                mensaje = String.format("La unidad fiscalizable %s que intenta eliminar no existe en la base de datos",
                        idUnidadMinera);
                responseDTO = new ResponseDTO("mensaje", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar la unidad fiscalizable a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimEventoDTO> lPgimEventoDTO = eventoRepository.listarEventoPorUnidadMinera(idUnidadMinera);
            if (lPgimEventoDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar la unidad fiscalizable, por que se encuentra asignada a "
                        + lPgimEventoDTO.size() + " evento(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la unidad fiscalizable. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimComponenteMineroDTO> lPgimComponenteMineroDTO = pgimComponenteMinRepository
                    .listarComponenteMineroPorUnidadMinera(idUnidadMinera);
            if (lPgimComponenteMineroDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar la unidad fiscalizable, por que se encuentra asignada a "
                        + lPgimComponenteMineroDTO.size() + " componente(s) minero(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la unidad fiscalizable. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimAutorizacionDTO> lPgimAutorizacionDTO = autorizacionRepository
                    .listarAutorizacionPorUnidadMinera(idUnidadMinera);
            if (lPgimAutorizacionDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar la unidad fiscalizable, por que se encuentra asignada a "
                        + lPgimAutorizacionDTO.size() + " autorizacion(es)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la unidad fiscalizable. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimSupervisionDTO> lPgimSupervisionDTO = supervisionRepository
                    .listarSupervisionPorUnidadMinera(idUnidadMinera);
            if (lPgimSupervisionDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar la unidad fiscalizable, por que se encuentra asignada a "
                        + lPgimSupervisionDTO.size() + " supervision(es)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la unidad fiscalizable. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            this.unidadMineraService.eliminarUnidadMinera(pgimUnidadMinerActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar eliminar la unidad fiscalizable",
                    e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "La unidad fiscalizable fue eliminada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /***
     * Permite obtener la lista preparada de unidades mineras usado en reporte
     * correspondiente paginada
     * 
     * @param filtroUnidadMinera Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */
    @PostMapping("/listarUMPaginado")
    public ResponseEntity<Page<PgimUnidadMineraAuxDTO>> listarReporteUMPaginado(
            @RequestBody PgimUnidadMineraAuxDTO filtroUnidadMinera, Pageable paginador) {

        Page<PgimUnidadMineraAuxDTO> lPgimUnidadMineraDTO = this.unidadMineraService
                .listarReporteUMPaginado(filtroUnidadMinera, paginador);
        return new ResponseEntity<Page<PgimUnidadMineraAuxDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
    }

    @PostMapping("/actualizarAliasUM")
    public ResponseEntity<ResponseDTO> actualizarAliasUM() throws Exception {

        ResponseDTO responseDTO = null;

        try {
            this.unidadMineraService.actualizarAliasUM(this.obtenerAuditoria());
        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            throw new PgimException("error", "Ocurrió un error al intentar actualizar el alias de las unidades fiscalizables");
        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO("error", e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success",
                "Se han actualizado los alias de anonimización de las unidades fiscalizables");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /***
     * Permite obtener la lista preparada del historial de cambios de las unidades
     * mineras usado en reporte correspondiente
     * paginada
     * 
     * @param filtroUnidadMinera Objeto filtro que porta las propiedades de la
     *                           unidad minera
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */
    @PostMapping("/listarHistoricoUmPaginado")
    public ResponseEntity<Page<PgimCopiaDetalleUmDTO>> listarHistoricoUMPaginado(
            @RequestBody PgimCopiaDetalleUmDTO filtroUnidadMinera, Pageable paginador) {

        Page<PgimCopiaDetalleUmDTO> pPgimCopiaDetalleUmDTO = this.unidadMineraService
                .listarHistoricoUMPaginado(filtroUnidadMinera, paginador);
        return new ResponseEntity<Page<PgimCopiaDetalleUmDTO>>(pPgimCopiaDetalleUmDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerUnidadMinera/coUm/{coUm}")
    public ResponseEntity<?> obtenerUmPorCodigo(@PathVariable String coUm) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimUnidadMineraDTO pgimUnidadMineraDTO = null;

        try {
            pgimUnidadMineraDTO = this.unidadMineraService.obtenerUmPorCodigo(coUm);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la unidad fiscalizable");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimUnidadMineraDTO == null) {
            mensaje = String.format("La unidad fiscalizable con el codigo: %d no existe en la base de datos", coUm);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);

        }

        respuesta.put("mensaje", "La unidad fiscalizable ha sido recuperada");
        respuesta.put("pgimUnidadMineraDTO", pgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
    
    /***
     * Permite consultar la lista preparada de unidades mineras (vista PgimUnidadMineraAux) 
     * que aún no pertenecen a un rankig de riesgo determinado
     * de acuerdo con los criterios filtro y de manera paginada.
     * 
     * @param filtroUnidadMinera Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */
    @PostMapping("/listarUmAuxDisponiblePorRankingRPaginado")
    public ResponseEntity<Page<PgimUnidadMineraAuxDTO>> listarUmAuxDisponiblePorRankingRPaginado(
            @RequestBody PgimUnidadMineraAuxDTO filtroUnidadMinera, Pageable paginador) {

        Page<PgimUnidadMineraAuxDTO> lPgimUnidadMineraDTO = this.unidadMineraService
                .listarUmAuxDisponiblePorRankingRPaginado(filtroUnidadMinera, paginador);
        return new ResponseEntity<Page<PgimUnidadMineraAuxDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
    }

    @GetMapping("/listarUmAsociadas/{idPlntaBeneficioDestino}")
    public ResponseEntity<?> listarUmAsociadas(@PathVariable Long idPlntaBeneficioDestino) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimUnidadMineraDTO> lPgimUnidadMineraDTO = null;

        if (idPlntaBeneficioDestino == 0) {
            idPlntaBeneficioDestino = null;
        }

        try {
            lPgimUnidadMineraDTO = this.unidadMineraService.listarUmAsociadas(idPlntaBeneficioDestino);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite una unidad fiscalizable asociada");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de la unidad fiscalizable asociada");
        respuesta.put("lPgimUnidadMineraDTO", lPgimUnidadMineraDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
