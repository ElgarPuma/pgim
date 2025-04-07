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
* Clase Entidad para la tabla PGIM_TM_MATRIZ_PARES: 
* @descripción: Matriz de pares
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 16/02/2021
*/
@Entity
@Table(name = "PGIM_TM_MATRIZ_PARES")
@Data
@NoArgsConstructor
public class PgimMatrizPares implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem de la matriz de pares. Secuencia: PGIM_SEQ_MATRIZ_PARES
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MATRIZ_PARES")
   @SequenceGenerator(name = "PGIM_SEQ_MATRIZ_PARES", sequenceName = "PGIM_SEQ_MATRIZ_PARES", allocationSize = 1)
   @Column(name = "ID_MATRIZ_PARES", nullable = false)
  private Long idMatrizPares;

  /*
  *Identificador interno del factor de riesgo de la fila de la matriz. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FACTOR_RIESGO_FILA", nullable = false)
  private PgimFactorRiesgo factorRiesgoFila;

  /*
  *Identificador interno del factor de riesgo de la columna de la matriz. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FACTOR_RIESGO_COLUMNA", nullable = false)
  private PgimFactorRiesgo factorRiesgoColumna;

  /*
  *Valor del numerador del ítem de la matriz
  */
   @Column(name = "NU_NUMERADOR", nullable = true)
  private BigDecimal nuNumerador;

  /*
  *Valor del denominador del ítem de la matriz
  */
   @Column(name = "NU_DENOMINADOR", nullable = true)
  private BigDecimal nuDenominador;

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