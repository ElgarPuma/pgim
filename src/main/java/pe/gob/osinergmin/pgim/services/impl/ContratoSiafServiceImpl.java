package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimContratoSiaf;
import pe.gob.osinergmin.pgim.models.repository.ContratoSiafAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoSiafRepository;
import pe.gob.osinergmin.pgim.services.ContratoSiafService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad contrato siaf
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */
@Service
@Transactional(readOnly = true)
public class ContratoSiafServiceImpl implements ContratoSiafService {

    @Autowired
    private ContratoSiafRepository contratoSiafRepository;
    
    @Autowired
    private ContratoSiafAuxRepository contratoSiafAuxRepository;

    @Override
    public PgimContratoSiafDTO obtenerContratoSiafPorId(Long idContrato) {
        return this.contratoSiafRepository.obtenerContratoSiafPorId(idContrato);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContratoSiafDTO crearContratoSiaf(@Valid PgimContratoSiafDTO pgimContratoSiafDTO,
            AuditoriaDTO auditoriaDTO) throws Exception {

        PgimContratoSiaf pgimContratoSiaf = new PgimContratoSiaf();

        this.configurarValores(pgimContratoSiafDTO, pgimContratoSiaf);

        pgimContratoSiaf.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimContratoSiaf.setFeCreacion(auditoriaDTO.getFecha());
        pgimContratoSiaf.setUsCreacion(auditoriaDTO.getUsername());
        pgimContratoSiaf.setIpCreacion(auditoriaDTO.getTerminal());

        PgimContratoSiaf pgimContratoSiafCreado = this.contratoSiafRepository.save(pgimContratoSiaf);

        PgimContratoSiafDTO pgimContratoSiafDTOCreado = this
                .obtenerContratoSiafPorId(pgimContratoSiafCreado.getIdContratoSiaf());

        return pgimContratoSiafDTOCreado;
    }

    private void configurarValores(PgimContratoSiafDTO pgimContratoSiafDTO, PgimContratoSiaf pgimContratoSiaf) {

        pgimContratoSiaf.setPgimContrato(new PgimContrato());
        pgimContratoSiaf.getPgimContrato().setIdContrato(pgimContratoSiafDTO.getIdContrato());
        pgimContratoSiaf.setNuSiaf(pgimContratoSiafDTO.getNuSiaf());
        pgimContratoSiaf.setNuAnio(pgimContratoSiafDTO.getNuAnio());
        pgimContratoSiaf.setMoPresupuestoSiaf(pgimContratoSiafDTO.getMoPresupuestoSiaf());
    }

    @Override
    public List<PgimContratoSiafAuxDTO> listarContratoSiaf(Long idContrato) {
        List<PgimContratoSiafAuxDTO> lPgimContratoSiafDTO = this.contratoSiafAuxRepository.listarContratoSiaf(idContrato);

        return lPgimContratoSiafDTO;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimContratoSiafDTO modificarContratoSiaf(@Valid PgimContratoSiafDTO pgimContratoSiafDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimContratoSiaf pgimContratoSiaf = null;

        Optional<PgimContratoSiaf> contratoSiaf = contratoSiafRepository.findById(pgimContratoSiafDTO.getIdContratoSiaf());

        pgimContratoSiaf = contratoSiaf.get();

        this.configurarValores(pgimContratoSiafDTO, pgimContratoSiaf);

        pgimContratoSiaf.setFeActualizacion(auditoriaDTO.getFecha());
        pgimContratoSiaf.setUsActualizacion(auditoriaDTO.getUsername());
        pgimContratoSiaf.setIpActualizacion(auditoriaDTO.getTerminal());

        this.contratoSiafRepository.save(pgimContratoSiaf);

        PgimContratoSiafDTO pgimContratoSiafDTOModificada = obtenerContratoSiafPorId(
                pgimContratoSiaf.getIdContratoSiaf());

        return pgimContratoSiafDTOModificada;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarContratoSiaf(PgimContratoSiaf pgimContratoSiafActual, AuditoriaDTO auditoriaDTO) {
        pgimContratoSiafActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimContratoSiafActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimContratoSiafActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimContratoSiafActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.contratoSiafRepository.save(pgimContratoSiafActual);
    }

    @Override
    public PgimContratoSiaf getByIdContratoSiaf(Long idContratoSiaf) {
        return this.contratoSiafRepository.findById(idContratoSiaf).orElse(null);
    }
}
