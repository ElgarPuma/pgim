package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_PERSONAL_OSI: 
* @descripción: Personal de Osinergmin
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonalOsiDTO {

  /*
  *Identificador interno del personal de Osinergmin. Secuencia: PGIM_SEQ_PERSONAL_OSI
  */
  private Long idPersonalOsi;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Nombre Windows de usuario. Este dato se utilizará para obtener el identificador interno del usuario Siged.
  */
  private String noUsuario;

  /*
  *Identificador interno del usuario Siged.
  */
  private Long coUsuarioSiged;

  /*
  *Indicador lógico que determina si la persona del Osinergmin se encuentra activa o no en el marco de las asignaciones de pasos de flujos de trabajo
  */
  private String flActivo;

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
  *Nombre completo del personal de Osinergmin
  */
  private String descPersonaCompleto;

  /*
  *Nombre del personal de Osinergmin
  */
  private String descNoPersona;

  /*
  *Apellido paterno del personal de Osinergmin
  */
  private String descApPaterno;

  /*
  *Apellido materno del personal de Osinergmin
  */
  private String descApMaterno;

  /*
  *Número de documento de identidad del personal de Osinergmin
  */
  private String descCoDocumentoIdentidad;

  /*
  *Tipo documento de identidad del personal de Osinergmin
  */
  private String descTipoDocumentoIdentidad;

  /*
  *Correo del personal de Osinergmin
  */
  private String descDeCorreo;

  /*
  *Número de teléfono del personal de Osinergmin
  */
  private String descDeTelefono;

  /*
  *DESC_NO_PREFIJO_PERSONA
  */
  private String descNoPrefijoPersona;

  /*
  *Sexo de la persona
  */
  private String descTiSexo;
  
  /*
  * Cadena en base64 de la foto del usuario personal de Osinergmin
  */
  private String descFotoBase64;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}