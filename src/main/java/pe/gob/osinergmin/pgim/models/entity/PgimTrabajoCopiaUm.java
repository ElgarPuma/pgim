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
* Clase Entidad para la tabla PGIM_TZ_TRABAJO_COPIA_UM: 
* @descripción: Trabajo de copia de los datos clave de las unidades mineras
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TZ_TRABAJO_COPIA_UM")
@Data
@NoArgsConstructor
public class PgimTrabajoCopiaUm implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del trabajo de copia de datos de las unidades mineras. Secuencia: PGIM_SEQ_TRABAJO_COPIA_UM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TRABAJO_COPIA_UM")
   @SequenceGenerator(name = "PGIM_SEQ_TRABAJO_COPIA_UM", sequenceName = "PGIM_SEQ_TRABAJO_COPIA_UM", allocationSize = 1)
   @Column(name = "ID_TRABAJO_COPIA_UM", nullable = false)
  private Long idTrabajoCopiaUm;

  /*
  *Fecha de copia mensual. La copia se debe realizar a las 23:55 del último día del mes
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_COPIA_UM", nullable = false)
  private Date feCopiaUm;

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
  *Periodo de la copia de las unidades mineras
  */
 @Transient
  private String descPeriodoCopiaUm;


}