package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TC_INDICADOR: 
* @descripción: Indicador
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 27/09/2023
*/
@Entity
@Table(name = "PGIM_TC_INDICADOR")
@Data
@NoArgsConstructor
public class PgimIndicador implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del indicador. Secuencia: PGIM_SEQ_INDICADOR
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INDICADOR")
   @SequenceGenerator(name = "PGIM_SEQ_INDICADOR", sequenceName = "PGIM_SEQ_INDICADOR", allocationSize = 1)
   @Column(name = "ID_INDICADOR", nullable = false)
  private Long idIndicador;

  /*
  *Tipo de indicador. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_INDICADOR", nullable = false)
  private PgimValorParametro tipoIndicador;

  /*
  *Unidad de medida del indicador. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_UNIDAD_MEDIDA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_UNIDAD_MEDIDA", nullable = true)
  private PgimValorParametro tipoUnidadMedida;

  /*
  *Identificador interno del proceso. Tabla padre: PGIM_TM_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PROCESO", nullable = true)
  private PgimProceso pgimProceso;

  /*
  *Identificador interno de la relación de paso origen del proceso para la transición más antigua. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO_ORIGEN", nullable = true)
  private PgimRelacionPaso relacionPasoOrigen;
  
  /*
  *Identificador interno de la relación de paso destino del proceso para la transición más reciente. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO_DESTINO", nullable = true)
  private PgimRelacionPaso relacionPasoDestino;

  /*
  *Tipo de agrupación de los datos. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_AGRUPACION_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_AGRUPADO_POR", nullable = true)
  private PgimValorParametro tipoAgrupadoPor;

  /*
  *Tipo de agrupación para actor de negocio. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ACTORN_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ACTOR_NEGOCIO", nullable = true)
  private PgimValorParametro tipoActorNegocio;

  /*
  *Tipo de agrupación para característica de la fiscalización. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CARACTERF_INDICADOR. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CARACTER_FISC", nullable = true)
  private PgimValorParametro tipoCaracterFisc;

  /*
  *Código del indicador
  */
   @Column(name = "CO_INDICADOR", nullable = false)
  private String coIndicador;

  /*
  *Nombre del indicador
  */
   @Column(name = "NO_INDICADOR", nullable = false)
  private String noIndicador;

  /*
  *Descripción del indicador
  */
   @Column(name = "DE_INDICADOR", nullable = false)
  private String deIndicador;

  /*
  *Fórmula de cálculo del indicador
  */
   @Column(name = "DE_FORMULA", nullable = false)
  private String deFormula;

  /*
  *Cantidad de meses anteriores para calcular la fecha del horizonte temporal
  */
   @Column(name = "CA_MESES_ATRAS", nullable = true)
  private Integer caMesesAtras;
   
  /*
   *url del indicador
   */
  @Column(name = "DE_URL_RELATIVO", nullable = true)
  private String deUrlRelativo;

  /*
  *Clave del indicador que permitirá que la PGIM sepa qué lógica de negocio aplicar para la generación de los datos para las mediciones que se creen a partir de este indicador. Debido a ello, la clave deberá ser única en toda la lista de indicadores
  */
   @Column(name = "CO_CLAVE_INDICADOR", nullable = true)
  private String coClaveIndicador;

  /*
  *Estado del indicador, los valores posibles son: A = Activo | I = Inactivo y su condición de registro es obligatoria. Por defecto se colocará el valor «Activo». Al respecto se debe considerar que: Indicador en estado «Activo», es elegible para seleccionarse para la creación de nuevas mediciones. Indicador en estado «Inactivo», no es elegible para seleccionarse en la creación de nuevas mediciones.
  */
   @Column(name = "ES_INDICADOR", nullable = false)
  private String esIndicador;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Usuario creador
  */
   @Column(name = "US_CREACION", nullable = false)
  private String usCreacion;

  /*
  *Terminal de creación
  */
   @Column(name = "IP_CREACION", nullable = false)
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = false)
  private Date feCreacion;

  /*
  *Usuario modificador
  */
   @Column(name = "US_ACTUALIZACION", nullable = true)
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
   @Column(name = "IP_ACTUALIZACION", nullable = true)
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ACTUALIZACION", nullable = true)
  private Date feActualizacion;


}