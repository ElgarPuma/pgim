package pe.gob.osinergmin.pgim.dtos;

public class PgimSubcategoriaDocDTOResultado extends PgimSubcategoriaDocDTO {
	
    /**
    * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#listarSubCategorias()
     * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#listarSubCategoriasFase()
     * 
     * @param idSubcatDocumento
     * @param noSubcatDocumento
     * @param idCategoriaDocumento
     * @param flNumeradoPorSiged
     * @param coTipoDocumentoSiged
     * @param idTipoOrigenDocumento
     * @param idEspecialidad
     * @param deExtensionesPermitidas
     * @param flReservaNumero
     * @param flAdjuntadoManual
     */
	public PgimSubcategoriaDocDTOResultado(Long idSubcatDocumento, String noSubcatDocumento, Long idCategoriaDocumento,
			String flNumeradoPorSiged,Long coTipoDocumentoSiged, Long idTipoOrigenDocumento, Long idEspecialidad, 
            String deExtensionesPermitidas, String flReservaNumero) {
        super();

        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setIdCategoriaDocumento(idCategoriaDocumento);
        this.setFlNumeradoPorSiged(flNumeradoPorSiged);
        this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
        this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setDeExtensionesPermitidas(deExtensionesPermitidas);
        this.setFlReservaNumero(flReservaNumero);
    }

