package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Clase Entidad para la tabla PGIM_VW_PRGRM_MONTO_ESP_AUX: 
* @descripción: Monto del programa por especialidad
*
* @author: humbertoruiz90
* @version 1.0
* @fecha_de_creación: 10/04/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimVwPrgrmMontoEspAuxDTO {

	  private Long idPlanSupervisionAux;

	  private Long idPlanSupervision;

	  private Long idEspecialidad;

	  private String noEspecialidad;

	  private BigDecimal moPartida;

	  private BigDecimal moDisponibleContrato;

	  private String contratos;

	  private BigDecimal costoTotal;

}
