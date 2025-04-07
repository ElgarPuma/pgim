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
* Clase Entidad para la tabla PGIM_TC_CMINERO_SPRVSION: 
* @descripción: Supervisión de componentes mineros
*
* @author: 
* @version 1.0
* @fecha_de_creación: 17/12/2024
*/
@Entity
@Table(name = "PGIM_TC_CMINERO_SPRVSION")
@Data
@NoArgsConstructor
public class PgimCmineroSprvsion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del componente minero inspeccionado. Secuencia: PGIM_SEQ_CMINERO_SPRVSION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CMINERO_SPRVSION")
   @SequenceGenerator(name = "PGIM_SEQ_CMINERO_SPRVSION", sequenceName = "PGIM_SEQ_CMINERO_SPRVSION", allocationSize = 1)
   @Column(name = "ID_CMINERO_SPRVSION", nullable = false)
  private Long idCmineroSprvsion;

  /*
  *Identificador interno de la supervisión a la que está asociado el componente minero: Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno de la supervisión a la que está asociado el componente minero: Tabla padre: PGIM_TM_COMPONENTE_MINERO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_COMPONENTE_MINERO", nullable = false)
  private PgimComponenteMinero pgimComponenteMinero;

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