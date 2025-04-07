package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class ReenviarSubFlujoInROO {
	
	private String keyval;
	private String nombreAppInvoca;
	private String usuarioRemitente;
	private String numeroExpediente;
	private String asunto;
	private String contenido;
	private List<Usuarios> usuarios;
	
	public String getKeyval(){
	    return keyval;
	}

	public String getNombreAppInvoca(){
	    return nombreAppInvoca;
	}

	public String getUsuarioRemitente(){
	    return usuarioRemitente;
	}

	public String getNumeroExpediente(){
	    return numeroExpediente;
	}

	public String getAsunto(){
	    return asunto;
	}

	public String getContenido(){
	    return contenido;
	}

	public List<Usuarios> getUsuarios(){
	    return usuarios;
	}

	public void setKeyval (String keyval){
	    this.keyval = keyval;
	}

	public void setNombreAppInvoca (String nombreAppInvoca){
	    this.nombreAppInvoca = nombreAppInvoca;
	}

	public void setUsuarioRemitente (String usuarioRemitente){
	    this.usuarioRemitente = usuarioRemitente;
	}

	public void setNumeroExpediente (String numeroExpediente){
	    this.numeroExpediente = numeroExpediente;
	}

	public void setAsunto (String asunto){
	    this.asunto = asunto;
	}

	public void setContenido (String contenido){
	    this.contenido = contenido;
	}
	
	public void setUsuarios (List<Usuarios> usuarios){
	    this.usuarios = usuarios;
	}	
	
	public static String getConstruirXmlFile(ReenviarSubFlujoInROO obj) throws Exception {
		String xmlSource = 
				"<reenviarSubFlujoInRO>\n" + 
				"    <keyval>"+ obj.getKeyval() +"</keyval>\n" +
				"    <nombreAppInvoca>"+ obj.getNombreAppInvoca() +"</nombreAppInvoca>\n" +
				"    <usuarioRemitente>"+ obj.getUsuarioRemitente() +"</usuarioRemitente>\n" +
				"    <numeroExpediente>"+ obj.getNumeroExpediente() +"</numeroExpediente>\n" +
				"    <asunto>"+ obj.getAsunto() +"</asunto>\n" +
				"    <contenido>"+ obj.getContenido() +"</contenido>\n" +
				"    <usuarios>\n" + 
				"        <usuario>\n" +
				"        </usuario>\n" + 
				"    </usuarios>\n" + 
				"</reenviarSubFlujoInRO>\n";
//		CommonsUtil comm = new CommonsUtil();
//		String nombre = CommonsUtil.generadorNombreUnico();
//		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);
        return xmlSource;
    }	
	

}
