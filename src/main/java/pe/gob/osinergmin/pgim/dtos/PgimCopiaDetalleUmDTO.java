package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TZ_COPIA_DETALLE_UM: 
* @descripción: Detalle de las copias realizadas por cada trabajo de copia
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCopiaDetalleUmDTO {

  /*
  *Identificador interno de cada una de las filas de las unidades mineras copiadas en la el trabajo de copia. Secuencia: PGIM_SEQ_COPIA_DETALLE_UM
  */
  private Long idCopiaDetalleUm;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TZ_TRABAJO_COPIA_UM
  */
  private Long idTrabajoCopiaUm;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Identificador interno del agente supervisado relacionado. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Situación de la unidad minera, de acuerdo con lo establecido por el Minem. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = SITUACION_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idSituacion;

  /*
  *Tipo de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoUnidadMinera;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idMetodoMinado;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoSustancia;

  /*
  *Tipo de actividad de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTIVIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoActividad;

  /*
  *RUC del agente supervisado
  */
  private String rucAgenteSupervisado;

  /*
  *Razón social del agente supervisado
  */
  private String razonSocialAs;

  /*
  *Código de la unidad minera que tiene estricta correspondencia con la codificación del Ingemmet
  */
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String noUnidadMinera;

  /*
  *Fecha de copia mensual. La copia se debe realizar a las 23:59 del último día del mes
  */
  private String coAnonimizacion;

  /*
  *Capacidad instalada de planta expresada en TM/d. Aplica solo cuando el <<Tipo de Unidad Minera>> sea igual a <<Concesión de beneficio>>
  */
  private BigDecimal nuCpcdadInstldaPlanta;

  /*
  *Código de la unidad minera que tiene estricta correspondencia con la codificación del Ingemmet y que se refiere a la planta beneficio destino
  */
  private String coUnidadMineraPlanta;

  /*
  *Nombre de la unidad minera de la planta de beneficio destino
  */
  private String noUnidadMineraPlanta;

  /*
  *Fecha del inicio de la titularidad del agente supervisado
  */
  private Date feInicioTitularidad;

  /*
  *Fecha del inicio de la titularidad del agente supervisado
  */
  private String feInicioTitularidadDesc;

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
  *Nombre de la división supervisora
  */
  private String descNoDivisionSupervisora;

  /*
  *Nombre de la situación de la unidad minera
  */
  private String descNoSituacion;

  /*
  *Nombre del tipo de la unidad minera
  */
  private String descNoTipoUnidadMinera;

  /*
  *Nombre del método de minado
  */
  private String descNoMetodoMinado;

  /*
  *Nombre del tipo de sustancia
  */
  private String descNoTipoSustancia;

  /*
  *Nombre del tipo de actividad
  */
  private String descNoTipoActividad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}