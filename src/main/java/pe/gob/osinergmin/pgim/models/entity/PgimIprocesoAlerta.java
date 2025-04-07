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
* Clase Entidad para la tabla PGIM_TC_IPROCESO_ALERTA: 
* @descripción: Alerta a nivel de la instancia de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 14/12/2022
*/
@Entity
@Table(name = "PGIM_TC_IPROCESO_ALERTA")
@Data
@NoArgsConstructor
public class PgimIprocesoAlerta implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la alerta a nivel de la instancia del proceso. Secuencia: PGIM_SEQ_IPROCESO_ALERTA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_IPROCESO_ALERTA")
   @SequenceGenerator(name = "PGIM_SEQ_IPROCESO_ALERTA", sequenceName = "PGIM_SEQ_IPROCESO_ALERTA", allocationSize = 1)
   @Column(name = "ID_IPROCESO_ALERTA", nullable = false)
  private Long idIprocesoAlerta;

  /*
  *Identificador interno del tipo de alerta del proceso. Tabla padre: PGIM_TM_TIPO_PROCESO_ALERTA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_PROCESO_ALERTA", nullable = false)
  private PgimTipoProcesoAlerta pgimTipoProcesoAlerta;

  /*
  *Identificador interno padre de la alerta a nivel de la instancia del proceso. Tabla padre: PGIM_TC_IPROCESO_ALERTA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_IPROCESO_ALERTA_PADRE", nullable = true)
  private PgimIprocesoAlerta iprocesoAlertaPadre;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = false)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Fecha y hora inicial del intervalo 1
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INTERVALO1_INICIAL", nullable = false)
  private Date feIntervalo1Inicial;

  /*
  *Fecha y hora final del intervalo 1
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INTERVALO1_FINAL", nullable = false)
  private Date feIntervalo1Final;

  /*
  *Fecha y hora inicial del intervalo 2
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INTERVALO2_INICIAL", nullable = false)
  private Date feIntervalo2Inicial;

  /*
  *Fecha y hora final del intervalo 2
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INTERVALO2_FINAL", nullable = false)
  private Date feIntervalo2Final;

  /*
  *Fecha y hora inicial del intervalo 3
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INTERVALO3_INICIAL", nullable = false)
  private Date feIntervalo3Inicial;

  /*
  *Fecha y hora final del intervalo 3
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INTERVALO3_FINAL", nullable = false)
  private Date feIntervalo3Final;

  /*
  *Indicador lógico que determina si la alerta de la instancia de proceso se encuentra activa o no
  */
   @Column(name = "FL_ACTIVO", nullable = false)
  private String flActivo;

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


}