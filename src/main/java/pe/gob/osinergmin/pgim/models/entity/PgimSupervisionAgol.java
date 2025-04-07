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
* Clase Entidad para la tabla PGIM_TC_SUPERVISION_AGOL: 
* @descripción: Registro de la supervisión en el aplicativo AGOL
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_SUPERVISION_AGOL")
@Data
@NoArgsConstructor
public class PgimSupervisionAgol implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del  registro de supervisión Agol. Secuencia: PGIM_SEQ_SUPERVISION_AGOL
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERVISION_AGOL")
   @SequenceGenerator(name = "PGIM_SEQ_SUPERVISION_AGOL", sequenceName = "PGIM_SEQ_SUPERVISION_AGOL", allocationSize = 1)
   @Column(name = "ID_SUPERVISION_AGOL", nullable = false)
  private Long idSupervisionAgol;

  /*
  *Identificador interno de la supervisión que dio lugar al PAS. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Flag que indica si la supervisión se encuentra activa en el Agol. Los posibles valores son: "1" = Sí y "0" = No
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