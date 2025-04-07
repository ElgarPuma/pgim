package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TC_ITEM_PROGRAMA_SUPE: 
* @descripción: Ítem del programa de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemProgramaSupeDTO {

  /*
  *Identificador interno del programa de supervisión. Secuencia: PGIM_SEQ_ITEM_PROGRAMA_SUPE
  */
  private Long idItemProgramaSupe;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno del programa de supervisión base desde donde se copiaron los datos de este registro. Tabla padre: PGIM_TC_ITEM_PROGRAMA_SUPE
  */
  private Long idItemProgramaSupeBase;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
  private Long idLineaPrograma;

  /*
  *Fecha inicial del mes estimado de ejecución, primero del mes seleccionado
  */
  private Date feMesEstimado;

  /*
  *Fecha inicial del mes estimado de ejecución, primero del mes seleccionado
  */
  private String feMesEstimadoDesc;

  /*
  *Monto del costo en soles estimado para el ítem de la supervisión
  */
  private BigDecimal moCostoEstimadoSupervision;

  /*
  *Descripción del ítem del programa
  */
  private String deItemPrograma;

  /*
  *Motivo de la cancelación del ítem del programa
  */
  private String deMotivoCancelacion;

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
  *Código de la unidad minera
  */
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Nombre del subtipo de supervisión
  */
  private String descSubtipoSupervision;

  /*
  *Identificador interno del agente supervisado
  */
  private Long descIdAgenteSupervisado;

  /*
  *Razón social de la empresa supervisora
  */
  private String descNoRazonSocial;

  /*
  *Flag de asignado: Sí o No
  */
  private String descFlAsignado;

  /*
  *Año del plan de supervisión
  */
  private Integer descNuAnio;

  /*
  *Nombre de la división supervisora de la unidad minera
  */
  private String descDivisionSupervision;

  /*
  *Nombre del método de minado de la unidad minera
  */
  private String descMetodoMinado;

  /*
  *Nombre de la situación de la unidad minera
  */
  private String descSituacionUm;

  /*
  *Indicador lógico de la unidad minera, que señala si el registro de datos de riesgo será o no mandatorio en el contexto de una fiscalizaciónPosibles valores: "1" = Sí y 0 = "No"
  */
  private String descFlRegistraRiesgos;

  /*
  *Listado de configuraciones de riesgo que aplican sobre la unidad minera del ítem del programa de supervisión
  */
  private String descDeConfiguracionesRiesgo;

  /*
  *Nombre del tipo de actividad
  */
  private String descNoTipoactividad;

  /*
  *Nombre del estado de la UM
  */
  private String descNoEstadoUm;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}