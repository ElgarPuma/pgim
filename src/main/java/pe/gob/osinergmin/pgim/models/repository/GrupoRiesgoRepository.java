package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.gob.osinergmin.pgim.dtos.PgimGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimGrupoRiesgo;

/**
 * @descripción: Logica de negocio de la entidad grupo riesgo
 * 
 * @author: humbertoruiz90
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface GrupoRiesgoRepository extends JpaRepository<PgimGrupoRiesgo, Long> {

	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGrupoRiesgoDTOResultado("
            + "gruri.idGrupoRiesgo, gruri.noGrupo, gruri.deGrupo "
            + ") "
            + "FROM PgimGrupoRiesgo gruri "
            + "WHERE gruri.esRegistro = '1' ")
	List<PgimGrupoRiesgoDTO> listarPgimGrupoRiesgoDTO();

}