package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_TPRMTRO_X_TINSTRMNTO: 
* @descripción: Tipo de parámetro por tipo de instrumento de medición
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTprmtroXTinstrmntoDTO {

  /*
  *Identificador interno del tipo de parámetro por tipo de instrumento. Secuencia: PGIM_SEQ_TPRMTRO_X_TINSTRMNTO
  */
  private Long idTprmtroXTinstrmnto;

  /*
  *Identificador interno del tipo de instrumento de medición. Tabla padre: PGIM_TM_TIPO_INSTRUMENTO
  */
  private Long idTipoInstrumento;

  /*
  *Identificador interno del tipo de parámetro de medición. Tabla padre: PGIM_TM_TIPO_PARAMETRO_MED
  */
  private Long idTipoParametroMed;

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
  *Identificador del contrato para la selección
  */
  private Long descIdContrato;

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
  *Flag indicativo de la selección
  */
  private String descFlSeleccionado;

  /*
  *DESC_NUM_USOS
  */
  private Long descNumUsos;

  /*
  *DESC_NUM_USOS
  */
  private String descNuContrato;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}