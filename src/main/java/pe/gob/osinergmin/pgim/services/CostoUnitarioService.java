package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;

public interface CostoUnitarioService {

	PgimCostoUnitarioDTO crearCostoUnitario(PgimCostoUnitarioDTO pgimCostoUnitarioDTO, AuditoriaDTO auditoriaDTO);

	PgimCostoUnitarioDTO modificarCostoUnitario(PgimCostoUnitarioDTO pgimCostoUnitarioDTO, AuditoriaDTO auditoriaDTO);
	
    PgimCostoUnitarioDTO obtenerCostoUnitarioPorId(Long idCostoUnitario);
    
    List<PgimCostoUnitarioDTO> obtenerCostoUnitarioPorIdTarifarioContrato(Long idTarifarioContrato);
}
