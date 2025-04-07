package pe.gob.osinergmin.pgim.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTdrPlantilla;

public interface TdrPlantillaService {

        /**
         * Lista de plantillas de TDR base
         * 
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimTdrPlantillaDTO> listarTdrBase(PgimTdrPlantillaDTO filtro, Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista de plantillas de TDR base por el id.
         * 
         * @param idTdrPlantilla
         * @return
         */
        PgimTdrPlantillaDTO obtenerTdrBasePorId(Long idTdrPlantilla);

        /**
         * Permite obtener la lista de plantillas de TDR base de acuerdo con su
         * identificador interno.
         * 
         * @param idTdrPlantilla
         * @return
         */
        PgimTdrPlantilla getByIdTdrBase(Long idTdrPlantilla);

        /**
         * Permirte crear plantillas de TDR base.
         * 
         * @param pgimTdrPlantillaDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimTdrPlantillaDTO crearTdrBase(PgimTdrPlantillaDTO pgimTdrPlantillaDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite modificar plantillas de TDR base.
         * 
         * @param pgimTdrPlantillaDTO
         * @param pgimTdrPlantilla
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimTdrPlantillaDTO modificarTdrBase(@Valid PgimTdrPlantillaDTO pgimTdrPlantillaDTO,
                        PgimTdrPlantilla pgimTdrPlantilla,
                        AuditoriaDTO auditoriaDTO) throws Exception;

        void eliminarTdrBase(PgimTdrPlantilla pgimTdrPlantillaActual, AuditoriaDTO auditoriaDTO);
}
