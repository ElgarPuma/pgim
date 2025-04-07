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
* Clase Entidad para la tabla PGIM_TC_GEN_PROGRAMA: 
* @descripción: Generación de programa de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 27/12/2023
*/
@Entity
@Table(name = "PGIM_TC_GEN_PROGRAMA")
@Data
@NoArgsConstructor
public class PgimGenPrograma implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la propuesta de programa. Secuencia: PGIM_SEQ_GEN_PROGRAMA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_GEN_PROGRAMA")
   @SequenceGenerator(name = "PGIM_SEQ_GEN_PROGRAMA", sequenceName = "PGIM_SEQ_GEN_PROGRAMA", allocationSize = 1)
   @Column(name = "ID_GEN_PROGRAMA", nullable = false)
  private Long idGenPrograma;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROGRAMA_SUPERVISION", nullable = false)
  private PgimPrgrmSupervision pgimPrgrmSupervision;

  /*
  *Estado de generación conforme. Los posibles valores son: "1" = Conforme y "0" = No conforme
  */
   @Column(name = "FL_CONFORME", nullable = false)
  private String flConforme;

  /*
  *Fecha de la generación de la propuesto del programa
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_GENERACION", nullable = false)
  private Date feGeneracion;

  /*
  *Cantidad anual máxima de fiscalizaciones por unidad fiscalizable
  */
   @Column(name = "NU_MAX_FISCA_ANUAL_XUF", nullable = false)
  private Integer nuMaxFiscaAnualXuf;

  /*
  *Cantidad mensual máxima de fiscalizaciones
  */
   @Column(name = "NU_MAX_FISCA_MENSUAL", nullable = false)
  private Integer nuMaxFiscaMensual;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de enero
  */
   @Column(name = "NU_ENERO", nullable = true)
  private Integer nuEnero;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de febrero
  */
   @Column(name = "NU_FEBRERO", nullable = true)
  private Integer nuFebrero;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de marzo
  */
   @Column(name = "NU_MARZO", nullable = true)
  private Integer nuMarzo;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de abril
  */
   @Column(name = "NU_ABRIL", nullable = true)
  private Integer nuAbril;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de mayo
  */
   @Column(name = "NU_MAYO", nullable = true)
  private Integer nuMayo;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de junio
  */
   @Column(name = "NU_JUNO", nullable = true)
  private Integer nuJuno;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de julio
  */
   @Column(name = "NU_JULIO", nullable = true)
  private Integer nuJulio;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de agosto
  */
   @Column(name = "NU_AGOSTO", nullable = true)
  private Integer nuAgosto;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de septiembre
  */
   @Column(name = "NU_SEPTIEMBRE", nullable = true)
  private Integer nuSeptiembre;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de octubre
  */
   @Column(name = "NU_OCTUBRE", nullable = true)
  private Integer nuOctubre;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de noviembre
  */
   @Column(name = "NU_NOVIEMBRE", nullable = true)
  private Integer nuNoviembre;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de diciembre
  */
   @Column(name = "NU_DICIEMBRE", nullable = true)
  private Integer nuDiciembre;

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