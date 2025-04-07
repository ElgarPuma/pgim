package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCostoUnitario;

/** 
 * Ésta interface de CostoUnitarioRepository incluye 
 * los metodos para obtener el costo unitario.
 * 
 * @descripción: Lógica de negocio de la entidad Costo unitario
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 04/10/2020
 * @fecha_de_ultima_actualización: 10/10/2020
*/
@Repository
public interface CostoUnitarioRepository extends JpaRepository<PgimCostoUnitario, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTOResultado("
            + "pcu.idCostoUnitario, pcu.pgimTarifarioContrato.idTarifarioContrato, pcu.unDiaCostoUnitario, "
            + "pcu.moCostoUnitario, pcu.moCostoActa, pcu.moCostoInformeSupervision, "
            + "pcu.moCostoInformeGestion, pcu.esRegistro) "
            + "FROM PgimCostoUnitario pcu " 
            + "WHERE pcu.esRegistro = '1' "
            + "AND pcu.idCostoUnitario = :idCostoUnitario ")
	PgimCostoUnitarioDTO obtenerCostoUnitarioPorId(@Param("idCostoUnitario") Long idCostoUnitario);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTOResultado("
            + "pcu.idCostoUnitario, ptc.idTarifarioContrato, pcu.unDiaCostoUnitario, "
            + "pcu.moCostoUnitario, pcu.moCostoActa, pcu.moCostoInformeSupervision, "
            + "pcu.moCostoInformeGestion, pcu.esRegistro) "
            + "FROM PgimCostoUnitario pcu "
            + "LEFT JOIN PgimTarifarioContrato ptc ON pcu.pgimTarifarioContrato.idTarifarioContrato = ptc.idTarifarioContrato " 
            + "WHERE pcu.esRegistro = '1' "
            + "AND ptc.idTarifarioContrato = :idTarifarioContrato ")
	List<PgimCostoUnitarioDTO> obtenerCostoUnitarioPorIdTarifarioContrato(@Param("idTarifarioContrato") Long idTarifarioContrato);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTOResultado("
            + "coun.idCostoUnitario, taco.idTarifarioContrato, coun.unDiaCostoUnitario, "
            + "coun.moCostoUnitario, coun.moCostoActa, coun.moCostoInformeSupervision, "
            + "coun.moCostoInformeGestion, coun.esRegistro) "
            + "FROM PgimCostoUnitario coun "
            + "INNER JOIN coun.pgimTarifarioContrato taco "
            + "WHERE coun.esRegistro = '1' "
            + "AND taco.esRegistro = '1' "
            + "AND coun.unDiaCostoUnitario = :cantidadDiasEntre "
            + "AND taco.idTarifarioContrato = :idTarifarioContrato ")
    List<PgimCostoUnitarioDTO> obtenerCUnitarioPorIdTarifarioYDiaCosto(@Param("idTarifarioContrato") Long idTarifarioContrato,
    @Param("cantidadDiasEntre") Integer cantidadDiasEntre);

}
