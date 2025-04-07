package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSubcatDocFirmaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcatDocFirma;

/**
 *  
 * @descripción: Logica de negocio de la entidad SubcatDocFirma
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 22/04/2021
 * 
 */
@Repository
public interface SubcatDocFirmaRepository extends JpaRepository<PgimSubcatDocFirma, Long> {

	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcatDocFirmaDTOResultado( "
            + "subc.idSubcatDocFirma, subc.pgimSubcategoriaDoc.idSubcatDocumento, subc.pgimPasoProceso.idPasoProceso, "
            + "subc.flFirmaMultiple, subc.flVistoBueno ) "
            + "FROM PgimSubcatDocFirma subc WHERE subc.esRegistro = '1' "
            + "AND subc.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
            //+ "AND subc.pgimPasoProceso.idPasoProceso = :idPasoProceso "
            + "AND subc.pgimPasoProceso.idPasoProceso in ( "
            + "SELECT relpas.pasoProcesoDestino.idPasoProceso FROM PgimInstanciaPaso inspas, PgimRelacionPaso relpas "
            + "WHERE inspas.esRegistro = '1' AND relpas.esRegistro = '1' AND inspas.pgimRelacionPaso = relpas "
            + "AND inspas.idInstanciaPaso = :idInstanciaPasoActual ) "
            )
    List<PgimSubcatDocFirmaDTO> listarPgimSubcatDocFirmaBySubcatAndPaso(@Param("idSubcatDocumento") Long idSubcatDocumento, @Param("idInstanciaPasoActual") Long idInstanciaPasoActual);

}
