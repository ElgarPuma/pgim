package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class UnidadesOutRo {
	
	private String resultCode;
	private String message;
	private String errorCode;
	
	private String cantidadProcesos;
	private String idUnidad;
	private String nombreUnidad;
	
	List<UnidadesOutRo> lUnidadesOutRo;

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

	public String getCantidadProcesos() {
		return cantidadProcesos;
	}

	public void setCantidadProcesos(String cantidadProcesos) {
		this.cantidadProcesos = cantidadProcesos;
	}

	public String getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public List<UnidadesOutRo> getlUnidadesOutRo() {
		return lUnidadesOutRo;
	}

	public void setlUnidadesOutRo(List<UnidadesOutRo> lUnidadesOutRo) {
		this.lUnidadesOutRo = lUnidadesOutRo;
	}
	
	
	

}
