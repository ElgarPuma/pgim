package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_MEDIDA_ADM: 
* @descripción: Medidas administrativas emitidas en los procesos de supervisión y fiscalización
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMedidaAdmDTO {

  /*
  *Identificador interno de la medida administrativa. Secuencia: PGIM_SEQ_MEDIDA_ADM
  */
  private Long idMedidaAdministrativa;

  /*
  *Tipo de medida administrativa. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_MEDIDA_ADMINISTRATIVA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoMedidaAdministrativa;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno deL PAS. Tabla padre: PGIM_TC_PAS
  */
  private Long idPas;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Tipo de objeto relacionado a la medidad administrativa. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OBJ_RELACIONADO_MED_ADM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoObjeto;

  /*
  *Código de la medida administrativa
  */
  private String coMedidaAdministrativa;

  /*
  *Descripción de la medida administrativa
  */
  private String deMedidaAdministrativa;

  /*
  *Fecha de la medida administrativa
  */
  private Date feMedidaAdministrativa;

  /*
  *Fecha de la medida administrativa
  */
  private String feMedidaAdministrativaDesc;

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
  *DESC_NU_EXPEDIENTE_SIGED
  */
  private String descNuExpedienteSiged;

  /*
  *DESC_NO_TIPO_MEDIDA_ADMINISTRATIVA
  */
  private String descNoTipoMedidaAdministrativa;

  /*
  *DESC_NO_TIPO_OBJETO
  */
  private String descNoTipoObjeto;

  /*
  *DESC_NO_UNIDAD_MINERA
  */
  private String descNoUnidadMinera;

  /*
  *DESC_CO_SUPERVISION
  */
  private String descCoSupervision;

  /*
  *DESC_CO_SUPERVISION_PAS
  */
  private String descCoSupervisionPas;

  /*
  *DESC_FLAG_MIS_ASIGNACIONES
  */
  private String descFlagMisAsignaciones;

  /*
  *DESC_NO_PERSONA_ASIGNADA
  */
  private String descNoPersonaAsignada;

  /*
  *DESC_USUARIO_ASIGNADO
  */
  private String descUsuarioAsignado;

  /*
  *descIdInstanciPaso
  */
  private Long descIdInstanciaPaso;

  /*
  *descFeInstanciaPaso
  */
  private Date descFeInstanciaPaso;

  /*
  *descDeMensaje
  */
  private String descDeMensaje;

  /*
  *descFlPasoActivo
  */
  private String descFlPasoActivo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}