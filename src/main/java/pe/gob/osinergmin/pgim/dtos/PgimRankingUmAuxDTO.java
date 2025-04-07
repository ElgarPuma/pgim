package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_VW_RANKING_UM_AUX: 
* @descripción: Vista unidadades mineras calificadas por factores técnicos, gestión y el respectivo puntaje total
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRankingUmAuxDTO {

  /*
  *Identificador interno de la liquidación AUX. Secuencia: PGIM_SEQ_ENTRE_LIQ_AUX
  */
  private Long idRankingUmAux;

  /*
  *ID_RANKING_UM
  */
  private Long idRankingUm;

  /*
  *Identificador interno del ranking de riesgo. Secuencia: PGIM_SEQ_RANKING_RIESGO
  */
  private Long idRankingRiesgo;

  /*
  *ID_UNIDAD_MINERA
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
  *CO_ANONIMIZACION
  */
  private String coAnonimizacion;

  /*
  *PUNTAJE_TECNICO
  */
  private BigDecimal puntajeTecnico;

  /*
  *PUNTAJE_GESTION
  */
  private BigDecimal puntajeGestion;

  /*
  *PUNTAJE_GENERAL
  */
  private BigDecimal puntajeGeneral;

  /*
  *NRO_TECNICO_PENDIENTE
  */
  private Integer nroTecnicoPendiente;

  /*
  *NRO_GESTION_PENDIENTE
  */
  private Integer nroGestionPendiente;

  /*
  *CO_TIPO_INCLUSION_RANKING
  */
  private String coTipoInclusionRanking;

  /*
  *NO_TIPO_INCLUSION_RANKING
  */
  private String noTipoInclusionRanking;

  /*
  *NU_CALIFICACION_TECNICO
  */
  private BigDecimal nuCalificacionTecnico;

  /*
  *NU_CALIFICACION_GESTION
  */
  private BigDecimal nuCalificacionGestion;
  
  /*
  *Indica si la unidad minera está activa o no en el ranking. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String flActivo;

  /*
  *DESC_DATOS_COMPLETOS
  */
  private String descDatosCompletos;

  /*
  *Código del nivel de riesgo
  */
  private String descCoNivelRiesgo;

  /*
  *Valor del nivel inferior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
  private BigDecimal descNuValorInferior;

  /*
  *Valor del nivel superior, números entre 0 y 100 en la calificación total del ranking de una UM
  */
  private BigDecimal descNuValorSuperior;

  /*
  *Código del color en hexadecimal del nivel de riesgo
  */
  private String descCoColorHexadecimal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}