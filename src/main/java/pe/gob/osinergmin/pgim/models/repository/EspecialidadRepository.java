package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;

import java.util.List;

/**
 * En ésta interface EspecialidadRepository esta conformado pos sus metodos de aplicacion de 
 * los filtros por nombres de Especialidad.
 * 
 * @descripción: Lógica de negocio de la entidad Especialidad
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
public interface EspecialidadRepository extends JpaRepository<PgimEspecialidad, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTOResultado( "
            + "espe.idEspecialidad, espe.noEspecialidad ) "
            + "FROM PgimEspecialidad espe WHERE espe.esRegistro = '1' ")
    List<PgimEspecialidadDTO> filtrarPorNombreEspecialidad(@Param("nombre") String nombre);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTOResultado("
            + "espe.idEspecialidad, espe.noEspecialidad, disu.idValorParametro, disu.noValorParametro ) "
            + "FROM PgimEspecialidad espe, PgimValorParametro disu "
            + "WHERE disu.pgimParametro.idParametro = 5 "
            + "AND espe.esRegistro = '1' "
            + "AND disu.esRegistro = '1' "
            + "ORDER BY disu.noValorParametro ASC ")
    List<PgimEspecialidadDTO> listarEspecialidadPorDivisionSupervisora();
}