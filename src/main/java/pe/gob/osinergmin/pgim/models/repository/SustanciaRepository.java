package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSustanciaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSustancia;

/**
 * Ésta interface SustanciaRepository permitirá listar los nombres de las sustancias.
 * 
 * @descripción: Lógica de negocio de la entidad Sustancia
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface SustanciaRepository extends JpaRepository<PgimSustancia, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSustanciaDTOResultado( "
            + "sust.idSustancia, sust.noSustancia, sust.tipoSustancia.idValorParametro) "
            + "FROM PgimSustancia sust WHERE sust.esRegistro = '1' ORDER BY sust.noSustancia")
    public List<PgimSustanciaDTO> listar();

}