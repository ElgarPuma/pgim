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
* Clase Entidad para la tabla PGIM_TC_PRGRM_SUPERVISION: 
* @descripción: Programa de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_PRGRM_SUPERVISION")
@Data
@NoArgsConstructor
public class PgimPrgrmSupervision implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del programa de supervisión. Secuencia: PGIM_SEQ_PRGRM_SUPERVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PRGRM_SUPERVISION")
   @SequenceGenerator(name = "PGIM_SEQ_PRGRM_SUPERVISION", sequenceName = "PGIM_SEQ_PRGRM_SUPERVISION", allocationSize = 1)
   @Column(name = "ID_PROGRAMA_SUPERVISION", nullable = false)
  private Long idProgramaSupervision;

  /*
  *Identificador interno del plan de supervisión. Tabla padre: PGIM_TC_PLAN_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PLAN_SUPERVISION", nullable = false)
  private PgimPlanSupervision pgimPlanSupervision;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Descripción del programa de supervisión
  */
   @Column(name = "DE_PROGRAMA_SUPERVISION", nullable = true)
  private String deProgramaSupervision;

  /*
  *Monto en soles de la partida asignada al programa
  */
   @Column(name = "MO_PARTIDA", nullable = true)
  private BigDecimal moPartida;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Usuario creador
  */
   @Column(name = "US_CREACION", nullable = false)
  private String usCreacion;

  /*
  *Terminal de creación
  */
   @Column(name = "IP_CREACION", nullable = false)
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = false)
  private Date feCreacion;

  /*
  *Usuario modificador
  */
   @Column(name = "US_ACTUALIZACION", nullable = true)
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
   @Column(name = "IP_ACTUALIZACION", nullable = true)
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ACTUALIZACION", nullable = true)
  private Date feActualizacion;

  /*
  *Etiqueta para el nombre del programa de supervisión
  */
 @Transient
  private String noProgramaSupervision;

  /*
  *Etiqueta para el nombre del programa de supervisión
  */
 @Transient
  private Long descNuAnio;

  /*
  *Costo del monto del programa
  */
 @Transient
  private BigDecimal descMoCosto;

  /*
  *Descripción de la especialidad
  */
 @Transient
  private String descDeEspecialidad;

  /*
  *Nombre de la división supervisora
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *Nombre del paso
  */
 @Transient
  private String descPaso;

  /*
  *Identificador interno de la relación del paso
  */
 @Transient
  private Long descIdRelacionPaso;

  /*
  *Identificador interno del personal del Osinergmin
  */
 @Transient
  private Long descIdPersonalOsi;

  /*
  *Mensaje de la asignación de la instancia del programa
  */
 @Transient
  private String descDeMensaje;

  /*
  *DESC_ID_LINEA_PROGRAMA
  */
 @Transient
  private Long descIdLineaPrograma;

  /*
  *DESC_ESTADO
  */
 @Transient
  private String descEstado;


}