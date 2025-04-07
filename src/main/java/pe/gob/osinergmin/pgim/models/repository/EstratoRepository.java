package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEstrato;

import java.util.List;

/**
 * Éste interface EstratoRepository que nos permitirá la lista y tambien mostrar
 * por filtros de los nombres de Estratos de una empresa.
 * 
 * @descripción: Logica de negocio de la entidad Estrato
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface EstratoRepository extends JpaRepository<PgimEstrato, Long> {


	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstratoDTOResultado( "
			+ "e.idEstrato,e.noEstrato,e.nuCapacidadProductiva,e.nuCapacidadBeneficio,e.esRegistro) "
			+ "FROM PgimEstrato e "
			+ "WHERE e.esRegistro = '1' ")
	public List<PgimEstratoDTO> listarEstrato();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEstratoDTOResultado( "
            + "estr.idEstrato, estr.noEstrato ) "
            + "FROM PgimEstrato estr WHERE estr.esRegistro = '1' ")
    List<PgimEstratoDTO> filtrarPorNombreEstrato(@Param("nombre") String nombre);
}

