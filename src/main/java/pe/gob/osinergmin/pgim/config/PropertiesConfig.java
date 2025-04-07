package pe.gob.osinergmin.pgim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:C:/dev/osi.pgim.repo/application.properties") // descomentar para desarrollo
//@PropertySource("classpath:/pgim/properties/application.properties")//descomentar para pase
public class PropertiesConfig {

	@Value("${security.enc.key}")
	private String securityEncKey;

	@Value("${application.id}")
	private Integer aplicactionId;
	
	@Value("${url.sisseg.as}")
	private String urlSissegAs;
	
	@Value("${url.sisseg.resources}")
	private String urlSissegResources;

	@Value("${carpeta.tmp}")
	private String carpetaTmp;

	@Value("${files.repo}")
	private String filesRepo;

	@Value("${url.siged.rest.old}")
	private String urlSigedRestOld;
	
	@Value("${url.siged}")
	private String urlSiged;

	@Value("${url.sisseg.pgim}")
	private String urlSisseg;

	@Value("${usuario.pido}")
	private String usuarioPido;

	@Value("${password.pido}")
	private String passwordPido;

	@Value("${url.pido}")
	private String urlPido;

	@Value("${url.siged.soap}")
	private String urlSigedSoap;

	@Value("${username.siged.soap}")
	private String usernameSigedSoap;

	@Value("${password.siged.soap}")
	private String passwordSigedSoap;
	
	@Value("${user.dni.ws.pido}")
	private String userDniWsPido;

	@Value("${password.ws.pido}")
	private String passwordWsPido;
	
	@Value("${reniec.user.dni.ws.pido}")
	private String reniecUserDniWsPido;	

	@Value("${user.siged.admin}")
	private String userSigedAdmin;

	@Value("${terminal.siged.admin}")
	private String terminalSigedAdmin;

	@Value("${url.sne.api.rest}")
	private String urlSneApiRest;

	@Value("${app.sne.api.invoker}")
	private String appSneApiInvoker;

	@Value("${id.sne.api.procedimiento}")
	private int idSneApiProcedimiento;

	@Value("${sector.sne.api}")
	private String sectorSneApi;
	
	@Value("${url.sym.web}")
	private String urlSymWeb;
	
	@Value("${url.sym.rest}")
	private String urlSymRest;
	
	@Value("${files.repo.plantillas}")
	private String filesRepoPantillas;
	
	@Value("${licencia.aspose}")
	private String licenciaAspose;
	
	@Value("${access.token.validity.seconds}")
	private Integer accessTokenValiditySeconds;
	
	@Value("${refresh.token.validity.seconds}")
	private Integer refreshTokenValiditySeconds;

	@Value("${cronpgim.semanal}")
	private String cronpgimSemanal;
	
	@Value("${cronpgim.diario}")
	private String cronpgimDiario;
	
	@Value("${key.enc.acceso.siged.rest}")
	private String keyEncAccesoSigedRest;

	@Value("${carpeta.photos}")
	private String carpetaPhotos;
	
	@Value("${alfresco.api.url}")
	private String alfrescoApiUrl;
	
	@Value("${alfresco.api.usuario}")
	private String alfrescoApiUsuario;
	
	@Value("${alfresco.api.clave}")
	private String alfrescoApiClave;

	@Value("${nu.max.fiscalizacion.anual.xuf}")
	private Integer nuMaxFiscalizacionAnualXuf;
	
	@Value("${nu.max.fiscalizacion.mensual}")
	private Integer nuMaxFiscalizacionMensual;
	
	/**
	 * Dirección URL base de la aplicación cliente.
	 */
	@Value("${url.pgim.cliente}")
	private String urlPgimCliente;

	/**
	 * Dirección de la carpeta en donde se encuentran las guías de la PGIM.
	 */
	@Value("${carpeta.guias}")
	private String carpetaGuias;

	public String getUserSigedAdmin() {
		return userSigedAdmin;
	}

	public String getTerminalSigedAdmin() {
		return terminalSigedAdmin;
	}

	public String getUsuarioPido() {
		return usuarioPido;
	}

	public String getPasswordPido() {
		return passwordPido;
	}

	public String geturlPido() {
		return urlPido;
	}

	public String getSecurityEncKey() {
		return securityEncKey;
	}

	public Integer getAplicactionId() {
		return aplicactionId;
	}
	
	public String getUrlSissegAs() {
		return urlSissegAs;
	}
	
