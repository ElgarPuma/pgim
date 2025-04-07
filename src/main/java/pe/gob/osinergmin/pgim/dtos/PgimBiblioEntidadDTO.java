package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_BIBLIO_ENTIDAD: 
* @descripción: Documento por entidad de negocio
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 07/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimBiblioEntidadDTO {

  /*
  *Identificador interno del documento por entidad de negocio. Secuencia: PGIM_SEQ_BIBLIO_ENTIDAD
  */
  private Long idBiblioEntidad;

  /*
  *Identificador interno de la entidad de negocio. Tabla padre: PGIM_TD_BIBLIO_DOCUMENTO
  */
  private Long idEntidadNegocio;

  /*
  *Identificador interno del documento bibliográfico. Tabla padre: PGIM_TD_BIBLIO_DOCUMENTO
  */
  private Long idBiblioDocumento;

  /*
  *Identificador interno del registro de la entidad de negocio asociado al documento bibliográfico. Para conocer a qué tabla corresponde este registro se debe consultar en la columna respectiva de la relación PGIM_TD_BIBLIO_ENTIDAD.NO_TABLA_ENTIDAD_NEGOCIO
  */
  private Long idRegistroEntidadNegocio;

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
  * descriptor del nombre de entidad de negocio (nombre del AS o UM o Componente minero)
  */
  private String descNoEntidadNegocio;

  /**
   * descriptor del nombre del tipo de entidad de negocio
   */
  private String descNoTipoEntidadNegocio;

}