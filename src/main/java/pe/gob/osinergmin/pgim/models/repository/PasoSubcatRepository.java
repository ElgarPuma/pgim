package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPasoSubcatDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPasoSubcat;

/**
 * Tiene como nombre Paso subcategoria.
 * 
 * @descripción: Logica de negocio de la entidad Paso subcategoria.
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 10/02/2023
 */
public interface PasoSubcatRepository extends JpaRepository<PgimPasoSubcat, Long> {
	
	/**
     * Permite obtener la configuración de un paso-subcategoría  
     * por idSubcategoria y idPasoProceso
     * 
     * @param idSubcatDocumento
     * @param idPasoProceso
     * @return
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoSubcatDTOResultado( ps.idPasoSubcat, "
			+ "ps.pgimPasoProceso.idPasoProceso, ps.pgimSubcategoriaDoc.idSubcatDocumento, ps.caDocsAdjuntar, "
			+ "ps.flValidaSoloNuevos, ps.flModificarEnviado, ps.flAdjuntadoManual, ps.flDefinirDestinatario, ps.flAsociarDocNotificado "
			+ ") "
			+ "FROM PgimPasoSubcat ps "
			+ "WHERE "
			+ "ps.esRegistro = '1' "
			+ "AND ps.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
			+ "AND ps.pgimPasoProceso.idPasoProceso = :idPasoProceso "
			)
	PgimPasoSubcatDTO obtenerConfigPasoSubcat(
			@Param("idSubcatDocumento") Long idSubcatDocumento, 
			@Param("idPasoProceso") Long idPasoProceso
	);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasoSubcatDTOResultado( ps.idPasoSubcat, "
			+ "ps.pgimPasoProceso.idPasoProceso, ps.pgimSubcategoriaDoc.idSubcatDocumento, ps.caDocsAdjuntar, "
			+ "ps.flValidaSoloNuevos, ps.flModificarEnviado, ps.flAdjuntadoManual, ps.flDefinirDestinatario, ps.flAsociarDocNotificado "
			+ ") "
			+ "FROM PgimPasoSubcat ps "
			+ "WHERE "
			+ "ps.esRegistro = '1' "
			+ "AND ps.flAsociarDocNotificado = '1' "
			)
	List<PgimPasoSubcatDTO> listarConfigPasoSubcat();
}
