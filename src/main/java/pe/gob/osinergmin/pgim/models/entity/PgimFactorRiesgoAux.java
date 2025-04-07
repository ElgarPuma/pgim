package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_FACTOR_RIESGO_AUX: 
* @descripción: Vista del factor riesgo
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_FACTOR_RIESGO_AUX")
@Data
@NoArgsConstructor
public class PgimFactorRiesgoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del factor de riesgo. Secuencia: PGIM_SEQ_FACRIES_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FACRIES_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_FACRIES_AUX", sequenceName = "PGIM_SEQ_FACRIES_AUX", allocationSize = 1)
   @Column(name = "ID_FACTOR_RIESGO_AUX", nullable = false)
  private Long idFactorRiesgoAux;

  /*
  *ID_GRUPO_RIESGO
  */
   @Column(name = "ID_GRUPO_RIESGO", nullable = true)
  private Long idGrupoRiesgo;

  /*
  *NO_GRUPO
  */
   @Column(name = "NO_GRUPO", nullable = true)
  private String noGrupo;

  /*
  *ID_ESPECIALIDAD
  */
   @Column(name = "ID_ESPECIALIDAD", nullable = true)
  private Long idEspecialidad;

  /*
  *NO_ESPECIALIDAD
  */
   @Column(name = "NO_ESPECIALIDAD", nullable = true)
  private String noEspecialidad;

  /*
  *ID_TIPO_ORIGEN_DATO_RIESGO
  */
   @Column(name = "ID_TIPO_ORIGEN_DATO_RIESGO", nullable = true)
  private Long idTipoOrigenDatoRiesgo;

  /*
  *NO_VALOR_PARAMETRO
  */
   @Column(name = "NO_VALOR_PARAMETRO", nullable = true)
  private String noValorParametro;

  /*
  *NO_FACTOR
  */
   @Column(name = "NO_FACTOR", nullable = true)
  private String noFactor;

  /*
  *DE_FACTOR
  */
   @Column(name = "DE_FACTOR", nullable = true)
  private String deFactor;

  /*
  *ES_REGISTRO
  */
   @Column(name = "ES_REGISTRO", nullable = true)
  private String esRegistro;


}