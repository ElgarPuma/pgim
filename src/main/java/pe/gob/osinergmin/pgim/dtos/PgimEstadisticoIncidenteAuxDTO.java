package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
public class PgimEstadisticoIncidenteAuxDTO {
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSINER_INDFRECUE_AUX. Secuencia:
     * MV_OSINER_INDFRECUE_AUX
     */
    private Long idCliente;

    /*
     * ANOPRO
     */
    private String anioPro;

    /*
     * MES
     */
    private String mes;
    
    /*
     * ID_CORRELATIVO
     */
    private Integer idCorrelativo;

    /*
     * RUC
     */
    private String ruc;

    /*
     * ESTRATO
     */
    private String estrato;

    /*
     * TITULAR_MINERO
     */
    private String titularMinero;

    /*
     * CODIGO
     */
    private String codigo;

    /*
     * UNIDAD
     */
    private String unidad;
    
    /*
     * TRABAJADORES_CIA
     */
    private Integer trabajadoresCia;

    /*
     * TRABAJADORES_CM
     */
    private Integer trabajadoresCm;

    /*
     * TRABAJADORES_OTROS
     */
    private Integer trabajadoresOtros;

    /*
     * TRABAJADORES_TOTAL
     */
    private Integer trabajadoresTotal;

    /*
     * INCIDENTES_MES
     */
    private Integer incidentesMes;
    
    /*
    * INCIDENTES_ACUMALADA
    */
    private Integer incidentesAcumalada;
    
    /*
    * ACCID_LEVES_MES
    */
    private Integer accidLevesMes;
    
    /*
    * ACCID_LEVES_ACUM
    */
    private Integer accidLevesAcum;
    
    /*
    * ACCID_INCAPACIT_MES
    */
    private Integer accidIncapacitMes;
    
    /*
    * ACCID_INCAPACIT_ACUM
    */
    private Integer accidIncapacitAcum;
    
    /*
    * ACCID_MORTALES_MES
    */
    private Integer accidMortalesMes;
    
    /*
    * ACCID_MORTALES_ACUM
    */
    private Integer accidMortalesAcum;
    
    /*
    * DIASPERDIDOS_MES
    */
    private Integer diasperdidosMes;
    
    /*
    * DIASPERDIDOS_ACUM
    */
    private Integer diasperdidosAcum;
    
    /*
    * HORHOMBRES_TRAB_MES
    */
    private Integer horhombresTrabMes;
    
    /*
    * HORHOMBRES_TRAB_ACUM
    */
    private Integer horhombresTrabAcum;
    
    /*
    * INDICE_FRECUENCIA_MES
    */
    private BigDecimal indiceFrecuenciaMes;
    
    /*
    * INDICE_FRECUENCIA_ACUM
    */
    private BigDecimal indiceFrecuenciaAcum;
    
    /*
    * INDICE_SEVERIDAD_MES
    */
    private BigDecimal indiceSeveridadMes;
    
    /*
    * INDICE_SEVERIDAD_ACUM
    */
    private BigDecimal indiceSeveridadAcum;
    
    /*
    * INDICE_ACCIDENTES_MES
    */
    private BigDecimal indiceAccidentesMes;
    
    /*
    * INDICE_ACCIDENTES_ACUM
    */
    private BigDecimal indiceAccidentesAcum;
    
    /*
    * USU_INGRESO
    */
    private String usuIngreso;
    
    /*
    * FEC_INGRESO
    */
    private Date fecIngreso;
    
    /*
     * IP_INGRESO
     */
    private String ipIngreso;
    
    /*
     * USU_MODIFICA
     */
    private String usuModifica;
    
    /*
     * FEC_MODIFICA
     */
    private Date fecModifica;
    
    /*
     * IP_MODIFICA
     */
    private String ipModifica;

    /**
     * Número de registros que contiene la vista del reporte
     */
    private Integer cantidadRegistros;

    /**
     * Periodo inicial
     */
    private String descPeriodoInicial;

    /**
     * Periodo final
     */
    private String descPeriodoFinal;

    /*
     * Titulo del reporte
     */
    private String deTituloReporte;

    /*
     * Nombre del archivo
     */
    private String descNoArchivo;

    /**
     * Nombre de la extensión del archivo
     */
    private String descExtension;

    /**
   * Nombre de unidad fiscalizable
   */
  private String descNoUnidadMinera;
}
