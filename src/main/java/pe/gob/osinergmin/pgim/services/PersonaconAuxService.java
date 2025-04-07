package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTO;

public interface PersonaconAuxService {
	
	List<PgimPersonaconAuxDTO> listarPersonalContratoSinDuplicadosXrol(Long idInstanciaProceso,Long idRolProceso);

}
