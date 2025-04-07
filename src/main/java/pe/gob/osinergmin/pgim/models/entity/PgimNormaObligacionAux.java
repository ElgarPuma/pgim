package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_NORMA_OBLIGACION_AUX: 
* @descripción: Vista para obtener la lista de obligaciones de norma con información adicional
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 24/05/2021
*/
@Entity
@Table(name = "PGIM_VW_NORMA_OBLIGACION_AUX")
@Data
@NoArgsConstructor
public class PgimNormaObligacionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la obligación de norma por criterio de matriz de supervisión AUX. Secuencia: PGIM_SEQ_OBLGCN_NRMA_CRTRIO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_OBLGCN_NRMA_CRTRIO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_OBLGCN_NRMA_CRTRIO_AUX", sequenceName = "PGIM_SEQ_OBLGCN_NRMA_CRTRIO_AUX", allocationSize = 1)
   @Column(name = "ID_NORMA_OBLIGACION_AUX", nullable = false)
  private Long idNormaObligacionAux;

  /*
  *ID_NORMA_OBLIGACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA_OBLIGACION", nullable = true)
  private PgimNormaObligacion pgimNormaObligacion;

  /*
  *NO_CORTO_NORMA
  */
   @Column(name = "NO_CORTO_NORMA", nullable = true)
  private String noCortoNorma;

  /*
  *NO_TIPO_NORMA
  */
   @Column(name = "NO_TIPO_NORMA", nullable = true)
  private String noTipoNorma;

  /*
  *ID_TIPO_NORMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_NORMA", nullable = true)
  private PgimValorParametro tipoNorma;

  /*
  *NO_NORMA
  */
   @Column(name = "NO_NORMA", nullable = true)
  private String noNorma;

  /*
  *CO_ITEM
  */
   @Column(name = "CO_ITEM", nullable = true)
  private String coItem;

  /*
  *DE_CONTENIDO
  */
   @Column(name = "DE_CONTENIDO_T", nullable = true)
  private String deContenidoT;

  /*
  *FL_VIGENTE_ITEM
  */
   @Column(name = "FL_VIGENTE_ITEM", nullable = true)
  private String flVigenteItem;

  /*
  *ID_DIVISION_ITEM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_ITEM", nullable = true)
  private PgimValorParametro divisionItem;

  /*
  *UBICACION_ITEM
  */
   @Column(name = "UBICACION_ITEM", nullable = true)
  private String ubicacionItem;

  /*
  *DE_NORMA_OBLIGACION
  */
   @Column(name = "DE_NORMA_OBLIGACION_T", nullable = true)
  private String deNormaObligacionT;

  /*
  *ES_VIGENTE_NORMA_OBLIGACION
  */
   @Column(name = "ES_VIGENTE_NORMA_OBLIGACION", nullable = true)
  private String esVigenteNormaObligacion;

  /*
  *DE_SANCION_PECUNIARIA_UIT
  */
   @Column(name = "DE_SANCION_PECUNIARIA_UIT", nullable = true)
  private String deSancionPecuniariaUit;

  /*
  *CO_TIPIFICACION
  */
   @Column(name = "CO_TIPIFICACION", nullable = true)
  private String coTipificacion;

  /*
  *NO_ITEM_TIPIFICACION
  */
   @Column(name = "NO_ITEM_TIPIFICACION", nullable = true)
  private String noItemTipificacion;

  /*
  *ES_VIGENTE_TIPIFICACION
  */
   @Column(name = "ES_VIGENTE_TIPIFICACION", nullable = true)
  private String esVigenteTipificacion;

  /*
  *CO_NORMA_TIPIFICACION
  */
   @Column(name = "CO_NORMA_TIPIFICACION", nullable = true)
  private String coNormaTipificacion;

   /*
   *ID_ITEM_TIPIFICACION
   */
	@Column(name = "ID_ITEM_TIPIFICACION", nullable = true)
	private Long idItemTipificacion;

  /*
   *ID_NORMA_ITEM_PADRE
   */
	@Column(name = "ID_NORMA_ITEM_PADRE", nullable = true)
	private Long idNormaItemPadre;

  /*
   *ID_NORMA_ITEM
   */
	@Column(name = "ID_NORMA_ITEM", nullable = true)
	private Long idNormaItem;
}