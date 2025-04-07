package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO para la entidad PGIM_TD_COMPONENTE_HC:
 * 
 * @descripción: Componentes mineros asociados a un hecho constatado
 *
 * @author:
 * @version: 1.0
 * @fecha_de_creación: 17/12/2024
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimComponenteHcDTO {

  /*
   * Identificador interno del componente minero asociado a un hecho constatado.
   * Secuencia: PGIM_SEQ_COMPONENTE_HC
   */
  private Long idComponenteHc;

  /*
   * Identificador interno del hecho constatado asociado el componente minero:
   * Tabla padre: PGIM_TC_HECHO_CONSTATADO
   */
  private Long idHechoConstatado;

  /*
   * Identificador interno del componente minero a supervisar: Tabla padre:
   * PGIM_TC_CMINERO_SPRVSION
   */
  private Long idCmineroSprvsion;

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

  /*
   * Flag que indica si el componente minero ha sido asociado al hecho verificado.
   * Los posibles valores son: "1" = Sí y "0" = No
   */
  private String flAplica;

  private boolean descSelected;
  private String descNoTipoComponente;
  private String descCoComponente;
  private String descNoComponente;
}