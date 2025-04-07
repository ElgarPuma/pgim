package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class ProcesosSiged {
    
    private String resultCode;
	private String message;
	private String errorCode;
	private String errorMessage;
	private List<ProcesoSiged> listProcesoSiged;

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
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public List<ProcesoSiged> getListProcesoSiged() {
        return listProcesoSiged;
    }
    public void setListProcesoSiged(List<ProcesoSiged> listProcesoSiged) {
        this.listProcesoSiged = listProcesoSiged;
    }
    
}
