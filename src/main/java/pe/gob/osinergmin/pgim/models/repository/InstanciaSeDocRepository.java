package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimInstanciaSeDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaSeDoc;

/**
 * Ésta interface InstanciaSeDocRepository incluye los metodos de obtener los correlativos
 * de las subcategorías de documentos.
 * 
 * @descripción: Logica de negocio de la entidad InstanciaSeDoc
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 01/08/2022
 * @fecha_de_ultima_actualización: 01/08/2022
 *
 */
public interface InstanciaSeDocRepository extends JpaRepository<PgimInstanciaSeDoc, Long> {
	

	/**
	 * Permite obtener el último registro de correlativo para una instancia de proceso y
	 * subcategoria de documento determinadas 
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaSeDocDTOResultado(ised2.idInstanciaSeDoc, "
			+ "ised2.pgimInstanciaProces.idInstanciaProceso, ised2.pgimSubcategoriaDoc.idSubcatDocumento, ised2.seCorrelativo) "
			+ "FROM PgimInstanciaSeDoc ised2 "
			+ "WHERE ised2.idInstanciaSeDoc = ( "
			+ 	"SELECT MAX(ised.idInstanciaSeDoc) "
			+ 	"FROM PgimInstanciaSeDoc ised "
			+ 	"WHERE ised.esRegistro = '1' "
			+ 	"AND ised.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
			+ 	"AND ised.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
			+ ")"
			)
	PgimInstanciaSeDocDTO obtenerSeCorrelativoPorInstanciaYSubcat(
			@Param("idInstanciaProceso") Long idInstanciaProceso,
			@Param("idSubcatDocumento") Long idSubcatDocumento
			);
	
}
