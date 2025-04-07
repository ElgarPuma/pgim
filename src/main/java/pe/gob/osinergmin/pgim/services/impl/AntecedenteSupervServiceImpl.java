package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAddAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAntecedenteSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AntecedenteSupervRepository;
import pe.gob.osinergmin.pgim.services.AntecedenteSupervService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.siged.DocumentoConsultaOutRO;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestion de la interaccion con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad antecedentes de la supervisión
 * 
 * @author: palominovega
 * @version: 1.0 @fecha_de_creacion: 02/09/2020 @fecha_de_ultima_actualizacion:
 *           02/09/2020
 */
@Service
@Transactional(readOnly = true)
public class AntecedenteSupervServiceImpl implements AntecedenteSupervService {
	
	@Autowired
	AntecedenteSupervRepository antecedenteSupervRepository;
	
	@Autowired
	DocumentoService documentoService;
	
	@Override
	public Page<PgimSelectAntecedenteAuxDTO> listarSelecccionAntecedente(PgimSupervisionDTO pgimSupervisionDTO,  Pageable paginador) {
		Page<PgimSelectAntecedenteAuxDTO> pPgimSelectAntecedenteAuxDTO = antecedenteSupervRepository.listarAntecedenteSeleccion( pgimSupervisionDTO.getIdUnidadMinera(), 
				pgimSupervisionDTO.getDescIdEspecialidad(), pgimSupervisionDTO.getIdSupervision(), paginador);
		
		return pPgimSelectAntecedenteAuxDTO;
	}
	
	@Transactional(readOnly = false)
    @Override
	public PgimSelectAntecedenteAuxDTO crearAntecedenteDesdeSelectAntecedente(PgimSelectAntecedenteAddAuxDTO pgimSelectAntecedenteAddAuxDTO, AuditoriaDTO auditoriaDTO) {
		
		//registramos los antecedentes seleccionados
		for (PgimSelectAntecedenteAuxDTO pgimSelectAntecedenteAuxDTO : pgimSelectAntecedenteAddAuxDTO.getAuxListaSelectAntecedente()) {
			
			PgimAntecedenteSuperv pgimAntecedenteSuperv = new PgimAntecedenteSuperv();
			
			pgimAntecedenteSuperv.setPgimSupervision(new PgimSupervision());
			pgimAntecedenteSuperv.getPgimSupervision().setIdSupervision(pgimSelectAntecedenteAddAuxDTO.getIdSupervision());
			
			pgimAntecedenteSuperv.setPgimDocumento(new PgimDocumento());
			pgimAntecedenteSuperv.getPgimDocumento().setIdDocumento(pgimSelectAntecedenteAuxDTO.getIdDocumento());
			
			pgimAntecedenteSuperv.setTipoAntecedente(new PgimValorParametro());
			pgimAntecedenteSuperv.getTipoAntecedente().setIdValorParametro(pgimSelectAntecedenteAuxDTO.getIdTipoAntecedente());
			
			pgimAntecedenteSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
	        pgimAntecedenteSuperv.setFeCreacion(auditoriaDTO.getFecha());
	        pgimAntecedenteSuperv.setUsCreacion(auditoriaDTO.getUsername());
	        pgimAntecedenteSuperv.setIpCreacion(auditoriaDTO.getTerminal());
	        
	        antecedenteSupervRepository.save(pgimAntecedenteSuperv);	        
		}
		
		return pgimSelectAntecedenteAddAuxDTO.getAuxListaSelectAntecedente()[0];
				
	}
	
