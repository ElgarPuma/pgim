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

import javax.persistence.Lob;
import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_FICHA_OBSERVACION: 
* @descripción: Detalle de las observaciones asociadas a una ficha de observaciones al informe de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_FICHA_OBSERVACION")
@Data
@NoArgsConstructor
public class PgimFichaObservacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la observación específica en la ficha del tipo observaciones. Secuencia: PGIM_SEQ_FICHA_OBSERVACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FICHA_OBSERVACION")
   @SequenceGenerator(name = "PGIM_SEQ_FICHA_OBSERVACION", sequenceName = "PGIM_SEQ_FICHA_OBSERVACION", allocationSize = 1)
   @Column(name = "ID_FICHA_OBSERVACION", nullable = false)
  private Long idFichaObservacion;

  /*
  *Identificador interno del documento ficha de revisión. Tabla padre: PGIM_TD_FICHA_REVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FICHA_REVISION", nullable = false)
  private PgimFichaRevision pgimFichaRevision;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OBSERVACION_FICHA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_OBSERVACION_FICHA", nullable = false)
  private PgimValorParametro tipoObservacionFicha;

  /*
  *Identificador interno del ítem de observación previo que no fue subsanado en la ficha de revisión previa. Tabla padre: PGIM_TD_FICHA_OBSERVACION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FICHA_OBSERVACION_ORIGEN", nullable = true)
  private PgimFichaObservacion fichaObservacionOrigen;

  /*
  *Fecha de revisión del informe de supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_REVISION_FICHA", nullable = false)
  private Date feRevisionFicha;

  /*
  *Cantidad de días para realizar la presentación del informe de supervisión
  */
   @Column(name = "CA_DIAS_PARA_PRESENTACION", nullable = false)
  private Integer caDiasParaPresentacion;

  /*
  *Fecha inicial del conteo de la cantidad de dias para la presentación del informe de supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_DESDE_PARA_PRESENTACION", nullable = false)
  private Date feDesdeParaPresentacion;

  /*
  *Fecha de presentación del informe de supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PRESENTACION", nullable = false)
  private Date fePresentacion;

  /*
  *Parte del informe que se está observando
  */
   @Column(name = "DE_PARTE_INFORME_OBSERVADA_T", nullable = true)
@Lob
  private String deParteInformeObservadaT;

  /*
  *Ítem descriptivo de la observación realizada
  */
   @Column(name = "DE_ITEM_OBSERVACION_T", nullable = true)
@Lob
  private String deItemObservacionT;

  /*
  *Flag que indica si la observación fue subsanada o no. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_SUBSANADA", nullable = false)
  private String flSubsanada;

  /*
  *Comentario del ítem de la observación
  */
   @Column(name = "CM_ITEM_OBSERVACION_T", nullable = true)
@Lob
  private String cmItemObservacionT;

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
  *Tipo de observación de la ficha
  */
 @Transient
  private String descTipoObservacionFicha;

  /*
  *Condición lógica de la subsanación
  */
 @Transient
  private boolean descChkSubsanada;


}