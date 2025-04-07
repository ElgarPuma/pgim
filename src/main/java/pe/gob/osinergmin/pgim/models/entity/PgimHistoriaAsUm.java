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
* Clase Entidad para la tabla PGIM_TZ_HISTORIA_AS_UM: 
* @descripción: Trazabilidad de agente supervisado titular de la UM en el tiempo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TZ_HISTORIA_AS_UM")
@Data
@NoArgsConstructor
public class PgimHistoriaAsUm implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad minera. Secuencia: PGIM_SEQ_HISTORIA_AS_UM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_HISTORIA_AS_UM")
   @SequenceGenerator(name = "PGIM_SEQ_HISTORIA_AS_UM", sequenceName = "PGIM_SEQ_HISTORIA_AS_UM", allocationSize = 1)
   @Column(name = "ID_HISTORIA_AS_UM", nullable = false)
  private Long idHistoriaAsUm;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Identificador interno del agente supervisado previo. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO_PREVIO", nullable = false)
  private PgimAgenteSupervisado agenteSupervisadoPrevio;

  /*
  *Fecha del inicio de la titularidad del nuevo titular
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_TITULARIDAD", nullable = true)
  private Date feInicioTitularidad;

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
  *Identificador interno del nuevo agente supervisado.
  */
 @Transient
  private Long idAgenteSupervisadoNuevo;

  /*
  *Código del documento de identidad del agente supervisado previo
  */
 @Transient
  private String coDocidAgenteSupervisadoPrevio;

  /*
  *Razón social del agente supervisado previo
  */
 @Transient
  private String descAgenteSupervisadoPrevio;

  /*
  *Razón social del nuevo agente supervisado
  */
 @Transient
  private String descAgenteSupervisadoNuevo;


}