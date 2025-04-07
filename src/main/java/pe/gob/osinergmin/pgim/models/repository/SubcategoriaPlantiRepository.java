package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaPlantiDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaPlanti;

/**
 * Tiene como nombre Subcategoria plantilla.
 * 
 * @descripción: Logica de negocio de la entidad Subcategoria plantilla. 
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface SubcategoriaPlantiRepository  extends JpaRepository<PgimSubcategoriaPlanti, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaPlantiDTOResultado( "
			+ "scp.idSubcategoriaPlanti, scp.pgimSubcategoriaDoc.idSubcatDocumento, scp.noPlantilla "
			+ ") "
			+ "FROM PgimSubcategoriaPlanti scp "
			+ "WHERE scp.esRegistro = '1' "
			+ "AND scp.idSubcategoriaPlanti = :idSubcategoriaPlanti ")
	public PgimSubcategoriaPlantiDTO obtenerSubcategoriaPlantiPorId(@Param("idSubcategoriaPlanti") Long idSubcategoriaPlanti);
}
