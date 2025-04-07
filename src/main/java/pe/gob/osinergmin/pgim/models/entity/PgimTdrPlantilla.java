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

import javax.persistence.Lob;
import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_TDR_PLANTILLA: 
* @descripción: Plantilla de metadatos para las supervisiones
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_TDR_PLANTILLA")
@Data
@NoArgsConstructor
public class PgimTdrPlantilla implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la plantilla de metadatos de los TDR para las supervisiones. Secuencia: PGIM_SEQ_TDR_PLANTILLA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TDR_PLANTILLA")
   @SequenceGenerator(name = "PGIM_SEQ_TDR_PLANTILLA", sequenceName = "PGIM_SEQ_TDR_PLANTILLA", allocationSize = 1)
   @Column(name = "ID_TDR_PLANTILLA", nullable = false)
  private Long idTdrPlantilla;

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
  *Objetivo del TDR
  */
   @Column(name = "DE_TDR_OBJETIVO_TEXTO", nullable = true)
@Lob
  private String deTdrObjetivoTexto;

  /*
  *Alcance del servicio requerido en el TDR
  */
   @Column(name = "DE_TDR_ALCANCE_TEXTO", nullable = true)
@Lob
  private String deTdrAlcanceTexto;

  /*
  *Metodología del servicio definido para el TDR
  */
   @Column(name = "DE_TDR_METODOLOGIA_TEXTO", nullable = true)
@Lob
  private String deTdrMetodologiaTexto;

  /*
  *Informe de supervisión definido para el TDR
  */
   @Column(name = "DE_TDR_INFORME_SUPERV_TEXTO", nullable = true)
@Lob
  private String deTdrInformeSupervTexto;

  /*
  *Honorarios profesionales definidos para el TDR
  */
   @Column(name = "DE_TDR_HONORARIOS_PROF", nullable = true)
  private String deTdrHonorariosProf;

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
  *DESC_ID_ESPECIALIDAD
  */
 @Transient
  private Long descIdEspecialidad;

  /*
  *DESC_NO_ESPECIALIDAD
  */
 @Transient
  private String descNoEspecialidad;

  /*
  *DESC_NO_CONFIGURACION_BASE
  */
 @Transient
  private String descNoConfiguracionBase;

  /*
  *DESC_ID_TIPO_CONFIGURACION_BASE
  */
 @Transient
  private Long descIdTipoConfiguracionBase;

  /*
  *DESC_DE_CONFIGURACION_BASE
  */
 @Transient
  private String descDeConfiguracionBase;


}