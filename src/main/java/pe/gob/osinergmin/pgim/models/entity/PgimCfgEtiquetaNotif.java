package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_CFG_ETIQUETA_NOTIF: 
* @descripción: Configuracion de etiqueta notificación
*
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 09/02/2023
*/
@Entity
@Table(name = "PGIM_TM_CFG_ETIQUETA_NOTIF")
@Data
@NoArgsConstructor
public class PgimCfgEtiquetaNotif implements Serializable{

     /*
  *Identificador interno del documento etiqueta notifica. Secuencia: PGIM_SEQ_CFG_ETIQUETA_NOTIF
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CFG_ETIQUETA_NOTIF")
   @SequenceGenerator(name = "PGIM_SEQ_CFG_ETIQUETA_NOTIF", sequenceName = "PGIM_SEQ_CFG_ETIQUETA_NOTIF", allocationSize = 1)
   @Column(name = "ID_CFG_ETIQUETA_NOTIF", nullable = false)
  private Long idCfgEtiquetaNotif;

  /*
  *Identificador interno del documento etiquetado. Tabla padre: PGIM_TM_PASO_PROCESO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PASO_PROCESO", nullable = false)
  private PgimPasoProceso pgimPasoProceso;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TM_RELACION_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RELACION_PASO_FIN", nullable = false)
  private PgimRelacionPaso pgimRelacionPaso;

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
