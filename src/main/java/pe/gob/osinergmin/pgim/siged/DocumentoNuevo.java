package pe.gob.osinergmin.pgim.siged;

import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class DocumentoNuevo {

	// expediente
	private String nroExpediente;
	// documento
	private String nrodocumento;
	private String appNameInvokes; 
	// archivos
	// archivo
	private String descripcion;
	private String idArchivo;
	// archivo
	// archivos
	// clientes
	// cliente
	private String codigoTipoIdentificacion;
	private String razonSocial;
	private String nroIdentificacion; 
	private String tipoCliente; 
	// cliente
	// clientes
	private String codTipoDocumento;
	private String enumerado;
	private String estaEnFlujo;
	private String firmado;
	private String creaExpediente;
	private String nroFolios;
	private String asunto;
	private String publico;
	private String usuarioCreador;
	// documento
	// expediente
	
	
	public String getNroExpediente() {
		return nroExpediente;
	}
	
	public DocumentoNuevo(String nroExpediente, String descripcion, 
				String asunto, String codTipoDocumento, String nrodocumento, 
				String flNumeradoPorSiged, String usuarioCreador) {
		super();
		this.nroExpediente = nroExpediente;
		
		if (flNumeradoPorSiged.equals("S")) {
			this.nrodocumento = "";
			this.enumerado = ConstantesUtil.PARAM_negativo;
		}
		else {
			this.enumerado = ConstantesUtil.PARAM_afirmativo;
			this.nrodocumento = nrodocumento;
		}
		
		this.appNameInvokes = ConstantesUtil.PARAM_appNameInvokes;
		this.descripcion = descripcion;
		this.idArchivo = "";
		this.codigoTipoIdentificacion = ConstantesUtil.PARAM_codigoTipoIdentificacion;
		this.razonSocial = ConstantesUtil.PARAM_razonSocial;
		this.nroIdentificacion = ConstantesUtil.PARAM_RUC_OSINERGMIN;
		this.tipoCliente = ConstantesUtil.PARAM_tipoCliente;
		this.codTipoDocumento = codTipoDocumento;
		this.estaEnFlujo = ConstantesUtil.PARAM_estaEnFlujo;
		this.firmado = ConstantesUtil.PARAM_negativo;
		this.creaExpediente = ConstantesUtil.PARAM_creaExpediente;
		this.nroFolios = ConstantesUtil.PARAM_nroFolios;
		this.asunto = asunto;
		this.publico = ConstantesUtil.PARAM_publico;
		this.usuarioCreador = usuarioCreador;
	}
	
	public DocumentoNuevo(String nroExpediente, String idArchivo, String descripcion, 
			String asunto, String codTipoDocumento, String usuarioCreador) {
	super();
	this.nroExpediente = nroExpediente;
	this.appNameInvokes = ConstantesUtil.PARAM_appNameInvokes;
	this.descripcion = descripcion;
	this.idArchivo = idArchivo;
	this.codigoTipoIdentificacion = ConstantesUtil.PARAM_codigoTipoIdentificacion;
	this.razonSocial = ConstantesUtil.PARAM_razonSocial;
	this.nroIdentificacion = ConstantesUtil.PARAM_RUC_OSINERGMIN;
	this.tipoCliente = ConstantesUtil.PARAM_tipoCliente;
	this.codTipoDocumento = codTipoDocumento;
	this.asunto = asunto;
	this.usuarioCreador = usuarioCreador;
	}
	
	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}
	public String getAppNameInvokes() {
		return appNameInvokes;
	}
	public void setAppNameInvokes(String appNameInvokes) {
		this.appNameInvokes = appNameInvokes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}
	public String getCodigoTipoIdentificacion() {
		return codigoTipoIdentificacion;
	}
	public void setCodigoTipoIdentificacion(String codigoTipoIdentificacion) {
		this.codigoTipoIdentificacion = codigoTipoIdentificacion;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getEnumerado() {
		return enumerado;
	}
	public void setEnumerado(String enumerado) {
		this.enumerado = enumerado;
	}
	public String getEstaEnFlujo() {
		return estaEnFlujo;
	}
	public void setEstaEnFlujo(String estaEnFlujo) {
		this.estaEnFlujo = estaEnFlujo;
	}
	public String getFirmado() {
		return firmado;
	}
	public void setFirmado(String firmado) {
		this.firmado = firmado;
	}
	public String getCreaExpediente() {
		return creaExpediente;
	}
	public void setCreaExpediente(String creaExpediente) {
		this.creaExpediente = creaExpediente;
	}
	public String getNroFolios() {
		return nroFolios;
	}
	public void setNroFolios(String nroFolios) {
		this.nroFolios = nroFolios;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getPublico() {
		return publico;
	}
	public void setPublico(String publico) {
		this.publico = publico;
	}
	public String getUsuarioCreador() {
		return usuarioCreador;
	}
	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public static String getConstruirXmlFileNuevo(DocumentoNuevo obj, String carpetaTmp) throws Exception {
		String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"\n" + 
				"<expediente>\n" + 
				"	<nroExpediente>"+ obj.getNroExpediente() +"</nroExpediente>\n" + 
				"	<documento>\n" + 
				"		<numeroDocumento>"+ (obj.getNrodocumento() == null ? "": obj.getNrodocumento()) + "</numeroDocumento>\n" + 
				"		<appNameInvokes>" + obj.getAppNameInvokes() + "</appNameInvokes>\n" + 
				"		<archivos>\n" + 
				"			<archivo>\n" + 
				"				<descripcion>" + obj.getDescripcion() + "</descripcion>\n" + 
				"				<idArchivo></idArchivo>\n" + 
				"			</archivo>\n" + 
				"		</archivos>\n" + 
				"		<clientes>\n" + 
				"			<cliente>\n" + 
				"				<codigoTipoIdentificacion>"+obj.getCodigoTipoIdentificacion()+"</codigoTipoIdentificacion>\n" + 
				"				<razonSocial>"+obj.getRazonSocial()+"</razonSocial>\n" + 
				"				<nroIdentificacion>"+obj.getNroIdentificacion()+"</nroIdentificacion>\n" + 
				"				<tipoCliente>"+obj.getTipoCliente()+"</tipoCliente>\n" + 
				"			</cliente>\n" + 
				"		</clientes>\n" + 
				"		<codTipoDocumento>"+obj.getCodTipoDocumento()+"</codTipoDocumento>\n" + 
				"		<enumerado>"+obj.getEnumerado()+"</enumerado>\n" + 
				"		<estaEnFlujo>"+obj.getEstaEnFlujo()+"</estaEnFlujo>\n" + 
				"		<firmado>"+obj.getFirmado()+"</firmado>\n" + 
				"		<creaExpediente>"+obj.getCreaExpediente()+"</creaExpediente>\n" + 
				"		<nroFolios>"+obj.getNroFolios()+"</nroFolios>\n" + 
				"		<asunto>"+obj.getAsunto()+"</asunto>\n" + 
				"		<publico>"+obj.getPublico()+"</publico>\n" + 
				"		<usuarioCreador>"+obj.getUsuarioCreador()+"</usuarioCreador>\n" + 
				"		<accion>1</accion>\n" +
			    "		<rolAutor>5</rolAutor>\n" +
				"	</documento>\n" + 
				"</expediente>\n" + 
				"";
		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);

        return pathFile;
    }		
	
	public static String getConstruirXmlFileReemplazo(DocumentoNuevo obj, String carpetaTmp) throws Exception {
		String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<expediente>\n" + 
				"	<nroExpediente>" + obj.getNroExpediente() + "</nroExpediente>\n" + 
				"	<documento>\n" + 
				"		<numeroDocumento>" + (obj.getNrodocumento() == null ? "" :obj.getNrodocumento() ) + "</numeroDocumento>\n" + 
				"		<appNameInvokes>" + obj.getAppNameInvokes() + "</appNameInvokes>\n" + 
				"		<archivos>\n" + 
				"			<archivo>\n" + 
				"				<descripcion>"+obj.getDescripcion()+"</descripcion>\n" + 
				"				<idArchivo>"+obj.getIdArchivo()+"</idArchivo>\n" + 
				"			</archivo>\n" + 
				"		</archivos>\n" + 
				"		<clientes>\n" + 
				"			<cliente>\n" + 
				"				<codigoTipoIdentificacion>"+obj.getCodigoTipoIdentificacion()+"</codigoTipoIdentificacion>\n" + 
				"				<razonSocial>"+obj.getRazonSocial()+"</razonSocial>\n" + 
				"				<nroIdentificacion>"+obj.getNroIdentificacion()+"</nroIdentificacion>\n" + 
				"				<tipoCliente>"+obj.getTipoCliente()+"</tipoCliente>\n" + 
				"			</cliente>\n" + 
				"		</clientes>\n" + 
				"		<codTipoDocumento>"+obj.getCodTipoDocumento()+"</codTipoDocumento>\n" + 
				"		<asunto>"+obj.getAsunto()+"</asunto>\n" + 
				"		<usuarioCreador>"+obj.getUsuarioCreador()+"</usuarioCreador>\n" + 
				"		<accion>1</accion>\n" +
			    "		<rolAutor>5</rolAutor>\n" +
				"	</documento>\n" + 
				"</expediente>\n" + 
				"";
		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);

        return pathFile;
    }		
	
	
}
