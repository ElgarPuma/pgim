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
* Clase Entidad para la tabla PGIM_VW_EXP_PERFA_AUX: 
* @descripción: Vista de todas las personas responsables que tienen expedientes de supervisión pendientes de atender, por fase, según año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXP_PERFA_AUX")
@Data
@NoArgsConstructor
public class PgimExpPerfaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_ID_INSTANCIA_FA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ID_INSTANCIA_FA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ID_INSTANCIA_FA_AUX", sequenceName = "PGIM_SEQ_ID_INSTANCIA_FA_AUX", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_FA_AUX", nullable = false)
  private Long idInstanciaFaAux;

  /*
  *DE_ENTIDAD_PERSONA
  */
   @Column(name = "DE_ENTIDAD_PERSONA", nullable = true)
  private String deEntidadPersona;

  /*
  *ID_PERSONA
  */
   @Column(name = "ID_PERSONA", nullable = true)
  private Long idPersona;

  /*
  *ETIQUETA_PERSONA
  */
   @Column(name = "ETIQUETA_PERSONA", nullable = true)
  private String etiquetaPersona;

  /*
  *NO_USUARIO
  */
   @Column(name = "NO_USUARIO", nullable = true)
  private String noUsuario;

  /*
  *ANIO_SUPERVISION
  */
   @Column(name = "ANIO_SUPERVISION", nullable = true)
  private Long anioSupervision;

  /*
  *F1_PRE_SUPERVISION
  */
   @Column(name = "F1_PRE_SUPERVISION", nullable = true)
  private Long f1PreSupervision;

  /*
  *F2_SUPERVISION_DE_CAMPO
  */
   @Column(name = "F2_SUPERVISION_DE_CAMPO", nullable = true)
  private Long f2SupervisionDeCampo;

  /*
  *F3_POST_SUPERVISION_DE_CAMPO
  */
   @Column(name = "F3_POST_SUPERVISION_DE_CAMPO", nullable = true)
  private Long f3PostSupervisionDeCampo;

  /*
  *F4_REV_INFORME_SUPERVISION
  */
   @Column(name = "F4_REV_INFORME_SUPERVISION", nullable = true)
  private Long f4RevInformeSupervision;

  /*
  *F5_APROBACION_RESULTADOS
  */
   @Column(name = "F5_APROBACION_RESULTADOS", nullable = true)
  private Long f5AprobacionResultados;


}