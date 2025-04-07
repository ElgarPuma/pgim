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
* Clase Entidad para la tabla PGIM_TM_TIPO_PROCESO_ALERTA: 
* @descripción: Tipo de alerta del proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 14/12/2022
*/
@Entity
@Table(name = "PGIM_TM_TIPO_PROCESO_ALERTA")
@Data
@NoArgsConstructor
public class PgimTipoProcesoAlerta implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del tipo de alerta del proceso. Secuencia: PGIM_SEQ_TIPO_PROCESO_ALERTA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPO_PROCESO_ALERTA")
   @SequenceGenerator(name = "PGIM_SEQ_TIPO_PROCESO_ALERTA", sequenceName = "PGIM_SEQ_TIPO_PROCESO_ALERTA", allocationSize = 1)
   @Column(name = "ID_TIPO_PROCESO_ALERTA", nullable = false)
  private Long idTipoProcesoAlerta;

  /*
  *Tipo de periodo de las alertas de proceso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_PERIODO_ALERTA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_PERIODO_ALERTA", nullable = false)
  private PgimValorParametro tipoPeriodoAlerta;

  /*
  *Identificador interno del tipo de alerta que debe dar origen a una nueva alerta a nivel de la instancia de proceso. Tabla padre: PGIM_TM_TIPO_PROCESO_ALERTA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_PROCESO_ALERTA_PADRE", nullable = true)
  private PgimTipoProcesoAlerta tipoProcesoAlertaPadre;

  /*
  *Código del tipo de la alerta del proceso
  */
   @Column(name = "CO_TIPO_PROCESO_ALERTA", nullable = false)
  private String coTipoProcesoAlerta;

  /*
  *Nombre del tipo de la alerta del proceso
  */
   @Column(name = "NO_TIPO_PROCESO_ALERTA", nullable = false)
  private String noTipoProcesoAlerta;

  /*
  *Descripción del tipo de la alerta del proceso
  */
   @Column(name = "DE_TIPO_PROCESO_ALERTA", nullable = false)
  private String deTipoProcesoAlerta;

  /*
  *Cantidad de unidades del periodo para el inicio de la alerta del proceso
  */
   @Column(name = "CA_PERIODO_INICIO", nullable = false)
  private Integer caPeriodoInicio;

  /*
  *Cantidad de unidades del periodo para el fin de la alerta del proceso
  */
   @Column(name = "CA_PERIODO_FIN", nullable = false)
  private Integer caPeriodoFin;

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