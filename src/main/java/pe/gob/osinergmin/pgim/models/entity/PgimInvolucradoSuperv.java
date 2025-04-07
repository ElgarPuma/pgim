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
* Clase Entidad para la tabla PGIM_TD_INVOLUCRADO_SUPERV: 
* @descripción: Persona involucrada en la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_INVOLUCRADO_SUPERV")
@Data
@NoArgsConstructor
public class PgimInvolucradoSuperv implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del involucrado en la supervisión. Secuencia: PGIM_SEQ_INVOLUCRADO_SUPERV
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INVOLUCRADO_SUPERV")
   @SequenceGenerator(name = "PGIM_SEQ_INVOLUCRADO_SUPERV", sequenceName = "PGIM_SEQ_INVOLUCRADO_SUPERV", allocationSize = 1)
   @Column(name = "ID_INVOLUCRADO_SUPERV", nullable = false)
  private Long idInvolucradoSuperv;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Tipo de involucramiento registrado para la persona sobre la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTA_INVOLUCRADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTA_INVOLUCRADO", nullable = false)
  private PgimValorParametro tipoActaInvolucrado;

  /*
  *Tipo de involucramiento registrado para la persona sobre la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_INVOLUCRADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_INVOLUCRADO", nullable = false)
  private PgimValorParametro tipoInvolucrado;

  /*
  *Tipo de prefijo para la persona involucrada en la supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_PREFIJO_INVOLUCRADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_PREFIJO_INVOLUCRADO", nullable = false)
  private PgimValorParametro tipoPrefijoInvolucrado;

  /*
  *Identificador interno de la solicitud de documentación. Tabla padre: PGIM_TC_SOLICITUD_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SOLICITUD_DOC", nullable = true)
  private PgimSolicitudDoc pgimSolicitudDoc;

  /*
  *Descripción del cargo de la persona
  */
   @Column(name = "DE_CARGO", nullable = false)
  private String deCargo;

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
  *Nombre completo de la persona
  */
 @Transient
  private String descPersonaCompleto;

  /*
  *DNI o CE de la persona
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Identificador interno del tipo de involucrado
  */
 @Transient
  private String descIdTipoInvolucrado;

  /*
  *Nombre del prefijo de la persona
  */
 @Transient
  private String descIdTipoPrefijoInvolucrado;

  /*
  *Nombre de la persona
  */
 @Transient
  private String descNoPersona;

  /*
  *Apellido paterno
  */
 @Transient
  private String descApPaterno;

  /*
  *Apellido materno
  */
 @Transient
  private String descApMaterno;

  /*
  *Entidad
  */
 @Transient
  private String descEntidad;

  /*
  *Tipo de documento de identidad
  */
 @Transient
  private String descTipoDocIdentidad;


}