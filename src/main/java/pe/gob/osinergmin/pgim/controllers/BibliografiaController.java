package pe.gob.osinergmin.pgim.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEntidadNegocioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.dtos.alfresco.ArchivoDescargadoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioArchivo;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioDocumento;
import pe.gob.osinergmin.pgim.services.BibliografiaService;
import pe.gob.osinergmin.pgim.services.LogPgimService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de bibliografía de entidades de negocio
 *
 * @descripción: Bibliografía de entidades de negocio
 *
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 13/12/2023
 *
 */
@RestController
@Slf4j
@RequestMapping("/bibliografia")
public class BibliografiaController extends BaseController {
	
	@Autowired
	private LogPgimService logPgimService;
	
	@Autowired
	private BibliografiaService bibliografiaService;
	
	@Autowired
	private ParametroService parametroService;
		
	
	  /**
	   * Permite obtener el listado de los documentos y archivos de la bibliografía, 
	   * de acuerdo con los filtros aplicados
	   * 
	   * @param pgimBiblioDocumentoDTOFiltro
	   * @param paginador
	   * @return
	   */
		@PreAuthorize("hasAnyAuthority('doc001_AC')")
	  @PostMapping("/listarDocumentosYArchivos")
	  public ResponseEntity<Page<PgimBiblioArchivoDTO>> listarDocumentosYArchivos(
			  @RequestBody PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOFiltro, final Pageable paginador) {
	        
	    final Page<PgimBiblioArchivoDTO> pPgimBiblioArchivoDTO = this.bibliografiaService.listarDocumentosYArchivos(
	    		pgimBiblioDocumentoDTOFiltro, paginador); 
	    
	    return new ResponseEntity<Page<PgimBiblioArchivoDTO>>(pPgimBiblioArchivoDTO, HttpStatus.OK);

	  }

    /**
     * Permite obtener un listado de las subcategorias según una palabra clave ingresada
	   * requerido en campos autocomplete del frontend
     * @param textoBuscador (puede ser codigo o nombre de la categoria, codigo o nombre de la subcategoria)
     * @return
     */
	@PostMapping("/listarSubcategoriaPorPalabraClave")
    public ResponseEntity<?> listarSubcategoriaPorPalabraClave(@RequestBody String textoBuscador) {

		List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO = null;
		ResponseDTO responseDTO = null;

		try {
			if (textoBuscador.equals("_vacio_")) {
				lPgimSubcategoriaDocDTO = new ArrayList<PgimSubcategoriaDocDTO>();
				responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimSubcategoriaDocDTO, "Subcategorias encontrados");		
				return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);	
			}

