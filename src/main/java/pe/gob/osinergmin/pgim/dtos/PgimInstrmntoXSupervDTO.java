package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TD_INSTRMNTO_X_SUPERV: 
* @descripción: Instrumento de medición por fiscalización
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInstrmntoXSupervDTO {

  /*
  *Identificador interno del instrumento de medición de la fiscalización. Secuencia: PGIM_SEQ_INSTRMNTO_X_SUPERV
  */
  private Long idInstrmntoXSuperv;

  /*
  *Identificador interno del tipo de instrumento de medición. Tabla padre: PGIM_TM_TIPO_INSTRUMENTO
  */
  private Long idTipoInstrumento;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Estado del instrumento de medición en el marco de una fiscalización. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_INSTRUMENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idEstadoInstrumento;

  /*
  *Identificador de la instancia de paso de proceso en donde se creó el instrumento. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Identificador del instrumento de la fiscalización que reemplaza al actual instrumento. Tabla padre: PGIM_TD_INSTRMNTO_X_SUPERV. Si tiene valor entonces el estado del instrumento actual debe ser "Reemplazado"
  */
  private Long idInstrmntoXSupervRmplzo;

  /*
  *Código del instrumento de medición que se utiliza en la fiscalización
  */
  private String coInstrumento;

  /*
  *Número de serie del instrumento de medición que se utiliza en la fiscalización
  */
  private String coSerieInstrumento;

  /*
  *Marca del instrumento de medición que se utiliza en la fiscalización
  */
  private String noMarcaInstrumento;

  /*
  *Modelo del instrumento de medición que se utiliza en la fiscalización
  */
  private String noModeloInstrumento;

  /*
  *Código del certificado de calibración
  */
  private String coCertificadoCalibracion;

  /*
  *Nombre del laboratorio que realizó la calibración
  */
  private String noLaboratorioCalibracion;

  /*
  *Fecha en la que se realizó la calibración del instrumento
  */
  private Date feCalibracion;

  /*
  *Fecha en la que se realizó la calibración del instrumento
  */
  private String feCalibracionDesc;

  /*
  *Fecha de vencimiento de la calibración
  */
  private Date feVencimientoCalibracion;

  /*
  *Fecha de vencimiento de la calibración
  */
  private String feVencimientoCalibracionDesc;

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
  *Código del estado del instrumento
  */
  private String descCoEstadoInstrumento;

  /*
  *Nombre del tipo de instrumento
  */
  private String descNoTipoInstrumento;

  /*
  *Nombre del estado del instrumento de medición
  */
  private String descNoEstadoInstrumento;

  /*
  *Lista de parámetros por contrato
  */
  private List<PgimTipopameXContratoDTO> descLPgimTipopameXContrato;

  /*
  *DESC_NO_PASO_PROCESO
  */
  private String descNoPasoProceso;

  /*
  *DESC_NO_INSTRMNTO_X_SUPERV_RMPLZO
  */
  private String descNoInstrmntoXSupervRmplzo;

  /*
  *DESC_NO_INSTRMNTO_X_SUPERV_RMPLZDO
  */
  private String descNoInstrmntoXSupervRmplzdo;

  /*
  *DESC_ID_INSTRMNTO_X_SUPERV_RMPLZDO
  */
  private Long descIdInstrmntoXSupervRmplzdo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
   * Codigo de supervisión
   */
  private String descCodSupervision;


}