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

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_AGENTE_SUPERVISADO: 
* @descripción: Agente supervisado (titular minero)
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_AGENTE_SUPERVISADO")
@Data
@NoArgsConstructor
public class PgimAgenteSupervisado implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del agente supervisado. Secuencia: PGIM_SEQ_AGENTE_SUPERVISADO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_AGENTE_SUPERVISADO")
   @SequenceGenerator(name = "PGIM_SEQ_AGENTE_SUPERVISADO", sequenceName = "PGIM_SEQ_AGENTE_SUPERVISADO", allocationSize = 1)
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = false)
  private Long idAgenteSupervisado;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Identificador interno del estrato. Tabla padre: PGIM_TM_ESTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESTRATO", nullable = false)
  private PgimEstrato pgimEstrato;

  /*
  *Tamaño de la empresa. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TAMANIO_EMPRESA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TAMANIO_EMPRESA", nullable = false)
  private PgimValorParametro tamanioEmpresa;

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
  *Principal actividad económica
  */
   @Column(name = "DE_PRINCIPAL_ACTIVIDAD_ECNMCA", nullable = true)
  private String dePrincipalActividadEcnmca;

  /*
  *Número de documento de identidad
  */
 @Transient
  private String descCoDocumentoIdentidad;

  /*
  *Razón social de la persona jurídica
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Nombre del estrato
  */
 @Transient
  private String descNoEstrato;

  /*
  *Nombre del Tamaño de la empresa
  */
 @Transient
  private String descNoTamanioEmpresa;

  /*
  *Teléfono
  */
 @Transient
  private String descDeTelefono;

  /*
  *Teléfono 02
  */
 @Transient
  private String descDeTelefono2;

  /*
  *Correo electrónico
  */
 @Transient
  private String descDeCorreo;

  /*
  *Correo electrónico 02
  */
 @Transient
  private String descDeCorreo2;

  /*
  *Dirección
  */
 @Transient
  private String descDeDireccion;

  /*
  *Identificador interno del ubigeo.
  */
 @Transient
  private Long idUbigeo;

  /*
  *Nombre del ubigeo
  */
 @Transient
  private String noUbigeo;

  /*
  *Flag que indica si la persona se encuentra afiliado a notificación electrónica. Posibles valores: "1" = Sí y 0 = "No"
  */
 @Transient
  private String flAfiliadoNtfccionElctrnca;

  /*
  *Fecha desde que se afilió a la notificación electrónica
  */
 @Transient
  private Date feAfiliadoDesde;

  /*
  *Correo de notificación electrónica
  */
 @Transient
  private String deCorreoNtfccionElctrnca;

  /*
  *Nombre corto de la persona jurídica
  */
 @Transient
  private String descNoCorto;

  /*
  *Cantidad de Unidades Mineras asociadas al Agente Supervisado
  */
 @Transient
  private Long nuCantidadUniMinera;

  /*
  *Capacidad total instalada de planta (TM/d), de las unidades mineras asociadas al Agente Supervisado, y que son de tipo concesión de beneficio.
  */
 @Transient
  private BigDecimal nuCapacidadTotalInstalada;


}