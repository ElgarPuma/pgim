package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAntecedenteSuperv;

/**
 * Interface AntecedenteSupervRepository 
 * 
 * @descripción: Logica de negocio para la gestion de antecedentes de la supervisión.
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creaci�n: 02/09/2020
 * @fecha_de_ultima_actualizaci�n: 02/09/2020
 */
@Repository
	public interface AntecedenteSupervRepository extends JpaRepository<PgimAntecedenteSuperv, Long>{ 

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAuxDTOResultado( "
    		+ "ante.pgimDocumento.idDocumento, ante.fecha, ante.codigo, ante.descripcion, ante.idInstanciaProceso, ante.coTablaInstancia, ante.nuExpedienteSiged, ante.tipoAntecedente.noValorParametro, ante.tipoAntecedente.idValorParametro, ante.pgimEspecialidad.idEspecialidad ) " 
            + "FROM PgimSelectAntecedenteAux ante "
            + "WHERE ante.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
            + "AND (ante.pgimEspecialidad.idEspecialidad IS NULL OR ante.pgimEspecialidad.idEspecialidad = :idEspecialidad ) "
            + "AND ante.pgimDocumento.idDocumento not in (select a.pgimDocumento.idDocumento from PgimAntecedenteAux a where a.pgimSupervision.idSupervision = :idSupervision )"
            )
    Page<PgimSelectAntecedenteAuxDTO> listarAntecedenteSeleccion( @Param("idUnidadMinera") Long idUnidadMinera, 
    		@Param("idEspecialidad") Long idEspecialidad, @Param("idSupervision") Long idSupervision, Pageable paginador );

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAntecedenteSupervDTOResultado( "
    		+ "ante.idAntecedenteSuperv, ante.pgimSupervision.idSupervision, ante.pgimDocumento.idDocumento, ante.tipoAntecedente.idValorParametro, ante.tipoSupervision.idValorParametro, ante.nuExpedienteSiged, ante.feInicioSupervisionReal, ante.feFinSupervisionReal, ante.deAspectosRevisados ) " 
            + "FROM PgimAntecedenteSuperv ante "
            + "WHERE ante.idAntecedenteSuperv = :idAntecedenteSuperv "
            )
	PgimAntecedenteSupervDTO obtenerAntecedenteSupervDTOPorId(@Param("idAntecedenteSuperv") Long idAntecedenteSuperv);
	
    @Query("SELECT subCat.tipoAntecedente.idValorParametro "
    		+ "FROM PgimSubcatDocTipAnte subCat "
    		+ "WHERE subCat.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
    		)
    Long tipoAntecedente(@Param("idSubcatDocumento") Long idSubcatDocumento);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTOResultado( "
    		+ "ante.idAntecedenteSupervAux, ante.pgimAntecedenteSuperv.idAntecedenteSuperv, ante.pgimSupervision.idSupervision, "
            + "ante.pgimDocumento.idDocumento, ante.deAspectosRevisados, ante.tipoAntecedente.idValorParametro, "
    		+ "ante.tipoAntecedente.noValorParametro, ante.tipoOrigin.noValorParametro, ante.tipoOrigin.idValorParametro, "
            + "ante.pgimCategoriaDoc.idCategoriaDocumento, ante.noCategoriaDocumento, ante.noSubcatDocumento, "
            + "ante.pgimSubcategoriaDoc.idSubcatDocumento, ante.pgimDocumento.coDocumentoSiged, ante.deAsuntoDocumento, "
            + "ante.pgimInstanciaProces.idInstanciaProceso, ante.nuExpedienteSiged, ante.cotablainstancia, "
			+ "ante.fecha, ante.codigo, ante.descripcion, ante.coDocumentoSigedPCopia, ante.tipoSupervision "
    		+ ") " 
            + "FROM PgimAntecedenteAux ante "
            + "WHERE ante.pgimSupervision.idSupervision = :idSupervision "
            + "ORDER BY ante.pgimAntecedenteSuperv.idAntecedenteSuperv ASC "
            )
    Page<PgimAntecedenteAuxDTO> listarAntecedente(@Param("idSupervision") Long idSupervision, 
    		Pageable paginador);
	
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTOResultado( "   		
            + "ante.nuExpedienteSiged, ante.fecha, ante.tipoSupervision, "
    		+ "ante.deAspectosRevisados "
    		+ ") " 
            + "FROM PgimAntecedenteAux ante "
            + "WHERE ante.pgimSupervision.idSupervision = :idSupervision "
            + "AND ante.tipoAntecedente.coClaveTexto = :TIANT_INFRME_FSCLZCION "
            + "ORDER BY ante.pgimAntecedenteSuperv.idAntecedenteSuperv ASC "
            )
    List<PgimAntecedenteAuxDTO> obtenerAntecedenteInformeSuperv(@Param("idSupervision") Long idSupervision, @Param("TIANT_INFRME_FSCLZCION") String TIANT_INFRME_FSCLZCION);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTOResultado( "
    		+ "ante.tipoAntecedente.noValorParametro, ante.deAspectosRevisados, ante.pgimDocumento.coDocumentoSiged "
    		+ ") " 
            + "FROM PgimAntecedenteAux ante "
            + "WHERE ante.pgimSupervision.idSupervision = :idSupervision "
            + "AND ante.tipoAntecedente.coClaveTexto <> :TIANT_INFRME_FSCLZCION "
            + "ORDER BY ante.pgimAntecedenteSuperv.idAntecedenteSuperv ASC "
            )
    List<PgimAntecedenteAuxDTO> obtenerAntecedenteOtrosDocs(@Param("idSupervision") Long idSupervision, @Param("TIANT_INFRME_FSCLZCION") String TIANT_INFRME_FSCLZCION); 
    
    @Query("SELECT count(ante.idAntecedenteSupervAux) " 
            + "FROM PgimAntecedenteAux ante "
            + "WHERE ante.pgimSupervision.idSupervision = :idSupervision "
            + "AND ante.deAspectosRevisados IS NULL"
            )
    Integer validarRevisionAntecedente(@Param("idSupervision") Long idSupervision);
    
    @Query("SELECT count(ante.idAntecedenteSupervAux) " 
            + "FROM PgimAntecedenteAux ante "
            + "WHERE ante.pgimSupervision.idSupervision = :idSupervision "
            )
    Integer cantidadAntecedentes(@Param("idSupervision") Long idSupervision);
    
    
}
