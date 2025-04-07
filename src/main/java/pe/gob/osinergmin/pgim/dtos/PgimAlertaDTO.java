package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_ALERTA: 
* @descripción: Alerta generada en el sistema
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAlertaDTO {

  /*
  *Identificador interno de la alerta del sistema. Secuencia: PGIM_SEQ_ALERTA
  */
  private Long idAlerta;

  /*
  *Tipo de fechas cambiadas. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ALERTA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoAlerta;

  /*
  *Identificador interno de la instancia de paso del proceso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;
  
  /*
  *Identificador interno de la alerta a nivel de la instancia del proceso. Tabla padre: PGIM_TC_IPROCESO_ALERTA
  */
  private Long idIprocesoAlerta;

  /*
  *Nombre o título de la alerta
  */
  private String noAlerta;

  /*
  *Descripción de la alerta
  */
  private String deAlerta;

  /*
  *Fecha de generación de la alerta
  */
  private Date feAlerta;

  /*
  *Fecha de generación de la alerta
  */
  private String feAlertaDesc;

  /*
  *Dirección URL de enlace del objeto de negocio
  */
  private String diEnlace;

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
   *Descripción del detalle de la alerta
   */
   private String descDeDetalleAlerta;
   
  /*
  *Id del tipo de detalle de la alerta
  */
  private Long descIdTipoDetalleAlerta;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}