package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimMonitoreoComponenteDTOResultado;
import pe.gob.osinergmin.pgim.models.repository.MonitoreoComponenteRepository;


import pe.gob.osinergmin.pgim.services.MonitoreoComponenteService;


@Service
@Transactional(readOnly = true)
public class MonitoreoComponenteServiceImpl  implements MonitoreoComponenteService{

	@Autowired
	MonitoreoComponenteRepository monitoreoComponenteRepository;
	
	@Override
	public List<PgimMonitoreoComponenteDTOResultado> listarMonitoreoComponenteDistancias(Long idComponente) {
		return monitoreoComponenteRepository.listarMonitoreoComponenteDistancias(idComponente);
	}
	
	
}
