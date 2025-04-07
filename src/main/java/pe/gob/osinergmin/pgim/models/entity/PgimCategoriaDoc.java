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
* Clase Entidad para la tabla PGIM_TM_CATEGORIA_DOC: 
* @descripción: Categoría de documento
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CATEGORIA_DOC")
@Data
@NoArgsConstructor
public class PgimCategoriaDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de categoría de documento. Secuencia: PGIM_SEQ_CATEGORIA_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CATEGORIA_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_CATEGORIA_DOC", sequenceName = "PGIM_SEQ_CATEGORIA_DOC", allocationSize = 1)
   @Column(name = "ID_CATEGORIA_DOCUMENTO", nullable = false)
  private Long idCategoriaDocumento;

  /*
  *Código de la categoría de documento
  */
   @Column(name = "CO_CATEGORIA_DOCUMENTO", nullable = false)
  private String coCategoriaDocumento;

  /*
  *Nombre de la categoría de documento
  */
   @Column(name = "NO_CATEGORIA_DOCUMENTO", nullable = false)
  private String noCategoriaDocumento;

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
  *DESC_ID_SUBCAT_DOCUMENTO
  */
 @Transient
  private Long descIdSubcatDocumento;

  /*
  *DESC_CO_SUBCAT_DOCUMENTO
  */
 @Transient
  private String descCoSubcatDocumento;

  /*
  *DESC_NO_SUBCAT_DOCUMENTO
  */
 @Transient
  private String descNoSubcatDocumento;

  /*
  *DESC_ID_SUBCATEGORIA_PLANTI
  */
 @Transient
  private Long descIdSubcategoriaPlanti;

  /*
  *DESC_NO_PLANTILLA
  */
 @Transient
  private String descNoPlantilla;


}