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
* Clase Entidad para la tabla PGIM_TM_SCATEGORIA_ENTREGABLE: 
* @descripción: Subcategoría y tipo de entregable
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_SCATEGORIA_ENTREGABLE")
@Data
@NoArgsConstructor
public class PgimScategoriaEntregable implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la subcategoría del tipo de entregable. Secuencia: PGIM_SEQ_SCATEGORIA_ENTREGABLE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SCATEGORIA_ENTREGABLE")
   @SequenceGenerator(name = "PGIM_SEQ_SCATEGORIA_ENTREGABLE", sequenceName = "PGIM_SEQ_SCATEGORIA_ENTREGABLE", allocationSize = 1)
   @Column(name = "ID_SCATEGORIA_ENTREGABLE", nullable = false)
  private Long idScategoriaEntregable;

  /*
  *Identificador interno de la subcategoría del documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

  /*
  *Tipo de entregable. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ENTREGABLE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>.  Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ENTREGABLE", nullable = false)
  private PgimValorParametro tipoEntregable;

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