package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionMotivo;

public interface SupervisionMotivoService {

                
        /**
         * Permite listar los motivos de las supervisiones de la vista 
         * 
         * @param idSupervision
         * @return
         */
        Page<PgimSupervisionMotivoAuxDTO> listarSupervisionMotivoAux(Long idSupervision, Pageable paginador);

        /**
         * Permite eliminar un motivo de una supervision
         * 
         * @param pgimSupervisionMotivoActual
         * @param auditoriaDTO
         */
        void eliminarSupervisionMotivo(PgimSupervisionMotivo pgimSupervisionMotivoActual, AuditoriaDTO auditoriaDTO);

        /**
         * Permite obtener el id de un motivo de una supervision "idSupervisionMotivo"
         * 
         * @param idSupervisionMotivo
         * @return
         */
        PgimSupervisionMotivo getByIdSupervisionMotivo(Long idSupervisionMotivo);

        /**
         * Permite obtener las propiedades necesarias
         * 
         * @param idSupervisionMotivo
         * @return
         */
        PgimSupervisionMotivoDTO obtenerSupervisionMotivo(Long idSupervisionMotivo);
        
        /**
         * Permite asociar la supervision a un evento en especifico
         * 
         * @param pgimSupervisionMotivoDTO
         * @param auditoriaDTO
         * @return
         */
        PgimSupervisionMotivoDTO crearSupervisionMotivo(PgimSupervisionMotivoDTO pgimSupervisionMotivoDTO,
                        AuditoriaDTO obtenerAuditoria);
                
       
        /**
         * Permite filtrar los motivos potenciales para ser seleccionables para una fiscalización, 
         * según la unidad minera de esta
         * 
         * @param idUnidadMinera
         * @param idSupervision
         * @return
         */
        Page<PgimSupervisionMotivoAuxDTO> filtrarSeleccionMotivosPorUM(Long idUnidadMinera, Long idSupervision, 
                Pageable paginador);
}
