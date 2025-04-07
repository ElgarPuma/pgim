package pe.gob.osinergmin.pgim.siged;

import pe.gob.osinergmin.pgim.utils.CommonsUtil;

public class ArchivoAnularInRO {

	private String nroExpediente;
	private String idDocumento;
	private String idUsuarioAnulacion;
	private String idArchivo;
	private String motivo;
	private String appNameInvokes;
	
	public String getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}
	public String getNroExpediente() {
		return nroExpediente;
	}
	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}
	public String getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getIdUsuarioAnulacion() {
		return idUsuarioAnulacion;
	}
	public void setIdUsuarioAnulacion(String idUsuarioAnulacion) {
		this.idUsuarioAnulacion = idUsuarioAnulacion;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getAppNameInvokes() {
		return appNameInvokes;
	}
	public void setAppNameInvokes(String appNameInvokes) {
		this.appNameInvokes = appNameInvokes;
	}
	public static String getConstruirXmlFile(ArchivoAnularInRO obj, String carpetaTmp) throws Exception {
		String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<ArchivoAnularInRO>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" + 
				"    <idDocumento>"+ obj.getIdDocumento() +"</idDocumento>\n" +
				"    <idArchivo>"+ obj.getIdArchivo() +"</idArchivo>\n" +
				"    <idUsuarioAnulacion>"+ obj.getIdUsuarioAnulacion() +"</idUsuarioAnulacion>\n" +
				"    <motivo>"+ obj.getMotivo() +"</motivo>\n" +
				"</ArchivoAnularInRO>\n";
		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);
        return pathFile;
    }	
	
	public static String getStringXmlFile(ArchivoAnularInRO obj) {
		String xmlSource = "<anularArchivo>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" + 
				"    <idDocumento>"+ obj.getIdDocumento() +"</idDocumento>\n" +
				"    <idArchivo>"+ obj.getIdArchivo() +"</idArchivo>\n" +
				"    <idUsuarioAnulacion>"+ obj.getIdUsuarioAnulacion() +"</idUsuarioAnulacion>\n" +
				"    <motivo>"+ obj.getMotivo() +"</motivo>\n" +
				"</anularArchivo>\n";
        return xmlSource;
    }	
	
}
