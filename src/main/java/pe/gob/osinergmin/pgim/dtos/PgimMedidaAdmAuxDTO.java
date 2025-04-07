package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_MEDIDA_ADM_AUX: 
* @descripción: Vista para la lista de medidas administrativas y filtros avanzados
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMedidaAdmAuxDTO {

  /*
  *Identificador interno de la infracción. Secuencia: PGIM_SEQ_MEDIDA_ADM_AUX
  */
  private Long idMedidaAdministrativaAux;

  /*
  *ID_MEDIDA_ADMINISTRATIVA
  */
  private Long idMedidaAdministrativa;

  private Long idInstanciaPaso;

  /*
  *ID_TIPO_MEDIDA_ADMINISTRATIVA
  */
  private Long idTipoMedidaAdministrativa;

  /*
  *ID_INSTANCIA_PROCESO
  */
  private Long idInstanciaProceso;

  /*
  *ID_TIPO_OBJETO
  */
  private Long idTipoObjeto;

  /*
  *ID_RELACION_PASO
  */
  private Long idRelacionPaso;

  /*
  *ID_TIPO_RELACION
  */
  private Long idTipoRelacion;

  /*
  *CO_MEDIDA_ADMINISTRATIVA
  */
  private String coMedidaAdministrativa;

  /*
  *ID_SUPERVISION
  */
  private Long idSupervision;

  /*
  *CO_SUPERVISION
  */
  private String coSupervision;

  /*
  *NO_UNIDAD_MINERA_SUP
  */
  private String noUnidadMineraSup;

  /*
  *ID_PAS
  */
  private Long idPas;

  /*
  *CO_SUPERVISION_PAS
  */
  private String coSupervisionPas;

  /*
  *NO_UNIDAD_MINERA_PAS
  */
  private String noUnidadMineraPas;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *TIPO_MEDIDA_ADMINISTRATIVA
  */
  private String tipoMedidaAdministrativa;

  /*
  *NU_EXPEDIENTE_SIGED
  */
  private String nuExpedienteSiged;

  /*
  *REFERIDA_A
  */
  private String referidaA;

  /*
  *DE_MEDIDA_ADMINISTRATIVA
  */
  private String deMedidaAdministrativa;

  /*
  *FE_MEDIDA_ADMINISTRATIVA
  */
  private Date feMedidaAdministrativa;

  /*
  *FE_MEDIDA_ADMINISTRATIVA
  */
  private String feMedidaAdministrativaDesc;

  /*
  *NO_USUARIO_ASIGNADO
  */
  private String noUsuarioAsignado;

  /*
  *NO_PERSONA_ASIGNADA
  */
  private String noPersonaAsignada;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
  *FE_INSTANCIA_PASO
  */
  private Date feIntanciaPaso;

  /*
  *DE_MENSAJE
  */
  private String deMensaje;

  /*
  *FL_PASO_ACTIVO
  */
  private String flPasoActivo;

}