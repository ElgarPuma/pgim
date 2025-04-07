package pe.gob.osinergmin.pgim.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoCreadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDescargadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoReemplazadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.CrearArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ErrorApiAlfrescoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.services.AlfrescoArchivoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con los servicios del Alfresco.
 * 
 * @descripción: Lógica de negocio de la entidades relacionadas a los archivos de Alfresco
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 20/12/2023
 */
@Service
@Slf4j
public class AlfrescoArchivoServiceImpl implements AlfrescoArchivoService {
	
	@Autowired
	private PropertiesConfig propertiesConfig;
	
	private final RestTemplate restTemplate;

    public AlfrescoArchivoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	
	@Override
    public ArchivoCreadoDTO crearArchivoAlfresco(CrearArchivoDTO crearArchivoDTO, MultipartFile fileData) {
		
		final String nombreMetodo = ConstantesUtil.PARAM_ALFRESCO_NOM_METODO_CREAR_ARCHIVO;

        log.info(String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_INI, 
        		nombreMetodo + " " + crearArchivoDTO.getNombreArchivo()));

        ArchivoCreadoDTO rpta = null;

        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);

        File tempFile = convertMultipartToFile(fileData);

        MultiValueMap<String, Object> requestMapBody = new LinkedMultiValueMap<>();
        requestMapBody.add(ConstantesUtil.PARAM_ALFRESCO_AGREGAR_JSON, new HttpEntity<>(crearArchivoDTO, jsonHeaders)); // Agregar el JSON
        requestMapBody.add(ConstantesUtil.PARAM_ALFRESCO_AGREGAR_FILE, new FileSystemResource(Objects.requireNonNull(tempFile)));// agregar el file
        
        HttpHeaders bodyHeaders = new HttpHeaders();
        bodyHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        bodyHeaders.setBasicAuth(propertiesConfig.getAlfrescoApiUsuario(), propertiesConfig.getAlfrescoApiClave());        

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestMapBody, bodyHeaders);
        
        String serverUrl = propertiesConfig.getAlfrescoApiUrl();
        String url =  serverUrl + ConstantesUtil.PARAM_ALFRESCO_SERVICIO_CREAR_ARCHIVO;

        try {
        	
            ResponseEntity<ArchivoCreadoDTO> response = this.restTemplate.exchange(
            		url,
                    HttpMethod.POST,
                    requestEntity,
                    ArchivoCreadoDTO.class);

            if (Objects.requireNonNull(response.getStatusCode()) == HttpStatus.CREATED) {
                ArchivoCreadoDTO archivoCreado = response.getBody();
                assert archivoCreado != null;
                rpta = archivoCreado;
            }

            eliminarArchivoTemporal(tempFile); // Por buenas practicas y mejorar la eficiencia y el rendimiento de la
                                               // aplicación eliminar el file temp

        } catch (HttpClientErrorException ex) { 
            // Esta exception se puede manejar asi como esta en un try catch o en una excepción global
            Gson gson = new Gson();
            ErrorApiAlfrescoDTO errorResponse = gson.fromJson(ex.getResponseBodyAsString(), ErrorApiAlfrescoDTO.class);

            String rptaMsj = String.format(ConstantesUtil.MENSAJE_ErrorAlfresco, nombreMetodo, 
            		errorResponse.getCodigoEstadoDetalle(), errorResponse.getMensajeEstadoDetalle());
            log.error(rptaMsj, ex);
            
            throw new PgimException(TipoResultado.ERROR, rptaMsj);

        } catch (Exception e) {
            // Manejar otras excepciones
        	String rptaMsj = String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_FIN_ERROR, nombreMetodo + ": " + e.getMessage());
            log.error(rptaMsj, e);        	
        	throw new PgimException(TipoResultado.ERROR, rptaMsj);
        }
        
        log.info(String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_FIN, nombreMetodo + " " + rpta.getIdAlfresco()));

        return rpta;
    }
	
	@Override
    public ArchivoReemplazadoDTO reemplazarArchivoAlfresco(ArchivoDTO reemplazarArchivoDTO, MultipartFile fileData) {
		
		final String nombreMetodo = ConstantesUtil.PARAM_ALFRESCO_NOM_METODO_REEMPLAZAR_ARCHIVO;

		log.info(String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_INI, 
        		nombreMetodo + " " + reemplazarArchivoDTO.getNombreArchivo()));

        ArchivoReemplazadoDTO rpta = null;

        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);

        File tempFile = convertMultipartToFile(fileData);

        MultiValueMap<String, Object> requestMapBody = new LinkedMultiValueMap<>();
        requestMapBody.add("archivoReemplazar", new HttpEntity<>(reemplazarArchivoDTO, jsonHeaders)); // Agregar el JSON
        requestMapBody.add("archivoData", new FileSystemResource(Objects.requireNonNull(tempFile)));// agregar el file

        HttpHeaders bodyHeaders = new HttpHeaders();
        bodyHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        bodyHeaders.setBasicAuth(propertiesConfig.getAlfrescoApiUsuario(), propertiesConfig.getAlfrescoApiClave());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestMapBody, bodyHeaders);
        
        String serverUrl = propertiesConfig.getAlfrescoApiUrl();
        String url =  serverUrl + ConstantesUtil.PARAM_ALFRESCO_SERVICIO_REEMPLAZAR_ARCHIVO;
        try {

            ResponseEntity<ArchivoReemplazadoDTO> response = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                ArchivoReemplazadoDTO.class);

            if (Objects.requireNonNull(response.getStatusCode()) == HttpStatus.OK) {
                ArchivoReemplazadoDTO archivoReemplazado = response.getBody();
                assert archivoReemplazado != null;
                rpta = archivoReemplazado;
            }

            eliminarArchivoTemporal(tempFile); // Por buenas practicas y mejorar la eficiencia y el rendimiento de la
                                               // aplicación eliminar el file temp

        } catch (HttpClientErrorException ex) {
            Gson gson = new Gson();
            ErrorApiAlfrescoDTO errorResponse = gson.fromJson(ex.getResponseBodyAsString(), ErrorApiAlfrescoDTO.class);
            
            String rptaMsj = String.format(ConstantesUtil.MENSAJE_ErrorAlfresco, nombreMetodo, 
            		errorResponse.getCodigoEstadoDetalle(), errorResponse.getMensajeEstadoDetalle());
            log.error(rptaMsj, ex);

            throw new PgimException(TipoResultado.ERROR, rptaMsj);

        } catch (Exception e) {
            // Manejar otras excepciones
        	String rptaMsj = String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_FIN_ERROR, nombreMetodo + ": " + e.getMessage());
            log.error(rptaMsj, e);
            throw new PgimException(TipoResultado.ERROR, rptaMsj);
        }
        
        log.info(String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_FIN, nombreMetodo + " " + rpta.getIdAlfresco()));

        return rpta;
    }
	
	@Override
    public ArchivoDescargadoDTO descargarArchivoAlfresco(ArchivoDTO descargarArchivoDTO) {
		
		final String nombreMetodo = ConstantesUtil.PARAM_ALFRESCO_NOM_METODO_DESCARGAR_ARCHIVO;

		log.info(String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_INI, 
        		nombreMetodo + " " + descargarArchivoDTO.getNombreArchivo()));

        ArchivoDescargadoDTO rpta = new ArchivoDescargadoDTO();

        HttpHeaders bodyHeaders = new HttpHeaders();        
        bodyHeaders.setBasicAuth(propertiesConfig.getAlfrescoApiUsuario(), propertiesConfig.getAlfrescoApiClave());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyHeaders);
        
        String serverUrl = propertiesConfig.getAlfrescoApiUrl();
        String url =  serverUrl + ConstantesUtil.PARAM_ALFRESCO_SERVICIO_DESCARGAR_ARCHIVO
        		.replace("{idAlfresco}", descargarArchivoDTO.getIdAlfresco());

        try {
        	
            ResponseEntity<ArchivoDescargadoDTO> response = this.restTemplate.exchange(
            		url,
                    HttpMethod.GET,
                    requestEntity,
                    ArchivoDescargadoDTO.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                rpta = response.getBody();
            }

        } catch (HttpClientErrorException ex) {
            Gson gson = new Gson();
            ErrorApiAlfrescoDTO errorResponse = gson.fromJson(ex.getResponseBodyAsString(), ErrorApiAlfrescoDTO.class);
            
            String rptaMsj = String.format(ConstantesUtil.MENSAJE_ErrorAlfresco, nombreMetodo, 
            		errorResponse.getCodigoEstadoDetalle(), errorResponse.getMensajeEstadoDetalle());
            log.error(rptaMsj, ex);

            throw new PgimException(TipoResultado.ERROR, rptaMsj);
            
        } catch (Exception e) {
            // Manejar otras excepciones
        	String rptaMsj = String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_FIN_ERROR, nombreMetodo + ": " + e.getMessage());
            log.error(rptaMsj, e);
            throw new PgimException(TipoResultado.ERROR, rptaMsj);            
        }
        
        log.info(String.format(ConstantesUtil.PARAM_ALFRESCO_LOG_DE_USO_FIN, nombreMetodo + " " + rpta.getNombreArchivo()));

        return rpta;
    }

	
	/**
	 * Convierte un archivo MultipartFile (springframework) a File (java.io)
	 * @param multipartFile
	 * @return
	 */
	private static File convertMultipartToFile(MultipartFile multipartFile) {

        try {
            File file = File.createTempFile("tempFile", null);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }

            return file;

        } catch (Exception ex) {
            return null;
        }
    }
	
	/**
	 * Elimina un archivo temporal
	 * @param tempFile
	 */
	private static void eliminarArchivoTemporal(File tempFile) {
        if (tempFile != null && tempFile.exists()) {
            if (tempFile.delete()) {
                System.out.println("Archivo temporal eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el archivo temporal.");
            }
        }
    }

}
