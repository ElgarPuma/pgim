/**
 * 
 */
package pe.gob.osinergmin.pgim.pido;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class IntegracionPido {
	
	List<PersonaNatural> lstPersonaNatural;
	List<PersonaJuridica> lstPersonaJuridica;
	protected String coResultado;
    protected String deResultado;
    protected String numeroDNIoCE;
    protected String numeroRUC;
    protected String UsuarioPido;
    protected String PasswordPido;
    
    
    
    public String getNumeroDNIoCE() {
		return numeroDNIoCE;
	}

	public void setNumeroDNIoCE(String numeroDNIoCE) {
		this.numeroDNIoCE = numeroDNIoCE;
	}

	public String getNumeroRUC() {
		return numeroRUC;
	}

	public void setNumeroRUC(String numeroRUC) {
		this.numeroRUC = numeroRUC;
	}
    
    public String getUsuarioPido() {
		return UsuarioPido;
	}

	public void setUsuarioPido(String usuarioPido) {
		UsuarioPido = usuarioPido;
	}

	public String getPasswordPido() {
		return PasswordPido;
	}

	public void setPasswordPido(String passwordPido) {
		PasswordPido = passwordPido;
	}

	public List<PersonaNatural> getLstPersonaNatural() {
		return lstPersonaNatural;
	}
    
    public void setLstPersonaNatural(List<PersonaNatural> lstPersonaNatural) {
		this.lstPersonaNatural = lstPersonaNatural;
	}

	public List<PersonaJuridica> getLstPersonaJuridica() {
		return lstPersonaJuridica;
	}

	public void setLstPersonaJuridica(List<PersonaJuridica> lstPersonaJuridica) {
		this.lstPersonaJuridica = lstPersonaJuridica;
	}

	public String getCoResultado() {
		return coResultado;
	}

	public void setCoResultado(String coResultado) {
		this.coResultado = coResultado;
	}

	public String getDeResultado() {
		return deResultado;
	}

	public void setDeResultado(String deResultado) {
		this.deResultado = deResultado;
	}
	
	public static String getXmlObtenerCiudadano(IntegracionPido integracionPido, AuditoriaDTO auditoriaDTO) throws Exception {
		String xmlSource = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:con=\"http://soa.osinergmin.gob.pe/schema/comun/consumidor\" xmlns:ns=\"http://soa.osinergmin.gob.pe/schema/registroidentificacion/ciudadano/obtener/1.0\">\r\n" + 
				"   <soap:Header>\r\n" + 
				"	   <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\r\n" + 
				"         <wsse:UsernameToken wsu:Id=\"Id-0001427873415141-0000000076fdd541-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\r\n" + 
				"            <wsse:Username>"+ integracionPido.getUsuarioPido() +"</wsse:Username>\r\n" + 
				"            <wsse:Password>"+ integracionPido.getPasswordPido() +"</wsse:Password>\r\n" + 
				"         </wsse:UsernameToken>\r\n" + 
				"        </wsse:Security>\r\n" + 
				"      <con:consumidor>\r\n" + 
				"         <con:organizacion>OSINERGMIN</con:organizacion>\r\n" + 
				"         <con:canal>WEB</con:canal>\r\n" + 
				"         <con:sistema>"+ConstantesUtil.PARAM_appNameInvokes.toUpperCase()+"</con:sistema>\r\n" + 
				"         <con:modulo>RENIEC</con:modulo>\r\n" + 
				"         <con:usuario>"+auditoriaDTO.getUsername()+"</con:usuario>\r\n" + 
				"         <con:ip>"+auditoriaDTO.getTerminal()+"</con:ip>\r\n" + 
				"      </con:consumidor>\r\n" + 
				"   </soap:Header>\r\n" + 
				"   <soap:Body>\r\n" + 
				"      <ns:CiudadanoObtenerReqParam>\r\n" + 
				"         <ns:dni>"+integracionPido.getNumeroDNIoCE()+"</ns:dni>\r\n" + 
				"      </ns:CiudadanoObtenerReqParam>\r\n" + 
				"   </soap:Body>\r\n" + 
				"</soap:Envelope>";
        return xmlSource;
    }

	public static String getXmlObtenerPersonaJuridica(IntegracionPido integracionPido, AuditoriaDTO auditoriaDTO) {
		 String xmlSource = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:con=\"http://soa.osinergmin.gob.pe/schema/comun/consumidor\" xmlns:ns=\"http://soa.osinergmin.org.pe/schema/consultarucdatosprincipales/contribuyentes/consultar/1.0\">\r\n"
		 		+ "   <soap:Header>\r\n"
		 		+ "    <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\r\n"
		 		+ "         <wsse:UsernameToken wsu:Id=\"Id-0001427873415141-0000000076fdd541-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\r\n"
		 		+ "            <wsse:Username>"+ integracionPido.getUsuarioPido() +"</wsse:Username>\r\n"
		 		+ "            <wsse:Password>"+ integracionPido.getPasswordPido() +"</wsse:Password>\r\n"
		 		+ "         </wsse:UsernameToken>\r\n"
		 		+ "        </wsse:Security>\r\n"
		 		+ "      <con:consumidor>\r\n"
		 		+ "         <con:organizacion>OSINERGMIN</con:organizacion>\r\n"
		 		+ "         <con:canal>WEB</con:canal>\r\n"
		 		+ "         <con:sistema>"+ConstantesUtil.PARAM_appNameInvokes.toUpperCase()+"</con:sistema>\r\n"
		 		+ "         <con:modulo>SUNAT</con:modulo>\r\n"
		 		+ "         <con:usuario>"+auditoriaDTO.getUsername()+"</con:usuario>\r\n"
		 		+ "         <con:ip>"+auditoriaDTO.getTerminal()+"</con:ip>\r\n"
		 		+ "      </con:consumidor>\r\n"
		 		+ "   </soap:Header>\r\n"
		 		+ "   <soap:Body>\r\n"
		 		+ "      <ns:ContribuyentesConsultarReqParam>\r\n"
		 		+ "         <ns:numRuc>"+integracionPido.getNumeroRUC()+"</ns:numRuc>\r\n"
		 		+ "      </ns:ContribuyentesConsultarReqParam>\r\n"
		 		+ "   </soap:Body>\r\n"
		 		+ "</soap:Envelope>";
	        
	        return xmlSource;
	}		

}
