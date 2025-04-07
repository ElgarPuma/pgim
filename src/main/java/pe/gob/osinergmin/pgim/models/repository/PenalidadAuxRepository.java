package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPenalidadAux;

/**
 * @descripción: Logica de negocio de la entidad penalidades del contrato
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface PenalidadAuxRepository extends JpaRepository<PgimPenalidadAux, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTOResultado("
            + "peaux.idPenalidadAux, peaux.pgimLiquidacion.idLiquidacion, peaux.nuLiquidacion, peaux.pgimContrato.idContrato, peaux.nuContrato, "
            + "peaux.pgimEspecialidad.idEspecialidad, peaux.noEspecialidad, peaux.feCreacion, peaux.faseActual.idFaseProceso, peaux.noFaseActual, "
            + "peaux.pasoActual.idPasoProceso, peaux.noPasoActual, peaux.moItemConsumo, peaux.moPenalidadPlazo, peaux.moPenalidadReincidencia, "
            + "peaux.moPenalidadSinEpp, peaux.moPenalidad, emsu.pgimPersona.coDocumentoIdentidad || ' - ' || emsu.pgimPersona.noRazonSocial "
            + ") " 
            + "FROM PgimPenalidadAux peaux "
            + "INNER JOIN PgimContrato con ON peaux.pgimContrato.idContrato = con.idContrato "
            + "INNER JOIN PgimEmpresaSupervisora emsu ON con.pgimEmpresaSupervisora.idEmpresaSupervisora = emsu.idEmpresaSupervisora "
            + "WHERE 1 = 1 "
            + "AND (:nuContrato IS NULL OR LOWER(con.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:descNoRazonSocial IS NULL OR LOWER(emsu.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :descNoRazonSocial, '%')) ) "
            + "AND (:feInicio IS NULL OR TRUNC(peaux.feCreacion) >= :feInicio ) "
            + "AND (:feFin IS NULL OR TRUNC(peaux.feCreacion) <= :feFin ) "
            )
    Page<PgimPenalidadAuxDTO> listarReportePenalidadesPeriodoContratoSupervisora(
    		@Param("nuContrato") String nuContrato, @Param("descNoRazonSocial") String descNoRazonSocial, 
    		@Param("feInicio") Date feInicio, @Param("feFin") Date feFin, Pageable paginador);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTOResultado("
            + "peaux.idPenalidadAux, peaux.pgimLiquidacion.idLiquidacion, peaux.nuLiquidacion, peaux.pgimContrato.idContrato, peaux.nuContrato, "
            + "peaux.pgimEspecialidad.idEspecialidad, peaux.noEspecialidad, peaux.feCreacion, peaux.faseActual.idFaseProceso, peaux.noFaseActual, "
            + "peaux.pasoActual.idPasoProceso, peaux.noPasoActual, peaux.moItemConsumo, peaux.moPenalidadPlazo, peaux.moPenalidadReincidencia, "
            + "peaux.moPenalidadSinEpp, peaux.moPenalidad, emsu.pgimPersona.coDocumentoIdentidad || ' - ' || emsu.pgimPersona.noRazonSocial "
            + ") " 
            + "FROM PgimPenalidadAux peaux "
            + "INNER JOIN PgimContrato con ON peaux.pgimContrato.idContrato = con.idContrato "
            + "INNER JOIN PgimEmpresaSupervisora emsu ON con.pgimEmpresaSupervisora.idEmpresaSupervisora = emsu.idEmpresaSupervisora "
            + "WHERE 1 = 1 "
            + "AND (:nuContrato IS NULL OR LOWER(con.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:descNoRazonSocial IS NULL OR LOWER(emsu.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :descNoRazonSocial, '%')) ) "
            + "AND (:feInicio IS NULL OR TRUNC(peaux.feCreacion) >= :feInicio ) "
            + "AND (:feFin IS NULL OR TRUNC(peaux.feCreacion) <= :feFin ) "
            + "ORDER BY peaux.nuContrato DESC "
            )
    List<PgimPenalidadAuxDTO> listarReportePenPerContratoSupervisora(@Param("nuContrato") String nuContrato, 
    		@Param("descNoRazonSocial") String descNoRazonSocial, @Param("feInicio") Date feInicio, @Param("feFin") Date feFin);
}
