package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository;
import pe.gob.osinergmin.pgim.services.PasoProcesoService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad pasos del proceso
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 19/12/2023
 * @fecha_de_ultima_actualización: 19/12/2023
 */
@Service
@Transactional(readOnly = true)
public class PasoProcesoServiceImpl implements PasoProcesoService{

    @Autowired
    PasoProcesoRepository pasoProcesoRepository;

    @Override
    public Page<PgimPasoProcesoDTO> listarPasosPorIdProceso(Long idProceso, Pageable pageable) {
       
        Page<PgimPasoProcesoDTO> pPgimPasoProcesoDTO = this.pasoProcesoRepository.listarPasosPorIdProceso(idProceso, pageable);
        return pPgimPasoProcesoDTO;
    }
    
}
