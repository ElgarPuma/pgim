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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_NORMA_ITEM_AUX: 
* @descripción: Vista auxiliar para la consulta de los ítems de las normas
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_NORMA_ITEM_AUX")
@Data
@NoArgsConstructor
public class PgimNormaItemAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador auxiliar del ítem de la norma. Secuencia: PGIM_SEQ_NORMA_ITEM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_NORMA_ITEM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_NORMA_ITEM_AUX", sequenceName = "PGIM_SEQ_NORMA_ITEM_AUX", allocationSize = 1)
   @Column(name = "ID_NORMA_ITEM_AUX", nullable = false)
  private Long idNormaItemAux;

  /*
  *Identificador interno del ítem de norma legal. Tabla padre PGIM_TM_NORMA_ITEM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA_ITEM", nullable = true)
  private PgimNormaItem pgimNormaItem;

  /*
  *Identificador interno del ítem de norma legal padre. Tabla padre PGIM_TM_NORMA_ITEM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA_ITEM_PADRE", nullable = true)
  private PgimNormaItem normaItemPadre;

  /*
  *Identificador interno de la norma
  */
   @Column(name = "ID_NORMA", nullable = true)
  private Long idNorma;

  /*
  *Código del ítem de la norma
  */
   @Column(name = "CO_ITEM", nullable = true)
  private String coItem;

  /*
  *Contenido del ítem de norma
  */
   @Column(name = "DE_CONTENIDO_T", nullable = true)
  private String deContenidoT;

  /*
  *Indicador de la vigencia o no vigencia del ítem de norma
  */
   @Column(name = "FL_VIGENTE", nullable = true)
  private String flVigente;

  /*
  *Indicador del estado de registro
  */
   @Column(name = "ES_REGISTRO", nullable = true)
  private String esRegistro;

  /*
  *División de Ítem de Norma. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_DIVISION_ITEM. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @Column(name = "ID_DIVISION_ITEM", nullable = true)
  private Long idDivisionItem;

  /*
  *Descripción de la división (tipo) del ítem de la norma
  */
   @Column(name = "DE_DIVISION_ITEM", nullable = true)
  private String deDivisionItem;

  /*
  *Número de nivel en la jerarquía de los ítems de normas
  */
   @Column(name = "NU_NIVEL", nullable = true)
  private Long nuNivel;

  /*
  *Ubicación del ítem de norma
  */
   @Column(name = "UBICACION_ITEM", nullable = true)
  private String ubicacionItem;

  /*
  *Jerarquía del ítem de norma
  */
   @Column(name = "JERARQUIA_ITEM", nullable = true)
  private String jerarquiaItem;

  /*
  *Nombre de la norma legal
  */
 @Transient
  private String descNoNorma;


}