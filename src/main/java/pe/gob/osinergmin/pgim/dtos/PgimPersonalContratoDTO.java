package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_PERSONAL_CONTRATO: 
* @descripción: Personal del contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonalContratoDTO {

  /*
  *Identificador interno del personal del contrato. Secuencia: PGIM_SEQ_PERSONAL_CONTRATO
  */
  private Long idPersonalContrato;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Identificador interno del rol del proceso PGIM. Solo aplica para roles de la fiscalización. Tabla padre PGIM_TM_ROL_PROCESO
  */
  private Long idRolProceso;

  /*
  *Nombre Windows de usuario. Este dato se utilizará para obtener el identificador interno del usuario Siged.
  */
  private String noUsuario;

  /*
  *Identificador interno del usuario Siged.
  */
  private Long coUsuarioSiged;

  /*
  *Perfil de la persona en el contrato. Este dato se registra a partir del perfil definido en el contrato
  */
  private String noPerfilEnContrato;

  /*
  *Cargo de la persona en el contrato, este es el cargo que funge la persona en el marco del contrato
  */
  private String noCargoEnContrato;

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
  *Nombre completo de la persona
  */
  private String descPersonaCompleto;

  /*
  *Flag que indica si el contrato se encuentra vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String descFlEstado;

  /*
  *DNI o CE de la persona
  */
  private String descCoDocumentoIdentidad;

  /*
  *Nombre de la persona
  */
  private String descNoPersona;

  /*
  *Apellido paterno de la persona
  */
  private String descApPaterno;

  /*
  *Apellido materno de la persona
  */
  private String descApMaterno;

  /*
  *Identificador del tipo de documento de identidad de la persona
  */
  private Long descIdTipoDocIdentidad;

  /*
  *Tipo de documento de identidad de la persona
  */
  private String descTipoDocIdentidad;

  /*
  *Identificador del ubigeo de la persona
  */
  private Long descIdUbigeo;

  /*
  *Descripcion del ubigeo
  */
  private String descUbigeo;

  /*
  *DESC_NO_PREFIJO_PERSONA
  */
  private String descNoPrefijoPersona;

  /*
  *Nombre del rol asociado al proceso de negocio
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

  /**
   * Id de la división supervisora
   */
  private Long idDivisionSupervisora;

  /**
   * Nombre de la división supervisora
   */
  private String descDeValorParametro;

}