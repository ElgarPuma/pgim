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

import java.math.BigDecimal;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_ENTREGABLE_LIQUIDA_AUX: 
* @descripción: Vista para obtener la lista de entregables de la supervisión, a saber: 346: Actas de supervisión; 347: Informes de supervisión; 359: Informes de supervisión fallida
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_ENTREGABLE_LIQUIDA_AUX")
@Data
@NoArgsConstructor
public class PgimEntregableLiquidaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_ENTRE_LIQ_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ENTRE_LIQ_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ENTRE_LIQ_AUX", sequenceName = "PGIM_SEQ_ENTRE_LIQ_AUX", allocationSize = 1)
   @Column(name = "ID_ENTREGABLE_LIQUIDA_AUX", nullable = false)
  private Long idEntregableLiquidaAux;

  /*
  *ID_ITEM_CONSUMO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ITEM_CONSUMO", nullable = true)
  private PgimItemConsumo pgimItemConsumo;

  /*
  *ID_TIPO_ENTREGABLE
  */
   @Column(name = "ID_TIPO_ENTREGABLE", nullable = true)
  private Long idTipoEntregable;

  /*
  *DE_TIPO_ENTREGABLE
  */
   @Column(name = "DE_TIPO_ENTREGABLE", nullable = true)
  private String deTipoEntregable;

  /*
  *ID_SUPERVISION
  */
   @Column(name = "ID_SUPERVISION", nullable = true)
  private Long idSupervision;

  /*
  *CO_SUPERVISION
  */
   @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

  /*
  *ID_TIPO_SUPERVISION
  */
  @Column(name = "ID_TIPO_SUPERVISION", nullable = true)
  private Long idTipoSupervision;

  /*
  *DE_TIPO_SUPERVISION
  */
   @Column(name = "DE_TIPO_SUPERVISION", nullable = true)
  private String deTipoSupervision;

  /*
  *ID_SUBTIPO_SUPERVISION
  */
   @Column(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private Long idSubtipoSupervision;

  /*
  *DE_SUBTIPO_SUPERVISION
  */
   @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = true)
  private String deSubtipoSupervision;

  /*
  *ID_MOTIVO_SUPERVISION
  */
   @Column(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private Long idMotivoSupervision;

  /*
  *DE_MOTIVO_SUPERVISION
  */
   @Column(name = "DE_MOTIVO_SUPERVISION", nullable = true)
  private String deMotivoSupervision;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
  private Date feInicioSupervisionReal;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
  private Date feFinSupervisionReal;

  /*
  *ID_AGENTE_SUPERVISADO
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
   @Column(name = "NO_RAZON_SOCIAL", nullable = true)
  private String noRazonSocial;

  /*
  *MO_CONSUMO_CONTRATO
  */
   @Column(name = "MO_CONSUMO_CONTRATO", nullable = true)
  private BigDecimal moConsumoContrato;

  /*
  *MO_ITEM_CONSUMO
  */
   @Column(name = "MO_ITEM_CONSUMO", nullable = true)
  private BigDecimal moItemConsumo;

  /*
  *ID_DOCUMENTO
  */
   @Column(name = "ID_DOCUMENTO", nullable = true)
  private Long idDocumento;

  /*
  *DE_ASUNTO_DOCUMENTO
  */
   @Column(name = "DE_ASUNTO_DOCUMENTO", nullable = true)
  private String deAsuntoDocumento;

  /*
  *ID_SUBCAT_DOCUMENTO
  */
   @Column(name = "ID_SUBCAT_DOCUMENTO", nullable = true)
  private Long idSubcatDocumento;

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
  *Mensaje de la instancia de paso del proceso
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *FE_ORIGEN_DOCUMENTO
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ORIGEN_DOCUMENTO", nullable = true)
  private Date feOrigenDocumento;

  /*
  *FE_INSTANCIA_PASO
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;

  /*
  *ID_CONTRATO
  */
   @Column(name = "ID_CONTRATO", nullable = true)
  private Long idContrato;

  /*
  *NU_CONTRATO
  */
   @Column(name = "NU_CONTRATO", nullable = true)
  private String nuContrato;

  /*
  *ID_DIVISION_SUPERVISORA
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *DE_DIVISION_SUPERVISORA
  */
   @Column(name = "DE_DIVISION_SUPERVISORA", nullable = true)
  private String deDivisionSupervisora;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *ID_TIPO_ESTADIO_CONSUMO
  */
   @Column(name = "ID_TIPO_ESTADIO_CONSUMO", nullable = true)
  private Long idTipoEstadioConsumo;

  /*
  *DE_TIPO_ESTADIO_CONSUMO
  */
   @Column(name = "DE_TIPO_ESTADIO_CONSUMO", nullable = true)
  private String deTipoEstadioConsumo;
   
  /*
  *FE_PRESENTA_ENTREGABLE
  */
   @Column(name = "FE_PRESENTA_ENTREGABLE", nullable = true)
  private Date fePresentaEntregable;


}