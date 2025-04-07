package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadOrganica;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository;
import pe.gob.osinergmin.pgim.services.RolProcesoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Rol proceso
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020
 */
@Service
@Transactional(readOnly = true)
public class RolProcesoServiceImpl implements RolProcesoService {

    @Autowired
    private RolProcesoRepository rolProcesoRepository;
        
    @Autowired
    private PasoProcesoRepository pasoProcesoRepository;

    @Override
    public List<PgimRolProcesoDTO> obtenerRolesProceso(Long idProceso, String campo, Sort.Direction direccion) {
        Sort sort = Sort.by(direccion, campo);
        List<PgimRolProcesoDTO> lPgimRolProcesoDTO = this.rolProcesoRepository.obtenerRolesProceso(idProceso, sort);
        return lPgimRolProcesoDTO;
    }

    @Override
    public List<PgimRolProcesoDTO> obtenerRolesProcesoPorAmbito(String flSoloOsinergmin, Long idProceso) {
        List<PgimRolProcesoDTO> lPgimRolProcesoDTO = this.rolProcesoRepository
                .obtenerRolesProcesoPorAmbito(flSoloOsinergmin, idProceso);

        return lPgimRolProcesoDTO;
    }

    // STORY:PGIM-7233: Validación de existencia de rol "coordinador de empresa
    // supervisora" en el equipo
    @Override
    public List<PgimRolProcesoDTO> obtenerRolCoordinadorESParaReprogramacion(Long idRolProceso) {
        List<PgimRolProcesoDTO> lPgimRolProcesoDTO = this.rolProcesoRepository
                .obtenerRolCoordinadorESParaReprogramacion(idRolProceso);

        return lPgimRolProcesoDTO;
    }

    // STORY: PGIM-7276: Relación de personal con roles del proceso de fiscalización
    @Override
    public List<PgimRolProcesoDTO> listarRolesPersonalContrato() {
        List<PgimRolProcesoDTO> lPgimRolProcesoDTO = this.rolProcesoRepository.listarRolesPersonalContrato();

        return lPgimRolProcesoDTO;
    }

    @Override
    public PgimRolProcesoDTO obtenerRolProcesoPorId(Long idRolProceso) {
        return this.rolProcesoRepository.obtenerRolPorID(idRolProceso);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimRolProcesoDTO crearRolProceso(PgimRolProcesoDTO pgimRolProcesoDTO, AuditoriaDTO auditoriaDTO)
            throws Exception {
        PgimRolProceso pgimRolProceso = new PgimRolProceso();

        pgimRolProceso.setPgimProceso(new PgimProceso());
        pgimRolProceso.getPgimProceso().setIdProceso(pgimRolProcesoDTO.getIdProceso());

        pgimRolProceso.setPgimUnidadOrganica(new PgimUnidadOrganica());
        pgimRolProceso.getPgimUnidadOrganica().setIdUnidadOrganica(pgimRolProcesoDTO.getIdUnidadOrganica());

        pgimRolProceso.setCoRolProceso(pgimRolProcesoDTO.getCoRolProceso());
        pgimRolProceso.setNoRolProceso(pgimRolProcesoDTO.getNoRolProceso());
        pgimRolProceso.setDeRolProceso(pgimRolProcesoDTO.getDeRolProceso());
        pgimRolProceso.setFlSoloOsinergmin(pgimRolProcesoDTO.getFlSoloOsinergmin());

        pgimRolProceso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimRolProceso.setFeCreacion(auditoriaDTO.getFecha());
        pgimRolProceso.setUsCreacion(auditoriaDTO.getUsername());
        pgimRolProceso.setIpCreacion(auditoriaDTO.getTerminal());

        PgimRolProceso pgimRolProcesoCreado = this.rolProcesoRepository.save(pgimRolProceso);

        PgimRolProcesoDTO pgimRolProcesoDTOCreado = this
                .obtenerRolProcesoPorId(pgimRolProcesoCreado.getIdRolProceso());

        return pgimRolProcesoDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimRolProcesoDTO modificarRolProceso(PgimRolProcesoDTO pgimRolProcesoDTO, PgimRolProceso pgimRolProceso,
            AuditoriaDTO auditoriaDTO) throws Exception {

        pgimRolProceso.setPgimProceso(new PgimProceso());
        pgimRolProceso.getPgimProceso().setIdProceso(pgimRolProcesoDTO.getIdProceso());

        pgimRolProceso.setPgimUnidadOrganica(new PgimUnidadOrganica());
        pgimRolProceso.getPgimUnidadOrganica().setIdUnidadOrganica(pgimRolProcesoDTO.getIdUnidadOrganica());

        pgimRolProceso.setCoRolProceso(pgimRolProcesoDTO.getCoRolProceso());
        pgimRolProceso.setNoRolProceso(pgimRolProcesoDTO.getNoRolProceso());
        pgimRolProceso.setDeRolProceso(pgimRolProcesoDTO.getDeRolProceso());
        pgimRolProceso.setFlSoloOsinergmin(pgimRolProcesoDTO.getFlSoloOsinergmin());

        pgimRolProceso.setFeActualizacion(auditoriaDTO.getFecha());
        pgimRolProceso.setUsActualizacion(auditoriaDTO.getUsername());
        pgimRolProceso.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimRolProceso pgimRolProcesoModificado = this.rolProcesoRepository.save(pgimRolProceso);

        PgimRolProcesoDTO pgimRolProcesoDTOModificado = this
                .obtenerRolProcesoPorId(pgimRolProcesoModificado.getIdRolProceso());

        return pgimRolProcesoDTOModificado;
    }

    @Override
    public PgimRolProceso getByIdRolProceso(Long idRolProceso) throws Exception {
        return this.rolProcesoRepository.findById(idRolProceso).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarRolProceso(PgimRolProceso pgimRolProcesoActual, AuditoriaDTO auditoriaDTO) throws Exception {

        pgimRolProcesoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimRolProcesoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimRolProcesoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimRolProcesoActual.setIpActualizacion(auditoriaDTO.getTerminal());
	
		this.rolProcesoRepository.save(pgimRolProcesoActual);
    }

    @Override
    public void validarEliminacionRolProceso(PgimRolProceso pgimRolProcesoActual, AuditoriaDTO auditoriaDTO)
            throws Exception {

        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = this.pasoProcesoRepository.listarPasosPorRol(pgimRolProcesoActual.getIdRolProceso());

		if(lPgimPasoProcesoDTO.size() > 0){
			 
            throw new PgimException(TipoResultado.WARNING, "Para eliminar un rol, es necesario asegurarse de que no tenga ninguna tarea asociada. En caso contrario, no será posible llevar a cabo la eliminación.", lPgimPasoProcesoDTO.size());
		}
    }

}
