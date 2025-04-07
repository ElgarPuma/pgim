package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.gob.osinergmin.pgim.dtos.PgimSubactividadDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSubactividad;

/**
 * Tiene como nombre SubactividadRepository.
 * 
 * @descripción: Logica de negocio de la entidad subactividad
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface SubactividadRepository extends JpaRepository<PgimSubactividad, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubactividadDTOResultado( "
            + "sub.idSubactividad, sub.actividad.idValorParametro, " 
            + "sub.noSubactividad) "
            + "FROM PgimSubactividad sub " 
            + "WHERE sub.esRegistro = '1' ORDER BY sub.noSubactividad")
    public List<PgimSubactividadDTO> listarSubActividad();
}
