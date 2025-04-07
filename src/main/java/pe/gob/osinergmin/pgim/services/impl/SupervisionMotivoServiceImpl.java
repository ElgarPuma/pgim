package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionMotivo;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.SupervisionMotivoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionMotivoRepository;
import pe.gob.osinergmin.pgim.services.SupervisionMotivoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Supervision motivo
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020
 */
@Service
@Transactional(readOnly = true)
public class SupervisionMotivoServiceImpl implements SupervisionMotivoService {

    @Autowired
    private SupervisionMotivoRepository supervisionMotivoRepository;

    @Autowired
    private SupervisionMotivoAuxRepository supervisionMotivoAuxRepository;
    
    @Override
    public Page<PgimSupervisionMotivoAuxDTO> listarSupervisionMotivoAux(final Long idSupervision, final Pageable paginador) {
        return this.supervisionMotivoAuxRepository.listarSupervisionMotivoAuxPorSupervision(idSupervision, paginador);
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarSupervisionMotivo(PgimSupervisionMotivo pgimSupervisionMotivoActual,
            AuditoriaDTO auditoriaDTO) {

        pgimSupervisionMotivoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimSupervisionMotivoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimSupervisionMotivoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimSupervisionMotivoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.supervisionMotivoRepository.save(pgimSupervisionMotivoActual);

    }

    @Override
    public PgimSupervisionMotivo getByIdSupervisionMotivo(Long idSupervisionMotivo) {
        return this.supervisionMotivoRepository.findById(idSupervisionMotivo).orElse(null);
    }

    @Override
    public PgimSupervisionMotivoDTO obtenerSupervisionMotivo(Long idSupervisionMotivo) {
        return this.supervisionMotivoRepository.obtenerSupervisionMotivo(idSupervisionMotivo);
    }
  
    @Transactional(readOnly = false)
    @Override
    public PgimSupervisionMotivoDTO crearSupervisionMotivo(PgimSupervisionMotivoDTO pgimSupervisionMotivoDTO,
            AuditoriaDTO obtenerAuditoria) {
        PgimSupervisionMotivo pgimSupervisionMotivo = new PgimSupervisionMotivo();

        pgimSupervisionMotivo.setPgimSupervision(new PgimSupervision());
        pgimSupervisionMotivo.getPgimSupervision().setIdSupervision(pgimSupervisionMotivoDTO.getIdSupervision());
        
        if(pgimSupervisionMotivoDTO.getIdTipoMotivoInicio() != null) {
	        pgimSupervisionMotivo.setPgimTipoMotivoInicio(new PgimValorParametro());
	        pgimSupervisionMotivo.getPgimTipoMotivoInicio().setIdValorParametro(pgimSupervisionMotivoDTO.getIdTipoMotivoInicio());
        }
        
        pgimSupervisionMotivo.setIdObjetoMotivoInicio(pgimSupervisionMotivoDTO.getIdObjetoMotivoInicio());

        pgimSupervisionMotivo.setEsRegistro("1");
        pgimSupervisionMotivo.setFeCreacion(obtenerAuditoria.getFecha());
        pgimSupervisionMotivo.setUsCreacion(obtenerAuditoria.getUsername());
        pgimSupervisionMotivo.setIpCreacion(obtenerAuditoria.getTerminal());
        PgimSupervisionMotivo pgimSupervisionMotivoCreada = this.supervisionMotivoRepository.save(pgimSupervisionMotivo);

        PgimSupervisionMotivoDTO ppgimSupervisionMotivoDTOCreada = new PgimSupervisionMotivoDTO();

        ppgimSupervisionMotivoDTOCreada.setIdSupervisionMotivo(pgimSupervisionMotivoCreada.getIdSupervisionMotivo());
        return ppgimSupervisionMotivoDTOCreada;
    }
    
    @Override
    public Page<PgimSupervisionMotivoAuxDTO> filtrarSeleccionMotivosPorUM(Long idUnidadMinera, Long idSupervision, 
            Pageable paginador){  	

        Page<PgimSupervisionMotivoAuxDTO> pgimSupervisionMotivoAuxDTO = this.supervisionMotivoAuxRepository
                .filtrarSeleccionMotivosPorUM(idUnidadMinera, idSupervision, paginador);

        return pgimSupervisionMotivoAuxDTO;
    }
}
