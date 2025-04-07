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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_ESTRATO: 
* @descripción: Estrato de la gestión de la información minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_ESTRATO")
@Data
@NoArgsConstructor
public class PgimEstrato implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del estrato. Secuencia: PGIM_SEQ_ESTRATO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ESTRATO")
   @SequenceGenerator(name = "PGIM_SEQ_ESTRATO", sequenceName = "PGIM_SEQ_ESTRATO", allocationSize = 1)
   @Column(name = "ID_ESTRATO", nullable = false)
  private Long idEstrato;

  /*
  *Nombre del estrato
  */
   @Column(name = "NO_ESTRATO", nullable = false)
  private String noEstrato;

  /*
  *Nombre corto del estrato
  */
   @Column(name = "NO_CORTO", nullable = false)
  private String noCorto;

  /*
  *Capacidad productiva para el estrato.
  */
   @Column(name = "NU_CAPACIDAD_PRODUCTIVA", nullable = false)
  private BigDecimal nuCapacidadProductiva;

  /*
  *Capacidad de beneficio para el estrato.
  */
   @Column(name = "NU_CAPACIDAD_BENEFICIO", nullable = false)
  private BigDecimal nuCapacidadBeneficio;

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