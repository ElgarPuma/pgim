package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
* DTO para la entidad PGIM_TD_BIBLIO_DOCUMENTO: 
* @descripción: Documento bibliográfico
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 07/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimBiblioDocumentoDTO {

  /*
  *Identificador interno del documento bibliográfico. Secuencia: PGIM_SEQ_BIBLIO_DOCUMENTO
  */
  private Long idBiblioDocumento;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Tipo del medio de ingreso del documento bibliográfico. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_MEDIO_INGRESO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoMedioIngreso;

  /*
  *Identificador interno de la persona emisora del documento bibliográfico. Tabla padre: PGIM_TM_PERSONA
  */
  private Long idPersonaEmisora;

  /*
  *Número del documento bibliográfico
  */
  private String nuDocumento;

  /*
  *Asunto del documento bibliográfico
  */
  private String deAsuntoDocumento;

  /*
  *Sumilla del documento bibliográfico
  */
  private String deSumillaDocumento;

  /*
  *Fecha de emisión del documento bibliográfico
  */
  private Date feEmisionDocumento;

  /*
  *Fecha de emisión del documento bibliográfico
  */
  private String feEmisionDocumentoDesc;

  /*
  *Número del expediente Siged. Solo debe tener valor cuando el valor de la columna ID_TIPO_MEDIO_INGRESO está referido al medio de ingreso Siged, caso contrario deberá tener el valor NULL
  */
  private String nuExpedienteSiged;

  /*
  *Descripción del motivo de la eliminación del documento bibliográfico. Solo tiene valor cuando el valor de la columna ES_REGISTRO = ''0'', caso contrario debe tener el valor NULL
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
   * Id de la categoría del documento
   */
  private Long descIdCategoriaDocumento;
  
  /*
   * Fecha de inicio para filtrar los documentos respecto a su fecha de emisión
   */
  private Date descFeEmisionDesde;
  
  /*
   * Fecha de fin para filtrar los documentos respecto a su fecha de emisión
   */
  private Date descFeEmisionHasta;
  
  /*
   * Id del Agente fiscalizado para filtrar los documentos por dicha entidad de negocio 
   */
  private Long descIdAgenteSupervisado;
  
  /*
   * Id de la Unidad fiscalizable para filtrar los documentos por dicha entidad de negocio 
   */
  private Long descIdUnidadMinera;
  
  /*
   * Id del Componente minero para filtrar los documentos por dicha entidad de negocio 
   */
  private Long descIdComponenteMinero;

  /*
   * Lista de entidades de negocio del documento
   */ 
  private List<PgimBiblioEntidadDTO> lstPgimBiblioEntidadDTO;

  /*
  * Descriptor del nombre de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private String descNoSubcatDocumento;

  /*
  * Descriptor del nombre de la persona emisora del documento bibliográfico. Tabla padre: PGIM_TM_PERSONA
  */
  private String descNoPersonaEmisora;

}