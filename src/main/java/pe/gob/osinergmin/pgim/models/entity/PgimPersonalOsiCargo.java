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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_PERSONAL_OSI_CARGO: 
* @descripción: Cargos del personal de Osinergmin
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 20/01/2023
*/
@Entity
@Table(name = "PGIM_TD_PERSONAL_OSI_CARGO")
@Data
@NoArgsConstructor
public class PgimPersonalOsiCargo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del personal de Osinergmin. Secuencia: PGIM_SEQ_PERSONAL_OSI_CARGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PERSONAL_OSI_CARGO")
   @SequenceGenerator(name = "PGIM_SEQ_PERSONAL_OSI_CARGO", sequenceName = "PGIM_SEQ_PERSONAL_OSI_CARGO", allocationSize = 1)
   @Column(name = "ID_PERSONAL_OSI_CARGO", nullable = false)
  private Long idPersonalOsiCargo;

  /*
  *Identificador interno del personal del Osinergin. Table padre: PGIM_TC_PERSONAL_OSI
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONAL_OSI", nullable = false)
  private PgimPersonalOsi pgimPersonalOsi;

  /*
  *Identificador interno de la unidad orgánica. Table padre: PGIM_TM_UNIDAD_ORGANICA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_ORGANICA", nullable = false)
  private PgimUnidadOrganica pgimUnidadOrganica;

  /*
  *Nombre del cargo que funge el personal del Osinergmin
  */
   @Column(name = "NO_CARGO", nullable = false)
  private String noCargo;

  /*
  *Fecha de inicio de vigencia del cargo
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO", nullable = false)
  private Date feInicio;

  /*
  *Fecha final de vigencia del cargo
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN", nullable = true)
  private Date feFin;

  /*
  *Indicador lógico que determina  si el cargo es principal o no lo es. El valor "1" indica que sí es principal, por otro lado, el valor "0" indica que no lo es.
  */
   @Column(name = "FL_PRINCIPAL", nullable = false)
  private String flPrincipal;

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
  *Número de expediente siged de encargatura
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *Código del tipo de documento siged de encargatura
  */
   @Column(name = "CO_TIPO_DOCUMENTO_SIGED", nullable = true)
  private Long coTipoDocumentoSiged;

  /*
  *Número de documento siged de encargatura
  */
   @Column(name = "NU_DOCUMENTO_SIGED", nullable = true)
  private String nuDocumentoSiged;
}