package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaObligacion;

/**
 * @descripción: Lógica de negocio de la entidad norma obligación
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface NormaObligacionRepository extends JpaRepository<PgimNormaObligacion, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNormaObligacionDTOResultado(pnormaobligacion.idNormaObligacion, pnormaobligacion.pgimNormaItem.idNormaItem, pnormaobligacion.pgimItemTipificacion.idItemTipificacion, pnormaobligacion.deNormaObligacionT, pnormaobligacion.esVigente) "
    		+ "FROM PgimNormaObligacion pnormaobligacion "
    		+"where pnormaobligacion.idNormaObligacion = :idNormaObligacion")
    PgimNormaObligacionDTO obtenerNormaObligacionPorId(
    		@Param("idNormaObligacion") Long idNormaObligacion);
	
}
