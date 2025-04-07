package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.LogPgimService;

/**
 * @descripción: Métodos de utilidad para el Log
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 22/12/2023
 *
 */
@Service
@Slf4j
public class LogPgimServiceImpl implements LogPgimService {
	
	@Autowired
	private FlujoTrabajoService flujoTrabajoService;
	
	@Autowired
	private InstanciaPasoRepository instanciaPasoRepository;
	
	
	@Override
	public String obtenerPrefijoLogSimple(AuditoriaDTO auditoriaDTO) {
		String prefijoLog = "";
		
		if(auditoriaDTO != null && auditoriaDTO.getUsername() != null) {
			prefijoLog = auditoriaDTO.getUsername().toUpperCase() + ".";			
		}
			
		return prefijoLog;		
	}
	
	@Override
	public String obtenerPrefijoLogPorInstanProceso(Long idInstanciaProceso, AuditoriaDTO auditoriaDTO) {
		String prefijoLog = "";
		
		if(auditoriaDTO != null && auditoriaDTO.getUsername() != null) {
			prefijoLog = auditoriaDTO.getUsername().toUpperCase() + ".";			
		}
		
		if(idInstanciaProceso != null) {
			try {
				String lblObjTrabajo = this.flujoTrabajoService.obtenerEtiquetaObjetoTrabajo(idInstanciaProceso);
				prefijoLog += lblObjTrabajo + ".";
				
			}catch (Exception e) {
				String mensaje = "Surgió un inconveniente al obtener la etiqueta del objeto de trabajo para el Log: " + e.getMessage();
				log.warn(mensaje, e); // Solo pintamos la excepción, evitamos que sea bloqueante
			}
		}
			
		return prefijoLog;		
	}
	
	@Override
	public String obtenerPrefijoLogPorInstanPaso(Long idInstanciaPaso, AuditoriaDTO auditoriaDTO) {
		String prefijoLog = "";
		
		PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);
		
		if(pgimInstanciaPaso != null && pgimInstanciaPaso.getPgimInstanciaProces() != null) {
			prefijoLog = this.obtenerPrefijoLogPorInstanProceso(
					pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso(), auditoriaDTO);
		
		}else if(auditoriaDTO != null && auditoriaDTO.getUsername() != null) {
			prefijoLog = auditoriaDTO.getUsername().toUpperCase() + ".";			
		}
			
		return prefijoLog;		
	}

}
