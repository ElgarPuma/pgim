package pe.gob.osinergmin.pgim.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEntidadNegocioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoCreadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDescargadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoReemplazadoDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.CrearArchivoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioArchivo;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioEntidad;
import pe.gob.osinergmin.pgim.models.entity.PgimEntidadNegocio;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.BiblioArchivoRepository;
import pe.gob.osinergmin.pgim.models.repository.BiblioDocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.BiblioEntidadRepository;
import pe.gob.osinergmin.pgim.models.repository.CategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.EntidadNegocioRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.services.AlfrescoArchivoService;
import pe.gob.osinergmin.pgim.services.BibliografiaService;
import pe.gob.osinergmin.pgim.services.CategoriaDocService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidades relacionadas a la Bibliografía de entidades de negocio
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 13/12/2023
 * @fecha_de_ultima_actualización: 15/12/2023
 */
@Service
@Transactional(readOnly = true)
public class BibliografiaServiceImpl implements BibliografiaService {
	
	@Autowired
	private BiblioArchivoRepository biblioArchivoRepository;
	
	@Autowired
	private SubcategoriaDocRepository subcategoriaDocRepository;

	@Autowired
	private BiblioDocumentoRepository biblioDocumentoRepository;

	@Autowired
	private EntidadNegocioRepository entidadNegocioRepository;

	@Autowired
	private AlfrescoArchivoService alfrescoArchivoService;

	@Autowired
	private CategoriaDocService categoriaDocService;
	
	@Autowired
	private BiblioEntidadRepository biblioEntidadRepository;
	
