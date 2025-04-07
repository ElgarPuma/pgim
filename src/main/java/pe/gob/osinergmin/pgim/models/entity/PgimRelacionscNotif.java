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
* Clase Entidad para la tabla PGIM_TM_RELACIONSC_NOTIF: 
* @descripción: Subcategorías relacionadas para notificación. Identificador interno de la subcategoría de documento que se puede utilizar para notificar electrónicamente y la respectiva constancia de notificación electrónica a modo de subcategoría de documento 
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_RELACIONSC_NOTIF")
@Data
@NoArgsConstructor
public class PgimRelacionscNotif implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la configuración de qué documento, susceptible de ser notificado electrónicamente, genera qué subcategoría de constancia de dicha notificación. Secuencia: PGIM_SEQ_RELACIONSC_NOTIF
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_RELACIONSC_NOTIF")
   @SequenceGenerator(name = "PGIM_SEQ_RELACIONSC_NOTIF", sequenceName = "PGIM_SEQ_RELACIONSC_NOTIF", allocationSize = 1)
   @Column(name = "ID_RELACIONSC_NOTIF", nullable = false)
  private Long idRelacionscNotif;

  /*
  *Identificador interno de la categoría de documento constancia. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOC_CONSTANCIA", nullable = false)
  private PgimSubcategoriaDoc subcatDocConstancia;

  /*
  *Identificador interno de la categoría de documento notificable electrónicamente. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOC_NOTIFICABLE", nullable = false)
  private PgimSubcategoriaDoc subcatDocNotificable;
   
  /*
  *Tipo de notificación. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = TIPO_NOTIFICACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_NOTIFICACION", nullable = false)
  private PgimValorParametro tipoNotificacion;   

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