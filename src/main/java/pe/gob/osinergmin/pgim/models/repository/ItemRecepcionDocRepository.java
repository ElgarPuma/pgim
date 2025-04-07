package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemRecepcionDoc;

/**
 * Éste interface ItemRecepcionDocRepository
 * que aplica obtener y listar Item recepcion documento.
 * 
 * @descripción: Logica de negocio de la entidad Item recepcion documento.
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */

public interface ItemRecepcionDocRepository extends JpaRepository<PgimItemRecepcionDoc, Long>  {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTOResultado("
            + "ird.idItemRecepcionDoc, ird.pgimItemSolicitudDoc.idItemSolicitudDoc, ird.pgimDocumento.idDocumento"
            + ")"
            + "FROM PgimItemRecepcionDoc ird "
            + "WHERE ird.esRegistro = '1' "
            + "AND ird.idItemRecepcionDoc = :idItemRecepcionDoc ")
	PgimItemRecepcionDocDTO obtenerItemRecepcionDocPorId(@Param("idItemRecepcionDoc") Long idItemRecepcionDoc);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemRecepcionDocDTOResultado("
            + "ird.idItemRecepcionDoc, ird.pgimItemSolicitudDoc.idItemSolicitudDoc, ird.pgimDocumento.idDocumento"
            + ")"
            + "FROM PgimItemRecepcionDoc ird "
            + "WHERE ird.esRegistro = '1' "
            + "AND ird.pgimItemSolicitudDoc.idItemSolicitudDoc = :idItemSolicitudDoc ")
	List<PgimItemRecepcionDocDTO> listarItemRecepcionDocxIdItemSolicitudDoc(@Param("idItemSolicitudDoc") Long idItemSolicitudDoc);
}
