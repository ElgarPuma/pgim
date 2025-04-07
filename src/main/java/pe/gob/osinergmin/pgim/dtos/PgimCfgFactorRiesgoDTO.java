package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_CFG_FACTOR_RIESGO: 
* @descripción: Configuración del factor de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCfgFactorRiesgoDTO {

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_CFG_FACTOR_RIESGO
  */
  private Long idCfgFactorRiesgo;

  /*
  *Identificador interno del del grupo de riesgo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
  private Long idCfgGrupoRiesgo;

  /*
  *Identificador interno del del grupo de riesgo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
  private Long idFactorRiesgo;

  /*
  *Orden de prioridad del factor
  */
  private Long nuOrdenPrioridad;

  /*
  *Indicador si el factor es afectado o no por la gestión de la empresa. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flAfectadoGestion;

  /*
  *Sumatoria de PGIM_TM_CFG_MATRIZ_PARES.NU_MCP cuando PGIM_TM_CFG_MATRIZ_PARES.ID_CFG_FACTOR_RIESGO_COLU = PGIM_TM_CFG_FACTOR_RIESGO.ID_CFG_FACTOR_RIESGO.
  */
  private BigDecimal nuSumatoriaColumnaMcp;

  /*
  *Promedio de las ponderaciones PGIM_TM_CFG_MATRIZ_PARES.NU_MCPN
  */
  private BigDecimal nuVectorResultante;

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
  private String descNoFactor;

  /*
  *Nombre del valor de parámetro
  */
  private String descNoValorParametro;

  /*
  *Id utilizado para poblar la matriz de pares
  */
  private Long descIdCfgFactorRiesgoTemp;

  /*
  *DESC_DE_FACTOR
  */
  private String descDeFactor;

  /*
  *DESC_ID_VALOR_PARAMETRO
  */
  private Long descIdValorParametro;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}