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

import javax.persistence.Transient;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_CONFIGURACION_BASE: 
* @descripción: Configuración para datos base en la fiscalización
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_CONFIGURACION_BASE")
@Data
@NoArgsConstructor
public class PgimConfiguracionBase implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Secuencia: PGIM_SEQ_CONFIGURACION_BASE
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CONFIGURACION_BASE")
   @SequenceGenerator(name = "PGIM_SEQ_CONFIGURACION_BASE", sequenceName = "PGIM_SEQ_CONFIGURACION_BASE", allocationSize = 1)
   @Column(name = "ID_CONFIGURACION_BASE", nullable = false)
  private Long idConfiguracionBase;

  /*
  *Identificador interno de la configuración origen desde la que se copió la actual configuración. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_CONFIGURACION_BASE_ORIGEN", nullable = true)
  private PgimConfiguracionBase pgimConfiguracionBaseOrigen;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = false)
  private PgimEspecialidad pgimEspecialidad;

  /*
  *Tipo de la relación del paso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CONFIGURACION_BASE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_CONFIGURACION_BASE", nullable = false)
  private PgimValorParametro tipoConfiguracionBase;

  /*
  *Nombre de la configuración base que se utilizará en el marco de la fiscalización
  */
   @Column(name = "NO_COFIGURACION_BASE", nullable = false)
  private String noCofiguracionBase;

  /*
  *Descripción de la configuración base que se utilizará en el marco de la fiscalización
  */
   @Column(name = "DE_COFIGURACION_BASE", nullable = true)
  private String deCofiguracionBase;

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
  *DESC_NO_TIPO_CONFIGURACION_BASE
  */
 @Transient
  private String descNoTipoConfiguracionBase;

  /*
  *DESC_NO_ESPECIALIDAD
  */
 @Transient
  private String descNoEspecialidad;


}