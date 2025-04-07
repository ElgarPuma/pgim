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
* Clase Entidad para la tabla PGIM_TM_TIPO_INSTRUMENTO: 
* @descripción: Tipo de instrumento de medición
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_TIPO_INSTRUMENTO")
@Data
@NoArgsConstructor
public class PgimTipoInstrumento implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador del tipo de instrumento de medición. Secuencia: PGIM_SEQ_TIPO_INSTRUMENTO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPO_INSTRUMENTO")
   @SequenceGenerator(name = "PGIM_SEQ_TIPO_INSTRUMENTO", sequenceName = "PGIM_SEQ_TIPO_INSTRUMENTO", allocationSize = 1)
   @Column(name = "ID_TIPO_INSTRUMENTO", nullable = false)
  private Long idTipoInstrumento;

  /*
  *Identificador interno del tipo de la especialidad. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Código del tipo de instrumento de medición
  */
   @Column(name = "CO_TIPO_INSTRUMENTO", nullable = false)
  private String coTipoInstrumento;

  /*
  *Nombre del tipo de instrumento de medición
  */
   @Column(name = "NO_TIPO_INSTRUMENTO", nullable = false)
  private String noTipoInstrumento;

  /*
  *Descripción del tipo de instrumento de medición
  */
   @Column(name = "DE_TIPO_INSTRUMENTO", nullable = true)
  private String deTipoInstrumento;

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
  *Lista de parámetros por contrato
  */
 @Transient
  private List<PgimTipopameXContrato> descLPgimTipopameXContrato;

  /*
  *Lista de parámetros maestros de medición por tipo de instrumento
  */
 @Transient
  private List<PgimTprmtroXTinstrmnto> descLPgimTipoParametroMed;

  /*
  *DESC_NO_ESPECIALIDAD
  */
 @Transient
  private String descNoEspecialidad;


}