package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TC_EQP_INSTANCIA_PRO: 
* @descripción: Equipo de la instancia del proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEqpInstanciaProDTO {

  /*
  *Identificador interno del equipo de la instancia del proceso. Secuencia: PGIM_SEQ_EQP_INSTANCIA_PRO
  */
  private Long idEquipoInstanciaPro;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno del rol del proceso PGIM. Tabla padre PGIM_TM_ROL_PROCESO
  */
  private Long idRolProceso;

  /*
  *Identificador interno del personal del contrato. Tabla padre: PGIM_TD_PERSONAL_CONTRATO
  */
  private Long idPersonalContrato;

  /*
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI
  */
  private Long idPersonalOsi;

  /*
  *Flag que indica si el personal del equipo es o no responsable
  */
  private String flEsResponsable;

  /*
  *Nombre del perfil asignado a la persona del equipo. Para las personas del Osinergmin no se debe colocar valor alguno. Este valor se copiará desde la PGIM_TD_PERSONAL_CONTRATO.NO_PERFIL_EN_CONTRATO
  */
  private String noPerfilPersonaEquipo;

  /*
  *Nombre del prefijo para asignar al personal del equipo. Este valor se copiará desde la columna PGIM_TM_PERSONA.NO_PREFIJO_PERSONA y aplica tanto para el personal del Osinergmin como para el personal de la empresa supervisora
  */
  private String noPrefijoPersonaEquipo;

  /*
  *Cargo de la persona en el equipo, este es el cargo que funge la persona en el equipo de la fiscalización. Para el caso del personal de la empresa supervisora, se debe obtener desde la columna PGIM_TD_PERSONAL_CONTRATO.NO_CARGO_EN_CONTRATO y, para el caso del personal del Osinergmin, debe ir el valor NULL
  */
  private String noCargoPersonaEquipo;

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
  *Identificador interno del personal de Osinergmin. Tabla padre: PGIM_TC_PERSONAL_OSI/PGIM_TD_PERSONAL_CONTRATO
  */
  private Long descIdPersona;

  /*
  *Nombre del usuario Windows
  */
  private String descNoUsuario;

  /*
  *Identificador interno del documento de identidad
  */
  private Long descIdTipoDocIdentidad;

  /*
  *Nombre del tipo de documento de identidad
  */
  private String descNoTipoDocIdentidad;

  /*
  *Código del documento de identidad
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
  *Nombre completo de la persona
  */
  private String descNoCompletoPersona;

  /*
  *Nombre del rol en el proceso
  */
  private String descNoRolProceso;

  /*
  *Nombre completo de la personal asociada al personal del Osinergmin
  */
  private String descNombreOsinergmin;

  /*
  *Nombre completo de la personal asociada al personal del contrato
  */
  private String descNombreContrato;

  /*
  *Descriptivo que señala si se trata de un personal del Osinergmin o un personal de la empresa supervisora
  */
  private String descDeOrigen;

  /*
  *Listado del personal del contrato
  */
  private List<PgimPersonalContratoDTO> auxListaPersonalContrato;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
  private Long descIdPersonal;

  /*
  *Descripción corta del nombre de la persona con fines de codificar el nombre del documento Tabla padre: PGIM_TM_PERSONA
  */
  private String descNoPersonaDoc;

  /*
  *Nombre original del archivo
  */
  private String descNoOriginalArchivo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
   * Foto de la persona
   */
  private String descFotoBase64;

  /*
   * Tipo de genero de la persona
   */
  private String descTiSexo;

  /*
   * Nombre del tipo de documento de identidad del personal del Osinergmin
   */
  private String descNoTipoDocIdentidadOsi;
}