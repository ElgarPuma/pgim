package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado;
import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDemarcacionUminera;
import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.repository.DemarcacionUMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.PgimUbigeoRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.services.DemarcacionUMineraService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Demarcación de Unidad Minera
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 25/05/2020
 * @fecha_de_ultima_actualización: 30/05/2020 
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class DemarcacionUMineraServiceImpl implements DemarcacionUMineraService{

	@Autowired
	private DemarcacionUMineraRepository demarcacionUMineraRepository;
	
	@Autowired
	private UnidadMineraRepository unidadMineraRepository;
	
	@Autowired
	private PgimUbigeoRepository ubigeoRepository;
	
	@Transactional(readOnly = false)
	public Long registrarDemarcacionUMinera(PgimDemarcacionUmineraDTO demarcacionUmineraDTO, AuditoriaDTO auditoriaDTO){

		log.info("Ingresando a registrar Demarcacion UMinera");
		
		Long rpta = 0L;
		
		PgimDemarcacionUminera demarcacionUMinera = null;
		
		if(CommonsUtil.isNullOrZeroLong(demarcacionUmineraDTO.getIdDemarcacionUm())) {
			
			//if(demarcacionUmineraDTO.getFlPrincipal().equals("1")) { //comentado elozano siempre poner la constante adelante buenas practicas
			if(("1").equals(demarcacionUmineraDTO.getFlPrincipal())) {
				actualizarFlPrincipal(demarcacionUmineraDTO.getIdUnidadMinera(),auditoriaDTO);
			}
			
			demarcacionUMinera  = demarcacionUMineraRepository.findById(demarcacionUmineraDTO.getIdDemarcacionUm()).orElse(null);
			PgimUbigeo ubigeo = ubigeoRepository.findById(demarcacionUmineraDTO.getIdUbigeo()).orElse(null);
			demarcacionUMinera.setPgimUbigeo(ubigeo);
			demarcacionUMinera.setPcUbigeo(demarcacionUmineraDTO.getPcUbigeo());
			demarcacionUMinera.setFlPrincipal(demarcacionUmineraDTO.getFlPrincipal());
			demarcacionUMinera.setFeActualizacion(auditoriaDTO.getFecha());
			demarcacionUMinera.setUsActualizacion(auditoriaDTO.getUsername());
			demarcacionUMinera.setIpActualizacion(auditoriaDTO.getTerminal());
			
			if(demarcacionUMinera != null) {
				demarcacionUMineraRepository.save(demarcacionUMinera);
				rpta = demarcacionUMinera.getIdDemarcacionUm();
			}			
		}else {
			//if(demarcacionUmineraDTO.getFlPrincipal().equals("1")) { //comentado elozano siempre poner la constante adelante buenas practicas
			if(("1").equals(demarcacionUmineraDTO.getFlPrincipal())) {
				actualizarFlPrincipal(demarcacionUmineraDTO.getIdUnidadMinera(),auditoriaDTO);
			}
			
			demarcacionUMinera = new PgimDemarcacionUminera();
			
			PgimUnidadMinera unidadMinera = unidadMineraRepository.findById(demarcacionUmineraDTO.getIdUnidadMinera()).orElse(null);
			demarcacionUMinera.setPgimUnidadMinera(unidadMinera);
			PgimUbigeo ubigeo = ubigeoRepository.findById(demarcacionUmineraDTO.getIdUbigeo()).orElse(null);
			demarcacionUMinera.setPgimUbigeo(ubigeo);
			demarcacionUMinera.setPcUbigeo(demarcacionUmineraDTO.getPcUbigeo());
			demarcacionUMinera.setFlPrincipal(demarcacionUmineraDTO.getFlPrincipal());
			
			demarcacionUMinera.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			demarcacionUMinera.setFeCreacion(auditoriaDTO.getFecha());
			demarcacionUMinera.setUsCreacion(auditoriaDTO.getUsername());
			demarcacionUMinera.setIpCreacion(auditoriaDTO.getTerminal());
			
			demarcacionUMineraRepository.save(demarcacionUMinera);
			rpta = demarcacionUMinera.getIdDemarcacionUm();		
		}

		return rpta;
	}
	
	@Override
	public PgimDemarcacionUmineraDTO obtenerDemarcacionUMinera(Long idDemarcacion) {
		PgimDemarcacionUmineraDTO demarcacionUMinera = demarcacionUMineraRepository.getDemarcacionById(idDemarcacion);
		return demarcacionUMinera;
	}
	
	@Override
	public List<PgimUbigeoDTO> listarUbigeoPorNombre(String noUbigeo) {

		List<PgimUbigeoDTO> ubigeos = demarcacionUMineraRepository.getUbigeoPorNombre(noUbigeo);

		return ubigeos;
	}
	
	
	@Override
	public List<PgimUbigeoDTO> listarUbigeo() {

		List<PgimUbigeoDTO> ubigeos = demarcacionUMineraRepository.getUbigeo();

		return ubigeos;
	}
	

	@Transactional(readOnly = false)
	public Long modificarUnidadMineraUbicacion(PgimUnidadMineraDTO unidadMineraDTO, AuditoriaDTO auditoriaDTO){

		Long rpta = 0L;
		
		PgimUnidadMinera unidadMinera = null;
		
		if(CommonsUtil.isNullOrZeroLong(unidadMineraDTO.getIdUnidadMinera())) {
			unidadMinera  = unidadMineraRepository.findById(unidadMineraDTO.getIdUnidadMinera()).orElse(null);
			
			if(unidadMinera != null) {
				unidadMinera.setDeUbicacionAcceso(unidadMineraDTO.getDeUbicacionAcceso());
				unidadMinera.setFeActualizacion(auditoriaDTO.getFecha());
				unidadMinera.setUsActualizacion(auditoriaDTO.getUsername());
				unidadMinera.setIpActualizacion(auditoriaDTO.getTerminal());
				/*unidadMinera.setFeActualizacion(new Date());
				unidadMinera.setUsActualizacion("admin");
				unidadMinera.setIpActualizacion("127.0.0.1");*/
				unidadMineraRepository.save(unidadMinera);
				rpta = unidadMinera.getIdUnidadMinera();
			}			
		}
		
		return rpta;
	}

	@Override
	public List<PgimDemarcacionUmineraDTOResultado> listarDemarcacionesPorUm(Long id){
		List<PgimDemarcacionUmineraDTOResultado> demarcaciones = demarcacionUMineraRepository.findByUm(id);
		return demarcaciones;
	}
	
	@Transactional(readOnly = false)
	public Long eliminarDemarcacionUMinera(Long idDemarcacionUMinera, AuditoriaDTO auditoriaDTO){
		Long rpta = 0L;
		PgimDemarcacionUminera demarcacionUMinera = null;
		if(CommonsUtil.isNullOrZeroLong(idDemarcacionUMinera)) {
			demarcacionUMinera  = demarcacionUMineraRepository.findById(idDemarcacionUMinera).orElse(null);
			if(demarcacionUMinera != null) {
				demarcacionUMinera.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				/*demarcacionUMinera.setFeActualizacion(new Date());
				demarcacionUMinera.setUsActualizacion("admin");
				demarcacionUMinera.setIpActualizacion("127.0.0.1");*/
				demarcacionUMinera.setFeActualizacion(auditoriaDTO.getFecha());
				demarcacionUMinera.setUsActualizacion(auditoriaDTO.getUsername());
				demarcacionUMinera.setIpActualizacion(auditoriaDTO.getTerminal());
				demarcacionUMineraRepository.save(demarcacionUMinera);
				rpta = demarcacionUMinera.getIdDemarcacionUm();
			}
		}
		return rpta;
	}
	
	private void actualizarFlPrincipal(Long idUnidadMinera, AuditoriaDTO auditoriaDTO) {
		List<PgimDemarcacionUminera> lista = demarcacionUMineraRepository.getDemarcacionByidUnidadMinera(idUnidadMinera);
		for (PgimDemarcacionUminera pgimDemarcacionUminera : lista) {
			pgimDemarcacionUminera.setFlPrincipal("0");
			/*pgimDemarcacionUminera.setFeActualizacion(new Date());
			pgimDemarcacionUminera.setUsActualizacion("admin");
			pgimDemarcacionUminera.setIpActualizacion("127.0.0.1");*/
			pgimDemarcacionUminera.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDemarcacionUminera.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDemarcacionUminera.setIpActualizacion(auditoriaDTO.getTerminal());
			demarcacionUMineraRepository.save(pgimDemarcacionUminera);
		}
	}
	
	@Override
    public List<PgimDemarcacionUmineraDTO> existeDemarcacionUMinera(Long idUnidadMinera, Long idUbigeo, Long idDemarcacionUm) {
        List<PgimDemarcacionUmineraDTO> lDemarcacionUmineraDTO = this.demarcacionUMineraRepository.existeDemarcacionUMinera(idUnidadMinera,
        		idUbigeo, idDemarcacionUm);
        return lDemarcacionUmineraDTO;
    }
	
	@Override
    public PgimDemarcacionUmineraDTO excedeLimiteDemarcacionUMinera(Long idUnidadMinera, Long idDemarcacionUm) {
        PgimDemarcacionUmineraDTO demarcacionUmineraDTO = this.demarcacionUMineraRepository.excedeLimiteDemarcacionUMinera(idUnidadMinera, idDemarcacionUm);
        return demarcacionUmineraDTO;
    }
	
    @Override
    public Page<PgimDemarcacionAuxDTO> listarReporteDemarcacionesUMineraPaginado(PgimDemarcacionAuxDTO filtroUnidadMinera, Pageable paginador){
       
        Page<PgimDemarcacionAuxDTO> pPgimUnidadMineraDTO = this.demarcacionUMineraRepository.listarReporteDemarcacionesUMineraPaginado(
                filtroUnidadMinera.getCoUnidadMinera(), filtroUnidadMinera.getNoUnidadMinera(),
                filtroUnidadMinera.getCoDocumentoIdentidadAf(), filtroUnidadMinera.getNoRazonSocialAf(), 
                filtroUnidadMinera.getIdTipoUnidadMinera(),
                filtroUnidadMinera.getIdDivisionSupervisora(),
                filtroUnidadMinera.getFlPrincipalDemarcacion(),
                filtroUnidadMinera.getDescFlDemarcacion(),
                filtroUnidadMinera.getDescUbigeo(),
                filtroUnidadMinera.getIdDistritoDemarcacion(),
                paginador);

        return pPgimUnidadMineraDTO;
    }
	
}
