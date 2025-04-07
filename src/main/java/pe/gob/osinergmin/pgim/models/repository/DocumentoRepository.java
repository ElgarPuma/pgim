package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;

import java.util.List;

/**
 * Ésta interface DocumentoRepository que cumple con los metodos de listar los documentos del evento y
 * obtener los documentos Siged y Pgim.
 * 
 * @descripción: Logica de negocio de la entidad Documento
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 29/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface DocumentoRepository extends JpaRepository<PgimDocumento, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docs.pgimInstanciaProces.idInstanciaProceso, docs.idDocumento, TO_CHAR(docs.feOrigenDocumento, 'DD/MON/YYYY'), "
			+ "docs.pgimSubcategoriaDoc.noSubcatDocumento, '', docs.deAsuntoDocumento "
			+ ") "
			+ "FROM PgimDocumento docs "
			+ "WHERE docs.esRegistro = '1' "
			+ "AND docs.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso")
	List<PgimDocumentoDTO> verDocumentosEvento(@Param("idInstanciaProceso") Long idInstanciaProceso); //elozano este metodo no se utiliza

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "pd.pgimSubcategoriaDoc.pgimCategoriaDoc.coCategoriaDocumento, pd.pgimSubcategoriaDoc.coSubcatDocumento, pd.idDocumento, "
			+ "pd.tipoOrigenDocumento.noValorParametro, pd.deAsuntoDocumento, pd.feOrigenDocumento, "
			+ "pd.pgimSubcategoriaDoc.noSubcatDocumento, pd.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento, pd.coDocumentoSiged, "
			+ "0, pd.pgimSubcategoriaDoc.coTipoDocumentoSiged, pd.pgimSubcategoriaDoc.flNumeradoPorSiged, "
			+ "pd.pgimSubcategoriaDoc.idSubcatDocumento, pd.pgimInstanciaProces.idInstanciaProceso, pd.tipoOrigenDocumento.idValorParametro, "
			+ "pd.seDocumento, pd.pgimSubcategoriaDoc.pgimCategoriaDoc.idCategoriaDocumento, pd.pgimInstanciaProces.pgimProceso.idProceso, "
			+ "pd.pgimInstanciaProces.coTablaInstancia, pd.pgimFaseProceso.idFaseProceso "
			+ ") "
			+ "FROM PgimDocumento pd "
			+ "WHERE pd.esRegistro = '1' "
			+ "AND pd.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso")
	List<PgimDocumentoDTO> obtenerDocumentosSiged(@Param("idInstanciaProceso") Long idInstanciaProceso);

	/**
	 * informe de supervision
	 * @param idSubcatDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado( "
			+ "subcat.idSubcatDocumento, docs.pgimInstanciaProces.idInstanciaProceso, docs.idDocumento, docs.feOrigenDocumento, subcat.noSubcatDocumento, cat.noCategoriaDocumento "
			+ ",docs.coDocumentoSiged, docs.deAsuntoDocumento, ori.noValorParametro, 'D',docs.seDocumento,docs.pgimFaseProceso.idFaseProceso "
			+ ",subcat.flNumeradoPorSiged, docs.feEnvioDocumento, etq.flEtiquetaNotifActiva, etq.idDocEtiquetaNotif "
			+ ",ori.idValorParametro, subcat.coTipoDocumentoSiged, inspro.pgimProceso.idProceso, docs.flReservaActiva "
			+ ",subcat.flPermiteNotifPorArchivo, docs.feRechazo, subcat.flEtiquetaNotifS "
			+ ") " 
			+ "FROM PgimDocumento docs "
			+ ", PgimInstanciaProces inspro "			
			+ ", PgimSubcategoriaDoc subcat "
			+ ", PgimCategoriaDoc cat "
			+ ", PgimValorParametro ori "
			+ "LEFT JOIN PgimDocEtiquetaNotif etq ON ( docs.idDocumento = etq.documentoEtiquetado.idDocumento AND etq.flEtiquetaNotifActiva = '1' ) "
			+ "WHERE docs.pgimInstanciaProces = inspro AND docs.pgimSubcategoriaDoc = subcat AND subcat.pgimCategoriaDoc=cat "
			+ "AND docs.tipoOrigenDocumento = ori "
			+ "AND docs.esRegistro = '1' and inspro.esRegistro = '1' "
			+ "AND (:idSubcatDocumento IS NULL OR inspro.idInstanciaProceso = :idInstanciaProceso) "
			+ "AND (:idSubcatDocumento IS NULL OR subcat.idSubcatDocumento = :idSubcatDocumento) " )
	List<PgimDocumentoDTO> obtenerDocPorInstanciaYSubCategoria(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idSubcatDocumento") Long idSubcatDocumento);
		
	/**
	 * listar documentos por IdInstancia proceso y id subCategoria
	 * @param idInstanciaProceso
	 * @param idSubcatDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "subcat.idSubcatDocumento, docs.pgimInstanciaProces.idInstanciaProceso, docs.idDocumento, "
			+ "docs.feOrigenDocumento, subcat.noSubcatDocumento, cat.noCategoriaDocumento, "
			+ "docs.coDocumentoSiged, docs.deAsuntoDocumento, ori.noValorParametro, "
			+ "'D', docs.seDocumento, docs.pgimFaseProceso.idFaseProceso, "
			+ "subcat.flNumeradoPorSiged, docs.feEnvioDocumento, etq.flEtiquetaNotifActiva, etq.idDocEtiquetaNotif, "
			+ "ori.idValorParametro, subcat.coTipoDocumentoSiged, inspro.pgimProceso.idProceso, docs.flReservaActiva, "
			+ "subcat.flPermiteNotifPorArchivo, docs.feRechazo, subcat.flEtiquetaNotifS " 
			+ ") " 
			+ "FROM PgimDocumento docs "
			+ ", PgimInstanciaProces inspro "			
			+ ", PgimSubcategoriaDoc subcat "
			+ ", PgimCategoriaDoc cat "
			+ ", PgimValorParametro ori "
			+ "LEFT JOIN PgimDocEtiquetaNotif etq ON ( docs.idDocumento = etq.documentoEtiquetado.idDocumento AND etq.flEtiquetaNotifActiva = '1' ) "
			+ "WHERE docs.pgimInstanciaProces = inspro AND docs.pgimSubcategoriaDoc = subcat AND subcat.pgimCategoriaDoc=cat "
			+ "AND docs.tipoOrigenDocumento = ori "
			+ "AND docs.esRegistro = '1' and inspro.esRegistro = '1' "
			+ "AND inspro.idInstanciaProceso = :idInstanciaProceso "
			+ "AND subcat.idSubcatDocumento = :idSubcatDocumento "
			+ "ORDER BY docs.feOrigenDocumento DESC, docs.idDocumento DESC "
			)
	List<PgimDocumentoDTO> listarDocPorInstanciaYSubCategoria(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idSubcatDocumento") Long idSubcatDocumento
	);
	
	/**
	 * listar documentos por IdInstancia proceso, idSubCategoria y idFaseProceso
	 * @param idInstanciaProceso
	 * @param idSubcatDocumento
	 * @param idFaseProceso
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "subcat.idSubcatDocumento, docs.pgimInstanciaProces.idInstanciaProceso, docs.idDocumento, "
			+ "docs.feOrigenDocumento, subcat.noSubcatDocumento, cat.noCategoriaDocumento, "
			+ "docs.coDocumentoSiged, docs.deAsuntoDocumento, ori.noValorParametro, "
			+ "'D', docs.seDocumento, docs.pgimFaseProceso.idFaseProceso, "
			+ "subcat.flNumeradoPorSiged, docs.feEnvioDocumento, etq.flEtiquetaNotifActiva, etq.idDocEtiquetaNotif, "
			+ "ori.idValorParametro, subcat.coTipoDocumentoSiged, inspro.pgimProceso.idProceso, docs.flReservaActiva, "
			+ "subcat.flPermiteNotifPorArchivo, docs.feRechazo, subcat.flEtiquetaNotifS " 
			+ ") " 
			+ "FROM PgimDocumento docs "
			+ ", PgimInstanciaProces inspro "			
			+ ", PgimSubcategoriaDoc subcat "
			+ ", PgimCategoriaDoc cat "
			+ ", PgimValorParametro ori "
			+ "LEFT JOIN PgimDocEtiquetaNotif etq ON ( docs.idDocumento = etq.documentoEtiquetado.idDocumento AND etq.flEtiquetaNotifActiva = '1' ) "
			+ "WHERE docs.pgimInstanciaProces = inspro AND docs.pgimSubcategoriaDoc = subcat AND subcat.pgimCategoriaDoc=cat "
			+ "AND docs.tipoOrigenDocumento = ori "
			+ "AND docs.esRegistro = '1' and inspro.esRegistro = '1' "
			+ "AND inspro.idInstanciaProceso = :idInstanciaProceso "
			+ "AND subcat.idSubcatDocumento = :idSubcatDocumento "
			+ "AND docs.pgimFaseProceso.idFaseProceso = :idFaseProceso "
			+ "ORDER BY docs.feOrigenDocumento DESC, docs.idDocumento DESC "
			)
	List<PgimDocumentoDTO> listarDocPorInstanciaYSubcatYFase(
			@Param("idInstanciaProceso") Long idInstanciaProceso, 
			@Param("idSubcatDocumento") Long idSubcatDocumento,
			@Param("idFaseProceso") Long idFaseProceso
	);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
	+ "docs.pgimInstanciaProces.idInstanciaProceso, docs.idDocumento, docs.feOrigenDocumento, "
	+ "subcat.noSubcatDocumento, cat.noCategoriaDocumento ,docs.coDocumentoSiged, "
	+ "docs.deAsuntoDocumento, ori.noValorParametro, 'D', "
	+ "subcat.idSubcatDocumento, docs.seDocumento, fp.idFaseProceso, "
	+ "fp.noFaseProceso, fp.deFaseProceso, subcat.flNumeradoPorSiged, "
	+ "docs.feEnvioDocumento, ori.idValorParametro, subcat.coTipoDocumentoSiged, "
	+ "inspro.pgimProceso.idProceso, docs.flReservaActiva, etq.flEtiquetaNotifActiva, "
	+ "etq.idDocEtiquetaNotif, subcat.flPermiteNotifPorArchivo, docs.feRechazo, subcat.flEtiquetaNotifS "
	+ ") " 
	+ "FROM PgimDocumento docs "
	+ ", PgimInstanciaProces inspro "			
	+ ", PgimSubcategoriaDoc subcat "
	+ ", PgimCategoriaDoc cat "
	+ ", PgimValorParametro ori "
	+ ", PgimFaseProceso fp "
	+ "LEFT JOIN PgimDocEtiquetaNotif etq ON ( docs.idDocumento = etq.documentoEtiquetado.idDocumento AND etq.flEtiquetaNotifActiva = '1' ) "
	+ "WHERE docs.pgimInstanciaProces = inspro AND docs.pgimSubcategoriaDoc = subcat AND subcat.pgimCategoriaDoc=cat "
	+ "AND docs.pgimFaseProceso.idFaseProceso= fp.idFaseProceso "
	+ "AND docs.tipoOrigenDocumento = ori "
	+ "AND docs.esRegistro = '1' and inspro.esRegistro = '1' "
	+ "AND inspro.idInstanciaProceso = :idInstanciaProceso " )
	List<PgimDocumentoDTO> obtenerDocumentosInstancia(@Param("idInstanciaProceso") Long idInstanciaProceso, Sort sort);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "inspro.idInstanciaProceso, docs.idDocumento, docs.feOrigenDocumento, "
			+ "subcat.noSubcatDocumento, cat.noCategoriaDocumento ,docs.coDocumentoSiged, "
			+ "docs.deAsuntoDocumento, ori.noValorParametro, 'D', "
			+ "subcat.idSubcatDocumento, docs.seDocumento, fp.idFaseProceso, "
			+ "fp.noFaseProceso, fp.deFaseProceso, subcat.flNumeradoPorSiged, "
			+ "docs.feEnvioDocumento, ori.idValorParametro, subcat.coTipoDocumentoSiged, "
			+ "inspro.pgimProceso.idProceso, docs.flReservaActiva, etq.flEtiquetaNotifActiva, "
			+ "etq.idDocEtiquetaNotif, subcat.flPermiteNotifPorArchivo, docs.feRechazo, subcat.flEtiquetaNotifS "
			+ ") " 
			+ "FROM PgimDocumento docs "
			+ "     INNER JOIN docs.pgimInstanciaProces inspro "			
			+ "     INNER JOIN docs.pgimSubcategoriaDoc subcat "
			+ "     INNER JOIN subcat.pgimCategoriaDoc cat "
			+ "     INNER JOIN docs.pgimFaseProceso fp "
			+ "     INNER JOIN docs.tipoOrigenDocumento ori "
			+ "		LEFT JOIN PgimDocEtiquetaNotif etq ON ( docs.idDocumento = etq.documentoEtiquetado.idDocumento AND etq.flEtiquetaNotifActiva = '1' ) "
			+ "WHERE 1 = 1 "
			+ "AND inspro.idInstanciaProceso = :idInstanciaProcesoFiscalizacion "
			+ "AND NOT EXISTS "
			+ "( "
			+ "SELECT 1  "
			+ "FROM PgimDocumento dopa  "
			+ "		INNER JOIN dopa.pgimInstanciaProces inpa  "
			+ "     INNER JOIN dopa.pgimDocumentoPadreCopia doco "			
			+ "WHERE 1 = 1   "
			+ "AND inpa.idInstanciaProceso = :idInstanciaProcesoPas  "
			+ "AND doco.idDocumento = docs.idDocumento  "
			+ "AND dopa.esRegistro = '1'  "
			+ "AND inpa.esRegistro = '1'  "
			+ ") "
			+ "AND docs.esRegistro = '1' "
			+ "AND inspro.esRegistro = '1' ")
	List<PgimDocumentoDTO> obtenerDocumentosInstancia(@Param("idInstanciaProcesoFiscalizacion") Long idInstanciaProcesoFiscalizacion,
													  @Param("idInstanciaProcesoPas") Long idInstanciaProcesoPas, 
													  Sort sort);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docs.pgimInstanciaProces.idInstanciaProceso, docs.idDocumento, docs.feOrigenDocumento, "
			+ "subcat.noSubcatDocumento, cat.noCategoriaDocumento, docs.coDocumentoSiged, "
			+ "docs.deAsuntoDocumento, ori.noValorParametro, 'D', "
			+ "subcat.idSubcatDocumento, docs.seDocumento, docs.pgimFaseProceso.idFaseProceso, "
			+ "docs.flNotificado, subcat.flNotificableE, 0, "
			+ "subcat.flNumeradoPorSiged, cat.idCategoriaDocumento, docs.feEnvioDocumento, etq.flEtiquetaNotifActiva, etq.idDocEtiquetaNotif, "
			+ "ori.idValorParametro, subcat.coTipoDocumentoSiged, inspro.pgimProceso.idProceso, docs.flReservaActiva, subcat.flPermiteNotifPorArchivo, "
			+ "docs.feRechazo, subcat.flEtiquetaNotifS "
			+ ") " 
			+ "FROM PgimDocumento docs "
			+ ", PgimInstanciaProces inspro "			
			+ ", PgimSubcategoriaDoc subcat "
			+ ", PgimCategoriaDoc cat "
			+ ", PgimValorParametro ori "
			+ "LEFT JOIN PgimDocEtiquetaNotif etq ON ( docs.idDocumento = etq.documentoEtiquetado.idDocumento AND etq.flEtiquetaNotifActiva = '1' ) "
			+ "WHERE docs.pgimInstanciaProces = inspro AND docs.pgimSubcategoriaDoc = subcat AND subcat.pgimCategoriaDoc=cat "
			+ "AND docs.tipoOrigenDocumento = ori "
			+ "AND docs.esRegistro = '1' AND inspro.esRegistro = '1' "
			+ "AND inspro.idInstanciaProceso = :idInstanciaProceso "
			+ "AND docs.pgimFaseProceso.idFaseProceso = :idFase "			
			)
	List<PgimDocumentoDTO> obtenerDocumentosInstanciaFase(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idFase") Long idFase, Sort sort);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "pd.pgimSubcategoriaDoc.pgimCategoriaDoc.coCategoriaDocumento, "
			+ "pd.pgimSubcategoriaDoc.coSubcatDocumento, pd.idDocumento, "
			+ "pd.tipoOrigenDocumento.noValorParametro, pd.deAsuntoDocumento, pd.feOrigenDocumento, "
			+ "pd.pgimSubcategoriaDoc.noSubcatDocumento, pd.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento, "
			+ "pd.coDocumentoSiged, 0,pd.pgimFaseProceso.idFaseProceso) "
			+ "FROM PgimDocumento pd "
			+ "WHERE pd.esRegistro = '1' "
			+ "AND pd.idDocumento = :idDocumento")
	PgimDocumentoDTO obtenerDocumentoById(@Param("idDocumento") Long idDocumento);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "pd.idDocumento, pd.pgimSubcategoriaDoc.idSubcatDocumento, pd.pgimInstanciaProces.idInstanciaProceso, "
			+ "pd.tipoOrigenDocumento.idValorParametro, pd.deAsuntoDocumento, pd.feOrigenDocumento, pd.coDocumentoSiged, "
			+ "pd.seDocumento, pd.pgimSubcategoriaDoc.pgimCategoriaDoc.idCategoriaDocumento"
			+ ",pd.pgimFaseProceso.idFaseProceso, pd.pgimSubcategoriaDoc.flNumeradoPorSiged, pd.flReservaActiva "
			+ ") "
			+ "FROM PgimDocumento pd "
			+ "WHERE pd.esRegistro = '1' "
			+ "AND pd.idDocumento = :idDocumento")
	PgimDocumentoDTO obtenerDocumentoPgimById(@Param("idDocumento") Long idDocumento);
	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "pd.idDocumento, pd.pgimSubcategoriaDoc.idSubcatDocumento, pd.pgimInstanciaProces.idInstanciaProceso, "
			+ "pd.tipoOrigenDocumento.idValorParametro, pd.deAsuntoDocumento, pd.feOrigenDocumento, "
			+ "pd.coDocumentoSiged, pd.seDocumento, pd.pgimSubcategoriaDoc.pgimCategoriaDoc.idCategoriaDocumento, "
			+ "pd.pgimFaseProceso.idFaseProceso, pd.pgimSubcategoriaDoc.flNumeradoPorSiged, pd.flReservaActiva " 
			+ ") "
			+ "FROM PgimDocumento pd "
			+ "WHERE pd.esRegistro = '1' "
			+ "AND pd.coDocumentoSiged = :idDocumentoSiged")
	PgimDocumentoDTO obtenerDocumentoPgimByIdDocumentoSiged(@Param("idDocumentoSiged") Long idDocumentoSiged);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
    		+ "(SELECT psd.noSubcatDocumento FROM PgimSubcategoriaDoc psd "
    		+ "WHERE psd.idSubcatDocumento = pd.pgimSubcategoriaDoc.idSubcatDocumento), "
    		+ "pd.feOrigenDocumento, "
    		+ "(SELECT MAX(pip.feInstanciaPaso) FROM PgimInstanciaPaso pip "
    		+ "WHERE pip.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso AND pip.pgimInstanciaProces.idInstanciaProceso = pd.pgimInstanciaProces.idInstanciaProceso), "
    		+ "(TRUNC( SYSDATE - (SELECT MAX(pip.feInstanciaPaso) FROM PgimInstanciaPaso pip "
    		+ "WHERE pip.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso AND pip.pgimInstanciaProces.idInstanciaProceso = pd.pgimInstanciaProces.idInstanciaProceso))) "
            + ") "
            + "FROM PgimDocumento pd " 
            + "WHERE pd.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            + "AND pd.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
            + "AND pd.esRegistro = '1' "
            + "ORDER BY pd.feCreacion DESC ")
	List<PgimDocumentoDTO> obtenerAprobacionResultadoPlazo(@Param("idInstanciaProceso") Long idInstanciaProceso,
    		@Param("idSubcatDocumento") Long idSubcatDocumento, @Param("idRelacionPaso") Long idRelacionPaso);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docu.idDocumento, sudo.idSubcatDocumento, inpr.idInstanciaProceso, "
			+ "tido.idValorParametro, fapr.idFaseProceso, docu.deAsuntoDocumento, "
			+ "docu.feOrigenDocumento, docu.coDocumentoSiged, docu.seDocumento, "
			+ "sudo.noSubcatDocumento, dore.idDocumentoRelacion, inpr.coTablaInstancia, "
			+ "docu.feEnvioDocumento,inpr.nuExpedienteSiged, sudo.flNumeradoPorSiged, "
			+ "sudo.coTipoDocumentoSiged, inpr.pgimProceso.idProceso, docu.feRechazo, docu.flIncumplimientoET "
			+ ") "
			+ "FROM PgimDocumento docu "
			+ "LEFT OUTER JOIN PgimDocumentoRelacion dore ON (dore.pgimDocumento.idDocumento = docu.idDocumento) "
			+ "LEFT OUTER JOIN PgimInstanciaProces inpr ON (docu.pgimInstanciaProces.idInstanciaProceso = inpr.idInstanciaProceso) "
			+ "LEFT OUTER JOIN PgimSubcategoriaDoc sudo ON (docu.pgimSubcategoriaDoc.idSubcatDocumento = sudo.idSubcatDocumento) "
			+ "LEFT OUTER JOIN PgimValorParametro tido ON (docu.tipoOrigenDocumento.idValorParametro = tido.idValorParametro) "
			+ "LEFT OUTER JOIN PgimFaseProceso fapr ON (docu.pgimFaseProceso.idFaseProceso = fapr.idFaseProceso) "
			+ "WHERE dore.esRegistro = '1' "
			+ "AND docu.esRegistro = '1' "
			+ "AND inpr.esRegistro = '1' "
			+ "AND dore.documentoPadre.idDocumento = :idDocumentoPadre "
			+ "AND sudo.idSubcatDocumento = :idSubcatDocumento ")
	PgimDocumentoDTO obtenerDocumentoObsAprob(@Param("idDocumentoPadre") Long idDocumentoPadre, @Param("idSubcatDocumento") Long idSubcatDocumento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docu.idDocumento, sudo.idSubcatDocumento, inpr.idInstanciaProceso, "
			+ "todo.idValorParametro, fapr.idFaseProceso, docu.deAsuntoDocumento, "
			+ "docu.feOrigenDocumento, docu.coDocumentoSiged, docu.seDocumento, "
			+ "sudo.noSubcatDocumento, dore.idDocumentoRelacion, inpr.coTablaInstancia, "
			+ "docu.feEnvioDocumento, inpr.nuExpedienteSiged, sudo.flNumeradoPorSiged, "
			+ "sudo.coTipoDocumentoSiged, inpr.pgimProceso.idProceso, docu.feRechazo, docu.flIncumplimientoET "
			+ ") "
			+ "FROM PgimDocumento docu "
			+ "INNER JOIN PgimSubcategoriaDoc sudo ON (docu.pgimSubcategoriaDoc.idSubcatDocumento = sudo.idSubcatDocumento) "
			+ "INNER JOIN PgimInstanciaProces inpr ON (docu.pgimInstanciaProces.idInstanciaProceso = inpr.idInstanciaProceso) "
			+ "LEFT OUTER JOIN PgimDocumentoRelacion dore ON (dore.pgimDocumento.idDocumento = docu.idDocumento AND dore.esRegistro = '1') "
			+ "LEFT OUTER JOIN PgimValorParametro todo ON docu.tipoOrigenDocumento.idValorParametro = todo.idValorParametro "
			+ "LEFT OUTER JOIN PgimFaseProceso fapr ON docu.pgimFaseProceso.idFaseProceso = fapr.idFaseProceso "
			+ "WHERE docu.esRegistro = '1' "
			+ "AND inpr.esRegistro = '1' "
			+ "AND inpr.idInstanciaProceso = :idInstanciaProceso "
			+ "AND sudo.idSubcatDocumento = :idSubcatDocumento " 
			+ "ORDER BY docu.idDocumento DESC")
	List<PgimDocumentoDTO> obtenerDocumentosDescendentes(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idSubcatDocumento") Long idSubcatDocumento);
	
	/**
	 * obtener los informes de supervision y saber si tienen un documento que tenga una relacion con este (padre-hijo)
	 * es decir si tienen una relación con una ficha de observación  
	 * @param idInstanciaProceso
	 * @param idSubcatDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docu.idDocumento, psd.idSubcatDocumento, pip.idInstanciaProceso, "
			+ "pvp.idValorParametro, pfp.idFaseProceso, docu.deAsuntoDocumento, "
			+ "docu.feOrigenDocumento, docu.coDocumentoSiged, docu.seDocumento, "
			+ "psd.noSubcatDocumento, dore.idDocumentoRelacion, pip.coTablaInstancia, "
			+ "docu.feEnvioDocumento, pip.nuExpedienteSiged, psd.flNumeradoPorSiged, "
			+ "psd.coTipoDocumentoSiged, pip.pgimProceso.idProceso, docu.feRechazo, docu.flIncumplimientoET "
			+ ") "
			+ "FROM PgimDocumento docu "
			+ "LEFT OUTER JOIN PgimDocumentoRelacion dore ON (dore.documentoPadre.idDocumento = docu.idDocumento AND dore.esRegistro = '1') "
			+ "LEFT OUTER JOIN PgimInstanciaProces pip ON (docu.pgimInstanciaProces.idInstanciaProceso = pip.idInstanciaProceso) "
			+ "LEFT OUTER JOIN PgimSubcategoriaDoc psd ON (docu.pgimSubcategoriaDoc.idSubcatDocumento = psd.idSubcatDocumento) "
			+ "LEFT OUTER JOIN PgimValorParametro pvp ON (docu.tipoOrigenDocumento.idValorParametro = pvp.idValorParametro) "
			+ "LEFT OUTER JOIN PgimFaseProceso pfp ON (docu.pgimFaseProceso.idFaseProceso = pfp.idFaseProceso) "
			+ "WHERE docu.esRegistro = '1' "
			+ "AND pip.esRegistro = '1' "
			+ "AND pip.idInstanciaProceso = :idInstanciaProceso "
			+ "AND psd.idSubcatDocumento = :idSubcatDocumento " 
			+ "ORDER BY docu.feOrigenDocumento DESC, docu.idDocumento DESC ")
	List<PgimDocumentoDTO> obtenerDocumentoInformeSupervisionAux(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idSubcatDocumento") Long idSubcatDocumento);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docu.idDocumento, psd.idSubcatDocumento, pip.idInstanciaProceso, "
			+ "pvp.idValorParametro, pfp.idFaseProceso, docu.deAsuntoDocumento, "
			+ "docu.feOrigenDocumento, docu.coDocumentoSiged, docu.seDocumento, "
			+ "psd.noSubcatDocumento, pdr.idDocumentoRelacion, pip.coTablaInstancia, "
			+ "docu.feEnvioDocumento, pip.nuExpedienteSiged, psd.flNumeradoPorSiged, "
			+ "psd.coTipoDocumentoSiged, pip.pgimProceso.idProceso, docu.feRechazo, docu.flIncumplimientoET "
			+ ") "
			+ "FROM PgimDocumento docu "
			+ "LEFT JOIN PgimDocumentoRelacion pdr ON pdr.pgimDocumento.idDocumento = docu.idDocumento "
			+ "LEFT JOIN PgimInstanciaProces pip ON docu.pgimInstanciaProces.idInstanciaProceso = pip.idInstanciaProceso "
			+ "LEFT JOIN PgimSubcategoriaDoc psd ON docu.pgimSubcategoriaDoc.idSubcatDocumento = psd.idSubcatDocumento "
			+ "LEFT JOIN PgimValorParametro pvp ON docu.tipoOrigenDocumento.idValorParametro = pvp.idValorParametro "
			+ "LEFT JOIN PgimFaseProceso pfp ON docu.pgimFaseProceso.idFaseProceso = pfp.idFaseProceso "
			+ "WHERE docu.esRegistro = '1' "
			+ "AND docu.idDocumento = :idDocumento ")
	PgimDocumentoDTO obtenerDocumentoPorId(@Param("idDocumento") Long idDocumento);

	/**
	 * Permite obtener la cantidad de informes de fiscalización de una determinada instancia de proceso.
	 * @param idInstanciaProceso
	 * @return
	 */
	@Query("SELECT COUNT(1) "
			+ "FROM PgimDocumento docu "
			+ "INNER JOIN docu.pgimSubcategoriaDoc suca "
			+ "INNER JOIN docu.pgimInstanciaProces inpr "
			+ "WHERE suca.idSubcatDocumento = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_SC_INFORME_SUPERVISION "
			+ "AND inpr.idInstanciaProceso = :idInstanciaProceso "
			+ "AND docu.esRegistro = '1' "
			+ "AND EXISTS "
			+ "( "
			+ "SELECT 1 "
			+ "FROM PgimDocumentoRelacion dore "
			+ "WHERE dore.documentoPadre.idDocumento = docu.idDocumento "
			+ "AND dore.tipoRelacionDocumento.coClaveTexto = :REDOC_OBSRVCION "
			+ "AND dore.esRegistro = '1' "
			+ ") "
			)
    Long obtenerNroInformesFiscalizacionConObs(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("REDOC_OBSRVCION") String REDOC_OBSRVCION);


	/**
	 * Permite obtener los documentos de subcategorías que están configuradas para
	 * registro de fecha de envío
	 * 
	 * @param idRelacionPaso
	 * @param idInstanciaProceso
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docu.idDocumento "
			+ ") "
			+ "FROM PgimDocumento docu "
			+ "WHERE "
//			+ "docu.feEnvioDocumento IS NULL "
			+ "docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
			+ "AND EXISTS ("
			+ "			   SELECT 1"
			+ "			   FROM PgimRelacionSubcat resc"
			+ "     			INNER JOIN resc.pgimSubcategoriaDoc scdo "
			+ "     			INNER JOIN resc.pgimRelacionPaso repa "
			+ "			   WHERE resc.flRegistrarFechaEnvio = '1' "
			+ "            AND scdo.idSubcatDocumento = docu.pgimSubcategoriaDoc.idSubcatDocumento "
			+ "			   AND repa.idRelacionPaso = :idRelacionPaso "
			+ "			   AND resc.esRegistro = '1' "
			+ "			   AND scdo.esRegistro = '1' "
			+ "			   AND repa.esRegistro = '1' "
			+ "			  ) "
			+ "AND docu.esRegistro = '1' ")
	List<PgimDocumentoDTO> listarDocumentosParaActualizarEnvio(
			@Param("idRelacionPaso") Long idRelacionPaso,
			@Param("idInstanciaProceso") Long idInstanciaProceso);
	
	/**
	 * Permite obtener la cantidad del documentos que se quiere copiar del siged de una determinada instancia de proceso.
	 * @param idInstanciaProceso 
	 * @paramidDocumentoSiged
	 * @return
	 */
	@Query("SELECT COUNT(1) "
			+ "FROM PgimDocumento docu "
			+ "WHERE docu.esRegistro = '1' "
			+ "AND docu.coDocumentoSigedPCopia = :idDocumentoSiged "
			+ "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
			)
    Long validarCopiadoAntecedente(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idDocumentoSiged") Long idDocumentoSiged);

	/**
	 * Permite obtener la lista de documentos de la instancia de proceso y subcategorías dadas.
	 * @param idInstanciaProceso
	 * @param idSubcategoriaDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTOResultado("
			+ "docu.idDocumento "
			+ ") "
			+ "FROM PgimDocumento docu "
			+ "WHERE 1 = 1"
			+ "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
			+ "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcategoriaDocumento "			
			+ "AND docu.esRegistro = '1' ")
    List<PgimDocumentoDTO> obtenerDocumentosPorSubcategoria(@Param("idInstanciaProceso") Long idInstanciaProceso,
			@Param("idSubcategoriaDocumento") Long idSubcategoriaDocumento);	
	
}
