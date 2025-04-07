package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
* DTO para la entidad PGIM_TC_CRITERIO_SPRVSION: 
* @descripción: Configuración de criterios de la matriz de supervisión tomada para una supervisión en particular
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCriterioSprvsionDTO {

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular. Secuencia: PGIM_SEQ_CRITERIO_SPRVSION
  */
  private Long idCriterioSprvsion;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno del criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_CRITERIO
  */
  private Long idMatrizCriterio;

  /*
  *Código del criterio de la matriz de supervisión
  */
  private String coMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
  private String deMatrizCriterio;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String esVigenteMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
  private String deBaseLegal;

  /*
  *Número de orden en la presentación
  */
  private Long nuOrdenCriterio;

  /*
  *Identificador interno del grupo de criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_GRPO_CRTRIO
  */
  private Long idMatrizGrpoCrtrio;

  /*
  *Identificador interno de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_SUPERVISION
  */
  private Long idMatrizSupervision;

  /*
  *Código del grupo de criterio de la matriz de supervisión
  */
  private String coMatrizGrpoCrtrio;

  /*
  *Nombre del grupo de criterio de la matriz de supervisión
  */
  private String noMatrizGrpoCrtrio;

  /*
  *Número de orden en la presentación de grupo de supervisión
  */
  private Long nuOrdenGrpoCrtrio;

  /*
  *Flag que indica si el grupo de criterio es visible. Los posibles valores son: "1" = Visible y "0" = No visible
  */
  private String esVisibleGrpoCrtrio;
  
  /*
  *Identificador interno de la instancia de paso en la que se agrega el criterio manualmente a la fiscalización. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

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
  *Descripción del hecho constatado
  */
  private String descDeHechoConstatado;

  /*
  *Descripción del complemento de la observación
  */
  private String descDeComplementoObservacion;

  /*
  *Descripción del sustento
  */
  private String descDeSustento;

  /*
  *Identificador interno del tipo de cumplimiento
  */
  private Long descIdtipoCumplimiento;
  
  /*
  * Lista de obligaciones fiscalizables para actualización
  */
  private List<PgimOblgcnNrmaCrtrioDTO> listaObligacFiscalizable;
  
  /*
  *Identificador interno del rol de proceso para filtrar los hechos verificados del criterio de fiscalización en función de este campo
  */
  private Long descIdRolProceso;
  
  /*
  *Identificador interno de la instancia de paso actual del objeto de trabajo del criterio de supervisión en cuestión
  */
  private Long descIdInstanciaPasoActual;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;
  
  /**
   * Id de la especialidad
   */
  private Long descIdEspecialidad;

  /**
   * Id de la unidad minera
   */
  private Long descIdUnidadMinera;
}