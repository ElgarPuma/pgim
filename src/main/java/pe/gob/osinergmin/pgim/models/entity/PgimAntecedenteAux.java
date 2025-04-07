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
* Clase Entidad para la tabla PGIM_VW_ANTECEDENTE_AUX: 
* @descripción: Vista para la lista de antecedentes detallado
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_ANTECEDENTE_AUX")
@Data
@NoArgsConstructor
public class PgimAntecedenteAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_ANTECEDENTES_AUX Secuencia: PGIM_SEQ_ANTECEDENTES_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ANTECEDENTES_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ANTECEDENTES_AUX", sequenceName = "PGIM_SEQ_ANTECEDENTES_AUX", allocationSize = 1)
   @Column(name = "ID_ANTECEDENTE_SUPERV_AUX", nullable = false)
  private Long idAntecedenteSupervAux;

  /*
  *ID_ANTECEDENTE_SUPERV
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ANTECEDENTE_SUPERV", nullable = true)
  private PgimAntecedenteSuperv pgimAntecedenteSuperv;

  /*
  *ID_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *ID_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = true)
  private PgimDocumento pgimDocumento;

  /*
  *DE_ASPECTOS_REVISADOS
  */
   @Column(name = "DE_ASPECTOS_REVISADOS", nullable = true)
  private String deAspectosRevisados;

  /*
  *ID_TIPO_ANTECEDENTE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ANTECEDENTE", nullable = true)
  private PgimValorParametro tipoAntecedente;

  /*
  *NO_TIPO_ANTECEDENTE
  */
   @Column(name = "NO_TIPO_ANTECEDENTE", nullable = true)
  private String noTipoAntecedente;

  /*
  *ID_TIPO_ORIGIN
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ORIGIN", nullable = true)
  private PgimValorParametro tipoOrigin;

  /*
  *NO_TIPO_ORIGIN
  */
   @Column(name = "NO_TIPO_ORIGIN", nullable = true)
  private String noTipoOrigin;

  /*
  *ID_CATEGORIA_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CATEGORIA_DOCUMENTO", nullable = true)
  private PgimCategoriaDoc pgimCategoriaDoc;

  /*
  *NO_CATEGORIA_DOCUMENTO
  */
   @Column(name = "NO_CATEGORIA_DOCUMENTO", nullable = true)
  private String noCategoriaDocumento;

  /*
  *ID_SUBCAT_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = true)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

  /*
  *NO_SUBCAT_DOCUMENTO
  */
   @Column(name = "NO_SUBCAT_DOCUMENTO", nullable = true)
  private String noSubcatDocumento;

  /*
  *CO_DOCUMENTO_SIGED
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "CO_DOCUMENTO_SIGED", nullable = true)
  private PgimDocumento documentoSiged;

  /*
  *DE_ASUNTO_DOCUMENTO
  */
   @Column(name = "DE_ASUNTO_DOCUMENTO", nullable = true)
  private String deAsuntoDocumento;

  /*
  *ID_INSTANCIA_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *COTABLAINSTANCIA
  */
   @Column(name = "COTABLAINSTANCIA", nullable = true)
  private Long cotablainstancia;

  /*
  *FECHA
  */
   @Column(name = "FECHA", nullable = true)
  private String fecha;

  /*
  *CODIGO
  */
   @Column(name = "CODIGO", nullable = true)
  private String codigo;

  /*
  *DESCRIPCION
  */
   @Column(name = "DESCRIPCION", nullable = true)
  private String descripcion;

  /**
   * 
   * Identificador interno del documento Siged desde el que se copio el actual documento
   */
  @Column(name = "CO_DOCUMENTO_SIGED_P_COPIA", nullable = true)
  private Long coDocumentoSigedPCopia;

  /*
  *TIPO_SUPERVISION
  */
  @Column(name = "TIPO_SUPERVISION", nullable = true)
  private String tipoSupervision;

  /*
  *NOMBRE_ARCHIVO
  */
 @Transient
  private String nombreArchivo;


}