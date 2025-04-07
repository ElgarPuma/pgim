package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_SELECT_ANTECEDENTE_AUX: 
* @descripción: Vista para seleccionar antecedentes ya registradas en las tablas maestras
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSelectAntecedenteAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_SELECT_ANTECEDENTE_AUX Secuencia: PGIM_SEQ_SELECT_ANTECEDENTE_AUX
  */
  private Long idDocumentoAux;

  /*
  *ID_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *CO_TABLA_INSTANCIA
  */
  private Long coTablaInstancia;

  /*
  *FECHA
  */
  private String fecha;

  /*
  *CODIGO
  */
  private String codigo;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *ID_INSTANCIA_PROCESO
  */
  private Long idInstanciaProceso;

  /*
  *DESCRIPCION
  */
  private String descripcion;

  /*
  *NO_TIPO_ANTECEDENTE
  */
  private String noTipoAntecedente;

  /*
  *Tipo de antecedente. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ANTECEDENTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoAntecedente;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *ID_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}