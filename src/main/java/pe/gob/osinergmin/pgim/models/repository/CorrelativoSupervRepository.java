package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimCorrelativoSuperv;

/**
 * @descripción: Interface para generación de consultas relacionadas a la entidad CorrelativoSuperv.
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 07/08/2020
 * @fecha_de_ultima_actualización: 07/08/2020
 */
@Repository
public interface CorrelativoSupervRepository extends JpaRepository<PgimCorrelativoSuperv, Long>{

}
