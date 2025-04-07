package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.models.repository.SubTipoSupervisionRepository;
import pe.gob.osinergmin.pgim.services.SubTipoSupervisionService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Subtipo supervision
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020 
 */
@Service
@Transactional(readOnly = true)
public class SubTipoSupervisionServiceImpl implements SubTipoSupervisionService {
   
	@Autowired
    private SubTipoSupervisionRepository subTipoSupervisionRepository;

    @Override
    public List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervision() {
        List<PgimSubtipoSupervisionDTO> lPgimPrgrmSupervisionDTO = subTipoSupervisionRepository.obtenerSubTipoSupervision();
        return lPgimPrgrmSupervisionDTO;
    }
    
    @Override
    public List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervisionPorIdEspecialidad(Long idEspecialidad) {
        List<PgimSubtipoSupervisionDTO> lPgimPrgrmSupervisionDTO = subTipoSupervisionRepository.obtenerSubTipoSupervisionPorIdEspecialidad(idEspecialidad);
        return lPgimPrgrmSupervisionDTO;
    }

    @Override
    public List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervision(Long idTipoSupervision) {
        List<PgimSubtipoSupervisionDTO> lPgimPrgrmSupervisionDTO = subTipoSupervisionRepository.obtenerSubTipoSupervision(idTipoSupervision);
        return lPgimPrgrmSupervisionDTO;
    }

}