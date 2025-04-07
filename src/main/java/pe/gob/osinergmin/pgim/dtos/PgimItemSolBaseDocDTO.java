package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_ITEM_SOL_BASE_DOC: 
* @descripción: Maestro de los ítems de la solicitud de la documentación
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemSolBaseDocDTO {

  /*
  *Identificador interno del ítem de la solicitud de documentación. Secuencia: PGIM_SEQ_ITEM_SOL_BASE_DOC
  */
  private Long idItemSolBaseDoc;

  /*
  *Identificador interno de la solicitud base (plantilla) de documentación. Tabla padre: PGIM_TM_SOLICITUD_BASE_DOC
  */
  private Long idSolicitudBaseDoc;

  /*
  *Descripción de la solicitud de documentación
  */
  private String deSolicitudObservacion;

  /*
  *Orden de presentación de la solicitud de la documentación
  */
  private BigDecimal nuOrden;

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