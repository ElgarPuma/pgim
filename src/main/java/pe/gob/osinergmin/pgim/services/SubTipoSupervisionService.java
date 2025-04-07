package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;

public interface SubTipoSupervisionService {
   
	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervision();

	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervisionPorIdEspecialidad(Long idEspecialidad);

	List<PgimSubtipoSupervisionDTO> obtenerSubTipoSupervision(Long idTipoSupervision);
}