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
 * Clase Entidad para la tabla MV_OSIN_IND_DESEMP_OPERAT:
 * 
 * @descripción: Vista para obtener la lista de indicadores de desempeños
 *               operativo
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 07/03/2023
 */
@Entity
@Table(name = "MV_OSIN_IND_DESEMP_OPERAT", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimIndicadorDesempenioAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_IND_DESEMP_OPERAT_AUX. Secuencia:
     * MV_OSIN_IND_DESEMP_OPERAT_AUX
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_IND_DESEMP_OPERAT_AUX")
    @SequenceGenerator(name = "MV_OSIN_IND_DESEMP_OPERAT_AUX", sequenceName = "MV_OSIN_IND_DESEMP_OPERAT_AUX", allocationSize = 1)
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
     * COEFICIENTE
     */
    @Column(name = "COEFICIENTE", nullable = true)
    private String coeficiente;

    /*
     * UNIDAD_M
     */
    @Column(name = "UNIDAD_M", nullable = true)
    private String unidadM;

    /*
     * DESCRIPCION
     */
    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    /*
     * CANTIDAD
     */
    @Column(name = "CANTIDAD", nullable = true)
    private String cantidad;

    /*
     * CANTIDAD
     */
    @Column(name = "EXPLICACION", nullable = true)
    private String explicacion;

}
