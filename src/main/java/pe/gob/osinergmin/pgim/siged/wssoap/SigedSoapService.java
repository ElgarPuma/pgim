package pe.gob.osinergmin.pgim.siged.wssoap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXBElement;
//import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.ws.soap.SOAPFaultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.DetalleExcepcionDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.services.ArchivoService;
import pe.gob.osinergmin.pgim.siged.Archivo;
import pe.gob.osinergmin.pgim.siged.ArchivoAnularInRO;
import pe.gob.osinergmin.pgim.siged.ArchivoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.Archivos;
import pe.gob.osinergmin.pgim.siged.BuscarExpedienteOut;
import pe.gob.osinergmin.pgim.siged.DescargaArchivo;
import pe.gob.osinergmin.pgim.siged.DevolverExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.Documento;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularInRO;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.DocumentoNuevo;
import pe.gob.osinergmin.pgim.siged.DocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.Documentos;
import pe.gob.osinergmin.pgim.siged.EnumerarDocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteNuevo;
import pe.gob.osinergmin.pgim.siged.ExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.FirmaDigitalSiged;
import pe.gob.osinergmin.pgim.siged.Usuario;
import pe.gob.osinergmin.pgim.siged.UsuarioSiged;
import pe.gob.osinergmin.pgim.siged.Usuarios;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.FechaFeriado;
import pe.gob.osinergmin.soa.AccederExpedienteServiceLocator;
import pe.gob.osinergmin.soa.AgregarArchivoDocumentoServiceLocator;
import pe.gob.osinergmin.soa.AgregarDocumentoServiceLocator;
import pe.gob.osinergmin.soa.AnularArchivoServiceLocator;
import pe.gob.osinergmin.soa.AnularDocumentoServiceLocator;
import pe.gob.osinergmin.soa.AprobarExpedienteServiceLocator;
import pe.gob.osinergmin.soa.AprobarExpedienteServiceLocatorException;
import pe.gob.osinergmin.soa.BuscarExpedienteServiceLocator;
import pe.gob.osinergmin.soa.CrearExpedienteServiceLocator;
import pe.gob.osinergmin.soa.DescargarArchivoServiceLocator;
import pe.gob.osinergmin.soa.DevolverExpedienteServiceLocator;
import pe.gob.osinergmin.soa.DevolverExpedienteServiceLocatorException;
import pe.gob.osinergmin.soa.EnumerarExpedienteServiceLocator;
import pe.gob.osinergmin.soa.EnumerarExpedienteServiceLocatorException;
import pe.gob.osinergmin.soa.EsFeriadoServiceLocator;
import pe.gob.osinergmin.soa.EsFeriadoServiceLocatorException;
import pe.gob.osinergmin.soa.ListarArchivoDocumentoServiceLocator;
import pe.gob.osinergmin.soa.ListarDocumentoExpedienteServiceLocator;
import pe.gob.osinergmin.soa.ListarTrazabilidadDocumentoServiceLocator;
import pe.gob.osinergmin.soa.ListarTrazabilidadDocumentoServiceLocatorException;
import pe.gob.osinergmin.soa.ObtenerUsuarioServiceLocator;
import pe.gob.osinergmin.soa.ReenviarExpedienteServiceLocator;
import pe.gob.osinergmin.soa.ReenviarExpedienteServiceLocatorException;
import pe.gob.osinergmin.soa.RevertirFirmaServiceLocator;
import pe.gob.osinergmin.soa.RevertirFirmaServiceLocatorException;
import pe.gob.osinergmin.soa.service.expediente.documentos.accesoexpediente.v1.AccesoExpediente;
import pe.gob.osinergmin.soa.service.expediente.documentos.accesoexpediente.v1.AccesoExpedienteResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.accesoexpediente.v1.ObjectFactory;
import pe.gob.osinergmin.soa.service.expediente.documentos.accesoexpediente.v1.ServiceAP40316;
import pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.AgregarArchivoADocumento;
import pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.AgregarArchivoADocumentoResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.ServiceAP4036;
import pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.AgregarDocumento;
import pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.AgregarDocumentoResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.ServiceAP4032;
import pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.AnularArchivo;
import pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.AnularArchivoResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.ServiceAP40311;
import pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.AnularDocumento;
import pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.AnularDocumentoResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.ServiceAP40312;
import pe.gob.osinergmin.soa.service.expediente.documentos.aprobarexpediente.v1.AprobarExpediente;
import pe.gob.osinergmin.soa.service.expediente.documentos.aprobarexpediente.v1.AprobarExpedienteResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.aprobarexpediente.v1.FaultMsg;
import pe.gob.osinergmin.soa.service.expediente.documentos.aprobarexpediente.v1.ServiceAP40322;
import pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.BuscarExpediente;
import pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.BuscarExpedienteResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.ServiceAP4037;
import pe.gob.osinergmin.soa.service.expediente.documentos.descargararchivo.v1.AccesoExpedientetype;
import pe.gob.osinergmin.soa.service.expediente.documentos.descargararchivo.v1.DescargarArchivo;
import pe.gob.osinergmin.soa.service.expediente.documentos.descargararchivo.v1.DescargarArchivoResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.descargararchivo.v1.ServiceAP4031;
import pe.gob.osinergmin.soa.service.expediente.documentos.devolverexpediente.v1.DevolverExpediente;
import pe.gob.osinergmin.soa.service.expediente.documentos.devolverexpediente.v1.DevolverExpedienteResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.devolverexpediente.v1.ServiceAP40323;
import pe.gob.osinergmin.soa.service.expediente.documentos.enumeraciondocumetos.v1.EnumeracionDocumetos;
import pe.gob.osinergmin.soa.service.expediente.documentos.enumeraciondocumetos.v1.EnumeracionDocumetosResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.enumeraciondocumetos.v1.ServiceAP40319;
import pe.gob.osinergmin.soa.service.expediente.documentos.esferiado.v1.EsFeriado;
import pe.gob.osinergmin.soa.service.expediente.documentos.esferiado.v1.EsFeriadoResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.esferiado.v1.ServiceAP40321;
import pe.gob.osinergmin.soa.service.expediente.documentos.listararchivos.v1.AccesoType;
import pe.gob.osinergmin.soa.service.expediente.documentos.listararchivos.v1.ListarArchivos;
import pe.gob.osinergmin.soa.service.expediente.documentos.listararchivos.v1.ListarArchivosResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.listararchivos.v1.ParametrosType;
import pe.gob.osinergmin.soa.service.expediente.documentos.listararchivos.v1.ServiceAP4035;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.AccesoTypeAP4034;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.ListarDocumentos;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.ListarDocumentosResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.ListarDocumentosResponse.Documento.Archivos.Archivo.FirmasDigitales;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.ListarDocumentosResponse.Documento.Archivos.Archivo.FirmasDigitales.FirmaDigital;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.ParametrosTypeAP4034;
import pe.gob.osinergmin.soa.service.expediente.documentos.listardocumentos.v1.ServiceAP4034;
import pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ListarTrazabilidadDocumentos;
import pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ListarTrazabilidadDocumentosResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ServiceAP4038;
import pe.gob.osinergmin.soa.service.expediente.documentos.obtenerusuario.v1.ObtenerUsuario;
import pe.gob.osinergmin.soa.service.expediente.documentos.obtenerusuario.v1.ObtenerUsuarioResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.obtenerusuario.v1.ServiceAP40313;
import pe.gob.osinergmin.soa.service.expediente.documentos.reenviarexpediente.v1.ReenviarExpediente;
import pe.gob.osinergmin.soa.service.expediente.documentos.reenviarexpediente.v1.ReenviarExpedienteResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.reenviarexpediente.v1.ServiceAP40318;
import pe.gob.osinergmin.soa.service.expediente.documentos.revertirfirma.v1.RevertirFirma;
import pe.gob.osinergmin.soa.service.expediente.documentos.revertirfirma.v1.RevertirFirmaResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.revertirfirma.v1.ServiceAP40324;
import pe.gob.osinergmin.soa.service.expediente.expedientes.crearexpediente.v1.CrearExpediente;
import pe.gob.osinergmin.soa.service.expediente.expedientes.crearexpediente.v1.CrearExpedienteResponse;
import pe.gob.osinergmin.soa.service.expediente.expedientes.crearexpediente.v1.FileType;
import pe.gob.osinergmin.soa.service.expediente.expedientes.crearexpediente.v1.FilesType;
import pe.gob.osinergmin.soa.service.expediente.expedientes.crearexpediente.v1.ServiceAP4033;
import pe.gob.osinergmin.soa.service.expediente.expedientes.crearexpediente.v1.XmlType;

