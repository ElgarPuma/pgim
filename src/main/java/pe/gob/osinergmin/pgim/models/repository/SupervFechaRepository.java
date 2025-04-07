package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervFecha;

/**
 * En ésta interface SupervFechaRepository esta conformado pos sus metodos de listar
 * Supervision fecha y obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Supervision fecha
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
public interface SupervFechaRepository extends JpaRepository<PgimSupervFecha, Long>  {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTOResultado("
            + "sf.idSupervFecha, sf.pgimSupervision.idSupervision, sf.tipoFecha.idValorParametro, "
            + "sf.feInicioSupervision, sf.feFinSupervision, sf.deMotivoCambio) "
            + "FROM PgimSupervFecha sf " 
            + "WHERE sf.pgimSupervision.idSupervision = :idSupervision")
	PgimSupervFechaDTO obtenerSupervFechaByIdSupervision(@Param("idSupervision") Long idSupervision);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTOResultado("
            + "sf.idSupervFecha, sf.pgimSupervision.idSupervision, sf.tipoFecha.idValorParametro, "
            + "sf.feInicioSupervision, sf.feFinSupervision, sf.deMotivoCambio) "
            + "FROM PgimSupervFecha sf "
            + "WHERE sf.idSupervFecha = :idSupervFecha "
            + "AND sf.esRegistro = '1' ")
	PgimSupervFechaDTO obtenerSupervFechaByIdSupervFecha(@Param("idSupervFecha") Long idSupervFecha);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervFechaDTOResultado("
            + "sf.idSupervFecha, sf.pgimSupervision.idSupervision, sf.tipoFecha.idValorParametro, "
            + "sf.feInicioSupervision, sf.feFinSupervision, sf.deMotivoCambio) "
            + "FROM PgimSupervFecha sf "
            + "WHERE sf.pgimSupervision.idSupervision = :idSupervision "
            + "AND sf.idSupervFecha <> :idSupervFecha "
            + "AND sf.tipoFecha.idValorParametro = :idTipoFecha "
            + "AND sf.esRegistro = '1' ")
	List<PgimSupervFechaDTO> listaObtenerSupervFechaByIdSupervisionAndIdSupervFecha(@Param("idSupervision") Long idSupervision, 
			@Param("idSupervFecha") Long idSupervFecha, @Param("idTipoFecha") Long idTipoFecha);
}
