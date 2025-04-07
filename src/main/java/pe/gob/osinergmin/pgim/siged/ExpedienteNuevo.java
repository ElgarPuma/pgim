package pe.gob.osinergmin.pgim.siged;

import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class ExpedienteNuevo {

	private String nrodocumento;
	private String appNameInvokes;
	private String asunto;
	private String ubigeo;
	private String codigoTipoIdentificacion;
	private String razonSocial;
	private String nroIdentificacion;
	private String tipoCliente;
	private String codTipoDocumento;
	private String enumerado;
	private String estaEnFlujo;
	private String firmado;
	private String creaExpediente;
	private String nroFolios;
	private String publico;
	private String usuarioCreador;
	private String expedienteFisico;
	private String historico;
	private String destinatario;
	private String proceso;

	/**
	 * Permite definir las propiedades para la creación del nuevo expediente Siged.
	 * @param pgimPersona
	 * @param asunto
	 * @param codTipoDocumento
	 * @param nrodocumento
	 * @param flNumeradoPorSiged
	 * @param proceso
	 * @param usuarioCreador
	 */
	public ExpedienteNuevo(PgimPersona pgimPersona, String asunto, String codTipoDocumento, String nrodocumento,
			String flNumeradoPorSiged, String proceso, String usuarioCreador) {
		super();

		if (flNumeradoPorSiged.equals("S")) {
			this.nrodocumento = "";
			this.enumerado = ConstantesUtil.PARAM_negativo;
		} else {
			this.enumerado = ConstantesUtil.PARAM_afirmativo;
			this.nrodocumento = nrodocumento;
		}

		this.codigoTipoIdentificacion = ConstantesUtil.PARAM_codigoTipoIdentificacion;
		this.tipoCliente = ConstantesUtil.PARAM_tipoCliente;

		this.razonSocial = pgimPersona.getNoRazonSocial();
		this.nroIdentificacion = pgimPersona.getCoDocumentoIdentidad();

		if (!pgimPersona.getCoDocumentoIdentidad().equals(ConstantesUtil.PARAM_RUC_OSINERGMIN)) {
			// No es Osinergmin.
			this.codigoTipoIdentificacion = ConstantesUtil.PARAM_codigoTipoIdentificacion;
		}

		this.appNameInvokes = ConstantesUtil.PARAM_appNameInvokes;
		this.asunto = asunto;
		this.ubigeo = ConstantesUtil.PARAM_ubigeo;
		this.codTipoDocumento = codTipoDocumento;
		this.estaEnFlujo = ConstantesUtil.PARAM_estaEnFlujo;
		this.firmado = ConstantesUtil.PARAM_negativo;
		this.creaExpediente = ConstantesUtil.PARAM_creaExpedienteSi;
		this.nroFolios = ConstantesUtil.PARAM_nroFolios;
		this.publico = ConstantesUtil.PARAM_publico;
		this.usuarioCreador = usuarioCreador;
		this.expedienteFisico = ConstantesUtil.PARAM_expedienteFisico;
		this.historico = ConstantesUtil.PARAM_historico;
		this.destinatario = usuarioCreador;
		this.proceso = proceso;
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
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

	public String getExpedienteFisico() {
		return expedienteFisico;
	}

	public void setExpedienteFisico(String expedienteFisico) {
		this.expedienteFisico = expedienteFisico;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public static String getConstruirXmlFile(ExpedienteNuevo obj, String carpetaTmp) throws Exception {
		String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<expediente>\n" + "    <documento>\n"
				+ "		 <numeroDocumento>" + (obj.getNrodocumento() == null ? "" : obj.getNrodocumento())
				+ "</numeroDocumento>" + "        <appNameInvokes>"
				+ obj.getAppNameInvokes() + "</appNameInvokes>\n" + "        <asunto>" + obj.getAsunto() + "</asunto>\n"
				+ "        <archivos>\n" + "            <archivo>\n" + "                <descripcion></descripcion>\n"
				+ "                <idArchivo></idArchivo>\n" + "            </archivo>\n" + "        </archivos>\n"
				+ "        <clientes>\n" + "            <cliente>\n" + "                <direcciones>\n"
				+ "                    <direccion>\n" + "                        <direccion></direccion>\n"
				+ "                        <direccionPrincipal></direccionPrincipal>\n"
				+ "                        <estado></estado>\n" + "                        <telefono></telefono>\n"
				+ "                        <referencia></referencia>\n" + "                        <ubigeo>"
				+ obj.getUbigeo() + "</ubigeo>\n" + "                    </direccion>\n"
				+ "                </direcciones>\n" + "                <nombre></nombre>\n"
				+ "                <codigoTipoIdentificacion>" + obj.getCodigoTipoIdentificacion()
				+ "</codigoTipoIdentificacion>			                			                			\r\n"
				+ "                <razonSocial>" + CommonsUtil.limpiarCadenaParaXML(obj.getRazonSocial())
				+ "</razonSocial>\n"
				+ "                <nroIdentificacion>" + obj.getNroIdentificacion() + "</nroIdentificacion>\n"
				+ "                <tipoCliente>" + obj.getTipoCliente() + "</tipoCliente>\n"
				+ "            </cliente>\n" + "        </clientes>\n" + "        <codTipoDocumento>"
				+ obj.getCodTipoDocumento() + "</codTipoDocumento>\n" + "        <enumerado>" + obj.getEnumerado()
				+ "</enumerado>\n" + "        <estaEnFlujo>" + obj.getEstaEnFlujo() + "</estaEnFlujo>\n"
				+ "        <firmado>" + obj.getFirmado() + "</firmado>\n" + "        <creaExpediente>"
				+ obj.getCreaExpediente() + "</creaExpediente>\n" + "        <nroFolios>" + obj.getNroFolios()
				+ "</nroFolios>\n" + "        <publico>" + obj.getPublico() + "</publico>\n"
				+ "        <usuarioCreador>" + obj.getUsuarioCreador() + "</usuarioCreador>\n"
				+ "		 <accion>1</accion>\n" + "		 <rolAutor>5</rolAutor>\n" + "    </documento>\n"
				+ "    <expedienteFisico>" + obj.getExpedienteFisico() + "</expedienteFisico>\n"
				+ "    <notificacionCliente></notificacionCliente>\n" + "    <historico>" + obj.getHistorico()
				+ "</historico>\n" + "    <destinatario>" + obj.getDestinatario() + "</destinatario>\n"
				+ "    <proceso>" + obj.getProceso() + "</proceso>\n" + "</expediente>";

		CommonsUtil comm = new CommonsUtil();
		String nombre = CommonsUtil.generadorNombreUnico();
		String pathFile = comm.stringToDom(xmlSource, nombre, carpetaTmp);

		return pathFile;
	}
}
