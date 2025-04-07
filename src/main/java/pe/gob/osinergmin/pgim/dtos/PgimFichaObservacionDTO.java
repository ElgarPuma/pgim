package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_FICHA_OBSERVACION: 
* @descripción: Detalle de las observaciones asociadas a una ficha de observaciones al informe de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimFichaObservacionDTO {

  /*
  *Identificador interno de la observación específica en la ficha del tipo observaciones. Secuencia: PGIM_SEQ_FICHA_OBSERVACION
  */
  private Long idFichaObservacion;

  /*
  *Identificador interno del documento ficha de revisión. Tabla padre: PGIM_TD_FICHA_REVISION
  */
  private Long idFichaRevision;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OBSERVACION_FICHA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoObservacionFicha;

  /*
  *Identificador interno del ítem de observación previo que no fue subsanado en la ficha de revisión previa. Tabla padre: PGIM_TD_FICHA_OBSERVACION
  */
  private Long idFichaObservacionOrigen;

  /*
  *Fecha de revisión del informe de supervisión
  */
  private Date feRevisionFicha;

  /*
  *Fecha de revisión del informe de supervisión
  */
  private String feRevisionFichaDesc;

  /*
  *Cantidad de días para realizar la presentación del informe de supervisión
  */
  private Integer caDiasParaPresentacion;

  /*
  *Fecha inicial del conteo de la cantidad de dias para la presentación del informe de supervisión
  */
  private Date feDesdeParaPresentacion;

  /*
  *Fecha inicial del conteo de la cantidad de dias para la presentación del informe de supervisión
  */
  private String feDesdeParaPresentacionDesc;

  /*
  *Fecha de presentación del informe de supervisión
  */
  private Date fePresentacion;

  /*
  *Fecha de presentación del informe de supervisión
  */
  private String fePresentacionDesc;

  /*
  *Parte del informe que se está observando
  */
  private String deParteInformeObservadaT;

  /*
  *Ítem descriptivo de la observación realizada
  */
  private String deItemObservacionT;

  /*
  *Flag que indica si la observación fue subsanada o no. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flSubsanada;

  /*
  *Comentario del ítem de la observación
  */
  private String cmItemObservacionT;

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
  *Tipo de observación de la ficha
  */
  private String descTipoObservacionFicha;

  /*
  *Condición lógica de la subsanación
  */
  private boolean descChkSubsanada;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}