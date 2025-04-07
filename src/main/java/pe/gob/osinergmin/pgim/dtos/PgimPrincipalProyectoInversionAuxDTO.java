package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla MV_OSIN_PRINCI_PROY_INVER:
 * 
 * @descripción: Vista para obtener la lista de reporte de principales proyectos
 *               de inversión
 * 
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimPrincipalProyectoInversionAuxDTO {

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PRINCI_PROY_INVER_AUX. Secuencia:
     * MV_OSIN_PRINCI_PROY_INVER_AUX
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
     * RUC
     */
    private String ruc;

    /*
     * TITULAR_MINERO
     */
    private String titularMinero;

    /*
     * ESTRATO
     */
    private String estrato;

    /*
     * NOMBRE_PROYECTO
     */
    private String nombreProyecto;

    /*
     * FECHA_INICIO
     */
    private Date fechaInicio;

    /*
     * FECHA_TERMINO
     */
    private Date fechaTermino;

    /*
     * REGION
     */
    private String region;

    /*
     * ETAPA_PROYECTO
     */
    private String etapaProyecto;

    /*
     * ANIOS_VIDA_UTIL
     */
    private BigDecimal aniosVidaUtil;

    /*
     * PRESUPUESTO_GLOBAL
     */
    private BigDecimal presupuestoGlobal;

    /*
     * PRESUPUESTO_ANUAL_ESTIMADO
     */
    private BigDecimal presupuestoAnualEstimado;

    /*
     * INVERSION_ACU_2018
     */
    private BigDecimal inversionAcu2018;

    /*
     * INVERSION_MES_ANTERIOR
     */
    private BigDecimal inversionMesAnterior;

    /*
     * INVERSION_MES
     */
    private BigDecimal inversionMes;

    /*
     * PRODUCCION_ANUAL
     */
    private BigDecimal produccionAnual;

    /*
     * PORC_AVANCE_FISICO
     */
    private String porcAvanceFisico;

    /*
     * PROYECTO_CARTERA_MINEM
     */
    private String proyectoCarteraMinem;

    /*
     * MINERAL_PRINCIPAL
     */
    private String mineralPrincipal;

    /*
     * OTROS_MINERALES
     */
    private String otrosMinerales;

    /*
     * EMPLEO_OPERACION
     */
    private String empleoOperacion;

    /*
     * EMPLEO_CONSTRUCCION
     */
    private String empleoConstruccion;

    /*
     * NOMBRE_INVERSIONISTA
     */
    private String nombreInversionista;

    /*
     * CONCESION_MINERA
     */
    private String concesionMinera;

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
}
