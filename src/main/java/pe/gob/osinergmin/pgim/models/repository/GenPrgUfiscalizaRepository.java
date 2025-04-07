package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimGenPrgUfiscaliza;

/** 
 * Ésta interface de GenPrgRankingRepository.
 * 
 * @descripción: Lógica de negocio de la entidad PgimGenPrgUfiscaliza
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 03/01/2024
*/
@Repository
public interface GenPrgUfiscalizaRepository extends JpaRepository<PgimGenPrgUfiscaliza, Long> {


}
