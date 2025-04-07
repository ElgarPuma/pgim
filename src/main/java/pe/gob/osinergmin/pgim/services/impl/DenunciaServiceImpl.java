package pe.gob.osinergmin.pgim.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDenuncia;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.DenunciaRepository;
import pe.gob.osinergmin.pgim.services.DenunciaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Denuncia
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 25/05/2020
 * @fecha_de_ultima_actualización: 30/05/2020 
 */
@Service
@Transactional(readOnly = true)
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;
    
    @Override
    public Page<PgimDenunciaDTO> listarDenuncia(Long idUnidadMinera, Pageable paginador) throws Exception {
        Page<PgimDenunciaDTO> lPgimDenunciaDTO = this.denunciaRepository.listarDenuncia(idUnidadMinera, EValorParametro.DOIDE_RUC.toString(), paginador);

        return lPgimDenunciaDTO;
    }

    @Override
    public PgimDenunciaDTO obtenerDenunciaPorId(Long idDenuncia) {
        return this.denunciaRepository.obtenerDenunciaPorId(idDenuncia, EValorParametro.DOIDE_RUC.toString());
    }

    @Transactional(readOnly = false)
    @Override
    public PgimDenunciaDTO crearDenuncia(PgimDenunciaDTO pgimDenunciaDTO, AuditoriaDTO auditoriaDTO) throws Exception {
        PgimDenuncia pgimDenuncia = new PgimDenuncia();

        this.configurarValores(pgimDenunciaDTO, pgimDenuncia);

        String anio = new SimpleDateFormat("yyyy").format(new Date());
        String coDenuncia = "";

        coDenuncia = "DEN-" + anio + "-";

        pgimDenuncia.setCoDenuncia(coDenuncia);
        // pgimDenuncia.setCoDenuncia(pgimDenunciaDTO.getCoDenuncia());

        pgimDenuncia.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimDenuncia.setFeCreacion(auditoriaDTO.getFecha());
        pgimDenuncia.setUsCreacion(auditoriaDTO.getUsername());
        pgimDenuncia.setIpCreacion(auditoriaDTO.getTerminal());

        PgimDenuncia pgimDenunciaCreado = this.denunciaRepository.save(pgimDenuncia);

        String correlativo = String.format("%04d", pgimDenuncia.getIdDenuncia());

        pgimDenuncia.setCoDenuncia(coDenuncia + correlativo);

        PgimDenunciaDTO pgimDenunciaCreadoDTOCreado = this.obtenerDenunciaPorId(pgimDenunciaCreado.getIdDenuncia());

        return pgimDenunciaCreadoDTOCreado;
    }

    @Transactional(readOnly = false)
    private PgimDenuncia configurarValores(PgimDenunciaDTO pgimDenunciaDTO, PgimDenuncia pgimDenuncia) {

        PgimUnidadMinera pgimUnidadMinera = new PgimUnidadMinera();
        pgimUnidadMinera.setIdUnidadMinera(pgimDenunciaDTO.getIdUnidadMinera());
        pgimDenuncia.setPgimUnidadMinera(pgimUnidadMinera);

        // Persona denunciante
        if (pgimDenunciaDTO.getIdPersonaDenunciante() != null) {
            PgimPersona pgimPersona = new PgimPersona();
            pgimPersona.setIdPersona(pgimDenunciaDTO.getIdPersonaDenunciante());
            pgimDenuncia.setPersonaDenunciante(pgimPersona);
        } else {
            pgimDenuncia.setPersonaDenunciante(null);
        }

        // Medio de denuncia
        if (pgimDenunciaDTO.getIdMedioDenuncia() != null) {
            PgimValorParametro pgimValorParametro = new PgimValorParametro();
            pgimValorParametro.setIdValorParametro(pgimDenunciaDTO.getIdMedioDenuncia());
            pgimDenuncia.setMedioDenuncia(pgimValorParametro);
        } else {
            pgimDenuncia.setMedioDenuncia(null);
        }

        pgimDenuncia.setFePresentacion(pgimDenunciaDTO.getFePresentacion());
        pgimDenuncia.setDeDenuncia(pgimDenunciaDTO.getDeDenuncia());

        return pgimDenuncia;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimDenunciaDTO modificarDenuncia(@Valid PgimDenunciaDTO pgimDenunciaDTO, PgimDenuncia pgimDenuncia,
            AuditoriaDTO auditoriaDTO) throws Exception {
        this.configurarValores(pgimDenunciaDTO, pgimDenuncia);

        pgimDenuncia.setFeActualizacion(auditoriaDTO.getFecha());
        pgimDenuncia.setUsActualizacion(auditoriaDTO.getUsername());
        pgimDenuncia.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimDenuncia pgimDenunciaModificado = this.denunciaRepository.save(pgimDenuncia);

        PgimDenunciaDTO pgimDenunciaDTOModificado = this.obtenerDenunciaPorId(pgimDenunciaModificado.getIdDenuncia());

        return pgimDenunciaDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarDenuncia(PgimDenuncia pgimDenunciaActual, AuditoriaDTO auditoriaDTO) throws Exception {
        pgimDenunciaActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimDenunciaActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimDenunciaActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimDenunciaActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.denunciaRepository.save(pgimDenunciaActual);
    }

    @Override
    public PgimDenuncia getByIdDenuncia(Long idDenuncia) {
        return this.denunciaRepository.findById(idDenuncia).orElse(null);
    }

    @Override
    public List<PgimDenunciaDTO> listarPorPersona(Long idUnidadMinera, String palabraClave) {
        return this.denunciaRepository.listarPorPersona(idUnidadMinera, palabraClave, EValorParametro.DOIDE_RUC.toString());
    }

    @Override
    public PgimDenunciaDTO obtenerMaxCodigoDenuncia() {
        // return null;
        return this.denunciaRepository.obtenerMaxCodigoDenuncia();
    }

    @Override
    public PgimDenunciaDTO obtenerMaxCodigoDenunciaPorId(Long idDenuncia) {
        // return null;
        return this.denunciaRepository.obtenerMaxCodigoDenunciaPorId(idDenuncia);
    }

    @Override
    public Page<PgimDenunciaDTO> listarDenunciaGeneral(PgimDenunciaDTO filtroDenunciaDTO, Pageable paginador)
            throws Exception {
        Page<PgimDenunciaDTO> pPgimDenunciaDTO = this.denunciaRepository.listarDenunciaGeneral(
                filtroDenunciaDTO.getCoDenuncia(), filtroDenunciaDTO.getIdMedioDenuncia(),
                filtroDenunciaDTO.getDescIdTipoDocIdentidad(), filtroDenunciaDTO.getFePresentacion(),
                filtroDenunciaDTO.getFePresentacionHasta(), 
                filtroDenunciaDTO.getDescNoUnidadMinera(),
                filtroDenunciaDTO.getDescNoPersonaDenunciante(), 
                filtroDenunciaDTO.getTextoBusqueda(), 
                EValorParametro.DOIDE_RUC.toString(),
                paginador
        		);

        return pPgimDenunciaDTO;
    }

    @Override
    public List<PgimDenunciaDTO> listarPorPersonaGeneral(String palabraClave) {
        return this.denunciaRepository.listarPorPersonaGeneral(palabraClave, EValorParametro.DOIDE_RUC.toString());
    }
    
}
