package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_CONTR_LIQUIDACION_AUX: 
* @descripción: Vista de la lista de liquidaciones para reporte
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 15/02/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimContrLiquidacionAuxDTO {

    /*
    *Identificador interno de la fiscalización. Secuencia: PGIM_SEQ_FISCALIZACION_AUX
    */
    private Long idContrLiquidacionAux;

    /*
    *ID_ESPECIALIDAD
    */
    private Long idEspecialidad;

    /*
    *ESPECIALIDAD
    */
    private String noEspecialidad;

    /*
    *NO_DIVISION_SUPERVISORA
    */
    private String noDivisionSupervisora;

    /*
    *CO_SUPERVISION
    */
    private String coSupervision;

    /*
    *FE_INICIO_SUPERVISION_REAL
    */
    private Date feInicioSupervisionReal;

    /*
    *FE_FIN_SUPERVISION_REAL
    */
    private Date feFinSupervisionReal;

    /*
    *CO_EXPEDIENTE_SIGED_FISC
    */
    private String coExpedienteSigedFisc;

    /*
    *DE_SUBTIPO_SUPERVISION
    */
    private String deSubtipoFisc;

    /*
    *DE_MOTIVO_SUPERVISION
    */
    private String deMotivoFisc;

    /*
    *CO_UNIDAD_MINERA
    */
    private String coUnidadMinera;

    /*
    *NO_UNIDAD_MINERA
    */
    private String noUnidadMinera;

    /*
    *CO_RUC_AFISCALIZADO
    */
    private String coRucAFisc;

    /*
    *NO_RAZON_SOCIAL_AFISCALIZADO
    */
    private String noRazonSocialAFisc;

    /*
    *NO_FASE_ORIGEN_FISCALIZACION
    */
    private String noFaseOrigenFisc;

    /*
    *NO_PASO_ORIGEN_FISCALIZACION
    */
    private String noPasoOrigenFisc;

    /*
    *NO_FASE_DESTINO_FISCALIZACION
    */
    private String noFaseDestinoFisc;

    /*
    *NO_PASO_DESTINO_FISCALIZACION
    */
    private String noPasoDestinoFisc;

    /*
    *NO_PERSONA_ORIGEN_FISCALIZA
    */
    private String noPersonaOrigenFisc;

        /*
    *NO_PERSONA_DESTINO_FISCALIZA
    */
    private String noPersonaDestinoFisc;

    /*
    *FE_TAREA_FISCALIZACION
    */
    private Date feTareaFisc;

    /*
    *DE_MENSAJE_TAREA_FISCALIZACION
    */
    private String deMensajeTareaFisc;

    /*
    *NU_CONTRATO
    */
    private String nuContrato;

    /*
    *FE_INICIO_CONTRATO
    */
    private Date feInicioContrato;

    /*
    *FE_FIN_CONTRATO
    */
    private Date feFinContrato;

    /*
    *MO_CONSUMO_CONTRATO
    */
    private Long montoConsumoContrato;

    /*
    *NO_TIPO_ENTREGABLE
    */
    private String noTipoEntregable;

    /*
    *NO_ESTADO_CONSUMO
    */
    private String noEstadoConsumo;

    /*
    *MO_ITEM_CONSUMO
    */
    private Long montoItemConsumo;

    /*
    *MO_ITEM_PENALIDAD
    */
    private Long montoItemPenalidad;

    /*
    *MO_ITEM_SUPERVISION_FALLIDA
    */
    private Long montoItemFiscFallida;

    /*
    *NU_LIQUIDACION
    */
    private String nuLiquidacion;

    /*
    *CO_EXP_SIGED_LIQUIDACION
    */
    private String coExpSigedLiquidacion;

    /*
    *NO_FASE_ORIGEN_LIQUIDACION
    */
    private String noFaseOrigenLiquidacion;

    /*
    *NO_PASO_ORIGEN_LIQUIDACION
    */
    private String noPasoOrigenLiquidacion;

    /*
    *NO_FASE_DESTINO_LIQUIDACION
    */
    private String noFaseDestinoLiquidacion;

    /*
    *NO_PASO_DESTINO_LIQUIDACION
    */
    private String noPasoDestinoLiquidacion;

    /*
    *NO_PERSONA_ORIGEN_LIQUI
    */
    private String noPersonaOrigenLiqu;

    /*
    *PERSONA_DESTINO_LIQUI
    */
    private String noPersonaDestinoLiqu;

    /*
    *FE_TAREA_LIQUIDACION
    */
    private String feTareaLiquidacion;

    /*
    *DE_MENSAJE_TAREA_LIQUIDACION
    */
    private String deMensajeTareaLiqu;

    /*
    *ID_DIVISION_SUPERVISORA
    */
    private Long idDivisionSupervisora;

    /*
    *ID_FASE_DESTINO_FISCALIZACION
    */
    private Long idFaseDestinoFisc;

    /*
    *ID_PASO_DESTINO_FISCALIZACION
    */
    private Long idPasoDestinoFisc;

    /*
    *ID_TIPO_ENTREGABLE
    */
    private Long idTipoEntregable;

    /*
    *ID_FASE_DESTINO_LIQUIDACION
    */
    private Long idFaseDestinoLiquidacion;

    /*
    *ID_PASO_DESTINO_LIQUIDACION
    */
    private Long idPasoDestinoLiquidacion;
  
    /*
    *FISCALIZACION_ES_PROPIA
    */
    private String fiscEsPropia;

    /**
     * ID_ESTADO_CONSUMO
     */
    private Long idEstadoConsumo;

}