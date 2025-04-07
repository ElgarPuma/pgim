package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.gob.osinergmin.pgim.models.entity.PgimUbigeo;

/**
 * Ésta interface PgimUbigeoRepository esta conformado sus propeidades por deparatmentos, provincia y distritos.
 * 
 * @descripción: Logica de negocio de la entidad Ubigeo
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface PgimUbigeoRepository extends JpaRepository<PgimUbigeo, Long>{

}
