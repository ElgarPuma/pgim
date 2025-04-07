package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimBiblioArchivoDTOResultado extends PgimBiblioArchivoDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioArchivoRepository#obtenerBiblioArchivoPorId()
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioArchivoRepository#listarBiblioArchivosPaginado()
	 * 
	 * @param idBiblioArchivo
	 * @param idBiblioDocumento
	 * @param nuArchivoEcm
	 * @param nuCarpetaEcm
	 * @param noBibilioArchivo
	 * @param descNuDocumento
	 * @param descNoCategoriaDocumento
	 * @param descCoCategoriaDocumento
	 * @param descNoSubcatDocumento
	 * @param descCoSubcatDocumento
	 * @param descDeAsuntoDocumento
	 * @param descNoTipoMedioIngreso
	 * @param descFeEmisionDocumento
	 * @param descNoEmisorDocumento
	 * @param descCoDiEmisorDocumento
	 */
	public PgimBiblioArchivoDTOResultado(Long idBiblioArchivo, Long idBiblioDocumento, String nuArchivoEcm, 
			String nuCarpetaEcm, String noBibilioArchivo, String descNuDocumento, 
			String descNoCategoriaDocumento, String descCoCategoriaDocumento, 
			String descNoSubcatDocumento, String descCoSubcatDocumento, 
			String descDeAsuntoDocumento, String descNoTipoMedioIngreso, Date descFeEmisionDocumento, 
			String descNoEmisorDocumento, String descCoDiEmisorDocumento
			) {

		super();
		this.setIdBiblioArchivo(idBiblioArchivo);
		this.setIdBiblioDocumento(idBiblioDocumento);
		this.setNuArchivoEcm(nuArchivoEcm);
		this.setNuCarpetaEcm(nuCarpetaEcm);
		this.setNoBibilioArchivo(noBibilioArchivo);
		this.setDescNuDocumento(descNuDocumento);
		this.setDescNoCategoriaDocumento(descNoCategoriaDocumento);
		this.setDescCoCategoriaDocumento(descCoCategoriaDocumento);
		this.setDescNoSubcatDocumento(descNoSubcatDocumento);
		this.setDescCoSubcatDocumento(descCoSubcatDocumento);
		this.setDescDeAsuntoDocumento(descDeAsuntoDocumento);
		this.setDescNoTipoMedioIngreso(descNoTipoMedioIngreso);
		this.setDescFeEmisionDocumento(descFeEmisionDocumento);
		this.setDescNoEmisorDocumento(descNoEmisorDocumento);
		this.setDescCoDiEmisorDocumento(descCoDiEmisorDocumento);
		
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.BiblioArchivoRepository#listarBiblioArchivoPorIdDoc()
	 * 
	 * @param idBiblioArchivo
	 * @param idBiblioDocumento
	 * @param nuArchivoEcm
	 * @param nuCarpetaEcm
	 * @param noBibilioArchivo
	 */
	public PgimBiblioArchivoDTOResultado(Long idBiblioArchivo, Long idBiblioDocumento, String nuArchivoEcm, 
			String nuCarpetaEcm, String noBibilioArchivo
			) {

		super();
		this.setIdBiblioArchivo(idBiblioArchivo);
		this.setIdBiblioDocumento(idBiblioDocumento);
		this.setNuArchivoEcm(nuArchivoEcm);
		this.setNuCarpetaEcm(nuCarpetaEcm);
		this.setNoBibilioArchivo(noBibilioArchivo);
	}


}
