package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_EXP_PERPA_AUX: 
* @descripción: Vista de todas las personas responsables que tienen expedientes de supervisión pendientes de atender, por paso, según año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExpPerpaAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_ID_INSTANCIA_PA_AUX
  */
  private Long idInstanciaPaAux;

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
  *F1_ASIGNARSUPE 
  */
  private Long f1Asignarsupe ;

  /*
  *F1_COORDINARLASUPE 
  */
  private Long f1Coordinarlasupe ;

  /*
  *F1_FIRMARDJI 
  */
  private Long f1Firmardji ;

  /*
  *F1_GENERARCREDENCIALYTDR 
  */
  private Long f1Generarcredencialytdr ;

  /*
  *F1_APROBARTDRYCREDENCIAL 
  */
  private Long f1Aprobartdrycredencial ;

  /*
  *F1_GESTIONARSALDO 
  */
  private Long f1Gestionarsaldo ;

  /*
  *F1_DEFINIRANTECEDENTESAREVISAR 
  */
  private Long f1Definirantecedentesarevisar ;

  /*
  *F1_REVISARANTECEDENTESDELAUM 
  */
  private Long f1Revisarantecedentesdelaum ;

  /*
  *F1_APROBARREVANTECEDENTES 
  */
  private Long f1Aprobarrevantecedentes ;

  /*
  *F1_COMPLETARSUPECOMOCANCELADA 
  */
  private Long f1Completarsupecomocancelada ;

  /*
  *F2_INICIARSUPEDECAMPO 
  */
  private Long f2Iniciarsupedecampo ;

  /*
  *F2_SOLICITARDOCUMENTACIONALAUM 
  */
  private Long f2Solicitardocumentacionalaum ;

  /*
  *F2_REALIZARMEDYCONSTATARHECHOS 
  */
  private Long f2Realizarmedyconstatarhechos ;

  /*
  *F2_PRESENTARACTADESUPE 
  */
  private Long f2Presentaractadesupe ;

  /*
  *F2_REPROGRAMARSUPE 
  */
  private Long f2Reprogramarsupe ;

  /*
  *F3_ELABORARINFDESUPEFALLIDA 
  */
  private Long f3Elaborarinfdesupefallida ;

  /*
  *F3_ELABORARYPRESENTARINFDESUPE 
  */
  private Long f3Elaborarypresentarinfdesupe ;

  /*
  *F4_REVISARYAPROBARINFSUPE 
  */
  private Long f4Revisaryaprobarinfsupe ;

  /*
  *F4_ELABORARMEMODESUPEFALLIDA 
  */
  private Long f4Elaborarmemodesupefallida ;

  /*
  *F4_APROBARMEMOSUPEFALLIDA 
  */
  private Long f4Aprobarmemosupefallida ;

  /*
  *F4_COMPLETARSUPECOMOFALLIDA 
  */
  private Long f4Completarsupecomofallida ;
  
  /*
  *F5_CONFIRMAHCONSTATAEINFRAC 
  */
  private Long f5Confirmahconstataeinfrac ;

  /*
  *F5_CONTINUARCONELPAS 
  */
  private Long f5Continuarconelpas ;

  /*
  *F5_SUPECOMPLETADACONINICIOPAS 
  */
  private Long f5Supecompletadaconiniciopas ;

  /*
  *F5_CONTINUARCONELARCHIVADO 
  */
  private Long f5Continuarconelarchivado ;

  /*
  *F5_APROBAROCAFARCH 
  */
  private Long f5Aprobarocafarch ;

  /*
  *F5_COMPLETARSUPEARCHIVANDOLA 
  */
  private Long f5Completarsupearchivandola ;

  /*
  *F5_SOLICITARAMPLIACIONDEIP 
  */
  private Long f5Solicitarampliaciondeip ;

  /*
  *F5_FIRMARSOLDEAMPLIACIONPLAZO 
  */
  private Long f5Firmarsoldeampliacionplazo ;

  /*
  *F5_APROBARSOLAMPLIACIONDEPLAZO 
  */
  private Long f5Aprobarsolampliaciondeplazo ;

  /*
  *F5_VISARHECHOSCONSTATADOS 
  */
  private Long f5Visarhechosconstatados ;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}