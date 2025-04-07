package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_NIVEL_RIESGO: 
* @descripción: Nivel de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNivelRiesgoDTO {

  /*
  *Identificador interno del nivel de riesgo. Secuencia: PGIM_SEQ_NIVEL_RIESGO
  */
  private Long idNivelRiesgo;

  /*
  *Tipo de operador lógico. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OPERADOR_LOGICO. Finalmente, el valor persistido en esta columna es el valor obtenido de <PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idOperadorValorInferior;

  /*
  *Tipo de operador lógico. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OPERADOR_LOGICO. Finalmente, el valor persistido en esta columna es el valor obtenido de <PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idOperadorValorSuperior;

  /*
  *Código del nivel de riesgo
  */
  private String coNivelRiesgo;

  /*
  *Nombre del factor de riesgo
  */
  private String noNivelRiesgo;

  /*
  *Descripción del factor de riesgo
  */
  private String deNivelRiesgo;

  /*
  *Orden del nivel
  */
  private Long nuOrden;

  /*
  *Valor del nivel, números entre 0 y 1.
  */
  private BigDecimal nuValor;

  /*
  *Valor del nivel inferior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
  private BigDecimal nuValorInferior;

  /*
  *Valor del nivel superior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
  private BigDecimal nuValorSuperior;

  /*
  *Código del color en hexadecimal del nivel de riesgo
  */
  private String coColorHexadecimal;

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