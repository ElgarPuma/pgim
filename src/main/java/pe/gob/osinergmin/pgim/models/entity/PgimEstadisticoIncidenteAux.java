package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Entidad para la tabla MV_OSINER_INDFRECUE_AUX:
 * 
 * @descripción: Vista para obtener la lista de reporte de estadístico de incidentes
 *               
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Entity
@Table(name = "MV_OSINER_INDFRECUE", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimEstadisticoIncidenteAux implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSINER_INDFRECUE_AUX. Secuencia:
     * MV_OSINER_INDFRECUE_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSINER_INDFRECUE_AUX")
    @SequenceGenerator(name = "MV_OSINER_INDFRECUE_AUX", sequenceName = "MV_OSINER_INDFRECUE_AUX", allocationSize = 1)
    @Column(name = "ID_CLIENTE", nullable = true)
    private Long idCliente;

    /*
     * ANOPRO
     */
    @Column(name = "ANOPRO", nullable = true)
    private String anioPro;

    /*
     * MES
     */
    @Column(name = "MES", nullable = true)
    private String mes;
    
    /*
     * ID_CORRELATIVO
     */
    @Column(name = "ID_CORRELATIVO", nullable = true)
    private Integer idCorrelativo;

    /*
     * RUC
     */
    @Column(name = "RUC", nullable = true)
    private String ruc;

    /*
     * ESTRATO
     */
    @Column(name = "ESTRATO", nullable = true)
    private String estrato;

    /*
     * TITULAR_MINERO
     */
    @Column(name = "TITULAR_MINERO", nullable = true)
    private String titularMinero;

    /*
     * CODIGO
     */
    @Column(name = "CODIGO", nullable = true)
    private String codigo;

    /*
     * UNIDAD
     */
    @Column(name = "UNIDAD", nullable = true)
    private String unidad;
    
    /*
     * TRABAJADORES_CIA
     */
    @Column(name = "TRABAJADORES_CIA", nullable = true)
    private Integer trabajadoresCia;

    /*
     * TRABAJADORES_CM
     */
    @Column(name = "TRABAJADORES_CM", nullable = true)
    private Integer trabajadoresCm;

    /*
     * TRABAJADORES_OTROS
     */
    @Column(name = "TRABAJADORES_OTROS", nullable = true)
    private Integer trabajadoresOtros;

    /*
     * TRABAJADORES_TOTAL
     */
    @Column(name = "TRABAJADORES_TOTAL", nullable = true)
    private Integer trabajadoresTotal;

    /*
     * INCIDENTES_MES
     */
    @Column(name = "INCIDENTES_MES", nullable = true)
    private Integer incidentesMes;
    
    /*
    * INCIDENTES_ACUMALADA
    */
    @Column(name = "INCIDENTES_ACUMALADA", nullable = true)
    private Integer incidentesAcumalada;
    
    /*
    * ACCID_LEVES_MES
    */
    @Column(name = "ACCID_LEVES_MES", nullable = true)
    private Integer accidLevesMes;
    
    /*
    * ACCID_LEVES_ACUM
    */
    @Column(name = "ACCID_LEVES_ACUM", nullable = true)
    private Integer accidLevesAcum;
    
    /*
    * ACCID_INCAPACIT_MES
    */
    @Column(name = "ACCID_INCAPACIT_MES", nullable = true)
    private Integer accidIncapacitMes;
    
    /*
    * ACCID_INCAPACIT_ACUM
    */
    @Column(name = "ACCID_INCAPACIT_ACUM", nullable = true)
    private Integer accidIncapacitAcum;
    
    /*
    * ACCID_MORTALES_MES
    */
    @Column(name = "ACCID_MORTALES_MES", nullable = true)
    private Integer accidMortalesMes;
    
    /*
    * ACCID_MORTALES_ACUM
    */
    @Column(name = "ACCID_MORTALES_ACUM", nullable = true)
    private Integer accidMortalesAcum;
    
    /*
    * DIASPERDIDOS_MES
    */
    @Column(name = "DIASPERDIDOS_MES", nullable = true)
    private Integer diasperdidosMes;
    
    /*
    * DIASPERDIDOS_ACUM
    */
    @Column(name = "DIASPERDIDOS_ACUM", nullable = true)
    private Integer diasperdidosAcum;
    
    /*
    * HORHOMBRES_TRAB_MES
    */
    @Column(name = "HORHOMBRES_TRAB_MES", nullable = true)
    private Integer horhombresTrabMes;
    
    /*
    * HORHOMBRES_TRAB_ACUM
    */
    @Column(name = "HORHOMBRES_TRAB_ACUM", nullable = true)
    private Integer horhombresTrabAcum;
    
    /*
    * INDICE_FRECUENCIA_MES
    */
    @Column(name = "INDICE_FRECUENCIA_MES", nullable = true)
    private BigDecimal indiceFrecuenciaMes;
    
    /*
    * INDICE_FRECUENCIA_ACUM
    */
    @Column(name = "INDICE_FRECUENCIA_ACUM", nullable = true)
    private BigDecimal indiceFrecuenciaAcum;
    
    /*
    * INDICE_SEVERIDAD_MES
    */
    @Column(name = "INDICE_SEVERIDAD_MES", nullable = true)
    private BigDecimal indiceSeveridadMes;
    
    /*
    * INDICE_SEVERIDAD_ACUM
    */
    @Column(name = "INDICE_SEVERIDAD_ACUM", nullable = true)
    private BigDecimal indiceSeveridadAcum;
    
    /*
    * INDICE_ACCIDENTES_MES
    */
    @Column(name = "INDICE_ACCIDENTES_MES", nullable = true)
    private BigDecimal indiceAccidentesMes;
    
    /*
    * INDICE_ACCIDENTES_ACUM
    */
    @Column(name = "INDICE_ACCIDENTES_ACUM", nullable = true)
    private BigDecimal indiceAccidentesAcum;
    
    /*
    * USU_INGRESO
    */
    @Column(name = "USU_INGRESO", nullable = true)
    private String usuIngreso;
    
    /*
    * FEC_INGRESO
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_INGRESO", nullable = true)
    private Date fecIngreso;
    
    /*
     * IP_INGRESO
     */
    @Column(name = "IP_INGRESO", nullable = true)
    private String ipIngreso;
    
    /*
     * USU_MODIFICA
     */
    @Column(name = "USU_MODIFICA", nullable = true)
    private String usuModifica;
    
    /*
     * FEC_MODIFICA
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_MODIFICA", nullable = true)
    private Date fecModifica;
    
    /*
     * IP_MODIFICA
     */
    @Column(name = "IP_MODIFICA", nullable = true)
    private String ipModifica;
}
