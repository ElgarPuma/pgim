package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_INSTANCIA_SE_DOC: 
* @descripción: Correlativo de documentos por instancia de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInstanciaSeDocDTO {

  /*
  *Identificador interno de la instancia del proceso PGIM. Secuencia: PGIM_SEQ_INSTANCIA_SE_DOC
  */
  private Long idInstanciaSeDoc;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la subcategoría documental. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
  private Long idSubcatDocumento;

  /*
  *Número del último número de documento usado para la numeración de documentos de la subcategoría documental.
  */
  private Long seCorrelativo;

  /*
  *Número que hace referencia al correlativo de la instancia padre (contrato) usado para la numeración de documentos de la subcategoría documental.
  */
  private Long seCorrelativoInstancPadre;

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
  *Nombre de tabla de la instancia
  */
  private String descNoTablaInstancia;

  /*
  *Identificador del contrato relacionado con la instancia de proceso
  */
  private Long descIdContrato;

  /*
  *Identificador de la especialidad relacionada con la instancia de proceso
  */
  private Long descIdEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}