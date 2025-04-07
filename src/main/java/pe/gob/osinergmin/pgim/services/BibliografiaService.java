package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEntidadNegocioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDescargadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioArchivo;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioDocumento;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidades relacionadas a la Bibliografía de entidades de negocio
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 13/12/2023
 */
public interface BibliografiaService {
	
	/**
	 * Permite obtener el listado de los documentos y archivos de la bibliografía, 
	 * de acuerdo con los filtros aplicados
	 * 
	 * @param pgimBiblioDocumentoDTOFiltro
	 * @param paginador
	 * @return
	 */
	Page<PgimBiblioArchivoDTO> listarDocumentosYArchivos(PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOFiltro, Pageable paginador);
	
	/**
	 * Permite obtener el listado de subcategorias filtrado por la palabra clave
	 * @param palabraClave
	 * @return
	 */
	public List<PgimSubcategoriaDocDTO> listarSubcategoriaPorPalabraClave(String palabraClave);
	
	/**
	 * Permite listar las categorías correspondientes a Bibliografía
	 * @return
	 */
	List<PgimCategoriaDocDTO> listarCategoriaDocBibliografia();
	
	/**
	 * Permite listar las subcategorías correspondientes a Bibliografía
	 * @return
	 */
	List<PgimSubcategoriaDocDTO> listarSubcategoriaDocBibliografia();

	/**
	 * Permite crear un documento (y su archivo) en el alfresco.
	 * @param pgimBiblioDocumentoDTO
	 * @param fileDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimBiblioDocumentoDTO crearDocumento(PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO,
		MultipartFile fileDocumento, List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite crear un archivo bibliográfico
	 * 
	 * @param pgimBiblioArchivoDTO
	 * @param fileDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimBiblioArchivoDTO crearArchivoBibliografia(PgimBiblioArchivoDTO pgimBiblioArchivoDTO,
			MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite reemplazar un archivo bibliográfico
	 * 
	 * @param pgimBiblioArchivoDTO
	 * @param fileDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimBiblioArchivoDTO reemplazarArchivoBibliografia(PgimBiblioArchivoDTO pgimBiblioArchivoDTO,
			MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener el listado de entidades de negocio
	 * @return
	 */
	public List<PgimEntidadNegocioDTO> listadoEntidadNegocio();

	/**
	 * Permite obtener el listado de biblioEntidades (AS, UM, compomente minero) según la palabra clave y el identificador de entidad de negocio
	 * @return
	 */
	public List<PgimBiblioEntidadDTO> listadoBiblioEntidadPorPalabraClaveYEntidadNegocio(String palabraClave, String noTablaEntidadNegocio);

	/**
	 * Permite obtener el biblioDocumento por su identificador interno
	 * @param idBiblioDocumento
	 * @return
	 */
	public PgimBiblioDocumentoDTO obtenerDocumentoBiblioPorId(Long idBiblioDocumento);

	/**
	 * Permite obtener el biblioDocumento por su identificador interno
	 * @param idBiblioDocumento
	 * @return
	 */
	public PgimBiblioDocumento obtenerDocumentoBiblioById(Long idBiblioDocumento);
	
	/**
	 * Permite obtener el biblioArchivo por su identificador interno
	 * @param idBiblioDocumento
	 * @return
	 */
	PgimBiblioArchivo obtenerBiblioArchivoById(Long idBiblioArchivo);

	/**
	 * Permite modificar documento bibliográfico
	 * @param pgimBiblioDocumentoDTO
	 * @param pgimBiblioDocumento
	 * @param lPgimBiblioEntidadDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public PgimBiblioDocumentoDTO modificarDocumentoBibliografia(PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO, PgimBiblioDocumento pgimBiblioDocumento,
		List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite eliminar un documento bibliográfico 
	 * 
	 * @param pgimBiblioDocumentoActual
	 * @param motivo
	 * @param auditoriaDTO
	 */
	void eliminarDocumentoBibliografia(PgimBiblioDocumento pgimBiblioDocumentoActual, String motivo, 
    		AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite eliminar un archivo bibliográfico
	 * 
	 * @param pgimBiblioArchivoActual
	 * @param motivo
	 * @param auditoriaDTO
	 */
	void eliminarArchivoBibliografia(PgimBiblioArchivo pgimBiblioArchivoActual, String motivo, 
    		AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite descargar un archivo bibliográfico
	 * 
	 * @param idBilblioArchivo
	 * @param auditoriaDTO
	 * @return
	 */
	ArchivoDescargadoDTO descargarArchivoBibliografia(Long idBiblioArchivo);


}
