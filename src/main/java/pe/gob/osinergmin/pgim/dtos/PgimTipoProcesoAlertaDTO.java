package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_TIPO_PROCESO_ALERTA: 
* @descripción: Tipo de alerta del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 14/12/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTipoProcesoAlertaDTO {

  /*
  *Identificador interno del tipo de alerta del proceso. Secuencia: PGIM_SEQ_TIPO_PROCESO_ALERTA
  */
  private Long idTipoProcesoAlerta;

  /*
  *Tipo de periodo de las alertas de proceso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_PERIODO_ALERTA
  */
  private Long idTipoPeriodoAlerta;

  /*
  *Identificador interno del tipo de alerta que debe dar origen a una nueva alerta a nivel de la instancia de proceso. Tabla padre: PGIM_TM_TIPO_PROCESO_ALERTA
  */
  private Long idTipoProcesoAlertaPadre;

  /*
  *Código del tipo de la alerta del proceso
  */
  private String coTipoProcesoAlerta;

  /*
  *Nombre del tipo de la alerta del proceso
  */
  private String noTipoProcesoAlerta;

  /*
  *Descripción del tipo de la alerta del proceso
  */
  private String deTipoProcesoAlerta;

  /*
  *Cantidad de unidades del periodo para el inicio de la alerta del proceso
  */
  private Integer caPeriodoInicio;

  /*
  *Cantidad de unidades del periodo para el fin de la alerta del proceso
  */
  private Integer caPeriodoFin;

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