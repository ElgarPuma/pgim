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
* Clase Entidad para la tabla PGIM_TM_FASE_PROCESO: 
* @descripción: Fase de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_FASE_PROCESO")
@Data
@NoArgsConstructor
public class PgimFaseProceso implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la fase de proceso PGIM. Secuencia: PGIM_SEQ_FASE_PROCESO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FASE_PROCESO")
   @SequenceGenerator(name = "PGIM_SEQ_FASE_PROCESO", sequenceName = "PGIM_SEQ_FASE_PROCESO", allocationSize = 1)
   @Column(name = "ID_FASE_PROCESO", nullable = false)
  private Long idFaseProceso;

  /*
  *Identificador interno del proceso. Tabla padre: PGIM_TM_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROCESO", nullable = false)
  private PgimProceso pgimProceso;

  /*
  *Nombre de la fase del proceso
  */
   @Column(name = "NO_FASE_PROCESO", nullable = false)
  private String noFaseProceso;

  /*
  *Descripción de la fase del proceso
  */
   @Column(name = "DE_FASE_PROCESO", nullable = true)
  private String deFaseProceso;

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