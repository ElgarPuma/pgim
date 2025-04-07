package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolBaseDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudBaseDoc;
import pe.gob.osinergmin.pgim.models.repository.ItemSolBaseDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SolicitudBaseDocRepository;
import pe.gob.osinergmin.pgim.services.SolicitudBaseDocService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Solicitud base documento
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020 
 */
@Service
@Transactional(readOnly = true)
public class SolicitudBaseDocServiceImpl implements SolicitudBaseDocService{
	@Autowired
	SolicitudBaseDocRepository solicitudBaseDocRepository;

	@Autowired
	ItemSolBaseDocRepository itemSolBaseDocRepository;
	
	
	@Override
	public PgimSolicitudBaseDocDTO obtenerSolicitudBaseDocPorId(Long idSolicitudBaseDoc) {
		
		return solicitudBaseDocRepository.obtenerSolicitudBaseDocPorId(idSolicitudBaseDoc);
	}
	
	@Override
	public List<PgimItemSolBaseDocDTO> listarItemsSolicitudBaseDocPorIdSolicitudBaseDoc(Long idSolicitudBaseDoc) {
		
		return itemSolBaseDocRepository.listarItemsSolicitudBaseDocPorIdSolicitudBaseDoc(idSolicitudBaseDoc);
	}
	
	
    @Override
    public Page<PgimSolicitudBaseDocDTO> listarSolicitudBaseDocPage(PgimSolicitudBaseDocDTO filtro, Pageable paginador) {
    	
             Page<PgimSolicitudBaseDocDTO> pPgimSolicitudBaseDocDTO = this.solicitudBaseDocRepository.listarSolicitudBaseDocPage(
             		filtro.getDescIdEspecialidad(),
             		filtro.getTextoBusqueda(),
             		paginador);

            return pPgimSolicitudBaseDocDTO;
    }
    
