package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad PGIM_TH_HECHO_CNSTTDO_FASE: 
* @descripción: Histórico de hechos constatados por fase
*
* @author: gdelaguila
* @version: 1.0
* @fecha_de_creación: 06/02/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimHechoCnsttdoFaseDTO {
	
	
	/*
	 *Identificador interno del histórico de hechos constatados. Secuencia: PGIM_SEQ_HECHO_CONSTATADO_HIST
	 */
	private Long idHechoConstatadoHist;
	   
	/*
	 *Fecha de generación del registro histórico
	 */
	private Date feGeneracion;
	   
	/*
	 *Identificador de la fase a la cual pertenece el registro
	 */
	private Long idFaseMuestra;
	
	/*
	 *Identificador del hecho constatado
	 */
	private Long idHechoConstatado;

	 
	 /*
	  *Identificador del criterio de supervisión
	  */
	private Long idCriterioSprvsion;
	 
	 
	 /*
	  *Código de criterio de matriz 
	  */
	private String coMatrizCriterio;
	  
	   
	 /*
	  *Código de criterio de matriz 
	  */
	private Long nuOrdenGrpoCrtrio;

	 /*
	  *Número de orden de criterio 
	  */
	private Long nuOrdenCrtrio;
	 
	  /*
	  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
	  */
	private Long idSupervision;

	  /*
	  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
	  */
	private Long idInstanciaPaso;

	  /*
	   *Descripción del hecho constatado
	   */
	private String deHechoConstatado;
	  
	  
	  /*
	   *Sustento del hecho constatado
	   */
	private String deSustento;
	  
	  
	  /*
	  *Identificador del tipo de cumplimiento
	  */
	private Long idTipoCumplimiento;
	  
	  
	  /*
	   *Descripción del tipo de cumplimiento 
	   */
	private String deTipoCumplimiento;
	  
	  /*
	   *Código de tipo de cumplimiento 
	   */
	private Long coTipoCumplimiento;
	  
	  

	  /*
	  *Identificador interno del rol del proceso. Tabla foránea PGIM_TM_ROL_PROCESO
	  */	
	private Long idRolProceso;

	  /*
	  *Identificador interno del hecho constatado origen de la supervisión. Tabla: PGIM_TC_HECHO_CONSTATADO
	  */
	private Long idHechoConstatadoOrigen;

	  /*
	  *Identificador interno del hecho constatado reemplazante. Tabla: PGIM_TC_HECHO_CONSTATADO
	  */
	private Long idHechoConstatadoRmplznte;
  

	  /*
	  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión.
	  */
	private String deComplementoObservacion;

	  
	  /*
	  *Flag que indica si el hecho constatado ha sido incluido en el acta de supervisión. Los posibles valores son: "1" = Incluido y "0" = No incluido
	  */
	private String flIncluidoActaSupervision;

	  
	  /*
	  *Indica si el registro de hecho constatado es vigente o histórico. Valores: V=Vigente; H=Histórico
	  */
	private String esVigente;
	   
	  
	  /*
	   *Identificador del rol del proceso
	   */
	private Long idFaseProceso;
	  	  
	/*
	 *Identificador del hecho constatado (personal OSINERGMIN)
	 */
	private Long idHechoConstatadoOsi;
	
	
	/*
	 *Descripción del hecho constatado (personal OSINERGMIN)
	 */
	private String deHechoConstatadoOsi;
	
	/*
	 *Complemento de la observación (personal OSINERGMIN)
	 */
	private String deComplementoObservacionOsi;
	
	/*
	 *Descripción del sustento del hecho constatado (personal OSINERGMIN)
	 */
	private String deSustentoOsi;
	
	/*
	 *Identificador del tipo de cumplimiento (personal OSINERGMIN)
	 */
	private Long idTipoCumplimientoOsi;
	  
	  
	/*
	 *Descripción del tipo de cumplimiento (personal OSINERGMIN)
	 */
	private String deTipoCumplimientoOsi;
	  
	/*
	 *Código de tipo de cumplimiento (personal OSINERGMIN)
	 */
	private Long coTipoCumplimientoOsi;
	
	
	/*
	 *Comentario (personal OSINERGMIN)
	 */
	private String deComentarioOsi;
	
	/*
	 *Identificador de la fase del proceso (personal OSINERGMIN)
	 */
	private Long idFaseProcesoOsi;
	  
	/*
	 *Identificador del hecho constatado (origen) - personal OSINERGMIN
	 */
	private Long idHechoConstatadoOrigenOsi;

	/*
	 * Identificador del hecho constatado (reemplazante) - personal OSINERGMIN
	 */
	private Long idHechoConstatadoRmplznteOsi;
	

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

}
