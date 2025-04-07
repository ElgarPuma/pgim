package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.models.entity.Ubigeo;
import pe.gob.osinergmin.pgim.models.repository.UbigeoRepository;
import pe.gob.osinergmin.pgim.models.repository.UbigeoRepositoryAlternativo;
import pe.gob.osinergmin.pgim.services.UbigeoService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Ubigeo
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020
 */
@Service
@Transactional(readOnly = true)
public class UbigeoServiceImpl implements UbigeoService {

	@Autowired
	private UbigeoRepositoryAlternativo ubigeoRepositoryAlternativo;

	@Autowired
	private UbigeoRepository ubigeoRepository;

	@Override
	public Iterable<Ubigeo> listarUbigeos() {
		return ubigeoRepositoryAlternativo.findAll();
	}

	@Override
	public Ubigeo obtenerUbigeo(String id) {
		return ubigeoRepositoryAlternativo.findById(id).orElse(null);
	}

	@Override
	public List<Ubigeo> filtrarPorNombre(String nombre) {
		return ubigeoRepositoryAlternativo.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = false)
	public Ubigeo registrarUbigeo(Ubigeo ubigeo) {
		return ubigeoRepositoryAlternativo.save(ubigeo);
	}

	@Override
	@Transactional(readOnly = false)
	public Ubigeo actualizarUbigeo(Ubigeo ubigeo) {

		Ubigeo ubigeoM = obtenerUbigeo(ubigeo.getIdUbigeo());

		if (null == ubigeoM) {
			return null;
		}
		ubigeoM.setNombre(ubigeo.getNombre());

		return ubigeoRepositoryAlternativo.save(ubigeoM);
	}

	@Override
	@Transactional(readOnly = false)
	public void eliminarUbigeo(String id) {
		ubigeoRepositoryAlternativo.deleteById(id);
	}

	@Override
	public Page<Ubigeo> listarPageable(Pageable pageable) {
		return ubigeoRepositoryAlternativo.findAll(pageable);
	}

	@Override
	public List<PgimUbigeoDTO> listarPorPalabraClave(String palabra) {
		return this.ubigeoRepository.listarPorPalabraClave(palabra);
	}
	
	@Override
	public List<PgimUbigeoDTO> listarPorPalabraClaveAlternativo(String palabra) {
		return this.ubigeoRepository.listarPorPalabraClaveAlternativo(palabra);
	}
	
	@Override
    public List<PgimUbigeoDTO> obtenerUbigeoPorIdSupervision(Long idSupervision) {
        return ubigeoRepository.obtenerUbigeoPorIdSupervision(idSupervision);
    }

}
