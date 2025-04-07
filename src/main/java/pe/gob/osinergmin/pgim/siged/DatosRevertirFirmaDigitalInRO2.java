package pe.gob.osinergmin.pgim.siged;

import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class DatosRevertirFirmaDigitalInRO2 {

	private String appNameInvokes;
	private String idArchivo;
	private String idUsuario;
	private String motivoReversion;

	public String getIdArchivo() {
		return idArchivo;
	}
	public void setArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMotivoReversion() {
		return motivoReversion;
	}
	public void setMotivoReversion(String motivoReversion) {
		this.motivoReversion = motivoReversion;
	}
	public String getAppNameInvokes() {
		return appNameInvokes;
	}
	public void setAppNameInvokes(String appNameInvokes) {
		this.appNameInvokes = appNameInvokes;
	}
	public static String getConstruirXmlFile(DatosRevertirFirmaDigitalInRO2 obj, String carpetaTmp) throws Exception {
		String xmlSource = 
				"<datosRevertirFirmaDigitalInRO>" + 
				"    <appNameInvokes>"+ ConstantesUtil.PARAM_appNameInvokes+"</appNameInvokes>" +
				"    <idUsuario>"+ obj.getIdUsuario() +"</idUsuario>" +
				"	    <archivosRevertir>" +	
				"	    	<archivoRevertir>" +	
				"    			<idArchivo>"+ obj.getIdArchivo() +"</idArchivo>" +
				"    			<motivoReversion>"+ obj.getMotivoReversion() +"</motivoReversion>" +
				"	    	</archivoRevertir>" +	
				"	     </archivosRevertir>" +	
				"</datosRevertirFirmaDigitalInRO>";
		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);
        return pathFile;
    }	
	
	public static String getStringXmlFile(DatosRevertirFirmaDigitalInRO2 obj) {
		String xmlSource = 
				"<datosRevertirFirmaDigitalInRO>" + 
				"    <appNameInvokes>"+ ConstantesUtil.PARAM_appNameInvokes+"</appNameInvokes>" +
				"    <idUsuario>"+ obj.getIdUsuario() +"</idUsuario>" +
				"	    <archivosRevertir>" +	
				"	    	<archivoRevertir>" +	
				"    			<idArchivo>"+ obj.getIdArchivo() +"</idArchivo>" +
				"    			<motivoReversion>"+ obj.getMotivoReversion() +"</motivoReversion>" +
				"	    	</archivoRevertir>" +	
				"	     </archivosRevertir>" +	
				"</datosRevertirFirmaDigitalInRO>";
        return xmlSource;
    }	
	
}
