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
 * Clase Entidad para la tabla MV_OSIN_TITULAR:
 * 
 * @descripción: Vista para obtener la lista de reporte de identificación y ubicación del titular
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "MV_OSIN_TITULAR", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimTitularAux implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_TITULAR. Secuencia:
     * MV_OSIN_TITULAR
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_TITULAR")
    @SequenceGenerator(name = "MV_OSIN_TITULAR", sequenceName = "MV_OSIN_TITULAR", allocationSize = 1)
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
     * ID_REPRESENTANTE
     */
    @Column(name = "ID_REPRESENTANTE", nullable = true)
    private Long idRepresentante;

    /*
     * NOMBRE_REPRESENTANTE_LEGAL
     */
    @Column(name = "NOMBRE_REPRESENTANTE_LEGAL", nullable = true)
    private String nombreRepresentanteLegal;

    /*
     * EMAIL
     */
    @Column(name = "EMAIL", nullable = true)
    private String email;

    /*
     * CARGO_REPRESENTANTE
     */
    @Column(name = "CARGO_REPRESENTANTE", nullable = true)
    private String cargoRepresentante;

    /*
     * NOMBRERESPONSABLE
     */
    @Column(name = "NOMBRERESPONSABLE", nullable = true)
    private String nombreresponsable;

    /*
     * CARGO_RESPONSABLE
     */
    @Column(name = "CARGO_RESPONSABLE", nullable = true)
    private Long cargoResponsable;

    /*
     * DESCRIPCION
     */
    @Column(name = "DESCRIPCION", nullable = true)
    private String descripcion;

    /*
     * RESP_NO_DOCUMENTO
     */
    @Column(name = "RESP_NO_DOCUMENTO", nullable = true)
    private String respNoDocumento;

    /*
     * RESP_EMAIL
     */
    @Column(name = "RESP_EMAIL", nullable = true)
    private String respEmail;

    /*
     * RESP_TELEFONO
     */
    @Column(name = "RESP_TELEFONO", nullable = true)
    private String respTelefono;
 
    /*
     * RESP_TELEFONO_MOVIL
     */
    @Column(name = "RESP_TELEFONO_MOVIL", nullable = true)
    private String respTelefonoMovil;
 
    /*
     * ID_CARGO
     */
    @Column(name = "ID_CARGO", nullable = true)
    private Long idCargo;
}
