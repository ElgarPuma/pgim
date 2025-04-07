package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_CONTACTO_AGENTE: 
* @descripción: Contacto del agente supervisado
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimContactoAgenteDTO {

  /*
  *Identificador interno de la persona contacto del agente supervisado. Secuencia: PGIM_SEQ_CONTACTO_AGENTE
  */
  private Long idContactoAgente;

  /*
  *Identificador interno de la persona contacto del agente supervisado. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *Identificador interno de la persona contacto. Table padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Nombre del cargo del contacto del agente fiscalizado
  */
  private String noCargo;

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
  *Nombre de la persona
  */
  private String descNoPersona;

  /*
  *Apellido paterno de la persona
  */
  private String descApPaterno;

  /*
  *Apellido materno de la persona natural
  */
  private String descApMaterno;

  /*
  *Teléfono
  */
  private String descDeTelefono;

  /*
  *Correo electrónico
  */
  private String descDeCorreo;

  /*
  *Razón social de la persona jurídica
  */
  private String descNoRazonSocial;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}