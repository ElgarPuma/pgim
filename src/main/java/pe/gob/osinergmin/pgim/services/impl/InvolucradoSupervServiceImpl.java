package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInvolucradoSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.InvolucradoSupervRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.InvolucradoSupervService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Involucrado superv
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */
@Service
@Transactional(readOnly = true)
public class InvolucradoSupervServiceImpl implements InvolucradoSupervService {

    @Autowired
    private InvolucradoSupervRepository involucradoSupervRepository;

    @Autowired
    private SupervisionRepository supervisionRepository;

    @Override
    public List<PgimInvolucradoSupervDTO> listarInvolucradoSupervision(final Long idSupervision,
            final Long idValorParametro) throws Exception {
        return this.involucradoSupervRepository.listarInvolucradoSupervision(idSupervision, idValorParametro);
    }

    @Override
    public PgimInvolucradoSupervDTO obtenerInvolucradoSupervision(Long idInvolucradoSuperv) {
        return this.involucradoSupervRepository.obtenerInvolucradoSuperv(idInvolucradoSuperv);
    }

    @Override
    public List<PgimInvolucradoSupervDTO> listarPorInvolucradoAiAs(String palabraClave) {
    	return this.involucradoSupervRepository.listarPorInvolucradoAiAs(palabraClave, EValorParametro.DOIDE_RUC.toString());
    }

