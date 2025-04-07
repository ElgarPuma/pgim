package pe.gob.osinergmin.pgim.siged;

public class ExpedienteAprobadoIn {

	private String asunto;
	private String contenido;
	private String destinatario;
	private String numeroExpediente;
	private String aprobacion;
	
	public String getAprobacion() {
		return aprobacion;
	}
	public void setAprobacion(String aprobacion) {
		this.aprobacion = aprobacion;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	
	public static String getStringXmlFile(ExpedienteAprobadoIn obj) {
		String xmlSource = "<expedienteAprobadoIn>\n" + 
				"    <asunto>"+ obj.getAsunto() +"</asunto>\n" + 
				"    <contenido>"+ obj.getContenido() +"</contenido>\n" +
				"    <destinatario>"+ obj.getDestinatario() +"</destinatario>\n" +
				"    <numeroExpediente>"+ obj.getNumeroExpediente() +"</numeroExpediente>\n" +
				"</expedienteAprobadoIn>\n";
        return xmlSource;
    }
	
	
	
}
