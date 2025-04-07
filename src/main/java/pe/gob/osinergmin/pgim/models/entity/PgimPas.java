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
* Clase Entidad para la tabla PGIM_TC_PAS: 
* @descripción: Instancia del procedimiento administrativo sancionador
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_PAS")
@Data
@NoArgsConstructor
public class PgimPas implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del proceso administrativo sancionador. Secuencia: PGIM_SEQ_PAS
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PAS")
   @SequenceGenerator(name = "PGIM_SEQ_PAS", sequenceName = "PGIM_SEQ_PAS", allocationSize = 1)
   @Column(name = "ID_PAS", nullable = false)
  private Long idPas;

  /*
  *Identificador interno de la supervisión que dio lugar al PAS. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;
   
  /*
  *Razón social del agente fiscalizado al momento de la finalización del flujo de trabajo del PAS
  */
   @Column(name = "NO_RAZON_SOCIAL_AFISCALIZADO", nullable = true)
  private String noRazonSocialAfiscalizado;


  /*
  *Fecha de la creación del PAS desde el proceso de supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION_PAS", nullable = true)
  private Date feCreacionPas;

  /*
  *Código de PAS. Formato ejemplo: PAS-2023-00001
  */
   @Column(name = "CO_PAS", nullable = false)
  private String coPas;

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
  *Id de la Especialidad
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Número de expediente Siged
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Nombre de la empresa supervisora
  */
 @Transient
  private String descNoRazonSocial;

  /*
  *Nombre del agente supevisado
  */
 @Transient
  private String descNoAgenteSupervisado;

  /*
  *Fecha año de Fiscalización
  */
 @Transient
  private String descFeCreacionPasAnio;

  /*
  *Fecha año de Fiscalización
  */
 @Transient
  private String descCoSupervision;


}