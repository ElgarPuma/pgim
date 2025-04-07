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
* Clase Entidad para la tabla PGIM_TC_INSTANCIA_PROCES: 
* @descripción: Instancia de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_INSTANCIA_PROCES")
@Data
@NoArgsConstructor
public class PgimInstanciaProces implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la instancia del proceso PGIM. Secuencia: PGIM_SEQ_INSTANCIA_PROCES
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INSTANCIA_PROCES")
   @SequenceGenerator(name = "PGIM_SEQ_INSTANCIA_PROCES", sequenceName = "PGIM_SEQ_INSTANCIA_PROCES", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = false)
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la instancia del proceso padre. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO_PADRE", nullable = true)
  private PgimInstanciaProces instanciaProcesoPadre;

  /*
  *Identificador interno del proceso. Tabla padre: PGIM_TM_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROCESO", nullable = false)
  private PgimProceso pgimProceso;

  /*
  *Nombre de la tabla en donde se encuentra la instancia de negocio
  */
   @Column(name = "NO_TABLA_INSTANCIA", nullable = false)
  private String noTablaInstancia;

  /*
  *Identificador interno de la instancia de negocio en la tabla señalada por NO_TABLA_INSTANCIA
  */
   @Column(name = "CO_TABLA_INSTANCIA", nullable = false)
  private Long coTablaInstancia;

  /*
  *Número de expediente Siged
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;
   
  /*
  *Nombre del usuario Windows del flujo principal; este se actualiza al realizar una asignación convecional (reenvío simple) o asignación en subflujo inicial (aquel que se realiza desde una instancia de paso cuya columna ID_TIPO_SUBFLUJO = NULL ) colocando, en este último caso, al usuario del subflujo que se defina como principal en el asignación
  */
   @Column(name = "NO_USUARIO_PRINCIPAL", nullable = true)
  private String noUsuarioPrincipal;   

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
  *Nombre de tabla de la instancia
  */
 @Transient
  private String descNoTablaInstancia;

  /*
  *Identificador del contrato relacionado con la instancia de proceso
  */
 @Transient
  private Long descIdContrato;

  /*
  *Identificador de la especialidad relacionada con la instancia de proceso
  */
 @Transient
  private Long descIdEspecialidad;


}