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
* Clase Entidad para la tabla PGIM_TM_FACTOR_RIESGO: 
* @descripción: Factor de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_FACTOR_RIESGO")
@Data
@NoArgsConstructor
public class PgimFactorRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_FACTOR_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FACTOR_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_FACTOR_RIESGO", sequenceName = "PGIM_SEQ_FACTOR_RIESGO", allocationSize = 1)
   @Column(name = "ID_FACTOR_RIESGO", nullable = false)
  private Long idFactorRiesgo;

  /*
  *Identificador interno del del grupo de riesgo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_GRUPO_RIESGO", nullable = false)
  private PgimGrupoRiesgo pgimGrupoRiesgo;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Tipo de origen del dato del riesgo. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ORIGEN_DATO_RIESGO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ORIGEN_DATO_RIESGO", nullable = false)
  private PgimValorParametro tipoOrigenDatoRiesgo;

  /*
  *Nombre del factor de riesgo
  */
   @Column(name = "NO_FACTOR", nullable = false)
  private String noFactor;

  /*
  *Descripción del factor de riesgo
  */
   @Column(name = "DE_FACTOR", nullable = true)
  private String deFactor;

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
  *DESC_NO_GRUPO
  */
 @Transient
  private String descNoGrupo;

  /*
  *DESC_NO_ESPECIALIDAD
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *DESC_NO_VALOR_PARAMETRO
  */
 @Transient
  private String descNoValorParametro;


}