package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VC_AGENTE_SUPERVISADO_AUX: 
* @descripción: Vista del agente supervisado (titular minero)
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAgenteSupervisadoAuxDTO {

  /*
  *Identificador interno del agente supervisado AUX. Secuencia: PGIM_SEQ_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisadoAux;

  /*
  *Identificador interno del agente supervisado relacionado
  */
  private Long idAgenteSupervisado;

  /*
  *Cantidad de Unidades Mineras asociadas al Agente Supervisado
  */
  private Long nuCantidadUniMinera;

  /*
  *Capacidad total instalada de planta (TM/d), de las unidades mineras asociadas al Agente Supervisado, y que son de tipo concesión de beneficio.
  */
  private BigDecimal nuCapacidadTotalInstalada;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}