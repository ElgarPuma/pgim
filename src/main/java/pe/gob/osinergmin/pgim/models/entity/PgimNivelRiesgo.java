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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_NIVEL_RIESGO: 
* @descripción: Nivel de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_NIVEL_RIESGO")
@Data
@NoArgsConstructor
public class PgimNivelRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del nivel de riesgo. Secuencia: PGIM_SEQ_NIVEL_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NIVEL_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_NIVEL_RIESGO", sequenceName = "PGIM_SEQ_NIVEL_RIESGO", allocationSize = 1)
   @Column(name = "ID_NIVEL_RIESGO", nullable = false)
  private Long idNivelRiesgo;

  /*
  *Tipo de operador lógico. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OPERADOR_LOGICO. Finalmente, el valor persistido en esta columna es el valor obtenido de <PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OPERADOR_VALOR_INFERIOR", nullable = true)
  private PgimValorParametro operadorValorInferior;

  /*
  *Tipo de operador lógico. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OPERADOR_LOGICO. Finalmente, el valor persistido en esta columna es el valor obtenido de <PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OPERADOR_VALOR_SUPERIOR", nullable = true)
  private PgimValorParametro operadorValorSuperior;

  /*
  *Código del nivel de riesgo
  */
   @Column(name = "CO_NIVEL_RIESGO", nullable = true)
  private String coNivelRiesgo;

  /*
  *Nombre del factor de riesgo
  */
   @Column(name = "NO_NIVEL_RIESGO", nullable = false)
  private String noNivelRiesgo;

  /*
  *Descripción del factor de riesgo
  */
   @Column(name = "DE_NIVEL_RIESGO", nullable = true)
  private String deNivelRiesgo;

  /*
  *Orden del nivel
  */
   @Column(name = "NU_ORDEN", nullable = false)
  private Long nuOrden;

  /*
  *Valor del nivel, números entre 0 y 1.
  */
   @Column(name = "NU_VALOR", nullable = false)
  private BigDecimal nuValor;

  /*
  *Valor del nivel inferior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
   @Column(name = "NU_VALOR_INFERIOR", nullable = true)
  private BigDecimal nuValorInferior;

  /*
  *Valor del nivel superior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
   @Column(name = "NU_VALOR_SUPERIOR", nullable = true)
  private BigDecimal nuValorSuperior;

  /*
  *Código del color en hexadecimal del nivel de riesgo
  */
   @Column(name = "CO_COLOR_HEXADECIMAL", nullable = true)
  private String coColorHexadecimal;

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