package pe.gob.osinergmin.pgim.siged;

import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class DocumentoAnularInRO {

	private String nroExpediente;
	private String idDocumento;
	private String idUsuarioAnulacion;
	private String motivo;
	private String appNameInvokes;
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
	public static String getConstruirXmlFile(DocumentoAnularInRO obj, String carpetaTmp) throws Exception {
		String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<documentoAnularInRo>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>" + 
				"    <idDocumento>"+ obj.getIdDocumento() +"</idDocumento>\n" +
				"    <idUsuarioAnulacion>"+ obj.getIdUsuarioAnulacion() +"</idUsuarioAnulacion>\n" +
				"    <motivo>"+ obj.getMotivo() +"</motivo>\n" +
				"    <appNameInvokes>"+ ConstantesUtil.PARAM_appNameInvokes+"</appNameInvokes>\n" + 
				"</documentoAnularInRo>\n";
		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);
        return pathFile;
    }	
	
	public static String getStringXmlFile(DocumentoAnularInRO obj) {
		String xmlSource = "<anularDocumento>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" + 
				"    <idDocumento>"+ obj.getIdDocumento() +"</idDocumento>\n" +
				"    <idUsuarioAnulacion>"+ obj.getIdUsuarioAnulacion() +"</idUsuarioAnulacion>\n" +
				"    <motivo>"+ obj.getMotivo() +"</motivo>\n" +
				"    <appNameInvokes>"+ ConstantesUtil.PARAM_appNameInvokes+"</appNameInvokes>\n" + 
				"</anularDocumento>\n";
        return xmlSource;
    }	
	
}
