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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VC_AGENTE_SUPERVISADO_AUX: 
* @descripción: Vista del agente supervisado (titular minero)
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VC_AGENTE_SUPERVISADO_AUX")
@Data
@NoArgsConstructor
public class PgimAgenteSupervisadoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del agente supervisado AUX. Secuencia: PGIM_SEQ_AGENTE_SUPERVISADO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_AGENTE_SUPERVISADO")
   @SequenceGenerator(name = "PGIM_SEQ_AGENTE_SUPERVISADO", sequenceName = "PGIM_SEQ_AGENTE_SUPERVISADO", allocationSize = 1)
   @Column(name = "ID_AGENTE_SUPERVISADO_AUX", nullable = false)
  private Long idAgenteSupervisadoAux;

  /*
  *Identificador interno del agente supervisado relacionado
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private PgimAgenteSupervisado pgimAgenteSupervisado;

  /*
  *Cantidad de Unidades Mineras asociadas al Agente Supervisado
  */
   @Column(name = "NU_CANTIDAD_UNI_MINERA", nullable = true)
  private Long nuCantidadUniMinera;

  /*
  *Capacidad total instalada de planta (TM/d), de las unidades mineras asociadas al Agente Supervisado, y que son de tipo concesión de beneficio.
  */
   @Column(name = "NU_CAPACIDAD_TOTAL_INSTALADA", nullable = true)
  private BigDecimal nuCapacidadTotalInstalada;


}