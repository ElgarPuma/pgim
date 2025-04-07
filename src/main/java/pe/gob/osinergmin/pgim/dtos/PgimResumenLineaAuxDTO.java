package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_RESUMEN_LINEA_AUX: 
* @descripción: Vista para obtener la lista resumida de la programación de las supervisiones de una línea base dada
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimResumenLineaAuxDTO {

  /*
  *Identificador auxiliar de la línea base del programa. Secuencia: PGIM_SEQ_RESUMENLB_AUX
  */
  private Long idLineaProgramaAux;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
  private Long idLineaPrograma;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Código de la unidad minera
  */
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String noUnidadMinera;

  /*
  *Cantidad de unidades mineras programadas para el mes de enero
  */
  private Long enero;

  /*
  *Cantidad de unidades mineras programadas para el mes de febrero
  */
  private Long febrero;

  /*
  *Cantidad de unidades mineras programadas para el mes de marzo
  */
  private Long marzo;

  /*
  *Cantidad de unidades mineras programadas para el mes de abril
  */
  private Long abril;

  /*
  *Cantidad de unidades mineras programadas para el mes de mayo
  */
  private Long mayo;

  /*
  *Cantidad de unidades mineras programadas para el mes de junio
  */
  private Long junio;

  /*
  *Cantidad de unidades mineras programadas para el mes de julio
  */
  private Long julio;

  /*
  *Cantidad de unidades mineras programadas para el mes de agosto
  */
  private Long agosto;

  /*
  *Cantidad de unidades mineras programadas para el mes de septiembre
  */
  private Long septiembre;

  /*
  *Cantidad de unidades mineras programadas para el mes de octubre
  */
  private Long octubre;

  /*
  *Cantidad de unidades mineras programadas para el mes de noviembre
  */
  private Long noviembre;

  /*
  *Cantidad de unidades mineras programadas para el mes de diciembre
  */
  private Long diciembre;

  /*
  *Cantidad de unidades mineras programadas para el mes de enero de otras líneas base de programas en el mismo plan
  */
  private Long eneroOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de febrero de otras líneas base de programas en el mismo plan
  */
  private Long febreroOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de marzo de otras líneas base de programas en el mismo plan
  */
  private Long marzoOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de abril de otras líneas base de programas en el mismo plan
  */
  private Long abrilOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de mayo de otras líneas base de programas en el mismo plan
  */
  private Long mayoOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de junio de otras líneas base de programas en el mismo plan
  */
  private Long junioOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de julio de otras líneas base de programas en el mismo plan
  */
  private Long julioOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de agosto de otras líneas base de programas en el mismo plan
  */
  private Long agostoOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de septiembre de otras líneas base de programas en el mismo plan
  */
  private Long septiembreOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de octubre de otras líneas base de programas en el mismo plan
  */
  private Long octubreOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de noviembre de otras líneas base de programas en el mismo plan
  */
  private Long noviembreOtros;

  /*
  *Cantidad de unidades mineras programadas para el mes de diciembre de otras líneas base de programas en el mismo plan
  */
  private Long diciembreOtros;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}