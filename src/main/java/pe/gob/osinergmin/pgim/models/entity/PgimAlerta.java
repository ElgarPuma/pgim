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
* Clase Entidad para la tabla PGIM_TC_ALERTA: 
* @descripción: Alerta generada en el sistema
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TC_ALERTA")
@Data
@NoArgsConstructor
public class PgimAlerta implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la alerta del sistema. Secuencia: PGIM_SEQ_ALERTA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ALERTA")
   @SequenceGenerator(name = "PGIM_SEQ_ALERTA", sequenceName = "PGIM_SEQ_ALERTA", allocationSize = 1)
   @Column(name = "ID_ALERTA", nullable = false)
  private Long idAlerta;

  /*
  *Tipo de fechas cambiadas. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ALERTA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ALERTA", nullable = false)
  private PgimValorParametro tipoAlerta;

  /*
  *Identificador interno de la instancia de paso del proceso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PASO", nullable = true)
  private PgimInstanciaPaso pgimInstanciaPaso;

  /*
  *Identificador interno de la alerta a nivel de la instancia del proceso. Tabla padre: PGIM_TC_IPROCESO_ALERTA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_IPROCESO_ALERTA", nullable = true)
  private PgimIprocesoAlerta pgimIprocesoAlerta;

  /*
  *Nombre o título de la alerta
  */
   @Column(name = "NO_ALERTA", nullable = false)
  private String noAlerta;

  /*
  *Descripción de la alerta
  */
   @Column(name = "DE_ALERTA", nullable = false)
  private String deAlerta;

  /*
  *Fecha de generación de la alerta
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ALERTA", nullable = false)
  private Date feAlerta;

  /*
  *Dirección URL de enlace del objeto de negocio
  */
   @Column(name = "DI_ENLACE", nullable = true)
  private String diEnlace;

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