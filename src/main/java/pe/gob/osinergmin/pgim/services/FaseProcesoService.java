package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimFaseProceso;

public interface FaseProcesoService {

        /**
         * Listar los fases del proceso y realizar criterios de ordenamiento
         * 
         * @param sort
         * @return
         */
        List<PgimFaseProcesoDTO> listarFasesProceso(Long idProceso, String campo, Sort.Direction direccion);

        /**
         * Permite obtener la fase del proceso por el Id
         * 
         * @param idFaseProceso
         * @return
         */
        public PgimFaseProcesoDTO obtenerFaseProcesoPorId(Long idFaseProceso);

        /**
         * Me permite crear la fase del proceso
         * 
         * @param pgimFaseProcesoDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        public PgimFaseProcesoDTO crearFaseProceso(PgimFaseProcesoDTO pgimFaseProcesoDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /***
         * Permite modificar la fase del proceso
         * 
         * @param pgimFaseProcesoDTO
         * @param pgimFaseProceso
         * @param auditoriaDTO
         * @return
         */
        PgimFaseProcesoDTO modificarFaseProceso(PgimFaseProcesoDTO pgimFaseProcesoDTO, PgimFaseProceso pgimFaseProceso,
                        AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite obtener el ID de la fase del proceso
         * 
         * @param idFaseProceso
         * @return
         */
        PgimFaseProceso getByIdFaseProceso(Long idFaseProceso) throws Exception;

        /**
         * Permite eliminar logicamente la fase de flujos de trabajo del proceso
         * 
         * @param pgimFaseProcesoActual
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        void eliminarFaseProceso(PgimFaseProceso pgimFaseProcesoActual, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite validar la eliminación de una fase de proceso asociado a una tarea
         * existente
         * 
         * @param pgimFaseProcesoActual
         * @param auditoriaDTO
         * @throws Exception
         */
        public void validarEliminacionFaseProceso(PgimFaseProceso pgimFaseProcesoActual, AuditoriaDTO auditoriaDTO) throws Exception;

}
