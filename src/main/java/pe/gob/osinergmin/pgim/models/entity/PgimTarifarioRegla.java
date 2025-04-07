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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_TARIFARIO_REGLA: 
* @descripción: Regla de aplicación del tarifario del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_TARIFARIO_REGLA")
@Data
@NoArgsConstructor
public class PgimTarifarioRegla implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del tarifario del contrato. Secuencia: PGIM_SEQ_TARIFARIO_REGLA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TARIFARIO_REGLA")
   @SequenceGenerator(name = "PGIM_SEQ_TARIFARIO_REGLA", sequenceName = "PGIM_SEQ_TARIFARIO_REGLA", allocationSize = 1)
   @Column(name = "ID_TARIFARIO_REGLA", nullable = false)
  private Long idTarifarioRegla;

  /*
  *Identificador interno del tarifario del contrato. Tabla padre: PGIM_TD_TARIFARIO_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TARIFARIO_CONTRATO", nullable = false)
  private PgimTarifarioContrato pgimTarifarioContrato;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = false)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MOTIVO_SUPERVISION", nullable = false)
  private PgimMotivoSupervision pgimMotivoSupervision;

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
  *Listado de subtipos de supervisión
  */
 @Transient
  private List<PgimSubtipoSupervision> descLpgimSubtipoSupervision;


}