package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.Persona;

/**
 * En ésta interface PersonaRepositoryAlternativo incluye los metodos que
 * permite obtener el DNI (Número de Documento de Identidad).
 *
 * @descripción: Logica de negocio de la entidad Persona
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 08/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface PersonaRepositoryAlternativo extends JpaRepository<Persona, Long> {

	public Persona findByNroDocIdentidad(String nrodocidentidad);

}
