package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCategoriaDoc;

/**
 * Ésta interface CategoriaDocRepository incluye los metodos 
 * listar y obtener las categorias del documento Pgim.
 * 
 * @descripción: Logica de negocio de la entidad Categoria doc
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 25/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface CategoriaDocRepository  extends JpaRepository<PgimCategoriaDoc, Long> {
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "c.idCategoriaDocumento,c.coCategoriaDocumento ||' - ' ||c.noCategoriaDocumento,c.esRegistro) "
			+ "FROM PgimCategoriaDoc c "
			+ "WHERE c.esRegistro = '1' ORDER BY c.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaDoc();
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "c.idCategoriaDocumento,c.coCategoriaDocumento ||' - ' ||c.noCategoriaDocumento,c.esRegistro) "
			+ "FROM PgimCategoriaDoc c "
			+ "WHERE c.esRegistro = '1' "
			+ "AND (:idProceso IS NULL OR c.idCategoriaDocumento in "
			+ "( "
			+ "SELECT distinct(pc.idCategoriaDocumento) FROM PgimCategoriaDoc pc "
			+ "INNER JOIN PgimSubcategoriaDoc psc ON pc.idCategoriaDocumento = psc.pgimCategoriaDoc.idCategoriaDocumento AND psc.esRegistro = '1' "
			+ "INNER JOIN PgimSubcatFasepro psf ON psc.idSubcatDocumento = psf.pgimSubcategoriaDoc.idSubcatDocumento AND psf.esRegistro = '1' "
			+ "INNER JOIN PgimFaseProceso pfp ON psf.pgimFaseProceso.idFaseProceso = pfp.idFaseProceso AND pfp.esRegistro = '1' "
			+ "INNER JOIN PgimProceso pp ON pfp.pgimProceso.idProceso = pp.idProceso AND pp.esRegistro = '1' "
			+ "WHERE pp.idProceso = :idProceso "
			+ "AND pc.esRegistro = '1' "
			+ ") ) "
			+ "ORDER BY c.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaDocByIdProceso(@Param("idProceso") Long idProceso);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "c.idCategoriaDocumento,c.coCategoriaDocumento ||' - ' ||c.noCategoriaDocumento,c.esRegistro) "
			+ "FROM PgimCategoriaDoc c "
			+ "WHERE c.esRegistro = '1' "
			+ "AND (:idProceso IS NULL OR c.idCategoriaDocumento in "
			+ "( "
			+ "SELECT distinct(pc.idCategoriaDocumento) FROM PgimCategoriaDoc pc "
			+ "INNER JOIN PgimSubcategoriaDoc psc ON pc.idCategoriaDocumento = psc.pgimCategoriaDoc.idCategoriaDocumento AND psc.esRegistro = '1' "
			+ "INNER JOIN PgimSubcatFasepro psf ON psc.idSubcatDocumento = psf.pgimSubcategoriaDoc.idSubcatDocumento AND psf.esRegistro = '1' "
			+ "INNER JOIN PgimFaseProceso pfp ON psf.pgimFaseProceso.idFaseProceso = pfp.idFaseProceso AND pfp.esRegistro = '1' "
			+ "INNER JOIN PgimProceso pp ON pfp.pgimProceso.idProceso = pp.idProceso AND pp.esRegistro = '1' "
			+ "WHERE pp.idProceso = :idProceso "
			+ "AND pc.esRegistro = '1' AND pfp.idFaseProceso = :idFase"
			+ ") ) "
			+ "ORDER BY c.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaDocByIdProcesoIdFase(@Param("idProceso") Long idProceso, @Param("idFase") Long idFase);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "cado.idCategoriaDocumento, cado.coCategoriaDocumento ||' - ' || cado.noCategoriaDocumento, cado.esRegistro) "
			+ "FROM PgimCategoriaDoc cado "
			+ "WHERE cado.esRegistro = '1' "
			+ "AND EXISTS "
			+ "( "
			+ "SELECT 1 "
			+ "FROM PgimRelacionSubcat resc "
			+ "		INNER JOIN resc.pgimSubcategoriaDoc sudo "
			+ "		INNER JOIN resc.pgimRelacionPaso repa "
			+ "WHERE repa.idRelacionPaso = :idRelacionPaso "
			+ "AND sudo.pgimCategoriaDoc = cado "
			+ "AND resc.esRegistro = '1' "
			+ "AND repa.esRegistro = '1' "
			+ "AND sudo.esRegistro = '1' "
			+ ") "
			+ "ORDER BY cado.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaDocByIdRelacionPaso(@Param("idRelacionPaso") Long idRelacionPaso);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "c.idCategoriaDocumento,c.coCategoriaDocumento, c.noCategoriaDocumento, "
			+ "psc.idSubcatDocumento, psc.coSubcatDocumento, psc.noSubcatDocumento, "
			+ "sp.idSubcategoriaPlanti, sp.noPlantilla) "
			+ "FROM PgimCategoriaDoc c "
			+ "INNER JOIN PgimSubcategoriaDoc psc ON c.idCategoriaDocumento = psc.pgimCategoriaDoc.idCategoriaDocumento AND psc.esRegistro = '1' "
			+ "INNER JOIN PgimSubcategoriaPlanti sp ON psc.idSubcatDocumento = sp.pgimSubcategoriaDoc.idSubcatDocumento AND sp.esRegistro = '1' "
			+ "INNER JOIN PgimSubcatFasepro psf ON psc.idSubcatDocumento = psf.pgimSubcategoriaDoc.idSubcatDocumento AND psf.esRegistro = '1' "
			+ "INNER JOIN PgimFaseProceso pfp ON psf.pgimFaseProceso.idFaseProceso = pfp.idFaseProceso AND pfp.esRegistro = '1' "
			+ "INNER JOIN PgimProceso pp ON pfp.pgimProceso.idProceso = pp.idProceso AND pp.esRegistro = '1' "
			+ "WHERE pp.idProceso = :idProceso "
			+ "AND c.esRegistro = '1' AND pfp.idFaseProceso = :idFase "
			+ "ORDER BY c.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaYSubCategoriaByIdProcesoIdFase(@Param("idProceso") Long idProceso, @Param("idFase") Long idFase);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "c.idCategoriaDocumento,c.coCategoriaDocumento, c.noCategoriaDocumento, "
			+ "psc.idSubcatDocumento, psc.coSubcatDocumento, psc.noSubcatDocumento, "
			+ "sp.idSubcategoriaPlanti, sp.noPlantilla) "
			+ "FROM PgimCategoriaDoc c "
			+ "INNER JOIN PgimSubcategoriaDoc psc ON c.idCategoriaDocumento = psc.pgimCategoriaDoc.idCategoriaDocumento AND psc.esRegistro = '1' "
			+ "INNER JOIN PgimSubcategoriaPlanti sp ON psc.idSubcatDocumento = sp.pgimSubcategoriaDoc.idSubcatDocumento AND sp.esRegistro = '1' "
			+ "INNER JOIN PgimSubcatFasepro psf ON psc.idSubcatDocumento = psf.pgimSubcategoriaDoc.idSubcatDocumento AND psf.esRegistro = '1' "
			+ "INNER JOIN PgimFaseProceso pfp ON psf.pgimFaseProceso.idFaseProceso = pfp.idFaseProceso AND pfp.esRegistro = '1' "
			+ "INNER JOIN PgimProceso pp ON pfp.pgimProceso.idProceso = pp.idProceso AND pp.esRegistro = '1' "
			+ "WHERE pp.idProceso = :idProceso "
			+ "AND c.esRegistro = '1' "
			+ "ORDER BY c.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaYSubCategoriaByIdProceso(@Param("idProceso") Long idProceso);
	
	/**
	 * Lista las categorías correspondientes a bibliografía
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTOResultado( "
			+ "c.idCategoriaDocumento, c.coCategoriaDocumento ||' - ' ||c.noCategoriaDocumento, c.esRegistro) "
			+ "FROM PgimCategoriaDoc c "
			+ "WHERE 1 = 1 "
			+ "AND c.esRegistro = '1' "
			+ "AND EXISTS ( "
			+ "	SELECT 1 "
			+ "	FROM PgimSubcategoriaDoc sc "
			+ "	WHERE 1 = 1 "
            + "	AND sc.esRegistro = '1' "
            + "	AND sc.flBibiografia = '1' "
            + "	AND sc.pgimCategoriaDoc.idCategoriaDocumento = c.idCategoriaDocumento "
			+ ")"
			+ "ORDER BY c.coCategoriaDocumento ")
	public List<PgimCategoriaDocDTO> listarCategoriaDocBibliografia();
}
