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
* Clase Entidad para la tabla PGIM_TD_OBLGCN_NRMTVA_ITEM: 
* @descripción: Ítem de norma detalle de la obligación fiscalizada
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 04/05/2023
*/
@Entity
@Table(name = "PGIM_TD_OBLGCN_NRMTVA_ITEM")
@Data
@NoArgsConstructor
public class PgimOblgcnNrmtvaItem implements Serializable{

   private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del ítem de norma de la obligación fiscalizada por hecho constatado de la fscalización. Secuencia: PGIM_SEQ_OBLGCN_NRMTVA_ITEM
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_OBLGCN_NRMTVA_ITEM")
   @SequenceGenerator(name = "PGIM_SEQ_OBLGCN_NRMTVA_ITEM", sequenceName = "PGIM_SEQ_OBLGCN_NRMTVA_ITEM", allocationSize = 1)
   @Column(name = "ID_OBLGCN_NRMTVA_ITEM", nullable = false)
  private Long idOblgcnNrmtvaItem;

  /*
  *Identificador interno de la obligación fiscalizada por hecho constatado de la fiscalización. Tabla padre: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OBLGCN_NRMTVA_HCHOC", nullable = false)
  private PgimOblgcnNrmtvaHchoc pgimOblgcnNrmtvaHchoc;

  /*
  *Identificador interno de la obligación fiscalizada padre por hecho constatado de la fiscalización. Tabla padre: PGIM_TD_OBLGCN_NRMTVA_ITEM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_OBLGCN_NRMTVA_ITEM_PADRE", nullable = true)
  private PgimOblgcnNrmtvaItem oblgcnNrmtvaItemPadre;

  /*
  *Identificador interno del ítem de norma. Tabla padre: PGIM_TM_NORMA_ITEM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_NORMA_ITEM", nullable = false)
  private PgimNormaItem pgimNormaItem;

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