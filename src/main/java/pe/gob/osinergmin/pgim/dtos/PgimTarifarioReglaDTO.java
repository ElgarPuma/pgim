package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TD_TARIFARIO_REGLA: 
* @descripción: Regla de aplicación del tarifario del contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTarifarioReglaDTO {

  /*
  *Identificador interno del tarifario del contrato. Secuencia: PGIM_SEQ_TARIFARIO_REGLA
  */
  private Long idTarifarioRegla;

  /*
  *Identificador interno del tarifario del contrato. Tabla padre: PGIM_TD_TARIFARIO_CONTRATO
  */
  private Long idTarifarioContrato;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;

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
  *Listado de subtipos de supervisión
  */
  private List<PgimSubtipoSupervisionDTO> descLpgimSubtipoSupervision;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}