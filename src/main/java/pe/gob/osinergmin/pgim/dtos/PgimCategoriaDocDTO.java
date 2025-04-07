package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_CATEGORIA_DOC: 
* @descripción: Categoría de documento
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCategoriaDocDTO {

  /*
  *Identificador interno de categoría de documento. Secuencia: PGIM_SEQ_CATEGORIA_DOC
  */
  private Long idCategoriaDocumento;

  /*
  *Código de la categoría de documento
  */
  private String coCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
  private String noCategoriaDocumento;

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
  *DESC_ID_SUBCAT_DOCUMENTO
  */
  private Long descIdSubcatDocumento;

  /*
  *DESC_CO_SUBCAT_DOCUMENTO
  */
  private String descCoSubcatDocumento;

  /*
  *DESC_NO_SUBCAT_DOCUMENTO
  */
  private String descNoSubcatDocumento;

  /*
  *DESC_ID_SUBCATEGORIA_PLANTI
  */
  private Long descIdSubcategoriaPlanti;

  /*
  *DESC_NO_PLANTILLA
  */
  private String descNoPlantilla;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}