package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimFaseProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository;
import pe.gob.osinergmin.pgim.services.FaseProcesoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Fase del proceso
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 18/12/2023
 * @fecha_de_ultima_actualización: 18/12/2023
 */
@Service
@Transactional(readOnly = true)
public class FaseProcesoServiceImpl implements FaseProcesoService {

    @Autowired
    FaseProcesoRepository faseProcesoRepository;
    
    @Autowired
    PasoProcesoRepository pasoProcesoRepository;

    @Override
    public List<PgimFaseProcesoDTO> listarFasesProceso(Long idProceso, String campo, Sort.Direction direccion) {
        Sort sort = Sort.by(direccion, campo);
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = this.faseProcesoRepository.listarFasesProceso(idProceso, sort);
        return lPgimFaseProcesoDTO;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimFaseProcesoDTO crearFaseProceso(PgimFaseProcesoDTO pgimFaseProcesoDTO, AuditoriaDTO auditoriaDTO)
            throws Exception {

        PgimFaseProceso pgimFaseProceso = new PgimFaseProceso();

        pgimFaseProceso.setPgimProceso(new PgimProceso());
        pgimFaseProceso.getPgimProceso().setIdProceso(pgimFaseProcesoDTO.getIdProceso());

        pgimFaseProceso.setNoFaseProceso(pgimFaseProcesoDTO.getNoFaseProceso());
        pgimFaseProceso.setDeFaseProceso(pgimFaseProcesoDTO.getDeFaseProceso());

        pgimFaseProceso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimFaseProceso.setFeCreacion(auditoriaDTO.getFecha());
        pgimFaseProceso.setUsCreacion(auditoriaDTO.getUsername());
        pgimFaseProceso.setIpCreacion(auditoriaDTO.getTerminal());

        PgimFaseProceso pgimFaseProcesoCreado = this.faseProcesoRepository.save(pgimFaseProceso);

        PgimFaseProcesoDTO pgimFaseProcesoDTOCreado = this
                .obtenerFaseProcesoPorId(pgimFaseProcesoCreado.getIdFaseProceso());

        return pgimFaseProcesoDTOCreado;

    }

    @Override
    public PgimFaseProcesoDTO obtenerFaseProcesoPorId(Long idFaseProceso) {
        return this.faseProcesoRepository.obtenerFaseProcesoPorId(idFaseProceso);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimFaseProcesoDTO modificarFaseProceso(PgimFaseProcesoDTO pgimFaseProcesoDTO,
            PgimFaseProceso pgimFaseProceso, AuditoriaDTO auditoriaDTO) throws Exception {

        pgimFaseProceso.setPgimProceso(new PgimProceso());
        pgimFaseProceso.getPgimProceso().setIdProceso(pgimFaseProcesoDTO.getIdProceso());

        pgimFaseProceso.setNoFaseProceso(pgimFaseProcesoDTO.getNoFaseProceso());
        pgimFaseProceso.setDeFaseProceso(pgimFaseProcesoDTO.getDeFaseProceso());

        pgimFaseProceso.setFeActualizacion(auditoriaDTO.getFecha());
        pgimFaseProceso.setUsActualizacion(auditoriaDTO.getUsername());
        pgimFaseProceso.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimFaseProceso pgimFaseProcesoModificado = this.faseProcesoRepository.save(pgimFaseProceso);

        PgimFaseProcesoDTO pgimFaseProcesoDTOModificado = this
                .obtenerFaseProcesoPorId(pgimFaseProcesoModificado.getIdFaseProceso());

        return pgimFaseProcesoDTOModificado;
    }

    @Override
    public PgimFaseProceso getByIdFaseProceso(Long idFaseProceso) throws Exception {
        return this.faseProcesoRepository.findById(idFaseProceso).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarFaseProceso(PgimFaseProceso pgimFaseProcesoActual, AuditoriaDTO auditoriaDTO) throws Exception {
 
		pgimFaseProcesoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimFaseProcesoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimFaseProcesoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimFaseProcesoActual.setIpActualizacion(auditoriaDTO.getTerminal());
	
		this.faseProcesoRepository.save(pgimFaseProcesoActual);
		
    }

    @Override
    public void validarEliminacionFaseProceso(PgimFaseProceso pgimFaseProcesoActual, AuditoriaDTO auditoriaDTO)
            throws Exception {
        
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = this.pasoProcesoRepository.listarPasosPorFase(pgimFaseProcesoActual.getIdFaseProceso());

		if(lPgimPasoProcesoDTO.size() > 0){
			 
			 throw new PgimException(TipoResultado.WARNING, "Para eliminar una fase, es necesario asegurarse de que no tenga ninguna tarea asociada. En caso contrario, no será posible llevar a cabo la eliminación.", lPgimPasoProcesoDTO.size());
		}
    }

}
