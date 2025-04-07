package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimHechoConstatado;

/**
 * Esta interface HechoConstatadoRepository esta conformada por los métodos:
 * listar hechos constatados
 * 
 * @descripción: Lógica de negocio de la entidad hecho constatado
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 06/10/2020
 * 
 */
@Repository
public interface HechoConstatadoRepository extends JpaRepository<PgimHechoConstatado, Long> {

        /**
         * Permite obtener la lista hechos constatados por el identificador de la
         * supervisión, se usa específicamente para obtener lo registrado por el usuario
         * con el rol supervisor
         * 
         * @param idSupervision
         * @param rol
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoResultado("
                        + "phc.idHechoConstatado, ponaux.deObligacionNormativa, ponaux.deNormaItem, phc.tipoCumplimiento.noValorParametro) "
                        + "FROM PgimHechoConstatado phc inner join PgimRolProceso prp "
                        + "on phc.pgimRolProceso.idRolProceso = prp.idRolProceso "
                        + "LEFT JOIN PgimOblgcnNrmtvaHchoc ponh on (phc.idHechoConstatado = ponh.pgimHechoConstatado.idHechoConstatado AND ponh.esRegistro = '1' )"
                        + "left join PgimObligacionNormaAux ponaux on ponh.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio = ponaux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio "
                        + "WHERE phc.esRegistro = '1' AND ponh.esAplica = '1' AND phc.esVigente = 'V' AND prp.idRolProceso = :rol "
                        + "AND phc.pgimSupervision.idSupervision = :idSupervision ")
        List<PgimHechoConstatadoDTO> obtenerHechosConstatadosIncumplimiento(@Param("idSupervision") Long idSupervision,
                        @Param("rol") Long rol);

        /**
         * Permite obtener la lista hechos constatados por el identificador de la
         * supervisión, se usa específicamente para obtener lo registrado por los
         * usuario con el rol de especialista técnico y legal de Osinergmin
         * 
         * @param idSupervision
         * @param rol1
         * @param rol2
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoResultado("
                        + "phc.idHechoConstatado, ponaux.deObligacionNormativa, ponaux.deNormaItem, phc.tipoCumplimiento.noValorParametro ) "
                        + "FROM PgimHechoConstatado phc inner join PgimRolProceso prp "
                        + "on phc.pgimRolProceso.idRolProceso = prp.idRolProceso "
                        + "LEFT JOIN PgimOblgcnNrmtvaHchoc ponh on (phc.idHechoConstatado = ponh.pgimHechoConstatado.idHechoConstatado AND ponh.esRegistro = '1') "
                        + "left join PgimObligacionNormaAux ponaux on ponh.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio = ponaux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio "
                        + "WHERE phc.esRegistro = '1' AND ponh.esAplica = '1' AND phc.esVigente = 'V' AND prp.idRolProceso in (:rol1, :rol2) "
                        + "AND phc.pgimSupervision.idSupervision = :idSupervision ")
        List<PgimHechoConstatadoDTO> obtenerHechosConstatadosIncumplimiento(@Param("idSupervision") Long idSupervision,
                        @Param("rol1") Long rol1, @Param("rol2") Long rol2);

        /**
         * Permite obtener la lista hechos constatados por el identificador de la
         * supervisión, se usa específicamente para obtener lo registrado por los
         * usuario con el rol de especialista técnico y legal de Osinergmin y mostrarlos
         * en la fichas de instrucción preliminar
         * 
         * @param idSupervision
         * @param rol1
         * @param rol2
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoResultado("
                        + "phc.idHechoConstatado, '', '', phc.tipoCumplimiento.noValorParametro ) "
                        + "FROM PgimHechoConstatado phc inner join PgimRolProceso prp "
                        + "on phc.pgimRolProceso.idRolProceso = prp.idRolProceso "
                        + "WHERE phc.esRegistro = '1' AND phc.esVigente = 'V' AND phc.tipoCumplimiento.idValorParametro IN (307,308) AND prp.idRolProceso in (:rol1, :rol2) "
                        + "AND phc.pgimSupervision.idSupervision = :idSupervision ")
        List<PgimHechoConstatadoDTO> obtenerHechosConstatadosFicha(@Param("idSupervision") Long idSupervision,
                        @Param("rol1") Long rol1, @Param("rol2") Long rol2);

        /**
         * Permite obtener la lista hechos constatados por criterio de la matriz de
         * supervisión
         * 
         * @param idCriterioSprvsion
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, "  
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision " 
                        + ")  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.pgimCriterioSprvsion.idCriterioSprvsion = :idCriterioSprvsion "
                        + "AND hc.esRegistro = '1' " 
                        + "AND hc.esVigente = 'V' "

                        + "AND (:codTipoCumplimiento=0L OR hc.tipoCumplimiento.coClave=:codTipoCumplimiento) "

                        + "AND (:idRolProceso=0L OR hc.pgimRolProceso.idRolProceso = :idRolProceso) " // Si
                                                                                                      // ID_ROL_PROCESO
                                                                                                      // = 0 --> se
                                                                                                      // muestra todos
                                                                                                      // los HC vigentes
                        + "AND (:idRolProceso=4L OR hc.hechoConstatadoRmplznte.idHechoConstatado is null) " // Si ID_ROL_PROCESO = 4 --> Supervisor/a
                                                                                                            // sino, otros que no son del Supervisor/a

                        + "ORDER BY hc.idHechoConstatado asc ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosPorCriterioMatriz(
                        @Param("idCriterioSprvsion") Long idCriterioSprvsion,
                        @Param("codTipoCumplimiento") Long codTipoCumplimiento,
                        @Param("idRolProceso") Long idRolProceso);
        
        
        
        /**
         * Permite obtener la lista hechos constatados histórico por fase, y por criterio de la matriz de
         * supervisión
         * 
         * @param idCriterioSprvsion
         * @param idFase
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, "                        
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision " 
                        + ")  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.idHechoConstatado IN "
                        + "(SELECT NVL(hhf.idHechoConstatadoOsi , hhf.idHechoConstatado) FROM PgimHechoCnsttdoFase hhf WHERE hhf.idFaseMuestra =:idFase "
                        + "AND hhf.idCriterioSprvsion = :idCriterioSprvsion ) "                        
                        + "ORDER BY hc.idHechoConstatado asc ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosPorCriterioMatrizHistPorFase(
                        @Param("idCriterioSprvsion") Long idCriterioSprvsion,
                        @Param("idFase") Long idFase
        				);
        

        /**
         * Permite obtener un hecho constatado por ID
         * 
         * @param idHechoConstatado
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.deComentarioOsiT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision,hc.usCreacion,hc.feCreacion,hc.usActualizacion,hc.feActualizacion, "
                        + "hc.pgimRolProceso.noRolProceso, "
                        + "hc.pgimCriterioSprvsion.coMatrizCriterio,hc.pgimCriterioSprvsion.nuOrdenGrpoCrtrio,hc.pgimCriterioSprvsion.nuOrdenCriterio, " 
                        + "hc.tipoCumplimiento.coClave, hc.pgimCriterioSprvsion.deMatrizCriterio )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "where hc.idHechoConstatado = :idHechoConstatado " 
                        + "and hc.esRegistro = '1' "
                        )
        PgimHechoConstatadoDTO obtenerHechoConstatadoDtoPorId(@Param("idHechoConstatado") Long idHechoConstatado);

        /**
         * Permite obtener la lista hechos constatados por idHechoConstatadoRmplznte
         * 
         * @param idHechoConstatadoRmplznte
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, "  
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.hechoConstatadoRmplznte.idHechoConstatado = :idHechoConstatadoRmplznte "
                        + "AND hc.esRegistro = '1' " 
                        + "AND hc.esVigente = 'V' " 
                        + "ORDER BY hc.idHechoConstatado asc ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosPorHechoConstatadoRmplznte(
                        @Param("idHechoConstatadoRmplznte") Long idHechoConstatadoRmplznte);

        /**
         * Permite obtener la lista hechos constatados por ID de supervisión
         * 
         * @param idSupervision
         * @param idRolProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, "  
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.pgimSupervision.idSupervision = :idSupervision " 
                        + "AND hc.esRegistro = '1' "
                        + "AND hc.esVigente = 'V' "
                        + "AND (:idRolProceso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_HC_ROL_ALL "
                        + "OR hc.pgimRolProceso.idRolProceso = :idRolProceso) "
                        + "AND (:idRolProceso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_HC_ROL_SUPERVISOR "
                        + "OR hc.hechoConstatadoRmplznte.idHechoConstatado IS NULL) "
                        + "ORDER BY hc.pgimCriterioSprvsion.nuOrdenGrpoCrtrio, hc.pgimCriterioSprvsion.coMatrizCriterio ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosPorSupervision(@Param("idSupervision") Long idSupervision,                        
                        @Param("idRolProceso") Long idRolProceso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion, hc.pgimCriterioSprvsion.coMatrizCriterio, hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, " 
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.pgimSupervision.idSupervision = :idSupervision " 
                        + "AND hc.esRegistro = '1' "
                        + "AND hc.esVigente = 'V' "
                        + "AND hc.tipoCumplimiento.idValorParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_ID_NO_CUMPLE_TC "
                        + "AND (:idRolProceso=pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_HC_ROL_ALL "
                        + "OR hc.pgimRolProceso.idRolProceso = :idRolProceso) "
                        + "AND (:idRolProceso=pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_HC_ROL_SUPERVISOR "
                        + "OR hc.hechoConstatadoRmplznte.idHechoConstatado IS NULL) "
                        + "ORDER BY hc.pgimCriterioSprvsion.nuOrdenGrpoCrtrio, hc.pgimCriterioSprvsion.coMatrizCriterio ASC ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosConNoCumplimientos(@Param("idSupervision") Long idSupervision,                        
                        @Param("idRolProceso") Long idRolProceso);

        /**
         * Permite obtener la lista hechos constatados por ID de supervisión
         * 
         * @param idSupervision
         * @param idRolProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, " 
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.pgimSupervision.idSupervision = :idSupervision " 
                        + "AND hc.esRegistro = '1' "
                        + "AND hc.esVigente = 'V' "

                        // + "AND (:codTipoCumplimiento=0L OR
                        // hc.tipoCumplimiento.coClave=:codTipoCumplimiento) "

                        + "AND hc.pgimRolProceso.idRolProceso in (:idRolProceso1, :idRolProceso2) "
                        + "AND hc.hechoConstatadoRmplznte.idHechoConstatado is null "

                        + "ORDER BY hc.pgimCriterioSprvsion.nuOrdenGrpoCrtrio,hc.pgimCriterioSprvsion.coMatrizCriterio asc ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosPorSupervision(@Param("idSupervision") Long idSupervision,
                        // @Param("codTipoCumplimiento") Long codTipoCumplimiento,
                        @Param("idRolProceso1") Long idRolProceso1, @Param("idRolProceso2") Long idRolProceso2);
        
        /**
         * Permite obtener la lista de hechos constatados por ID de supervisión 
         * utilizado para la generación de las Fichas de evaluación de HV 
         * 
         * @param idSupervision
         * @param idRolProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, " 
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.pgimSupervision.idSupervision = :idSupervision " 
                        + "AND hc.esRegistro = '1' "
                        + "AND hc.esVigente = 'V' "

                        + "AND hc.pgimRolProceso.idRolProceso in (:idRolProceso1, :idRolProceso2) "
                        + "AND hc.hechoConstatadoRmplznte.idHechoConstatado is null "
                        
                        + "AND ( "
                        // Sea HV categorizado por el fiscalizador como "No cumple" (es reemplazante de un HV vigente tipo 'No cumple')
                        + "	EXISTS ( "
                        + "		SELECT 1 "
                        + "		FROM PgimHechoConstatado hc2 "
                        + "		WHERE 1 = 1"
                        + "		AND hc2.esRegistro = '1' "
                        + "		AND hc2.esVigente = 'V' "
                        + "		AND hc2.hechoConstatadoRmplznte.idHechoConstatado = hc.idHechoConstatado "
                        + "		AND hc2.tipoCumplimiento.idValorParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_ID_NO_CUMPLE_TC "
                        + "	)"
                        // O sea HV nuevo, registrado por especialista de Osinergmin (no es reemplazante de ningún otro HV). 
                        + "	OR NOT EXISTS ( "
                        + "		SELECT 1 "
                        + "		FROM PgimHechoConstatado hc3 "
                        + "		WHERE 1 = 1 "
                        + "		AND hc3.esRegistro = '1' "
                        + "		AND hc3.esVigente = 'V' "
                        + "		AND hc3.hechoConstatadoRmplznte.idHechoConstatado = hc.idHechoConstatado "
                        + "	)"
                        + ")"

                        + "ORDER BY hc.pgimCriterioSprvsion.nuOrdenGrpoCrtrio,hc.pgimCriterioSprvsion.coMatrizCriterio asc ")
        List<PgimHechoConstatadoDTO> listarHechosConstatadosPorSupervisionParaFEHV(@Param("idSupervision") Long idSupervision,
                        @Param("idRolProceso1") Long idRolProceso1, @Param("idRolProceso2") Long idRolProceso2);
        

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                        + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                        + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                        + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.pgimRolProceso.idRolProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                        + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.noPasoProceso, "
                        + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                        + "hc.flIncluidoActaSupervision )  " 
                        + "FROM PgimHechoConstatado hc "
                        + "WHERE hc.pgimSupervision.idSupervision = :idSupervision "
                        + "AND hc.tipoCumplimiento.idValorParametro = :idTipoCumplimiento " 
                        + "AND hc.pgimRolProceso.idRolProceso IN (pe.gob.osinergmin.pgim.utils.ConstRolSupervision.ESPECIALISTA_TECNICO, "
                        + "pe.gob.osinergmin.pgim.utils.ConstRolSupervision.ESPECIALISTA_LEGAL) "
                        + "AND hc.esRegistro = '1' "
                        + "AND hc.esVigente = 'V' "
                        + "ORDER BY hc.pgimCriterioSprvsion.nuOrdenGrpoCrtrio,hc.pgimCriterioSprvsion.coMatrizCriterio ASC ")
        List<PgimHechoConstatadoDTO> obtenerHechosConstatadosPorTipoCumplimiento(
                        @Param("idSupervision") Long idSupervision,
                        @Param("idTipoCumplimiento") Long idTipoCumplimiento);

        /**
         * Permite obtener una lista de PgimHechoConstatadoDTO de una fiscalización de acuerdo con su id de Criterio  
         * @param idSupervision
         * @param idCriterioSprvsion
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTOResultado "
                + "(hc.idHechoConstatado,hc.pgimCriterioSprvsion.idCriterioSprvsion,hc.pgimSupervision.idSupervision, "
                + "hc.pgimInstanciaPaso.idInstanciaPaso, hc.tipoCumplimiento.idValorParametro, hc.tipoCumplimiento.noValorParametro, "
                + "hc.deHechoConstatadoT, hc.deComplementoObservacion, hc.deSustentoT, hc.deComentarioOsiT, hc.pgimRolProceso.idRolProceso, "
                + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.idFaseProceso, "
                + "hc.pgimInstanciaPaso.pgimRelacionPaso.pasoProcesoDestino.pgimFaseProceso.noFaseProceso, "
                + "hc.esVigente,hc.hechoConstatadoRmplznte.idHechoConstatado,hc.hechoConstatadoOrigen.idHechoConstatado, "
                + "hc.flIncluidoActaSupervision,hc.usCreacion,hc.feCreacion,hc.usActualizacion,hc.feActualizacion, "
                + "hc.pgimRolProceso.noRolProceso " 
                + ")  " 
                + "FROM PgimHechoConstatado hc "
                + "where hc.pgimSupervision.idSupervision = :idSupervision " 
                + "and hc.pgimCriterioSprvsion.idCriterioSprvsion = :idCriterioSprvsion "
                + "and hc.esRegistro = '1' "
                )
        List<PgimHechoConstatadoDTO> obtenerLstHechosConstatadosDTO(
                @Param("idSupervision") Long idSupervision,
                @Param("idCriterioSprvsion") Long idCriterioSprvsion);
        
        
}