package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad para la tabla PGIM_TM_CORRELATIVO_TABLA: 
* @descripción: Correlativo de códigos de negocio para tipos de objetos
*
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 27/06/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCorrelativoTablaDTO {
    
    /*
    *Identificador interno del correlativo de la supervisión. Secuencia: PGIM_SEQ_CORRELATIVO_TABLA
    */
    private Long idCorrelativoTabla;

    /*
    *Nombre de la tabla sobre la que se genera el correlativo
    */
    private String noTabla;

    /*
    *Año del correlativo
    */
    private Long nuAnioCorrelativo;

    /*
    *Último correlativo generado por año
    */
    private Long seUltimoCorrelativo;

    /*
    *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
    */
    private String esRegistro;

    /*
    *Usuario creador
    */
    private String usCreacion;

    /*
    *Terminal de creación
    */
    private String ipCreacion;

    /*
    *Fecha y hora de creación
    */
    private Date feCreacion;

    /*
    *Usuario modificador
    */
    private String usActualizacion;

    /*
    *Terminal de modificación
    */
    private String ipActualizacion;

    /*
    *Fecha y hora de modificación
    */
    private Date feActualizacion;
}
