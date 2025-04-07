package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;

public interface PasoProcesoService {

    /**
     * Me permite listar y paginar las tareas existentes de un flujo de trabajo del proceso
     * @param idProceso
     * @param pageable
     * @return
     */
    Page<PgimPasoProcesoDTO> listarPasosPorIdProceso(Long idProceso, Pageable pageable);

}
