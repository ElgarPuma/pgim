package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_REGLA_BASE: 
* @descripción: Reglas de configuración para datos base en la fiscalización
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimReglaBaseDTO {

  /*
  *Identificador interno de la regla base para los datos en el marco de la fiscalización. Secuencia: PGIM_SEQ_REGLA_BASE
  */
  private Long idReglaBase;

  /*
  *Identificador interno de la configuración base de documento. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
  private Long idConfiguracionBase;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Identificador interno del motivo de la fiscalización. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Tipo de minado de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = METODO_MINADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idMetodoMinado;

  /*
  *Flag que indica si la fiscalización es propia, es decir, se la realizó el personal de Osinergmin. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flPropia;

  /*
  *Indicador lógico que señala si el registro de la UM tiene componentes de tipo Piques. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flConPiques;

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
  *DESC_ID_TIPO_CONFIGURACION_BASE
  */
  private Long descIdTipoConfiguracionBase;

  /*
  *DESC_NO_CONFIGURACION_BASE
  */
  private String descNoConfiguracionBase;

  /*
  *DESC_NO_TIPO_CONFIGURACION_BASE
  */
  private String descNoTipoConfiguracionBase;

  /*
  *DESC_ID_ESPECIALIDAD
  */
  private Long descIdEspecialidad;

  /*
  *DESC_NO_ESPECIALIDAD
  */
  private String descNoEspecialidad;

  /*
  *DESC_NO_DIVISION_SUPERVISORA
  */
  private String descNoDivisionSupervisora;

  /*
  *DESC_ID_TIPO_SUPERVISION
  */
  private Long descIdTipoSupervision;

  /*
  *DESC_NO_TIPO_SUPERVISION
  */
  private String descNoTipoSupervision;

  /*
  *DESC_NO_MOTIVO_SUPERVISION
  */
  private String descNoMotivoSupervision;

  /*
  *DESC_NO_SUBTIPO_SUPERVISION
  */
  private String descNoSubtipoSupervision;

  /*
  *DESC_NO_METODO_MINADO
  */
  private String descNoMetodoMinado;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}