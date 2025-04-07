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
* Clase Entidad para la tabla PGIM_TC_INSTANCIA_PASO: 
* @descripción: Instancia de paso de procedimiento administrativo sancionador
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_INSTANCIA_PASO")
@Data
@NoArgsConstructor
public class PgimInstanciaPaso implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la instancia de paso del proceso. Secuencia: PGIM_SEQ_INSTANCIA_PASO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INSTANCIA_PASO")
   @SequenceGenerator(name = "PGIM_SEQ_INSTANCIA_PASO", sequenceName = "PGIM_SEQ_INSTANCIA_PASO", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_PASO", nullable = false)
  private Long idInstanciaPaso;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = false)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO", nullable = false)
  private PgimRelacionPaso pgimRelacionPaso;

  /*
  *Identificador interno de la persona origen del equipo de supervisión. Table padre: PGIM_TC_EQUIPO_SUPERV
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA_EQP_ORIGEN", nullable = false)
  private PgimEqpInstanciaPro personaEqpOrigen;

  /*
  *Identificador interno de la persona destino del equipo de supervisión. Table padre: PGIM_TC_EQUIPO_SUPERV
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA_EQP_DESTINO", nullable = false)
  private PgimEqpInstanciaPro personaEqpDestino;

  /*
  *Identificador interno de la instancia de paso origen. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO_PADRE", nullable = true)
  private PgimInstanciaPaso instanciaPasoPadre;

  /*
  *Identificador interno de la persona del equipo de supervisión que solicita una aprobación. Table padre: PGIM_TC_EQP_INSTANCIA_PRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA_EQP_SOLIC", nullable = true)
  private PgimEqpInstanciaPro personaEqpSolic;

  /*
  *Identificador interno del paso de proceso origen. Table padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO_ORIGEN", nullable = true)
  private PgimPasoProceso pasoProcesoOrigen;

  /*
  *Identificador interno del paso de proceso destino. Table padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO_DESTINO", nullable = true)
  private PgimPasoProceso pasoProcesoDestino;

  /*
  *Fecha de la instancia en la que se registra la transición de pasos
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INSTANCIA_PASO", nullable = false)
  private Date feInstanciaPaso;

  /*
  *Mensaje de la instancia de paso del proceso
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *Flag de lectura o revisión de la alerta. Los posibles valores son: "1" = Leído y "0" = No leído
  */
   @Column(name = "FL_LEIDO", nullable = true)
  private String flLeido;

  /*
  *Fecha de lectura
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_LECTURA", nullable = true)
  private Date feLectura;
   
  /*
  *Flag que indica si es el último paso dado en la instancia de proceso. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_ES_PASO_ACTIVO", nullable = true)
  private String flEsPasoActivo;

  /*
  *Tipo de subflujo que tiene un valor cuando la instancia de paso ha sido creada a través de una asignación paralela (tarea agrupadora), vale decir que cuando es NULL indica que ha sido creada a través de una asignación convencional. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUBFLUJO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUBFLUJO", nullable = true)
  private PgimValorParametro tipoSubflujo;
     
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
  *Identificador interno del personal de Osinergmin origen. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
 @Transient
  private Long descIdPersonalOsiOrigen;

  /*
  *Identificador interno del personal del contrato origen. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
 @Transient
  private Long descIdPersonalContratoOrigen;

  /*
  *Identificador interno del personal de Osinergmin destino. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
 @Transient
  private Long descIdPersonalOsiDestino;

  /*
  *Identificador interno del personal del contrato destino. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
 @Transient
  private Long descIdPersonalContratoDestino;

  /*
  *Identificador interno de la especialidad
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Lista de documento seleccionados para asociación
  */
 @Transient
  private List<PgimDocumento> descListIdDocumentos;


}