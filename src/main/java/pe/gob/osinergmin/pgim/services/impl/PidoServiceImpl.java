/**
 * 
 */
package pe.gob.osinergmin.pgim.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.pido.IntegracionPido;
import pe.gob.osinergmin.pgim.pido.PersonaJuridica;
import pe.gob.osinergmin.pgim.pido.PersonaNatural;
import pe.gob.osinergmin.pgim.pido.PidoBeanOutRO;
import pe.gob.osinergmin.pgim.services.PidoService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.soa.DatosBasicosCiudadanoOrquestadoServiceLocator;
import pe.gob.osinergmin.soa.DatosBasicosCiudadanoOrquestadoServiceLocatorException;
import pe.org.osinergmin.soa.schema.consultaidentificacion.ciudadano.orquestado.CiudadanoOrquestadoConsultarReqParamTYPE;
import pe.org.osinergmin.soa.schema.consultaidentificacion.ciudadano.orquestado.CiudadanoOrquestadoConsultarRespParamTYPE;
import pe.org.osinergmin.soa.service.consultaidentificacion.ciudadano.orquestado.CiudadanoConsultarPT;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la PIDO
 * 
 * @author: barrantesluis
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class PidoServiceImpl implements PidoService {

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Override
	public IntegracionPido procesaObtenerCiudadano(String numeroDNIoCE, AuditoriaDTO auditoriaDTO)
			throws JAXBException {

		String serverUrl = propertiesConfig.geturlPido();
		String urlServicio = ConstantesUtil.PARAM_PIDO_SUNAT_CONSULTAR_DNI;
		log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_INI,
				"CONSULTAR_DNI : " + auditoriaDTO.getUsername()));
		IntegracionPido integracionPido = new IntegracionPido();

		try {
			URL url = new URL(serverUrl + urlServicio);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(ConstantesUtil.PARAM_REQUEST_METHOD_POST);
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setAllowUserInteraction(true);
			conn.setRequestProperty(ConstantesUtil.PARAM_REQUEST_PROPERTY_CONTENT_TYPE, "application/soap+xml");
			OutputStreamWriter infWebSvcReqWriter = new OutputStreamWriter(conn.getOutputStream());

			integracionPido.setNumeroDNIoCE(numeroDNIoCE);
			integracionPido.setUsuarioPido(propertiesConfig.getUsuarioPido());
			integracionPido.setPasswordPido(propertiesConfig.getPasswordPido());

			String xmlStringReq = IntegracionPido.getXmlObtenerCiudadano(integracionPido, auditoriaDTO);
			infWebSvcReqWriter.write(xmlStringReq);
			infWebSvcReqWriter.flush();

			if (conn.getResponseCode() != 200) {
				log.error(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN_ERROR,
						"CONSULTAR_DNI: " + auditoriaDTO.getUsername()));
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())), 100000);
			StringBuilder xmlString = new StringBuilder("");
			String output;
			while ((output = br.readLine()) != null) {
				output = CommonsUtil.getLimpiarString(output);
				xmlString.append(output);
			}

			Document doc = CommonsUtil.convertStringToXMLDocument(xmlString);
			doc.getDocumentElement().normalize();

			NodeList nodeListPrincipal = doc.getElementsByTagName(ConstantesUtil.PARAM_PIDO_TAG_RAIZ);
			Node nodeRaiz = nodeListPrincipal.item(0);
			Node nodeBody = nodeRaiz.getLastChild();
			Node nodeRespParam = nodeBody.getFirstChild();
			NodeList nodeListCiudadano = nodeRespParam.getChildNodes();
			// Node node3 = node2.getChildNodes().item(0);
			integracionPido.setLstPersonaNatural(new ArrayList<PersonaNatural>());
			String claseXMLPadre = "pe.gob.osinergmin.pgim.pido.PersonaNatural";
			String claseXMLHijo = "";
			String nodoHijo = "";
			String methodsOff = "setFoto";

			for (int i = 0; i < nodeListCiudadano.getLength(); i++) {
				try {
					String name = nodeListCiudadano.item(i).getNodeName();
					Element element = (Element) nodeListCiudadano.item(i);
					if (nodeListCiudadano.item(i).getNodeType() == Node.ELEMENT_NODE) {
						if (name.contains("ciudadano")) {
							integracionPido.getLstPersonaNatural()
									.add((PersonaNatural) CommonsUtil.getXML2ObjectPrefijo(
											ConstantesUtil.PARAM_PIDO_TAG_ITEM_RESULTADO, nodeListCiudadano.item(i),
											claseXMLPadre, claseXMLHijo, nodoHijo, methodsOff, element, null));
						}
						if (name.contains("coResultado")) {
							integracionPido.setCoResultado(element.getTextContent());
						}
						if (name.contains("deResultado")) {
							integracionPido.setDeResultado(element.getTextContent());
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN, "CONSULTAR_DNI: " + e.getMessage()));
			;
		}
		log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN,
				"CONSULTAR_DNI: " + auditoriaDTO.getUsername()));

		return integracionPido;
	}

	@Override
	public IntegracionPido procesaObtenerContribuyente(String numeroRUC, AuditoriaDTO auditoriaDTO)
			throws JAXBException {

		String serverUrl = propertiesConfig.geturlPido();
		String urlServicio = ConstantesUtil.PARAM_PIDO_SUNAT_CONSULTAR_RUC;
		log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_INI,
				"CONSULTAR_RUC : " + auditoriaDTO.getUsername()));
		IntegracionPido integracionPido = new IntegracionPido();

		try {
			URL url = new URL(serverUrl + urlServicio);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(ConstantesUtil.PARAM_REQUEST_METHOD_POST);
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setAllowUserInteraction(true);
			conn.setRequestProperty(ConstantesUtil.PARAM_REQUEST_PROPERTY_CONTENT_TYPE, "application/soap+xml");
			OutputStreamWriter infWebSvcReqWriter = new OutputStreamWriter(conn.getOutputStream());

			integracionPido.setNumeroRUC(numeroRUC);
			integracionPido.setUsuarioPido(propertiesConfig.getUsuarioPido());
			integracionPido.setPasswordPido(propertiesConfig.getPasswordPido());

			String xmlStringReq = IntegracionPido.getXmlObtenerPersonaJuridica(integracionPido, auditoriaDTO);
			infWebSvcReqWriter.write(xmlStringReq);
			infWebSvcReqWriter.flush();

			if (conn.getResponseCode() != 200) {
				log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN_ERROR,
						"CONSULTAR_RUC: " + auditoriaDTO.getUsername()));
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())), 100000);
			StringBuilder xmlString = new StringBuilder("");
			String output;
			while ((output = br.readLine()) != null) {
				output = CommonsUtil.getLimpiarString(output);
				xmlString.append(output);
			}

			Document doc = CommonsUtil.convertStringToXMLDocument(xmlString);
			doc.getDocumentElement().normalize();

			NodeList nodeListPrincipal = doc.getElementsByTagName(ConstantesUtil.PARAM_PIDO_TAG_RAIZ);
			Node nodeRaiz = nodeListPrincipal.item(0);
			Node nodeBody = nodeRaiz.getLastChild();
			Node nodeRespParam = nodeBody.getFirstChild();
			NodeList nodeListBase = nodeRespParam.getChildNodes();
			// Node node3 = node2.getChildNodes().item(0);
			integracionPido.setLstPersonaJuridica(new ArrayList<PersonaJuridica>());
			String claseXMLPadre = "pe.gob.osinergmin.pgim.pido.PersonaJuridica";
			String claseXMLHijo = "";
			String nodoHijo = "";
			String methodsOff = "";

			for (int i = 0; i < nodeListBase.getLength(); i++) {
				try {
					String name = nodeListBase.item(i).getNodeName();
					Element element = (Element) nodeListBase.item(i);
					if (nodeListBase.item(i).getNodeType() == Node.ELEMENT_NODE) {
						if (name.contains("datosPrincipales")) {
							integracionPido.getLstPersonaJuridica()
									.add((PersonaJuridica) CommonsUtil.getXML2ObjectPrefijo(
											ConstantesUtil.PARAM_PIDO_TAG_ITEM_RESULTADO, nodeListBase.item(i),
											claseXMLPadre, claseXMLHijo, nodoHijo, methodsOff, element, null));
						}
						if (name.contains("coResultado")) {
							integracionPido.setCoResultado(element.getTextContent());
						}
						if (name.contains("deResultado")) {
							integracionPido.setDeResultado(element.getTextContent());
						}
					}
				} catch (IllegalArgumentException e) {
					log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN,
							"CONSULTAR_RUC: " + e.getMessage()));
					;
					log.error(e.getMessage(), e);
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			log.error(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN, "CONSULTAR_RUC: " + e.getMessage()));
			;
			log.error(e.getMessage(), e);
		}
		log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_FIN,
				"CONSULTAR_RUC: " + auditoriaDTO.getUsername()));

		if (!integracionPido.getLstPersonaJuridica().get(0).getDdp_nombre().equals("")) {
			integracionPido.setCoResultado("0000");
			integracionPido.setDeResultado("Consulta realizada correctamente");
		} else {
			integracionPido.setCoResultado("0001");
			integracionPido.setDeResultado("No se ha encontrado información para el RUC " + numeroRUC);
		}

		return integracionPido;
	}

	@Override
	public PidoBeanOutRO procesaConsultarCiudadano(String numeroDNIoCE, AuditoriaDTO auditoriaDTO) throws Exception {

		String serverUrl = propertiesConfig.geturlPido();
		String urlServicio = ConstantesUtil.PARAM_PIDO_RENIEC_CONSULTAR_DNI;
		String PIDO_URL_SERVICE_RENIEC_ORQUESTADO = serverUrl.concat(urlServicio);
		log.info(String.format(ConstantesUtil.PARAM_PIDO_LOG_DE_USO_INI,
				"CONSULTAR_CIUDADANO : " + auditoriaDTO.getUsername()));

		PidoBeanOutRO pidoOutRO = new PidoBeanOutRO();

		CiudadanoConsultarPT service;

		try {

			service = DatosBasicosCiudadanoOrquestadoServiceLocator.getInstance().getService(
					PIDO_URL_SERVICE_RENIEC_ORQUESTADO, propertiesConfig.getUsernameSigedSoap(),
					propertiesConfig.getPasswordSigedSoap());

			if (service != null) {

				CiudadanoOrquestadoConsultarReqParamTYPE req = new CiudadanoOrquestadoConsultarReqParamTYPE();

				req.setNumDni(numeroDNIoCE);

				req.setDniUsuario(propertiesConfig.getUserDniWsPido());

				req.setPassword(propertiesConfig.getPasswordWsPido());

				req.setDniUsuarioReniec(propertiesConfig.getReniecUserDniWsPido());

				CiudadanoOrquestadoConsultarRespParamTYPE resp = service.ciudadanoConsultar(req,
						DatosBasicosCiudadanoOrquestadoServiceLocator.buildConsumidor("PGIM", "RENIEC",
								auditoriaDTO.getUsername(), auditoriaDTO.getTerminal()));

				pidoOutRO.setResultCode(resp.getCoResultado());

				pidoOutRO.setMessage(resp.getDeResultado());

				pidoOutRO.setNombres(resp.getCiudadano().getNombres());

				pidoOutRO.setApellidoPaterno(resp.getCiudadano().getApellidoPaterno());

				pidoOutRO.setApellidoMaterno(resp.getCiudadano().getApellidoMaterno());

				pidoOutRO.setDireccion(resp.getCiudadano().getDireccion());

				pidoOutRO.setDeResultado(resp.getDeResultado());

				pidoOutRO.setEsCiudadanoValido(!resp.getCiudadano().getRestriccion().equalsIgnoreCase("FALLECIMIENTO"));

				pidoOutRO.setRestriccion(resp.getCiudadano().getRestriccion());

				pidoOutRO.setUbigeo(resp.getCiudadano().getUbigeo());

			}

			log.info("Exito de consumer PidoApiConsumerImpl - obtenerCiudadanoLocal()");

		} catch (DatosBasicosCiudadanoOrquestadoServiceLocatorException | javax.xml.ws.WebServiceException
				| NullPointerException ex) {
			log.error("Error de consumer PidoApiConsumerImpl - obtenerCiudadanoLocal()", ex);
			pidoOutRO.setResultCode("");
			pidoOutRO.setDeResultado("");
		} catch (Exception ex) {
			log.error("Error de consumer PidoApiConsumerImpl - obtenerCiudadanoLocal()", ex);
			pidoOutRO.setResultCode("");
			pidoOutRO.setDeResultado("");
		}

		return pidoOutRO;
	}
}
