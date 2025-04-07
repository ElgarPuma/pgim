package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
import pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadOrganicaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonaConsorcio;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsi;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsiCargo;
import pe.gob.osinergmin.pgim.models.repository.AccidentadoRepository;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.ContactoAgenteRepository;
import pe.gob.osinergmin.pgim.models.repository.EmpresaEventoRepository;
import pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaConsorcioRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.pido.IntegracionPido;
import pe.gob.osinergmin.pgim.pido.PidoBeanOutRO;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PerfilUserService;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.services.PidoService;
import pe.gob.osinergmin.pgim.siged.TipoDocumento;
import pe.gob.osinergmin.pgim.siged.Tiposdocumento;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas con la
 * Persona natural y juridica
 * 
 * @descripción: Persona
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/personas")
public class PersonaController extends BaseController {

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PidoService pidoService;
    
    @Autowired
    private PerfilUserService perfilUserService;

    @Autowired
    private AgenteSupervisadoRepository agenteSupervisadoRepository;

    @Autowired
    private EmpresaSupervisoraRepository empresaSupervisoraRepository;

    @Autowired
    private AccidentadoRepository accidentadoRepository;

    @Autowired
    private EmpresaEventoRepository empresaEventoRepository;

    @Autowired
    private ContactoAgenteRepository contactoAgenteRepository;

    @Autowired
    private PersonaConsorcioRepository personaConsorcioRepository;
    
    @Autowired
	private ValorParametroRepository valorParametroRepository;

    @Autowired
	private PersonalOsiRepository personalOsiRepository;
    
    @Autowired
	DocumentoService documentoService;

