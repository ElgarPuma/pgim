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
import pe.gob.osinergmin.pgim.dtos.ItemMedicionIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedicionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimMedicion;
import pe.gob.osinergmin.pgim.services.MedicionService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * mediciones de los indicadores de la PGIM.
 * 
 * @descripción: Mediciones
 *
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 23/10/2023
 */

@RestController
@Slf4j
@RequestMapping("/mediciones")
public class MedicionController extends BaseController {

  @Autowired
  private MedicionService medicionService;

  @Autowired
  private ParametroService parametroService;

  /**
   * Permite obtener el listado de las mediciones del flujos de trabajo
   * 
   * @param pgimMedicionDTOFiltro
   * @param paginador
   * @return
   */
  @PreAuthorize("hasAnyAuthority('ind002_AC')")
  @PostMapping("/listarMediciones")
  public ResponseEntity<Page<PgimMedicionDTO>> listarMediciones(@RequestBody PgimMedicionDTO pgimMedicionDTOFiltro,
      final Pageable paginador) {
        
    final Page<PgimMedicionDTO> pPgimMedicionDTO = this.medicionService.listarMediciones(pgimMedicionDTOFiltro, paginador); 
    return new ResponseEntity<Page<PgimMedicionDTO>>(pPgimMedicionDTO, HttpStatus.OK);

  }

