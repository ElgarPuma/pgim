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
* Clase Entidad para la tabla PGIM_VW_EVENTO_AUX: 
* @descripción: Vista para obtener la lista de eventos
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EVENTO_AUX")
@Data
@NoArgsConstructor
public class PgimEventoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del evento. Secuencia: PGIM_SEQ_EVENTO_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_EVENTO_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_EVENTO_AUX", sequenceName = "PGIM_SEQ_EVENTO_AUX", allocationSize = 1)
   @Column(name = "ID_EVENTO_AUX", nullable = false)
  private Long idEventoAux;

  /*
  *Identificador interno del evento
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EVENTO", nullable = true)
  private PgimEvento pgimEvento;

  /*
  *CO_EVENTO
  */
   @Column(name = "CO_EVENTO", nullable = true)
  private String coEvento;

  /*
  *FE_PRESENTACION
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PRESENTACION", nullable = true)
  private Date fePresentacion;

  /*
  *FE_EVENTO
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_EVENTO", nullable = true)
  private Date feEvento;

  /*
  *FE_ANIO_EVENTO
  */
   @Column(name = "FE_ANIO_EVENTO", nullable = true)
  private Long feAnioEvento;

  /*
  *DE_EVENTO
  */
   @Column(name = "DE_EVENTO", nullable = true)
  private String deEvento;

  /*
  *DE_LUGAR_EVENTO
  */
   @Column(name = "DE_LUGAR_EVENTO", nullable = true)
  private String deLugarEvento;

  /*
  *ES_NO_CONTABILIZAR
  */
   @Column(name = "ES_NO_CONTABILIZAR", nullable = true)
  private String esNoContabilizar;

  /*
  *DE_MOTIVO_NOCONTABILIZAR
  */
   @Column(name = "DE_MOTIVO_NOCONTABILIZAR", nullable = true)
  private String deMotivoNocontabilizar;

  /*
  *ES_REGISTRO
  */
   @Column(name = "ES_REGISTRO", nullable = true)
  private String esRegistro;

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
  *ID_AGENTE_SUPERVISADO
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;

  /*
  *ID_PERSONA
  */
   @Column(name = "ID_PERSONA", nullable = true)
  private Long idPersona;

  /*
  *RUC_AS
  */
   @Column(name = "RUC_AS", nullable = true)
  private String rucAs;

  /*
  *NO_RAZON_SOCIAL_AS
  */
   @Column(name = "NO_RAZON_SOCIAL_AS", nullable = true)
  private String noRazonSocialAs;

  /*
  *NO_CORTO_AS
  */
   @Column(name = "NO_CORTO_AS", nullable = true)
  private String noCortoAs;

  /*
  *ID_TIPO_EVENTO
  */
   @Column(name = "ID_TIPO_EVENTO", nullable = true)
  private Long idTipoEvento;

  /*
  *NO_TIPO_EVENTO
  */
   @Column(name = "NO_TIPO_EVENTO", nullable = true)
  private String noTipoEvento;

  /*
  *ID_AGENTE_CAUSANTE
  */
   @Column(name = "ID_AGENTE_CAUSANTE", nullable = true)
  private Long idAgenteCausante;

  /*
  *NO_AGENTE_CAUSANTE
  */
   @Column(name = "NO_AGENTE_CAUSANTE", nullable = true)
  private String noAgenteCausante;

  /*
  *ID_TIPO_ACCIDENTE_MORTAL
  */
   @Column(name = "ID_TIPO_ACCIDENTE_MORTAL", nullable = true)
  private Long idTipoAccidenteMortal;

  /*
  *NO_TIPO_ACCIDENTE_MORTAL
  */
   @Column(name = "NO_TIPO_ACCIDENTE_MORTAL", nullable = true)
  private String noTipoAccidenteMortal;


}