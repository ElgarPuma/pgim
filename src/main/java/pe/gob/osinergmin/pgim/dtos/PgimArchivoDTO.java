package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_ARCHIVO: 
* @descripción: Archivo del documento del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimArchivoDTO {

  /*
  *Identificador interno del documento. Secuencia: PGIM_SEQ_ARCHIVO
  */
  private Long idArchivo;

  /*
  *Identificador interno del documento. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *Nombre del archivo con el que originalmente fue cargado
  */
  private String noOriginalArchivo;

  /*
  *Nuevo nombre del archivo con el que finalmente es cargado al Siged
  */
  private String noNuevoArchivo;

  /*
  *Secuencia utilizada para la conformación del nombre del archivo. Utiliza la secuencia PGIM_SEQ_GEN_ARCHIVO
  */
  private Long seArchivo;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo, "0" = Inactivo
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
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long descIdInstanciaProceso;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}