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
* Clase Entidad para la tabla PGIM_VW_ACC_MORTAL_AUX: 
* @descripción: Vista para obtener la lista de los accidentes mortales
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_ACC_MORTAL_AUX")
@Data
@NoArgsConstructor
public class PgimAccMortalAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_EVENTO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EVENTO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_EVENTO_AUX", sequenceName = "PGIM_SEQ_EVENTO_AUX", allocationSize = 1)
   @Column(name = "ID_EVENTO_AUX", nullable = false)
  private Long idEventoAux;

  /*
  *ID_EVENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EVENTO", nullable = false)
  private PgimEvento pgimEvento;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *ID_TIPO_EVENTO
  */
   @Column(name = "ID_TIPO_EVENTO", nullable = true)
  private Long idTipoEvento;

  /*
  *NO_TIPO_EVENTO
  */
   @Column(name = "NO_TIPO_EVENTO", nullable = true)
  private String noTipoEvento;

  /*
  *CO_EVENTO
  */
   @Column(name = "CO_EVENTO", nullable = true)
  private String coEvento;

  /*
  *DE_EVENTO
  */
   @Column(name = "DE_EVENTO", nullable = true)
  private String deEvento;

  /*
  *FE_PRESENTACION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PRESENTACION", nullable = true)
  private Date fePresentacion;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *CA_ACCIDENTADOS
  */
   @Column(name = "CA_ACCIDENTADOS", nullable = true)
  private Long caAccidentados;


}