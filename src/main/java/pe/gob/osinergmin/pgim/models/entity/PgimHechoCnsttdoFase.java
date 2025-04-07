package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TH_HECHO_CNSTTDO_FASE: 
* @descripción: Histórico de hechos constatados por fase
*
* @author: gdelaguila
* @version 1.0
* @fecha_de_creación: 06/02/2023
*/
@Entity
@Table(name = "PGIM_TH_HECHO_CNSTTDO_FASE")
@Data
@NoArgsConstructor
public class PgimHechoCnsttdoFase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 *Identificador interno del histórico de hechos constatados. Secuencia: PGIM_SEQ_HECHO_CONSTATADO_HIST
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_HECHO_CONSTATADO_HIST")
	@SequenceGenerator(name = "PGIM_SEQ_HECHO_CONSTATADO_HIST", sequenceName = "PGIM_SEQ_HECHO_CONSTATADO_HIST", allocationSize = 1)
	@Column(name = "ID_HECHO_CONSTATADO_HIST", nullable = false)
	private Long idHechoConstatadoHist;
	   
	   
	/*
	 *Fecha de generación del registro histórico
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FE_GENERACION", nullable = false)
	private Date feGeneracion;
	   
	/*
	 *Identificador de la fase a la cual pertenece el registro
	 */
	@Column(name = "ID_FASE_MUESTRA", nullable = false)
	private Long idFaseMuestra;
	
	/*
	 *Identificador del hecho constatado
	 */
	 @Column(name = "ID_HECHO_CONSTATADO", nullable = false)
	 private Long idHechoConstatado;

	 
	 /*
	  *Identificador del criterio de supervisión
	  */
	 @Column(name = "ID_CRITERIO_SPRVSION", nullable = false)
	 private Long idCriterioSprvsion;
	 
	 
	 /*
	  *Código de criterio de matriz 
	  */
	   @Column(name = "CO_MATRIZ_CRITERIO", nullable = true)
	  private String coMatrizCriterio;
	  
	   
	 /*
	  *Código de criterio de matriz 
	  */
	 @Column(name = "NU_ORDEN_GRPO_CRTRIO", nullable = true)
	 private Long nuOrdenGrpoCrtrio;

	 /*
	  *Número de orden de criterio 
	  */
	 @Column(name = "NU_ORDEN_CRITERIO", nullable = true)
	 private Long nuOrdenCrtrio;
	 
	  /*
	  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
	  */	   
	 @Column(name = "ID_SUPERVISION", nullable = false)
	 private Long idSupervision;

	  /*
	  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
	  */
	 @Column(name = "ID_INSTANCIA_PASO", nullable = false)
	  private Long idInstanciaPaso;

	  /*
	   *Descripción del hecho constatado
	   */
	  @Column(name = "DE_HECHO_CONSTATADO", nullable = true)
	  @Lob
	  private String deHechoConstatado;
	  
	  
	  /*
	   *Sustento del hecho constatado
	   */
	  @Column(name = "DE_SUSTENTO", nullable = true)
	  @Lob
	  private String deSustento;
	  
	  
	  /*
	  *Identificador del tipo de cumplimiento
	  */
	  @Column(name = "ID_TIPO_CUMPLIMIENTO", nullable = false)
	  private Long idTipoCumplimiento;
	  
	  
	  /*
	   *Descripción del tipo de cumplimiento 
	   */
	  @Column(name = "DE_TIPO_CUMPLIMIENTO", nullable = true)
	  private String deTipoCumplimiento;
	  
	  /*
	   *Código de tipo de cumplimiento 
	   */
	  @Column(name = "CO_TIPO_CUMPLIMIENTO", nullable = true)
	  private Long coTipoCumplimiento;
	  
	  

	  /*
	  *Identificador interno del rol del proceso. Tabla foránea PGIM_TM_ROL_PROCESO
	  */	   
	  @Column(name = "ID_ROL_PROCESO", nullable = false)
	  private Long idRolProceso;

	  /*
	  *Identificador interno del hecho constatado origen de la supervisión. Tabla: PGIM_TC_HECHO_CONSTATADO
	  */
	  @Column(name = "ID_HECHO_CONSTATADO_ORIGEN", nullable = true)
	  private Long idHechoConstatadoOrigen;

	  /*
	  *Identificador interno del hecho constatado reemplazante. Tabla: PGIM_TC_HECHO_CONSTATADO
	  */
	  @Column(name = "ID_HECHO_CONSTATADO_RMPLZNTE", nullable = true)
	  private Long idHechoConstatadoRmplznte;
  

	  /*
	  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión.
	  */
	   @Column(name = "DE_COMPLEMENTO_OBSERVACION", nullable = true)
	  private String deComplementoObservacion;

	  
	  /*
	  *Flag que indica si el hecho constatado ha sido incluido en el acta de supervisión. Los posibles valores son: "1" = Incluido y "0" = No incluido
	  */
	   @Column(name = "FL_INCLUIDO_ACTA_SUPERVISION", nullable = true)
	  private String flIncluidoActaSupervision;

	  
	  /*
	  *Indica si el registro de hecho constatado es vigente o histórico. Valores: V=Vigente; H=Histórico
	  */
	   @Column(name = "ES_VIGENTE", nullable = false)
	  private String esVigente;
	   
	  
	  /*
	   *Identificador del rol del proceso
	   */
	  @Column(name = "ID_FASE_PROCESO", nullable = true)
	  private Long idFaseProceso;
	  
	  
	  
	  
	  
	/*
	 *Identificador del hecho constatado (personal OSINERGMIN)
	 */
	@Column(name = "ID_HECHO_CONSTATADO_OSI", nullable = false)
	private Long idHechoConstatadoOsi;
	
	
	/*
	 *Descripción del hecho constatado (personal OSINERGMIN)
	 */
	@Column(name = "DE_HECHO_CONSTATADO_OSI", nullable = true)
	@Lob
	private String deHechoConstatadoOsi;
	
	/*
	 *Complemento de la observación (personal OSINERGMIN)
	 */
	@Column(name = "DE_COMPLEMENTO_OBSERVACION_OSI", nullable = true)
	private String deComplementoObservacionOsi;
	
	/*
	 *Descripción del sustento del hecho constatado (personal OSINERGMIN)
	 */
	@Column(name = "DE_SUSTENTO_OSI", nullable = true)
	@Lob
	private String deSustentoOsi;
	
	/*
	 *Identificador del tipo de cumplimiento (personal OSINERGMIN)
	 */
	@Column(name = "ID_TIPO_CUMPLIMIENTO_OSI", nullable = false)
	private Long idTipoCumplimientoOsi;
	  
	  
	/*
	 *Descripción del tipo de cumplimiento (personal OSINERGMIN)
	 */
	@Column(name = "DE_TIPO_CUMPLIMIENTO_OSI", nullable = true)
	private String deTipoCumplimientoOsi;
	  
	/*
	 *Código de tipo de cumplimiento (personal OSINERGMIN)
	 */
	@Column(name = "CO_TIPO_CUMPLIMIENTO_OSI", nullable = true)
	private Long coTipoCumplimientoOsi;
	
	
	/*
	 *Comentario (personal OSINERGMIN)
	 */
	@Column(name = "DE_COMENTARIO_OSI", nullable = true)
	@Lob
	private String deComentarioOsi;
	
	/*
	 *Identificador de la fase del proceso (personal OSINERGMIN)
	 */
	@Column(name = "ID_FASE_PROCESO_OSI", nullable = true)
	private Long idFaseProcesoOsi;
	  
	/*
	 *Identificador del hecho constatado (origen) - personal OSINERGMIN
	 */
	@Column(name = "ID_HECHO_CONSTATADO_ORIGEN_OSI", nullable = true)
	private Long idHechoConstatadoOrigenOsi;

	/*
	 * Identificador del hecho constatado (reemplazante) - personal OSINERGMIN
	 */
	@Column(name = "ID_HECHO_CNSTTDO_RMPLZNTE_OSI", nullable = true)
	private Long idHechoConstatadoRmplznteOsi;
	

	/*
	 *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
	 */
	   @Column(name = "ES_REGISTRO", nullable = false)
	  private String esRegistro;

	  /*
	  *Usuario creador
	  */
	   @Column(name = "US_CREACION", nullable = false)
	  private String usCreacion;

	  /*
	  *Terminal de creación
	  */
	   @Column(name = "IP_CREACION", nullable = false)
	  private String ipCreacion;

	  /*
	  *Fecha y hora de creación
	  */
	   @Temporal(TemporalType.TIMESTAMP)
	   @Column(name = "FE_CREACION", nullable = false)
	  private Date feCreacion;

	  /*
	  *Usuario modificador
	  */
	   @Column(name = "US_ACTUALIZACION", nullable = true)
	  private String usActualizacion;

	  /*
	  *Terminal de modificación
	  */
	   @Column(name = "IP_ACTUALIZACION", nullable = true)
	  private String ipActualizacion;

	  /*
	  *Fecha y hora de modificación
	  */
	   @Temporal(TemporalType.TIMESTAMP)
	   @Column(name = "FE_ACTUALIZACION", nullable = true)
	  private Date feActualizacion;

}
