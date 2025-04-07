package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemSolBaseDoc;

/**
 * Éste interface ItemSolBaseDocRepository
 * que aplica obtener y listar Item solicitud de documento.
 * 
 * @descripción: Logica de negocio de la entidad Item solicitud de documento.
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface ItemSolBaseDocRepository extends JpaRepository<PgimItemSolBaseDoc, Long>{
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTOResultado("
            + "isbd.idItemSolBaseDoc, isbd.pgimSolicitudBaseDoc.idSolicitudBaseDoc, isbd.deSolicitudObservacion, isbd.nuOrden "
            + ")"
            + "FROM PgimItemSolBaseDoc isbd "
            + "WHERE isbd.esRegistro = '1' "
            + "AND isbd.pgimSolicitudBaseDoc.idSolicitudBaseDoc = :idSolicitudBaseDoc "
            + "ORDER BY isbd.nuOrden ASC")
	List<PgimItemSolBaseDocDTO> listarItemsSolicitudBaseDocPorIdSolicitudBaseDoc(@Param("idSolicitudBaseDoc") Long idSolicitudBaseDoc);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemSolBaseDocDTOResultado("
			+ "isbd.idItemSolBaseDoc, isbd.pgimSolicitudBaseDoc.idSolicitudBaseDoc, isbd.deSolicitudObservacion, isbd.nuOrden "
            + ")"
            + "FROM PgimItemSolBaseDoc isbd "
            + "WHERE isbd.esRegistro = '1' "
            + "AND isbd.idItemSolBaseDoc = :idItemSolBaseDoc ")
	PgimItemSolBaseDocDTO obtenerItemSolBaseDocPorId(@Param("idItemSolBaseDoc") Long idItemSolBaseDoc);
}
