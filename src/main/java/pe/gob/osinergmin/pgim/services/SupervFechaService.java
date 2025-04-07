package pe.gob.osinergmin.pgim.services;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTO;

public interface SupervFechaService {

	PgimSupervFechaDTO crearSupervFecha(PgimSupervFechaDTO pgimSupervFechaDTO, AuditoriaDTO auditoriaDTO);

	PgimSupervFechaDTO obtenerSupervFechaByIdSupervFecha(Long idSupervFecha);
	
	void modificarEstadosSupervFecha(Long idSupervision, Long idSupervFecha, Long idTipoFecha, AuditoriaDTO auditoriaDTO);
}
