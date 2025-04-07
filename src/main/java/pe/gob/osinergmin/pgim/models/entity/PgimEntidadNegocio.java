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
* Clase Entidad para la tabla PGIM_TM_ENTIDAD_NEGOCIO: 
* @descripción: Entidad de negocio
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 07/12/2023
*/
@Entity
@Table(name = "PGIM_TM_ENTIDAD_NEGOCIO")
@Data
@NoArgsConstructor
public class PgimEntidadNegocio implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la entidad de negocio. Secuencia: PGIM_SEQ_ENTIDAD_NEGOCIO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ENTIDAD_NEGOCIO")
   @SequenceGenerator(name = "PGIM_SEQ_ENTIDAD_NEGOCIO", sequenceName = "PGIM_SEQ_ENTIDAD_NEGOCIO", allocationSize = 1)
   @Column(name = "ID_ENTIDAD_NEGOCIO", nullable = false)
  private Long idEntidadNegocio;

  /*
  *Número del documento bibliográfico
  */
   @Column(name = "NO_TABLA_ENTIDAD_NEGOCIO", nullable = false)
  private String noTablaEntidadNegocio;
 
  /*
  *Descripcion de la etiqueta del tipo de entidad
  */
   @Column(name = "DE_ETIQUETA_TABLA_ENEGOCIO", nullable = false)
  private String deEtiquetaTablaNegocio;

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