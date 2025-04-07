package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_OBLGCN_NRMTVA_ITEM: 
* @descripción: Ítem de norma detalle de la obligación fiscalizada
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 04/05/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimOblgcnNrmtvaItemDTO {

  /*
  *Identificador interno del ítem de norma de la obligación fiscalizada por hecho constatado de la fscalización. Secuencia: PGIM_SEQ_OBLGCN_NRMTVA_ITEM
  */
  private Long idOblgcnNrmtvaItem;

  /*
  *Identificador interno de la obligación fiscalizada por hecho constatado de la fiscalización. Tabla padre: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
  private Long idOblgcnNrmtvaHchoc;

  /*
  *Identificador interno de la obligación fiscalizada padre por hecho constatado de la fiscalización. Tabla padre: PGIM_TD_OBLGCN_NRMTVA_ITEM
  */
  private Long idOblgcnNrmtvaItemPadre;

  /*
  *Identificador interno del ítem de norma. Tabla padre: PGIM_TM_NORMA_ITEM
  */
  private Long idNormaItem;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Descripción de la ubicación del ítem de norma
  */
  private String descUbicacionNormaItem;

  /*
  *Código del ítem de la norma
  */
  private String descCoItem;

  /*
  *Descripción de la división (tipo) del ítem de la norma
  */
  private String descDeDivisionItem;

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