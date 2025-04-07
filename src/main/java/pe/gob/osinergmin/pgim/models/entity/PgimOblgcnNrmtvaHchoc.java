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
* Clase Entidad para la tabla PGIM_TC_OBLGCN_NRMTVA_HCHOC: 
* @descripción: Obligación normativa por hecho constatado de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_OBLGCN_NRMTVA_HCHOC")
@Data
@NoArgsConstructor
public class PgimOblgcnNrmtvaHchoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la obligación normativa por hecho constatado de la supervisión. Secuencia: PGIM_SEQ_OBLGCN_NRMTVA_HCHOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_OBLGCN_NRMTVA_HCHOC")
   @SequenceGenerator(name = "PGIM_SEQ_OBLGCN_NRMTVA_HCHOC", sequenceName = "PGIM_SEQ_OBLGCN_NRMTVA_HCHOC", allocationSize = 1)
   @Column(name = "ID_OBLGCN_NRMTVA_HCHOC", nullable = false)
  private Long idOblgcnNrmtvaHchoc;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla padre: PGIM_TC_HECHO_CONSTATADO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_HECHO_CONSTATADO", nullable = false)
  private PgimHechoConstatado pgimHechoConstatado;

  /*
  *Identificador interno de la obligación normativa por criterio de la matriz de supervisión.. Tabla padre: PGIM_TM_OBLGCN_NRMA_CRTRIO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OBLGCN_NRMA_CRTRIO", nullable = false)
  private PgimOblgcnNrmaCrtrio pgimOblgcnNrmaCrtrio;

  /*
  *Aplica para la obligación normativa. Los posibles valores son: "1" = Aplica y "0" = No aplica
  */
   @Column(name = "ES_APLICA", nullable = false)
  private String esAplica;

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