    @PreAuthorize("hasAnyAuthority('pe-lista_AC')")
    @PostMapping("/listarPersonas")
    public ResponseEntity<Page<PgimPersonaDTO>> listarPersonas(@RequestBody PgimPersonaDTO filtroPersona,
            Pageable paginador) {
        Page<PgimPersonaDTO> lPgimPersonaDTO = this.personaService.listarPersonas(filtroPersona, paginador);
        return new ResponseEntity<Page<PgimPersonaDTO>>(lPgimPersonaDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener las configuraciones necesarias para el listado de personas
     * naturales y juridicas. Acá se incluyen configuraciones como: Tipo de
     * documento de identidad ya sea RUC, DNI O CE.
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocIdentidad = null;
        List<PgimUnidadOrganicaDTO> lPgimUnidadOrganicaDTO = null;
        try {
            lPgimValorParametroDTOTipoDocIdentidad = this.parametroService.filtrarPorTipoDocIdentidad(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD);
            lPgimUnidadOrganicaDTO = this.parametroService.listarUnidadOrganica();
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParametroDTOTipoDocIdentidad", lPgimValorParametroDTOTipoDocIdentidad);
        respuesta.put("lPgimUnidadOrganicaDTO", lPgimUnidadOrganicaDTO);
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Me permite listar por numero de documento de identidad de la persona juridica
     * o natural
     * 
     * @param palabra = coDocumentoIdentidad
     * @return
     */
    @GetMapping("/filtrar/coDocumentoIdentidad/{palabra}")
    public ResponseEntity<?> listarPorCoDocumentoIdentidad(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTOCoDocumentoIdentidad = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTOCoDocumentoIdentidad = new ArrayList<PgimPersonaDTO>();
            respuesta.put("mensaje", "No se encontraron número de documetno de identidad");
            respuesta.put("lPgimPersonaDTOCoDocumentoIdentidad", lPgimPersonaDTOCoDocumentoIdentidad);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTOCoDocumentoIdentidad = this.personaService.listarPorCoDocumentoIdentidad(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de número de documetno de identidad");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los números de documetno de identidad");
        respuesta.put("lPgimPersonaDTOCoDocumentoIdentidad", lPgimPersonaDTOCoDocumentoIdentidad);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite filtrar por nombre de la razón social de una persona juridica
     * 
     * @param palabra = noRazonSocial
     * @return
     */
    @GetMapping("/filtrar/noRazonSocial/{palabra}")
    public ResponseEntity<?> listarPorNoRazonSocial(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTONoRazonSocial = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTONoRazonSocial = new ArrayList<PgimPersonaDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres de la Razón social");
            respuesta.put("lPgimPersonaDTONoRazonSocial", lPgimPersonaDTONoRazonSocial);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTONoRazonSocial = this.personaService.listarPorNoRazonSocial(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los nombres de la Razón social");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres de la Razón social");
        respuesta.put("lPgimPersonaDTONoRazonSocial", lPgimPersonaDTONoRazonSocial);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista de personas jurídicas en base a la razón social o RUC.
     * 
     * @param palabra
     * @return
     */
    @GetMapping("/filtrar/razonSocial/{palabra}")
    public ResponseEntity<?> listarPorRazonSocial(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTORazonSocial = null;
        String mensajeExcepcion;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTORazonSocial = new ArrayList<PgimPersonaDTO>();
            mensajeExcepcion = String.format("No se encontraron personas jurídicas con el RUC o razón social especificada por %s", palabra);
            respuesta.put("mensaje", mensajeExcepcion);
            respuesta.put("lPgimPersonaDTORazonSocial", lPgimPersonaDTORazonSocial);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTORazonSocial = this.personaService.listarPorRazonSocial(palabra);
        } catch (DataAccessException e) {
            mensajeExcepcion = String.format("Ocurrió un error al realizar la consulta de las personas jurídicas con el RUC o razón social especificada por %s", palabra);
            respuesta.put("mensaje", mensajeExcepcion);
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las personas jurídicas");
        respuesta.put("lPgimPersonaDTORazonSocial", lPgimPersonaDTORazonSocial);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('pe-juri_CO') or hasAnyAuthority('pe-natu_CO')")
    @GetMapping("/obtenerPersonalNatuJuriPorId/{idPersona}")
    public ResponseEntity<?> obtenerPersonalNatuJuriPorId(@PathVariable Long idPersona) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimPersonaDTO pgimPersonaDTO = null;

        try {
            pgimPersonaDTO = this.personaService.obtenerPersonalNatuJuriPorId(idPersona);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el agente fiscalizado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimPersonaDTO == null) {
            mensaje = String.format("El agente fiscalizado con el id: %d no existe en la base de datos", idPersona);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El agente fiscalizado ha sido recuperado");
        respuesta.put("pgimPersonaDTO", pgimPersonaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('pe-juri_IN') or hasAnyAuthority('pe-natu_IN')")
    @PostMapping("/crearPersona")
    public ResponseEntity<?> crearPersona(@Valid @RequestBody PgimPersonaDTO pgimPersonaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonaDTO pgimPersonaDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una persona natural o juridica");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonaDTOCreado = this.personaService.crearPersona(pgimPersonaDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear una persona natural o juridica");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La persona natural o juridica ha sido creada");
        respuesta.put("pgimPersonaDTOCreado", pgimPersonaDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguraciones/generales/{idPersona}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idPersona) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocIdentidad = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocIdentidadRuc = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocIdentidadDniCe = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocIdentidadDni = null;
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoDocIdentidadCe = null;
        try {

            lPgimValorParametroDTOTipoDocIdentidad = this.parametroService
            		.listarValoresParametro(ConstantesUtil.PARAM_TDI_RUC_DNI_CE);
            lPgimValorParametroDTOTipoDocIdentidadRuc = this.parametroService
                    .filtrarPorTipoDocIdentidadRuc(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD_RUC);
            lPgimValorParametroDTOTipoDocIdentidadDniCe = this.parametroService
                    .filtrarPorTipoDocIdentidadDniCe(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD_DNI_CE);
            lPgimValorParametroDTOTipoDocIdentidadDni = this.parametroService
                    .filtrarPorTipoDocIdentidadDni(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD_DNI);
            lPgimValorParametroDTOTipoDocIdentidadCe = this.parametroService
                    .filtrarPorTipoDocIdentidadCe(ConstantesUtil.PARAM_TIPO_DOCUMENTO_IDENTIDAD_CE);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        respuesta.put("lPgimValorParametroDTOTipoDocIdentidad", lPgimValorParametroDTOTipoDocIdentidad);
        respuesta.put("lPgimValorParametroDTOTipoDocIdentidadRuc", lPgimValorParametroDTOTipoDocIdentidadRuc);
        respuesta.put("lPgimValorParametroDTOTipoDocIdentidadDniCe", lPgimValorParametroDTOTipoDocIdentidadDniCe);
        respuesta.put("lPgimValorParametroDTOTipoDocIdentidadDni", lPgimValorParametroDTOTipoDocIdentidadDni);
        respuesta.put("lPgimValorParametroDTOTipoDocIdentidadCe", lPgimValorParametroDTOTipoDocIdentidadCe);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('pe-juri_MO') or hasAnyAuthority('pe-natu_MO')")
    @PutMapping("/modificarPersona")
    public ResponseEntity<?> modificarPersona(@Valid @RequestBody PgimPersonaDTO pgimPersonaDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersona pgimPersonaActual = null;
        PgimPersonaDTO pgimPersonaDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar una persona natural o juridica");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonaActual = this.personaService.getByIdPersona(pgimPersonaDTO.getIdPersona());

            if (pgimPersonaActual == null) {
                mensaje = String.format(
                        "La persona natural o juridica %s que intenta actualizar no existe en la base de datos",
                        pgimPersonaDTO.getIdPersona());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la persona natural o juridica a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimPersonaDTOModificada = this.personaService.modificarPersona(pgimPersonaDTO, pgimPersonaActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la persona natural o juridica");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La persona natural o juridica ha sido modificada");
        respuesta.put("pgimPersonaDTOModificada", pgimPersonaDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('pe-juri_EL') or hasAnyAuthority('pe-natu_EL')")
    @DeleteMapping("/eliminarPersona/{idPersona}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long idPersona) throws Exception {
        ResponseDTO responseDTO = null;
        String mensaje;

        PgimPersona pgimPersActual = null;

        try {
            pgimPersActual = this.personaService.getByIdPersona(idPersona);

            if (pgimPersActual == null) {
                mensaje = String.format(
                        "La persona natural o juridica %s que intenta eliminar no existe en la base de datos",
                        idPersona);
                responseDTO = new ResponseDTO("mensaje", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar recuperar la persona natural o juridica a actualizar",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimAgenteSupervisadoDTO> lPgimAgenteSupervisadoDTO = agenteSupervisadoRepository
                    .listarAgenteSupervisadoPorPersona(idPersona);
            if (lPgimAgenteSupervisadoDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar a la persona, por que se encuentra asignada a "
                        + lPgimAgenteSupervisadoDTO.size() + " agente(s) supervisado(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la persona. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimEmpresaSupervisoraDTO> lPgimEmpresaSupervisoraDTO = empresaSupervisoraRepository
                    .listarEmpresaSupervisoraPorPersona(idPersona);
            if (lPgimEmpresaSupervisoraDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar a la persona, por que se encuentra asignada a "
                        + lPgimEmpresaSupervisoraDTO.size() + " empresa(s) supervisora(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la persona. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimAccidentadoDTO> lPgimAccidentadoDTO = accidentadoRepository.listarAccidentadoPorPersona(idPersona);
            if (lPgimAccidentadoDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar a la persona, por que se encuentra asignada a "
                        + lPgimAccidentadoDTO.size() + " accidentado(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la persona. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimEmpresaEventoDTO> lPgimEmpresaEventoDTO = empresaEventoRepository
                    .listarEmpresaEventoPorPersona(idPersona);
            if (lPgimEmpresaEventoDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar a la persona, por que se encuentra asignada a "
                        + lPgimEmpresaEventoDTO.size() + " empresas(s) relacionadas a un evento");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la persona. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimContactoAgenteDTO> lPgimContactoAgenteDTO = contactoAgenteRepository
                    .listarContactoAgentePorPersona(idPersona);
            if (lPgimContactoAgenteDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar a la persona, por que se encuentra asignada a "
                        + lPgimContactoAgenteDTO.size() + " contacto(s) de agente fiscalizado");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la persona. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            List<PgimPersonaConsorcioDTO> lPgimPersonaConsorcioDTO = personaConsorcioRepository
                    .listarConsorciosPorPersona(idPersona);
            if (lPgimPersonaConsorcioDTO.size() > 0) {
                mensaje = String.format("No se puede eliminar a la persona, por que se encuentra asignada a "
                        + lPgimPersonaConsorcioDTO.size() + " consorcio(s)");
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar la persona. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            this.personaService.eliminarPersona(pgimPersActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error intentar eliminar una persona natural o juridica",
                    e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "La persona fue eliminada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/existePersona/{idPersona}/{coClaveTexto}/{numeroDocumento}")
    public ResponseEntity<?> existePersona(@PathVariable Long idPersona, @PathVariable String coClaveTexto,
            @PathVariable String numeroDocumento) {

        if (idPersona == 0) {
            idPersona = null;
        }

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTO = null;

        try {
        	Long idTipoDocumento = valorParametroRepository.obtenerIdValorParametro(coClaveTexto);
        	
            lPgimPersonaDTO = this.personaService.existePersona(idPersona, idTipoDocumento, numeroDocumento);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite una persona accidentado");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de la persona accidentada");
        respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener los datos de la persona natural mediante DNI/CE desde la PIDO
     * 
     * @param numeroDNIoCE
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    @GetMapping("/procesaObtenerCiudadano/{numeroDNIoCE}")
    public ResponseEntity<?> procesaObtenerCiudadano(@PathVariable String numeroDNIoCE) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        IntegracionPido pido = null;

        try {
            pido = this.pidoService.procesaObtenerCiudadano(numeroDNIoCE, this.obtenerAuditoria());
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del número de DNI");
            respuesta.put("error", e.getMessage());
            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pido == null) {
            mensaje = String.format("No es posible procesar el número de documento ingresado", numeroDNIoCE);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "Se encontró DNI");
        respuesta.put("IntegracionPido", pido);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener los datos de la persona natural mediante DNI/CE desde la PIDO
     * 
     * @param numeroDNIoCE
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    @GetMapping("/procesaObtenerContribuyente/{numeroRUC}")
    public ResponseEntity<?> procesaObtenerContribuyente(@PathVariable String numeroRUC) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        IntegracionPido pido = null;

        try {
            pido = this.pidoService.procesaObtenerContribuyente(numeroRUC, this.obtenerAuditoria());
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del número RUC");
            respuesta.put("error", e.getMessage());
            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pido == null) {
            mensaje = String.format("No es posible procesar el número de RUC ingresado", numeroRUC);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "Se encontró RUC");
        respuesta.put("IntegracionPido", pido);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrarPersonaNatural/noRazonSocial/{idAgenteSupervisado}/{palabra}")
    public ResponseEntity<?> listarPersonaNaturalPorNoRazonSocial(@PathVariable Long idAgenteSupervisado,
            @PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTONoRazonSocial = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTONoRazonSocial = new ArrayList<PgimPersonaDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres de la Razón social");
            respuesta.put("lPgimPersonaDTONoRazonSocial", lPgimPersonaDTONoRazonSocial);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTONoRazonSocial = this.personaService
                    .listarPersonalNaturalPorNoRazonSocial(idAgenteSupervisado, palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los nombres de la Razón social");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres de la Razón social");
        respuesta.put("lPgimPersonaDTONoRazonSocial", lPgimPersonaDTONoRazonSocial);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/filtrarPersonaNatural/noRazonSocialSuper/{idEmpresaSupervisora}/{palabra}")
    public ResponseEntity<?> listarPersonalNaturalPorNoRazonSocialSuper(@PathVariable Long idEmpresaSupervisora,
            @PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTONoRazonSocial = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTONoRazonSocial = new ArrayList<PgimPersonaDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres de la Razón social");
            respuesta.put("lPgimPersonaDTONoRazonSocial", lPgimPersonaDTONoRazonSocial);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTONoRazonSocial = this.personaService
                    .listarPersonalNaturalPorNoRazonSocialSuper(idEmpresaSupervisora, palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los nombres de la Razón social");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres de la Razón social");
        respuesta.put("lPgimPersonaDTONoRazonSocial", lPgimPersonaDTONoRazonSocial);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/listarPersonaNaturalPorPersonalOsi/{palabra}")
    public ResponseEntity<?> listarPersonaNaturalPorPersonalOsi(
            @PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTOPersonalOsi = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaDTOPersonalOsi = new ArrayList<PgimPersonaDTO>();
            respuesta.put("mensaje", "No se encontraron los nombres del personal de Osinergmin");
            respuesta.put("lPgimPersonaDTOPersonalOsi", lPgimPersonaDTOPersonalOsi);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaDTOPersonalOsi = this.personaService
                    .listarPersonaNaturalPorPersonalOsi(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la consulta de los nombres del personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los nombres del personal de Osinergmin");
        respuesta.put("lPgimPersonaDTOPersonalOsi", lPgimPersonaDTOPersonalOsi);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/listarPorPersonaJuridica/palabraClave/{idPersona}/{palabra}")
    public ResponseEntity<?> listarPorPersonaJuridica(@PathVariable Long idPersona, @PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaConsorcioDTO> lPgimPersonaConsorcioDTOJuridica = null;

        if (palabra.equals("_vacio_")) {
            lPgimPersonaConsorcioDTOJuridica = new ArrayList<PgimPersonaConsorcioDTO>();
            respuesta.put("mensaje", "No se encontraron número de documetno de identidad");
            respuesta.put("lPgimPersonaConsorcioDTOJuridica", lPgimPersonaConsorcioDTOJuridica);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimPersonaConsorcioDTOJuridica = this.personaService.listarPorPersonaJuridica(idPersona, palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de número de documetno de identidad");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron los números de documetno de identidad");
        respuesta.put("lPgimPersonaConsorcioDTOJuridica", lPgimPersonaConsorcioDTOJuridica);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/listarConsorcios")
    public ResponseEntity<Page<PgimPersonaConsorcioDTO>> listarConsorcios(
            @RequestBody Long idPersonaJuridicaConsorcio,
            Pageable paginador) {

        Page<PgimPersonaConsorcioDTO> lPgimPersonaConsorcioDTO = this.personaService
                .listarConsorcios(idPersonaJuridicaConsorcio, paginador);
        return new ResponseEntity<Page<PgimPersonaConsorcioDTO>>(lPgimPersonaConsorcioDTO, HttpStatus.OK);
    }

    @PostMapping("/crearConsorcio")
    public ResponseEntity<?> crearConsorcio(@Valid @RequestBody PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonaConsorcioDTO pgimPersonaConsorcioDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear una nueva empresa supervisora");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonaConsorcioDTOCreado = this.personaService.crearPersonaConsorcio(pgimPersonaConsorcioDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear una empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La empresa supervisora ha sido creada");
        respuesta.put("pgimPersonaConsorcioDTOCreado", pgimPersonaConsorcioDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarConsorcio/{idPersonaconsorcio}")
    public ResponseEntity<?> eliminarPersonaConsorcio(@PathVariable Long idPersonaconsorcio) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimPersonaConsorcio pgimPersonaConsorcioActual = null;

        try {
            pgimPersonaConsorcioActual = this.personaService.getByIdPersonaConsorcio(idPersonaconsorcio);

            if (pgimPersonaConsorcioActual == null) {
                mensaje = String.format("La empresa supervisora %s que intenta eliminar no existe en la base de datos",
                        idPersonaconsorcio);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la empresa supervisora a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.personaService.eliminarPersonaConsorcio(pgimPersonaConsorcioActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerConsorcioPorId/{idPersonaConsorcio}")
    public ResponseEntity<?> obtenerConsorcioPorId(@PathVariable Long idPersonaConsorcio) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO = null;

        try {
            pgimPersonaConsorcioDTO = this.personaService.obtenerConsorcioPorId(idPersonaConsorcio);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la empresa supervisora");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimPersonaConsorcioDTO == null) {
            mensaje = String.format("La empresa supervisora con el id: %d no existe en la base de datos",
                    idPersonaConsorcio);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "La empresa supervisora ha sido recuperado");
        respuesta.put("pgimPersonaConsorcioDTO", pgimPersonaConsorcioDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /** ------------REVISAR ESTE METODO---------------- */
    @GetMapping("/obtenerConfiguracionesGeneralesConsorcio/generales/{idPersonaConsorcio}")
    public ResponseEntity<?> obtenerConfiguracionesGeneralesConsorcio(@PathVariable Long idPersonaConsorcio) {

        Map<String, Object> respuesta = new HashMap<>();
        try {

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/existePersonaJuridica/{idPersona}/{noRazonSocial}")
    public ResponseEntity<?> existePersonaJuridica(@PathVariable Long idPersona, @PathVariable String noRazonSocial) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaDTO> lPgimPersonaDTO = null;

        if (idPersona == 0) {
            idPersona = null;
        }

        try {
            lPgimPersonaDTO = this.personaService.existePersonaJuridica(idPersona,
                    noRazonSocial.toUpperCase());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite una persona jurídica");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de una persona jurídica");
        respuesta.put("lPgimPersonaDTO", lPgimPersonaDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/listarPersonalOsi")
    public ResponseEntity<Page<PgimPersonalOsiDTO>> listarPersonalOsi(@RequestBody PgimPersonalOsiDTO pgimPersonalOsiDTO, Pageable paginador) throws Exception {

        Page<PgimPersonalOsiDTO> lPgimPersonalOsiDTO = this.personaService
                .listarPersonalOsi(pgimPersonalOsiDTO, paginador);
        return new ResponseEntity<Page<PgimPersonalOsiDTO>>(lPgimPersonalOsiDTO, HttpStatus.OK);
    }

    @GetMapping("/obtenerPersonalOsigPorId/{idPersonalOsi}")
    public ResponseEntity<?> obtenerPersonalOsigPorId(@PathVariable Long idPersonalOsi) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        PgimPersonalOsiDTO pgimPersonalOsiDTO = null;

        try {
            pgimPersonalOsiDTO = this.personaService.obtenerPersonalOsigPorId(idPersonalOsi);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pgimPersonalOsiDTO == null) {
            mensaje = String.format("El personal de Osinergmin con el id: %d no existe en la base de datos",
                    idPersonalOsi);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El personal de Osinergmin ha sido recuperado");
        respuesta.put("pgimPersonalOsiDTO", pgimPersonalOsiDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @PostMapping("/crearPersonalOsi")
    public ResponseEntity<?> crearPersonalOsi(@Valid @RequestBody PgimPersonalOsiDTO pgimPersonalOsiDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonalOsiDTO pgimPersonalOsiDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear El personal de Osinergmin");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonalOsiDTOCreado = this.personaService.crearPersonalOsi(pgimPersonalOsiDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear El personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El personal de Osinergmin ha sido creada");
        respuesta.put("pgimPersonalOsiDTOCreado", pgimPersonalOsiDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/desactivarPersonalOsi")
    public ResponseEntity<?> desactivarPersonalOsi(@Valid @RequestBody PgimPersonalOsiDTO pgimPersonalOsiDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonalOsi pgimPersonalOsiActual = null;
        PgimPersonalOsiDTO pgimPersonalOsiDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el personal de Osinergmin");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonalOsiActual = this.personaService.getByIdPersonalOsig(pgimPersonalOsiDTO.getIdPersonalOsi());

            if (pgimPersonalOsiActual == null) {
                mensaje = String.format(
                        "El personal de Osinergmin %s que intenta actualizar no existe en la base de datos",
                        pgimPersonalOsiDTO.getIdPersonalOsi());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el personal de Osinergmin a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimPersonalOsiDTOModificada = this.personaService.desactivarPersonalOsi(pgimPersonalOsiDTO,
                    pgimPersonalOsiActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El personal de Osinergmin ha sido modificada");
        respuesta.put("pgimPersonalOsiDTOModificada", pgimPersonalOsiDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarPersonalOsi")
    public ResponseEntity<?> modificarPersonalOsi(@Valid @RequestBody PgimPersonalOsiDTO pgimPersonalOsiDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonalOsi pgimPersonalOsiActual = null;
        PgimPersonalOsiDTO pgimPersonalOsiDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el personal de Osinergmin");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonalOsiActual = this.personaService.getByIdPersonalOsig(pgimPersonalOsiDTO.getIdPersonalOsi());

            if (pgimPersonalOsiActual == null) {
                mensaje = String.format(
                        "El personal de Osinergmin %s que intenta actualizar no existe en la base de datos",
                        pgimPersonalOsiDTO.getIdPersonalOsi());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el personal de Osinergmin a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimPersonalOsiDTOModificada = this.personaService.modificarPersonalOsi(pgimPersonalOsiDTO,
                    pgimPersonalOsiActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El personal de Osinergmin ha sido modificada");
        respuesta.put("pgimPersonalOsiDTOModificada", pgimPersonalOsiDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/existeUsuario/{idPersonalOsi}/{noUsuario}")
    public ResponseEntity<?> existeUsuario(@PathVariable Long idPersonalOsi, @PathVariable String noUsuario) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonalOsiDTO> lPgimPersonalOsiDTO = null;

        if (idPersonalOsi == 0) {
            idPersonalOsi = null;
        }

        try {
            lPgimPersonalOsiDTO = this.personaService.existeUsuario(idPersonalOsi,
            noUsuario.toUpperCase());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar verificar si ya exite " +
            "un usuario del personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de " + 
        "un usuario del personal de Osinergmin");
        respuesta.put("lPgimPersonalOsiDTO", lPgimPersonalOsiDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    //STORY: PGIM-6103: Avatar de usuario/a respectivo para sexo definido en maestro de personas
    @GetMapping("/obtenerUsuario/{noUsuario}")
    public ResponseEntity<ResponseDTO> obtenerUsuario(@PathVariable String noUsuario) {

        List<PgimPersonalOsiDTO> lPgimPersonalOsiDTO = null;
        PgimPersonalOsiDTO pgimPersonalOsiDTO = null;
        String mensaje = null;

        try {
            lPgimPersonalOsiDTO = this.personalOsiRepository.obtenerPersonalOsiPorUsuario(noUsuario.toUpperCase());

            int nroPersonalOsi = lPgimPersonalOsiDTO.size();

            if (nroPersonalOsi == 1) {
                pgimPersonalOsiDTO = lPgimPersonalOsiDTO.get(0);
                String fotoBase64 = this.perfilUserService.obtenerFotoPersona(pgimPersonalOsiDTO.getIdPersona());
                pgimPersonalOsiDTO.setDescFotoBase64(fotoBase64);
            } else if (nroPersonalOsi > 1) {
               
                mensaje = String.format("Se ha encontrado más de un usuario con el mismo nombre ('%s')", noUsuario); // STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.WARNING, mensaje, 0));
            } else if (nroPersonalOsi == 0) {
               
                mensaje = String.format("El usuario no existe o no forma parte del personal de Osinergmin"); // STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.WARNING, mensaje, 0));
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al obtener un usuario del personal de Osinergmin");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje, 0));
            
        } catch (Exception e) {
        	mensaje = "Ocurrió un error al obtener un usuario del personal de Osinergmin: " + e.getMessage();
        	log.error(e.getMessage(), e);
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, pgimPersonalOsiDTO, "Genial, el usuario fue encontrado"));
    }
    
    /**
     * Permite obtener los datos de la persona natural mediante DNI/CE desde el RENIEC orquestado
     * 
     * @param numeroDNIoCE
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    @GetMapping("/procesaConsultarCiudadano/{numeroDNIoCE}")
    public ResponseEntity<?> procesaConsultarCiudadano(@PathVariable String numeroDNIoCE) throws Exception {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();
        PidoBeanOutRO pido = null;

        try {
            pido = this.pidoService.procesaConsultarCiudadano(numeroDNIoCE, this.obtenerAuditoria());
        } catch (Exception e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del número de DNI");
            respuesta.put("error", e.getMessage());
            log.error(e.getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pido == null) {
            mensaje = String.format("No es posible procesar el número de documento ingresado", numeroDNIoCE);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "Se encontró DNI");
        respuesta.put("IntegracionReniec", pido);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * 
     * Permite modificar el flag es principal del consorcio
     * 
     * @param pgimPersonaConsorcioDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PutMapping("/modificarFlPrincipalConsorcio")
    public ResponseEntity<?> modificarFlPrincipalConsorcio(@Valid @RequestBody PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonaConsorcio pgimPersonaConsorcioActual = null;
        PgimPersonaConsorcioDTO pgimPersonaConsorcioDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar la empresa consorcio");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonaConsorcioActual = this.personaService.getByIdPersonaConsorcio(pgimPersonaConsorcioDTO.getIdPersonaConsorcio());

            if (pgimPersonaConsorcioActual == null) {
                mensaje = String.format(
                        "La empresa consorcio %s que intenta actualizar no existe en la base de datos",
                        pgimPersonaConsorcioDTO.getIdPersonaConsorcio());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar la empresa consorcio a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            pgimPersonaConsorcioDTOModificada = this.personaService.modificarFlPrincipalConsorcio(pgimPersonaConsorcioDTO,
                    pgimPersonaConsorcioActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar la empresa consorcio");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "La empresa consorcio ha sido modificada");
        respuesta.put("pgimPersonaConsorcioDTOModificada", pgimPersonaConsorcioDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/listarPersonalOsiCargo/{idPersonalOsi}")
    public ResponseEntity<?> listarPersonalOsiCargo(@PathVariable Long idPersonalOsi) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimPersonalOsiCargoDTO> lPgimPersonalOsiCargoDTO = null;

        try {
            lPgimPersonalOsiCargoDTO = this.personaService.listarPersonalOsiCargo(idPersonalOsi);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar El cargo del personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El cargo del personal de Osinergmin ha sido recuperado");
        respuesta.put("lPgimPersonalOsiCargoDTO", lPgimPersonalOsiCargoDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    //STORY: PGIM-6128, PGIM-7994
    @PostMapping("/crearPersonalOsiCargo")
    public ResponseEntity<?> crearPersonalOsiCargo(@Valid @RequestBody PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream().map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage())).collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear El cargo del personal de Osinergmin");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {

            pgimPersonalOsiCargoDTOCreado = this.personaService.crearPersonalOsiCargo(pgimPersonalOsiCargoDTO, this.obtenerAuditoria());
        
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear El cargo del personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El cargo del personal de Osinergmin ha sido creada");
        respuesta.put("pgimPersonalOsiCargoDTOCreado", pgimPersonalOsiCargoDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarPersonalOsiCargo")
    public ResponseEntity<?> modificarPersonalOsiCargo(@Valid @RequestBody PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO,
            BindingResult resultadoValidacion) throws Exception {

        PgimPersonalOsiCargo pgimPersonalOsiCargoActual = null;
        PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTOModificado = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream().map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage())).collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el cargo del personal de Osinergmin");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            pgimPersonalOsiCargoActual = this.personaService.getPersonalOsiCargoById(pgimPersonalOsiCargoDTO.getIdPersonalOsiCargo());

            if (pgimPersonalOsiCargoActual == null) {
                mensaje = String.format("El cargo del personal de Osinergmin %s que intenta actualizar no existe en la base de datos", pgimPersonalOsiCargoDTO.getIdPersonalOsi());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el cargo del personal de Osinergmin a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {

            pgimPersonalOsiCargoDTOModificado = this.personaService.modificarPersonalOsiCargo(pgimPersonalOsiCargoDTO, pgimPersonalOsiCargoActual, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el cargo del personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El personal de Osinergmin ha sido modificada");
        respuesta.put("pgimPersonalOsiCargoDTOModificado", pgimPersonalOsiCargoDTOModificado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerConfiguracionesParaPersonalOsiCargo/{idPersonalOsi}")
    public ResponseEntity<ResponseDTO> obtenerConfiguracionesParaPersonalOsiCargo(@PathVariable Long idPersonalOsi) throws Exception{

        Map<String, Object> respuesta = new HashMap<>();

        ResponseDTO responseDTO = new ResponseDTO();
        Integer numeroCargoPrincipal = null;
        List<PgimUnidadOrganicaDTO> lPgimUnidadOrganicaDTO = null;
		List<TipoDocumento> lTipoDocumento = new LinkedList<TipoDocumento>();
		String mensajeExcepcionGeneral = "Ocurrió un problema para obtener las configuraciones de antecedentes: ";

        try {

            numeroCargoPrincipal = this.parametroService.cantidadCargoPrincipalPorPersonalOsi(idPersonalOsi);
           lPgimUnidadOrganicaDTO = this.parametroService.listarUnidadOrganica();

           Tiposdocumento tiposdocumento = this.documentoService.listarTipoDocsSiged();
			
			// Si hay un error en el consumo del servicio SIGED, debe enviar el error
			if (!tiposdocumento.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				mensajeExcepcionGeneral += tiposdocumento.getMessage();
				log.error(mensajeExcepcionGeneral);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral));
			}
			
			lTipoDocumento = tiposdocumento.getListTipoDocumento();
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

        respuesta.put("numeroCargoPrincipal", numeroCargoPrincipal);
        respuesta.put("lPgimUnidadOrganicaDTO", lPgimUnidadOrganicaDTO);
		respuesta.put("lTipoDocumento", lTipoDocumento);

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Se encontraron las configuraciones.");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/eliminarPersonalOsiCargo/{idPersonalOsiCargo}")
    public ResponseEntity<?> eliminarPersonalOsiCargo(@PathVariable Long idPersonalOsiCargo) throws Exception {
        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        PgimPersonalOsiCargo pgimPersonalOsiCargoActual = null;

        try {
            pgimPersonalOsiCargoActual = this.personaService.getPersonalOsiCargoById(idPersonalOsiCargo);

            if (pgimPersonalOsiCargoActual == null) {
                mensaje = String.format("El cargo del personal de Osinergmin %s que intenta eliminar no existe en la base de datos", idPersonalOsiCargo);
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el cargo del personal de Osinergmin a eliminar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.personaService.eliminarPersonalOsiCargo(pgimPersonalOsiCargoActual, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar eliminar el cargo del personal de Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
