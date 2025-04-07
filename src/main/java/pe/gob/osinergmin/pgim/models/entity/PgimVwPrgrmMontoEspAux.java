package pe.gob.osinergmin.pgim.models.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_PRGRM_MONTO_ESP_AUX: 
* @descripción: Monto del programa por especialidad
*
* @author: humbertoruiz90
* @version 1.0
* @fecha_de_creación: 10/04/2022
*/
@Entity
@Table(name = "PGIM_VW_PRGRM_MONTO_ESP_AUX")
@Data
@NoArgsConstructor
public class PgimVwPrgrmMontoEspAux {

   @Id
   @Column(name = "ID_PLAN_SUPERVISION_AUX", nullable = false)
  private Long idPlanSupervisionAux;

   @Column(name = "ID_PLAN_SUPERVISION", nullable = false)
  private Long idPlanSupervision;

   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

   @Column(name = "MO_PARTIDA", nullable = true)
  private BigDecimal moPartida;

   @Column(name = "MO_DISPONIBLE_CONTRATO", nullable = true)
  private BigDecimal moDisponibleContrato;

   @Column(name = "CONTRATOS", nullable = true)
  private String contratos;

   @Column(name = "COSTO_TOTAL", nullable = true)
  private BigDecimal costoTotal;


}