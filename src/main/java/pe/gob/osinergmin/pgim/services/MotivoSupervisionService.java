package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;

public interface MotivoSupervisionService {

	List<PgimMotivoSupervisionDTO> obtenerMotivoSupervision();
	
	List<PgimMotivoSupervisionDTO> listarMotivoSupervisionByTipoSupervision(Long idTipoSupervision);
}
