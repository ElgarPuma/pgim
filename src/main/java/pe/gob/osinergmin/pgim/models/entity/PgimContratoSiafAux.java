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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_CONTRATO_SIAF_AUX: 
* @descripción: Vista para obtener la lista de SIAF en el contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 08/04/2021
*/
@Entity
@Table(name = "PGIM_VW_CONTRATO_SIAF")
@Data
@NoArgsConstructor
public class PgimContratoSiafAux implements Serializable {
    
    private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_ENTRE_LIQ_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ENTRE_LIQ_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ENTRE_LIQ_AUX", sequenceName = "PGIM_SEQ_ENTRE_LIQ_AUX", allocationSize = 1)
   @Column(name = "ID_CONTRATO_SIAF_AUX", nullable = false)
  private Long idContratoSiafAux;

  /*
  *ID_CONTRATO_SIAF
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO_SIAF", nullable = true)
  private PgimContratoSiaf pgimContratoSiaf;

  /*
  *ID_CONTRATO
  */
   @Column(name = "ID_CONTRATO", nullable = true)
  private Long idContrato;

  /*
  *NU_SIAF
  */
  @Column(name = "NU_SIAF", nullable = true)
  private String nuSiaf;

  /*
  *NU_ANIO
  */
   @Column(name = "NU_ANIO", nullable = true)
  private Long nuAnio;

  /*
  *MO_PRESUPUESTO_SIAF
  */
   @Column(name = "MO_PRESUPUESTO_SIAF", nullable = true)
  private BigDecimal moPresupuestoSiaf;

  /*
  *MO_CONSUMO_CONTRATO
  */
   @Column(name = "MO_CONSUMO_CONTRATO", nullable = true)
  private BigDecimal moConsumoContrato;

}
