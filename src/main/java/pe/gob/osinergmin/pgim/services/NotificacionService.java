package pe.gob.osinergmin.pgim.services;

import java.util.List;
import java.util.Map;

import gob.osinergmin.sne.domain.dto.rest.in.AccessRequestInRO;
import gob.osinergmin.sne.domain.dto.rest.in.AfiliacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.NotificacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.filter.FilterGenericInRO;
import gob.osinergmin.sne.domain.dto.rest.out.AccessResponseOutRO;
import gob.osinergmin.sne.domain.dto.rest.out.AfiliacionOutROsne;
import gob.osinergmin.sne.domain.dto.rest.out.BaseNotificacionOutRO;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;

/**
 * Interfaz que permite señalar la firma de los métodos necesarios para la
 * integración con el SNE.
 */
public interface NotificacionService {

    /**
     * Permite obtener el token para el inicio de sesión en el SNE.
     * 
     * @param accessRequestInRO
     * @return
     */
    AccessResponseOutRO iniciarSesion(AccessRequestInRO accessRequestInRO);

    /**
     * Permite obtener la información sobre la afiliación al SNE dede una
     * determinada persona descrita por el parámetro de entrada.
     * 
     * @param afiliacionInRO
     * @param token
     * @return
     */
    AfiliacionOutROsne obtenerAfiliacion(AfiliacionInRO afiliacionInRO, String token);

    /**
     * Registarr la notifiación electrónica.
     * 
     * @param notificacionInRO
     * @param token
     * @return
     */
    BaseNotificacionOutRO registrarNotificacion(NotificacionInRO notificacionInRO, String token);

    /**
     * Permite obtener el arreglo de bytes del archivo (en formato PDF) constancia
     * de la notificación.
     * 
     * @param filterGenericInRO
     * @param token
     * @return
     */
    byte[] obtenerConstanciaNotificacion(FilterGenericInRO filterGenericInRO, String token);

    /**
     * Permite realizar la notificación electrónica.
     * 
     * @param pgimInstanciaProces
     * @param lPgimDocumentoAdjuntos
     * @param pgimPersonaDestino
     * @param auditoriaDTO
     * @return
     */
    Map<String, Object> notificarElectronicamente(PgimInstanciaProces pgimInstanciaProces,
            List<PgimDocumentoDTO> lPgimDocumentoAdjuntos,
            PgimPersona pgimPersonaDestino, AuditoriaDTO auditoriaDTO);

}
