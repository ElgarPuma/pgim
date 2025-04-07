package pe.gob.osinergmin.pgim.siged;

public class DevolverExpedienteInRO {

	private String nroExpediente;
	private String asunto;
	private String motivo;
	private String idUsuario;
	
	public String getNroExpediente() {
		return nroExpediente;
	}
	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public static String getStringXmlFile(DevolverExpedienteInRO obj) {
		String xmlSource = "<devolverExpediente>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" + 
				"    <asunto>"+ obj.getAsunto() +"</asunto>\n" +
				"    <motivo>"+ obj.getMotivo() +"</motivo>\n" +
				"    <idUsuario>"+ obj.getIdUsuario() +"</idUsuario>\n" +
				"</devolverExpediente>\n";
        return xmlSource;
    }
	
	
}
