package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.math.BigDecimal;

import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_DEMARCACION_AUX: 
* @descripción: Vista para obtener las unidades mineras y sus correspondientes demarcaciones geográficas
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_DEMARCACION_AUX")
@Data
@NoArgsConstructor
public class PgimDemarcacionAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_DEMARCACION_AUX. Secuencia: PGIM_SEQ_DEMARCACION_UM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DEMARCACION_UM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_DEMARCACION_UM_AUX", sequenceName = "PGIM_SEQ_DEMARCACION_UM_AUX", allocationSize = 1)
   @Column(name = "ID_DEMARCACION_UM_AUX", nullable = false)
  private Long idDemarcacionUmAux;

  /*
  *Identicador interno de la demarcación de la UM
  */
   @Column(name = "ID_DEMARCACION_UM", nullable = true)
  private Long idDemarcacionUm;

  /*
  *Identificador interno del agente fiscalizado
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

  /*
  *Código del documento de identidad del agente fiscalizado
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD_AF", nullable = true)
  private String coDocumentoIdentidadAf;

  /*
  *Razón social del agente fiscalizado
  */
   @Column(name = "NO_RAZON_SOCIAL_AF", nullable = true)
  private String noRazonSocialAf;

  /*
  *Identificador interno de la unidad minera
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *Código de la unidad minera
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *Identificador interno del tipo de la unidad minera
  */
   @Column(name = "ID_TIPO_UNIDAD_MINERA", nullable = true)
  private Long idTipoUnidadMinera;

  /*
  *Nombre del tipo de la unidad minera
  */
   @Column(name = "NO_TIPO_UNIDAD_MINERA", nullable = true)
  private String noTipoUnidadMinera;

  /*
  *Identificador interno de la división supervisora de la unidad minera
  */
   @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
  private Long idDivisionSupervisora;

  /*
  *Nombre de la división supervisora de la unidad minera
  */
   @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
  private String noDivisionSupervisora;

  /*
  *Identificador interno del ubigeo como departamento
  */
   @Column(name = "ID_DEPARTAMENTO_DEMARCACION", nullable = true)
  private Long idDepartamentoDemarcacion;

  /*
  *Identificador interno del ubigeo como provincia
  */
   @Column(name = "ID_PROVINCIA_DEMARCACION", nullable = true)
  private Long idProvinciaDemarcacion;

  /*
  *Identificador interno del ubigeo como distrito
  */
   @Column(name = "ID_DISTRITO_DEMARCACION", nullable = true)
  private Long idDistritoDemarcacion;

  /*
  *Nombre del departamento de la demarcación de la unidad minera
  */
   @Column(name = "NO_DEPARTAMENTO_DEMARCACION", nullable = true)
  private String noDepartamentoDemarcacion;

  /*
  *Nombre de la provincia de la demarcación de la unidad minera
  */
   @Column(name = "NO_PROVINCIA_DEMARCACION", nullable = true)
  private String noProvinciaDemarcacion;

  /*
  *Nombre del distrito de la demarcación de la unidad minera
  */
   @Column(name = "NO_DISTRITO_DEMARCACION", nullable = true)
  private String noDistritoDemarcacion;

  /*
  *Valor lógico que señala si la demarcación es o no principal para la unidad minera
  */
   @Column(name = "FL_PRINCIPAL_DEMARCACION", nullable = true)
  private String flPrincipalDemarcacion;

  /*
  *Valor que señala si la demarcación es o no principal para la unidad minera
  */
   @Column(name = "PRINCIPAL_DEMARCACION", nullable = true)
  private String principalDemarcacion;

  /*
  *Valor en porcentaje que señala cuál es el porcentaje de la demarcación representa para la unidad minera
  */
   @Column(name = "PC_DEMARCACION", nullable = true)
  private BigDecimal pcDemarcacion;

  /*
  *RUC y razón social del agente fiscalizado
  */
 @Transient
  private String descRazonSocial;

  /*
  *Código y nombre de la unidad minera
  */
 @Transient
  private String descUnidadMinera;

  /*
  *Departamento, provincia y distrito del ubigeo
  */
 @Transient
  private String descUbigeo;

  /*
  *DESC_FL_DEMARCACION
  */
 @Transient
  private String descFlDemarcacion;

  /*
  *DESC_CON_DEMARCACION
  */
 @Transient
  private String descConDemarcacion;


}