	@Autowired
	private CategoriaDocRepository categoriaDocRepository;	
	
	
	@Override
	public Page<PgimBiblioArchivoDTO> listarDocumentosYArchivos(PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOFiltro, Pageable paginador) {
		
		// Aseguramos el orden de los archivos, para que se mantengan unidos por documento, 
		// añadiendo al ordenamiento seleccionado, un ordenamiento por ID de documento y por ID de archivo
		
		Pageable paginadorFinal = paginador;
		
		// verifica si la propiedad de ordenamiento seleccionada es por fecha de emisión del documento
		boolean esOrdenadoByFecha = paginador.getSort().isSorted() &&
		        paginador.getSort().get().findFirst().map(Sort.Order::getProperty).orElse("").equals("bdoc.feEmisionDocumento");
		
		String direccionOrden = paginador.getSort().get().findFirst().map(Sort.Order::getDirection).map(Enum::toString).orElse("");
		
		if (esOrdenadoByFecha && direccionOrden.equals("ASC")) {
			paginadorFinal = PageRequest.of(
			        paginador.getPageNumber(),
			        paginador.getPageSize(),
			        paginador.getSort().and(Sort.by("bdoc.idBiblioDocumento").ascending())
			        					.and(Sort.by("idBiblioArchivo").ascending()) // archivos de un doc en el orden en que se adjunta
			    );
			
		}else {
			// en cualquier otro caso, añadimos ordenamiento por Id del documento los más recientes primero
			paginadorFinal = PageRequest.of(
			        paginador.getPageNumber(),
			        paginador.getPageSize(),
			        paginador.getSort().and(Sort.by("bdoc.idBiblioDocumento").descending())
			        					.and(Sort.by("idBiblioArchivo").ascending()) // archivos de un doc en el orden en que se adjunta
			    );
		}
		
		// Obtenemos la lista de registros según filtros
		Page<PgimBiblioArchivoDTO>  pPgimBiblioArchivoDTO = this.biblioArchivoRepository.listarBiblioArchivosPaginado(
				pgimBiblioDocumentoDTOFiltro.getNuDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDeAsuntoDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDeSumillaDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDescIdCategoriaDocumento(),
				pgimBiblioDocumentoDTOFiltro.getIdSubcatDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDescFeEmisionDesde(),
				pgimBiblioDocumentoDTOFiltro.getDescFeEmisionHasta(),
				pgimBiblioDocumentoDTOFiltro.getIdPersonaEmisora(),				
				pgimBiblioDocumentoDTOFiltro.getDescIdAgenteSupervisado(),				
				pgimBiblioDocumentoDTOFiltro.getDescIdUnidadMinera(),				
				pgimBiblioDocumentoDTOFiltro.getDescIdComponenteMinero(),				
	    		pgimBiblioDocumentoDTOFiltro.getTextoBusqueda(), 
	    		paginadorFinal);
		
		// Obtenemos la cantidad total de "documentos" distintos según filtros
		Integer cantTotalDocs = this.biblioArchivoRepository.obtenerCantidadBiblioDocumentos(
				pgimBiblioDocumentoDTOFiltro.getNuDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDeAsuntoDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDeSumillaDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDescIdCategoriaDocumento(),
				pgimBiblioDocumentoDTOFiltro.getIdSubcatDocumento(),
				pgimBiblioDocumentoDTOFiltro.getDescFeEmisionDesde(),
				pgimBiblioDocumentoDTOFiltro.getDescFeEmisionHasta(),
				pgimBiblioDocumentoDTOFiltro.getIdPersonaEmisora(),				
				pgimBiblioDocumentoDTOFiltro.getDescIdAgenteSupervisado(),				
				pgimBiblioDocumentoDTOFiltro.getDescIdUnidadMinera(),				
				pgimBiblioDocumentoDTOFiltro.getDescIdComponenteMinero(),				
	    		pgimBiblioDocumentoDTOFiltro.getTextoBusqueda()
	    		);
		
		Long idBiblioDocumentoAux = 0L;
		
		// Catalogamos los registros como "Documento" o como "Archivo"
		for (PgimBiblioArchivoDTO pgimBiblioArchivoDTO : pPgimBiblioArchivoDTO) {
			
			if (pgimBiblioArchivoDTO.getIdBiblioDocumento() != idBiblioDocumentoAux) {
				// Se le asigna el valor 'D' por ser el primer archivo de un nuevo Documento
				pgimBiblioArchivoDTO.setDescTipoRegistro(ConstantesUtil.TIPO_REG_DOCUMENTAL_DOCUMENTO);
				idBiblioDocumentoAux = pgimBiblioArchivoDTO.getIdBiblioDocumento();
				
				// Añadimos la lista de entidades de negocio del documento
				List<PgimBiblioEntidadDTO> lstPgimBiblioEntidadDTO = this.biblioEntidadRepository.listaBiblioEntidadPorIddoc(idBiblioDocumentoAux);
				pgimBiblioArchivoDTO.setLstPgimBiblioEntidadDTO(lstPgimBiblioEntidadDTO);
				
				// Añadimos de manera auxiliar la cantidad total de "documentos" de la consulta
				pgimBiblioArchivoDTO.setDescCantDocsTotal(cantTotalDocs);
				
			}else {
				pgimBiblioArchivoDTO.setDescTipoRegistro(ConstantesUtil.TIPO_REG_DOCUMENTAL_ARCHIVO);
			}
		}
	    
	    return pPgimBiblioArchivoDTO;
	}

	@Override
	public List<PgimSubcategoriaDocDTO> listarSubcategoriaPorPalabraClave(String palabraClave) {
		return this.subcategoriaDocRepository.listarSubcatBiblioPorPalabraClave(palabraClave);
	}
	
	@Override
	public List<PgimCategoriaDocDTO> listarCategoriaDocBibliografia() {
		return this.categoriaDocRepository.listarCategoriaDocBibliografia();
	}
	
	@Override
	public List<PgimSubcategoriaDocDTO> listarSubcategoriaDocBibliografia() {
		return this.subcategoriaDocRepository.listarSubcatBibliografia();
	}

