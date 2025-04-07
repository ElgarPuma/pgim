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
* Clase Entidad para la tabla PGIM_TM_SOLICITUD_BASE_DOC: 
* @descripción: Solicitud de documentación plantilla base
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_SOLICITUD_BASE_DOC")
@Data
@NoArgsConstructor
public class PgimSolicitudBaseDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la solicitud base (plantilla) de documentación. Secuencia: PGIM_SEQ_SOLICITUD_BASE_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SOLICITUD_BASE_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_SOLICITUD_BASE_DOC", sequenceName = "PGIM_SEQ_SOLICITUD_BASE_DOC", allocationSize = 1)
   @Column(name = "ID_SOLICITUD_BASE_DOC", nullable = false)
  private Long idSolicitudBaseDoc;

  /*
  *Identificador interno de la instancia del proceso PGIM. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURACION_BASE", nullable = true)
  private PgimConfiguracionBase pgimConfiguracionBase;

  /*
  *Nombre de la solicitud base de de documentos (Nombre de la plantilla)
  */
   @Column(name = "NO_SOLICITUD_BASE_DOC", nullable = true)
  private String noSolicitudBaseDoc;

  /*
  *Descripción de la solicitud base de de documentos (Nombre de la plantilla)
  */
   @Column(name = "DE_SOLICITUD_BASE_DOC", nullable = true)
  private String deSolicitudBaseDoc;

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
  *DESC_NO_CONFIGURACION_BASE
  */
 @Transient
  private String descNoConfiguracionBase;

  /*
  *Nombre de la división supervisora
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *Nombre del subtipo de supervisión
  */
 @Transient
  private String descNoSubtipoSupervision;

  /*
  *Identificador interno de la especialidad
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Identificador del tipo de supervisión
  */
 @Transient
  private Long descIdTipoSupervision;

  /*
  *Nombre del tipo de supervisión
  */
 @Transient
  private String descNoTipoSupervision;


}