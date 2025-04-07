package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicador;

/**
 * @descripción: Logica de negocio de la entidad indicador.
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 28/02/2023
 * @fecha_de_ultima_actualización: 
 */
public interface IndicadorRepository extends JpaRepository<PgimIndicador, Long> {
	
	 @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTOResultado("
	            + "indi.idIndicador,indi.coIndicador, indi.noIndicador, indi.deIndicador, indi.tipoIndicador.idValorParametro, " 
	            + "indi.tipoIndicador.noValorParametro, indi.deFormula, tumd.noValorParametro, indi.caMesesAtras, " 
	            + "indi.deUrlRelativo ) " 
	            + "FROM PgimIndicador indi "
	            + "LEFT JOIN indi.tipoUnidadMedida tumd "
	            + "WHERE indi.esRegistro = '1' "	            
	            + "AND (:parametro IS NULL OR indi.tipoIndicador.coClaveTexto = :parametro)"
	            )
	 List<PgimIndicadorDTO> listarIndicadorBycoClavetexto(@Param("parametro") String parametro);
	 
	 
	 @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTOResultado("
	            + "indi.idIndicador,indi.coIndicador, indi.noIndicador, indi.deIndicador, indi.tipoIndicador.idValorParametro, " 
	            + "indi.tipoIndicador.noValorParametro, indi.deFormula, tumd.noValorParametro, indi.caMesesAtras, " 
	            + "indi.deUrlRelativo ) " 
	            + "FROM PgimIndicador indi "
	            + "LEFT JOIN indi.tipoUnidadMedida tumd "
	            + "WHERE indi.esRegistro = '1' "	            
	            + "AND indi.idIndicador = :idIndicador"
	            )
	 PgimIndicadorDTO obtenerIndicadorById(@Param("idIndicador") Long idIndicador);
	 

