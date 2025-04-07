package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TZ_SUPERV_FECHA: 
* @descripción: Trazabilidad de cambios de fechas de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSupervFechaDTO {

  /*
  *Identificador interno del histórico de la reprogramación. Secuencia: PGIM_SEQ_SUPERV_FECHA
  */
  private Long idSupervFecha;

  /*
  *Identificador interno de la supervisión que dio lugar al PAS. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Tipo de fechas cambiadas. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_FECHA_CAMBIADA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoFecha;

  /*
  *Fecha de inicio de la supervisión anterior a la reprogramación
  */
  private Date feInicioSupervision;

  /*
  *Fecha de inicio de la supervisión anterior a la reprogramación
  */
  private String feInicioSupervisionDesc;

  /*
  *Fecha fin de la supervisión anterior a la reprogramación
  */
  private Date feFinSupervision;

  /*
  *Fecha fin de la supervisión anterior a la reprogramación
  */
  private String feFinSupervisionDesc;

  /*
  *Motivo de la reprogramación
  */
  private String deMotivoCambio;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}