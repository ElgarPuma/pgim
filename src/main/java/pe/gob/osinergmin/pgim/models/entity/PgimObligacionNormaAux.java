package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_OBLIGACION_NORMA_AUX: 
* @descripción: Vista para obtener la lista de obligaciones normativas
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_OBLIGACION_NORMA_AUX")
@Data
@NoArgsConstructor
public class PgimObligacionNormaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_OBLIGACION_NORMA_AUX:  Secuencia: PGIM_SEQ_XYZ
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_XYZ")
   @SequenceGenerator(name = "PGIM_SEQ_XYZ", sequenceName = "PGIM_SEQ_XYZ", allocationSize = 1)
   @Column(name = "ID_OBLIGACION_NORMA_AUX", nullable = false)
  private Long idObligacionNormaAux;

  /*
  *Identificador interno de la obligación normativa por criterio de la matriz de supervisión. Tabla: PGIM_TM_OBLGCN_NRMA_CRTRIO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OBLGCN_NRMA_CRTRIO", nullable = true)
  private PgimOblgcnNrmaCrtrio pgimOblgcnNrmaCrtrio;

  /*
  *Identificador interno del criterio de la matriz de supervisión. Tabla padre: PGIM_TM_MATRIZ_CRITERIO
  */
   @Column(name = "ID_MATRIZ_CRITERIO", nullable = true)
  private Long idMatrizCriterio;

  /*
  *Identificador interno de la obligación normativa. Tabla padre: PGIM_TM_NORMA_OBLIGACION
  */
   @Column(name = "ID_NORMA_OBLIGACION", nullable = true)
  private Long idNormaObligacion;

  /*
  *Indica si el registro de la tabla PGIM_TM_OBLGCN_NRMA_CRTRIO, es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE_OBLGCN_NRMA_CRTRIO", nullable = true)
  private String esVigenteOblgcnNrmaCrtrio;

  /*
  *Indica el estado del registro de la tabla PGIM_TM_OBLGCN_NRMA_CRTRIO. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO_OBLGCN_NRMA_CRTRIO", nullable = true)
  private String esRegistroOblgcnNrmaCrtrio;

  /*
  *Identificador interno del ítem de norma legal. Tabla padre PGIM_TM_NORMA_ITEM
  */
   @Column(name = "ID_NORMA_ITEM", nullable = true)
  private Long idNormaItem;

  /*
  *Identificador interno del ítem de tipificación. Tabla padre PGIM_TM_ITEM_TIPIFICACION
  */
   @Column(name = "ID_ITEM_TIPIFICACION", nullable = true)
  private Long idItemTipificacion;

  /*
  *Descripción de  la obligación normativa
  */
   @Column(name = "DE_NORMA_OBLIGACION_T", nullable = true)
  private String deNormaObligacionT;

  /*
  *Indica si el registro de la tabla PGIM_TM_NORMA_OBLIGACION, es vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
   @Column(name = "ES_VIGENTE_NORMA_OBLIGACION", nullable = true)
  private String esVigenteNormaObligacion;

  /*
  *Indica el estado del registro de la tabla PGIM_TM_NORMA_OBLIGACION. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO_NORMA_OBLIGACION", nullable = true)
  private String esRegistroNormaObligacion;

  /*
  *Descripción de la obligación normativa obtenido a través de la función: PGIM_FUN_OBLIGACION_NORMATIVA
  */
   @Column(name = "DE_OBLIGACION_NORMATIVA", nullable = true)
  private String deObligacionNormativa;

  /*
  *Detalle del contenido del ítem de norma
  */
   @Column(name = "DE_NORMA_ITEM", nullable = true)
  private String deNormaItem;

  /*
  * Código de la tipificación
  */
   @Column(name = "CO_TIPIFICACION", nullable = true)
  private String coTipificacion;

  /*
  * Nombre de la tipificación
  */
   @Column(name = "NO_ITEM_TIPIFICACION", nullable = true)
  private String noItemTipificacion;

  /*
  * Número de la norma a la que pertenece el ítem de tipificación
  */
   @Column(name = "NRO_NORMA_TIPIFICACION", nullable = true)
  private String nroNormaTipificacion;

  /*
  *Flag que indica si la obligación normativa ha sido seleccionada (Auxiliar)
  */
 @Transient
  private boolean descSeleccionado;

  /*
  *Identificador interno de la obligación normativa por hecho constatado de la supervisión. Tabla: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
 @Transient
  private Long idOblgcnNrmtvaHchoc;


}