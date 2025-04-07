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
* Clase Entidad para la tabla PGIM_TD_INFRACCION_CONTRA: 
* @descripción: Contratista responsable por infracción de una obligación normativa de hecho constatado
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 25/04/2023
*/
@Entity
@Table(name = "PGIM_TD_INFRACCION_CONTRA")
@Data
@NoArgsConstructor
public class PgimInfraccionContra implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del contratista responsable de la infracción asociada a la obligación normativa de la fiscalización. Secuencia: PGIM_SEQ_INFRACCION_CONTRA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INFRACCION_CONTRA")
   @SequenceGenerator(name = "PGIM_SEQ_INFRACCION_CONTRA", sequenceName = "PGIM_SEQ_INFRACCION_CONTRA", allocationSize = 1)
   @Column(name = "ID_INFRACCION_CONTRA", nullable = false)
  private Long idInfraccionContra;

  /*
  *Identificador interno de la infracción. Tabla padre: PGIM_TD_INFRACCION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INFRACCION", nullable = false)
  private PgimInfraccion pgimInfraccion;

  /*
  *Identificador interno del contratista responsable de la infracción del cual se ha copiado este registro. Tabla padre: PGIM_TD_INFRACCION_CONTRA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INFRACCION_CONTRA_ORIGEN", nullable = true)
  private PgimInfraccionContra infraccionContraOrigen;

  /*
  *Identificador interno de la persona jurídica. Tabla padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = false)
  private PgimPersona pgimPersona;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Flag que indica si la infracción se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_VIGENTE", nullable = true)
  private String flVigente;

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