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
* Clase Entidad para la tabla PGIM_TD_MEDICION: 
* @descripción: Medición de un indicador
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 23/10/2023
*/
@Entity
@Table(name = "PGIM_TD_MEDICION")
@Data
@NoArgsConstructor
public class PgimMedicion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la medición. Secuencia: PGIM_SEQ_MEDICION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MEDICION")
   @SequenceGenerator(name = "PGIM_SEQ_MEDICION", sequenceName = "PGIM_SEQ_MEDICION", allocationSize = 1)
   @Column(name = "ID_MEDICION", nullable = false)
  private Long idMedicion;

  /*
  *Identificador interno del indicador sobre el que se realizará una medición. Tabla padre: PGIM_TC_INDICADOR
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INDICADOR", nullable = false)
  private PgimIndicador pgimIndicador;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = true)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private PgimValorParametro divisionSupervisora;

  /*
  *Código de la medición
  */
   @Column(name = "CO_MEDICION", nullable = false)
  private String coMedicion;

  /*
  *Nombre de la medición
  */
   @Column(name = "NO_MEDICION", nullable = false)
  private String noMedicion;

  /*
  *Descripción del indicador
  */
   @Column(name = "DE_MEDICION", nullable = false)
  private String deMedicion;

  /*
  *Estado de publicación de la medición, los valores posibles son: "0" = No publicado | "1" = Publicado y su condición de registro es obligatoria. Por defecto se colocará el valor "0"
  */
   @Column(name = "ES_PUBLICACION", nullable = false)
  private String esPublicacion;

  /*
  *Fecha inicial para el filtro de los datos. Las restricciones del horizonte temporal se aplicarán sobre la fecha de realización de la tarea final más reciente en cada instancia del flujo de trabajo asociado al indicador.
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIAL", nullable = false)
  private Date feInicial;

  /*
  *Fecha final para el filtro de los datos. Las restricciones del horizonte temporal se aplicarán sobre la fecha de realización de la tarea final más reciente en cada instancia del flujo de trabajo asociado al indicador.
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FINAL", nullable = false)
  private Date feFinal;

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


}