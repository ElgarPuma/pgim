package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolicitudDoc;

/**
 * Éste interface ItemSolicitudDocRepository
 * que aplica obtener y listar Item solicitud documento.
 * 
 * @descripción: Logica de negocio de la entidad Item solicitud documento.
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface ItemSolicitudDocRepository extends JpaRepository<PgimItemSolicitudDoc, Long>  {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTOResultado("
            + "isd.idItemSolicitudDoc, isd.pgimSolicitudDoc.idSolicitudDoc, isd.feSolicitudDocumentacion, "
            + "isd.deSolicitudObservacion, isd.feRecepcionDocumentacion, isd.deRecepcionObservacion, isd.esRecibido "
            + ", isd.nuOrden "
            + ")"
            + "FROM PgimItemSolicitudDoc isd "
            + "WHERE isd.esRegistro = '1' "
            + "AND isd.idItemSolicitudDoc = :idItemSolicitudDoc ")
	PgimItemSolicitudDocDTO obtenerItemSolicitudDocPorId(@Param("idItemSolicitudDoc") Long idItemSolicitudDoc);
	
    // STORY: PGIM-6222: Drag & drop para ordenamiento de documentos solicitados en la fisc
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTOResultado("
            + "isd.idItemSolicitudDoc, isd.nuOrden "
            + ")"
            + "FROM PgimItemSolicitudDoc isd "
            + "WHERE isd.esRegistro = '1' "
            + "AND isd.idItemSolicitudDoc = :idItemSolicitudDoc "
            + "AND isd.pgimSolicitudDoc.idSolicitudDoc = :idSolicitudDoc "
            + "AND isd.pgimSolicitudDoc.pgimSupervision.idSupervision = :idSupervision "
            )
	PgimItemSolicitudDocDTO modificarOrden(@Param("idItemSolicitudDoc") Long idItemSolicitudDoc, @Param("idSolicitudDoc") Long idSolicitudDoc, @Param("idSupervision") Long idSupervision);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemSolicitudDocDTOResultado("
            + "itsol.idItemSolicitudDoc, itsol.pgimSolicitudDoc.idSolicitudDoc, itsol.feSolicitudDocumentacion, "
            + "itsol.deSolicitudObservacion, itsol.feRecepcionDocumentacion, itsol.deRecepcionObservacion, itsol.esRecibido, "
            + "espe.noEspecialidad, itsol.nuOrden, supe.idSupervision "
            + ")"
            + "FROM PgimItemSolicitudDoc itsol "
            + "INNER JOIN itsol.pgimSolicitudDoc sol "
            + "INNER JOIN sol.pgimSupervision supe "
            + "INNER JOIN supe.pgimPrgrmSupervision pgrsup "
            + "INNER JOIN pgrsup.pgimEspecialidad espe "
            + "WHERE itsol.esRegistro = '1' "
            + "AND itsol.pgimSolicitudDoc.idSolicitudDoc = :idSolicitudDoc ORDER BY itsol.nuOrden ")
	List<PgimItemSolicitudDocDTO> listarItemSolicitudDocPorIdSolicitudDoc(@Param("idSolicitudDoc") Long idSolicitudDoc);
}