  /**
   * Permite crear una medición del flujo de trabajo
   * 
   * @param pgimMedicionDTO
   * @param resultadoValidacion
   * @return
   * @throws Exception
   */
  @PostMapping("/crearMedicion")
  public ResponseEntity<ResponseDTO> crearMedicion(@Valid @RequestBody PgimMedicionDTO pgimMedicionDTO,
      BindingResult resultadoValidacion) throws Exception {

    PgimMedicionDTO pgimMedicionDTOCU = null;

    ResponseDTO responseDTO = null;
    String mensaje = "";

    if (resultadoValidacion.hasErrors()) {
      List<String> errores = null;

      errores = resultadoValidacion.getFieldErrors().stream()
          .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
          .collect(Collectors.toList());

      mensaje = "Se han encontrado inconsistencias para crear la medición de flujos de trabajo";
      log.error(mensaje);
      log.error(errores.toString());

      responseDTO = new ResponseDTO(TipoResultado.ERROR, errores.toString());
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    try {
      pgimMedicionDTOCU = this.medicionService.crearMedicion(pgimMedicionDTO, this.obtenerAuditoria());

    } catch (DataAccessException e) {
      mensaje = "Ocurrió un error al intentar crear la medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMostSpecificCause().getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    } catch (final PgimException e) {
      log.error(e.getMensaje(), e);
      responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    } catch (Exception e) {
      mensaje = "Ocurrió un error al intentar crear el medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    mensaje = "Medición de flujos de trabajo ha sido creada";

    responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimMedicionDTOCU, mensaje);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

  }

  /**
   * Permite hacer modificaciones en la medición de flujos de trabajo
   * 
   * @param pgimMedicionDTO
   * @param resultadoValidacion
   * @return
   * @throws Exception
   */
  @PutMapping("/modificarMedicion")
  public ResponseEntity<ResponseDTO> modificarMedicion(@Valid @RequestBody PgimMedicionDTO pgimMedicionDTO,
      BindingResult resultadoValidacion) throws Exception {

    PgimMedicion pgimMedicionActual = null;
    PgimMedicionDTO pgimMedicionDTOModificado = null;
    ResponseDTO responseDTO = null;
    String mensajeExcepcion;
    Map<String, Object> respuesta = new HashMap<>();

    if (resultadoValidacion.hasErrors()) {
      List<String> errores = null;

      errores = resultadoValidacion.getFieldErrors().stream()
          .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
          .collect(Collectors.toList());

      mensajeExcepcion = "Se han encontrado inconsistencias para modificar la medición de flujo de trabajo: "
          + errores.toString();

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    try {
      pgimMedicionActual = this.medicionService.getByIdMedicion(pgimMedicionDTO.getIdMedicion());

      if (pgimMedicionActual == null) {
        mensajeExcepcion = String.format(
            "La medición de flujo de trabajo %s que intenta actualizar no existe en la base de datos",
            pgimMedicionDTO.getIdIndicador());

        responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

    } catch (DataAccessException e) {
      log.error(e.getMostSpecificCause().getMessage(), e);

      mensajeExcepcion = "Ocurrió un error al intentar recuperar la medición  de flujo de trabajo a actualizar: "
          + e.getMostSpecificCause().getMessage();
      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    } catch (Exception e) {
      String mensaje = "Ocurrió un error al intentar eliminar la medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    try {
      pgimMedicionDTOModificado = this.medicionService.modificarMedicion(pgimMedicionDTO, pgimMedicionActual,
          this.obtenerAuditoria());
    } catch (DataAccessException e) {
      log.error(e.getMostSpecificCause().getMessage(), e);

      mensajeExcepcion = "Se han encontrado inconsistencias para modificar la medición de flujo de trabajo: "
          + e.getMostSpecificCause().getMessage();
      respuesta.put("mensaje", "Ocurrió un error al intentar modificar la persona accidentada");
      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    } catch (Exception e) {
      String mensaje = "Ocurrió un error al intentar eliminar la medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimMedicionDTOModificado,
        "La medición de flujo de trabajo ha sido modificada");
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  /**
   * Permite cambiar el estado de publicación de una medición de flujos de trabajo
   * 
   * @param pgimMedicionDTO
   * @param resultadoValidacion
   * @return
   * @throws Exception
   */
  @PutMapping("/publicarMedicion")
  public ResponseEntity<ResponseDTO> publicarMedicion(@RequestBody PgimMedicionDTO pgimMedicionDTO) throws Exception {

    PgimMedicion pgimMedicionActual = null;
    PgimMedicionDTO pgimMedicionDTOModificado = null;
    ResponseDTO responseDTO = null;
    String mensajeExcepcion;
    Map<String, Object> respuesta = new HashMap<>();

    try {
      pgimMedicionActual = this.medicionService.getByIdMedicion(pgimMedicionDTO.getIdMedicion());

      if (pgimMedicionActual == null) {
        mensajeExcepcion = String.format(
            "La medición de flujo de trabajo %s que intenta cambiar el estado de publicación, no existe en la base de datos",
            pgimMedicionDTO.getIdIndicador());

        responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

    } catch (DataAccessException e) {
      log.error(e.getMostSpecificCause().getMessage(), e);

      mensajeExcepcion = "Ocurrió un error al intentar recuperar la medición  de flujo de trabajo a actualizar: "
          + e.getMostSpecificCause().getMessage();
      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    } catch (Exception e) {
      String mensaje = "Ocurrió un error al intentar eliminar la medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    try {
      pgimMedicionDTOModificado = this.medicionService.publicarMedicion(pgimMedicionDTO, pgimMedicionActual,
          this.obtenerAuditoria());
    } catch (DataAccessException e) {
      log.error(e.getMostSpecificCause().getMessage(), e);

      mensajeExcepcion = "Se han encontrado inconsistencias para cambiar el estado de publicación de la medición de flujo de trabajo: "
          + e.getMostSpecificCause().getMessage();
      respuesta.put("mensaje", "Ocurrió un error al intentar cambiar el estado de publicación de la persona accidentada");
      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    } catch (Exception e) {
      String mensaje = "Ocurrió un error al intentar cambiar el estado de publicación de la medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    String mensaje = "La medición de flujo de trabajo ha sido despublicada";
    if(pgimMedicionDTOModificado.getEsPublicacion().equals("1")){
      mensaje = "La medición de flujo de trabajo ha sido publicada";
    }

    responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimMedicionDTOModificado,mensaje);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  /**
   * Permite eliminar logicamente un indicador de flujos de trabajo
   * 
   * @param indicador
   * @return
   * @throws Exception
   */
  @DeleteMapping("/eliminarMedicion/{indicador}")
  public ResponseEntity<ResponseDTO> eliminarMedicion(
      @PathVariable Long indicador) throws Exception {

    ResponseDTO responseDTO = null;
    PgimMedicion pgimIndicadorActual = null;
    PgimMedicionDTO pgimIndicadorDTOCU = null;
    String mensaje;

    try {
      pgimIndicadorActual = medicionService.getByIdMedicion(indicador);

      if (pgimIndicadorActual == null) {
        mensaje = String.format(
            "La medición  de flujo de trabajo con id %s que intenta eliminar no existe en la base de datos",
            indicador);

        responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

    } catch (DataAccessException e) {
      mensaje = "Ocurrió un error intentar eliminar la medición de flujo de trabajo: " + e.getMessage();
      log.error(e.getMostSpecificCause().getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    } catch (final Exception e) {
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR,
          "Ocurrió un error al intentar eliminar la medición de flujos de trabajo");

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    try {
      this.medicionService.eliminarMedicion(pgimIndicadorActual,this.obtenerAuditoria());

    } catch (DataAccessException e) {
      mensaje = "Ocurrió un error al intentar eliminarla medición de flujos de trabajo: " + e.getMessage();
      log.error(mensaje);
      log.error(e.getMostSpecificCause().getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    } catch (Exception e) {
      mensaje = "Ocurrió un error al intentar eliminarla medición de flujos de trabajo: " +e.getMessage();
      log.error(mensaje);
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    mensaje = "El indicador de flujos de trabajo ha sido eliminado";
    responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimIndicadorDTOCU, mensaje);

    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }

  /**
   * Permite obtenerla medición del flujo de trabajo por su identificador interno
   * 
   * @param idMedicion
   * @return
   */
  @GetMapping("/obtenerMedicionPorId/{indicador}")
  public ResponseEntity<ResponseDTO> obtenerMedicionPorId(@PathVariable final Long indicador) {

    PgimMedicionDTO pgimMedicionDTO = null;
    ResponseDTO responseDTO = null;

    try {
      pgimMedicionDTO = this.medicionService.obtenerMedicionPorId(indicador);
      

    } catch (final DataAccessException e) {
      log.error(e.getMostSpecificCause().getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR,
          "Ocurrió un error al intentar recuperar la medición de flujos de trabajo");

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    } catch (final PgimException e) {
      // Excepcion controlada que deberá ser manejada por el frontend
      log.error(e.getMensaje(), e);

      responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    } catch (final Exception e) {
      log.error(e.getMessage(), e);

      responseDTO = new ResponseDTO(TipoResultado.ERROR,
          "Ocurrió un error al intentar recuperar la medición de flujos de trabajo");

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimMedicionDTO,
        "La medición de flujos de trabajo ha sido recuperada");

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @GetMapping("/obtenerConfiguracionesMediciones")
  public ResponseEntity<ResponseDTO> obtenerConfiguracionesMediciones() throws Exception{

      Map<String, Object> respuesta = new HashMap<>();

      ResponseDTO responseDTO = new ResponseDTO();
      String mensajeExcepcionGeneral = "Ocurrió un problema para obtener las configuraciones de mediciones: ";
      
      List<PgimValorParametroDTO> lPgimValorParamDTODivSuper = null;
      List<PgimEspecialidadDTO> lPgimEspecialidadDTO = null;
      
      try {
        
          lPgimValorParamDTODivSuper = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);
          lPgimEspecialidadDTO = this.parametroService.filtrarPorNombreEspecialidad(ConstantesUtil.PARAM_TIPO_ESPECIALIDAD_SUPERVISION);

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
    respuesta.put("lPgimValorParamDTODivSuper", lPgimValorParamDTODivSuper);
    respuesta.put("lPgimEspecialidadDTO", lPgimEspecialidadDTO);
    
    responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Se encontraron las configuraciones.");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }
  
  /**
   * Permite listar los items de una medición de indicadores por actor de negocio
   * 
   * @param filtroIndicadorDTO
   * @return
   */
  @PostMapping("/listarItemsMedicionPorActorNegocio")
  public ResponseEntity<List<ItemMedicionIndicadorDTO>> listarItemsMedicionPorActorNegocio(
		  @RequestBody FiltroIndicadorDTO filtroIndicadorDTO) {
        
    final List<ItemMedicionIndicadorDTO> lstItemMedicionIndicadorDTO = this.medicionService.listarItemsMedicionPorActorNegocio(filtroIndicadorDTO); 
    return new ResponseEntity<List<ItemMedicionIndicadorDTO>>(lstItemMedicionIndicadorDTO, HttpStatus.OK);

  }
  
  /**
   * Permite listar los items de una medición de indicadores por característica de fiscalización 
   * 
   * @param filtroIndicadorDTO
   * @return
   */
  @PostMapping("/listarItemsMedicionPorCaracteristica")
  public ResponseEntity<List<ItemMedicionIndicadorDTO>> listarItemsMedicionPorCaracteristica(
		  @RequestBody FiltroIndicadorDTO filtroIndicadorDTO) {
        
    final List<ItemMedicionIndicadorDTO> lstItemMedicionIndicadorDTO = this.medicionService.listarItemsMedicionPorCaracteristica(filtroIndicadorDTO); 
    return new ResponseEntity<List<ItemMedicionIndicadorDTO>>(lstItemMedicionIndicadorDTO, HttpStatus.OK);

  }
}
