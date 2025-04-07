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
* Clase Entidad para la tabla PGIM_TD_ESTANDAR_PROGRAMA: 
* @descripción: Datos estándares del programa
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_ESTANDAR_PROGRAMA")
@Data
@NoArgsConstructor
public class PgimEstandarPrograma implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del dato estándar del programa. Secuencia: PGIM_SEQ_ESTANDAR_PROGRAMA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ESTANDAR_PROGRAMA")
   @SequenceGenerator(name = "PGIM_SEQ_ESTANDAR_PROGRAMA", sequenceName = "PGIM_SEQ_ESTANDAR_PROGRAMA", allocationSize = 1)
   @Column(name = "ID_ESTANDAR_PROGRAMA", nullable = false)
  private Long idEstandarPrograma;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private PgimSubtipoSupervision pgimSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private PgimMotivoSupervision pgimMotivoSupervision;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LINEA_PROGRAMA", nullable = true)
  private PgimLineaPrograma pgimLineaPrograma;

  /*
  *Identificador interno del dato estándar del programa base desde donde se copiaron los datos para la creación de este registro. Tabla padre PGIM_TD_ESTANDAR_PROGRAMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESTANDAR_PROGRAMA_BASE", nullable = true)
  private PgimEstandarPrograma estandarProgramaBase;

  /*
  *Monto por supervisión S/
  */
   @Column(name = "MO_POR_SUPERVISION", nullable = true)
  private BigDecimal moPorSupervision;

  /*
  *Cantidad de días de supervisión
  */
   @Column(name = "CA_DIAS_SUPERVISION", nullable = true)
  private Integer caDiasSupervision;

  /*
  *Cantidad de supervisiones. Solo aplica para algunos tipos de no programadas, para las demás se dejará en NULL
  */
   @Column(name = "CA_SUPERVISIONES", nullable = true)
  private Integer caSupervisiones;

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
  *Descripción del tipo de supervisión
  */
 @Transient
  private String descTipoSupervision;

  /*
  *Descripción del subtipo de supervisión y el motivo
  */
 @Transient
  private String descSubtipoMotivo;

  /*
  *Costo total de las supervisiones
  */
 @Transient
  private BigDecimal descCoTotalSupervisiones;


}