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
 * Clase Entidad para la tabla MV_OSIN_IDENT_UNID_MINERA:
 * 
 * @descripción: Vista para obtener la lista de reporte de identificación de concesiones y/o UEA
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "MV_OSIN_IDENT_UNID_MINERA", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimIdentUnidMineraAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_IDENT_UNID_MINERA. Secuencia:
     * MV_OSIN_IDENT_UNID_MINERA
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_IDENT_UNID_MINERA")
    @SequenceGenerator(name = "MV_OSIN_IDENT_UNID_MINERA", sequenceName = "MV_OSIN_IDENT_UNID_MINERA", allocationSize = 1)
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
     * ID_CLASE_SUSTANCIA
     */
    @Column(name = "ID_CLASE_SUSTANCIA", nullable = true)
    private String idClaseSustancia;

    /*
     * ID_SITUACIONUP
     */
    @Column(name = "ID_SITUACIONUP", nullable = true)
    private String idSituacionup;

    /*
     * DES_SITUACIONUP
     */
    @Column(name = "DES_SITUACIONUP", nullable = true)
    private String desSituacionup;

}