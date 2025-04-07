package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioContrato;

/**
 * Ésta interface FichaRevisionRepository incluye los metodos de obtener y listar Tarifario contrato
 * 
 * @descripción: Lógica de negocio de la entidad Tarifario contrato
 * 
 * @author hruiz
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 *
 */
@Repository
public interface TarifarioContratoRepository extends JpaRepository<PgimTarifarioContrato, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTOResultado("
            + "tc.idTarifarioContrato, tc.pgimContrato.idContrato, tc.noTarifario, tc.moSupervisionFallida) "
            + "FROM PgimTarifarioContrato tc " 
            + "WHERE tc.esRegistro = '1' "
            + "AND tc.pgimContrato.idContrato = :idContrato ")
    List<PgimTarifarioContratoDTO> listarTarifarioContrato(@Param("idContrato") Long idContrato);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTOResultado("
            + "tc.idTarifarioContrato, tc.pgimContrato.idContrato, tc.noTarifario, tc.moSupervisionFallida) "
            + "FROM PgimTarifarioContrato tc " 
            + "WHERE tc.esRegistro = '1' "
            + "AND tc.idTarifarioContrato = :idTarifarioContrato ")
    PgimTarifarioContratoDTO obtenerTarifarioContratoPorId(@Param("idTarifarioContrato") Long idTarifarioContrato);
}
