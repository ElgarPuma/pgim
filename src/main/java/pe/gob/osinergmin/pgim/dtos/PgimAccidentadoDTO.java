package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TM_ACCIDENTADO: 
* @descripción: Persona accidentada en un evento dado
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAccidentadoDTO {

  /*
  *Identificador interno del mapeo del accidentado. Secuencia: PGIM_SEQ_ACCIDENTADO
  */
  private Long idAccidentado;

  /*
  *Identificador interno del evento por empresa. Tabla padre: PGIM_TM_EMPRESA_EVENTO
  */
  private Long idEmpresaEvento;

  /*
  *Identificador interno de la persona accidentada. Tabla padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Categoría ocupacional del accidentado. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = CATEGORIA_OCUPACIONAL. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idCategoriaOcupacional;

  /*
  *Fecha del fallecimiento del accidentado
  */
  private Date feFallecimiento;

  /*
  *Fecha del fallecimiento del accidentado
  */
  private String feFallecimientoDesc;

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
  *Identificador del tipo de documento de identidad del accidentado
  */
  private Long descAccidentadoIdTipoDi;

  /*
  *Tipo de documento de identidad del accidentado
  */
  private String descAccidentadoTipoDi;

  /*
  *Número de DNI o carne de extranjería de la persona accidentada
  */
  private String descAccidentadoDnice;

  /*
  *Nombre de la persona accidentada
  */
  private String descAccidentadoNombres;

  /*
  *Apellido paterno de la persona accidentada
  */
  private String descAccidentadoApPaterno;

  /*
  *Apellido materno de la persona accidentada
  */
  private String descAccidentadoApMaterno;

  /*
  *Fecha de nacimiento de la persona accidentada
  */
  private Date descAccidentadoFeNacimiento;

  /*
  *Fecha de nacimiento de la persona accidentada
  */
  private String descAccidentadoFeNacimientoDesc;

  /*
  *Domicilio de la persona accidentada
  */
  private String descAccidentadoDomicilio;

  /*
  *Sexo de la persona accidentada
  */
  private String descAccidentadoSexo;

  /*
  *Identificador del ubigeo de la persona accidentada
  */
  private Long descAccidentadoIdUbigeo;

  /*
  *Descripción del ubigeo de la persona accidentada
  */
  private String descAccidentadoUbigeo;

  /*
  *Teléfono de la persona accidentada
  */
  private String descAccidentadoTelefono;

  /*
  *Lista de seguros de la persona accidentada
  */
  private List<PgimValorParametroDTO> auxAccidentadoSeguros;

  /*
  *Identificador interno de la empleadora de la persona accidentada
  */
  private Long descAccidentadoIdEmpresa;

  /*
  *Tipo de involucramiento de la persona jurídica correspondiente a la empresa del evento
  */
  private String descEmpresaTipo;

  /*
  *Número de RUC de la persona jurídica correspondiente a la empresa del evento
  */
  private String descEmpresaRuc;

  /*
  *Razón social de la persona jurídica correspondiente a la empresa del evento
  */
  private String descEmpresaRazonSocial;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}