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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_NIVEL_RIESGO_FACTOR: 
* @descripción: Nivel de riesgo por factor
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 16/02/2021
*/
@Entity
@Table(name = "PGIM_TM_NIVEL_RIESGO_FACTOR")
@Data
@NoArgsConstructor
public class PgimNivelRiesgoFactor implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del nivel de riesgo por factor. Secuencia: PGIM_SEQ_NIVEL_RIESGO_FACTOR
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NIVEL_RIESGO_FACTOR")
   @SequenceGenerator(name = "PGIM_SEQ_NIVEL_RIESGO_FACTOR", sequenceName = "PGIM_SEQ_NIVEL_RIESGO_FACTOR", allocationSize = 1)
   @Column(name = "ID_NIVEL_RIESGO_FACTOR", nullable = false)
  private Long idNivelRiesgoFactor;

  /*
  *Identificador interno del factor de riesgo. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FACTOR_RIESGO", nullable = false)
  private PgimFactorRiesgo pgimFactorRiesgo;

  /*
  *Identificador interno del nivel de riesgo. Tabla padre: PGIM_TM_NIVEL_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NIVEL_RIESGO", nullable = false)
  private PgimNivelRiesgo pgimNivelRiesgo;

  /*
  *Especificación del nivel de riesgo del factor
  */
   @Column(name = "DE_ESPECIFICACION", nullable = false)
  private String deEspecificacion;

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