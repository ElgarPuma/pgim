package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEventoDTO;

public interface EventoService {

	List<PgimEventoDTO> listarEvento(Long idUnidadMinera);

	PgimEventoDTO obtenerEventoById(Long idEvento);

	PgimEventoDTO registrarEvento(PgimEventoDTO eventoDTO, AuditoriaDTO auditoriaDTO);

	Long eliminarEvento(Long idEvento, AuditoriaDTO auditoriaDTO);
	
	PgimEventoDTO crearEvento(PgimEventoDTO eventoDTO, AuditoriaDTO auditoriaDTO);
		
    PgimEventoDTO modificarEvento(PgimEventoDTO eventoDTO, AuditoriaDTO auditoriaDTO);
    
    
    Page<PgimEventoAuxDTO> listarEventosReporte(PgimEventoAuxDTO filtroPgimEventoAuxDTO, Pageable paginador) throws Exception;
    
    List<PgimEventoAuxDTO> listarEventosTotal(PgimEventoAuxDTO filtroPgimEventoAuxDTO) throws Exception;
    
    //List<PgimEventoAuxDTO> listarAniosEvento() throws Exception;

}
