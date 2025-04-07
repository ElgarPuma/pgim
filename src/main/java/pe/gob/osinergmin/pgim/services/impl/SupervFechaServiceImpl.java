package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervFecha;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.SupervFechaRepository;
import pe.gob.osinergmin.pgim.services.SupervFechaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Supervision fecha
 * 
 * @author: humbertoruiz90
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020 
 */
@Service
@Transactional(readOnly = true)
public class SupervFechaServiceImpl implements SupervFechaService{

	@Autowired
    private SupervFechaRepository supervFechaRepository;
	
	@Transactional(readOnly = false)
    @Override
    public PgimSupervFechaDTO crearSupervFecha(PgimSupervFechaDTO pgimSupervFechaDTO, AuditoriaDTO auditoriaDTO) {
        PgimSupervFecha pgimSupervFecha = new PgimSupervFecha();

        PgimSupervision pgimSupervision = new PgimSupervision();
        pgimSupervision.setIdSupervision(pgimSupervFechaDTO.getIdSupervision());
        pgimSupervFecha.setPgimSupervision(pgimSupervision);
        
        PgimValorParametro tipoFecha = new PgimValorParametro();
        tipoFecha.setIdValorParametro(pgimSupervFechaDTO.getIdTipoFecha());
        pgimSupervFecha.setTipoFecha(tipoFecha);
        
        pgimSupervFecha.setFeInicioSupervision(pgimSupervFechaDTO.getFeInicioSupervision());
        pgimSupervFecha.setFeFinSupervision(pgimSupervFechaDTO.getFeFinSupervision());
        pgimSupervFecha.setDeMotivoCambio(pgimSupervFechaDTO.getDeMotivoCambio());

        pgimSupervFecha.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimSupervFecha.setFeCreacion(auditoriaDTO.getFecha());
        pgimSupervFecha.setUsCreacion(auditoriaDTO.getUsername());
        pgimSupervFecha.setIpCreacion(auditoriaDTO.getTerminal());
        PgimSupervFecha pgimSupervFechaCreada = supervFechaRepository.save(pgimSupervFecha);
        
        modificarEstadosSupervFecha(pgimSupervFechaCreada.getPgimSupervision().getIdSupervision(), 
        		pgimSupervFechaCreada.getIdSupervFecha(), pgimSupervFechaDTO.getIdTipoFecha(), auditoriaDTO);        

        PgimSupervFechaDTO pgimSupervFechaDTOCreada = obtenerSupervFechaByIdSupervFecha(pgimSupervFechaCreada.getIdSupervFecha());

        return pgimSupervFechaDTOCreada;
    }
	
	@Transactional(readOnly = false)
	@Override
    public void modificarEstadosSupervFecha(Long idSupervision, Long idSupervFecha, Long idTipoFecha, AuditoriaDTO auditoriaDTO) {
		List<PgimSupervFechaDTO> lista = supervFechaRepository.listaObtenerSupervFechaByIdSupervisionAndIdSupervFecha(idSupervision, idSupervFecha, idTipoFecha);
		if (lista != null && lista.size() > 0) {
			for (PgimSupervFechaDTO pgimSupervFechaDTO : lista) {
				PgimSupervFecha pgimSupervFecha = supervFechaRepository.findById(pgimSupervFechaDTO.getIdSupervFecha()).orElse(null);
				pgimSupervFecha.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				pgimSupervFecha.setFeActualizacion(auditoriaDTO.getFecha());
		        pgimSupervFecha.setUsActualizacion(auditoriaDTO.getUsername());
		        pgimSupervFecha.setIpActualizacion(auditoriaDTO.getTerminal());
				supervFechaRepository.save(pgimSupervFecha);
			}
		}
	}
	
	
	@Override
    public PgimSupervFechaDTO obtenerSupervFechaByIdSupervFecha(Long idSupervFecha) {
        return supervFechaRepository.obtenerSupervFechaByIdSupervFecha(idSupervFecha);
    }
}
