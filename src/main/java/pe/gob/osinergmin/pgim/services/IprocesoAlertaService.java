package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;

/**
 * Interfaz para la gestión de los servicios relacionados con Instancia proceso alertas
 * 
 * @descripción: Instancia proceso alertas
 *
 * @author: palominovega  
 * @version: 1.0
 * @fecha_de_creación: 12/12/2021
 */
public interface IprocesoAlertaService {
  
  /**
   * Permite determinar que tipo de alerta de instancias procesa
   * se va a registrar o desactivar.
   * 
   * @param pgimInstanciaProces
   * @param pgimInstanciaPaso
   * @param auditoriaDTO
   * @throws Exception
   */
  public void determinarTipAlertInstanciaACrear(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception;

  /**
   * permite obtener todas las alertas vigentes de tipo cumplimiento por plazos 
   * por el usuario logeado
   * @param auditoriaDTO
   * @param paginador
   * @return
   */
  public Page<PgimIprocesoAlertaDTO> listarAlertaCumple(AuditoriaDTO auditoriaDTO, Pageable paginador);

  /**
   * Permite obtener todas las alertas de instancia proceso vigentes 
   * por instancias proceso (por objeto de trabajo) 
   * @param idInstanciaProceso
   * @return
   */
  public List<PgimIprocesoAlertaDTO> listarIProcesoAlertaPorIdInstancia(Long idInstanciaProceso);

  /**
   * Permite obtener todas las alertas de instancia proceso vigentes semanalmente
   * 
   * @param auditoriaDTO
   * @return
   * @throws Exception
   */
  public List<PgimIprocesoAlertaDTO> notificarAlertasCumpliemientoSemanal(AuditoriaDTO auditoriaDTO) throws Exception;

  /**
   * Permite obtener todas las alertas de instancia proceso vigentes diario
   * 
   * @param auditoriaDTO
   * @return
   * @throws Exception
   */
  public List<PgimIprocesoAlertaDTO> notificarAlertasCumpliemientoDiario(AuditoriaDTO auditoriaDTO) throws Exception;
  
}
