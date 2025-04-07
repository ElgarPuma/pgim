package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimInfracciontop15AuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInfracciontop15Aux;

/**
 * @descripción: Logica de negocio de la entidad resumen de las top 15 infracciones más frecuentes por año
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface Infracciontop15AuxRepository extends JpaRepository<PgimInfracciontop15Aux, Long>  {

	/**
	 * Permite obtener la lista preparada de infracciones mas recurrentes (top15) usado en reporte correspondiente
	 * @param anioInfraccion
	 * @return
	 */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfracciontop15AuxDTOResultado( "
            + "infra.nroItemAux, infra.noCortoNorma, infra.coTipificacion, infra.noItemTipificacion, infra.nroInfracciones "
            + ") " 
            + "FROM PgimInfracciontop15Aux infra "
            + "WHERE infra.anioInfraccion = :anioInfraccion "
            + "ORDER BY infra.nroInfracciones desc, infra.coTipificacion asc" )
    List<PgimInfracciontop15AuxDTO> listarReporteInfraccionesTop15(@Param("anioInfraccion") Long anioInfraccion);
    
    /**
     * Permite obtener la lista preparada de infracciones mas recurrentes (top15) usado en reporte correspondiente
     * @param anioInfraccion
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfracciontop15AuxDTOResultado( "
    		+ "infra.nroItemAux, infra.noCortoNorma, infra.coTipificacion, infra.noItemTipificacion, infra.nroInfracciones "
    		+ ") " 
    		+ "FROM PgimInfracciontop15Aux infra "
    		+ "WHERE infra.anioInfraccion = :anioInfraccion "
    		+ "ORDER BY infra.nroInfracciones desc, infra.coTipificacion asc" )
    Page<PgimInfracciontop15AuxDTO> listarReporteInfraccionesTop15Paginado(@Param("anioInfraccion") Long anioInfraccion, Pageable paginador);
}
