package pe.gob.osinergmin.pgim.services;

import org.springframework.web.multipart.MultipartFile;

import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoCreadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDescargadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoReemplazadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.CrearArchivoDTO;

/**
 * Servicio para la gestión de la interacción con los servicios de Alfresco.
 * 
 * @descripción: Lógica de negocio de la entidades relacionadas a los archivos de Alfresco
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 20/12/2023
 */
public interface AlfrescoArchivoService {

	/**
	 * Permite crear un archivo en el Alfresco
	 * 
	 * @param crearArchivoDTO
	 * @param fileData
	 * @return
	 */
	ArchivoCreadoDTO crearArchivoAlfresco(CrearArchivoDTO crearArchivoDTO, MultipartFile fileData);
	
	/**
	 * Permite reemplazar un archivo en el Alfresco
	 * 
	 * @param reemplazarArchivoDTO
	 * @param fileData
	 * @return
	 */
	ArchivoReemplazadoDTO reemplazarArchivoAlfresco(ArchivoDTO reemplazarArchivoDTO, MultipartFile fileData);
	
	/**
	 * Permite descargar un archivo de Alfresco
	 * 
	 * @param descargarArchivoDTO
	 * @return
	 */
	ArchivoDescargadoDTO descargarArchivoAlfresco(ArchivoDTO descargarArchivoDTO);

}
