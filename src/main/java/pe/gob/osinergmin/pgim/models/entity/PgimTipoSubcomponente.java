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
* Clase Entidad para la tabla PGIM_TM_TIPO_SUBCOMPONENTE: 
* @descripción: Tipo de subcomponente minero
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_TIPO_SUBCOMPONENTE")
@Data
@NoArgsConstructor
public class PgimTipoSubcomponente implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del tipo de subcomponente minero. Secuencia: PGIM_SEQ_TIPO_SUBCOMPONENTE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_TIPO_SUBCOMPONENTE")
   @SequenceGenerator(name = "PGIM_SEQ_TIPO_SUBCOMPONENTE", sequenceName = "PGIM_SEQ_TIPO_SUBCOMPONENTE", allocationSize = 1)
   @Column(name = "ID_TIPO_SUBCOMPONENTE", nullable = false)
  private Long idTipoSubcomponente;

  /*
  *Tipo de componente minero de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_COMPONENTE_MINERO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_COMPONENTE_MINERO", nullable = false)
  private PgimValorParametro tipoComponenteMinero;

  /*
  *Código del tipo de subcomponente minero
  */
   @Column(name = "CO_TIPO_SUBCOMPONENTE", nullable = false)
  private String coTipoSubcomponente;

  /*
  *Nombre del tipo de subcomponente minero
  */
   @Column(name = "NO_TIPO_SUBCOMPONENTE", nullable = false)
  private String noTipoSubcomponente;

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