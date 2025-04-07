package pe.gob.osinergmin.pgim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.PersonaconAuxService;
import pe.gob.osinergmin.pgim.services.RolProcesoService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a la
 * instancia de procesos
 * 
 * @descripción: Instancia de procesos
 *
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 */
@RestController
@Slf4j
@RequestMapping("/instanciaproces")
public class InstanciaProcesController extends BaseController {

    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private RolProcesoService rolProcesoService;

    @Autowired
    private PersonaconAuxService personaconAuxService;

    @Autowired
    private SupervisionService supervisionService;

    @Autowired
    private InstanciaPasoRepository instanciaPasoRepository;

    @Autowired
    private InstanciaProcesRepository instanciaProcesRepository;

    @GetMapping("/filtrar/palabraClave/{palabra}")
    public ResponseEntity<?> listarPorPalabraClave(@PathVariable String palabra) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = null;

        if (palabra.equals("_vacio_")) {
            lPgimInstanciaProcesDTO = new ArrayList<PgimInstanciaProcesDTO>();
            respuesta.put("mensaje", "No se encontraron número de espediente");
            respuesta.put("lPgimInstanciaProcesDTO", lPgimInstanciaProcesDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        try {
            lPgimInstanciaProcesDTO = this.instanciaProcesService.listarPorPalabraClave(palabra);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de número de espediente");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron el número de espediente");
        respuesta.put("lPgimInstanciaProcesDTO", lPgimInstanciaProcesDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite listar los participantes de una instancia de proceso
     * 
     * @param idInstanciaProceso
     * 
     */
    @GetMapping("/listarParticipantes/{id}")
    public ResponseEntity<List<PgimEqpInstanciaProDTO>> obtenerParticipantesInstanciaPro(
            @PathVariable("id") Long idInstanciaProceso) throws Exception {
        return ResponseEntity.ok(this.instanciaProcesService.obtenerParticipantesInstanciaPro(idInstanciaProceso));

    }

    /**
     * Permite obtener las configuraciones para la selección de los participantes del equipo del flujo de trabajo.
     * @param idInstanciaProceso
     * @param idProceso
     * @param idProceso
     * @return
     */
    @GetMapping("/obtenerConfiguracionesSeleccionParticipante/{id}/{idProceso}/{flSoloOsinergmin}")
    public ResponseEntity<?> obtenerConfiguracionesSeleccionParticipante(@PathVariable("id") Long idInstanciaProceso,
            @PathVariable("idProceso") Long idProceso, @PathVariable("flSoloOsinergmin") String flSoloOsinergmin) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimRolProcesoDTO> lPgimRolProcesoDTO = null;
        List<PgimRolProcesoDTO> lPgimRolProcesoDTOSupervisora = null;
        List<PgimRolProcesoDTO> lPgimRolProcesoDTOSupervisoraParaReprogramacion = null;
        PgimSupervisionDTO pgimSupervisionDTO = null;

        try {
            lPgimRolProcesoDTO = this.rolProcesoService.obtenerRolesProcesoPorAmbito("1", idProceso);
            lPgimRolProcesoDTOSupervisora = this.rolProcesoService.obtenerRolesProcesoPorAmbito(flSoloOsinergmin, idProceso);

            // STORY:PGIM-7233: Validación de existencia de rol "coordinador de empresa supervisora" en el equipo
            lPgimRolProcesoDTOSupervisoraParaReprogramacion = this.rolProcesoService.obtenerRolCoordinadorESParaReprogramacion(ConstantesUtil.PROCESO_ROL_COORDINADOR_SUPERVISION);

            if (idProceso == ConstantesUtil.PARAM_PROCESO_SUPERVISION) {
                pgimSupervisionDTO = supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
            }

        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar la consulta de las configuraciones para la selección de participantes");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimRolProcesoDTO", lPgimRolProcesoDTO);
        respuesta.put("lPgimRolProcesoDTOSupervisora", lPgimRolProcesoDTOSupervisora);
        respuesta.put("lPgimRolProcesoDTOSupervisoraParaReprogramacion", lPgimRolProcesoDTOSupervisoraParaReprogramacion);
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite listar el personal de una empresa supervisora, sin considerar las
     * personas selecciondas, con un rol, en la supervisión
     * 
     * @param idInstanciaProceso
     * @param idRolProceso
     */
    @GetMapping("/listarPersonalContratoSinDuplicadosXrol/{idInstanciaProceso}/{idRolProceso}")
    public ResponseEntity<List<PgimPersonaconAuxDTO>> listarPersonalContratoSinDuplicadosXrol(
            @PathVariable("idInstanciaProceso") Long idInstanciaProceso,
            @PathVariable("idRolProceso") Long idRolProceso) {
        return ResponseEntity.ok(
                this.personaconAuxService.listarPersonalContratoSinDuplicadosXrol(idInstanciaProceso, idRolProceso));
    }

    /**
     * Permite obtener la lista de personas del Osinergmin que coincidan con los
     * criterios la palabra filtro, y que no se dupliquen con un rol, en el equipo
     * de la instancia de un proceso
     * 
     * @param palabra            Palabra clave empleada para realizar la búsqueda
     *                           por aproximación.
     * @param idInstanciaProceso
     * @param idRolProceso
     * @return
     */
    @GetMapping("/listarPersonalOsiSinDuplicadosXrol/{palabra}/{idInstanciaProceso}/{idRolProceso}")
    public ResponseEntity<?> listarPersonalOsiSinDuplicadosXrol(@PathVariable String palabra,
            @PathVariable Long idInstanciaProceso, @PathVariable Long idRolProceso) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimPersonaosiAuxDTO> lPgimPersonaosiAuxDTO = null;

        if (palabra.equals("_vacio_") || idInstanciaProceso == null || idInstanciaProceso == 0 || idRolProceso == null
                || idRolProceso == 0) {
            lPgimPersonaosiAuxDTO = new ArrayList<PgimPersonaosiAuxDTO>();
            respuesta.put("mensaje", "No se encontraron personas del Osinergmin");
            respuesta.put("lPgimPersonaosiAuxDTO", lPgimPersonaosiAuxDTO);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

        if (palabra.equals("todos")) {
            palabra = null;
        }

        try {
            lPgimPersonaosiAuxDTO = this.instanciaProcesService.listarPersonalOsiSinDuplicadosXrol(palabra,
                    idInstanciaProceso, idRolProceso);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la búsqueda de las personas del Osinergmin");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se encontraron las personas del Osinergmin con la palabra clave");
        respuesta.put("lPgimPersonaosiAuxDTO", lPgimPersonaosiAuxDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite seleccionar el personal de Osinergmin o de la empresa supervisora,
     * como parte de los participantes de la instancia de proceso
     * 
     * @param PgimEqpInstanciaProDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PostMapping("/seleccionarEquipoInstancia")
    public ResponseEntity<?> seleccionarEquipoInstancia(
            @Valid @RequestBody PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO, BindingResult resultadoValidacion)
            throws Exception {

        // PgimEqpInstanciaProDTO pgimEqpInstanciaProDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para seleccionar el participante");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            instanciaProcesService.seleccionarEquipoInstancia(pgimEqpInstanciaProDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar seleccionar el participante");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El equipo ha sido seleccionado.");
        // respuesta.put("pgimEqpInstanciaProDTO", pgimEqpInstanciaProDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Permite deseleccionar el personal de Osinergmin o de la empresa supervisora,
     * como parte de los participantes de la instancia de proceso
     * 
     * @param idEquipoInstanciaPro
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @DeleteMapping("/deseleccionarEquipoInstancia/{idEquipoInstanciaPro}")
    public ResponseEntity<ResponseDTO> deseleccionarEquipoInstancia(@PathVariable Long idEquipoInstanciaPro)
            throws Exception {
        ResponseDTO responseDTO = null;
        String mensaje;

        PgimEqpInstanciaPro pgimEqpInstanciaProActual = null;

        try {
            pgimEqpInstanciaProActual = this.instanciaProcesService.getByidEquipoInstanciaPro(idEquipoInstanciaPro);

            if (pgimEqpInstanciaProActual == null) {
                mensaje = String.format("El participante que intenta retirar de la lista no existe en la base de datos",
                        idEquipoInstanciaPro);
                responseDTO = new ResponseDTO("validacion", mensaje);

                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar recuperar el partipante a retirar. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);
            log.error(e.getMostSpecificCause().getMessage(), e);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            PgimInstanciaProces pgimInstanciaProces = pgimEqpInstanciaProActual.getPgimInstanciaProces();
            final List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTO = this.instanciaPasoRepository
                    .obtenerPasosActualesPorInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

            for (PgimInstanciaPasoDTO pgimInstanciaPasoDTO : lPgimInstanciaPasoDTO) {
                if (idEquipoInstanciaPro.equals(pgimInstanciaPasoDTO.getIdPersonaEqpDestino())) {
                    mensaje = String.format(
                            "No se puede retirar a la persona participante con el rol dado, debido a que ella esta asignada al menos a un paso activo del flujo de trabajo; se sugiere reasignar la tarea de esta persona y volver a intentar");
    
                    responseDTO = new ResponseDTO("validacion", mensaje);
    
                    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
                } 
            }

        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar validar el retiro de la persona partipante. Mensaje:",
                    e.getMostSpecificCause().getMessage());

            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        try {
            this.instanciaProcesService.deseleccionarEquipoInstancia(pgimEqpInstanciaProActual,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format("Ocurrió un error al intentar retirar el partipante a retirar. Mensaje:",
                    e.getMostSpecificCause().getMessage());
                    log.error(e.getMostSpecificCause().getMessage(), e);
            responseDTO = new ResponseDTO("error", mensaje);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", "La persona del equipo ha sido retirada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Permite modificar el campo responsable del personal de Osinermig en la
     * entidad EquipoInstanciaPro.
     * 
     * @param PgimEqpInstanciaProDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/modificarEquipoInstancia")
    public ResponseEntity<?> modificarEquipoInstancia(@Valid @RequestBody PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO,
            BindingResult resultadoValidacion) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        String mensaje;

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el participante.");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        PgimEqpInstanciaPro pgimEqpInstanciaProActual = null;
        try {
            pgimEqpInstanciaProActual = this.instanciaProcesService
                    .getByidEquipoInstanciaPro(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());

            if (pgimEqpInstanciaProActual == null) {
                mensaje = String.format(
                        "El participante que intenta modificar de la lista no existe en la base de datos",
                        pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el partipante a modificar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            this.instanciaProcesService.modificarEquipoInstancia(pgimEqpInstanciaProActual, pgimEqpInstanciaProDTO,
                    this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar modificar el participante");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "ok");

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

    }

    @GetMapping("/existeNuExpediente/{idInstanciaProceso}/{descNuExpedienteSiged}")
    public ResponseEntity<?> existeNuExpediente(@PathVariable Long idInstanciaProceso,
            @PathVariable String descNuExpedienteSiged) {

        Map<String, Object> respuesta = new HashMap<>();
        List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = null;

        if (idInstanciaProceso == 0) {
            idInstanciaProceso = null;
        }

        try {
            lPgimInstanciaProcesDTO = this.instanciaProcesService.existeNuExpediente(idInstanciaProceso,
                    descNuExpedienteSiged);
        } catch (DataAccessException e) {
            respuesta.put("mensaje",
                    "Ocurrió un error al realizar verificar si ya exite un número de expediente Siged");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Si fue posible determinar la existencia de un número de expediente Siged");
        respuesta.put("lPgimInstanciaProcesDTO", lPgimInstanciaProcesDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerParticipantesRolSupervisor/{id}")
    public ResponseEntity<List<PgimEqpInstanciaProDTO>> obtenerParticipantesRolSupervisor(
            @PathVariable("id") Long idInstanciaProceso) {
        return ResponseEntity.ok(this.instanciaProcesService.obtenerParticipantesRolSupervisor(idInstanciaProceso));
    }

    /**
     * Permite obtener el registro de Supervisión a partir del ID de la Instancia de
     * proceso
     * 
     * @return
     */
    @GetMapping("/obtenerSupervisionPorIdInstanciaProceso/{idInstanciaProceso}")
    public ResponseEntity<?> obtenerSupervisionPorIdInstanciaProceso(
            @PathVariable("idInstanciaProceso") Long idInstanciaProceso) {

        Map<String, Object> respuesta = new HashMap<>();
        PgimSupervisionDTO pgimSupervisionDTO = null;
        List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = null;
        try {
            pgimSupervisionDTO = supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

            List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOContraRol = this.instanciaProcesService
                    .listarPersonalAsignableContraConRol(idInstanciaProceso, ConstantesUtil.PARAM_id_rol_SUPERVISOR,
                            "");

            List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOOsiRol = this.instanciaProcesService
                    .listarPersonalAsignableOsiConRol(idInstanciaProceso, ConstantesUtil.PARAM_id_rol_SUPERVISOR, "");

            (lPgimEqpInstanciaProDTO = new ArrayList<PgimEqpInstanciaProDTO>(lPgimEqpInstanciaProDTOContraRol))
                    .addAll(lPgimEqpInstanciaProDTOOsiRol);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de la supervisión.");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvo la supervisión");
        respuesta.put("pgimSupervisionDTO", pgimSupervisionDTO);
        respuesta.put("lPgimEqpInstanciaProDTO", lPgimEqpInstanciaProDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/obtenerInstanciaProceso/{idInstanciaProceso}")
    public ResponseEntity<?> obtenerInstanciaProceso(@PathVariable Long idInstanciaProceso) {

        Map<String, Object> respuesta = new HashMap<>();

        PgimInstanciaProcesDTO pgimInstanciaProcesDTO = null;

        try {
            pgimInstanciaProcesDTO = this.instanciaProcesRepository.obtenerInstanciaProceso(idInstanciaProceso);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de obtener el id de la instancia del proceso");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvo el id de la instancia del proceso");
        respuesta.put("pgimInstanciaProcesDTO", pgimInstanciaProcesDTO);
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}