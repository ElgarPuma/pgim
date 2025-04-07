package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrmtroXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstrmntoXSuperv;

/**
 * Interfaz para la gestión de los servicios relacionados con los parámetros.
 * 
 * @descripción: Instrumentos de supervisión
 *
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/08/2022
 */
public interface SupervInstrumentoService {

  /**
   * Permite listar los instrumentos de una determinado fiscalización
   * @param pgimInstrmntoXSupervDTO
   * @param paginador
   * @return
   */
  Page<PgimInstrmntoXSupervDTO> listar(PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, Pageable paginador);

  /**
   * Permite listar los instrumentos de una fiscalización
   * @param pgimInstrmntoXSupervDTO
   * @return
   */
  List<PgimInstrmntoXSupervDTO> listarInstXsuperv(String codSupervision);
  
  /**
   * Permite listar los instrumentos de medición según su estado
   */
  List<PgimInstrmntoXSupervDTO> ObtenerListaInstrumentoRegAprob(Long idSupervision, String estRegistrado, String estAprobado, String estModificado);

  /**
   * Permite crear un nuevo instrumento de medición
   * @param pgimInstrmntoXSupervDTO
   * @param auditoriaDTO
   * @return
   */
  PgimInstrmntoXSupervDTO crearInstrumentoXSuperv(PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, AuditoriaDTO auditoriaDTO);

  /**
   * Permite modificar un instrumento de medición
   * @param pgimInstrmntoXSupervDTO
   * @param pgimInstrmntoXSuperv
   * @param auditoriaDTO
   * @return
   */
  PgimInstrmntoXSupervDTO modificarInstrumentoXSuperv(PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, PgimInstrmntoXSuperv pgimInstrmntoXSuperv , AuditoriaDTO auditoriaDTO);  
  
  /**
   * Permite modificar el estado de un instrumento de medición
   * @param coEstado
   * @param pgimInstrmntoXSuperv
   * @param auditoriaDTO
   * @return
   */
  PgimInstrmntoXSupervDTO cambiarEstadoInstrumentoXSuperv(String coEstado, PgimInstrmntoXSuperv pgimInstrmntoXSuperv , AuditoriaDTO auditoriaDTO);

  /**
   * Permite eliminar un instrumento de medición
   * @param pgimInstrmntoXSupervDTOActual
   * @param auditoriaDTO
   */
  void eliminarInstrumentoXSuperv(PgimInstrmntoXSuperv pgimInstrmntoXSupervActual, PgimInstrmntoXSuperv pgimInstrmntoXSupervReemplazado, AuditoriaDTO auditoriaDTO);

  /**
   *  Permite obtener un determinado instrumento medición por su ID
   * @param idInstrmntoXSuperv
   * @return
   */
  PgimInstrmntoXSupervDTO obtenerInstrumentoXSupervPorId(Long idInstrmntoXSuperv);

  /**
   *  Permite obtener un determinado instrumento medición por su ID
   * @param idInstrmntoXSuperv
   * @return
   */
  PgimInstrmntoXSuperv getByIdInstrumentoXSuperv(Long idInstrmntoXSuperv);

  /**
   * Permite obtener el listado de los tipos de intrumentos que pertencen a un determinado contrato
   * @param idContrato
   * @return
   */
  List<PgimTipoInstrumentoDTO> obtenerTipoInstrumentoPorIdContrato(Long idContrato);

  /**
   * Permite obtener el listado de los parametros de mediciones que pertenecen a un tipo de instrumento y a un contrato
   * @param idContrato
   * @return
   */
  List<PgimTipopameXContratoDTO> obtenerTipoParamInstrumentoContrato(Long idContrato);

  /**
   * Permite obtener el listado de los parametros de mediciones que pertenecen a un instrumento de una fiscalización
   * @param instrmntoXSuperv
   * @return
   */
  List<PgimPrmtroXSupervDTO> obtenerParamInstrumentoSuperv(Long instrmntoXSuperv);

  Page<PgimInstrmntoXSupervDTO> listarInstrumentoParaAprobar( Long idSupervision, Pageable paginador);
  
  List<PgimInstrmntoXSupervDTO> aprobarInstrumentoManual(Long idSupervision, AuditoriaDTO auditoriaDTO);
}
