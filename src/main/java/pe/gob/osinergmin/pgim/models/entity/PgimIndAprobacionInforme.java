package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la vista PGIM_VW_IND_APROBACION_INFORME: 
* @descripción: Vista para el indicador de aprobación de los informes de fiscalización
*
* @author: gdelaguila
* @version 1.0
* @fecha_de_creación: 02/03/2023
*/
@Entity
@Table(name = "PGIM_VW_IND_APROBACION_INFORME")
@Data
@NoArgsConstructor
public class PgimIndAprobacionInforme implements Serializable {

private static final long serialVersionUID = 1L;

/*
 *ID_SUPERVISION
 */
  @Id
  @Column(name = "ID_SUPERVISION", nullable = false)
 private Long idSupervision;
  
  
  /*
   * CO_SUPERVISION
   */
  @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

 /*
 *ID_ESPECIALIDAD
 */
  @Column(name = "ID_ESPECIALIDAD", nullable = true)
 private Long idEspecialidad;

 /*
 *NO_ESPECIALIDAD
 */
  @Column(name = "NO_ESPECIALIDAD", nullable = true)
 private String noEspecialidad;
 
  /*
   *ID_DIVISION_SUPERVISORA
   */
  @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

   /*
   *NO_DIVISION_SUPERVISORA
   */
  @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;
   
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
   *ID_MOTIVO_SUPERVISION
   */
  @Column(name = "ID_MOTIVO_SUPERVISION", nullable = true)
  private Long idMotivoSupervision;

   /*
   *DE_MOTIVO_SUPERVISION
   */
  @Column(name = "DE_MOTIVO_SUPERVISION", nullable = true)
  private String deMotivoSupervision;
  
  /*
   *ID_UNIDAD_MINERA
   */
  @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

   /*
   *CO_UNIDAD_MINERA
   */
  @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;
  
  /*
   *NO_UNIDAD_MINERA
   */
  @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;
  
  /*
   *ID_CONTRATO
   */
  @Column(name = "ID_CONTRATO", nullable = true)
  private Long idContrato;

   /*
   *NU_CONTRATO
   */
  @Column(name = "NU_CONTRATO", nullable = true)
  private String nuContrato;
  
  /*
   *ID_AGENTE_SUPERVISADO
   */
  @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

   /*
   *NO_AGENTE_SUPERVISADO
   */
  @Column(name = "NO_AGENTE_SUPERVISADO", nullable = true)
  private String noAgenteSupervisado;
  
  /*
   *RUC_AGENTE_SUPERVISADO
   */
  @Column(name = "RUC_AGENTE_SUPERVISADO", nullable = true)
  private String rucAgenteSupervisado;
  
  /*
   *ID_EMPRESA_SUPERVISORA
   */
  @Column(name = "ID_EMPRESA_SUPERVISORA", nullable = true)
  private Long idEmpresaSupervisora;
  
  /*
   *RUC_EMPRESA_SUPERVISORA
   */
  @Column(name = "RUC_EMPRESA_SUPERVISORA", nullable = true)
  private String rucEmpresaSupervisora;
  
  /*
   *NO_EMPRESA_SUPERVISORA
   */
  @Column(name = "NO_EMPRESA_SUPERVISORA", nullable = true)
  private String noEmpresaSupervisora;
  
  /*
   *FL_FE_ENVIO
   */
  @Column(name = "FL_FE_ENVIO", nullable = true)
  private String flFeEnvio;
  
  /*
   * FE_PRESENTA_INFORME
   */
  @Column(name = "FE_PRESENTA_INFORME", nullable = true)
  private Date fePresentaInforme;
  
  /*
   * FE_APRUEBA_INFORME
   */
  @Column(name = "FE_APRUEBA_INFORME", nullable = true)
  private Date feApruebaInforme;
  
  
  /*
   * CA_DIAS_DEMORA
   */
  @Column(name = "CA_DIAS_DEMORA", nullable = true)
  private Long caDiasDemora;
  
  
}
