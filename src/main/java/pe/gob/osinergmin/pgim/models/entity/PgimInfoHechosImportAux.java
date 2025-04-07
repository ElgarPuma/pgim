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
 * Clase Entidad para la tabla MV_OSIN_HECHOS_IMPORTAN.:
 * 
 * @descripción: Vista para obtener la lista de reporte de información de hechos de importancia
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "MV_OSIN_HECHOS_IMPORTAN", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimInfoHechosImportAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * ID_CLIENTE
     * Identificador interno de la vista MV_OSIN_HECHOS_IMPORTAN. Secuencia:
     * MV_OSIN_HECHOS_IMPORTAN
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MV_OSIN_HECHOS_IMPORTAN")
    @SequenceGenerator(name = "MV_OSIN_HECHOS_IMPORTAN", sequenceName = "MV_OSIN_HECHOS_IMPORTAN", allocationSize = 1)
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
     * HECHOS_AFECTAN_DECLARACION
     */
    @Column(name = "HECHOS_AFECTAN_DECLARACION", nullable = true)
    private String hechosAfectanDeclaracion;

    /*
     * INFO_GESTION_SOCIAL
     */
    @Column(name = "INFO_GESTION_SOCIAL", nullable = true)
    private String infoGestionSocial;

}
