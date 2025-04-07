package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioRegla;

/**
 * Ésta interface FichaRevisionRepository incluye los metodos de obtener y listar Tarifario regla
 * 
 * @descripción: Lógica de negocio de la entidad Tarifario regla
 * 
 * @author hruiz
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 *
 */
@Repository
public interface TarifarioReglaRepository extends JpaRepository<PgimTarifarioRegla, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTOResultado("
            + "ptr.idTarifarioRegla, ptr.pgimTarifarioContrato.idTarifarioContrato, "
            + "ptr.pgimSubtipoSupervision.idSubtipoSupervision, ptr.pgimMotivoSupervision.idMotivoSupervision, ptr.esRegistro) "
            + "FROM PgimTarifarioRegla ptr " 
            + "WHERE ptr.esRegistro = '1' "
            + "AND ptr.idTarifarioRegla = :idTarifarioRegla ")
	PgimTarifarioReglaDTO obtenerTarifarioReglaPorId(@Param("idTarifarioRegla") Long idTarifarioRegla);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTOResultado("
            + "ptr.idTarifarioRegla, ptc.idTarifarioContrato, "
            + "pss.idSubtipoSupervision, pms.idMotivoSupervision, ptr.esRegistro) "
            + "FROM PgimTarifarioRegla ptr "
            + "LEFT JOIN PgimTarifarioContrato ptc ON ptr.pgimTarifarioContrato.idTarifarioContrato = ptc.idTarifarioContrato "
            + "LEFT JOIN PgimSubtipoSupervision pss ON ptr.pgimSubtipoSupervision.idSubtipoSupervision = pss.idSubtipoSupervision "
            + "LEFT JOIN PgimMotivoSupervision pms ON ptr.pgimMotivoSupervision.idMotivoSupervision = pms.idMotivoSupervision " 
            + "WHERE ptr.esRegistro = '1' "
            + "AND ptc.idTarifarioContrato = :idTarifarioContrato ")
	List<PgimTarifarioReglaDTO> obtenerTarifarioReglaPorIdTarifarioContrato(@Param("idTarifarioContrato") Long idTarifarioContrato);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTOResultado("
            + "tare.idTarifarioRegla, taco.idTarifarioContrato, "
            + "stsu.idSubtipoSupervision, mosu.idMotivoSupervision) "
            + "FROM PgimTarifarioRegla tare "
            + "INNER JOIN tare.pgimTarifarioContrato taco "
            + "INNER JOIN taco.pgimContrato contr "
            + "INNER JOIN tare.pgimSubtipoSupervision stsu "
            + "INNER JOIN tare.pgimMotivoSupervision mosu "
            + "WHERE tare.esRegistro = '1' "
            + "AND taco.esRegistro = '1' "
            + "AND contr.esRegistro = '1' "
            + "AND stsu.esRegistro = '1' "
            + "AND mosu.esRegistro = '1' "
            + "AND contr.idContrato = :idContrato "
            + "AND stsu.idSubtipoSupervision = :idSubtipoSupervision "
            + "AND mosu.idMotivoSupervision = :idMotivoSupervision")
    List<PgimTarifarioReglaDTO> listarTarifariosCumplenRegla(@Param("idContrato") Long idContrato,
                                                             @Param("idSubtipoSupervision") Long idSubtipoSupervision,
                                                             @Param("idMotivoSupervision") Long idMotivoSupervision);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTOResultado("
            + "ptr.idTarifarioRegla, ptr.pgimTarifarioContrato.idTarifarioContrato, "
            + "ptr.pgimSubtipoSupervision.idSubtipoSupervision, ptr.pgimMotivoSupervision.idMotivoSupervision, ptr.esRegistro) "
            + "FROM PgimTarifarioRegla ptr "
            + "INNER JOIN ptr.pgimTarifarioContrato ptc " 
            + "WHERE ptr.esRegistro = '1' "
            + "and ptc.esRegistro = '1' "
            + "AND ptc.pgimContrato.idContrato = :idContrato ")
	List<PgimTarifarioReglaDTO> obtenerTarifarioReglaPorIdContrato(@Param("idContrato") Long idContrato);
    
}
