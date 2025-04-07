package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_PASO_SUBCAT: 
* @descripción: Subcategorías por paso de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPasoSubcatDTO {

  /*
  *Identificador interno de la subcategoría por paso. Secuencia: PGIM_SEQ_PASO_SUBCAT
  */
  private Long idPasoSubcat;

  /*
  *Identificador interno del paso origen del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
  private Long idPasoProceso;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;
  
  /*
  *Cantidad de documentos de determinada subcategoría que puede adjuntarse para dicho paso
  */
  private Integer caDocsAdjuntar;

  /*
  *Flag que indica si se valida la cantidad de documentos a adjuntar considerando solo los nuevos documentos, vale decir que no tienen fecha de envío. Los posibles valores son: "1" = Sí y "0" = No, considera el total
  */
  private String flValidaSoloNuevos;
  
  /*
  *Flag que indica si se permite hacer modificaciones al documento cuando tenga fecha de envío. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flModificarEnviado;

  /*
  *Flag que indica si la PGIM se permite o no el adjuntado o inclusión manual de la subcategoría documental en el paso. Los posibles valores son: "1" = Sí y "0" = No.
  */
  private String flAdjuntadoManual;  

  /*
  *Flag que indica si se permite definir destinatarios.
  */
  private String flDefinirDestinatario;
   
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

  /*
    *Flag que indica si, en determinado paso, la subcategoría permitirá ser asociado con un documento notificado. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flAsociarDocNotificado;
}