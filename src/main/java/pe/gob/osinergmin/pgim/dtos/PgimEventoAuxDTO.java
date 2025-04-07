package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_EVENTO_AUX: 
* @descripción: Vista para obtener la lista de eventos
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEventoAuxDTO {

  /*
  *Identificador interno del evento. Secuencia: PGIM_SEQ_EVENTO_AUX
  */
  private Long idEventoAux;

  /*
  *Identificador interno del evento
  */
  private Long idEvento;

  /*
  *CO_EVENTO
  */
  private String coEvento;

  /*
  *FE_PRESENTACION
  */
  private Date fePresentacion;

  /*
  *FE_PRESENTACION
  */
  private String fePresentacionDesc;

  /*
  *FE_EVENTO
  */
  private Date feEvento;

  /*
  *FE_EVENTO
  */
  private String feEventoDesc;

  /*
  *FE_ANIO_EVENTO
  */
  private Long feAnioEvento;

  /*
  *DE_EVENTO
  */
  private String deEvento;

  /*
  *DE_LUGAR_EVENTO
  */
  private String deLugarEvento;

  /*
  *ES_NO_CONTABILIZAR
  */
  private String esNoContabilizar;

  /*
  *DE_MOTIVO_NOCONTABILIZAR
  */
  private String deMotivoNocontabilizar;

  /*
  *ES_REGISTRO
  */
  private String esRegistro;

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
  *ID_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *RUC_AS
  */
  private String rucAs;

  /*
  *NO_RAZON_SOCIAL_AS
  */
  private String noRazonSocialAs;

  /*
  *NO_CORTO_AS
  */
  private String noCortoAs;

  /*
  *ID_TIPO_EVENTO
  */
  private Long idTipoEvento;

  /*
  *NO_TIPO_EVENTO
  */
  private String noTipoEvento;

  /*
  *ID_AGENTE_CAUSANTE
  */
  private Long idAgenteCausante;

  /*
  *NO_AGENTE_CAUSANTE
  */
  private String noAgenteCausante;

  /*
  *ID_TIPO_ACCIDENTE_MORTAL
  */
  private Long idTipoAccidenteMortal;

  /*
  *NO_TIPO_ACCIDENTE_MORTAL
  */
  private String noTipoAccidenteMortal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}