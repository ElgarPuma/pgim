package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_RELACION_FIRMA: 
* @descripción: Firma de subcategoría de documento por relación de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRelacionFirmaDTO {

  /*
  *Identificador interno del  registro de firma por relación de transición de proceso. Secuencia: PGIM_SEQ_RELACION_FIRMA
  */
  private Long idRelacionFirma;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Identificador interno de la relación del paso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Valor lógico que señála si la relación es obligatoria o no. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String flObligatorio;

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
  *Nombre de la subcategoría de documento
  */
  private String descNoSubcatDocumento;

  /*
  *Nombre de la categoría de documento
  */
  private String descNoCategoriaDocumento;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}