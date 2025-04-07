package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository;
import pe.gob.osinergmin.pgim.services.EqpInstanciaProService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Equipo instancia proceso
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class EqpInstanciaProServiceImpl implements EqpInstanciaProService {

	@Autowired
	private EquipoInstanciaProcesoRepository eqpInstanciaProRepository;
	
	@Override
    public List<PgimEqpInstanciaProDTO> obtenerPersonalXRolOsi(Long idInstanciaProceso, Long idRolProceso) {
        return eqpInstanciaProRepository.obtenerPersonalXRolOsi(idInstanciaProceso, idRolProceso);
    }
    
    @Override
    public List<PgimEqpInstanciaProDTO> obtenerPersonalXRolContrato(Long idInstanciaProceso, Long idRolProceso) {
        return eqpInstanciaProRepository.obtenerPersonalXRolContrato(idInstanciaProceso, idRolProceso);
    }

    @Override
    public List<PgimEqpInstanciaProDTO> obtenerPersonalResponsableXRolOsi(Long idInstanciaProceso, Long idRolProceso) {
        return eqpInstanciaProRepository.obtenerPersonalResponsableXRolOsi(idInstanciaProceso, idRolProceso);
    }
}
