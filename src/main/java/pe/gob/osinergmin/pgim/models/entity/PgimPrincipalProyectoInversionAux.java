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
@Entity
@Table(name = "MV_OSIN_PRINCI_PROY_INVER", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimPrincipalProyectoInversionAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PRINCI_PROY_INVER_AUX. Secuencia:
     * MV_OSIN_PRINCI_PROY_INVER_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_PRINCI_PROY_INVER_AUX")
    @SequenceGenerator(name = "MV_OSIN_PRINCI_PROY_INVER_AUX", sequenceName = "MV_OSIN_PRINCI_PROY_INVER_AUX", allocationSize = 1)
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
     * RUC
     */
    @Column(name = "RUC", nullable = true)
    private String ruc;

    /*
     * TITULAR_MINERO
     */
    @Column(name = "TITULAR_MINERO", nullable = true)
    private String titularMinero;

    /*
     * ESTRATO
     */
    @Column(name = "ESTRATO", nullable = true)
    private String estrato;

    /*
     * NOMBRE_PROYECTO
     */
    @Column(name = "NOMBRE_PROYECTO", nullable = true)
    private String nombreProyecto;

    /*
     * FECHA_INICIO
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_INICIO", nullable = true)
    private Date fechaInicio;

    /*
     * FECHA_TERMINO
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_TERMINO", nullable = true)
    private Date fechaTermino;

    /*
     * REGION
     */
    @Column(name = "REGION", nullable = true)
    private String region;

    /*
     * ETAPA_PROYECTO
     */
    @Column(name = "ETAPA_PROYECTO", nullable = true)
    private String etapaProyecto;

    /*
     * ANIOS_VIDA_UTIL
     */
    @Column(name = "ANIOS_VIDA_UTIL", nullable = true)
    private BigDecimal aniosVidaUtil;

    /*
     * PRESUPUESTO_GLOBAL
     */
    @Column(name = "PRESUPUESTO_GLOBAL", nullable = true)
    private BigDecimal presupuestoGlobal;

    /*
     * PRESUPUESTO_ANUAL_ESTIMADO
     */
    @Column(name = "PRESUPUESTO_ANUAL_ESTIMADO", nullable = true)
    private BigDecimal presupuestoAnualEstimado;

    /*
     * INVERSION_ACU_2018
     */
    @Column(name = "INVERSION_ACU_2018", nullable = true)
    private BigDecimal inversionAcu2018;

    /*
     * INVERSION_MES_ANTERIOR
     */
    @Column(name = "INVERSION_MES_ANTERIOR", nullable = true)
    private BigDecimal inversionMesAnterior;

    /*
     * INVERSION_MES
     */
    @Column(name = "INVERSION_MES", nullable = true)
    private BigDecimal inversionMes;

    /*
     * PRODUCCION_ANUAL
     */
    @Column(name = "PRODUCCION_ANUAL", nullable = true)
    private BigDecimal produccionAnual;

    /*
     * PORC_AVANCE_FISICO
     */
    @Column(name = "PORC_AVANCE_FISICO", nullable = true)
    private String porcAvanceFisico;

    /*
     * PROYECTO_CARTERA_MINEM
     */
    @Column(name = "PROYECTO_CARTERA_MINEM", nullable = true)
    private String proyectoCarteraMinem;

    /*
     * MINERAL_PRINCIPAL
     */
    @Column(name = "MINERAL_PRINCIPAL", nullable = true)
    private String mineralPrincipal;

    /*
     * OTROS_MINERALES
     */
    @Column(name = "OTROS_MINERALES", nullable = true)
    private String otrosMinerales;

    /*
     * EMPLEO_OPERACION
     */
    @Column(name = "EMPLEO_OPERACION", nullable = true)
    private String empleoOperacion;

    /*
     * EMPLEO_CONSTRUCCION
     */
    @Column(name = "EMPLEO_CONSTRUCCION", nullable = true)
    private String empleoConstruccion;

    /*
     * NOMBRE_INVERSIONISTA
     */
    @Column(name = "NOMBRE_INVERSIONISTA", nullable = true)
    private String nombreInversionista;

    /*
     * CONCESION_MINERA
     */
    @Column(name = "CONCESION_MINERA", nullable = true)
    private String concesionMinera;
}