			lPgimSubcategoriaDocDTO = this.bibliografiaService.listarSubcategoriaPorPalabraClave(textoBuscador);

		} catch (final DataAccessException e) {
				log.error(e.getMostSpecificCause().getMessage(), e);

				responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar las subcategorías");

				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
				log.error(e.getMessage(), e);

				responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar las subcategorías");

				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lPgimSubcategoriaDocDTO, "Subcategorías encontradas");

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

		/**
		 * Permite crear un documento (y su archivo) en el alfresco.
		 * 
		 * @param pgimBiblioDocumentoDTO       Documento agregado.
		 * @param fileDocumento          Archivo adjunto.
		 * 
		 * @return
		 */
		@PreAuthorize("hasAnyAuthority('doc001_MO')")
		@PostMapping(value = "/crearDocumento", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public ResponseEntity<ResponseDTO> crearDocumento(@RequestPart("pgimBiblioDocumentoDTO") PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO,
				@RequestPart("fileDocumento") MultipartFile fileDocumento, @RequestPart("lPgimBiblioEntidadDTO") List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO) throws PgimException, IOException, Exception {

			ResponseDTO responseDTO = null;
			PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOOCreado = null;

			if(lPgimBiblioEntidadDTO.size()==0){
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Al menos debe de seleccionar una entidad"));
			}

			try {
				pgimBiblioDocumentoDTOOCreado = this.bibliografiaService.crearDocumento(pgimBiblioDocumentoDTO,
						fileDocumento, lPgimBiblioEntidadDTO, this.obtenerAuditoria());
				
			} catch (PgimException e) {
				String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
	            log.error(msjLog, e);            
	            TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
	            
	            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 
				
			} catch (Exception e) {
				String mensaje = "Ocurrió un error al intentar crear el documento: "+ e.getMessage();
				String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
		        log.error(msjLog, e);
		
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));		        
			}

			responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimBiblioDocumentoDTOOCreado, "Bien, el documento ha sido creado");
			return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
		}
		
		
	/**
	 * Permite agregar un archivo dentro de un documento bibliográfico, 
	 * ya sea adjuntando uno nuevo o reemplazando uno existente. 
	 * 
	 * @param pgimBiblioArchivoDTO
	 * @param versionar
	 * @param fileDocumento
	 * @return
	 * @throws PgimException
	 * @throws IOException
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('doc001_MO')")
	@PostMapping(value = "/agregarArchivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseDTO> agregarArchivo(@RequestPart("pgimBiblioArchivoDTO") PgimBiblioArchivoDTO pgimBiblioArchivoDTO,
			@RequestPart("versionar") String versionar, @RequestPart("fileDocumento") MultipartFile fileDocumento)
			throws PgimException, IOException, Exception {		
		
		ResponseDTO responseDTO = null;
		PgimBiblioArchivoDTO pgimBiblioArchivoDTONuevo = null;

		try {
			
			if(versionar.equals("1")) {
				pgimBiblioArchivoDTONuevo = this.bibliografiaService.reemplazarArchivoBibliografia(pgimBiblioArchivoDTO,
						fileDocumento, this.obtenerAuditoria());
				
			}else {
				pgimBiblioArchivoDTONuevo = this.bibliografiaService.crearArchivoBibliografia(pgimBiblioArchivoDTO,
						fileDocumento, this.obtenerAuditoria());					
			}
			
			
		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
            log.error(msjLog, e);            
            TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 
            
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar adjuntar o reemplazar el archivo: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
            log.error(msjLog, e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
        }	
		
		String operacionRealizada = (versionar.equals("1")) ? "reemplazado" : "adjuntado";
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimBiblioArchivoDTONuevo, "Bien, el archivo ha sido " + operacionRealizada);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}

    /***
     * Permite obtener las configuraciones necesarias para la creación o modificación del biblioDocumento
     * @return
     */
	@GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

		ResponseDTO responseDTO = null;
		Map<String, Object> respuesta = new HashMap<>();
		List<PgimValorParametroDTO> lValorParamTipoMedioIngreso = null;
		List<PgimCategoriaDocDTO> lPgimCategoriaDocDTO = null;
		List<PgimSubcategoriaDocDTO> lPgimSubCategoriaDocDTO = null;
		List<PgimEntidadNegocioDTO> lPgimEntidadNegocioDTO = null;

		try {
			lValorParamTipoMedioIngreso = this.parametroService.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_MEDIO_INGRESO);
			lPgimCategoriaDocDTO = this.bibliografiaService.listarCategoriaDocBibliografia();
			lPgimSubCategoriaDocDTO = this.bibliografiaService.listarSubcategoriaDocBibliografia();
			lPgimEntidadNegocioDTO = this.bibliografiaService.listadoEntidadNegocio();
					
		} catch (final DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener las configuraciones para documentos bibliográficos");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener las configuraciones para documentos bibliográficos");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		respuesta.put("mensaje", "Se encontraron las configuraciones"); 
		respuesta.put("lValorParamTipoMedioIngreso", lValorParamTipoMedioIngreso);
		respuesta.put("lPgimCategoriaDocDTO", lPgimCategoriaDocDTO);
		respuesta.put("lPgimSubcategoriaDocDTO", lPgimSubCategoriaDocDTO);
		respuesta.put("lPgimEntidadNegocioDTO", lPgimEntidadNegocioDTO);

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Configuraciones encontradas");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

	
    /***
     * Permite obtener las configuraciones necesarias para la selección de la entidad de negocio
     * @return
     */
	@GetMapping("/obtenerConfiguracionesEntidadNegocio")
    public ResponseEntity<?> obtenerConfiguracionesEntidadNegocio() {

		ResponseDTO responseDTO = null;
		Map<String, Object> respuesta = new HashMap<>();
		List<PgimEntidadNegocioDTO> lPgimEntidadNegocioDTO = null;

		try {
			lPgimEntidadNegocioDTO = this.bibliografiaService.listadoEntidadNegocio();
		} catch (final DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener uan configuración para documentos bibliográficos");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener uan configuración para documentos bibliográficos");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		respuesta.put("mensaje", "Se encontraron los indicadores"); 
		respuesta.put("lPgimEntidadNegocioDTO", lPgimEntidadNegocioDTO);

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Configuraciones encontradas");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /***
     * Permite obtener las configuraciones necesarias para la selección de la entidad de negocio
     * @return
     */
	@GetMapping("/filtroBiblioEntidad/{palabraClave}/{noTablaEntidadNegocio}")
    public ResponseEntity<?> listadoBiblioEntidadPorPalabraClaveYEntidadNegocio(@PathVariable String palabraClave, @PathVariable String noTablaEntidadNegocio) {

		ResponseDTO responseDTO = null;
		List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO = null;

		try {
			if (palabraClave.equals("_vacio_") || noTablaEntidadNegocio.equals("_vacio_")) {
				lPgimBiblioEntidadDTO = new ArrayList<PgimBiblioEntidadDTO>();
				responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Entidades de negocio encontradas");
				responseDTO.setLista(lPgimBiblioEntidadDTO);

				return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);	
			}

			lPgimBiblioEntidadDTO = this.bibliografiaService.listadoBiblioEntidadPorPalabraClaveYEntidadNegocio(palabraClave, noTablaEntidadNegocio);
		} catch (final DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener entidades de negocio");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (final Exception e) {
			log.error(e.getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al obtener entidades de negocio");

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Entidades de negocio encontradas");
		responseDTO.setLista(lPgimBiblioEntidadDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

	/**
     * Permite obtener el documento bibliografico por su identificador interno
     * @param idBiblioDocumento
     * @return
     */
    @GetMapping("/obtenerDocumentoPorId/{idBiblioDocumento}")
    public ResponseEntity<ResponseDTO> obtenerDocumentoPorId(@PathVariable final Long idBiblioDocumento) {
        
    	PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO = null;
        ResponseDTO responseDTO = null;

        try {
        	pgimBiblioDocumentoDTO = this.bibliografiaService.obtenerDocumentoBiblioPorId(idBiblioDocumento);

        } catch (final DataAccessException e) {
            log.error(e.getMostSpecificCause().getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el documento bibliográfico");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
            log.error(e.getMensaje(), e);

            responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
            
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);

            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar recuperar el documento bibliográfico");

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimBiblioDocumentoDTO, "El documento bibliográfico ha sido recuperada");
        
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

	/**
	 * Permite modificar un documento en el alfresco.
	 * 
	 * @param pgimBiblioDocumentoDTO
	 * 
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('doc001_MO')")
	@PostMapping(value = "/modificarDocumento", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseDTO> modificarDocumento(@RequestPart("pgimBiblioDocumentoDTO") PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO,
			@RequestPart("lPgimBiblioEntidadDTO") List<PgimBiblioEntidadDTO> lPgimBiblioEntidadDTO) throws PgimException, IOException, Exception {

		ResponseDTO responseDTO = null;
		PgimBiblioDocumentoDTO pgimBiblioDocumentoDTOModificado = null;
		PgimBiblioDocumento pgimBiblioDocumentoActual = null;

		if(lPgimBiblioEntidadDTO.size()==0){
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Al menos debe de seleccionar una entidad"));
		}

		try {
			pgimBiblioDocumentoActual = this.bibliografiaService.obtenerDocumentoBiblioById(pgimBiblioDocumentoDTO.getIdBiblioDocumento());

			if (pgimBiblioDocumentoActual == null) {    	        
				String mensajeExcepcion = String.format("El documento bibliográfico %s que intenta actualizar no existe en la base de datos", pgimBiblioDocumentoDTO.getIdBiblioDocumento());
				log.error(mensajeExcepcion);

				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
			
		} catch (DataAccessException e) {
			String mensajeExcepcion = "Ocurrió un error al intentar recuperar al documento bibliográfico a actualizar: " + e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensajeExcepcion;
			log.error(msjLog, e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
			

		try {
			
			pgimBiblioDocumentoDTOModificado = this.bibliografiaService.modificarDocumentoBibliografia(pgimBiblioDocumentoDTO, pgimBiblioDocumentoActual,
					lPgimBiblioEntidadDTO, this.obtenerAuditoria());

		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
            log.error(msjLog, e);            
            TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 

		} catch (DataAccessException e) {
			String mensaje = "Ocurrió un error intentar modificar el documento: "+ e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje)); 
		
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar modificar el documento: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
	        
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimBiblioDocumentoDTOModificado, "Bien, el documento ha sido modificado");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}
	
	/**
	 * Permite eliminar un documento bibliográfico
	 * 
	 * @param pgimBiblioDocumentoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('doc001_EL')")
	@PostMapping("/eliminarDocumento")
	public ResponseEntity<ResponseDTO> eliminarDocumento(@RequestBody PgimBiblioDocumentoDTO pgimBiblioDocumentoDTO)
			throws Exception {
				
		ResponseDTO responseDTO = null;
		
		PgimBiblioDocumento pgimBiblioDocumentoActual = null;

        try {
        	pgimBiblioDocumentoActual = this.bibliografiaService.obtenerDocumentoBiblioById(
        			pgimBiblioDocumentoDTO.getIdBiblioDocumento());

            if (pgimBiblioDocumentoActual == null) {
                String mensaje = String.format("El documento %s que intenta eliminar no existe en la base de datos",
                		pgimBiblioDocumentoDTO.getIdBiblioDocumento());
                log.error(mensaje);
                
    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }
        } catch (DataAccessException e) {        	
        	String mensaje = "Ocurrió un error intentar recuperar el documento a eliminar: "+ e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));        
	        
        }

        try {
            this.bibliografiaService.eliminarDocumentoBibliografia(pgimBiblioDocumentoActual, 
            		pgimBiblioDocumentoDTO.getDeMotivoEliminacion(), this.obtenerAuditoria());
            
        } catch (DataAccessException e) {        	
        	String mensaje = "Ocurrió un error intentar eliminar el documento: "+ e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));  
        
		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
	        log.error(msjLog, e);            
	        TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
	        
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 
	        
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar eliminar el documento: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
	    }	
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Bien, el documento ha sido eliminado");
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Permite eliminar un archivo bibliográfico
	 * 
	 * @param pgimBiblioArchivoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('doc001_EL')")
	@PostMapping("/eliminarArchivo")
	public ResponseEntity<ResponseDTO> eliminarArchivo(@RequestBody PgimBiblioArchivoDTO pgimBiblioArchivoDTO)
			throws Exception {
				
		ResponseDTO responseDTO = null;
		
		PgimBiblioArchivo pgimBiblioArchivoActual = null;

        try {
        	pgimBiblioArchivoActual = this.bibliografiaService.obtenerBiblioArchivoById(
        			pgimBiblioArchivoDTO.getIdBiblioArchivo());

            if (pgimBiblioArchivoActual == null) {
                String mensaje = String.format("El archivo %s que intenta eliminar no existe en la base de datos",
                		pgimBiblioArchivoDTO.getIdBiblioArchivo());
                log.error(mensaje);
                
    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
            }
        } catch (DataAccessException e) {        	
        	String mensaje = "Ocurrió un error intentar recuperar el archivo a eliminar: "+ e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));        
	        
        }

        try {
            this.bibliografiaService.eliminarArchivoBibliografia(pgimBiblioArchivoActual, 
            		pgimBiblioArchivoDTO.getDeMotivoEliminacion(), this.obtenerAuditoria());
            
        } catch (DataAccessException e) {        	
        	String mensaje = "Ocurrió un error intentar eliminar el archivo: "+ e.getMostSpecificCause().getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));  
        
		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
	        log.error(msjLog, e);            
	        TipoResultado tipoResultado = (e.getTipoResultado() != null) ? e.getTipoResultado() : TipoResultado.WARNING; 
	        
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); 
	        
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar eliminar el archivo: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
	    }	
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Bien, el archivo ha sido eliminado");
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	
	/**
	 * Permite descargar un archivo bibliográfico desde Alfresco
	 *
	 * @param idArchivo ID del Archivo deseado.
	 * 
	 * @return Archivos en cualquier formato.
	 * @throws Exception
	 */
	@GetMapping("/descargarArchivo/{idArchivo}")
	public ResponseEntity<?> descargarArchivoSiged(@PathVariable Long idArchivo) throws Exception {
		
		ArchivoDescargadoDTO archivoDescargadoDTO = null;
		byte[] archivo = null;		
		HttpHeaders headers = new HttpHeaders();
		
		try {

			archivoDescargadoDTO = this.bibliografiaService.descargarArchivoBibliografia(idArchivo);
				
			archivo = archivoDescargadoDTO.getArchivoData();

			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(archivoDescargadoDTO.getNombreArchivo());
			headers.setContentType(MediaType.valueOf(mimeType));
			headers.set("NombreArchivo", archivoDescargadoDTO.getNombreArchivo());
			headers.setContentLength(archivo.length);
			
		} catch (PgimException e) {
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + e.getMensaje();
	        log.error(msjLog, e);            
	        
	        return null;
	        
		} catch (Exception e) {
			String mensaje = "Ocurrió un error al intentar descargar el archivo: "+ e.getMessage();
			String msjLog = this.logPgimService.obtenerPrefijoLogSimple(this.obtenerAuditoria()) + mensaje;
	        log.error(msjLog, e);
	
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
	        return null;
	    }	
		
		return new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);
	}

}
