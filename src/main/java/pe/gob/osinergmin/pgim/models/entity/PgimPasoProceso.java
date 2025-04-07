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
* Clase Entidad para la tabla PGIM_TM_PASO_PROCESO: 
* @descripción: Paso de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_PASO_PROCESO")
@Data
@NoArgsConstructor
public class PgimPasoProceso implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del paso de proceso PGIM. Secuencia: PGIM_SEQ_PASO_PROCESO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PASO_PROCESO")
   @SequenceGenerator(name = "PGIM_SEQ_PASO_PROCESO", sequenceName = "PGIM_SEQ_PASO_PROCESO", allocationSize = 1)
   @Column(name = "ID_PASO_PROCESO", nullable = false)
  private Long idPasoProceso;

  /*
  *Identificador interno de la fase del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FASE_PROCESO", nullable = false)
  private PgimFaseProceso pgimFaseProceso;

  /*
  *Identificador interno del rol del proceso PGIM. Tabla padre PGIM_TM_ROL_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ROL_PROCESO", nullable = false)
  private PgimRolProceso pgimRolProceso;

  /*
  *Código del paso del proceso
  */
   @Column(name = "CO_PASO_PROCESO", nullable = true)
  private String coPasoProceso;

  /*
  *Nombre del paso del proceso
  */
   @Column(name = "NO_PASO_PROCESO", nullable = false)
  private String noPasoProceso;

  /*
  *Descripción del paso del proceso
  */
   @Column(name = "DE_PASO_PROCESO", nullable = true)
  private String dePasoProceso;

  /*
  *Flag que indica si desde el paso se pueden notificar documentos de manera electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_NOTIFICABLE_E", nullable = true)
  private String flNotificableE;

  /*
  *Flag que indica si la tarea es agrupadora esto es, que incluye asignación paralela de otras tareas. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_AGRUPADORA", nullable = true)
  private String flAgrupadora;

  /*
  *Flag que indica si desde el paso se pueden etiquetar documentos para notificar de manera electrónicamente a través del SNE. Posibles valores: "1" = Sí y 0 = "No"
  */
  @Column(name = "FL_ETIQUETAR_NOTIFICACION", nullable = true)
  private String flEtiquetarNotificacion;
  
  /*
   *Código del subproceso en caso exista
   */
   @Column(name = "CO_SUBPROCESO", nullable = true)
   private String coSubproceso;
   
   /*
    *Código que señala si el paso, en el caso que sea un paso de subproceso, es el inicio del subproceso "I" o el fin del subproceso "F"; en cualquier otro caso el valor es nulo
    */
    @Column(name = "CO_TIPO_PASO_SUBPROCESO", nullable = true)
    private String coTipoPasoSubproceso;

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