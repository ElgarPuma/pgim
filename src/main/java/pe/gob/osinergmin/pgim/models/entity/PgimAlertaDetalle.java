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
* Clase Entidad para la tabla PGIM_TD_ALERTA_DETALLE: 
* @descripción: Detalle de la alerta generada en el sistema
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_ALERTA_DETALLE")
@Data
@NoArgsConstructor
public class PgimAlertaDetalle implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la alerta del sistema. Secuencia: PGIM_SEQ_ALERTA_DETALLE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ALERTA_DETALLE")
   @SequenceGenerator(name = "PGIM_SEQ_ALERTA_DETALLE", sequenceName = "PGIM_SEQ_ALERTA_DETALLE", allocationSize = 1)
   @Column(name = "ID_DETALLE_ALERTA", nullable = false)
  private Long idDetalleAlerta;

  /*
  *Identificador interno de la alerta del sistema. Tabla padre PGIM_TD_ALERTA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ALERTA", nullable = false)
  private PgimAlerta pgimAlerta;

  /*
  *Tipo del detalle de la alerta. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DETALLE_ALERTA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_DETALLE_ALERTA", nullable = false)
  private PgimValorParametro tipoDetalleAlerta;

  /*
  *Nombre Windows del usuario destino de la alerta
  */
   @Column(name = "NO_USUARIO_DESTINO", nullable = false)
  private String noUsuarioDestino;

  /*
  *Flag de lectura o revisión de la alerta. Los posibles valores son: "1" = Revisado y "0" = Sin revisar
  */
   @Column(name = "FL_LEIDO", nullable = false)
  private String flLeido;

  /*
  *Descripción de la alerta
  */
   @Column(name = "DE_DETALLE_ALERTA", nullable = true)
  private String deDetalleAlerta;

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
  *Nombre del tipo de detalle de la alerta
  */
 @Transient
  private String descNoTipoDetalleAlerta;

  /*
  *Nombre de la alerta
  */
 @Transient
  private String descNoAlerta;

  /*
  *Descripción de la alerta
  */
 @Transient
  private String descDeAlerta;

  /*
  *Fecha de la alerta
  */
 @Transient
  private Date descFeAlerta;

  /*
  *Dirección de enlace de loa alerta
  */
 @Transient
  private String descDiEnlace;

  /*
  *Identificador interno del tipo de la alerta
  */
 @Transient
  private Long descIdTipoAlerta;

  /*
  *Nombre del tipo de la alerta
  */
 @Transient
  private String descNoTipoAlerta;

  /*
  *Cantidad de alertas no revisadas
  */
 @Transient
  private Long descCantidadAlerta;

  /*
  *Identificador del proceso
  */
 @Transient
  private Long descIdProceso;

  /*
  *Identificador de fase del proceso
  */
 @Transient
  private Long descIdFaseProceso;

  /*
  *Identificador de paso de proceso
  */
 @Transient
  private Long descIdPasoProceso;

  /*
  *Nombre de la fase de proceso
  */
 @Transient
  private String descNoFaseProceso;

  /*
  *Nombre de paso de proceso
  */
 @Transient
  private String descNoPasoProceso;

  /*
  *Identificador interno de la instancia de paso asociada al detalle de la alerta
  */
 @Transient
  private Long descIdInstanciaPaso;


}