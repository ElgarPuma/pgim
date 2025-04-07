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
* Clase Entidad para la tabla PGIM_TM_RELACION_PASO: 
* @descripción: Relación del paso del proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_RELACION_PASO")
@Data
@NoArgsConstructor
public class PgimRelacionPaso implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la relación del paso del proceso. Secuencia: PGIM_SEQ_RELACION_PASO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RELACION_PASO")
   @SequenceGenerator(name = "PGIM_SEQ_RELACION_PASO", sequenceName = "PGIM_SEQ_RELACION_PASO", allocationSize = 1)
   @Column(name = "ID_RELACION_PASO", nullable = false)
  private Long idRelacionPaso;

  /*
  *Identificador interno del paso origen del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO_ORIGEN", nullable = false)
  private PgimPasoProceso pasoProcesoOrigen;

  /*
  *Identificador interno del paso destino del proceso. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO_DESTINO", nullable = false)
  private PgimPasoProceso pasoProcesoDestino;

  /*
  *Tipo de la relación del paso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_RELACION_PASO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_RELACION", nullable = false)
  private PgimValorParametro tipoRelacion;

  /*
  *Flag que indica si la relación inicia el proceso. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_INICIA_PROCESO", nullable = false)
  private String flIniciaProceso;

  /*
  *Flag que indica si para la transición al paso se requiere especificar destinatario. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_REQUIERE_DESTINATARIO", nullable = true)
  private String flRequiereDestinatario;

  /*
  *Flag que indica si para la transición al paso se requiere aprobación. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_REQUIERE_APROBACION", nullable = true)
  private String flRequiereAprobacion;

  /*
  *Flag que indica si la transición se invocará a través de una tarea agrupada, es decir que no estará disponible para selección por parte del usuario al momento de asignar una tarea. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_RELACION_AGRUPADA", nullable = true)
  private String flRelacionAgrupada;

  /*
  *Tipo de acción SIGED. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_ACCION_SIGED. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACCION_SIGED", nullable = true)
  private PgimValorParametro tipoAccionSiged;

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
  *Nombre del paso de origen
  */
 @Transient
  private String descNoPasoProcesoOrigen;

  /*
  *Descripción del paso de origen
  */
 @Transient
  private String descDePasoProcesoOrigen;

  /*
  *Nombre del paso de destino
  */
 @Transient
  private String descNoPasoProcesoDestino;

  /*
  *Descripción del paso de destino
  */
 @Transient
  private String descDePasoProcesoDestino;

  /*
  *Identificador interno del rol del proceso asociado al paso de origen
  */
 @Transient
  private Long descIdRolProcesoOrigen;

  /*
  *Nombre del rol del proceso asociado al paso de origen
  */
 @Transient
  private String descNoRolProcesoOrigen;

  /*
  *Identificador interno del rol del proceso asociado al paso de destino
  */
 @Transient
  private Long descIdRolProcesoDestino;

  /*
  *Nombre del rol del proceso asociado al paso de destino
  */
 @Transient
  private String descNoRolProcesoDestino;

  /*
  *Nombre del tipo de relación de paso
  */
 @Transient
  private String descNoTipoRelacion;

  /*
  *Identificador interno de la relación del paso del proceso. Tabla padre: PGIM_TM_RELACION_PASO
  */
 @Transient
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
 @Transient
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la instancia de paso del proceso
  */
 @Transient
  private String descDeMensaje;

  /*
  *Nombre del tipo de acción Siged
  */
 @Transient
  private String descTipoAccionSiged;


}