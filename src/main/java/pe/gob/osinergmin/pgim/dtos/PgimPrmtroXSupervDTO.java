package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_PRMTRO_X_SUPERV: 
* @descripción: Parámetro de medición por fiscalización
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPrmtroXSupervDTO {

  /*
  *Identificador interno del parámetro de medición por fiscalización. Secuencia: PGIM_SEQ_PRMTRO_X_SUPERV
  */
  private Long idPrmtroXSuperv;

  /*
  *Identificador interno del tipo de instrumento de medición por fiscalización. Tabla padre: PGIM_TD_INSTRMNTO_X_SUPERV
  */
  private Long idInstrmntoXSuperv;

  /*
  *Identificador interno del tipo parámetro de medición por contrato. Tabla padre: PGIM_TD_TIPOPAME_X_CONTRATO
  */
  private Long idTipopameXContrato;

  /*
  *Rango de medición del tipo de parámetro por instrumento de medición. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_RANGO_MEDICION
  */
  private String deRangoMedicion;

  /*
  *Descripción de la precisión del tipo de instrumento de medición respecto al tipo de parámetro. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_PRECISION
  */
  private String dePrecision;

  /*
  *Descripción de la exactitud del tipo de instrumento de medición respecto al tipo de parámetro. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_EXACTITUD
  */
  private String deExactitud;

  /*
  *Descripción de la resolución del tipo de instrumento de medición respecto al tipo de parámetro. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_RESOLUCION
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
  *Nombre del parámetro por tipo de instrumento
  */
  private String descNoTprmtroXTinstrmnto;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}