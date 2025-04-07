package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaObservacion;

/**
 * Ésta interface FichaObservacionRepository incluye los metodos de obtener
 * la ficha de observación
 * 
 * @descripción: Lógica de negocio de la entidad Fiacha observacion
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 */
@Repository
public interface FichaObservacionRepository extends JpaRepository<PgimFichaObservacion, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTOResultado("
                        + "aprob.idFichaObservacion, aprob.pgimFichaRevision.idFichaRevision, " 
                        + "aprob.tipoObservacionFicha.idValorParametro, aprob.fichaObservacionOrigen.idFichaObservacion, "
                        + "aprob.feRevisionFicha, aprob.caDiasParaPresentacion, aprob.feDesdeParaPresentacion, aprob.fePresentacion, "
                        + "aprob.deParteInformeObservadaT, aprob.deItemObservacionT, aprob.flSubsanada, aprob.cmItemObservacionT, " 
                        + "aprob.tipoObservacionFicha.noValorParametro, aprob.feCreacion " 
                        + ") " 
                        + "FROM PgimFichaObservacion aprob "
                        + "WHERE aprob.esRegistro = '1' AND aprob.idFichaObservacion = :idFichaObservacion")
        PgimFichaObservacionDTO obtenerFichaAprobacionPorId(@Param("idFichaObservacion") Long idFichaObservacion);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTOResultado("
			+ "fo.idFichaObservacion, fo.pgimFichaRevision.idFichaRevision, fo.tipoObservacionFicha.idValorParametro, "
			+ "fo.fichaObservacionOrigen.idFichaObservacion, fo.feRevisionFicha, fo.caDiasParaPresentacion, fo.feDesdeParaPresentacion, "
			+ "fo.fePresentacion, fo.deParteInformeObservadaT, fo.deItemObservacionT, fo.flSubsanada, fo.cmItemObservacionT, "
			+ "fo.tipoObservacionFicha.noValorParametro, fo.feCreacion ) "
			+ "FROM PgimFichaObservacion fo "
			+ "WHERE fo.esRegistro = '1' "
			+ "AND fo.idFichaObservacion = :idFichaObservacion ")
	PgimFichaObservacionDTO obtenerFichaObservacionPorId(@Param("idFichaObservacion") Long idFichaObservacion);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTOResultado("
			+ "fo.idFichaObservacion, fo.pgimFichaRevision.idFichaRevision, fo.tipoObservacionFicha.idValorParametro, "
			+ "fo.fichaObservacionOrigen.idFichaObservacion, fo.feRevisionFicha, fo.caDiasParaPresentacion, fo.feDesdeParaPresentacion, "
			+ "fo.fePresentacion, fo.deParteInformeObservadaT, fo.deItemObservacionT, fo.flSubsanada, fo.cmItemObservacionT, "
			+ "fo.tipoObservacionFicha.noValorParametro, fo.feCreacion ) "
			+ "FROM PgimFichaObservacion fo "
			+ "WHERE fo.esRegistro = '1' "
			+ "AND fo.pgimFichaRevision.idFichaRevision = :idFichaRevision ORDER BY fo.idFichaObservacion ASC")
	List<PgimFichaObservacionDTO> obtenerFichaObservacionPorIdFichaRevision(@Param("idFichaRevision") Long idFichaRevision);
}
