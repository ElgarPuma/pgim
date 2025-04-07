package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_ANTECEDENTE_SUPERV: 
* @descripción: Antecedentes de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAntecedenteSupervDTO {

  /*
  *Identificador interno del antecedente de la supervisión. Secuencia: PGIM_SEQ_ANTECEDENTE_SUPERV
  */
  private Long idAntecedenteSuperv;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno del documento relacionado con el entregable. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *Aspectos revisados por el fiscalizador
  */
  private String deAspectosRevisados;

  /*
  *Tipo de antecedente. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ANTECEDENTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoAntecedente;

  /*
  *Tipo de supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUPERVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoSupervision;

  /*
  *Número del expediente Siged asociado al evento
  */
  private String nuExpedienteSiged;

  /*
  *Fecha real de inicio de la supervisión
  */
  private Date feInicioSupervisionReal;

  /*
  *Fecha real de inicio de la supervisión
  */
  private String feInicioSupervisionRealDesc;

  /*
  *Fecha real fin de la supervisión
  */
  private Date feFinSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
  private String feFinSupervisionRealDesc;

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