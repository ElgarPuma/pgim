package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimSubcatFasepro;

/**
 *  
 * @descripción: Logica de negocio de la entidad SubcatFasepro
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 02/04/2021
 * 
 */
@Repository
public interface SubcatFaseproRepository extends JpaRepository<PgimSubcatFasepro, Long> {

}
