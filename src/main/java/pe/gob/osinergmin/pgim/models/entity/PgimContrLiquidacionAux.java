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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_CONTR_LIQUIDACION_AUX: 
* @descripción: Vista de la lista de liquidaciones para reporte
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 15/02/2023
*/
@Entity
@Table(name = "PGIM_VW_CONTR_LIQUIDACION_AUX")
@Data
@NoArgsConstructor
public class PgimContrLiquidacionAux implements Serializable{

private static final long serialVersionUID = 1L;

    /*
    *Identificador interno de la fiscalización. Secuencia: PGIM_SEQ_FISCALIZACION_AUX
    */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_FISCALIZACION_AUX")
    @SequenceGenerator(name = "PGIM_SEQ_FISCALIZACION_AUX", sequenceName = "PGIM_SEQ_FISCALIZACION_AUX", allocationSize = 1)
    @Column(name = "ID_CONTR_LIQUIDACION_AUX", nullable = false)
    private Long idContrLiquidacionAux;

    /*
    *ID_ESPECIALIDAD
    */
    @Column(name = "ID_ESPECIALIDAD", nullable = false)
    private Long idEspecialidad;

    /*
    *ESPECIALIDAD
    */
    @Column(name = "NO_ESPECIALIDAD", nullable = false)
    private String noEspecialidad;

    /*
    *NO_DIVISION_SUPERVISORA
    */
    @Column(name = "NO_DIVISION_SUPERVISORA", nullable = false)
    private String noDivisionSupervisora;

    /*
    *CO_SUPERVISION
    */
    @Column(name = "CO_SUPERVISION", nullable = false)
    private String coSupervision;

    /*
    *FE_INICIO_SUPERVISION_REAL
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
    private Date feInicioSupervisionReal;

    /*
    *FE_FIN_SUPERVISION_REAL
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
    private Date feFinSupervisionReal;

    /*
    *CO_EXPEDIENTE_SIGED_FISC
    */
    @Column(name = "CO_EXPEDIENTE_SIGED_FISC", nullable = true)
    private String coExpedienteSigedFisc;

