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
* Clase Entidad para la tabla PGIM_TM_SUBSECTOR: 
* @descripción: Subsector
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 19/12/2023
*/
@Entity
@Table(name = "PGIM_TM_SUBSECTOR")
@Data
@NoArgsConstructor
public class PgimSubsector implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del subsector. Secuencia: PGIM_SEQ_SUBSECTOR
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUBSECTOR")
   @SequenceGenerator(name = "PGIM_SEQ_SUBSECTOR", sequenceName = "PGIM_SEQ_SUBSECTOR", allocationSize = 1)
   @Column(name = "ID_SUBSECTOR", nullable = false)
  private Long idSubsector;

  /*
  *Identificador del sector. Tabla padre: PGIM_TM_SECTOR
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SECTOR", nullable = false)
  private PgimSector pgimSector;

  /*
  *Nombre del subsector
  */
   @Column(name = "NO_SUBSECTOR", nullable = false)
  private String noSubsector;

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