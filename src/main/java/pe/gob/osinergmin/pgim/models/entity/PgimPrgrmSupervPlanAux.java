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
* Clase Entidad para la tabla PGIM_VW_PRGRM_SUPERV_PLAN_AUX: 
* @descripción: Vista para obtener la lista de los programas del plan de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_PRGRM_SUPERV_PLAN_AUX")
@Data
@NoArgsConstructor
public class PgimPrgrmSupervPlanAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_PRG_SUPERVISION_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PRG_SUPERVISION_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_PRG_SUPERVISION_AUX", sequenceName = "PGIM_SEQ_PRG_SUPERVISION_AUX", allocationSize = 1)
   @Column(name = "ID_PROGRAMA_SUPERVISION_AUX", nullable = false)
  private Long idProgramaSupervisionAux;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROGRAMA_SUPERVISION", nullable = false)
  private PgimPrgrmSupervision pgimPrgrmSupervision;

  /*
  *ID_PLAN_SUPERVISION
  */
   @Column(name = "ID_PLAN_SUPERVISION", nullable = true)
  private Long idPlanSupervision;

  /*
  *ID_DIVISION_SUPERVISORA
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *ID_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *ID_LINEA_PROGRAMA
  */
   @Column(name = "ID_LINEA_PROGRAMA", nullable = true)
  private Long idLineaPrograma;

  /*
  *ID_LINEA_PROGRAMA_ESTADO
  */
   @Column(name = "ID_LINEA_PROGRAMA_ESTADO", nullable = true)
  private Long idLineaProgramaEstado;

  /*
  *NO_DIVISION_SUPERVISION
  */
   @Column(name = "NO_DIVISION_SUPERVISION", nullable = true)
  private String noDivisionSupervision;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *ESTADO
  */
   @Column(name = "ESTADO", nullable = true)
  private String estado;

  /*
  *MO_PARTIDA
  */
   @Column(name = "MO_PARTIDA", nullable = true)
  private BigDecimal moPartida;

  /*
  *MO_COSTO_TOTAL
  */
   @Column(name = "MO_COSTO_TOTAL", nullable = true)
  private BigDecimal moCostoTotal;


}