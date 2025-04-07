package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_MATRIZ_SPRVSION_HIST: 
* @descripción: Vista para obtener la lista de la matriz de supervisión de campo histórica por fase
*
* @author: gdelaguila
* @version 1.0
* @fecha_de_creación: 09/02/2023
*/
@Entity
@Table(name = "PGIM_VW_MATRIZ_SPRVSION_HIST")
@Data
@NoArgsConstructor
public class PgimMatrizSprvsionHist implements Serializable{

	  private static final long serialVersionUID = 1L;

	  /*
	  *Identificador interno de la vista PGIM_VW_MATRIZ_SPRVSION_HIST:  
	  */
	   @Id	   
	   @Column(name = "ID_MATRIZ_SPRVSION_HIST", nullable = false)
	  private Long idMatrizSprvsionHist;

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
	   *Número de orden en la presentación del criterio
	   */
	  @Column(name = "NU_ORDEN_CRITERIO", nullable = true)
	  private Long nuOrdenCriterio;
	   
	   
	  /*
	   *Identificador interno de la fase del proceso
	   */
	  @Column(name = "ID_FASE_PROCESO", nullable = true)
      private Long idFaseProceso;
	   
	   
	  /*
	  *Cantidad de hechos constatados por criterio de la matriz de supervisión
	  */
	   @Column(name = "CANTIDAD_HC", nullable = true)
	  private BigDecimal cantidadHc;

	  /*
	  *Código de tipo de cumplimiento (CO_CLAVE de la tabla PGIM_TP_VALOR_PARAMETRO)
	  */
	   @Column(name = "CODIGO_CUMPLE", nullable = true)
	  private Long codigoCumple;

	  /*
	  *Descripción del tipo de cumplimiento
	  */
	   @Column(name = "DESCRIPCION_CUMPLE", nullable = true)
	  private String descripcionCumple;

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
}
