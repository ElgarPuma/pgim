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
* Clase Entidad para la tabla PGIM_VW_EXP_PERPA_AUX: 
* @descripción: Vista de todas las personas responsables que tienen expedientes de supervisión pendientes de atender, por paso, según año
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EXP_PERPA_AUX")
@Data
@NoArgsConstructor
public class PgimExpPerpaAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_ID_INSTANCIA_PA_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ID_INSTANCIA_PA_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ID_INSTANCIA_PA_AUX", sequenceName = "PGIM_SEQ_ID_INSTANCIA_PA_AUX", allocationSize = 1)
   @Column(name = "ID_INSTANCIA_PA_AUX", nullable = false)
  private Long idInstanciaPaAux;

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
  *F1_ASIGNARSUPE 
  */
   @Column(name = "F1_ASIGNARSUPE ", nullable = true)
  private Long f1Asignarsupe ;

  /*
  *F1_COORDINARLASUPE 
  */
   @Column(name = "F1_COORDINARLASUPE ", nullable = true)
  private Long f1Coordinarlasupe ;

  /*
  *F1_FIRMARDJI 
  */
   @Column(name = "F1_FIRMARDJI ", nullable = true)
  private Long f1Firmardji ;

  /*
  *F1_GENERARCREDENCIALYTDR 
  */
   @Column(name = "F1_GENERARCREDENCIALYTDR ", nullable = true)
  private Long f1Generarcredencialytdr ;

  /*
  *F1_APROBARTDRYCREDENCIAL 
  */
   @Column(name = "F1_APROBARTDRYCREDENCIAL ", nullable = true)
  private Long f1Aprobartdrycredencial ;

  /*
  *F1_GESTIONARSALDO 
  */
   @Column(name = "F1_GESTIONARSALDO ", nullable = true)
  private Long f1Gestionarsaldo ;

  /*
  *F1_DEFINIRANTECEDENTESAREVISAR 
  */
   @Column(name = "F1_DEFINIRANTECEDENTESAREVISAR ", nullable = true)
  private Long f1Definirantecedentesarevisar ;

  /*
  *F1_REVISARANTECEDENTESDELAUM 
  */
   @Column(name = "F1_REVISARANTECEDENTESDELAUM ", nullable = true)
  private Long f1Revisarantecedentesdelaum ;

  /*
  *F1_APROBARREVANTECEDENTES 
  */
   @Column(name = "F1_APROBARREVANTECEDENTES ", nullable = true)
  private Long f1Aprobarrevantecedentes ;

  /*
  *F1_COMPLETARSUPECOMOCANCELADA 
  */
   @Column(name = "F1_COMPLETARSUPECOMOCANCELADA ", nullable = true)
  private Long f1Completarsupecomocancelada ;

  /*
  *F2_INICIARSUPEDECAMPO 
  */
   @Column(name = "F2_INICIARSUPEDECAMPO ", nullable = true)
  private Long f2Iniciarsupedecampo ;

  /*
  *F2_SOLICITARDOCUMENTACIONALAUM 
  */
   @Column(name = "F2_SOLICITARDOCUMENTACIONALAUM ", nullable = true)
  private Long f2Solicitardocumentacionalaum ;

  /*
  *F2_REALIZARMEDYCONSTATARHECHOS 
  */
   @Column(name = "F2_REALIZARMEDYCONSTATARHECHOS ", nullable = true)
  private Long f2Realizarmedyconstatarhechos ;

  /*
  *F2_PRESENTARACTADESUPE 
  */
   @Column(name = "F2_PRESENTARACTADESUPE ", nullable = true)
  private Long f2Presentaractadesupe ;

  /*
  *F2_REPROGRAMARSUPE 
  */
   @Column(name = "F2_REPROGRAMARSUPE ", nullable = true)
  private Long f2Reprogramarsupe ;

  /*
  *F3_ELABORARINFDESUPEFALLIDA 
  */
   @Column(name = "F3_ELABORARINFDESUPEFALLIDA ", nullable = true)
  private Long f3Elaborarinfdesupefallida ;

  /*
  *F3_ELABORARYPRESENTARINFDESUPE 
  */
   @Column(name = "F3_ELABORARYPRESENTARINFDESUPE ", nullable = true)
  private Long f3Elaborarypresentarinfdesupe ;

  /*
  *F4_REVISARYAPROBARINFSUPE 
  */
   @Column(name = "F4_REVISARYAPROBARINFSUPE ", nullable = true)
  private Long f4Revisaryaprobarinfsupe ;

  /*
  *F4_ELABORARMEMODESUPEFALLIDA 
  */
   @Column(name = "F4_ELABORARMEMODESUPEFALLIDA ", nullable = true)
  private Long f4Elaborarmemodesupefallida ;

  /*
  *F4_APROBARMEMOSUPEFALLIDA 
  */
   @Column(name = "F4_APROBARMEMOSUPEFALLIDA ", nullable = true)
  private Long f4Aprobarmemosupefallida ;

  /*
  *F4_COMPLETARSUPECOMOFALLIDA 
  */
   @Column(name = "F4_COMPLETARSUPECOMOFALLIDA ", nullable = true)
  private Long f4Completarsupecomofallida ;

  /*
  *F5_CONFIRMAHCONSTATAEINFRAC 
  */
   @Column(name = "F5_CONFIRMAHCONSTATAEINFRAC ", nullable = true)
  private Long f5Confirmahconstataeinfrac ;

  /*
  *F5_CONTINUARCONELPAS 
  */
   @Column(name = "F5_CONTINUARCONELPAS ", nullable = true)
  private Long f5Continuarconelpas ;

  /*
  *F5_SUPECOMPLETADACONINICIOPAS 
  */
   @Column(name = "F5_SUPECOMPLETADACONINICIOPAS ", nullable = true)
  private Long f5Supecompletadaconiniciopas ;

  /*
  *F5_CONTINUARCONELARCHIVADO 
  */
   @Column(name = "F5_CONTINUARCONELARCHIVADO ", nullable = true)
  private Long f5Continuarconelarchivado ;

  /*
  *F5_APROBAROCAFARCH 
  */
   @Column(name = "F5_APROBAROCAFARCH ", nullable = true)
  private Long f5Aprobarocafarch ;

  /*
  *F5_COMPLETARSUPEARCHIVANDOLA 
  */
   @Column(name = "F5_COMPLETARSUPEARCHIVANDOLA ", nullable = true)
  private Long f5Completarsupearchivandola ;

  /*
  *F5_SOLICITARAMPLIACIONDEIP 
  */
   @Column(name = "F5_SOLICITARAMPLIACIONDEIP ", nullable = true)
  private Long f5Solicitarampliaciondeip ;

  /*
  *F5_FIRMARSOLDEAMPLIACIONPLAZO 
  */
   @Column(name = "F5_FIRMARSOLDEAMPLIACIONPLAZO ", nullable = true)
  private Long f5Firmarsoldeampliacionplazo ;

  /*
  *F5_APROBARSOLAMPLIACIONDEPLAZO 
  */
   @Column(name = "F5_APROBARSOLAMPLIACIONDEPLAZO ", nullable = true)
  private Long f5Aprobarsolampliaciondeplazo ;

  /*
  *F5_VISARHECHOSCONSTATADOS 
  */
   @Column(name = "F5_VISARHECHOSCONSTATADOS ", nullable = true)
  private Long f5Visarhechosconstatados ;
}