	@Override
	@Transactional(readOnly = false)
	public PgimBiblioDocumentoDTO crearDocumento(PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO,
			MultipartFile fileDocumento, List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO, AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimBiblioDocumento pgimBiblioDocumento = new PgimBiblioDocumento();
		
		PgimSubcategoriaDoc pgimSubcategoriaDoc = new PgimSubcategoriaDoc();
		pgimSubcategoriaDoc.setIdSubcatDocumento(pgimBiblioDocumentoDTO.getIdSubcatDocumento());
		pgimBiblioDocumento.setPgimSubcategoriaDoc(pgimSubcategoriaDoc);

		PgimValorParametro pgimValorParametro = new PgimValorParametro();
		pgimValorParametro.setIdValorParametro(pgimBiblioDocumentoDTO.getIdTipoMedioIngreso());
		pgimBiblioDocumento.setTipoMedioIngreso(pgimValorParametro);

		PgimPersona pgimPersona = new PgimPersona();
		pgimPersona.setIdPersona(pgimBiblioDocumentoDTO.getIdPersonaEmisora());
		pgimBiblioDocumento.setPersonaEmisora(pgimPersona);

		pgimBiblioDocumento.setNuDocumento(pgimBiblioDocumentoDTO.getNuDocumento());
		pgimBiblioDocumento.setDeAsuntoDocumento(pgimBiblioDocumentoDTO.getDeAsuntoDocumento());
		pgimBiblioDocumento.setDeSumillaDocumento(pgimBiblioDocumentoDTO.getDeSumillaDocumento());
		pgimBiblioDocumento.setFeEmisionDocumento(pgimBiblioDocumentoDTO.getFeEmisionDocumento());
		pgimBiblioDocumento.setNuExpedienteSiged(pgimBiblioDocumentoDTO.getNuExpedienteSiged());

		pgimBiblioDocumento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimBiblioDocumento.setFeCreacion(auditoriaDTO.getFecha());
		pgimBiblioDocumento.setUsCreacion(auditoriaDTO.getUsername());
		pgimBiblioDocumento.setIpCreacion(auditoriaDTO.getTerminal());
		
		PgimBiblioDocumento pgimBiblioDocumentoCreado = this.biblioDocumentoRepository.save(pgimBiblioDocumento);
		
		for (PgimBiblioEntidadDTO pgimBiblioEntidadDTO : lPgimBiblioEntidadDTO) {
			PgimBiblioEntidad pgimBiblioEntidad = new PgimBiblioEntidad();
			
			PgimEntidadNegocio pgimEntidadNegocio = new PgimEntidadNegocio();
			pgimEntidadNegocio.setIdEntidadNegocio(pgimBiblioEntidadDTO.getIdEntidadNegocio());
			pgimBiblioEntidad.setPgimEntidadNegocio(pgimEntidadNegocio); 

			pgimBiblioEntidad.setPgimBiblioDocumento(pgimBiblioDocumento); 
			pgimBiblioEntidad.setIdRegistroEntidadNegocio(pgimBiblioEntidadDTO.getIdRegistroEntidadNegocio());

			pgimBiblioEntidad.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimBiblioEntidad.setFeCreacion(auditoriaDTO.getFecha());
			pgimBiblioEntidad.setUsCreacion(auditoriaDTO.getUsername());
			pgimBiblioEntidad.setIpCreacion(auditoriaDTO.getTerminal());

			this.biblioEntidadRepository.save(pgimBiblioEntidad);
		}

		// consumo del api-alfresco

		Date feEmisionDocument = pgimBiblioDocumentoDTO.getFeEmisionDocumento();
		Calendar calFeOrigenDocumento = Calendar.getInstance();
		calFeOrigenDocumento.setTime(feEmisionDocument);
		int anioOrigenDocumento = calFeOrigenDocumento.get(Calendar.YEAR);

		PgimSubcategoriaDoc pgimSubcategoriaDocumento = categoriaDocService.obtenerSubcategoria(pgimBiblioDocumentoDTO.getIdSubcatDocumento());
		
		String rutaRelativa =  anioOrigenDocumento +"/"+ pgimSubcategoriaDocumento.getCoSubcatDocumento();
		
		ArchivoCreadoDTO ArchivoCreadoDTO = null;
		
		CrearArchivoDTO archivoDTO = new CrearArchivoDTO();
		archivoDTO.setRutaRelativa(rutaRelativa);
		archivoDTO.setAutoRenombrar(true);
		archivoDTO.setNombreArchivo(fileDocumento.getOriginalFilename());
		ArchivoCreadoDTO = this.alfrescoArchivoService.crearArchivoAlfresco(archivoDTO, fileDocumento);
		
		// fin consumo del api-alfresco


		PgimBiblioArchivo pgimBiblioArchivo = new PgimBiblioArchivo(); 
		pgimBiblioArchivo.setPgimBiblioDocumento(pgimBiblioDocumentoCreado);
		pgimBiblioArchivo.setNuArchivoEcm(ArchivoCreadoDTO.getIdAlfresco());
		pgimBiblioArchivo.setNuCarpetaEcm(ArchivoCreadoDTO.getIdAlfrescoPadre());
		pgimBiblioArchivo.setNoBibilioArchivo(ArchivoCreadoDTO.getNombreArchivoCreado());
		pgimBiblioArchivo.setNoTipoMime(ArchivoCreadoDTO.getTipoMime());

		pgimBiblioArchivo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimBiblioArchivo.setFeCreacion(auditoriaDTO.getFecha());
		pgimBiblioArchivo.setUsCreacion(auditoriaDTO.getUsername());
		pgimBiblioArchivo.setIpCreacion(auditoriaDTO.getTerminal());
		
		this.biblioArchivoRepository.save(pgimBiblioArchivo);

		PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOCreado = this.obtenerDocumentoBiblioPorId(pgimBiblioDocumentoCreado.getIdBiblioDocumento());

		return pgimBiblioDocumentoDTOCreado;

	}
	

