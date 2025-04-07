package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_INFRACCION_CONTRA: 
* @descripción: Contratista responsable por infracción de una obligación normativa de hecho constatado
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 25/04/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionContraDTO {

  /*
  *Identificador interno del contratista responsable de la infracción asociada a la obligación normativa de la fiscalización. Secuencia: PGIM_SEQ_INFRACCION_CONTRA
  */
  private Long idInfraccionContra;

  /*
  *Identificador interno de la infracción. Tabla padre: PGIM_TD_INFRACCION
  */
  private Long idInfraccion;

  /*
  *Identificador interno del contratista responsable de la infracción del cual se ha copiado este registro. Tabla padre: PGIM_TD_INFRACCION_CONTRA
  */
  private Long idInfraccionContraOrigen;

  /*
  *Identificador interno de la persona jurídica. Tabla padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Flag que indica si la infracción se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flVigente;

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

  /**
   *Razón social de la empresa responsable
   */
  private String descNoRazonSocial;

  /**
   * RUC de la empresa responsable
   */
  private String descCoDocumentoIdentidad;
  
  /**
   * Flag que indica si es el agente fiscalizado
   */
  private String descFlAgenteFiscalizado;

  /**
   * Permite obtener los destinatarios actuales.
   */
  private String descFlDestinatarioActual;
  
  /**
   *Propiedad auxiliar: Id de la instancia de paso actual
   */
  private Long descIdInstanciaPasoActual;

}