package pe.gob.osinergmin.pgim.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.UserAuthDTO;

@Slf4j
public class CommonsUtil {

	public static String capitalizar(String cadena){
		
		if(cadena == null || cadena.isEmpty()) {
			return cadena;  
		}

		cadena = cadena.toLowerCase();

		String resultado = Arrays.stream(cadena.split("\\s+"))
        .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
        .collect(Collectors.joining(" "));

		return resultado;
	  }
			
	public static boolean isNullOrZeroLong(Long valor) {

		boolean rpta = false;

		if (valor != null && valor != 0L) {
			rpta = true;
		}

		return rpta;
	}

	/**
	 * Permite convertir una fecha a cada de acuerdo con el formato especificado.
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String convertirFechaACadena(Date fecha, String formato) {
		if (fecha == null) {
			return "";
		}

		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(fecha);
	}

	/**
	 * Permite obetner los valores de un XML.
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String getTagValue(String tag, Element element) {
		if (element.getElementsByTagName(tag).item(0) == null) {
			return "";
		}
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		if (node != null)
			return node.getNodeValue();
		else
			return "";
	}

	public static String getTagValuePrefijo(String prefijo, String tag, Element element) {
		if (element.getElementsByTagName(prefijo+tag).item(0) == null) {
			return ""; 
		}
		NodeList nodeList = element.getElementsByTagName(prefijo+tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		if (node != null) {
			return node.getNodeValue().trim();
		}
		else
			return "";
	}

	
	public static String getNodeValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public static String getLimpiarString(String tag) {
		tag = tag.replace("&lt;p&gt;", "");
		tag = tag.replace("&lt;br /&gt;", "");
		tag = tag.replace("&#xD;", "");
		tag = tag.replace("&lt;/p&gt;", "");
		return tag;
	}

	public static Document convertStringToXMLDocument(StringBuilder xmlString) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		builder = factory.newDocumentBuilder();
		xmlString.trimToSize();
		List<String> xmlSecString = new ArrayList<String>();
		int xmlLenthg = xmlString.length();
		int xmlSec = (int) (xmlLenthg / 5000);
		int xmlLenthgAdd = 0;
		if (xmlSec == 0) {
			xmlSecString.add(xmlString.substring(0, xmlLenthg));
		} else {
			for (int i = 0; i < xmlSec; i++) {
				if (i == 0) {
					xmlSecString.add(xmlString.substring(i * 5000, 5000 * (i + 1)));
				} else {
					xmlSecString.add(xmlString.substring((i * 5000), 5000 * (i + 1)));
				}
				xmlLenthgAdd += 5000;
			}
		}

		if ((xmlLenthg - xmlLenthgAdd) > 0) {
			xmlSecString.add(xmlString.substring((xmlLenthgAdd), xmlLenthg));
		}

		Document doc = null;
		
		String cadena ="";
		for (int i = 0; i <= xmlSec; i++) {
			cadena = cadena+xmlSecString.get(i);
		}
		
		if(cadena.length()>0) {
			doc = builder.parse(new InputSource(new StringReader(cadena)));
		}		
		
		return doc;
	}

	public static String getResultCode(Document document, String tagParent, String tagResult) {
		String resultCode = null;
		NodeList nodeList = document.getElementsByTagName(tagParent);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodeList.item(0);
				resultCode = CommonsUtil.getTagValue("resultCode", element);
			}
		}

		return resultCode;
	}

	public static String getValue2Tag(Document document, String tagParent, String tagResult) {
		String resultCode = null;
		NodeList nodeList = document.getElementsByTagName(tagParent);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodeList.item(0);
				resultCode = CommonsUtil.getTagValue(tagResult, element);
			}
		}

		return resultCode;
	}

	public static Object getXML2Object(Node node, String claseXMLPadre, String claseXMLHijo, String nodoHijo,
			String methodsOff, Element element, List<Object> lista) throws Exception, IllegalAccessException {
		Class aClass;
		Object abean = null;
		Method method;
		Method methodAdd = null;
		aClass = Class.forName(claseXMLPadre);
		abean = aClass.newInstance();
		String[] arrMethodsOff = methodsOff.split("-");

		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			String nameMethod = method.getName();
			int lengthNameMethod = method.getName().length();
			nameMethod = nameMethod.substring(3, 4).toLowerCase() + nameMethod.substring(4, lengthNameMethod);
			if (method.getName().substring(0, 3).contains("set") && !Arrays.asList(arrMethodsOff).contains(method.getName())) {
				method.invoke(abean, CommonsUtil.getTagValue(nameMethod, element));	
			}			
				
			if (method.getName().equals(methodsOff)) {
				methodAdd = method;
			}
				
		}
		for (int i = 0; i < element.getChildNodes().getLength(); ++i) {
			Node nodeHijo = element.getChildNodes().item(i);
			if (nodeHijo.getNodeName().equals(nodoHijo)) {
				for (int j = 0; j < nodeHijo.getChildNodes().getLength(); ++j) {
					Node nodeHijoItem = nodeHijo.getChildNodes().item(j);
					String methodsOffHijo = "setFirmaDigitalSiged-setVersiones";
					if (nodeHijoItem.getNodeType() == Node.ELEMENT_NODE) {
						Element elementHijo = (Element) nodeHijoItem;
						lista.add(CommonsUtil.getXML2Object(nodeHijoItem, claseXMLHijo, "", "", methodsOffHijo, elementHijo, lista));
					}
				}
				try {
					methodAdd.invoke(abean, lista);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					log.error(e.getMessage(), e);
					e.printStackTrace();
				}
			}
		}

		return abean;
	}
	
	
	public static Object getAtributos2Object(String claseResultado, String methodsOff, Object element) throws Exception {
		Class aClass;
		Object abean = null;
		Method method;
		aClass = Class.forName(claseResultado);
		abean = aClass.newInstance();
		
		String[] methods = methodsOff.split("-");
		String methodsOff0 = "";
		String methodsOff1 = "";
		
		if (methods.length == 1) {
			methodsOff0 = methods[0];
		}
		else {
			methodsOff0 = methods[0];
			methodsOff1 = methods[1];
		}
		
		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			String nameMethod = method.getName();
			int lengthNameMethod = method.getName().length();
			nameMethod = nameMethod.substring(3, 4).toLowerCase() + nameMethod.substring(4, lengthNameMethod);
			
			try {
				if (method.getName().substring(0, 3).contains("set")
						&& (!method.getName().equals(methodsOff0) && !method.getName().equals(methodsOff1))) {
					if (getValue2Object(method, element) != null) {
						method.invoke(abean, getValue2Object(method, element).toString());
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw e;
			}		
		}
		return abean;
	}

	public static Object getValue2Object(Method method, Object element) throws Exception{
		Object value = "";
		String methodName = method.getName();
		String methodNameFin = methodName.replaceFirst("s", "g"); 
		Class aClass = null;
		aClass = element.getClass();
		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			if (method.getName().equals(methodNameFin)) {
				value = method.invoke(element);
			}
		}
		return value;
	}
		
	public static Object getXML2ObjectPrefijo(String prefijo, Node node, String claseXMLPadre, String claseXMLHijo, String nodoHijo,
			String methodsOff, Element element, List<Object> lista) throws Exception{
		Class aClass;
		Object abean = null;
		Method method;
		Method methodAdd = null;
		aClass = Class.forName(claseXMLPadre);
		abean = aClass.newInstance();

		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			String nameMethod = method.getName();
			int lengthNameMethod = method.getName().length();
			nameMethod = nameMethod.substring(3, 4).toLowerCase() + nameMethod.substring(4, lengthNameMethod);
			if (method.getName().substring(0, 3).contains("set") && !method.getName().equals(methodsOff)) {
				method.invoke(abean, CommonsUtil.getTagValuePrefijo(prefijo, nameMethod, element));	
			}			
				
			if (method.getName().equals(methodsOff)) {
				methodAdd = method;
			}
				
		}
		
		for (int i = 0; i < element.getChildNodes().getLength(); ++i) {
			Node nodeHijo = element.getChildNodes().item(i);
			if (nodeHijo.getNodeName().equals(nodoHijo)) {
				for (int j = 0; j < nodeHijo.getChildNodes().getLength(); ++j) {
					Node nodeHijoItem = nodeHijo.getChildNodes().item(j);
					if (nodeHijoItem.getNodeType() == Node.ELEMENT_NODE) {
						Element elementHijo = (Element) nodeHijoItem;
						lista.add(CommonsUtil.getXML2Object(nodeHijoItem, claseXMLHijo, "", "", "", elementHijo, lista));
					}
				}
				methodAdd.invoke(abean, lista);
			}
		}
		return abean;
	}

	public String stringToDom(String xmlSource, String name, String carpetaTmp) throws Exception {
		String pathFile = "";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		pathFile = carpetaTmp + name + ".xml";

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(pathFile));
		transformer.transform(source, result);

		return pathFile;
	}

	public static String generadorNombreUnico() {
		Calendar cal = Calendar.getInstance();
		String nombre = String.valueOf(cal.get(Calendar.YEAR))
				+ String.valueOf(cal.get(Calendar.MONTH)+1) + String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
						+ "-" +String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + String.valueOf(cal.get(Calendar.MINUTE))
						+ String.valueOf(cal.get(Calendar.SECOND)) + String.valueOf(cal.get(Calendar.MILLISECOND));
		return nombre;
	}

	public static PrintWriter addFileToService(String fieldName, File uploadFile, PrintWriter writer,
			OutputStream outputStream, String boundary) throws IOException {
		String LINE_FEED = "\r\n";
		String fileName = uploadFile.getName();
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED);
		writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
		writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.flush();

		FileInputStream inputStream = new FileInputStream(uploadFile);
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.flush();
		inputStream.close();

		writer.append(LINE_FEED);
		writer.flush();

		return writer;
	}

	/**
	 * Permite obtener la extensión del nombre del archivo.
	 * @param nombreArchivo
	 * @return
	 */
	public static String obtenerExtensionNombreArchivo(String nombreArchivo) {
		String extension = "";

		int i = nombreArchivo.lastIndexOf('.');
		if (i > 0) {
			extension = nombreArchivo.substring(i + 1);
		}

		return extension;
	}
	
	
	public static String extensionArchivoToLowerCase(String originalFileName) {
		originalFileName = originalFileName.trim();
		String extensionArchivo = obtenerExtensionNombreArchivo(originalFileName).toLowerCase();
		String nombreArchivoSinExtension = originalFileName.substring(0, originalFileName.length()-extensionArchivo.length());
		String nombreArchivo = nombreArchivoSinExtension + extensionArchivo;
		
		return nombreArchivo;
	}
	