@Component
@Slf4j
public class SigedSoapService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PropertiesConfig propertiesConfig;
	
	@Autowired
	private ArchivoService archivoService;

	/*
	 * Listar Documentos expedientes
	 * 
	 */
	public Documentos listarDocumentos(String nroExp, String files, AuditoriaDTO auditoriaDTO,
			Boolean obtenerNombresUsuario) {
		// Clase actual Siged
		Documentos obj = new Documentos();
		ListarDocumentosResponse resp = null;
		ServiceAP4034 service = null;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";
		try {
			service = ListarDocumentoExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_L_DOCUMENTOS_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());
			if (service != null) {
				// ACCESO EXPEDIENTE
				AccesoTypeAP4034 acceso = new AccesoTypeAP4034();
				acceso.setNroExpediente(nroExp);
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				acceso.setAccionServicio(ConstantesUtil.PARAM_L_DOCUMENTOS_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_TIPO_ACCION);
				// PARAMETRO DEL SERVICIO
				ParametrosTypeAP4034 parametro = new ParametrosTypeAP4034();
				parametro.setNumeroExpediente(new BigInteger(nroExp));
				parametro.setFiles(new BigInteger(files));
				parametro.setAcceso(acceso);
				// REQUEST
				ListarDocumentos reqTratado = new ListarDocumentos();
				reqTratado.setParametros(parametro);
				// RESPONSE Y CONTROL DE RESULTADOS
				resp = service.callServiceListarDocumentos(reqTratado,
						ListarDocumentoExpedienteServiceLocator.buildConsumidor(
								ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA, auditoriaDTO.getModuloPgimActual(),
								auditoriaDTO.getUsername(), auditoriaDTO.getTerminal()));

				if (resp.getAccesoResponse().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					// Todo correcto
					obj.setResultCode(resp.getResultCode().toString());
					obj.setMessage(resp.getMessage().toString());
					// Datos del documento
					obj.setListaDocumento(new LinkedList<Documento>());
					for (int i = 0; i < resp.getDocumento().size(); i++) {
						// Obtener documento
						Documento aDoc = (Documento) CommonsUtil.getAtributos2Object(
								"pe.gob.osinergmin.pgim.siged.Documento", "setArchivos", resp.getDocumento().get(i));
						aDoc.setArchivos(new LinkedList<Archivo>());
						// Obtener archivos
						for (int j = 0; j < resp.getDocumento().get(i).getArchivos().getArchivo().size(); j++) {
							Archivo archivo = (Archivo) CommonsUtil.getAtributos2Object(
									"pe.gob.osinergmin.pgim.siged.Archivo", "setVersiones-setFirmaDigitalSiged",
									resp.getDocumento().get(i).getArchivos().getArchivo().get(j));
							// carga de campo FlagBloqueo
							archivo.setFlagBloqueo(resp.getDocumento().get(i).getArchivos().getArchivo().get(j)
									.getFlagBloqueo().get(0).toString());
							// Validar si tiene firma electrónica
							FirmasDigitales firmasDigitales = resp.getDocumento().get(i).getArchivos().getArchivo()
									.get(j).getFirmasDigitales();

							DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

							if (firmasDigitales != null) {
								List<FirmaDigital> firmas = firmasDigitales.getFirmaDigital();
								archivo.setFirmaDigitalSiged(new LinkedList<FirmaDigitalSiged>());
								if (firmas != null && firmas.size() > 0) {
									for (int k = 0; k < firmas.size(); k++) {
										FirmaDigitalSiged firmaSiged = new FirmaDigitalSiged();

										firmaSiged.setIdUsuarioFirma(firmas.get(k).getIdUsuarioFirma().toString());

										String aDate = firmas.get(k).getFechaFirma().substring(0, 19);
										LocalDateTime dateTime = LocalDateTime.parse(aDate, originalFormat);
										firmaSiged.setFechaFirma(dateTime);

										if (obtenerNombresUsuario) {
											Usuario usuario = new Usuario();
											usuario.setIdUsuario(firmaSiged.getIdUsuarioFirma());
											UsuarioSiged usuarioSiged = this.obtenerUsuario(usuario);
											firmaSiged.setNombresUsuario(usuarioSiged.getListaUsuarios().get(0)
													.getUsuarios().get(0).getNombresUsuario());
											;
										}

										archivo.getFirmaDigitalSiged().add(firmaSiged);
									}
								}
							}
							aDoc.getArchivos().add(archivo);
						}

						if (aDoc.getNroDocumento().equals("null")) {
							aDoc.setNroDocumento("S/N");
						}

						obj.getListaDocumento().add(aDoc);
					}
				} else {
					ctrlError = true;
					// error al procesar
					obj.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					obj.setErrorCode("PGIM-DOC-AP4034");
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
							" LISTAR_DOCUMENTOS en el expediente " + nroExp, resp.getAccesoResponse().getErrorCode(),
							resp.getAccesoResponse().getMessage());
					obj.setMessage(mensajeErrorSiged);
					log.error(mensajeErrorSiged);
				}
			}
		} catch (Exception ex) {
			obj.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			obj.setErrorCode(ConstantesUtil.PARAM_RESULTADO_ERROR_EXCEPTION);
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					" LISTAR_DOCUMENTOS en el expediente " + nroExp, " PGIM-DOC-AP4034 ", ex.getMessage());
			obj.setMessage(mensajeErrorSiged);
			log.error(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			//throw new PgimException("error", mensajeErrorSiged);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		if (ctrlError) {
			//throw new PgimException("error", mensajeErrorSiged);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return obj;
	}

	/*
	 * Listar trazabilidad
	 */

	public ListarTrazabilidadDocumentosResponse listarTrazabilidad(String sIdDocumento, AuditoriaDTO auditoriaDTO)
			throws pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.FaultMsg, MalformedURLException {

		ServiceAP4038 service;
		ListarTrazabilidadDocumentosResponse resp = new ListarTrazabilidadDocumentosResponse();
		String mensajeErrorSiged = "";

		try {

			service = ListarTrazabilidadDocumentoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_L_TRAZABILIDAD_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {
				// ACCESOS AL EXPEDIENTE
				pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.AccesoType acceso = new pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.AccesoType();
				acceso.setIdDocumento(new BigInteger(sIdDocumento));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				acceso.setAccionServicio(ConstantesUtil.PARAM_L_TRAZABILIDAD_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_TIPO_ACCION);
				// PARAMETROS DEL SERVICIOS
				pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ParametrosType param = new pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ParametrosType();
				param.setAcceso(acceso);
				param.setIdDocumento(new BigInteger(sIdDocumento));
				// REQUEST
				ListarTrazabilidadDocumentos reqTratado = new ListarTrazabilidadDocumentos();
				reqTratado.setParametros(param);
				// RESPONSE
				resp = service.callServiceListarTrazabilidadDocumentos(reqTratado,
						ListarTrazabilidadDocumentoServiceLocator.buildConsumidor(
								ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA, auditoriaDTO.getModuloPgimActual(),
								auditoriaDTO.getUsername(), auditoriaDTO.getTerminal()));
			}

		} catch (ListarTrazabilidadDocumentoServiceLocatorException | NullPointerException | SOAPFaultException ex) {
			resp.setResultCode(new BigInteger(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS));
			resp.setErrorCode(new BigInteger(ConstantesUtil.PARAM_RESULTADO_ERROR_EXCEPTION));
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					" LISTAR_TRAZABILIDAD en el documento " + sIdDocumento, " PGIM-DOC-AP4038 ", ex.getMessage());
			resp.setMessage(mensajeErrorSiged);
			
			log.error(ex.getMessage(), ex);

			//throw new PgimException("error", mensajeErrorSiged);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return resp;
	}

	public ExpedienteOutRO crearExpediente(ExpedienteNuevo expNuevoSiged, MultipartFile multipartFile,
			AuditoriaDTO auditoriaDTO) {

		ExpedienteOutRO expedienteOutRO = new ExpedienteOutRO();
		ServiceAP4033 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";
		List<File> lstArchivosTmp = new ArrayList<>();

		try {
			
			// Validamos el nombre del archivo para evitar el error remitido por el Siged cuando se supera la longitud máxima
			multipartFile = this.archivoService.validarLongitudNombreArchivo(multipartFile);

			// ARCHIVOS NECESARIOS
			String carpetaTmp = propertiesConfig.getCarpetaTmp();
			// ARCHIVO CARGADO
			File fileDocumento = new File(carpetaTmp + multipartFile.getName());
			multipartFile.transferTo(fileDocumento);
			// XML DE CONFIGURACIÓN DEL SERVICIO
			String xmlPath = ExpedienteNuevo.getConstruirXmlFile(expNuevoSiged, carpetaTmp);
			File fileXml = new File(xmlPath);
			lstArchivosTmp.add(fileDocumento);
			lstArchivosTmp.add(fileXml);

			service = CrearExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_CREAR_EXP_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// ARCHIVOS
				DataSource dataSourceDocumento = new FileDataSource(fileDocumento);
				DataHandler handleDocumento = new DataHandler(dataSourceDocumento);
				// XML
				DataSource dataXml = new FileDataSource(fileXml);
				DataHandler handlerXml = new DataHandler(dataXml);
				// LIST
				FileType fileTypeDocumento = new FileType();
				fileTypeDocumento.setDocFileName(fileDocumento.getName());
				fileTypeDocumento.setDocFile(handleDocumento);

				FilesType filesType = new FilesType();
				filesType.getFile().add(fileTypeDocumento);

				XmlType xmlType = new XmlType();
				xmlType.setXmlFileName(fileXml.getName());
				xmlType.setXmlFile(handlerXml);

				CrearExpediente reqTratado = new CrearExpediente();
				reqTratado.setFiles(filesType);
				reqTratado.setXml(xmlType);

				CrearExpedienteResponse resp = service.callServiceCrearExpediente(reqTratado,
						CrearExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				log.info("crearExpediente - esp.getExpedienteOutRO().getResultCode() = " + resp.getExpedienteOutRO().getResultCode().toString());

				if (resp.getExpedienteOutRO().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {

					log.info("crearExpediente - Creación existosa" + resp.getExpedienteOutRO().toString());

					expedienteOutRO = (ExpedienteOutRO) CommonsUtil.getAtributos2Object(
							"pe.gob.osinergmin.pgim.siged.ExpedienteOutRO", "setDetalleExcepcionDTO", resp.getExpedienteOutRO());

					log.info("crearExpediente - expedienteOutRO - OK");							
				} else {

					log.info("crearExpediente - Creación NO existosa" + resp.getExpedienteOutRO().toString());

					ctrlError = true;
					expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					expedienteOutRO.setCodigoExpediente("0");
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " CREAR_EXPEDIENTE ",
							" PGIM-DOC-AP4033 " + resp.getExpedienteOutRO().getErrorCode(),
							resp.getExpedienteOutRO().getMessage());
					expedienteOutRO.setMessage(mensajeErrorSiged);

					log.error(mensajeErrorSiged);
				}
			}

		} catch (Exception ex) {
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "Crear expediente", ex.getStackTrace(),
					ex.getMessage());
			expedienteOutRO.setCodigoExpediente("0");
			expedienteOutRO.setMessage(mensajeErrorSiged);
			expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			
			log.error(mensajeErrorSiged, ex);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		
		}finally {
			// Borramos archivos temporales
			CommonsUtil.borrarArchivos(lstArchivosTmp, "Crear Expediente");	
		}

		if (ctrlError) {
			// Borramos archivos temporales
			CommonsUtil.borrarArchivos(lstArchivosTmp, "Crear Expediente");	
			throw new PgimException("error", mensajeErrorSiged);
		}

		return expedienteOutRO;
	}

	public Archivos obtenerArhivosSiged(String idDocumento, AuditoriaDTO auditoriaDTO) {

		Archivos archivos = new Archivos();
		ServiceAP4035 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";
		try {

			service = ListarArchivoDocumentoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_L_ARCHIVOS_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// ACCESO AL EXPEDIENTE
				AccesoType acceso = new AccesoType();
				acceso.setIdDocumento(new BigInteger(idDocumento));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				acceso.setAccionServicio(ConstantesUtil.PARAM_L_ARCHIVOS_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_TIPO_ACCION);

				// PARAMETROS DEL SERVICIO
				ParametrosType parametro = new ParametrosType();
				parametro.setIdDocumento(new BigInteger(idDocumento));
				parametro.setAcceso(acceso);

				// REQUEST
				ListarArchivos reqTratado = new ListarArchivos();
				reqTratado.setParametros(parametro);

				// RESPONSE
				ListarArchivosResponse resp = service.callServiceListarArchivos(reqTratado,
						ListarArchivoDocumentoServiceLocator.buildConsumidor(
								ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA, auditoriaDTO.getModuloPgimActual(),
								auditoriaDTO.getCoUsuarioSiged(), auditoriaDTO.getTerminal()));

				if (resp.getAccesoResponse().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {

					archivos.setListaArchivo(new LinkedList<Archivo>());
					archivos.setResultCode(resp.getResultCode().toString());
					archivos.setMessage(resp.getAccesoResponse().getMessage());

					for (int i = 0; i < resp.getArchivo().size(); i++) {
						Archivo archivo = (Archivo) CommonsUtil.getAtributos2Object(
								"pe.gob.osinergmin.pgim.siged.Archivo", "setVersiones-setFirmaDigitalSiged",
								resp.getArchivo().get(i));
						archivo.setIdDocumento(idDocumento);
						archivos.getListaArchivo().add(archivo);
					}
				} else {
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
							"LISTAR_ARCHIVOS para el documento " + idDocumento,
							resp.getAccesoResponse().getResultCode().toString(), resp.getAccesoResponse().getMessage());
					archivos.setMessage(mensajeErrorSiged);
					archivos.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					log.info(mensajeErrorSiged);
					ctrlError = true;
				}
			}
		} catch (Exception ex) {
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					"LISTAR_ARCHIVOS para el documento " + idDocumento, "PGIM-DOC-AP4035", ex.getMessage());
			archivos.setMessage(mensajeErrorSiged);
			archivos.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}

		if (ctrlError) {
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}
		
		// Ordenar archivos siged por su Id asc	
		if(archivos.getListaArchivo() != null) {
			List<Archivo> lArchivosSiged = archivos.getListaArchivo().stream()
					.sorted(Comparator.comparing(Archivo::getIdArchivo))
					.collect(Collectors.toList());	
			
			archivos.setListaArchivo(lArchivosSiged);
		}

		return archivos;
	}

	public DocumentoOutRO agregarDocumentoExpediente(DocumentoNuevo docNuevoSiged, MultipartFile multipartFile,
			String versionar, AuditoriaDTO auditoriaDTO) {

		DocumentoOutRO documentoOutRO = new DocumentoOutRO();
		ServiceAP4032 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";

		try {

			// ARCHIVOS NECESARIOS
			String carpetaTmp = propertiesConfig.getCarpetaTmp();
			// ARCHIVO CARGADO
			File fileDocumento = new File(carpetaTmp + multipartFile.getName());
			multipartFile.transferTo(fileDocumento);
			// XML DE CONFIGURACIÓN DEL SERVICIO
			String xmlPath = "";
			if (versionar.equals("0")) {
				xmlPath = DocumentoNuevo.getConstruirXmlFileNuevo(docNuevoSiged, carpetaTmp);
			} else {
				xmlPath = DocumentoNuevo.getConstruirXmlFileReemplazo(docNuevoSiged, carpetaTmp);
			}

			File fileXml = new File(xmlPath);

			service = AgregarDocumentoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_AGREGAR_DOC_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARA ESTA PRUEBA SE ENVIARAN DOS ARCHIVOS
				DataSource dataSourceDocumento = new FileDataSource(fileDocumento);
				DataHandler handleDocumento = new DataHandler(dataSourceDocumento);

				// XML
				DataSource dataXml = new FileDataSource(fileXml);
				DataHandler handlerXml = new DataHandler(dataXml);

				// ACCESO EXPEDIENTE
				pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.AccesoType acceso = new pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.AccesoType();
				acceso.setNroExpediente(docNuevoSiged.getNroExpediente());
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				
				// HDT.Inicio - 29.04.2022: Cambiado a solicitado de CCALERO para ejecutar la creación de documentos con el rol Siged de USUARIO_FINAL
				// acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				if (auditoriaDTO.getIdRolSiged().equals(ConstantesUtil.PARAM_ID_ROL_SUPERVISOR_CONCESIONARIA)) {
					acceso.setIdRol(new BigInteger(ConstantesUtil.PARAM_ID_ROL_USUARIO_FINAL));
				} else {
					acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				}
				// HDT.Fin				

				acceso.setAccionServicio(ConstantesUtil.PARAM_AGREGAR_DOC_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_AGREGAR_DOC_TIPO_ACCION);

				// LIST

				pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.FileType file1 = new pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.FileType();
				file1.setDocFileName(fileDocumento.getName());
				file1.setDocFile(handleDocumento);

				pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.FilesType files = new pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.FilesType();
				files.getFile().add(file1);

				// XMLTYPE
				pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.XmlType archivoXml = new pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.XmlType();
				archivoXml.setXmlFileName(fileXml.getName());
				archivoXml.setXmlFile(handlerXml);

				// PARAMETROS DEL SERVICE
				pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.ParametrosType parametrosType = new pe.gob.osinergmin.soa.service.expediente.documentos.agregardocumento.v1.ParametrosType();
				parametrosType.setAcceso(acceso);
				parametrosType.setVersion(versionar);

				AgregarDocumento reqTratado = new AgregarDocumento();
				reqTratado.setParametros(parametrosType);
				reqTratado.setFiles(files);
				reqTratado.setXml(archivoXml);

				// RESPONSE Y CONTROL DE RESULTADOS
				AgregarDocumentoResponse resp = service.callServiceAgregarDocumento(reqTratado,
						AgregarDocumentoServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				if (resp.getAccesoResponse().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					if (resp.getDocumentoOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						documentoOutRO.setResultCode(resp.getAccesoResponse().getResultCode().toString());
						documentoOutRO.setCodigoDocumento(resp.getDocumentoOutRO().getCodigoDocumento());
						documentoOutRO.setMessage(resp.getAccesoResponse().getMessage());
					} else {
						documentoOutRO.setCodigoDocumento("0");
						documentoOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
								" AGREGAR_REEMPLAZAR_DOCUMENTO en el expediente " + docNuevoSiged.getNroExpediente(),
								resp.getDocumentoOutRO().getErrorCode(), resp.getDocumentoOutRO().getMessage());
						documentoOutRO.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
						ctrlError = true;
					}
				} else {
					documentoOutRO.setCodigoDocumento("0");
					documentoOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
							" AGREGAR_REEMPLAZAR_DOCUMENTO en el expediente " + docNuevoSiged.getNroExpediente(),
							resp.getAccesoResponse().getErrorCode(), resp.getAccesoResponse().getMessage());
					documentoOutRO.setMessage(mensajeErrorSiged);
					log.info(mensajeErrorSiged);
					ctrlError = true;
				}
			}
		} catch (Exception ex) {
			documentoOutRO.setCodigoDocumento("0");
			documentoOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					" AGREGAR_REEMPLAZAR_DOCUMENTO en el expediente " + docNuevoSiged.getNroExpediente(),
					"PGIM-DOC-AP4032", ex.getMessage());
			documentoOutRO.setErrorCode("PGIM-DOC-AP4032");
			documentoOutRO.setMessage(mensajeErrorSiged);
			
			log.info(mensajeErrorSiged);

			log.error(ex.getMessage(), ex);

			throw new PgimException("error", mensajeErrorSiged);
		}
		if (ctrlError) {
			throw new PgimException("error", mensajeErrorSiged);
		}
		return documentoOutRO;
	}

	public Archivos agregarArchivosDocumento(Documento documento, String versionar, MultipartFile multipartFile,
			String NuExpedienteSiged, AuditoriaDTO auditoriaDTO) {

		Archivos archivos = new Archivos();
		archivos.setListaArchivo(new LinkedList<Archivo>());
		ServiceAP4036 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";

		try {

			String carpetaTmp = propertiesConfig.getCarpetaTmp();
			File fileDocument = new File(carpetaTmp + multipartFile.getName());
			multipartFile.transferTo(fileDocument);

			String xmlPath = Documento.getConstruirXmlFile(documento, carpetaTmp);
			File fileXml = new File(xmlPath);

			service = AgregarArchivoDocumentoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_AGREGAR_ARCHIVO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// ARCHIVOS
				DataSource dataDocument = new FileDataSource(fileDocument);
				DataHandler handleDocument = new DataHandler(dataDocument);

				// XML
				DataSource dataXml = new FileDataSource(fileXml);

				DataHandler handlerXml = new DataHandler(dataXml);

				// ACCESOS
				pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.AccesoType acceso = new pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.AccesoType();
				acceso.setIdDocumento(new BigInteger(documento.getIdDocumento()));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				acceso.setAccionServicio(ConstantesUtil.PARAM_AGREGAR_ARCHIVO_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_AGREGAR_ARCHIVO_TIPO_ACCION);

				// FILES
				pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.FileType file1 = new pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.FileType();

				file1.setDocFileName(fileDocument.getName());
				file1.setDocFile(handleDocument);

				pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.FilesType files = new pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.FilesType();
				files.getFile().add(file1);

				// FILE XML
				pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.XmlType archivoXml = new pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.XmlType();
				archivoXml.setXmlFileName(fileXml.getName());
				archivoXml.setXmlFile(handlerXml);

				// PARAMETROS SERVICIO
				pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.ParametrosType parametro = new pe.gob.osinergmin.soa.service.expediente.documentos.agregararchivoadoc.v1.ParametrosType();
				parametro.setAcceso(acceso);
				parametro.setVersion(versionar);

				AgregarArchivoADocumento reqTratado = new AgregarArchivoADocumento();
				reqTratado.setParametros(parametro);
				reqTratado.setFiles(files);
				reqTratado.setXml(archivoXml);

				AgregarArchivoADocumentoResponse resp = service.callServiceAgregarArchivoADocumento(reqTratado,
						AgregarArchivoDocumentoServiceLocator.buildConsumidor(
								ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA, auditoriaDTO.getModuloPgimActual(),
								auditoriaDTO.getUsername(), auditoriaDTO.getTerminal()));

				if (resp.getAccesoResponse().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					if (resp.getArchivos().getResultCode().toString().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						// Cargar los archivos
						archivos.setResultCode(resp.getArchivos().getResultCode().toString());
						archivos.setMessage(resp.getAccesoResponse().getMessage());
						for (int j = 0; j < resp.getArchivos().getArchivo().size(); j++) {
							Archivo archivo = (Archivo) CommonsUtil.getAtributos2Object(
									"pe.gob.osinergmin.pgim.siged.Archivo", "setVersiones-setFirmaDigitalSiged",
									resp.getArchivos().getArchivo().get(j));
							// carga de campo FlagBloqueo
							archivos.getListaArchivo().add(archivo);
						}
					} else {
						archivos.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
								" AGREGAR_ARCHIVO_DOCUMENTO en el expediente " + documento.getNroExpediente(),
								resp.getAccesoResponse().getErrorCode(), resp.getAccesoResponse().getMessage());
						archivos.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
						ctrlError = true;
					}
				} else {
					archivos.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
							" AGREGAR_ARCHIVO_DOCUMENTO en el expediente " + documento.getNroExpediente(),
							resp.getAccesoResponse().getErrorCode(), resp.getAccesoResponse().getMessage());
					archivos.setMessage(mensajeErrorSiged);
					log.info(mensajeErrorSiged);
					ctrlError = true;
				}
			}

		} catch (Exception ex) {
			archivos.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					" AGREGAR_ARCHIVO_DOCUMENTO en el expediente " + documento.getNroExpediente(), "PGIM-DOC-AP4036",
					ex.getMessage());
			archivos.setErrorCode("PGIM-DOC-AP4036");
			archivos.setMessage(mensajeErrorSiged);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		}
		if (ctrlError) {
			throw new PgimException("error", mensajeErrorSiged);
		}
		return archivos;
	}

	public DescargaArchivo descargarArchivo(String idArchivo, AuditoriaDTO auditoriaDTO) {

		DescargaArchivo descargaArchivo = new DescargaArchivo();
		ServiceAP4031 service;
		ByteArrayOutputStream aFile = null;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";
		try {

			service = DescargarArchivoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_DESCARGA_ARCHIVO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {
				// ACCESO AL EXPEDIENTE
				AccesoExpedientetype acceso = new AccesoExpedientetype();
				acceso.setIdArchivo(new BigInteger(idArchivo));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				acceso.setAccionServicio(ConstantesUtil.PARAM_DESCARGA_ARCHIVO_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_DESCARGA_ARCHIVO_TIPO_ACCION);

				// PARAMETROS DEL SERVICIO AP403-1
				pe.gob.osinergmin.soa.service.expediente.documentos.descargararchivo.v1.Parametrostype parametrosType = new pe.gob.osinergmin.soa.service.expediente.documentos.descargararchivo.v1.Parametrostype();
				parametrosType.setAccesoExpediente(acceso);
				parametrosType.setIdarchivo(Integer.parseInt(idArchivo));

				// REQUEST
				DescargarArchivo reqTratado = new DescargarArchivo();
				reqTratado.setParametros(parametrosType);

				// RESPONSE
				DescargarArchivoResponse resp = service.callServiceDescargarArchivo(reqTratado,
						DescargarArchivoServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				// DOCUMENTO DESCARGADO
				if (resp.getResultado().getResultCode().equals("1")) {

					InputStream inputStream = resp.getDownloadFile().getContents().getFilebase64().getInputStream();
					int bytesRead = -1;
					byte[] buffer = new byte[4096];
					aFile = new ByteArrayOutputStream(buffer.length);
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						aFile.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					descargaArchivo.setFile(aFile);
					descargaArchivo.setResultCode(ConstantesUtil.PARAM_RESULTADO_SUCCESS);
					descargaArchivo.setIdArchivo(idArchivo);
					descargaArchivo.setNombre(resp.getDownloadFile().getContents().getFileName());
				} else {
					descargaArchivo.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " DESCARGAR_ARCHIVO ",
							" PGIM-DOC-AP4031 ", resp.getResultado().getMessage());
					descargaArchivo.setErrorCode("PGIM-DOC-AP4031");
					descargaArchivo.setMessage(mensajeErrorSiged);
					log.info(mensajeErrorSiged);
					ctrlError = true;
				}
			}
		} catch (Exception ex) {
			descargaArchivo.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " DESCARGAR_ARCHIVO ",
					"PGIM-DOC-AP4031", ex.getMessage());
			descargaArchivo.setErrorCode("PGIM-DOC-AP4031");
			descargaArchivo.setMessage(mensajeErrorSiged);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		}

		if (ctrlError) {
			throw new PgimException("error", mensajeErrorSiged);
		}
		return descargaArchivo;
	}

	public ArchivoAnularOutRO anularArchivoSIGED(ArchivoAnularInRO archivoAnularInRO, AuditoriaDTO auditoriaDTO) {

		ArchivoAnularOutRO objResultado = new ArchivoAnularOutRO();
		ServiceAP40311 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";

		try {

			service = AnularArchivoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_ANULAR_ARCHIVO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// ACCESOS
				pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.AccesoType acceso = new pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.AccesoType();
				acceso.setIdArchivo(new BigInteger(archivoAnularInRO.getIdArchivo()));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				
				// HDT.Inicio - 29.04.2022: Cambiado a solicitado de CCALERO para ejecutar la creación de documentos con el rol Siged de USUARIO_FINAL
				// acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				if (auditoriaDTO.getIdRolSiged().equals(ConstantesUtil.PARAM_ID_ROL_SUPERVISOR_CONCESIONARIA)) {
					acceso.setIdRol(new BigInteger(ConstantesUtil.PARAM_ID_ROL_USUARIO_FINAL));
				} else {
					acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				}
				// HDT.Fin				
				

				acceso.setAccionServicio(ConstantesUtil.PARAM_ANULAR_ARCHIVO_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_ANULAR_ARCHIVO_TIPO_ACCION);

				// PARAMETROS
				pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.ParametrosType parametro = new pe.gob.osinergmin.soa.service.expediente.documentos.anulararchivo.v1.ParametrosType();
				parametro.setAcceso(acceso);
				parametro.setNroExpediente(archivoAnularInRO.getNroExpediente());
				parametro.setIdDocumento(new BigInteger(archivoAnularInRO.getIdDocumento()));
				parametro.setIdArchivo(new BigInteger(archivoAnularInRO.getIdArchivo()));
				parametro.setIdUsuarioAnulacion(auditoriaDTO.getCoUsuarioSiged());
				parametro.setMotivo(archivoAnularInRO.getMotivo());

				AnularArchivo reqTratado = new AnularArchivo();
				reqTratado.setParametros(parametro);

				AnularArchivoResponse resp = service.callServiceAnularArchivo(reqTratado,
						AnularArchivoServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				if (resp.getAccesoResponse().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					if (resp.getArchivoAnularOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						// Cargar objeto
						objResultado.setResultCode(resp.getAccesoResponse().getResultCode().toString());
						objResultado.setMessage(resp.getAccesoResponse().getMessage());
					} else {
						objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
								" ANULAR_ARCHIVO en el expediente " + archivoAnularInRO.getNroExpediente(),
								resp.getArchivoAnularOutRO().getErrorCode(), resp.getArchivoAnularOutRO().getMessage());
						objResultado.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
						
						if(resp.getArchivoAnularOutRO().getErrorCode().toString().equals("1000")){
							throw new PgimException(TipoResultado.WARNING, mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
						}

						ctrlError = true;
					}
				} else {
					objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
							" ANULAR_ARCHIVO en el expediente " + archivoAnularInRO.getNroExpediente(),
							resp.getAccesoResponse().getErrorCode(), resp.getAccesoResponse().getMessage());
					objResultado.setMessage(mensajeErrorSiged);
					log.info(mensajeErrorSiged);
					ctrlError = true;
				}
			}
		} catch (Exception ex) {
			objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					" ANULAR_ARCHIVO en el expediente " + archivoAnularInRO.getNroExpediente(), "PGIM-DOC-AP40311",
					ex.getMessage());
			objResultado.setErrorCode("PGIM-DOC-AP40311");
			objResultado.setMessage(mensajeErrorSiged);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);

			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

		}

		if (ctrlError) {
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return objResultado;
	}

	public DocumentoAnularOutRO anularDocumentoSIGED(DocumentoAnularInRO documentoAnularInRO,
			AuditoriaDTO auditoriaDTO) {

		DocumentoAnularOutRO objResultado = new DocumentoAnularOutRO();
		ServiceAP40312 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";

		try {

			service = AnularDocumentoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_ANULAR_DOCUMENTO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// ACCESOS
				pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.AccesoType acceso = new pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.AccesoType();
				acceso.setIdDocumento(new BigInteger(documentoAnularInRO.getIdDocumento()));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));

				// HDT.Inicio - 29.04.2022: Cambiado a solicitado de CCALERO para ejecutar la creación de documentos con el rol Siged de USUARIO_FINAL
				// acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				if (auditoriaDTO.getIdRolSiged().equals(ConstantesUtil.PARAM_ID_ROL_SUPERVISOR_CONCESIONARIA)) {
					acceso.setIdRol(new BigInteger(ConstantesUtil.PARAM_ID_ROL_USUARIO_FINAL));
				} else {
					acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				}
				// HDT.Fin

				acceso.setAccionServicio(ConstantesUtil.PARAM_ANULAR_DOCUMENTO_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_ANULAR_DOCUMENTO_TIPO_ACCION);

				// PARAMETROS
				pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.ParametrosType parametro = new pe.gob.osinergmin.soa.service.expediente.documentos.anulardocumento.v1.ParametrosType();
				parametro.setAcceso(acceso);
				parametro.setNroExpediente(documentoAnularInRO.getNroExpediente());
				parametro.setIdDocumento(new BigInteger(documentoAnularInRO.getIdDocumento()));
				parametro.setIdUsuarioAnulacion(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				parametro.setMotivo(documentoAnularInRO.getMotivo());

				AnularDocumento reqTratado = new AnularDocumento();
				reqTratado.setParametros(parametro);

				AnularDocumentoResponse resp = service.callServiceAnularDocumento(reqTratado,
						AnularDocumentoServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				if (resp.getAccesoResponse().getResultCode().toString()
						.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					if (resp.getDocumentoAnularOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						// Cargar objeto
						objResultado.setResultCode(resp.getAccesoResponse().getResultCode().toString());
						objResultado.setMessage(resp.getAccesoResponse().getMessage());
					} else {
						objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
								" ANULAR_DOCUMENTO en el expediente " + documentoAnularInRO.getNroExpediente(),
								resp.getDocumentoAnularOutRO().getErrorCode(),
								resp.getDocumentoAnularOutRO().getMessage());
						objResultado.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
						ctrlError = true;
					}
				} else {
					objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
							" ANULAR_DOCUMENTO en el expediente " + documentoAnularInRO.getNroExpediente(),
							resp.getAccesoResponse().getErrorCode(), resp.getAccesoResponse().getMessage());
					objResultado.setMessage(mensajeErrorSiged);
					log.info(mensajeErrorSiged);
					ctrlError = true;
				}
			}
		} catch (Exception ex) {
			objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					" ANULAR_DOCUMENTO en el expediente " + documentoAnularInRO.getNroExpediente(), "PGIM-DOC-AP40312",
					ex.getMessage());
			objResultado.setErrorCode("PGIM-DOC-AP40312");
			objResultado.setMessage(mensajeErrorSiged);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);  //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

		}

		if (ctrlError) {
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);  //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return objResultado;
	}

	public UsuarioSiged obtenerUsuario(Usuario usuarioPgim) {

		UsuarioSiged usuarioSiged = new UsuarioSiged();
		ServiceAP40313 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";

		try {
			service = ObtenerUsuarioServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_OBTENER_USUARIO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {
				// ACCESOS
				pe.gob.osinergmin.soa.service.expediente.documentos.obtenerusuario.v1.ParametrosType param = new pe.gob.osinergmin.soa.service.expediente.documentos.obtenerusuario.v1.ParametrosType();

				if (usuarioPgim.getUsuario() != null) {
					param.setUsuario(usuarioPgim.getUsuario());
				}

				if (usuarioPgim.getIdUsuario() != null && usuarioPgim.getIdUsuario() != "") {
					param.setIdUsuario(BigInteger.valueOf(Long.parseLong(usuarioPgim.getIdUsuario())));
				}

				ObtenerUsuario reqTratado = new ObtenerUsuario();
				reqTratado.setParametros(param);

				ObtenerUsuarioResponse resp = service.callServiceObtenerUsuario(reqTratado,
						ObtenerUsuarioServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, this.propertiesConfig.getUserSigedAdmin(),
								this.propertiesConfig.getTerminalSigedAdmin()));
				if (resp.getUsuarios().getUsuario().size() > 0) {
					usuarioSiged.setResultCode(resp.getResultCode().toString());
					usuarioSiged.setMessage(resp.getMessage());
					usuarioSiged.setErrorCode(resp.getErrorCode());
					List<Usuarios> listUsuarios = new LinkedList<Usuarios>();
					Usuarios usuarios = new Usuarios();
					List<Usuario> usuario = new LinkedList<Usuario>();

					for (int i = 0; i < resp.getUsuarios().getUsuario().size(); i++) {
						Usuario respUsuario = new Usuario();
						respUsuario.setIdUsuario(resp.getUsuarios().getUsuario().get(i).getIdUsuario().toString());
						respUsuario.setUsuario(resp.getUsuarios().getUsuario().get(i).getUsuario());
						respUsuario.setNombresUsuario(resp.getUsuarios().getUsuario().get(i).getNombresUsuario());
						respUsuario.setApellidosUsuario(resp.getUsuarios().getUsuario().get(i).getApellidosUsuario());
						respUsuario.setEstadoUsuario(resp.getUsuarios().getUsuario().get(i).getEstadoUsuario());
						respUsuario.setCorreoUsuario(resp.getUsuarios().getUsuario().get(i).getCorreoUsuario());
						usuario.add(respUsuario);
					}
					usuarios.setUsuarios(usuario);
					listUsuarios.add(usuarios);
					usuarioSiged.setListaUsuarios(listUsuarios);
				} else {
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "OBTENER_USUARIO",
							"PGIM-DOC-AP40313", "El usuario Siged no es válido");
					usuarioSiged.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					usuarioSiged.setMessage(mensajeErrorSiged);
					log.error(mensajeErrorSiged);
					ctrlError = true;
				}
			}
		} catch (Exception ex) {
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "OBTENER_USUARIO", "PGIM-DOC-AP40313",
					"Problemas al obtener el usuario " + ex.getMessage());
			usuarioSiged.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			usuarioSiged.setMessage(mensajeErrorSiged);
			log.error(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			//throw new PgimException("error", mensajeErrorSiged);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}

		if (ctrlError) {
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}

		return usuarioSiged;
	}

	public SigedPermisos accesoExpediente(String nroExpediente, AuditoriaDTO auditoriaDTO) {
		ServiceAP40316 service;
		SigedPermisos sigedPermisos = new SigedPermisos();
		String mensajeErrorSiged = "";

		try {

			service = AccederExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_ACCESO_EXPEDIENTE_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {
				// PARAMETROS DEL SERVICIO
				ObjectFactory object = new ObjectFactory();
				JAXBElement<String> exp = object.createAccesoExpedienteNroExpediente(nroExpediente);
				JAXBElement<Integer> us = object
						.createAccesoExpedienteIdUsuario(Integer.parseInt(auditoriaDTO.getCoUsuarioSiged()));
				JAXBElement<Integer> rol = object
						.createAccesoExpedienteIdRol(Integer.parseInt(auditoriaDTO.getIdRolSiged()));

				// REQUEST
				AccesoExpediente req = new AccesoExpediente();
				req.setNroExpediente(exp);
				req.setIdUsuario(us);
				req.setIdRol(rol);

				sigedPermisos.setDescNuExpedienteSiged(nroExpediente);

				// RESPONSE ListarDocumentos
				JAXBElement<String> accion = object
						.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_L_DOCUMENTOS_ACCION_SERVICIO);
				JAXBElement<String> tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				AccesoExpedienteResponse resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setListarDocumentos(Boolean.parseBoolean(resp.getAcceso().getValue()));

				// Crear expediente
				sigedPermisos.setCrearExpediente(true);

				// RESPONSE listarArchivos
				accion = object.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_L_ARCHIVOS_ACCION_SERVICIO);
				tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setListarArchivos(Boolean.parseBoolean(resp.getAcceso().getValue()));

				if (!sigedPermisos.isListarDocumentos()) {
					sigedPermisos.setMessage(String.format(ConstantesUtil.PARAM_MSG_NO_L_DOCUMENTOS, nroExpediente));
				}

				// RESPONSE agregarDocumento
				accion = object.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_AGREGAR_DOC_ACCION_SERVICIO);
				tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_AGREGAR_DOC_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setAgregarDocumento(Boolean.parseBoolean(resp.getAcceso().getValue()));

				// RESPONSE agregarArchivoADocumento
				accion = object
						.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_AGREGAR_ARCHIVO_ACCION_SERVICIO);
				tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_AGREGAR_ARCHIVO_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setAgregarArchivoADocumento(Boolean.parseBoolean(resp.getAcceso().getValue()));

				// RESPONSE descargarArchivo
				accion = object
						.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_DESCARGA_ARCHIVO_ACCION_SERVICIO);
				tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_DESCARGA_ARCHIVO_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setDescargarArchivo(Boolean.parseBoolean(resp.getAcceso().getValue()));

				// RESPONSE anularArchivo
				accion = object
						.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_ANULAR_ARCHIVO_ACCION_SERVICIO);
				tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_ANULAR_ARCHIVO_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setAnularArchivo(Boolean.parseBoolean(resp.getAcceso().getValue()));

				// RESPONSE anularDocumento
				accion = object
						.createAccesoExpedienteAccionServicio(ConstantesUtil.PARAM_ANULAR_DOCUMENTO_ACCION_SERVICIO);
				tipo = object.createAccesoExpedienteTipoAccion(ConstantesUtil.PARAM_ANULAR_DOCUMENTO_TIPO_ACCION);
				req.setAccionServicio(accion);
				req.setTipoAccion(tipo);

				resp = service.callServiceAccesoExpediente(req,
						AccederExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								auditoriaDTO.getModuloPgimActual(), auditoriaDTO.getUsername(),
								auditoriaDTO.getTerminal()));

				sigedPermisos.setAnularDocumento(Boolean.parseBoolean(resp.getAcceso().getValue()));
			}

		} catch (Exception ex) {
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "ACCESO_EXPEDIENTE", "PGIM-DOC-AP40316",
					ex.getMessage());
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		}
		return sigedPermisos;

	}

	public ExpedienteOutRO reenviarExpediente(String asunto, String contenido, Long destinatario,
			String numeroExpediente, int aprobacion, int idUsuario, int idRol) throws Exception {
		// throws FaultMsg, IOException {

		ExpedienteOutRO expedienteOutRO = new ExpedienteOutRO();
		String mensajeErrorSiged = "";
		boolean ctrlError = false;
		ServiceAP40318 service;

		try {

			service = ReenviarExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_REENVIAR_EXPEDIENTE_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARAMETROS DEL SERVICIO
				ReenviarExpediente req = new ReenviarExpediente();
				req.setAsunto(asunto);
				req.setContenido(contenido);
				req.setDestinatario(BigInteger.valueOf(destinatario));
				req.setNumeroExpediente(numeroExpediente);
				req.setAprobacion(BigInteger.valueOf(aprobacion));
				req.setIdUsuario(BigInteger.valueOf(idUsuario));
				req.setIdRol(BigInteger.valueOf(idRol));

				// RESPONSE
				ReenviarExpedienteResponse resp = service.callServiceReenviarExpediente(req,
						ReenviarExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, this.propertiesConfig.getUserSigedAdmin(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				if (resp.getAccesoResponse() != null) {
					if (resp.getAccesoResponse().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						expedienteOutRO.setResultCode(resp.getAccesoResponse().getResultCode().toString());
						expedienteOutRO.setMessage(resp.getAccesoResponse().getMessage());
					} else {
						ctrlError = true;
						expedienteOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						expedienteOutRO.setCodigoExpediente("0");
						/*mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " REENVIAR_EXPEDIENTE ",
								" PGIM-DOC-AP40318 " + resp.getAccesoResponse().getErrorCode(),
								resp.getAccesoResponse().getMessage());*/
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged," REENVIAR_EXPEDIENTE "+numeroExpediente,
								" PGIM-DOC-AP40318 " + 
								((resp.getAccesoResponse()!=null && resp.getAccesoResponse().getResultCode()!=null)?resp.getAccesoResponse().getResultCode().toString():""), 
								((resp.getAccesoResponse()!=null && resp.getAccesoResponse().getErrorCode()!=null)?resp.getAccesoResponse().getErrorCode().toString():"") + "/" +
								((resp.getAccesoResponse()!=null && resp.getAccesoResponse().getMessage()!=null)?resp.getAccesoResponse().getMessage():""));
						expedienteOutRO.setMessage(mensajeErrorSiged);
						log.error(mensajeErrorSiged);
					}
				}

				// System.out.println();
				System.out.println("expedienteOutRO");
				if (resp.getExpedienteOutRO() != null) {
					/*
					 * System.out.println("resultCode: " +
					 * resp.getExpedienteOutRO().getResultCode()); System.out.println("errorCode: "
					 * + resp.getExpedienteOutRO().getErrorCode()); System.out.println("message: " +
					 * resp.getExpedienteOutRO().getMessage()); System.out.println("resultado: " +
					 * resp.getExpedienteOutRO().getCodigoExpediente());
					 */

					if (resp.getExpedienteOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						/*
						 * expedienteOutRO = (ExpedienteOutRO) CommonsUtil.getAtributos2Object(
						 * "pe.gob.osinergmin.pgim.siged.ExpedienteOutRO", "",
						 * resp.getExpedienteOutRO());
						 */
						expedienteOutRO.setResultCode(resp.getExpedienteOutRO().getResultCode().toString());
						expedienteOutRO.setMessage(resp.getExpedienteOutRO().getMessage());
					} else {
						ctrlError = true;
						expedienteOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						expedienteOutRO.setCodigoExpediente("0");
						/*mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " REENVIAR_EXPEDIENTE ",
								" PGIM-DOC-AP40318 " + resp.getExpedienteOutRO().getErrorCode(),
								resp.getExpedienteOutRO().getMessage());*/
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged," REENVIAR_EXPEDIENTE "+numeroExpediente,
								" PGIM-DOC-AP40318 " + 
								((resp.getExpedienteOutRO()!=null && resp.getExpedienteOutRO().getResultCode()!=null)?resp.getExpedienteOutRO().getResultCode().toString():""), 
								((resp.getExpedienteOutRO()!=null && resp.getExpedienteOutRO().getErrorCode()!=null)?resp.getExpedienteOutRO().getErrorCode().toString():"") + "/" +
								((resp.getExpedienteOutRO()!=null && resp.getExpedienteOutRO().getMessage()!=null)?resp.getExpedienteOutRO().getMessage():""));
						expedienteOutRO.setMessage(mensajeErrorSiged);
						log.error(mensajeErrorSiged);
					}
				}
				
				
				if (resp.getAccesoResponse() == null && resp.getExpedienteOutRO() == null) {
					ctrlError = true;
					expedienteOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);					
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " REENVIAR_EXPEDIENTE "+numeroExpediente,
							" PGIM-DOC-AP40318 ", "No hay respuesta del método resp.getAccesoResponse(), ni del método resp.getExpedienteOutRO()...");

					expedienteOutRO.setMessage(mensajeErrorSiged);
					log.error(mensajeErrorSiged);
				}

			}

			// } catch (ReenviarExpedienteServiceLocatorException | NullPointerException |
			// SOAPFaultException ex) {
		} catch (ReenviarExpedienteServiceLocatorException | NullPointerException ex) {
			
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "Reenviar expediente",
					ex.getStackTrace(), ex.getMessage());
			expedienteOutRO.setCodigoExpediente("0");
			expedienteOutRO.setMessage(mensajeErrorSiged);
			expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			expedienteOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			
			DetalleExcepcionDTO detalleExcepcionDTO = new DetalleExcepcionDTO();
			detalleExcepcionDTO.setMensajeInfraResumen("No se pudo reenviar el expediente Siged N° " + numeroExpediente);					
			
			expedienteOutRO.setDetalleExcepcionDTO(detalleExcepcionDTO);			
