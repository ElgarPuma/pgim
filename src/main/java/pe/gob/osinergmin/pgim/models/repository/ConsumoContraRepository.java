package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConsumoContra;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;

/**
 * 
 * @descripción: Lógica de negocio de la entidad Consumo contrato
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
public interface ConsumoContraRepository extends JpaRepository<PgimConsumoContra, Long>{

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTOResultado("
			+ "coco.idConsumoContra, coco.pgimSupervision.idSupervision, coco.pgimContrato.idContrato, coco.moConsumoContrato) "
			+ "FROM PgimConsumoContra coco "
            + "WHERE coco.pgimSupervision.idSupervision = :idSupervision "
            + "AND coco.esRegistro = '1'")
	List<PgimConsumoContraDTO> obtenerMontoConsumo(@Param("idSupervision") Long idSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTOResultado("
			+ "coco.idConsumoContra, coco.moConsumoContrato) "
            + "FROM PgimConsumoContra coco "
            + "INNER JOIN coco.pgimSupervision supe "
            + "INNER JOIN supe.pgimInstanciaProces inpr "
            + "WHERE coco.pgimContrato.idContrato = :idContrato "
            + "AND NOT EXISTS ( "
            + "SELECT 1 "
            + "FROM PgimInstanciaPaso inpa "
            + "INNER JOIN inpa.pgimRelacionPaso repa "
            + "INNER JOIN repa.tipoRelacion tire "
            + "WHERE inpa.pgimInstanciaProces = inpr "
            + "AND tire.coClaveTexto = :REPAS_CNCLAR_FLJO "
            + "AND inpa.esRegistro = '1' "
            + "AND repa.esRegistro = '1' "
            + "AND tire.esRegistro = '1' "
            + ") "
            + "AND coco.esRegistro = '1' "
            + "AND supe.esRegistro = '1'"
            )
	List<PgimConsumoContraDTO> obtenerMontoConsumoTotal(@Param("idContrato") Long idContrato, @Param("REPAS_CNCLAR_FLJO") String REPAS_CNCLAR_FLJO);

	List<PgimConsumoContra> findByPgimSupervisionAndEsRegistro(PgimSupervision pgimSupervision, String string);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTOResultado("
			+ "coco.idConsumoContra, coco.moConsumoContrato ) "
			+ "FROM PgimConsumoContra coco "
			+ "INNER JOIN PgimSupervision supe ON coco.pgimSupervision = supe "
			+ "INNER JOIN PgimInstanciaProces inpro ON supe.pgimInstanciaProces = inpro "
            + "INNER JOIN PgimInstanciaPaso inpa ON inpro = inpa.pgimInstanciaProces AND inpa.flEsPasoActivo = '1' AND inpa.esRegistro = '1' "
            + "WHERE coco.esRegistro = '1' "
            + "AND coco.pgimContrato.idContrato = :idContrato "
            + "AND inpa.pgimRelacionPaso.tipoRelacion.idValorParametro <> 291L ")
	List<PgimConsumoContraDTO> listarConsumoContratoPorContrato(@Param("idContrato") Long idContrato);

}
