package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_EXPPAS_PENDIENTES_AUX: 
* @descripción: Vista expedientes PAS pendientes por persona asignada
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExppasPendientesAuxDTO {

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_EXP_SIGED_PAS_AUX
  */
  private String idExpSigedPasAux;

  /*
  *ANIO_SUPERVISION
  */
  private Long anioSupervision;

  /*
  *CO_SUPERVISION
  */
  private String coSupervision;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
  private Date feInicioSupervisionReal;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
  private String feInicioSupervisionRealDesc;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
  private Date feFinSupervisionReal;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
  private String feFinSupervisionRealDesc;

  /*
  *EXP_SIGED_SUPERVISION
  */
  private String expSigedSupervision;

  /*
  *EXP_SIGED_PAS
  */
  private String expSigedPas;

  /*
  *ETIQUETA_AGENTE_SUPERVISADO
  */
  private String etiquetaAgenteSupervisado;

  /*
  *ETIQUETA_UNIDAD_MINERA
  */
  private String etiquetaUnidadMinera;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *ETIQUETA_PASO_ACTUAL
  */
  private String etiquetaPasoActual;

  /*
  *FECHA_PASO_ACTUAL
  */
  private Date fechaPasoActual;

  /*
  *FECHA_PASO_ACTUAL
  */
  private String fechaPasoActualDesc;

  /*
  *DIAS_TRANSCURRIDOS
  */
  private Long diasTranscurridos;

  /*
  *ETIQUETA_PERSONA_ASIGNADA
  */
  private String etiquetaPersonaAsignada;

  /*
  *FE_INICIO_SUPERVISION
  */
  private Date feInicioSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
  private String feInicioSupervisionDesc;

  /*
  *FE_FIN_SUPERVISION
  */
  private Date feFinSupervision;

  /*
  *FE_FIN_SUPERVISION
  */
  private String feFinSupervisionDesc;

  /*
  *FE_FIN_SUPERVISION
  */
  private String noDivisionSupervisora;

  /*
  *FL_PROPIA
  */
  private String flPropia;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}