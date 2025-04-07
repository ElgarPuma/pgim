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
* Clase Entidad para la tabla PGIM_VW_PAS_RPT_AUX: 
* @descripción: Vista para obtener la lista de PAS para reporte
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_PAS_RPT_AUX")
@Data
@NoArgsConstructor
public class PgimPasRptAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_ID_PAS_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ID_PAS_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ID_PAS_AUX", sequenceName = "PGIM_SEQ_ID_PAS_AUX", allocationSize = 1)
   @Column(name = "ID_PAS_AUX", nullable = false)
  private Long idPasAux;

  /*
  *ID_PAS
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PAS", nullable = true)
  private PgimPas pgimPas;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *ID_SUPERVISION
  */
   @Column(name = "ID_SUPERVISION", nullable = true)
  private Long idSupervision;

  /*
  *CO_SUPERVISION
  */
   @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

  /*
  *ID_TIPO_SUPERVISION
  */
   @Column(name = "ID_TIPO_SUPERVISION", nullable = true)
  private Long idTipoSupervision;

  /*
  *NO_TIPO_SUPERVISION
  */
   @Column(name = "NO_TIPO_SUPERVISION", nullable = true)
  private String noTipoSupervision;

  /*
  *ID_SUBTIPO_SUPERVISION
  */
   @Column(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
  private Long idSubtipoSupervision;

  /*
  *DE_SUBTIPO_SUPERVISION
  */
   @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = true)
  private String deSubtipoSupervision;

  /*
  *FE_INICIO_SUPERVISION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION", nullable = true)
  private Date feInicioSupervision;

  /*
  *FE_FIN_SUPERVISION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION", nullable = true)
  private Date feFinSupervision;

  /*
  *FE_INICIO_SUPERVISION_REAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
  private Date feInicioSupervisionReal;

  /*
  *FE_FIN_SUPERVISION_REAL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
  private Date feFinSupervisionReal;

  /*
  *ETIQUETA_PASO_ACTUAL
  */
   @Column(name = "ETIQUETA_PASO_ACTUAL", nullable = true)
  private String etiquetaPasoActual;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *FE_CREACION_PAS
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION_PAS", nullable = true)
  private Date feCreacionPas;


}