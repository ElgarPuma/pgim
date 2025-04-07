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
* Clase Entidad para la tabla PGIM_TP_VALOR_PARAMETRO: 
* @descripción: Valor del parámetro de la aplicación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TP_VALOR_PARAMETRO")
@Data
@NoArgsConstructor
public class PgimValorParametro implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador del valor del parámetro. Secuencia: PGIM_SEQ_VALOR_PARAMETRO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_VALOR_PARAMETRO")
   @SequenceGenerator(name = "PGIM_SEQ_VALOR_PARAMETRO", sequenceName = "PGIM_SEQ_VALOR_PARAMETRO", allocationSize = 1)
   @Column(name = "ID_VALOR_PARAMETRO", nullable = false)
  private Long idValorParametro;

  /*
  *Identificador del parámetro. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PARAMETRO", nullable = false)
  private PgimParametro pgimParametro;

  /*
  *Clave en texto del ítem del parámetro. Este valor sirve para mapear los códigos de negocio empleados por ejemplo para las listas.
  */
   @Column(name = "CO_CLAVE_TEXTO", nullable = false)
  private String coClaveTexto;

  /*
  *Clave del ítem del parámetro. Este valor sirve para mapear los códigos de negocio empleados por ejemplo para las listas.
  */
   @Column(name = "CO_CLAVE", nullable = true)
  private Long coClave;

  /*
  *Nombre del valor parámetro
  */
   @Column(name = "NO_VALOR_PARAMETRO", nullable = false)
  private String noValorParametro;

  /*
  *Descripción del valor parámetro
  */
   @Column(name = "DE_VALOR_PARAMETRO", nullable = true)
  private String deValorParametro;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN", nullable = false)
  private BigDecimal nuOrden;

  /*
  *Valor alfanumérico del Código
  */
   @Column(name = "DE_VALOR_ALFANUM", nullable = true)
  private String deValorAlfanum;

  /*
  *Valor numérico del código
  */
   @Column(name = "NU_VALOR_NUMERICO", nullable = true)
  private BigDecimal nuValorNumerico;

  /*
  *Indicador lógico que determina si el valor del parámetro del Osinergmin se encuentra activo o no
  */
   @Column(name = "FL_ACTIVO", nullable = false)
  private String flActivo;

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