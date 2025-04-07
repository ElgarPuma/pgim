package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VC_PERSONAOSI_AUX: 
* @descripción: Vista para obtener el personal del Osinergmin
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonaosiAuxDTO {

  /*
  *Identificador interno del personal Osinergmin AUX. Secuencia: PGIM_SEQ_PERSONAL_OSI
  */
  private Long idPersonalOsiAux;

  /*
  *Identificador interno de la supervisión
  */
  private Long idPersonalOsi;

  /*
  *Identificador interno de la persona
  */
  private Long idPersona;

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
  *Nombre del rol en el proceso
  */
  private String descNoRolProceso;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}