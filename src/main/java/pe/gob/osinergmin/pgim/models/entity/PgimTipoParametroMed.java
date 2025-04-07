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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_TIPO_PARAMETRO_MED: 
* @descripción: Tipo de parámetro de medición
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_TIPO_PARAMETRO_MED")
@Data
@NoArgsConstructor
public class PgimTipoParametroMed implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador del tipo de parámetro de medición. Secuencia: PGIM_SEQ_TIPO_PARAMETRO_MED
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPO_PARAMETRO_MED")
   @SequenceGenerator(name = "PGIM_SEQ_TIPO_PARAMETRO_MED", sequenceName = "PGIM_SEQ_TIPO_PARAMETRO_MED", allocationSize = 1)
   @Column(name = "ID_TIPO_PARAMETRO_MED", nullable = false)
  private Long idTipoParametroMed;

  /*
  *Código del tipo de parámetro de medición
  */
   @Column(name = "CO_TIPO_PARAMETRO", nullable = false)
  private String coTipoParametro;

  /*
  *Nombre del tipo de parámetro de medición
  */
   @Column(name = "NO_TIPO_PARAMETRO", nullable = false)
  private String noTipoParametro;

  /*
  *Descripción del tipo de parámetro de medición
  */
   @Column(name = "DE_TIPO_PARAMETRO", nullable = true)
  private String deTipoParametro;

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
  *DESC_NUM_USOS
  */
 @Transient
  private Long descNumUsos;


}