	@Override
	@Transactional(readOnly = false)
	public PgimBiblioArchivoDTO crearArchivoBibliografia(PgimBiblioArchivoDTO pgimBiblioArchivoDTO,
			MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimBiblioDocumento pgimBiblioDocumento =  this.biblioDocumentoRepository.findById(pgimBiblioArchivoDTO.getIdBiblioDocumento()).orElse(null);
		
		if(pgimBiblioDocumento == null) {
			throw new PgimException(TipoResultado.ERROR, String.format("No se encontró el documento con ID: %s para crear el archivo",
					pgimBiblioArchivoDTO.getIdBiblioDocumento()));
		}
		
		PgimSubcategoriaDoc pgimSubcategoriaDocumento = categoriaDocService.obtenerSubcategoria(pgimBiblioDocumento.getPgimSubcategoriaDoc().getIdSubcatDocumento());
		
		if(pgimSubcategoriaDocumento == null) {
			throw new PgimException(TipoResultado.ERROR, "No se encontró la subcategoría con ID: %s para crear el archivo " + 
					pgimBiblioDocumento.getPgimSubcategoriaDoc().getIdSubcatDocumento());
		}

		// Consumo del Api-alfresco		
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String anioOrigenDocumento = getYearFormat.format(pgimBiblioDocumento.getFeEmisionDocumento());
		
		String rutaRelativa =  anioOrigenDocumento +"/"+ pgimSubcategoriaDocumento.getCoSubcatDocumento(); 		
		
		ArchivoCreadoDTO ArchivoCreadoDTO = null;
		
		CrearArchivoDTO archivoDTO = new CrearArchivoDTO();
		archivoDTO.setRutaRelativa(rutaRelativa);
		archivoDTO.setAutoRenombrar(true);
		archivoDTO.setNombreArchivo(fileDocumento.getOriginalFilename());
		ArchivoCreadoDTO = this.alfrescoArchivoService.crearArchivoAlfresco(archivoDTO, fileDocumento);		
		// Fin consumo del Api-alfresco

		PgimBiblioArchivo pgimBiblioArchivo = new PgimBiblioArchivo(); 
		pgimBiblioArchivo.setPgimBiblioDocumento(pgimBiblioDocumento);
		pgimBiblioArchivo.setNuArchivoEcm(ArchivoCreadoDTO.getIdAlfresco());
		pgimBiblioArchivo.setNuCarpetaEcm(ArchivoCreadoDTO.getIdAlfrescoPadre());
		pgimBiblioArchivo.setNoBibilioArchivo(ArchivoCreadoDTO.getNombreArchivoCreado());
		pgimBiblioArchivo.setNoTipoMime(ArchivoCreadoDTO.getTipoMime());

		pgimBiblioArchivo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimBiblioArchivo.setFeCreacion(auditoriaDTO.getFecha());
		pgimBiblioArchivo.setUsCreacion(auditoriaDTO.getUsername());
		pgimBiblioArchivo.setIpCreacion(auditoriaDTO.getTerminal());
		
		PgimBiblioArchivo pgimBiblioArchivoCreado = this.biblioArchivoRepository.save(pgimBiblioArchivo);

		PgimBiblioArchivoDTO pgimBiblioArchivoDTOCreado = this.biblioArchivoRepository.obtenerBiblioArchivoPorId(pgimBiblioArchivoCreado.getIdBiblioArchivo());

		return pgimBiblioArchivoDTOCreado;

	}
	
