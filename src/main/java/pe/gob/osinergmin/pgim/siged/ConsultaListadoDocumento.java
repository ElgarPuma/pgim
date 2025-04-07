package pe.gob.osinergmin.pgim.siged;

public class ConsultaListadoDocumento {
  
  private String files;
  private String idProceso;
  private String idTipoDocumento;
  private String nroExpediente;
  private String asuntoDocumento;
  private String nroDocumento;
  private String rucCliente;
  private String dniCliente;
  private String razonsocialCliente;
  private String nombreCliente;
  private String fechaCreacion;
  private String fechaDocumento;
  
  private Long idInstanciaProceso;
  
  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }

  public String getIdProceso() {
    return idProceso;
  }

  public void setIdProceso(String idProceso) {
    this.idProceso = idProceso;
  }

  public String getIdTipoDocumento() {
    return idTipoDocumento;
  }

  public void setIdTipoDocumento(String idTipoDocumento) {
    this.idTipoDocumento = idTipoDocumento;
  }

  public String getNroExpediente() {
    return nroExpediente;
  }

  public void setNroExpediente(String nroExpediente) {
    this.nroExpediente = nroExpediente;
  }

  public String getAsuntoDocumento() {
    return asuntoDocumento;
  }

  public void setAsuntoDocumento(String asuntoDocumento) {
    this.asuntoDocumento = asuntoDocumento;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }

  public void setNroDocumento(String nroDocumento) {
    this.nroDocumento = nroDocumento;
  }

  public String getRucCliente() {
    return rucCliente;
  }

  public void setRucCliente(String rucCliente) {
    this.rucCliente = rucCliente;
  }

  public String getDniCliente() {
    return dniCliente;
  }

  public void setDniCliente(String dniCliente) {
    this.dniCliente = dniCliente;
  }

  public String getRazonsocialCliente() {
    return razonsocialCliente;
  }

  public void setRazonsocialCliente(String razonsocialCliente) {
    this.razonsocialCliente = razonsocialCliente;
  }

  public String getNombreCliente() {
    return nombreCliente;
  }

  public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
  }

  public String getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(String fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public String getFechaDocumento() {
    return fechaDocumento;
  }

  public void setFechaDocumento(String fechaDocumento) {
    this.fechaDocumento = fechaDocumento;
  }
 
  public Long getIdInstanciaProceso() {
    return idInstanciaProceso;
  }

  public void setIdInstanciaProceso(Long idInstanciaProceso) {
    this.idInstanciaProceso = idInstanciaProceso;
  }
  
  public static String getStringXmlFile(ConsultaListadoDocumento obj) {
	  
		String xmlSource = "<consultaListadoDocumento>\n" + 
				"    <files>"+ obj.getFiles() +"</files>\n" + 
				"    <idProceso>"+ obj.getIdProceso() +"</idProceso>\n" + 
				"    <idTipoDocumento>"+ obj.getIdTipoDocumento() +"</idTipoDocumento>\n" + 
				"    <nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" + 
				"    <asuntoDocumento>"+ obj.getAsuntoDocumento() +"</asuntoDocumento>\n" + 
				"    <nroDocumento>"+ obj.getNroDocumento() +"</nroDocumento>\n" + 
				"    <rucCliente>"+ obj.getRucCliente() +"</rucCliente>\n" + 
				//"    <dniCliente>"+ obj.getDniCliente() +"</dniCliente>\n" + 
				"    <razonsocialCliente>"+ obj.getRazonsocialCliente() +"</razonsocialCliente>\n" + 
				//"    <nombreCliente>"+ obj.getNombreCliente() +"</nombreCliente>\n" + 
				//"    <fechaCreacion>"+ obj.getFechaCreacion() +"</fechaCreacion>\n" + 
				//"    <fechaDocumento>"+ obj.getFechaDocumento() +"</fechaDocumento>\n" + 
				"</consultaListadoDocumento>\n";
        return xmlSource;
  }	

}
