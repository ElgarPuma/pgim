package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_MATRIZ_PARES: 
* @descripción: Matriz de pares
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 16/02/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMatrizParesDTO {

  /*
  *Identificador interno del ítem de la matriz de pares. Secuencia: PGIM_SEQ_MATRIZ_PARES
  */
  private Long idMatrizPares;

  /*
  *Identificador interno del factor de riesgo de la fila de la matriz. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idFactorRiesgoFila;

  /*
  *Identificador interno del factor de riesgo de la columna de la matriz. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idFactorRiesgoColumna;

  /*
  *Valor del numerador del ítem de la matriz
  */
  private BigDecimal nuNumerador;

  /*
  *Valor del denominador del ítem de la matriz
  */
  private BigDecimal nuDenominador;

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