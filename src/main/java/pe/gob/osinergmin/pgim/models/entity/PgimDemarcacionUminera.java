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
* Clase Entidad para la tabla PGIM_TM_DEMARCACION_UMINERA: 
* @descripción: Demarcación de la unidad minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_DEMARCACION_UMINERA")
@Data
@NoArgsConstructor
public class PgimDemarcacionUminera implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad minera. Secuencia: PGIM_SEQ_DEMARCACION_UMINERA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DEMARCACION_UMINERA")
   @SequenceGenerator(name = "PGIM_SEQ_DEMARCACION_UMINERA", sequenceName = "PGIM_SEQ_DEMARCACION_UMINERA", allocationSize = 1)
   @Column(name = "ID_DEMARCACION_UM", nullable = false)
  private Long idDemarcacionUm;

  /*
  *Identificador interno del ubigeo. Tabla padre: PGIM_TM_UBIGEO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UBIGEO", nullable = false)
  private PgimUbigeo pgimUbigeo;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Indicador lógico que señala si el ubigeo es principal. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_PRINCIPAL", nullable = false)
  private String flPrincipal;

  /*
  *Porcentaje del ubigeo dentro de la demarcación de una unidad minera
  */
   @Column(name = "PC_UBIGEO", nullable = false)
  private BigDecimal pcUbigeo;

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
  *Departamento correspondiente al ubigeo
  */
 @Transient
  private String descDepartamento;

  /*
  *Provincia correspondiente al ubigeo
  */
 @Transient
  private String descProvincia;

  /*
  *Distrito correspondiente al ubigeo
  */
 @Transient
  private String descDistrito;


}