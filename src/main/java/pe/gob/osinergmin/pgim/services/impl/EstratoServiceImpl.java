package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.models.repository.EstratoRepository;
import pe.gob.osinergmin.pgim.services.EstratoService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Estrato
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 24/05/2020 
 */
@Service
@Transactional(readOnly = true)
public class EstratoServiceImpl implements EstratoService{

	@Autowired
	private EstratoRepository estratoRepository;
	
	@Override
	public List<PgimEstratoDTO> listarEstrato() {
		List<PgimEstratoDTO> lista = new ArrayList<>();
		lista = estratoRepository.listarEstrato();

		return lista;
	}
}
