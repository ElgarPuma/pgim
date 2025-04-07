/**
 * 
 */
package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.*;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionAux;

import java.util.List;

/**
 * Ésta interface InfraccionAuxRepository incluye los metodos de listar y
 * paginar la Infracción
 * 
 * @descripción: Lógica de negocio de la entidad Infracción
 * 
 * @author Luis Barrantes
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 *
 */
@Repository
public interface InfraccionAuxRepository extends JpaRepository<PgimInfraccionAux, Long> {
	
		/**
		 * Obtiene datos de una infracción dada
		 * 
		 * @param idInfraccion
		 * @return
		 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTOResultado( "
            + "infaux.pgimInfraccion.idInfraccion, infaux.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc, infaux.pgimSupervision.idSupervision, "
            + "infaux.flIncluirEnPas, infaux.deSustento, infaux.feComisionDeteccion, "
            + "infaux.moMultaUit, infaux.pgimInstanciaPaso.idInstanciaPaso, infaux.pgimPas.idPas, "
            + "infaux.rucAs, infaux.noRazonSocialAs, infaux.coUnidadMinera, infaux.noUnidadMinera) "                 
            + "FROM PgimInfraccionAux infaux "
            + "WHERE 1=1"
            + "AND infaux.pgimInfraccion.idInfraccion = :idInfraccion "
            )
		PgimInfraccionAuxDTO obtenerInfraccionAuxPorId(@Param("idInfraccion") Long idInfraccion);

        /**
         * Permite listar de manera paginada las infracciones de una supervisión dada, 
         * por fase seleccionada y flag de vigencia
         * 
         * @param idSupervision
         * @param idFaseProceso
         * @param flVigente
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTOResultado( "
                        + "infaux.pgimInfraccion.idInfraccion, infaux.coTipificacion, infaux.noItemTipificacion, infaux.deBaseLegal, "
                        + "CASE WHEN infaux.flIncluirEnPas = '1' THEN 'Sí' ELSE 'No' END AS flIncluirEnPas, "
                        + "infaux.deSancionPecuniariaUit, infaux.deSustento, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimMatrizCriterio.coMatrizCriterio, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimMatrizCriterio.deMatrizCriterio, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimMatrizCriterio.deBaseLegal, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.deHechoConstatadoT, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.deComplementoObservacion, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.deSustentoT, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.tipoCumplimiento.deValorParametro, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimNormaItem.pgimNorma.noCortoNorma, "
                        + " pi.moMultaUit, "
                        + " pi.feComisionDeteccion, infaux.pgimPasoProceso.idPasoProceso, infaux.pgimFaseProceso.idFaseProceso "
                        + ") "                        
                        + "FROM PgimInfraccionAux infaux "
                        + "     INNER JOIN infaux.pgimInfraccion pi "
                        + "     LEFT OUTER JOIN infaux.pgimPas pasa "
                        + "WHERE infaux.pgimSupervision.idSupervision = :idSupervision "
                        + "AND pasa.idPas IS NULL "
                        + "AND (:idFaseProceso IS NULL OR infaux.pgimFaseProceso.idFaseProceso = :idFaseProceso) "
                        + "AND infaux.flVigente = :flVigente "
                        
                        // omitimos los que hayan dado origen a otra infracción 
                        // (sirve cuando consulto no vigentes, ps pueden haber varias infracciones no vigentes en la misma cadena histórica y solo necesito la última de la fase dada)
                        + "AND NOT EXISTS( "
                        + "		SELECT 1 "
                        + "		FROM PgimInfraccionAux infaux2 "
                        + "     LEFT OUTER JOIN infaux2.pgimPas pasa2 "
                        + "		WHERE infaux2.pgimSupervision.idSupervision = :idSupervision "
                        + "		AND pasa2.idPas IS NULL "
                        + "		AND (:idFaseProceso IS NULL OR infaux2.pgimFaseProceso.idFaseProceso = :idFaseProceso) "
                        + "		AND infaux2.flVigente = :flVigente "
                        + " 	AND infaux2.infraccionOrigen.idInfraccion = infaux.pgimInfraccion.idInfraccion " //si la infracción evaluada ha dado origen a otra infracción
                        + ") "
                        
                        // omitimos las infracciones que correspondan a un HV (de la fase dada) que ha dado origen a otro HV 
                        // (sirve cuando consulto infracciones no vigentes, en tal caso, necesito solo las infracciones de la última versión del HV de la fase dada)
                        + "AND NOT EXISTS( "
                        + "		SELECT 1 "
                        + "		FROM PgimInfraccionAux infaux3 "
                        + "     LEFT OUTER JOIN infaux3.pgimPas pasa3 "
                        + "		WHERE infaux3.pgimSupervision.idSupervision = :idSupervision "
                        + "		AND pasa3.idPas IS NULL "
                        + "		AND (:idFaseProceso IS NULL OR infaux3.pgimFaseProceso.idFaseProceso = :idFaseProceso) "
                        + "		AND infaux3.flVigente = :flVigente "
                        + " 	AND infaux3.pgimHechoConstatadoOrigen.idHechoConstatado = infaux.pgimHechoConstatado.idHechoConstatado " //si la infracción evaluada corresponde a un HV que ha dado origen a otro HV
                        + " 	AND infaux3.pgimFaseProcesoHC.idFaseProceso = :idFaseProceso "
                        + ") "
                        
                        )
        Page<PgimInfraccionAuxDTO> listarInfraccionPorIdSupervisionYFase(@Param("idSupervision") Long idSupervision, 
        		@Param("idFaseProceso") Long idFaseProceso, 
        		@Param("flVigente") String flVigente, 
        		Pageable paginador);


        /**
         * Permite listar de manera paginada las infracciones de un PAS dado, 
         * por fase seleccionada y flag de vigencia
         * 
         * @param idPas
         * @param idFaseProceso
         * @param flVigente
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTOResultado( "
                        + "infaux.pgimInfraccion.idInfraccion, infaux.coTipificacion, infaux.noItemTipificacion, infaux.deBaseLegal, "
                        + "CASE WHEN infaux.flIncluirEnPas = '1' THEN 'Sí' ELSE 'No' END AS flIncluirEnPas, "
                        + "infaux.deSancionPecuniariaUit, infaux.deSustento, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimMatrizCriterio.coMatrizCriterio, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimMatrizCriterio.deMatrizCriterio, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimMatrizCriterio.deBaseLegal, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.deHechoConstatadoT, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.deComplementoObservacion, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.deSustentoT, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.tipoCumplimiento.deValorParametro, "
                        + " pi.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimNormaItem.pgimNorma.noCortoNorma, "
                        + " pi.moMultaUit, "
                        + " pi.feComisionDeteccion, infaux.pgimPasoProceso.idPasoProceso, infaux.pgimFaseProceso.idFaseProceso "
                        + ") "
                        + "FROM PgimInfraccionAux infaux "
                        + "INNER JOIN infaux.pgimInfraccion pi "
                        + "INNER JOIN infaux.pgimPas pasa "
                        + "WHERE pasa.idPas = :idPas "
                        + "AND pasa.idPas IS NOT NULL "
                        + "AND (:idFaseProceso IS NULL OR infaux.pgimFaseProceso.idFaseProceso = :idFaseProceso) "
                        + "AND infaux.flVigente = :flVigente "
                        )
        Page<PgimInfraccionAuxDTO> listarInfraccionPorIdPasYFase(@Param("idPas") Long idPas, 
        		@Param("idFaseProceso") Long idFaseProceso, 
        		@Param("flVigente") String flVigente, 
        		Pageable paginador);

      @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTOResultado( "
                        + "supe.coSupervision, pasa.coPas, infaux.coTipificacion, infaux.noItemTipificacion, "
                        + "infaux.deSancionPecuniariaUit, ponaux.deObligacionNormativa "
                        + ") "
                        + "FROM PgimInfraccionAux infaux "
                        + "INNER JOIN infaux.pgimInfraccion pi "
                        + "INNER JOIN infaux.pgimPas pasa "
                        + "INNER JOIN infaux.pgimSupervision supe "
                        + "INNER JOIN infaux.pgimOblgcnNrmtvaHchoc obl "
                        + "INNER JOIN PgimObligacionNormaAux ponaux on obl.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio = ponaux.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio "
                        + "WHERE UPPER(infaux.coUnidadMinera) LIKE UPPER(:coUnidadMinera) "
                        + "and pi.flIncluirEnPas = '1' "
                        + "and pi.flVigente = '1' "
                        + "and pi.esRegistro = '1' "
                        + "and pasa.esRegistro = '1' "
                        + "and supe.esRegistro = '1' "
                        + "and obl.esRegistro = '1' "
                        + "and EXISTS (" 
                        + "            SELECT 1 " 
                        + "            FROM PgimInstanciaPaso T1 " 
                        + "            WHERE T1.pgimRelacionPaso.idRelacionPaso IN (pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTFISICA__ESPERAR_DESCARGOS_OIPAS, pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTIFELECTRONICA__ESPERAR_DESCARGOS_OIPAS) " 
                        + "            AND T1.esRegistro = '1' " 
                        + "            AND T1.pgimInstanciaProces.idInstanciaProceso = pasa.pgimInstanciaProces.idInstanciaProceso " 
                        + ") "
                        + "ORDER BY TRIM(supe.coSupervision) ASC, TRIM(pasa.coPas) ASC, TRIM(infaux.coTipificacion) ASC "
                        )
        List<PgimInfraccionAuxDTO> listaInfraccionPorUM(@Param("coUnidadMinera") String coUnidadMinera);

        /**
         * Permite listar las infracciones vigentes por el identificador interno de la supervisión.
         * @param idSupervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTOResultado( "
                        + "infaux.idInfraccionAux, infaux.pgimInfraccion.idInfraccion, super.idSupervision, "
                        + "pasa.idPas, heco.idOblgcnNrmtvaHchoc, inpa.idInstanciaPaso, "
                        + "inor.idInfraccion, infaux.coTipificacion, infaux.noItemTipificacion, "
                        + "infaux.deBaseLegal, infaux.flIncluirEnPas, infaux.deSancionPecuniariaUit, "
                        + "infaux.deSustento, infaux.feAnioInfraccion, infaux.coUnidadMinera, "
                        + "infaux.noUnidadMinera, infaux.rucAs, infaux.noRazonSocialAs, "
                        + "infaux.noCortoAs, infaux.feComisionDeteccion, infaux.moMultaUit) "
                        + "FROM PgimInfraccionAux infaux " 
//                        + "INNER JOIN infaux.pgimInfraccion infra "
                        + "INNER JOIN infaux.pgimSupervision super " 
                        + "INNER JOIN infaux.pgimOblgcnNrmtvaHchoc heco "
                        + "LEFT OUTER JOIN infaux.pgimInstanciaPaso inpa " 
                        + "LEFT OUTER JOIN infaux.pgimPas pasa "
                        + "LEFT OUTER JOIN infaux.infraccionOrigen inor "
                        + "WHERE infaux.pgimSupervision.idSupervision = :idSupervision "
                        + "AND pasa.idPas IS NULL "
                        + "AND infaux.flVigente = '1' " // infraccion vigente
                        + "AND infaux.esVigenteHc = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_HC_ESTADO_VIGENTE " // y hecho constatado vigente
                        )
        List<PgimInfraccionAuxDTO> listarInfraccionPorIdSupervision(Long idSupervision);

        /**
         * Permite listar las infracciones vigentes por el identificador del PAS 
         * @param idPas
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTOResultado( "
                        + "infaux.idInfraccionAux, infaux.pgimInfraccion.idInfraccion, super.idSupervision, "
                        + "pasa.idPas, heco.idOblgcnNrmtvaHchoc, inpa.idInstanciaPaso, "
                        + "inor.idInfraccion, infaux.coTipificacion, infaux.noItemTipificacion, "
                        + "infaux.deBaseLegal, infaux.flIncluirEnPas, infaux.deSancionPecuniariaUit, "
                        + "infaux.deSustento, infaux.feAnioInfraccion, infaux.coUnidadMinera, "
                        + "infaux.noUnidadMinera, infaux.rucAs, infaux.noRazonSocialAs, "
                        + "infaux.noCortoAs, infaux.feComisionDeteccion, infaux.moMultaUit) "
                        + "FROM PgimInfraccionAux infaux " 
//                        + "INNER JOIN infaux.pgimInfraccion infra "
                        + "INNER JOIN infaux.pgimSupervision super " 
                        + "INNER JOIN infaux.pgimOblgcnNrmtvaHchoc heco "
                        + "INNER JOIN infaux.pgimInstanciaPaso inpa " 
                        + "INNER JOIN infaux.pgimPas pasa "
                        + "LEFT OUTER JOIN infaux.infraccionOrigen inor " 
                        + "WHERE pasa.idPas = :idPas "
                        + "AND infaux.flVigente = '1' " // infracción vigente
                        + "AND infaux.esVigenteHc = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_HC_ESTADO_VIGENTE " // y hecho constatado vigente
                        )
        List<PgimInfraccionAuxDTO> listarInfraccionPorIdPas(Long idPas);

        /**
         * Permite obtener la lista preparada de cantidad de infracciones por titular
         * minero y año
         * 
         * @param idEstrato
         * @param nuInfraccionesMin
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxtitularAuxDTOResultado( "
                        + "infaux.idAgenteSupervisadoAux, infaux.coDocumentoIdentidad, infaux.noRazonSocial, infaux.noCortoAgenteSupervisado, infaux.idEstrato, infaux.noEstrato, infaux.noCortoEstrato, "
                        + "infaux.nroInfraccionP1, infaux.nroInfraccionP2, infaux.nroInfraccionP3, infaux.nroInfraccionP4, infaux.nroInfraccionP5, infaux.nroInfraccionP6, infaux.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxtitularAux infaux "
                        + "WHERE (:idEstrato IS NULL OR infaux.idEstrato = :idEstrato) "
                        + "AND (infaux.nroInfraccionTotal >= :nuInfraccionesMin) "
                        + "ORDER BY infaux.nroInfraccionTotal DESC, infaux.noRazonSocial ASC")
        List<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitular(@Param("idEstrato") Long idEstrato,
                        @Param("nuInfraccionesMin") Long nuInfraccionesMin);

        /**
         * Permite obtener la lista preparada de cantidad de infracciones por titular
         * minero y año para exportar
         * 
         * @param idEstrato
         * @param nuInfraccionesMin
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxtitularAuxDTOResultado( "
                        + "infaux.idAgenteSupervisadoAux, infaux.coDocumentoIdentidad, infaux.noRazonSocial, infaux.noCortoAgenteSupervisado, infaux.idEstrato, infaux.noEstrato, infaux.noCortoEstrato, "
                        + "infaux.nroInfraccionP1, infaux.nroInfraccionP2, infaux.nroInfraccionP3, infaux.nroInfraccionP4, infaux.nroInfraccionP5, infaux.nroInfraccionP6, infaux.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxtitularAux infaux "
                        + "WHERE (:idEstrato IS NULL OR infaux.idEstrato = :idEstrato) "
                        + "AND (infaux.nroInfraccionTotal >= :nuInfraccionesMin) ")
        List<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitularExportar(@Param("idEstrato") Long idEstrato,
                        @Param("nuInfraccionesMin") Long nuInfraccionesMin, Sort sort);

        /**
         * Permite obtener la lista preparada de cantidad de infracciones por titular
         * minero y año de manera paginada
         * 
         * @param idEstrato
         * @param nuInfraccionesMin
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxtitularAuxDTOResultado( "
                        + "infaux.idAgenteSupervisadoAux, infaux.coDocumentoIdentidad, infaux.noRazonSocial, infaux.noCortoAgenteSupervisado, infaux.idEstrato, infaux.noEstrato, infaux.noCortoEstrato, "
                        + "infaux.nroInfraccionP1, infaux.nroInfraccionP2, infaux.nroInfraccionP3, infaux.nroInfraccionP4, infaux.nroInfraccionP5, infaux.nroInfraccionP6, infaux.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxtitularAux infaux "
                        + "WHERE (:idEstrato IS NULL OR infaux.idEstrato = :idEstrato) "
                        + "AND (infaux.nroInfraccionTotal >= :nuInfraccionesMin) ")
        Page<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitularPaginado(@Param("idEstrato") Long idEstrato,
                        @Param("nuInfraccionesMin") Long nuInfraccionesMin, Pageable paginador);

        /**
         * Permite obtener la lista preparada de infracciones por especialidad y año
         * usado en reporte correspondiente
         * 
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespAuxDTOResultado( "
                        + "inf.idEspecialidad, inf.noEspecialidad, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxespAux inf ")
        List<PgimInfraccionxespAuxDTO> listarReporteInfraccionxesp();

        /**
         * Permite obtener la lista preparada de infracciones por especialidad y año
         * usado en reporte correspondiente
         * 
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespAuxDTOResultado( "
                        + "inf.idEspecialidad, inf.noEspecialidad, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxespAux inf ")
        List<PgimInfraccionxespAuxDTO> listarReporteInfraccionxespExportar(Sort sort);

        /**
         * Permite obtener la lista preparada de infracciones por especialidad y año
         * usado en reporte correspondiente de manera paginada
         * 
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespAuxDTOResultado( "
                        + "inf.idEspecialidad, inf.noEspecialidad, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxespAux inf ")
        Page<PgimInfraccionxespAuxDTO> listarReporteInfraccionxespPaginado(Pageable paginador);

        /**
         * Permite obtener la lista preparada de infracciones por especialidad y año
         * usado en reporte correspondiente
         * 
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespMesAuxDTOResultado( "
                        + "inf.pgimEspecialidad.idEspecialidad, inf.noEspecialidad, inf.anio, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionP7, inf.nroInfraccionP8, inf.nroInfraccionP9, inf.nroInfraccionP10, inf.nroInfraccionP11, inf.nroInfraccionP12, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxespMesAux inf " + "WHERE (inf.anio = :anio) ")
        List<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMesExportar(@Param("anio") String anio, Sort sort);

        /**
         * Permite obtener la lista preparada de infracciones por especialidad y año
         * usado en reporte correspondiente
         * 
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespMesAuxDTOResultado( "
                        + "inf.pgimEspecialidad.idEspecialidad, inf.noEspecialidad, inf.anio, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionP7, inf.nroInfraccionP8, inf.nroInfraccionP9, inf.nroInfraccionP10, inf.nroInfraccionP11, inf.nroInfraccionP12, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxespMesAux inf " + "WHERE (inf.anio = :anio) ")
        List<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMes(@Param("anio") String anio);

        /**
         * Permite obtener la lista preparada de infracciones por especialidad y año
         * usado en reporte correspondiente de manera paginada
         * 
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespMesAuxDTOResultado( "
                        + "inf.pgimEspecialidad.idEspecialidad, inf.noEspecialidad, inf.anio, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionP7, inf.nroInfraccionP8, inf.nroInfraccionP9, inf.nroInfraccionP10, inf.nroInfraccionP11, inf.nroInfraccionP12, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxespMesAux inf " + "WHERE (inf.anio = :anio) ")
        Page<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMesPaginado(@Param("anio") String anio,
                        Pageable paginador);

        /**
         * Permite obtener la lista preparada de infracciones por um y año usado en
         * reporte correspondiente de manera paginada
         * 
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxumAuxDTOResultado( inf.coUnidadMinera, "
                        + "inf.noUnidadMinera, inf.noTipoUnidadMinera, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxumAux inf "
                        + "WHERE (inf.nroInfraccionTotal >= :nuInfraccionesMin) "
                        + "AND (:idTipoUnidadMinera IS NULL OR inf.tipoUnidadMinera.idValorParametro = :idTipoUnidadMinera) ")
        Page<PgimInfraccionxumAuxDTO> listarReporteInfraccionesUmAnioPaginado(
                        @Param("nuInfraccionesMin") Long nuInfraccionesMin,
                        @Param("idTipoUnidadMinera") Long idTipoUnidadMinera, Pageable paginador);

        /**
         * Permite obtener la lista preparada de infracciones por um y año usado en
         * reporte correspondiente
         * 
         * @param sort
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionxumAuxDTOResultado( inf.coUnidadMinera, "
                        + "inf.noUnidadMinera, inf.noTipoUnidadMinera, inf.nroInfraccionP1, inf.nroInfraccionP2, inf.nroInfraccionP3, inf.nroInfraccionP4, inf.nroInfraccionP5, inf.nroInfraccionP6, inf.nroInfraccionTotal "
                        + ") " + "FROM PgimInfraccionxumAux inf "
                        + "WHERE (inf.nroInfraccionTotal >= :nuInfraccionesMin) "
                        + "AND (:idTipoUnidadMinera IS NULL OR inf.tipoUnidadMinera.idValorParametro = :idTipoUnidadMinera) ")
        List<PgimInfraccionxumAuxDTO> listarReporteInfraccionesUmAnio(
                        @Param("nuInfraccionesMin") Long nuInfraccionesMin,
                        @Param("idTipoUnidadMinera") Long idTipoUnidadMinera, Sort sort);

}
