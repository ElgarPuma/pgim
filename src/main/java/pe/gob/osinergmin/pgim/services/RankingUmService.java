package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingUm;

public interface RankingUmService {
	
	/**
	 * Permite obtener un entity Rankin_UM por Id
	 * @param idRankingUm
	 * @return
	 */
	PgimRankingUm getByIdRankingUm(Long idRankingUm);

    /***
     * Permite listar los grupos de rankin de la unidad minera calificada.
     * 
     * @param idRankingRiesgo
     * @return
     */
    Page<PgimRankingUmAuxDTO> listarRankingUm(Long idRankingRiesgo, Pageable paginador);
    
    /**
     * Permite listar las unidades mineras calificadas en un ranking (RANKING_UM),
     * de acuerdo con los criterios filtro y de manera paginada.
     * 
     * @param filtro
     * @param flActivo
     * @param paginador
     * @return
     */
    Page<PgimRankingUmAuxDTO> listarRankingUmPorFiltroUM(PgimUnidadMineraAuxDTO filtro, String flActivo, Pageable paginador);
    

    List<PgimRankingUmAuxDTO> listarRankingUmByIdRanking(Long idRankingRiesgo);

    List<PgimRankingUmAuxDTO> listarResumenPorAnonimizacion(Long idRankingRiesgo);

    /**
     * Permite asegurar que la condici´ñon del registro del ranking sea el
     * pertinente.
     * 
     * @param idRankingUmGrupo
     * @param esRegistrar
     * @param idGrupoRiesgo
     * @param auditoriaDTO
     */
    void asegurarRegistrarEnRankingUmGrupo(Long idRankingUmGrupo, String esRegistrar, Long idGrupoRiesgo,
            AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite eliminar el Rankin_UM
     * 
     * @param pgimRankingUmActual
     * @param auditoriaDTO
     */
    void eliminarRankingUm(PgimRankingUm pgimRankingUmActual, AuditoriaDTO auditoriaDTO);


    /**
     * Permite desactivar/activar el Rankin_UM
     * 
     * @param pgimRankingUmActual
     * @param flActivo
     * @param auditoriaDTO
     */
    void conmutarFlActivoRankingUm(PgimRankingUm pgimRankingUmActual, String flActivo, AuditoriaDTO auditoriaDTO);
}
