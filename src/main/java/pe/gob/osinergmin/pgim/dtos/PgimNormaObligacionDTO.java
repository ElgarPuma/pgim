package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_NORMA_OBLIGACION: 
* @descripción: Obligación normativa
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNormaObligacionDTO {

  /*
  *Identificador interno de la obligación normativa. Secuencia: PGIM_SEQ_NORMA_OBLIGACION
  */
  private Long idNormaObligacion;

  /*
  *Identificador interno del ítem de norma legal. Tabla padre PGIM_TM_NORMA_ITEM
  */
  private Long idNormaItem;

  /*
  *Identificador interno del ítem de tipificación. Tabla padre PGIM_TM_ITEM_TIPIFICACION
  */
  private Long idItemTipificacion;

  /*
  *Descripción de  la obligación normativa
  */
  private String deNormaObligacionT;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigente;

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