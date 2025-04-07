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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfracciontop15AuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespMesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxtitularAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxumAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionContra;
import pe.gob.osinergmin.pgim.services.EstratoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InfraccionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las infracciones
 * 
 * @descripción: métodos relacionados a las infracciones
 *
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 30/09/2020
 */
@RestController
@Slf4j
@RequestMapping("/infraccion")
public class InfraccionController extends BaseController {

    @Autowired
    private InfraccionService infraccionService;

    @Autowired
    private EstratoService estratoService;

    @Autowired
    private FlujoTrabajoService flujoTrabajoService;

    @Autowired
    private ParametroService parametroService;

    /**
     * Permite obtener la lista preparada de cantidad de infracciones por titular
     * minero y año
     * 
     * @param filtroPgimInfraccionxtitularAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionxtitular")
    public ResponseEntity<List<PgimInfraccionxtitularAuxDTO>> listarInfraccionxtitular(
            @RequestBody PgimInfraccionxtitularAuxDTO filtroPgimInfraccionxtitularAuxDTO) throws Exception {

        List<PgimInfraccionxtitularAuxDTO> lPgimInfraccionxtitularAuxDTO = this.infraccionService
                .listarInfraccionxtitular(filtroPgimInfraccionxtitularAuxDTO);

        return new ResponseEntity<List<PgimInfraccionxtitularAuxDTO>>(lPgimInfraccionxtitularAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de cantidad de infracciones por titular
     * minero y año paginada
     * 
     * @param filtroPgimInfraccionxtitularAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionxtitularPaginado")
    public ResponseEntity<Page<PgimInfraccionxtitularAuxDTO>> listarInfraccionxtitularPaginado(
            @RequestBody PgimInfraccionxtitularAuxDTO filtroPgimInfraccionxtitularAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimInfraccionxtitularAuxDTO> lPgimInfraccionxtitularAuxDTO = this.infraccionService
                .listarInfraccionxtitularPaginado(filtroPgimInfraccionxtitularAuxDTO, paginador);

        return new ResponseEntity<Page<PgimInfraccionxtitularAuxDTO>>(lPgimInfraccionxtitularAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener las listas de Estratos usadas en el frontend
     * 
     * @return
     * @throws Exception
     */
    @GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimEstratoDTO> lPgimEstratoDTO = null;
        List<PgimEspecialidadDTO> lPgimEspecialidadDTOEspecialidad = null;
        PgimPersonaDTO pgimPersonaDTO = null;
        List<PgimValorParametroDTO> lPgimDivisionDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoUM = null;

        try {
            lPgimEstratoDTO = this.estratoService.listarEstrato();

            lPgimEspecialidadDTOEspecialidad = this.parametroService
                    .filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);
            pgimPersonaDTO = this.flujoTrabajoService
                    .obtenerPersonaPorNombreUsuario(this.obtenerAuditoria().getUsername());

            lPgimDivisionDTO = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

