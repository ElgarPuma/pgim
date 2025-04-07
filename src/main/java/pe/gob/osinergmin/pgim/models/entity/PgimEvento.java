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
* Clase Entidad para la tabla PGIM_TM_EVENTO: 
* @descripción: Evento ocurrido tal como un accidente mortal o incidente peligroso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_EVENTO")
@Data
@NoArgsConstructor
public class PgimEvento implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del evento minero. Secuencia: PGIM_SEQ_EVENTO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EVENTO")
   @SequenceGenerator(name = "PGIM_SEQ_EVENTO", sequenceName = "PGIM_SEQ_EVENTO", allocationSize = 1)
   @Column(name = "ID_EVENTO", nullable = false)
  private Long idEvento;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Tipo del evento minero asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_EVENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_EVENTO", nullable = false)
  private PgimValorParametro tipoEvento;

  /*
  *Agente causante del evento minero (tipo accidente) asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = AGENTE_CAUSANTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre:PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_CAUSANTE", nullable = true)
  private PgimValorParametro agenteCausante;

  /*
  *Tipo de accidente mortal asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACCIDENTE_MORTAL. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACCIDENTE_MORTAL", nullable = true)
  private PgimValorParametro tipoAccidenteMortal;

  /*
  *Tipo de incidente peligroso asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_INCIDENTE_PELIGROSO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_INCIDENTE_PELIGRO", nullable = true)
  private PgimValorParametro tipoIncidentePeligro;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Código del evento
  */
   @Column(name = "CO_EVENTO", nullable = false)
  private String coEvento;

  /*
  *Fecha de presentación del evento
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PRESENTACION", nullable = false)
  private Date fePresentacion;

  /*
  *Fecha de ocurrencia del evento
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_EVENTO", nullable = false)
  private Date feEvento;

  /*
  *Descripción del evento
  */
   @Column(name = "DE_EVENTO", nullable = false)
  private String deEvento;

  /*
  *Lugar del evento
  */
   @Column(name = "DE_LUGAR_EVENTO", nullable = false)
  private String deLugarEvento;

  /*
  *Valor lógico que señala si se va a contabilizar o no el evento para el análisis estadístico posterior de los datos. Los posibles valores son: "1" = No contabilizar y "0" = Contabilizar
  */
   @Column(name = "ES_NO_CONTABILIZAR", nullable = false)
  private String esNoContabilizar;

  /*
  *Descripción del motivo por el que se señala que no se va a contabilizar
  */
   @Column(name = "DE_MOTIVO_NOCONTABILIZAR", nullable = true)
  private String deMotivoNocontabilizar;

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
  *Nombre del tipo de evento
  */
 @Transient
  private String descIdTipoEvento;

  /*
  *Identificador interno del agente supervisado
  */
 @Transient
  private Long descIdAgenteSupervisado;

  /*
  *Número del expediente Siged asociado al evento
  */
 @Transient
  private String nuExpedienteSiged;

  /*
  *DESC_NO_TIPO_ACCIDENTE_MORTAL
  */
 @Transient
  private String descNoTipoAccidenteMortal;

  /*
  *DESC_NO_TIPO_INCIDENTE_PELIGRO
  */
 @Transient
  private String descNoTipoIncidentePeligro;


}