package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_PROCESO: 
* @descripción: Proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimProcesoDTO {

  /*
  *Identificador interno del proceso PGIM. Secuencia: PGIM_SEQ_PROCESO
  */
  private Long idProceso;

  /*
  *Nombre del proceso PGIM
  */
  private String noProceso;

  /*
  *Descripción del proceso PGIM
  */
  private String deProceso;

  /*
  *Identificador interno del proceso Siged. Este valor se utiliza para crear un nuevo expediente en el Siged a partir de las acciones de adjuntar o incluir lanzadas desde la PGIM. Si es que tiene no tiene valor entonces no podrá crearse un nuevo expediente desde la PGIM
  */
  private Long coProcesoSiged;

   /*
  *Estado que permite conocer si el proceso es o no seleccionable para la generación de indicadores. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flIndicador;

  /*
  *Identificador interno del subsector. Tabla padre: PGIM_TM_SUBSECTOR
  */
  private Long idSubsector;

  /*
   * Identificador interno del subsector. Tabla padre: PGIM_TM_SECTOR
   */
  private Long descIdSector;

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