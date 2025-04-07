package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_ENTIDAD_NEGOCIO: 
* @descripción: Entidad de negocio
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 07/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEntidadNegocioDTO {

  /*
  *Identificador interno de la entidad de negocio. Secuencia: PGIM_SEQ_ENTIDAD_NEGOCIO
  */
  private Long idEntidadNegocio;

  /*
  *Número del documento bibliográfico
  */
  private String noTablaEntidadNegocio;

  /*
  *Descripcion de la etiqueta del tipo de entidad
  */
  private String deEtiquetaTablaNegocio;

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