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

import java.math.BigDecimal;

import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_UNIDAD_MINERA_AUX: 
* @descripción: Vista para obtener la lista de las unidades mineras
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_UNIDAD_MINERA_AUX")
@Data
@NoArgsConstructor
public class PgimUnidadMineraAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad minera auxiliar. Secuencia: PGIM_SEQ_UMINERA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_UMINERA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_UMINERA_AUX", sequenceName = "PGIM_SEQ_UMINERA_AUX", allocationSize = 1)
   @Column(name = "ID_UNIDAD_MINERA", nullable = false)
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
   @Column(name = "NO_RAZON_SOCIAL", nullable = true)
  private String noRazonSocial;

  /*
  *Identificador interno del tipo de actividad
  */
   @Column(name = "ID_TIPO_ACTIVIDAD", nullable = true)
  private Long idTipoActividad;

  /*
  *NO_TIPO_ACTIVIDAD
  */
   @Column(name = "NO_TIPO_ACTIVIDAD", nullable = true)
  private String noTipoActividad;

  /*
  *Identificador interno de la subactividad
  */
   @Column(name = "ID_SUBACTIVIDAD", nullable = true)
  private Long idSubactividad;

  /*
  *Nombre de la subactividad de la UM
  */
   @Column(name = "NO_SUBACTIVIDAD", nullable = true)
  private String noSubactividad;

  /*
  *ID_DIVISION_SUPERVISORA
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISON_SUPERVISORA
  */
   @Column(name = "NO_DIVISON_SUPERVISORA", nullable = true)
  private String noDivisonSupervisora;

  /*
  *ID_SITUACION
  */
   @Column(name = "ID_SITUACION", nullable = true)
  private Long idSituacion;

  /*
  *NO_TIPO_SITUACION
  */
   @Column(name = "NO_TIPO_SITUACION", nullable = true)
  private String noTipoSituacion;

  /*
  *ID_TIPO_UNIDAD_MINERA
  */
   @Column(name = "ID_TIPO_UNIDAD_MINERA", nullable = true)
  private Long idTipoUnidadMinera;

  /*
  *NO_TIPO_UNIDAD_MINERA
  */
   @Column(name = "NO_TIPO_UNIDAD_MINERA", nullable = true)
  private String noTipoUnidadMinera;

  /*
  *Identificador interno del método de minado
  */
   @Column(name = "ID_METODO_MINADO", nullable = true)
  private Long idMetodoMinado;

  /*
  *Nombre del método de minado de la unidad minera
  */
   @Column(name = "NO_METODO_MINADO", nullable = true)
  private String noMetodoMinado;

  /*
  *Identificador interno del mñetodo de la explotación
  */
   @Column(name = "ID_METODO_EXPLOTACION", nullable = true)
  private Long idMetodoExplotacion;

  /*
  *Nombre del método de explotación
  */
   @Column(name = "NO_METODO_EXPLOTACION", nullable = true)
  private String noMetodoExplotacion;

  /*
  *Identificador interno del tipo de yacimiento
  */
   @Column(name = "ID_TIPO_YACIMIENTO", nullable = true)
  private Long idTipoYacimiento;

  /*
  *Nombre del tipo de yacimiento
  */
   @Column(name = "NO_TIPO_YACIMIENTO", nullable = true)
  private String noTipoYacimiento;

  /*
  *Identificador interno de la UM
  */
   @Column(name = "ID_ESTADO_UM", nullable = true)
  private Long idEstadoUm;

  /*
  *Nombre del estado de la UM
  */
   @Column(name = "NO_ESTADO_UM", nullable = true)
  private String noEstadoUm;

  /*
  *NO_UBIGEO
  */
   @Column(name = "NO_UBIGEO", nullable = true)
  private String noUbigeo;

  /*
  *Capacidad instalada de planta expresada en TM/d. Aplica solo cuando el <<Tipo de Unidad Minera>> sea igual a <<Concesión de beneficio>>
  */
   @Column(name = "NU_CPCDAD_INSTLDA_PLANTA", nullable = true)
  private BigDecimal nuCpcdadInstldaPlanta;

  /*
  *ID_TIPO_UNIDAD_MINERA
  */
   @Column(name = "ID_RANKING_RIESGO", nullable = true)
  private Long idRankingRiesgo;

  /*
  *Valor obtenido de la sumatoria de los puntajes técnicos y de gestión
  */
   @Column(name = "MO_PUNTAJE", nullable = true)
  private BigDecimal moPuntaje;

  /*
  *Nombre del ranking de riesgo
  */
   @Column(name = "NO_RANKING", nullable = true)
  private String noRanking;

  /*
  *Fecha de generación del ranking
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_GENERACION_RANKING", nullable = true)
  private Date feGeneracionRanking;

  /*
  *ID_INSTANCIA_PASO_RANKING
  */
   @Column(name = "ID_INSTANCIA_PASO_RANKING", nullable = true)
  private Long idInstanciaPasoRanking;     
   
  /*
  *Código de planta de beneficio destino
  */
   @Column(name = "CO_PLANTA_BENEFICIO_DESTINO", nullable = true)
  private String coPlantaBeneficioDestino;

  /*
  *Nombre de planta de beneficio destino
  */
   @Column(name = "NO_PLANTA_BENEFICIO_DESTINO", nullable = true)
  private String noPlantaBeneficioDestino;

  /*
  *Indicio de cámara subterránea
  */
   @Column(name = "INDICIO_CAMARA_SUBTERRANEA", nullable = true)
  private String indicioCamaraSubterranea;

  /*
  *Profundidad expresada en metros (m)
  */
   @Column(name = "NU_PROFUNDIDAD", nullable = true)
  private BigDecimal nuProfundidad;

  /*
  *Número de altura mínima
  */
   @Column(name = "NU_ALTURA_MINIMA", nullable = true)
  private BigDecimal nuAlturaMinima;

  /*
  *Número de altura máxima
  */
   @Column(name = "NU_ALTURA_MAXIMA", nullable = true)
  private BigDecimal nuAlturaMaxima;

  /*
  *Nombre de minerales o sustancias
  */
   @Column(name = "MINERALES_SUSTANCIAS", nullable = true)
  private String mineralesSustancias;

  /*
  *Requiere datos de riesgos
  */
   @Column(name = "REQUIERE_DATOS_RIESGO", nullable = true)
  private String requiereDatosRiesgo;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
 @Transient
  private Long descIdLineaPrograma;

  /*
  *DESC_MO_PUNTAJE
  */
 @Transient
  private String descMoPuntaje;

  /*
  *DESC_ID_RANKING_RIESGO
  */
 @Transient
  private Long descIdRankingRiesgo;


}