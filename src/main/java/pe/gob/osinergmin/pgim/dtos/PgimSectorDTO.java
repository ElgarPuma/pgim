package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
* DTO para la entidad PGIM_TM_SECTOR: 
* Sector
*
* Autor: 
* Versión: 1.0
* Creado el: 19/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSectorDTO {

  /***
  *Identificador interno del sector. Secuencia: PGIM_SEQ_SECTOR
  */
  private Long idSector;

  /***
  *Nombre del sector
  */
  private String noSector;

  /***
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /***
  *Usuario creador
  */
  private String usCreacion;

  /***
  *Terminal de creación
  */
  private String ipCreacion;

  /***
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /***
  *Usuario modificador
  */
  private String usActualizacion;

  /***
  *Terminal de modificación
  */
  private String ipActualizacion;

  /***
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
  private Long exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}