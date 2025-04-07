package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_AGENTE_SUPERVISADO: 
* @descripción: Agente supervisado (titular minero)
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAgenteSupervisadoDTO {

  /*
  *Identificador interno del agente supervisado. Secuencia: PGIM_SEQ_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Identificador interno del estrato. Tabla padre: PGIM_TM_ESTRATO
  */
  private Long idEstrato;

  /*
  *Tamaño de la empresa. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TAMANIO_EMPRESA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idTamanioEmpresa;

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
  *Principal actividad económica
  */
  private String dePrincipalActividadEcnmca;

  /*
  *Número de documento de identidad
  */
  private String descCoDocumentoIdentidad;

  /*
  *Razón social de la persona jurídica
  */
  private String descNoRazonSocial;

  /*
  *Nombre del estrato
  */
  private String descNoEstrato;

  /*
  *Nombre del Tamaño de la empresa
  */
  private String descNoTamanioEmpresa;

  /*
  *Teléfono
  */
  private String descDeTelefono;

  /*
  *Teléfono 02
  */
  private String descDeTelefono2;

  /*
  *Correo electrónico
  */
  private String descDeCorreo;

  /*
  *Correo electrónico 02
  */
  private String descDeCorreo2;

  /*
  *Dirección
  */
  private String descDeDireccion;

  /*
  *Identificador interno del ubigeo.
  */
  private Long idUbigeo;

  /*
  *Nombre del ubigeo
  */
  private String noUbigeo;

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
  *Nombre corto de la persona jurídica
  */
  private String descNoCorto;

  /*
  *Cantidad de Unidades Mineras asociadas al Agente Supervisado
  */
  private Long nuCantidadUniMinera;

  /*
  *Capacidad total instalada de planta (TM/d), de las unidades mineras asociadas al Agente Supervisado, y que son de tipo concesión de beneficio.
  */
  private BigDecimal nuCapacidadTotalInstalada;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}