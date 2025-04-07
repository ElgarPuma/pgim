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
* Clase Entidad para la tabla PGIM_TM_CFG_FACTOR_RIESGO: 
* @descripción: Configuración del factor de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CFG_FACTOR_RIESGO")
@Data
@NoArgsConstructor
public class PgimCfgFactorRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_CFG_FACTOR_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CFG_FACTOR_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_CFG_FACTOR_RIESGO", sequenceName = "PGIM_SEQ_CFG_FACTOR_RIESGO", allocationSize = 1)
   @Column(name = "ID_CFG_FACTOR_RIESGO", nullable = false)
  private Long idCfgFactorRiesgo;

  /*
  *Identificador interno del del grupo de riesgo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CFG_GRUPO_RIESGO", nullable = false)
  private PgimCfgGrupoRiesgo pgimCfgGrupoRiesgo;

  /*
  *Identificador interno del del grupo de riesgo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FACTOR_RIESGO", nullable = false)
  private PgimFactorRiesgo pgimFactorRiesgo;

  /*
  *Orden de prioridad del factor
  */
   @Column(name = "NU_ORDEN_PRIORIDAD", nullable = false)
  private Long nuOrdenPrioridad;

  /*
  *Indicador si el factor es afectado o no por la gestión de la empresa. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_AFECTADO_GESTION", nullable = true)
  private String flAfectadoGestion;

  /*
  *Sumatoria de PGIM_TM_CFG_MATRIZ_PARES.NU_MCP cuando PGIM_TM_CFG_MATRIZ_PARES.ID_CFG_FACTOR_RIESGO_COLU = PGIM_TM_CFG_FACTOR_RIESGO.ID_CFG_FACTOR_RIESGO.
  */
   @Column(name = "NU_SUMATORIA_COLUMNA_MCP", nullable = true)
  private BigDecimal nuSumatoriaColumnaMcp;

  /*
  *Promedio de las ponderaciones PGIM_TM_CFG_MATRIZ_PARES.NU_MCPN
  */
   @Column(name = "NU_VECTOR_RESULTANTE", nullable = true)
  private BigDecimal nuVectorResultante;

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
  *Nombre del factor de riesgo
  */
 @Transient
  private String descNoFactor;

  /*
  *Nombre del valor de parámetro
  */
 @Transient
  private String descNoValorParametro;

  /*
  *Id utilizado para poblar la matriz de pares
  */
 @Transient
  private Long descIdCfgFactorRiesgoTemp;

  /*
  *DESC_DE_FACTOR
  */
 @Transient
  private String descDeFactor;

  /*
  *DESC_ID_VALOR_PARAMETRO
  */
 @Transient
  private Long descIdValorParametro;


}