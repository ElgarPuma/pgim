package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_TIPOPAME_X_CONTRATO: 
* @descripción: Tipo de parámetro de medición por contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTipopameXContratoDTO {

  /*
  *Identificador interno del tipo parámetro de medición por contrato. Secuencia: PGIM_SEQ_TIPOPAME_X_CONTRATO
  */
  private Long idTipopameXContrato;

  /*
  *Identificador interno del tipo de parámetro por tipo de instrumento. Tabla padre: PGIM_TM_TPRMTRO_X_TINSTRMNTO
  */
  private Long idTprmtroXTinstrmnto;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Rango de medición del tipo de parámetro por instrumento de medición
  */
  private String deRangoMedicion;

  /*
  *Descripción de la precisión del tipo de instrumento de medición respecto al tipo de parámetro
  */
  private String dePrecision;

  /*
  *Descripción de la exactitud del tipo de instrumento de medición respecto al tipo de parámetro
  */
  private String deExactitud;

  /*
  *Descripción de la resolución del tipo de instrumento de medición respecto al tipo de parámetro
  */
  private String deResolucion;

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
  *Identificador interno del tipo de instrumento de medición
  */
  private Long descIdTipoInstrumento;

  /*
  *Código del tipo de instrumento
  */
  private String descCoTipoInstrumento;

  /*
  *Nombre del tipo de instrumento
  */
  private String descNoTipoInstrumento;

  /*
  *Nombre del tipo de parámetro
  */
  private String descCoTipoParametro;

  /*
  *Nombre del tipo de parámetro
  */
  private String descNoTipoParametro;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}