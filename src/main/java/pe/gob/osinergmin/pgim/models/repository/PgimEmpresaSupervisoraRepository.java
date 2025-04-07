package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;

/**
 * @descripción: Logica de negocio de la entidad Empresa supervisora
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface PgimEmpresaSupervisoraRepository extends JpaRepository<PgimEmpresaSupervisora, Long> {
    
}
