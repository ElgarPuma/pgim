package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;

import java.util.List;

/**
 * 
 * @descripción: Logica de negocio de la entidad liquidación
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 01/12/2020
 * @fecha_de_ultima_actualización:
 */
public interface LiquidacionRepository extends JpaRepository<PgimLiquidacion, Long> {

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                        + "liqu.idLiquidacion, liqu.pgimContrato.idContrato, liqu.pgimContrato.nuContrato, liqu.tipoEntregable.idValorParametro, liqu.tipoEntregable.noValorParametro, liqu.pgimInstanciaProces.idInstanciaProceso, liqu.nuLiquidacion, "
                        + "liqu.pgimContrato.pgimEmpresaSupervisora.pgimPersona.noRazonSocial, liqu.pgimInstanciaProces.nuExpedienteSiged, liqu.pgimContrato.pgimInstanciaProces.nuExpedienteSiged, "
                        + "disu.noValorParametro, tisu.noValorParametro, "
                        + "stsu.deSubtipoSupervision, mosu.deMotivoSupervision, liqu.flPenalidadReemplazoPersona, liqu.moPenalidadReemplazoPersona) "
                        + "FROM PgimLiquidacion liqu " 
                        + "LEFT OUTER JOIN liqu.divisionSupervisora disu " 
                        + "LEFT OUTER JOIN liqu.pgimSubtipoSupervision stsu " 
                        + "LEFT OUTER JOIN stsu.tipoSupervision tisu " 
                        + "LEFT OUTER JOIN liqu.pgimMotivoSupervision mosu " 
                        + "WHERE liqu.idLiquidacion = :idLiquidacion "
                        + "AND liqu.esRegistro = '1' ")
        PgimLiquidacionDTO obtenerLiquidacionPorId(@Param("idLiquidacion") Long idLiquidacion);

        /**
         * Me permite validar uno de los campos de Item liquidacion de la Penalidad en
         * el dialogo por especialidad. Validar "moPenalidadReincidencia"
         *
         * @param idLiquidacion
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                        + "pl.idLiquidacion, pc.idContrato, ppe.idEspecialidad, pc.nuContrato, "
                        + "te.idValorParametro, te.noValorParametro, pips.idInstanciaProceso, "
                        + "pl.nuLiquidacion, pp.noRazonSocial, pip.nuExpedienteSiged) " + "FROM PgimLiquidacion pl "
                        + "INNER JOIN pl.pgimInstanciaProces pips " + "INNER JOIN pl.tipoEntregable te "
                        + "INNER JOIN pl.pgimContrato pc " + "INNER JOIN pc.pgimEmpresaSupervisora pes "
                        + "INNER JOIN pes.pgimPersona pp " + "INNER JOIN pc.pgimInstanciaProces pip "
                        + "INNER JOIN pc.pgimEspecialidad ppe " + "WHERE pl.idLiquidacion = :idLiquidacion "
                        + "AND pl.esRegistro = '1' ")
        PgimLiquidacionDTO validarPenalidadPorEspecialidad(@Param("idLiquidacion") Long idLiquidacion);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                        + "pl.idLiquidacion, pc.idContrato, tipo.idValorParametro, pl.pgimInstanciaProces.idInstanciaProceso, pl.nuLiquidacion, "
                        + "pc.nuContrato, tipo.noValorParametro, pc.pgimEmpresaSupervisora.pgimPersona.noRazonSocial, pc.pgimInstanciaProces.nuExpedienteSiged"
                        + ") " + "FROM PgimLiquidacion pl "
                        + "LEFT JOIN PgimContrato pc ON pl.pgimContrato.idContrato = pc.idContrato "
                        + "LEFT JOIN PgimValorParametro tipo ON pl.tipoEntregable.idValorParametro = tipo.idValorParametro "
                        + "WHERE pl.esRegistro = '1' " + "AND pc.esRegistro = '1' "
                        + "AND (:nuLiquidacion IS NULL OR pl.nuLiquidacion = :nuLiquidacion) "
                        + "AND (:nuContrato IS NULL OR LOWER(pc.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(pc.pgimEmpresaSupervisora.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                        + "AND (:nuExpedienteSiged IS NULL OR LOWER(pc.pgimInstanciaProces.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :nuExpedienteSiged, '%')) ) "
                        + "AND (:idEspecialidad IS NULL OR pc.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(pl.nuLiquidacion) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(pc.nuContrato ||' - '|| pc.pgimEmpresaSupervisora.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(pc.pgimEmpresaSupervisora.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(pc.pgimInstanciaProces.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(pc.pgimEspecialidad.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + " )) ")
        Page<PgimLiquidacionDTO> listarLiquidaciones(@Param("nuLiquidacion") String nuLiquidacion,
                        @Param("nuContrato") String nuContrato, @Param("noRazonSocial") String noRazonSocial,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged,
                        @Param("idEspecialidad") Long idEspecialidad, @Param("textoBusqueda") String textoBusqueda,
                        Pageable paginador);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                        + "supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.noRazonSocial, supe.coSupervision, "
                        + "supe.pgimSubtipoSupervision.deSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, "
                        + "coco.moConsumoContrato, docu.feOrigenDocumento, inpa.feInstanciaPaso, itco.moItemConsumo, docu.idDocumento, "
                        + "itco.idItemConsumo, liqu.idLiquidacion, supe.idSupervision, docu.coDocumentoSiged, inpr.nuExpedienteSiged, "
                        + "docu.deAsuntoDocumento) "
                        + "FROM PgimItemConsumo itco INNER JOIN itco.pgimConsumoContra coco "
                        + "INNER JOIN coco.pgimContrato cont INNER JOIN PgimLiquidacion liqu ON (cont = liqu.pgimContrato) "
                        + "INNER JOIN coco.pgimSupervision supe INNER JOIN supe.pgimInstanciaProces inpr "
                        + "INNER JOIN PgimDocumento docu ON docu.pgimInstanciaProces = inpr "
                        + "INNER JOIN PgimInstanciaPaso inpa ON inpa.pgimInstanciaProces = inpr "
                        + "WHERE itco.tipoEntregable.idValorParametro = :idTipoEntregable "
                        + "AND liqu.idLiquidacion = :idLiquidacion "
                        + "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
                        + "AND inpa.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso " + "AND NOT EXISTS (SELECT 1 "
                        + "FROM PgimItemLiquidacion itlq " + "WHERE itlq.pgimItemConsumo = itco "
                        + "AND itlq.pgimDocumento = docu " + "AND itlq.esRegistro = '1') "
                        + "AND itco.esRegistro = '1' " + "AND itco.esRegistro = '1' " + "AND itco.esVigente = '1' "
                        + "AND itco.esRegistro = '1' " + "AND coco.esRegistro = '1' " + "AND cont.esRegistro = '1' "
                        + "AND liqu.esRegistro = '1' " + "AND supe.esRegistro = '1' " + "AND inpr.esRegistro = '1' "
                        + "AND docu.esRegistro = '1' " + "AND inpa.esRegistro = '1' "
                        + "AND supe.pgimUnidadMinera.divisionSupervisora = liqu.divisionSupervisora "
                        + "AND supe.pgimSubtipoSupervision = liqu.pgimSubtipoSupervision "
                        + "AND supe.pgimMotivoSupervision = liqu.pgimMotivoSupervision")
        List<PgimLiquidacionDTO> obtenerActasALiquidar(@Param("idLiquidacion") Long idLiquidacion,
                        @Param("idTipoEntregable") Long idTipoEntregable,
                        @Param("idSubcatDocumento") Long idSubcatDocumento,
                        @Param("idRelacionPaso") Long idRelacionPaso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                        + "supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.noRazonSocial, supe.coSupervision, "
                        + "supe.pgimSubtipoSupervision.deSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, "
                        + "coco.moConsumoContrato, docu.feOrigenDocumento, inpa.feInstanciaPaso, itco.moItemConsumo, docu.idDocumento, "
                        + "itco.idItemConsumo, liqu.idLiquidacion, supe.idSupervision, docu.coDocumentoSiged, inpr.nuExpedienteSiged, "
                        + "docu.deAsuntoDocumento) "
                        + "FROM PgimItemConsumo itco INNER JOIN itco.pgimConsumoContra coco "
                        + "INNER JOIN coco.pgimContrato cont INNER JOIN PgimLiquidacion liqu ON (cont = liqu.pgimContrato) "
                        + "INNER JOIN coco.pgimSupervision supe INNER JOIN supe.pgimInstanciaProces inpr "
                        + "INNER JOIN PgimDocumento docu ON docu.pgimInstanciaProces = inpr "
                        + "INNER JOIN PgimInstanciaPaso inpa ON inpa.pgimInstanciaProces = inpr "
                        + "WHERE itco.tipoEntregable.idValorParametro = :idTipoEntregable "
                        + "AND liqu.idLiquidacion = :idLiquidacion "
                        + "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
                        + "AND inpa.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso " + "AND NOT EXISTS (SELECT 1 "
                        + "FROM PgimItemLiquidacion itlq " + "WHERE itlq.pgimItemConsumo = itco "
                        + "AND itlq.pgimDocumento = docu " + "AND itlq.esRegistro = '1') " + "AND ("
                        + "(:idSubcatDocumento = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SC_INFORME_SUPERVISION "
                        + "AND EXISTS (SELECT 1 " + "FROM PgimDocumentoRelacion dore "
                        + "INNER JOIN dore.documentoPadre dopa " + "INNER JOIN dore.pgimDocumento dohi "
                        + "INNER JOIN dohi.pgimSubcategoriaDoc suca " + "WHERE dopa.idDocumento = docu.idDocumento "
                        + "AND suca.idSubcatDocumento = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SUBCAT_DOC_CIS "
                        + "AND dore.esRegistro = '1')) "
                        + "OR :idSubcatDocumento != pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SC_INFORME_SUPERVISION "
                        + ") " + "AND itco.esRegistro = '1' " + "AND itco.esRegistro = '1' "
                        + "AND itco.esVigente = '1' " + "AND itco.esRegistro = '1' " + "AND coco.esRegistro = '1' "
                        + "AND cont.esRegistro = '1' " + "AND liqu.esRegistro = '1' " + "AND supe.esRegistro = '1' "
                        + "AND inpr.esRegistro = '1' " + "AND docu.esRegistro = '1' " + "AND inpa.esRegistro = '1' "
                        + "AND supe.pgimUnidadMinera.divisionSupervisora = liqu.divisionSupervisora "
                        + "AND supe.pgimSubtipoSupervision = liqu.pgimSubtipoSupervision "
                        + "AND supe.pgimMotivoSupervision = liqu.pgimMotivoSupervision")
        List<PgimLiquidacionDTO> obtenerInformesSupervisionALiquidar(@Param("idLiquidacion") Long idLiquidacion,
                        @Param("idTipoEntregable") Long idTipoEntregable,
                        @Param("idSubcatDocumento") Long idSubcatDocumento,
                        @Param("idRelacionPaso") Long idRelacionPaso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                        + "pl.idLiquidacion, pl.nuLiquidacion, pl.pgimContrato.nuContrato) " 
                        + "FROM PgimLiquidacion pl "
                        + "WHERE pl.esRegistro = '1' "
                        + "AND (:palabraClave IS NULL OR LOWER(pl.nuLiquidacion) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
        List<PgimLiquidacionDTO> listarPorNumeroLiquidacion(@Param("palabraClave") String palabraClave);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTOResultado("
                + "pl.idLiquidacion, pl.nuLiquidacion, pl.pgimContrato.nuContrato) " 
                + "FROM PgimLiquidacion pl "
                + "WHERE pl.esRegistro = '1' " 
                + "AND pl.nuLiquidacion = :nuLiquidacion "
                + "AND pl.pgimContrato.idContrato = :idContrato "
                )
        List<PgimLiquidacionDTO> listarLiquidacionPorContratoYNumero(
        		@Param("nuLiquidacion") String nuLiquidacion,
                @Param("idContrato") Long idContrato
                );


}
