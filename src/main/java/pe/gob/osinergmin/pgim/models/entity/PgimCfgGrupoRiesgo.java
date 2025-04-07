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
* Clase Entidad para la tabla PGIM_TM_CFG_GRUPO_RIESGO: 
* @descripción: Configuración del grupo de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CFG_GRUPO_RIESGO")
@Data
@NoArgsConstructor
public class PgimCfgGrupoRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del del grupo de riesgo. Secuencia: PGIM_SEQ_CFG_GRUPO_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CFG_GRUPO_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_CFG_GRUPO_RIESGO", sequenceName = "PGIM_SEQ_CFG_GRUPO_RIESGO", allocationSize = 1)
   @Column(name = "ID_CFG_GRUPO_RIESGO", nullable = false)
  private Long idCfgGrupoRiesgo;

  /*
  *Identificador interno de la configuración del riesgo. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURA_RIESGO", nullable = false)
  private PgimConfiguraRiesgo pgimConfiguraRiesgo;

  /*
  *Identificador interno de la configuración del grupo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_GRUPO_RIESGO", nullable = false)
  private PgimGrupoRiesgo pgimGrupoRiesgo;

  /*
  *Nombre del grupo de riesgo
  */
   @Column(name = "NO_GRUPO_RIESGO", nullable = false)
  private String noGrupoRiesgo;

  /*
  *Decripción del grupo de riesgo
  */
   @Column(name = "DE_GRUPO_RIESGO", nullable = true)
  private String deGrupoRiesgo;

  /*
  *Factor de correción aplicado al valor de riesgo obtenido para el grupo de riesgo
  */
   @Column(name = "PC_FACTOR_CORRECCION", nullable = false)
  private BigDecimal pcFactorCorreccion;

  /*
  *Valor alfa para el grupo técnico, valor beta (1 - alfa) para el grupo de gestión. Para calcular alfa, se debe contar la cantidad de factores técnicos de la configuración marcados con PGIM_TM_CFG_FACTOR_RIESGO.FL_AFECTADO_GESTION = "1" entre el total de factores técnicos.
  */
   @Column(name = "NU_CALIFICACION_GRUPO", nullable = true)
  private BigDecimal nuCalificacionGrupo;

  /*
  *Resultado de la multiplicación matricial de la fila de la Matriz de Comparación de Pares (MCP) con la columna “Vector Resultante” de la Matriz de Comparación de Pares Normalizada (MCPN
  */
   @Column(name = "NU_NMAX", nullable = true)
  private BigDecimal nuNmax;

  /*
  *Índice de consistencia (IC)
  */
   @Column(name = "NU_INDICE_CONSISTENCIA", nullable = true)
  private BigDecimal nuIndiceConsistencia;

  /*
  *Ratio de consistencia de la configuración (RC)
  */
   @Column(name = "NU_RATIO_CONSISTENCIA", nullable = true)
  private BigDecimal nuRatioConsistencia;

  /*
  *Índice de consistencia aleatoria (IA)
  */
   @Column(name = "NU_IND_CONSISTENCIA_ALEATORIA", nullable = true)
  private BigDecimal nuIndConsistenciaAleatoria;

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
  *Id utilizado para poblar la matriz de pares
  */
 @Transient
  private Long descIdCfgGrupoRiesgoTemp;


}