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

import java.util.List;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_INSTRMNTO_X_SUPERV: 
* @descripción: Instrumento de medición por fiscalización
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_INSTRMNTO_X_SUPERV")
@Data
@NoArgsConstructor
public class PgimInstrmntoXSuperv implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del instrumento de medición de la fiscalización. Secuencia: PGIM_SEQ_INSTRMNTO_X_SUPERV
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INSTRMNTO_X_SUPERV")
   @SequenceGenerator(name = "PGIM_SEQ_INSTRMNTO_X_SUPERV", sequenceName = "PGIM_SEQ_INSTRMNTO_X_SUPERV", allocationSize = 1)
   @Column(name = "ID_INSTRMNTO_X_SUPERV", nullable = false)
  private Long idInstrmntoXSuperv;

  /*
  *Identificador interno del tipo de instrumento de medición. Tabla padre: PGIM_TM_TIPO_INSTRUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_INSTRUMENTO", nullable = false)
  private PgimTipoInstrumento pgimTipoInstrumento;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = false)
  private PgimSupervision pgimSupervision;

  /*
  *Estado del instrumento de medición en el marco de una fiscalización. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ESTADO_INSTRUMENTO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESTADO_INSTRUMENTO", nullable = false)
  private PgimValorParametro estadoInstrumento;

  /*
  *Identificador de la instancia de paso de proceso en donde se creó el instrumento. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = true)
  private PgimInstanciaPaso pgimInstanciaPaso;

  /*
  *Identificador del instrumento de la fiscalización que reemplaza al actual instrumento. Tabla padre: PGIM_TD_INSTRMNTO_X_SUPERV. Si tiene valor entonces el estado del instrumento actual debe ser "Reemplazado"
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTRMNTO_X_SUPERV_RMPLZO", nullable = true)
  private PgimInstrmntoXSuperv instrmntoXSupervRmplzo;

  /*
  *Código del instrumento de medición que se utiliza en la fiscalización
  */
   @Column(name = "CO_INSTRUMENTO", nullable = false)
  private String coInstrumento;

  /*
  *Número de serie del instrumento de medición que se utiliza en la fiscalización
  */
   @Column(name = "CO_SERIE_INSTRUMENTO", nullable = false)
  private String coSerieInstrumento;

  /*
  *Marca del instrumento de medición que se utiliza en la fiscalización
  */
   @Column(name = "NO_MARCA_INSTRUMENTO", nullable = false)
  private String noMarcaInstrumento;

  /*
  *Modelo del instrumento de medición que se utiliza en la fiscalización
  */
   @Column(name = "NO_MODELO_INSTRUMENTO", nullable = false)
  private String noModeloInstrumento;

  /*
  *Código del certificado de calibración
  */
   @Column(name = "CO_CERTIFICADO_CALIBRACION", nullable = true)
  private String coCertificadoCalibracion;

  /*
  *Nombre del laboratorio que realizó la calibración
  */
   @Column(name = "NO_LABORATORIO_CALIBRACION", nullable = true)
  private String noLaboratorioCalibracion;

  /*
  *Fecha en la que se realizó la calibración del instrumento
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CALIBRACION", nullable = true)
  private Date feCalibracion;

  /*
  *Fecha de vencimiento de la calibración
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_VENCIMIENTO_CALIBRACION", nullable = true)
  private Date feVencimientoCalibracion;

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

  /*
  *Código del estado del instrumento
  */
 @Transient
  private String descCoEstadoInstrumento;

  /*
  *Nombre del tipo de instrumento
  */
 @Transient
  private String descNoTipoInstrumento;

  /*
  *Nombre del estado del instrumento de medición
  */
 @Transient
  private String descNoEstadoInstrumento;

  /*
  *Lista de parámetros por contrato
  */
 @Transient
  private List<PgimTipopameXContrato> descLPgimTipopameXContrato;

  /*
  *DESC_NO_PASO_PROCESO
  */
 @Transient
  private String descNoPasoProceso;

  /*
  *DESC_NO_INSTRMNTO_X_SUPERV_RMPLZO
  */
 @Transient
  private String descNoInstrmntoXSupervRmplzo;

  /*
  *DESC_NO_INSTRMNTO_X_SUPERV_RMPLZDO
  */
 @Transient
  private String descNoInstrmntoXSupervRmplzdo;

  /*
  *DESC_ID_INSTRMNTO_X_SUPERV_RMPLZDO
  */
 @Transient
  private Long descIdInstrmntoXSupervRmplzdo;


}