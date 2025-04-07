package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimBiblioDocumentoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioDocumento;

/**
 * @descripción: Consultas de la entidad BiblioDocumento
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 15/12/2023
 */
@Repository
public interface BiblioDocumentoRepository  extends JpaRepository<PgimBiblioDocumento, Long>  {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioDocumentoDTOResultado( "
        + "bdoc.idBiblioDocumento, sc.idSubcatDocumento, bdoc.tipoMedioIngreso.idValorParametro, "
        + "pers.idPersona, bdoc.nuDocumento, bdoc.deAsuntoDocumento, "
        + "bdoc.deSumillaDocumento, bdoc.feEmisionDocumento, bdoc.nuExpedienteSiged, " 
        + "cat.coCategoriaDocumento || ': ' || cat.noCategoriaDocumento || ' / ' || sc.coSubcatDocumento || ': ' || sc.noSubcatDocumento, "
        + "pers.noRazonSocial || ' (' || pers.coDocumentoIdentidad || ')' "
		+ ") "
		+ "FROM PgimBiblioDocumento bdoc "
        + "INNER JOIN bdoc.personaEmisora pers "
        + "INNER JOIN bdoc.pgimSubcategoriaDoc sc "
        + "INNER JOIN sc.pgimCategoriaDoc cat "
        + "WHERE bdoc.idBiblioDocumento = :idBiblioDocumento "
        )
    PgimBiblioDocumentoDTO obtenerDocumentoBiblioPorId(@Param("idBiblioDocumento") Long idBiblioDocumento);
    
    
}
