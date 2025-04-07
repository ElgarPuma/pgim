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
* Clase Entidad para la tabla PGIM_TM_GRUPO_RIESGO: 
* @descripción: Grupo de los factores de riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_GRUPO_RIESGO")
@Data
@NoArgsConstructor
public class PgimGrupoRiesgo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del grupo de riesgo. Secuencia: PGIM_SEQ_GRUPO_RIESGO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_GRUPO_RIESGO")
   @SequenceGenerator(name = "PGIM_SEQ_GRUPO_RIESGO", sequenceName = "PGIM_SEQ_GRUPO_RIESGO", allocationSize = 1)
   @Column(name = "ID_GRUPO_RIESGO", nullable = false)
  private Long idGrupoRiesgo;

  /*
  *Nombre del grupo
  */
   @Column(name = "NO_GRUPO", nullable = false)
  private String noGrupo;

  /*
  *Descripción del grupo
  */
   @Column(name = "DE_GRUPO", nullable = true)
  private String deGrupo;

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