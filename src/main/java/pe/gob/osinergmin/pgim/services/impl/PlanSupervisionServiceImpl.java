package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimPlanSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.EspecialidadRepository;
import pe.gob.osinergmin.pgim.models.repository.PlanSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository;
import pe.gob.osinergmin.pgim.services.PlanSupervisionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Plan de supervisión
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020
 */
@Service
@Transactional(readOnly = true)
public class PlanSupervisionServiceImpl implements PlanSupervisionService {

    @Autowired
    private PlanSupervisionRepository planSupervisionRepository;
    
    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    @Autowired
    private PrgrmSupervisionRepository prgrmSupervisionRepository;

    @Override
    public Page<PgimPlanSupervisionDTO> listarPlanSupervision(PgimPlanSupervisionDTO filtroPlanSupervision,
            Pageable paginador) {
        Page<PgimPlanSupervisionDTO> pPgimPlanSupervisionDTO = this.planSupervisionRepository.listarPlanSupervision(
                filtroPlanSupervision.getNuAnio(), filtroPlanSupervision.getTextoBusqueda(), paginador);

        return pPgimPlanSupervisionDTO;
    }

    @Override
    public PgimPlanSupervisionDTO obtenerPlanSupervisionPorId(Long idPlanSupervision) {
        return this.planSupervisionRepository.obtenerPlanSupervisionPorId(idPlanSupervision);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimPlanSupervisionDTO crearPlanSupervision(@Valid PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            AuditoriaDTO auditoriaDTO) throws Exception {

        PgimPlanSupervision pgimPlanSupervision = new PgimPlanSupervision();

        pgimPlanSupervision.setDePlanSupervision(pgimPlanSupervisionDTO.getDePlanSupervision());

        pgimPlanSupervision.setNuAnio(pgimPlanSupervisionDTO.getNuAnio());

        pgimPlanSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimPlanSupervision.setFeCreacion(auditoriaDTO.getFecha());
        pgimPlanSupervision.setUsCreacion(auditoriaDTO.getUsername());
        pgimPlanSupervision.setIpCreacion(auditoriaDTO.getTerminal());

        PgimPlanSupervision pgimPlanSupervisionCreado = this.planSupervisionRepository.save(pgimPlanSupervision);

        PgimPlanSupervisionDTO pgimPlanSupervisionDTOCreado = this
                .obtenerPlanSupervisionPorId(pgimPlanSupervisionCreado.getIdPlanSupervision());
        
        this.crearProgramaPorPlanSupervision(pgimPlanSupervisionDTOCreado, auditoriaDTO);

        return pgimPlanSupervisionDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimPlanSupervisionDTO modificarPlanSupervision(PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            PgimPlanSupervision pgimPlanSupervision, AuditoriaDTO auditoriaDTO) {

        pgimPlanSupervision.setNuAnio(pgimPlanSupervisionDTO.getNuAnio());
        pgimPlanSupervision.setDePlanSupervision(pgimPlanSupervisionDTO.getDePlanSupervision());

        pgimPlanSupervision.setFeActualizacion(auditoriaDTO.getFecha());
        pgimPlanSupervision.setUsActualizacion(auditoriaDTO.getUsername());
        pgimPlanSupervision.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimPlanSupervision pgimPlanSupervisionModificado = planSupervisionRepository.save(pgimPlanSupervision);

        PgimPlanSupervisionDTO pgimPlanSupervisionDTOResultado = obtenerPlanSupervisionPorId(
                pgimPlanSupervisionModificado.getIdPlanSupervision());

        return pgimPlanSupervisionDTOResultado;
    }

    @Override
    public PgimPlanSupervision getByIdPlanSupervision(Long idPlanSupervision) {
        return this.planSupervisionRepository.findById(idPlanSupervision).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarPlanSupervision(PgimPlanSupervision pgimPlanSupervisionActual, AuditoriaDTO auditoriaDTO) {
        pgimPlanSupervisionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimPlanSupervisionActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimPlanSupervisionActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimPlanSupervisionActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.planSupervisionRepository.save(pgimPlanSupervisionActual);

    }

    @Override
    public List<PgimPlanSupervisionDTO> filtrarPorAnio(Long nuAnio) {
        List<PgimPlanSupervisionDTO> lPgimPlanSupervisionDTO = this.planSupervisionRepository.filtrarPorAnio(nuAnio);
        return lPgimPlanSupervisionDTO;
    }

    @Override
    public List<PgimPlanSupervisionDTO> existeNuAnio(Long idPlanSupervision, Long nuAnio) {
        return this.planSupervisionRepository.existeNuAnio(idPlanSupervision, nuAnio);
    }

    
    @Transactional(readOnly = false)
    public void crearProgramaPorPlanSupervision(PgimPlanSupervisionDTO pgimPlanSupervisionDTO,
            AuditoriaDTO auditoriaDTO) throws Exception {

    	List<PgimEspecialidadDTO> lPgimEspecialidadDTO = especialidadRepository.listarEspecialidadPorDivisionSupervisora();
    	
    	for (PgimEspecialidadDTO obj : lPgimEspecialidadDTO) {
    		PgimPrgrmSupervision pgimPrgrmSupervision = new PgimPrgrmSupervision();
    	
    		PgimPlanSupervision pgimPlanSupervision = new PgimPlanSupervision();
    		pgimPlanSupervision.setIdPlanSupervision(pgimPlanSupervisionDTO.getIdPlanSupervision());
    		pgimPrgrmSupervision.setPgimPlanSupervision(pgimPlanSupervision);
    		
    		PgimEspecialidad pgimEspecialidad = new PgimEspecialidad();
    		pgimEspecialidad.setIdEspecialidad(obj.getIdEspecialidad());
    		pgimPrgrmSupervision.setPgimEspecialidad(pgimEspecialidad);
    		
    		PgimValorParametro divisionSupervisora = new PgimValorParametro();
    		divisionSupervisora.setIdValorParametro(obj.getDescIdValorParametro());
    		pgimPrgrmSupervision.setDivisionSupervisora(divisionSupervisora);
    		
    		pgimPrgrmSupervision.setPgimInstanciaProces(null);
    		pgimPrgrmSupervision.setDeProgramaSupervision(null);
    		pgimPrgrmSupervision.setMoPartida(null);
    		
    		pgimPrgrmSupervision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
    		pgimPrgrmSupervision.setFeCreacion(auditoriaDTO.getFecha());
    		pgimPrgrmSupervision.setUsCreacion(auditoriaDTO.getUsername());
    		pgimPrgrmSupervision.setIpCreacion(auditoriaDTO.getTerminal());
    		prgrmSupervisionRepository.save(pgimPrgrmSupervision);
		}
    }

}
