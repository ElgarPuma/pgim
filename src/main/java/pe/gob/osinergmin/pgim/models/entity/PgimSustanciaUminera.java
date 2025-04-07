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
* Clase Entidad para la tabla PGIM_TM_SUSTANCIA_UMINERA: 
* @descripción: Sustancia por unidad minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_SUSTANCIA_UMINERA")
@Data
@NoArgsConstructor
public class PgimSustanciaUminera implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la sustancia por unidad minera. Secuencia: PGIM_SEQ_SUSTANCIA_UMINERA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUSTANCIA_UMINERA")
   @SequenceGenerator(name = "PGIM_SEQ_SUSTANCIA_UMINERA", sequenceName = "PGIM_SEQ_SUSTANCIA_UMINERA", allocationSize = 1)
   @Column(name = "ID_SUSTANCIA_UMINERA", nullable = false)
  private Long idSustanciaUminera;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Identificador interno de la sustancia. Tabla padre: PGIM_TM_SUSTANCIA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUSTANCIA", nullable = false)
  private PgimSustancia pgimSustancia;

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
  *DESC_NO_SUSTANCIA
  */
 @Transient
  private String descNoSustancia;


}