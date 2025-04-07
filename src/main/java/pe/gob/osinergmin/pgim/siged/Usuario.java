package pe.gob.osinergmin.pgim.siged;

public class Usuario {
	
	private String idUsuario;
	private String usuario;
	private String nombresUsuario;
	private String apellidosUsuario;
	private String estadoUsuario;
	private String correoUsuario;

	public Usuario() {
		super();
	}
	public Usuario(String usuario) {
		super();
		this.usuario = usuario;
		this.idUsuario = "";
		this.nombresUsuario = "";
		this.apellidosUsuario = "";
		this.estadoUsuario = "";
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombresUsuario() {
		return nombresUsuario;
	}
	public void setNombresUsuario(String nombresUsuario) {
		this.nombresUsuario = nombresUsuario;
	}
	public String getApellidosUsuario() {
		return apellidosUsuario;
	}
	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}
	public String getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public static String getStringXmlFile(Usuario obj) {
		String xmlSource = "<usuario>\n" + 
				"    <idUsuario>"+ obj.getIdUsuario() +"</idUsuario>\n" + 
				"    <usuario>"+ obj.getUsuario() +"</usuario>\n" +
				"    <nombresUsuario>"+ obj.getNombresUsuario() +"</nombresUsuario>\n" +
				"    <apellidosUsuario>"+ obj.getApellidosUsuario() +"</apellidosUsuario>\n" +
				"    <estadoUsuario>"+ obj.getEstadoUsuario() +"</estadoUsuario>\n" +
				"</usuario>\n";
        return xmlSource;
    }	
	
	
}
