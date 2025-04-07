package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_MEDICION: 
* @descripción: Medición de un indicador
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 23/10/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMedicionDTO {

  /*
  *Identificador interno de la medición. Secuencia: PGIM_SEQ_MEDICION
  */
  private Long idMedicion;

  /*
  *Identificador interno del indicador sobre el que se realizará una medición. Tabla padre: PGIM_TC_INDICADOR
  */
  private Long idIndicador;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *División supervisora a cargo de la supervisión de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = DIVISION_SUPERVISORA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idDivisionSupervisora;

  /*
  *Código de la medición
  */
  private String coMedicion;

  /*
  *Nombre de la medición
  */
  private String noMedicion;

  /*
  *Descripción del indicador
  */
  private String deMedicion;

  /*
  *Estado de publicación de la medición, los valores posibles son: "0" = No publicado | "1" = Publicado y su condición de registro es obligatoria. Por defecto se colocará el valor "0"
  */
  private String esPublicacion;

  /*
  *Fecha inicial para el filtro de los datos. Las restricciones del horizonte temporal se aplicarán sobre la fecha de realización de la tarea final más reciente en cada instancia del flujo de trabajo asociado al indicador.
  */
  private Date feInicial;

  /*
  *Fecha inicial para el filtro de los datos. Las restricciones del horizonte temporal se aplicarán sobre la fecha de realización de la tarea final más reciente en cada instancia del flujo de trabajo asociado al indicador.
  */
  private String feInicialDesc;

  /*
  *Fecha final para el filtro de los datos. Las restricciones del horizonte temporal se aplicarán sobre la fecha de realización de la tarea final más reciente en cada instancia del flujo de trabajo asociado al indicador.
  */
  private Date feFinal;

  /*
  *Fecha final para el filtro de los datos. Las restricciones del horizonte temporal se aplicarán sobre la fecha de realización de la tarea final más reciente en cada instancia del flujo de trabajo asociado al indicador.
  */
  private String feFinalDesc;

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
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


  /**
   * Descriptor del Id del proceso
   */
  private Long descIdProceso;
  
  /**
   * Descriptor del nombre del proceso
   */
  private String descNoProceso;

  /**
   * Id de Unidad de medida del indicador
   */
  private Long descIdTipoUnidadMedida;
  
  /**
   * Unidad de medida del indicador
   */
  private String descNoTipoUnidadMedida;

  /**
   * Descriptor del nombre de la transición inicial más antigua
   */
  private String descNoRelacionPasoOrigen;

  /**
   * Descriptor del nombre de la transición final más reciente
   */
  private String descNoRelacionPasoDestino;
  
  /**
   * Descriptor del Id de tipo de agrupación 
   */
  private Long descIdTipoAgrupadoPor;

  /**
   * Descriptor del tipo de agrupación 
   */
  private String descNoTipoAgrupadoPor;

  /**
   * Descriptor del Id del tipo de agrupación para actor de negocio.
   */
  private Long descIdTipoActorNegocio;
  
  /**
   * Descripto del tipo de agrupación para actor de negocio.
   */
  private String descNoTipoActorNegocio;

  /**
   * Descriptor del Id del tipo de agrupación para característica de la fiscalización.
   */
  private Long descIdTipoCaracterFisc;
  
  /**
   * Descripto del tipo de agrupación para característica de la fiscalización.
   */
  private String descNoTipoCaracterFisc;

  /**
   * Descriptor del coClaveTexto del tipo de agrupación para característica de la fiscalización
   */
  private String descCoClaveTextoTipoAgrupadoPor;

  /*
  *Descriptor del nombre del estado de publicación de la medición
  */
  private String descNoEsPublicacion;

  /**
   * Descripto del código del indicador
   */
  private String descCoIndicador;

  /**
   * Descripto del nombre del indicador
   */
  private String descNoIndicador;

  /***
  *Descriptor del nombre de la especialidad de supervisión.
  */
  private String descNoEspecialidad;

  /***
  *Descriptor del nombre división supervisora a cargo de la supervisión de la unidad minera. 
  */
  private String descNoDivisionSupervisora;

}