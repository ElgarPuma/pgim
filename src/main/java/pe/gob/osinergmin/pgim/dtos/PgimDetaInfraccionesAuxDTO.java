package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_DETA_INFRACCIONES_AUX: 
* @descripción: Vista detallada de infracciones en el expediente PAS
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDetaInfraccionesAuxDTO {

  /*
  *Identificador interno de la infracción. Secuencia: PGIM_SEQ_FASE_PROCESO_AUX
  */
  private Long idInfraccionAux;

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
  *ASUNTO_RESOLUCION
  */
  private String asuntoResolucion;

  /*
  *FECHA_EMISION_RESOLUCION
  */
  private Date fechaEmisionResolucion;

  /*
  *FECHA_EMISION_RESOLUCION
  */
  private String fechaEmisionResolucionDesc;

  /*
  *ETIQUETA_INFRACCION
  */
  private String etiquetaInfraccion;

  /*
  *DE_SANCION_PECUNIARIA_UIT
  */
  private String deSancionPecuniariaUit;

  /*
  *ID_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisado;

  /*
  *DI_AGENTE_SUPERVISADO
  */
  private String diAgenteSupervisado;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
  private String noDivisionSupervisora;

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