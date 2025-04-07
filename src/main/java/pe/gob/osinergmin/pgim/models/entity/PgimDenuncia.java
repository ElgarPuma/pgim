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
* Clase Entidad para la tabla PGIM_TM_DENUNCIA: 
* @descripción: Registro de denuncia
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_DENUNCIA")
@Data
@NoArgsConstructor
public class PgimDenuncia implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la denuncia. Secuencia: PGIM_SEQ_DENUNCIA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_DENUNCIA")
   @SequenceGenerator(name = "PGIM_SEQ_DENUNCIA", sequenceName = "PGIM_SEQ_DENUNCIA", allocationSize = 1)
   @Column(name = "ID_DENUNCIA", nullable = false)
  private Long idDenuncia;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = false)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *Medio utilizado para realizar la denuncia. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = MEDIO_DENUNCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_MEDIO_DENUNCIA", nullable = false)
  private PgimValorParametro medioDenuncia;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA. Hace referencia a la persona que realiza la denuncia.
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA_DENUNCIANTE", nullable = false)
  private PgimPersona personaDenunciante;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private PgimInstanciaProces pgimInstanciaProces;

  /*
  *Código de la denuncia
  */
   @Column(name = "CO_DENUNCIA", nullable = false)
  private String coDenuncia;

  /*
  *Fecha de presentación de la denuncia
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_PRESENTACION", nullable = false)
  private Date fePresentacion;

  /*
  *Descripción de la denuncia
  */
   @Column(name = "DE_DENUNCIA", nullable = false)
  private String deDenuncia;

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
  *Número del expediente Siged asociado a la denuncia
  */
 @Transient
  private String descNuExpedienteSiged;

  /*
  *Nombre completo de la persona denunciante
  */
 @Transient
  private String descNoPersonaDenunciante;

  /*
  *Nombre de medio de la denuncia
  */
 @Transient
  private String descMedioDenuncia;

  /*
  *Obtiene el máximo número de código de denuncia
  */
 @Transient
  private String descMaxCoDenuncia;

  /*
  *Nombre de la unidad minera
  */
 @Transient
  private String descNoUnidadMinera;

  /*
  *Tipo de documento de identidad
  */
 @Transient
  private Long descIdTipoDocIdentidad;

  /*
  *Rango de fecha final
  */
 @Transient
  private Date fePresentacionHasta;


}