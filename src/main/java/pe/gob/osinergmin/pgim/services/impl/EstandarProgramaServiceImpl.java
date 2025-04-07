package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEstandarPrograma;
import pe.gob.osinergmin.pgim.models.repository.EstandarProgramaRepository;
import pe.gob.osinergmin.pgim.services.EstandarProgramaService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Programa
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class EstandarProgramaServiceImpl implements EstandarProgramaService {

	@Autowired
	EstandarProgramaRepository estandarProgramaRepository;
	
	@Override
	public PgimEstandarPrograma getById(Long idEstandarPrograma) {
		return this.estandarProgramaRepository.findById(idEstandarPrograma).orElse(null);
	}
	
	@Transactional(readOnly = false)
    @Override
    public PgimEstandarProgramaDTO modificarEstandarPrograma(
    		PgimEstandarPrograma pgimEstandarPrograma, AuditoriaDTO auditoriaDTO) {
    	
		pgimEstandarPrograma.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEstandarPrograma.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEstandarPrograma.setIpActualizacion(auditoriaDTO.getTerminal());
		PgimEstandarPrograma pgimEstandarProgramaModificado = estandarProgramaRepository.save(pgimEstandarPrograma);

		PgimEstandarProgramaDTO pgimEstandarProgramaDTOModificado = this.obtenerEstandarProgramaPorId(pgimEstandarProgramaModificado.getIdEstandarPrograma());

        return pgimEstandarProgramaDTOModificado;
    }
	
	@Override
    public PgimEstandarProgramaDTO obtenerEstandarProgramaPorId(Long idEstandarPrograma) {
        return this.estandarProgramaRepository.obtenerEstandarProgramaPorId(idEstandarPrograma);
    }
	
}
