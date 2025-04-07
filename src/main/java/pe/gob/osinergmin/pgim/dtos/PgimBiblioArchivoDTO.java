package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
* DTO para la entidad PGIM_TD_BIBLIO_ARCHIVO: 
* @descripción: Archivo bibliográfico
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 07/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimBiblioArchivoDTO {

  /*
  *Identificador interno del archivo bibliográfico. Secuencia: PGIM_SEQ_BIBLIO_ARCHIVO
  */
  private Long idBiblioArchivo;

  /*
  *Identificador interno del documento bibliográfico. Tabla padre: PGIM_TD_BIBLIO_DOCUMENTO
  */
  private Long idBiblioDocumento;

  /*
  *Número del archivo Alfresco
  */
  private String nuArchivoEcm;

  /*
  *Número de la carpeta Alfresco que contiene al archivo en Alfresco
  */
  private String nuCarpetaEcm;

  /*
  *Nombre del archivo bibliográfico retornado luego del procesamiento por parte de la ApiAlfresco
  */
  private String noBibilioArchivo;

  /*
  *Valor que permite describir la naturaleza y el formato del archivo. Multipurpose Internet Mail Extensions = Extensiones multipropósito de correo de internet
  */
  private String noTipoMime;

  /*
  *Descripción del motivo de la eliminación del archivo bibliográfico. Solo tiene valor cuando el valor de la columna ES_REGISTRO = ''0'', caso contrario debe tener el valor NULL
  */
  private String deMotivoEliminacion;

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
   * Número del documento
   */
  private String descNuDocumento;
  
  /*
   * Nombre de la categoría del documento
   */
  private String descNoCategoriaDocumento;
  
  /*
   * Código de la categoría del documento
   */
  private String descCoCategoriaDocumento;
  
  /*
   * Nombre de la subcategoría del documento
   */
  private String descNoSubcatDocumento;
  
  /*
   * Código de la subcategoría del documento
   */
  private String descCoSubcatDocumento;
  
  /*
   * Asunto del documento
   */
  private String descDeAsuntoDocumento;
  
  /*
   * Nombre del tipo de medio de ingreso
   */
  private String descNoTipoMedioIngreso;
  
  /*
   * Fecha de emisión del documento
   */
  private Date descFeEmisionDocumento;
  
  /*
   * Tipo de registro: 'D' = Documento , 'A' = Archivo
   */ 
  private String descTipoRegistro;
  
  /*
   * Nombre del emisor del documento
   */ 
  private String descNoEmisorDocumento;
  
  /*
   * Documento de identidad del emisor del documento
   */ 
  private String descCoDiEmisorDocumento;
  
  /*
   * Cantidad de documentos total 
   */
  private Integer descCantDocsTotal;
  
  /*
   * Lista de entidades de negocio del documento
   */ 
  private List<PgimBiblioEntidadDTO> lstPgimBiblioEntidadDTO;


}