    @Override
    public PgimInvolucradoSuperv getByIdInvolucradoSupervision(Long idInvolucradoSuperv) {
        return this.involucradoSupervRepository.findById(idInvolucradoSuperv).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimInvolucradoSupervDTO crearInvolucradoSupervision(
            @Valid PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO, AuditoriaDTO auditoriaDTO) {

        PgimInvolucradoSuperv pgimInvolucradoSuperv = new PgimInvolucradoSuperv();

        this.configurarValoresAgregadas(pgimInvolucradoSupervDTO, pgimInvolucradoSuperv);

        /**
         * Falta un campo mas "AÑADIR EL TIPO DE ACTA DE INVOLUCRADO =
         * ID_TIPO_ACTA_INVOLUCRADO"
         */
        pgimInvolucradoSuperv.setTipoActaInvolucrado(new PgimValorParametro());
        pgimInvolucradoSuperv.getTipoActaInvolucrado()
                .setIdValorParametro(pgimInvolucradoSupervDTO.getIdTipoActaInvolucrado());
     // pgimInvolucradoSuperv.getTipoActaInvolucrado().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ACINV_ACTA_INCIO.toString()));

        pgimInvolucradoSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimInvolucradoSuperv.setFeCreacion(auditoriaDTO.getFecha());
        pgimInvolucradoSuperv.setUsCreacion(auditoriaDTO.getUsername());
        pgimInvolucradoSuperv.setIpCreacion(auditoriaDTO.getTerminal());

        PgimInvolucradoSuperv pgimInvolucradoSupervCreado = this.involucradoSupervRepository
                .save(pgimInvolucradoSuperv);

        PgimInvolucradoSupervDTO pgimInvolucradoSupervDTOCreado = this
                .obtenerInvolucradoSupervision(pgimInvolucradoSupervCreado.getIdInvolucradoSuperv());

        return pgimInvolucradoSupervDTOCreado;
    }

    private void configurarValoresAgregadas(PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO,
            PgimInvolucradoSuperv pgimInvolucradoSuperv) {

        pgimInvolucradoSuperv.setPgimPersona(new PgimPersona());
        pgimInvolucradoSuperv.getPgimPersona().setIdPersona(pgimInvolucradoSupervDTO.getIdPersona());

        pgimInvolucradoSuperv.setTipoInvolucrado(new PgimValorParametro());
        pgimInvolucradoSuperv.getTipoInvolucrado().setIdValorParametro(pgimInvolucradoSupervDTO.getIdTipoInvolucrado());

        pgimInvolucradoSuperv.setTipoPrefijoInvolucrado(new PgimValorParametro());
        pgimInvolucradoSuperv.getTipoPrefijoInvolucrado()
                .setIdValorParametro(pgimInvolucradoSupervDTO.getIdTipoPrefijoInvolucrado());

        pgimInvolucradoSuperv.setPgimSupervision(new PgimSupervision());
        pgimInvolucradoSuperv.getPgimSupervision().setIdSupervision(pgimInvolucradoSupervDTO.getIdSupervision());

        // if (pgimInvolucradoSupervDTO.getIdTipoActaInvolucrado() !=
        // this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ACINV_ACTA_INCIO.toString())){
        // pgimInvolucradoSuperv.setTipoActaInvolucrado(new PgimValorParametro());
        // pgimInvolucradoSuperv.getTipoActaInvolucrado().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ACINV_ACTA_INCIO.toString()));
        // }
        // if (pgimInvolucradoSupervDTO.getIdTipoActaInvolucrado() ==
        // this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ACINV_ACTA_SPRVSION.toString())) {
        // pgimInvolucradoSuperv.setTipoActaInvolucrado(new PgimValorParametro());
        // pgimInvolucradoSuperv.getTipoActaInvolucrado().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.ACINV_ACTA_SPRVSION.toString()));
        // }

        pgimInvolucradoSuperv.setDeCargo(pgimInvolucradoSupervDTO.getDeCargo());
    }

    @Transactional(readOnly = false)
    @Override
    public PgimInvolucradoSupervDTO modificarInvolucradoSupervision(
            @Valid PgimInvolucradoSupervDTO pgimInvolucradoSupervDTO, AuditoriaDTO auditoriaDTO) {

        PgimInvolucradoSuperv pgimInvolucradoSuperv = null;

        Optional<PgimInvolucradoSuperv> involucradoSuperv = involucradoSupervRepository
                .findById(pgimInvolucradoSupervDTO.getIdInvolucradoSuperv());

        pgimInvolucradoSuperv = involucradoSuperv.get();

        this.configurarValoresAgregadas(pgimInvolucradoSupervDTO, pgimInvolucradoSuperv);

        pgimInvolucradoSuperv.setFeActualizacion(auditoriaDTO.getFecha());
        pgimInvolucradoSuperv.setUsActualizacion(auditoriaDTO.getUsername());
        pgimInvolucradoSuperv.setIpActualizacion(auditoriaDTO.getTerminal());

        this.involucradoSupervRepository.save(pgimInvolucradoSuperv);

        PgimInvolucradoSupervDTO pgimInvolucradoSupervDTOModificada = obtenerInvolucradoSupervision(
                pgimInvolucradoSuperv.getIdInvolucradoSuperv());

        return pgimInvolucradoSupervDTOModificada;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarInvolucradoSupervision(PgimInvolucradoSuperv pgimInvolucradoSupervActual,
            AuditoriaDTO auditoriaDTO) {

        pgimInvolucradoSupervActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimInvolucradoSupervActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimInvolucradoSupervActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimInvolucradoSupervActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.involucradoSupervRepository.save(pgimInvolucradoSupervActual);

    }

    @Override
    @Transactional(readOnly = false)
    public Long modificarObservacionInicioSuper(PgimSupervisionDTO supervisionDTO, AuditoriaDTO auditoriaDTO) {
        Long rpta = 0L;
		
		PgimSupervision supervision = null;
		
		if(CommonsUtil.isNullOrZeroLong(supervisionDTO.getIdSupervision())) {
			supervision  = supervisionRepository.findById(supervisionDTO.getIdSupervision()).orElse(null);
			
			if(supervision != null) {
				supervision.setDeObservacionInicioSuperT(supervisionDTO.getDeObservacionInicioSuperT());
				supervision.setFeActualizacion(auditoriaDTO.getFecha());
				supervision.setUsActualizacion(auditoriaDTO.getUsername());
				supervision.setIpActualizacion(auditoriaDTO.getTerminal());
				supervisionRepository.save(supervision);
				rpta = supervision.getIdSupervision();
			}			
		}
		
		return rpta;
    }
    

    @Override
    @Transactional(readOnly = false)
    public Long modificarObservacionFinSuper(PgimSupervisionDTO supervisionDTO, AuditoriaDTO auditoriaDTO) {
        Long rpta = 0L;
		
		PgimSupervision supervision = null;
		
		if(CommonsUtil.isNullOrZeroLong(supervisionDTO.getIdSupervision())) {
			supervision  = supervisionRepository.findById(supervisionDTO.getIdSupervision()).orElse(null);
			
			if(supervision != null) {
				supervision.setDeObservacionFinSuperT(supervisionDTO.getDeObservacionFinSuperT());
				supervision.setFeActualizacion(auditoriaDTO.getFecha());
				supervision.setUsActualizacion(auditoriaDTO.getUsername());
				supervision.setIpActualizacion(auditoriaDTO.getTerminal());
				supervisionRepository.save(supervision);
				rpta = supervision.getIdSupervision();
			}			
		}
		
		return rpta;

    }
    
    @Override
    public List<PgimInvolucradoSupervDTO> obtenerRepresentantesAgenteSupervisado(Long idSupervision, Long tipoActaInvolucrado) {
        return involucradoSupervRepository.obtenerRepresentantesAgenteSupervisado(idSupervision, tipoActaInvolucrado);
    }
    
    @Override
    public List<PgimInvolucradoSupervDTO> obtenerRepresentantesTrabajadores(Long idSupervision, Long tipoActaInvolucrado) {
        return involucradoSupervRepository.obtenerRepresentantesTrabajadores(idSupervision, tipoActaInvolucrado);
    }

    @Override
    public List<PgimInvolucradoSupervDTO> existeRepresentanteAi(Long idValorParametro, Long idSupervision, String coDocumentoIdentidad) {
        List<PgimInvolucradoSupervDTO> pPgimContratoDTO = this.involucradoSupervRepository.existeRepresentanteAi(idValorParametro, idSupervision,
        coDocumentoIdentidad);

return pPgimContratoDTO;
    }
}
