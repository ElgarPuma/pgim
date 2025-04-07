package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_CONTRATO_SEGUMNTO_AUX: 
* @descripción: Vista de seguimiento de contratos
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_CONTRATO_SEGUMNTO_AUX")
@Data
@NoArgsConstructor
public class PgimContratoSegumntoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_CONTRATO_SEGUMNTO_AUX Secuencia: PGIM_SEQ_CONTRATO_SEGUMNTO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONTRATO_SEGUMNTO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_CONTRATO_SEGUMNTO_AUX", sequenceName = "PGIM_SEQ_CONTRATO_SEGUMNTO_AUX", allocationSize = 1)
   @Column(name = "ID_CONTRATO_SEGUMNTO_AUX", nullable = false)
  private Long idContratoSegumntoAux;

  /*
  *ID_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = true)
  private PgimContrato pgimContrato;

  /*
  *NU_CONTRATO
  */
   @Column(name = "NU_CONTRATO", nullable = true)
  private String nuContrato;

  /*
  *ID_EMPRESA_SUPERVISORA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EMPRESA_SUPERVISORA", nullable = true)
  private PgimEmpresaSupervisora pgimEmpresaSupervisora;

  /*
  *ID_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA", nullable = true)
  private PgimPersona pgimPersona;

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
  *ID_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = true)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *MO_IMPORTE_CONTRATO
  */
   @Column(name = "MO_IMPORTE_CONTRATO", nullable = true)
  private BigDecimal moImporteContrato;

  /*
  *SALDO_CONTRATO
  */
   @Column(name = "SALDO_CONTRATO", nullable = true)
  private BigDecimal saldoContrato;

  /*
  *PRE_COMPROMETIDO
  */
   @Column(name = "PRE_COMPROMETIDO", nullable = true)
  private BigDecimal preComprometido;

  /*
  *COMPROMETIDO
  */
   @Column(name = "COMPROMETIDO", nullable = true)
  private BigDecimal comprometido;

  /*
  *POR_LIQUIDAR
  */
   @Column(name = "POR_LIQUIDAR", nullable = true)
  private BigDecimal porLiquidar;

  /*
  *LIQUIDADO
  */
   @Column(name = "LIQUIDADO", nullable = true)
  private BigDecimal liquidado;

  /*
  *FACTURADO
  */
   @Column(name = "FACTURADO", nullable = true)
  private BigDecimal facturado;

  /*
  *TOTAL_CONSUMO_CONTRATO
  */
   @Column(name = "TOTAL_CONSUMO_CONTRATO", nullable = true)
  private BigDecimal totalConsumoContrato;

  /*
  *SALDO_PRECOMPROMETIDO
  */
   @Column(name = "SALDO_PRECOMPROMETIDO", nullable = true)
  private BigDecimal saldoPrecomprometido;

  /*
  *SALDO_COMPROMETIDO
  */
   @Column(name = "SALDO_COMPROMETIDO", nullable = true)
  private BigDecimal saldoComprometido;

  /*
  *SALDO_POR_LIQUIDAR
  */
   @Column(name = "SALDO_POR_LIQUIDAR", nullable = true)
  private BigDecimal saldoPorLiquidar;

  /*
  *SALDO_LIQUIDADO
  */
   @Column(name = "SALDO_LIQUIDADO", nullable = true)
  private BigDecimal saldoLiquidado;

  /*
  *SALDO_FACTURADO
  */
   @Column(name = "SALDO_FACTURADO", nullable = true)
  private BigDecimal saldoFacturado;

  /*
  *DESC_EXTENSION
  */
 @Transient
  private String descExtension;

  /*
  *DESC_NO_ARCHIVO
  */
 @Transient
  private String descNoArchivo;

  /*
  *DE_TITULO_REPORTE
  */
 @Transient
  private String deTituloReporte;


}