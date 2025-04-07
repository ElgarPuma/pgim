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
* Clase Entidad para la tabla PGIM_TM_NORMA: 
* @descripción: Norma Legal
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_NORMA")
@Data
@NoArgsConstructor
public class PgimNorma implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la norma legal. Secuencia: PGIM_SEQ_NORMA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NORMA")
   @SequenceGenerator(name = "PGIM_SEQ_NORMA", sequenceName = "PGIM_SEQ_NORMA", allocationSize = 1)
   @Column(name = "ID_NORMA", nullable = false)
  private Long idNorma;

  /*
  *Tipo de norma. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_NORMA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_NORMA", nullable = false)
  private PgimValorParametro tipoNorma;

  /*
  *Nombre corto de la norma legal
  */
   @Column(name = "NO_CORTO_NORMA", nullable = false)
  private String noCortoNorma;

  /*
  *Nombre de la norma legal
  */
   @Column(name = "NO_NORMA", nullable = false)
  private String noNorma;

  /*
  *Fecha de la publicación de la norma
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PUBLICACION", nullable = true)
  private Date fePublicacion;

  /*
  *Flag que indica si la norma se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_VIGENTE", nullable = true)
  private String flVigente;

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