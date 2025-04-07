package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_FICHA_REVISION: 
* @descripción: Documento de la ficha de revisión de informe de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimFichaRevisionDTO {

  /*
  *Identificador interno del documento ficha de revisión. Secuencia: PGIM_SEQ_FICHA_REVISION
  */
  private Long idFichaRevision;

  /*
  *Identificador interno del documento de conformidad. Tabla padre: PGIM_TD_DOCUMENTO
  */
  private Long idDocumento;

  /*
  *Tipo de origen del documento. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_FICHA_REVISION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoConformidad;

  /*
  *Fecha de revisión del informe de fiscalización.
  */
  private Date feRevisionFicha;

  /*
  *Fecha de revisión del informe de fiscalización.
  */
  private String feRevisionFichaDesc;

  /*
  *Plazo end días para realizar la presentación del informe de supervisión
  */
  private Integer caDiasPlazoPresentacion;

  /*
  *Cantidad de días para realizar la presentación del informe de supervisión
  */
  private Integer caDiasParaPresentacion;

  /*
  *Fecha inicial del conteo de la cantidad de dias para la presentación del informe de supervisión
  */
  private Date feDesdeParaPresentacion;

  /*
  *Fecha inicial del conteo de la cantidad de dias para la presentación del informe de supervisión
  */
  private String feDesdeParaPresentacionDesc;

  /*
  *Fecha de presentación del informe de fiscalización. Cuando se trata de una aprobación sin observaciones tiene el valor NULL
  */
  private Date fePresentacion;

  /*
  *Fecha de presentación del informe de fiscalización. Cuando se trata de una aprobación sin observaciones tiene el valor NULL
  */
  private String fePresentacionDesc;

  /*
  *Cantidad de días calculados de demora en la presentación del informe de supervisión
  */
  private Integer caDiasDemoraCalculados;

  /*
  *Flag que indica si se aplica o no penalidad. Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flAplicaPenalidad;

  /*
  *Cantidad de días establecidos de demora en la presentación del informe de supervisión. Este dato será proporcionado por el usuario.
  */
  private Integer caDiasDemoraEstablecidos;

  /*
  *Comentario de la penalidad establecida
  */
  private String cmPenalidad;

  /*
  *Flag que indica si existe alguna observación sobre el uso de equipos de protección personal (EPP). Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flObservacionEpp;

  /*
  *Comentario de la observación sobre el uso de EPP
  */
  private String cmObservacionEpp;

  /*
  *Fecha límite hasta la que se espera la presentación del informe de fiscalización
  */
  private Date feHastaParaPresentacion;

  /*
  *Fecha límite hasta la que se espera la presentación del informe de fiscalización
  */
  private String feHastaParaPresentacionDesc;

  /*
  *Fecha de firma del acta de fiscalización
  */
  private Date feFirmaActaFiscaliza;

  /*
  *Fecha de firma del acta de fiscalización
  */
  private String feFirmaActaFiscalizaDesc;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *Cantidad de días para presentación de la primera subsanación
  */
  private Integer descCaDiasParaPresentacionInforme2;

  /*
  *Cantidad de días de demora de la primera observación efectuada hasta la conformidad del informe
  */
  private Long descCaDiasDemoraDesde1ObsHastaInfConforme;

  /*
  *Cantidad de días en la que se presentó el informe 1
  */
  private Long descCaDiasPlazoPresentacionInforme1;

  /*
  *Fecha hasta la que se puede presentar el informe 1
  */
  private Date descFeHastaParaPresentacionInforme1;

  /*
  *Fecha hasta la que se puede presentar el informe 1
  */
  private String descFeHastaParaPresentacionInforme1Desc;

  /*
  *Fecha de firma del acta de fiscalización
  */
  private Date descFeFirmaActaFiscalizacion;

  /*
  *Fecha de firma del acta de fiscalización
  */
  private String descFeFirmaActaFiscalizacionDesc;

  /*
  *Fecha de presentación del informe 1
  */
  private Date descFePresentacionInforme1;

  /*
  *Fecha de presentación del informe 1
  */
  private String descFePresentacionInforme1Desc;

  /*
  *Cantidad de días de demora en presentar el informe 1
  */
  private Long descCaDiasDemoraInforme1;

  /*
  *Fecha desde la que se puede presentar el informe 1
  */
  private Date descFeDesdeParaPresentacionInforme1;

  /*
  *Fecha desde la que se puede presentar el informe 1
  */
  private String descFeDesdeParaPresentacionInforme1Desc;

  /*
  *Fecha en la que se realizó la primera observación al informe 1
  */
  private Date descFeObservacionInforme1;

  /*
  *Fecha en la que se realizó la primera observación al informe 1
  */
  private String descFeObservacionInforme1Desc;

  /*
  *DESC_FE_ENVIO_DOCUMENTO
  */
  private Date descFeEnvioDocumento;

  /*
  *DESC_FE_ENVIO_DOCUMENTO
  */
  private String descFeEnvioDocumentoDesc;

  /*
   * Flag que indica si existen otras penalidades.
   */
  private String descFlOtrasPenalidades;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
  *Flag que indica si existe penalidad "Por usar equipos de protección personal (EPP) del agente fiscalizado". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flEppAfiscalizado;

    /*
  *Comentario de penalidad "Por usar equipos de protección personal (EPP) del agente fiscalizado"
  */
  private String cmEppFiscalizado;
  
  /*
  *Flag que indica si existe penalidad "Sin contar con equipos". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flSinEquipos;

    /*
  *Comentario de penalidad "Sin contar con equipos"
  */
  private String cmSinEquipos;
  
  /*
  *Flag que indica si existe penalidad "Sin contar con instrumentos de medición". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flSinInstrumentos;

    /*
  *Comentario de penalidad "Sin contar con instrumentos de medición"
  */
  private String cmSinInstrumentos;
  
  /*
  *Flag que indica si existe penalidad "Contar con equipos defectuosos". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flEquiposDefectuosos;

    /*
  *Comentario de penalidad "Contar con equipos defectuosos"
  */
  private String cmEquiposDefectuosos;
  
  /*
  *Flag que indica si existe penalidad "Contar con instrumentos de medición defectuosos". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flEqpMedicionDefectuosos;

    /*
  *Comentario de penalidad "Contar con instrumentos de medición defectuosos"
  */
  private String cmEqpMedicionDefectuosos;
  
  /*
  *Flag que indica si existe penalidad "Equipos sin certificado de calibración vigente". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flEqpCalibracionNvigente;

    /*
  *Comentario de penalidad "Equipos sin certificado de calibración vigente"
  */
  private String cmEqpCalibracionNvigente;
  
  /*
  *Flag que indica si existe penalidad "Instrumentos de medición sin certificado de calibración vigente". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flInsCalibracionNvigente;

    /*
  *Comentario de penalidad "Instrumentos de medición sin certificado de calibración vigente"
  */
  private String cmInsCalibracionNvigente;
  
  /*
  *Flag que indica si existe penalidad "Por alterar los formatos de las actas proporcionados". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flAlterarFormatos;

    /*
  *Comentario de penalidad "Por alterar los formatos de las actas proporcionados"
  */
  private String cmAlterarFormatos;
  
  /*
  *Flag que indica si existe penalidad "Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)". Posibles valores: "1" = Sí y 0 = "No"
  */
  private String flFrustrarFiscalizacion;

    /*
  *Comentario de penalidad "Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)"
  */
  private String cmFrustrarFiscalizacion;
}