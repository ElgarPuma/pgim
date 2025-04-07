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
* Clase Entidad para la tabla PGIM_TM_RELACION_ACCION: 
* @descripción: Acciones por relación de paso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_RELACION_ACCION")
@Data
@NoArgsConstructor
public class PgimRelacionAccion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la acción por relación de paso. Secuencia: PGIM_SEQ_RELACION_ACCION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RELACION_ACCION")
   @SequenceGenerator(name = "PGIM_SEQ_RELACION_ACCION", sequenceName = "PGIM_SEQ_RELACION_ACCION", allocationSize = 1)
   @Column(name = "ID_RELACION_ACCION", nullable = false)
  private Long idRelacionAccion;

  /*
  *Identificador interno de la relación de paso. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO", nullable = false)
  private PgimRelacionPaso pgimRelacionPaso;

  /*
  *Identificador interno del paso con el que se creará la relación para asignaciones paralelas. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO_PARALELA", nullable = false)
  private PgimRelacionPaso pgimRelacionPasoParalela;
   
  /*
  *Valor lógico que señala si la relación dada por el valor en la columna PGIM_TM_RELACION_ACCION.ID_RELACION_PASO_PARALELA será creada con el valor del paso activo o no activo. Los posibles valores son: "1" = Activo y "0" = Inactivo; para los valores NULL quiere decir que no aplica porque el valor PGIM_TM_RELACION_ACCION.ID_RELACION_PASO_PARALELA = NULL
  */
   @Column(name = "FL_ACTIVAR_INSTANCIA_PASO", nullable = true)
  private String flActivarInstanciaPaso;  

  /*
  *Descripción de la acción a realizarse durante la transición de paso
  */
   @Column(name = "DE_ACCION", nullable = false)
  private String deAccion;

  /*
  *Número de orden de presentación
  */
   @Column(name = "NU_ORDEN", nullable = false)
  private Long nuOrden;

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