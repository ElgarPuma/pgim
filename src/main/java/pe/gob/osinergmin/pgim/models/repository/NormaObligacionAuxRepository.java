package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaObligacionAux;

/**
 * @descripción: Lógica de negocio de la entidad norma obligación
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface NormaObligacionAuxRepository extends JpaRepository<PgimNormaObligacionAux, Long>{
	
	/**
	 * Permite listar las obligaciones de un item de tipificacion 
	 * 
	 * @param idItemTipificacion
	 * 
	 * @param paginador
	 * @return
	 */
	 
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionAuxDTOResultado(pnormaobligacion.pgimNormaObligacion.idNormaObligacion, pnormaobligacion.noCortoNorma, pnormaobligacion.coItem, pnormaobligacion.divisionItem.idValorParametro, pnormaobligacion.deContenidoT , pnormaobligacion.flVigenteItem , pnormaobligacion.ubicacionItem, pnormaobligacion.deNormaObligacionT, pnormaobligacion.esVigenteNormaObligacion, pnormaobligacion.coTipificacion, pnormaobligacion.esVigenteTipificacion, pnormaobligacion.coNormaTipificacion, pnormaobligacion.noItemTipificacion, pnormaobligacion.idItemTipificacion, pnormaobligacion.noTipoNorma) "
    		+ "FROM PgimNormaObligacionAux pnormaobligacion "
    		+"where pnormaobligacion.idItemTipificacion = :idItemTipificacion")
    Page<PgimNormaObligacionAuxDTO> listar(
    		@Param("idItemTipificacion") Long idItemTipificacion,
            Pageable paginador);


	
	/**
	 * Obetener un normaObligacion filtrado por su identificador
	 * @param idNormaObligacion
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionAuxDTOResultado(pnormaobligacion.pgimNormaObligacion.idNormaObligacion, pnormaobligacion.noCortoNorma, pnormaobligacion.coItem, pnormaobligacion.divisionItem.idValorParametro, pnormaobligacion.deContenidoT , pnormaobligacion.flVigenteItem , pnormaobligacion.ubicacionItem, pnormaobligacion.deNormaObligacionT, pnormaobligacion.esVigenteNormaObligacion, pnormaobligacion.coTipificacion, pnormaobligacion.esVigenteTipificacion, pnormaobligacion.coNormaTipificacion, pnormaobligacion.noItemTipificacion, pnormaobligacion.idItemTipificacion, pnormaobligacion.noTipoNorma"
			+ ",pnormaobligacion.divisionItem.deValorParametro) "
    		+ "FROM PgimNormaObligacionAux pnormaobligacion "
    		+"where pnormaobligacion.idNormaObligacionAux = :idNormaObligacion")
    PgimNormaObligacionAuxDTO obtenerNormaObligacionAuxPorId(
    		@Param("idNormaObligacion") Long idNormaObligacion);
	
}
