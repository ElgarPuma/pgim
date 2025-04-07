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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TP_PARAMETRO: 
* @descripción: Parámetro de la aplicación
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TP_PARAMETRO")
@Data
@NoArgsConstructor
public class PgimParametro implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador del parámetro. Secuencia: PGIM_SEQ_PARAMETRO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PARAMETRO")
   @SequenceGenerator(name = "PGIM_SEQ_PARAMETRO", sequenceName = "PGIM_SEQ_PARAMETRO", allocationSize = 1)
   @Column(name = "ID_PARAMETRO", nullable = false)
  private Long idParametro;

  /*
  *Código del parámetro
  */
   @Column(name = "CO_PARAMETRO", nullable = false)
  private String coParametro;

  /*
  *Descripción del parámetro
  */
   @Column(name = "DE_PARAMETRO", nullable = true)
  private String deParametro;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN", nullable = true)
  private Long nuOrden;

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