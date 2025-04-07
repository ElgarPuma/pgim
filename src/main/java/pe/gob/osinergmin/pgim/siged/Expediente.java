package pe.gob.osinergmin.pgim.siged;

public class Expediente {
	
	private String observacionArchivar;
	private String nroExpediente;
		
	public String getObservacionArchivar() {
		return observacionArchivar;
	}
	public void setObservacionArchivar(String observacionArchivar) {
		this.observacionArchivar = observacionArchivar;
	}	
	public String getNroExpediente() {
		return nroExpediente;
	}
	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}
	public static String getStringXmlFile(Expediente obj) {
		String xmlSource = "<expediente>\n" + 
				"    <observacionArchivar>"+ obj.getObservacionArchivar() +"</observacionArchivar>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" +
				"</expediente>\n";
        return xmlSource;
    }	
	
}
