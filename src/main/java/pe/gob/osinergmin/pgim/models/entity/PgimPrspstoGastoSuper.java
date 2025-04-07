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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_PRSPSTO_GASTO_SUPER: 
* @descripción: Vista para reporte ad-hoc de presupuesto y gasto de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_PRSPSTO_GASTO_SUPER")
@Data
@NoArgsConstructor
public class PgimPrspstoGastoSuper implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_PRSPSTO_GASTO_SUPER. Secuencia: PGIM_SEQ_ID_CONTRATO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ID_CONTRATO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ID_CONTRATO_AUX", sequenceName = "PGIM_SEQ_ID_CONTRATO_AUX", allocationSize = 1)
   @Column(name = "ID_CONTRATO_AUX", nullable = false)
  private Long idContratoAux;

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
  *FE_INICIO_CONTRATO
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_CONTRATO", nullable = true)
  private Date feInicioContrato;

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
  *NU_RUC_EMPRESA_SUPERVISORA
  */
   @Column(name = "NU_RUC_EMPRESA_SUPERVISORA", nullable = true)
  private String nuRucEmpresaSupervisora;

  /*
  *DE_EMPRESA_SUPERVISORA
  */
   @Column(name = "DE_EMPRESA_SUPERVISORA", nullable = true)
  private String deEmpresaSupervisora;

  /*
  *MO_IMPORTE_CONTRATO
  */
   @Column(name = "MO_IMPORTE_CONTRATO", nullable = true)
  private BigDecimal moImporteContrato;

  /*
  *MO_CONSUMO_CONTRATO
  */
   @Column(name = "MO_CONSUMO_CONTRATO", nullable = true)
  private BigDecimal moConsumoContrato;

  /*
  *MO_SALDO
  */
   @Column(name = "MO_SALDO", nullable = true)
  private BigDecimal moSaldo;

  /*
  *MO_ACTA_SUPERVISION
  */
   @Column(name = "MO_ACTA_SUPERVISION", nullable = true)
  private BigDecimal moActaSupervision;

  /*
  *NU_ACTA_SUPERVISION
  */
   @Column(name = "NU_ACTA_SUPERVISION", nullable = true)
  private Long nuActaSupervision;

  /*
  *MO_INFORME_SUPERVISION
  */
   @Column(name = "MO_INFORME_SUPERVISION", nullable = true)
  private BigDecimal moInformeSupervision;

  /*
  *NU_INFORME_SUPERVISION
  */
   @Column(name = "NU_INFORME_SUPERVISION", nullable = true)
  private Long nuInformeSupervision;

  /*
  *MO_SUPERVISION_FALLIDA
  */
   @Column(name = "MO_SUPERVISION_FALLIDA", nullable = true)
  private BigDecimal moSupervisionFallida;

  /*
  *NU_SUPERVISION_FALLIDA
  */
   @Column(name = "NU_SUPERVISION_FALLIDA", nullable = true)
  private Long nuSupervisionFallida;

  /*
  *MO_INFORME_GESTION
  */
   @Column(name = "MO_INFORME_GESTION", nullable = true)
  private BigDecimal moInformeGestion;

  /*
  *NU_INFORME_GESTION
  */
   @Column(name = "NU_INFORME_GESTION", nullable = true)
  private Long nuInformeGestion;

  /*
  *DESC_NU_ANIO
  */
 @Transient
  private Long descNuAnio;

  /*
  *DESC_NO_ARCHIVO
  */
 @Transient
  private String descNoArchivo;

  /*
  *DESC_EXTENSION
  */
 @Transient
  private String descExtension;

  /*
  *DE_TITULO_REPORTE
  */
 @Transient
  private String deTituloReporte;


}