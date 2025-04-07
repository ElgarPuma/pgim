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
* Clase Entidad para la tabla PGIM_TD_COMPONENTE_HC: 
* @descripción: Componentes mineros asociados a un hecho constatado
*
* @author: 
* @version 1.0
* @fecha_de_creación: 17/12/2024
*/
@Entity
@Table(name = "PGIM_TD_COMPONENTE_HC")
@Data
@NoArgsConstructor
public class PgimComponenteHc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del componente minero asociado a un hecho constatado. Secuencia: PGIM_SEQ_COMPONENTE_HC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_COMPONENTE_HC")
   @SequenceGenerator(name = "PGIM_SEQ_COMPONENTE_HC", sequenceName = "PGIM_SEQ_COMPONENTE_HC", allocationSize = 1)
   @Column(name = "ID_COMPONENTE_HC", nullable = false)
  private Long idComponenteHc;

  /*
  *Identificador interno del hecho constatado asociado el componente minero: Tabla padre: PGIM_TC_HECHO_CONSTATADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_HECHO_CONSTATADO", nullable = false)
  private PgimHechoConstatado pgimHechoConstatado;

  /*
  *Identificador interno del componente minero a supervisar: Tabla padre: PGIM_TC_CMINERO_SPRVSION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CMINERO_SPRVSION", nullable = false)
  private PgimCmineroSprvsion pgimCmineroSprvsion;

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
   * Flag que indica si el componente minero ha sido asociado al hecho verificado.
   * Los posibles valores son: "1" = Sí y "0" = No
   */
  @Column(name = "FL_APLICA", nullable = true)
  private String flAplica;
}