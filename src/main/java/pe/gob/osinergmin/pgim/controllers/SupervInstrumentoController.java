package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrmtroXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimInstrmntoXSuperv;
import pe.gob.osinergmin.pgim.services.SupervInstrumentoService;
import pe.gob.osinergmin.pgim.services.SupervisionService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas con los instrumentos  de mediciones en una supervisión
 * 
 * @descripción: Supervision
 *
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/08/2022
 */
@RestController
@Slf4j
@RequestMapping("/supervinstrumento")
public class SupervInstrumentoController extends BaseController{
  
  @Autowired
  private SupervInstrumentoService supervInstrumentoService;
	
  @Autowired
	private SupervisionService supervisionService;
  
  /**
   * Permite obtener el listado de los instrumentos de medeción de forma paginada
   * @param pgimInstrmntoXSupervDTO
   * @param paginador
   * @return
   * @throws Exception
   */
  @PostMapping("/listar")
  public ResponseEntity<Page<PgimInstrmntoXSupervDTO>> listarInstrumento(@RequestBody PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, Pageable paginador) throws Exception {

      Page<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = this.supervInstrumentoService.listar(pgimInstrmntoXSupervDTO, paginador);

      return new ResponseEntity<Page<PgimInstrmntoXSupervDTO>>(lPgimInstrmntoXSupervDTO, HttpStatus.OK);
  }

  /**
   * Permite obtener el listado de los instrumentos de medición de una fiscalización para el acta de medición
   * @param pgimInstrmntoXSupervDTO
   * @return
   * @throws Exception
   */
  // @PostMapping("/listarInstXsuperv")
  // public ResponseEntity<List<PgimInstrmntoXSupervDTO>> listarInstXsuperv(@RequestBody PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO) throws Exception {

  //     List<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = this.supervInstrumentoService.listarInstXsuperv(pgimInstrmntoXSupervDTO);

  //     return new ResponseEntity<List<PgimInstrmntoXSupervDTO>>(lPgimInstrmntoXSupervDTO, HttpStatus.OK);
  // }

