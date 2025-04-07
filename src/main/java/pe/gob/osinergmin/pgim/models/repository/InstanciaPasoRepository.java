package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;

import java.util.List;

/**
 * En ésta interface InstanciaPasoRepository esta conformado pos sus metodos de 
 * obtener sus propiedade.
 * 
 * @descripción: Lógica de negocio de la entidad Instancia paso
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface InstanciaPasoRepository extends JpaRepository<PgimInstanciaPaso, Long> {

    /**
     * Permite obtener el listado de pasos actuales.
     * 
     * @param idInstanciaPaso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
            + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
            + "inpa.personaEqpOrigen.idEquipoInstanciaPro, inpa.personaEqpDestino.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
            + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
            + "inpa.tipoSubflujo.idValorParametro) " 
            + "FROM PgimInstanciaPaso inpa " 
            + "WHERE inpa.esRegistro = '1' "
            + "AND inpa.idInstanciaPaso = :idInstanciaPaso "
            + "AND inpa.flEsPasoActivo = '1' "
            )
    List<PgimInstanciaPasoDTO> obtenerInstanciaPasosActuales(@Param("idInstanciaPaso") Long idInstanciaPaso);

    /**
     * Permite obtener el listado de pasos actuales.
     * 
     * @param idInstanciaProceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
            + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
            + "inpa.personaEqpOrigen.idEquipoInstanciaPro, inpa.personaEqpDestino.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
            + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
            + "inpa.tipoSubflujo.idValorParametro) " 
            + "FROM PgimInstanciaPaso inpa " 
            + "WHERE inpa.esRegistro = '1' "
            + "AND inpa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            + "AND inpa.flEsPasoActivo = '1' "
            )
    List<PgimInstanciaPasoDTO> obtenerPasosActualesPorInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);

    /**
     * Permite obtener el listado de pasos actuales.
     * 
     * @param idInstanciaPaso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
            + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
            + "peeo.idEquipoInstanciaPro, peed.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
            + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
            + "inpa.tipoSubflujo.idValorParametro) " 
            + "FROM PgimInstanciaPaso inpa " 
            + "     INNER JOIN inpa.personaEqpOrigen peeo " 
            + "     INNER JOIN inpa.personaEqpDestino peed "
            + "WHERE 1 = 1 "
            + "AND inpa.flEsPasoActivo = '1' "
            + "AND inpa.idInstanciaPaso = :idInstanciaPaso "
            + "AND inpa.esRegistro = '1' "
            + "AND inpa.esRegistro = '1' "
            )
    List<PgimInstanciaPasoDTO> obtenerInstanciaPaso(@Param("idInstanciaPaso") Long idInstanciaPaso);    

    /**
     * Permite obtener la instancia de paso.
     * 
     * @param idInstanciaPaso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
            + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
            + "inpa.personaEqpOrigen.idEquipoInstanciaPro, inpa.personaEqpDestino.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
            + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
            + "inpa.tipoSubflujo.idValorParametro) " 
            + "FROM PgimInstanciaPaso inpa " 
            + "WHERE inpa.esRegistro = '1' "
            + "AND inpa.idInstanciaPaso = :idInstanciaPaso")
    PgimInstanciaPasoDTO obtenerInstanciaPasoPorId(@Param("idInstanciaPaso") Long idInstanciaPaso);

    /**
     * Permite obtener la instancia de paso.
     * 
     * @param idInstanciaPaso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
            + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
            + "inpa.personaEqpOrigen.idEquipoInstanciaPro, inpa.personaEqpDestino.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
            + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
            + "inpa.tipoSubflujo.idValorParametro) " 
            + "FROM PgimInstanciaPaso inpa " 
            + "WHERE inpa.esRegistro = '1' "
            + "AND inpa.idInstanciaPaso = :idInstanciaPaso")
    PgimInstanciaPasoDTO obtenerInstanciaPasoDescriptivoPorId(@Param("idInstanciaPaso") Long idInstanciaPaso);
    
    /**
     * Permite obtener el listado de pasos x Relación de paso.
     * 
     * @param idInstanciaProceso
     * @param idRelacionPaso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
            + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
            + "inpa.personaEqpOrigen.idEquipoInstanciaPro, inpa.personaEqpDestino.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
            + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
            + "inpa.tipoSubflujo.idValorParametro) " 
            + "FROM PgimInstanciaPaso inpa " 
            + "WHERE inpa.esRegistro = '1' "
            + "AND inpa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            + "AND inpa.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
            + "ORDER BY inpa.feInstanciaPaso DESC")
    List<PgimInstanciaPasoDTO> obtenerInstanciaPasosXrelacion(@Param("idInstanciaProceso") Long idInstanciaProceso,@Param("idRelacionPaso") Long idRelacionPaso);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTOResultado( "
        + "inpa.idInstanciaPaso, inpa.pgimInstanciaProces.idInstanciaProceso, inpa.pgimRelacionPaso.idRelacionPaso, "
        + "inpa.personaEqpOrigen.idEquipoInstanciaPro, inpa.personaEqpDestino.idEquipoInstanciaPro, inpa.feInstanciaPaso, "
        + "inpa.deMensaje, inpa.pasoProcesoOrigen.idPasoProceso, inpa.pasoProcesoDestino.idPasoProceso, "
        + "inpa.tipoSubflujo.idValorParametro) " 
        + "FROM PgimInstanciaPaso inpa " 
        + "WHERE inpa.esRegistro = '1' "
        + "AND inpa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
        + "AND inpa.flEsPasoActivo = '1' "
        + "ORDER BY inpa.idInstanciaPaso ASC "
        )
        List<PgimInstanciaPasoDTO> obtenerInstanciaPasosActualesPorIdInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);

    

}