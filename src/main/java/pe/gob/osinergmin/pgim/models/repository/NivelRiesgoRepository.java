package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimNivelRiesgo;

/**
 * 
 * 
 * @descripción: Lógica de negocio de la entidad Nivel de riesgo
 * 
 * @author hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/02/2021
 * @fecha_de_ultima_actualización:
 *
 */
public interface NivelRiesgoRepository extends JpaRepository<PgimNivelRiesgo, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoResultadoDTO( "
            + "niri.idNivelRiesgo, niri.noNivelRiesgo, niri.deNivelRiesgo, niri.nuOrden, niri.nuValor, "
            + "niri.nuValorInferior, niri.nuValorSuperior, niri.coNivelRiesgo, niri.coColorHexadecimal ) "
            + "FROM PgimNivelRiesgo niri "
            + "WHERE niri.esRegistro = '1' "
            + "ORDER BY niri.nuOrden"
            )
	List<PgimNivelRiesgoDTO> listarNivelRiesgo();
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoResultadoDTO( "
            + "niri.idNivelRiesgo, niri.noNivelRiesgo, niri.deNivelRiesgo, niri.nuOrden, niri.nuValor, "
            + "niri.nuValorInferior, niri.nuValorSuperior, niri.coNivelRiesgo, niri.coColorHexadecimal ) "
            + "FROM PgimNivelRiesgo niri "
            + "WHERE niri.esRegistro = '1' "
            + "ORDER BY niri.nuOrden DESC "
            )
	List<PgimNivelRiesgoDTO> listarNivelRiesgoDesc();

}
