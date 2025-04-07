package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.math.BigDecimal;

import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_MATRIZ_SUPERVISION_AUX: 
* @descripción: Vista para obtener la lista de la matriz de supervisión de campo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_MATRIZ_SUPERVISION_AUX")
@Data
@NoArgsConstructor
public class PgimMatrizSupervisionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VC_MATRIZ_SUPERVISION_AUX:  Secuencia: PGIM_SEQ_ABC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ABC")
   @SequenceGenerator(name = "PGIM_SEQ_ABC", sequenceName = "PGIM_SEQ_ABC", allocationSize = 1)
   @Column(name = "ID_MATRIZ_SUPERVISION_AUX", nullable = false)
  private Long idMatrizSupervisionAux;

  /*
  *ID de la tabla PGIM_TC_CRITERIO_SPRVSION (configuración de criterios de MS por supervisión específica)
  */
   @Column(name = "ID_CRITERIO_SPRVSION", nullable = true)
  private Long idCriterioSprvsion;

  /*
  *Identificador interno de la Especialidad
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *ID de la matriz de supervisión
  */
   @Column(name = "ID_MATRIZ_SUPERVISION", nullable = true)
  private Long idMatrizSupervision;

  /*
  *Descripción de la matriz de supervisión
  */
   @Column(name = "DE_MATRIZ_SUPERVISION", nullable = true)
  private String deMatrizSupervision;

  /*
  *ID del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "ID_MATRIZ_GRPO_CRTRIO", nullable = true)
  private Long idMatrizGrpoCrtrio;

  /*
  *Código del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_GRPO_CRTRIO", nullable = true)
  private String coMatrizGrpoCrtrio;

  /*
  *Nombre del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "NO_MATRIZ_GRPO_CRTRIO", nullable = true)
  private String noMatrizGrpoCrtrio;

  /*
  *Número de orden en la presentación de grupo de supervisión
  */
   @Column(name = "NU_ORDEN_GRPO_CRTRIO", nullable = true)
  private Long nuOrdenGrpoCrtrio;

  /*
  *Identificador del criterio de la matriz de supervisión
  */
   @Column(name = "ID_MATRIZ_CRITERIO", nullable = true)
  private Long idMatrizCriterio;

  /*
  *Código del criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_CRITERIO", nullable = true)
  private String coMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
   @Column(name = "DE_MATRIZ_CRITERIO", nullable = true)
  private String deMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
   @Column(name = "DE_BASE_LEGAL", nullable = true)
  private String deBaseLegal;

  /*
  *Identificador interno de la supervisión
  */
   @Column(name = "ID_SUPERVISION", nullable = true)
  private Long idSupervision;

  /*
  *Cantidad de hechos constatados por criterio de la matriz de supervisión - Registrado en la fase de supervisión y post-supervisión
  */
   @Column(name = "CANTIDAD_HC_SUPERVISION", nullable = true)
  private BigDecimal cantidadHcSupervision;

  /*
  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO)  - Registrado en la fase de supervisión y post-supervisión
  */
   @Column(name = "CODIGO_CUMPLE_SUPERVISION", nullable = true)
  private Long codigoCumpleSupervision;

  /*
  *Descripción del tipo de cumplimiento - Registrado en la fase de supervisión y post-supervisión
  */
   @Column(name = "DESCRIPCION_CUMPLE_SUPERVISION", nullable = true)
  private String descripcionCumpleSupervision;

  /*
  *Cantidad de hechos constatados por criterio de la matriz de supervisión - Registrado en la fase de aprobación de resultados
  */
   @Column(name = "CANTIDAD_HC_APROBADO", nullable = true)
  private BigDecimal cantidadHcAprobado;

  /*
  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO) - Registrado en la fase de aprobación de resultados
  */
   @Column(name = "CODIGO_CUMPLE_APROBADO", nullable = true)
  private Long codigoCumpleAprobado;

  /*
  *Descripción del tipo de cumplimiento - Registrado en la fase de aprobación de resultados
  */
   @Column(name = "DESCRIPCION_CUMPLE_APROBADO", nullable = true)
  private String descripcionCumpleAprobado;

  /*
  *Número de orden en la presentación del criterio
  */
   @Column(name = "NU_ORDEN_CRITERIO", nullable = true)
  private Long nuOrdenCriterio;

  /**
   * Descripción de la obligación normativa
   */
  @Column(name = "DE_NORMA", nullable = true)
  private String deNorma;
  
  /**
   * Descripción de ítem de tipificación
   */
  @Column(name = "DE_TIPIFICACION", nullable = true)
  private String deTipificacion;
  
  /**
   *ID de la instancia de paso en la que se agrega el criterio a la fiscalización
   */
   @Column(name = "ID_INSTANCIA_PASO_CRITERIO", nullable = true)
  private Long idInstanciaPasoCriterio;
   
  /**
   *ID del paso de proceso en el que se agrega el criterio a la fiscalización
   */
   @Column(name = "ID_PASO_PROCESO_AGREGO_CS", nullable = true)
  private Long idPasoProcesoAgregoCs;

  /*
  *Cantidad de hechos constatados por criterio de la matriz de supervisión - Valor final a mostrar
  */
 @Transient
  private BigDecimal cantidadHc;

  /*
  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO) - Valor final a mostrar
  */
 @Transient
  private Long codigoCumple;

  /*
  *Descripción del tipo de cumplimiento - Valor final a mostrar
  */
 @Transient
  private String descripcionCumple;


}