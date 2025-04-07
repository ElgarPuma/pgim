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
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_PRGRM_SEGUIMIENTO_AUX: 
* @descripción: Vista para obtener la lista de seguimientos de supervisiones
*
* @author: jvalerio
* @version: 1.0
* @fecha_de_creación: 25/04/2021
*/
@Entity
@Table(name = "PGIM_VW_PRGRM_SEGUIMIENTO_AUX")
@Data
@NoArgsConstructor
public class PgimPrgrmSeguimientoAux implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /*
     * ID_SUPERVISION_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XXX")
    @SequenceGenerator(name = "XXX", sequenceName = "XXX", allocationSize = 1)
    @Column(name = "ID_SUPERVISION_AUX", nullable = false)
    private Long idSupervisionAux;

    /*
     * ID_SUPERVISION
     */
    @Column(name = "ID_SUPERVISION", nullable = true)
    private Long idSupervision;

    /*
     * ID_PROGRAMA_SUPERVISION
     */
    @Column(name = "ID_PROGRAMA_SUPERVISION", nullable = true)
    private Long idProgramaSupervision;

    /*
     * ID_UNIDAD_MINERA
     */
    @Column(name = "ID_UNIDAD_MINERA", nullable = true)
    private Long idUnidadMinera;

    /*
     * ID_INSTANCIA_PROCESO
     */
    @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
    private Long idInstanciaProceso;

    /*
     * ID_SUBTIPO_SUPERVISION
     */
    @Column(name = "ID_SUBTIPO_SUPERVISION", nullable = true)
    private Long idSubtSupervision;

    /*
     * NO_UNIDAD_MINERA
     */
    @Column(name = "NO_UNIDAD_MINERA", nullable = true)
    private String noUnidadMinera;

    /*
     * NO_TIPO_SUPERVISION
     */
    @Column(name = "NO_TIPO_SUPERVISION", nullable = true)
    private String noTipoSupervision;

    /*
     * FE_INICIO_SUPERVISION
     */
    @Column(name = "FE_INICIO_SUPERVISION", nullable = true)
    private Date feInicioSupervision;

    /*
     * FE_FIN_SUPERVISION
     */
    @Column(name = "FE_FIN_SUPERVISION", nullable = true)
    private Date feFinSupervision;

    /*
     * FE_INICIO_SUPERVISION_REAL
     */
    @Column(name = "FE_INICIO_SUPERVISION_REAL", nullable = true)
    private Date feInicioSupervisionReal;

    /*
     * FE_FIN_SUPERVISION_REAL
     */
    @Column(name = "FE_FIN_SUPERVISION_REAL", nullable = true)
    private Date feFinSupervisionReal;

    /*
     * ID_FASE_ACTUAL
     */
    @Column(name = "ID_FASE_ACTUAL", nullable = true)
    private Long idFaseActual;

    /*
     * NO_FASE_ACTUAL
     */
    @Column(name = "NO_FASE_ACTUAL", nullable = true)
    private String noFaseActual;

    /*
     * ID_PASO_ACTUAL
     */
    @Column(name = "ID_PASO_ACTUAL", nullable = true)
    private Long idPasoActual;

    /*
     * NO_PASO_ACTUAL
     */
    @Column(name = "NO_PASO_ACTUAL", nullable = true)
    private String noPasoActual;
    
    /*
     * ID_INSTANCIA_PASO_ACTUAL
     */
    @Column(name = "ID_INSTANCIA_PASO_ACTUAL", nullable = true)
    private Long idInstanciaPasoActual;

    /*
     * RESPONSABLE
     */
    @Column(name = "RESPONSABLE", nullable = true)
    private String noResponsable;

    /*
     * FE_INICIO_REAL_O_PROGRAMADA
     */
    @Column(name = "FE_INICIO_REAL_O_PROGRAMADA", nullable = true)
    private Date feInicioRealOprogramada;

    /*
     * FE_FIN_REAL_O_PROGRAMADA
     */
    @Column(name = "FE_FIN_REAL_O_PROGRAMADA", nullable = true)
    private Date feFinRealOprogramada;

    /*
     * COSTO
     */
    @Column(name = "COSTO", nullable = true)
    private BigDecimal costo;

    /*
     * ID_LINEA_PROGRAMA
     */
    @Column(name = "ID_LINEA_PROGRAMA", nullable = true)
    private Long idLineaPrograma;

    /*
     * ID_ITEM_PROGRAMA_SUPE
     */
    @Column(name = "ID_ITEM_PROGRAMA_SUPE", nullable = true)
    private Long idItemProgramaSupe;

    /*
     * FE_MES_ESTIMADO
     */
    @Column(name = "FE_MES_ESTIMADO", nullable = true)
    private Date feMesEstimado;

    /*
     * DE_SUBTIPO_SUPERVISION
     */
    @Column(name = "DE_SUBTIPO_SUPERVISION", nullable = true)
    private String deSubtipoSupervision;

    /*
     * MO_CONSUMO_CONTRATO
     */
    @Column(name = "MO_CONSUMO_CONTRATO", nullable = true)
    private BigDecimal moConsumoContrato;

    /*
     * MO_COSTO_ESTIMADO_SUPERVISION
     */
    @Column(name = "MO_COSTO_ESTIMADO_SUPERVISION", nullable = true)
    private BigDecimal moCostoEstimadoSupervision;

    /*
     * ID_TIPO_SUPERVISION
     */
    @Column(name = "ID_TIPO_SUPERVISION", nullable = true)
    private Long idTipoSupervision;

    /*
     * SITUACION
     */
    @Column(name = "SITUACION", nullable = true)
    private String situacion;

    /*
     * AGENTE
     */
    @Column(name = "AGENTE", nullable = true)
    private String agente;

    /*
     * CO_SUPERVISION
     */
    @Column(name = "CO_SUPERVISION", nullable = true)
    private String coSupervision;
}
