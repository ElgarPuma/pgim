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
* Clase Entidad para la tabla PGIM_TD_FICHA_REVISION: 
* @descripción: Documento de la ficha de revisión de informe de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TD_FICHA_REVISION")
@Data
@NoArgsConstructor
public class PgimFichaRevision implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento ficha de revisión. Secuencia: PGIM_SEQ_FICHA_REVISION
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FICHA_REVISION")
   @SequenceGenerator(name = "PGIM_SEQ_FICHA_REVISION", sequenceName = "PGIM_SEQ_FICHA_REVISION", allocationSize = 1)
   @Column(name = "ID_FICHA_REVISION", nullable = false)
  private Long idFichaRevision;

  /*
  *Identificador interno del documento de conformidad. Tabla padre: PGIM_TD_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = false)
  private PgimDocumento pgimDocumento;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_FICHA_REVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CONFORMIDAD", nullable = false)
  private PgimValorParametro tipoConformidad;

  /*
  *Fecha de revisión del informe de fiscalización.
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_REVISION_FICHA", nullable = false)
  private Date feRevisionFicha;

  /*
  *Plazo end días para realizar la presentación del informe de supervisión
  */
   @Column(name = "CA_DIAS_PLAZO_PRESENTACION", nullable = true)
  private Integer caDiasPlazoPresentacion;

  /*
  *Cantidad de días para realizar la presentación del informe de supervisión
  */
   @Column(name = "CA_DIAS_PARA_PRESENTACION", nullable = false)
  private Integer caDiasParaPresentacion;

  /*
  *Fecha inicial del conteo de la cantidad de dias para la presentación del informe de supervisión
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_DESDE_PARA_PRESENTACION", nullable = false)
  private Date feDesdeParaPresentacion;

  /*
  *Fecha de presentación del informe de fiscalización. Cuando se trata de una aprobación sin observaciones tiene el valor NULL
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PRESENTACION", nullable = true)
  private Date fePresentacion;

  /*
  *Cantidad de días calculados de demora en la presentación del informe de supervisión
  */
   @Column(name = "CA_DIAS_DEMORA_CALCULADOS", nullable = false)
  private Integer caDiasDemoraCalculados;

  /*
  *Flag que indica si se aplica o no penalidad. Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_APLICA_PENALIDAD", nullable = false)
  private String flAplicaPenalidad;

  /*
  *Cantidad de días establecidos de demora en la presentación del informe de supervisión. Este dato será proporcionado por el usuario.
  */
   @Column(name = "CA_DIAS_DEMORA_ESTABLECIDOS", nullable = true)
  private Integer caDiasDemoraEstablecidos;

  /*
  *Comentario de la penalidad establecida
  */
   @Column(name = "CM_PENALIDAD", nullable = true)
  private String cmPenalidad;

  /*
  *Flag que indica si existe alguna observación sobre el uso de equipos de protección personal (EPP). Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_OBSERVACION_EPP", nullable = true)
  private String flObservacionEpp;

  /*
  *Comentario de la observación sobre el uso de EPP
  */
   @Column(name = "CM_OBSERVACION_EPP", nullable = true)
  private String cmObservacionEpp;

  /*
  *Fecha límite hasta la que se espera la presentación del informe de fiscalización
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_HASTA_PARA_PRESENTACION", nullable = true)
  private Date feHastaParaPresentacion;

  /*
  *Fecha de firma del acta de fiscalización
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_FIRMA_ACTA_FISCALIZA", nullable = true)
  private Date feFirmaActaFiscaliza;
  
  /*
  *Flag que indica si existe penalidad "Por usar equipos de protección personal (EPP) del agente fiscalizado". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_EPP_AFISCALIZADO", nullable = true)
  private String flEppAfiscalizado;

    /*
  *Comentario de penalidad "Por usar equipos de protección personal (EPP) del agente fiscalizado"
  */
   @Column(name = "CM_EPP_FISCALIZADO", nullable = true)
  private String cmEppFiscalizado;
  
  /*
  *Flag que indica si existe penalidad "Sin contar con equipos". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_SIN_EQUIPOS", nullable = true)
  private String flSinEquipos;

    /*
  *Comentario de penalidad "Sin contar con equipos"
  */
   @Column(name = "CM_SIN_EQUIPOS", nullable = true)
  private String cmSinEquipos;
  
  /*
  *Flag que indica si existe penalidad "Sin contar con instrumentos de medición". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_SIN_INSTRUMENTOS", nullable = true)
  private String flSinInstrumentos;

    /*
  *Comentario de penalidad "Sin contar con instrumentos de medición"
  */
   @Column(name = "CM_SIN_INSTRUMENTOS", nullable = true)
  private String cmSinInstrumentos;
  
  /*
  *Flag que indica si existe penalidad "Contar con equipos defectuosos". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_EQUIPOS_DEFECTUOSOS", nullable = true)
  private String flEquiposDefectuosos;

    /*
  *Comentario de penalidad "Contar con equipos defectuosos"
  */
   @Column(name = "CM_EQUIPOS_DEFECTUOSOS", nullable = true)
  private String cmEquiposDefectuosos;
  
  /*
  *Flag que indica si existe penalidad "Contar con instrumentos de medición defectuosos". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_EQP_MEDICION_DEFECTUOSOS", nullable = true)
  private String flEqpMedicionDefectuosos;

    /*
  *Comentario de penalidad "Contar con instrumentos de medición defectuosos"
  */
   @Column(name = "CM_EQP_MEDICION_DEFECTUOSOS", nullable = true)
  private String cmEqpMedicionDefectuosos;
  
  /*
  *Flag que indica si existe penalidad "Equipos sin certificado de calibración vigente". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_EQP_CALIBRACION_NVIGENTE", nullable = true)
  private String flEqpCalibracionNvigente;

    /*
  *Comentario de penalidad "Equipos sin certificado de calibración vigente"
  */
   @Column(name = "CM_EQP_CALIBRACION_NVIGENTE", nullable = true)
  private String cmEqpCalibracionNvigente;
  
  /*
  *Flag que indica si existe penalidad "Instrumentos de medición sin certificado de calibración vigente". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_INS_CALIBRACION_NVIGENTE", nullable = true)
  private String flInsCalibracionNvigente;

    /*
  *Comentario de penalidad "Instrumentos de medición sin certificado de calibración vigente"
  */
   @Column(name = "CM_INS_CALIBRACION_NVIGENTE", nullable = true)
  private String cmInsCalibracionNvigente;
  
  /*
  *Flag que indica si existe penalidad "Por alterar los formatos de las actas proporcionados". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_ALTERAR_FORMATOS", nullable = true)
  private String flAlterarFormatos;

    /*
  *Comentario de penalidad "Por alterar los formatos de las actas proporcionados"
  */
   @Column(name = "CM_ALTERAR_FORMATOS", nullable = true)
  private String cmAlterarFormatos;
  
  /*
  *Flag que indica si existe penalidad "Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)". Posibles valores: "1" = Sí y 0 = "No"
  */
   @Column(name = "FL_FRUSTRAR_FISCALIZACION", nullable = true)
  private String flFrustrarFiscalizacion;

    /*
  *Comentario de penalidad "Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)"
  */
   @Column(name = "CM_FRUSTRAR_FISCALIZACION", nullable = true)
  private String cmFrustrarFiscalizacion;

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
  *Cantidad de días para presentación de la primera subsanación
  */
 @Transient
  private Integer descCaDiasParaPresentacionInforme2;

  /*
  *Cantidad de días de demora de la primera observación efectuada hasta la conformidad del informe
  */
 @Transient
  private Long descCaDiasDemoraDesde1ObsHastaInfConforme;

  /*
  *Cantidad de días en la que se presentó el informe 1
  */
 @Transient
  private Long descCaDiasPlazoPresentacionInforme1;

  /*
  *Fecha hasta la que se puede presentar el informe 1
  */
 @Transient
  private Date descFeHastaParaPresentacionInforme1;

  /*
  *Fecha de firma del acta de fiscalización
  */
 @Transient
  private Date descFeFirmaActaFiscalizacion;

  /*
  *Fecha de presentación del informe 1
  */
 @Transient
  private Date descFePresentacionInforme1;

  /*
  *Cantidad de días de demora en presentar el informe 1
  */
 @Transient
  private Long descCaDiasDemoraInforme1;

  /*
  *Fecha desde la que se puede presentar el informe 1
  */
 @Transient
  private Date descFeDesdeParaPresentacionInforme1;

  /*
  *Fecha en la que se realizó la primera observación al informe 1
  */
 @Transient
  private Date descFeObservacionInforme1;

  /*
  *DESC_FE_ENVIO_DOCUMENTO
  */
 @Transient
  private Date descFeEnvioDocumento;


}