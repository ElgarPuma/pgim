package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_MATRIZ_SUPERVISION_AUX: 
* @descripción: Vista para obtener la lista de la matriz de supervisión de campo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMatrizSupervisionAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VC_MATRIZ_SUPERVISION_AUX:  Secuencia: PGIM_SEQ_ABC
  */
  private Long idMatrizSupervisionAux;

  /*
  *ID de la tabla PGIM_TC_CRITERIO_SPRVSION (configuración de criterios de MS por supervisión específica)
  */
  private Long idCriterioSprvsion;

  /*
  *Identificador interno de la Especialidad
  */
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String noEspecialidad;

  /*
  *ID de la matriz de supervisión
  */
  private Long idMatrizSupervision;

  /*
  *Descripción de la matriz de supervisión
  */
  private String deMatrizSupervision;

  /*
  *ID del grupo de criterio de la matriz de supervisión
  */
  private Long idMatrizGrpoCrtrio;

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
  *Identificador del criterio de la matriz de supervisión
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
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
  private String deBaseLegal;

  /*
  *Identificador interno de la supervisión
  */
  private Long idSupervision;

  /*
  *Cantidad de hechos constatados por criterio de la matriz de supervisión - Registrado en la fase de supervisión y post-supervisión
  */
  private BigDecimal cantidadHcSupervision;

  /*
  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO)  - Registrado en la fase de supervisión y post-supervisión
  */
  private Long codigoCumpleSupervision;

  /*
  *Descripción del tipo de cumplimiento - Registrado en la fase de supervisión y post-supervisión
  */
  private String descripcionCumpleSupervision;

  /*
  *Cantidad de hechos constatados por criterio de la matriz de supervisión - Registrado en la fase de aprobación de resultados
  */
  private BigDecimal cantidadHcAprobado;

  /*
  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO) - Registrado en la fase de aprobación de resultados
  */
  private Long codigoCumpleAprobado;

  /*
  *Descripción del tipo de cumplimiento - Registrado en la fase de aprobación de resultados
  */
  private String descripcionCumpleAprobado;

  /*
  *Número de orden en la presentación del criterio
  */
  private Long nuOrdenCriterio;

  /*
  *Cantidad de hechos constatados por criterio de la matriz de supervisión - Valor final a mostrar
  */
  private BigDecimal cantidadHc;

  /*
  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO) - Valor final a mostrar
  */
  private Long codigoCumple;

  /*
  *Descripción del tipo de cumplimiento - Valor final a mostrar
  */
  private String descripcionCumple;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Descripción de la obligación normativa
   */
  private String deNorma;
  
  /**
   * Descripción de ítem de tipificación
   */
  private String deTipificacion;
  
  /**
   * ID de la instancia de paso en la que se agrega el criterio a la fiscalización
   */
  private Long idInstanciaPasoCriterio;
  
  /**
   * ID del paso de proceso en el que se agrega el criterio a la fiscalización
   */
  private Long idPasoProcesoAgregoCs;

  /**
   * Código de la supervisión para la validación de la eliminación de un cuadro de verificación
   */
  private String descCoSupervision;
}