    @Override
    public PgimSolicitudBaseDoc getByIdSolicitudBaseDoc(Long idSolicitudBaseDoc) {
    	return this.solicitudBaseDocRepository.findById(idSolicitudBaseDoc).orElse(null);
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimSolicitudBaseDocDTO crearPlantilla(PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO, AuditoriaDTO auditoriaDTO) {
        PgimSolicitudBaseDoc pgimSolicitudBaseDoc = new PgimSolicitudBaseDoc();
        
        PgimConfiguracionBase pgimConfiguracionBase = new PgimConfiguracionBase();
        pgimConfiguracionBase.setIdConfiguracionBase(pgimSolicitudBaseDocDTO.getIdConfiguracionBase());
        pgimSolicitudBaseDoc.setPgimConfiguracionBase(pgimConfiguracionBase);
        
        pgimSolicitudBaseDoc.setNoSolicitudBaseDoc(pgimSolicitudBaseDocDTO.getNoSolicitudBaseDoc());
        pgimSolicitudBaseDoc.setDeSolicitudBaseDoc(pgimSolicitudBaseDocDTO.getDeSolicitudBaseDoc());
        
        pgimSolicitudBaseDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimSolicitudBaseDoc.setFeCreacion(auditoriaDTO.getFecha());
        pgimSolicitudBaseDoc.setUsCreacion(auditoriaDTO.getUsername());
        pgimSolicitudBaseDoc.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimSolicitudBaseDoc pgimSolicitudBaseDocCreada = solicitudBaseDocRepository.save(pgimSolicitudBaseDoc);
        
        
        PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTOCreada = this.obtenerSolicitudBaseDocPorId(pgimSolicitudBaseDocCreada.getIdSolicitudBaseDoc());

        return pgimSolicitudBaseDocDTOCreada;
    }
	
	
	@Transactional(readOnly = false)
    @Override
    public PgimSolicitudBaseDocDTO modificarPlantilla(PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTO, PgimSolicitudBaseDoc pgimSolicitudBaseDoc, AuditoriaDTO auditoriaDTO) {
    	
        pgimSolicitudBaseDoc.setNoSolicitudBaseDoc(pgimSolicitudBaseDocDTO.getNoSolicitudBaseDoc());
        pgimSolicitudBaseDoc.setDeSolicitudBaseDoc(pgimSolicitudBaseDocDTO.getDeSolicitudBaseDoc());
    	
        pgimSolicitudBaseDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimSolicitudBaseDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimSolicitudBaseDoc.setIpActualizacion(auditoriaDTO.getTerminal());
        
        PgimSolicitudBaseDoc pgimSolicitudBaseDocModificada = this.solicitudBaseDocRepository.save(pgimSolicitudBaseDoc);    

        PgimSolicitudBaseDocDTO pgimSolicitudBaseDocDTOModificada = this.obtenerSolicitudBaseDocPorId(pgimSolicitudBaseDocModificada.getIdSolicitudBaseDoc());

        return pgimSolicitudBaseDocDTOModificada;
    }
	
	@Transactional(readOnly = false)
    @Override
    public void eliminarPlantilla(PgimSolicitudBaseDoc pgimSolicitudBaseDocActual, AuditoriaDTO auditoriaDTO) {
        
    	pgimSolicitudBaseDocActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimSolicitudBaseDocActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimSolicitudBaseDocActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimSolicitudBaseDocActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.solicitudBaseDocRepository.save(pgimSolicitudBaseDocActual);
    }
	
	@Override
	public Integer validarTraslapePlantilla(Long idSolicitudBaseDoc, String noSolicitudBaseDoc) {
		Integer cantidad = this.solicitudBaseDocRepository.validarTraslapePlantilla(idSolicitudBaseDoc, noSolicitudBaseDoc);
		return cantidad;
	}
	
	@Override
    public PgimItemSolBaseDoc getByIdItemSolBaseDoc(Long idItemSolBaseDoc) {
    	return this.itemSolBaseDocRepository.findById(idItemSolBaseDoc).orElse(null);
    }
	
	@Override
	public PgimItemSolBaseDocDTO obtenerItemSolBaseDocPorId(Long idItemSolBaseDoc) {		
		return this.itemSolBaseDocRepository.obtenerItemSolBaseDocPorId(idItemSolBaseDoc);
	}
	
	@Transactional(readOnly = false)
    @Override
	public PgimItemSolBaseDocDTO registrarItemSolicitud(PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO, AuditoriaDTO auditoriaDTO) {
		PgimItemSolBaseDoc pgimItemSolBaseDoc = new PgimItemSolBaseDoc();
		
		PgimSolicitudBaseDoc pgimSolicitudBaseDoc = new PgimSolicitudBaseDoc();
		pgimSolicitudBaseDoc.setIdSolicitudBaseDoc(pgimItemSolBaseDocDTO.getIdSolicitudBaseDoc());
		pgimItemSolBaseDoc.setPgimSolicitudBaseDoc(pgimSolicitudBaseDoc);
		
		pgimItemSolBaseDoc.setNuOrden(pgimItemSolBaseDocDTO.getNuOrden());
		pgimItemSolBaseDoc.setDeSolicitudObservacion(pgimItemSolBaseDocDTO.getDeSolicitudObservacion());
		
		pgimItemSolBaseDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimItemSolBaseDoc.setFeCreacion(auditoriaDTO.getFecha());
		pgimItemSolBaseDoc.setUsCreacion(auditoriaDTO.getUsername());
		pgimItemSolBaseDoc.setIpCreacion(auditoriaDTO.getTerminal());
		
		PgimItemSolBaseDoc pgimItemSolBaseDocCreado = this.itemSolBaseDocRepository.save(pgimItemSolBaseDoc);
		
		PgimItemSolBaseDocDTO pgimItemSolBaseDocDTOCreado = this.itemSolBaseDocRepository.obtenerItemSolBaseDocPorId(pgimItemSolBaseDocCreado.getIdItemSolBaseDoc());
		
		return pgimItemSolBaseDocDTOCreado;
	}
	
	@Transactional(readOnly = false)
    @Override
	public PgimItemSolBaseDocDTO modificarItemSolicitud(PgimItemSolBaseDocDTO pgimItemSolBaseDocDTO, PgimItemSolBaseDoc pgimItemSolBaseDocActual, AuditoriaDTO auditoriaDTO) {
		PgimSolicitudBaseDoc pgimSolicitudBaseDoc = new PgimSolicitudBaseDoc();
		pgimSolicitudBaseDoc.setIdSolicitudBaseDoc(pgimItemSolBaseDocDTO.getIdSolicitudBaseDoc());
		pgimItemSolBaseDocActual.setPgimSolicitudBaseDoc(pgimSolicitudBaseDoc);
		
		pgimItemSolBaseDocActual.setNuOrden(pgimItemSolBaseDocDTO.getNuOrden());
		pgimItemSolBaseDocActual.setDeSolicitudObservacion(pgimItemSolBaseDocDTO.getDeSolicitudObservacion());
		
		pgimItemSolBaseDocActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimItemSolBaseDocActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimItemSolBaseDocActual.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimItemSolBaseDoc pgimItemSolBaseDocModificado = this.itemSolBaseDocRepository.save(pgimItemSolBaseDocActual);
		
		PgimItemSolBaseDocDTO pgimItemSolBaseDocDTOModificado = this.itemSolBaseDocRepository.obtenerItemSolBaseDocPorId(pgimItemSolBaseDocModificado.getIdItemSolBaseDoc());
		
		return pgimItemSolBaseDocDTOModificado;
	}
	
	@Transactional(readOnly = false)
    @Override
	public void eliminarItemSolicitud(PgimItemSolBaseDoc pgimItemSolBaseDocActual, AuditoriaDTO auditoriaDTO) {
		pgimItemSolBaseDocActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimItemSolBaseDocActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimItemSolBaseDocActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimItemSolBaseDocActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.itemSolBaseDocRepository.save(pgimItemSolBaseDocActual);
	}
}
