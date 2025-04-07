package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimDocumentoDTOResultado extends PgimDocumentoDTO{

	/**
     * Este constructor sirve para la lista de documentos para todas las fases. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository #obtenerDocumentosInstancia()
	 * 
	 * @param idInstanciaProceso
	 * @param idDocumento
	 * @param feOrigenDocumento
	 * @param noSubcatDocumento
	 * @param noCategoriaDocumento
	 * @param coDocumentoSiged
	 * @param deAsuntoDocumento
	 * @param deOrigen
	 * @param tipoRegistro
	 * @param idSubcatDocumento
	 * @param seDocumento
	 * @param idFaseProceso
	 * @param noFaseProceso
	 * @param deFaseProceso
	 * @param flNumeradoPorSiged
	 * @param feEnvioDocumento
	 * @param flEtiquetaNotifActiva
	 * @param idDocEtiquetaNotif
	 * @param descFlPermiteNotifPorArchivo
	 * @param feRechazo
	 */
	public PgimDocumentoDTOResultado(Long idInstanciaProceso, Long idDocumento, Date feOrigenDocumento, 
			String noSubcatDocumento, String noCategoriaDocumento, Long coDocumentoSiged, 
			String deAsuntoDocumento, String deOrigen, String tipoRegistro, 
			Long idSubcatDocumento, Long seDocumento, Long idFaseProceso, 
			String noFaseProceso, String deFaseProceso, String flNumeradoPorSiged,
			Date feEnvioDocumento, Long idTipoOrigenDocumento, Long coTipoDocumentoSiged, 
			Long idProceso, String flReservaActiva, String flEtiquetaNotifActiva, 
			Long idDocEtiquetaNotif, String descFlPermiteNotifPorArchivo, Date feRechazo, String descFlEtiquetaNotifS) {
		super();

		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdDocumento(idDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setDeOrigen(deOrigen);
		this.setTipoRegistro(tipoRegistro);
		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setSeDocumento(seDocumento);
		this.setIdFaseProceso(idFaseProceso);
		this.setNoFaseProceso(noFaseProceso);
		this.setDeFaseProceso(deFaseProceso);
		this.setFlNumeradoPorSiged(flNumeradoPorSiged);
		this.setFeEnvioDocumento(feEnvioDocumento);		
		this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
		this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
		this.setIdProceso(idProceso);
		this.setFlReservaActiva(flReservaActiva);
		this.setDescFlEtiqNotifActiva(flEtiquetaNotifActiva);
		this.setDescIdDocEtiquetaNotif(idDocEtiquetaNotif);
		this.setDescFlPermiteNotifPorArchivo(descFlPermiteNotifPorArchivo);
		this.setFeRechazo(feRechazo);
		this.setDescFlEtiquetaNotifS(descFlEtiquetaNotifS);
	}
	
	/**
     * Este constructor sirve para la lista de documentos por fase. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository #obtenerDocumentosInstanciaFase()
	 * 
	 * @param idInstanciaProceso
	 * @param idDocumento
	 * @param feOrigenDocumento
	 * @param noSubcatDocumento
	 * @param noCategoriaDocumento
	 * @param coDocumentoSiged
	 * @param deAsuntoDocumento
	 * @param deOrigen
	 * @param tipoRegistro
	 * @param idSubcatDocumento
	 * @param seDocumento
	 * @param idFaseProceso
	 * @param flNotificado
	 * @param descFlNotificableE
	 * @param valorDistinto
	 * @param flNumeradoPorSiged
	 * @param idCategoriaDocumento
	 * @param feEnvioDocumento
	 * @param flEtiquetaNotifActiva
	 * @param idDocEtiquetaNotif
	 * @param idTipoOrigenDocumento
	 * @param coTipoDocumentoSiged
	 * @param idProceso
	 * @param flReservaActiva
	 * @param desFlPermiteNotifPorArchivo
	 * @param feRechazo
	 */
	public PgimDocumentoDTOResultado(Long idInstanciaProceso, Long idDocumento, Date feOrigenDocumento,
			String noSubcatDocumento, String noCategoriaDocumento, Long coDocumentoSiged,
			String deAsuntoDocumento, String deOrigen, String tipoRegistro, 
			Long idSubcatDocumento, Long seDocumento, Long idFaseProceso,
			String flNotificado, String descFlNotificableE, Integer valorDistinto,
			String flNumeradoPorSiged, Long idCategoriaDocumento, Date feEnvioDocumento, String flEtiquetaNotifActiva, Long idDocEtiquetaNotif,
			Long idTipoOrigenDocumento, Long coTipoDocumentoSiged, Long idProceso, String flReservaActiva, String desFlPermiteNotifPorArchivo,
			Date feRechazo, String descFlEtiquetaNotifS) {
		super();

		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdDocumento(idDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setDeOrigen(deOrigen);
		this.setTipoRegistro(tipoRegistro);
		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setSeDocumento(seDocumento);
		this.setIdFaseProceso(idFaseProceso);
		this.setFlNotificado(flNotificado);
		this.setDescFlNotificableE(descFlNotificableE);
		this.setFlNumeradoPorSiged(flNumeradoPorSiged);
		this.setIdCategoriaDocumento(idCategoriaDocumento);
		this.setFeEnvioDocumento(feEnvioDocumento);
		this.setDescFlEtiqNotifActiva(flEtiquetaNotifActiva);
		this.setDescIdDocEtiquetaNotif(idDocEtiquetaNotif);
		this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
		this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
		this.setIdProceso(idProceso);
		this.setFlReservaActiva(flReservaActiva);
		this.setDescFlPermiteNotifPorArchivo(desFlPermiteNotifPorArchivo);
		this.setFeRechazo(feRechazo);
		this.setDescFlEtiquetaNotifS(descFlEtiquetaNotifS);
	}

	/**
	 * Informe de supervision 
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository #obtenerDocPorInstanciaYSubCategoria()
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository #listarDocPorInstanciaYSubCategoria()
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository #listarDocPorInstanciaYSubcatYFase()
	 * 
	 * @param idSubcatDocumento
	 * @param idInstanciaProceso
	 * @param idDocumento
	 * @param feOrigenDocumento
	 * @param noSubcatDocumento
	 * @param noCategoriaDocumento
	 * @param coDocumentoSiged
	 * @param deAsuntoDocumento
	 * @param deOrigen
	 * @param tipoRegistro
	 * @param seDocumento
	 * @param idFaseProceso
	 * @param flNumeradoPorSiged
	 * @param feEnvioDocumento
	 * @param flEtiquetaNotifActiva
	 * @param idDocEtiquetaNotif
	 * @param idTipoOrigenDocumento
	 * @param coTipoDocumentoSiged
	 * @param idProceso
	 * @param flReservaActiva
	 * @param descFlPermiteNotifPorArchivo
	 * @param feRechazo
	 */
	public PgimDocumentoDTOResultado(Long idSubcatDocumento, Long idInstanciaProceso, Long idDocumento, Date feOrigenDocumento, String noSubcatDocumento,
			String noCategoriaDocumento,Long coDocumentoSiged, String deAsuntoDocumento, String deOrigen, String tipoRegistro,Long seDocumento, Long idFaseProceso,
			String flNumeradoPorSiged, Date feEnvioDocumento, String flEtiquetaNotifActiva, Long idDocEtiquetaNotif,
			Long idTipoOrigenDocumento, Long coTipoDocumentoSiged, Long idProceso, String flReservaActiva, String descFlPermiteNotifPorArchivo,
			Date feRechazo, String descFlEtiquetaNotifS) {
		super();

		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdDocumento(idDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setDeOrigen(deOrigen);
		this.setTipoRegistro(tipoRegistro);
		this.setSeDocumento(seDocumento);
		this.setIdFaseProceso(idFaseProceso);
		this.setFlNumeradoPorSiged(flNumeradoPorSiged);
		this.setFeEnvioDocumento(feEnvioDocumento);
		this.setDescFlEtiqNotifActiva(flEtiquetaNotifActiva);
		this.setDescIdDocEtiquetaNotif(idDocEtiquetaNotif);
		this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
		this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
		this.setIdProceso(idProceso);
		this.setFlReservaActiva(flReservaActiva);
		this.setDescFlPermiteNotifPorArchivo(descFlPermiteNotifPorArchivo);
		this.setFeRechazo(feRechazo);
		this.setDescFlEtiquetaNotifS(descFlEtiquetaNotifS);
	}
	
	public PgimDocumentoDTOResultado(Long idInstanciaProceso, Long idDocumento, String feOrigenDocumentoDesc, String noSubcatDocumento,
			String noCategoriaDocumento, String deAsuntoDocumento) {
		super();
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdDocumento(idDocumento);
		this.setFeOrigenDocumentoDesc(feOrigenDocumentoDesc);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
	}
	
	//
	/**
	 * obtenerDocumentoById 
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository #obtenerDocumentoById()
	 */
	public PgimDocumentoDTOResultado(String coCategoriaDocumento, String coSubcatDocumento, Long idDocumento,
			String noValorParametro, String deAsuntoDocumento, Date feOrigenDocumento, String noSubcatDocumento,
			String noCategoriaDocumento, Long coDocumentoSiged, Integer existeDocumentoSiged,Long idFaseProceso) {
		super();
		this.setCoCategoriaDocumento(coCategoriaDocumento);
		this.setCoSubcatDocumento(coSubcatDocumento);
		this.setIdDocumento(idDocumento);
		this.setNoValorParametro(noValorParametro);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento); 
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setExisteDocumentoSiged(existeDocumentoSiged);
		this.setIdFaseProceso(idFaseProceso);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentoPgimById()
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentoPgimByIdDocumentoSiged()
	 */
	public PgimDocumentoDTOResultado(Long idDocumento, Long idSubcatDocumento, Long idInstanciaProceso,
			Long idTipoOrigenDocumento, String deAsuntoDocumento, Date feOrigenDocumento, Long coDocumentoSiged,
			Long seDocumento, Long idCategoriaDocumento,Long idFaseProceso, String flNumeradoPorSiged, String flReservaActiva) {
		super();

		this.setIdDocumento(idDocumento);
		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setSeDocumento(seDocumento); 
		this.setIdCategoriaDocumento(idCategoriaDocumento);
		this.setIdFaseProceso(idFaseProceso);
		this.setFlNumeradoPorSiged(flNumeradoPorSiged);
		this.setFlReservaActiva(flReservaActiva);
	}
	
	//
	/**
	 * obtenerDocumentosSiged 
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentosSiged()
	 *
	 */
	public PgimDocumentoDTOResultado(String coCategoriaDocumento, String coSubcatDocumento, Long idDocumento,
			String noValorParametro, String deAsuntoDocumento, Date feOrigenDocumento, String noSubcatDocumento,
			String noCategoriaDocumento, Long coDocumentoSiged, Integer existeDocumentoSiged, Long coTipoDocumentoSiged, String flNumeradoPorSiged,
			Long idSubcatDocumento, Long idInstanciaProceso, Long idTipoOrigenDocumento, Long seDocumento, 
			Long idCategoriaDocumento, Long idProceso, Long coTablaInstancia,Long idFaseProceso) {
		super();
		this.setCoCategoriaDocumento(coCategoriaDocumento);
		this.setCoSubcatDocumento(coSubcatDocumento);
		this.setIdDocumento(idDocumento);
		this.setNoValorParametro(noValorParametro);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento); 
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setExisteDocumentoSiged(existeDocumentoSiged);
		this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
		this.setFlNumeradoPorSiged(flNumeradoPorSiged);
		this.setIdSubcatDocumento(idSubcatDocumento); 
		this.setIdInstanciaProceso(idInstanciaProceso); 
		this.setIdTipoOrigenDocumento(idTipoOrigenDocumento); 
		this.setSeDocumento(seDocumento); 
		this.setIdCategoriaDocumento(idCategoriaDocumento);
		this.setIdProceso(idProceso);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setIdFaseProceso(idFaseProceso);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerAprobacionResultadoPlazo()
	 * @param descEntregable
	 * @param descFirmadoEl
	 * @param descPresentadoEl
	 * @param descDiasTranscurridos
	 */
	public PgimDocumentoDTOResultado(String descEntregable, Date descFirmadoEl,
		Date descPresentadoEl, Double descDiasTranscurridos) {
        super();
        this.setDescEntregable(descEntregable);
        this.setDescFirmadoEl(descFirmadoEl);
        this.setDescPresentadoEl(descPresentadoEl);
        this.setDescDiasTranscurridos(descDiasTranscurridos);
    }
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentoPorId()
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentosDescendentes()
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentoObsAprob()
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#obtenerDocumentoInformeSupervisionAux()
	 * 
	 * @param idDocumento
	 * @param idSubcatDocumento
	 * @param idInstanciaProceso
	 * @param idTipoOrigenDocumento
	 * @param idFaseProceso
	 * @param deAsuntoDocumento
	 * @param feOrigenDocumento
	 * @param coDocumentoSiged
	 * @param seDocumento
	 * @param noSubcatDocumento
	 * @param descIdDocumentoRelacion
	 * @param coTablaInstancia
	 * @param feEnvioDocumento
	 * @param nuExpedienteSiged
	 * @param flNumeradoPorSiged
	 * @param coTipoDocumentoSiged
	 * @param idProceso
	 * @param feRechazo
	 * @param flIncumplimientoET
	 */
	public PgimDocumentoDTOResultado(Long idDocumento, Long idSubcatDocumento, Long idInstanciaProceso,
			Long idTipoOrigenDocumento, Long idFaseProceso, String deAsuntoDocumento, 
			Date feOrigenDocumento, Long coDocumentoSiged, Long seDocumento, 
			String noSubcatDocumento, Long descIdDocumentoRelacion, Long coTablaInstancia,
			Date feEnvioDocumento, String nuExpedienteSiged, String flNumeradoPorSiged, 
			Long coTipoDocumentoSiged, Long idProceso, Date feRechazo, String flIncumplimientoET) {
		super();

		this.setIdDocumento(idDocumento);
		this.setIdSubcatDocumento(idSubcatDocumento);
		this.setIdInstanciaProceso(idInstanciaProceso);
		this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
		this.setIdFaseProceso(idFaseProceso);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setFeOrigenDocumento(feOrigenDocumento);
		this.setCoDocumentoSiged(coDocumentoSiged);
		this.setSeDocumento(seDocumento); 
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setDescIdDocumentoRelacion(descIdDocumentoRelacion);
		this.setCoTablaInstancia(coTablaInstancia);
		this.setFeEnvioDocumento(feEnvioDocumento);
		this.setNuExpedienteSiged(nuExpedienteSiged);
		this.setFlNumeradoPorSiged(flNumeradoPorSiged);
		this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
		this.setIdProceso(idProceso);
		this.setFeRechazo(feRechazo);
		this.setFlIncumplimientoET(flIncumplimientoET);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.DocumentoRepository#listarDocumentosParaActualizarEnvio()
	 * @param idDocumento
	 */
	public PgimDocumentoDTOResultado(Long idDocumento) {
		super();

		this.setIdDocumento(idDocumento);
	}
	
}
