package pe.gob.osinergmin.pgim.dtos;

public class PgimPasoSubcatDTOResultado extends PgimPasoSubcatDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasoSubcatRepository#obtenerConfigPasoSubcat()
	 * @param idPasoSubcat
	 * @param idPasoProceso
	 * @param idSubcatDocumento
	 * @param caDocsAdjuntar
	 * @param flValidaSoloNuevos
	 * @param flModificarEnviado
	 * @param flAdjuntadoManual
	 * @param flDefinirDestinatario
	 */
	public PgimPasoSubcatDTOResultado(Long idPasoSubcat, Long idPasoProceso, Long idSubcatDocumento,
			Integer caDocsAdjuntar, String flValidaSoloNuevos, String flModificarEnviado,
			String flAdjuntadoManual, String flDefinirDestinatario, String flAsociarDocNotificado) {
        super();

        this.setIdPasoSubcat(idPasoSubcat); 
        this.setIdPasoProceso(idPasoProceso);
        this.setIdSubcatDocumento(idSubcatDocumento);
        this.setCaDocsAdjuntar(caDocsAdjuntar);
        this.setFlValidaSoloNuevos(flValidaSoloNuevos);
        this.setFlModificarEnviado(flModificarEnviado);
		this.setFlAdjuntadoManual(flAdjuntadoManual);
		this.setFlDefinirDestinatario(flDefinirDestinatario);
		this.setFlAsociarDocNotificado(flAsociarDocNotificado);
    }

}
