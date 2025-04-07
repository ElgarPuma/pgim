package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
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
* Clase Entidad para la tabla PGIM_TM_CORRELATIVO_TABLA: 
* @descripción: Correlativo de códigos de negocio para tipos de objetos
*
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 27/06/2023
*/
@Entity
@Table(name = "PGIM_TM_CORRELATIVO_TABLA")
@Data
@NoArgsConstructor
public class PgimCorrelativoTabla implements Serializable{

    private static final long serialVersionUID = 1L;

    /*
    *Identificador interno del correlativo de la supervisión. Secuencia: PGIM_SEQ_CORRELATIVO_TABLA
    */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_CORRELATIVO_TABLA")
    @SequenceGenerator(name = "PGIM_SEQ_CORRELATIVO_TABLA", sequenceName = "PGIM_SEQ_CORRELATIVO_TABLA", allocationSize = 1)
    @Column(name = "ID_CORRELATIVO_TABLA", nullable = false)
    private Long idCorrelativoTabla;

    /*
    *Nombre de la tabla sobre la que se genera el correlativo
    */
    @Column(name = "NO_TABLA", nullable = false)
    private String noTabla;

    /*
    *Año del correlativo
    */
    @Column(name = "NU_ANIO_CORRELATIVO", nullable = false)
    private Long nuAnioCorrelativo;

    /*
    *Último correlativo generado por año
    */
    @Column(name = "SE_ULTIMO_CORRELATIVO", nullable = false)
    private Long seUltimoCorrelativo;

    /*
    *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
    */
    @Column(name = "ES_REGISTRO", nullable = false)
    private String esRegistro;

    /*
    *Usuario creador
    */
    @Column(name = "US_CREACION", nullable = false)
    private String usCreacion;

    /*
    *Terminal de creación
    */
    @Column(name = "IP_CREACION", nullable = false)
    private String ipCreacion;

    /*
    *Fecha y hora de creación
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_CREACION", nullable = false)
    private Date feCreacion;

    /*
    *Usuario modificador
    */
    @Column(name = "US_ACTUALIZACION", nullable = true)
    private String usActualizacion;

    /*
    *Terminal de modificación
    */
    @Column(name = "IP_ACTUALIZACION", nullable = true)
    private String ipActualizacion;

    /*
    *Fecha y hora de modificación
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_ACTUALIZACION", nullable = true)
    private Date feActualizacion;


    
}
