package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionFirmaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionFirma;

/** 
 * @descripción: Lógica de negocio de la entidad relación firma
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 26/04/2021
 */
@Repository
public interface RelacionFirmaRepository extends JpaRepository<PgimRelacionFirma, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionFirmaDTOResultado( "
		    + "refi.idRelacionFirma, refi.pgimSubcategoriaDoc.idSubcatDocumento, "
		    + "refi.pgimRelacionPaso.idRelacionPaso,refi.pgimSubcategoriaDoc.noSubcatDocumento, "
		    + "refi.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento "
		    + ")" 
		    + "FROM PgimRelacionFirma refi " 
		    + "WHERE refi.esRegistro = '1' "
		    + "AND refi.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
		    + "AND (:flObligatorio IS NULL OR refi.flObligatorio = :flObligatorio) "
		    )
	List<PgimRelacionFirmaDTO> listarRelacionFirmaPorIdRelacion(@Param("idRelacionPaso") Long idRelacionPaso
			, @Param("flObligatorio") String flObligatorio
			);

}
