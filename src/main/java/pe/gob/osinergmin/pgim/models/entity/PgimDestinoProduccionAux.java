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
 * Clase Entidad para la tabla MV_OSIN_DESTINO_PRODUCCI:
 * 
 * @descripción: Vista para obtener la lista de reporte de destino de la producción
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "MV_OSIN_DESTINO_PRODUCCI", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimDestinoProduccionAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_DESTINO_PRODUCCI. Secuencia:
     * MV_OSIN_DESTINO_PRODUCCI
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_DESTINO_PRODUCCI")
    @SequenceGenerator(name = "MV_OSIN_DESTINO_PRODUCCI", sequenceName = "MV_OSIN_DESTINO_PRODUCCI", allocationSize = 1)
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
     * PLANTA_ORIGEN
     */
    @Column(name = "PLANTA_ORIGEN", nullable = true)
    private String plantaOrigen;

    /*
     * DESTINO
     */
    @Column(name = "DESTINO", nullable = true)
    private String destino;

    /*
     * REGIONPAIS
     */
    @Column(name = "REGIONPAIS", nullable = true)
    private String regionpais;

    /*
     * DUA
     */
    @Column(name = "DUA", nullable = true)
    private String dua;

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

    /*
     * VALOR
     */
    @Column(name = "VALOR", nullable = true)
    private BigDecimal valor;

}
