package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_EMPRESA_EVENTO: 
* @descripción: Empresa involucrada en un evento
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEmpresaEventoDTO {

  /*
  *Identificador interno del mapeo de la empresa por evento. Secuencia: PGIM_SEQ_EMPRESA_EVENTO
  */
  private Long idEmpresaEvento;

  /*
  *Identificador interno del evento. Tabla padre: PGIM_TM_EVENTO
  */
  private Long idEvento;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

  /*
  *Agente causante del evento minero asociado a la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_EMPRESA_INVOLUCRADA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoEmpresaInvolucrada;

  /*
  *Tipo de la actividad CIIU (Clasificación Industrial Internacional Uniforme). Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTVIDAD_CIIU. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoActvidadCiiu;

  /*
  *Número de trabajadores de sexo masculino
  */
  private BigDecimal nuTrabajadoresM;

  /*
  *Número de trabajadores de sexo femenino
  */
  private BigDecimal nuTrabajadoresF;

  /*
  *Descripción de la actividad económica principal
  */
  private String deActEconomicaPrincipal;

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
  *Tipo de involucramiento de la empresa en el evento
  */
  private String descEmpresaTipo;

  /*
  *Número de RUC de la persona jurídica del evento
  */
  private String descEmpresaRuc;

  /*
  *Razón social de la persona jurídica del evento
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