	@Transactional(readOnly = false)
    @Override
	public PgimAntecedenteSupervDTO crearAntecedente(PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO, AuditoriaDTO auditoriaDTO) {
		
		PgimAntecedenteSuperv pgimAntecedenteSuperv = new PgimAntecedenteSuperv();
		pgimAntecedenteSuperv.setPgimSupervision(new PgimSupervision());
		pgimAntecedenteSuperv.getPgimSupervision().setIdSupervision(pgimAntecedenteSupervDTO.getIdSupervision());
		
		pgimAntecedenteSuperv.setPgimDocumento(new PgimDocumento());
		pgimAntecedenteSuperv.getPgimDocumento().setIdDocumento(pgimAntecedenteSupervDTO.getIdDocumento());
		
		pgimAntecedenteSuperv.setTipoAntecedente(new PgimValorParametro());
		pgimAntecedenteSuperv.getTipoAntecedente().setIdValorParametro(pgimAntecedenteSupervDTO.getIdTipoAntecedente());
		
		if(pgimAntecedenteSupervDTO.getIdTipoAntecedente().equals(ConstantesUtil.PARAM_TIPO_ANTECEDENTE_INFORME_SUPERV)) {
			
			pgimAntecedenteSuperv.setTipoSupervision(new PgimValorParametro());
			pgimAntecedenteSuperv.getTipoSupervision().setIdValorParametro(pgimAntecedenteSupervDTO.getIdTipoSupervision());
			
			pgimAntecedenteSuperv.setFeInicioSupervisionReal(pgimAntecedenteSupervDTO.getFeInicioSupervisionReal());
			pgimAntecedenteSuperv.setFeFinSupervisionReal(pgimAntecedenteSupervDTO.getFeFinSupervisionReal());
		}
		
		pgimAntecedenteSuperv.setNuExpedienteSiged(pgimAntecedenteSupervDTO.getNuExpedienteSiged());
		pgimAntecedenteSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimAntecedenteSuperv.setFeCreacion(auditoriaDTO.getFecha());
        pgimAntecedenteSuperv.setUsCreacion(auditoriaDTO.getUsername());
        pgimAntecedenteSuperv.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimAntecedenteSuperv pgimAntecedenteSupervCreada = antecedenteSupervRepository.save(pgimAntecedenteSuperv);
        
        PgimAntecedenteSupervDTO pgimAntecedenteSupervDTOCreado = this.obtenerAntecedenteSupervDTOPorId(pgimAntecedenteSupervCreada.getIdAntecedenteSuperv());
        

		return pgimAntecedenteSupervDTOCreado;
				
	}
	
	@Override
    public PgimAntecedenteSupervDTO obtenerAntecedenteSupervDTOPorId(Long idAntecedenteSuperv) {
        return this.antecedenteSupervRepository.obtenerAntecedenteSupervDTOPorId(idAntecedenteSuperv);
    }
    
	@Override
	public Page<PgimAntecedenteAuxDTO> listarAntecedentes(Long idSupervisión,  Pageable paginador, AuditoriaDTO AuditoriaDTO) throws Exception {
		Page<PgimAntecedenteAuxDTO> pPgimAntecedenteAuxDTO = antecedenteSupervRepository.listarAntecedente( idSupervisión, paginador);

		for (PgimAntecedenteAuxDTO pgimAntecedenteAuxDTO : pPgimAntecedenteAuxDTO.getContent()) {
			if(pgimAntecedenteAuxDTO.getCoDocumentoSigedPCopia() != null && pgimAntecedenteAuxDTO.getNuExpedienteSiged() == null){
				DocumentoConsultaOutRO documentoConsultaOutRO = documentoService.listarArchivosConVersiones_old(pgimAntecedenteAuxDTO.getCoDocumentoSigedPCopia().toString(), AuditoriaDTO);
				pgimAntecedenteAuxDTO.setNuExpedienteSiged(documentoConsultaOutRO.getNroExpediente());

			}
		}
		
		return pPgimAntecedenteAuxDTO;
	}
	
	@Transactional(readOnly = false)
	@Override
	public PgimAntecedenteSupervDTO modificarAntecedente(PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO, PgimAntecedenteSuperv pgimAntecedenteSuperv, AuditoriaDTO auditoriaDTO) {
		
		pgimAntecedenteSuperv.setDeAspectosRevisados(pgimAntecedenteSupervDTO.getDeAspectosRevisados());
		
		pgimAntecedenteSuperv.setFeActualizacion(auditoriaDTO.getFecha());
		pgimAntecedenteSuperv.setUsActualizacion(auditoriaDTO.getUsername());
		pgimAntecedenteSuperv.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimAntecedenteSuperv pgimAntecedenteSupervModificado = this.antecedenteSupervRepository.save(pgimAntecedenteSuperv);

		PgimAntecedenteSupervDTO PgimAntecedenteSupervDTOResultado = this.antecedenteSupervRepository.obtenerAntecedenteSupervDTOPorId(pgimAntecedenteSupervModificado.getIdAntecedenteSuperv());

		return PgimAntecedenteSupervDTOResultado;

	};
	
