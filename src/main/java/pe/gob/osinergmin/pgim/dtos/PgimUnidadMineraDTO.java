package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

import java.util.List;

/**
* DTO para la entidad PGIM_TM_UNIDAD_MINERA: 
* @descripción: Unidad minera a supervisar
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimUnidadMineraDTO {

  /*
  *Identificador interno de la unidad minera. Secuencia: PGIM_SEQ_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Identificador interno del agente supervisado. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *Identificador interno del método de explotación de las unidades mineras. Tabla padre: PGIM_TM_MTDO_EXPLOTACION
  */
  private Long idMetodoExplotacion;

  /*
  *Identificador interno de otra unidad minera que funge de plata de beneficio destino para la unidad minera en cuestión. Cuando la propiedad del registro <<Tipo de UM>> tenga alguno de los valores siguientes: <<1. UEA>>; <<2.1.3 Concesión minera>>; o <<2.2 Acumulación>>; entonces esta columna podrá tener algún valor. Ahora bien, la lista de códigos de UM factibles de utilizarse para actualizar esta columna deben ser aquellas cuyo tipo es <<2.1.1 Concesión de beneficio>>. Table padre: PGIM_TM_UNIDAD_MINERA.  Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idPlntaBeneficioDestino;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Situación de la unidad minera, de acuerdo con lo establecido por el Minem. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = SITUACION_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idSituacion;

  /*
  *Tipo de actividad de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTIVIDAD. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoActividad;

  /*
  *Subtipo de actividad
  */
  private Long idSubactividad;

  /*
  *Estado de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = ESTADO_UM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idEstadoUm;

  /*
  *Tipo de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_UNIDAD_MINERA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoUnidadMinera;

  /*
  *Tipo de yacimiento de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_YACIMIENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoYacimiento;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoSustancia;

  /*
  *Tipo de sustancia de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUSTANCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idMetodoMinado;

  /*
  *Código de la unidad minera que tiene estricta correspondencia con la codificación del Ingemmet
  */
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String noUnidadMinera;

  /*
  *Código del alias de anonimización
  */
  private String coAnonimizacion;

  /*
  *Fecha del inicio de la titularidad del agente supervisado
  */
  private Date feInicioTitularidad;

  /*
  *Fecha del inicio de la titularidad del agente supervisado
  */
  private String feInicioTitularidadDesc;

  /*
  *Capacidad instalada de planta expresada en TM/d. Aplica solo cuando el <<Tipo de Unidad Minera>> sea igual a <<Concesión de beneficio>>
  */
  private BigDecimal nuCpcdadInstldaPlanta;

  /*
  *Indicador lógico que señala la existencia o no de indicios de cámara subterránea de gas en las operaciones. Aplica solo cuando la columna <<CO_METODO_MINADO>> tenga el valor que representa a <<Subterráneo>>. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flCmraSubtrraneaGas;

  /*
  *Profundidad expresada en metros (m)
  */
  private BigDecimal nuProfundidad;

  /*
  *Altura mínima expresada en metros sobre el nivel del mar (msnm)
  */
  private BigDecimal nuAlturaMinima;

  /*
  *Altura máxima expresada en metros sobre el nivel del mar (msnm)
  */
  private BigDecimal nuAlturaMaxima;

  /*
  *Ubicación, acceso y antecedentes de la unidad minera
  */
  private String deUbicacionAcceso;

  /*
  *Indicador lógico que señala si el registro de datos de riesgo será o no mandatorio en el contexto de una fiscalización. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flRegistraRiesgos;

  /*
  *Indicador lógico que señala si el registro de la UM tiene componentes de tipo Piques. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flConPiques;

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
  *Número de documento de identidad
  */
  private String descCoDocumentoIdentidad;

  /*
  *Razón social de la persona jurídica
  */
  private String descNoRazonSocial;

  /*
  *Nombre del código de la actividad de la unidad minera
  */
  private String descIdTipoActividad;

  /*
  *Nombre del código de división supervisora de la unidad minera
  */
  private String descIdDivisonSupervisora;

  /*
  *Nombre del código de la situación de la unidad minera
  */
  private String descIdTipoSituacion;

  /*
  *Nombre del código del tipo de la unidad minera
  */
  private String descIdTipoUnidadMinera;

  /*
  *Nombre de la planta de beneficio destino asociado a la unidad minera.
  */
  private String descIdPlntaBeneficioDestino;

  /*
  *Lista de sustancias seleccionadas de la unidad minera
  */
  private List<PgimSustanciaDTO> auxSustanciasUm;

  /*
  *Descripcion del ubigeo
  */
  private String descUbigeo;

  /*
  *DESC_NO_ESTRATO
  */
  private String descNoEstrato;

  /*
  *DESC_NO_DIVISION_SUPERVISORA
  */
  private String descNoDivisionSupervisora;

  /*
  *DESC_NO_TIPO_UNIDAD_MINERA
  */
  private String descNoTipoUnidadMinera;

  /*
  *DESC_NO_SITUACION
  */
  private String descNoSituacion;

  /*
  *DESC_NO_PLNTA_BENEFICIO_DESTINO
  */
  private String descNoPlntaBeneficioDestino;

  /*
  *DESC_NO_TIPO_MINADO
  */
  private String descNoTipoMinado;

  /*
  *DESC_NO_METODO_EXPLOTACION
  */
  private String descNoMetodoExplotacion;

  /*
  *DESC_NO_TIPO_YACIMIENTO
  */
  private String descNoTipoYacimiento;

  /*
  *DESC_NO_TIPO_SUSTANCIA
  */
  private String descNoTipoSustancia;

  /*
  *DESC_BASE64_IMG
  */
  private String descBase64Img;

  /*
  *DESC_NO_METODO_MINADO
  */
  private String descNoMetodoMinado;

  /*
  *DESC_UBIGEO_DEMARCACION
  */
  private String descUbigeoDemarcacion;

  /*
  *DESC_FL_PRINCIPAL_DEMARCACION
  */
  private String descFlPrincipalDemarcacion;

  /*
  *DESC_PORCENTAJE_DEMARCACION
  */
  private BigDecimal descPorcentajeDemarcacion;

  /*
  *DESC_NU_CANTIDAD_PIQUES
  */
  private Long descNuCantPiques;

  /*
  *DESC_NO_ESTADO_UM
  */
  private String descNoEstadoUm;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}