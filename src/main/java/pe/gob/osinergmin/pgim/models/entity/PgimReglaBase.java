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
* Clase Entidad para la tabla PGIM_TM_REGLA_BASE: 
* @descripción: Reglas de configuración para datos base en la fiscalización
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_REGLA_BASE")
@Data
@NoArgsConstructor
public class PgimReglaBase implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la regla base para los datos en el marco de la fiscalización. Secuencia: PGIM_SEQ_REGLA_BASE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_REGLA_BASE")
   @SequenceGenerator(name = "PGIM_SEQ_REGLA_BASE", sequenceName = "PGIM_SEQ_REGLA_BASE", allocationSize = 1)
   @Column(name = "ID_REGLA_BASE", nullable = false)
  private Long idReglaBase;

  /*
  *Identificador interno de la configuración base de documento. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURACION_BASE", nullable = false)
  private PgimConfiguracionBase pgimConfiguracionBase;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Identificador interno del motivo de la fiscalización. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private PgimMotivoSupervision pgimMotivoSupervision;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Tipo de minado de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = METODO_MINADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_METODO_MINADO", nullable = true)
  private PgimValorParametro metodoMinado;

  /*
  *Flag que indica si la fiscalización es propia, es decir, se la realizó el personal de Osinergmin. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_PROPIA", nullable = true)
  private String flPropia;

  /*
  *Indicador lógico que señala si el registro de la UM tiene componentes de tipo Piques. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_CON_PIQUES", nullable = true)
  private String flConPiques;

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
  *DESC_ID_TIPO_CONFIGURACION_BASE
  */
 @Transient
  private Long descIdTipoConfiguracionBase;

  /*
  *DESC_NO_CONFIGURACION_BASE
  */
 @Transient
  private String descNoConfiguracionBase;

  /*
  *DESC_NO_TIPO_CONFIGURACION_BASE
  */
 @Transient
  private String descNoTipoConfiguracionBase;

  /*
  *DESC_ID_ESPECIALIDAD
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *DESC_NO_ESPECIALIDAD
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *DESC_NO_DIVISION_SUPERVISORA
  */
 @Transient
  private String descNoDivisionSupervisora;

  /*
  *DESC_ID_TIPO_SUPERVISION
  */
 @Transient
  private Long descIdTipoSupervision;

  /*
  *DESC_NO_TIPO_SUPERVISION
  */
 @Transient
  private String descNoTipoSupervision;

  /*
  *DESC_NO_MOTIVO_SUPERVISION
  */
 @Transient
  private String descNoMotivoSupervision;

  /*
  *DESC_NO_SUBTIPO_SUPERVISION
  */
 @Transient
  private String descNoSubtipoSupervision;

  /*
  *DESC_NO_METODO_MINADO
  */
 @Transient
  private String descNoMetodoMinado;


}