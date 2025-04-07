package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.Ubigeo;

/**
 * En ésta interface UbigeoRepositoryAlternativo esta conformado por sus metodos de listar por 
 * filtros por departamento,provincia o distrito.
 * 
 * @descripción: Lógica de negocio de la entidad Ubigeo
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface UbigeoRepositoryAlternativo extends JpaRepository<Ubigeo, String>{
	
	@Query("select u from Ubigeo u where LOWER(u.nombre) like %?1%")
	public List<Ubigeo> findByNombre(String nombre);

}
