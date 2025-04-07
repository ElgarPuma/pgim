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
* Clase Entidad para la tabla PGIM_TM_MATRIZ_GRPO_CRTRIO: 
* @descripción: Grupo de criterio de matriz de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_MATRIZ_GRPO_CRTRIO")
@Data
@NoArgsConstructor
public class PgimMatrizGrpoCrtrio implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del grupo de criterio de la matriz de supervisión. Secuencia: PGIM_SEQ_MATRIZ_GRPO_CRTRIO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MATRIZ_GRPO_CRTRIO")
   @SequenceGenerator(name = "PGIM_SEQ_MATRIZ_GRPO_CRTRIO", sequenceName = "PGIM_SEQ_MATRIZ_GRPO_CRTRIO", allocationSize = 1)
   @Column(name = "ID_MATRIZ_GRPO_CRTRIO", nullable = false)
  private Long idMatrizGrpoCrtrio;

  /*
  *Identificador interno de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MATRIZ_SUPERVISION", nullable = false)
  private PgimMatrizSupervision pgimMatrizSupervision;

  /*
  *Código del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_GRPO_CRTRIO", nullable = false)
  private String coMatrizGrpoCrtrio;

  /*
  *Nombre del grupo de criterio de la matriz de supervisión
  */
   @Column(name = "NO_MATRIZ_GRPO_CRTRIO", nullable = false)
  private String noMatrizGrpoCrtrio;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN", nullable = true)
  private Long nuOrden;

  /*
  *Flag que indica si el grupo de criterio es visible. Los posibles valores son: "1" = Visible y "0" = No visible
  */
   @Column(name = "ES_VISIBLE", nullable = false)
  private String esVisible;

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
  *Cantidadde criterios registrados en el grupo de la matriz
  */
 @Transient
  private Long descCantidadCriterios;

  /*
  *Nombre de la especialidad
  */
 @Transient
  private String descEspecialidad;


}