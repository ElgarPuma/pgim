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
* Clase Entidad para la tabla PGIM_TM_CFG_NIVEL_RIESGO: 
* @descripción: Configuración del nivel de riesgo por factor
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CFG_NIVEL_RIESGO")
@Data
@NoArgsConstructor
public class PgimCfgNivelRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del nivel de riesgo por factor. Secuencia: PGIM_SEQ_CFG_NIVEL_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CFG_NIVEL_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_CFG_NIVEL_RIESGO", sequenceName = "PGIM_SEQ_CFG_NIVEL_RIESGO", allocationSize = 1)
   @Column(name = "ID_CFG_NIVEL_RIESGO", nullable = false)
  private Long idCfgNivelRiesgo;

  /*
  *Identificador interno del factor de riesgo. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CFG_FACTOR_RIESGO", nullable = false)
  private PgimCfgFactorRiesgo pgimCfgFactorRiesgo;

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
   @Column(name = "DE_ESPECIFICACION", nullable = true)
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

  /*
  *Número del orden del nivel de riesgo
  */
 @Transient
  private Long descNuOrden;

  /*
  *Nombre del nivel de riesgo
  */
 @Transient
  private String descNoNivelRiesgo;

  /*
  *Valor del nivel, números entre 0 y 1.
  */
 @Transient
  private BigDecimal descNuValor;


}