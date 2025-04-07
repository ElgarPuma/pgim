package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_SUBCAT_DOC_FIRMA: 
* @descripción: Firma de subcategoría de documento por paso de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSubcatDocFirmaDTO {

  /*
  *Identificador interno del  registro de firma por subcategoría documental. Secuencia: PGIM_SEQ_SUBCAT_DOC_FIRMA
  */
  private Long idSubcatDocFirma;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Identificador interno del paso del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoProceso;

  /*
  *Flag que indica si el documento puede ser firmado más de una vez en el mismo paso. Normalmente esto ocurre cuando se trata de un documento firmado por los supervisores de campo de la empresa supervisora. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flFirmaMultiple;

  /*
  *Flag que indica si el documento es firmado digital o visado digital. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flVistoBueno;

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