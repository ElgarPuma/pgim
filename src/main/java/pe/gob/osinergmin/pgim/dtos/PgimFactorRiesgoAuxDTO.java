package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_FACTOR_RIESGO_AUX: 
* @descripción: Vista del factor riesgo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimFactorRiesgoAuxDTO {

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_FACRIES_AUX
  */
  private Long idFactorRiesgoAux;

  /*
  *ID_GRUPO_RIESGO
  */
  private Long idGrupoRiesgo;

  /*
  *NO_GRUPO
  */
  private String noGrupo;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *ID_TIPO_ORIGEN_DATO_RIESGO
  */
  private Long idTipoOrigenDatoRiesgo;

  /*
  *NO_VALOR_PARAMETRO
  */
  private String noValorParametro;

  /*
  *NO_FACTOR
  */
  private String noFactor;

  /*
  *DE_FACTOR
  */
  private String deFactor;

  /*
  *ES_REGISTRO
  */
  private String esRegistro;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}