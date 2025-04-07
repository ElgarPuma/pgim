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
* Clase Entidad para la tabla PGIM_TM_PERSONA: 
* @descripción: Persona (natural o jurídica)
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_PERSONA")
@Data
@NoArgsConstructor
public class PgimPersona implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la persona. Secuencia: PGIM_SEQ_PERSONA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PERSONA")
   @SequenceGenerator(name = "PGIM_SEQ_PERSONA", sequenceName = "PGIM_SEQ_PERSONA", allocationSize = 1)
   @Column(name = "ID_PERSONA", nullable = false)
  private Long idPersona;

  /*
  *Identificador interno del ubigeo. Tabla padre: PGIM_TM_UBIGEO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UBIGEO", nullable = true)
  private PgimUbigeo pgimUbigeo;

  /*
  *Código de tipo de documento de identidad de la persona. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DOCUMENTO_IDENTIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_DOC_IDENTIDAD", nullable = false)
  private PgimValorParametro tipoDocIdentidad;

  /*
  *Código del tipo de la fuente de la persona natural. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = FUENTE_PERSONA_NATURAL. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FUENTE_PERSONA_NATURAL", nullable = true)
  private PgimValorParametro fuentePersonaNatural;

  /*
  *Indicador lógico que señala si el la persona jurídica es o no un consorcio. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_CONSORCIO", nullable = true)
  private String flConsorcio;

  /*
  *Número de documento de identidad
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String coDocumentoIdentidad;

  /*
  *Razón social de la persona jurídica
  */
   @Column(name = "NO_RAZON_SOCIAL", nullable = true)
  private String noRazonSocial;

  /*
  *Nombre corto de la persona jurídica
  */
   @Column(name = "NO_CORTO", nullable = true)
  private String noCorto;

  /*
  *Nombres de la persona natural
  */
   @Column(name = "NO_PERSONA", nullable = true)
  private String noPersona;

  /*
  *Apellido paterno de la persona natural
  */
   @Column(name = "AP_PATERNO", nullable = true)
  private String apPaterno;

  /*
  *Apellido materno de la persona natural
  */
   @Column(name = "AP_MATERNO", nullable = true)
  private String apMaterno;

  /*
  *Sexo. Los valores admisibles son: "1": Masculino y "0": Femenino
  */
   @Column(name = "TI_SEXO", nullable = true)
  private String tiSexo;

  /*
  *Fecha de nacimiento
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_NACIMIENTO", nullable = true)
  private Date feNacimiento;

  /*
  *Teléfono
  */
   @Column(name = "DE_TELEFONO", nullable = true)
  private String deTelefono;

  /*
  *Teléfono 02
  */
   @Column(name = "DE_TELEFONO2", nullable = true)
  private String deTelefono2;

  /*
  *Correo electrónico
  */
   @Column(name = "DE_CORREO", nullable = true)
  private String deCorreo;

  /*
  *Correo electrónico 02
  */
   @Column(name = "DE_CORREO2", nullable = true)
  private String deCorreo2;

  /*
  *Dirección
  */
   @Column(name = "DI_PERSONA", nullable = true)
  private String diPersona;

  /*
  *Nota
  */
   @Column(name = "CM_NOTA", nullable = true)
  private String cmNota;

  /*
  *Restricción de acuerdo con el Reniec
  */
   @Column(name = "DE_RESTRICCION", nullable = true)
  private String deRestriccion;

  /*
  *Flag que indica si la persona se encuentra afiliado a notificación electrónica. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_AFILIADO_NTFCCION_ELCTRNCA", nullable = true)
  private String flAfiliadoNtfccionElctrnca;

  /*
  *Fecha desde que se afilió a la notificación electrónica
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_AFILIADO_DESDE", nullable = true)
  private Date feAfiliadoDesde;

  /*
  *Correo de notificación electrónica
  */
   @Column(name = "DE_CORREO_NTFCCION_ELCTRNCA", nullable = true)
  private String deCorreoNtfccionElctrnca;

  /*
  *Prefijo de la persona natural; para el caso de persona jurídica no se debe permitir registrar valor alguno
  */
   @Column(name = "NO_PREFIJO_PERSONA", nullable = true)
  private String noPrefijoPersona;

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
  *Descripcion del nombre de tipo de documento de identidad DNI, CE y RUC
  */
 @Transient
  private String descIdTipoDocumento;

  /*
  *Descripcion del ubigeo
  */
 @Transient
  private String descUbigeo;

  /*
  *Descripción del tipo de documento de identidad
  */
 @Transient
  private String descIdTipoDocIdentidad;

  /*
  *Identificador interno del cliente Siged asociado a la persona de la PGIM
  */
 @Transient
  private String descIdClienteSiged;


}