	@Override
	@Transactional(readOnly = false)
	public PgimBiblioArchivoDTO reemplazarArchivoBibliografia(PgimBiblioArchivoDTO pgimBiblioArchivoDTO,
			MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimBiblioArchivo pgimBiblioArchivo =  this.biblioArchivoRepository.findById(pgimBiblioArchivoDTO.getIdBiblioArchivo()).orElse(null);
		
		if(pgimBiblioArchivo == null) {
			throw new PgimException(TipoResultado.ERROR, String.format("No se encontró el archivo con ID: %s que se intenta reemplazar",
					pgimBiblioArchivoDTO.getIdBiblioArchivo()));
		}
		
		// Consumo del Api-alfresco	
		ArchivoReemplazadoDTO archivoReemplazadoDTO = null;
		
		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setIdAlfresco(pgimBiblioArchivo.getNuArchivoEcm());			
		archivoDTO.setNombreArchivo(fileDocumento.getOriginalFilename());
		archivoReemplazadoDTO = this.alfrescoArchivoService.reemplazarArchivoAlfresco(archivoDTO, fileDocumento);
		// Fin consumo del Api-alfresco

		pgimBiblioArchivo.setNoBibilioArchivo(archivoReemplazadoDTO.getNombreArchivoReemplazado());
		pgimBiblioArchivo.setNoTipoMime(archivoReemplazadoDTO.getTipoMime());

		pgimBiblioArchivo.setFeActualizacion(auditoriaDTO.getFecha());
		pgimBiblioArchivo.setUsActualizacion(auditoriaDTO.getUsername());
		pgimBiblioArchivo.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimBiblioArchivo pgimBiblioArchivoReemplazado = this.biblioArchivoRepository.save(pgimBiblioArchivo);

		PgimBiblioArchivoDTO pgimBiblioArchivoDTOReemplazado = this.biblioArchivoRepository.obtenerBiblioArchivoPorId(pgimBiblioArchivoReemplazado.getIdBiblioArchivo());

		return pgimBiblioArchivoDTOReemplazado;

	}

	@Override
	public PgimBiblioDocumentoDTO obtenerDocumentoBiblioPorId(Long idBiblioDocumento) {

		PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO = this.biblioDocumentoRepository.obtenerDocumentoBiblioPorId(idBiblioDocumento);
		List<PgimBiblioEntidadDTO> lstPgimBiblioEntidadDTO = this.biblioEntidadRepository.listaBiblioEntidadPorIddoc(idBiblioDocumento);
		pgimBiblioDocumentoDTO.setLstPgimBiblioEntidadDTO(lstPgimBiblioEntidadDTO);

		return pgimBiblioDocumentoDTO;	
	}

	@Override
	public PgimBiblioDocumento obtenerDocumentoBiblioById(Long idBiblioDocumento) {

		return this.biblioDocumentoRepository.findById(idBiblioDocumento).orElse(null);
			
	}

	public PgimBiblioArchivo obtenerBiblioArchivoById(Long idBiblioArchivo) {

		return this.biblioArchivoRepository.findById(idBiblioArchivo).orElse(null);
			
	}

	@Override
	public List<PgimEntidadNegocioDTO> listadoEntidadNegocio() {
		return this.entidadNegocioRepository.listadoEntidadNegocio();
	}

