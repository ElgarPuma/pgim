package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.gob.osinergmin.pgim.dtos.PgimTipoSubcomponenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoSubcomponente;

/**
 * @descripción: Logica de negocio de la entidad tipo subcomponente minero.
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface TipoSubcomponenteRepository extends JpaRepository<PgimTipoSubcomponente, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoSubcomponenteDTOResultado( "
            + "sub.idTipoSubcomponente, sub.tipoComponenteMinero.idValorParametro, " 
            + "sub.coTipoSubcomponente, sub.noTipoSubcomponente) "
            + "FROM PgimTipoSubcomponente sub " 
            + "WHERE sub.esRegistro = '1' ORDER BY sub.noTipoSubcomponente")
    public List<PgimTipoSubcomponenteDTO> listarSubTipoComponentes();
}
