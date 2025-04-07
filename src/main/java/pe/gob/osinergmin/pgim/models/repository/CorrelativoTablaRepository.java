package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.gob.osinergmin.pgim.models.entity.PgimCorrelativoTabla;

/**
* @descripción: Interface para generación de consultas relacionadas a la entidad PgimCorrelativoTabla
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 27/06/2023
*/
public interface CorrelativoTablaRepository extends JpaRepository<PgimCorrelativoTabla, Long>{
    
}
