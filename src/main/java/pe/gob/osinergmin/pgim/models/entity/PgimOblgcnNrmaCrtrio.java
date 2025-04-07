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
* Clase Entidad para la tabla PGIM_TM_OBLGCN_NRMA_CRTRIO: 
* @descripción: Obligación normativa por criterio de matriz de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_OBLGCN_NRMA_CRTRIO")
@Data
@NoArgsConstructor
public class PgimOblgcnNrmaCrtrio implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la obligación normativa por criterio de la matriz de supervisión. Secuencia: PGIM_SEQ_OBLGCN_NRMA_CRTRIO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_OBLGCN_NRMA_CRTRIO")
   @SequenceGenerator(name = "PGIM_SEQ_OBLGCN_NRMA_CRTRIO", sequenceName = "PGIM_SEQ_OBLGCN_NRMA_CRTRIO", allocationSize = 1)
   @Column(name = "ID_OBLGCN_NRMA_CRTRIO", nullable = false)
  private Long idOblgcnNrmaCrtrio;

  /*
  *Identificador interno del criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_CRITERIO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MATRIZ_CRITERIO", nullable = false)
  private PgimMatrizCriterio pgimMatrizCriterio;

  /*
  *Identificador interno de la obligación normativa. Tabla padre: PGIM_TM_NORMA_OBLIGACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA_OBLIGACION", nullable = false)
  private PgimNormaObligacion pgimNormaObligacion;

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
  *Nombre corto de la norma legal
  */
 @Transient
  private String descNoCortoNorma;

  /*
  *Código del ítem de la norma
  */
 @Transient
  private String descCoItem;

  /*
  *Contenido del ítem de norma
  */
 @Transient
  private String descDeContenido;

  /*
  *Descripción de  la obligación normativa
  */
 @Transient
  private String descDeNormaObligacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
 @Transient
  private String descEsVigenteNormaObligacion;

  /*
  *Descripción de la sancion pecuniaria en UIT
  */
 @Transient
  private String descDeSancionPecuniariaUit;

  /*
  *Código de la tipificación
  */
 @Transient
  private String descCoTipificacion;

  /*
  *Nombre del ítem de tipificación
  */
 @Transient
  private String descNoItemTipificacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
 @Transient
  private String descEsVigenteTipificacion;

  /*
  *Es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
 @Transient
  private String descFlVigenteItem;

  /*
  *Nombre del tipo de norma
  */
 @Transient
  private String descNoTipoNorma;

  /*
  *Ubicación del item de norma
  */
 @Transient
  private String descUbicacionItem;

  /*
  *Número o código de la norma de tipificación
  */
 @Transient
  private String descCoNormaTipificacion;

  /*
  *Tipo de norma
  */
 @Transient
  private Long descIdTipoNorma;

  /*
  *Tipo division del item
  */
 @Transient
  private Long descIdDivisionItem;

  /*
  *Nombre de la norma legal
  */
 @Transient
  private String descNoNorma;

  /*
  *DESC_NU_HIJOS
  */
 @Transient
  private Long descNuHijos;


}