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
 * Clase Entidad para la tabla MV_OSIN_CARBO_DESTINO:
 * 
 * @descripción: Vista para obtener la lista de reporte de destino de la producción carbonífera
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "MV_OSIN_CARBO_DESTINO", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimCarboniferaDestinoAux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_CARBO_DESTINO. Secuencia:
     * MV_OSIN_CARBO_DESTINO
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_CARBO_DESTINO")
    @SequenceGenerator(name = "MV_OSIN_CARBO_DESTINO", sequenceName = "MV_OSIN_CARBO_DESTINO", allocationSize = 1)
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
     * RECURSO_EXTRAIDO
     */
    @Column(name = "RECURSO_EXTRAIDO", nullable = true)
    private String recursoExtraido;

    /*
     * DESTINO
     */
    @Column(name = "DESTINO", nullable = true)
    private String destino;

    /*
     * PAIS
     */
    @Column(name = "PAIS", nullable = true)
    private String pais;

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

    /*
     * ID_MONEDA
     */
    @Column(name = "ID_MONEDA", nullable = true)
    private String idMoneda;
}
