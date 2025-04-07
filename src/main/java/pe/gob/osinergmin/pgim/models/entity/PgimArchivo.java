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
* Clase Entidad para la tabla PGIM_TD_ARCHIVO: 
* @descripción: Archivo del documento del proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_ARCHIVO")
@Data
@NoArgsConstructor
public class PgimArchivo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento. Secuencia: PGIM_SEQ_ARCHIVO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ARCHIVO")
   @SequenceGenerator(name = "PGIM_SEQ_ARCHIVO", sequenceName = "PGIM_SEQ_ARCHIVO", allocationSize = 1)
   @Column(name = "ID_ARCHIVO", nullable = false)
  private Long idArchivo;

  /*
  *Identificador interno del documento. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento pgimDocumento;

  /*
  *Nombre del archivo con el que originalmente fue cargado
  */
   @Column(name = "NO_ORIGINAL_ARCHIVO", nullable = false)
  private String noOriginalArchivo;

  /*
  *Nuevo nombre del archivo con el que finalmente es cargado al Siged
  */
   @Column(name = "NO_NUEVO_ARCHIVO", nullable = false)
  private String noNuevoArchivo;

  /*
  *Secuencia utilizada para la conformación del nombre del archivo. Utiliza la secuencia PGIM_SEQ_GEN_ARCHIVO
  */
   @Column(name = "SE_ARCHIVO", nullable = false)
  private Long seArchivo;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo, "0" = Inactivo
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
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
 @Transient
  private Long descIdInstanciaProceso;


}