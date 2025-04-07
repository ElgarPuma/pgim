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
* Clase Entidad para la tabla PGIM_TZ_SUPERV_FECHA: 
* @descripción: Trazabilidad de cambios de fechas de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TZ_SUPERV_FECHA")
@Data
@NoArgsConstructor
public class PgimSupervFecha implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del histórico de la reprogramación. Secuencia: PGIM_SEQ_SUPERV_FECHA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERV_FECHA")
   @SequenceGenerator(name = "PGIM_SEQ_SUPERV_FECHA", sequenceName = "PGIM_SEQ_SUPERV_FECHA", allocationSize = 1)
   @Column(name = "ID_SUPERV_FECHA", nullable = false)
  private Long idSupervFecha;

  /*
  *Identificador interno de la supervisión que dio lugar al PAS. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Tipo de fechas cambiadas. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_FECHA_CAMBIADA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_FECHA", nullable = false)
  private PgimValorParametro tipoFecha;

  /*
  *Fecha de inicio de la supervisión anterior a la reprogramación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION", nullable = false)
  private Date feInicioSupervision;

  /*
  *Fecha fin de la supervisión anterior a la reprogramación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION", nullable = true)
  private Date feFinSupervision;

  /*
  *Motivo de la reprogramación
  */
   @Column(name = "DE_MOTIVO_CAMBIO", nullable = true)
  private String deMotivoCambio;

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