package pe.gob.osinergmin.pgim.services;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.RevisionInforme;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumentoRelacion;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaObservacion;

public interface FichaObservacionService {

        /**
         * Permite obtener las propiedades necesarias de la ficha de aprobacion
         * 
         * @param idFichaObservacion
         * @return
         */
        PgimFichaObservacionDTO obtenerFichaAprobacionPorId(Long idFichaObservacion);

    /**
     * Permirte crear una ficha de aprobacion
     * 
     * @param pgimFichaObservacionDTO
     * @return
     */
//     PgimFichaObservacionDTO crearFichaAprobacion(@Valid PgimFichaObservacionDTO pgimFichaObservacionDTO,
        //     AuditoriaDTO auditoriaDTO);

        // PgimFichaObservacionDTO crearFichaAprobacion(@Valid PgimFichaObservacionDTO
        // pgimFichaObservacionDTO,
        // AuditoriaDTO auditoriaDTO);
        RevisionInforme crearFichaAprobacionInforme(RevisionInforme observacionInforme, AuditoriaDTO auditoriaDTO);
        PgimDocumento getDocumentoByIdDocumento(Long idDocumento);
        PgimDocumentoRelacion getDocumentoRelacionByIdDocumentoRelacion(Long idDocumentoRelacion);
        PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorId(Long idDocumentoRelacion);
        PgimFichaRevisionDTO obtenerFichaRevisionPorId(Long idFichaRevision);

        
        /**
         * Permite obtener el id de la ficha de aprobacion de acuerdo con su
         * identificador interno.
         * 
         * @param idFichaObservacion
         * @return
         */
        PgimFichaObservacion getByIdFichaObservacion(Long idFichaObservacion);

        /**
         * Permite modificar los datos de una ficha de aprobacion
         * 
         * @param pgimFichaObservacionDTO
         * @param pgimFichaObservacionActual
         * @return
         */
        PgimFichaObservacionDTO modificarFichaAprobacion(@Valid PgimFichaObservacionDTO pgimFichaObservacionDTO,
                        PgimFichaObservacion pgimFichaObservacionActual, AuditoriaDTO auditoriaDTO);

        /**
         * Permiote eliminar una ficha de aprobacion
         * 
         * @param pgimFichaObservacionActual
         */
        void eliminarFichaAprobacion(PgimFichaObservacion pgimFichaObservacionActual, AuditoriaDTO auditoriaDTO);
}
