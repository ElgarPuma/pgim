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
* Clase Entidad para la tabla PGIM_TD_SUPERVISION_MOTIVO: 
* @descripción: Motivos de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_SUPERVISION_MOTIVO")
@Data
@NoArgsConstructor
public class PgimSupervisionMotivo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del motivo de la supervisión. Secuencia: PGIM_SEQ_SUPERVISION_MOTIVO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERVISION_MOTIVO")
   @SequenceGenerator(name = "PGIM_SEQ_SUPERVISION_MOTIVO", sequenceName = "PGIM_SEQ_SUPERVISION_MOTIVO", allocationSize = 1)
   @Column(name = "ID_SUPERVISION_MOTIVO", nullable = false)
  private Long idSupervisionMotivo;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;
   
  /*
  *Tipo de motivio de inicio. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_MOTIVO_INICIO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_MOTIVO_INICIO", nullable = true)
  private PgimValorParametro pgimTipoMotivoInicio;
   
  /*
  *Identificador del objeto del motivo de inicio de una fiscalización.
  */
   @Column(name = "ID_OBJETO_MOTIVO_INICIO", nullable = true)
  private Long idObjetoMotivoInicio;

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
  *Código del evento
  */
 @Transient
  private String descCoEvento;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Nombre del tipo del evento
  */
 @Transient
  private String descNoTipoEvento;

  /*
  *Nombre del tipo de evento asociado a la UM
  */
 @Transient
  private String descNoTipoEventoUm;

  /*
  *Fecha del evento
  */
 @Transient
  private Date descFeEvento;

  /*
  *Descripción del evento
  */
 @Transient
  private String descDeEvento;


}