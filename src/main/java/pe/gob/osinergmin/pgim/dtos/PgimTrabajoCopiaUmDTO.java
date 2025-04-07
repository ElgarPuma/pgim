package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TZ_TRABAJO_COPIA_UM: 
* @descripción: Trabajo de copia de los datos clave de las unidades mineras
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTrabajoCopiaUmDTO {

  /*
  *Identificador interno del trabajo de copia de datos de las unidades mineras. Secuencia: PGIM_SEQ_TRABAJO_COPIA_UM
  */
  private Long idTrabajoCopiaUm;

  /*
  *Fecha de copia mensual. La copia se debe realizar a las 23:55 del último día del mes
  */
  private Date feCopiaUm;

  /*
  *Fecha de copia mensual. La copia se debe realizar a las 23:55 del último día del mes
  */
  private String feCopiaUmDesc;

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
  *Periodo de la copia de las unidades mineras
  */
  private String descPeriodoCopiaUm;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}