	@Override
	public List<PgimBiblioEntidadDTO> listadoBiblioEntidadPorPalabraClaveYEntidadNegocio(String palabraClave, String noTablaEntidadNegocio){
		
		List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO = null;

		if(noTablaEntidadNegocio.equals(ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO)){
			lPgimBiblioEntidadDTO = this.biblioEntidadRepository.listarAgenteSuperPorPalabraClave(palabraClave);

		}else if(noTablaEntidadNegocio.equals(ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA)){
			lPgimBiblioEntidadDTO = this.biblioEntidadRepository.listarUMPorPalabraClave(palabraClave);

		} else if(noTablaEntidadNegocio.equals(ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO)){
			lPgimBiblioEntidadDTO = this.biblioEntidadRepository.listarComponenteMineroPorPalabraClave(palabraClave);

		}

		return lPgimBiblioEntidadDTO; 
	}


	@Override
	@Transactional(readOnly = false)
	public PgimBiblioDocumentoDTO modificarDocumentoBibliografia(PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO, PgimBiblioDocumento pgimBiblioDocumentoActual,
			List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO, AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimValorParametro pgimValorParametro = new PgimValorParametro();
		pgimValorParametro.setIdValorParametro(pgimBiblioDocumentoDTO.getIdTipoMedioIngreso());
		pgimBiblioDocumentoActual.setTipoMedioIngreso(pgimValorParametro);

		PgimPersona pgimPersona = new PgimPersona();
		pgimPersona.setIdPersona(pgimBiblioDocumentoDTO.getIdPersonaEmisora());
		pgimBiblioDocumentoActual.setPersonaEmisora(pgimPersona);

		pgimBiblioDocumentoActual.setNuDocumento(pgimBiblioDocumentoDTO.getNuDocumento());
		pgimBiblioDocumentoActual.setDeAsuntoDocumento(pgimBiblioDocumentoDTO.getDeAsuntoDocumento());
		pgimBiblioDocumentoActual.setDeSumillaDocumento(pgimBiblioDocumentoDTO.getDeSumillaDocumento());
		pgimBiblioDocumentoActual.setNuExpedienteSiged(pgimBiblioDocumentoDTO.getNuExpedienteSiged());

		pgimBiblioDocumentoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimBiblioDocumentoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimBiblioDocumentoActual.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimBiblioDocumento pgimBiblioDocumentoCreado = this.biblioDocumentoRepository.save(pgimBiblioDocumentoActual);
		
		for (PgimBiblioEntidadDTO pgimBiblioEntidadDTO : lPgimBiblioEntidadDTO) {

			PgimBiblioEntidad pgimBiblioEntidad = new PgimBiblioEntidad();
			if(pgimBiblioEntidadDTO.getIdBiblioEntidad() != null){
				pgimBiblioEntidad = this.biblioEntidadRepository.findById(pgimBiblioEntidadDTO.getIdBiblioEntidad()).orElse(null);
				
				if(pgimBiblioEntidadDTO.getEsRegistro() == null) // indica que la entidad de negocio pre-existente no a sido eliminado
					continue;

				pgimBiblioEntidad.setEsRegistro(pgimBiblioEntidadDTO.getEsRegistro());
				pgimBiblioEntidad.setFeActualizacion(auditoriaDTO.getFecha());
				pgimBiblioEntidad.setUsActualizacion(auditoriaDTO.getUsername());
				pgimBiblioEntidad.setIpActualizacion(auditoriaDTO.getTerminal());

			}else{
				pgimBiblioEntidad.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimBiblioEntidad.setFeCreacion(auditoriaDTO.getFecha());
				pgimBiblioEntidad.setUsCreacion(auditoriaDTO.getUsername());
				pgimBiblioEntidad.setIpCreacion(auditoriaDTO.getTerminal());
			}

			pgimBiblioEntidad.setPgimBiblioDocumento(pgimBiblioDocumentoActual);
			PgimEntidadNegocio pgimEntidadNegocio = new PgimEntidadNegocio();
			pgimEntidadNegocio.setIdEntidadNegocio(pgimBiblioEntidadDTO.getIdEntidadNegocio());
			pgimBiblioEntidad.setPgimEntidadNegocio(pgimEntidadNegocio);
			pgimBiblioEntidad.setIdRegistroEntidadNegocio(pgimBiblioEntidadDTO.getIdRegistroEntidadNegocio());

			this.biblioEntidadRepository.save(pgimBiblioEntidad);
		}

		PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOCreado = this.obtenerDocumentoBiblioPorId(pgimBiblioDocumentoCreado.getIdBiblioDocumento());

		return pgimBiblioDocumentoDTOCreado;

	}
	
