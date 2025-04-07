package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimObligacionNormaAux;

/** 
 * 
 * 
 * @descripción: Lógica de negocio de la entidad Obligación Normativa Auxiliar
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 09/10/2020
 * @fecha_de_ultima_actualización: 09/10/2020
*/
@Repository
public interface ObligacionNormaAuxRepository extends JpaRepository<PgimObligacionNormaAux, Long> {
	
	/**
     * Permite obtener la lista de obligaciones normativas por criterio de matriz de supervisión, 
     * para un nuevo registro de HC
     * @param  idMatrizCriterio 
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTOResultado( "
			+ "aux.idObligacionNormaAux, aux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio, aux.idMatrizCriterio, "
			+ "aux.idNormaObligacion, aux.idNormaItem, aux.deObligacionNormativa, "
			+ "aux.deNormaItem, aux.coTipificacion, aux.noItemTipificacion, "
			+ "aux.nroNormaTipificacion "
			+ ") "
			+ "FROM PgimObligacionNormaAux aux WHERE aux.esRegistroOblgcnNrmaCrtrio = '1' AND aux.idMatrizCriterio = :idMatrizCriterio "
			 )
	List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasPorCriterioMatriz(@Param("idMatrizCriterio") Long idMatrizCriterio,Sort sort);
	
	
	/**
     * Permite obtener la lista de obligaciones normativas por criterio de matriz de supervisión, 
     * para un registro existente de HC
     * @param  idHechoConstatado 
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTOResultado( "
			+ "aux.idObligacionNormaAux, aux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio, aux.idMatrizCriterio, "
			+ "aux.idNormaObligacion, aux.idNormaItem, aux.deObligacionNormativa, "
			+ "aux.deNormaItem, onhc.idOblgcnNrmtvaHchoc, onhc.esAplica, "
			+ "aux.coTipificacion, aux.noItemTipificacion, aux.nroNormaTipificacion"
			+ ") "
			+ "FROM PgimObligacionNormaAux aux, PgimOblgcnNrmtvaHchoc onhc "			
			+ "WHERE aux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio=onhc.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio "
			+ "AND onhc.esRegistro = '1' AND onhc.pgimHechoConstatado.idHechoConstatado = :idHechoConstatado "
			 )
	List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasPorHC(@Param("idHechoConstatado") Long idHechoConstatado,Sort sort);

	/**
     * Permite obtener la lista de obligaciones normativas por criterio de matriz de supervisión, 
     * para un registro existente de HC
     * @param  idHechoConstatado 
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTOResultado( "
			+ "aux.idObligacionNormaAux, aux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio, aux.idMatrizCriterio, "
			+ "aux.idNormaObligacion, aux.idNormaItem, aux.deObligacionNormativa, "
			+ "aux.deNormaItem, onhc.idOblgcnNrmtvaHchoc, onhc.esAplica, "
			+ "aux.coTipificacion, aux.noItemTipificacion, aux.nroNormaTipificacion"
			+ ") "
			+ "FROM PgimObligacionNormaAux aux, PgimOblgcnNrmtvaHchoc onhc "			
			+ "WHERE aux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio=onhc.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio "
			+ "AND onhc.esRegistro = '1' AND onhc.esAplica = '1' AND onhc.pgimHechoConstatado.idHechoConstatado = :idHechoConstatado "
			 )
	List<PgimObligacionNormaAuxDTO> lObligacionesNormativasPorHCSelec(@Param("idHechoConstatado") Long idHechoConstatado);


	/**
     * Permite obtener la lista de obligaciones normativas por criterio de matriz de supervisión, 
     * para un nuevo registro de HC con todas las obligaciones normativas seleccionadas para el HC
     * @param  idMatrizCriterio 
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimObligacionNormaAuxDTOResultado( "
		+ "aux.idObligacionNormaAux, aux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio, aux.idMatrizCriterio, "
		+ "aux.idNormaObligacion, aux.idNormaItem, aux.deObligacionNormativa, aux.deNormaItem, true) "
		+ "FROM PgimObligacionNormaAux aux WHERE aux.esRegistroOblgcnNrmaCrtrio = '1' AND aux.idMatrizCriterio = :idMatrizCriterio "
		 )
	List<PgimObligacionNormaAuxDTO> listarObligacionesNormativasPorCriterioMatrizAllSelecion(@Param("idMatrizCriterio") Long idMatrizCriterio,Sort sort);


}
