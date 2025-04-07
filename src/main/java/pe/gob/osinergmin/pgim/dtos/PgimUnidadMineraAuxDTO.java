package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_UNIDAD_MINERA_AUX: 
* @descripción: Vista para obtener la lista de las unidades mineras
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimUnidadMineraAuxDTO {

  /*
  *Identificador interno de la unidad minera auxiliar. Secuencia: PGIM_SEQ_UMINERA_AUX
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
  private String noRazonSocial;

  /*
  *Identificador interno del tipo de actividad
  */
  private Long idTipoActividad;

  /*
  *NO_TIPO_ACTIVIDAD
  */
  private String noTipoActividad;

  /*
  *Identificador interno de la subactividad
  */
  private Long idSubactividad;

  /*
  *Nombre de la subactividad de la UM
  */
  private String noSubactividad;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISON_SUPERVISORA
  */
  private String noDivisonSupervisora;

  /*
  *ID_SITUACION
  */
  private Long idSituacion;

  /*
  *NO_TIPO_SITUACION
  */
  private String noTipoSituacion;

  /*
  *ID_TIPO_UNIDAD_MINERA
  */
  private Long idTipoUnidadMinera;

  /*
  *NO_TIPO_UNIDAD_MINERA
  */
  private String noTipoUnidadMinera;

  /*
  *Identificador interno del método de minado
  */
  private Long idMetodoMinado;

  /*
  *Nombre del método de minado de la unidad minera
  */
  private String noMetodoMinado;

  /*
  *Identificador interno del mñetodo de la explotación
  */
  private Long idMetodoExplotacion;

  /*
  *Nombre del método de explotación
  */
  private String noMetodoExplotacion;

  /*
  *Identificador interno del tipo de yacimiento
  */
  private Long idTipoYacimiento;

  /*
  *Nombre del tipo de yacimiento
  */
  private String noTipoYacimiento;

  /*
  *Identificador interno de la UM
  */
  private Long idEstadoUm;

  /*
  *Nombre del estado de la UM
  */
  private String noEstadoUm;

  /*
  *NO_UBIGEO
  */
  private String noUbigeo;

  /*
  *Capacidad instalada de planta expresada en TM/d. Aplica solo cuando el <<Tipo de Unidad Minera>> sea igual a <<Concesión de beneficio>>
  */
  private BigDecimal nuCpcdadInstldaPlanta;

  /*
  *ID_TIPO_UNIDAD_MINERA
  */
  private Long idRankingRiesgo;

  /*
  *Valor obtenido de la sumatoria de los puntajes técnicos y de gestión
  */
  private BigDecimal moPuntaje;

  /*
  *Nombre del ranking de riesgo
  */
  private String noRanking;

  /*
  *Fecha de generación del ranking
  */
  private Date feGeneracionRanking;

  /*
  *Fecha de generación del ranking
  */
  private String feGeneracionRankingDesc;
  
  /*
  * Id de la instancia de paso del ranking de riesgo
  */
  private Long idInstanciaPasoRanking;     

  /*
  *Código de planta de beneficio destino
  */
  private String coPlantaBeneficioDestino;

  /*
  *Nombre de planta de beneficio destino
  */
  private String noPlantaBeneficioDestino;

  /*
  *Indicio de cámara subterránea
  */
  private String indicioCamaraSubterranea;

  /*
  *Profundidad expresada en metros (m)
  */
  private BigDecimal nuProfundidad;

  /*
  *Número de altura mínima
  */
  private BigDecimal nuAlturaMinima;

  /*
  *Número de altura máxima
  */
  private BigDecimal nuAlturaMaxima;

  /*
  *Nombre de minerales o sustancias
  */
  private String mineralesSustancias;

  /*
  *Requiere datos de riesgos
  */
  private String requiereDatosRiesgo;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
  private Long descIdLineaPrograma;

  /*
  *DESC_MO_PUNTAJE
  */
  private String descMoPuntaje;

  /*
  *DESC_ID_RANKING_RIESGO
  */
  private Long descIdRankingRiesgo;

  /*
  *DESC_CO_CLAVE_TIPO_CONFIG_RANKING_RIESGO
  */
  private String descCoTipoConfiguracionRiesgo;

  /*
  *DESC_FL_CUMPLE_REGLA: Si cumple = 1, No cumple = 0
  */
  private String descFlCumpleRegla;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}