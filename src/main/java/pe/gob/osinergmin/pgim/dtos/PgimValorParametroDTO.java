package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TP_VALOR_PARAMETRO: 
* @descripción: Valor del parámetro de la aplicación
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimValorParametroDTO {

  /*
  *Identificador del valor del parámetro. Secuencia: PGIM_SEQ_VALOR_PARAMETRO
  */
  private Long idValorParametro;

  /*
  *Identificador del parámetro. Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idParametro;

  /*
  *Clave en texto del ítem del parámetro. Este valor sirve para mapear los códigos de negocio empleados por ejemplo para las listas.
  */
  private String coClaveTexto;

  /*
  *Clave del ítem del parámetro. Este valor sirve para mapear los códigos de negocio empleados por ejemplo para las listas.
  */
  private Long coClave;

  /*
  *Nombre del valor parámetro
  */
  private String noValorParametro;

  /*
  *Descripción del valor parámetro
  */
  private String deValorParametro;

  /*
  *Número de orden en la presentación
  */
  private BigDecimal nuOrden;

  /*
  *Valor alfanumérico del Código
  */
  private String deValorAlfanum;

  /*
  *Valor numérico del código
  */
  private BigDecimal nuValorNumerico;

  /*
  *Indicador lógico que determina si el valor del parámetro del Osinergmin se encuentra activo o no
  */
  private String flActivo;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}