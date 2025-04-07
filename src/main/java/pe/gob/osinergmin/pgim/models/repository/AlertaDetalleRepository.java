package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAlertaDetalle;

/**
 * Interface AlertaRepository
 * 
 * @descripción: Lógica de negocio de la gestión del detalle de alertas
 * 
 * @author: HRUIZ
 * @version: 1.0 @fecha_de_creación: 02/09/2020 @fecha_de_ultima_actualización:
 *           02/09/2020
 */
@Repository
public interface AlertaDetalleRepository extends JpaRepository<PgimAlertaDetalle, Long> {

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTOResultado("
                        + "pad.idDetalleAlerta, pad.pgimAlerta.idAlerta, pad.tipoDetalleAlerta.idValorParametro, "
                        + "pad.noUsuarioDestino, pad.flLeido, pad.deDetalleAlerta) "
                        + "FROM PgimAlertaDetalle pad " 
                        + "WHERE pad.esRegistro = '1' "
                        + "AND pad.pgimAlerta.idAlerta = :idAlerta ")
        PgimAlertaDetalleDTO obtenerAlertaDetalleByIdAlerta(@Param("idAlerta") Long idAlerta);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTOResultado("
                        + "alde.idDetalleAlerta, aler.idAlerta, vapa.idValorParametro, "
                        + "vapa.noValorParametro, alde.noUsuarioDestino, alde.flLeido, "
                        + "alde.deDetalleAlerta, aler.noAlerta, aler.deAlerta, " 
                        + "aler.feAlerta, aler.diEnlace, avapa.idValorParametro, avapa.noValorParametro, " 
                        + "fase.idFaseProceso, fase.noFaseProceso, paso.idPasoProceso, paso.noPasoProceso, " 
                        + "proc.idProceso " 
                        + ") "
                        + "FROM PgimAlertaDetalle alde " 
                        + "INNER JOIN alde.pgimAlerta aler "
                        + "INNER JOIN alde.tipoDetalleAlerta vapa "
                        + "INNER JOIN alde.pgimAlerta.tipoAlerta avapa "

                        + "INNER JOIN alde.pgimAlerta.pgimInstanciaPaso ipaso "
                        + "INNER JOIN alde.pgimAlerta.pgimInstanciaPaso.pgimRelacionPaso rpaso "
                        + "INNER JOIN alde.pgimAlerta.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino paso "
                        + "INNER JOIN alde.pgimAlerta.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso fase "
                        + "INNER JOIN alde.pgimAlerta.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.pgimProceso proc "

                        + "WHERE aler.esRegistro = '1' "
                        + "AND alde.esRegistro = '1' " 
                        
                        + "AND LOWER(alde.noUsuarioDestino) = LOWER(:noUsuarioDestino) "
                        + "AND (:idProceso IS NULL OR proc.idProceso = :idProceso ) "
                        + "AND (:idFaseProceso IS NULL OR fase.idFaseProceso = :idFaseProceso ) "
                        + "AND (:idPasoProceso IS NULL OR paso.idPasoProceso = :idPasoProceso) "
                        + "AND (:flLeido IS NULL OR alde.flLeido = :flLeido) "
                        + "AND (:descIdTipoAlerta IS NULL OR avapa.idValorParametro = :descIdTipoAlerta) "
                        )
        Page<PgimAlertaDetalleDTO> listarAlertasByNoUsuarioDestino(
                @Param("idProceso") Long idProceso, 
                @Param("idFaseProceso") Long idFaseProceso, 
                @Param("idPasoProceso") Long idPasoProceso,
                @Param("flLeido") String flLeido,
                @Param("descIdTipoAlerta") Long descIdTipoAlerta,
                @Param("noUsuarioDestino") String noUsuarioDestino,
                Pageable paginador);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTOResultado("
                        + "COUNT(1) "
                        + ") "
                        + "FROM PgimAlertaDetalle alde " 
                        + "INNER JOIN alde.pgimAlerta aler "
                        + "INNER JOIN alde.tipoDetalleAlerta vapa "
                        + "INNER JOIN alde.pgimAlerta.tipoAlerta avapa "
                        + "INNER JOIN alde.pgimAlerta.pgimInstanciaPaso ipaso "
                        + "WHERE aler.esRegistro = '1' "
                        + "AND alde.esRegistro = '1' "
                        + "AND alde.flLeido = '0' "
                        + "AND LOWER(alde.noUsuarioDestino) = LOWER(:noUsuarioDestino) "
                        )
        PgimAlertaDetalleDTO contarAlertasPendientes(@Param("noUsuarioDestino") String noUsuarioDestino);
        
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTOResultado("
                + "pad.idDetalleAlerta, pad.pgimAlerta.idAlerta, pad.tipoDetalleAlerta.idValorParametro, "
                + "pad.noUsuarioDestino, pad.flLeido, pad.deDetalleAlerta) "
                + "FROM PgimAlertaDetalle pad " 
                + "WHERE pad.esRegistro = '1' "
                + "AND pad.idDetalleAlerta = :idDetalleAlerta ")
        PgimAlertaDetalleDTO obtenerAlertaDetalleById(@Param("idDetalleAlerta") Long idDetalleAlerta);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTOResultado("
                + "pad.idDetalleAlerta, pad.pgimAlerta.idAlerta, pad.pgimAlerta.pgimInstanciaPaso.idInstanciaPaso, " 
                + "pad.flLeido, pad.pgimAlerta.tipoAlerta.idValorParametro "
                +") "
                + "FROM PgimAlertaDetalle pad " 
                + "WHERE pad.esRegistro = '1' "
                + "AND pad.pgimAlerta.pgimInstanciaPaso.idInstanciaPaso = :idInstanciaPaso "
                + "AND pad.pgimAlerta.tipoAlerta.idValorParametro = :idTipoAlerta "
                )
        PgimAlertaDetalleDTO obtenerAlertaDetalleByInstanciaPaso(@Param("idInstanciaPaso") Long idInstanciaPaso, @Param("idTipoAlerta") Long idTipoAlerta);

}
