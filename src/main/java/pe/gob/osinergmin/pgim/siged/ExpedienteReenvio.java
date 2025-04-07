package pe.gob.osinergmin.pgim.siged;

public class ExpedienteReenvio {

		private String asunto;
		private String contenido;
		private String motivo;
		private Long idDestinatario;
		private Long idRemitente;
		private String numeroExpediente;
		private String aprobacion;

		public Long getIdDestinatario() {
			return idDestinatario;
		}
		public void setIdDestinatario(Long idDestinatario) {
			this.idDestinatario = idDestinatario;
		}
		public Long getIdRemitente() {
			return idRemitente;
		}
		public void setIdRemitente(Long idRemitente) {
			this.idRemitente = idRemitente;
		}
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
		public String getMotivo() {
			return motivo;
		}
		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}
		public String getNumeroExpediente() {
			return numeroExpediente;
		}
		public void setNumeroExpediente(String numeroExpediente) {
			this.numeroExpediente = numeroExpediente;
		}
		public static String getStringXmlFile(ExpedienteReenvio obj) {
			String xmlSource = "<expreenvio>\n" + 
					"    <asunto>"+ obj.getAsunto() +"</asunto>\n" + 
					"    <contenido>"+ obj.getContenido() +"</contenido>\n" +
					"    <destinatario>"+ obj.getIdDestinatario() +"</destinatario>\n" +
					"    <numeroExpediente>"+ obj.getNumeroExpediente() +"</numeroExpediente>\n" +
					"</expreenvio>\n";
	        return xmlSource;
	    }	
		
	
	
}
