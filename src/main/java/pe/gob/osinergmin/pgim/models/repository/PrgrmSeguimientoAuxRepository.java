package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.SeguimientoProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPrgrmSeguimientoAux;

/**
 * Éste interface PrgrmSeguimientoAuxRepository
 * que aplica obtener y listar el seguimiento de las supervisiones del programa.
 * 
 * @descripción: Logica de negocio de la entidad seguimineto de supervisiones.
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface PrgrmSeguimientoAuxRepository extends JpaRepository<PgimPrgrmSeguimientoAux, Long> {

    /**
     * Permite obtener la lista de seguimiento de programacion
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTOResultado("
            + "pas_aux.idSupervisionAux, pas_aux.idSupervision, pas_aux.idProgramaSupervision, pas_aux.idLineaPrograma, "
            + "pas_aux.noUnidadMinera, pas_aux.noTipoSupervision, pas_aux.feInicioSupervision, pas_aux.feFinSupervision, " 
            + "pas_aux.feInicioSupervisionReal, pas_aux.feFinSupervisionReal, pas_aux.idItemProgramaSupe, "
            + "pas_aux.noFaseActual, pas_aux.noPasoActual, pas_aux.noResponsable, "
            + "pas_aux.feInicioRealOprogramada, pas_aux.feFinRealOprogramada, pas_aux.costo, "

            + "pas_aux.feMesEstimado,  " 
            + "(CASE WHEN pas_aux.feInicioRealOprogramada IS NOT NULL AND pas_aux.feFinRealOprogramada IS NOT NULL " 
            + "THEN ( 'Inicio y fin: ' || ' ' || (TO_CHAR(pas_aux.feInicioRealOprogramada, 'DD/MM/YYYY') || ' - ' || TO_CHAR(pas_aux.feFinRealOprogramada, 'DD/MM/YYYY'))) " 
            + "ELSE ( 'Mes estimado: ' || ' ' || (TO_CHAR (pas_aux.feMesEstimado, 'MM/YYYY'))) END), "
            + "pas_aux.idInstanciaPasoActual " 
            + " ) " 
            + "FROM PgimPrgrmSeguimientoAux pas_aux " 
            + "WHERE 1 = 1 "
            + "AND pas_aux.idLineaPrograma = :idLineaPrograma "
            + "AND (:idFaseProceso IS NULL OR pas_aux.idFaseActual = :idFaseProceso ) "
            + "AND (:idPasoProceso IS NULL OR pas_aux.idPasoActual = :idPasoProceso) "
            + "AND (:descNoResponsable is null OR  pas_aux.idInstanciaProceso " 
            + "in (select eipx.pgimInstanciaProces.idInstanciaProceso from PgimEqpInstanciaPro eipx, PgimPersonalOsi posix "
            + ", PgimPersona perx where eipx.flEsResponsable='1' and eipx.esRegistro = '1' and posix.esRegistro = '1' and perx.esRegistro = '1' "
            + "and eipx.pgimPersonalOsi.idPersonalOsi =posix.idPersonalOsi and posix.pgimPersona.idPersona = perx.idPersona "
            + "and LOWER(perx.noPersona || ' ' || perx.apPaterno || ' ' || perx.apMaterno) LIKE LOWER(CONCAT('%', :descNoResponsable, '%'))"
            + ")  ) "
            + " ")
    Page<PgimPrgrmSeguimientoAuxDTO> listarProgramaSeguimiento(
            @Param("idLineaPrograma") Long idLineaPrograma, 
            @Param("idFaseProceso") Long idFaseProceso,
            @Param("idPasoProceso") Long idPasoProceso, 
            @Param("descNoResponsable") String descNoResponsable, Pageable paginador);


        /**
         * Obtener las propiedades de la supervision por el idSupervision
         * 
         * @param idSupervision identificador de la supervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTOResultado("
                        + "aux.idSupervisionAux, aux.idSupervision, aux.idUnidadMinera, " 
                        + "aux.idProgramaSupervision, aux.idLineaPrograma, aux.idTipoSupervision ,"
                        + "aux.noUnidadMinera, aux.noTipoSupervision, aux.idItemProgramaSupe, "
                        + "aux.noPasoActual, aux.idInstanciaPasoActual, "
                        + "aux.noResponsable, aux.costo, aux.deSubtipoSupervision, aux.moConsumoContrato, "
                        + "aux.moCostoEstimadoSupervision, aux.situacion, aux.agente, aux.coSupervision, "
                        + "aux.feInicioRealOprogramada, aux.feFinRealOprogramada, "
                        + "aux.feMesEstimado, "
                        + "(TO_CHAR(aux.feInicioRealOprogramada, 'DD/MM/YYYY') || ' - ' || TO_CHAR(aux.feFinRealOprogramada, 'DD/MM/YYYY')) "
                        + " ) "
                        + "FROM PgimPrgrmSeguimientoAux aux "
                        + "WHERE aux.idSupervision = :idSupervision " 
                        + "AND aux.idLineaPrograma = :idLineaPrograma ")
        PgimPrgrmSeguimientoAuxDTO obtenerSeguimientoPorSupervisionId(
                        @Param("idSupervision") Long idSupervision,
                        @Param("idLineaPrograma") Long idLineaPrograma);

        /**
         * Obtener las propiedades de la supervision por el id de item del programa
         * @param idItemProgramaSupe
         * @param idLineaPrograma
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTOResultado("
                        + "aux.idSupervisionAux, aux.idSupervision, aux.idUnidadMinera, " 
                        + "aux.idProgramaSupervision, aux.idLineaPrograma, aux.idTipoSupervision ,"
                        + "aux.noUnidadMinera, aux.noTipoSupervision, aux.idItemProgramaSupe, "
                        + "aux.noPasoActual, aux.idInstanciaPasoActual, "
                        + "aux.noResponsable, aux.costo, aux.deSubtipoSupervision, aux.moConsumoContrato, "
                        + "aux.moCostoEstimadoSupervision, aux.situacion, aux.agente, aux.coSupervision, "
                        + "aux.feInicioRealOprogramada, aux.feFinRealOprogramada, "
                        + "aux.feMesEstimado, "
                        + "(TO_CHAR(aux.feInicioRealOprogramada, 'DD/MM/YYYY') || ' - ' || TO_CHAR(aux.feFinRealOprogramada, 'DD/MM/YYYY')) "
                        + " ) "
                        + "FROM PgimPrgrmSeguimientoAux aux "
                        + "WHERE aux.idItemProgramaSupe = :idItemProgramaSupe AND aux.idLineaPrograma = :idLineaPrograma ")
        PgimPrgrmSeguimientoAuxDTO obtenerSeguimientoPorItemProgramaId(
                        @Param("idItemProgramaSupe") Long idItemProgramaSupe,
                        @Param("idLineaPrograma") Long idLineaPrograma);
        
        
        
        /**
         * Permite obtener los costos reales por linea base
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.SeguimientoProgramaDTOResultado("
        		+ "sum(seg.moConsumoContrato), "
        		+ "sum(case when seg.idTipoSupervision= :SUPER_FSCLZCION_NO_PRGRMDA then seg.moConsumoContrato end), "
        		+ "sum(case when seg.idTipoSupervision= :SUPER_FSCLZCION_PRGRMDA then seg.moConsumoContrato end) "        		 
                + " ) " 
                + "FROM PgimPrgrmSeguimientoAux seg " 
                + "WHERE seg.idSupervision is not null "
                + "AND seg.idLineaPrograma = :idLineaPrograma "                
                 )
        SeguimientoProgramaDTO obtenerCostosRealesPrograma(@Param("idLineaPrograma") Long idLineaPrograma, @Param("SUPER_FSCLZCION_NO_PRGRMDA") Long SUPER_FSCLZCION_NO_PRGRMDA, @Param("SUPER_FSCLZCION_PRGRMDA") Long SUPER_FSCLZCION_PRGRMDA);
}
