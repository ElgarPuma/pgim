package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.List;

import javax.persistence.Lob;
import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_HECHO_CONSTATADO: 
* @descripción: Hecho constatado de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_HECHO_CONSTATADO")
@Data
@NoArgsConstructor
public class PgimHechoConstatado implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del hecho constatado de la supervisión. Secuencia: PGIM_SEQ_HECHO_CONSTATADO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_HECHO_CONSTATADO")
   @SequenceGenerator(name = "PGIM_SEQ_HECHO_CONSTATADO", sequenceName = "PGIM_SEQ_HECHO_CONSTATADO", allocationSize = 1)
   @Column(name = "ID_HECHO_CONSTATADO", nullable = false)
  private Long idHechoConstatado;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular. Tabla padre: PGIM_TC_CRITERIO_SPRVSION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CRITERIO_SPRVSION", nullable = false)
  private PgimCriterioSprvsion pgimCriterioSprvsion;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = false)
  private PgimInstanciaPaso pgimInstanciaPaso;

  /*
  *Tipo de cumplimiento del hecho constatado. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CUMPLIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CUMPLIMIENTO", nullable = false)
  private PgimValorParametro tipoCumplimiento;

  /*
  *Identificador interno del rol del proceso. Tabla foránea PGIM_TM_ROL_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ROL_PROCESO", nullable = false)
  private PgimRolProceso pgimRolProceso;

  /*
  *Identificador interno del hecho constatado origen de la supervisión. Tabla: PGIM_TC_HECHO_CONSTATADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_HECHO_CONSTATADO_ORIGEN", nullable = true)
  private PgimHechoConstatado hechoConstatadoOrigen;

  /*
  *Identificador interno del hecho constatado reemplazante. Tabla: PGIM_TC_HECHO_CONSTATADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_HECHO_CONSTATADO_RMPLZNTE", nullable = true)
  private PgimHechoConstatado hechoConstatadoRmplznte;

  /*
  *Descripción del hecho constatado
  */
   @Column(name = "DE_HECHO_CONSTATADO_T", nullable = true)
@Lob
  private String deHechoConstatadoT;

  /*
  *Complemento u observación para el hecho constatado, que se utilizará para generar el documento impreso de la matriz de supervisión.
  */
   @Column(name = "DE_COMPLEMENTO_OBSERVACION", nullable = true)
  private String deComplementoObservacion;

  /*
  *Sustento del hecho constatado
  */
   @Column(name = "DE_SUSTENTO_T", nullable = true)
@Lob
  private String deSustentoT;

  /*
  *Flag que indica si el hecho constatado ha sido incluido en el acta de supervisión. Los posibles valores son: "1" = Incluido y "0" = No incluido
  */
   @Column(name = "FL_INCLUIDO_ACTA_SUPERVISION", nullable = true)
  private String flIncluidoActaSupervision;

  /*
  *Comentario del personal responsable de Osinergmin (Especialista técnico o legal)
  */
   @Column(name = "DE_COMENTARIO_OSI_T", nullable = true)
@Lob
  private String deComentarioOsiT;

  /*
  *Indica si el registro de hecho constatado es vigente o histórico. Valores: V=Vigente; H=Histórico
  */
   @Column(name = "ES_VIGENTE", nullable = false)
  private String esVigente;

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

  /*
  *Descripción del tipo de cumplimiento.
  */
 @Transient
  private String descTipoCumplimiento;

  /*
  *Lista de Obligaciones de obligaciones
  */
 @Transient
  private List<PgimObligacionNormaAux> descListaObligaciones;

  /*
  *ID de la fase del proceso de supervisión actual
  */
 @Transient
  private Long descFaseProcesoSupervisionActual;

  /*
  *Nombre valor parametro
  */
 @Transient
  private String descNoValorParametro;

  /*
  *Descripción de la obligación normativa
  */
 @Transient
  private String descDeObligacionNormativa;

  /*
  *Descripción del ítem de la norma
  */
 @Transient
  private String descDeNormaItem;

  /*
  *Descripción del incumplimiento detectado
  */
 @Transient
  private String descIncumplimientoDetectado;

  /*
  *ID de la fase del proceso de supervisión donde se ha ubicado al componente angular que muestra al hecho constatado
  */
 @Transient
  private Long descFaseProcesoSupervisionUbicado;

  /*
  *ID de la fase del proceso de supervisión donde se generó al hecho constatado
  */
 @Transient
  private Long descFaseProcesoSupervision;

  /*
  *Nombre del rol en el proceso
  */
 @Transient
  private String descNoRolProceso;

  /*
  *Nombre del responsable del registro o actualización del hecho constatado
  */
 @Transient
  private String descResponsable;

  /*
  *Fecha y hora histórico
  */
 @Transient
  private Date descFeHistorico;

  /*
  *Identificador del hecho constatado de la supervisión a reemplazar
  */
 @Transient
  private Long descIdHechoConstatadoSupervision;

  /*
  *Flag que indica si el hecho constatado fue registrado por un especialista técnico o legal de Osinergmin
  */
 @Transient
  private boolean descFlEspecialistaOsi;

  /*
  *Nombre de la fase auxiliar del hecho constatado
  */
 @Transient
  private String descNoFaseProcesoAux;


}