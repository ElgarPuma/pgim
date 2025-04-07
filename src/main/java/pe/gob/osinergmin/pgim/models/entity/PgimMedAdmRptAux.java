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
* Clase Entidad para la tabla PGIM_VW_MED_ADM_RPT_AUX: 
* @descripción: Vista para obtener la lista los PAS
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_MED_ADM_RPT_AUX")
@Data
@NoArgsConstructor
public class PgimMedAdmRptAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_ID_M_ADM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ID_M_ADM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ID_M_ADM_AUX", sequenceName = "PGIM_SEQ_ID_M_ADM_AUX", allocationSize = 1)
   @Column(name = "ID_MEDIDA_ADMINISTRATIVA_AUX", nullable = false)
  private Long idMedidaAdministrativaAux;

  /*
  *ID_MEDIDA_ADMINISTRATIVA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MEDIDA_ADMINISTRATIVA", nullable = true)
  private PgimMedidaAdm pgimMedidaAdm;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *ID_TIPO_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "ID_TIPO_MEDIDA_ADMINISTRATIVA", nullable = true)
  private Long idTipoMedidaAdministrativa;

  /*
  *DE_TIPO_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "DE_TIPO_MEDIDA_ADMINISTRATIVA", nullable = true)
  private String deTipoMedidaAdministrativa;

  /*
  *DE_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "DE_MEDIDA_ADMINISTRATIVA", nullable = true)
  private String deMedidaAdministrativa;

  /*
  *FE_MEDIDA_ADMINISTRATIVA
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_MEDIDA_ADMINISTRATIVA", nullable = true)
  private Date feMedidaAdministrativa;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *DESC_CO_MEDIDA_ADMINISTRATIVA
  */
 @Transient
  private String descCoMedidaAdministrativa;


}