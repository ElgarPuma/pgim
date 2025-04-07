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
* Clase Entidad para la tabla PGIM_TD_PRMTRO_X_SUPERV: 
* @descripción: Parámetro de medición por fiscalización
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_PRMTRO_X_SUPERV")
@Data
@NoArgsConstructor
public class PgimPrmtroXSuperv implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del parámetro de medición por fiscalización. Secuencia: PGIM_SEQ_PRMTRO_X_SUPERV
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PRMTRO_X_SUPERV")
   @SequenceGenerator(name = "PGIM_SEQ_PRMTRO_X_SUPERV", sequenceName = "PGIM_SEQ_PRMTRO_X_SUPERV", allocationSize = 1)
   @Column(name = "ID_PRMTRO_X_SUPERV", nullable = false)
  private Long idPrmtroXSuperv;

  /*
  *Identificador interno del tipo de instrumento de medición por fiscalización. Tabla padre: PGIM_TD_INSTRMNTO_X_SUPERV
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTRMNTO_X_SUPERV", nullable = false)
  private PgimInstrmntoXSuperv pgimInstrmntoXSuperv;

  /*
  *Identificador interno del tipo parámetro de medición por contrato. Tabla padre: PGIM_TD_TIPOPAME_X_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPOPAME_X_CONTRATO", nullable = false)
  private PgimTipopameXContrato pgimTipopameXContrato;

  /*
  *Rango de medición del tipo de parámetro por instrumento de medición. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_RANGO_MEDICION
  */
   @Column(name = "DE_RANGO_MEDICION", nullable = false)
  private String deRangoMedicion;

  /*
  *Descripción de la precisión del tipo de instrumento de medición respecto al tipo de parámetro. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_PRECISION
  */
   @Column(name = "DE_PRECISION", nullable = true)
  private String dePrecision;

  /*
  *Descripción de la exactitud del tipo de instrumento de medición respecto al tipo de parámetro. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_EXACTITUD
  */
   @Column(name = "DE_EXACTITUD", nullable = true)
  private String deExactitud;

  /*
  *Descripción de la resolución del tipo de instrumento de medición respecto al tipo de parámetro. Copiado inicialmente de la tabla PGIM_TD_TIPOPAME_X_CONTRATO.DE_RESOLUCION
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
  *Nombre del parámetro por tipo de instrumento
  */
 @Transient
  private String descNoTprmtroXTinstrmnto;


}