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
* Clase Entidad para la tabla PGIM_VW_DOCUMENTO_AUX: 
* @descripción: Vista de documentos por expediente
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_DOCUMENTO_AUX")
@Data
@NoArgsConstructor
public class PgimDocumentoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_DOCUMENTOS_AUX Secuencia: PGIM_SEQ_DOCUMENTO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DOCUMENTO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_DOCUMENTO_AUX", sequenceName = "PGIM_SEQ_DOCUMENTO_AUX", allocationSize = 1)
   @Column(name = "ID_DOCUMENTO_AUX", nullable = false)
  private Long idDocumentoAux;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *NO_FASE_PROCESO
  */
   @Column(name = "NO_FASE_PROCESO", nullable = true)
  private String noFaseProceso;

  /*
  *CO_CATEGORIA_DOCUMENTO
  */
   @Column(name = "CO_CATEGORIA_DOCUMENTO", nullable = true)
  private String coCategoriaDocumento;

  /*
  *NO_CATEGORIA_DOCUMENTO
  */
   @Column(name = "NO_CATEGORIA_DOCUMENTO", nullable = true)
  private String noCategoriaDocumento;

  /*
  *CO_SUBCAT_DOCUMENTO
  */
   @Column(name = "CO_SUBCAT_DOCUMENTO", nullable = true)
  private String coSubcatDocumento;

  /*
  *NO_SUBCAT_DOCUMENTO
  */
   @Column(name = "NO_SUBCAT_DOCUMENTO", nullable = true)
  private String noSubcatDocumento;

  /*
  *DE_ASUNTO_DOCUMENTO
  */
   @Column(name = "DE_ASUNTO_DOCUMENTO", nullable = true)
  private String deAsuntoDocumento;

  /*
  *ID_TIPO_ORIGEN_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ORIGEN_DOCUMENTO", nullable = true)
  private PgimValorParametro tipoOrigenDocumento;

  /*
  *NO_TIPO_ORIGEN_DOCUMENTO
  */
   @Column(name = "NO_TIPO_ORIGEN_DOCUMENTO", nullable = true)
  private String noTipoOrigenDocumento;

  /*
  *NO_USUARIO
  */
   @Column(name = "NO_USUARIO", nullable = true)
  private String noUsuario;

  /*
  *FE_CREACION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = true)
  private Date feCreacion;


}