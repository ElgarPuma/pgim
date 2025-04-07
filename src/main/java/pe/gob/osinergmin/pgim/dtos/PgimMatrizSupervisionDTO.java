package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_MATRIZ_SUPERVISION: 
* @descripción: Matriz de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMatrizSupervisionDTO {

  /*
  *Identificador interno de la matriz de supervisión. Secuencia: PGIM_SEQ_MATRIZ_SUPERVISION
  */
  private Long idMatrizSupervision;

  /*
  *Identificador interno de la matriz de supervisión orgein desde la que se copió la presente matriz. Tabla padre: PGIM_TM_MATRIZ_SUPERVISION
  */
  private Long idMatrizSupervisionOrigen;

  /*
  *Identificador interno de la instancia del proceso PGIM. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
  private Long idConfiguracionBase;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Descripción de la matriz de supervisión
  */
  private String deMatrizSupervision;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Indicador lógico que determina si el cuadro de verificación se encuentra activa o no
  */
  private String flActivo;

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
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Nombre de la configuración base
  */
  private String descNoConfiguracionBase;

  /*
  *Identificador interno de la especialidad
  */
  private Long descIdEspecialidad;

  /*
  *Identificador interno del tipo de configuración base
  */
  private Long descIdTipoConfiguracionBase;

  /*
  *Descripción de la configuración basen
  */
  private String descDeConfiguracionBase;

  /*
  * Identificador interno de la configuración base
  */
  private Long descIdConfiguracionBase;

  /*
  * Identificador interno de la supervisión
  */
  private Long descIdSupervision;

  /*
  * Indicador lógico sobre si el cuadro de verificación es compatible con la la configuración base
  */
  private String descEsCompatibleConSupervision;

  /*
   * Descriptor del grupo de norma: 0: Cuadro de tipificación, 1: Obligación normativa
   */
  private String descNoGrupoNorma;
  
  /*
   * Descriptor del identificador interno del tipo de norma
   */
  private String descCoTipoNorma;
  
  /*
   * Descriptor del nombre corto (número de la norma) de la norma
   */
  private String descNoCortoNorma;
  
  /*
   * Descriptor del nombre de la norma
   */
  private String descNoNorma;
  
  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}