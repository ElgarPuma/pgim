package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_EXP_PERFA_AUX: 
* @descripción: Vista de todas las personas responsables que tienen expedientes de supervisión pendientes de atender, por fase, según año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExpPerfaAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_ID_INSTANCIA_FA_AUX
  */
  private Long idInstanciaFaAux;

  /*
  *DE_ENTIDAD_PERSONA
  */
  private String deEntidadPersona;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *ETIQUETA_PERSONA
  */
  private String etiquetaPersona;

  /*
  *NO_USUARIO
  */
  private String noUsuario;

  /*
  *ANIO_SUPERVISION
  */
  private Long anioSupervision;

  /*
  *F1_PRE_SUPERVISION
  */
  private Long f1PreSupervision;

  /*
  *F2_SUPERVISION_DE_CAMPO
  */
  private Long f2SupervisionDeCampo;

  /*
  *F3_POST_SUPERVISION_DE_CAMPO
  */
  private Long f3PostSupervisionDeCampo;

  /*
  *F4_REV_INFORME_SUPERVISION
  */
  private Long f4RevInformeSupervision;

  /*
  *F5_APROBACION_RESULTADOS
  */
  private Long f5AprobacionResultados;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}