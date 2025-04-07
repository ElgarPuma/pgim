package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimEntidadNegocioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEntidadNegocio;

/**
 * Éste interface EntidadNegocioRepository
 * que mostrar el listado de entidades de negocio
 * 
 * @descripción: Logica de negocio de la entidad de negocio
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 18/12/2023
 */
@Repository
public interface EntidadNegocioRepository  extends JpaRepository<PgimEntidadNegocio, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEntidadNegocioDTOResultado("
        + "ent.idEntidadNegocio, ent.noTablaEntidadNegocio, ent.deEtiquetaTablaNegocio "
        + ") "
        + "FROM PgimEntidadNegocio ent " 
        + "WHERE ent.esRegistro = '1' " 
        )
	List<PgimEntidadNegocioDTO> listadoEntidadNegocio();
    
}
