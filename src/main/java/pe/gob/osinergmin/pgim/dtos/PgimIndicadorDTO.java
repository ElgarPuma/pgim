package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_INDICADOR: 
* @descripción: Indicador
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 27/09/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimIndicadorDTO {

  /*
  *Identificador interno del indicador. Secuencia: PGIM_SEQ_INDICADOR
  */
  private Long idIndicador;

  /*
  *Tipo de indicador. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoIndicador;

  /*
  *Unidad de medida del indicador. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_UNIDAD_MEDIDA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoUnidadMedida;

  /*
  *Identificador interno del proceso. Tabla padre: PGIM_TM_PROCESO
  */
  private Long idProceso;

  /**
   * Identificador interno de la relación de paso origen del proceso para la transición más antigua. Tabla padre: PGIM_TM_RELACION_PASO
   */
  private Long idRelacionPasoOrigen;

  /*
  *Identificador interno de la relación de paso destino del proceso para la transición más reciente. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPasoDestino;
   
  /*
  *Tipo de agrupación de los datos. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_AGRUPACION_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoAgrupadoPor;

  /*
  *Tipo de agrupación para actor de negocio. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTORN_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoActorNegocio;

  /*
  *Tipo de agrupación para característica de la fiscalización. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CARACTERF_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoCaracterFisc;

  /*
  *Código del indicador
  */
  private String coIndicador;

  /*
  *Nombre del indicador
  */
  private String noIndicador;

  /*
  *Descripción del indicador
  */
  private String deIndicador;

  /*
  *Fórmula de cálculo del indicador
  */
  private String deFormula;

  /*
  *Cantidad de meses anteriores para calcular la fecha del horizonte temporal
  */
  private Integer caMesesAtras;
  
  /*
   *url del indicador
   */
  private String deUrlRelativo;

  /*
  *Clave del indicador que permitirá que la PGIM sepa qué lógica de negocio aplicar para la generación de los datos para las mediciones que se creen a partir de este indicador. Debido a ello, la clave deberá ser única en toda la lista de indicadores
  */
  private String coClaveIndicador;

  /*
  *Estado del indicador, los valores posibles son: A = Activo | I = Inactivo y su condición de registro es obligatoria. Por defecto se colocará el valor «Activo». Al respecto se debe considerar que: Indicador en estado «Activo», es elegible para seleccionarse para la creación de nuevas mediciones. Indicador en estado «Inactivo», no es elegible para seleccionarse en la creación de nuevas mediciones.
  */
  private String esIndicador;

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


  /*
   * Descripción del tipo de indicador
   */
  private String descDeTipoIndicador;
  
  
  /*
   * Fecha desde
   */
  private Date descFechaDesde;
  
  
  /*
   * Fecha hasta
   */
  private Date descFechaHasta;

  /*
   * descripción del codigo del tipo agrupador por
   */
  private String descCoTipoAgrupadoPor;
  
  /*
   * Descripción del tipo de unidad de medida
   */
  private String descDeUnidadMedida;

  /*
   * Descripción del estado
   */
  private String descDeEstado;  

  
  /**
   * Descripto del nombre del proceso
   */
  private String descNoProceso;

  /**
   * Unidad de medida del indicador
   */
  private String descNoTipoUnidadMedida;

  /**
   * Descripto del tipo de agrupación para característica de la fiscalización
   */
  private String descNoTipoAgrupadoPor;

  /**
   * Descripto del tipo de agrupación para actor de negocio.
   */
  private String descNoTipoActorNegocio;

  /**
   * Descripto del tipo de agrupación para característica de la fiscalización.
   */
  private String descNoTipoCaracterFisc;

    /**
   * Descriptor del nombre de la relación de paso origen del proceso para la transición más antigua.
   */
  private String descNoRelacionPasoOrigen;

  /**
   * Descriptor del nombre de la relación de paso destino del proceso para la transición más reciente.
   */
  private String descNoRelacionPasoDestino;

  
}