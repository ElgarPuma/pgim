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

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_INFRACCION: 
* @descripción: Infracción por obligación normativa de hecho constatado
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_INFRACCION")
@Data
@NoArgsConstructor
public class PgimInfraccion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la infracción asociada a la obligación normativa de la supervisión. Secuencia: PGIM_SEQ_INFRACCION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INFRACCION")
   @SequenceGenerator(name = "PGIM_SEQ_INFRACCION", sequenceName = "PGIM_SEQ_INFRACCION", allocationSize = 1)
   @Column(name = "ID_INFRACCION", nullable = false)
  private Long idInfraccion;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla padre: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OBLGCN_NRMTVA_HCHOC", nullable = false)
  private PgimOblgcnNrmtvaHchoc pgimOblgcnNrmtvaHchoc;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = true)
  private PgimInstanciaPaso pgimInstanciaPaso;

  /*
  *Identificador interno del PAS al que está asociada la infracción en el marco del flujo de trabajo de fiscalización. Tabla padre: PGIM_TC_PAS
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PAS", nullable = true)
  private PgimPas pgimPas;

  /*
  *Identificador interno de la infracción origen (supervisión). Tabla padre: PGIM_TD_INFRACCION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INFRACCION_ORIGEN", nullable = true)
  private PgimInfraccion infraccionOrigen;

  /*
  *Flag que indica si la infracción será incluida en el PAS o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_INCLUIR_EN_PAS", nullable = false)
  private String flIncluirEnPas;

  /*
  *Descripción de la decisión de incluir o no la infracción en el PAS
  */
   @Column(name = "DE_SUSTENTO", nullable = true)
  private String deSustento;

  /*
  *Fecha de la comisión o detección registrada por el Especialista legal en el paso "Formular el IFI"
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_COMISION_DETECCION", nullable = true)
  private Date feComisionDeteccion;

  /*
  *Monto exacto de la multa expresado en UIT determinado por el revisor económico
  */
   @Column(name = "MO_MULTA_UIT", nullable = true)
  private BigDecimal moMultaUit;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Flag que indica si la infracción se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_VIGENTE", nullable = true)
  private String flVigente;

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
  *Texto descriptivo del código de la infracción
  */
 @Transient
  private String descCodigo;

  /*
  *Texto descriptivo de la posible la infracción
  */
 @Transient
  private String descInfraccion;

  /*
  *Texto descriptivo del valor de la sanción
  */
 @Transient
  private String descSancionUit;

  /*
  *Identificador del hecho constatado
  */
 @Transient
  private Long descIdHechoConstatado;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular
  */
 @Transient
  private Long descIdCriterioSprvsion;

  /*
  *Identificador del hecho constatado Reemplazado
  */
 @Transient
  private Long descIdHechoConstatadoReemplazado;

  /*
  *Nombre del rol en el proceso de la persona responsable
  */
 @Transient
  private String descNoRolProceso;

  /*
  *Nombre del responsable del registro o actualización de la infracción
  */
 @Transient
  private String descResponsable;

  /*
  *Nombre de la fase de la infracción
  */
 @Transient
  private String descNoFaseProceso;

  /*
  *Nombre del paso de la infracción
  */
 @Transient
  private String descNoPasoProceso;

  /*
  *Fecha de creación de la infracción
  */
 @Transient
  private Date descFeCreacion;


}