//			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged, detalleExcepcionDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		if (ctrlError) {			
			DetalleExcepcionDTO detalleExcepcionDTO = new DetalleExcepcionDTO();
			detalleExcepcionDTO.setMensajeInfraResumen("No se pudo reenviar el expediente Siged N° " + numeroExpediente);					
			
			expedienteOutRO.setDetalleExcepcionDTO(detalleExcepcionDTO);
//			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged, detalleExcepcionDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario			
		}

		return expedienteOutRO;

	}

	public DevolverExpedienteOutRO devolverExpediente(String asunto, String motivo, String numeroExpediente,
			int idUsuario, int idRol) throws Exception {
		// public String devovlerExpediente() throws FaultMsg, IOException {
		DevolverExpedienteOutRO devolverExpOutRO = new DevolverExpedienteOutRO();
		String mensajeErrorSiged = "";
		boolean ctrlError = false;
		// String resultado = "";
		ServiceAP40323 service;

		try {
			// service = DevolverExpedienteServiceLocator.getInstance().getService(URL,
			// USERNAME, PASSWORD);

			service = DevolverExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_DEVOLVER_EXPEDIENTE_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARAMETROS DEL SERVICIO
				DevolverExpediente req = new DevolverExpediente();
				req.setAsunto(asunto);
				req.setMotivo(motivo);
				req.setIdUsuario(BigInteger.valueOf(idUsuario));
				req.setIdRol(BigInteger.valueOf(idRol));
				req.setNumeroExpediente(numeroExpediente);

				// RESPONSE
				DevolverExpedienteResponse resp = service.callServiceDevolverExpediente(req,
						DevolverExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, this.propertiesConfig.getUserSigedAdmin(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				if (resp.getAccesoResponse() != null) {
					/*
					 * System.out.println("errorCode: " + resp.getAccesoResponse().getErrorCode());
					 * System.out.println("message: " + resp.getAccesoResponse().getMessage());
					 * System.out.println("resultCode: " +
					 * resp.getAccesoResponse().getResultCode());
					 */
					if (resp.getAccesoResponse().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						devolverExpOutRO.setResultCode(resp.getAccesoResponse().getResultCode().toString());
						devolverExpOutRO.setMessage(resp.getAccesoResponse().getMessage());
					} else {
						ctrlError = true;
						devolverExpOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						// expedienteOutRO.setCodigoExpediente("0");
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " DEVOLVER_EXPEDIENTE ",
								" PGIM-DOC-AP40323 " + resp.getAccesoResponse().getErrorCode(),
								resp.getAccesoResponse().getMessage());
						devolverExpOutRO.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
					}
				}

				if (resp.getDevolverExpedienteOutRO() != null) {
					/*
					 * System.out.println("resultCode: " +
					 * resp.getDevolverExpedienteOutRO().getResultCode());
					 * System.out.println("errorCode: " +
					 * resp.getDevolverExpedienteOutRO().getErrorCode());
					 * System.out.println("message: " +
					 * resp.getDevolverExpedienteOutRO().getMessage());
					 */
					if (resp.getDevolverExpedienteOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {

						devolverExpOutRO.setResultCode(resp.getDevolverExpedienteOutRO().getResultCode().toString());
						devolverExpOutRO.setMessage(resp.getDevolverExpedienteOutRO().getMessage());
					} else {
						ctrlError = true;
						devolverExpOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						// expedienteOutRO.setCodigoExpediente("0");
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " DEVOLVER_EXPEDIENTE ",
								" PGIM-DOC-AP40323 " + resp.getDevolverExpedienteOutRO().getErrorCode(),
								resp.getDevolverExpedienteOutRO().getMessage());
						devolverExpOutRO.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
					}
				}

			}

			// } catch (DevolverExpedienteServiceLocatorException | NullPointerException |
			// SOAPFaultException ex) {
		} catch (DevolverExpedienteServiceLocatorException | NullPointerException ex) {
			// System.out.println(ex.getMessage());
			// resultado = "error al consumir el servicio";

			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "Devolver expediente",
					ex.getStackTrace(), ex.getMessage());
			// expedienteOutRO.setCodigoExpediente("0");
			devolverExpOutRO.setMessage(mensajeErrorSiged);
			devolverExpOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}
		// return resultado;

		if (ctrlError) {
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}

		return devolverExpOutRO;

	}

	public ExpedienteOutRO aprobarExpediente(String asunto, String contenido, Long destinatario,
			String numeroExpediente, int aprobacion, int idUsuario, int idRol) throws FaultMsg, IOException {

		ExpedienteOutRO expedienteOutRO = new ExpedienteOutRO();
		String mensajeErrorSiged = "";
		boolean ctrlError = false;
		// String resultado = "";
		ServiceAP40322 service;

		try {
			service = AprobarExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_APROBAR_EXPEDIENTE_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARAMETROS DEL SERVICIO
				AprobarExpediente req = new AprobarExpediente();
				req.setAsunto(asunto);
				req.setContenido(contenido);
				req.setDestinatario(BigInteger.valueOf(destinatario));
				req.setNumeroExpediente(numeroExpediente);
				req.setParaAprobacion(BigInteger.valueOf(aprobacion));
				req.setIdUsuario(BigInteger.valueOf(idUsuario));
				req.setIdRol(BigInteger.valueOf(idRol));

				// RESPONSE
				AprobarExpedienteResponse resp = service.callServiceAprobarExpediente(req,
						AprobarExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, this.propertiesConfig.getUserSigedAdmin(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				/*
				 * if (resp.getAccesoResponse() != null) { if
				 * (resp.getAccesoResponse().getResultCode().toString()
				 * .equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				 * expedienteOutRO.setResultCode(resp.getAccesoResponse().getResultCode().
				 * toString());
				 * expedienteOutRO.setMessage(resp.getAccesoResponse().getMessage()); } else {
				 * ctrlError = true;
				 * expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
				 * expedienteOutRO.setCodigoExpediente("0"); mensajeErrorSiged =
				 * String.format(ConstantesUtil.MENSAJE_ErrorSiged, " APROBAR_EXPEDIENTE ",
				 * " PGIM-DOC-AP40322 " + resp.getAccesoResponse().getErrorCode(),
				 * resp.getAccesoResponse().getMessage());
				 * expedienteOutRO.setMessage(mensajeErrorSiged); log.info(mensajeErrorSiged); }
				 * }
				 */

				if (resp.getExpedienteOutRO() != null) {

					if (resp.getExpedienteOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						expedienteOutRO.setResultCode(resp.getExpedienteOutRO().getResultCode().toString());
						expedienteOutRO.setMessage(resp.getExpedienteOutRO().getMessage());
					} else {
						ctrlError = true;
						expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						expedienteOutRO.setCodigoExpediente("0");
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " APROBAR_EXPEDIENTE ",
								" PGIM-DOC-AP40322 " + resp.getExpedienteOutRO().getErrorCode(),
								resp.getExpedienteOutRO().getMessage());
						expedienteOutRO.setMessage(mensajeErrorSiged);
						log.info(mensajeErrorSiged);
					}
				} else {

					ctrlError = true;
					expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					expedienteOutRO.setCodigoExpediente("0");
					mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " APROBAR_EXPEDIENTE ",
							" PGIM-DOC-AP40322 ", "No hay respuesta del método resp.getExpedienteOutRO()...");

					expedienteOutRO.setMessage(mensajeErrorSiged);
					log.error(mensajeErrorSiged);

				}

			}

			// } catch (AprobarExpedienteServiceLocatorException | NullPointerException |
			// SOAPFaultException ex) {
		} catch (AprobarExpedienteServiceLocatorException | NullPointerException ex) {
			/*
			 * System.out.println(ex.getMessage()); resultado =
			 * "error al consumir el servicio";
			 */

			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "Aprobar expediente",
					ex.getStackTrace(), ex.getMessage());
			expedienteOutRO.setCodigoExpediente("0");
			expedienteOutRO.setMessage(mensajeErrorSiged);
			expedienteOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);

		}

		// return resultado;
		if (ctrlError) {
			throw new PgimException(TipoResultado.ERROR, mensajeErrorSiged);
		}

		return expedienteOutRO;
	}

	public EnumerarDocumentoOutRO enumerarDocumento(Long idDocumento, String userName, int idUsuario, int idRol)
			throws Exception {

		EnumerarDocumentoOutRO enumerarDocOutRO = new EnumerarDocumentoOutRO();
		String mensajeErrorSiged = "";
		boolean ctrlError = false;
		ServiceAP40319 service;

		try {
			service = EnumerarExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_ENUMERAR_DOCUMENTO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARAMETROS DEL SERVICIO
				EnumeracionDocumetos req = new EnumeracionDocumetos();
				req.setIdDocumento(BigInteger.valueOf(idDocumento));
				req.setUsername(userName);
				req.setIdUsuario(BigInteger.valueOf(idUsuario));
				req.setIdRol(BigInteger.valueOf(idRol));

				// RESPONSE
				EnumeracionDocumetosResponse respuesta = service.callServiceEnumeracionDocumetos(req,
						EnumerarExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, this.propertiesConfig.getUserSigedAdmin(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				if (respuesta.getAccesoResponse() != null) {
					if (respuesta.getAccesoResponse().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						enumerarDocOutRO.setResultCode(respuesta.getAccesoResponse().getResultCode().toString());
						enumerarDocOutRO.setMessage(respuesta.getAccesoResponse().getMessage());
					} else {
						ctrlError = true;
						enumerarDocOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " ENUMERAR_DOCUMENTO ",
								" PGIM-DOC-AP40319.AR " + respuesta.getAccesoResponse().getErrorCode(),
								respuesta.getAccesoResponse().getMessage());
						enumerarDocOutRO.setMessage(mensajeErrorSiged);
						log.error(mensajeErrorSiged);
					}
				}

				if (respuesta.getDocumentoOutRO() != null) {
					if (respuesta.getDocumentoOutRO().getResultCode().toString()
							.equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						enumerarDocOutRO.setResultCode(respuesta.getDocumentoOutRO().getResultCode().toString());
						enumerarDocOutRO.setMessage(respuesta.getDocumentoOutRO().getMessage());
					} else {
						ctrlError = true;
						enumerarDocOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
						mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, " ENUMERAR_DOCUMENTO ",
								" PGIM-DOC-AP40319.DR " + respuesta.getDocumentoOutRO().getErrorCode(),
								respuesta.getDocumentoOutRO().getMessage());
						enumerarDocOutRO.setMessage(mensajeErrorSiged);
						log.error(mensajeErrorSiged);
					}
				}
			}
		} catch (EnumerarExpedienteServiceLocatorException | NullPointerException ex) {
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "Enumerar documento",
					ex.getStackTrace(), ex.getMessage());
			enumerarDocOutRO.setMessage(mensajeErrorSiged);
			enumerarDocOutRO.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);

		}

		if (ctrlError) {
			throw new PgimException("error", mensajeErrorSiged);
		}

		return enumerarDocOutRO;
	}

	public FechaFeriado esFeriado(String fechaConsulta)
			// throws FaultMsg, IOException {
			throws Exception {
		// String resultado = "";
		FechaFeriado fechaFeriado = new FechaFeriado();
		String mensajeErrorSiged = "";
		// boolean ctrlError = false;
		ServiceAP40321 service;

		try {
			service = EsFeriadoServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_ES_FERIADO_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARAMETROS DEL SERVICIO
				EsFeriado req = new EsFeriado();
				req.setFecha(fechaConsulta);

				// RESPONSE
				EsFeriadoResponse resp = service.callServiceEsFeriado(req,
						EsFeriadoServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, this.propertiesConfig.getUserSigedAdmin(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				// System.out.println("resp.getMensajeFeriado(): "+resp.getMensajeFeriado());
				fechaFeriado.setMensajeFeriado(resp.getMensajeFeriado());

				/*
				 * if (resp.getResultCode().toString()
				 * .equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				 * 
				 * //resultado = resp.getMensajeFeriado();
				 * fechaFeriado.setResultCode(resp.getResultCode().toString());
				 * fechaFeriado.setMessage(resp.getMensajeFeriado()); } else { //resultado =
				 * resp.getMessage();
				 * 
				 * ctrlError = true;
				 * fechaFeriado.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
				 * 
				 * mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
				 * " ES_FERIADO ", " PGIM-DOC-AP40321 " + resp.getErrorCode(),
				 * resp.getMessage()); fechaFeriado.setMessage(mensajeErrorSiged);
				 * log.info(mensajeErrorSiged); }
				 */
			}

			// } catch (EsFeriadoServiceLocatorException | NullPointerException |
			// SOAPFaultException ex) {
		} catch (EsFeriadoServiceLocatorException | NullPointerException ex) {

			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "Es feriado", ex.getStackTrace(),
					ex.getMessage());
			fechaFeriado.setMessage(mensajeErrorSiged);
			fechaFeriado.setErrorCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		}

		return fechaFeriado;
	}

	public BuscarExpedienteOut obtenerExpedienteSiged(String nroExpediente, AuditoriaDTO auditoriaDTO) {
		BuscarExpedienteOut buscarExpedienteOut = new BuscarExpedienteOut();
		ServiceAP4037 service;
		boolean ctrlError = false;
		String mensajeErrorSiged = "";
		try {

			service = BuscarExpedienteServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_BUSCAR_EXPEDIENTE_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// ACCESO AL EXPEDIENTE
				pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.AccesoType acceso = new pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.AccesoType();
				acceso.setNroExpediente(nroExpediente);
				acceso.setIdRol(new BigInteger(auditoriaDTO.getIdRolSiged()));
				acceso.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));
				acceso.setAccionServicio(ConstantesUtil.PARAM_BUSCAR_EXPEDIENTE_ACCION_SERVICIO);
				acceso.setTipoAccion(ConstantesUtil.PARAM_BUSCAR_EXPEDIENTE_TIPO_ACCION_SERVICIO);

				// PARAMETROS DEL SERVICIO
				pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.ParametrosType param = new pe.gob.osinergmin.soa.service.expediente.documentos.buscarexpediente.v1.ParametrosType();
				param.setAcceso(acceso);
				param.setNroExpediente(nroExpediente);

				// REQUEST
				BuscarExpediente reqTratado = new BuscarExpediente();
				reqTratado.setParametros(param);

				// RESPONSE
				BuscarExpedienteResponse resp = service.callServiceBuscarExpediente(reqTratado,
						BuscarExpedienteServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_BUSCAR_EXPEDIENTE_MODULO, auditoriaDTO.getUsername(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				if (resp.getResultCode() != null && resp.getResultCode().intValue() == 1) {

					if (resp.getAccesoResponse() != null) {
						buscarExpedienteOut.setArAcceso(resp.getAccesoResponse().getAcceso());
						buscarExpedienteOut.setArErrorCode(resp.getAccesoResponse().getErrorCode());
						buscarExpedienteOut.setArMessage(resp.getAccesoResponse().getMessage());
						buscarExpedienteOut.setArResultCode(resp.getAccesoResponse().getResultCode());
					}

					buscarExpedienteOut.setAsunto(resp.getAsunto());

					if (resp.getClienteCorreo() != null) {
						buscarExpedienteOut.setClienteCorreo(resp.getClienteCorreo().toString());
					}

					if (resp.getClienteDireccionAlternativa() != null) {
						buscarExpedienteOut
								.setClienteDireccionAlternativa(resp.getClienteDireccionAlternativa().toString());
					}

					buscarExpedienteOut.setClienteDireccionPrincipal(resp.getClienteDireccionPrincipal());

					if (resp.getClientes() != null) {
						if (resp.getClientes().getCliente() != null) {
							buscarExpedienteOut
									.setcCodigoCliente(resp.getClientes().getCliente().get(0).getCodigoCliente());
							buscarExpedienteOut.setcCodigoTipoIdentificacion(
									resp.getClientes().getCliente().get(0).getCodigoTipoIdentificacion());
							buscarExpedienteOut
									.setcNombreCliente(resp.getClientes().getCliente().get(0).getNombreCliente());
							buscarExpedienteOut.setcNumeroIdentificacion(
									resp.getClientes().getCliente().get(0).getNumeroIdentificacion());
						}
					}

					buscarExpedienteOut.setClienteTelefono(resp.getClienteTelefono());

					if (resp.getClienteUbigeoAlternativo() != null) {
						buscarExpedienteOut.setClienteUbigeoAlternativo(resp.getClienteUbigeoAlternativo().toString());
					}

					buscarExpedienteOut.setClienteUbigeoPrincipal(resp.getClienteUbigeoPrincipal());
					buscarExpedienteOut.setErrorCode(resp.getErrorCode());
					buscarExpedienteOut.setEsExpedienteSym(resp.getEsExpedienteSym());
					buscarExpedienteOut.setEstado(resp.getEstado());
					buscarExpedienteOut.setFechaArchivar(resp.getFechaArchivar());
					buscarExpedienteOut.setFechaCreacion(resp.getFechaCreacion());

					if (resp.getIdCreador() != null) {
						buscarExpedienteOut.setIdCreador(new BigInteger(resp.getIdCreador().toString()));
					}

					buscarExpedienteOut.setIdExpediente(resp.getIdExpediente());
					buscarExpedienteOut.setIdProceso(resp.getIdProceso());
					buscarExpedienteOut.setIdPropietario(resp.getIdPropietario());
					buscarExpedienteOut.setMessage(resp.getMessage());
					buscarExpedienteOut.setMsFechaArchivar(resp.getMsFechaArchivar());
					buscarExpedienteOut.setMsFechaCreacion(resp.getMsFechaCreacion());

					if (resp.getNombreCreador() != null) {
						buscarExpedienteOut.setNombreCreador(resp.getNombreCreador().toString());
					}

					buscarExpedienteOut.setNombreProceso(resp.getNombreProceso());
					buscarExpedienteOut.setNombrePropietario(resp.getNombrePropietario());
					buscarExpedienteOut.setNroExpediente(resp.getNroExpediente());

					if (resp.getRemitente() != null) {
						buscarExpedienteOut.setRemitente(resp.getRemitente().toString());
					}

					buscarExpedienteOut.setResultCode(resp.getResultCode());

				} else {
					buscarExpedienteOut.setErrorCode(new BigInteger(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS));
				}
			}
		} catch (Exception ex) {
			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
					"BUSCAR_EXPEDIENTE para el expediente " + nroExpediente, "SRV_AP403-7_EXPEDIENTE", ex.getMessage());
			buscarExpedienteOut.setMessage(mensajeErrorSiged);
			buscarExpedienteOut.setErrorCode(new BigInteger(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS));
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		}

		if (ctrlError) {
			throw new PgimException("error", mensajeErrorSiged);
		}

		return buscarExpedienteOut;
	}

	public RevertirFirmaResponse revertirFirma(Long idArchivo, String motivo, AuditoriaDTO auditoriaDTO)
			throws pe.gob.osinergmin.soa.service.expediente.documentos.revertirfirma.v1.FaultMsg, IOException {

		RevertirFirmaResponse revertirFirmaResponse = new RevertirFirmaResponse();
		ServiceAP40324 service;
		String mensajeErrorSiged = "";

		try {

			service = RevertirFirmaServiceLocator.getInstance().getService(
					this.propertiesConfig.getUrlSigedSoap() + ConstantesUtil.PARAM_REVERTIR_FIRMA_URL,
					this.propertiesConfig.getUsernameSigedSoap(), this.propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				// PARAMETROS DEL SERVICIO
				RevertirFirma req = new RevertirFirma();

				RevertirFirma.ArchivosRevertir.ArchivoRevertir archivoRevertir = new RevertirFirma.ArchivosRevertir.ArchivoRevertir();
				archivoRevertir.setIdArchivo(BigInteger.valueOf(idArchivo));
				archivoRevertir.setMotivoReversion(motivo);

				RevertirFirma.ArchivosRevertir archivosRevertir = new RevertirFirma.ArchivosRevertir();
				archivosRevertir.getArchivoRevertir().add(archivoRevertir);

				req.setArchivosRevertir(archivosRevertir);
				req.setIdUsuario(new BigInteger(auditoriaDTO.getCoUsuarioSiged()));

				// RESPONSE
				revertirFirmaResponse = service.callServiceRevertirFirma(req,
						RevertirFirmaServiceLocator.buildConsumidor(ConstantesUtil.PARAM_SIGED_SOAP_COD_SISTEMA,
								ConstantesUtil.PARAM_MODULO_GENERICO, auditoriaDTO.getUsername(),
								this.propertiesConfig.getTerminalSigedAdmin()));

				System.out.println(revertirFirmaResponse.getArchivosRevertir().getArchivoRevertir().getMessage());
				System.out.println(revertirFirmaResponse.getArchivosRevertir().getArchivoRevertir().getResultCode());

			}

		} catch (RevertirFirmaServiceLocatorException | NullPointerException | SOAPFaultException ex) {

			mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged, "REVERTIR_FIRMA_ARCHIVO", ex.getStackTrace(),
					ex.getMessage());
			revertirFirmaResponse.setMessage(mensajeErrorSiged);
			revertirFirmaResponse.setErrorCode(new BigInteger(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS));
			log.info(mensajeErrorSiged);
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", mensajeErrorSiged);
		}

		return revertirFirmaResponse;
	}

}
