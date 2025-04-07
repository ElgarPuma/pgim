package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimHechoCnsttdoFase;

/**
 * Esta interface HechoCnsttdoFaseRepository
 * 
 * @descripción: Lógica de negocio de la entidad hecho constatado historico por fase
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 06/02/2023
 * 
 */
@Repository
public interface HechoCnsttdoFaseRepository extends JpaRepository<PgimHechoCnsttdoFase, Long> {


}
