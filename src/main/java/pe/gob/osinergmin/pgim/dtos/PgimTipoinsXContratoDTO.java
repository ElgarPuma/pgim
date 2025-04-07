package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_TIPOINS_X_CONTRATO: 
* @descripción: Tipo de instrumento de medición por contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 05/08/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTipoinsXContratoDTO {

  /*
  *Identificador interno del tipo de instrumento de medición por contrato. Secuencia: PGIM_SEQ_TIPOINS_X_CONTRATO
  */
  private Long idTipoinsXContrato;

  /*
  *Identificador del tipo de instrumento de medición. Tabla padre: PGIM_TM_TIPO_INSTRUMENTO
  */
  private Long idTipoInstrumento;

  /*
  *Identificador interno del contrato de supervisión. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

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