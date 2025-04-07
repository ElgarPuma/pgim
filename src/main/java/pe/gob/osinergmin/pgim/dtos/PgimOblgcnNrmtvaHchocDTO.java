package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_OBLGCN_NRMTVA_HCHOC: 
* @descripción: Obligación normativa por hecho constatado de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimOblgcnNrmtvaHchocDTO {

  /*
  *Identificador interno de la obligación normativa por hecho constatado de la supervisión. Secuencia: PGIM_SEQ_OBLGCN_NRMTVA_HCHOC
  */
  private Long idOblgcnNrmtvaHchoc;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla padre: PGIM_TC_HECHO_CONSTATADO
  */
  private Long idHechoConstatado;

  /*
  *Identificador interno de la obligación normativa por criterio de la matriz de supervisión.. Tabla padre: PGIM_TM_OBLGCN_NRMA_CRTRIO
  */
  private Long idOblgcnNrmaCrtrio;

  /*
  *Aplica para la obligación normativa. Los posibles valores son: "1" = Aplica y "0" = No aplica
  */
  private String esAplica;

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
  
  /**
   * Obligación normativa del criterio maestro relacionado a la obligación fiscalizada
   */
  private PgimOblgcnNrmaCrtrioDTO pgimOblgcnNrmaCrtrioDTO;


}