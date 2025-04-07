package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_CFG_MATRIZ_PARES: 
* @descripción: Configuración de la matriz de pares de riesgos
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCfgMatrizParesDTO {

  /*
  *Identificador interno del ítem de la matriz de pares. Secuencia: PGIM_SEQ_CFG_MATRIZ_PARES
  */
  private Long idCfgMatrizPares;

  /*
  *Identificador interno del factor de riesgo de la fila de la matriz. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idCfgFactorRiesgoFila;

  /*
  *Identificador interno del factor de riesgo de la columna de la matriz. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idCfgFactorRiesgoColu;

  /*
  *Indicador si el dato es editable. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flEsEditable;

  /*
  *Valor del numerador del ítem de la matriz
  */
  private BigDecimal nuNumerador;

  /*
  *Valor del denominador del ítem de la matriz
  */
  private BigDecimal nuDenominador;

  /*
  *Número de la MCP (Matriz de comparación de pares). Valor obtenido a través de la división entre el NU_NUMERADOR y NU_DENOMINADOR
  */
  private BigDecimal nuMcp;

  /*
  *Número de la MCPN (Matriz de comparación de pares normalizada). Valor obtenido a través de la división de UN_MCP / Sumar(PGIM_TM_CFG_FACTOR_RIESGO.NU_SUMATORIA_COLUMNA_MCP)
  */
  private BigDecimal nuMcpn;

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
  *Nombre del factor de riesgo
  */
  private String descNoFactorFila;

  /*
  *Orden de prioridad del factor
  */
  private Long descNuOrdenPrioridadFila;

  /*
  *Nombre del factor de riesgo
  */
  private String descNoFactorColu;

  /*
  *Orden de prioridad del factor
  */
  private Long descNuOrdenPrioridadColu;

  /*
  *Promedio de las ponderaciones PGIM_TM_CFG_MATRIZ_PARES.NU_MCPN
  */
  private BigDecimal descNuVectorResultante;

  /*
  *DESC_ID_CONFIGURA_RIESGO
  */
  private Long descIdConfiguraRiesgo;

  /*
  *DESC_ID_GRUPO_RIESGO
  */
  private Long descIdGrupoRiesgo;

  /*
  *DESC_ID_CFG_GRUPO_RIESGO
  */
  private Long descIdCfgGrupoRiesgo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}