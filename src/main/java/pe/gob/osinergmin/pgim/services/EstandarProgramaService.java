package pe.gob.osinergmin.pgim.services;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEstandarPrograma;

public interface EstandarProgramaService {

	PgimEstandarPrograma getById(Long idEstandarPrograma);
    
	PgimEstandarProgramaDTO modificarEstandarPrograma(PgimEstandarPrograma pgimEstandarPrograma, 
    		AuditoriaDTO auditoriaDTO);

    PgimEstandarProgramaDTO obtenerEstandarProgramaPorId(Long idEstandarPrograma);
}
