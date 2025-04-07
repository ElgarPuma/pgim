package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VC_PERSONACON_AUX: 
* @descripción: Vista para obtener el personal de las empresas supervisoras
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonaconAuxDTO {

  /*
  *Identificador interno de la vista personal contrato AUX:  Secuencia: PGIM_SEQ_XYZ
  */
  private Long idPersonalConAux;

  /*
  *Identificador interno del personal del contrato. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
  private Long idPersonalContrato;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Nombre del usuario Windows
  */
  private String noUsuario;

  /*
  *Identificador interno del documento de identidad
  */
  private Long idTipoDocIdentidad;

  /*
  *Nombre del tipo de documento de identidad
  */
  private String noTipoDocIdentidad;

  /*
  *Código del documento de identidad
  */
  private String coDocumentoIdentidad;

  /*
  *Nombre de la persona
  */
  private String noPersona;

  /*
  *Apellido paterno de la persona
  */
  private String apPaterno;

  /*
  *Apellido materno de la persona
  */
  private String apMaterno;

  /*
  *Nombre completo de la persona
  */
  private String noCompletoPersona;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}