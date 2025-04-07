package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_INSTANCIA_PASO_DOC: 
* @descripción: Documentos de la instancia de paso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInstanciaPasoDocDTO {

  /*
  *Identificador interno del documento enviado en la instancia de paso del proceso. Secuencia: PGIM_SEQ_INSTANCIA_PASO_DOC
  */
  private Long idInstanciaPasoDoc;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Identificador interno del documento relacionado con el entregable. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *Fecha de la transición de paso del documento
  */
  private Date feTransicionPasoDoc;

  /*
  *Fecha de la transición de paso del documento
  */
  private String feTransicionPasoDocDesc;

  /*
  *Mensaje por cada documento que se envía
  */
  private String deMensaje;

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