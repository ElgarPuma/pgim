package pe.gob.osinergmin.pgim.services.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTdrPlantillaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;
import pe.gob.osinergmin.pgim.models.entity.PgimTdrPlantilla;
import pe.gob.osinergmin.pgim.models.repository.TdrPlantillaRepository;
import pe.gob.osinergmin.pgim.services.TdrPlantillaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad TDR plantilla
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class TdrPlantillaServiceImpl implements TdrPlantillaService {

    @Autowired
    private TdrPlantillaRepository tdrPlantillaRepository;

    @Override
    public Page<PgimTdrPlantillaDTO> listarTdrBase(PgimTdrPlantillaDTO filtro, Pageable paginador) throws Exception {
        Page<PgimTdrPlantillaDTO> lPgimTdrPlantillaDTO = this.tdrPlantillaRepository.listarTdrBase(
                filtro.getDescIdEspecialidad(), filtro.getDescNoConfiguracionBase(), filtro.getTextoBusqueda(),
                paginador);

        return lPgimTdrPlantillaDTO;
    }

    @Override
    public PgimTdrPlantillaDTO obtenerTdrBasePorId(Long idTdrPlantilla) {
        return this.tdrPlantillaRepository.obtenerTdrBasePorId(idTdrPlantilla);
    }

    @Override
    public PgimTdrPlantilla getByIdTdrBase(Long idTdrPlantilla) {
        return this.tdrPlantillaRepository.findById(idTdrPlantilla).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTdrPlantillaDTO crearTdrBase(PgimTdrPlantillaDTO pgimTdrPlantillaDTO, AuditoriaDTO auditoriaDTO)
            throws Exception {
        PgimTdrPlantilla pgimTdrPlantilla = new PgimTdrPlantilla();

        this.configurarValores(pgimTdrPlantillaDTO, pgimTdrPlantilla);

        pgimTdrPlantilla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimTdrPlantilla.setFeCreacion(auditoriaDTO.getFecha());
        pgimTdrPlantilla.setUsCreacion(auditoriaDTO.getUsername());
        pgimTdrPlantilla.setIpCreacion(auditoriaDTO.getTerminal());

        PgimTdrPlantilla pgimTdrPlantillaCreado = this.tdrPlantillaRepository.save(pgimTdrPlantilla);

        PgimTdrPlantillaDTO pgimTdrPlantillaDTOCreado = this
                .obtenerTdrBasePorId(pgimTdrPlantillaCreado.getIdTdrPlantilla());

        return pgimTdrPlantillaDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTdrPlantillaDTO modificarTdrBase(@Valid PgimTdrPlantillaDTO pgimTdrPlantillaDTO,
            PgimTdrPlantilla pgimTdrPlantilla, AuditoriaDTO auditoriaDTO) throws Exception {
        this.configurarValores(pgimTdrPlantillaDTO, pgimTdrPlantilla);

        pgimTdrPlantilla.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTdrPlantilla.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTdrPlantilla.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTdrPlantilla pgimTdrPlantillaModificado = this.tdrPlantillaRepository.save(pgimTdrPlantilla);

        PgimTdrPlantillaDTO pgimTdrPlantillaDTOModificado = this
                .obtenerTdrBasePorId(pgimTdrPlantillaModificado.getIdTdrPlantilla());

        return pgimTdrPlantillaDTOModificado;
    }

    @Transactional(readOnly = false)
    private PgimTdrPlantilla configurarValores(PgimTdrPlantillaDTO pgimTdrPlantillaDTO,
            PgimTdrPlantilla pgimTdrPlantilla) {

        if (pgimTdrPlantillaDTO.getIdConfiguracionBase() != null) {
            PgimConfiguracionBase pgimConfiguracionBase = new PgimConfiguracionBase();
            pgimConfiguracionBase.setIdConfiguracionBase(pgimTdrPlantillaDTO.getIdConfiguracionBase());
            pgimTdrPlantilla.setPgimConfiguracionBase(pgimConfiguracionBase);
        } else {
            pgimTdrPlantilla.setPgimConfiguracionBase(null);
        }

        pgimTdrPlantilla.setDeTdrObjetivoTexto(pgimTdrPlantillaDTO.getDeTdrObjetivoTexto());
        pgimTdrPlantilla.setDeTdrAlcanceTexto(pgimTdrPlantillaDTO.getDeTdrAlcanceTexto());
        pgimTdrPlantilla.setDeTdrMetodologiaTexto(pgimTdrPlantillaDTO.getDeTdrMetodologiaTexto());
        pgimTdrPlantilla.setDeTdrInformeSupervTexto(pgimTdrPlantillaDTO.getDeTdrInformeSupervTexto());
        pgimTdrPlantilla.setDeTdrHonorariosProf(pgimTdrPlantillaDTO.getDeTdrHonorariosProf());

        return pgimTdrPlantilla;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarTdrBase(PgimTdrPlantilla pgimTdrPlantillaActual, AuditoriaDTO auditoriaDTO) {
        pgimTdrPlantillaActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimTdrPlantillaActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTdrPlantillaActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTdrPlantillaActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.tdrPlantillaRepository.save(pgimTdrPlantillaActual);
    }
}
