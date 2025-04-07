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
* Clase Entidad para la tabla PGIM_TD_GEN_PRG_UFISCALIZA: 
* @descripción: Generación de programa por unidad fiscalizable
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 27/12/2023
*/
@Entity
@Table(name = "PGIM_TD_GEN_PRG_UFISCALIZA")
@Data
@NoArgsConstructor
public class PgimGenPrgUfiscaliza implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la generación del programa por unidad fiscalizable. Secuencia: PGIM_SEQ_GEN_PRG_UFISCALIZA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_GEN_PRG_UFISCALIZA")
   @SequenceGenerator(name = "PGIM_SEQ_GEN_PRG_UFISCALIZA", sequenceName = "PGIM_SEQ_GEN_PRG_UFISCALIZA", allocationSize = 1)
   @Column(name = "ID_GEN_PRG_UFISCALIZA", nullable = false)
  private Long idGenPrgUfiscaliza;

  /*
  *Identificador interno de la generación del programa por ranking de riesgo. Tabla padre: PGIM_TD_GEN_PRG_RANKING
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_GEN_PRG_RANKING", nullable = false)
  private PgimGenPrgRanking pgimGenPrgRanking;

  /*
  *Identificador interno de la unidad minera dentro del ranking. Tabla padre: PGIM_TD_RANKING_UM
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_RANKING_UM", nullable = false)
  private PgimRankingUm pgimRankingUm;

  /*
  *Puntaje de riesgo de la unidad fiscalizable en el respectivo ranking de riesgo. Copiado desde la columna respectiva PGIM_TD_RANKING_UM.MO_PUNTAJE
  */
   @Column(name = "MO_PUNTAJE_RIESGO", nullable = false)
  private BigDecimal moPuntajeRiesgo;

  /*
  *Proporción del puntaje de riesgo de la unidad fiscalizable respecto a la sumatoria de los puntajes de riesgo de todas las unidades fiscalizables en el respectivo ranking de riesgo
  */
   @Column(name = "MO_PUNTAJE_PROPORCION", nullable = false)
  private BigDecimal moPuntajeProporcion;

  /*
  *Proporción entera del puntaje de riesgo de la unidad fiscalizable respecto a la sumatoria de los puntajes de riesgo de todas las unidades fiscalizables en el respectivo ranking de riesgo
  */
   @Column(name = "MO_PROPORCION_ENTERA", nullable = false)
  private Integer moProporcionEntera;

  /*
  *Cantidad de visitas a realizar por unidad fiscalizable. Si el valor de la columna MO_PROPORCION_ENTERA es >= 1 entonces se debe tomar el valor mínimo entre MO_PROPORCION_ENTERA y PGIM_TC_GEN_PROGRAMA.NU_MAX_FISCA_ANUAL_XUF; por otro lado, si es igual a cero, entonces se debe tomar el valor de 1
  */
   @Column(name = "CA_VISITAS", nullable = false)
  private Integer caVisitas;

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