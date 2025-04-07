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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_CRITERIO_SPRVSION: 
* @descripción: Configuración de criterios de la matriz de supervisión tomada para una supervisión en particular
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_CRITERIO_SPRVSION")
@Data
@NoArgsConstructor
public class PgimCriterioSprvsion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular. Secuencia: PGIM_SEQ_CRITERIO_SPRVSION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CRITERIO_SPRVSION")
   @SequenceGenerator(name = "PGIM_SEQ_CRITERIO_SPRVSION", sequenceName = "PGIM_SEQ_CRITERIO_SPRVSION", allocationSize = 1)
   @Column(name = "ID_CRITERIO_SPRVSION", nullable = false)
  private Long idCriterioSprvsion;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno del criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_CRITERIO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MATRIZ_CRITERIO", nullable = false)
  private PgimMatrizCriterio pgimMatrizCriterio;

  /*
  *Código del criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_CRITERIO", nullable = false)
  private String coMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
   @Column(name = "DE_MATRIZ_CRITERIO", nullable = false)
  private String deMatrizCriterio;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE_MATRIZ_CRITERIO", nullable = false)
  private String esVigenteMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
   @Column(name = "DE_BASE_LEGAL", nullable = false)
  private String deBaseLegal;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN_CRITERIO", nullable = true)
  private Long nuOrdenCriterio;

  /*
  *Identificador interno del grupo de criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_GRPO_CRTRIO
  */
   @Column(name = "ID_MATRIZ_GRPO_CRTRIO", nullable = false)
  private Long idMatrizGrpoCrtrio;

  /*
  *Identificador interno de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_SUPERVISION
  */
   @Column(name = "ID_MATRIZ_SUPERVISION", nullable = false)
  private Long idMatrizSupervision;

  /*
  *Código del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_GRPO_CRTRIO", nullable = false)
  private String coMatrizGrpoCrtrio;

  /*
  *Nombre del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "NO_MATRIZ_GRPO_CRTRIO", nullable = false)
  private String noMatrizGrpoCrtrio;

  /*
  *Número de orden en la presentación de grupo de supervisión
  */
   @Column(name = "NU_ORDEN_GRPO_CRTRIO", nullable = true)
  private Long nuOrdenGrpoCrtrio;

  /*
  *Flag que indica si el grupo de criterio es visible. Los posibles valores son: "1" = Visible y "0" = No visible
  */
   @Column(name = "ES_VISIBLE_GRPO_CRTRIO", nullable = false)
  private String esVisibleGrpoCrtrio;
   
  /*
  *Identificador interno de la instancia de paso en la que se agrega el criterio manualmente a la fiscalización. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = true)
  private PgimInstanciaPaso pgimInstanciaPaso;

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
  *Descripción del hecho constatado
  */
 @Transient
  private String descDeHechoConstatado;

  /*
  *Descripción del complemento de la observación
  */
 @Transient
  private String descDeComplementoObservacion;

  /*
  *Descripción del sustento
  */
 @Transient
  private String descDeSustento;

  /*
  *Identificador interno del tipo de cumplimiento
  */
 @Transient
  private Long descIdtipoCumplimiento;


}