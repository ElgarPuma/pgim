package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository;
import pe.gob.osinergmin.pgim.services.RelacionPasoService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad relación paso del proceso
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/12/2023
 * @fecha_de_ultima_actualización: 20/12/2023
 */
@Service
@Transactional(readOnly = true)
public class RelacionPasoServiceImpl implements RelacionPasoService {

    @Autowired
    RelacionPasoRepository relacionPasoRepository;

    @Override
    public Page<PgimRelacionPasoDTO> listarRelacionPasoPorIdProceso(Long idProceso, Pageable page) {

        Page<PgimRelacionPasoDTO> pPgimRelacionPasoDTO = this.relacionPasoRepository.listarRelacionPasoPorIdProceso(idProceso,
                page);
        return pPgimRelacionPasoDTO;
    }

}
