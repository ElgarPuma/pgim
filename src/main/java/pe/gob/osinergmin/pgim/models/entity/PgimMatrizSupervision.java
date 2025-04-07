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
* Clase Entidad para la tabla PGIM_TM_MATRIZ_SUPERVISION: 
* @descripción: Matriz de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_MATRIZ_SUPERVISION")
@Data
@NoArgsConstructor
public class PgimMatrizSupervision implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la matriz de supervisión. Secuencia: PGIM_SEQ_MATRIZ_SUPERVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MATRIZ_SUPERVISION")
   @SequenceGenerator(name = "PGIM_SEQ_MATRIZ_SUPERVISION", sequenceName = "PGIM_SEQ_MATRIZ_SUPERVISION", allocationSize = 1)
   @Column(name = "ID_MATRIZ_SUPERVISION", nullable = false)
  private Long idMatrizSupervision;

  /*
  *Identificador interno de la matriz de supervisión orgein desde la que se copió la presente matriz. Tabla padre: PGIM_TM_MATRIZ_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MATRIZ_SUPERVISION_ORIGEN", nullable = true)
  private PgimMatrizSupervision pgimMatrizSupervisionOrigen;

  /*
  *Identificador interno de la instancia del proceso PGIM. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURACION_BASE", nullable = true)
  private PgimConfiguracionBase pgimConfiguracionBase;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Descripción de la matriz de supervisión
  */
   @Column(name = "DE_MATRIZ_SUPERVISION", nullable = true)
  private String deMatrizSupervision;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Indicador lógico que determina si el cuadro de verificación se encuentra activa o no
  */
  @Column(name = "FL_ACTIVO", nullable = false)
  private String flActivo;

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
  *Nombre de la especialidad
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *Nombre de la configuración base
  */
 @Transient
  private String descNoConfiguracionBase;

  /*
  *Identificador interno de la especialidad
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *Identificador interno del tipo de configuración base
  */
 @Transient
  private Long descIdTipoConfiguracionBase;

  /*
  *Descripción de la configuración basen
  */
 @Transient
  private String descDeConfiguracionBase;


}