package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSustanciaUmineraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSustanciaUminera;

/**
 * Ésta interface SustanciaUnidadMineraRepository esta conformado por sus métodos de 
 * listar las suntancias de la unidades mineras.
 * 
 * @descripción: Lógica de negocio de la entidad Sustancia unidad minera
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface SustanciaUnidadMineraRepository extends JpaRepository<PgimSustanciaUminera, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSustanciaUmineraDTOResultado(suum.idSustanciaUminera, "
            + "sust.idSustancia, unmi.idUnidadMinera) "
            + "FROM PgimSustanciaUminera suum INNER JOIN suum.pgimSustancia sust INNER JOIN suum.pgimUnidadMinera unmi "
            + "WHERE suum.esRegistro = '1' AND unmi.idUnidadMinera = :idUnidadMinera")
    public List<PgimSustanciaUmineraDTO> listar(@Param("idUnidadMinera") Long idUnidadMinera);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSustanciaUmineraDTOResultado(suum.idSustanciaUminera, "
            + "sust.idSustancia, unmi.idUnidadMinera, sust.noSustancia) "
            + "FROM PgimSustanciaUminera suum "
            + "INNER JOIN suum.pgimSustancia sust "
            + "INNER JOIN suum.pgimUnidadMinera unmi "
            + "WHERE suum.esRegistro = '1' "
            + "AND unmi.idUnidadMinera = :idUnidadMinera")
    public List<PgimSustanciaUmineraDTO> listarSustanciaPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);

}