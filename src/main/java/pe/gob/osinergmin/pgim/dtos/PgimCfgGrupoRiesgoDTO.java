package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_CFG_GRUPO_RIESGO: 
* @descripción: Configuración del grupo de riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCfgGrupoRiesgoDTO {

  /*
  *Identificador interno del del grupo de riesgo. Secuencia: PGIM_SEQ_CFG_GRUPO_RIESGO
  */
  private Long idCfgGrupoRiesgo;

  /*
  *Identificador interno de la configuración del riesgo. Tabla padre: PGIM_TM_CONFIGURA_RIESGO
  */
  private Long idConfiguraRiesgo;

  /*
  *Identificador interno de la configuración del grupo. Tabla padre: PGIM_TM_GRUPO_RIESGO
  */
  private Long idGrupoRiesgo;

  /*
  *Nombre del grupo de riesgo
  */
  private String noGrupoRiesgo;

  /*
  *Decripción del grupo de riesgo
  */
  private String deGrupoRiesgo;

  /*
  *Factor de correción aplicado al valor de riesgo obtenido para el grupo de riesgo
  */
  private BigDecimal pcFactorCorreccion;

  /*
  *Valor alfa para el grupo técnico, valor beta (1 - alfa) para el grupo de gestión. Para calcular alfa, se debe contar la cantidad de factores técnicos de la configuración marcados con PGIM_TM_CFG_FACTOR_RIESGO.FL_AFECTADO_GESTION = "1" entre el total de factores técnicos.
  */
  private BigDecimal nuCalificacionGrupo;

  /*
  *Resultado de la multiplicación matricial de la fila de la Matriz de Comparación de Pares (MCP) con la columna “Vector Resultante” de la Matriz de Comparación de Pares Normalizada (MCPN
  */
  private BigDecimal nuNmax;

  /*
  *Índice de consistencia (IC)
  */
  private BigDecimal nuIndiceConsistencia;

  /*
  *Ratio de consistencia de la configuración (RC)
  */
  private BigDecimal nuRatioConsistencia;

  /*
  *Índice de consistencia aleatoria (IA)
  */
  private BigDecimal nuIndConsistenciaAleatoria;

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
  *Id utilizado para poblar la matriz de pares
  */
  private Long descIdCfgGrupoRiesgoTemp;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}