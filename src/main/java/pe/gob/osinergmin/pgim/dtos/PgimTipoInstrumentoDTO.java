package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TM_TIPO_INSTRUMENTO: 
* @descripción: Tipo de instrumento de medición
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTipoInstrumentoDTO {

  /*
  *Identificador del tipo de instrumento de medición. Secuencia: PGIM_SEQ_TIPO_INSTRUMENTO
  */
  private Long idTipoInstrumento;

  /*
  *Identificador interno del tipo de la especialidad. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Código del tipo de instrumento de medición
  */
  private String coTipoInstrumento;

  /*
  *Nombre del tipo de instrumento de medición
  */
  private String noTipoInstrumento;

  /*
  *Descripción del tipo de instrumento de medición
  */
  private String deTipoInstrumento;

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
  *Lista de parámetros por contrato
  */
  private List<PgimTipopameXContratoDTO> descLPgimTipopameXContrato;

  /*
  *Lista de parámetros maestros de medición por tipo de instrumento
  */
  private List<PgimTprmtroXTinstrmntoDTO> descLPgimTipoParametroMed;

  /*
  *DESC_NO_ESPECIALIDAD
  */
  private String descNoEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}