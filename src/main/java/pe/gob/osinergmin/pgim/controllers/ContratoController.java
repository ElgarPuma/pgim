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
import pe.gob.osinergmin.pgim.dtos.FiltroValidaSaldoContratoXsupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimContratoSiaf;
import pe.gob.osinergmin.pgim.models.repository.ConsumoContraRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository;
import pe.gob.osinergmin.pgim.services.ContratoService;
import pe.gob.osinergmin.pgim.services.ContratoSiafService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.TipoParametroMedicionPorContratoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los
 * contratos.
 * 
 * @descripción: Contrato
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/supervisioncontratos")
public class ContratoController extends BaseController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private ContratoSiafService contratoSiafService;
    
    @Autowired
    private PersonalContratoRepository personalContratoRepository;
    
    @Autowired
    private ConsumoContraRepository consumoContraRepository;

    @Autowired
    private TipoParametroMedicionPorContratoService tipoParametroMedicionPorContratoService;

    /**
     * Permite obtener un listado de contratos en el contexto de la paginación de
     * resultados requerida en la lista den el frontend.
     * 
     * @param filtroContrato Objeto filtro que porta las propiedades que de tener
     *                       valor, representan criterios filtro específicos esto
     *                       siempre que la propiedad esté configurada para
     *                       aplicarse como criterio al momento de las consultas.
     * @param paginador      Objeto paginador que tiene la información de la página
     *                       actual, tamaño de la página y criterios de
     *                       ordenamiento.
     * @return
     */
    @PreAuthorize("hasAnyAuthority('co-lista_AC')")
    @PostMapping("/listarContratos")
    public ResponseEntity<Page<PgimContratoDTO>> listarContratos(@RequestBody PgimContratoDTO filtroContrato,
            Pageable paginador) {

        Page<PgimContratoDTO> lPgimContratoDTO = this.contratoService.listarContratos(filtroContrato, paginador);
        return new ResponseEntity<Page<PgimContratoDTO>>(lPgimContratoDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener las configuraciones necesarias para el listado de contratos.
     * Acá se incluyen configuraciones como: Tipo de especialidad.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;

        try {
            lPgimEspecialidadDTO = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite listar por código de contrato de la lista de Contratos
     * 
     * @param palabra = coSupervision
     * @return
     */
    @GetMapping("/filtrar/codigoContrato/{palabra}")
    public ResponseEntity<?> listarPorCodigoContrato(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimContratoDTO> lPgimContratoDTOCodigo = null;

        if (palabra.equals("_vacio_")) {
            lPgimContratoDTOCodigo = new ArrayList<PgimContratoDTO>();
            respuesta.put("mensaje", "No se encontraron código de contratos");
            respuesta.put("lPgimContratoDTOCodigo", lPgimContratoDTOCodigo);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimContratoDTOCodigo = this.contratoService.listarPorCodigoContrato(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de código de contratos");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los código de contratos");
        respuesta.put("lPgimContratoDTOCodigo", lPgimContratoDTOCodigo);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite listar por número de expediente de la lista de Contratos
     * 
     * @param palabra = descNuExpediente
     * @return
     */
    @GetMapping("/filtrar/numeroExpediente/{palabra}")
    public ResponseEntity<?> listarPorNumeroExpediente(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimContratoDTO> lPgimContratoDTONumeroExp = null;

        if (palabra.equals("_vacio_")) {
            lPgimContratoDTONumeroExp = new ArrayList<PgimContratoDTO>();
            respuesta.put("mensaje", "No se encontraron los números de expediente del contrato");
            respuesta.put("lPgimContratoDTONumeroExp", lPgimContratoDTONumeroExp);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimContratoDTONumeroExp = this.contratoService.listarPorNumeroExpediente(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la consulta de los números de expediente del contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los números de expediente del contrato");
        respuesta.put("lPgimContratoDTONumeroExp", lPgimContratoDTONumeroExp);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('co-lista_CO')")
    @GetMapping("/obtenerContratoPorId/{idContrato}")
    public ResponseEntity<?> obtenerContratoPorId(@PathVariable Long idContrato) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimContratoDTO pgimContratoDTO = null;

        try {
            pgimContratoDTO = this.contratoService.obtenerContratoPorId(idContrato);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimContratoDTO == null) {
            mensaje = String.format("El contrato con el id: %d no existe en la base de datos", idContrato);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El contrato ha sido recuperado");
        respuesta.put("pgimContratoDTO", pgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener el contrato usado en la tarjeta del frontend
     * 
     * @param idContrato
     * @return
     */
    // @PreAuthorize("hasAnyAuthority('contrato_CO')")
    @GetMapping("/obtenerContrato/{idContrato}")
    public ResponseEntity<?> obtenerContrato(@PathVariable Long idContrato) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimContratoDTO pgimContratoDTO = null;

        try {
            pgimContratoDTO = this.contratoService.obtenerContrato(idContrato);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimContratoDTO == null) {
            mensaje = String.format("El contrato con el id: %d no existe en la base de datos", idContrato);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El contrato ha sido recuperado");
        respuesta.put("pgimContratoDTO", pgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    /***
     * Permite crear un contrato.
     * 
     * @param pgimContratoDTO     Portador de los datos para la creación de contrato
     * @param resultadoValidacion Resultado de la validación aplicada a nivel de la
     *                            entidad del modelo de datos.
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('co-lista_IN')")
    @PostMapping("/crearContrato")
    public ResponseEntity<?> crearContrato(@Valid @RequestBody PgimContratoDTO pgimContratoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimContratoDTO pgimContratoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un contrato");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimContratoDTOCreado = this.contratoService.crearContrato(pgimContratoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear un contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Empresa involucrada ha sido creada");
        respuesta.put("pgimContratoDTOCreado", pgimContratoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /***
     * Permite obtener las configuraciones necesarias para el formulario de
     * creación/edición de los contratos. Acá se incluyen configuraciones como: tipo
     * de especialidad.
     * 
     * @param idContrato
     * @return
     */
    @GetMapping("/obtenerConfiguraciones/generales/{idContrato}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idContrato) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEspecialidadDTO> lPgimEspecialidadDTOEspecialidad = null;
        PgimContratoDTO pgimContratoDTODias = new PgimContratoDTO();

        try {

            lPgimEspecialidadDTOEspecialidad = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

            List<PgimValorParametroDTO> lPgimValorParametroDTO = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_PLAZOS_REVISION_INFORME);

            for (PgimValorParametroDTO pgimValorParametroDTO : lPgimValorParametroDTO) {
                if (pgimValorParametroDTO.getCoClave().equals(ConstantesUtil.PARAM_PLAZOS_REVINFORME_ENTREGA_CO_CLAVE)) {

                    pgimContratoDTODias.setNuDiasEntregaInforme(pgimValorParametroDTO.getNuValorNumerico().intValue());

                } else {

                    pgimContratoDTODias.setNuDiasAbsolucionInforme(pgimValorParametroDTO.getNuValorNumerico().intValue());
                    
                }
            }

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        respuesta.put("lPgimEspecialidadDTOEspecialidad", lPgimEspecialidadDTOEspecialidad);
        respuesta.put("pgimContratoDTODias", pgimContratoDTODias);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('co-lista_MO')")
    @PutMapping("/modificarContrato")
    public ResponseEntity<?> modificarContrato(@Valid @RequestBody PgimContratoDTO pgimContratoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimContrato pgimContratoActual = null;
        PgimContratoDTO pgimContratoDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el contrato");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimContratoActual = this.contratoService.getByIdContrato(pgimContratoDTO.getIdContrato());

            if (pgimContratoActual == null) {
                mensaje = String.format("El contrato %s que intenta actualizar no existe en la base de datos",
                        pgimContratoDTO.getIdContrato());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el contrato a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimContratoDTOModificada = this.contratoService.modificarContrato(pgimContratoDTO, pgimContratoActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El contrato ha sido modificada");
        respuesta.put("pgimContratoDTOModificada", pgimContratoDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('co-lista_EL')")
    @DeleteMapping("/eliminarContrato/{idContrato}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long idContrato) throws Exception {
    	ResponseDTO responseDTO = null;
        String mensaje;

        PgimContrato pgimContrActual = null;

        try {
            pgimContrActual = this.contratoService.getByIdContrato(idContrato);

            if (pgimContrActual == null) {
                mensaje = String.format("El contrato %s que intenta eliminar no existe en la base de datos",
                        idContrato);
                responseDTO = new ResponseDTO("mensaje", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar el contrato a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
        
        try { 
        	List<PgimConsumoContraDTO> lPgimConsumoContraDTO = consumoContraRepository.listarConsumoContratoPorContrato(idContrato);
            if (lPgimConsumoContraDTO.size() > 0) {
                mensaje = String.format(
                        "No se puede eliminar el contrato, por que se encuentra asignada a " + lPgimConsumoContraDTO.size() + " consumo(s) de contrato");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar el contrato. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
        
        try { 
        	List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = personalContratoRepository.listarPersonalContratoPorContrato(idContrato);
            if (lPgimPersonalContratoDTO.size() > 0) {
                mensaje = String.format(
                        "No se puede eliminar el contrato, por que se encuentra asignada a " + lPgimPersonalContratoDTO.size() + " personal de contrato");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar el contrato. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            this.contratoService.eliminarContrato(pgimContrActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar eliminar un contrato",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "El contrato fue eliminado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Permite validar el código de contrato que sea único
     * 
     * @param idContrato
     * @param nuContrato
     * @return
     */
    @GetMapping("/existeContrato/{idContrato}/{nuContrato}")
    public ResponseEntity<?> existeContrato(@PathVariable Long idContrato, @PathVariable String nuContrato) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimContratoDTO> lPgimContratoDTO = null;

        if (idContrato == 0) {
            idContrato = null;
        }

        try {
            lPgimContratoDTO = this.contratoService.existeContrato(idContrato, nuContrato);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite un contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de un contrato");
        respuesta.put("lPgimContratoDTO", lPgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/existeNuExpediente/{idInstanciaProceso}/{nuExpedienteSiged}")
    public ResponseEntity<?> existeNuExpediente(@PathVariable Long idInstanciaProceso,
            @PathVariable String nuExpedienteSiged) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimContratoDTO> lPgimContratoDTO = null;

        if (idInstanciaProceso == 0) {
            idInstanciaProceso = null;
        }

        try {
            lPgimContratoDTO = this.contratoService.existeNuExpediente(idInstanciaProceso, nuExpedienteSiged);

        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar verificar si ya exite un número de expediente Siged");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de un número de expediente Siged");
        respuesta.put("lPgimContratoDTO", lPgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite hacer el calculo del costo de una supervisión, es empleado en la
     * asignación de supervisión, así como en los casos que se hace la actualización
     * de la supervisión en la etapa de pre-supervisión y supervisión de campo
     * 
     * @param pgimSupervisionDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/calcularCostoSupervision")
    public ResponseEntity<ResponseDTO> calcularCostoSupervision(@RequestBody final PgimSupervisionDTO pgimSupervisionDTO) throws Exception {

        ResponseDTO responseDTO = null;
        PgimConsumoContraDTO pgimConsumoContraDTO = null;

        try {
        	
        	PgimContratoDTO pgimContratoDTO = contratoService.obtenerContratoPorId(pgimSupervisionDTO.getDescIdContrato());

        	if (pgimSupervisionDTO.getFeInicioSupervision().before(pgimContratoDTO.getFeInicioContrato())) {
                responseDTO = new ResponseDTO(TipoResultado.WARNING,
                        "La fecha de inicio programada de la fiscalización no puede ubicarse antes de la fecha de inicio del contrato.");

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }            
        	
        	if(pgimSupervisionDTO.getFeInicioSupervision().after(pgimContratoDTO.getFeFinContrato())) {
                responseDTO = new ResponseDTO(TipoResultado.WARNING, "La fecha de inicio programada de la fiscalización no puede ubicarse después de la fecha de finalización del contrato.");

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        	}
        	
            pgimConsumoContraDTO = this.contratoService.calcularCostoSupervision(pgimSupervisionDTO, false);
            
        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar calcular el costo de la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final PgimException e) {
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar calcular el costo de la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimConsumoContraDTO, "Se ha calculado el costo de la fiscalización así como el saldo probable del monto disponible del contrato");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
    }

    @PostMapping("/obtenerSaldoContrato")
    public ResponseEntity<ResponseDTO> obtenerSaldoContrato(@RequestBody final PgimContratoDTO pgimContratoDTO)
            throws Exception {

        ResponseDTO responseDTO = null;
        PgimContratoDTO pgimContratoDTOResultante = null;

        try {
            pgimContratoDTOResultante = this.contratoService.calcularSaldoContrato(pgimContratoDTO);
        } catch (final DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar calcular el costo de la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {

            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar calcular el costo de la fiscalización");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimContratoDTOResultante, "El saldo del contrato fue calculado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
    }

    /**
     * Permite validar el saldo del contrato para una nueva supervisión, es empleado
     * en la asignación de supervisión
     * 
     * @param pgimSupervisionDTO
     * @param pgimContratoDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/validarSaldoContratoXsupervision")
    public ResponseEntity<ResponseDTO> validarSaldoContratoXsupervision(
            @RequestBody final FiltroValidaSaldoContratoXsupervisionDTO filtroValidaSaldoContratoXsupervisionDTO)
            throws Exception {

        ResponseDTO responseDTO = null;
        PgimConsumoContraDTO pgimConsumoContraDTO = null;

        try {
            pgimConsumoContraDTO = this.contratoService.validarSaldoContratoXsupervision(
                    filtroValidaSaldoContratoXsupervisionDTO.getPgimContratoDTO(),
                    filtroValidaSaldoContratoXsupervisionDTO.getPgimSupervisionDTO());
        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            throw new PgimException("error",
                    "Ocurrió un error al intentar validar el saldo del contrato, para el monto de la supervisión");
        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO("error", e.getMensaje());

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            throw e;
        }

        responseDTO = new ResponseDTO("success", pgimConsumoContraDTO,
                "El saldo del contrato fue validado, para el monto de la supervisión");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/obtenerContratoSeguimientoPorId/{idContrato}")
    public ResponseEntity<?> obtenerContratoSeguimientoPorId(@PathVariable Long idContrato) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimContratoDTO pgimContratoDTO = null;

        try {
            pgimContratoDTO = this.contratoService.obtenerContratoSeguimientoPorId(idContrato);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el contrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimContratoDTO == null) {
            mensaje = String.format("El contrato con el id: %d no existe en la base de datos", idContrato);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El contrato ha sido recuperado");
        respuesta.put("pgimContratoDTO", pgimContratoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite crear el contrato SIAF
     * 
     * @param pgimContratoSiafDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('co-siaf_IN')")
    @PostMapping("/crearContratoSiaf")
    public ResponseEntity<?> crearContratoSiaf(@Valid @RequestBody PgimContratoSiafDTO pgimContratoSiafDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimContratoSiafDTO pgimContratoSiafDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para contrato SIAF");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimContratoSiafDTOCreado = this.contratoSiafService.crearContratoSiaf(pgimContratoSiafDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear contrato SIAF");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Contrato SIAF ha sido creado");
        respuesta.put("pgimContratoSiafDTO", pgimContratoSiafDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('co-siaf_AC')")
    @GetMapping("/listarSiaf/{idContrato}")
	public ResponseEntity<?> listarContratoSiaf(@PathVariable Long idContrato) {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimContratoSiafAuxDTO> lPgimContratoSiafAuxDTO = null;

		try {
			lPgimContratoSiafAuxDTO = this.contratoSiafService.listarContratoSiaf(idContrato);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de contrato SIAF");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los contratos SIAF");
		respuesta.put("lPgimContratoSiafAuxDTO", lPgimContratoSiafAuxDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

    /**
	 * Me permite modificar el personal de un contrato
	 * "Este metodo se esta en uso todavia"
	 * @param pgimPersonalContratoDTO
	 * @param resultadoValidacion
	 * @return
	 * @throws Exception
	 */
    @PreAuthorize("hasAnyAuthority('co-siaf_MO')")
	@PostMapping("/modificarContratoSiaf")
	public ResponseEntity<?> modificarContratoSiaf(
			@Valid @RequestBody PgimContratoSiafDTO pgimContratoSiafDTO, BindingResult resultadoValidacion)
			throws Exception {

                PgimContratoSiafDTO pgimContratoSiafDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para crear a la empresa evento");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			pgimContratoSiafDTOCreado = this.contratoSiafService
					.modificarContratoSiaf(pgimContratoSiafDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar la empresa evento");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La empresa evento ha sido modificada");
		respuesta.put("pgimContratoSiafDTO", pgimContratoSiafDTOCreado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

    /**
	 * Permite eliminar un contrato. Esta eliminación es lógica.
	 * 
	 * @param idContratoSiaf
	 * @return
	 * @throws Exception
	 */
    @PreAuthorize("hasAnyAuthority('co-siaf_EL')")
	@DeleteMapping("/eliminarContratoSiaf/{idContratoSiaf}")
	public ResponseEntity<?> eliminarContratoSiaf(@PathVariable Long idContratoSiaf) throws Exception {
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje;

		PgimContratoSiaf pgimContratoSiafActual = null;

		try {
			pgimContratoSiafActual = this.contratoSiafService.getByIdContratoSiaf(idContratoSiaf);

			if (pgimContratoSiafActual == null) {
				mensaje = String.format("El personal de contrato %s que intenta eliminar no existe en la base de datos",
                idContratoSiaf);
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar personal de contrato a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			this.contratoSiafService.eliminarContratoSiaf(pgimContratoSiafActual, this.obtenerAuditoria());
			;
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar eliminar un personal de contrato");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "ok");

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping("/validarExpedienteSiged/{nuExpedienteSiged}")
    public ResponseEntity<?> validarExpedienteSiged(@PathVariable String nuExpedienteSiged) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        String expedienteValido = "";

        try {
        	expedienteValido = this.contratoService.validarExpedienteSiged(nuExpedienteSiged, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar verificar el número de expediente Siged");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) { 
            log.error(e.getMessage(), e);
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar verificar el número de expediente Siged");
            respuesta.put("error", e.getMessage());
        }

        respuesta.put("mensaje", expedienteValido);
        
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
    @PostMapping("/listarControlSaldosContratoSupervisora")
    public ResponseEntity<Page<PgimContratoSegumntoAuxDTO>> listarControlSaldosContratoSupervisora(@RequestBody PgimContratoSegumntoAuxDTO filtroContratoSegumntoAux,
            Pageable paginador) {

        Page<PgimContratoSegumntoAuxDTO> lPgimContratoSegumntoAuxDTO = this.contratoService.listarContratoSeguimientoAux(filtroContratoSegumntoAux, paginador);
        return new ResponseEntity<Page<PgimContratoSegumntoAuxDTO>>(lPgimContratoSegumntoAuxDTO, HttpStatus.OK);
    }
    
    @PostMapping("/listarReporteEjecucionPresupuestal")
    public ResponseEntity<Page<PgimContratoSiafAuxDTO>> listarReporteEjecucionPresupuestal(@RequestBody PgimContratoSiafAuxDTO filtroContratoSiafAux,
            Pageable paginador) {

        Page<PgimContratoSiafAuxDTO> lPgimContratoSiafAuxDTO = this.contratoService.listarReporteEjecucionPresupuestal(filtroContratoSiafAux, paginador);
        return new ResponseEntity<Page<PgimContratoSiafAuxDTO>>(lPgimContratoSiafAuxDTO, HttpStatus.OK);
    }
    
    @PostMapping("/listarReportePenalidadesPeriodoContratoSupervisora")
    public ResponseEntity<Page<PgimPenalidadAuxDTO>> listarReportePenalidadesPeriodoContratoSupervisora(@RequestBody PgimPenalidadAuxDTO filtroPenalidadAuxDTO,
            Pageable paginador) {

        Page<PgimPenalidadAuxDTO> lPgimPenalidadAuxDTO = this.contratoService.listarReportePenalidadesPeriodoContratoSupervisora(filtroPenalidadAuxDTO, paginador);
        return new ResponseEntity<Page<PgimPenalidadAuxDTO>>(lPgimPenalidadAuxDTO, HttpStatus.OK);
    }
    
    @PostMapping("/listarReportePresupuestoGastoSupervision")
    public ResponseEntity<Page<PgimPrspstoGastoSuperDTO>> listarReportePresupuestoGastoSupervision(@RequestBody PgimPrspstoGastoSuperDTO filtroPrspstoGastoSuperDTO,
            Pageable paginador) {

        Page<PgimPrspstoGastoSuperDTO> lPgimPrspstoGastoSuperDTO = this.contratoService.listarReportePresupuestoGastoSupervision(filtroPrspstoGastoSuperDTO, paginador);
        return new ResponseEntity<Page<PgimPrspstoGastoSuperDTO>>(lPgimPrspstoGastoSuperDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista de parámetros de medición por identificador de contrato.
     * @param idContrato
     * @return
     */
    @GetMapping("/listarParametrosPorContrato/{idContrato}")
    public ResponseEntity<ResponseDTO> listarParametrosPorContrato(@PathVariable Long idContrato) {

        ResponseDTO responseDTO = null;
        List<PgimTipopameXContratoDTO> lPgimTipopameXContratoDTO = null;
        String mensaje;

        try {
            
            lPgimTipopameXContratoDTO = this.tipoParametroMedicionPorContratoService.listarParametrosPorContrato(idContrato);
            
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensaje = String.format("Ocurrió un error intentar recuperar el contrato a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }  catch (Exception e) { 
            log.error(e.getMessage(), e);
            
            mensaje = String.format("Ocurrió un error intentar recuperar los parámetros de medición del contrato",
            e.getMessage());            

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", lPgimTipopameXContratoDTO,
        "La lista de parámetros del contrato se ha recuperado");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /***
     * Permite crear un contrato.
     * 
     * @param pgimContratoDTO     Portador de los datos para la creación de contrato
     * @param resultadoValidacion Resultado de la validación aplicada a nivel de la
     *                            entidad del modelo de datos.
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('co-lista_IN')")
    @PostMapping("/seleccionarTParametrosXTTipoInstrumento/{idContrato}")
    public ResponseEntity<ResponseDTO> seleccionarTParametrosXTTipoInstrumento(@RequestBody List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO, @PathVariable Long idContrato) throws Exception {

        ResponseDTO responseDTO = null;
        String mensaje;

        try {
            this.contratoService.seleccionarTParametrosXTTipoInstrumento(idContrato, lPgimTprmtroXTinstrmntoDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            mensaje = String.format("Ocurrió un error de base de datos al intentar seleccionar los parámetros para el contrato",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }  catch (Exception e) { 
            log.error(e.getMessage(), e);
            
            mensaje = String.format("Ocurrió un error general al intentar seleccionar los parámetros para el contrato",
            e.getMessage());            

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", null,
        "La selección se ha realizado con éxito");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PreAuthorize("hasAnyAuthority('co-lista_EL')")
    @DeleteMapping("/eliminarTipoParametroDeContrato/{idTipopameXContrato}")
    public ResponseEntity<ResponseDTO> eliminarTipoParametroDeContrato(@PathVariable Long idTipopameXContrato) throws Exception {
    	ResponseDTO responseDTO = null;
        String mensaje;

        try {
            this.tipoParametroMedicionPorContratoService.eliminarTipoParametroDeContrato(idTipopameXContrato, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            
            mensaje = String.format("Ocurrió un error intentar eliminar un contrato",
                    e.getMostSpecificCause().getMessage());
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (PgimException e) {
            log.error(e.getMessage(), e);
            
            mensaje = String.format("No se puede deseleccionar el tipo de parámetro del contrato porque %s",
                    e.getMessage());
            responseDTO = new ResponseDTO("validation", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            
            mensaje = String.format("No se puede deseleccionar el tipo de parámetro del contrato porque ",
                    e.getMessage());
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "El tipo de parámetro fue deseleccionado del contrato");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}