package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimMonitoreoComponenteDTOResultado;


public interface MonitoreoComponenteService {

	public List<PgimMonitoreoComponenteDTOResultado> listarMonitoreoComponenteDistancias(Long idComponente);
	
}
