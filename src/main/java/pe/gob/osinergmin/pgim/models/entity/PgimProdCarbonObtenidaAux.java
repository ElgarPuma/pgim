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
 * Clase Entidad para la tabla MV_OSIN_PRODUC_CARBON_OBT.:
 * 
 * @descripción: Vista para obtener la lista de reporte de producción carbonífera
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "MV_OSIN_PRODUC_CARBON_OBT", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimProdCarbonObtenidaAux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_PRODUC_CARBON_OBT. Secuencia:
     * MV_OSIN_PRODUC_CARBON_OBT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_PRODUC_CARBON_OBT")
    @SequenceGenerator(name = "MV_OSIN_PRODUC_CARBON_OBT", sequenceName = "MV_OSIN_PRODUC_CARBON_OBT", allocationSize = 1)
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
     * CATEGORIA
     */
    @Column(name = "CATEGORIA", nullable = true)
    private String categoria;

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
     * CODIGO_INTEGRANTE_UEA
     */
    @Column(name = "CODIGO_INTEGRANTE_UEA", nullable = true)
    private String codigoIntegranteUea;

    /*
     * UNIDAD_INTEGRANTE
     */
    @Column(name = "UNIDAD_INTEGRANTE", nullable = true)
    private String unidadIntegrante;

    /*
     * REGION
     */
    @Column(name = "REGION", nullable = true)
    private String region;

    /*
     * PROVINCIA
     */
    @Column(name = "PROVINCIA", nullable = true)
    private String provincia;

    /*
     * DISTRITO
     */
    @Column(name = "DISTRITO", nullable = true)
    private String distrito;

    /*
     * PRODUCTO
     */
    @Column(name = "PRODUCTO", nullable = true)
    private String producto;

    /*
     * ID_UNIDAD_MEDIDA
     */
    @Column(name = "ID_UNIDAD_MEDIDA", nullable = true)
    private String idUnidadMedida;

    /*
     * DESCRIPCION
     */
    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    /*
     * CANTIDAD
     */
    @Column(name = "CANTIDAD", nullable = true)
    private BigDecimal cantidad;

}