	/**
	 * Permite obtener los días calendario entre dos fechas.
	 * @param fechas1
	 * @param fechas2
	 * @return
	 */
	public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
	     long startTime = fecha1.getTime();
	     long endTime = fecha2.getTime();
	     long diffTime = endTime - startTime;
	     long diffDays = diffTime / (1000 * 60 * 60 * 24);
	     return (int)diffDays;
	}

	/**
	 * Permite obtener los días calendario entre dos fechas, por ello cuenta inclusive el último día.
	 * @param fechas1
	 * @param fechas2
	 * @return
	 */
	public static int plazoEnDiasEntreDosFechas(Date fecha1, Date fecha2){
	     long startTime = fecha1.getTime();
	     long endTime = fecha2.getTime();
	     long diffTime = endTime - startTime;
	     long diffDays = diffTime / (1000 * 60 * 60 * 24);
	     return (int)diffDays+1;
	}

	/**
	 * Permite obtener los meses entre dos fechas.
	 * @param fechas1
	 * @param fechas2
	 * @return
	 */
	public static int numeroMesesEntreDosFechas(Date fecha1, Date fecha2){
	     long startTime = fecha1.getTime();
	     long endTime = fecha2.getTime();
	     long diffTime = endTime - startTime;
	     long diffDays = diffTime / (1000 * 60 * 60 * 24 * 30);
	     return (int)diffDays;
	}
	
	/**
	 * Permite obtener la fecgha a partir de una cadena de formato dd/MM/yyyy.
	 * @param strDate
	 * @return
	 */
	public static Date Strig2Date(String strDate)throws Exception {  
	    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);  
	    return  date;
	}  

	public static String setListToString(Set<String> lista){
		String salidaStr = "";
		for (String str : lista) {
			if(salidaStr.equals("")) {
				salidaStr = str;
			}else {
				salidaStr = salidaStr + ", " + str;
			}
		}
	     return salidaStr;
	}
	

	/**
	 * Permite reemplazar las tildes y otros caracteres especiales en textos 
	 * para evitar nombre inadecuados en, por ejemplo, los documentos enviados
	 * al Siges.
	 * @param cadena
	 * @return
	 */
	public static String removerCaracteresEspeciales(String cadena) {
		  // Cadena de caracteres original a sustituir.
		  String orgnl = "áàäéèëíìïóòöúùüñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ'°";
		  // Cadena de caracteres ASCII que reemplazarán los originales.
		  String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC__";
		  String output = cadena;
		  for (int i=0; i<orgnl.length(); i++) {
		      // Reemplazamos los caracteres especiales.
		      output = output.replace(orgnl.charAt(i), ascii.charAt(i));
		  }//for i
		  return output;
	}
	
	
	/**
     * Permite reemplazar algunos caracteres particulares de un string que corresponden a UTF-8 pero NO reconocidos por ISO-8859-15 (encoding de la BD), 
     * para evitar que en la BD se persista como carácter raro (¿).
     * Uso de expresiones regulares con bandera /g (global) y /u (unicode) 
     * Lit. ref. https://www.ascii-code.com/ 
     */
	public static String reemplazarCaracteresParticulares(String texto){

    	String newString = texto
                  .replaceAll("\u201C", "\"") // “    Left double quotation mark
                  .replaceAll("\u201D", "\"") // ”    Right double quotation mark
                  .replaceAll("\u2018", "'")  // ‘    Left single quotation mark
                  .replaceAll("\u2019", "'")  // ’    Right single quotation mark
                  .replaceAll("\u2026", "...")// …    Horizontal ellipsis
                  .replaceAll("\u02C6", "^")  // ˆ    Modifier letter circumflex accent
                  .replaceAll("\u2039", "<")  // ‹    Single left-pointing angle quotation
                  .replaceAll("\u203A", ">")  // ›    Single right-pointing angle quotation mark
                  .replaceAll("\u2013", "-")  // –    En dash
                  .replaceAll("\u2014", "-")  // —    Em dash
                  .replaceAll("\u00A1", "¡")  // ¡    Inverted exclamation mark
                  .replaceAll("\u00AB", "«")  // «    Left double angle quotes
                  .replaceAll("\u00BB", "»")  // »    Right double angle quotes
                  .replaceAll("\u00B0", "°")  // °    Degree sign
                  .replaceAll("\u00BA", "°")  // º    Masculine ordinal indicator
                  .replaceAll("\u00B7", ".")  // ·    Middle dot - Georgian comma
                  ;
    
    	return newString;
	}	
	
	/**
	 * Permite validar si una cadena de texto es un número
	 * 
	 * @param cadena
	 * @return
	 */
	 public static boolean esUnNumero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			log.error(nfe.getMessage());
			return false;
		}
					   
	}
	 
	 public static String obtenerValor(String valor) {
		 String nuevoValor = "";
		 if(valor != null) {
			 nuevoValor = "'"+valor+"'";
		 }else {
			 nuevoValor = ConstantesUtil.PARAM_PLANTILLA_CADENA_VACIA;
		 }
		 return nuevoValor;
	 }
	 
	public static FechaFeriado esFeriado_old(String strFecha, String urlSigedRestOld) throws Exception {
		FechaFeriado objResultado = new FechaFeriado();
		// Ejecutar el procedimiento de reenvío del expediente
		String serverUrl = urlSigedRestOld;
		String urlServicio = ConstantesUtil.PARAM_SIGED_ES_FERIADO;
		log.info(String.format(ConstantesUtil.PARAM_SIGED_LOG_DE_USO_INI, "ES_FERIADO: " + strFecha));
		try {
				URL url = new URL(serverUrl + urlServicio);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod(ConstantesUtil.PARAM_REQUEST_METHOD_POST);
				conn.setUseCaches(false);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setAllowUserInteraction(true);
				conn.setRequestProperty(ConstantesUtil.PARAM_REQUEST_PROPERTY_CONTENT_TYPE, "application/xml");
				OutputStreamWriter infWebSvcReqWriter = new OutputStreamWriter(conn.getOutputStream());
				/* obtener xml file */
				String xmlStringReq = FechaFeriado.getStringXmlFile(strFecha);
				infWebSvcReqWriter.write(xmlStringReq);
				infWebSvcReqWriter.flush();

				if (conn.getResponseCode() != 200) {
					objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
					objResultado.setErrorCode(String.valueOf(conn.getResponseCode()));
					objResultado.setMessage(conn.getResponseMessage());
					log.info(String.format(ConstantesUtil.PARAM_SIGED_LOG_DE_USO_FIN_ERROR,
							"ES_FERIADO: " + strFecha));
					return objResultado;
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

				String resultCode = CommonsUtil.getResultCode(doc, "fechaFeriadoOutRO", "resultCode");
				String mensajeFeriado = CommonsUtil.getValue2Tag(doc, "fechaFeriadoOutRO", "mensajeFeriado");
				String errorCode = CommonsUtil.getValue2Tag(doc, "fechaFeriadoOutRO", "errorCode");
				String message = CommonsUtil.getValue2Tag(doc, "fechaFeriadoOutRO", "message");
				objResultado.setResultCode(resultCode);
				objResultado.setMensajeFeriado(mensajeFeriado);
				objResultado.setErrorCode(errorCode);
				objResultado.setMessage(message);
				conn.disconnect();
			} catch (Exception e) {
				objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
				objResultado.setErrorCode(ConstantesUtil.PARAM_RESULTADO_ERROR_EXCEPTION);
				objResultado.setMessage("Message: " + e.getMessage() + " || Localized: " + e.getLocalizedMessage()
						+ " || Cause: " + e.getCause());
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
			log.info(String.format(ConstantesUtil.PARAM_SIGED_LOG_DE_USO_FIN, "ES_FERIADO: " + strFecha));
			
			return objResultado;
		}
	
		
	public static String convertStandardJSONString(String data_json){
		if(data_json == null){
			data_json = "";
		}
		
		if(data_json.contains("'")) {
			data_json = data_json.replaceAll("'", "\'");	
		}
		
	    return data_json;
	}
	
	public static String validarNull(String valor) {
		 String cadenaSalida = "";
		 if(valor != null) {
			 cadenaSalida = valor;
		 }
		 
		 return cadenaSalida;
	 }
	
	/**
	 * obtener el idSubcatDocumento para el doc. informe de supervision según el tipo de supervision es (propio o no propia)
	 * @param pgimSupervisionDTO
	 * @return
	 */
	public static Long obtenerIdSubCatInformeSupervByTipoSuperv( PgimSupervisionDTO pgimSupervisionDTO) {
		
		Long idSubCatInformeSupervision; 
		
		if(pgimSupervisionDTO.getFlPropia().equals("1")) {
			idSubCatInformeSupervision = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_PROPIA; 
		}else {
			idSubCatInformeSupervision = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION;
		}
		
		return idSubCatInformeSupervision;
	}
	
	public static boolean esExtensionArchivoValida(String originalFileName, String extensionesValidas) throws Exception{
    	boolean esExtensionArchivoValida = false;
    	String extensionArchivo = obtenerExtensionNombreArchivo(originalFileName).toLowerCase();
    	if(Arrays.asList(extensionesValidas.toLowerCase().split(",")).contains("."+extensionArchivo)){
    		esExtensionArchivoValida = true;
		}
    	return esExtensionArchivoValida;
    }

	/**
	 * Permite limpiar una cadena que formará parte de otra en formato XML a fin de reemplazar los caracteres & por su equivalente aceptado 
	 * por XML, a saber: &amp;
	 * @param cadenaALimpiar
	 * @return
	 */
	public static String limpiarCadenaParaXML(String cadenaALimpiar) {
		
		return cadenaALimpiar.replace("&", "&amp;");
	}

	/**
	 * Permite convertir un objeto del tipo LocalDate a Date
	 * @param ldFecha
	 * @return
	 */
	public static Date convertirADate(LocalDate ldFecha) {
		if (ldFecha == null) {
			return null;
		}
		ZoneId zonaTiempoSistema = ZoneId.systemDefault();
		ZonedDateTime fechaTiempoZonificado = ldFecha.atStartOfDay(zonaTiempoSistema);
		Date dFechaHastaParaPresentacion = Date.from(fechaTiempoZonificado.toInstant());

		return dFechaHastaParaPresentacion;
	}

	/**
	 * Permite convertir un entero int a long
	 * @param ldFecha
	 * @return
	 */
	public static Long convertirALong(Integer entero) {
		if (entero == null) {
			return null;
		}
		
		return new Long(entero);
	}

	/**
	 * Permite repetir la cadena la cantidad de veces señaladas
	 * @param repeticiones
	 * @param cadena
	 * @return
	 */
	public static String repetir(int repeticiones, String cadena) {
		return String.join("", Collections.nCopies(repeticiones, cadena));
	}
	
	/**
	 * Permite rellenar con ceros a un String 
	 * @param value	String a la cual se va a rellenar
	 * @param sizeChart	tamaño total del string resultado
	 * @param position	posición del lado al cual se va hacer el relleno (L: izquierdo, R: derecho)
	 * @return	String rellenado
	 */
	public static String fillWithZero(String value, Integer sizeChart, String position ){
		value = value.toString();
	    while (value.length() < sizeChart){
	      if(position.toUpperCase().equals("L")) value = "0" + value;
	      else value = value+"0";
	    }
    	return value;
	}
	
	/**
	 * Permite borrar una lista de archivos. 
	 * Usado para borrar los archivos tmp guardados al adjuntar documentos
	 *  
	 * @param lstArchivos
	 * @param funcionalidad
	 */
	public static void borrarArchivos(List<File> lstArchivos, String funcionalidad) {
		for (File file : lstArchivos) {
			String nombreFile = file.getName();
			if (file.delete()) {
				log.info("El archivo " + nombreFile + " ha sido borrado satisfactoriamente después de haberse ejecutado la funcionalidad " + 
						funcionalidad);
		     }else {
		    	log.info("El archivo " + nombreFile + " no pudo ser borrado después de haberse ejecutado la funcionalidad" + 
		    			funcionalidad);
		     }			
		}		
	}
	
	
	
	/**
	 * Permite deserializar el token priviamente serializado.
	 * 
	 * @param idToken Token que viaje entre el frontend y backend.
	 * @return
	 * @throws Exception
	 */
	public static UserAuthDTO deserializarToken(String idToken) throws Exception {

		String jwtToken = idToken;
		UserAuthDTO userAuthDTO = new UserAuthDTO();
		String body = "";

		try {
			System.out.println("------------ Decode JWT ------------");
			String[] split_string = jwtToken.split("\\.");
			// HDT Inicio.25.04.2022: Comentado para proteger la información de la clave del Siged
			// String base64EncodedHeader = split_string[0];
			// HDT Fin.
			String base64EncodedBody = split_string[1];

			System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
			Base64 base64Url = new Base64(true);			

			// HDT Inicio.25.04.2022: Comentado para proteger la información de la clave del Siged
			// String header = new String(base64Url.decode(base64EncodedHeader));
			// System.out.println("JWT Header : " + header);
			// HDT Fin.

			body = new String(base64Url.decode(base64EncodedBody));

			// HDT Inicio.25.04.2022: Comentado para proteger la información de la clave del Siged
			// System.out.println("JWT Header : " + body);
			// HDT Fin.

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			userAuthDTO = mapper.readValue(body, UserAuthDTO.class);

		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		return userAuthDTO;
	}

	/**
	 * Permite convertir un objeto del tipo Date a LocalDate
	 * @param dFecha
	 * @return
	 */
	public static LocalDate convertirALocalDate(Date dFecha) {
		if (dFecha == null) {
			return null;
		}
		ZoneId zonaTiempoSistema = ZoneId.systemDefault();
		LocalDate ldFecha = dFecha.toInstant().atZone(zonaTiempoSistema).toLocalDate();
		return ldFecha;
	}

	public static Date convertirADateEndDay(LocalDate ldFecha) {
		if (ldFecha == null) {
			return null;
		}
		ZoneId zonaTiempoSistema = ZoneId.systemDefault();
		ZonedDateTime fechaTiempoZonificado = ldFecha.atTime(23, 59, 59).atZone(zonaTiempoSistema);
		Date dFechaHastaParaPresentacion = Date.from(fechaTiempoZonificado.toInstant());
		return dFechaHastaParaPresentacion;
	}
	
	/**
	 * Permite obtener el nombre de un proceso misional a partir de su Id, 
	 * nombre del proceso en singular.
	 * 
	 * @param idProceso
	 * @return
	 */
	public static String obtenerNombreProceso(Long idProceso) {
		int idProcesoInt = Math.toIntExact(idProceso);
		String noProceso = "";
		switch (idProcesoInt) {
			case 2:
				noProceso = "Fiscalización";
				break;
			case 4:
				noProceso = "PAS";
				break;
			case 9:
				noProceso = "Programación";
				break;
			case 10:
				noProceso = "Liquidación";
				break;
			case 11:
				noProceso = "Ranking";
				break;
			case 12:
				noProceso = "Configuración de riesgo";
				break;
				
			default:
				break;
		}
		return noProceso;
	}
}
