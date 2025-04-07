package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_RESUMEN_LINEA_AUX: 
* @descripción: Vista para obtener la lista resumida de la programación de las supervisiones de una línea base dada
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_RESUMEN_LINEA_AUX")
@Data
@NoArgsConstructor
public class PgimResumenLineaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador auxiliar de la línea base del programa. Secuencia: PGIM_SEQ_RESUMENLB_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RESUMENLB_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_RESUMENLB_AUX", sequenceName = "PGIM_SEQ_RESUMENLB_AUX", allocationSize = 1)
   @Column(name = "ID_LINEA_PROGRAMA_AUX", nullable = false)
  private Long idLineaProgramaAux;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_LINEA_PROGRAMA", nullable = true)
  private PgimLineaPrograma pgimLineaPrograma;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = true)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Código de la unidad minera
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *Cantidad de unidades mineras programadas para el mes de enero
  */
   @Column(name = "ENERO", nullable = true)
  private Long enero;

  /*
  *Cantidad de unidades mineras programadas para el mes de febrero
  */
   @Column(name = "FEBRERO", nullable = true)
  private Long febrero;

  /*
  *Cantidad de unidades mineras programadas para el mes de marzo
  */
   @Column(name = "MARZO", nullable = true)
  private Long marzo;

  /*
  *Cantidad de unidades mineras programadas para el mes de abril
  */
   @Column(name = "ABRIL", nullable = true)
  private Long abril;

  /*
  *Cantidad de unidades mineras programadas para el mes de mayo
  */
   @Column(name = "MAYO", nullable = true)
  private Long mayo;

  /*
  *Cantidad de unidades mineras programadas para el mes de junio
  */
   @Column(name = "JUNIO", nullable = true)
  private Long junio;

  /*
  *Cantidad de unidades mineras programadas para el mes de julio
  */
   @Column(name = "JULIO", nullable = true)
  private Long julio;

  /*
  *Cantidad de unidades mineras programadas para el mes de agosto
  */
   @Column(name = "AGOSTO", nullable = true)
  private Long agosto;

  /*
  *Cantidad de unidades mineras programadas para el mes de septiembre
  */
   @Column(name = "SEPTIEMBRE", nullable = true)
  private Long septiembre;

  /*
  *Cantidad de unidades mineras programadas para el mes de octubre
  */
   @Column(name = "OCTUBRE", nullable = true)
  private Long octubre;

  /*
  *Cantidad de unidades mineras programadas para el mes de noviembre
  */
   @Column(name = "NOVIEMBRE", nullable = true)
  private Long noviembre;

  /*
  *Cantidad de unidades mineras programadas para el mes de diciembre
  */
   @Column(name = "DICIEMBRE", nullable = true)
  private Long diciembre;

  /*
  *Cantidad de unidades mineras programadas para el mes de enero de otras líneas base de programas en el mismo plan
  */
   @Column(name = "ENERO_OTROS", nullable = true)
  private Long eneroOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de febrero de otras líneas base de programas en el mismo plan
  */
   @Column(name = "FEBRERO_OTROS", nullable = true)
  private Long febreroOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de marzo de otras líneas base de programas en el mismo plan
  */
   @Column(name = "MARZO_OTROS", nullable = true)
  private Long marzoOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de abril de otras líneas base de programas en el mismo plan
  */
   @Column(name = "ABRIL_OTROS", nullable = true)
  private Long abrilOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de mayo de otras líneas base de programas en el mismo plan
  */
   @Column(name = "MAYO_OTROS", nullable = true)
  private Long mayoOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de junio de otras líneas base de programas en el mismo plan
  */
   @Column(name = "JUNIO_OTROS", nullable = true)
  private Long junioOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de julio de otras líneas base de programas en el mismo plan
  */
   @Column(name = "JULIO_OTROS", nullable = true)
  private Long julioOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de agosto de otras líneas base de programas en el mismo plan
  */
   @Column(name = "AGOSTO_OTROS", nullable = true)
  private Long agostoOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de septiembre de otras líneas base de programas en el mismo plan
  */
   @Column(name = "SEPTIEMBRE_OTROS", nullable = true)
  private Long septiembreOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de octubre de otras líneas base de programas en el mismo plan
  */
   @Column(name = "OCTUBRE_OTROS", nullable = true)
  private Long octubreOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de noviembre de otras líneas base de programas en el mismo plan
  */
   @Column(name = "NOVIEMBRE_OTROS", nullable = true)
  private Long noviembreOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de diciembre de otras líneas base de programas en el mismo plan
  */
   @Column(name = "DICIEMBRE_OTROS", nullable = true)
  private Long diciembreOtros;


}