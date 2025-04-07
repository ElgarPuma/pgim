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
* Clase Entidad para la tabla PGIM_TM_REPORTE: 
* @descripción: Mantenimiento de información de reportes
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_REPORTE")
@Data
@NoArgsConstructor
public class PgimReporte implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del reporte. Secuencia: PGIM_SEQ_REPORTE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_REPORTE")
   @SequenceGenerator(name = "PGIM_SEQ_REPORTE", sequenceName = "PGIM_SEQ_REPORTE", allocationSize = 1)
   @Column(name = "ID_REPORTE", nullable = false)
  private Long idReporte;

  /*
  *Código del reporte
  */
   @Column(name = "CO_REPORTE", nullable = false)
  private String coReporte;

  /*
  *Título del reporte
  */
   @Column(name = "DE_TITULO", nullable = false)
  private String deTitulo;

  /*
  *Descripción del reporte
  */
   @Column(name = "DE_REPORTE", nullable = false)
  private String deReporte;

  /*
  *Tipo de reporte. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_REPORTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_REPORTE", nullable = false)
  private PgimValorParametro tipoReporte;

  /*
  *Objetivo del reporte
  */
   @Column(name = "DE_OBJETIVO", nullable = false)
  private String deObjetivo;

  /*
  *url del reporte
  */
   @Column(name = "DE_URL_RELATIVO", nullable = false)
  private String deUrlRelativo;

  /*
  *Materia del reporte. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = MATERIA_REPORTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MATERIA", nullable = false)
  private PgimValorParametro materia;

  /*
  *Grupo de reporte. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = GRUPO_REPORTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_GRUPO_REPORTE", nullable = false)
  private PgimValorParametro grupoReporte;

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
  *Nombre del tipo de reporte
  */
 @Transient
  private String descNoTipoReporte;

  /*
  *Nombre de la materia
  */
 @Transient
  private String descNoMateria;

  /*
  *Nombre del grupo de reportes
  */
 @Transient
  private String descNoGrupoReporte;


}