  @GetMapping("/listarInstXsuperv/{codSupervision}")
  public ResponseEntity<?> listarInstXsuperv(@PathVariable String codSupervision) throws Exception {

      Map<String, Object> respuesta = new HashMap<>();
      List<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO;

      try {
        lPgimInstrmntoXSupervDTO = this.supervInstrumentoService.listarInstXsuperv(codSupervision);
  
      } catch (DataAccessException e) {
          respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de instrumentos.");
          respuesta.put("error", e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);

          return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      respuesta.put("mensaje", "Se obtuviero la lista de instrumentos de medición.");
      respuesta.put("lPgimInstrmntoXSupervDTO", lPgimInstrmntoXSupervDTO);

      return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
  }

  /**
   * Permite obtener las configuraciones necesarias para el formulario de creación/edición de los instrumentos de medición
   * @param idSupervision
   * @return
   * @throws Exception
   */
  @GetMapping("/obtenerConfiguracionesGenerales/{idSupervision}")
  public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idSupervision) throws Exception {

      Map<String, Object> respuesta = new HashMap<>();

      List<PgimTipoInstrumentoDTO> lPgimTipoInstrumentoDTO = null;
      List<PgimTipopameXContratoDTO> lPgimTipopameXContratoDTO = null;

      try {
          PgimSupervisionDTO pgimSupervisionDTO = supervisionService.obtenerSupervisionPorId(idSupervision);
          lPgimTipoInstrumentoDTO = this.supervInstrumentoService.obtenerTipoInstrumentoPorIdContrato(pgimSupervisionDTO.getDescIdContrato());
          lPgimTipopameXContratoDTO = this.supervInstrumentoService.obtenerTipoParamInstrumentoContrato(pgimSupervisionDTO.getDescIdContrato());

      } catch (DataAccessException e) {
          respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
          respuesta.put("error", e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);

          return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
      respuesta.put("lPgimTipoInstrumentoDTO", lPgimTipoInstrumentoDTO);
      respuesta.put("lPgimTipopameXContratoDTO", lPgimTipopameXContratoDTO);

      return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
  }
  
  /**
   * Permite obtener las configuraciones necesarias para el formulario de creación/edición de los instrumentos de medición
   * @param idSupervision
   * @return
   * @throws Exception
   */
  @GetMapping("/obtenerConfiguracioneEdit/{idInstrmntoXSuperv}")
  public ResponseEntity<?> obtenerConfiguracioneEdit(@PathVariable Long idInstrmntoXSuperv) throws Exception {

      Map<String, Object> respuesta = new HashMap<>();

      List<PgimTipoInstrumentoDTO> lPgimTipoInstrumentoDTO = null;
      List<PgimTipopameXContratoDTO> lPgimTipopameXContratoDTO = null;
      PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO = null;
      List <PgimPrmtroXSupervDTO> lPgimPrmtroXSupervDTO = null;

      try {
        pgimInstrmntoXSupervDTO = this.supervInstrumentoService.obtenerInstrumentoXSupervPorId(idInstrmntoXSuperv);
        lPgimPrmtroXSupervDTO = this.supervInstrumentoService.obtenerParamInstrumentoSuperv(idInstrmntoXSuperv);
        
        PgimSupervisionDTO pgimSupervisionDTO = supervisionService.obtenerSupervisionPorId(pgimInstrmntoXSupervDTO.getIdSupervision());
        lPgimTipoInstrumentoDTO = this.supervInstrumentoService.obtenerTipoInstrumentoPorIdContrato(pgimSupervisionDTO.getDescIdContrato());
        
        lPgimTipopameXContratoDTO = this.supervInstrumentoService.obtenerTipoParamInstrumentoContrato(pgimSupervisionDTO.getDescIdContrato());

      } catch (DataAccessException e) {
          respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
          respuesta.put("error", e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);

          return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
      respuesta.put("pgimInstrmntoXSupervDTO", pgimInstrmntoXSupervDTO);
      respuesta.put("lPgimPrmtroXSupervDTO", lPgimPrmtroXSupervDTO);
      respuesta.put("lPgimTipoInstrumentoDTO", lPgimTipoInstrumentoDTO);
      respuesta.put("lPgimTipopameXContratoDTO", lPgimTipopameXContratoDTO);

      return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
  }
  
  /**
   * Permite crear un nuevo instrumento de medición
   * @param pgimInstrmntoXSupervDTO
   * @return
   * @throws PgimException
   * @throws IOException
   * @throws Exception
   */
  @PostMapping("/crearInstrumento")
  public ResponseEntity<?> crearInstrumento( @RequestBody PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO) throws PgimException, IOException, Exception {

      ResponseDTO responseDTO = null;
      
      String mensaje;
     
      PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTOCreado = null;
      
      try {
        pgimInstrmntoXSupervDTOCreado = this.supervInstrumentoService.crearInstrumentoXSuperv(pgimInstrmntoXSupervDTO, this.obtenerAuditoria());
      } catch (DataAccessException e) {
        String mensajeAux = "Ocurrió un error al intentar crear un instrumento de medición"; 
        if(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv() != 0){
          mensajeAux = "Ocurrió un error al intentar reemplazar un instrumento de medición";
        }

        log.error(e.getMostSpecificCause().getMessage(), e);
          mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());
          
          responseDTO = new ResponseDTO("error", mensaje);

          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
          
      }catch (final PgimException e) {
          // Excepcion controlada que deberá ser manejada por el frontend
        log.error(e.getMensaje(), e);
          responseDTO = new ResponseDTO("error", e.getMensaje());
          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

      mensaje = "Estupendo, el instrumento de medición ha sido creado"; 
      if(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv() != 0){
        mensaje = "Estupendo, el instrumento de medición ha sido reemplazado";
      }
      
      responseDTO = new ResponseDTO("success", pgimInstrmntoXSupervDTOCreado, mensaje);

      return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }

  /**
   * Permite eliminar un determinado instrumento de medición
   * @param idInstrmntoXSuperv
   * @return
   * @throws Exception
   */
  @DeleteMapping("/eliminarInstrumentoSuperv/{idInstrmntoXSuperv}/{descIdInstrmntoXSupervRmplzdo}")
  public ResponseEntity<ResponseDTO> eliminarInstrumentoSuperv(@PathVariable Long idInstrmntoXSuperv, @PathVariable Long descIdInstrmntoXSupervRmplzdo) throws Exception {
    ResponseDTO responseDTO = null;
      String mensaje;

      PgimInstrmntoXSuperv pgimInstrmntoXSupervActual = null;
      PgimInstrmntoXSuperv pgimInstrmntoXSupervReemplazado = null;

      try {
        pgimInstrmntoXSupervActual = this.supervInstrumentoService.getByIdInstrumentoXSuperv(idInstrmntoXSuperv);
        pgimInstrmntoXSupervReemplazado = this.supervInstrumentoService.getByIdInstrumentoXSuperv(descIdInstrmntoXSupervRmplzdo);

          if (pgimInstrmntoXSupervActual == null) {
              mensaje = String.format("El instrumento de medición %s que intenta eliminar no existe en la base de datos",
                  idInstrmntoXSuperv);
              responseDTO = new ResponseDTO("mensaje", mensaje);

              return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
          }
      } catch (DataAccessException e) {
          log.error(e.getMostSpecificCause().getMessage(), e);
          mensaje = String.format("Ocurrió un error intentar recuperar el instrumento de medición a eliminar",
                  e.getMostSpecificCause().getMessage());

          responseDTO = new ResponseDTO("error", mensaje);
          log.error(e.getMostSpecificCause().getMessage(), e);
          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }
    
      try {
          this.supervInstrumentoService.eliminarInstrumentoXSuperv(pgimInstrmntoXSupervActual, pgimInstrmntoXSupervReemplazado, this.obtenerAuditoria());
      } catch (DataAccessException e) {            
          mensaje = String.format("Ocurrió un error intentar eliminar el instrumento de medición",
                  e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);
          responseDTO = new ResponseDTO("error", mensaje);

          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

      responseDTO = new ResponseDTO("success", "El instrumento de medición fue eliminado");

      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }


  @PutMapping("/cambiarEstadoInstrumentoSuperv")
  public ResponseEntity<ResponseDTO> cambiarEstadoInstrumentoSuperv(@RequestBody PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO) throws Exception {
    ResponseDTO responseDTO = null;
      String mensaje;

      PgimInstrmntoXSuperv pgimInstrmntoXSupervActual = null;

      PgimInstrmntoXSupervDTO pgimInstrmntoXSupervActualDTOModificado = null;

      try {
        pgimInstrmntoXSupervActual = this.supervInstrumentoService.getByIdInstrumentoXSuperv(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv());

          if (pgimInstrmntoXSupervActual == null) {
              mensaje = String.format("El instrumento de medición %s que intenta actualizar no existe en la base de datos",
              pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv());
              responseDTO = new ResponseDTO("mensaje", mensaje);

              return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
          }
      } catch (DataAccessException e) {
          log.error(e.getMostSpecificCause().getMessage(), e);
          mensaje = String.format("Ocurrió un error intentar recuperar el instrumento de medición a actualizar",
                  e.getMostSpecificCause().getMessage());

          responseDTO = new ResponseDTO("error", mensaje);
          log.error(e.getMostSpecificCause().getMessage(), e);
          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }
    
      try {
        String coEstado = pgimInstrmntoXSupervDTO.getDescCoEstadoInstrumento();
        pgimInstrmntoXSupervActualDTOModificado = this.supervInstrumentoService.cambiarEstadoInstrumentoXSuperv(coEstado, pgimInstrmntoXSupervActual, this.obtenerAuditoria());

      } catch (DataAccessException e) {            
          mensaje = String.format("Ocurrió un error intentar cambiar de estado al instrumento de medición",
                  e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);
          responseDTO = new ResponseDTO("error", mensaje);

          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

      responseDTO = new ResponseDTO("success", pgimInstrmntoXSupervActualDTOModificado, "Estupendo, el instrumento de medición fue actualizado");
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }
  
  @PutMapping("/modificarInstrumentoSuperv")
  public ResponseEntity<ResponseDTO> modificarInstrumentoSuperv(@RequestBody PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO) throws Exception {
    ResponseDTO responseDTO = null;
      String mensaje;

      PgimInstrmntoXSuperv pgimInstrmntoXSupervActual = null;

      PgimInstrmntoXSupervDTO pgimInstrmntoXSupervActualDTOModificado = null;

      try {
        pgimInstrmntoXSupervActual = this.supervInstrumentoService.getByIdInstrumentoXSuperv(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv());

          if (pgimInstrmntoXSupervActual == null) {
              mensaje = String.format("El instrumento de medición %s que intenta actualizar no existe en la base de datos",
              pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv());
              responseDTO = new ResponseDTO("mensaje", mensaje);

              return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
          }
      } catch (DataAccessException e) {
          log.error(e.getMostSpecificCause().getMessage(), e);
          mensaje = String.format("Ocurrió un error intentar recuperar el instrumento de medición a actualizar",
                  e.getMostSpecificCause().getMessage());

          responseDTO = new ResponseDTO("error", mensaje);
          log.error(e.getMostSpecificCause().getMessage(), e);
          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }
    
      try {
        pgimInstrmntoXSupervActualDTOModificado = this.supervInstrumentoService.modificarInstrumentoXSuperv(pgimInstrmntoXSupervDTO, pgimInstrmntoXSupervActual, this.obtenerAuditoria());

      } catch (DataAccessException e) {            
          mensaje = String.format("Ocurrió un error intentar eliminar el instrumento de medición",
                  e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);
          responseDTO = new ResponseDTO("error", mensaje);

          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

      responseDTO = new ResponseDTO("success", pgimInstrmntoXSupervActualDTOModificado, "Estupendo, el instrumento de medición fue actualizado");
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }
  
  
  @PostMapping("/listarInstrumentoParaAprobar")
  public ResponseEntity<Page<PgimInstrmntoXSupervDTO>> listarInstrumentoParaAprobar(@RequestBody Long idSupervision, Pageable paginador) throws Exception {

      Page<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = this.supervInstrumentoService.listarInstrumentoParaAprobar(idSupervision, paginador);

      return new ResponseEntity<Page<PgimInstrmntoXSupervDTO>>(lPgimInstrmntoXSupervDTO, HttpStatus.OK);
  }

  @PostMapping("/aprobarInstrumentoManual")
  public ResponseEntity<ResponseDTO> aprobarInstrumentoManual(@RequestBody Long idSupervision) throws Exception {
    ResponseDTO responseDTO = null;
      String mensaje;
      
      List <PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = new ArrayList<PgimInstrmntoXSupervDTO>();
   
      try {
        
        lPgimInstrmntoXSupervDTO = this.supervInstrumentoService.aprobarInstrumentoManual(idSupervision, this.obtenerAuditoria());

      } catch (DataAccessException e) {            
          mensaje = String.format("Ocurrió un error intentar aprobar un instrumento de medición",
                  e.getMostSpecificCause().getMessage());
          log.error(e.getMostSpecificCause().getMessage(), e);
          responseDTO = new ResponseDTO("error", mensaje);

          return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
      }

      responseDTO = new ResponseDTO("success", lPgimInstrmntoXSupervDTO, "Estupendo, los instrumentos fueron aprobados");
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

}
