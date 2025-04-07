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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_INSTANCIA_SE_DOC: 
* @descripción: Correlativo de documentos por instancia de proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_INSTANCIA_SE_DOC")
@Data
@NoArgsConstructor
public class PgimInstanciaSeDoc implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la instancia del proceso PGIM. Secuencia: PGIM_SEQ_INSTANCIA_SE_DOC
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INSTANCIA_SE_DOC")
   @SequenceGenerator(name = "PGIM_SEQ_INSTANCIA_SE_DOC", sequenceName = "PGIM_SEQ_INSTANCIA_SE_DOC", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_SE_DOC", nullable = false)
  private Long idInstanciaSeDoc;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la subcategoría documental. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

  /*
  *Número del último número de documento usado para la numeración de documentos de la subcategoría documental.
  */
   @Column(name = "SE_CORRELATIVO", nullable = false)
  private Long seCorrelativo;

  /*
  *Número que hace referencia al correlativo de la instancia padre (contrato) usado para la numeración de documentos de la subcategoría documental.
  */
   @Column(name = "SE_CORRELATIVO_INSTANC_PADRE", nullable = false)
  private Long seCorrelativoInstancPadre;

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
  *Nombre de tabla de la instancia
  */
 @Transient
  private String descNoTablaInstancia;

  /*
  *Identificador del contrato relacionado con la instancia de proceso
  */
 @Transient
  private Long descIdContrato;

  /*
  *Identificador de la especialidad relacionada con la instancia de proceso
  */
 @Transient
  private Long descIdEspecialidad;


}