	@Transactional(readOnly = false)
	@Override
	public PgimAntecedenteSupervDTO modificarAntecedenteAdjuntado(PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO, PgimAntecedenteSuperv pgimAntecedenteSuperv, AuditoriaDTO auditoriaDTO) {
		
		if(pgimAntecedenteSupervDTO.getIdTipoAntecedente().equals(ConstantesUtil.PARAM_TIPO_ANTECEDENTE_INFORME_SUPERV)) {
			
			pgimAntecedenteSuperv.setTipoSupervision(new PgimValorParametro());
			pgimAntecedenteSuperv.getTipoSupervision().setIdValorParametro(pgimAntecedenteSupervDTO.getIdTipoSupervision());
			
			pgimAntecedenteSuperv.setNuExpedienteSiged(pgimAntecedenteSupervDTO.getNuExpedienteSiged());
			pgimAntecedenteSuperv.setFeInicioSupervisionReal(pgimAntecedenteSupervDTO.getFeInicioSupervisionReal());
			pgimAntecedenteSuperv.setFeFinSupervisionReal(pgimAntecedenteSupervDTO.getFeFinSupervisionReal());
		}

		if(pgimAntecedenteSupervDTO.getNuExpedienteSiged() != null){
			pgimAntecedenteSuperv.setNuExpedienteSiged(pgimAntecedenteSupervDTO.getNuExpedienteSiged());
		}
		
		pgimAntecedenteSuperv.setFeActualizacion(auditoriaDTO.getFecha());
		pgimAntecedenteSuperv.setUsActualizacion(auditoriaDTO.getUsername());
		pgimAntecedenteSuperv.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimAntecedenteSuperv pgimAntecedenteSupervModificado = this.antecedenteSupervRepository.save(pgimAntecedenteSuperv);
		
		PgimAntecedenteSupervDTO PgimAntecedenteSupervDTOResultado = this.antecedenteSupervRepository.obtenerAntecedenteSupervDTOPorId(pgimAntecedenteSupervModificado.getIdAntecedenteSuperv());
		
		return PgimAntecedenteSupervDTOResultado;
		
	};
	
	@Override
	public PgimAntecedenteSuperv obtenerAntecedenteSupervPorId(Long idAntecedenteSuperv) {
		return this.antecedenteSupervRepository.findById(idAntecedenteSuperv).orElse(null);
	}
	
	@Transactional(readOnly = false)
	@Override
	public Long eliminarAntecedente(Long idAntecedenteSuperv, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimAntecedenteSuperv pgimAntecedenteSuperv = null;
		
		if (CommonsUtil.isNullOrZeroLong(idAntecedenteSuperv)) {
			pgimAntecedenteSuperv = this.antecedenteSupervRepository.findById(idAntecedenteSuperv).orElse(null);
			if (pgimAntecedenteSuperv != null) {
				pgimAntecedenteSuperv.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				pgimAntecedenteSuperv.setFeActualizacion(auditoriaDTO.getFecha());
				pgimAntecedenteSuperv.setUsActualizacion(auditoriaDTO.getUsername());
				pgimAntecedenteSuperv.setIpActualizacion(auditoriaDTO.getTerminal());
				
				this.antecedenteSupervRepository.save(pgimAntecedenteSuperv);
				
				rpta = pgimAntecedenteSuperv.getIdAntecedenteSuperv();
			}
		}
		return rpta;
	};
	
	public Integer validarRevisionAntecedente(Long idSupervisión){
		Integer cantidad = antecedenteSupervRepository.validarRevisionAntecedente( idSupervisión);
		
		return cantidad;
	};
	
	public Integer cantidadAntecedentes(Long idSupervisión) {
		
		Integer cantidad = antecedenteSupervRepository.cantidadAntecedentes( idSupervisión);
		return cantidad; 
	};
	
}