	@Query("SELECT max(indi.coIndicador) "
    		+ "FROM PgimIndicador indi "
    		+ "WHERE indi.tipoIndicador.coClaveTexto <> :coClaveTipoIndicadorFt "
        + "AND 1=1 "
				+ "ORDER BY indi.idIndicador desc")
	String obtenerUltimoCoCorrelativoIndicarFt(@Param("coClaveTipoIndicadorFt") String coClaveTipoIndicadorFt );
     
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTOResultado("
	            + "indi.idIndicador,indi.coIndicador, indi.noIndicador, indi.deIndicador, indi.tipoIndicador.idValorParametro, " 
	            + "indi.tipoIndicador.noValorParametro, indi.deFormula, indi.tipoUnidadMedida.noValorParametro, indi.caMesesAtras, " 
	            + "indi.deUrlRelativo ) " 
	            + "FROM PgimIndicador indi "
	            + "WHERE indi.esRegistro = '1' "
	            + "AND LOWER(indi.coClaveIndicador) = LOWER(:coClaveIndicador) "
	            + "AND (:idIndicador IS NULL OR indi.idIndicador <> :idIndicador) "
	            )
	List<PgimIndicadorDTO> validarCoClaveUnicaIndicadorFt(@Param("coClaveIndicador") String coClaveIndicador, 
			@Param("idIndicador") Long idIndicador);
     
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTOResultado("
            + "indi.idIndicador,indi.coIndicador, indi.noIndicador, indi.deIndicador, indi.tipoIndicador.idValorParametro, " 
            + "indi.tipoIndicador.noValorParametro, indi.deFormula, indi.coClaveIndicador, indi.esIndicador, "
            + "indi.tipoUnidadMedida.idValorParametro, indi.pgimProceso.idProceso, "
            + "indi.tipoAgrupadoPor.idValorParametro, tact.idValorParametro, "
            + "tcar.idValorParametro, indi.tipoAgrupadoPor.coClaveTexto, indi.tipoUnidadMedida.noValorParametro, "
            + "indi.relacionPasoDestino.idRelacionPaso, indi.relacionPasoOrigen.idRelacionPaso "
            + ") " 
            + "FROM PgimIndicador indi "
            + "LEFT JOIN indi.tipoCaracterFisc tcar "
            + "LEFT JOIN indi.tipoActorNegocio tact "
            + "WHERE indi.tipoIndicador.coClaveTexto <> :coClaveTipoIndicadorFt "
            + "AND indi.esRegistro = '1' "
            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(indi.coIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(indi.noIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(indi.deFormula) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(indi.deIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
            + "OR LOWER(indi.tipoUnidadMedida.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
            + "OR LOWER(indi.tipoIndicador.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))"
            + "))"
            )
	Page<PgimIndicadorDTO> listarIndicadoresFt(@Param("coClaveTipoIndicadorFt") String coClaveTipoIndicadorFt, 
			@Param("textoBusqueda") String textoBusqueda, 
			Pageable paginador);
	
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTOResultado("
            + "indi.idIndicador,indi.coIndicador, indi.noIndicador, indi.deIndicador, indi.tipoIndicador.idValorParametro, " 
            + "indi.tipoIndicador.noValorParametro, indi.deFormula, indi.coClaveIndicador, indi.esIndicador, "
            + "indi.tipoUnidadMedida.idValorParametro, indi.pgimProceso.idProceso, "
            + "indi.tipoAgrupadoPor.idValorParametro, tact.idValorParametro, "
            + "tcar.idValorParametro, indi.tipoAgrupadoPor.coClaveTexto, indi.tipoUnidadMedida.noValorParametro, "
            + "indi.relacionPasoDestino.idRelacionPaso, indi.relacionPasoOrigen.idRelacionPaso "
            + ") " 
            + "FROM PgimIndicador indi "
            + "LEFT JOIN indi.tipoCaracterFisc tcar "
            + "LEFT JOIN indi.tipoActorNegocio tact "
            + "WHERE indi.esRegistro = '1' "	            
            + "AND indi.idIndicador = :idIndicador"
            )
	 PgimIndicadorDTO obtenerIndicadorFtById(@Param("idIndicador") Long idIndicador);
	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTOResultado("
            + "indi.idIndicador,indi.coIndicador, indi.noIndicador, indi.deIndicador, indi.tipoIndicador.idValorParametro, " 
            + "indi.tipoIndicador.noValorParametro, indi.deFormula, indi.coClaveIndicador, indi.esIndicador, "
            + "indi.tipoUnidadMedida.idValorParametro, indi.pgimProceso.idProceso, "
            + "indi.tipoAgrupadoPor.idValorParametro, tact.idValorParametro, "
            + "tcar.idValorParametro, indi.tipoAgrupadoPor.coClaveTexto, indi.tipoUnidadMedida.noValorParametro, "

            + "indi.pgimProceso.noProceso, indi.tipoUnidadMedida.noValorParametro, "
            + "indi.tipoAgrupadoPor.noValorParametro, tcar.noValorParametro, tact.noValorParametro, "
            + "indi.relacionPasoOrigen.pasoProcesoOrigen.noPasoProceso || '  =>  ' || indi.relacionPasoOrigen.pasoProcesoDestino.noPasoProceso, "
            + "indi.relacionPasoDestino.pasoProcesoOrigen.noPasoProceso || ' => ' || indi.relacionPasoDestino.pasoProcesoDestino.noPasoProceso "

            + ") " 
            + "FROM PgimIndicador indi "
            + "LEFT JOIN indi.tipoCaracterFisc tcar "
            + "LEFT JOIN indi.tipoActorNegocio tact "
            + "WHERE indi.tipoIndicador.coClaveTexto <> :coClaveTipoIndicadorFt "
            + "AND indi.esRegistro = '1' "
						+ "AND (LOWER(indi.coIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER(indi.noIndicador) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) "
            + "ORDER BY indi.coIndicador "
            )
	List<PgimIndicadorDTO> listarIndicadoresByPalabra(@Param("coClaveTipoIndicadorFt") String coClaveTipoIndicadorFt, 
			@Param("textoBusqueda") String textoBusqueda);
}
