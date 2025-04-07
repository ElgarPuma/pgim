package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_ITEM_TIPIFICACION: 
* @descripción: Item de tipificación de norma legal
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimItemTipificacionDTO {

  /*
  *Identificador interno del ítem de tipificación. Secuencia: PGIM_SEQ_ITEM_TIPIFICACION
  */
  private Long idItemTipificacion;

  /*
  *Identificador interno de la norma legal. Tabla padre PGIM_TM_NORMA
  */
  private Long idNorma;

  /*
  *Código de la tipificación
  */
  private String coTipificacion ;

  /*
  *Nombre del ítem de tipificación
  */
  private String noItemTipificacion;

  /*
  *Base legal
  */
  private String deBaseLegal;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
  private String deSancionPecuniariaUit;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigente;

  /*
  *Número de orden en la presentación
  */
  private Long nuOrden;

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
  *Número de obligaciones registradas por tipificación
  */
  private Long descNuObligaciones;

  /*
  *DESC_NO_CORTO_NORMA
  */
  private String descNoCortoNorma;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}