package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimArchivoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimArchivo;

import java.util.List;

/**
 * Ésta interface ArchivoRepository incluye el metodo 
 * obtenerCorrelativoCodNombre (para obtener el correlativo del codigo nombre del archivo).
 * 
 * @descripción: Lógica de negocio de la entidad Archivo
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/07/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface ArchivoRepository  extends JpaRepository<PgimArchivo, Long> {

	@Query(value = "SELECT PGIM.PGIM_SEQ_GEN_ARCHIVO.NEXTVAL FROM DUAL", nativeQuery = true)
	Long obtenerCorrelativoCodNombre();

	@Query("SELECT DISTINCT new pe.gob.osinergmin.pgim.dtos.PgimArchivoDTOResultado("
			+ " arc.noNuevoArchivo "
			+ " ) "
			+ "FROM PgimArchivo arc WHERE arc.pgimDocumento.esRegistro = '1' "
			// + "AND (:noOriginalArchivo IS NULL OR arc.noNuevoArchivo = :noOriginalArchivo) " 
			+ "AND arc.pgimDocumento.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso " 
			+ " ")
	List<PgimArchivoDTO> obtenerArchivoPorIntanciaPro(@Param("idInstanciaProceso") Long idInstanciaProceso);
}
