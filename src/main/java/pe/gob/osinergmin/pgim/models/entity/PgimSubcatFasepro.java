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
* Clase Entidad para la tabla PGIM_TM_SUBCAT_FASEPRO: 
* @descripción: Subcategoría por fase de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_SUBCAT_FASEPRO")
@Data
@NoArgsConstructor
public class PgimSubcatFasepro implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la fase de proceso PGIM. Secuencia: PGIM_SEQ_SUBCAT_FASEPRO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUBCAT_FASEPRO")
   @SequenceGenerator(name = "PGIM_SEQ_SUBCAT_FASEPRO", sequenceName = "PGIM_SEQ_SUBCAT_FASEPRO", allocationSize = 1)
   @Column(name = "ID_SUBCAT_FASEPRO", nullable = false)
  private Long idSubcatFasepro;

  /*
  *Identificador interno de la fase del proceso. Tabla padre: PGIM_TM_FASE_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_FASE_PROCESO", nullable = false)
  private PgimFaseProceso pgimFaseProceso;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

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