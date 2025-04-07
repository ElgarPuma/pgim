package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.models.entity.Ubigeo;

public interface UbigeoService {

	Iterable<Ubigeo> listarUbigeos();
	
	Page<Ubigeo> listarPageable(Pageable pageable);

	Ubigeo obtenerUbigeo(String id);
	
	List<Ubigeo> filtrarPorNombre(String nombre);

	Ubigeo registrarUbigeo(Ubigeo ubigeo);
	
	Ubigeo actualizarUbigeo(Ubigeo ubigeo);
	
	void eliminarUbigeo(String id);

	/**
	 * Permite buscar los ubigeos por palabra clave.
	 * @param palabra
	 * @return
	 */
	List<PgimUbigeoDTO> listarPorPalabraClave(String palabra);
	
	List<PgimUbigeoDTO> listarPorPalabraClaveAlternativo(String palabra);
	
	List<PgimUbigeoDTO> obtenerUbigeoPorIdSupervision(Long idSupervision);

}
