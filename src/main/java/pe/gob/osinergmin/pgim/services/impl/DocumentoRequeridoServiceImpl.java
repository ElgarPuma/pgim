package pe.gob.osinergmin.pgim.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimItemRecepcionDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolicitudDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.repository.ItemRecepcionDocRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemSolicitudDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SolicitudDocRepository;
import pe.gob.osinergmin.pgim.services.DocumentoRequeridoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Documento requerido
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */

@Service
@Transactional(readOnly = true)
public class DocumentoRequeridoServiceImpl implements DocumentoRequeridoService {

    @Autowired
    private SolicitudDocRepository solicitudDocRepository;

    @Autowired
    private ItemSolicitudDocRepository itemSolicitudDocRepository;
    
    @Autowired
    private ItemRecepcionDocRepository itemRecepcionDocRepository;

    @Transactional(readOnly = false)
    @Override
    public PgimSolicitudDocDTO crearDocumentoRequerido(PgimSolicitudDocDTO pgimSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {

        // Creación del registro PgimSolicitudDoc
        PgimSolicitudDoc pgimSolicitudDoc = new PgimSolicitudDoc();

        PgimSupervision pgimSupervision = new PgimSupervision();
        pgimSupervision.setIdSupervision(pgimSolicitudDocDTO.getIdSupervision());

        pgimSolicitudDoc.setPgimSupervision(pgimSupervision);
        pgimSolicitudDoc.setFeSolicitudDocumentacion(pgimSolicitudDocDTO.getFeSolicitudDocumentacion());
        pgimSolicitudDoc.setDeSolicitudObservacion(pgimSolicitudDocDTO.getDeSolicitudObservacion());

        pgimSolicitudDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimSolicitudDoc.setFeCreacion(auditoriaDTO.getFecha());
        pgimSolicitudDoc.setUsCreacion(auditoriaDTO.getUsername());
        pgimSolicitudDoc.setIpCreacion(auditoriaDTO.getTerminal());

        PgimSolicitudDoc pgimSolicitudDocCreado = solicitudDocRepository.save(pgimSolicitudDoc);

        // Creación del registro PgimItemSolicitudDoc
        PgimItemSolicitudDoc pgimItemSolicitudDoc = new PgimItemSolicitudDoc();

        pgimItemSolicitudDoc.setPgimSolicitudDoc(pgimSolicitudDocCreado);
        pgimItemSolicitudDoc.setFeSolicitudDocumentacion(pgimSolicitudDocDTO.getFeSolicitudDocumentacion());
        pgimItemSolicitudDoc.setDeSolicitudObservacion(pgimSolicitudDocDTO.getDeSolicitudObservacion());
        pgimItemSolicitudDoc.setEsRecibido("0");

        pgimItemSolicitudDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimItemSolicitudDoc.setFeCreacion(auditoriaDTO.getFecha());
        pgimItemSolicitudDoc.setUsCreacion(auditoriaDTO.getUsername());
        pgimItemSolicitudDoc.setIpCreacion(auditoriaDTO.getTerminal());

        itemSolicitudDocRepository.save(pgimItemSolicitudDoc);

        PgimSolicitudDocDTO pgimSolicitudDocDTOCreado = solicitudDocRepository
                .obtenerSolicitudDocPorId(pgimSolicitudDocCreado.getIdSolicitudDoc());

        return pgimSolicitudDocDTOCreado;
    }

    @Override
    public PgimSolicitudDocDTO obtenerDocumentoRequerido(Long idSolicitudDoc) {
        return this.solicitudDocRepository.obtenerSolicitudAndItemSolicitudPorId(idSolicitudDoc);
    }

    @Override
    public PgimSolicitudDoc findById(Long idSolicitudDoc) {
        return this.solicitudDocRepository.findById(idSolicitudDoc).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimSolicitudDocDTO modificarDocumentoRequerido(PgimSolicitudDocDTO pgimSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimSolicitudDoc pgimSolicitudDoc = solicitudDocRepository.findById(pgimSolicitudDocDTO.getIdSolicitudDoc())
                .orElse(null);
        pgimSolicitudDoc.setFeSolicitudDocumentacion(pgimSolicitudDocDTO.getFeSolicitudDocumentacion());
        pgimSolicitudDoc.setDeSolicitudObservacion(pgimSolicitudDocDTO.getDeSolicitudObservacion());

        pgimSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        solicitudDocRepository.save(pgimSolicitudDoc);

        PgimItemSolicitudDoc pgimItemSolicitudDoc = itemSolicitudDocRepository
                .findById(pgimSolicitudDocDTO.getDescIdItemSolicitudDoc()).orElse(null);
        pgimItemSolicitudDoc.setEsRecibido(pgimSolicitudDocDTO.getDescEsRecibido());
        pgimItemSolicitudDoc.setFeRecepcionDocumentacion(pgimSolicitudDocDTO.getDescFeRecepcionDocumentacion());
        pgimItemSolicitudDoc.setDeRecepcionObservacion(pgimSolicitudDocDTO.getDescDeRecepcionObservacion());

        pgimItemSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimItemSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimItemSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        itemSolicitudDocRepository.save(pgimItemSolicitudDoc);

        PgimSolicitudDocDTO pgimSolicitudDocDTOModificado = solicitudDocRepository
                .obtenerSolicitudDocPorId(pgimSolicitudDocDTO.getIdSolicitudDoc());

        return pgimSolicitudDocDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarDocumentoRequerido(PgimSolicitudDoc pgimSolicitudDoc, AuditoriaDTO auditoriaDTO) {
        pgimSolicitudDoc.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        this.solicitudDocRepository.save(pgimSolicitudDoc);
    }

    @Override
    public List<PgimSolicitudDocDTO> listarDocumentoRequerido(Long idSupervision) {
        return this.solicitudDocRepository.listarSolicitudDocRequerido(idSupervision);
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimItemRecepcionDocDTO crearItemRecepcionDoc(PgimItemRecepcionDocDTO pgimItemRecepcionDocDTO,
            AuditoriaDTO auditoriaDTO) {

    	PgimItemRecepcionDoc pgimItemRecepcionDoc = new PgimItemRecepcionDoc();

    	PgimItemSolicitudDoc pgimItemSolicitudDoc = new PgimItemSolicitudDoc();
    	pgimItemSolicitudDoc.setIdItemSolicitudDoc(pgimItemRecepcionDocDTO.getIdItemSolicitudDoc());
    	pgimItemRecepcionDoc.setPgimItemSolicitudDoc(pgimItemSolicitudDoc);
    	
    	PgimDocumento pgimDocumento = new PgimDocumento();
    	pgimDocumento.setIdDocumento(pgimItemRecepcionDocDTO.getIdDocumento());
    	pgimItemRecepcionDoc.setPgimDocumento(pgimDocumento);

    	pgimItemRecepcionDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
    	pgimItemRecepcionDoc.setFeCreacion(auditoriaDTO.getFecha());
    	pgimItemRecepcionDoc.setUsCreacion(auditoriaDTO.getUsername());
    	pgimItemRecepcionDoc.setIpCreacion(auditoriaDTO.getTerminal());

        itemRecepcionDocRepository.save(pgimItemRecepcionDoc);

        PgimItemRecepcionDocDTO pgimItemRecepcionDocDTOCreado = itemRecepcionDocRepository
                .obtenerItemRecepcionDocPorId(pgimItemRecepcionDoc.getIdItemRecepcionDoc());

        return pgimItemRecepcionDocDTOCreado;
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimItemSolicitudDocDTO crearItemSolicitudDoc(PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {

    	PgimSolicitudDoc pgimSolicitudDocCreado = null;
//    	Verfiricamos que exista el registro en la tabla solicitudDoc
    	List<PgimSolicitudDocDTO> lPgimSolicitudDocDTO = solicitudDocRepository.validarSolicitudDocxIdSupervision(pgimItemSolicitudDocDTO.getDescIdSupervision());
//    	Si no existe el registro creamos uno
    	if(lPgimSolicitudDocDTO == null || lPgimSolicitudDocDTO.size() == 0) {
    		PgimSolicitudDoc pgimSolicitudDoc = new PgimSolicitudDoc();

            PgimSupervision pgimSupervision = new PgimSupervision();
            pgimSupervision.setIdSupervision(pgimItemSolicitudDocDTO.getDescIdSupervision());

            pgimSolicitudDoc.setPgimSupervision(pgimSupervision);
            pgimSolicitudDoc.setFeSolicitudDocumentacion(new Date());
            pgimSolicitudDoc.setDeSolicitudObservacion(pgimItemSolicitudDocDTO.getDeSolicitudObservacion());

            pgimSolicitudDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
            pgimSolicitudDoc.setFeCreacion(auditoriaDTO.getFecha());
            pgimSolicitudDoc.setUsCreacion(auditoriaDTO.getUsername());
            pgimSolicitudDoc.setIpCreacion(auditoriaDTO.getTerminal());

            pgimSolicitudDocCreado = solicitudDocRepository.save(pgimSolicitudDoc);
    	}
    	
    	PgimItemSolicitudDoc pgimItemSolicitudDoc = new PgimItemSolicitudDoc();
        
        if(pgimItemSolicitudDocDTO.getIdSolicitudDoc() != null) {
        	PgimSolicitudDoc pgimSolicitudDoc = new PgimSolicitudDoc();	
        	pgimSolicitudDoc.setIdSolicitudDoc(pgimItemSolicitudDocDTO.getIdSolicitudDoc());
        	pgimItemSolicitudDoc.setPgimSolicitudDoc(pgimSolicitudDoc);
        }else {
        	pgimItemSolicitudDoc.setPgimSolicitudDoc(pgimSolicitudDocCreado);
        }
        
        pgimItemSolicitudDoc.setFeRecepcionDocumentacion(pgimItemSolicitudDocDTO.getFeRecepcionDocumentacion());
        pgimItemSolicitudDoc.setDeRecepcionObservacion(pgimItemSolicitudDocDTO.getDeRecepcionObservacion());
        pgimItemSolicitudDoc.setFeSolicitudDocumentacion(pgimItemSolicitudDocDTO.getFeSolicitudDocumentacion());
        pgimItemSolicitudDoc.setDeSolicitudObservacion(pgimItemSolicitudDocDTO.getDeSolicitudObservacion());
        pgimItemSolicitudDoc.setNuOrden(pgimItemSolicitudDocDTO.getNuOrden());
        pgimItemSolicitudDoc.setEsRecibido("0");

        pgimItemSolicitudDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimItemSolicitudDoc.setFeCreacion(auditoriaDTO.getFecha());
        pgimItemSolicitudDoc.setUsCreacion(auditoriaDTO.getUsername());
        pgimItemSolicitudDoc.setIpCreacion(auditoriaDTO.getTerminal());

        PgimItemSolicitudDoc pgimItemSolicitudDocCreado = itemSolicitudDocRepository.save(pgimItemSolicitudDoc);

        PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOCreado = itemSolicitudDocRepository
                .obtenerItemSolicitudDocPorId(pgimItemSolicitudDocCreado.getIdItemSolicitudDoc());

        return pgimItemSolicitudDocDTOCreado;
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimItemSolicitudDocDTO modificarItemSolicitudDoc(PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimItemSolicitudDoc pgimItemSolicitudDoc = itemSolicitudDocRepository
                .findById(pgimItemSolicitudDocDTO.getIdItemSolicitudDoc()).orElse(null);
        
        pgimItemSolicitudDoc.setFeRecepcionDocumentacion(pgimItemSolicitudDocDTO.getFeRecepcionDocumentacion());
        pgimItemSolicitudDoc.setDeRecepcionObservacion(pgimItemSolicitudDocDTO.getDeRecepcionObservacion());
        pgimItemSolicitudDoc.setFeSolicitudDocumentacion(pgimItemSolicitudDocDTO.getFeSolicitudDocumentacion());
        pgimItemSolicitudDoc.setDeSolicitudObservacion(pgimItemSolicitudDocDTO.getDeSolicitudObservacion());
        pgimItemSolicitudDoc.setEsRecibido(pgimItemSolicitudDocDTO.getEsRecibido());
        pgimItemSolicitudDoc.setNuOrden(pgimItemSolicitudDocDTO.getNuOrden());

        pgimItemSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimItemSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimItemSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimItemSolicitudDoc pgimItemSolicitudDocModificado = itemSolicitudDocRepository.save(pgimItemSolicitudDoc);

        PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOModificado = itemSolicitudDocRepository
                .obtenerItemSolicitudDocPorId(pgimItemSolicitudDocModificado.getIdItemSolicitudDoc());

        return pgimItemSolicitudDocDTOModificado;
    }
    
    // STORY: PGIM-6222: Drag & drop para ordenamiento de documentos solicitados en la fisc.
    @Transactional(readOnly = false)
    @Override
    public PgimItemSolicitudDocDTO modificarOrden(PgimItemSolicitudDocDTO pgimItemSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimItemSolicitudDoc pgimItemSolicitudDoc = itemSolicitudDocRepository
                .findById(pgimItemSolicitudDocDTO.getIdItemSolicitudDoc()).orElse(null);
        
        
        pgimItemSolicitudDoc.setNuOrden(pgimItemSolicitudDocDTO.getNuOrden());

        pgimItemSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimItemSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimItemSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimItemSolicitudDoc pgimItemSolicitudDocModificado = itemSolicitudDocRepository.save(pgimItemSolicitudDoc);

        PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOModificado = itemSolicitudDocRepository
                .modificarOrden(pgimItemSolicitudDocModificado.getIdItemSolicitudDoc(), 
                                pgimItemSolicitudDocDTO.getIdSolicitudDoc(), 
                                pgimItemSolicitudDocDTO.getDescIdSupervision());

        return pgimItemSolicitudDocDTOModificado;
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimSolicitudDocDTO modificarSolicitudDoc(PgimSolicitudDocDTO pgimSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimSolicitudDoc pgimSolicitudDoc = solicitudDocRepository.findById(pgimSolicitudDocDTO.getIdSolicitudDoc())
                .orElse(null);
        pgimSolicitudDoc.setFeSolicitudDocumentacion(pgimSolicitudDocDTO.getFeSolicitudDocumentacion());
        pgimSolicitudDoc.setDeSolicitudObservacion(pgimSolicitudDocDTO.getDeSolicitudObservacion());
        pgimSolicitudDoc.setFeRecepcionDocumentacion(pgimSolicitudDocDTO.getFeRecepcionDocumentacion());
        pgimSolicitudDoc.setDeRecepcionObservacion(pgimSolicitudDocDTO.getDeRecepcionObservacion());

        pgimSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
        pgimSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
        pgimSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimSolicitudDoc pgimSolicitudDocModificado = solicitudDocRepository.save(pgimSolicitudDoc);

        PgimSolicitudDocDTO pgimSolicitudDocDTOModificado = solicitudDocRepository
                .obtenerSolicitudDocPorId(pgimSolicitudDocModificado.getIdSolicitudDoc());

        return pgimSolicitudDocDTOModificado;
    }
    
    @Override
    public PgimSolicitudDocDTO obtenerSolicitudDocxIdSupervision(Long idSupervision) {
        return solicitudDocRepository.obtenerSolicitudDocxIdSupervision(idSupervision);
    }
    
    @Override
    public List<PgimItemSolicitudDocDTO> listarItemSolicitudDocxIdSolicitudDoc(Long idSolicitudDoc) {
        return this.itemSolicitudDocRepository.listarItemSolicitudDocPorIdSolicitudDoc(idSolicitudDoc);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void eliminarItemSolicitudDoc(PgimItemSolicitudDoc pgimItemSolicitudDoc, AuditoriaDTO auditoriaDTO) {
    	pgimItemSolicitudDoc.setEsRegistro(ConstantesUtil.IND_INACTIVO);

    	pgimItemSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimItemSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimItemSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        this.itemSolicitudDocRepository.save(pgimItemSolicitudDoc);
    }
    
    @Override
    public PgimItemSolicitudDoc itemSolicitudDocfindById(Long idItemSolicitudDoc) {
        return this.itemSolicitudDocRepository.findById(idItemSolicitudDoc).orElse(null);
    }
    
    @Override
    public List<PgimItemRecepcionDocDTO> listarItemRecepcionDocxIdItemSolicitudDoc(Long idItemSolicitudDoc) {
        return this.itemRecepcionDocRepository.listarItemRecepcionDocxIdItemSolicitudDoc(idItemSolicitudDoc);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void eliminarItemRecepcionDoc(PgimItemRecepcionDoc pgimItemRecepcionDoc, AuditoriaDTO auditoriaDTO) {
    	pgimItemRecepcionDoc.setEsRegistro(ConstantesUtil.IND_INACTIVO);

    	pgimItemRecepcionDoc.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimItemRecepcionDoc.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimItemRecepcionDoc.setIpActualizacion(auditoriaDTO.getTerminal());

        this.itemRecepcionDocRepository.save(pgimItemRecepcionDoc);
    }
    
    @Override
    public PgimItemRecepcionDoc itemRecepcionDocfindById(Long idItemRecepcionDoc) {
        return this.itemRecepcionDocRepository.findById(idItemRecepcionDoc).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public List<PgimItemSolicitudDocDTO> modificarFechasRequerimiento(PgimSolicitudDocDTO pgimSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {
        List<PgimItemSolicitudDocDTO> lPgimSolicitudDocDTOModificado;
        List<PgimItemSolicitudDocDTO> lPgimItemSolicitudDocDTO;
        lPgimItemSolicitudDocDTO = this.listarItemSolicitudDocxIdSolicitudDoc(pgimSolicitudDocDTO.getIdSolicitudDoc());
        for (PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOItem : lPgimItemSolicitudDocDTO) {
            PgimItemSolicitudDoc pgimItemSolicitudDoc = itemSolicitudDocRepository
                .findById(pgimItemSolicitudDocDTOItem.getIdItemSolicitudDoc()).orElse(null);
                pgimItemSolicitudDoc.setFeSolicitudDocumentacion(pgimSolicitudDocDTO.getFeSolicitudDocumentacion());
                pgimItemSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
                pgimItemSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
                pgimItemSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

                
                PgimItemSolicitudDoc pgimItemSolicitudDocModificado = itemSolicitudDocRepository.save(pgimItemSolicitudDoc);
        }
        lPgimSolicitudDocDTOModificado = this.listarItemSolicitudDocxIdSolicitudDoc(pgimSolicitudDocDTO.getIdSolicitudDoc());
        return lPgimSolicitudDocDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public List<PgimItemSolicitudDocDTO> modificarFechasRecepcion(PgimSolicitudDocDTO pgimSolicitudDocDTO,
            AuditoriaDTO auditoriaDTO) {
        List<PgimItemSolicitudDocDTO> lPgimSolicitudDocDTOModificado;
        List<PgimItemSolicitudDocDTO> lPgimItemSolicitudDocDTO;
        lPgimItemSolicitudDocDTO = this.listarItemSolicitudDocxIdSolicitudDoc(pgimSolicitudDocDTO.getIdSolicitudDoc());
        for (PgimItemSolicitudDocDTO pgimItemSolicitudDocDTOItem : lPgimItemSolicitudDocDTO) {
            PgimItemSolicitudDoc pgimItemSolicitudDoc = itemSolicitudDocRepository
                .findById(pgimItemSolicitudDocDTOItem.getIdItemSolicitudDoc()).orElse(null);
                pgimItemSolicitudDoc.setEsRecibido(ConstantesUtil.IND_ACTIVO);
                pgimItemSolicitudDoc.setFeRecepcionDocumentacion(pgimSolicitudDocDTO.getFeRecepcionDocumentacion());
                pgimItemSolicitudDoc.setFeActualizacion(auditoriaDTO.getFecha());
                pgimItemSolicitudDoc.setUsActualizacion(auditoriaDTO.getUsername());
                pgimItemSolicitudDoc.setIpActualizacion(auditoriaDTO.getTerminal());

                
                PgimItemSolicitudDoc pgimItemSolicitudDocModificado = itemSolicitudDocRepository.save(pgimItemSolicitudDoc);
        }
        lPgimSolicitudDocDTOModificado = this.listarItemSolicitudDocxIdSolicitudDoc(pgimSolicitudDocDTO.getIdSolicitudDoc());
        return lPgimSolicitudDocDTOModificado;
    }

}