            lPgimValorParamDTOTipoUM = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_UNIDAD_MINERA);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEstratoDTO", lPgimEstratoDTO);

        respuesta.put("lPgimEspecialidadDTOEspecialidad", lPgimEspecialidadDTOEspecialidad);
        respuesta.put("pgimPersonaDTO", pgimPersonaDTO);
        respuesta.put("usuarioAutenticado", this.obtenerAuditoria().getUsername());
        respuesta.put("lPgimDivisionDTO", lPgimDivisionDTO);
        respuesta.put("lPgimValorParamDTOTipoUM", lPgimValorParamDTOTipoUM);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones por especialidad y año
     * usado en reporte correspondiente
     * 
     * @param filtroPgimInfraccionxespAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteInfraccionxesp")
    public ResponseEntity<List<PgimInfraccionxespAuxDTO>> listarReporteInfraccionxesp(
            @RequestBody PgimInfraccionxespAuxDTO filtroPgimInfraccionxespAuxDTO) throws Exception {

        List<PgimInfraccionxespAuxDTO> lPgimInfraccionxespAuxDTO = this.infraccionService
                .listarReporteInfraccionxesp(filtroPgimInfraccionxespAuxDTO);

        return new ResponseEntity<List<PgimInfraccionxespAuxDTO>>(lPgimInfraccionxespAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones por especialidad y año
     * usado en reporte correspondiente paginada
     * 
     * @param filtroPgimInfraccionxespAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteInfraccionxespPaginado")
    public ResponseEntity<Page<PgimInfraccionxespAuxDTO>> listarReporteInfraccionxespPaginado(
            @RequestBody PgimInfraccionxespAuxDTO filtroPgimInfraccionxespAuxDTO, Pageable paginador) throws Exception {

        Page<PgimInfraccionxespAuxDTO> lPgimInfraccionxespAuxDTO = this.infraccionService
                .listarReporteInfraccionxespPaginado(filtroPgimInfraccionxespAuxDTO, paginador);

        return new ResponseEntity<Page<PgimInfraccionxespAuxDTO>>(lPgimInfraccionxespAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones detalladas usado en
     * reporte correspondiente paginada
     * 
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfracciones")
    public ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>> listarReporteInfraccionesPaginado(
            @RequestBody PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimDetaInfraccionesAuxDTO> lPgimDetaInfraccionesAuxDTO = this.infraccionService
                .listarReporteInfraccionesPaginado(filtroPgimDetaInfraccionesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>>(lPgimDetaInfraccionesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones detalladas por agente
     * supervisado usado en reporte correspondiente paginada
     * 
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionesAS")
    public ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>> listarReporteInfraccionesASPaginado(
            @RequestBody PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimDetaInfraccionesAuxDTO> lPgimDetaInfraccionesAuxDTO = this.infraccionService
                .listarReporteInfraccionesASPaginado(filtroPgimDetaInfraccionesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>>(lPgimDetaInfraccionesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones detalladas por unidad
     * minera usado en reporte correspondiente paginada
     * 
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionesUM")
    public ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>> listarReporteInfraccionesUMPaginado(
            @RequestBody PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimDetaInfraccionesAuxDTO> lPgimDetaInfraccionesAuxDTO = this.infraccionService
                .listarReporteInfraccionesUMPaginado(filtroPgimDetaInfraccionesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>>(lPgimDetaInfraccionesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones detalladas por division
     * supervisora usado en reporte correspondiente paginada
     * 
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionesDS")
    public ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>> listarReporteInfraccionesDSPaginado(
            @RequestBody PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimDetaInfraccionesAuxDTO> lPgimDetaInfraccionesAuxDTO = this.infraccionService
                .listarReporteInfraccionesDSPaginado(filtroPgimDetaInfraccionesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>>(lPgimDetaInfraccionesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones detalladas por
     * especialidad usado en reporte correspondiente paginada
     * 
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionesEspec")
    public ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>> listarReporteInfraccionesEspecPaginado(
            @RequestBody PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimDetaInfraccionesAuxDTO> lPgimDetaInfraccionesAuxDTO = this.infraccionService
                .listarReporteInfraccionesEspecPaginado(filtroPgimDetaInfraccionesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>>(lPgimDetaInfraccionesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones mas recurrentes (top15)
     * usado en reporte correspondiente paginada
     * 
     * @param filtroPgimExppasdsuAnioAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarInfraccionesTop15")
    public ResponseEntity<Page<PgimInfracciontop15AuxDTO>> listarReporteInfraccionesTop15(
            @RequestBody PgimInfracciontop15AuxDTO filtroPgimInfracciontop15AuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimInfracciontop15AuxDTO> lPgimInfracciontop15AuxDTO = this.infraccionService
                .listarReporteInfraccionesTop15(filtroPgimInfracciontop15AuxDTO, paginador);

        return new ResponseEntity<Page<PgimInfracciontop15AuxDTO>>(lPgimInfracciontop15AuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones por especialidad y mes
     * usado en reporte correspondiente
     * 
     * @param filtroPgimInfraccionxespMesAuxDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteInfraccionesEspecMes")
    public ResponseEntity<List<PgimInfraccionxespMesAuxDTO>> listarReporteInfraccionesEspecMes(
            @RequestBody PgimInfraccionxespMesAuxDTO filtroPgimInfraccionxespMesAuxDTO) throws Exception {

        List<PgimInfraccionxespMesAuxDTO> lPgimInfraccionxespMesAuxDTO = this.infraccionService
                .listarReporteInfraccionxespMes(filtroPgimInfraccionxespMesAuxDTO);

        return new ResponseEntity<List<PgimInfraccionxespMesAuxDTO>>(lPgimInfraccionxespMesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones por especialidad y mes
     * usado en reporte correspondiente paginada
     * 
     * @param filtroPgimInfraccionxespMesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteInfraccionxespMesPaginado")
    public ResponseEntity<Page<PgimInfraccionxespMesAuxDTO>> listarReporteInfraccionxespMesPaginado(
            @RequestBody PgimInfraccionxespMesAuxDTO filtroPgimInfraccionxespMesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimInfraccionxespMesAuxDTO> lPgimInfraccionxespMesAuxDTO = this.infraccionService
                .listarReporteInfraccionxespMesPaginado(filtroPgimInfraccionxespMesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimInfraccionxespMesAuxDTO>>(lPgimInfraccionxespMesAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones por um y año usado en
     * reporte correspondiente paginada
     * 
     * @param filtroPgimInfraccionxumAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarReporteInfraccionesUmAnioPaginado")
    public ResponseEntity<Page<PgimInfraccionxumAuxDTO>> listarReporteInfraccionesUmAnioPaginado(
            @RequestBody PgimInfraccionxumAuxDTO filtroPgimInfraccionxumAuxDTO, Pageable paginador) throws Exception {

        Page<PgimInfraccionxumAuxDTO> lPgimInfraccionxumAuxDTO = this.infraccionService
                .listarReporteInfraccionesUmAnioPaginado(filtroPgimInfraccionxumAuxDTO, paginador);

        return new ResponseEntity<Page<PgimInfraccionxumAuxDTO>>(lPgimInfraccionxumAuxDTO, HttpStatus.OK);
    }

    /**
     * Permite obtener la lista preparada de infracciones detalladas usado en
     * reporte correspondiente paginada
     * 
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    @PostMapping("/listarHistorialInfraccion")
    public ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>> listarHistorialInfraccion(
            @RequestBody PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
            throws Exception {

        Page<PgimDetaInfraccionesAuxDTO> lPgimDetaInfraccionesAuxDTO = this.infraccionService
                .listarReporteInfraccionesPaginado(filtroPgimDetaInfraccionesAuxDTO, paginador);

        return new ResponseEntity<Page<PgimDetaInfraccionesAuxDTO>>(lPgimDetaInfraccionesAuxDTO, HttpStatus.OK);
    }

    @GetMapping("/listarHistorialInfraccion/{idInfraccion}")
    public ResponseEntity<ResponseDTO> listarHistorialInfraccion(@PathVariable final Long idInfraccion) {
        
        List<PgimInfraccionDTO> lPgimInfraccionDTO = null;
        ResponseDTO responseDTO = null;

        try {
            lPgimInfraccionDTO = this.infraccionService.listarHistorialInfraccion(idInfraccion);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            throw new PgimException("error", "Ocurrió un error al intentar recuperar el historial de la infracción");
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

        responseDTO = new ResponseDTO("success", lPgimInfraccionDTO, "El paso del proceso ha sido asignado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/obtenerInfraccionPorId/{idInfraccion}")
    public ResponseEntity<ResponseDTO> obtenerInfraccionPorId(@PathVariable final Long idInfraccion) {
        
        PgimInfraccionDTO pgimInfraccionDTO = null;
        ResponseDTO responseDTO = null;

        try {
                pgimInfraccionDTO = this.infraccionService.obtenerInfraccionPorId(idInfraccion);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO("error", "Ocurrió un error al intentar recuperar la infracción");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO("warning", e.getMensaje());
            
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO("error", "Ocurrió un error al intentar recuperar la infracción");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO("success", pgimInfraccionDTO, "La infracción ha sido recuperada");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * Permite listar responsable(s) de una infracción 
     * 
     * @param idInfraccion
     * @return
     */
    @GetMapping("/listarInfraccionResponsables/{idInfraccion}")
    public ResponseEntity<ResponseDTO> listarInfraccionResponsables(@PathVariable final Long idInfraccion) {
        
        List<PgimInfraccionContraDTO> lPgimInfraccionContraDTO = null;
        ResponseDTO responseDTO = null;

        try {
            lPgimInfraccionContraDTO = this.infraccionService.listarInfraccionResponsables(idInfraccion);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);
            throw new PgimException(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar la lista de responsables de la infracción");
        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimInfraccionContraDTO, "Se recuperó la lista de responsables de la infracción");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * Permite crear contratista(s) responsable(s) de una infracción 
     * 
     * @param pgimInfraccionDTO
     * @param resultadoValidacion
     * @return
     * @throws Exception
     */
    @PostMapping("/crearInfraccionContratistas")
	public ResponseEntity<ResponseDTO> crearInfraccionContratistas(@Valid @RequestBody PgimInfraccionDTO pgimInfraccionDTO,
			BindingResult resultadoValidacion) throws Exception {

		PgimInfraccionDTO pgimInfraccionDTOCU = null;
		
		ResponseDTO responseDTO = null;		
		Map<String, Object> respuesta = new HashMap<>();
		String mensaje = "";

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			mensaje = "Se han encontrado inconsistencias para crear los contratistas responsables de la infracción";
			log.error(mensaje);
			log.error(errores.toString());
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, errores.toString());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		try {
			pgimInfraccionDTOCU = this.infraccionService.crearLstInfraccionContratistas(pgimInfraccionDTO.getIdInfraccion(),
					pgimInfraccionDTO.getLstPgimInfraccionContraDTO(), pgimInfraccionDTO.getDescIdInstanciaPasoActual(), this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al intentar crear los contratistas responsables de la infracción: " + e.getMessage();
			log.error(mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            
		 } catch (final PgimException e) {	            
            log.error(e.getMensaje(), e);
            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		} catch (Exception e) {
			mensaje = "Ocurrió un error al intentar crear los contratistas responsables de la infracción: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		respuesta.put("pgimInfraccionDTO", pgimInfraccionDTOCU);
		
		if(pgimInfraccionDTO.getLstPgimInfraccionContraDTO().size() == 1) {
			mensaje = "Genial, el contratista responsable de la infracción fue registrado";
			
		}else if(pgimInfraccionDTO.getLstPgimInfraccionContraDTO().size() > 1) {
			mensaje = "Genial, los contratistas responsables de la infracción fueron registrados";
		}
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}
    
    /**
     * Permite eliminar un contratista responsable de infracción
     * 
     * @param pgimInfraccionContraDTO
     * @return
     * @throws Exception
     */
    @PutMapping("/eliminarInfraccionContratista")
	public ResponseEntity<?> eliminarInfraccionContratista(
		@RequestBody PgimInfraccionContraDTO pgimInfraccionContraDTO) throws Exception {

        ResponseDTO responseDTO = null;        
        PgimInfraccionContra pgimInfraccionContraActual = null;
        PgimInfraccionDTO pgimInfraccionDTOCU = null;
        String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		
        try {
            pgimInfraccionContraActual = this.infraccionService.getByIdInfraccionContra(pgimInfraccionContraDTO.getIdInfraccionContra());
            
            if (pgimInfraccionContraActual == null) {
                mensaje = String.format("El responsable con id %s que intenta eliminar no existe en la base de datos",
                pgimInfraccionContraDTO.getIdInfraccionContra());

                responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
            }

		}  catch (DataAccessException e) {
            mensaje = "Ocurrió un error intentar eliminar el responsable de la infracción: " + e.getMessage();
            log.error(e.getMostSpecificCause().getMessage(), e);
            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

		try {
			pgimInfraccionDTOCU = this.infraccionService.eliminarInfraccionContratista(pgimInfraccionContraActual, 
					pgimInfraccionContraDTO.getDescIdInstanciaPasoActual(), this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensaje = "Ocurrió un error al intentar eliminar el responsable de la infracción: " + e.getMessage();
			log.error(mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		} catch (Exception e) {
			mensaje = "Ocurrió un error al intentar eliminar el responsable de la infracción: " +e.getMessage();
			log.error(mensaje);
			log.error(e.getMessage(), e);
			
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		respuesta.put("pgimInfraccionDTO", pgimInfraccionDTOCU);
		
		mensaje = "El responsable de la infracción ha sido eliminado";          
        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensaje);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

}
