package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_ROL_PROCESO: 
* @descripción: Rol del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRolProcesoDTO {

  /*
  *Identificador interno del rol del proceso PGIM. Secuencia: PGIM_SEQ_ROL_PROCESO
  */
  private Long idRolProceso;

  /*
  *Identificador interno del proceso. Tabla padre: PGIM_TM_PROCESO
  */
  private Long idProceso;

  /*
  *Identificador interno de la unidad orgánica que permite establecer a qué unidad orgánica se encuentra relacionado el rol 
  *para atender la lógica de numeración de documentos por encargatura. Table padre: PGIM_TM_UNIDAD_ORGANICA
  */
  private Long idUnidadOrganica;
  
  /*
  *Código del rol del proceso
  */
  private String coRolProceso;

  /*
  *Nombre del rol del proceso
  */
  private String noRolProceso;

  /*
  *Descripción del rol del proceso
  */
  private String deRolProceso;

  /*
  *Flag que indica solo lo puede fungir personal del Osinergmin. Posibles valores: "1" = "Solo Osinergmin", 0 = "Cualquiera" y "2" = "Solo supervisor"
  */
  private String flSoloOsinergmin;

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
  *Descripción del nombre de la unidad organica
  */
  private String descNoUnidadOrganica;

}