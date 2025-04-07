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

import java.math.BigDecimal;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_GEN_PRG_MES: 
* @descripción: Generación de programa por unidad fiscalizable y mes
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 27/12/2023
*/
@Entity
@Table(name = "PGIM_TD_GEN_PRG_MES")
@Data
@NoArgsConstructor
public class PgimGenPrgMes implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la generación de la propuesta de ítem de programa (fiscalización) por mes. Secuencia: PGIM_SEQ_GEN_PRG_MES
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_GEN_PRG_MES")
   @SequenceGenerator(name = "PGIM_SEQ_GEN_PRG_MES", sequenceName = "PGIM_SEQ_GEN_PRG_MES", allocationSize = 1)
   @Column(name = "ID_GEN_PRG_MES", nullable = false)
  private Long idGenPrgMes;

  /*
  *Identificador interno de la generación del programa por ranking de riesgo. Tabla padre: PGIM_TD_GEN_PRG_UFISCALIZA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_GEN_PRG_UFISCALIZA", nullable = false)
  private PgimGenPrgUfiscaliza pgimGenPrgUfiscaliza;

  /*
  *Estado de generación conforme. Los posibles valores son: "1" = Conforme y "0" = No conforme
  */
   @Column(name = "FL_CONFORME", nullable = false)
  private String flConforme;

  /*
  *Número del mes: Enero = 1, Febrero = 2, Marzo = 3, …, Octubre = 10, Noviembre = 11 y Diciembre = 12
  */
   @Column(name = "NU_MES", nullable = false)
  private BigDecimal nuMes;

  /*
  *Mensaje de la generación de la propuesta del ítem del programa por unidad fiscalizable. Solo tiene valor cuando no se ha podido generar
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

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
  *Identificador interno de la unidad minera
  */
   @Transient
  private Long descIdUnidadMinera;


}