    /*
    *DE_SUBTIPO_SUPERVISION
    */
    @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = false)
    private String deSubtipoFisc;

    /*
    *DE_MOTIVO_SUPERVISION
    */
    @Column(name = "DE_MOTIVO_SUPERVISION", nullable = false)
    private String deMotivoFisc;

    /*
    *CO_UNIDAD_MINERA
    */
    @Column(name = "CO_UNIDAD_MINERA", nullable = false)
    private String coUnidadMinera;

    /*
    *NO_UNIDAD_MINERA
    */
    @Column(name = "NO_UNIDAD_MINERA", nullable = false)
    private String noUnidadMinera;

    /*
    *CO_RUC_AFISCALIZADO
    */
    @Column(name = "CO_RUC_AFISCALIZADO", nullable = true)
    private String coRucAFisc;

    /*
    *NO_RAZON_SOCIAL_AFISCALIZADO
    */
    @Column(name = "NO_RAZON_SOCIAL_AFISCALIZADO", nullable = true)
    private String noRazonSocialAFisc;

    /*
    *NO_FASE_ORIGEN_FISCALIZACION
    */
    @Column(name = "NO_FASE_ORIGEN_FISCALIZACION", nullable = false)
    private String noFaseOrigenFisc;

    /*
    *NO_PASO_ORIGEN_FISCALIZACION
    */
    @Column(name = "NO_PASO_ORIGEN_FISCALIZACION", nullable = false)
    private String noPasoOrigenFisc;

    /*
    *NO_FASE_DESTINO_FISCALIZACION
    */
    @Column(name = "NO_FASE_DESTINO_FISCALIZACION", nullable = false)
    private String noFaseDestinoFisc;

    /*
    *NO_PASO_DESTINO_FISCALIZACION
    */
    @Column(name = "NO_PASO_DESTINO_FISCALIZACION", nullable = false)
    private String noPasoDestinoFisc;

    /*
    *NO_PERSONA_ORIGEN_FISCALIZA
    */
    @Column(name = "NO_PERSONA_ORIGEN_FISCALIZA", nullable = true)
    private String noPersonaOrigenFisc;

        /*
    *NO_PERSONA_DESTINO_FISCALIZA
    */
    @Column(name = "NO_PERSONA_DESTINO_FISCALIZA", nullable = true)
    private String noPersonaDestinoFisc;

    /*
    *FE_TAREA_FISCALIZACION
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_TAREA_FISCALIZACION", nullable = false)
    private Date feTareaFisc;

    /*
    *DE_MENSAJE_TAREA_FISCALIZACION
    */
    @Column(name = "DE_MENSAJE_TAREA_FISCALIZACION", nullable = true)
    private String deMensajeTareaFisc;

    /*
    *NU_CONTRATO
    */
    @Column(name = "NU_CONTRATO", nullable = false)
    private String nuContrato;

    /*
    *FE_INICIO_CONTRATO
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_INICIO_CONTRATO", nullable = false)
    private Date feInicioContrato;

    /*
    *FE_FIN_CONTRATO
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_FIN_CONTRATO", nullable = false)
    private Date feFinContrato;

    /*
    *MO_CONSUMO_CONTRATO
    */
    @Column(name = "MO_CONSUMO_CONTRATO", nullable = false)
    private Long montoConsumoContrato;

    /*
    *NO_TIPO_ENTREGABLE
    */
    @Column(name = "NO_TIPO_ENTREGABLE", nullable = false)
    private String noTipoEntregable;

    /*
    *NO_ESTADO_CONSUMO
    */
    @Column(name = "NO_ESTADO_CONSUMO", nullable = false)
    private String noEstadoConsumo;

    /*
    *MO_ITEM_CONSUMO
    */
    @Column(name = "MO_ITEM_CONSUMO", nullable = false)
    private Long montoItemConsumo;

    /*
    *MO_ITEM_PENALIDAD
    */
    @Column(name = "MO_ITEM_PENALIDAD", nullable = true)
    private Long montoItemPenalidad;

    /*
    *MO_ITEM_SUPERVISION_FALLIDA
    */
    @Column(name = "MO_ITEM_SUPERVISION_FALLIDA", nullable = true)
    private Long montoItemFiscFallida;

    /*
    *NU_LIQUIDACION
    */
    @Column(name = "NU_LIQUIDACION", nullable = true)
    private String nuLiquidacion;

    /*
    *CO_EXP_SIGED_LIQUIDACION
    */
    @Column(name = "CO_EXP_SIGED_LIQUIDACION", nullable = true)
    private String coExpSigedLiquidacion;

    /*
    *NO_FASE_ORIGEN_LIQUIDACION
    */
    @Column(name = "NO_FASE_ORIGEN_LIQUIDACION", nullable = true)
    private String noFaseOrigenLiquidacion;

    /*
    *NO_PASO_ORIGEN_LIQUIDACION
    */
    @Column(name = "NO_PASO_ORIGEN_LIQUIDACION", nullable = true)
    private String noPasoOrigenLiquidacion;

    /*
    *NO_FASE_DESTINO_LIQUIDACION
    */
    @Column(name = "NO_FASE_DESTINO_LIQUIDACION", nullable = true)
    private String noFaseDestinoLiquidacion;

    /*
    *NO_PASO_DESTINO_LIQUIDACION
    */
    @Column(name = "NO_PASO_DESTINO_LIQUIDACION", nullable = true)
    private String noPasoDestinoLiquidacion;

    /*
    *NO_PERSONA_ORIGEN_LIQUI
    */
    @Column(name = "NO_PERSONA_ORIGEN_LIQUI", nullable = true)
    private String noPersonaOrigenLiqu;

    /*
    *PERSONA_DESTINO_LIQUI
    */
    @Column(name = "PERSONA_DESTINO_LIQUI", nullable = true)
    private String noPersonaDestinoLiqu;

    /*
    *FE_TAREA_LIQUIDACION
    */
    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_TAREA_LIQUIDACION", nullable = true)
    private String feTareaLiquidacion;

    /*
    *DE_MENSAJE_TAREA_LIQUIDACION
    */
    @Column(name = "DE_MENSAJE_TAREA_LIQUIDACION", nullable = true)
    private String deMensajeTareaLiqu;

    /*
    *ID_DIVISION_SUPERVISORA
    */
    @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
    private Long idDivisionSupervisora;

    /*
    *ID_FASE_DESTINO_FISCALIZACION
    */
    @Column(name = "ID_FASE_DESTINO_FISCALIZACION", nullable = true)
    private Long idFaseDestinoFisc;

    /*
    *ID_PASO_DESTINO_FISCALIZACION
    */
    @Column(name = "ID_PASO_DESTINO_FISCALIZACION", nullable = true)
    private Long idPasoDestinoFisc;

    /*
    *ID_TIPO_ENTREGABLE
    */
    @Column(name = "ID_TIPO_ENTREGABLE", nullable = true)
    private Long idTipoEntregable;

    /*
    *ID_FASE_DESTINO_LIQUIDACION
    */
    @Column(name = "ID_FASE_DESTINO_LIQUIDACION", nullable = true)
    private Long idFaseDestinoLiquidacion;

    /*
    *ID_PASO_DESTINO_LIQUIDACION
    */
    @Column(name = "ID_PASO_DESTINO_LIQUIDACION", nullable = true)
    private Long idPasoDestinoLiquidacion;

    /*
    *FISCALIZACION_ES_PROPIA
    */
    @Column(name = "FISCALIZACION_ES_PROPIA", nullable = true)
    private String fiscEsPropia;

    /*
    *ID_ESTADO_CONSUMO
    */
    @Column(name = "ID_ESTADO_CONSUMO", nullable = true)
    private Long idEstadoConsumo;

}