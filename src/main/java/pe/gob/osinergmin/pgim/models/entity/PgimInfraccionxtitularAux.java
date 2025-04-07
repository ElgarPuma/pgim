package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_INFRACCIONXTITULAR_AUX: 
* @descripción: Vista de todos los agentes supervisados y sus infracciones en seis años
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_INFRACCIONXTITULAR_AUX")
@Data
@NoArgsConstructor
public class PgimInfraccionxtitularAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_INFRACXTITULAR_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_INFRACXTITULAR_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_INFRACXTITULAR_AUX", sequenceName = "PGIM_SEQ_INFRACXTITULAR_AUX", allocationSize = 1)
   @Column(name = "ID_AGENTE_SUPERVISADO_AUX", nullable = false)
  private Long idAgenteSupervisadoAux;

  /*
  *ID_PERSONA
  */
   @Column(name = "ID_PERSONA", nullable = true)
  private Long idPersona;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
   @Column(name = "NO_RAZON_SOCIAL", nullable = true)
  private String noRazonSocial;

  /*
  *NO_CORTO_AGENTE_SUPERVISADO
  */
   @Column(name = "NO_CORTO_AGENTE_SUPERVISADO", nullable = true)
  private String noCortoAgenteSupervisado;

  /*
  *ID_ESTRATO
  */
   @Column(name = "ID_ESTRATO", nullable = true)
  private Long idEstrato;

  /*
  *NO_ESTRATO
  */
   @Column(name = "NO_ESTRATO", nullable = true)
  private String noEstrato;

  /*
  *NO_CORTO_ESTRATO
  */
   @Column(name = "NO_CORTO_ESTRATO", nullable = true)
  private String noCortoEstrato;

  /*
  *NRO_INFRACCION_P1
  */
   @Column(name = "NRO_INFRACCION_P1", nullable = true)
  private Long nroInfraccionP1;

  /*
  *NRO_INFRACCION_P2
  */
   @Column(name = "NRO_INFRACCION_P2", nullable = true)
  private Long nroInfraccionP2;

  /*
  *NRO_INFRACCION_P3
  */
   @Column(name = "NRO_INFRACCION_P3", nullable = true)
  private Long nroInfraccionP3;

  /*
  *NRO_INFRACCION_P4
  */
   @Column(name = "NRO_INFRACCION_P4", nullable = true)
  private Long nroInfraccionP4;

  /*
  *NRO_INFRACCION_P5
  */
   @Column(name = "NRO_INFRACCION_P5", nullable = true)
  private Long nroInfraccionP5;

  /*
  *NRO_INFRACCION_P6
  */
   @Column(name = "NRO_INFRACCION_P6", nullable = true)
  private Long nroInfraccionP6;

  /*
  *NRO_INFRACCION_TOTAL
  */
   @Column(name = "NRO_INFRACCION_TOTAL", nullable = true)
  private Long nroInfraccionTotal;

  /*
  *DESC_NU_INFRACCIONES_MIN
  */
 @Transient
  private Long descNuInfraccionesMin;


}