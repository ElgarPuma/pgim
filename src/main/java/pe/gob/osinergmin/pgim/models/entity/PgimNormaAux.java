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
* Clase Entidad para la tabla PGIM_VW_NORMA_AUX: 
* @descripción: Vista auxiliar para la consulta y visualización de las normas
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_NORMA_AUX")
@Data
@NoArgsConstructor
public class PgimNormaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la norma legal. Secuencia: PGIM_SEQ_NORMA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NORMA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_NORMA_AUX", sequenceName = "PGIM_SEQ_NORMA_AUX", allocationSize = 1)
   @Column(name = "ID_NORMA_AUX", nullable = false)
  private Long idNormaAux;

  /*
  *Identificador interno de la norma
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA", nullable = true)
  private PgimNorma pgimNorma;

  /*
  *Nombre corto de la norma legal
  */
   @Column(name = "NO_CORTO_NORMA", nullable = true)
  private String noCortoNorma;

  /*
  *Nombre de la norma legal
  */
   @Column(name = "NO_NORMA", nullable = true)
  private String noNorma;

  /*
  *Flag que indica si la norma se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
   @Column(name = "FL_VIGENTE", nullable = true)
  private String flVigente;

  /*
  *Fecha de la publicación de la norma
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PUBLICACION", nullable = true)
  private Date fePublicacion;

  /*
  *Tipo de norma
  */
   @Column(name = "ID_TIPO_NORMA", nullable = true)
  private Long idTipoNorma;

  /*
  *Nombre del tipo de norma
  */
   @Column(name = "NO_TIPO_NORMA", nullable = true)
  private String noTipoNorma;


}