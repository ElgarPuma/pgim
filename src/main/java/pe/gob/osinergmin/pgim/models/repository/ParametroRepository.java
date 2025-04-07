package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.gob.osinergmin.pgim.dtos.PgimParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimParametro;

import java.util.List;

/** 
 * @descripción: Lógica de negocio de la entidad Parametro
 * 
 * @author: pablo
 * @version: 1.0
 * @fecha_de_creación: 09/10/2020
 * @fecha_de_ultima_actualización: 09/10/2020
*/
public interface ParametroRepository extends JpaRepository<PgimParametro, Long>{
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimParametroDTOResultado(parametro.idParametro, parametro.coParametro, parametro.deParametro, parametro.nuOrden) "
            + "FROM PgimParametro parametro "
			+ "WHERE parametro.esRegistro = '1' "
            + "ORDER BY parametro.coParametro")
    List<PgimParametroDTO> listarParametros();	
}
