package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;

/**
 * Interfaz que determina el comportamiento requerido para la gestión de la
 * relación de los pasos de flujo de trabajo.
 * 
 * @descripción: Repositorio que incluye la interacción directa con las tablas
 *               relacionadas con la relación de pasos de flujo de trabajo.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/08/2020
 * @fecha_de_ultima_actualización: 05/08/2020
 */
@Repository
public interface RelacionPasoRepository extends JpaRepository<PgimRelacionPaso, Long> {

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTOResultado("
                        + "repa.idRelacionPaso, repa.pasoProcesoOrigen.idPasoProceso, repa.pasoProcesoDestino.idPasoProceso, "
                        + "repa.flRequiereDestinatario, repa.flRequiereAprobacion, pror.noPasoProceso, "
                        + "pror.dePasoProceso, prde.noPasoProceso, prde.dePasoProceso, "
                        + "pror.pgimRolProceso.idRolProceso, pror.pgimRolProceso.noRolProceso, "
                        + "prde.pgimRolProceso.idRolProceso, prde.pgimRolProceso.noRolProceso, "
                        + "tire.idValorParametro, tire.noValorParametro)"
                        + "FROM PgimRelacionPaso repa " 
                        + "INNER JOIN repa.pasoProcesoOrigen pror "
                        + "INNER JOIN repa.pasoProcesoDestino prde "
                        + "INNER JOIN repa.tipoRelacion tire "
                        + "WHERE repa.esRegistro = '1' "
                        + "AND pror.esRegistro = '1' "
                        + "AND prde.esRegistro = '1' "
                        + "AND repa.flIniciaProceso = '1' "
                        + "AND repa.pasoProcesoOrigen.pgimFaseProceso.pgimProceso.idProceso = :idProceso")
        PgimRelacionPasoDTO obtenerRelacionPasoInicial(@Param("idProceso") Long idProceso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTOResultado("
                        + "repa.idRelacionPaso, repa.pasoProcesoOrigen.idPasoProceso, repa.pasoProcesoDestino.idPasoProceso, "
                        + "repa.flRequiereDestinatario, repa.flRequiereAprobacion, pror.noPasoProceso, "
                        + "pror.dePasoProceso, prde.noPasoProceso, prde.dePasoProceso, "
                        + "pror.pgimRolProceso.idRolProceso, pror.pgimRolProceso.noRolProceso, prde.pgimRolProceso.idRolProceso, "
                        + "prde.pgimRolProceso.noRolProceso, tire.idValorParametro, tire.noValorParametro, "
                        + "tias.idValorParametro, tias.noValorParametro, prde.flAgrupadora "
                        + ") "
                        + "FROM PgimRelacionPaso repa " 
                        + "INNER JOIN repa.pasoProcesoOrigen pror "
                        + "INNER JOIN repa.pasoProcesoDestino prde "
                        + "INNER JOIN repa.tipoRelacion tire "
                        + "INNER JOIN repa.tipoAccionSiged tias "
                        + "WHERE repa.idRelacionPaso = :idRelacionPaso " 
                        + "AND repa.esRegistro = '1'")
        PgimRelacionPasoDTO obtenerRelacionPasoPorId(@Param("idRelacionPaso") Long idRelacionPaso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTOResultado("
                        + "repa.idRelacionPaso, repa.pasoProcesoOrigen.idPasoProceso, repa.pasoProcesoDestino.idPasoProceso, "
                        + "repa.flRequiereDestinatario, repa.flRequiereAprobacion, pror.noPasoProceso, "
                        + "pror.dePasoProceso, prde.noPasoProceso, prde.dePasoProceso, "
                        + "pror.pgimRolProceso.idRolProceso, pror.pgimRolProceso.noRolProceso, prde.pgimRolProceso.idRolProceso, "
                        + "prde.pgimRolProceso.noRolProceso, tire.idValorParametro, tire.noValorParametro, "
                        + "tias.idValorParametro, tias.noValorParametro, prde.flAgrupadora, fade.noFaseProceso "
                        + ") "
                        + "FROM PgimRelacionPaso repa " 
                        + "INNER JOIN repa.pasoProcesoOrigen pror "
                        + "INNER JOIN repa.pasoProcesoDestino prde "
                        + "INNER JOIN repa.pasoProcesoDestino.pgimFaseProceso fade "
                        + "INNER JOIN repa.tipoRelacion tire "
                        + "INNER JOIN repa.tipoAccionSiged tias "
                        + "WHERE prde.pgimFaseProceso.pgimProceso.idProceso = :idProceso " 
                        + "AND repa.esRegistro = '1'")
        Page<PgimRelacionPasoDTO> listarRelacionPasoPorIdProceso(@Param("idProceso") Long idProceso, Pageable page);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTOResultado("
                        + "repa.idRelacionPaso, repa.pasoProcesoOrigen.idPasoProceso, repa.pasoProcesoDestino.idPasoProceso, "
                        + "repa.flRequiereDestinatario, repa.flRequiereAprobacion, pror.noPasoProceso, "
                        + "pror.dePasoProceso, prde.noPasoProceso, prde.dePasoProceso, "
                        + "pror.pgimRolProceso.idRolProceso, pror.pgimRolProceso.noRolProceso, prde.pgimRolProceso.idRolProceso, "
                        + "prde.pgimRolProceso.noRolProceso, tire.idValorParametro, tire.noValorParametro, "
                        + "tias.idValorParametro, tias.noValorParametro, prde.flAgrupadora "
                        + ") "
                        + "FROM PgimRelacionPaso repa " 
                        + "INNER JOIN repa.pasoProcesoOrigen pror "
                        + "INNER JOIN repa.pasoProcesoDestino prde "
                        + "INNER JOIN repa.tipoRelacion tire "
                        + "INNER JOIN repa.tipoAccionSiged tias "
                        + "WHERE repa.pasoProcesoOrigen.idPasoProceso = :idPasoProcesoActual "
                        + "AND repa.flRelacionAgrupada = '0' " 
                        + "AND repa.esRegistro = '1' " 
                        + "AND pror.esRegistro = '1' "
                        + "AND prde.esRegistro = '1' "                        
                        + "ORDER BY tire.nuOrden")
        List<PgimRelacionPasoDTO> obtenerListaPasosSiguientes(@Param("idPasoProcesoActual") Long idPasoProcesoActual);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTOResultado("
                + "repa.idRelacionPaso, repa.pasoProcesoOrigen.idPasoProceso, repa.pasoProcesoDestino.idPasoProceso, "
                + "repa.flRequiereDestinatario, repa.flRequiereAprobacion, pror.noPasoProceso, "
                + "pror.dePasoProceso, prde.noPasoProceso, prde.dePasoProceso, "
                + "pror.pgimRolProceso.idRolProceso, pror.pgimRolProceso.noRolProceso, prde.pgimRolProceso.idRolProceso, "
                + "prde.pgimRolProceso.noRolProceso, tire.idValorParametro, tire.noValorParametro, "
                + "tias.idValorParametro, tias.noValorParametro, prde.flAgrupadora, "
                + "pror.pgimFaseProceso.pgimProceso.idProceso, pror.pgimFaseProceso.idFaseProceso, pror.pgimFaseProceso.noFaseProceso, "
                + "prde.pgimFaseProceso.pgimProceso.idProceso, prde.pgimFaseProceso.idFaseProceso, prde.pgimFaseProceso.noFaseProceso "
                + ") "
                + "FROM PgimRelacionPaso repa " 
                + "INNER JOIN repa.pasoProcesoOrigen pror "
                + "INNER JOIN repa.pasoProcesoDestino prde "
                + "INNER JOIN repa.tipoRelacion tire "
                + "INNER JOIN repa.tipoAccionSiged tias "
                + "WHERE 1=1 " 
                + "AND repa.esRegistro = '1' " 
                + "AND pror.esRegistro = '1' "
                + "AND prde.esRegistro = '1' "                        
                + "ORDER BY pror.pgimFaseProceso.idFaseProceso, pror.noPasoProceso")
        List<PgimRelacionPasoDTO> obtenerTodosRelacionPaso();

}