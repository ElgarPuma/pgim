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
* Clase Entidad para la tabla PGIM_TC_MEDIDA_ADM: 
* @descripción: Medidas administrativas emitidas en los procesos de supervisión y fiscalización
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_MEDIDA_ADM")
@Data
@NoArgsConstructor
public class PgimMedidaAdm implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la medida administrativa. Secuencia: PGIM_SEQ_MEDIDA_ADM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MEDIDA_ADM")
   @SequenceGenerator(name = "PGIM_SEQ_MEDIDA_ADM", sequenceName = "PGIM_SEQ_MEDIDA_ADM", allocationSize = 1)
   @Column(name = "ID_MEDIDA_ADMINISTRATIVA", nullable = false)
  private Long idMedidaAdministrativa;

  /*
  *Tipo de medida administrativa. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_MEDIDA_ADMINISTRATIVA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_MEDIDA_ADMINISTRATIVA", nullable = false)
  private PgimValorParametro tipoMedidaAdministrativa;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUPERVISION", nullable = true)
  private PgimSupervision pgimSupervision;

  /*
  *Identificador interno deL PAS. Tabla padre: PGIM_TC_PAS
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PAS", nullable = true)
  private PgimPas pgimPas;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = true)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Tipo de objeto relacionado a la medidad administrativa. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_OBJ_RELACIONADO_MED_ADM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_OBJETO", nullable = true)
  private PgimValorParametro tipoObjeto;

  /*
  *Código de la medida administrativa
  */
   @Column(name = "CO_MEDIDA_ADMINISTRATIVA", nullable = false)
  private String coMedidaAdministrativa;

  /*
  *Descripción de la medida administrativa
  */
   @Column(name = "DE_MEDIDA_ADMINISTRATIVA", nullable = true)
  private String deMedidaAdministrativa;

  /*
  *Fecha de la medida administrativa
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_MEDIDA_ADMINISTRATIVA", nullable = false)
  private Date feMedidaAdministrativa;

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
  *DESC_NU_EXPEDIENTE_SIGED
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *DESC_NO_TIPO_MEDIDA_ADMINISTRATIVA
  */
 @Transient
  private String descNoTipoMedidaAdministrativa;

  /*
  *DESC_NO_TIPO_OBJETO
  */
 @Transient
  private String descNoTipoObjeto;

  /*
  *DESC_NO_UNIDAD_MINERA
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *DESC_CO_SUPERVISION
  */
 @Transient
  private String descCoSupervision;

  /*
  *DESC_CO_SUPERVISION_PAS
  */
 @Transient
  private String descCoSupervisionPas;

  /*
  *DESC_FLAG_MIS_ASIGNACIONES
  */
 @Transient
  private String descFlagMisAsignaciones;

  /*
  *DESC_NO_PERSONA_ASIGNADA
  */
 @Transient
  private String descNoPersonaAsignada;

  /*
  *DESC_USUARIO_ASIGNADO
  */
 @Transient
  private String descUsuarioAsignado;


}