package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.Ranking;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUm;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmFactor;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUmGrupo;
import pe.gob.osinergmin.pgim.models.repository.RankingUmAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmFactorRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmGrupoRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingUmRepository;
import pe.gob.osinergmin.pgim.services.RankingUmService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Ranking unidad minera
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */

@Service
@Transactional(readOnly = true)
public class RankingUmServiceImpl implements RankingUmService {

    @Autowired
    private RankingUmAuxRepository rankingUmAuxRepository;
    
    @Autowired
    private RankingUmRepository rankingUmRepository;

    @Autowired
    private RankingUmGrupoRepository rankingUmGrupoRepository;
    
    @Autowired
    private RankingUmFactorRepository rankingUmFactorRepository;

    @Autowired
    private RankingRiesgoServiceImpl rankingRiesgoServiceImpl;
    
    
    @Override
	public PgimRankingUm getByIdRankingUm(Long idRankingUm) {
		return this.rankingUmRepository.findById(idRankingUm).orElse(null);
	}

    @Override
    public Page<PgimRankingUmAuxDTO> listarRankingUm(final Long idRankingRiesgo, final Pageable paginador) {
        return this.rankingUmAuxRepository.listarRankingUm(idRankingRiesgo, paginador);
    }
    
    @Override
    public Page<PgimRankingUmAuxDTO> listarRankingUmPorFiltroUM(PgimUnidadMineraAuxDTO filtro, String flActivo, Pageable paginador) {
    	
    	Page<PgimRankingUmAuxDTO> pPgimRankingUmAuxDTO = this.rankingUmAuxRepository.listarRankingUmPorFiltroUM(
        		filtro.getCoUnidadMinera(), filtro.getNoUnidadMinera(),
                filtro.getCoDocumentoIdentidad(), filtro.getNoRazonSocial(),
                filtro.getIdSituacion(), filtro.getIdTipoUnidadMinera(),
                filtro.getIdDivisionSupervisora(), filtro.getIdMetodoMinado(), 
                filtro.getIdTipoActividad(), filtro.getIdEstadoUm(), filtro.getDescIdRankingRiesgo(), 
                filtro.getTextoBusqueda(), flActivo, paginador);

    	return pPgimRankingUmAuxDTO;
    }

    @Override
    public List<PgimRankingUmAuxDTO> listarRankingUmByIdRanking(Long idRankingRiesgo) {
        return this.rankingUmAuxRepository.listarRankingUmByIdRanking(idRankingRiesgo);
    }

    @Override
    public List<PgimRankingUmAuxDTO> listarResumenPorAnonimizacion(Long idRankingRiesgo) {
        return this.rankingUmAuxRepository.listarRankingUmPorAnonimizacion(idRankingRiesgo);
    }

    @Override
    @Transactional(readOnly = false)
    public void asegurarRegistrarEnRankingUmGrupo(Long idRankingUmGrupo, String esRegistrar, Long idGrupoRiesgo,
            AuditoriaDTO auditoriaDTO) {
        PgimRankingUmGrupo pgimRankingUmGrupo = this.rankingUmGrupoRepository.findById(idRankingUmGrupo).orElse(null);

        pgimRankingUmGrupo.setFlRegistrar(esRegistrar);
        if (esRegistrar.equals("0")) {
            pgimRankingUmGrupo.setNuCalificacionGrupo(null);
            pgimRankingUmGrupo.setFlMaximo("0");
        }
        pgimRankingUmGrupo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimRankingUmGrupo.setFeActualizacion(auditoriaDTO.getFecha());
        pgimRankingUmGrupo.setUsActualizacion(auditoriaDTO.getUsername());
        pgimRankingUmGrupo.setIpActualizacion(auditoriaDTO.getTerminal());

        pgimRankingUmGrupo = this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);

        Ranking ranking = new Ranking();
        List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = this.rankingRiesgoServiceImpl.listarRankingUmFactor(
                idRankingUmGrupo,
                idGrupoRiesgo);

        ranking.setIdRankingUmGrupo(idRankingUmGrupo);
        ranking.setlPgimRankingUmFactorDTO(lPgimRankingUmFactorDTO);

