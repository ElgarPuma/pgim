package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_DOC_ETIQUETA_NOTIF: 
* @descripción: Configuración de relación fin de etiquetas para notificación
*
* @author: LEGION
* @version: 1.0
* @fecha_de_creación: 09/02/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCfgEtiquetaNotifDTO {

   /*
    *Identificador interno del documento etiqueta notifica. Secuencia: PGIM_SEQ_CFG_ETIQUETA_NOTIF
    */
    private Long idCfgEtiquetaNotif;

    /*
    *Identificador interno del documento etiquetado. Tabla padre: PGIM_TM_PASO_PROCESO
    */
    private Long idPasoProceso;

    /*
    *Identificador interno de la instancia de paso. Tabla padre: PGIM_TM_RELACION_PASO
    */
    private Long idRelacionPaso;

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