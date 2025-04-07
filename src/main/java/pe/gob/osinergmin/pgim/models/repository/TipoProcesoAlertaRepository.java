package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimTipoProcesoAlerta;

/**
 * Interface TipoProcesoAlertaRepository
 * 
 * @descripción: Lógica de negocio de la gestión del tipo de alertas de instancia de proceso
 * 
 * @author: palominovega
 * @version: 1.0 
 * @fecha_de_creación: 16/12/2022 
 */
@Repository
public interface TipoProcesoAlertaRepository extends JpaRepository<PgimTipoProcesoAlerta, Long>{

  
  
}
