package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * DTO para la entidad PGIM_TC_CMINERO_SPRVSION:
 * 
 * @descripción: Supervisión de componentes mineros
 *
 * @author:
 * @version: 1.0
 * @fecha_de_creación: 17/12/2024
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimCmineroSprvsionDTO {

  /*
   * Identificador interno del componente minero inspeccionado. Secuencia:
   * PGIM_SEQ_CMINERO_SPRVSION
   */
  private Long idCmineroSprvsion;

  /*
   * Identificador interno de la supervisión a la que está asociado el componente
   * minero: Tabla padre: PGIM_TC_SUPERVISION
   */
  private Long idSupervision;

  /*
   * Identificador interno de la supervisión a la que está asociado el componente
   * minero: Tabla padre: PGIM_TM_COMPONENTE_MINERO
   */
  private Long idComponenteMinero;

  /*
   * Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
   */
  private String esRegistro;

  /*
   * Usuario creador
   */
  private String usCreacion;

  /*
   * Terminal de creación
   */
  private String ipCreacion;

  /*
   * Fecha y hora de creación
   */
  private Date feCreacion;

  /*
   * Fecha y hora de creación
   */
  private String feCreacionDesc;

  /*
   * Usuario modificador
   */
  private String usActualizacion;

  /*
   * Terminal de modificación
   */
  private String ipActualizacion;

  /*
   * Fecha y hora de modificación
   */
  private Date feActualizacion;

  /*
   * Fecha y hora de modificación
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

  private boolean descSelected;
  private String descCoSupervision;
  private String descNoTipoComponente;
  private String descCoComponente;
  private String descNoComponente;
  private List<PgimComponenteMineroDTO> descListaComponenteMinero;
  private Long descIdHechoConstatado;
  private Integer descTipoAccion;
  private Integer datoAdicional;
}