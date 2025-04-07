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
* Clase Entidad para la tabla PGIM_TM_UBIGEO: 
* @descripción: Ubigeo utilizado en la plataforma
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_UBIGEO")
@Data
@NoArgsConstructor
public class PgimUbigeo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ubigeo. Secuencia: PGIM_SEQ_UBIGEO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_UBIGEO")
   @SequenceGenerator(name = "PGIM_SEQ_UBIGEO", sequenceName = "PGIM_SEQ_UBIGEO", allocationSize = 1)
   @Column(name = "ID_UBIGEO", nullable = false)
  private Long idUbigeo;

  /*
  *Código del ubigeo
  */
   @Column(name = "CO_UBIGEO", nullable = false)
  private String coUbigeo;

  /*
  *Nombre del ubigeo
  */
   @Column(name = "NO_UBIGEO", nullable = false)
  private String noUbigeo;

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
  *Distrito
  */
 @Transient
  private String descDistrito;

  /*
  *Provincia
  */
 @Transient
  private String descProvincia;

  /*
  *Departamento
  */
 @Transient
  private String descDepartamento;


}