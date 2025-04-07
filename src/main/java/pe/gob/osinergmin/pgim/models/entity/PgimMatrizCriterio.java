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
* Clase Entidad para la tabla PGIM_TM_MATRIZ_CRITERIO: 
* @descripción: Criterio de matriz de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_MATRIZ_CRITERIO")
@Data
@NoArgsConstructor
public class PgimMatrizCriterio implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del criterio de la matriz de supervisión. Secuencia: PGIM_SEQ_MATRIZ_CRITERIO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MATRIZ_CRITERIO")
   @SequenceGenerator(name = "PGIM_SEQ_MATRIZ_CRITERIO", sequenceName = "PGIM_SEQ_MATRIZ_CRITERIO", allocationSize = 1)
   @Column(name = "ID_MATRIZ_CRITERIO", nullable = false)
  private Long idMatrizCriterio;

  /*
  *Identificador interno del grupo de criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_GRPO_CRTRIO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MATRIZ_GRPO_CRTRIO", nullable = false)
  private PgimMatrizGrpoCrtrio pgimMatrizGrpoCrtrio;

  /*
  *Código del criterio de la matriz de supervisión
  */
   @Column(name = "CO_MATRIZ_CRITERIO", nullable = false)
  private String coMatrizCriterio;

  /*
  *Descripción del criterio de la matriz de supervisión
  */
   @Column(name = "DE_MATRIZ_CRITERIO", nullable = false)
  private String deMatrizCriterio;

  /*
  *Resumen de la base legal del criterio de la matriz de supervisión
  */
   @Column(name = "DE_BASE_LEGAL", nullable = false)
  private String deBaseLegal;

  /*
  *Número de orden en la presentación
  */
   @Column(name = "NU_ORDEN", nullable = true)
  private Long nuOrden;

  /*
  *Copia por defecto. Señala, cuando es "1", que el criterio se copiará por defecto en la fiscalización relacionada con la matriz de verificación; por otro lado, cuando es "0", señala que el criterio no será copiado de manera automática.
  */
   @Column(name = "FL_COPIA_POR_DEFECTO", nullable = false)
  private String flCopiaPorDefecto;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE", nullable = false)
  private String esVigente;

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
  *Identificador de la matriz de supervisión
  */
 @Transient
  private Long descIdMatrizSupervision;

  /*
  *Identificador de la matriz de supervisión
  */
 @Transient
  private String descCoMatrizGrpoCrtrio;

  /*
  *Descripción del nombre del grupo de criterio de la matriz
  */
 @Transient
  private String descNoMatrizGrpoCrtrio;

  /*
  *Número de obligaciones registradas por criterio
  */
 @Transient
  private Long descNuObligaciones;


}