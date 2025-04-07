package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumentoRelacion;

/**
 * Ésta interface de CostoUnitarioRepository incluye
 * los metodos para obtener el documento relacion.
 * 
 * @descripción: Lógica de negocio de la entidad Documento relacion
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 04/10/2020
 * @fecha_de_ultima_actualización: 10/10/2020
 */
public interface DocumentoRelacionRepository extends JpaRepository<PgimDocumentoRelacion, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dr.idDocumentoRelacion, dr.documentoPadre.idDocumento, dr.pgimDocumento.idDocumento, dr.tipoRelacionDocumento.idValorParametro) "
			+ "FROM PgimDocumentoRelacion dr "
			+ "WHERE dr.esRegistro = '1' "
			+ "AND dr.idDocumentoRelacion = :idDocumentoRelacion ")
	PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorId(@Param("idDocumentoRelacion") Long idDocumentoRelacion);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dore.idDocumentoRelacion, dore.documentoPadre.idDocumento, dore.pgimDocumento.idDocumento, dore.tipoRelacionDocumento.idValorParametro) "
			+ "FROM PgimDocumentoRelacion dore "
			+ "WHERE dore.esRegistro = '1' "
			+ "AND dore.pgimDocumento.idDocumento = :idDocumento "
			+ "AND dore.tipoRelacionDocumento.idValorParametro = :tipoRelacionDocumento ")
	PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorIdDocumento(@Param("idDocumento") Long idDocumento,
			@Param("tipoRelacionDocumento") Long tipoRelacionDocumento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dr.idDocumentoRelacion, dr.documentoPadre.idDocumento, pd.idDocumento, dr.tipoRelacionDocumento.idValorParametro) "
			+ "FROM PgimDocumentoRelacion dr "
			+ "LEFT JOIN PgimDocumento pd ON dr.pgimDocumento.idDocumento = pd.idDocumento "
			+ "WHERE dr.esRegistro = '1' "
			+ "and pd.esRegistro = '1' "
			+ "AND dr.documentoPadre.idDocumento = :idDocumentoPadre "
			+ "AND dr.tipoRelacionDocumento.idValorParametro = :tipoRelacionDocumento ")
	PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorIdDocumentoPadre(
			@Param("idDocumentoPadre") Long idDocumentoPadre,
			@Param("tipoRelacionDocumento") Long tipoRelacionDocumento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dr.idDocumentoRelacion, dr.documentoPadre.idDocumento, dr.pgimDocumento.idDocumento, dr.tipoRelacionDocumento.idValorParametro) "
			+ "FROM PgimDocumentoRelacion dr "
			+ "WHERE dr.esRegistro = '1' "
			+ "AND dr.documentoPadre.idDocumento = :idDocumentoPadre "
			+ "AND dr.tipoRelacionDocumento.idValorParametro = :tipoRelacionDocumento ")
	List<PgimDocumentoRelacionDTO> obtenerListDocRelacionPorIdDocumentoPadre(
			@Param("idDocumentoPadre") Long idDocumentoPadre,
			@Param("tipoRelacionDocumento") Long tipoRelacionDocumento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dore.idDocumentoRelacion, dore.documentoPadre.idDocumento, dore.pgimDocumento.idDocumento, "
			+ " dore.tipoRelacionDocumento.idValorParametro "
			+ ") "
			+ "FROM PgimDocumentoRelacion dore "
			+ "WHERE dore.esRegistro = '1' "
			+ "AND dore.pgimDocumento.idDocumento = :idDocumentoHijo "
			+ "AND dore.tipoRelacionDocumento.coClaveTexto = :REDOC_OBSRVCION ")
	PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorIdDocumentoHijo(@Param("idDocumentoHijo") Long idDocumentoHijo,
			@Param("REDOC_OBSRVCION") String REDOC_OBSRVCION);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dore.idDocumentoRelacion, dore.documentoPadre.idDocumento, dore.pgimDocumento.idDocumento, dore.tipoRelacionDocumento.idValorParametro) "
			+ "FROM PgimDocumentoRelacion dore "
			+ "WHERE dore.esRegistro = '1' "
			+ "AND dore.documentoPadre.idDocumento = :idDocumentoPadre")
	List<PgimDocumentoRelacionDTO> obtenerListDocRelacionPorIdDocumentoPadre(
			@Param("idDocumentoPadre") Long idDocumentoPadre);			

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTOResultado("
			+ "dore.idDocumentoRelacion, dore.documentoPadre.idDocumento, dore.pgimDocumento.idDocumento, dore.tipoRelacionDocumento.idValorParametro) "
			+ "FROM PgimDocumentoRelacion dore "
			+ "WHERE dore.esRegistro = '1' "
			+ "AND dore.pgimDocumento.idDocumento = :idDocumentoHijo")
	List<PgimDocumentoRelacionDTO> obtenerListDocRelacionPorIdHijo(
			@Param("idDocumentoHijo") Long idDocumentoHijo);			

}
