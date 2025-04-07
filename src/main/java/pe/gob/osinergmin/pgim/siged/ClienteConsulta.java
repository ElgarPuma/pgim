package pe.gob.osinergmin.pgim.siged;

public class ClienteConsulta {
	
	private String codigoTipoIdentificacion;
	private String nroIdentificacion;
	
	public String getCodigoTipoIdentificacion() {
		return codigoTipoIdentificacion;
	}
	public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
		this.codigoTipoIdentificacion = codigoTipoIdentificacion;
	}
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}
	
	public static String getStringXmlFile(ClienteConsulta obj) {
		String xmlSource = "<cliente>\n" + 
				"    <codigoTipoIdentificacion>"+ obj.getCodigoTipoIdentificacion() +"</codigoTipoIdentificacion>\n" + 
				"    <nroIdentificacion>"+ obj.getNroIdentificacion() +"</nroIdentificacion>\n" +				
				"</cliente>\n";
        return xmlSource;
    }	
	
	

}
