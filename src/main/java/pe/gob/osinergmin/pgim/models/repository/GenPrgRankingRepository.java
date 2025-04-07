package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimGenPrgRanking;

/** 
 * Ésta interface de GenPrgRankingRepository.
 * 
 * @descripción: Lógica de negocio de la entidad PgimGenPrgRanking
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 03/01/2024
*/
@Repository
public interface GenPrgRankingRepository extends JpaRepository<PgimGenPrgRanking, Long> {


}
