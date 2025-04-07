package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_ALERTA_DETALLE: 
* @descripción: Detalle de la alerta generada en el sistema
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAlertaDetalleDTO {

  /*
  *Identificador interno de la alerta del sistema. Secuencia: PGIM_SEQ_ALERTA_DETALLE
  */
  private Long idDetalleAlerta;

  /*
  *Identificador interno de la alerta del sistema. Tabla padre PGIM_TD_ALERTA
  */
  private Long idAlerta;

  /*
  *Tipo del detalle de la alerta. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DETALLE_ALERTA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoDetalleAlerta;

  /*
  *Nombre Windows del usuario destino de la alerta
  */
  private String noUsuarioDestino;

  /*
  *Flag de lectura o revisión de la alerta. Los posibles valores son: "1" = Revisado y "0" = Sin revisar
  */
  private String flLeido;

  /*
  *Descripción de la alerta
  */
  private String deDetalleAlerta;

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
  *Nombre del tipo de detalle de la alerta
  */
  private String descNoTipoDetalleAlerta;

  /*
  *Nombre de la alerta
  */
  private String descNoAlerta;

  /*
  *Descripción de la alerta
  */
  private String descDeAlerta;

  /*
  *Fecha de la alerta
  */
  private Date descFeAlerta;

  /*
  *Fecha de la alerta
  */
  private String descFeAlertaDesc;

  /*
  *Dirección de enlace de loa alerta
  */
  private String descDiEnlace;

  /*
  *Identificador interno del tipo de la alerta
  */
  private Long descIdTipoAlerta;

  /*
  *Nombre del tipo de la alerta
  */
  private String descNoTipoAlerta;

  /*
  *Cantidad de alertas no revisadas
  */
  private Long descCantidadAlerta;

  /*
  *Identificador del proceso
  */
  private Long descIdProceso;

  /*
  *Identificador de fase del proceso
  */
  private Long descIdFaseProceso;

  /*
  *Identificador de paso de proceso
  */
  private Long descIdPasoProceso;

  /*
  *Nombre de la fase de proceso
  */
  private String descNoFaseProceso;

  /*
  *Nombre de paso de proceso
  */
  private String descNoPasoProceso;

  /*
  *Identificador interno de la instancia de paso asociada al detalle de la alerta
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