	public String getUrlSissegResources() {
		return urlSissegResources;
	}

	public String getCarpetaTmp() {
		return carpetaTmp;
	}

	public String getFilesRepo() {
		return filesRepo;
	}

	public String getUrlSigedRestOld() {
		return urlSigedRestOld;
	}
	
	public String getUrlSiged() {
		return urlSiged;
	}

	public String getUrlSisseg() {
		return urlSisseg;
	}

	public String getUrlSigedSoap() {
		return urlSigedSoap;
	}

	public String getUsernameSigedSoap() {
		return usernameSigedSoap;
	}

	public String getPasswordSigedSoap() {
		return passwordSigedSoap;
	}		

	public String getUserDniWsPido() {
		return userDniWsPido;
	}

	public void setUserDniWsPido(String userDniWsPido) {
		this.userDniWsPido = userDniWsPido;
	}

	public String getPasswordWsPido() {
		return passwordWsPido;
	}

	public void setPasswordWsPido(String passwordWsPido) {
		this.passwordWsPido = passwordWsPido;
	}

	public String getReniecUserDniWsPido() {
		return reniecUserDniWsPido;
	}

	public void setReniecUserDniWsPido(String reniecUserDniWsPido) {
		this.reniecUserDniWsPido = reniecUserDniWsPido;
	}

	/**
	 * Permite obtener la dirección URL del servicio API-REST para las
	 * notificaciones electrónicas.
	 * 
	 * @return
	 */
	public String getUrlSneApiRest() {
		return urlSneApiRest;
	}

	/**
	 * Permite obtener el nombre de la aplicación invocadora de los métodos del
	 * servicio SNE
	 * 
	 * @return
	 */
	public String getAppSneApiInvoker() {
		return appSneApiInvoker;
	}

	/**
	 * Permite obtener el id del procedimiento que se utilizará para el registro de
	 * la notificación electrónica.
	 * 
	 * @return
	 */
	public int getIdSneApiProcedimiento() {
		return idSneApiProcedimiento;
	}

	/**
	 * Permite retornar el valor del sector que se empleará para la integración con el SNE.
	 * @return
	 */
	public String getSectorSneApi() {
		return sectorSneApi;
	}
	
	
	/**
	 * Permite obtener la dirección URL del servicio SYM-WEB para la
	 * intengración con el SYM-WEB.
	 * 
	 * @return
	 */
	public String getUrlSymWeb() {
		return urlSymWeb;
	}
	
	/**
	 * Permite obtener la dirección URL del servicio SYM-REST para la
	 * intengración con el SYM-REST.
	 * 
	 * @return
	 */
	public String getUrlSymRest() {
		return urlSymRest;
	}
	
	/**
	 * Licencia Aspose
	 * @return
	 */	
	public String getLicenciaAspose() {
		return licenciaAspose;
	}

	/**
	 * Ruta donde se almacenan las plantillas
	 * @return
	 */
	public String getFilesRepoPantillas() {
		return filesRepoPantillas;
	}
	
	/**
	 * Duración token
	 * @return
	 */
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}
	
	/**
	 * Duración token refresh
	 * @return
	 */
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public String getUrlPgimCliente() {
		return urlPgimCliente;
	}

	public void setUrlPgimCliente(String urlPgimCliente) {
		this.urlPgimCliente = urlPgimCliente;
	}

	public String getCronpgimSemanal() {
		return cronpgimSemanal;
	}

	public String getCronpgimDiario() {
		return cronpgimDiario;
	}
	
	public String getKeyEncAccesoSigedRest() {
		return keyEncAccesoSigedRest;
	}
	
	public String getCarpetaPhotos() {
		return carpetaPhotos;
	}
	
	public String getAlfrescoApiUrl() {
		return alfrescoApiUrl;
	}

	public String getAlfrescoApiUsuario() {
		return alfrescoApiUsuario;
	}
	
	public String getAlfrescoApiClave() {
		return alfrescoApiClave;
	}

	public Integer getNuMaxFiscalizacionAnualXuf() {
		return nuMaxFiscalizacionAnualXuf;
	}

	public Integer getNuMaxFiscalizacionMensual() {
		return nuMaxFiscalizacionMensual;
	}

	public String getCarpetaGuias() {
		return carpetaGuias;
	}

	public void setCarpetaGuias(String carpetaGuias) {
		this.carpetaGuias = carpetaGuias;
	}

}
