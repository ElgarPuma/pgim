package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_PERSONA: 
* @descripción: Persona (natural o jurídica)
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonaDTO {

  /*
  *Identificador interno de la persona. Secuencia: PGIM_SEQ_PERSONA
  */
  private Long idPersona;

  /*
  *Identificador interno del ubigeo. Tabla padre: PGIM_TM_UBIGEO
  */
  private Long idUbigeo;

  /*
  *Código de tipo de documento de identidad de la persona. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DOCUMENTO_IDENTIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoDocIdentidad;

  /*
  *Código del tipo de la fuente de la persona natural. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = FUENTE_PERSONA_NATURAL. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idFuentePersonaNatural;

  /*
  *Indicador lógico que señala si el la persona jurídica es o no un consorcio. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flConsorcio;

  /*
  *Número de documento de identidad
  */
  private String coDocumentoIdentidad;

  /*
  *Razón social de la persona jurídica
  */
  private String noRazonSocial;

  /*
  *Nombre corto de la persona jurídica
  */
  private String noCorto;

  /*
  *Nombres de la persona natural
  */
  private String noPersona;

  /*
  *Apellido paterno de la persona natural
  */
  private String apPaterno;

  /*
  *Apellido materno de la persona natural
  */
  private String apMaterno;

  /*
  *Sexo. Los valores admisibles son: "1": Masculino y "0": Femenino
  */
  private String tiSexo;

  /*
  *Fecha de nacimiento
  */
  private Date feNacimiento;

  /*
  *Fecha de nacimiento
  */
  private String feNacimientoDesc;

  /*
  *Teléfono
  */
  private String deTelefono;

  /*
  *Teléfono 02
  */
  private String deTelefono2;

  /*
  *Correo electrónico
  */
  private String deCorreo;

  /*
  *Correo electrónico 02
  */
  private String deCorreo2;

  /*
  *Dirección
  */
  private String diPersona;

  /*
  *Nota
  */
  private String cmNota;

  /*
  *Restricción de acuerdo con el Reniec
  */
  private String deRestriccion;

  /*
  *Flag que indica si la persona se encuentra afiliado a notificación electrónica. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flAfiliadoNtfccionElctrnca;

  /*
  *Fecha desde que se afilió a la notificación electrónica
  */
  private Date feAfiliadoDesde;

  /*
  *Fecha desde que se afilió a la notificación electrónica
  */
  private String feAfiliadoDesdeDesc;

  /*
  *Correo de notificación electrónica
  */
  private String deCorreoNtfccionElctrnca;

  /*
  *Prefijo de la persona natural; para el caso de persona jurídica no se debe permitir registrar valor alguno
  */
  private String noPrefijoPersona;

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
  *Descripcion del nombre de tipo de documento de identidad DNI, CE y RUC
  */
  private String descIdTipoDocumento;

  /*
  *Descripcion del ubigeo
  */
  private String descUbigeo;

  /*
  *Descripción del tipo de documento de identidad
  */
  private String descIdTipoDocIdentidad;

  /*
  *Identificador interno del cliente Siged asociado a la persona de la PGIM
  */
  private String descIdClienteSiged;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}