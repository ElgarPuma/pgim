package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCmineroSprvsion;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteHc;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoConstatado;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.repository.CmineroSprvsionRepository;
import pe.gob.osinergmin.pgim.models.repository.ComponenteHcRepository;
import pe.gob.osinergmin.pgim.services.CmineroSprvsionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de las entidades componente minero de
 *               supervision
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 30/06/2020
 * @fecha_de_ultima_actualización: 30/06/2020
 */
@Service
@Transactional(readOnly = true)
public class CmineroSprvsionServiceImpl implements CmineroSprvsionService {

    @Autowired
    private CmineroSprvsionRepository cmineroSprvsionRepository;

    @Autowired
    private ComponenteHcRepository componenteHcRepository;

    @Override
    public List<PgimCmineroSprvsionDTO> listarComponenteMineroSupervision(Long idSupervision) {
        List<PgimCmineroSprvsionDTO> lPgimCmineroSprvsionDTO = this.cmineroSprvsionRepository
                .listarComponenteMineroSupervision(idSupervision);
        return lPgimCmineroSprvsionDTO;
    }

    @Override
    public PgimCmineroSprvsionDTO obtenerComponenteMineroSupervisionId(Long idCmineroSprvsion) {
        PgimCmineroSprvsionDTO pgimCmineroSprvsionDTO = this.cmineroSprvsionRepository
                .obtenerComponenteMineroSupervisionId(idCmineroSprvsion);
        return pgimCmineroSprvsionDTO;
    }

    @Override
    public PgimCmineroSprvsion getByIdComponenteMineroSupervision(Long idCmineroSprvsion) {
        PgimCmineroSprvsion pgimCmineroSprvsion = this.cmineroSprvsionRepository.findById(idCmineroSprvsion)
                .orElse(null);
        return pgimCmineroSprvsion;
    }

    /**
     * ----------------------------------------------------------------------------
     * PGIM-11252: Agregar la sección “Componentes asociados al Hecho Verificado” en
     * fase 2 de la fiscalización
     * ----------------------------------------------------------------------------
     */
    @Override
    @Transactional(readOnly = false)
    public PgimCmineroSprvsionDTO agregarComponentesMineroSupHcNuevo(
            @Valid PgimCmineroSprvsionDTO pgimCmineroSprvsionDTO, AuditoriaDTO auditoriaDTO) throws Exception {

        // Estado de creación(DescTipoAccion = 1) de un hecho verificado y queremos impeccionar un nuevo componente minero al HV
        if(pgimCmineroSprvsionDTO.getDescTipoAccion().equals(1)){
            
            // Solo para Rol Esp.Tec, Esto quiere decir que estas impeccionando un componente minero desde un
            // componente de "hc-general.component.ts" para su creación esto en la fase 4 y 5. 
            // variable "DatoAdicional(yo lo he personalizado como un valor 4 que significa que viene de una creación)"
            if(pgimCmineroSprvsionDTO.getDatoAdicional().equals(4)){
                
                // registramos los componentes mineros que vamos a asociar
                if (pgimCmineroSprvsionDTO.getDescListaComponenteMinero() != null
                        && pgimCmineroSprvsionDTO.getDescListaComponenteMinero().size() != 0) {

                    for (PgimComponenteMineroDTO pgimComponenteMineroDTO : pgimCmineroSprvsionDTO
                            .getDescListaComponenteMinero()) {

                        PgimCmineroSprvsionDTO pgimCmineroSprvsionObtenidoDTO = this.cmineroSprvsionRepository
                                .obtenerComponenteMineroSupervisionIdCompMineroIdSup(
                                        pgimComponenteMineroDTO.getIdComponenteMinero(),
                                        pgimCmineroSprvsionDTO.getIdSupervision());

                        if (pgimCmineroSprvsionObtenidoDTO == null) {

                            PgimCmineroSprvsion pgimCmineroSprvsion = new PgimCmineroSprvsion();

                            pgimCmineroSprvsion.setPgimSupervision(new PgimSupervision());
                            pgimCmineroSprvsion.getPgimSupervision()
                                    .setIdSupervision(pgimCmineroSprvsionDTO.getIdSupervision());

                            pgimCmineroSprvsion.setPgimComponenteMinero(new PgimComponenteMinero());
                            pgimCmineroSprvsion.getPgimComponenteMinero()
                                    .setIdComponenteMinero(pgimComponenteMineroDTO.getIdComponenteMinero());

                            pgimCmineroSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                            pgimCmineroSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                            pgimCmineroSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                            pgimCmineroSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

                            PgimCmineroSprvsion pgimCmineroSprvsionCreado = this.cmineroSprvsionRepository
                                    .save(pgimCmineroSprvsion);

                            PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();

                            pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
                            pgimComponenteHc.getPgimHechoConstatado()
                                    .setIdHechoConstatado(pgimCmineroSprvsionDTO.getDescIdHechoConstatado());

                            pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
                            pgimComponenteHc.getPgimCmineroSprvsion()
                                    .setIdCmineroSprvsion(pgimCmineroSprvsionCreado.getIdCmineroSprvsion());
                            pgimComponenteHc.setFlAplica("0");

                            pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                            pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
                            pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
                            pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());

                            this.componenteHcRepository.save(pgimComponenteHc);

                        } else {

                            PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();

                            pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
                            pgimComponenteHc.getPgimHechoConstatado()
                                    .setIdHechoConstatado(pgimCmineroSprvsionDTO.getDescIdHechoConstatado());

                            pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
                            pgimComponenteHc.getPgimCmineroSprvsion()
                                    .setIdCmineroSprvsion(pgimCmineroSprvsionObtenidoDTO.getIdCmineroSprvsion());
                            pgimComponenteHc.setFlAplica("0");

                            pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                            pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
                            pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
                            pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());

                            this.componenteHcRepository.save(pgimComponenteHc);
                        }
                    }
                }
            } 
            
