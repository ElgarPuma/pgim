package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class UsuarioSiged {
	private String resultCode;
	private String message;
	private String errorCode;
	private List<Usuarios> listaUsuarios;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<Usuarios> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuarios> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
}
