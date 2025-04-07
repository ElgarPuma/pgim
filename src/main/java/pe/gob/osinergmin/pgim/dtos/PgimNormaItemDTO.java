package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_NORMA_ITEM: 
* @descripción: Item de norma legal
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNormaItemDTO {

  /*
  *Identificador interno del ítem de norma legal. Secuencia: PGIM_SEQ_NORMA_ITEM
  */
  private Long idNormaItem;

  /*
  *Identificador interno de la norma legal. Tabla padre PGIM_TM_NORMA
  */
  private Long idNorma;

  /*
  *División de Ítem de Norma. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DIVISION_ITEM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionItem;

  /*
  *Identificador interno del ítem de norma legal padre. Tabla padre PGIM_TM_NORMA_ITEM
  */
  private Long idNormaItemPadre;

  /*
  *Código del ítem de la norma
  */
  private String coItem;

  /*
  *Contenido del ítem de norma
  */
  private String deContenidoT;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String flVigente;

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