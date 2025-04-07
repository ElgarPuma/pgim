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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_ESPECIALIDAD: 
* @descripción: Especialidad
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_ESPECIALIDAD")
@Data
@NoArgsConstructor
public class PgimEspecialidad implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la especialidad de supervisión. Secuencia: PGIM_SEQ_ESPECIALIDAD
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ESPECIALIDAD")
   @SequenceGenerator(name = "PGIM_SEQ_ESPECIALIDAD", sequenceName = "PGIM_SEQ_ESPECIALIDAD", allocationSize = 1)
   @Column(name = "ID_ESPECIALIDAD", nullable = false)
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = false)
  private String noEspecialidad;

  /*
  *Descripción de la especialidad
  */
   @Column(name = "DE_ESPECIALIDAD", nullable = true)
  private String deEspecialidad;

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
  *DESC_ID_VALOR_PARAMETRO
  */
 @Transient
  private Long descIdValorParametro;

  /*
  *DESC_NO_VALOR_PARAMETRO
  */
 @Transient
  private String descNoValorParametro;


}