package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudBaseDoc;

/**
 * Ésta interface SolicitudBaseDocRepository incluye el metodo listar solicitud base de documento y otros metodos.
 * 
 * @descripción: Logica de negocio de la entidad Solicitud base de documento 
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface SolicitudBaseDocRepository extends JpaRepository<PgimSolicitudBaseDoc, Long>{
	
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTOResultado("
    		+ "doc.idSolicitudBaseDoc, cfg.idConfiguracionBase, cfg.noCofiguracionBase, doc.noSolicitudBaseDoc, doc.deSolicitudBaseDoc, esp.noEspecialidad, esp.idEspecialidad ) "
            + "FROM PgimSolicitudBaseDoc doc "
            + "INNER JOIN doc.pgimConfiguracionBase cfg "
            + "INNER JOIN cfg.pgimEspecialidad esp "
            + "WHERE doc.esRegistro = '1' "
    		+ "AND doc.pgimConfiguracionBase.idConfiguracionBase = :idConfiguracionBase "
    		)
    PgimSolicitudBaseDocDTO obtenerSolicitudbaseDocByIdConfiguracionBase(@Param("idConfiguracionBase") Long idConfiguracionBase);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTOResultado("
    		+ "doc.idSolicitudBaseDoc, cfg.idConfiguracionBase, cfg.noCofiguracionBase, doc.noSolicitudBaseDoc, doc.deSolicitudBaseDoc, esp.noEspecialidad, esp.idEspecialidad ) "
            + "FROM PgimSolicitudBaseDoc doc "
            + "INNER JOIN doc.pgimConfiguracionBase cfg "
            + "INNER JOIN cfg.pgimEspecialidad esp "
            + "WHERE doc.esRegistro = '1' "
            + "AND (:descIdEspecialidad IS NULL OR esp.idEspecialidad = :descIdEspecialidad) " 
            + "AND (:textoBusqueda IS NULL OR ( "
            + " LOWER(doc.noSolicitudBaseDoc) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " OR LOWER(esp.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " OR LOWER(cfg.noCofiguracionBase) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "))"
    		)
    Page<PgimSolicitudBaseDocDTO> listarSolicitudBaseDocPage(
    		@Param("descIdEspecialidad") Long descIdEspecialidad,
    		@Param("textoBusqueda") String textoBusqueda,
    		Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudBaseDocDTOResultado("
    		+ "doc.idSolicitudBaseDoc, cfg.idConfiguracionBase, cfg.noCofiguracionBase, doc.noSolicitudBaseDoc, doc.deSolicitudBaseDoc, esp.noEspecialidad, esp.idEspecialidad ) "
            + "FROM PgimSolicitudBaseDoc doc "
            + "INNER JOIN doc.pgimConfiguracionBase cfg "
            + "INNER JOIN cfg.pgimEspecialidad esp "
            + "WHERE doc.esRegistro = '1' "
			+ "AND doc.idSolicitudBaseDoc = :idSolicitudBaseDoc"
    		)
    PgimSolicitudBaseDocDTO obtenerSolicitudBaseDocPorId(@Param("idSolicitudBaseDoc") Long idSolicitudBaseDoc);
    
    @Query("SELECT COUNT(sol.idSolicitudBaseDoc) "
			+ "FROM PgimSolicitudBaseDoc sol "
			+ "WHERE 1 = 1 "
			+ "AND (:idSolicitudBaseDoc IS NULL OR sol.idSolicitudBaseDoc != :idSolicitudBaseDoc) "
			+ "AND (TRIM(UPPER(sol.noSolicitudBaseDoc)) LIKE TRIM(UPPER(:noSolicitudBaseDoc))) "
			+ "AND sol.esRegistro = '1'")
	Integer validarTraslapePlantilla(@Param("idSolicitudBaseDoc") Long idSolicitudBaseDoc,
			@Param("noSolicitudBaseDoc") String noSolicitudBaseDoc);
}
