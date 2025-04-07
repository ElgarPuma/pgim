package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;

import java.util.List;

/**
 * En ésta interface PgimComponenteMinRepository incluye los metodos que
 * permitrá listar sus componentes de las unidades mineras.
 * 
 * @descripción: Logica de negocio de la entidad Componente minero
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface PgimComponenteMinRepository extends JpaRepository<PgimComponenteMinero, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.noComponente, tico.noValorParametro, "
            + "comi.nuCapacidadPlanta, comi.coComponente, tico.idValorParametro, tico.deValorAlfanum, " 
            + "tico.nuValorNumerico, comi.componenteMineroPadre.idComponenteMinero, " 
            + "sub.noTipoSubcomponente, sub.coTipoSubcomponente " 
            + ") " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "LEFT OUTER JOIN comi.pgimTipoSubcomponente sub " 
            + "WHERE comi.esRegistro = '1' "
            + "AND comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
    // + "ORDER BY comi.nuCapacidadPlanta DESC"
    )
    List<PgimComponenteMineroDTO> listarComponenteMinero(@Param("idUnidadMinera") Long idUnidadMinera, Sort sort);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.pgimUnidadMinera.idUnidadMinera, sub.idTipoSubcomponente, sub.noTipoSubcomponente, comi.noComponente, tico.noValorParametro, "
            + "comi.nuCapacidadPlanta, comi.coComponente, tico.idValorParametro, tico.nuValorNumerico, comi.componenteMineroPadre.idComponenteMinero, comi.nvlAceptacion, " 
            + "comi.ipCreacion, comi.ipActualizacion " 
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "LEFT OUTER JOIN comi.pgimTipoSubcomponente sub " 
            + "WHERE comi.esRegistro = '1' "
            + "AND comi.idComponenteMinero = :idComponenteMinero "
            + " ")
    PgimComponenteMineroDTO obtenerComponenteMineroPorId(@Param("idComponenteMinero") Long idComponenteMinero);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.pgimUnidadMinera.idUnidadMinera, comi.componenteMineroPadre.idComponenteMinero, "
            + "(comi.coComponente || ' - ' || tico.noValorParametro || ' - ' || comi.noComponente) ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "WHERE comi.esRegistro = '1' "
            + "AND comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
            + "AND comi.idComponenteMinero <> :idComponenteMinero "
            + "ORDER BY comi.coComponente "
    )
    List<PgimComponenteMineroDTO> listarComponentePadrePorUM(@Param("idUnidadMinera") Long idUnidadMinera, @Param("idComponenteMinero") Long idComponenteMinero);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.pgimUnidadMinera.idUnidadMinera, comi.noComponente, tico.noValorParametro, "
            + "comi.nuCapacidadPlanta, comi.coComponente, tico.idValorParametro " 
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "WHERE comi.esRegistro = '1' "
            + "AND comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
            + " ")
    List<PgimComponenteMineroDTO> obtenerComponenteMineroPorIdUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.pgimUnidadMinera.idUnidadMinera, comi.noComponente, tico.noValorParametro, "
            + "comi.nuCapacidadPlanta, comi.coComponente, tico.idValorParametro " 
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "WHERE comi.esRegistro = '1' "

        //     + "AND NOT EXISTS (SELECT 1 "
        //     + "FROM PgimCmineroSprvsion cmins "
        //     + "INNER JOIN cmins.pgimComponenteMinero cmin "
        //     + "WHERE cmin.idComponenteMinero = comi.idComponenteMinero "
        //     + "AND cmins.esRegistro = '1' "
        //     + "AND cmin.esRegistro = '1' "
        //     + ") "
            + "AND comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "

            + " ")
    List<PgimComponenteMineroDTO> listarComponenteMineroNoAsociadoHc(@Param("idUnidadMinera") Long idUnidadMinera);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero ) " 
            + "FROM PgimComponenteMinero comi " 
            + "WHERE comi.esRegistro = '1' "
            + "AND comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
            + " ")
    List<PgimComponenteMineroDTO> listarComponenteMineroPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.pgimUnidadMinera.idUnidadMinera, comi.noComponente, tico.noValorParametro, "
            + "comi.nuCapacidadPlanta, comi.coComponente, tico.idValorParametro " 
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "WHERE comi.esRegistro = '1' "
            + "AND comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
            + " ")
    List<PgimComponenteMineroDTO> listarComponenteMinero();
    
    /**
     * Permite obtener la lista preparada de unidades mineras usado en reporte correspondiente de manera paginada
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
            + "comi.idComponenteMinero, comi.noComponente, comi.coComponente, "
            + "tico.idValorParametro, tico.noValorParametro, "
            + "um.idUnidadMinera, um.coUnidadMinera,um.noUnidadMinera, um.coDocumentoIdentidad, um.noRazonSocial " 
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "INNER JOIN PgimUnidadMineraAux um on comi.pgimUnidadMinera.idUnidadMinera = um.idUnidadMinera " 
            + "WHERE comi.esRegistro = '1' "
            + "AND (:idTipoComponenteMinero IS NULL OR tico.idValorParametro = :idTipoComponenteMinero) "
//            + "AND (:agenteFiscalizado IS NULL OR LOWER(um.noRazonSocial) = LOWER(:agenteFiscalizado)) "
            + "AND (:agenteFiscalizado IS NULL OR LOWER (um.noRazonSocial) LIKE LOWER(CONCAT('%', :agenteFiscalizado, '%'))) "
            )
    Page<PgimComponenteMineroDTO> listarReporteComponenteUMPaginado(
    		@Param("idTipoComponenteMinero") Long idTipoComponenteMinero, 
    		@Param("agenteFiscalizado") String agenteFiscalizado, 
    		Pageable paginador);

    /**
     * Permite obtener la lista preparada de unidades mineras usado en reporte correspondiente
     * @param sort
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
    		+ "comi.idComponenteMinero, comi.noComponente, comi.coComponente, "
    		+ "tico.idValorParametro, tico.noValorParametro, "
    		+ "um.idUnidadMinera, um.coUnidadMinera,um.noUnidadMinera, um.coDocumentoIdentidad, um.noRazonSocial " 
    		+ " ) " 
    		+ "FROM PgimComponenteMinero comi "
    		+ "INNER JOIN comi.tipoComponenteMinero tico " 
    		+ "INNER JOIN PgimUnidadMineraAux um on comi.pgimUnidadMinera.idUnidadMinera = um.idUnidadMinera " 
    		+ "WHERE comi.esRegistro = '1' "
            + "AND (:idTipoComponenteMinero IS NULL OR tico.idValorParametro = :idTipoComponenteMinero) "
            + "AND (:agenteFiscalizado IS NULL OR LOWER (um.noRazonSocial) LIKE LOWER(CONCAT('%', :agenteFiscalizado, '%'))) "
            )
    List<PgimComponenteMineroDTO> listarReporteComponenteUM(
    		@Param("idTipoComponenteMinero") Long idTipoComponenteMinero, 
    		@Param("agenteFiscalizado") String agenteFiscalizado,
    		Sort sort);
    
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
	  + "comi.idComponenteMinero, comi.noComponente, tico.noValorParametro, "
	  + "comi.coComponente, tico.idValorParametro, comi.componenteMineroPadre.idComponenteMinero, comi.pgimUnidadMinera.idUnidadMinera, um.coUnidadMinera, "
	  + "um.noUnidadMinera ) " 
	  + "FROM PgimComponenteMinero comi "
	  + "INNER JOIN comi.tipoComponenteMinero tico " 
	  + "INNER JOIN comi.pgimUnidadMinera um " 
	  + "WHERE comi.esRegistro = '1' "
	// + "ORDER BY comi.nuCapacidadPlanta DESC"
	)
	List<PgimComponenteMineroDTO> listarComponentes();
	
	/**
	 * Permite listar los componente mineros según la coincidencia por palabra clave 
	 * y de una unidad minera dada 
	 * 
	 * @param palabra
	 * @param idUnidadMinera
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTOResultado( "
			+ "comi.idComponenteMinero, comi.noComponente, tico.noValorParametro, "
			+ "comi.coComponente, tico.idValorParametro, comi.componenteMineroPadre.idComponenteMinero, "
			+ "comi.pgimUnidadMinera.idUnidadMinera, comi.pgimUnidadMinera.coUnidadMinera, comi.pgimUnidadMinera.noUnidadMinera "			
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "WHERE 1 = 1 "
            + "AND comi.esRegistro = '1' "
            + "AND (:idUnidadMinera IS NULL OR (comi.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera)) "
            + "AND (:palabraClave IS NULL OR LOWER(comi.coComponente) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
            + "OR LOWER(comi.noComponente) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) "
            + "ORDER BY comi.noComponente"
            + " ")
    List<PgimComponenteMineroDTO> listarPorPalabraClaveYUm(
    		@Param("palabraClave") String palabra,
    		@Param("idUnidadMinera") Long idUnidadMinera);
}
