package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSolicitudDoc;

import java.util.List;

/**
 * Tiene como nombre SolicitudDocRepository.
 * 
 * @descripción: Logica de negocio de la entidad Solicitud de documentos
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface SolicitudDocRepository extends JpaRepository<PgimSolicitudDoc, Long>  {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTOResultado("
            + "psd.idSolicitudDoc, psd.pgimSupervision.idSupervision, psd.feSolicitudDocumentacion, psd.deSolicitudObservacion, "
            + "psd.feRecepcionDocumentacion, psd.deRecepcionObservacion "
            + ")"
            + "FROM PgimSolicitudDoc psd "
            + "WHERE psd.esRegistro = '1' "
            + "AND psd.idSolicitudDoc = :idSolicitudDoc ")
    PgimSolicitudDocDTO obtenerSolicitudDocPorId(@Param("idSolicitudDoc") Long idSolicitudDoc);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTOResultado("
            + "psd.idSolicitudDoc, ps.idSupervision, ts.noValorParametro, " 
            + "psd.feSolicitudDocumentacion, psd.deSolicitudObservacion, "
            + "psd.feRecepcionDocumentacion, psd.deRecepcionObservacion, "
            + "ppe.noEspecialidad "
            + ") "
            + "FROM PgimSolicitudDoc psd "
            + "INNER JOIN psd.pgimSupervision ps "
            + "INNER JOIN ps.pgimPrgrmSupervision pps "
            + "INNER JOIN pps.pgimEspecialidad ppe "
            + "INNER JOIN ps.pgimSubtipoSupervision pss "
            + "INNER JOIN pss.tipoSupervision ts "
            + "WHERE psd.esRegistro = '1' "
            + "AND ps.idSupervision = :idSupervision " 
            + "ORDER BY psd.feSolicitudDocumentacion DESC ")
    List<PgimSolicitudDocDTO> listarSolicitudDocRequerido(@Param("idSupervision") Long idSupervision);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTOResultado("
            + "sol.idSolicitudDoc, sol.pgimSupervision.idSupervision, sol.feSolicitudDocumentacion, sol.deSolicitudObservacion, "
            + "sol.feRecepcionDocumentacion, sol.deRecepcionObservacion, "
            + "itemSol.idItemSolicitudDoc, itemSol.feSolicitudDocumentacion, itemSol.deSolicitudObservacion, itemSol.feRecepcionDocumentacion, "
            + "itemSol.deRecepcionObservacion, itemSol.esRecibido "
            + ")"
            + "FROM PgimSolicitudDoc sol "
            + "INNER JOIN PgimItemSolicitudDoc itemSol ON sol = itemSol.pgimSolicitudDoc "
            + "WHERE sol.esRegistro = '1' "
            + "and itemSol.esRegistro = '1' "
            + "AND sol.idSolicitudDoc = :idSolicitudDoc ")
    PgimSolicitudDocDTO obtenerSolicitudAndItemSolicitudPorId(@Param("idSolicitudDoc") Long idSolicitudDoc);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTOResultado("
			+ "psd.idSolicitudDoc, psd.pgimSupervision.idSupervision, psd.feSolicitudDocumentacion, psd.deSolicitudObservacion, "
            + "psd.feRecepcionDocumentacion, psd.deRecepcionObservacion "
            + ") "
            + "FROM PgimSolicitudDoc psd "
            + "WHERE psd.esRegistro = '1' "
            + "AND psd.pgimSupervision.idSupervision = :idSupervision ")
    List<PgimSolicitudDocDTO> validarSolicitudDocxIdSupervision(@Param("idSupervision") Long idSupervision);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSolicitudDocDTOResultado("
			+ "psd.idSolicitudDoc, psd.pgimSupervision.idSupervision, psd.feSolicitudDocumentacion, psd.deSolicitudObservacion, "
            + "psd.feRecepcionDocumentacion, psd.deRecepcionObservacion, psd.pgimSupervision.feInicioSupervisionReal, psd.pgimSupervision.feFinSupervisionReal "
            + ") "
            + "FROM PgimSolicitudDoc psd "
            + "WHERE psd.esRegistro = '1' "
            + "AND psd.pgimSupervision.idSupervision = :idSupervision ")
    PgimSolicitudDocDTO obtenerSolicitudDocxIdSupervision(@Param("idSupervision") Long idSupervision);
}
