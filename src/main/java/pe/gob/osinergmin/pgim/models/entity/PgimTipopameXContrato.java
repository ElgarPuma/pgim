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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_TIPOPAME_X_CONTRATO: 
* @descripción: Tipo de parámetro de medición por contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_TIPOPAME_X_CONTRATO")
@Data
@NoArgsConstructor
public class PgimTipopameXContrato implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del tipo parámetro de medición por contrato. Secuencia: PGIM_SEQ_TIPOPAME_X_CONTRATO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPOPAME_X_CONTRATO")
   @SequenceGenerator(name = "PGIM_SEQ_TIPOPAME_X_CONTRATO", sequenceName = "PGIM_SEQ_TIPOPAME_X_CONTRATO", allocationSize = 1)
   @Column(name = "ID_TIPOPAME_X_CONTRATO", nullable = false)
  private Long idTipopameXContrato;

  /*
  *Identificador interno del tipo de parámetro por tipo de instrumento. Tabla padre: PGIM_TM_TPRMTRO_X_TINSTRMNTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TPRMTRO_X_TINSTRMNTO", nullable = false)
  private PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmnto;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = false)
  private PgimContrato pgimContrato;

  /*
  *Rango de medición del tipo de parámetro por instrumento de medición
  */
   @Column(name = "DE_RANGO_MEDICION", nullable = false)
  private String deRangoMedicion;

  /*
  *Descripción de la precisión del tipo de instrumento de medición respecto al tipo de parámetro
  */
   @Column(name = "DE_PRECISION", nullable = true)
  private String dePrecision;

  /*
  *Descripción de la exactitud del tipo de instrumento de medición respecto al tipo de parámetro
  */
   @Column(name = "DE_EXACTITUD", nullable = true)
  private String deExactitud;

  /*
  *Descripción de la resolución del tipo de instrumento de medición respecto al tipo de parámetro
  */
   @Column(name = "DE_RESOLUCION", nullable = true)
  private String deResolucion;

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
  *Identificador interno del tipo de instrumento de medición
  */
 @Transient
  private Long descIdTipoInstrumento;

  /*
  *Código del tipo de instrumento
  */
 @Transient
  private String descCoTipoInstrumento;

  /*
  *Nombre del tipo de instrumento
  */
 @Transient
  private String descNoTipoInstrumento;

  /*
  *Nombre del tipo de parámetro
  */
 @Transient
  private String descCoTipoParametro;

  /*
  *Nombre del tipo de parámetro
  */
 @Transient
  private String descNoTipoParametro;


}