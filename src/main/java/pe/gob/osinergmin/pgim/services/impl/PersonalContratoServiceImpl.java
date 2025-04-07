package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.services.PersonalContratoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Personal contrato
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */

@Service
@Transactional(readOnly = true)
public class PersonalContratoServiceImpl implements PersonalContratoService {

    @Autowired
    private PersonalContratoRepository personalContratoRepository;

    @Autowired
    private PersonaService personaService;

    @Override
    public Page<PgimPersonalContratoDTO> listarPersonalContrato(final Long idContrato, final Pageable paginador) {

        return this.personalContratoRepository.listarPersonalContrato(idContrato, paginador);
    }

    @Override
    public PgimPersonalContratoDTO obtenerPersonalContrato(Long idPersonalContrato) {
        return this.personalContratoRepository.obtenerPersonalContrato(idPersonalContrato);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimPersonalContratoDTO agregarPersonalContrato(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
            AuditoriaDTO auditoriaDTO) {
        PgimPersonalContrato pgimPersonalContrato = new PgimPersonalContrato();

        this.configurarValoresAgregadas(pgimPersonalContratoDTO, pgimPersonalContrato);

        pgimPersonalContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimPersonalContrato.setFeCreacion(auditoriaDTO.getFecha());
        pgimPersonalContrato.setUsCreacion(auditoriaDTO.getUsername());
        pgimPersonalContrato.setIpCreacion(auditoriaDTO.getTerminal());

        PgimPersonalContrato pgimPersonalContratoCreado = this.personalContratoRepository.save(pgimPersonalContrato);

        PgimPersonalContratoDTO pgimPersonalContratoDTOCreado = this
                .obtenerPersonalContrato(pgimPersonalContratoCreado.getIdPersonalContrato());

        return pgimPersonalContratoDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimPersonalContratoDTO modificarPersonalContratoAsig(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimPersonalContrato pgimPersonalContrato = null;
        Optional<PgimPersonalContrato> personalContrato = personalContratoRepository
                .findById(pgimPersonalContratoDTO.getIdPersonalContrato());
        pgimPersonalContrato = personalContrato.get();

        this.configurarValoresAgregadas(pgimPersonalContratoDTO, pgimPersonalContrato);

        pgimPersonalContrato.setFeActualizacion(auditoriaDTO.getFecha());
        pgimPersonalContrato.setUsActualizacion(auditoriaDTO.getUsername());
        pgimPersonalContrato.setIpActualizacion(auditoriaDTO.getTerminal());

        this.personalContratoRepository.save(pgimPersonalContrato);

        PgimPersonalContratoDTO pgimPersonalContratoDTOModificada = obtenerPersonalContrato(
                pgimPersonalContrato.getIdPersonalContrato());

        return pgimPersonalContratoDTOModificada;
    }

    private void configurarValoresAgregadas(PgimPersonalContratoDTO pgimPersonalContratoDTO,
            PgimPersonalContrato pgimPersonalContrato) {

        long claveCoUsuarioSiged = 0;
        pgimPersonalContrato.setPgimPersona(new PgimPersona());
        pgimPersonalContrato.getPgimPersona().setIdPersona(pgimPersonalContratoDTO.getIdPersona());
        pgimPersonalContrato.setPgimContrato(new PgimContrato());
        pgimPersonalContrato.getPgimContrato().setIdContrato(pgimPersonalContratoDTO.getIdContrato());
        pgimPersonalContrato.setNoUsuario(pgimPersonalContratoDTO.getNoUsuario());
        pgimPersonalContrato.setCoUsuarioSiged(claveCoUsuarioSiged);
        pgimPersonalContrato.setNoCargoEnContrato(pgimPersonalContratoDTO.getNoCargoEnContrato());
        pgimPersonalContrato.setNoPerfilEnContrato(pgimPersonalContratoDTO.getNoPerfilEnContrato());

        if(pgimPersonalContratoDTO.getIdRolProceso() != null){
            pgimPersonalContrato.setPgimRolProceso(new PgimRolProceso());
            pgimPersonalContrato.getPgimRolProceso().setIdRolProceso(pgimPersonalContratoDTO.getIdRolProceso()); // <!-- STORY: PGIM-7276: Relación de personal con roles del proceso de fiscalización -->
        }else{
            pgimPersonalContrato.setPgimRolProceso(null);
        }
        
        if(pgimPersonalContratoDTO.getIdDivisionSupervisora() != null){
            pgimPersonalContrato.setPgimValorParametro(new PgimValorParametro());
            pgimPersonalContrato.getPgimValorParametro().setIdValorParametro(pgimPersonalContratoDTO.getIdDivisionSupervisora()); // <!-- STORY: PGIM-9559: División supervisora para personal de contrato -->
        }else{
            pgimPersonalContrato.setPgimValorParametro(null);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public PgimPersonalContratoDTO crearPersonalContrato(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
            AuditoriaDTO auditoriaDTO) {
        PgimPersonalContrato pgimPersonalContrato = new PgimPersonalContrato();

        PgimPersona pgimPersona = this.asegurarPersona(pgimPersonalContratoDTO, auditoriaDTO);

        this.configurarValores(pgimPersonalContratoDTO, pgimPersonalContrato, pgimPersona);

        pgimPersonalContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimPersonalContrato.setFeCreacion(auditoriaDTO.getFecha());
        pgimPersonalContrato.setUsCreacion(auditoriaDTO.getUsername());
        pgimPersonalContrato.setIpCreacion(auditoriaDTO.getTerminal());

        PgimPersonalContrato pgimPersonalContratoCreado = this.personalContratoRepository.save(pgimPersonalContrato);

        PgimPersonalContratoDTO pgimPersonalContratoDTOCreado = this
                .obtenerPersonalContrato(pgimPersonalContratoCreado.getIdPersonalContrato());

        return pgimPersonalContratoDTOCreado;
    }

    @Override
    public PgimPersonalContrato getByIdPersonalContrato(Long idPersonalContrato) {
        return this.personalContratoRepository.findById(idPersonalContrato).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimPersonalContratoDTO modificarPersonalContrato(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
            PgimPersonalContrato pgimPersonalContrato, AuditoriaDTO auditoriaDTO) {

        PgimPersona pgimPersona = this.asegurarPersona(pgimPersonalContratoDTO, auditoriaDTO);

        this.configurarValores(pgimPersonalContratoDTO, pgimPersonalContrato, pgimPersona);

        pgimPersonalContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimPersonalContrato.setFeActualizacion(auditoriaDTO.getFecha());
        pgimPersonalContrato.setUsActualizacion(auditoriaDTO.getUsername());
        pgimPersonalContrato.setIpActualizacion(auditoriaDTO.getTerminal());

        this.personalContratoRepository.save(pgimPersonalContrato);

        PgimPersonalContratoDTO pgimPersonalContratoDTOModificada = obtenerPersonalContrato(
                pgimPersonalContrato.getIdPersonalContrato());

        return pgimPersonalContratoDTOModificada;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarPersonalContrato(PgimPersonalContrato pgimPersonalContratoActual, AuditoriaDTO auditoriaDTO) {
        pgimPersonalContratoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimPersonalContratoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimPersonalContratoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimPersonalContratoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.personalContratoRepository.save(pgimPersonalContratoActual);
    }

    @Transactional(readOnly = false)
    private void configurarValores(PgimPersonalContratoDTO pgimPersonalContratoDTO,
            PgimPersonalContrato pgimPersonalContrato, PgimPersona pgimPersona) {

        long claveCoUsuarioSiged = 0; 
        pgimPersonalContrato.setPgimContrato(new PgimContrato());
        pgimPersonalContrato.getPgimContrato().setIdContrato(pgimPersonalContratoDTO.getIdContrato());
        pgimPersonalContrato.setPgimPersona(pgimPersona);
        pgimPersonalContrato.setNoUsuario(pgimPersonalContratoDTO.getNoUsuario());
        pgimPersonalContrato.setCoUsuarioSiged(claveCoUsuarioSiged);
    }

    @Transactional(readOnly = false)
    private PgimPersona asegurarPersona(PgimPersonalContratoDTO pgimPersonalContratoDTO, AuditoriaDTO auditoriaDTO) {

        PgimPersona pgimPersona = this.personaService.obtenerPorTipoYNumero(
                pgimPersonalContratoDTO.getDescIdTipoDocIdentidad(),
                pgimPersonalContratoDTO.getDescCoDocumentoIdentidad());

        if (pgimPersona == null) {
            pgimPersona = new PgimPersona();
            pgimPersona.setFeCreacion(auditoriaDTO.getFecha());
            pgimPersona.setUsCreacion(auditoriaDTO.getUsername());
            pgimPersona.setIpCreacion(auditoriaDTO.getTerminal());
        } else {
            pgimPersona.setFeActualizacion(auditoriaDTO.getFecha());
            pgimPersona.setUsActualizacion(auditoriaDTO.getUsername());
            pgimPersona.setIpActualizacion(auditoriaDTO.getTerminal());
        }

        pgimPersona.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimPersona.setTipoDocIdentidad(new PgimValorParametro());
        pgimPersona.getTipoDocIdentidad().setIdValorParametro(pgimPersonalContratoDTO.getDescIdTipoDocIdentidad());
        pgimPersona.setCoDocumentoIdentidad(pgimPersonalContratoDTO.getDescCoDocumentoIdentidad());
        pgimPersona.setNoPersona(pgimPersonalContratoDTO.getDescNoPersona());
        pgimPersona.setApPaterno(pgimPersonalContratoDTO.getDescApPaterno());
        pgimPersona.setApMaterno(pgimPersonalContratoDTO.getDescApMaterno());

        if (pgimPersonalContratoDTO.getDescIdUbigeo() != null) {
            pgimPersona.setPgimUbigeo(new PgimUbigeo());
            pgimPersona.getPgimUbigeo().setIdUbigeo(pgimPersonalContratoDTO.getDescIdUbigeo());
        } else
            pgimPersona.setPgimUbigeo(null);

        pgimPersona = this.personaService.salvar(pgimPersona);

        return pgimPersona;
    }

    @Override
    public List<PgimPersonalContratoDTO> listarPorPersona(String palabraClave) {
        return this.personalContratoRepository.listarPorPersona(palabraClave, EValorParametro.DOIDE_RUC.toString());
    }

    @Override
    public List<PgimPersonalContratoDTO> existePersonalContrato(Long idContrato, Long idPersona,
            Long idPersonalContrato) {
        List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = this.personalContratoRepository
                .existePersonalContrato(idContrato, idPersona, idPersonalContrato);
        return lPgimPersonalContratoDTO;
    }

    @Override
    public List<PgimPersonalContratoDTO> obtenerPersonalContratoPorNombre(String userName, Long idContrato) {
        return this.personalContratoRepository.obtenerPersonalContratoPorUsuario(userName, idContrato);
    }

    @Override
    public List<PgimPersonalContratoDTO> existeNoUsuario(Long idPersonalContrato, String noUsuario) {
        List<PgimPersonalContratoDTO> pPgimContratoDTO = this.personalContratoRepository.existeNoUsuario(idPersonalContrato,
        noUsuario.toUpperCase() );

        return pPgimContratoDTO;
    }
}
