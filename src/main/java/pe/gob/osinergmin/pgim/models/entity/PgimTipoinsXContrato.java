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
* Clase Entidad para la tabla PGIM_TD_TIPOINS_X_CONTRATO: 
* @descripción: Tipo de instrumento de medición por contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 05/08/2022
*/
@Entity
@Table(name = "PGIM_TD_TIPOINS_X_CONTRATO")
@Data
@NoArgsConstructor
public class PgimTipoinsXContrato implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del tipo de instrumento de medición por contrato. Secuencia: PGIM_SEQ_TIPOINS_X_CONTRATO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPOINS_X_CONTRATO")
   @SequenceGenerator(name = "PGIM_SEQ_TIPOINS_X_CONTRATO", sequenceName = "PGIM_SEQ_TIPOINS_X_CONTRATO", allocationSize = 1)
   @Column(name = "ID_TIPOINS_X_CONTRATO", nullable = false)
  private Long idTipoinsXContrato;

  /*
  *Identificador del tipo de instrumento de medición. Tabla padre: PGIM_TM_TIPO_INSTRUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_INSTRUMENTO", nullable = false)
  private PgimTipoInstrumento pgimTipoInstrumento;

  /*
  *Identificador interno del contrato de supervisión. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = false)
  private PgimContrato pgimContrato;

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