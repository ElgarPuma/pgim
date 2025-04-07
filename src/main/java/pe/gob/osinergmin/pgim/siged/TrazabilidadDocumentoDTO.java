package pe.gob.osinergmin.pgim.siged;

import java.util.List;

public class TrazabilidadDocumentoDTO {
    
    private String asunto;
    private String fechaCreacion;
    private String fechaLimiteAtencion;
    private String fechacreacionmonth;
    private String fechacreacionyear;

    private String idAccion;
    private String idDestinatrario;
    private String idProceso;
    private String idRemitente;
    private String idTrazabilidaddocumento;
    private String idUnidadDestinatario;
    private String idUnidadRemitente;
    
    private String nombreUnidadDestinatario;
    private String nombreUnidadRemitente;

    private String nombreAccion;
    private String nombreDestinatario;
    private String nombreProceso;
    private String nombreRemitente;
    private String nroregistro;
    
    private String resultCode;
    private String message;
    private String errorCode;

    private List<TrazabilidadDocumentoDTO> lTrazabilidadDocs;

    
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
    
    public List<TrazabilidadDocumentoDTO> getlTrazabilidadDocs() {
        return lTrazabilidadDocs;
    }
    public void setlTrazabilidadDocs(List<TrazabilidadDocumentoDTO> lTrazabilidadDocs) {
        this.lTrazabilidadDocs = lTrazabilidadDocs;
    }
    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getFechaLimiteAtencion() {
        return fechaLimiteAtencion;
    }
    public void setFechaLimiteAtencion(String fechaLimiteAtencion) {
        this.fechaLimiteAtencion = fechaLimiteAtencion;
    }
    public String getFechacreacionmonth() {
        return fechacreacionmonth;
    }
    public void setFechacreacionmonth(String fechacreacionmonth) {
        this.fechacreacionmonth = fechacreacionmonth;
    }
    public String getFechacreacionyear() {
        return fechacreacionyear;
    }
    public void setFechacreacionyear(String fechacreacionyear) {
        this.fechacreacionyear = fechacreacionyear;
    }
    public String getIdAccion() {
        return idAccion;
    }
    public void setIdAccion(String idAccion) {
        this.idAccion = idAccion;
    }
    public String getIdDestinatrario() {
        return idDestinatrario;
    }
    public void setIdDestinatrario(String idDestinatrario) {
        this.idDestinatrario = idDestinatrario;
    }
    public String getIdProceso() {
        return idProceso;
    }
    public void setIdProceso(String idProceso) {
        this.idProceso = idProceso;
    }
    public String getIdRemitente() {
        return idRemitente;
    }
    public void setIdRemitente(String idRemitente) {
        this.idRemitente = idRemitente;
    }
    public String getIdTrazabilidaddocumento() {
        return idTrazabilidaddocumento;
    }
    public void setIdTrazabilidaddocumento(String idTrazabilidaddocumento) {
        this.idTrazabilidaddocumento = idTrazabilidaddocumento;
    }
    public String getIdUnidadDestinatario() {
        return idUnidadDestinatario;
    }
    public void setIdUnidadDestinatario(String idUnidadDestinatario) {
        this.idUnidadDestinatario = idUnidadDestinatario;
    }
    public String getIdUnidadRemitente() {
        return idUnidadRemitente;
    }
    public void setIdUnidadRemitente(String idUnidadRemitente) {
        this.idUnidadRemitente = idUnidadRemitente;
    }
    public String getNombreAccion() {
        return nombreAccion;
    }
    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }
    public String getNombreDestinatario() {
        return nombreDestinatario;
    }
    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }
    public String getNombreProceso() {
        return nombreProceso;
    }
    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }
    public String getNombreRemitente() {
        return nombreRemitente;
    }
    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }
    public String getNroregistro() {
        return nroregistro;
    }
    public void setNroregistro(String nroregistro) {
        this.nroregistro = nroregistro;
    }
	public String getNombreUnidadDestinatario() {
		return nombreUnidadDestinatario;
	}
	public void setNombreUnidadDestinatario(String nombreUnidadDestinatario) {
		this.nombreUnidadDestinatario = nombreUnidadDestinatario;
	}
	public String getNombreUnidadRemitente() {
		return nombreUnidadRemitente;
	}
	public void setNombreUnidadRemitente(String nombreUnidadRemitente) {
		this.nombreUnidadRemitente = nombreUnidadRemitente;
	}
    
    

    
}
