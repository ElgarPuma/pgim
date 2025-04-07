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
* Clase Entidad para la tabla PGIM_TD_BIBLIO_ENTIDAD: 
* @descripción: Documento por entidad de negocio
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 07/12/2023
*/
@Entity
@Table(name = "PGIM_TD_BIBLIO_ENTIDAD")
@Data
@NoArgsConstructor
public class PgimBiblioEntidad implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento por entidad de negocio. Secuencia: PGIM_SEQ_BIBLIO_ENTIDAD
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_BIBLIO_ENTIDAD")
   @SequenceGenerator(name = "PGIM_SEQ_BIBLIO_ENTIDAD", sequenceName = "PGIM_SEQ_BIBLIO_ENTIDAD", allocationSize = 1)
   @Column(name = "ID_BIBLIO_ENTIDAD", nullable = false)
  private Long idBiblioEntidad;

  /*
  *Identificador interno de la entidad de negocio. Tabla padre: PGIM_TD_BIBLIO_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ENTIDAD_NEGOCIO", nullable = false)
  private PgimEntidadNegocio pgimEntidadNegocio;

  /*
  *Identificador interno del documento bibliográfico. Tabla padre: PGIM_TD_BIBLIO_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_BIBLIO_DOCUMENTO", nullable = false)
  private PgimBiblioDocumento pgimBiblioDocumento;

  /*
  *Identificador interno del registro de la entidad de negocio asociado al documento bibliográfico. Para conocer a qué tabla corresponde este registro se debe consultar en la columna respectiva de la relación PGIM_TD_BIBLIO_ENTIDAD.NO_TABLA_ENTIDAD_NEGOCIO
  */
   @Column(name = "ID_REGISTRO_ENTIDAD_NEGOCIO", nullable = false)
  private Long idRegistroEntidadNegocio;

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