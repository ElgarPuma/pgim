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
* Clase Entidad para la tabla PGIM_TC_LINEA_PROGRAMA: 
* @descripción: Línea base del programa
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_LINEA_PROGRAMA")
@Data
@NoArgsConstructor
public class PgimLineaPrograma implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la línea base del programa. Secuencia: PGIM_SEQ_LINEA_PROGRAMA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_LINEA_PROGRAMA")
   @SequenceGenerator(name = "PGIM_SEQ_LINEA_PROGRAMA", sequenceName = "PGIM_SEQ_LINEA_PROGRAMA", allocationSize = 1)
   @Column(name = "ID_LINEA_PROGRAMA", nullable = false)
  private Long idLineaPrograma;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROGRAMA_SUPERVISION", nullable = false)
  private PgimPrgrmSupervision pgimPrgrmSupervision;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = LINEA_PROGRAMA_ESTADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LINEA_PROGRAMA_ESTADO", nullable = false)
  private PgimValorParametro lineaProgramaEstado;

  /*
  *Fecha de la línea base del programa
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_LINEA_PROGRAMA", nullable = false)
  private Date feLineaPrograma;

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