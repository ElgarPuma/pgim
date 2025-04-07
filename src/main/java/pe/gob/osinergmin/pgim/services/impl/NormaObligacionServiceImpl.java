package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemSelectAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemTipificacion;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaItem;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaObligacion;
import pe.gob.osinergmin.pgim.models.repository.NormaObligacionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.NormaObligacionRepository;
import pe.gob.osinergmin.pgim.services.NormaObligacionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad norma obligación
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 30/06/2020 
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class NormaObligacionServiceImpl implements NormaObligacionService {
	@Autowired
    private NormaObligacionAuxRepository normaObligacionAuxRepository;
	
	@Autowired
	private NormaObligacionRepository normaObligacionRepository;
	
	@Override
	public Page<PgimNormaObligacionAuxDTO> filtrar(Long idItemTipificacion, Pageable paginador) {
		
		Page<PgimNormaObligacionAuxDTO> pPgimNormaObligacionDTO = this.normaObligacionAuxRepository.listar(idItemTipificacion, paginador);

        return pPgimNormaObligacionDTO;
	}
	
	
	@Transactional(readOnly = false)
    @Override
    public PgimNormaObligacionDTO crearNormaObligacion(PgimNormaObligacionDTO pgimNormObligacionDTO, AuditoriaDTO auditoriaDTO) {
        
		PgimNormaObligacion pgimNormaObligacion = new PgimNormaObligacion();
		
		PgimItemTipificacion pgimItemTipificacion = new PgimItemTipificacion();
        pgimItemTipificacion.setIdItemTipificacion(pgimNormObligacionDTO.getIdItemTipificacion());
        
        PgimNormaItem pgimNormaItem = new PgimNormaItem();
        pgimNormaItem.setIdNormaItem(pgimNormObligacionDTO.getIdNormaItem());
        
        pgimNormaObligacion.setPgimItemTipificacion(pgimItemTipificacion);
        pgimNormaObligacion.setPgimNormaItem(pgimNormaItem);
        pgimNormaObligacion.setDeNormaObligacionT(pgimNormObligacionDTO.getDeNormaObligacionT());
        pgimNormaObligacion.setEsVigente(pgimNormObligacionDTO.getEsVigente());

        pgimNormaObligacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimNormaObligacion.setFeCreacion(auditoriaDTO.getFecha());
        pgimNormaObligacion.setUsCreacion(auditoriaDTO.getUsername());
        pgimNormaObligacion.setIpCreacion(auditoriaDTO.getTerminal());
        
        
        PgimNormaObligacion pgimNormaObligacionCreado = normaObligacionRepository.save(pgimNormaObligacion);
        
        
        PgimNormaObligacionDTO pgimNormaObligacionDTOCreado = this.obtenerNormaObligacionPorId(pgimNormaObligacionCreado.getIdNormaObligacion());

        return pgimNormaObligacionDTOCreado;
        
    }

	
	@Override
    public PgimNormaObligacionDTO obtenerNormaObligacionPorId(Long idNormaObligacion) {
        return this.normaObligacionRepository.obtenerNormaObligacionPorId(idNormaObligacion);
	}


	@Transactional(readOnly = false)
    @Override
	public PgimNormaItemSelectAuxDTO crearNormaObligacionList(PgimNormaItemSelectAuxDTO pgimNormaItemSelectAuxDTO, AuditoriaDTO auditoriaDTO) {
		
		PgimNormaItemSelectAuxDTO pgimNormaItemSelectAuxDTOCreado = new PgimNormaItemSelectAuxDTO();
		List<PgimNormaObligacionDTO> lPgimNormaObligacionDTO = new ArrayList<PgimNormaObligacionDTO>(); 
		
		//registramos las obligaciones seleccionados
		for (PgimNormaObligacionDTO pgimNormObligacionDTO : pgimNormaItemSelectAuxDTO.getAuxListaNormaObligacion()) {
			
			PgimNormaObligacion pgimNormaObligacion = new PgimNormaObligacion();
			
			PgimItemTipificacion pgimItemTipificacion = new PgimItemTipificacion();
	        pgimItemTipificacion.setIdItemTipificacion(pgimNormObligacionDTO.getIdItemTipificacion());
	        
	        PgimNormaItem pgimNormaItem = new PgimNormaItem();
	        pgimNormaItem.setIdNormaItem(pgimNormObligacionDTO.getIdNormaItem());
	        
	        pgimNormaObligacion.setPgimItemTipificacion(pgimItemTipificacion);
	        pgimNormaObligacion.setPgimNormaItem(pgimNormaItem);
	        pgimNormaObligacion.setDeNormaObligacionT(pgimNormObligacionDTO.getDeNormaObligacionT());
	        pgimNormaObligacion.setEsVigente(pgimNormObligacionDTO.getEsVigente());

	        pgimNormaObligacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
	        pgimNormaObligacion.setFeCreacion(auditoriaDTO.getFecha());
	        pgimNormaObligacion.setUsCreacion(auditoriaDTO.getUsername());
	        pgimNormaObligacion.setIpCreacion(auditoriaDTO.getTerminal());
	        
	        PgimNormaObligacion pgimNormaObligacionCreado = normaObligacionRepository.save(pgimNormaObligacion);
	        PgimNormaObligacionDTO pgimNormaObligacionDTOCreado = this.obtenerNormaObligacionPorId(pgimNormaObligacionCreado.getIdNormaObligacion());
	        lPgimNormaObligacionDTO.add(pgimNormaObligacionDTOCreado);
	        
		}
		
		pgimNormaItemSelectAuxDTOCreado.setAuxListaNormaObligacion(lPgimNormaObligacionDTO);
		
		return pgimNormaItemSelectAuxDTOCreado;
				
	}
	
	@Transactional(readOnly = false)
	@Override
	public void eliminarNormaObligacion(PgimNormaObligacionDTO pgimNormaObligacionDTO, AuditoriaDTO auditoriaDTO) {
		
		PgimNormaObligacion pgimNormaObligacion = this.normaObligacionRepository.findById(pgimNormaObligacionDTO.getIdNormaObligacion()).orElse(null);
					
		pgimNormaObligacion.setEsRegistro(ConstantesUtil.IND_INACTIVO);		
		pgimNormaObligacion.setFeActualizacion(auditoriaDTO.getFecha());
		pgimNormaObligacion.setUsActualizacion(auditoriaDTO.getUsername());
		pgimNormaObligacion.setIpActualizacion(auditoriaDTO.getTerminal());

		this.normaObligacionRepository.save(pgimNormaObligacion);
	}
	
	
	@Override
    public PgimNormaObligacionAuxDTO obtenerNormaObligacionAuxPorId(Long idNormaObligacion) {
        return this.normaObligacionAuxRepository.obtenerNormaObligacionAuxPorId(idNormaObligacion);
	}
	
	
	@Override
	public PgimNormaObligacion getByIdNormaObligacion(Long idNormaObligacion) {
		log.info("getByIdNormaObligacion-Service"+ idNormaObligacion);
		return this.normaObligacionRepository.findById(idNormaObligacion).orElse(null);
	}
	
	
	
	@Transactional(readOnly = false)
    @Override
    public PgimNormaObligacionAuxDTO modificarNormaObligacion(PgimNormaObligacionDTO pgimNormaObligacionDTO, PgimNormaObligacion pgimNormaObligacion, AuditoriaDTO auditoriaDTO) {
    	
		log.info("modificacion-Service");
		PgimItemTipificacion pgimItemTipificacion = new PgimItemTipificacion();
        pgimItemTipificacion.setIdItemTipificacion(pgimNormaObligacionDTO.getIdItemTipificacion());
        
        PgimNormaItem pgimNormaItem = new PgimNormaItem();
        pgimNormaItem.setIdNormaItem(pgimNormaObligacionDTO.getIdNormaItem());
        
        pgimNormaObligacion.setDeNormaObligacionT(pgimNormaObligacionDTO.getDeNormaObligacionT());
        pgimNormaObligacion.setEsVigente(pgimNormaObligacionDTO.getEsVigente());

        pgimNormaObligacion.setFeCreacion(auditoriaDTO.getFecha());
        pgimNormaObligacion.setUsCreacion(auditoriaDTO.getUsername());
        pgimNormaObligacion.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimNormaObligacion pgimNormaObligacionModificado = normaObligacionRepository.save(pgimNormaObligacion);
        
        PgimNormaObligacionAuxDTO pgimNormaObligacionAuxDTOModificado = this.obtenerNormaObligacionAuxPorId(pgimNormaObligacionModificado.getIdNormaObligacion());
        
        return pgimNormaObligacionAuxDTOModificado;
    }
	
	
}



