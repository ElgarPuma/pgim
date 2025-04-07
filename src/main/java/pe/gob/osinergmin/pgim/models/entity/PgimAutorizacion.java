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

import java.util.List;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_AUTORIZACION: 
* @descripción: Autorización, asociada a una unidad minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_AUTORIZACION")
@Data
@NoArgsConstructor
public class PgimAutorizacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la Autorización. Secuencia: PGIM_SEQ_AUTORIZACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_AUTORIZACION")
   @SequenceGenerator(name = "PGIM_SEQ_AUTORIZACION", sequenceName = "PGIM_SEQ_AUTORIZACION", allocationSize = 1)
   @Column(name = "ID_AUTORIZACION", nullable = false)
  private Long idAutorizacion;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Tipo Autorización. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_AUTORIZACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_AUTORIZACION", nullable = false)
  private PgimValorParametro tipoAutorizacion;

  /*
  *Número de documento de la autorización
  */
   @Column(name = "NU_DOCUMENTO", nullable = false)
  private String nuDocumento;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA; especifica la entidad emisora de la autorización.
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Fecha de inicio de la autorización
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_AUTORIZACION", nullable = false)
  private Date feInicioAutorizacion;

  /*
  *Fecha de fin de la autorización
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_AUTORIZACION", nullable = true)
  private Date feFinAutorizacion;

  /*
  *Nota de la autorización
  */
   @Column(name = "DE_NOTA", nullable = true)
  private String deNota;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

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
  *Razón social de la entidad emisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Listado de componentes mineros
  */
 @Transient
  private List<PgimComponenteMinero> auxListaComponentesMineros;

  /*
  *Tipo de la autorización
  */
 @Transient
  private String descTipoAutorizacion;

  /*
  *Nombre corto de la persona que emite la autorización
  */
 @Transient
  private String descNoCorto;

  /*
  *Número de expediente Siged
  */
 @Transient
  private String descNuExpedienteSiged;


}