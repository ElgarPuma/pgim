package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TC_PRGRM_SUPERVISION: 
* @descripción: Programa de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPrgrmSupervisionDTO {

  /*
  *Identificador interno del programa de supervisión. Secuencia: PGIM_SEQ_PRGRM_SUPERVISION
  */
  private Long idProgramaSupervision;

  /*
  *Identificador interno del plan de supervisión. Tabla padre: PGIM_TC_PLAN_SUPERVISION
  */
  private Long idPlanSupervision;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Descripción del programa de supervisión
  */
  private String deProgramaSupervision;

  /*
  *Monto en soles de la partida asignada al programa
  */
  private BigDecimal moPartida;

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
  *Etiqueta para el nombre del programa de supervisión
  */
  private String noProgramaSupervision;

  /*
  *Etiqueta para el nombre del programa de supervisión
  */
  private Long descNuAnio;

  /*
  *Costo del monto del programa
  */
  private BigDecimal descMoCosto;

  /*
  *Descripción de la especialidad
  */
  private String descDeEspecialidad;

  /*
  *Nombre de la división supervisora
  */
  private String descNoDivisionSupervisora;

  /*
  *Nombre del paso
  */
  private String descPaso;

  /*
  *Identificador interno de la relación del paso
  */
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal del Osinergmin
  */
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la asignación de la instancia del programa
  */
  private String descDeMensaje;

  /*
  *DESC_ID_LINEA_PROGRAMA
  */
  private Long descIdLineaPrograma;

  /*
  *DESC_ESTADO
  */
  private String descEstado;

  /*
  *descIdInstanciaPaso
  */
  private Long descIdInstanciaPaso;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}