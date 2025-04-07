package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstrmntoXSuperv;

import java.util.List;


/**
 * Ésta interface SupervisionRepository nos permite mostrar la supervisión
 * 
 * @descripción: Logica de negocio de la entidad Supervisión
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/08/2022
 */
@Repository
public interface SupervInstrumentoRepository extends JpaRepository<PgimInstrmntoXSuperv, Long>{
  
  /**
   * Permite listar los instrumentos de una supervisión
   * @param idSupervision
   * @param paginador
   * @return
   */
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
    + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
    + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
    + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
    + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
    + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion, "
    + "inpaux.noPasoProcesoDestino, inreem.idInstrmntoXSuperv, "
    + "case when inreem.idInstrmntoXSuperv is not null then (inreem.coInstrumento||' - '||tiinree.noTipoInstrumento) else '' end,  "
    + "case when inreemaux.idInstrmntoXSuperv is not null then (inreemaux.coInstrumento||' - '||tiinreeaux.noTipoInstrumento) else '' end, "
    + "inreemaux.idInstrmntoXSuperv "
    + ") "
    + "FROM PgimInstrmntoXSuperv insu "
    + "INNER JOIN insu.pgimTipoInstrumento tiin "
    + "INNER JOIN insu.pgimSupervision supe "
    + "INNER JOIN insu.estadoInstrumento esin "
    + "LEFT JOIN insu.instrmntoXSupervRmplzo inreem "
    + "LEFT JOIN inreem.pgimTipoInstrumento tiinree "
    + "LEFT JOIN insu.pgimInstanciaPaso inpa "
    + "LEFT JOIN PgimInstanPasoAux inpaux ON (inpa.idInstanciaPaso = inpaux.idInstanciaPaso) "
    + "LEFT JOIN PgimInstrmntoXSuperv inreemaux ON (insu.idInstrmntoXSuperv = inreemaux.instrmntoXSupervRmplzo.idInstrmntoXSuperv)"
    + "LEFT JOIN inreemaux.pgimTipoInstrumento tiinreeaux "
    + "WHERE supe.idSupervision = :idSupervision "
    + "AND insu.esRegistro = '1' "
    + "AND tiin.esRegistro = '1' "
    + "AND supe.esRegistro = '1' "
    + "AND esin.esRegistro = '1' "
    )
  Page<PgimInstrmntoXSupervDTO> listar(@Param("idSupervision") Long idSupervision, Pageable paginador);

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
    + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
    + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
    + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
    + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
    + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion, "
    + "inpaux.noPasoProcesoDestino, inreem.idInstrmntoXSuperv, "
    + "case when inreem.idInstrmntoXSuperv is not null then (inreem.coInstrumento||' - '||tiinree.noTipoInstrumento) else '' end,  "
    + "case when inreemaux.idInstrmntoXSuperv is not null then (inreemaux.coInstrumento||' - '||tiinreeaux.noTipoInstrumento) else '' end, "
    + "inreemaux.idInstrmntoXSuperv "
    + ") "
    + "FROM PgimInstrmntoXSuperv insu "
    + "INNER JOIN insu.pgimTipoInstrumento tiin "
    + "INNER JOIN insu.pgimSupervision supe "
    + "INNER JOIN insu.estadoInstrumento esin "
    + "LEFT JOIN insu.instrmntoXSupervRmplzo inreem "
    + "LEFT JOIN inreem.pgimTipoInstrumento tiinree "
    + "LEFT JOIN insu.pgimInstanciaPaso inpa "
    + "LEFT JOIN PgimInstanPasoAux inpaux ON (inpa.idInstanciaPaso = inpaux.idInstanciaPaso) "
    + "LEFT JOIN PgimInstrmntoXSuperv inreemaux ON (insu.idInstrmntoXSuperv = inreemaux.instrmntoXSupervRmplzo.idInstrmntoXSuperv)"
    + "LEFT JOIN inreemaux.pgimTipoInstrumento tiinreeaux "
    + "WHERE supe.coSupervision LIKE :coSupervision "
    + "AND insu.esRegistro = '1' "
    + "AND tiin.esRegistro = '1' "
    + "AND supe.esRegistro = '1' "
    + "AND esin.esRegistro = '1' "
    + "AND esin.coClaveTexto IN (:coEstadoRegistrado, :coEstadoAprobado, :coEstadoModificado )"
    )
  List<PgimInstrmntoXSupervDTO> listarInstXsuperv(
    @Param("coSupervision") String coSupervision,
    @Param("coEstadoRegistrado") String coEstadoRegistrado,
    @Param("coEstadoAprobado") String coEstadoAprobado, 
    @Param("coEstadoModificado") String coEstadoModificado);
  
  
  /**
   * Permite listar los instrumentos de una supervisión
   * @param idSupervision
   * @return
   */
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
    + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
    + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
    + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
    + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
    + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion "
    + ") "
    + "FROM PgimInstrmntoXSuperv insu "
    + "INNER JOIN insu.pgimTipoInstrumento tiin "
    + "INNER JOIN insu.pgimSupervision supe "
    + "INNER JOIN insu.estadoInstrumento esin "
    + "WHERE supe.idSupervision = :idSupervision "
    + "AND insu.esRegistro = '1' "
    + "AND tiin.esRegistro = '1' "
    + "AND supe.esRegistro = '1' "
    + "AND esin.esRegistro = '1' "
    )
  List<PgimInstrmntoXSupervDTO> listarAll(@Param("idSupervision") Long idSupervision);

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
    + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
    + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
    + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
    + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
    + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion "
    + ") "
    + "FROM PgimInstrmntoXSuperv insu "
    + "INNER JOIN insu.pgimTipoInstrumento tiin "
    + "INNER JOIN insu.pgimSupervision supe "
    + "INNER JOIN insu.estadoInstrumento esin "
    + "WHERE supe.idSupervision = :idSupervision "
    + "AND insu.esRegistro = '1' "
    + "AND tiin.esRegistro = '1' "
    + "AND supe.esRegistro = '1' "
    + "AND esin.esRegistro = '1' "
    + "AND esin.coClaveTexto IN (:coEstadoRegistrado, :coEstadoAprobado, :coEstadoModificado )"
    )
  List<PgimInstrmntoXSupervDTO> ObtenerListaInstrumentoRegAprob(@Param("idSupervision") Long idSupervision,
  @Param("coEstadoRegistrado") String coEstadoRegistrado,
   @Param("coEstadoAprobado") String coEstadoAprobado, 
   @Param("coEstadoModificado") String coEstadoModificado
   );

  /**
   * Permite obtener un determinado instrumento medición por su ID
   * @param idInstrmntoXSuperv
   * @return
   */
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
    + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
    + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
    + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
    + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
    + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion, "
    + "inpaux.noPasoProcesoDestino, inreem.idInstrmntoXSuperv, "
    + "case when inreem.idInstrmntoXSuperv is not null then (inreem.coInstrumento||' - '||tiinree.noTipoInstrumento) else '' end,  "
    + "case when inreemaux.idInstrmntoXSuperv is not null then (inreemaux.coInstrumento||' - '||tiinreeaux.noTipoInstrumento) else '' end, "
    + "inreemaux.idInstrmntoXSuperv "
    + ") "
    + "FROM PgimInstrmntoXSuperv insu "
    + "INNER JOIN insu.pgimTipoInstrumento tiin "
    + "INNER JOIN insu.pgimSupervision supe "
    + "INNER JOIN insu.estadoInstrumento esin "
    + "LEFT JOIN insu.instrmntoXSupervRmplzo inreem "
    + "LEFT JOIN inreem.pgimTipoInstrumento tiinree "
    + "LEFT JOIN insu.pgimInstanciaPaso inpa "
    + "LEFT JOIN PgimInstanPasoAux inpaux ON (inpa.idInstanciaPaso = inpaux.idInstanciaPaso) "
    + "LEFT JOIN PgimInstrmntoXSuperv inreemaux ON (insu.idInstrmntoXSuperv = inreemaux.instrmntoXSupervRmplzo.idInstrmntoXSuperv)"
    + "LEFT JOIN inreemaux.pgimTipoInstrumento tiinreeaux "
    + "WHERE insu.idInstrmntoXSuperv = :idInstrmntoXSuperv "
    + "AND insu.esRegistro = '1' "
    + "AND tiin.esRegistro = '1' "
    + "AND supe.esRegistro = '1' "
    + "AND esin.esRegistro = '1' ")
  PgimInstrmntoXSupervDTO obtenerInstrmntoXSupervDTOPorId(@Param("idInstrmntoXSuperv") Long idInstrmntoXSuperv);

  /**
   * permite obtener el listado de los tipos de intrumentos que pertencen a un determinado contrato
   * @param idContrato
   * @return
   */
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTOResultado( "
    + "tiin.idTipoInstrumento, espe.idEspecialidad, tiin.coTipoInstrumento, "
    + "tiin.noTipoInstrumento "
    + " ) "
    + "FROM PgimTipoInstrumento tiin "
    + "INNER JOIN tiin.pgimEspecialidad espe "
    + "WHERE 1 = 1 "
    + "AND EXISTS ( "
    + "SELECT 1 "
    + "FROM PgimTipopameXContrato tpco "
    + "INNER JOIN tpco.pgimContrato cont "
    + "INNER JOIN tpco.pgimTprmtroXTinstrmnto tpti "
    + "INNER JOIN tpti.pgimTipoInstrumento tiini "
    + "WHERE tiini.idTipoInstrumento = tiin.idTipoInstrumento "
    + "AND cont.idContrato = :idContrato "
    + "AND tpco.esRegistro = '1' "
    + "AND cont.esRegistro = '1' "
    + "AND tpti.esRegistro = '1' "
    + "AND tiini.esRegistro = '1' "
    + ") "
    + "AND tiin.esRegistro = '1' "
    )
  List<PgimTipoInstrumentoDTO> obtenerTipoInstrumentoPorIdContrato(@Param("idContrato") Long idContrato);

  /**
   * Permite obtener el listado de los parametros de mediciones que pertenecen a un tipo de instrumento por un contrato
   * @param idContrato
   * @return
   */
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTOResultado( "
    + "tpco.idTipopameXContrato, tpti.idTprmtroXTinstrmnto, cont.idContrato, "
    + "tpco.deRangoMedicion, tpco.dePrecision, tpco.deExactitud, "
    + "tpco.deResolucion, tipa.noTipoParametro, tiin.idTipoInstrumento "
    + " ) "
    + "FROM PgimTipopameXContrato tpco "
    + "INNER JOIN tpco.pgimContrato cont "
    + "INNER JOIN tpco.pgimTprmtroXTinstrmnto tpti "
    + "INNER JOIN tpti.pgimTipoParametroMed tipa "
    + "INNER JOIN tpti.pgimTipoInstrumento tiin "
    + "WHERE cont.idContrato = :idContrato "
    + "AND tpco.esRegistro = '1' "
    + "AND cont.esRegistro = '1' "
    + "AND tpti.esRegistro = '1' "
    + "AND tipa.esRegistro = '1' "
    + "AND tiin.esRegistro = '1' "
    )
  List<PgimTipopameXContratoDTO> obtenerTipoParamPorIdContrato(@Param("idContrato") Long idContrato);
  
  /**
   * Permite obtener la cantidad de instrumentos que tiene una supervisión,
   * en determinado estado o todos los estados
   * 
   * @param idSupervision
   * @param idEstado
   * @return
   */
  @Query("SELECT count(insu.idInstrmntoXSuperv) " 
          + "FROM PgimInstrmntoXSuperv insu "
          + "WHERE insu.esRegistro = '1' "
          + "AND insu.pgimSupervision.idSupervision = :idSupervision "
          + "AND (:idEstado IS NULL OR (insu.estadoInstrumento.idValorParametro = :idEstado)) "
          )
  Integer cantidadInstrumentos(
		  @Param("idSupervision") Long idSupervision,
		  @Param("idEstado") Long idEstado		  
		  );

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
      + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
      + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
      + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
      + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
      + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion "
      + ") "
      + "FROM PgimInstrmntoXSuperv insu "
      + "INNER JOIN insu.pgimTipoInstrumento tiin "
      + "INNER JOIN insu.pgimSupervision supe "
      + "INNER JOIN insu.estadoInstrumento esin "
      + "WHERE supe.idSupervision = :idSupervision "
      + "AND insu.esRegistro = '1' "
      + "AND tiin.esRegistro = '1' "
      + "AND supe.esRegistro = '1' "
      + "AND esin.esRegistro = '1' "
      + "AND esin.coClaveTexto IN (:coEstadoRegistrado, :coEstadoModificado, :coEstadoNoDisponible )"
      )
  Page<PgimInstrmntoXSupervDTO> listarInstrumentoParaAprobar(@Param("idSupervision") Long idSupervision,
  @Param("coEstadoRegistrado") String coEstadoRegistrado, @Param("coEstadoModificado") String coEstadoModificado,  @Param("coEstadoNoDisponible") String coEstadoNoDisponible, Pageable paginador );

  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTOResultado( "
     + "insu.idInstrmntoXSuperv, tiin.idTipoInstrumento, supe.idSupervision, "
     + "esin.idValorParametro, insu.coInstrumento, insu.coSerieInstrumento, "
     + "insu.noMarcaInstrumento, insu.noModeloInstrumento, insu.feCalibracion, "
     + "insu.feVencimientoCalibracion, tiin.noTipoInstrumento, esin.noValorParametro, esin.coClaveTexto, "
     + "insu.coCertificadoCalibracion, insu.noLaboratorioCalibracion "
     + ") "
     + "FROM PgimInstrmntoXSuperv insu "
     + "INNER JOIN insu.pgimTipoInstrumento tiin "
     + "INNER JOIN insu.pgimSupervision supe "
     + "INNER JOIN insu.estadoInstrumento esin "
     + "WHERE supe.idSupervision = :idSupervision "
     + "AND insu.esRegistro = '1' "
     + "AND tiin.esRegistro = '1' "
     + "AND supe.esRegistro = '1' "
     + "AND esin.esRegistro = '1' "
     + "AND esin.coClaveTexto IN (:coEstadoRegistrado, :coEstadoModificado, :coEstadoNoDisponible )"
     )
  List<PgimInstrmntoXSupervDTO> obtenerListarInstrumentoParaAprobar(@Param("idSupervision") Long idSupervision,
    @Param("coEstadoRegistrado") String coEstadoRegistrado, @Param("coEstadoModificado") String coEstadoModificado, @Param("coEstadoNoDisponible") String coEstadoNoDisponible );
}
