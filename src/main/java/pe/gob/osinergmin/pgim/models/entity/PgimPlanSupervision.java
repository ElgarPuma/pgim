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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_PLAN_SUPERVISION: 
* @descripción: Plan de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_PLAN_SUPERVISION")
@Data
@NoArgsConstructor
public class PgimPlanSupervision implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del plan de supervisión. Secuencia: PGIM_SEQ_PLAN_SUPERVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PLAN_SUPERVISION")
   @SequenceGenerator(name = "PGIM_SEQ_PLAN_SUPERVISION", sequenceName = "PGIM_SEQ_PLAN_SUPERVISION", allocationSize = 1)
   @Column(name = "ID_PLAN_SUPERVISION", nullable = false)
  private Long idPlanSupervision;

  /*
  *Año al cual pertenece el plan de supervisión
  */
   @Column(name = "NU_ANIO", nullable = false)
  private Long nuAnio;

  /*
  *Descripción del plan de supervisión
  */
   @Column(name = "DE_PLAN_SUPERVISION", nullable = true)
  private String dePlanSupervision;

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