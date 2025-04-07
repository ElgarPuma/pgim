package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;

public interface TarifarioReglaService {

	PgimTarifarioReglaDTO crearTarifarioRegla(PgimTarifarioReglaDTO pgimTarifarioReglaDTO, AuditoriaDTO auditoriaDTO);
		
    PgimTarifarioReglaDTO modificarTarifarioRegla(PgimTarifarioReglaDTO pgimTarifarioReglaDTO, AuditoriaDTO auditoriaDTO);
	
    PgimTarifarioReglaDTO obtenerTarifarioReglaPorId(Long idTarifarioRegla);
    
    List<PgimTarifarioReglaDTO> obtenerTarifarioReglaPorIdTarifarioContrato(Long idTarifarioContrato);
}
