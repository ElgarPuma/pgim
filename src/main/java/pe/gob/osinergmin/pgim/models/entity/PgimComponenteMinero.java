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

import java.math.BigDecimal;

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_COMPONENTE_MINERO: 
* @descripción: Componente minero, asociado a una unidad minera
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_COMPONENTE_MINERO")
@Data
@NoArgsConstructor
public class PgimComponenteMinero implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del componente minero. Secuencia: PGIM_SEQ_COMPONENTE_MINERO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_COMPONENTE_MINERO")
   @SequenceGenerator(name = "PGIM_SEQ_COMPONENTE_MINERO", sequenceName = "PGIM_SEQ_COMPONENTE_MINERO", allocationSize = 1)
   @Column(name = "ID_COMPONENTE_MINERO", nullable = false)
  private Long idComponenteMinero;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Tipo de componente minero de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_COMPONENTE_MINERO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_COMPONENTE_MINERO", nullable = false)
  private PgimValorParametro tipoComponenteMinero;

  /*
  *Identificador interno del tipo de subcomponente minero. Tabla padre: PGIM_TM_TIPO_SUBCOMPONENTE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_SUBCOMPONENTE", nullable = true)
  private PgimTipoSubcomponente pgimTipoSubcomponente;

  /*
  *Identificador interno del componente padre, cuando tiene valor se trata de un subcomponente. Tabla padre: PGIM_TM_COMPONENTE_MINERO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_COMPONENTE_MINERO_PADRE", nullable = true)
  private PgimComponenteMinero componenteMineroPadre;

  /*
  *Código del componente minero
  */
   @Column(name = "CO_COMPONENTE", nullable = false)
  private String coComponente;

  /*
  *Nombre del componente minero
  */
   @Column(name = "NO_COMPONENTE", nullable = false)
  private String noComponente;

  /*
  *Capacidad de planta expresada en TM/d.
  */
   @Column(name = "NU_CAPACIDAD_PLANTA", nullable = true)
  private BigDecimal nuCapacidadPlanta;

    /*
  *Nivel de aceptación
  */
  @Column(name = "NVL_ACEPTACION", nullable = true)
  private Long nvlAceptacion;

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
  *Nombre del tipo de componente minero
  */
 @Transient
  private String descIdTipoComponenteMinero;

  /*
  *Nombre completo del componente minero (Auxiliar)
  */
 @Transient
  private String descNombreCompleto;

  /*
  *Flag que indica si el componente ha sido seleccionado (Auxiliar)
  */
 @Transient
  private boolean descSeleccionado;

  /*
  *Color que se le asignará al control del flag de selección (Auxiliar)
  */
 @Transient
  private String descColor;

  /*
  *Valor numérico del parámetro
  */
 @Transient
  private Long descNuValorNumerico;

  /*
  *Código y nombre de la unidad minera
  */
 @Transient
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descUnidadMinera;

  /*
  *RUC y razón social del agente fiscalizado
  */
 @Transient
  private String descAgenteFiscalizado;

  /*
  *Nombre tipo subcomponente minero
  */
 @Transient
  private String descNoTipoSubcomponente;

  /*
  *DESC_RUC_AGENTE_FISCALIZADO
  */
 @Transient
  private String descRucAgenteFiscalizado;

}