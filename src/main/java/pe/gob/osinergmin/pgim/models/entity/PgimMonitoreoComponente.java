package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TZ_MONITOREO_COMPONENTE: 
* @descripción: Monitoreo del componente minero 
*
* @author: 
* @version 1.0
* @fecha_de_creación: 19/11/2024
*/
@Entity
@Table(name = "PGIM_TZ_MONITOREO_COMPONENTE")
@Data
@NoArgsConstructor
public class PgimMonitoreoComponente implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del monitoreo Secuencia: PGIM_SEQ_MONITOREO_COMPONENTE
  */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MONITOREO_COMPONENTE")
  @SequenceGenerator(name = "PGIM_SEQ_MONITOREO_COMPONENTE", sequenceName = "PGIM_SEQ_MONITOREO_COMPONENTE", allocationSize = 1)
  @Column(name = "ID_MONITOREO_COMPONENTE", nullable = true)
  private Long idMonitoreoComponente;

  /*
  *Identificador interno del componente minero sobre el que se realizará el monitoreo: Tabla padre: PGIM_TM_COMPONENTE_MINERO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_COMPONENTE_MINERO", nullable = false)
  private PgimComponenteMinero pgimComponenteMinero;

  /*
  *Fecha del monitoreo del componente minero
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_MONITOREO", nullable = false)
  private Date feMonitoreo;

  /*
  *Distancia entre el espejo de agua del depósito de relave al dique
  */
   @Column(name = "DISTANCIA", nullable = true)
  private Double distancia;

  /*
  *Porcentaje del espejo de agua del depósito de relave
  */
   @Column(name = "PORCENTAJE_AGUA", nullable = true)
  private Double porcentajeAgua;

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