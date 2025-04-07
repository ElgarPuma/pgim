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
* Clase Entidad para la tabla PGIM_TM_MTDO_EXPLOTACION: 
* @descripción: Método de explotación de las unidades mineras
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_MTDO_EXPLOTACION")
@Data
@NoArgsConstructor
public class PgimMtdoExplotacion implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del método de explotación de las unidades mineras. Secuencia: PGIM_SEQ_MTDO_EXPLOTACION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MTDO_EXPLOTACION")
   @SequenceGenerator(name = "PGIM_SEQ_MTDO_EXPLOTACION", sequenceName = "PGIM_SEQ_MTDO_EXPLOTACION", allocationSize = 1)
   @Column(name = "ID_METODO_EXPLOTACION", nullable = false)
  private Long idMetodoExplotacion;

  /*
  *Nombre del método de explotación
  */
   @Column(name = "NO_METODO_EXPLOTACION", nullable = false)
  private String noMetodoExplotacion;

  /*
  *Método de minado de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = METODO_MINADO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_METODO_MINADO", nullable = false)
  private PgimValorParametro metodoMinado;

  /*
  *Alias del método de explotación
  */
   @Column(name = "DE_METODO_EXPLOTACION", nullable = true)
  private String deMetodoExplotacion;

  /*
  *Estado del registro (1=Activo, 0=Inactivo)
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