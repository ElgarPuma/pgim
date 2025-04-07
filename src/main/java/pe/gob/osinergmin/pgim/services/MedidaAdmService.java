package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMedidaAdm;

public interface MedidaAdmService {

        /**
         * Permite listar las medidas administrativas con las propiedades de filtros
         * según corresponda.
         * 
         * @param filtroMedidaAdmDTO
         * @param paginador
         * @return
         */
        Page<PgimMedidaAdmDTO> listarMedidaAdministrativa(PgimMedidaAdmDTO filtroMedidaAdmDTO, Pageable paginador, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        Page<PgimMedidaAdmDTO> listarMedidaAdministrativaSupervPas(Long idTipoObjeto, Pageable paginador)
                        throws Exception;

        Page<PgimMedidaAdmDTO> listarMedidaAdministrativaSupervision(Long idSupervision, Pageable paginador);

        Page<PgimMedidaAdmDTO> listarMedidaAdministrativaPas(Long idPas, Pageable paginador);

        Page<PgimMedidaAdmDTO> listarMedidaAdministrativaUm(Long idUnidadMinera, Pageable paginador);

        /***
         * Permite listar por codigo de medida administrativa.
         * 
         * @param palabra Palabra clave utilizada para buscar en la lista de medidas
         *                administrativas
         * @return
         */
        List<PgimMedidaAdmDTO> listarPorCoMedidaAdministrativa(String palabra);

        /***
         * Permite listar por numero de expediente siged de medida administrativa.
         * 
         * @param palabra Palabra clave utilizada para buscar en la lista de medidas
         *                administrativas
         * @return
         */
        List<PgimMedidaAdmDTO> listarPorNumeroExpediente(String nuExpediente);

        /**
         * Permite obtener las propiedades de la lista de medidas administrativas por el
         * id
         * 
         * @param idMedidaAdministrativa
         * @return
         */
        PgimMedidaAdmDTO obtenerMedidaAdministrativaPorId(Long idMedidaAdministrativa);

        PgimMedidaAdmDTO obtenerMedidaAdministrativaPorIdPas(Long idPas);

        /**
         * Permirte crear una nueva medida administrativa
         *
         * @param pgimMedidaAdmDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimMedidaAdmDTO crearMedidaAdministrativa(PgimMedidaAdmDTO pgimMedidaAdmDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /***
         * Permite obtener un objeto entidad de la medida administrativa con el valor
         * idMedidaAdministrativa.
         * 
         * @param idMedidaAdministrativa
         * @return
         */
        PgimMedidaAdm getByIdMedidaAdministrativa(Long idMedidaAdministrativa);

        /***
         * Permite modificar una medida administrativa.
         * 
         * @param pgimMedidaAdmDTO
         * @param pgimMedidaAdm
         * @param auditoriaDTO
         * @return
         */
        PgimMedidaAdmDTO modificarMedidaAdministrativa(PgimMedidaAdmDTO pgimMedidaAdmDTO, PgimMedidaAdm pgimMedidaAdm,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite eliminar una medida administrativa
         * @param pgimMedidaAdmActual
         * @param auditoriaDTO
         */
        void eliminarMedidaAdministrativa(PgimMedidaAdm pgimMedidaAdmActual, AuditoriaDTO auditoriaDTO);
}