            else {
                // registramos los componentes mineros que vamos a asociar
                if (pgimCmineroSprvsionDTO.getDescListaComponenteMinero() != null
                        && pgimCmineroSprvsionDTO.getDescListaComponenteMinero().size() != 0) {

                    for (PgimComponenteMineroDTO pgimComponenteMineroDTO : pgimCmineroSprvsionDTO
                            .getDescListaComponenteMinero()) {

                        PgimCmineroSprvsion pgimCmineroSprvsion = new PgimCmineroSprvsion();

                        pgimCmineroSprvsion.setPgimSupervision(new PgimSupervision());
                        pgimCmineroSprvsion.getPgimSupervision()
                                .setIdSupervision(pgimCmineroSprvsionDTO.getIdSupervision());

                        pgimCmineroSprvsion.setPgimComponenteMinero(new PgimComponenteMinero());
                        pgimCmineroSprvsion.getPgimComponenteMinero()
                                .setIdComponenteMinero(pgimComponenteMineroDTO.getIdComponenteMinero());

                        pgimCmineroSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimCmineroSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                        pgimCmineroSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                        pgimCmineroSprvsion.setIpCreacion(auditoriaDTO.getTerminal());

                        this.cmineroSprvsionRepository.save(pgimCmineroSprvsion);
                    }
                }
            }
        } 
        // Esta de modificación(DescTipoAccion = 2) de un hecho verificado y queremos impeccionar un nuevo componente minero al HV
        else {

            // registramos los componentes mineros que vamos a asociar
            if (pgimCmineroSprvsionDTO.getDescListaComponenteMinero() != null && pgimCmineroSprvsionDTO.getDescListaComponenteMinero().size() != 0) {
    
                for (PgimComponenteMineroDTO pgimComponenteMineroDTO : pgimCmineroSprvsionDTO.getDescListaComponenteMinero()) {
    
                    PgimCmineroSprvsionDTO pgimCmineroSprvsionObtenidoDTO = this.cmineroSprvsionRepository.obtenerComponenteMineroSupervisionIdCompMineroIdSup(pgimComponenteMineroDTO.getIdComponenteMinero(), pgimCmineroSprvsionDTO.getIdSupervision());
    
    
                    if(pgimCmineroSprvsionObtenidoDTO == null){
    
                        PgimCmineroSprvsion pgimCmineroSprvsion = new PgimCmineroSprvsion();
    
                        pgimCmineroSprvsion.setPgimSupervision(new PgimSupervision());
                        pgimCmineroSprvsion.getPgimSupervision()
                                .setIdSupervision(pgimCmineroSprvsionDTO.getIdSupervision());
    
                        pgimCmineroSprvsion.setPgimComponenteMinero(new PgimComponenteMinero());
                        pgimCmineroSprvsion.getPgimComponenteMinero()
                                .setIdComponenteMinero(pgimComponenteMineroDTO.getIdComponenteMinero());
    
                        pgimCmineroSprvsion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimCmineroSprvsion.setFeCreacion(auditoriaDTO.getFecha());
                        pgimCmineroSprvsion.setUsCreacion(auditoriaDTO.getUsername());
                        pgimCmineroSprvsion.setIpCreacion(auditoriaDTO.getTerminal());
    
                        PgimCmineroSprvsion pgimCmineroSprvsionCreado = this.cmineroSprvsionRepository.save(pgimCmineroSprvsion);
    
    
                        PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();
    
                        pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
                        pgimComponenteHc.getPgimHechoConstatado().setIdHechoConstatado(pgimCmineroSprvsionDTO.getDescIdHechoConstatado());
    
                        pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
                        pgimComponenteHc.getPgimCmineroSprvsion().setIdCmineroSprvsion(pgimCmineroSprvsionCreado.getIdCmineroSprvsion());
                        pgimComponenteHc.setFlAplica("0");

                        pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
                        pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
                        pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());
    
                        this.componenteHcRepository.save(pgimComponenteHc);
    
                    } else {
    
                            PgimComponenteHc pgimComponenteHc = new PgimComponenteHc();
        
                            pgimComponenteHc.setPgimHechoConstatado(new PgimHechoConstatado());
                            pgimComponenteHc.getPgimHechoConstatado()
                                    .setIdHechoConstatado(pgimCmineroSprvsionDTO.getDescIdHechoConstatado());
        
                            pgimComponenteHc.setPgimCmineroSprvsion(new PgimCmineroSprvsion());
                            pgimComponenteHc.getPgimCmineroSprvsion().setIdCmineroSprvsion(pgimCmineroSprvsionObtenidoDTO.getIdCmineroSprvsion());
                            pgimComponenteHc.setFlAplica("0");

                            pgimComponenteHc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                            pgimComponenteHc.setFeCreacion(auditoriaDTO.getFecha());
                            pgimComponenteHc.setUsCreacion(auditoriaDTO.getUsername());
                            pgimComponenteHc.setIpCreacion(auditoriaDTO.getTerminal());
        
                            this.componenteHcRepository.save(pgimComponenteHc);
                    }
                }
            }
        }

        return pgimCmineroSprvsionDTO;
    }
}
