package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIprocesoAlerta;

import java.util.List;

/**
 * Interface IprocesoAlertaRepository
 * 
 * @descripción: Lógica de negocio de la gestión del alertas de instancia de proceso
 * 
 * @author: palominovega
 * @version: 1.0 
 * @fecha_de_creación: 16/12/2022 
 */
@Repository
public interface IprocesoAlertaRepository extends JpaRepository<PgimIprocesoAlerta, Long> { 

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, "
    + "ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, ipa.flActivo, "
    + "ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, ipa.feIntervalo3Final ) "
    + "FROM PgimIprocesoAlerta ipa " 
    + "WHERE ipa.esRegistro = '1' "
    + "AND ipa.flActivo = '1' "
    + "AND ipa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
    + "AND ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta IN (:idTipoProcesoAlerta, :idTipoProcesoAlertaPadre) ")
  PgimIprocesoAlertaDTO obtenerIProcesoAlerta(@Param("idInstanciaProceso") Long idInstanciaProceso, 
    @Param("idTipoProcesoAlerta") Long idTipoProcesoAlerta, 
    @Param("idTipoProcesoAlertaPadre") Long idTipoProcesoAlertaPadre);

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, "
    + "ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, ipa.flActivo, "
    + "ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, ipa.feIntervalo3Final ) "
    + "FROM PgimIprocesoAlerta ipa " 
    + "WHERE ipa.esRegistro = '1' "
    + "AND ipa.flActivo = '1' "
    + "AND TRUNC( SYSDATE ) >= TRUNC( ipa.feIntervalo1Inicial ) "
    + "AND ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta IN ( pe.gob.osinergmin.pgim.utils.ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD, "
    +   "pe.gob.osinergmin.pgim.utils.ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD_AMPLIACION, "
    +   "pe.gob.osinergmin.pgim.utils.ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR, "
    +   "pe.gob.osinergmin.pgim.utils.ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR_AMPLIACION) "
    + "ORDER BY ipa.feIntervalo3Final ASC "
    )
  List<PgimIprocesoAlertaDTO> obtenerIProcesoAlertaActivosSemanal();

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, "
    + "ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, ipa.flActivo, "
    + "ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, ipa.feIntervalo3Final ) "
    + "FROM PgimIprocesoAlerta ipa " 
    + "WHERE ipa.esRegistro = '1' "
    + "AND ipa.flActivo = '1' "
    + "AND TRUNC( SYSDATE ) >= TRUNC( ipa.feIntervalo1Inicial ) "
    + "AND ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta IN ( pe.gob.osinergmin.pgim.utils.ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_REVISION_INFORME, "
    +   "pe.gob.osinergmin.pgim.utils.ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFORME ) "
    + "ORDER BY ipa.feIntervalo3Final ASC "
    )
  List<PgimIprocesoAlertaDTO> obtenerIProcesoAlertaActivosDiario();

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, "
    + "ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, "
    + "ipa.flActivo, ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, "
    + "ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, "
    + "ipa.feIntervalo3Final, ipaux.noEtiquetaOtrabajo, ipa.pgimTipoProcesoAlerta.noTipoProcesoAlerta, ipa.pgimInstanciaProces.pgimProceso.idProceso||'-'||ipa.pgimInstanciaProces.coTablaInstancia) "
    + "FROM PgimIprocesoAlerta ipa "
    + "INNER JOIN PgimInstanPasoAux ipaux ON ( ipa.pgimInstanciaProces.idInstanciaProceso = ipaux.idInstanciaProceso) "
    + "INNER JOIN PgimInstanciaPaso ipaso ON ( ipaux.idInstanciaPaso = ipaso.idInstanciaPaso) "
    + "WHERE ipa.esRegistro = '1' "
    + "AND ipa.flActivo = '1' "
    + "AND ipaso.flEsPasoActivo = '1' "
    + "AND TRUNC( SYSDATE ) >= TRUNC( ipa.feIntervalo1Inicial ) "
    + "AND LOWER(ipaux.noUsuarioDestino) = LOWER(:noUsuarioDestino) "
    )
  Page<PgimIprocesoAlertaDTO> listarAlertasCumpleByNoUsuarioDestino(@Param("noUsuarioDestino") String noUsuarioDestino,
    Pageable paginador);

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, "
    + "ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, "
    + "ipa.flActivo, ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, "
    + "ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, "
    + "ipa.feIntervalo3Final, ipa.pgimTipoProcesoAlerta.noTipoProcesoAlerta, aler.noAlerta, "
    + "nvl(aler.deAlerta, 'Alerta de cumplimiento, vence el '|| TO_CHAR(ipa.feIntervalo3Final, 'dd/mm/yyyy')), '' "
    + " ) "
    + "FROM PgimIprocesoAlerta ipa "
    + "LEFT JOIN PgimAlerta aler ON ( ipa = aler.pgimIprocesoAlerta "
    +   "AND aler.esRegistro = '1' AND aler.idAlerta = (SELECT MAX(alert.idAlerta) FROM PgimAlerta alert WHERE alert.pgimIprocesoAlerta = ipa) ) "
    + "WHERE ipa.esRegistro = '1' "
    + "AND ipa.flActivo = '1' "
    + "AND TRUNC( SYSDATE ) >= TRUNC( ipa.feIntervalo1Inicial ) "
    + "AND ipa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
    + "ORDER BY ipa.feIntervalo3Final ASC "
    )
  List<PgimIprocesoAlertaDTO> listarIProcesoAlertaPorIdInstancia(@Param("idInstanciaProceso") Long idInstanciaProceso);
  
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, "
    + "ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, ipa.flActivo, "
    + "ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, ipa.feIntervalo3Final ) "
    + "FROM PgimIprocesoAlerta ipa " 
    + "WHERE ipa.esRegistro = '1' "
    + "AND ipa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
    + "AND ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta IN (:idTipoProcesoAlerta, :idTipoProcesoAlertaPadre) ")
  List<PgimIprocesoAlertaDTO> existeIProcesoAlerta(@Param("idInstanciaProceso") Long idInstanciaProceso, 
    @Param("idTipoProcesoAlerta") Long idTipoProcesoAlerta, 
    @Param("idTipoProcesoAlertaPadre") Long idTipoProcesoAlertaPadre);

  
  
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTOResultado( ipa.idIprocesoAlerta, ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta, "
		    + "ipa.iprocesoAlertaPadre.idIprocesoAlerta, ipa.pgimInstanciaProces.idInstanciaProceso, ipa.flActivo, "
		    + "ipa.feIntervalo1Inicial, ipa.feIntervalo1Final, ipa.feIntervalo2Inicial, ipa.feIntervalo2Final, ipa.feIntervalo3Inicial, ipa.feIntervalo3Final ) "
		    + "FROM PgimIprocesoAlerta ipa " 
		    + "WHERE ipa.esRegistro = '1' "
		    + "AND ipa.flActivo = '1' "
		    + "AND ipa.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
		    + "AND ipa.pgimTipoProcesoAlerta.idTipoProcesoAlerta IN (:idTipoProcesoAlerta, :idTipoProcesoAlertaPadre) ")
  List<PgimIprocesoAlertaDTO> obtenerListaIProcesoAlerta(@Param("idInstanciaProceso") Long idInstanciaProceso, 
		    @Param("idTipoProcesoAlerta") Long idTipoProcesoAlerta, 
		    @Param("idTipoProcesoAlertaPadre") Long idTipoProcesoAlertaPadre);
  
  
  
}
