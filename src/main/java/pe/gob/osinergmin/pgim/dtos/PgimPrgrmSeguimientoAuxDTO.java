package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad PGIM_VW_PRGRM_SEGUIMIENTO_AUX: 
* @descripción: Vista para obtener la lista de seguimientos de supervisiones
*
* @author: jvalerio
* @version: 1.0
* @fecha_de_creación: 25/04/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPrgrmSeguimientoAuxDTO {
    /*
     * ID_SUPERVISION_AUX
     */
    private Long idSupervisionAux;

    /*
     * ID_SUPERVISION
     */
    private Long idSupervision;

    /*
     * ID_PROGRAMA_SUPERVISION
     */
    private Long idProgramaSupervision;

    /*
     * ID_SUBTIPO_SUPERVISION
     */
    private Long idSubtSupervision;

    /*
     * ID_UNIDAD_MINERA
     */
    private Long idUnidadMinera;

    /*
     * ID_INSTANCIA_PROCESO
     */
    private Long idInstanciaProceso;

    /*
     * NO_UNIDAD_MINERA
     */
    private String noUnidadMinera;

    /*
     * NO_TIPO_SUPERVISION
     */
    private String noTipoSupervision;

    /*
     * FE_INICIO_SUPERVISION
     */
    private Date feInicioSupervision;

    /*
     * FE_FIN_SUPERVISION
     */
    private Date feFinSupervision;

    /*
     * FE_INICIO_SUPERVISION_REAL
     */
    private Date feInicioSupervisionReal;

    /*
     * FE_FIN_SUPERVISION_REAL
     */
    private Date feFinSupervisionReal;

    /*
     * ID_FASE_ACTUAL
     */
    private Long idFaseActual;

    /*
     * NO_FASE_ACTUAL
     */
    private String noFaseActual;

    /*
     * ID_PASO_ACTUAL
     */
    private Long idPasoActual;

    /*
     * NO_PASO_ACTUAL
     */
    private String noPasoActual;
    
    /*
     * ID_INSTANCIA_PASO_ACTUAL
     */
    private Long idInstanciaPasoActual;    

    /*
     * RESPONSABLE
     */
    private String noResponsable;

    /**
     * Nombre del responsable de la supervisión
     */
    private String descNoResponsable;

    /*
     * FE_INICIO_REAL_O_PROGRAMADA
     */
    private Date feInicioRealOprogramada;

    /*
     * FE_FIN_REAL_O_PROGRAMADA
     */
    private Date feFinRealOprogramada;

    /*
     * COSTO
     */
    private BigDecimal costo;

    /*
     * ID_LINEA_PROGRAMA
     */
    private Long idLineaPrograma;

    /*
     * ID_ITEM_PROGRAMA_SUPE
     */
    private Long idItemProgramaSupe;

    /*
     * FE_MES_ESTIMADO
     */
    private Date feMesEstimado;

    /*
     * DE_SUBTIPO_SUPERVISION
     */
    private String deSubtipoSupervision;

    /*
     * MO_CONSUMO_CONTRATO
     */
    private BigDecimal moConsumoContrato;

    /*
     * MO_COSTO_ESTIMADO_SUPERVISION
     */
    private BigDecimal moCostoEstimadoSupervision;

    /*
     * ID_TIPO_SUPERVISION
     */
    private Long idTipoSupervision;

    /**
     * Fecha mes estimado o fecha de inicio y fin de la supervision
     */
    private String fechaIniFinMesEstimado;

    /*
     * SITUACION
     */
    private String situacion;

    /*
     * AGENTE
     */
    private String agente;

    /*
     * CO_SUPERVISION
     */
    private String coSupervision;
}
