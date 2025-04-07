package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_ITEM_PROGRAMA_SUPE_AUX: 
* @descripción: Vista para el soporte del listado de ítems del programa de supervisión
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_ITEM_PROGRAMA_SUPE_AUX")
@Data
@NoArgsConstructor
public class PgimItemProgramaSupeAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_ITEM_PROGRAMA_SUPE_AUX. Secuencia: PGIM_SEQ_ITEM_PROGRAMA_SUPE_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ITEM_PROGRAMA_SUPE_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ITEM_PROGRAMA_SUPE_AUX", sequenceName = "PGIM_SEQ_ITEM_PROGRAMA_SUPE_AUX", allocationSize = 1)
   @Column(name = "ID_ITEM_PROGRAMA_SUPE_AUX", nullable = false)
  private Long idItemProgramaSupeAux;

  /*
  *Identificador interno del ítem del programa de supervisión
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ITEM_PROGRAMA_SUPE", nullable = true)
  private PgimItemProgramaSupe pgimItemProgramaSupe;

  /*
  *Listado de configuraciones de riesgo que aplican sobre la unidad minera del ítem del programa de supervisión
  */
   @Column(name = "DE_CONFIGURACIONES_RIESGO", nullable = true)
  private String deConfiguracionesRiesgo;
   
  /*
  *Id del agente fiscalizado
  */
   @Column(name = "ID_AGENTE_SUPERVISADO", nullable = true)
  private Long idAgenteSupervisado;
   
  /*
  *Razón social del agente fiscalizado
  */
   @Column(name = "NO_RAZON_SOCIAL_AS", nullable = true)
  private String noRazonSocialAs;


}