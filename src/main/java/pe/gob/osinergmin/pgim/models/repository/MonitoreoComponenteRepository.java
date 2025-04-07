package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimMonitoreoComponenteDTOResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimMonitoreoComponente;

@Repository
public interface MonitoreoComponenteRepository  extends JpaRepository<PgimMonitoreoComponente, Long> {
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMonitoreoComponenteDTOResultado("
			+ "TO_CHAR(mc.feMonitoreo), "
			+ "mc.distancia, "
			+ "mc.pgimComponenteMinero.nvlAceptacion, "
			+ "mc.porcentajeAgua ) "
			+ "FROM PgimMonitoreoComponente mc  "
			+ "WHERE mc.esRegistro = '1' "
			+ "AND  mc.pgimComponenteMinero.idComponenteMinero = :codigoComponente "
			+ "ORDER BY mc.feMonitoreo ")
	public List<PgimMonitoreoComponenteDTOResultado> listarMonitoreoComponenteDistancias( @Param("codigoComponente") Long codigoComponente);
	
}