    /**
     * 
     * @param idSubcatDocumento
     * @param coSubcatDocumento
     * @param noSubcatDocumento
     * @param idCategoriaDocumento
     * @param flNumeradoPorSiged
     * @param coTipoDocumentoSiged
     * @param idTipoOrigenDocumento
     * @param idEspecialidad
     * @param deExtensionesPermitidas
     * @param flReservaNumero
     * @param descFlAdjuntadoManual
     * @param descFlAdjuntadoManualPropia
     * @param idFaseProceso
     */
    public PgimSubcategoriaDocDTOResultado(Long idSubcatDocumento, String coSubcatDocumento, String noSubcatDocumento, Long idCategoriaDocumento,
            String flNumeradoPorSiged, Long coTipoDocumentoSiged, Long idTipoOrigenDocumento, Long idEspecialidad,
            String deExtensionesPermitidas, String flReservaNumero, String descFlAdjuntadoManual,
            String descFlAdjuntadoManualPropia, Long idFaseProceso) {
        super();

        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setCoSubcatDocumento(coSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setIdCategoriaDocumento(idCategoriaDocumento);
        this.setFlNumeradoPorSiged(flNumeradoPorSiged);
        this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
        this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setDeExtensionesPermitidas(deExtensionesPermitidas);
        this.setFlReservaNumero(flReservaNumero);
        this.setDescFlAdjuntadoManual(descFlAdjuntadoManual);
        this.setDescFlAdjuntadoManualPropia(descFlAdjuntadoManualPropia);
        this.setDescIdFaseProceso(idFaseProceso);
    }  

    /***
     * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#listarSubCategoriasPorPaso()
     * @param idSubcatDocumento
     * @param noSubcatDocumento
     * @param idCategoriaDocumento
     * @param flNumeradoPorSiged
     * @param coTipoDocumentoSiged
     * @param idTipoOrigenDocumento
     * @param idEspecialidad
     * @param deExtensionesPermitidas
     * @param flReservaNumero
     * @param descFlAdjuntadoManual
     * @param descFlAdjuntadoManualPropia
     */
    public PgimSubcategoriaDocDTOResultado(Long idSubcatDocumento, String noSubcatDocumento, Long idCategoriaDocumento,
            String flNumeradoPorSiged, Long coTipoDocumentoSiged, Long idTipoOrigenDocumento, Long idEspecialidad,
            String deExtensionesPermitidas, String flReservaNumero, String descFlAdjuntadoManual,
            String descFlAdjuntadoManualPropia) {
        super();

        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setIdCategoriaDocumento(idCategoriaDocumento);
        this.setFlNumeradoPorSiged(flNumeradoPorSiged);
        this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
        this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setDeExtensionesPermitidas(deExtensionesPermitidas);
        this.setFlReservaNumero(flReservaNumero);
        this.setDescFlAdjuntadoManual(descFlAdjuntadoManual);
        this.setDescFlAdjuntadoManualPropia(descFlAdjuntadoManualPropia);
    }  
    
    /***
     * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#listarSubCategoriasFasePorIdPaso()
     * 
     * @param idSubcatDocumento
     * @param coSubcatDocumento
     * @param noSubcatDocumento
     * @param idCategoriaDocumento
     * @param flNumeradoPorSiged
     * @param coTipoDocumentoSiged
     * @param idTipoOrigenDocumento
     * @param idEspecialidad
     * @param deExtensionesPermitidas
     * @param flReservaNumero
     * @param descFlAdjuntadoManual
     * @param descFlAdjuntadoManualPropia
     */
    public PgimSubcategoriaDocDTOResultado(Long idSubcatDocumento, String coSubcatDocumento, String noSubcatDocumento, Long idCategoriaDocumento,
            String flNumeradoPorSiged, Long coTipoDocumentoSiged, Long idTipoOrigenDocumento, Long idEspecialidad,
            String deExtensionesPermitidas, String flReservaNumero, String descFlAdjuntadoManual,
            String descFlAdjuntadoManualPropia) {
        super();

        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setCoSubcatDocumento(coSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setIdCategoriaDocumento(idCategoriaDocumento);
        this.setFlNumeradoPorSiged(flNumeradoPorSiged);
        this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
        this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setDeExtensionesPermitidas(deExtensionesPermitidas);
        this.setFlReservaNumero(flReservaNumero);
        this.setDescFlAdjuntadoManual(descFlAdjuntadoManual);
        this.setDescFlAdjuntadoManualPropia(descFlAdjuntadoManualPropia);
    }  

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#obtenerSubcategoriaDocPorId()
	 * @param idSubcatDocumento
	 * @param idCategoriaDocumento
	 * @param coCategoriaDocumento
	 * @param noCategoriaDocumento
	 * @param idEspecialidad
	 * @param idTipoOrigenDocumento
	 * @param idTipoFirma
	 * @param coSubcatDocumento
	 * @param noSubcatDocumento
	 * @param flNumeradoPorSiged
	 * @param coTipoDocumentoSiged
	 * @param idTipoExtensionGen
	 * @param descNoTipoExtensionGen
	 * @param noArchivoReserva
	 */
	public PgimSubcategoriaDocDTOResultado(Long idSubcatDocumento, Long idCategoriaDocumento, String coCategoriaDocumento, 
			String noCategoriaDocumento, Long idEspecialidad, Long idTipoOrigenDocumento, Long idTipoFirma, String coSubcatDocumento, 
			String noSubcatDocumento, String flNumeradoPorSiged, Long coTipoDocumentoSiged, Long idTipoExtensionGen,
			String descNoTipoExtensionGen, String deExtensionesPermitidas, String noArchivoReserva, String descFlNumeradoPorSiged) {
        super();

        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setIdCategoriaDocumento(idCategoriaDocumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setIdTipoOrigenDocumento(idTipoOrigenDocumento);
        this.setIdTipoFirma(idTipoFirma);
        this.setCoSubcatDocumento(coSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setFlNumeradoPorSiged(flNumeradoPorSiged);
        this.setCoTipoDocumentoSiged(coTipoDocumentoSiged);
        this.setIdTipoExtensionGen(idTipoExtensionGen);
        this.setDescNoTipoExtensionGen(descNoTipoExtensionGen);
        this.setDeExtensionesPermitidas(deExtensionesPermitidas);
        this.setDescCoCategoriaDocumento(coCategoriaDocumento);
        this.setDescNoCategoriaDocumento(noCategoriaDocumento);
        this.setNoArchivoReserva(noArchivoReserva);
        this.setDescFlNumeradoPorSiged(descFlNumeradoPorSiged);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#listarSubcategoriasFirmadasPorFiscalizador()
     * @see pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository#listarSubcatBibliografia()
     * 
     * @param idSubcatDocumento
     * @param idCategoriaDocumento
     * @param coSubcatDocumento
     * @param noSubcatDocumento
     * @param descCoCategoriaDocumento
     * @param descNoCategoriaDocumento
     */
    public PgimSubcategoriaDocDTOResultado( Long idSubcatDocumento, Long idCategoriaDocumento, 
    		String coSubcatDocumento, String noSubcatDocumento, String descCoCategoriaDocumento, String descNoCategoriaDocumento) {
        super();

        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setIdCategoriaDocumento(idCategoriaDocumento);
        this.setCoSubcatDocumento(coSubcatDocumento);
        this.setNoSubcatDocumento(noSubcatDocumento);
        this.setDescCoCategoriaDocumento(descCoCategoriaDocumento);
        this.setDescNoCategoriaDocumento(descNoCategoriaDocumento);
    }

}
