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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_TARIFARIO_CONTRATO: 
* @descripción: Tarifario del contrato
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_TARIFARIO_CONTRATO")
@Data
@NoArgsConstructor
public class PgimTarifarioContrato implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del tarifario del contrato. Secuencia: PGIM_SEQ_TARIFARIO_CONTRATO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TARIFARIO_CONTRATO")
   @SequenceGenerator(name = "PGIM_SEQ_TARIFARIO_CONTRATO", sequenceName = "PGIM_SEQ_TARIFARIO_CONTRATO", allocationSize = 1)
   @Column(name = "ID_TARIFARIO_CONTRATO", nullable = false)
  private Long idTarifarioContrato;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONTRATO", nullable = true)
  private PgimContrato pgimContrato;

  /*
  *Nombre del tarifario del contrato
  */
   @Column(name = "NO_TARIFARIO", nullable = false)
  private String noTarifario;

  /*
  *Monto en caso de supervisión fallida
  */
   @Column(name = "MO_SUPERVISION_FALLIDA", nullable = false)
  private BigDecimal moSupervisionFallida;

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