    @Override
    @Transactional(readOnly = false)
    public void eliminarDocumentoBibliografia(PgimBiblioDocumento pgimBiblioDocumentoActual, String motivo, 
    		AuditoriaDTO auditoriaDTO) {
        
    	pgimBiblioDocumentoActual.setDeMotivoEliminacion(motivo);
    	pgimBiblioDocumentoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimBiblioDocumentoActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimBiblioDocumentoActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimBiblioDocumentoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.biblioDocumentoRepository.save(pgimBiblioDocumentoActual);
        
        // Eliminamos los archivos bibliográficos subyacentes
        List<PgimBiblioArchivoDTO> lstPgimBiblioArchivoDTO = this.biblioArchivoRepository.listarBiblioArchivoPorIdDoc(pgimBiblioDocumentoActual.getIdBiblioDocumento());
        
        for (PgimBiblioArchivoDTO pgimBiblioArchivoDTO : lstPgimBiblioArchivoDTO) {
        	PgimBiblioArchivo pgimBiblioArchivo = this.biblioArchivoRepository.findById(pgimBiblioArchivoDTO.getIdBiblioArchivo()).orElse(null);
			this.eliminarArchivoBibliografia(pgimBiblioArchivo, motivo, auditoriaDTO);
		}
        
        // Eliminamos los registros de entidades subyacentes 
        List<PgimBiblioEntidadDTO> lstBiblioEntidadDTO = this.biblioEntidadRepository.listarBiblioEntidadPorIddocBasic(pgimBiblioDocumentoActual.getIdBiblioDocumento());
        
        for (PgimBiblioEntidadDTO pgimBiblioEntidadDTO : lstBiblioEntidadDTO) {
        	PgimBiblioEntidad pgimBiblioEntidad = this.biblioEntidadRepository.findById(pgimBiblioEntidadDTO.getIdBiblioEntidad()).orElse(null);
			this.eliminarBiblioEntidad(pgimBiblioEntidad, auditoriaDTO);
		}
    }
    
    @Override
    @Transactional(readOnly = false)
    public void eliminarArchivoBibliografia(PgimBiblioArchivo pgimBiblioArchivoActual, String motivo, 
    		AuditoriaDTO auditoriaDTO) {
        
    	pgimBiblioArchivoActual.setDeMotivoEliminacion(motivo);
    	pgimBiblioArchivoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimBiblioArchivoActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimBiblioArchivoActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimBiblioArchivoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.biblioArchivoRepository.save(pgimBiblioArchivoActual);   
        
    }
    
    @Transactional(readOnly = false)
    public void eliminarBiblioEntidad(PgimBiblioEntidad pgimBiblioEntidadActual, AuditoriaDTO auditoriaDTO) {
        
    	pgimBiblioEntidadActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimBiblioEntidadActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimBiblioEntidadActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimBiblioEntidadActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.biblioEntidadRepository.save(pgimBiblioEntidadActual);   
        
    }
    
    @Override
    public ArchivoDescargadoDTO descargarArchivoBibliografia(Long idBiblioArchivo) {
    	
    	PgimBiblioArchivo pgimBiblioArchivo = this.obtenerBiblioArchivoById(idBiblioArchivo);
    	
    	if(pgimBiblioArchivo == null) {
    		throw new PgimException(TipoResultado.ERROR, String.format("No se encontró el archivo con ID: %s para ser descargado",
					idBiblioArchivo));
    	}
    	
		ArchivoDTO archivoDTO = new ArchivoDTO();
		archivoDTO.setIdAlfresco(pgimBiblioArchivo.getNuArchivoEcm());
		archivoDTO.setNombreArchivo(pgimBiblioArchivo.getNoBibilioArchivo());
		
		return this.alfrescoArchivoService.descargarArchivoAlfresco(archivoDTO);
    	
    }

}
