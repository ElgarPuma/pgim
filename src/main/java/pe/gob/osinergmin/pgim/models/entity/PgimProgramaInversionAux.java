package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;

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
 * Clase Entidad para la tabla MV_OSIN_PROGRAMA_INVERS:
 * 
 * @descripción: Vista para obtener la lista de reporte de programa de inversiones
 *               
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Entity
@Table(name = "MV_OSIN_PROGRAMA_INVERS", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimProgramaInversionAux implements Serializable{
    
    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PROGRAMA_INVERS_AUX. Secuencia:
     * MV_OSIN_PROGRAMA_INVERS_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_PROGRAMA_INVERS_AUX")
    @SequenceGenerator(name = "MV_OSIN_PROGRAMA_INVERS_AUX", sequenceName = "MV_OSIN_PROGRAMA_INVERS_AUX", allocationSize = 1)
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
     * TIPO_INVERSION
     */
    @Column(name = "TIPO_INVERSION", nullable = true)
    private String tipoInversion;

    /*
     * NIVEL_DETALLE
     */
    @Column(name = "NIVEL_DETALLE", nullable = true)
    private Integer nivelDetalle;

    /*
     * ORDEN
     */
    @Column(name = "ORDEN", nullable = true)
    private Integer orden;

    /*
     * OTROS_DESCRIPCION
     */
    @Column(name = "OTROS_DESCRIPCION", nullable = true)
    private String otrosDescripcion;

    /*
     * CANTIDAD_PROGRAMADA
     */
    @Column(name = "CANTIDAD_PROGRAMADA", nullable = true)
    private Integer cantidadProgramada;
    
    /*
    * CANTIDAD_EJECUTADA
    */
    @Column(name = "CANTIDAD_EJECUTADA", nullable = true)
    private Integer cantidadEjecutada;
    
    /*
    * CAPEX_SOSTENIMIENTO
    */
    @Column(name = "CAPEX_SOSTENIMIENTO", nullable = true)
    private Integer capexSostenimiento;
    
    /*
    * CAPEX_CRECIMIENTO
    */
    @Column(name = "CAPEX_CRECIMIENTO", nullable = true)
    private Integer capexCrecimiento;
}
