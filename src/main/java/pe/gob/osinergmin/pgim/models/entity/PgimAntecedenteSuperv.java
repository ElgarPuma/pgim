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
* Clase Entidad para la tabla PGIM_TC_ANTECEDENTE_SUPERV: 
* @descripción: Antecedentes de la supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_ANTECEDENTE_SUPERV")
@Data
@NoArgsConstructor
public class PgimAntecedenteSuperv implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del antecedente de la supervisión. Secuencia: PGIM_SEQ_ANTECEDENTE_SUPERV
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ANTECEDENTE_SUPERV")
   @SequenceGenerator(name = "PGIM_SEQ_ANTECEDENTE_SUPERV", sequenceName = "PGIM_SEQ_ANTECEDENTE_SUPERV", allocationSize = 1)
   @Column(name = "ID_ANTECEDENTE_SUPERV", nullable = false)
  private Long idAntecedenteSuperv;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno del documento relacionado con el entregable. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento pgimDocumento;

  /*
  *Aspectos revisados por el fiscalizador
  */
   @Column(name = "DE_ASPECTOS_REVISADOS", nullable = true)
  private String deAspectosRevisados;

  /*
  *Tipo de antecedente. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ANTECEDENTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ANTECEDENTE", nullable = false)
  private PgimValorParametro tipoAntecedente;

  /*
  *Tipo de supervisión. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_SUPERVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUPERVISION", nullable = true)
  private PgimValorParametro tipoSupervision;

  /*
  *Número del expediente Siged asociado al evento
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *Fecha real de inicio de la supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
  private Date feInicioSupervisionReal;

  /*
  *Fecha real fin de la supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
  private Date feFinSupervisionReal;

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