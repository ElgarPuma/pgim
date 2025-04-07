/**
 * 
 */
package pe.gob.osinergmin.pgim.utils;

/**
 * @author Luis Barrantes
 *
 */
public class FechaFeriado {
	
	private String resultCode;
	private String mensajeFeriado;
	private String errorCode; 
	private String message;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMensajeFeriado() {
		return mensajeFeriado;
	}
	public void setMensajeFeriado(String mensajeFeriado) {
		this.mensajeFeriado = mensajeFeriado;
	}
	public static String getStringXmlFile(String strFecha) {
		String xmlSource = "<fechaFeriado>\n" + 
						   "    <fecha>"+ strFecha +"</fecha>\n" +
						   "</fechaFeriado>\n";
        return xmlSource;
    }	
}
