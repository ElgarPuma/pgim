package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_PAS_AUX: 
* @descripción: Vista de Procesos administrativos de sanción (PAS)
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPasAuxDTO {

  /*
  *Identificador interno del PAS auxiliar. Secuencia: PGIM_SEQ_PAS_AUX
  */
  private Long idPasAux;

  /*
  *ID_PAS
  */
  private Long idPas;

  /*
  *Identificador de la instancia del paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Fecha de creación del PAS
  */
  private Date feCreacionPas;

  /*
  *Fecha de creación del PAS
  */
  private String feCreacionPasDesc;

  /*
  *Anño de creación del PAS
  */
  private String feCreacionPasAnio;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Código de la supervisión
  */
  private String coSupervision;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String noEspecialidad;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
  private String noUnidadMinera;

  /*
  *Identificador interno del agente supervisado relacionado
  */
  private Long idAgenteSupervisado;

  /*
  *Código de identididad del agente supervisado
  */
  private String asCoDocumentoIdentidad;

  /*
  *Razón social del agente supervisado
  */
  private String asNoRazonSocial;

  /*
  *Número de expediente Siged del PAS
  */
  private String nuExpedienteSigedPas;

  /*
  *Número de expediente Siged de la supervisión
  */
  private String nuExpedienteSigedSup;

  /*
  *FL_PROPIA
  */
  private String flPropia;

  /*
  *Nombre completo de la persona asignada
  */
  private String noPersonaAsignada;

  /*
  *Nombre windows del usuario asignado
  */
  private String noUsuarioAsignado;

  /*
  *Identificador interno de la fase actual del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
  private Long idFaseActual;

  /*
  *Nombre de la fase actual del proceso
  */
  private String noFaseActual;

  /*
  *Identificador interno del paso actual del proceso
  */
  private Long idPasoActual;

  /*
  *Nombre del paso actual del proceso
  */
  private String noPasoActual;

  /*
  *Identificador interno de la relación de paso
  */
  private Long idRelacionPaso;

  /*
  *Identificador interno del tipo de relación
  */
  private Long idTipoRelacion;

  /*
  *NO_DIVISION_SUPERVISORA
  */
  private String noDivisionSupervisora;

  /*
  *Identificador de la instancia PAS del proceso
  */
  private Long idInstanciaProcesoPas;

  /*
  *FE_INSTANCIA_PASO
  */
  private String feInstanciaPasoDesc;

  /*
  *DIAS_TRANSCURRIDOS
  */
  private Long diasTranscurridos;
  
  /*
  *FL_LEIDO
  */
  private String flLeido;
   
  /*
   * FE_LECTURA
   */
  private Date feLectura;

  /*
   * FE_INSTANCIA_PASO
   */
  private Date feInstanciaPaso;

  /*
   * DE_MENSAJE
   */
  private String deMensaje;

  /*
   * FL_PASO_ACTIVO
   */
  private String flPasoActivo;

  /*
  *FE_LECTURA
  */
  private String feLecturaDesc;

  /*
  *Nombre de la persona responsable
  */
  private String descNoResponsable;

  /*
  *Flag de mis asignaciones
  */
  private String descFlagMisAsignaciones;

  /*
  *Usuario (Windows) de la persona responsable
  */
  private String descUsuarioAsignado;

  /*
  *Nombre completo de la persona destino
  */
  private String descPersonaDestino;

  /*
  *Flag de mi interés
  */
  private String descFlagMiInteres;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

/*
  *Nombre de la persona origen
  */
  private String noPersonaOrigen;

  /*
  *Nombre usuario origen
  */
  private String noUsuarioOrigen;

  /**
   * número de alertas de cumplimiento de plazo que tiene la fiscalización 
   */
  private Long nuAlertas;
  
  /**
   * numero de intervalo en la cual se encuentra la alerta de cumplimiento de plazo
   */
  private Long nuIntervaloAlertas;
    
  /*
   * Titulo del reporte exportado
   */
  private String deTituloReporte;

  /*
   * Nombre del archivo
   */
  private String descNoArchivo;
  
  /*
   * Nombre del programa de la fiscalización
   */
  private String descPrograma;
  
  /*
   * Id del programa de la fiscalización
   */
  private Long descIdPrograma;
  
  /*
   * Id del motivo de la fiscalización
   */
  private Long idMotivoSupervision;

  /**
   * Cantidad de registros
   */
  private Integer cantidadRegistros;

  /**
   * Código del PAS
   */
  private String descCoPas;

  /**
   * Fecha de constancia electronica del Oipas
   */
  private Date feOrigenDocumento;

  /**
   * Cantidad de incumplimientos detectados de la fiscalización
   */
  private Long incumplimientos;

  /**
   * Cantidad de infracciones detectados de la fiscalización
   */
  private Long infracciones;
  
  /**
   * Columna a la que vamos a ordenar
   */
  private String sortColumna;

  /**
   * Tipo de ordenamiento ascendente(asc) o descendente(desc)
   */
  private String sortDirection;

  /**
   * Id del programa de la supervisión
   */
  private Long idProgramaSupervision;

  /**
   * Id de la división supervisora
   */
  private Long idDivisionSupervisora;

  /**
   * descripción del motivo de la fiscalización
   */
  private String deMotivoSupervision;
}