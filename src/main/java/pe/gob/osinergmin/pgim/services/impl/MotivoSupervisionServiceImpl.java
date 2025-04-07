package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.models.repository.MotivoSupervisionRepository;
import pe.gob.osinergmin.pgim.services.MotivoSupervisionService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Motivo supervision
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class MotivoSupervisionServiceImpl implements MotivoSupervisionService {

	@Autowired
    private MotivoSupervisionRepository motivoSupervisionRepository;
	
	@Override
    public List<PgimMotivoSupervisionDTO> obtenerMotivoSupervision() {
        List<PgimMotivoSupervisionDTO> lPgimObtenerMotivoSupervisionDTO = motivoSupervisionRepository.obtenerMotivoSupervision();
        return lPgimObtenerMotivoSupervisionDTO;
    }
	
	@Override
	public List<PgimMotivoSupervisionDTO> listarMotivoSupervisionByTipoSupervision(Long idTipoSupervision){
		List<PgimMotivoSupervisionDTO> lPgimMotivoSupervisionDTO = motivoSupervisionRepository.listarMotivoSupervisionByTipoSupervision(idTipoSupervision);
        return lPgimMotivoSupervisionDTO;
	}
}
