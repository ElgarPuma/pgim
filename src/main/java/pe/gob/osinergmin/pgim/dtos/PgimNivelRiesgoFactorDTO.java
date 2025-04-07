package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_NIVEL_RIESGO_FACTOR: 
* @descripción: Nivel de riesgo por factor
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 16/02/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimNivelRiesgoFactorDTO {

  /*
  *Identificador interno del nivel de riesgo por factor. Secuencia: PGIM_SEQ_NIVEL_RIESGO_FACTOR
  */
  private Long idNivelRiesgoFactor;

  /*
  *Identificador interno del factor de riesgo. Tabla padre: PGIM_TM_FACTOR_RIESGO
  */
  private Long idFactorRiesgo;

  /*
  *Identificador interno del nivel de riesgo. Tabla padre: PGIM_TM_NIVEL_RIESGO
  */
  private Long idNivelRiesgo;

  /*
  *Especificación del nivel de riesgo del factor
  */
  private String deEspecificacion;

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