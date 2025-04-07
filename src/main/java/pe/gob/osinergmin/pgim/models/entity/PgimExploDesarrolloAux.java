package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
* Clase Entidad para la tabla MV_OSIN_EXPLO_DESAR: 
* @descripción: Vista para obtener la lista de reporte de cumplimiento del programa de exploración y desarrollo
*
* @author: jvalerio
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "MV_OSIN_EXPLO_DESAR", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimExploDesarrolloAux implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_EXPLO_DESAR_AUX. Secuencia:
     * MV_OSIN_EXPLO_DESAR_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_EXPLO_DESAR_AUX")
    @SequenceGenerator(name = "MV_OSIN_EXPLO_DESAR_AUX", sequenceName = "MV_OSIN_EXPLO_DESAR_AUX", allocationSize = 1)
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
     * LABOR
     */
    @Column(name = "LABOR", nullable = true)
    private String labor;

    /*
     * EXPLORACION_CANTIDAD_EJECUTADA
     */
    @Column(name = "EXPLORACION_CANTIDAD_EJECUTADA", nullable = true)
    private BigDecimal exploracionCantidadEjecutada;

    /*
     * EXPLORACION_NRO_LABORES
     */
    @Column(name = "EXPLORACION_NRO_LABORES", nullable = true)
    private BigDecimal exploracionNroLabores;

    /*
     * DESARROLLO_CANTIDAD_EJECUTADA
     */
    @Column(name = "DESARROLLO_CANTIDAD_EJECUTADA", nullable = true)
    private BigDecimal desarrolloCantidadEjecutada;

    /*
     * DESARROLLO_NRO_LABORES
     */
    @Column(name = "DESARROLLO_NRO_LABORES", nullable = true)
    private BigDecimal desarrolloNroLabores;

    /*
     * PREPARACION_CANTIDAD_EJECUTADA
     */
    @Column(name = "PREPARACION_CANTIDAD_EJECUTADA", nullable = true)
    private BigDecimal preparacionCantidadEjecutada;

    /*
     * PREPARACION_NRO_LABORES
     */
    @Column(name = "PREPARACION_NRO_LABORES", nullable = true)
    private BigDecimal preparacionNroLabores;

    /*
     * EXPLOTACION_CANTIDAD_EJECUTADA
     */
    @Column(name = "EXPLOTACION_CANTIDAD_EJECUTADA", nullable = true)
    private BigDecimal explotacionCantidadEjecutada;

    /*
     * EXPLOTACION_NRO_LABORES
     */
    @Column(name = "EXPLOTACION_NRO_LABORES", nullable = true)
    private BigDecimal explotacionNroLabores;
}