        this.rankingRiesgoServiceImpl.modificarRankingUmFactor(ranking, auditoriaDTO);

        if (esRegistrar.equals("0")) {
            pgimRankingUmGrupo.setMoPuntaje(null);
            pgimRankingUmGrupo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
            pgimRankingUmGrupo.setFeActualizacion(auditoriaDTO.getFecha());
            pgimRankingUmGrupo.setUsActualizacion(auditoriaDTO.getUsername());
            pgimRankingUmGrupo.setIpActualizacion(auditoriaDTO.getTerminal());

            this.rankingUmGrupoRepository.save(pgimRankingUmGrupo);
        }
    }    
    
    @Transactional(readOnly = false)
    @Override
    public void eliminarRankingUm(PgimRankingUm pgimRankingUmActual, AuditoriaDTO auditoriaDTO) {
    	
    	// Obtenemos lista de RANKING_UM_GRUPO a eliminar
    	List<PgimRankingUmGrupoDTO> lPgimRankingUmGrupoDTO = this.rankingUmGrupoRepository
				.listarRankingUmGrupoPorRankingUm(pgimRankingUmActual.getIdRankingUm());
    	
    	for (PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO : lPgimRankingUmGrupoDTO) {
    		
    		// Obtenemos lista de RANKING_UM_FACTOR a eliminar
        	List<PgimRankingUmFactorDTO> lPgimRankingUmFactorDTO = this.rankingUmFactorRepository
    				.listarFactoresIncluidoPendientesPorIdGrupo(pgimRankingUmGrupoDTO.getIdRankingUmGrupo());
        	
        	for (PgimRankingUmFactorDTO pgimRankingUmFactorDTO : lPgimRankingUmFactorDTO) {        		
        		// Eliminamos cada RANKING_UM_FACTOR        		
        		PgimRankingUmFactor pgimRankingUmFactorActual = this.rankingUmFactorRepository.findById(pgimRankingUmFactorDTO.getIdRankingUmFactor()).orElse(null);
        		this.eliminarRankingUmFactor(pgimRankingUmFactorActual, auditoriaDTO);        		        		
			}
        	
        	// Eliminamos cada RANKING_UM_GRUPO        		
        	PgimRankingUmGrupo pgimRankingUmGrupoActual = this.rankingUmGrupoRepository.findById(pgimRankingUmGrupoDTO.getIdRankingUmGrupo()).orElse(null);
    		this.eliminarRankingUmGrupo(pgimRankingUmGrupoActual, auditoriaDTO);
		}

    	// Finalmente eliminamos el RANKING_UM
        
    	pgimRankingUmActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimRankingUmActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimRankingUmActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimRankingUmActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.rankingUmRepository.save(pgimRankingUmActual);
    }  
    
    @Transactional(readOnly = false)
    @Override
    public void conmutarFlActivoRankingUm(PgimRankingUm pgimRankingUmActual, String flActivo, AuditoriaDTO auditoriaDTO) {

    	pgimRankingUmActual.setFlActivo(flActivo);
    	pgimRankingUmActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimRankingUmActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimRankingUmActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.rankingUmRepository.save(pgimRankingUmActual);
    }  
        
    @Transactional(readOnly = false)
    public void eliminarRankingUmGrupo(PgimRankingUmGrupo pgimRankingUmGrupoActual, AuditoriaDTO auditoriaDTO) {
        
    	pgimRankingUmGrupoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimRankingUmGrupoActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimRankingUmGrupoActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimRankingUmGrupoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.rankingUmGrupoRepository.save(pgimRankingUmGrupoActual);
    }
    
    @Transactional(readOnly = false)
    public void eliminarRankingUmFactor(PgimRankingUmFactor pgimRankingUmFactorActual, AuditoriaDTO auditoriaDTO) {
        
    	pgimRankingUmFactorActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimRankingUmFactorActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimRankingUmFactorActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimRankingUmFactorActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.rankingUmFactorRepository.save(pgimRankingUmFactorActual);
    }

}
