package pe.gob.osinergmin.pgim.controllers;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.activation.MimetypesFileTypeMap;
import javax.validation.Valid;

import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado;
import pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFiscaDetalleAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorGeotecnicoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoSubcatDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionscNotifDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaPlantiDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ReporteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimDestinatarioDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimDocEtiquetaNotif;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimFaseProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.DemarcacionUMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubTipoSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaPlantiRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.AgenteSupervisadoService;
import pe.gob.osinergmin.pgim.services.ArchivoService;
import pe.gob.osinergmin.pgim.services.CategoriaDocService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.EmpresaSupervisoraService;
import pe.gob.osinergmin.pgim.services.EqpInstanciaProService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.InvolucradoSupervService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.ProcesoService;
import pe.gob.osinergmin.pgim.services.SupervInstrumentoService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.services.UbigeoService;
import pe.gob.osinergmin.pgim.services.UnidadMineraService;
import pe.gob.osinergmin.pgim.siged.ArchivoAnularInRO;
import pe.gob.osinergmin.pgim.siged.ArchivoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.ArchivoRevertir;
import pe.gob.osinergmin.pgim.siged.Archivos;
import pe.gob.osinergmin.pgim.siged.ConsultaListadoDocumento;
import pe.gob.osinergmin.pgim.siged.DatosRevertirFirmaDigitalInRO2;
import pe.gob.osinergmin.pgim.siged.DescargaArchivo;
import pe.gob.osinergmin.pgim.siged.Documento;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularInRO;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.DocumentoNuevo;
import pe.gob.osinergmin.pgim.siged.Documentos;
import pe.gob.osinergmin.pgim.siged.EnumerarDocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteDocOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteListarPorUsuarioOut;
import pe.gob.osinergmin.pgim.siged.ExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteReenvio;
import pe.gob.osinergmin.pgim.siged.ExpedienteSiged;
import pe.gob.osinergmin.pgim.siged.ResultadoRevertirFirmaDigital2;
import pe.gob.osinergmin.pgim.siged.TrazabilidadDocumentoDTO;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedPermisos;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.ClaveValorUtil;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.ETipoAccionCrud;
import pe.gob.osinergmin.pgim.utils.EValorParametro;
import pe.gob.osinergmin.pgim.utils.EscritorHtml;
import pe.gob.osinergmin.pgim.utils.PoiWordUtil;
import pe.gob.osinergmin.soa.service.expediente.documentos.revertirfirma.v1.RevertirFirmaResponse;
import javax.servlet.http.HttpServletResponse;
import pe.gob.osinergmin.pgim.models.repository.IndicadorGeotecnicoRepository;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a las
 * Documentos y la integración PGIM-SIGED
 * 
 * @descripción: Documentos
 *
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 01/07/2020
 */
@RestController
@Slf4j
@RequestMapping("/documentos")
public class DocumentoController extends BaseController {

	@Autowired
	private CategoriaDocService categoriaDocService;

	@Autowired
	private ProcesoService procesoService;

	@Autowired
	private SupervisionRepository supervisionRepository;
	
	@Autowired
	private SubTipoSupervisionRepository subTipoSupervisionRepository;

	@Autowired
	private DemarcacionUMineraRepository demarcacionUMineraRepository;
	
	@Autowired
	private EmpresaSupervisoraService empresaSupervisoraService;

	@Autowired
	private UnidadMineraService unidadMineraService;

	@Autowired
	private  SupervInstrumentoRepository supervInstrumentoRepository;
	
	@Autowired
  	private SupervInstrumentoService supervInstrumentoService;

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private ArchivoService archivoService;

	@Autowired
	private InstanciaProcesService instanciaProcesService;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private FaseProcesoRepository faseProcesoRepository;

	@Autowired
	private SupervisionService supervisionService;

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private UbigeoService ubigeoService;

	@Autowired
	private InvolucradoSupervService involucradoSupervService;

	@Autowired
	private EqpInstanciaProService eqpInstanciaProService;

	@Autowired
	private HechoConstatadoService hechoConstatadoService;

	@Autowired
	private SigedSoapService sigedSoapService;

	@Autowired
	private SubcategoriaPlantiRepository subcategoriaPlantiRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Autowired
	private InstanciaPasoRepository instanciaPasoRepository;

	@Autowired
	private IndicadorGeotecnicoRepository indicadorGeotecnicoRepository;

	@Autowired
	private AgenteSupervisadoService agenteSupervisadoService;

	@GetMapping("/obtenerGuiaPng/{nombreGuia:.+}")
	public ResponseEntity<?> obtenerGuiaPng(@PathVariable String nombreGuia) {
		
		String rutaGuias = propertiesConfig.getCarpetaGuias();
		Path rutaArchivo = Paths.get(rutaGuias).resolve(nombreGuia).toAbsolutePath();

		Resource recurso = null;
		String mensajeError = "Ocurrió un problema obteniendo la guía: %s; por favor informe a la persona administradora de la PGIM para que ella se asegure que la guía haya sido publicada.";
		mensajeError = String.format(mensajeError, rutaArchivo);
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {			
			log.error(mensajeError, e);

			return new ResponseEntity<String>(mensajeError, HttpStatus.OK);
		}

		if (!recurso.exists() && !recurso.isReadable()) {			
			log.error(mensajeError);

			return new ResponseEntity<String>(mensajeError, HttpStatus.OK);
		}

		HttpHeaders cabecera = new HttpHeaders();		
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		cabecera.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	/**
	 * Permite obtener los valores de los combos para el diálogo
	 * 
	 * @return Lista de categorias, subcategorías y tipo de origen de los
	 *         documentos.
	 */
	@GetMapping("/obtenerConfiguracionesDialogo")
	public ResponseEntity<?> obtenerConfiguracionesDialogo() {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimCategoriaDocDTO> lPgimCategoriaDocDTO = null;
		List<PgimSubcategoriaDocDTO> lPgimSubCategoriaDocDTO = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoOrigenDoc = null;

		try {
			lPgimCategoriaDocDTO = categoriaDocService.listarCategoriaDoc();
			lPgimSubCategoriaDocDTO = categoriaDocService.listarSubcategorias();
			lPgimValorParamDTOTipoOrigenDoc = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ORIGEN_DOCUMENTO);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {			
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimCategoriaDocDTO", lPgimCategoriaDocDTO);
		respuesta.put("lPgimSubCategoriaDocDTO", lPgimSubCategoriaDocDTO);
		respuesta.put("lPgimValorParamDTOTipoOrigenDoc", lPgimValorParamDTOTipoOrigenDoc);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite agregar un documento (y su archivo) en un expediente, si la instancia
	 * proceso no tiene un expediente Siged relacionado, el flujo considera la
	 * creación del mismo
	 * 
	 * @param pgimDocumentoDTO       Documento agregado.
	 * @param pgimInstanciaProcesDTO instancia del proceso.
	 * @param fileDocumento          Archivo adjunto.
	 * 
	 * @return ID del documento creado en Siged.
	 */
	@PostMapping(value = "/adjuntarDocumento", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseDTO> adjuntarDocumento(@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO,
			@RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO,
			@RequestPart("fileDocumento") MultipartFile fileDocumento) throws PgimException, IOException, Exception {

		ResponseEntity<ResponseDTO> responseDTO = null;
		
        Map<String, Object> respuestaLog = new HashMap<>();

		if(pgimDocumentoDTO.getDescIdInstanciaPasoActual() != null){
			Long idInstanciaProces = this.instanciaPasoRepository.findById(pgimDocumentoDTO.getDescIdInstanciaPasoActual()).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();            
			if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, 
                		pgimDocumentoDTO.getDescIdInstanciaPasoActual(), this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

		try {
			
			pgimDocumentoDTO.setDescFlAdjuntadoFormPrincipal("1");
			
			responseDTO = this.documentoService.procesarAdjuntadoDocumento(pgimDocumentoDTO, pgimInstanciaProcesDTO,
					fileDocumento, this.obtenerAuditoria());
		} catch (PgimException e) {
			
			// Excepcion controlada que deberá ser manejada por el frontend
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage())); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return responseDTO;
	}

	@PostMapping("/notificarDocumento")
	public ResponseEntity<ResponseDTO> notificarDocumento(
			@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO)
			throws PgimException, IOException, Exception {

		Map<String, Object> respuestaLog = new HashMap<>();

		ResponseEntity<ResponseDTO> respuesta = null;

		if(pgimDocumentoDTO.getIdInstanciaProceso() != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProceso(pgimDocumentoDTO.getIdInstanciaProceso()).getIdInstanciaProceso();            
			if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, 
                		pgimDocumentoDTO.getDescIdInstanciaPasoActual(), this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

		try {
			respuesta = this.documentoService.notificarDocumentoPersonaJuridica(pgimDocumentoDTO,
					this.obtenerAuditoria());
		} catch (PgimException e) {

			// Excepcion controlada que deberá ser manejada por el frontend
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }       

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage(), 0));
		}

		return respuesta;
	}

	/**
	 * Permite generar documento(s) (y su archivo) en un expediente, si la instancia
	 * proceso no tiene un expediente Siged relacionado, el flujo considera la
	 * creación del mismo
	 * 
	 * @param pgimDocumentoDTO       Documento agregado.
	 * @param pgimInstanciaProcesDTO instancia del proceso.
	 * 
	 * @return ID del documento creado en Siged.
	 */
	@PostMapping("/generarDocumento")
	public ResponseEntity<ResponseDTO> generarDocumento(@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO,
			@RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO)
			throws PgimException, IOException, Exception {

		Map<String, Object> respuestaLog = new HashMap<>();

		ResponseEntity<ResponseDTO> re = null;

		if(pgimDocumentoDTO.getIdInstanciaProceso() != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProceso(pgimDocumentoDTO.getIdInstanciaProceso()).getIdInstanciaProceso();            
			if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

		try {
				pgimDocumentoDTO.setIdProceso(pgimInstanciaProcesDTO.getIdProceso());
				
				re = this.documentoService.procesarGenerarDocumento(pgimDocumentoDTO, pgimInstanciaProcesDTO, this.obtenerAuditoria());
			
		} catch (PgimException e) {
			log.warn(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.warn(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), 0));		
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Error al intentar generar el documento: "+ e.getMessage(), 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return re;
	}

	/**
	 * Permite generar documento(s) (y su archivo) en un expediente, si la instancia
	 * proceso no tiene un expediente Siged relacionado, el flujo considera la
	 * creación del mismo - Liquidación
	 * 
	 * @param pgimDocumentoDTO       Documento agregado.
	 * @param pgimInstanciaProcesDTO instancia del proceso.
	 * 
	 * @return ID del documento creado en Siged.
	 */
	@PostMapping("/generarDocumentoLiquidacion")
	public ResponseEntity<ResponseDTO> generarDocumentoLiquidacion(
			@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO,
			@RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO)
			throws PgimException, IOException, Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
		ResponseEntity<ResponseDTO> respuesta = null;

		if(pgimDocumentoDTO.getIdInstanciaProceso() != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProceso(pgimDocumentoDTO.getIdInstanciaProceso()).getIdInstanciaProceso();            
			if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

		try {
			respuesta = this.documentoService.procesarGenerarDocumentoLiquidacion(pgimDocumentoDTO,
					pgimInstanciaProcesDTO,
					this.obtenerAuditoria());
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), 0));
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar generar el documento de la liquidación: "+ e.getMessage(), 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return respuesta;
	}

	/**
	 * Permite obtener la lista de procesos
	 * 
	 * @return Lista de Procesos PGIM.
	 */
	@GetMapping
	public ResponseEntity<Iterable<PgimProceso>> listarProcesos() {
		Iterable<PgimProceso> pgimProceso = new ArrayList<>();

		pgimProceso = procesoService.listarProcesos();

		if (pgimProceso == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(pgimProceso);
	}

	/**
	 * Permite recuperar los permisos de un usuario Siged respecto a un expediente.
	 * 
	 * @param coTablaInstancia Código de la tabla instancia del proceso
	 * @param idProceso        Identificador interno de la instancia del proceso.
	 * @param idFase           Identificador interno de la fase de proceso.
	 * 
	 * @return permisos en el expediente.
	 * @throws Exception
	 */
	@GetMapping("/permisosExpediente/{coTablaInstancia}/{idProceso}/{idFase}")
	public ResponseEntity<ResponseDTO> obtenerPermisosExpediente(@PathVariable Long coTablaInstancia,
			@PathVariable Long idProceso, @PathVariable Long idFase) throws Exception {

		Map<String, Object> respuestaLog = new HashMap<>();        
		ResponseDTO respuestaDTO = null;
		SigedPermisos sigedPermisos;

		try {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			
			Map<String, Object> parametros = this.documentoService.obtenerPermisosExpediente(coTablaInstancia,
					idProceso, idFase, this.obtenerAuditoria());
			sigedPermisos = (SigedPermisos) parametros.get("sigedPermisos");

			if(!sigedPermisos.getDescNuExpedienteSiged().equals("0")){
				Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(sigedPermisos.getDescNuExpedienteSiged()).getIdInstanciaProceso();
				if(idInstanciaProces != null){
					respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
				}else {
					respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
				}
			}

			respuestaDTO = new ResponseDTO("success", sigedPermisos, "Permisos Ok");

		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);

			String mensaje = String.format(
					"Ocurrió un error al realizar la consulta de permisos para el expediente. Mensaje %s",
					e.getMensaje());
			respuestaDTO = new ResponseDTO("error", null, mensaje);
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			String mensaje = String.format(
					"Ocurrió un error al realizar la consulta de permisos para el expediente. Mensaje %s",
					e.getMessage());
			respuestaDTO = new ResponseDTO("error", null, mensaje);
		}

		return ResponseEntity.status(HttpStatus.OK).body(respuestaDTO);
	}

	/**
	 * Permite recuperar la lista de documentos y archivos asociados a una instancia
	 * de paso de un flujo dado, para Tomar conocimiento de cuáles son los
	 * documentos y archivos de una instancia de proceso dado y su etapa.
	 *
	 * @param idProceso Identificador interno de la instancia del proceso.
	 * @param idFase    Identificador interno de la fase de proceso.
	 * 
	 * @return lista de Documentos.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/listarDocumentos/{coTablaInstancia}/{idProceso}/{idFase}/{idSubcatDocumento}/{ordenamiento}")
	public ResponseEntity<?> listarDocumentos(@PathVariable Long coTablaInstancia, @PathVariable Long idProceso,
			@PathVariable Long idFase, @PathVariable Long idSubcatDocumento, @PathVariable String ordenamiento)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		List<PgimDocumentoDTO> lPgimDocumentoDTO = null;
		Long cantidadDocumentos = 0L;

		Sort sort = Sort.by(ordenamiento.split(",")[0]);

		if (ordenamiento.split(",")[1].equals("asc")) {
			sort = sort.ascending();
		} else if (ordenamiento.split(",")[1].equals("desc")) {
			sort = sort.descending();
		}

		if (ordenamiento.split(",")[0].equals("pgimFaseProceso.noFaseProceso")) {
			Sort sort2 = Sort.by("feOrigenDocumento");
			if (ordenamiento.split(",")[1].equals("asc")) {
				sort2 = sort2.ascending();
			} else if (ordenamiento.split(",")[1].equals("desc")) {
				sort2 = sort2.descending();
			}

			sort = sort.and(sort2);
		}

		Sort sortCreacion = Sort.by("feCreacion");
		sortCreacion = sortCreacion.descending();

		sort = sort.and(sortCreacion);

		if (idSubcatDocumento == 0) {
			idSubcatDocumento = null;
		}

		try {
			Map<String, Object> parametros = this.documentoService.verDocumentos(coTablaInstancia, idProceso, idFase,
					idSubcatDocumento, sort, this.obtenerAuditoria());
			lPgimDocumentoDTO = (List<PgimDocumentoDTO>) parametros.get("lPgimDocumentoDTOResultado");
			cantidadDocumentos = (Long) parametros.get("cantidadDocumentos");

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMessage());

			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron documentos");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);
		respuesta.put("cantidadDocumentos", cantidadDocumentos);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite recuperar la lista de documentos y archivos asociados a una instancia
	 * de paso de un flujo dado, para Tomar conocimiento de cuáles son los
	 * documentos y archivos de una instancia de proceso dado y su etapa.
	 *
	 * @param idProceso Identificador interno de la instancia del proceso.
	 * @param idFase    Identificador interno de la fase de proceso.
	 * 
	 * @return lista de Documentos.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/listarDocumentos")
	public ResponseEntity<?> listarDocumentos(@RequestBody PgimDocumentoDTO pgimDocumentoDTO)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		List<PgimDocumentoDTO> lPgimDocumentoDTO = null;
		Long cantidadDocumentos = 0L;

		if(pgimDocumentoDTO.getDescIdInstanciaPasoActual() != null){
			Long idInstanciaProces = this.instanciaPasoRepository.findById(pgimDocumentoDTO.getDescIdInstanciaPasoActual()).orElse(null).getPgimInstanciaProces().getIdInstanciaProceso();
			if(idInstanciaProces != null){
                respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, 
                		pgimDocumentoDTO.getDescIdInstanciaPasoActual(), this.obtenerAuditoria().getUsername());
            }else {
                respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
            }
        } else {
            respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
        }

		String ordenamiento = pgimDocumentoDTO.getDescOrdenamiento();
		Long idSubcatDocumento = pgimDocumentoDTO.getIdSubcatDocumento();

		Sort sort = Sort.by(ordenamiento.split(",")[0]);

		if (ordenamiento.split(",")[1].equals("asc")) {
			sort = sort.ascending();
		} else if (ordenamiento.split(",")[1].equals("desc")) {
			sort = sort.descending();
		}

		if (ordenamiento.split(",")[0].equals("pgimFaseProceso.noFaseProceso")) {
			Sort sort2 = Sort.by("feOrigenDocumento");
			if (ordenamiento.split(",")[1].equals("asc")) {
				sort2 = sort2.ascending();
			} else if (ordenamiento.split(",")[1].equals("desc")) {
				sort2 = sort2.descending();
			}

			sort = sort.and(sort2);
		}

		Sort sortCreacion = Sort.by("feCreacion");
		sortCreacion = sortCreacion.descending();

		sort = sort.and(sortCreacion);

		if (idSubcatDocumento == 0) {
			idSubcatDocumento = null;
			pgimDocumentoDTO.setIdSubcatDocumento(idSubcatDocumento);
		}

		try {
			Map<String, Object> parametros = this.documentoService.verDocumentos(pgimDocumentoDTO, sort,
					this.obtenerAuditoria());
			lPgimDocumentoDTO = (List<PgimDocumentoDTO>) parametros.get("lPgimDocumentoDTOResultado");
			cantidadDocumentos = (Long) parametros.get("cantidadDocumentos");

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (final PgimException e) {
            // Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron documentos");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);
		respuesta.put("cantidadDocumentos", cantidadDocumentos);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/listarDocumentosSiged")
	public ResponseEntity<?> listarDocumentosSiged(@RequestBody ConsultaListadoDocumento consultaListadoDocumento) throws Exception {
		log.info("Inicio de solicitud para obtener los documentos del Siged");
		
		Map<String, Object> respuesta = new HashMap<>();
		ResponseDTO responseDTO = new ResponseDTO();
		List<PgimDocumentoDTO> lPgimDocumentoDTO = new LinkedList<PgimDocumentoDTO>();
		Long cantidadDocumentos = 0L;
		
		Map<String, Object> respuestaLog = new HashMap<>();
		String mensajeExcepcionGeneral = "Ocurrió un problema para obtener el listado de documentos del Siged. ";

		respuestaLog = this.flujoTrabajoService.mostrarLog(consultaListadoDocumento.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		
		try {
			
			Map<String, Object> parametros = this.documentoService.listarDocumentosSiged(consultaListadoDocumento);
			lPgimDocumentoDTO = (List<PgimDocumentoDTO>) parametros.get("lPgimDocumentoDTOResultado");
			cantidadDocumentos = (Long) parametros.get("cantidadDocumentos");
			
		} catch (DataAccessException e) {
			mensajeExcepcionGeneral += e.getMostSpecificCause().getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" ); 
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral, 0));

		} catch (PgimException e) {
			
			TipoResultado tipoResultado = (e.getTipoResultado() == null) ? TipoResultado.WARNING : e.getTipoResultado();
			
			mensajeExcepcionGeneral += e.getMensaje();
			
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" );
			log.error(e.getMensaje(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, mensajeExcepcionGeneral));

		} catch (Exception e) {
			mensajeExcepcionGeneral += e.getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" );
			log.error(e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral));
		}
	
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);
		respuesta.put("cantidadDocumentos", cantidadDocumentos);
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Obtención de datos satisfatorio");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
    
    
	

	/**
	 * Permite obtener el listado de destinatarios de documentos a notificar.
	 * @param pgimDocumentoDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/listarDestinatarios")
	public ResponseEntity<?> listarDestinatarios(@RequestBody PgimDocumentoDTO pgimDocumentoDTO)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		List<PgimInfraccionContraDTO> lPgimInfraccionContraDTO= null;

		try {
			lPgimInfraccionContraDTO = this.documentoService.listarDestinatarios(pgimDocumentoDTO);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de destinatarios");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (final PgimException e) {
            // Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de destinatarios");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de destinatarios");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los destinatarios");
		respuesta.put("lPgimInfraccionContraDTO", lPgimInfraccionContraDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	/**
	 * Permite definir los destinatarios de documentos a notificar.
	 * @param lPgimDestinatarioDocDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/definirDestinatarios/{idDocumento}")
	public ResponseEntity<?> definirDestinatarios(@RequestBody PgimDestinatarioDocDTO[] lPgimDestinatarioDocDTO, @PathVariable Long idDocumento)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		List<PgimDestinatarioDocDTO> lPgimDestinatarioDocDTODefinidos= null;

		try {
			lPgimDestinatarioDocDTODefinidos = this.documentoService.definirDestinatarios(lPgimDestinatarioDocDTO, idDocumento, this.obtenerAuditoria());

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar definir los detinatarios");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (final PgimException e) {
            // Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar definir los detinatarios");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar definir los detinatarios");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se ha definido los detinatarios de manera exitosa");
		respuesta.put("lPgimDestinatarioDocDTODefinidos", lPgimDestinatarioDocDTODefinidos);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite recuperar un documento y sus archivos de un expediente
	 *
	 * @param nroexp Número de expediente.
	 * @param files  files=1 mostrará adicionalmente el detalle de los archivos(no
	 *               anulados) de cada documento..
	 * 
	 * @return lista de Documentos.
	 * @throws Exception
	 */
	@GetMapping("/obtenerExpedienteDocumentoSIGED/{nroexp}/{files}")
	public ResponseEntity<?> obtenerExpedienteDocumentoSIGED(@PathVariable String nroexp, @PathVariable String files)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Documentos sIGEDDocumentos = null;
		
		try {
			sIGEDDocumentos = this.documentoService.obtenerExpedienteDocumentoSiged(nroexp, files,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());

			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);		
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMessage());

			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron documentos.");
		respuesta.put("Documentos", sIGEDDocumentos);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite recuperar la lista de documento en el PGIM
	 *
	 * @param nuExpedienteSiseg Número de expediente SIGED.
	 * 
	 * @return lista de Documentos SIGED.
	 * @throws Exception
	 */
	@GetMapping("/obtenerDocumentosPGIM/{idInstanciaProces}")
	public ResponseEntity<?> obtenerDocumentosPgim(@PathVariable Long idInstanciaProces) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		List<PgimDocumentoDTO> lPgimDocumentoDTO = null;

		PgimInstanciaProces pgimInstanciaProcess = null;

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimInstanciaProcess = this.documentoService.obtenerInstanciaProceso(idInstanciaProces);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la instancia de proceso");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la instancia de proceso");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			lPgimDocumentoDTO = this.documentoService
					.obtenerDocumentosPgim(pgimInstanciaProcess.getIdInstanciaProceso());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los documentos");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron los documentos");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener la lista de los documentos del SIGED y de la PGIM
	 * 
	 * @param idInstanciaProces
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/obtenerDocumentosSIGED/{idInstanciaProces}")
	public ResponseEntity<?> obtenerDocumentosSiged(@PathVariable Long idInstanciaProces) throws Exception {

		log.info("Inicio de solicitud para obtener los documentos del Siged");
		
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		List<PgimDocumentoDTO> lPgimDocumentoDTO = null;
		Documentos lDocumentos = null;

		PgimInstanciaProces pgimInstanciaProcess = null;

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimInstanciaProcess = this.documentoService.obtenerInstanciaProceso(idInstanciaProces);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la instancia de proceso");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la instancia de proceso");
			respuesta.put("error", e.getMessage());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			lPgimDocumentoDTO = this.documentoService
					.obtenerDocumentosPgim(pgimInstanciaProcess.getIdInstanciaProceso());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los documentos");
			respuesta.put("error", e.getMessage());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			lDocumentos = this.documentoService.obtenerExpedienteDocumentoSiged(
					pgimInstanciaProcess.getNuExpedienteSiged(), "0", this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (final PgimException e) {
            // Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMessage());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (lPgimDocumentoDTO != null && lPgimDocumentoDTO.size() > 0 && lDocumentos != null
				&& lDocumentos.getListaDocumento() != null && lDocumentos.getListaDocumento().size() > 0) {

			List<Documento> listaDocumentosSIGED = lDocumentos.getListaDocumento();
			List<PgimDocumentoDTO> listaDocumentosPGIM = lPgimDocumentoDTO;

			List<Documento> listaDocumentosSIGEDTmp = new LinkedList<>();
			lDocumentos.setListaDocumento(listaDocumentosSIGEDTmp);
			for (Documento docSIGED : listaDocumentosSIGED) {
				boolean docExistePGIM = false;
				for (PgimDocumentoDTO docPGIM : listaDocumentosPGIM) {
					if (docSIGED.getIdDocumento() != null && docSIGED.getIdDocumento() != ""
							&& docPGIM.getCoDocumentoSiged() != null
							&& docSIGED.getIdDocumento().equals(docPGIM.getCoDocumentoSiged().toString())) {
						docExistePGIM = true;
						break;
					}
				}

				if (!docExistePGIM) {
					listaDocumentosSIGEDTmp.add(docSIGED);
				}
			}
			lDocumentos.setListaDocumento(listaDocumentosSIGEDTmp);

			List<PgimDocumentoDTO> listaDocumentosPGIMTmp = new LinkedList<>();
			for (PgimDocumentoDTO docPGIM : listaDocumentosPGIM) {
				boolean docExisteSIGED = false;
				for (Documento docSIGED : listaDocumentosSIGED) {
					if (docSIGED.getIdDocumento() != null && docSIGED.getIdDocumento() != ""
							&& docPGIM.getCoDocumentoSiged() != null
							&& docSIGED.getIdDocumento().equals(docPGIM.getCoDocumentoSiged().toString())) {

						docPGIM.setNombreTipoDocumento(docSIGED.getNombreTipoDocumento());
						docPGIM.setNumeroDocumento(docSIGED.getNroDocumento());
						docPGIM.setAsuntoSiged(docSIGED.getUltimoAsunto());
						docPGIM.setFechaDocumentoSiged(docSIGED.getFechaDocumento());
						docPGIM.setFechaCreacionSiged(docSIGED.getFechaCreacion());
						docPGIM.setFechaLimiteAtencionSiged(docSIGED.getFechaLimiteAtencion());
						docPGIM.setFechaNumeracionSiged(docSIGED.getFechaNumeracion());

						docExisteSIGED = true;
						break;
					}
				}
				if (docExisteSIGED) {
					docPGIM.setExisteDocumentoSiged(1);
					listaDocumentosPGIMTmp.add(docPGIM);
				} /*
					 * else { listaDocumentosPGIMTmp.add(docPGIM); }
					 */
			}
			lPgimDocumentoDTO = listaDocumentosPGIMTmp;
		}

		respuesta.put("mensaje", "Se encontraron los documentos");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);
		respuesta.put("lDocumentos", lDocumentos);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite obtener los permisos del usuario respecto a un expedientes a partir
	 * del identificador de instancia proceso
	 * 
	 * @param idInstanciaProces
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/permisosExpedienteInstProces/{idInstanciaProces}")
	public ResponseEntity<?> permisosExpediente(@PathVariable Long idInstanciaProces) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		SigedPermisos sigedPermisos;

		PgimInstanciaProces pgimInstanciaProcess = null;
		Long idProceso = 0L;

		if(idInstanciaProces != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimInstanciaProcess = this.documentoService.obtenerInstanciaProceso(idInstanciaProces);
			idProceso = pgimInstanciaProcess.getPgimProceso().getIdProceso();
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la instancia de proceso");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			sigedPermisos = sigedSoapService.accesoExpediente(pgimInstanciaProcess.getNuExpedienteSiged(),
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al obtener los permisos sobre el expediente");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (final PgimException e) {
            // Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
            log.error(e.getMensaje(), e);

            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el ranking");
            respuesta.put("error", e.getMensaje());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al obtener los permisos sobre el expediente");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("sigedPermisos", sigedPermisos);
		respuesta.put("idProceso", idProceso);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite incluir documento Siged a Documento PGIM
	 * 
	 * @param documentoDTO
	 * @param instanciaProces
	 * @return
	 * @throws PgimException
	 * @throws IOException
	 */
	@PostMapping("/incluirDocumento")
	public ResponseEntity<?> incluirDocumento(PgimDocumentoDTO documentoDTO, BindingResult resultadoValidacion)
			throws PgimException, IOException, Exception {

		Long rpta = documentoService.incluirDocumento(documentoDTO, this.obtenerAuditoria());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO("success", "Documento registrado correctamente", rpta));
	}

	/**
	 * Permite crear un documento PGIM
	 * 
	 * @param pgimDocumentoDTO
	 * 
	 * @return Mensaje con el resultado de la operación.
	 */
	@PostMapping("/crearDocumentoPgim")
	public ResponseEntity<ResponseDTO> crearDocumentoPgim(@Valid @RequestBody PgimDocumentoDTO pgimDocumentoDTO,
			BindingResult resultadoValidacion) throws Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
		PgimDocumentoDTO pgimDocumentoDTOCreado = null;
		Map<String, Object> respuesta = new HashMap<>();

		if(pgimDocumentoDTO.getIdInstanciaProceso() != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(pgimDocumentoDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // DATA

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Se han encontrado inconsistencias para crear el documento: "+errores.toString() , 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		try {
			pgimDocumentoDTOCreado = documentoService.crearDocumentoPGIM(pgimDocumentoDTO, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar crear el documento: "+e.getMostSpecificCause().getMessage() , 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		} catch (final PgimException e) {
            // Excepcion controlada que deberá ser manejada por el frontend
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        } 
		catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar crear el documento");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			//return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar crear el documento: "+e.getMessage() , 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimDocumentoDTOCreado, "El documento ha sido creado"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	/**
	 * Permite obtener los expedientes para una supervición
	 * 
	 * @param idInstanciaProceso
	 * 
	 * @return lPgimInstanciaProcesDTO Lista de instancias de proceso para la
	 *         supervisión.
	 * @throws Exception
	 */
	@GetMapping("/obtenerExpedientes/{idInstanciaProceso}")
	public ResponseEntity<?> obtenerExpedientes(@PathVariable Long idInstanciaProceso) throws Exception {
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = null;

		if(idInstanciaProceso != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			lPgimInstanciaProcesDTO = documentoService.obtenerExpedientes(idInstanciaProceso);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la lista de expedientes");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar la lista de expedientes");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (lPgimInstanciaProcesDTO == null) {
			mensaje = String.format("No existen expedientes en la base de datos");
			respuesta.put("mensaje", mensaje);
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // EXCEPTION
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
		}

		respuesta.put("mensaje", "Los expedientes han sido recuperados");
		respuesta.put("lPgimInstanciaProcesDTO", lPgimInstanciaProcesDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

	}

	/**
	 * Permite descarga un archivo desde el repositorio del SIGED
	 *
	 * @param idArchivo ID del Archivo deseado.
	 * 
	 * @return Archivos en cualquier formato.
	 * @throws Exception
	 */
	@GetMapping("/descargaArchivo/{idarchivo}")
	public ResponseEntity<?> descargarArchivoSiged(@PathVariable String idarchivo) throws Exception {
		
		DescargaArchivo descargaArchivo = null;
		byte[] archivo = null;		
		HttpHeaders headers = new HttpHeaders();
		
		try {

			descargaArchivo = this.documentoService.descargaArchivo_old(idarchivo);

			if (descargaArchivo.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) { 
				
				archivo = descargaArchivo.getFile().toByteArray();
	
				MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
				String mimeType = fileTypeMap.getContentType(descargaArchivo.getNombre());
				headers.setContentType(MediaType.valueOf(mimeType));
				headers.set("NombreArchivo", descargaArchivo.getNombre());
				headers.setContentLength(archivo.length);
				
			}else {			
				log.error(descargaArchivo.getMessage());
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al descargar el archivo: "+descargaArchivo.getMessage()));
				
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al descargar el archivo: "+e.getMostSpecificCause().getMessage()));
	
		} catch (Exception e) {	
			log.error(e.getMessage(), e);		
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al descargar el archivo: "+e.getMessage()));	
			
		}

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	/**
	 * Permite descargar una versión de determinado archivo desde el repositorio del SIGED
	 *
	 * @param idArchivo 	ID del archivo deseado.
	 * @param labelVersion 	Versión deseada del archivo.
	 * @return 
	 * @throws Exception
	 */
	@GetMapping("/descargarVersionArchivo/{idArchivo}/{labelVersion}")
	public ResponseEntity<?> descargarVersionArchivoSiged(@PathVariable String idArchivo,
			@PathVariable String labelVersion) throws Exception {
		
		DescargaArchivo descargaArchivo = null;
		byte[] archivo = null;		
		HttpHeaders headers = new HttpHeaders();
		
		try {

			descargaArchivo = this.documentoService.descargarVersionArchivo_old(idArchivo, labelVersion);

			if (descargaArchivo.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) { 
				
				archivo = descargaArchivo.getFile().toByteArray();
	
				MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
				String mimeType = fileTypeMap.getContentType(descargaArchivo.getNombre());
				headers.setContentType(MediaType.valueOf(mimeType));
				headers.set("NombreArchivo", descargaArchivo.getNombre());
				headers.setContentLength(archivo.length);
				
			}else {			
				log.error(descargaArchivo.getMessage());
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al descargar la versión del archivo: "+descargaArchivo.getMessage()));
				
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al descargar la versión del archivo: "+e.getMostSpecificCause().getMessage()));
	
		} catch (Exception e) {	
			log.error(e.getMessage(), e);		
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al descargar la versión del archivo: "+e.getMessage()));	
			
		}

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	/**
	 * Permite recuperar la lista de archivos de un documento
	 *
	 * @param idDocumento ID del documento.
	 * 
	 * @return lista de Archivos.
	 * @throws Exception
	 */
	@GetMapping("/obtenerArhivosSiged/{idDocumento}")
	public ResponseEntity<ResponseDTO> obtenerArhivosSiged(@PathVariable String idDocumento) throws Exception {

		Archivos sigedArchivos = null;
		ResponseDTO responseDTO = null;

		try {
//			sigedArchivos = this.documentoService.obtenerArhivosSiged(idDocumento, this.obtenerAuditoria()); // PIDO (solo archivos)
			sigedArchivos = this.documentoService.obtenerArhivosConVersionesSiged(idDocumento, this.obtenerAuditoria()); // Siged-rest-old (archivos con versiones)
			
			if(!sigedArchivos.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				String mensajeExcepcion = "Ocurrió un problema al realizar la consulta de archivos del documento: " + sigedArchivos.getMessage();
				log.error(mensajeExcepcion);
	            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
				return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta de archivos del documento: "+e.getMostSpecificCause().getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (PgimException e) {
			log.error(e.getMensaje(), e);
			responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta de archivos del documento: "+e.getMessage() );
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, sigedArchivos, "Genial, se encontraron archivos."); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	/**
	 * Permite recuperar la lista de archivos de un documento
	 *
	 * @param idDocumento ID del documento.
	 * 
	 * @return lista de Archivos.
	 * @throws Exception
	 */
	@PostMapping("/listarArchivosPorIdDocumentos")
	public ResponseEntity<ResponseDTO> listarArchivosPorIdDocumentos(@RequestBody String[] lstIdDocumento) throws Exception {

		List<Archivos> lstArchivos = new ArrayList<Archivos>();
		Archivos sigedArchivos = null;	
		ResponseDTO responseDTO = null;
		Map<String, Object> lstDocsConArchivos = new HashMap<>();

		try {
			for (String idDocumento : lstIdDocumento) {				
			
				sigedArchivos = this.documentoService.obtenerArhivosSiged(idDocumento, this.obtenerAuditoria()); // PIDO (solo archivos)
//			sigedArchivos = this.documentoService.obtenerArhivosConVersionesSiged(idDocumento, this.obtenerAuditoria()); // Siged-rest-old (archivos con versiones)
			
				if(!sigedArchivos.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					String mensajeExcepcion = "Ocurrió un problema al realizar la consulta de archivos del documento: " + sigedArchivos.getMessage();
					log.error(mensajeExcepcion);
		            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensajeExcepcion);
					return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
				}
				
				lstArchivos.add(sigedArchivos);
				
				lstDocsConArchivos.put(idDocumento, sigedArchivos);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta de archivos del documento: "+e.getMostSpecificCause().getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (PgimException e) {
			log.error(e.getMensaje(), e);
			responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta de archivos del documento: "+e.getMessage() );
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, lstDocsConArchivos, "Genial, se encontraron archivos."); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Permite agregar un archivo dentro de un documento Siged.
	 * @param pgimDocumentoDTO
	 * @param versionar
	 * @param fileDocumento
	 * @return
	 * @throws PgimException
	 * @throws IOException
	 * @throws Exception
	 */
	@PostMapping(value = "/adjuntarArchivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseDTO> adjuntarArchivo(@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO,
			@RequestPart("versionar") String versionar, @RequestPart("fileDocumento") MultipartFile fileDocumento)
			throws PgimException, IOException, Exception {
		
		Long idArchivo = (long) 0;
		Map<String, Object> respuestaLog = new HashMap<>();

		// Obteniendo los datos de la auditoría:
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();

 		if(pgimDocumentoDTO.getIdInstanciaProceso() != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(pgimDocumentoDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			
		// Obtener correlativo
		Long correlativoArchivo = archivoService.obtenerCorrelativoCodNombre();

		// Obtener el nombre original del archivo
		String noOriginalArchivo = fileDocumento.getOriginalFilename();
		if (noOriginalArchivo == null) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[No se ha encontrado el nombre del archivo a adjuntar]" ); // STORY:PGIM-7606 // EXCEPTION
			throw new PgimException("No se ha encontrado el nombre del archivo a adjuntar");
		}
		// validar que el nombre de archivo no supere los 160 caracteres.
		if (noOriginalArchivo.length() > 164) {
			String mensaje = "No es posible adjuntar el archivo porque su nombre supera los 160 caracteres. Por favor reduzca el tamaño del nombre del archivo";
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // EXCEPTION
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.WARNING, mensaje, 0));
		}

		// Obtener el nombre codificado del archivo
		String nombreCodificado = null;

		if (versionar.equals("0")) {
			nombreCodificado = archivoService.codificarArchivoPgim(fileDocumento.getOriginalFilename(),
					pgimDocumentoDTO.getIdSubcatDocumento(), correlativoArchivo);
		} else if (versionar.equals("1")) {
			String extensionOriginal = CommonsUtil.obtenerExtensionNombreArchivo(noOriginalArchivo);
			String extensionNueva = CommonsUtil.obtenerExtensionNombreArchivo(pgimDocumentoDTO.getNombreArchivo());

			if (!extensionOriginal.toLowerCase().equals(extensionNueva.toLowerCase())) {
				String mensaje = "No es posible reemplazar el archivo porque no tiene la misma extensión que el que ha seleccionado.";
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // EXCEPTION
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.WARNING, mensaje, idArchivo));
			}

			nombreCodificado = pgimDocumentoDTO.getNombreArchivo();
		}

		// Cambiando nombre al archivo cargado
		MultipartFile multipartFile = new MockMultipartFile(nombreCodificado, fileDocumento.getInputStream());

		Documento documento = new Documento();
		documento.setIdDocumento(pgimDocumentoDTO.getCoDocumentoSiged().toString());
		documento.setUsuarioCreador(auditoriaDTO.getCoUsuarioSiged());

		PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository.findById(pgimDocumentoDTO.getIdFaseProceso())
				.orElse(null);

		
			Archivos archivos = this.documentoService.agregarArchivoSiged_old(documento, versionar, multipartFile,
					pgimDocumentoDTO.getNuExpedienteSiged(), pgimFaseProceso.getPgimProceso().getIdProceso(),
					auditoriaDTO);
			if (!archivos.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				String servicio = ConstantesUtil.PARAM_SIGED_AGREGAR_ARCHIVO;
				String msg = String.format("El servicio  " +servicio.replace("{versionar}", versionar)+ " generó el siguiente error: "+archivos.getMessage());
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + msg + "]"); // STORY:PGIM-7606 // // EXCEPTION
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, msg, 0));
			}
			
			// Registrar archivo PGIM (Log)
			PgimArchivoDTO archivoDTO = new PgimArchivoDTO();
			archivoDTO.setIdDocumento(pgimDocumentoDTO.getIdDocumento());
			archivoDTO.setNoOriginalArchivo(noOriginalArchivo);
			archivoDTO.setNoNuevoArchivo(nombreCodificado);
			archivoDTO.setSeArchivo(correlativoArchivo);

			archivoService.registrarArchivo(archivoDTO, auditoriaDTO);
			
		} catch (PgimException e) {
						
			// Excepcion controlada que deberá ser manejada por el frontend
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
            log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje())); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		
		} catch (final Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
            log.error(e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar adjuntar o reemplazar el archivo: "+ e.getMessage()));
        }

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(TipoResultado.SUCCESS, "Archivo cargado correctamente", idArchivo)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
	}

	/**
	 * Permite anular un documento de un expediente. Asimismo, el documento PGIM
	 * quedará como esRegistro = 0
	 * 
	 * @param pgimDocumentoDTOAux 
	 * @return
	 * @throws Exception
	 */
@PostMapping("/anularDocumentoSiged")
	public ResponseEntity<ResponseDTO> anularDocumentoSiged(@RequestBody PgimDocumentoDTO pgimDocumentoDTOAux)
			throws Exception {

				String nroExpedienteSiged = pgimDocumentoDTOAux.getNuExpedienteSiged();
				String idDocumentoSiged = pgimDocumentoDTOAux.getCoDocumentoSiged().toString();
				String motivoSiged = pgimDocumentoDTOAux.getMotivoAnulacion();
				Long idDocumentoPGIM = pgimDocumentoDTOAux.getIdDocumento();
		
				DocumentoAnularInRO documentoAnularInRO = new DocumentoAnularInRO();
		DocumentoAnularOutRO documentoAnularOutRO = null;
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		ResponseDTO responseDTO = null;
    	Map<String, Object> respuestaLog = new HashMap<>();        

		if(nroExpedienteSiged != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nroExpedienteSiged).getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProces, 
						pgimDocumentoDTOAux.getDescIdInstanciaPasoActual(), this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			documentoAnularInRO.setNroExpediente(nroExpedienteSiged);
			documentoAnularInRO.setIdDocumento(idDocumentoSiged);
			documentoAnularInRO.setMotivo(motivoSiged);
			documentoAnularInRO.setIdUsuarioAnulacion(auditoriaDTO.getCoUsuarioSiged());

			PgimDocumento pgimDocumento = this.documentoRepository.findById(idDocumentoPGIM).orElse(null);

			PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
					.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

			Long idProceso = pgimFaseProceso.getPgimProceso().getIdProceso();

			if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
				// Se debe validar si es que es posible anular el documento
				this.supervisionService.asegurarConsistenciaCambiosDocumento(idDocumentoPGIM, ETipoAccionCrud.ELIMINAR);
			}

			documentoAnularOutRO = this.documentoService.anularDocumentoSiged(documentoAnularInRO,
					pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

			if (documentoAnularOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				// Se procede a eliminar lógicamente al documento de la PGIM.
				this.documentoService.eliminarDocumento(idDocumentoPGIM, this.obtenerAuditoria());
			}

		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la anulación del documento: "+e.getMostSpecificCause().getMessage());
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);
			documentoAnularOutRO = new DocumentoAnularOutRO();
			documentoAnularOutRO.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL);
			documentoAnularOutRO.setMessage(e.getMensaje());

			responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());
			
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);			
            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la anulación del documento: "+e.getMessage() );

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		if (documentoAnularOutRO != null) {
			if (documentoAnularOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				responseDTO = new ResponseDTO(TipoResultado.SUCCESS, documentoAnularOutRO, "El documento fue eliminado"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			}
		}		

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Permite anular un documento de un expediente. Asimismo, el documento PGIM
	 * quedará como esRegistro = 0
	 * 
	 * @param nroExpedienteSiged Número del expediente Siged.
	 * @param idDocumentoSiged   Identificador interno del documento Siged.
	 * @param motivoSiged        Motivo de la eliminación.
	 * @param idDocumentoPGIM    Identificador interno del documento de la PGIM.
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/revertirFirmaDocumentoSiged/{nroExpedienteSiged}/{idDocumentoSiged}/{idDocumentoPGIM}/{motivoSiged}")
	public ResponseEntity<?> revertirFirmaDocumentoSiged(@PathVariable String nroExpedienteSiged,
			@PathVariable String idDocumentoSiged, @PathVariable String motivoSiged, @PathVariable Long idDocumentoPGIM)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		DatosRevertirFirmaDigitalInRO2 inRo = new DatosRevertirFirmaDigitalInRO2();
		ResultadoRevertirFirmaDigital2 result = null;
		ArchivoRevertir archivo = null;
    	Map<String, Object> respuestaLog = new HashMap<>();        
		
		if(nroExpedienteSiged != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nroExpedienteSiged).getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {

			inRo.setMotivoReversion(motivoSiged);
			PgimDocumento pgimDocumento = this.documentoRepository.findById(idDocumentoPGIM).orElse(null);
			PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
					.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

			result = this.documentoService.revertirFirmaDocumentoSiged(inRo, nroExpedienteSiged, idDocumentoSiged,
					pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

			archivo = result.getArchivosRevertir().get(0);

			if (archivo.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				// Se procede a actualizar el estado de firma en el documento de la PGIM.
				// this.documentoService.actualizarEstadoFirma(idDocumentoPGIM,
				// this.obtenerAuditoria());
			}

		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);
			result = new ResultadoRevertirFirmaDigital2();
			result.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL);
			result.setMessage(e.getMensaje());
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la reversión de la firma del documento");
			respuesta.put("error", e.getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);			

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);			
		}

		respuesta.put("resultadoRevertirFirmaDigital", result);

		if (archivo != null) {
			if (archivo.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				respuesta.put("mensaje", "La firma fue revertida");
			} else {
				respuesta.put("mensaje", archivo.getMessage());
			}
		}

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite anular un documento de un expediente. Asimismo, el documento PGIM
	 * quedará como esRegistro = 0
	 * 
	 * @param nroExpedienteSiged Número del expediente Siged.
	 * @param idArchivoSiged     Identificador interno del archivo Siged.
	 * @param motivoSiged        Motivo de la eliminación.
	 * @param idDocumentoPGIM    Identificador interno del documento de la PGIM.
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/revertirFirmaArchivoSiged_old/{nroExpedienteSiged}/{idArchivoSiged}/{idDocumentoPGIM}/{motivoSiged}")
	public ResponseEntity<?> revertirFirmaArchivoSiged_old(@PathVariable String nroExpedienteSiged,
			@PathVariable String idArchivoSiged, @PathVariable Long idDocumentoPGIM, @PathVariable String motivoSiged)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		DatosRevertirFirmaDigitalInRO2 inRo = new DatosRevertirFirmaDigitalInRO2();
		ResultadoRevertirFirmaDigital2 result = null;
		ArchivoRevertir archivo = null;
		try {

			inRo.setMotivoReversion(motivoSiged);
			inRo.setArchivo(idArchivoSiged);
			PgimDocumento pgimDocumento = this.documentoRepository.findById(idDocumentoPGIM).orElse(null);
			PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
					.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

			result = this.documentoService.revertirFirmaArchivoSiged_old(inRo, nroExpedienteSiged, idArchivoSiged,
					pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

			String msj = result.getArchivosRevertir().get(0).getMessage().replace("revirtio", "revirtió");
			result.getArchivosRevertir().get(0).setMessage(msj);
			archivo = result.getArchivosRevertir().get(0);

			if (archivo.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				// Se procede a actualizar el estado de firma en el documento de la PGIM.
				// this.documentoService.actualizarEstadoFirma(idDocumentoPGIM,
				// this.obtenerAuditoria());
			}
		} catch (PgimException e) {
			log.error(e.getMessage(), e);
			result = new ResultadoRevertirFirmaDigital2();
			result.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL);
			result.setMessage(e.getMensaje());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		respuesta.put("resultadoRevertirFirmaDigital", result);

		if (archivo != null) {
			if (archivo.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				respuesta.put("mensaje", "La firma fue revertida");
			} else {
				respuesta.put("mensaje", archivo.getMessage());
			}
		}
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite anular un documento de un expediente. Asimismo, el documento PGIM
	 * quedará como esRegistro = 0
	 * 
	 * @param nroExpedienteSiged Número del expediente Siged.
	 * @param idArchivoSiged     Identificador interno del archivo Siged.
	 * @param motivoSiged        Motivo de la eliminación.
	 * @param idDocumentoPGIM    Identificador interno del documento de la PGIM.
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/revertirFirmaArchivoSiged/{nroExpedienteSiged}/{idArchivoSiged}/{idDocumentoPGIM}/{motivoSiged}")
	public ResponseEntity<ResponseDTO> revertirFirmaArchivoSiged(@PathVariable String nroExpedienteSiged,
			@PathVariable Long idArchivoSiged, @PathVariable Long idDocumentoPGIM, @PathVariable String motivoSiged)
			throws Exception {

		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		RevertirFirmaResponse result = null;
		ResponseDTO responseDTO = null;
		String mensaje = "";
		Map<String, Object> respuestaLog = new HashMap<>();        
		
		if(nroExpedienteSiged != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nroExpedienteSiged).getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {

			PgimDocumento pgimDocumento = this.documentoRepository.findById(idDocumentoPGIM).orElse(null);
			PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
					.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

			result = this.documentoService.revertirFirmaArchivoSiged(idDocumentoPGIM, nroExpedienteSiged, idArchivoSiged, motivoSiged,
					pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

			mensaje = result.getArchivosRevertir().getArchivoRevertir().getMessage().replace("revirtio", "revirtió");

		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMensaje(), e);
			result = new RevertirFirmaResponse();
			result.setResultCode(new BigInteger(ConstantesUtil.PARAM_RESULTADO_FAIL));
			result.setMessage(e.getMensaje());

			responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		if (result.getArchivosRevertir().getArchivoRevertir() != null) {
			if (result.getArchivosRevertir().getArchivoRevertir().getResultCode()
					.equals(new BigInteger(ConstantesUtil.PARAM_RESULTADO_SUCCESS))) {
						
				responseDTO = new ResponseDTO(TipoResultado.SUCCESS, mensaje);
				
			} else {
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // PGIM
				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
			}
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
	}

			/**
			 * Permite anular un archivo de un documentos de un expediente
			 * @param pgimDocumentoDTOAux
			 * @return
			 * @throws Exception
			 */
			@PostMapping("/anularArchivoSiged")
	public ResponseEntity<ResponseDTO> anularArchivoSiged(@RequestBody PgimDocumentoDTO pgimDocumentoDTOAux) throws Exception {

		String nroExpedienteSiged = pgimDocumentoDTOAux.getNuExpedienteSiged();
		String idDocumentoSiged = pgimDocumentoDTOAux.getCoDocumentoSiged().toString();
		String idArchivoSiged = pgimDocumentoDTOAux.getIdArchivo().toString();
		String motivoSiged = pgimDocumentoDTOAux.getMotivoAnulacion();
		ResponseDTO responseDTO = null;
		String mensaje;
		Map<String, Object> respuestaLog = new HashMap<>();

		ArchivoAnularInRO archivoAnularInRO = new ArchivoAnularInRO();
		ArchivoAnularOutRO archivoAnularOutRO = null;

		if(nroExpedienteSiged != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nroExpedienteSiged).getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		// Obteniendo los datos de la auditoría:
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		try {
			archivoAnularInRO.setNroExpediente(nroExpedienteSiged);
			archivoAnularInRO.setIdDocumento(idDocumentoSiged);
			archivoAnularInRO.setIdArchivo(idArchivoSiged);
			archivoAnularInRO.setMotivo(motivoSiged);
			archivoAnularInRO.setIdUsuarioAnulacion(auditoriaDTO.getCoUsuarioSiged());

			PgimDocumentoDTO pgimDocumentoDTO = this.documentoRepository
					.obtenerDocumentoPgimByIdDocumentoSiged(Long.parseLong(idDocumentoSiged));
			PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository.findById(pgimDocumentoDTO.getIdFaseProceso())
					.orElse(null);

			archivoAnularOutRO = this.documentoService.anularArchivoSIGED(archivoAnularInRO,
					pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);
			mensaje = String.format("Ocurrió un error al anular el archivo",
					e.getMostSpecificCause().getMessage());

			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMensaje(), e);
			responseDTO = new ResponseDTO(e.getTipoResultado(), e.getMensaje());

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		if (archivoAnularOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "Se eliminó el archivo."); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
	}

	@DeleteMapping("/eliminarDocumentoPgim/{idDocumento}/{idInstanciaPaso}")
	public ResponseEntity<ResponseDTO> eliminarDocumentoPgim(@PathVariable Long idDocumento, @PathVariable Long idInstanciaPaso) throws Exception {
		Map<String, Object> respuestaLog = new HashMap<>();
		String mensaje;

		PgimDocumento pgimDocumentoActual = null;

		//- Se valida si es quye el documento se puede eliminar.
		ClaveValorUtil<Boolean, String> sePuedeEliminar = this.documentoService.sePuedeEliminar(idDocumento, idInstanciaPaso);

		if (!sePuedeEliminar.getClave()) {
			ResponseDTO responseDTO = new ResponseDTO(TipoResultado.WARNING, null, sePuedeEliminar.getValor());

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		//-

		if(idDocumento != null){
			Long idInstanciaProces = this.documentoService.obtenerDocumentoPgim(idDocumento).getPgimInstanciaProces().getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimDocumentoActual = this.documentoService.obtenerDocumentoPgim(idDocumento);

			if (pgimDocumentoActual == null) {
				mensaje = String.format("El documento que intenta eliminar no existe en la base de datos", idDocumento);
				
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" );

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje, 0));
			}
		} catch (DataAccessException e) {			
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar recuperar el documento a eliminar" +  e.getMostSpecificCause().getMessage() , 0));
		}

		try {
			this.documentoService.eliminarDocumentoPgim(pgimDocumentoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" );
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar eliminar el documento" +  e.getMostSpecificCause().getMessage() , 0));
		}

		ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, null, "El documento ha sido eliminado");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@PostMapping("/modificarDocumentoPgim")
	public ResponseEntity<ResponseDTO> modificarDocumentoPgim(@Valid @RequestBody PgimDocumentoDTO pgimDocumentoDTO,	
			BindingResult resultadoValidacion) throws Exception {

		PgimInstanciaProces pgimInstanciaProcesActual = null;
		PgimDocumento pgimDocumentoActual = null;
		PgimDocumentoDTO pgimDocumentoDTOModificado = null;
		String mensaje;
		Map<String, Object> respuestaLog = new HashMap<>();

		if (pgimDocumentoDTO.getIdInstanciaProceso() != null) {
			respuestaLog = this.flujoTrabajoService.mostrarLog(pgimDocumentoDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		} else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + errores.toString() + "]" ); // STORY:PGIM-7606 // PGIM

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Se han encontrado inconsistencias para modificar el documento" + errores.toString() ));
		}

		try {
			pgimInstanciaProcesActual = this.instanciaProcesService
					.obtenerInstanciaProceso(pgimDocumentoDTO.getIdInstanciaProceso());

			this.supervisionService.asegurarConsistenciaCambiosDocumento(pgimDocumentoDTO.getIdDocumento(), ETipoAccionCrud.MODIFICAR);

			if (pgimInstanciaProcesActual == null) {
				mensaje = String.format("El documento %s que intenta actualizar no existe en la base de datos",
						pgimDocumentoDTO.getIdInstanciaProceso());
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // PGIM

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje ));
			}
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar recuperar el documento a actualizar"+e.getMostSpecificCause().getMessage()));
		} catch (PgimException e) {		
			
			// Excepcion controlada que deberá ser manejada por el frontend
            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMensaje(), e);
            
            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null) {
                tipoResultado = TipoResultado.WARNING;
            } else {
                tipoResultado = e.getTipoResultado();
            }           

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			
		}

		try {
			pgimDocumentoActual = documentoService.obtenerDocumentoPgim(pgimDocumentoDTO.getIdDocumento());

			if (pgimDocumentoActual == null) {
				mensaje = String.format("El documento %s que intenta actualizar no existe en la base de datos",
						pgimDocumentoDTO.getIdDocumento());
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); // STORY:PGIM-7606 // PGIM
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensaje));
			}
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar recuperar el documento a actualizar: "+e.getMostSpecificCause().getMessage() ));
		}

		try {
			pgimDocumentoDTOModificado = this.documentoService.modificarDocumento(pgimDocumentoDTO,
					pgimInstanciaProcesActual, pgimDocumentoActual, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar modificar el documento: "+e.getMostSpecificCause().getMessage() ));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, pgimDocumentoDTOModificado, "El documento ha sido modificado")); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		
	}

	@GetMapping("/obtenerDocumentoPgimById/{idDocumento}")
	public ResponseEntity<?> obtenerDocumentoPgimById(@PathVariable Long idDocumento) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		PgimDocumentoDTO pgimDocumentoDTO = null;

		if(idDocumento != null){
			Long idInstanciaProces = this.documentoService.obtenerDocumentoPgim(idDocumento).getPgimInstanciaProces().getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimDocumentoDTO = this.documentoService.obtenerDocumentoPgimById(idDocumento);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del documento");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontró el documento");
		respuesta.put("pgimDocumentoDTO", pgimDocumentoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@GetMapping("/obtenerConfiguracionesDialogoByIdProceso/{idProceso}/{idFase}/{idInstanciaPaso}/{auxTipoAccionCrud}")
	public ResponseEntity<?> obtenerConfiguracionesDialogoByIdProceso(@PathVariable Long idProceso,
	@PathVariable Long idFase, @PathVariable Long idInstanciaPaso, @PathVariable Long auxTipoAccionCrud ) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimCategoriaDocDTO> lPgimCategoriaDocDTO = null;
		List<PgimSubcategoriaDocDTO> lPgimSubCategoriaDocDTO = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoOrigenDoc = null;
		List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = null;

		try {

			lPgimCategoriaDocDTO = categoriaDocService.listarCategoriaDocByIdProceso(idProceso);

			if (idFase != 0L && idInstanciaPaso != 0L) {

				PgimInstanciaProces pgimInstanciaProcess = this.instanciaProcesService.obtenerInstanciaProcesoPorInstanciaPaso(idInstanciaPaso);

				if (pgimInstanciaProcess == null) {
					throw new PgimException("error", "No se ha encontrado la instancia del proceso");
				}
				if(auxTipoAccionCrud == ConstantesUtil.INCLUIR){
					lPgimSubCategoriaDocDTO = this.categoriaDocService.listarSubCategoriasIncluirByProceso(idProceso, idFase);
				}else{
					lPgimSubCategoriaDocDTO = this.categoriaDocService.listarSubcategoriasFase(idProceso, idFase,
							pgimInstanciaProcess.getCoTablaInstancia(), idInstanciaPaso, false);

				}
	
				final List<PgimSubcategoriaDocDTO> flPgimSubCategoriaDocDTO = lPgimSubCategoriaDocDTO;
	
				lPgimCategoriaDocDTO = lPgimCategoriaDocDTO.stream().filter(pgimCategoriaDocDTO -> {
					Boolean encontrado = false;
					for (PgimSubcategoriaDocDTO pgimSubCategoriaDocDTO : flPgimSubCategoriaDocDTO) {
						if (pgimSubCategoriaDocDTO.getIdCategoriaDocumento()
								.equals(pgimCategoriaDocDTO.getIdCategoriaDocumento())) {
							encontrado = true;
							break;
						}
					}
	
					return encontrado;
				}).collect(Collectors.toList());	
	

			} else {
				lPgimSubCategoriaDocDTO = this.categoriaDocService.listarSubcategorias();
			}

			lPgimValorParamDTOTipoOrigenDoc = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ORIGEN_DOCUMENTO);
			lPgimFaseProcesoDTO = this.instanciaProcesService.obtenerFasesPorIdProceso(idProceso);
			

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al obtebner las configuraciones");
			respuesta.put("error", e.getMessage());
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimCategoriaDocDTO", lPgimCategoriaDocDTO);
		respuesta.put("lPgimSubCategoriaDocDTO", lPgimSubCategoriaDocDTO);
		respuesta.put("lPgimValorParamDTOTipoOrigenDoc", lPgimValorParamDTOTipoOrigenDoc);
		respuesta.put("lPgimFaseProcesoDTO", lPgimFaseProcesoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite listar las categorias de documentos por idProceso y idFaseProceso
	 * 
	 * @param idProceso
	 * @param idFaseProceso
	 * @return
	 */
	@GetMapping("/listarCategoriaDocByIdProcesoIdFase/{idProceso}/{idFase}")
	public ResponseEntity<List<PgimCategoriaDocDTO>> listarCategoriaDocByIdProcesoIdFase(@PathVariable Long idProceso,
			@PathVariable Long idFase) {

		List<PgimCategoriaDocDTO> lPgimCategoriaDocDTO = new ArrayList<>();
		lPgimCategoriaDocDTO = categoriaDocService.listarCategoriaDocByIdProcesoIdFase(idProceso, idFase);

		if (lPgimCategoriaDocDTO == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(lPgimCategoriaDocDTO);
	}

	@GetMapping("/obtenerConfiguracionesDialogoByIdProcesoIdFase/{idProceso}/{coTablaInstancia}/{idFaseFijada}/{idInstanciaPaso}")
	public ResponseEntity<?> obtenerConfiguracionesDialogoByIdProcesoIdFase(@PathVariable Long idProceso,
			@PathVariable Long coTablaInstancia, @PathVariable Long idFaseFijada, @PathVariable Long idInstanciaPaso) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimCategoriaDocDTO> lPgimCategoriaDocDTO = null;
		List<PgimSubcategoriaDocDTO> lPgimSubCategoriaDocDTO = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoOrigenDoc = null;
		List<PgimValorParametroDTO> lPgimValorParamDTOTipoSupervision = null;

		try {
			if (idFaseFijada == 0) {
				lPgimCategoriaDocDTO = this.categoriaDocService.listarCategoriaDocByIdProceso(idProceso);
				lPgimSubCategoriaDocDTO = this.categoriaDocService.listarSubcategorias();
			} else {
				lPgimCategoriaDocDTO = this.categoriaDocService.listarCategoriaDocByIdProcesoIdFase(idProceso,
						idFaseFijada);
				lPgimSubCategoriaDocDTO = this.categoriaDocService.listarSubcategoriasFase(idProceso, idFaseFijada,
						coTablaInstancia, idInstanciaPaso, true);

				final List<PgimSubcategoriaDocDTO> flPgimSubCategoriaDocDTO = lPgimSubCategoriaDocDTO;

				lPgimCategoriaDocDTO = lPgimCategoriaDocDTO.stream().filter(pgimCategoriaDocDTO -> {
					Boolean encontrado = false;
					for (PgimSubcategoriaDocDTO pgimSubCategoriaDocDTO : flPgimSubCategoriaDocDTO) {
						if (pgimSubCategoriaDocDTO.getIdCategoriaDocumento()
								.equals(pgimCategoriaDocDTO.getIdCategoriaDocumento())) {
							encontrado = true;
							break;
						}
					}

					return encontrado;
				}).collect(Collectors.toList());
			}

			lPgimValorParamDTOTipoOrigenDoc = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_ORIGEN_DOCUMENTO);

			lPgimValorParamDTOTipoSupervision = this.parametroService
					.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SUPERVISION);

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimCategoriaDocDTO", lPgimCategoriaDocDTO);
		respuesta.put("lPgimSubCategoriaDocDTO", lPgimSubCategoriaDocDTO);
		respuesta.put("lPgimValorParamDTOTipoOrigenDoc", lPgimValorParamDTOTipoOrigenDoc);
		respuesta.put("lPgimValorParamDTOTipoSupervision", lPgimValorParamDTOTipoSupervision);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite reenviar un expediente a otro usuario del SIGED
	 *
	 * @param numeroExpediente Número de expediente a ser reenviado.
	 * @param remitente        código del usuario al cual se hara el reenvío.
	 * @param destinatario     código del usuario al cual se hara el reenvío.
	 * @param asunto           Texto del asunto para el reenvio.
	 * @param contenido        Texto del contenido del reenvio.
	 * @param flagAprobacion   1: reenviar para aprobar / 0: reenviar simple
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/reenviarExpedienteSiged/{numeroExpediente}/{remitente}/{destinatario}/{asunto}/{contenido}/{flagAprobacion}")
	public ResponseEntity<?> reenviarExpedienteSiged(@PathVariable String numeroExpediente,
			@PathVariable Long remitente, @PathVariable Long destinatario, @PathVariable String asunto,
			@PathVariable String contenido, @PathVariable String flagAprobacion, AuditoriaDTO auditoriaDTO)
			throws PgimException, Exception {

		Map<String, Object> respuesta = new HashMap<>();
		ExpedienteReenvio expedienteReenvio = new ExpedienteReenvio();
		ExpedienteOutRO expedienteOutRO = null;

		try {
			expedienteReenvio.setNumeroExpediente(numeroExpediente);
			expedienteReenvio.setIdDestinatario(destinatario);
			expedienteReenvio.setIdRemitente(remitente);
			expedienteReenvio.setAsunto(asunto);
			expedienteReenvio.setContenido(contenido);
			expedienteReenvio.setAprobacion(flagAprobacion);
			expedienteOutRO = this.documentoService.reenviarExpedienteSiged(expedienteReenvio, auditoriaDTO);
		} catch (PgimException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar el reenvío del expediente");
			respuesta.put("error", e.getMessage());
			log.error(e.getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			throw new PgimException("error", expedienteOutRO.getMessage());
		}

		respuesta.put("expedienteOutRO", expedienteOutRO);

		if (expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS)) {
			respuesta.put("mensaje", expedienteOutRO.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			respuesta.put("mensaje", "El expediente fue reenviado.");
		}
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('sp-ar-plaz_AC')")
	@GetMapping("/listarPlazos/{idInstanciaProceso}")
	public ResponseEntity<?> listarPlazos(@PathVariable Long idInstanciaProceso) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		List<PgimDocumentoDTO> lPgimDocumentoDTO = null;
		PgimDocumentoDTO pgimDocumentoDTOFCIS = null;
		PgimDocumentoDTO pgimDocumentoDTOAA = null;
		PgimDocumentoDTO pgimDocumentoDTOIAIP = null;
		PgimDocumentoDTO pgimDocumentoDTOIAIPII = null;

		if(idInstanciaProceso != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			// Ficha de conformidad de informe de fiscalización (anexo 7)
			pgimDocumentoDTOFCIS = documentoService.obtenerAprobacionResultadoPlazo(idInstanciaProceso,
					ConstantesUtil.PARAM_SC_FICHA_CONFORMIDAD_INFORME_FISCALIZACION,
					ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD);
			// Autorización de ampliación (anexo 11 y 12)
			pgimDocumentoDTOAA = documentoService.obtenerAprobacionResultadoPlazo(idInstanciaProceso,
					ConstantesUtil.PARAM_SC_RESPUESTA_SOLICITUD_AMPLIACIÓN_PERIODO_SUPERVISION,
					ConstantesUtil.PARAM_RP_APROBAR_SOLICITUD_AMPLIACIÓN_PLAZO_CONF_HECHOS_CONSTATADOS_INFRACCIONES);
			// Informe de archivado de instrucción preliminar (IAIP)
			pgimDocumentoDTOIAIP = documentoService.obtenerAprobacionResultadoPlazo(idInstanciaProceso,
					ConstantesUtil.PARAM_SC_INFORME_ARCHIVADO_INSTRUCCIÓN_PRELIMINAR,
					ConstantesUtil.PARAM_RP_CONTINUAR_ARCHIVADO_APROBAR_IAIP);
			// Informe de archivado de instrucción preliminar (IAIP)
			pgimDocumentoDTOIAIPII = documentoService.obtenerAprobacionResultadoPlazo(idInstanciaProceso,
					ConstantesUtil.PARAM_SC_INFORME_INSTRUCCION_INICIO_PAS,
					ConstantesUtil.PARAM_RP_CONTINUAR_PAS_COMPLETAR_SUPERVISIÓN_INICIO_PAS);

			lPgimDocumentoDTO = new LinkedList<PgimDocumentoDTO>();
			if (pgimDocumentoDTOFCIS != null) {
				lPgimDocumentoDTO.add(pgimDocumentoDTOFCIS);
			}

			if (pgimDocumentoDTOAA != null) {
				lPgimDocumentoDTO.add(pgimDocumentoDTOAA);
			}

			if (pgimDocumentoDTOIAIP != null) {
				lPgimDocumentoDTO.add(pgimDocumentoDTOIAIP);
			}

			if (pgimDocumentoDTOIAIPII != null) {
				lPgimDocumentoDTO.add(pgimDocumentoDTOIAIPII);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron documentos");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite reemplazar un documento de un expediente. toma el archivo desde el
	 * mismo servidor
	 * 
	 * @param nroExpedienteSiged Número del expediente Siged.
	 * @param idDocumentoSiged   Identificador interno del documento Siged.
	 * @param motivoSiged        Motivo de la eliminación.
	 * @param idDocumentoPGIM    Identificador interno del documento de la PGIM.
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/reemplazarDocumentoSiged/{nroExpedienteSiged}/{idDocumentoSiged}/{idDocumentoPGIM}/{motivoSiged}")
	public ResponseEntity<?> reemplazarDocumentoSiged(@PathVariable String nroExpedienteSiged,
			@PathVariable String idDocumentoSiged, @PathVariable String motivoSiged, @PathVariable Long idDocumentoPGIM)
			throws Exception {
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		ResponseEntity<ResponseDTO> re = null;
        Map<String, Object> respuestaLog = new HashMap<>();

		if(nroExpedienteSiged != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nroExpedienteSiged).getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			DocumentoNuevo documentoNuevo = new DocumentoNuevo(nroExpedienteSiged, "0", "", "", "",
					auditoriaDTO.getCoUsuarioSiged());
			PgimDocumento pgimDocumento = this.documentoRepository.findById(idDocumentoPGIM).orElse(null);
			PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
					.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

			// Validación del propietario del expediente
			ExpedienteSiged expedienteSiged = new ExpedienteSiged();
			expedienteSiged.setIdPropietario(Long.parseLong(auditoriaDTO.getCoUsuarioSiged()));
			expedienteSiged.setNumeroExpediente(nroExpedienteSiged);

			ExpedienteDocOutRO elementosExpediente = this.documentoService.validarUsuarioPropietarioExpedienteSiged(
					expedienteSiged, pgimFaseProceso.getPgimProceso().getIdProceso(), "Reemplazar documento",
					auditoriaDTO);

			if (!elementosExpediente.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + elementosExpediente.getMessage() + "]" ); // STORY:PGIM-7606 // PGIM
				throw new PgimException("error", elementosExpediente.getMessage());
			}

			re = this.documentoService.procesarReemplazoDocumentoSiged(documentoNuevo, idDocumentoSiged,
					pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO, elementosExpediente);
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseDTO("error", e.getMensaje(), 0));
		}
		return re;
	}

	public byte[] generarDocumentoActaSupervisionPlantilla(String ruta, PgimSupervisionDTO pgimSupervisionDTO,
			Long idTipoExtensionGen)
			throws Exception {

		// fecha actual del sistema
		java.util.Date fechaActual = new Date();
		SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
		String fechaDeGeneracion = sdfg.format(fechaActual);

		String prefijo_nombre = "";

		PgimSupervisionDTO valoresActaSupervision = supervisionService
				.obtenerValoresActaSupervision(pgimSupervisionDTO.getIdSupervision());
		List<PgimUbigeoDTO> listaUbigeos = ubigeoService
				.obtenerUbigeoPorIdSupervision(pgimSupervisionDTO.getIdSupervision());
		Set<String> setDistrito = new LinkedHashSet<>();
		Set<String> setProvincia = new LinkedHashSet<>();
		Set<String> setDepartamento = new LinkedHashSet<>();
		for (PgimUbigeoDTO ubigeo : listaUbigeos) {
			setDistrito.add(ubigeo.getDescDistrito());
			setProvincia.add(ubigeo.getDescProvincia());
			setDepartamento.add(ubigeo.getDescDepartamento());
		}

		String departamentos = CommonsUtil.setListToString(setDepartamento);
		String provincias = CommonsUtil.setListToString(setProvincia);
		String distritos = CommonsUtil.setListToString(setDistrito);

		// String unidadMinera_PlantaBeneficio = valoresActaSupervision.getDescNoUnidadMinera();
		// if (!valoresActaSupervision.getDescPlntaBeneficioDestinoNoUnidadMinera().equals(" ")) {
		// 	unidadMinera_PlantaBeneficio += "\n" + valoresActaSupervision.getDescPlntaBeneficioDestinoNoUnidadMinera();
		// }

		List<PgimInvolucradoSupervDTO> listaTrabajadores = involucradoSupervService
				.obtenerRepresentantesTrabajadores(pgimSupervisionDTO.getIdSupervision(), 318L);

		List<PgimInvolucradoSupervDTO> listaAgenteSupervisado = involucradoSupervService
				.obtenerRepresentantesAgenteSupervisado(pgimSupervisionDTO.getIdSupervision(), 318L);

		List<PgimSupervisionDTO> listaSupervisores = new LinkedList<PgimSupervisionDTO>();

		List<PgimEqpInstanciaProDTO> listaSupervisoresOsi = eqpInstanciaProService
				.obtenerPersonalXRolOsi(pgimSupervisionDTO.getIdInstanciaProceso(), 4L);

		for (PgimEqpInstanciaProDTO supeOsi : listaSupervisoresOsi) {

			if (supeOsi.getDescNoPersona() != null) {
				if (supeOsi.getNoPrefijoPersonaEquipo() != null) {
					prefijo_nombre = supeOsi.getNoPrefijoPersonaEquipo() + " " + supeOsi.getDescNoPersona();
				} else {
					prefijo_nombre = supeOsi.getDescNoPersona();
				}
			} else {
				prefijo_nombre = supeOsi.getDescNoPersona();
			}

			PgimSupervisionDTO obj = new PgimSupervisionDTO();
			obj.setDescNoPersona(prefijo_nombre);
			obj.setDescApPaterno(supeOsi.getDescApPaterno());
			obj.setDescApMaterno(supeOsi.getDescApMaterno());
			obj.setDescCoDocumentoIdentidad(supeOsi.getDescCoDocumentoIdentidad());
			obj.setDescDeCargo(supeOsi.getNoCargoPersonaEquipo()); // STORY: PGIM-6166: Gen. acta de fiscalización con fecha generación, cargo y prefijo de firmantes
			listaSupervisores.add(obj);
		}

		List<PgimEqpInstanciaProDTO> listaSupervisoresContrato = eqpInstanciaProService
				.obtenerPersonalXRolContrato(pgimSupervisionDTO.getIdInstanciaProceso(), 4L);
		for (PgimEqpInstanciaProDTO supeContr : listaSupervisoresContrato) {

			if (supeContr.getDescNoPersona() != null) {
				if (supeContr.getNoPrefijoPersonaEquipo() != null) {
					prefijo_nombre = supeContr.getNoPrefijoPersonaEquipo() + " " + supeContr.getDescNoPersona();
				} else {
					prefijo_nombre = supeContr.getDescNoPersona();
				}
			} else {
				prefijo_nombre = supeContr.getDescNoPersona();
			}

			PgimSupervisionDTO obj = new PgimSupervisionDTO();
			obj.setDescNoPersona(prefijo_nombre);
			obj.setDescApPaterno(supeContr.getDescApPaterno());
			obj.setDescApMaterno(supeContr.getDescApMaterno());
			obj.setDescCoDocumentoIdentidad(supeContr.getDescCoDocumentoIdentidad());
			obj.setDescDeCargo(supeContr.getNoCargoPersonaEquipo()); // STORY: PGIM-6166: Gen. acta de fiscalización con fecha generación, cargo y prefijo de firmantes
			listaSupervisores.add(obj);
		}

		List<PgimHechoConstatadoDTO> listaHechosConstatadosNoCumplimiento = this.hechoConstatadoService.listarHechosConstatadosConNoCumplimientos(
				pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);

		File plantilla = new File(ruta);

		Path rutaPlantilla = Paths.get(plantilla.getPath());
		XWPFDocument documento = new XWPFDocument(Files.newInputStream(rutaPlantilla));

		JSONArray list = new JSONArray();

		// STORY: PGIM-6166: Gen. acta de fiscalización con fecha generación, cargo y prefijo de firmantes
		JSONObject fechaGeneracionDocumento = new JSONObject();
		fechaGeneracionDocumento.put("mergeField", "fecha_generacion_documento");
		fechaGeneracionDocumento.put("value", fechaDeGeneracion);
		list.put(fechaGeneracionDocumento);

		// cod fiscalización
		JSONObject cod_fiscalizacion = new JSONObject();
		cod_fiscalizacion.put("mergeField", "cod_fiscalizacion");
		cod_fiscalizacion.put("value", pgimSupervisionDTO.getCoSupervision().toUpperCase());
		list.put(cod_fiscalizacion);

		JSONObject nro_expediente = new JSONObject();
		nro_expediente.put("mergeField", "nro_expediente");
		nro_expediente.put("value", valoresActaSupervision.getDescNuExpedienteSiged());
		list.put(nro_expediente);

		String nombreRepresentante = "", dniRepresentante = "";
		for (PgimInvolucradoSupervDTO tra : listaTrabajadores) {

			// STORY: PGIM-6166: Gen. acta de fiscalización con fecha generación, cargo y prefijo de firmantes
			nombreRepresentante = tra.getDescIdTipoPrefijoInvolucrado() + " " + tra.getDescNoPersona() + " " + tra.getDescApPaterno() + " " + tra.getDescApMaterno();
			dniRepresentante = tra.getDescCoDocumentoIdentidad();
		}
		JSONObject nombre_representante = new JSONObject();
		nombre_representante.put("mergeField", "nombre_representante");
		nombre_representante.put("value", nombreRepresentante);
		list.put(nombre_representante);

		JSONObject dni_representante = new JSONObject();
		dni_representante.put("mergeField", "dni_representante");
		dni_representante.put("value", dniRepresentante);
		list.put(dni_representante);

		PgimUnidadMineraDTO pgimUnidadMineraDTO = unidadMineraService
				.obtenerUnidadMinera(pgimSupervisionDTO.getIdUnidadMinera());

		documento = PoiWordUtil.replaceJSONArray(documento, list);		

		List<XWPFParagraph> paragraphs = documento.getParagraphs();
		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_supervision")) {
					paragraph.removeRun(j);

					XWPFTable tableSupervision = PoiWordUtil.createTableInSpecificPosition(paragraph, documento, 9000);

					XWPFTableRow tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 0, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Agente fiscalizado", 8, true);
					tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowOne,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */4329, 0, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							valoresActaSupervision.getDescAgenteSupervisadoNoRazonSocial(), 8, false);
					tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowOne,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */941, 0, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Fecha", 8, true);
					tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowOne,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 0, 3, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							valoresActaSupervision.getFeFinSupervisionRealDesc(), 8, false);

					XWPFTableRow tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 1, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Unidad fiscalizada", 8, true);
					tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowTwo, XWPFVertAlign.CENTER, 200,
							"FFFFFF", /* TableWidthType.PCT, */4329, 1, 1, 0, ParagraphAlignment.LEFT,
							LineSpacingRule.EXACT, 0, 0, pgimUnidadMineraDTO.getCoUnidadMinera()+" - "+valoresActaSupervision.getDescNoUnidadMinera(),
							8, false);
					tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowTwo,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */941, 1, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Hora", 8, true);
					tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowTwo,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 1, 3, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							valoresActaSupervision.getHoraFinSupervisionRealDesc(), 8, false);
					
					XWPFTableRow tableSupervisionRowThreeTipo = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 2, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Tipo", 8, true);
					tableSupervisionRowThreeTipo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThreeTipo, XWPFVertAlign.CENTER, 200,
							"FFFFFF", /* TableWidthType.PCT, */4329, 2, 1, 0, ParagraphAlignment.LEFT,
							LineSpacingRule.EXACT, 0, 0, pgimUnidadMineraDTO.getDescIdTipoUnidadMinera(),
							8, false);
					tableSupervisionRowThreeTipo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThreeTipo,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */941, 2, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "", 8, true);
					tableSupervisionRowThreeTipo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThreeTipo,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 2, 3, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,"", 8, false);
					PoiWordUtil.mergeCellVertically(tableSupervision, 2, 1, 2);
					PoiWordUtil.mergeCellVertically(tableSupervision, 3, 1, 2);

					XWPFTableRow tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 3, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Fecha inicio y fin de la fiscalización",
							8, true);
					tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThree,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */3294, 3, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Inicio: " + valoresActaSupervision.getFeInicioSupervisionRealDesc(), 8, false);
					PoiWordUtil.setColumnWidth(tableSupervision, 3, 1, 500);
					tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThree,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */3293, 3, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Fin: " + valoresActaSupervision.getFeFinSupervisionRealDesc(), 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 3, 2, 3);
					PoiWordUtil.setColumnWidth(tableSupervision, 3, 2, 500);

					XWPFTableRow tableSupervisionRowFour = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 4, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Distrito", 8, true);
					tableSupervisionRowFour = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowFour,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */6587, 4, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, distritos, 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 4, 1, 3);

					XWPFTableRow tableSupervisionRowFive = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 5, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Provincia", 8, true);
					tableSupervisionRowFive = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowFive,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */6587, 5, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, provincias, 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 5, 1, 3);

					XWPFTableRow tableSupervisionRowSix = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 6, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Departamento", 8, true);
					tableSupervisionRowSix = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowSix,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */6587, 6, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, departamentos, 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 6, 1, 3);

					XWPFTableRow tableSupervisionRowSeven = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 7, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Representante(s) del agente fiscalizado ", 8, true);
					tableSupervisionRowSeven = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowSeven,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, 7, 1, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Nombre (s) y apellido(s)", 8,
							true);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 7, 1, 2);
					tableSupervisionRowSeven = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowSeven,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 7, 2, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "DNI", 8, true);

					int cant = 7;
					if (listaAgenteSupervisado != null && listaAgenteSupervisado.size() > 0) {
						for (int k = 0; k < listaAgenteSupervisado.size(); k++) {
							cant++;
							XWPFTableRow tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cant, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */5270, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaAgenteSupervisado.get(k).getDescIdTipoPrefijoInvolucrado() + " " +
									listaAgenteSupervisado.get(k).getDescNoPersona() + " "
											+ listaAgenteSupervisado.get(k).getDescApPaterno() + " "
											+ listaAgenteSupervisado.get(k).getDescApMaterno(),
									8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cant, 1, 2);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, cant, 2, 0,
									ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0,
									listaAgenteSupervisado.get(k).getDescCoDocumentoIdentidad(), 8, false);
						}
					} else {
						for (int k = 0; k < 3; k++) {
							cant++;
							XWPFTableRow tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cant, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cant, 1, 2);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */1317, cant, 2, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, false);
						}
					}

					PoiWordUtil.mergeCellVertically(tableSupervision, 0, 7, cant);

					int cantTwo = cant + 1;
					XWPFTableRow tableSupervisionRowEight = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cantTwo, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Fiscalizador(es)", 8, true);
					tableSupervisionRowEight = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowEight,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, cantTwo, 1, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Nombre (s) y apellido(s)", 8,
							true);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, cantTwo, 1, 2);
					tableSupervisionRowEight = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowEight,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */1317, cantTwo, 2, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "DNI", 8, true);

					int cantThree = cantTwo;
					if (listaSupervisores != null && listaSupervisores.size() > 0) {
						for (int k = 0; k < listaSupervisores.size(); k++) {
							cantThree++;
							XWPFTableRow tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cantThree, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "", 8, true);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.CENTER, 200, "FFFFFF",
									/* TableWidthType.PCT, */5270, cantThree, 1, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0,
									listaSupervisores.get(k).getDescNoPersona() + " "
											+ listaSupervisores.get(k).getDescApPaterno() + " "
											+ listaSupervisores.get(k).getDescApMaterno(),
									8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cantThree, 1, 2);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.CENTER, 200, "FFFFFF",
									/* TableWidthType.PCT, */1317, cantThree, 2, 0, ParagraphAlignment.CENTER,
									LineSpacingRule.EXACT, 0, 0, listaSupervisores.get(k).getDescCoDocumentoIdentidad(),
									8, false);
						}
					} else {
						for (int k = 0; k < 3; k++) {
							cantThree++;
							XWPFTableRow tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cantThree, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "", 8, true);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.BOTTOM, 200, "FFFFFF",
									/* TableWidthType.PCT, */5270, cantThree, 1, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0, " ", 8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cantThree, 1, 2);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.BOTTOM, 200, "FFFFFF",
									/* TableWidthType.PCT, */1317, cantThree, 2, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0, " ", 8, false);
						}
					}

					PoiWordUtil.mergeCellVertically(tableSupervision, 0, cantTwo, cantThree);
				}
			}
		}

		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_hechos_constatados")) {
					paragraph.removeRun(j);

					XWPFTable tableHechosConstatados = PoiWordUtil.createTableInSpecificPosition(paragraph, documento, 9000);

					XWPFTableRow tableHechosConstatadosRowOne = PoiWordUtil.createTableCell(tableHechosConstatados,
							null, XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 0, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "N°", 8, true);

					tableHechosConstatadosRowOne = PoiWordUtil.createTableCell(tableHechosConstatados,
							tableHechosConstatadosRowOne, XWPFVertAlign.BOTTOM, 200, "D9D9D9",
							/* TableWidthType.PCT, */7999, 0, 1, 0, ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0,
							0, "HECHOS VERIFICADOS", 8, true);

					int cant = 0;
					if (listaHechosConstatadosNoCumplimiento != null && listaHechosConstatadosNoCumplimiento.size() > 0) {
						for (int k = 0; k < listaHechosConstatadosNoCumplimiento.size(); k++) {

							cant++;
							XWPFTableRow tableHechosConstatadosRow = PoiWordUtil.createTableCell(
									tableHechosConstatados, null, XWPFVertAlign.CENTER, 200, "FFFFFF",
									/* TableWidthType.PCT, */1411, cant, 0, 0, ParagraphAlignment.CENTER,
									LineSpacingRule.EXACT, 0, 0, String.valueOf(cant), 9, false);

							tableHechosConstatadosRow = PoiWordUtil.createTableCellHtml(tableHechosConstatados, tableHechosConstatadosRow,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", 7999, cant, 1,
									ConstantesUtil.ALINEAR_LEFT, EscritorHtml.replaceLineBreakToPHtml(listaHechosConstatadosNoCumplimiento.get(k).getDeHechoConstatadoT()), 9, documento, "chunk9"+k);
						}
					} else {
						for (int k = 0; k < 1; k++) {
							cant++;
							XWPFTableRow tableHechosConstatadosRow = PoiWordUtil.createTableCell(tableHechosConstatados,
									null, XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 0,
									0, ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, " ", 8, false);

							tableHechosConstatadosRow = PoiWordUtil.createTableCell(tableHechosConstatados,
									tableHechosConstatadosRow, XWPFVertAlign.BOTTOM, 200, "FFFFFF",
									/* TableWidthType.PCT, */7999, cant, 1, 0, ParagraphAlignment.CENTER,
									LineSpacingRule.EXACT, 0, 0, "No se han registrado hechos verificados", 8, false);

						}
					}
				}
			}
		}

		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_observaciones_adicionales")) {
					paragraph.removeRun(j);

					XWPFTable tableObservacionesAdicionales = PoiWordUtil.createTableInSpecificPosition(paragraph, documento, 9000);

					XWPFTableRow tableObservacionesAdicionalesRowOne = PoiWordUtil.createTableCell(
							tableObservacionesAdicionales, null, XWPFVertAlign.CENTER, 200, "FFFFFF",
							/* TableWidthType.PCT, */9410, 0, 0, 0, ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0,
							0, "OBSERVACIONES ADICIONALES:", 8, true);
					
					PoiWordUtil.createTableCellHtml(tableObservacionesAdicionales, tableObservacionesAdicionalesRowOne,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", 9410, 1, 0,
							ConstantesUtil.ALINEAR_LEFT, EscritorHtml.replaceLineBreakToPHtml(valoresActaSupervision.getDeObservacionFinSuperT()), 8, documento, "chunk1"+j);
				}
			}
		}

		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_firmantes")) {
					paragraph.removeRun(j);

					XWPFTable tableFirmantes = PoiWordUtil.createTableInSpecificPosition(paragraph, documento, 9000);

					XWPFTableRow tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, null,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 0, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Documento de identidad", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 1, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Cargo", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */3200, 0, 2, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Nombre y apellidos", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1977, 0, 3, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Firma", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 4, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Entidad", 8, true);

					tableFirmantesRowOne.setRepeatHeader(true);

					int cant = 0;
					if ((listaAgenteSupervisado != null && listaAgenteSupervisado.size() > 0)
							|| (listaSupervisores != null && listaSupervisores.size() > 0)) {
						List<PgimSupervisionDTO> listaFirmantes = new LinkedList<PgimSupervisionDTO>();

						for (PgimSupervisionDTO supervisores : listaSupervisores) {
							supervisores.setDescEntidad("Supervisora");
							listaFirmantes.add(supervisores);
						}

						for (PgimInvolucradoSupervDTO agente : listaAgenteSupervisado) {
							PgimSupervisionDTO obj = new PgimSupervisionDTO();
							obj.setDescNoPersona(agente.getDescIdTipoPrefijoInvolucrado() + " " + agente.getDescNoPersona()); // STORY: PGIM-6166: Gen. acta de fiscalización con fecha generación, cargo y prefijo de firmantes
							obj.setDescApPaterno(agente.getDescApPaterno());
							obj.setDescApMaterno(agente.getDescApMaterno());
							obj.setDescCoDocumentoIdentidad(agente.getDescCoDocumentoIdentidad());
							obj.setDescEntidad("Titular minero");
							obj.setDescDeCargo(agente.getDeCargo());
							listaFirmantes.add(obj);
						}

						for (PgimInvolucradoSupervDTO tra : listaTrabajadores) {
							PgimSupervisionDTO obj = new PgimSupervisionDTO();
							obj.setDescNoPersona(tra.getDescIdTipoPrefijoInvolucrado() + " " + tra.getDescNoPersona()); // STORY: PGIM-6166: Gen. acta de fiscalización con fecha generación, cargo y prefijo de firmantes
							obj.setDescApPaterno(tra.getDescApPaterno());
							obj.setDescApMaterno(tra.getDescApMaterno());
							obj.setDescCoDocumentoIdentidad(tra.getDescCoDocumentoIdentidad());
							obj.setDescEntidad("Comité de Seguridad y Salud Ocupacional");
							obj.setDescDeCargo(tra.getDeCargo());
							listaFirmantes.add(obj);
						}

						for (int k = 0; k < listaFirmantes.size(); k++) {
							cant++;
							XWPFTableRow tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, null,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 0, 0,
									ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescCoDocumentoIdentidad(), 8, false);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescDeCargo(), 8, false);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */3200, cant, 2, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescNoPersona() + " "
											+ listaFirmantes.get(k).getDescApPaterno() + " "
											+ listaFirmantes.get(k).getDescApMaterno(),
									8, false);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1977, cant, 3, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, false);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 4, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescEntidad(), 8, false);
						}
					} else {
						for (int k = 0; k < 5; k++) {
							cant++;
							XWPFTableRow tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, null,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */3200, cant, 2, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1977, cant, 3, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 4, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
						}
					}
				}
			}
		}

		///////////////////////////////////////////////////////
		// REVIEW: Uniformidad de la fecha generada por la PGIM 
		// y el numero de paginas en el pie de paginas
		///////////////////////////////////////////////////////
		
		this.documentoService.getFooterAlignBottomDocs(documento, fechaDeGeneracion, "VERTICAL", 0, 8000); // PLANTILLA_ACTA_FISCALIZACION.docx

		///////////////////////////////////////////////////////
		// REVIEW: Fin
		///////////////////////////////////////////////////////

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		documento.write(baos);
		// documento.close();

		byte[] archivo = baos.toByteArray();

		try {
			archivo = PoiWordUtil.doc2pdf(archivo, idTipoExtensionGen, this.propertiesConfig.getLicenciaAspose());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PgimException(TipoResultado.ERROR, "DOC_GEN" + e.getMessage());
		}

		return archivo;
	}

	public byte[] generarDocumentoActaInicioSupervisionPlantilla(String ruta, PgimSupervisionDTO pgimSupervisionDTO,
			Long idTipoExtensionGen) throws Exception {

		// fecha actual del sistema
		java.util.Date fechaActual = new Date();
		SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
		String fechaDeGeneracion = sdfg.format(fechaActual);

		String prefijo_nombre = "";

		PgimSupervisionDTO valoresActaSupervision = supervisionService
				.obtenerValoresActaInicioSupervision(pgimSupervisionDTO.getIdSupervision());
		List<PgimUbigeoDTO> listaUbigeos = ubigeoService
				.obtenerUbigeoPorIdSupervision(pgimSupervisionDTO.getIdSupervision());
		Set<String> setDistrito = new LinkedHashSet<>();
		Set<String> setProvincia = new LinkedHashSet<>();
		Set<String> setDepartamento = new LinkedHashSet<>();
		for (PgimUbigeoDTO ubigeo : listaUbigeos) {
			setDistrito.add(ubigeo.getDescDistrito());
			setProvincia.add(ubigeo.getDescProvincia());
			setDepartamento.add(ubigeo.getDescDepartamento());
		}

		String departamentos = CommonsUtil.setListToString(setDepartamento);
		String provincias = CommonsUtil.setListToString(setProvincia);
		String distritos = CommonsUtil.setListToString(setDistrito);

		// String unidadMinera_PlantaBeneficio = valoresActaSupervision.getDescNoUnidadMinera();
		// if (!valoresActaSupervision.getDescPlntaBeneficioDestinoNoUnidadMinera().equals(" ")) {
		// 	unidadMinera_PlantaBeneficio += "\n" + valoresActaSupervision.getDescPlntaBeneficioDestinoNoUnidadMinera();
		// }

		List<PgimInvolucradoSupervDTO> listaTrabajadores = involucradoSupervService
				.obtenerRepresentantesTrabajadores(pgimSupervisionDTO.getIdSupervision(), 317L);
		List<PgimInvolucradoSupervDTO> listaAgenteSupervisado = involucradoSupervService
				.obtenerRepresentantesAgenteSupervisado(pgimSupervisionDTO.getIdSupervision(), 317L);
		List<PgimSupervisionDTO> listaSupervisores = new LinkedList<PgimSupervisionDTO>();
		List<PgimEqpInstanciaProDTO> listaSupervisoresOsi = eqpInstanciaProService
				.obtenerPersonalXRolOsi(pgimSupervisionDTO.getIdInstanciaProceso(), 4L);
		for (PgimEqpInstanciaProDTO supeOsi : listaSupervisoresOsi) {

			// STORY: PGIM-6160: Gen. acta de inicio de fiscalización con fecha generación, cargo y prefijo de firmantes
			if (supeOsi != null) {
				if (supeOsi.getDescNoPersona() != null) {
					if (supeOsi.getNoPrefijoPersonaEquipo() != null) {
						prefijo_nombre = supeOsi.getNoPrefijoPersonaEquipo() + " " + supeOsi.getDescNoPersona().toUpperCase();
					} else {
						prefijo_nombre = supeOsi.getDescNoPersona().toUpperCase();
					}
				} else {
					prefijo_nombre = supeOsi.getDescNoPersona().toUpperCase();
				}
			}

			PgimSupervisionDTO obj = new PgimSupervisionDTO();
			obj.setDescNoPersona(prefijo_nombre);
			obj.setDescApPaterno(supeOsi.getDescApPaterno().toUpperCase());
			obj.setDescApMaterno(supeOsi.getDescApMaterno().toUpperCase());
			obj.setDescCoDocumentoIdentidad(supeOsi.getDescCoDocumentoIdentidad());
			obj.setDescDeCargo(supeOsi.getNoCargoPersonaEquipo()); // STORY: PGIM-6160: Gen. acta de inicio de fiscalización con fecha generación, cargo y prefijo de firmantes
			obj.setDescNoRazonSocialEmpSupervisora("Osinergmin");
			listaSupervisores.add(obj);
		}

		List<PgimEqpInstanciaProDTO> listaSupervisoresContrato = eqpInstanciaProService
				.obtenerPersonalXRolContrato(pgimSupervisionDTO.getIdInstanciaProceso(), 4L);
		for (PgimEqpInstanciaProDTO supeContr : listaSupervisoresContrato) {

			// STORY: PGIM-6160: Gen. acta de inicio de fiscalización con fecha generación, cargo y prefijo de firmantes
			if (supeContr != null) {
				if (supeContr.getDescNoPersona() != null) {
					if (supeContr.getNoPrefijoPersonaEquipo() != null) {
						prefijo_nombre = supeContr.getNoPrefijoPersonaEquipo() + " " + supeContr.getDescNoPersona().toUpperCase();
					} else {
						prefijo_nombre = supeContr.getDescNoPersona().toUpperCase();
					}
				} else {
					prefijo_nombre = supeContr.getDescNoPersona().toUpperCase();
				}
			}

			PgimSupervisionDTO obj = new PgimSupervisionDTO();
			obj.setDescNoPersona(prefijo_nombre);
			obj.setDescApPaterno(supeContr.getDescApPaterno().toUpperCase());
			obj.setDescApMaterno(supeContr.getDescApMaterno().toUpperCase());
			obj.setDescCoDocumentoIdentidad(supeContr.getDescCoDocumentoIdentidad());
			obj.setDescDeCargo(supeContr.getNoCargoPersonaEquipo()); // STORY: PGIM-6160: Gen. acta de inicio de fiscalización con fecha generación, cargo y prefijo de firmantes
			obj.setDescNoRazonSocialEmpSupervisora("Supervisora");
			listaSupervisores.add(obj);
		}

		File plantilla = new File(ruta);

		Path rutaPlantilla = Paths.get(plantilla.getPath());
		XWPFDocument documento = new XWPFDocument(Files.newInputStream(rutaPlantilla));

		JSONArray list = new JSONArray();

		// STORY: PGIM-6160: Gen. acta de inicio de fiscalización con fecha generación, cargo y prefijo de firmantes
		JSONObject fechaDeGeneracionDocumento = new JSONObject();
		fechaDeGeneracionDocumento.put("mergeField", "fecha_generacion_documento");
		fechaDeGeneracionDocumento.put("value", fechaDeGeneracion);
		list.put(fechaDeGeneracionDocumento);
		
		// cod fiscalización
		JSONObject cod_fiscalizacion = new JSONObject();
		cod_fiscalizacion.put("mergeField", "cod_fiscalizacion");
		cod_fiscalizacion.put("value", pgimSupervisionDTO.getCoSupervision().toUpperCase());
		list.put(cod_fiscalizacion);

		JSONObject nro_expediente = new JSONObject();
		nro_expediente.put("mergeField", "nro_expediente");
		nro_expediente.put("value", valoresActaSupervision.getDescNuExpedienteSiged());
		list.put(nro_expediente);

		String nombreRepresentante = "", dniRepresentante = "";
		for (PgimInvolucradoSupervDTO tra : listaTrabajadores) {
			nombreRepresentante = tra.getDescNoPersona() + " " + tra.getDescApPaterno() + " " + tra.getDescApMaterno();
			dniRepresentante = tra.getDescCoDocumentoIdentidad();
		}
		JSONObject nombre_representante = new JSONObject();
		nombre_representante.put("mergeField", "nombre_representante");
		nombre_representante.put("value", nombreRepresentante);
		list.put(nombre_representante);

		JSONObject dni_representante = new JSONObject();
		dni_representante.put("mergeField", "dni_representante");
		dni_representante.put("value", dniRepresentante);
		list.put(dni_representante);

		PgimUnidadMineraDTO pgimUnidadMineraDTO = unidadMineraService
				.obtenerUnidadMinera(pgimSupervisionDTO.getIdUnidadMinera());

		documento = PoiWordUtil.replaceJSONArray(documento, list);

		List<XWPFParagraph> paragraphs = documento.getParagraphs();
		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_supervision")) {
					paragraph.removeRun(j);

					XWPFTable tableSupervision = PoiWordUtil.createTableInSpecificPosition(paragraph, documento,
							9000);

					XWPFTableRow tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 0, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Agente fiscalizado", 8, true);
					tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowOne,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */4329, 0, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							valoresActaSupervision.getDescAgenteSupervisadoNoRazonSocial(), 8, false);
					tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowOne,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */941, 0, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Fecha", 8, true);
					tableSupervisionRowOne = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowOne,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 0, 3, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							valoresActaSupervision.getFeInicioSupervisionDesc(), 8, false);

					XWPFTableRow tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 1, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Unidad fiscalizada", 8, true);
					tableSupervisionRowTwo = PoiWordUtil
							.createTableCell(tableSupervision, tableSupervisionRowTwo, XWPFVertAlign.CENTER, 200,
									"FFFFFF", /* TableWidthType.PCT, */4329, 1, 1, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0, pgimUnidadMineraDTO.getCoUnidadMinera()+" - "+valoresActaSupervision.getDescNoUnidadMinera(), 8, false);
					tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowTwo,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */941, 1, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Hora", 8, true);
					tableSupervisionRowTwo = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowTwo,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 1, 3, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							valoresActaSupervision.getDescHoraInicioSupervisionDesc(), 8, false);
					
					XWPFTableRow tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 2, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Tipo", 8, true);
					tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThree, XWPFVertAlign.CENTER, 200,
							"FFFFFF", /* TableWidthType.PCT, */4329, 2, 1, 0, ParagraphAlignment.LEFT,
							LineSpacingRule.EXACT, 0, 0, pgimUnidadMineraDTO.getDescIdTipoUnidadMinera(), 8, false);
					tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThree,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */941, 2, 2, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "", 8, true);
					tableSupervisionRowThree = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowThree,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 2, 3, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"", 8, false);
					PoiWordUtil.mergeCellVertically(tableSupervision, 2, 1, 2);
					PoiWordUtil.mergeCellVertically(tableSupervision, 3, 1, 2);

					XWPFTableRow tableSupervisionRowFour = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 3, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Distrito", 8, true);
					tableSupervisionRowFour = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowFour,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */6587, 3, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, distritos, 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 3, 1, 3);

					XWPFTableRow tableSupervisionRowFive = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 4, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Provincia", 8, true);
					tableSupervisionRowFive = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowFive,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */6587, 4, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, provincias, 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 4, 1, 3);

					XWPFTableRow tableSupervisionRowSix = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 5, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Departamento", 8, true);
					tableSupervisionRowSix = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowSix,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */6587, 5, 1, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, departamentos, 8, false);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 5, 1, 3);

					XWPFTableRow tableSupervisionRowSeven = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */2823, 6, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
							"Representante(s) del Agente fiscalizado ", 8, true);
					tableSupervisionRowSeven = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowSeven,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, 6, 1, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Nombre (s) y apellido(s)", 8,
							true);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, 6, 1, 2);
					tableSupervisionRowSeven = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowSeven,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */1317, 6, 2, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "DNI", 8, true);

					int cant = 6;
					if (listaAgenteSupervisado != null && listaAgenteSupervisado.size() > 0) {
						for (int k = 0; k < listaAgenteSupervisado.size(); k++) {
							cant++;
							XWPFTableRow tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cant, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaAgenteSupervisado.get(k).getDescIdTipoPrefijoInvolucrado() + " " +
									listaAgenteSupervisado.get(k).getDescNoPersona() + " "
											+ listaAgenteSupervisado.get(k).getDescApPaterno() + " "
											+ listaAgenteSupervisado.get(k).getDescApMaterno(),
									8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cant, 1, 2);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */1317, cant, 2, 0,
									ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0,
									listaAgenteSupervisado.get(k).getDescCoDocumentoIdentidad(), 8, false);
						}
					} else {
						for (int k = 0; k < 3; k++) {
							cant++;
							XWPFTableRow tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cant, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cant, 1, 2);
							tableSupervisionRowAS = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowAS,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */1317, cant, 2, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, false);
						}
					}

					PoiWordUtil.mergeCellVertically(tableSupervision, 0, 6, cant);

					int cantTwo = cant + 1;
					XWPFTableRow tableSupervisionRowEight = PoiWordUtil.createTableCell(tableSupervision, null,
							XWPFVertAlign.CENTER, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cantTwo, 0, 0,
							ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "Fiscalizador(es)", 8, true);
					tableSupervisionRowEight = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowEight,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */5270, cantTwo, 1, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Nombre (s) y apellido(s)", 8,
							true);
					PoiWordUtil.mergeCellHorizontally(tableSupervision, cantTwo, 1, 2);
					tableSupervisionRowEight = PoiWordUtil.createTableCell(tableSupervision, tableSupervisionRowEight,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */1317, cantTwo, 2, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "DNI", 8, true);

					int cantThree = cantTwo;
					if (listaSupervisores != null && listaSupervisores.size() > 0) {
						for (int k = 0; k < listaSupervisores.size(); k++) {
							cantThree++;
							XWPFTableRow tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cantThree, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "", 8, true);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.BOTTOM, 200, "FFFFFF",
									/* TableWidthType.PCT, */5270, cantThree, 1, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0,
									listaSupervisores.get(k).getDescNoPersona() + " "
											+ listaSupervisores.get(k).getDescApPaterno() + " "
											+ listaSupervisores.get(k).getDescApMaterno(),
									8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cantThree, 1, 2);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.CENTER, 200, "FFFFFF",
									/* TableWidthType.PCT, */1317, cantThree, 2, 0, ParagraphAlignment.CENTER,
									LineSpacingRule.EXACT, 0, 0, listaSupervisores.get(k).getDescCoDocumentoIdentidad(),
									8, false);
						}
					} else {
						for (int k = 0; k < 3; k++) {
							cantThree++;
							XWPFTableRow tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision, null,
									XWPFVertAlign.BOTTOM, 200, "FFFFFF", /* TableWidthType.PCT, */2823, cantThree, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, "", 8, true);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.BOTTOM, 200, "FFFFFF",
									/* TableWidthType.PCT, */5270, cantThree, 1, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0, " ", 8, false);
							PoiWordUtil.mergeCellHorizontally(tableSupervision, cantThree, 1, 2);
							tableSupervisionRowSUPE = PoiWordUtil.createTableCell(tableSupervision,
									tableSupervisionRowSUPE, XWPFVertAlign.BOTTOM, 200, "FFFFFF",
									/* TableWidthType.PCT, */1317, cantThree, 2, 0, ParagraphAlignment.LEFT,
									LineSpacingRule.EXACT, 0, 0, " ", 8, false);
						}
					}

					PoiWordUtil.mergeCellVertically(tableSupervision, 0, cantTwo, cantThree);
				}
			}
		}

		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_observaciones_adicionales")) {
					paragraph.removeRun(j);

					XWPFTable tableObservacionesAdicionales = PoiWordUtil.createTableInSpecificPosition(paragraph, documento, 9000);

					XWPFTableRow tableObservacionesAdicionalesRowOne = PoiWordUtil.createTableCell(
							tableObservacionesAdicionales, null, XWPFVertAlign.CENTER, 200, "FFFFFF",
							/* TableWidthType.PCT, */9410, 0, 0, 0, ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0,
							0, "OBSERVACIONES ADICIONALES:", 8, true);
					
					PoiWordUtil.createTableCellHtml(tableObservacionesAdicionales, tableObservacionesAdicionalesRowOne,
							XWPFVertAlign.BOTTOM, 200, "FFFFFF", 9410, 1, 0,
							ConstantesUtil.ALINEAR_LEFT, EscritorHtml.replaceLineBreakToPHtml(valoresActaSupervision.getDeObservacionInicioSuperT()), 8, documento, "chunk1"+j);
				}
			}
		}

		for (int i = 0; i < paragraphs.size(); i++) {
			XWPFParagraph paragraph = paragraphs.get(i);
			List<XWPFRun> runs = paragraph.getRuns();
			for (int j = 0; j < runs.size(); j++) {
				XWPFRun run = runs.get(j);
				String text = run.getText(run.getTextPosition());
				if (text != null && text.contains("tabla_firmantes")) {
					paragraph.removeRun(j);

					XWPFTable tableFirmantes = PoiWordUtil.createTableInSpecificPosition(paragraph, documento, 9000);

					XWPFTableRow tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, null,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 0, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Documento de identidad", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 1, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Cargo", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */3200, 0, 2, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Nombre y apellidos", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1977, 0, 3, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Firma", 8, true);
					tableFirmantesRowOne = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRowOne,
							XWPFVertAlign.CENTER, 200, "D9D9D9", /* TableWidthType.PCT, */1411, 0, 4, 0,
							ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0, "Entidad", 8, true);

					tableFirmantesRowOne.setRepeatHeader(true);

					int cant = 0;
					if ((listaAgenteSupervisado != null && listaAgenteSupervisado.size() > 0)
							|| (listaSupervisores != null && listaSupervisores.size() > 0)) {
						List<PgimSupervisionDTO> listaFirmantes = new LinkedList<PgimSupervisionDTO>();

						for (PgimSupervisionDTO supervisores : listaSupervisores) {
							supervisores.setDescEntidad(supervisores.getDescNoRazonSocialEmpSupervisora());
							listaFirmantes.add(supervisores);
						}

						for (PgimInvolucradoSupervDTO agente : listaAgenteSupervisado) {
							PgimSupervisionDTO obj = new PgimSupervisionDTO();
							obj.setDescNoPersona(agente.getDescIdTipoPrefijoInvolucrado() + " " + agente.getDescNoPersona());
							obj.setDescApPaterno(agente.getDescApPaterno());
							obj.setDescApMaterno(agente.getDescApMaterno());
							obj.setDescCoDocumentoIdentidad(agente.getDescCoDocumentoIdentidad());
							obj.setDescEntidad("Titular minero");
							obj.setDescDeCargo(agente.getDeCargo());
							listaFirmantes.add(obj);
						}

						for (PgimInvolucradoSupervDTO tra : listaTrabajadores) {
							PgimSupervisionDTO obj = new PgimSupervisionDTO();
							obj.setDescNoPersona(tra.getDescIdTipoPrefijoInvolucrado() + " " + tra.getDescNoPersona());
							obj.setDescApPaterno(tra.getDescApPaterno());
							obj.setDescApMaterno(tra.getDescApMaterno());
							obj.setDescCoDocumentoIdentidad(tra.getDescCoDocumentoIdentidad());
							obj.setDescEntidad("Comité de Seguridad y Salud Ocupacional");
							obj.setDescDeCargo(tra.getDeCargo());
							listaFirmantes.add(obj);
						}

						for (int k = 0; k < listaFirmantes.size(); k++) {
							cant++;
							XWPFTableRow tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, null,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 0, 0,
									ParagraphAlignment.CENTER, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescCoDocumentoIdentidad(), 8, false);

							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescDeCargo(), 8, false);

							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */3200, cant, 2, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescNoPersona() + " "
											+ listaFirmantes.get(k).getDescApPaterno() + " "
											+ listaFirmantes.get(k).getDescApMaterno(),
									8, false);

							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1977, cant, 3, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, false);

							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 4, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0,
									listaFirmantes.get(k).getDescEntidad(), 8, false);
						}
					} else {
						for (int k = 0; k < 5; k++) {
							cant++;
							XWPFTableRow tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, null,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 0, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 1, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */3200, cant, 2, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1977, cant, 3, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
							tableFirmantesRow = PoiWordUtil.createTableCell(tableFirmantes, tableFirmantesRow,
									XWPFVertAlign.CENTER, 600, "FFFFFF", /* TableWidthType.PCT, */1411, cant, 4, 0,
									ParagraphAlignment.LEFT, LineSpacingRule.EXACT, 0, 0, " ", 8, true);
						}
					}
				}
			}
		}

		///////////////////////////////////////////////////////
		// REVIEW: Uniformidad de la fecha generada por la PGIM 
		// y el numero de paginas en el pie de paginas
		///////////////////////////////////////////////////////
		
		this.documentoService.getFooterAlignBottomDocs(documento, fechaDeGeneracion, "VERTICAL", 0, 8000); // PLANTILLA_ACTA_INICIO_FISCALIZACION.docx

		///////////////////////////////////////////////////////
		// REVIEW: Fin
		///////////////////////////////////////////////////////
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		documento.write(baos);
		// documento.close();

		byte[] archivo = baos.toByteArray();

		try {
			archivo = PoiWordUtil.doc2pdf(archivo, idTipoExtensionGen, this.propertiesConfig.getLicenciaAspose());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PgimException(TipoResultado.ERROR, "DOC_GEN" + e.getMessage());
		}

		return archivo;
	}

	@PostMapping("/generarActaSupervision")
	public ResponseEntity<byte[]> generarActaSupervision(@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO)
			throws Exception {

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = documentoService
				.obtenerSubcategoriaDocPorId(pgimSupervisionDTO.getDescIdSubcategoriaDoc());

		byte[] archivo = generarDocumentoActaSupervisionPlantilla(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_PLANTILLA_ACTA_FISCALIZACION, pgimSupervisionDTO,
				pgimSubcategoriaDocDTO.getIdTipoExtensionGen());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/generarActaInicioSupervision")
	public ResponseEntity<byte[]> generarActaInicioSupervision(
			@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO)
			throws Exception {

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = documentoService
				.obtenerSubcategoriaDocPorId(pgimSupervisionDTO.getDescIdSubcategoriaDoc());

		byte[] archivo = generarDocumentoActaInicioSupervisionPlantilla(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_PLANTILLA_ACTA_INICIO_FISCALIZACION,
				pgimSupervisionDTO, pgimSubcategoriaDocDTO.getIdTipoExtensionGen());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargarWord/{idInstanciaProceso}")
	public ResponseEntity<ResponseDTO> descargarWord(@PathVariable Long idInstanciaProceso, @Valid @RequestBody PgimDocumentoDTO pgimDocumentoDTO) throws Exception {

		ResponseDTO responseDTO = null;

		String ruta = null;

		PgimSupervisionDTO pgimSupervisionDTO = null;

		PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = null;

		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = null;

		Map<String, Object> respuestaLog = new HashMap<>();

		byte[] byteDoc = null;

		HttpHeaders headers = new HttpHeaders();

		if (idInstanciaProceso != null) {
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
			pgimAgenteSupervisadoDTO = this.agenteSupervisadoService.obtenerAgenteSupervisadoPorInstancProceso(idInstanciaProceso);
		} else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {

			if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_DJIM)) {

				ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_DJ_INSTRUMENTOS_MEDICION;

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				// Datos de Supervisión y unidad minera
				PgimSupervisionDTO supervisionPorId = supervisionService.obtenerSupervisionPorIdAux(pgimSupervisionDTO.getIdSupervision());

				if(supervisionPorId != null && supervisionPorId.getDescIdPersonaEmpSuperv() != null) {
					PgimPersona empSupervisora = this.documentoService.obtenerConsorcioPrincipal(supervisionPorId.getDescIdPersonaEmpSuperv());

					supervisionPorId.setDescDocSupervisora(empSupervisora.getCoDocumentoIdentidad());
				}

				List<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = new ArrayList<PgimInstrmntoXSupervDTO>();

				// Obtener los datos de la persona asignada a la supervisión
				lPgimEqpInstanciaProDTO = this.instanciaProcesService.obtenerParticipantesInstanciaProXRol(supervisionPorId.getIdInstanciaProceso(), ConstantesUtil.PROCESO_ROL_COORDINADOR_SUPERVISION);

				PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = lPgimEqpInstanciaProDTO.get(0);

				lPgimInstrmntoXSupervDTO = this.supervInstrumentoService.ObtenerListaInstrumentoRegAprob(pgimSupervisionDTO.getIdSupervision(), EValorParametro.TIPO_ESTDO_INSTRMNTO_REGISTRADO.toString(), EValorParametro.TIPO_ESTDO_INSTRMNTO_APROBADO.toString(), EValorParametro.TIPO_ESTDO_INSTRMNTO_MODIFICADO.toString());

				byteDoc = this.documentoService.generarDJInstrumMedicion(ruta, supervisionPorId, lPgimInstrmntoXSupervDTO, ConstantesUtil.PARAM_SC_DOCX, pgimEqpInstanciaProDTO);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_CREDENCIAL)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				// Datos de Supervisión y unidad minera
				PgimSupervisionDTO supervisionPorId = supervisionService.obtenerSupervisionPorIdAux(pgimSupervisionDTO.getIdSupervision());

				lPgimEqpInstanciaProDTO = this.instanciaProcesService.obtenerParticipantesInstanciaProXRol(supervisionPorId.getIdInstanciaProceso(), ConstantesUtil.PARAM_id_rol_SUPERVISOR);

				PgimUnidadMineraDTO pgimUnidadMineraDTO = unidadMineraService.obtenerUnidadMinera(pgimSupervisionDTO.getIdUnidadMinera());

				// Obtener cotablaInstancia
				PgimInstanciaProces pgimInstanciaProces = instanciaProcesService.obtenerInstanciaProcesoPorId(idInstanciaProceso);

				// Obtener idPrograma
				PgimSupervisionDTO pgimSupervisionDTO_TDR = supervisionService.obtenerSupervisionPorId(pgimSupervisionDTO.getIdSupervision());

				PgimSubtipoSupervision pgimSubtipoSupervision = subTipoSupervisionRepository.getOne(pgimSupervisionDTO.getIdSubtipoSupervision());

				pgimSupervisionDTO.setDescDeMensaje(pgimSubtipoSupervision.getDeSubtipoSupervision());

				String flPropia = pgimSupervisionDTO_TDR.getFlPropia();

				String strSupervisor = "Osinergmin";

				if (flPropia.equals("0")) {
					// Obtener empresa supervisora
					PgimContratoDTO pgimContratoDTO = contratoRepository.obtenerContratoPorId(pgimSupervisionDTO.getDescIdContrato());

					PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO = empresaSupervisoraService.obtenerPorId(pgimContratoDTO.getIdEmpresaSupervisora());

					strSupervisor = pgimEmpresaSupervisoraDTO.getDescNoRazonSocial();
				}

				List<PgimDemarcacionUmineraDTOResultado> demarcaciones = demarcacionUMineraRepository.findByUm(pgimUnidadMineraDTO.getIdUnidadMinera());

				byteDoc = this.documentoService.generarDocumentoCredencial(lPgimEqpInstanciaProDTO, pgimUnidadMineraDTO, pgimSupervisionDTO, pgimInstanciaProces, strSupervisor, demarcaciones, ConstantesUtil.PARAM_SC_DOCX);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_REV_ANTE)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				byteDoc = this.documentoService.generarRevisionAntecedente(propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_REVISION_ANTECEDENTE_SUPERVISION, pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_SC_DOCX, this.obtenerAuditoria());
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_ACTA_INICIO_FISCALIZACION)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				byteDoc = generarDocumentoActaInicioSupervisionPlantilla(propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_PLANTILLA_ACTA_INICIO_FISCALIZACION, pgimSupervisionDTO, ConstantesUtil.PARAM_SC_DOCX);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_ACTA_REQUERIMIENTO_DOCUMENTACION)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				byteDoc = documentoService.generarDocRequerimientoPlantilla(propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_REQUERIMIENTO_DOCUMENTACION, pgimSupervisionDTO, ConstantesUtil.PARAM_SC_DOCX);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_ACTA_RECEPCION_DOCUMENTACION)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				byteDoc = documentoService.generarDocRecibidosPlantilla(propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_RECEPCION_DOCUMENTACION, pgimSupervisionDTO, ConstantesUtil.PARAM_SC_DOCX);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				byteDoc = generarDocumentoActaSupervisionPlantilla(propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_PLANTILLA_ACTA_FISCALIZACION, pgimSupervisionDTO, ConstantesUtil.PARAM_SC_DOCX);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_CUADRO_VERIFICACION)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO = new FiltroMatrizSupervisionDTO();
				filtroMatrizSupervisionDTO.setIdSupervision(pgimSupervisionDTO.getIdSupervision());
				filtroMatrizSupervisionDTO.setCodigoCumple(0L);
				filtroMatrizSupervisionDTO.setTipoMatriz("S");

				byteDoc = documentoService.generarMatrizSupervisionPlantilla(propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FORMATO_CUADRO_VERIFICACION, filtroMatrizSupervisionDTO, ConstantesUtil.PARAM_SC_DOCX);
			}

			else if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_HC)) {

				pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

				PgimSupervisionDTO valoresActaSupervision = supervisionService.obtenerValoresFichaHechosConstatados(pgimSupervisionDTO.getIdSupervision());

				valoresActaSupervision.setIdInstanciaProceso(idInstanciaProceso);
				valoresActaSupervision.setIdSupervision(pgimSupervisionDTO.getIdSupervision());
				valoresActaSupervision.setCoSupervision(pgimSupervisionDTO.getCoSupervision());
				valoresActaSupervision.setDescAgenteSupervisadoNoRazonSocial(pgimAgenteSupervisadoDTO.getDescNoRazonSocial());

				List<PgimHechoConstatadoDTO> listaHechos = hechoConstatadoService.obtenerHechosConstatados(valoresActaSupervision.getIdSupervision(), ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);

				List<PgimHechoConstatadoDTO> listaHechosIncumplimientos = hechoConstatadoService.obtenerHechosConstatadosIncumplimiento(valoresActaSupervision.getIdSupervision(), ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);

				byteDoc = documentoService.generarFichaHecCons(propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_PLANTILLA_FICHA_HECHOS_VERIFICADOS, valoresActaSupervision, listaHechos, listaHechosIncumplimientos, ConstantesUtil.PARAM_TIPO_HC_SUP, ConstantesUtil.PARAM_SC_DOCX);
			}

			PgimValorParametroDTO pgimValorParametro = parametroService.obtenerValorParametroPorID(ConstantesUtil.PARAM_SC_DOCX);

			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(pgimValorParametro.getNoValorParametro().toLowerCase());
			headers.setContentType(MediaType.valueOf(mimeType));
			headers.set("NombreArchivo", pgimValorParametro.getNoValorParametro().toLowerCase());
			headers.setContentLength(byteDoc.length);

		} catch (final PgimException e) {
	            // Excepcion controlada que deberá ser manejada por el frontend
	            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
				log.error(e.getMensaje(), e);
	            
	            TipoResultado tipoResultado;
	            if (e.getTipoResultado() == null) {
	                tipoResultado = TipoResultado.WARNING;
	            } else {
	                tipoResultado = e.getTipoResultado();
	            }

	            responseDTO = new ResponseDTO(tipoResultado, e.getMensaje());

	            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar la descarga " + (e.getMessage()!=null ?": "+e.getMessage():""));
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, byteDoc, "Documento a descargar");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

	}

	@PostMapping("/descargarDocRiesgoGestionTecnico/{idInstanciaProceso}") // REVIEW: Revisar si reproduce el log en la fiscalización 220
	public ResponseEntity<byte[]> descargarDocRiesgoGestionTecnico(@PathVariable Long idInstanciaProceso, @Valid @RequestBody PgimRankingUmGrupoDTO pgimRankingUmGrupoDTOsel ) throws Exception {

		byte[] byteDoc = null;
        Map<String, Object> respuestaLog = new HashMap<>();
		
		HttpHeaders headers = new HttpHeaders();

		if (idInstanciaProceso != null) {
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		} else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			// Formato de riesgos técnicos
			if (pgimRankingUmGrupoDTOsel.getDescIdGrupoRiesgo().equals(ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO)) {

				byteDoc = documentoService.generarFormatoRiesgos(propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FORMATO_RIESGOS, pgimRankingUmGrupoDTOsel, ConstantesUtil.PARAM_SC_DOCX);
			}
			// Formato de riesgos de gestión
			else {
				byteDoc = documentoService.generarFormatoRiesgos(propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FORMATO_RIESGOS, pgimRankingUmGrupoDTOsel, ConstantesUtil.PARAM_SC_DOCX);
			}

			PgimValorParametroDTO pgimValorParametro = parametroService.obtenerValorParametroPorID(ConstantesUtil.PARAM_SC_DOCX);

			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(pgimValorParametro.getNoValorParametro().toLowerCase());
			headers.setContentType(MediaType.valueOf(mimeType));
			headers.set("NombreArchivo", pgimValorParametro.getNoValorParametro().toLowerCase());
			headers.setContentLength(byteDoc.length);

			return new ResponseEntity<>(byteDoc, headers, HttpStatus.OK);

		} catch (final PgimException e) {
			// Manejo de logs
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606
			log.error(e.getMensaje(), e);

			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/obtenerUnValor/{idSupervision}")
    public ResponseEntity<?> obtenerUnValor(@PathVariable Long idSupervision) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();

        Integer cantidadInstrumentos = null;

		Map<String, Object> respuestaLog = new HashMap<>();

		if(idSupervision != null){
			Long idInstanciaProces = this.supervisionService.getByIdSupervision(idSupervision).getPgimInstanciaProces().getIdInstanciaProceso();
			if (idInstanciaProces != null) {
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			} else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            cantidadInstrumentos = this.supervInstrumentoRepository.cantidadInstrumentos(idSupervision, null);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron la cantidad de instrumentos");
        respuesta.put("cantidadInstrumentos", cantidadInstrumentos);
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

	@GetMapping("/obtenerValores/{idInstanciaProceso}/{rol}")
    public ResponseEntity<?> obtenerValores(@PathVariable Long idInstanciaProceso, @PathVariable Long rol) throws Exception {

        Map<String, Object> respuesta = new HashMap<>();
        Map<String, Object> respuestaLog = new HashMap<>();

		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = null;

		if(idInstanciaProceso != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {

            lPgimEqpInstanciaProDTO = this.instanciaProcesService.obtenerParticipantesInstanciaProXRol(idInstanciaProceso, rol);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de estrato");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimEqpInstanciaProDTO", lPgimEqpInstanciaProDTO);
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

	@GetMapping("/obtenerDocumentoPgimSiged/{idDocumento}/{nuExpedienteSiged}")
	public ResponseEntity<?> obtenerDocumentoPgimSiged(@PathVariable Long idDocumento,
			@PathVariable String nuExpedienteSiged) throws Exception {

		PgimDocumentoDTO pgimDocumentoDTOResultado = new PgimDocumentoDTO();
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();
		PgimDocumentoDTO pgimDocumentoDTO = null;

		if(nuExpedienteSiged != null){
			Long idInstanciaProces = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nuExpedienteSiged).getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimDocumentoDTO = this.documentoService.obtenerDocumentoPgimById(idDocumento);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta del documento");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Documentos documentosYArchivosSiged = documentoService.obtenerExpedienteDocumentoSiged(nuExpedienteSiged, "1",
				this.obtenerAuditoria());

		if (documentosYArchivosSiged.getListaDocumento() == null
				|| documentosYArchivosSiged.getListaDocumento().size() == 0) {
			pgimDocumentoDTOResultado = pgimDocumentoDTO;
		} else {
			pgimDocumentoDTOResultado = pgimDocumentoDTO;

			for (Documento docSiged : documentosYArchivosSiged.getListaDocumento()) {
				if (pgimDocumentoDTO.getCoDocumentoSiged().toString().equals(docSiged.getIdDocumento())) {
					pgimDocumentoDTOResultado.setNumeroDocumento(docSiged.getNroDocumento());
					pgimDocumentoDTOResultado.setNombreTipoDocumento(docSiged.getNombreTipoDocumento());
					pgimDocumentoDTOResultado.setAsuntoSiged(docSiged.getAsunto());
					pgimDocumentoDTOResultado.setFechaDocumentoSiged(docSiged.getFechaDocumento());
					pgimDocumentoDTOResultado.setFechaCreacionSiged(docSiged.getFechaCreacion());
					pgimDocumentoDTOResultado.setFechaLimiteAtencionSiged(docSiged.getFechaLimiteAtencion());
					pgimDocumentoDTOResultado.setFechaNumeracionSiged(docSiged.getFechaNumeracion());
					pgimDocumentoDTOResultado.setNuExpedienteSiged(nuExpedienteSiged);
					break;
				}
			}
		}

		respuesta.put("mensaje", "Se encontró el documento");
		respuesta.put("pgimDocumentoDTO", pgimDocumentoDTOResultado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	
	@PostMapping("/obtenerDocumentosLiquidacion")
	public ResponseEntity<?> obtenerDocumentosLiquidacion(@Valid @RequestBody Long idSupervision) throws Exception {

		List<PgimDocumentoDTO> lPgimDocumentoDTOResultado = null;
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		try {
			lPgimDocumentoDTOResultado = this.documentoService.obtenerDocumentosLiquidacion(idSupervision,  this.obtenerAuditoria());
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}		

		respuesta.put("mensaje", "Se encontraron los documentos de las fiscalizaciones asociadas a la liquidación");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTOResultado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/descargaMatrizSupervision")
	public ResponseEntity<byte[]> descargaMatrizSupervision(
			@Valid @RequestBody FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO) throws Exception {

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = documentoService
				.obtenerSubcategoriaDocPorId(filtroMatrizSupervisionDTO.getDescIdSubcategoriaDoc());

		byte[] archivo = documentoService.generarMatrizSupervisionPlantilla(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FORMATO_CUADRO_VERIFICACION,
				filtroMatrizSupervisionDTO, pgimSubcategoriaDocDTO.getIdTipoExtensionGen());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargaDocumentoRequerido")
	public ResponseEntity<byte[]> descargaDocumentoRequerido(
			@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO) throws Exception {

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = documentoService
				.obtenerSubcategoriaDocPorId(pgimSupervisionDTO.getDescIdSubcategoriaDoc());

		byte[] archivo = documentoService.generarDocRequerimientoPlantilla(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_REQUERIMIENTO_DOCUMENTACION,
				pgimSupervisionDTO, pgimSubcategoriaDocDTO.getIdTipoExtensionGen());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargaDocumentoRecibido")
	public ResponseEntity<byte[]> descargaDocumentoRecibido(
			@Valid @RequestBody PgimSupervisionDTO pgimSupervisionDTO) throws Exception {

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = documentoService
				.obtenerSubcategoriaDocPorId(pgimSupervisionDTO.getDescIdSubcategoriaDoc());

		byte[] archivo = documentoService.generarDocRecibidosPlantilla(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_RECEPCION_DOCUMENTACION,
				pgimSupervisionDTO, pgimSubcategoriaDocDTO.getIdTipoExtensionGen());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@GetMapping("/obtenerSubcategoriaDocPorId/{idSubcatDocumento}")
	public ResponseEntity<?> obtenerSubcategoriaDocPorId(@PathVariable Long idSubcatDocumento) {

		Map<String, Object> respuesta = new HashMap<>();

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO = null;

		try {
			pgimSubcategoriaDocDTO = documentoService.obtenerSubcategoriaDocPorId(idSubcatDocumento);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvo la subcategoria");
		respuesta.put("pgimSubcategoriaDocDTO", pgimSubcategoriaDocDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/descargarRankingExcel")
	public ResponseEntity<byte[]> descargarRankingExcel(@Valid @RequestBody PgimRankingUmAuxDTO pgimRankingUmAuxDTO)
			throws Exception {

		byte[] archivo = documentoService.generarDocumentoRanking(pgimRankingUmAuxDTO.getIdRankingRiesgo(),
				pgimRankingUmAuxDTO.getCoAnonimizacion());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType("DocumentoRanking.xlsx");
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", "DocumentoRanking.xlsx");
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargaFormatoRiesgos")
	public ResponseEntity<byte[]> descargaFormatoRiesgos(
			@Valid @RequestBody PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO) throws Exception {

		PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO;

		if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo().equals(ConstantesUtil.PARAM_GRUPO_RIESGO_TECNICO))
			pgimSubcategoriaDocDTO = documentoService
					.obtenerSubcategoriaDocPorId(ConstantesUtil.PARAM_SC_FICHA_RIESGOS_PARAM_TECNICOS);
		else if (pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo().equals(ConstantesUtil.PARAM_GRUPO_RIESGO_GESTION))
			pgimSubcategoriaDocDTO = documentoService
					.obtenerSubcategoriaDocPorId(ConstantesUtil.PARAM_SC_FICHA_RIESGOS_PARAM_GESTION);
		else
			throw new Exception("Error: Grupo de riesgo no implementado. IdGrupoRiesgo: "
					+ pgimRankingUmGrupoDTO.getDescIdGrupoRiesgo());

		byte[] archivo = documentoService.generarFormatoRiesgos(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FORMATO_RIESGOS,
				pgimRankingUmGrupoDTO, pgimSubcategoriaDocDTO.getIdTipoExtensionGen());

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		// String mimeType = fileTypeMap.getContentType("pdf");
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", pgimSubcategoriaDocDTO.getDescNoTipoExtensionGen().toLowerCase());
		// headers.set("NombreArchivo", "pdf");
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/generarFormatoRiesgos") // REVIEW:
	public ResponseEntity<ResponseDTO> generarFormatoRiesgos(
			@RequestPart("rankingUmGrupoDTO") PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO,
			@RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO)
			throws PgimException, IOException, Exception {

		ResponseEntity<ResponseDTO> re = null;
        Map<String, Object> respuestaLog = new HashMap<>();

		if(pgimInstanciaProcesDTO.getIdInstanciaProceso() != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(pgimInstanciaProcesDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			re = this.documentoService.procesarGeneracionFormatoRiesgos(pgimRankingUmGrupoDTO, pgimInstanciaProcesDTO,
					this.obtenerAuditoria());
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje(), 0));
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar generar el formato de riesgos: "+ e.getMessage(), 0)); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}
		return re;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/listarExpedientePorUsuario")
	public ResponseEntity<?> listarExpedientePorUsuario(@RequestBody ExpedienteListarPorUsuarioOut filtro,
			Pageable paginador) {

		Map<String, Object> respuesta = new HashMap<>();

		List<ExpedienteListarPorUsuarioOut> lExpedienteListarPorUsuarioOut = null;
		int cantidadRegistrosTotal = 0;

		try {
			Map<String, Object> resultado = documentoService.obtenerListaExpedientePorUsuario(filtro, paginador,
					this.obtenerAuditoria());
			lExpedienteListarPorUsuarioOut = (List<ExpedienteListarPorUsuarioOut>) resultado
					.get("lExpedienteListarPorUsuarioOut");
			cantidadRegistrosTotal = (int) resultado.get("cantidadRegistrosTotal");
			
		} catch (PgimException e) {
			respuesta.put("mensaje", "Ocurrió un problema al obtener la lista de expedientes por usuario: " + e.getMensaje());
			respuesta.put("error", e.getMensaje());
			log.error(e.getMensaje(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {
			respuesta.put("mensaje", "Ocurrió un error al obtener la lista de expedientes por usuario");
			respuesta.put("error", e.getMessage());
			log.error(e.getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvo la lista de expedientes por usuario");
		respuesta.put("lExpedienteListarPorUsuarioOut", lExpedienteListarPorUsuarioOut);
		respuesta.put("cantidadRegistrosTotal", cantidadRegistrosTotal);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/listarDocumentosSigedPorExpediente/{nroExp}/{files}")
	public ResponseEntity<?> listarDocumentosSigedPorExpediente(@PathVariable String nroExp, @PathVariable String files)
			throws Exception {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimDocumentoDTO> lPgimDocumentoDTO = null;
		Long cantidadDocumentos = 0L;

		try {
			Map<String, Object> parametros = this.documentoService.obtenerDocumentoSigedPorExpediente(nroExp, files,
					this.obtenerAuditoria());
			lPgimDocumentoDTO = (List<PgimDocumentoDTO>) parametros.get("lPgimDocumentoDTOResultado");
			cantidadDocumentos = (Long) parametros.get("cantidadDocumentos");

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de Documentos");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se encontraron documentos");
		respuesta.put("lPgimDocumentoDTO", lPgimDocumentoDTO);
		respuesta.put("cantidadDocumentos", cantidadDocumentos);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	/**
	 * Permite listar la trzabilidad por el número de expediente
	 *
	 * @param nroexp
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/listarTrazabilidad/{nroexp}")
	public ResponseEntity<?> listarTrazabilidad(@PathVariable String nroexp) throws Exception {
		ResponseDTO responseDTO = null;

		List<TrazabilidadDocumentoDTO> trazabilidadDocumentoDTO = null;
        Map<String, Object> respuestaLog = new HashMap<>();

		if (nroexp != null) {
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO = this.instanciaProcesService.obtenerInstanciaProcesoPorNuExpediente(nroexp);
			Long idInstanciaProces = null;

			if (pgimInstanciaProcesDTO != null) {
				idInstanciaProces = pgimInstanciaProcesDTO.getIdInstanciaProceso();
			}

			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			trazabilidadDocumentoDTO = this.documentoService.listarTrazabilidadPorExpediente(nroexp,
					this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar mostrar la trazabilidad"
		            + (e.getMostSpecificCause().getMessage()!=null ?": "+e.getMostSpecificCause().getMessage():"")            
		            );
		    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			
		}
		 catch (final PgimException e) {
	            // Excepcion controlada que deberá ser manejada por el frontend
	            log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
				log.error(e.getMensaje(), e);
	            
	            TipoResultado tipoResultado;
	            if (e.getTipoResultado() == null) {
	                tipoResultado = TipoResultado.WARNING;
	            } else {
	                tipoResultado = e.getTipoResultado();
	            }

	            responseDTO = new ResponseDTO(tipoResultado, e.getMensaje());

	            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	        } catch (final Exception e) {
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // EXCEPTION
	            log.error(e.getMessage(), e);
	            responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar mostrar la trazabilidad"
	            + (e.getMessage()!=null ?": "+e.getMessage():"")            
	            );
	            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	            
	        }
		
		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, trazabilidadDocumentoDTO, "Se encontraron documentos.");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Permite descargar un reporte tabular estándar de listado de entidades en
	 * formato pdf
	 * 
	 * @param reporteAuxDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/descargarReportePdf")
	public ResponseEntity<byte[]> descargarReportePdf(
			@Valid @RequestBody ReporteAuxDTO<?> reporteAuxDTO) throws Exception {

		byte[] archivo = documentoService.generarReporteListadoPdf(reporteAuxDTO);
		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(reporteAuxDTO.getNombreArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", reporteAuxDTO.getNombreArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	/**
	 * Permite descargar un reporte tabular estándar de listado de entidades en
	 * formato excel
	 * 
	 * @param reporteAuxDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/descargarReporteExcel")
	public ResponseEntity<byte[]> descargarReporteExcel(
			@Valid @RequestBody ReporteAuxDTO<?> reporteAuxDTO) throws Exception {

		byte[] archivo = documentoService.generarReporteListadoExcel(reporteAuxDTO);
		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(reporteAuxDTO.getNombreArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", reporteAuxDTO.getNombreArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargaFichaInformativaUnidadMinera")
	public ResponseEntity<byte[]> descargaFichaInformativaUnidadMinera(
			@Valid @RequestBody PgimUnidadMineraDTO pgimUnidadMineraDTO) throws Exception {

		byte[] archivo = documentoService.generarFichaInformativaUnidadMinera(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FICHA_INFORMATIVA_UM,
				pgimUnidadMineraDTO, ConstantesUtil.PARAM_SC_PDF);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType("pdf");
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", "pdf");
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargarReportePenPerContratoSupervisora")
	public ResponseEntity<byte[]> descargarReportePenPerContratoSupervisora(
			@Valid @RequestBody PgimPenalidadAuxDTO filtroPenalidadAuxDTO) throws Exception {

		byte[] archivo = null;
		if (filtroPenalidadAuxDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_PDF)) {
			archivo = documentoService.generarReportePenalidadPeriodoContratoSupervisoraPDF(
					propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_PEN_PER_CONTRATO_SUPERVISORA,
					filtroPenalidadAuxDTO, ConstantesUtil.PARAM_SC_PDF);
		} else if (filtroPenalidadAuxDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_EXCEL)) {
			archivo = documentoService.generarReportePenalidadPeriodoContratoSupervisoraEXCEL(filtroPenalidadAuxDTO);
		}

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroPenalidadAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroPenalidadAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargarReporteConSalContratoSupervisora")
	public ResponseEntity<byte[]> descargarReporteConSalContratoSupervisora(
			@Valid @RequestBody PgimContratoSegumntoAuxDTO filtroContratoSegumntoAuxDTO) throws Exception {

		byte[] archivo = null;
		if (filtroContratoSegumntoAuxDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_PDF)) {
			archivo = documentoService.generarReporteControlSaldoContratoSupervisoraPDF(
					propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_CON_SAL_CONTRATO_SUPERVISORA,
					filtroContratoSegumntoAuxDTO, ConstantesUtil.PARAM_SC_PDF);
		} else if (filtroContratoSegumntoAuxDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_EXCEL)) {
			archivo = documentoService.generarReporteControlSaldoContratoSupervisoraEXCEL(filtroContratoSegumntoAuxDTO);
		}

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroContratoSegumntoAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroContratoSegumntoAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargarReporteEjecucionPresupuestal")
	public ResponseEntity<byte[]> descargarReporteEjecucionPresupuestal(
			@Valid @RequestBody PgimContratoSiafAuxDTO filtroContratoSiafAuxDTO) throws Exception {

		byte[] archivo = null;
		if (filtroContratoSiafAuxDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_PDF)) {
			archivo = documentoService.generarReporteEjecucionPresupuestalPDF(
					propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_EJECUCION_PRESUPUESTAL,
					filtroContratoSiafAuxDTO, ConstantesUtil.PARAM_SC_PDF);
		} else if (filtroContratoSiafAuxDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_EXCEL)) {
			archivo = documentoService.generarReporteEjecucionPresupuestalEXCEL(filtroContratoSiafAuxDTO);
		}

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroContratoSiafAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroContratoSiafAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargarReportePresupuestoGastoSupervision")
	public ResponseEntity<byte[]> descargarReportePresupuestoGastoSupervision(
			@Valid @RequestBody PgimPrspstoGastoSuperDTO filtroPrspstoGastoSuperDTO) throws Exception {

		byte[] archivo = null;
		if (filtroPrspstoGastoSuperDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_PDF)) {
			archivo = documentoService.generarReportePresupuestoGastoSupervisionPDF(
					propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_PRESUPUESTO_GASTO_SUPERVISION,
					filtroPrspstoGastoSuperDTO, ConstantesUtil.PARAM_SC_PDF);
		} else if (filtroPrspstoGastoSuperDTO.getDescExtension().equals(ConstantesUtil.PARAM_EXTENSION_EXCEL)) {
			archivo = documentoService.generarReportePresupuestoGastoSupervisionEXCEL(filtroPrspstoGastoSuperDTO);
		}

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroPrspstoGastoSuperDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroPrspstoGastoSuperDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@GetMapping("/listarCategoriaYSubCategoriaByIdProcesoIdFase/{idProceso}/{idFase}")
	public ResponseEntity<?> listarCategoriaYSubCategoriaByIdProcesoIdFase(@PathVariable Long idProceso,
			@PathVariable Long idFase) throws Exception {

		Map<String, Object> respuesta = new HashMap<>();

		List<PgimCategoriaDocDTO> lPgimCategoriaDocDTO = null;

		try {

			if (idFase == 0) {
				lPgimCategoriaDocDTO = categoriaDocService.listarCategoriaYSubCategoriaByIdProceso(idProceso);
			} else {
				lPgimCategoriaDocDTO = categoriaDocService.listarCategoriaYSubCategoriaByIdProcesoIdFase(idProceso,
						idFase);
			}

		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
		respuesta.put("lPgimCategoriaDocDTO", lPgimCategoriaDocDTO);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@GetMapping("/descargaArchivoPlantillas/{idSubcategoriaPlanti}")
	public ResponseEntity<byte[]> descargaArchivoPlantillas(@PathVariable Long idSubcategoriaPlanti) throws Exception {

		PgimSubcategoriaPlantiDTO pgimSubcategoriaPlantiDTO = subcategoriaPlantiRepository
				.obtenerSubcategoriaPlantiPorId(idSubcategoriaPlanti);
		File plantilla = new File(propertiesConfig.getFilesRepo() + pgimSubcategoriaPlantiDTO.getNoPlantilla());

		byte[] archivo = null;

		HttpHeaders headers = new HttpHeaders();

		if (plantilla != null) {
			Path rutaPlantilla = Paths.get(plantilla.getPath());
			archivo = Files.readAllBytes(rutaPlantilla);

			MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
			String mimeType = fileTypeMap.getContentType(pgimSubcategoriaPlantiDTO.getNoPlantilla());
			headers.setContentType(MediaType.valueOf(mimeType));
			headers.set("NombreArchivo", pgimSubcategoriaPlantiDTO.getNoPlantilla());
			headers.setContentLength(archivo.length);
		}

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/descargaFichaComponentesUM")
	public ResponseEntity<byte[]> descargaFichaComponentesUM(
			@Valid @RequestBody PgimUnidadMineraDTO pgimUnidadMineraDTO) throws Exception {

		byte[] archivo = documentoService.generarFichaComponentesUnidadMinera(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_FICHA_COMPONENTES_UM,
				pgimUnidadMineraDTO, ConstantesUtil.PARAM_SC_PDF);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType("pdf");
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", "pdf");
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/enumerarDocumento") //REVIEW: nadies lo consume desde el frontend
	public ResponseEntity<ResponseDTO> enumerarDocumento(@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO)
			throws PgimException, IOException, Exception {

		ResponseEntity<ResponseDTO> respuesta = null;

		try {
			// Obteniendo los datos de la auditoría:
			AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();

			EnumerarDocumentoOutRO enumerarDocumentoOutRO = this.documentoService
					.enumeraDocumentoSiged(pgimDocumentoDTO.getCoDocumentoSiged(), auditoriaDTO);

			if (!enumerarDocumentoOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				String mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
						"enumerar el documento", enumerarDocumentoOutRO.getResultCode(),
						enumerarDocumentoOutRO.getMessage());
				log.error(mensajeErrorSiged);

				respuesta = ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", mensajeErrorSiged, 0));
			} else {
				respuesta = ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseDTO("success", "El documento ha sido enumerado"));
			}

		} catch (PgimException e) {
			log.error(e.getMessage(), e);
			respuesta = ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", e.getMensaje(), 0));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			respuesta = ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", e.getMessage(), 0));
		}

		return respuesta;
	}

	/**
	 * Permite la generación y descarga del formato de revisón de antecedente
	 * 
	 * @param idSupervision
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/generarRevisionAntecedente")
	public ResponseEntity<byte[]> generarRevisionAntecedente(@Valid @RequestBody Long idSupervision) throws Exception {

		// Obteniendo los datos de la auditoría:
		AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
		Long idTipoExtención = ConstantesUtil.PARAM_SC_PDF;

		byte[] archivo = documentoService.generarRevisionAntecedente(
				propertiesConfig.getFilesRepo() + ConstantesUtil.PLANTILLA_REVISION_ANTECEDENTE_SUPERVISION,
				idSupervision, idTipoExtención, auditoriaDTO);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType("pdf");
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", "pdf");
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@GetMapping("/obtenerArchivoPorIntanciaPro/{noOriginalArchivo}/{idInstanciaProceso}")
	public ResponseEntity<ResponseDTO> obtenerArchivoPorIntanciaPro(@PathVariable String noOriginalArchivo, @PathVariable Long idInstanciaProceso) throws Exception {
	
		Map<String, Object> respuesta = new HashMap<>();
		Map<String, Object> respuestaLog = new HashMap<>();

		String ultimosCaracteres = null;

		if(idInstanciaProceso != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			ultimosCaracteres = this.archivoService.obtenerArchivoPorIntanciaPro(noOriginalArchivo, idInstanciaProceso);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar recuperar El archivo");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage()));
		}

		respuesta.put("mensaje", "El archivo ha sido recuperada");
		respuesta.put("ultimosCaracteres", ultimosCaracteres);

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, respuesta, "Genial, El archivo ha sido recuperada"));
	}

	/**
	 * PGIM-5001
	 * Permite agregar uno o varios documentos (y su archivos) en un expediente, si
	 * la instancia
	 * proceso no tiene un expediente Siged relacionado, el flujo considera la
	 * creación del mismo
	 * 
	 * @param listaPgimDocumentoDTO  Lista de documentos agregados.
	 * @param pgimInstanciaProcesDTO instancia del proceso.
	 * 
	 * @return
	 */
	@PostMapping("/copiarDocumentosInicioPAS") // REVIEW:
	public ResponseEntity<ResponseDTO> copiarDocumentosInicioPAS(
			@RequestPart("documentoDTO") PgimDocumentoDTO[] listaPgimDocumentoDTO,
			@RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO)
			throws PgimException, IOException, Exception {

		Map<String, Object> respuestaLog = new HashMap<>();

		if(pgimInstanciaProcesDTO.getIdInstanciaProceso() != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(pgimInstanciaProcesDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		PgimInstanciaProcesDTO pgimInstanciaProcesAntesDTO = null;
		PgimInstanciaProcesDTO pgimInstanciaProcesDespuesDTO = null;

		try {
			pgimInstanciaProcesAntesDTO = this.instanciaProcesService.obtenerInstanciaProceso(pgimInstanciaProcesDTO.getIdProceso(), pgimInstanciaProcesDTO.getCoTablaInstancia());
			this.documentoService.procesarCopiadoDocumento_old(listaPgimDocumentoDTO, pgimInstanciaProcesDTO, this.obtenerAuditoria());
		} catch (PgimException e) {
			log.error(e.getMensaje(), e);
			TipoResultado tipoResultado;
			if (e.getTipoResultado() == null) {
					tipoResultado = TipoResultado.WARNING;
			} else {
					tipoResultado = e.getTipoResultado();
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje()));
			
		}catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, e.getMessage()));
		}

		pgimInstanciaProcesDespuesDTO = this.instanciaProcesService.obtenerInstanciaProceso(pgimInstanciaProcesDTO.getIdProceso(), pgimInstanciaProcesDTO.getCoTablaInstancia());

		PgimDocumentoDTO pgimDocumentoDTO = null;
		if (pgimInstanciaProcesAntesDTO.getNuExpedienteSiged() == null && pgimInstanciaProcesDespuesDTO.getNuExpedienteSiged() != null) {
			pgimDocumentoDTO = new PgimDocumentoDTO();
			pgimDocumentoDTO.setDescFlExpedienteCreado(1);
			pgimDocumentoDTO.setNuExpedienteSiged(pgimInstanciaProcesDespuesDTO.getNuExpedienteSiged());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, pgimDocumentoDTO, "Genial, los documentos han sido copiados correctamente"));
	}

	@PostMapping("/copiarAntecedenteSiged")
	public ResponseEntity<ResponseDTO> copiarAntecedenteSiged(
			@RequestPart("documentoDTOPadre") PgimDocumentoDTO pgimDocumentoDTOACopiar,
			@RequestPart("documentoDTO") PgimDocumentoDTO pgimDocumentoDTO,
			@RequestPart("instanciaProcesDTO") PgimInstanciaProcesDTO pgimInstanciaProcesDTO)
			throws PgimException, IOException, Exception {

		ResponseDTO responseDTO = new ResponseDTO();
		
		Map<String, Object> respuestaLog = new HashMap<>();
		String mensajeExcepcionGeneral = "Ocurrió un problema para al realizar el copiado del antecedente: ";

		respuestaLog = this.flujoTrabajoService.mostrarLog(pgimInstanciaProcesDTO.getIdInstanciaProceso(), this.obtenerAuditoria().getUsername());

		try {
			
			this.documentoService.procesarCopiadoDocumentoAntecedenteSiged(pgimDocumentoDTOACopiar, pgimDocumentoDTO, pgimInstanciaProcesDTO, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			mensajeExcepcionGeneral += e.getMostSpecificCause().getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" ); 
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral, 0));

		} catch (PgimException e) {
			
			TipoResultado tipoResultado;
			if (e.getTipoResultado() == null)
				tipoResultado = TipoResultado.WARNING;
			else
				tipoResultado = e.getTipoResultado();
			
			mensajeExcepcionGeneral = e.getMensaje();
			
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" );
			log.error(e.getMensaje(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, mensajeExcepcionGeneral));

		} catch (Exception e) {
			mensajeExcepcionGeneral += e.getMessage();
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" );
			log.error(e.getMessage(), e);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral));
		}

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "", "Genial, se ha realizado la copia del antecedente con éxito");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	/**
	 * Permite obtener el(los) destinatario(s) de un documento para notificación, 
	 * ya sea el agente fiscalizado o los destinatarios pre-definidos
	 * 
	 * @param idDocumento
	 * @return
	 */
	@GetMapping("/obtenerDestinatariosDocumento/{idDocumento}") 
	public ResponseEntity<ResponseDTO> obtenerDestinatariosDocumento(@PathVariable Long idDocumento) {

		Map<String, Object> respuesta = new HashMap<>();
		ResponseDTO responseDTO = null;
		PgimPersonaDTO pgimPersonaDTOAgenteFiscalizado = null;
		List<PgimDestinatarioDocDTO> lPgimDestinatarioDocDTO = null;
		String mensajeExcepcionGeneral = "Ocurrió un problema al obtener el(los) destinatario(s) para el documento";

		try {
			pgimPersonaDTOAgenteFiscalizado = this.documentoService.obtenerPersonaAgenteFiscalizado(idDocumento);
			
			lPgimDestinatarioDocDTO = this.documentoService.listarDestinatarioPorIdDoc(idDocumento);
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral, 0));
			
		} catch (PgimException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), mensajeExcepcionGeneral, 0));
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, mensajeExcepcionGeneral, 0));
		}
		
		String mensajeExito = "Se obtuvo el(los) destinatario(s) para el documento";
		respuesta.put("pgimPersonaDTOAgenteFiscalizado", pgimPersonaDTOAgenteFiscalizado);
		respuesta.put("lPgimDestinatarioDocDTO", lPgimDestinatarioDocDTO);	

		responseDTO = new ResponseDTO(TipoResultado.SUCCESS, respuesta, mensajeExito);

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@GetMapping("/listarSubcategoriasFirmadasPorFiscalizador/{idInstanciaProceso}") // REVIEW:
	public ResponseEntity<ResponseDTO> listarSubcategoriasFirmadasPorFiscalizador(
			@PathVariable Long idInstanciaProceso) throws Exception {

		ResponseEntity<ResponseDTO> respuesta = null;
		ResponseDTO responseDTO = null;
		List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO = null;
		String mensajeExcepcionGeneral = "Ocurrió un problema para obtener las subcategorías que firma la personas fiscalizadora";
		Map<String, Object> respuestaLog = new HashMap<>();

		if(idInstanciaProceso != null){
			respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProceso, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			lPgimSubcategoriaDocDTO = this.documentoService
					.listarSubcategoriasFirmadasPorFiscalizador(idInstanciaProceso);
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + ". " + e.getMostSpecificCause().getMessage() + "]" ); // STORY:PGIM-7606 // DATA
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error",
					mensajeExcepcionGeneral, 0));
		} catch (PgimException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + ". " + e.getMensaje() + "]" ); // STORY:PGIM-7606 // PGIM
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", mensajeExcepcionGeneral, 0));
		} catch (Exception e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensajeExcepcionGeneral + "]" ); // STORY:PGIM-7606 // EXCEPTION
			log.error(e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", mensajeExcepcionGeneral, 0));
		}

		responseDTO = new ResponseDTO("success", lPgimSubcategoriaDocDTO,
				"Se obtuvieron las subcategorías documentales firmadas por la persona fiscalizadora");

		respuesta = ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		return respuesta;
	}

	/**
	 * Permite registrar el el documento a sido firmado o revertido la firma.
	 * @param pgimDocumentoDTO
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/registrarFlFirmadoDoc")
	public ResponseEntity<?> registrarFlFirmadoDoc(@RequestBody PgimDocumentoDTO pgimDocumentoDTO) throws Exception {
		
		Map<String, Object> respuestaLog = new HashMap<>();
		String mensaje;

		PgimDocumento pgimDocumentoActual = null;

		Long idDocumento = pgimDocumentoDTO.getIdDocumento();
		if( idDocumento != null){
			Long idInstanciaProces = this.documentoService.obtenerDocumentoPgim(idDocumento).getPgimInstanciaProces().getIdInstanciaProceso();
			if(idInstanciaProces != null){
				respuestaLog = this.flujoTrabajoService.mostrarLog(idInstanciaProces, this.obtenerAuditoria().getUsername());
			}else {
				respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
			}
		}else{
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

		try {
			pgimDocumentoActual = this.documentoService.obtenerDocumentoPgim(idDocumento);

			if (pgimDocumentoActual == null) {
				mensaje = String.format("El documento que intenta firmar no existe en la base de datos", idDocumento);
				log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + mensaje + "]" ); 
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "El documento que intenta firmar no existe en la base de datos"));
				
			}
		} catch (DataAccessException e) {
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); 
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar recuperar el documento a firmar"));
		}

		try {
			this.documentoService.registrarFlFirmadoDoc(pgimDocumentoActual, ConstantesUtil.FL_IND_SI, this.obtenerAuditoria());
		} catch (DataAccessException e) {
			log.error("Ocurrió un error intentar registrar el flag firmado digital del documento");
			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMostSpecificCause().getMessage() + "]" ); 
			log.error(e.getMostSpecificCause().getMessage(), e);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar registrar el flag firmado digital del documento"));

		}

		ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, "El documento ha sido firmado");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

	}


	@PostMapping("/EtiquetarNotificacion") 
	public ResponseEntity<ResponseDTO> etiquetarNotificacion(@Valid @RequestBody Long idInstanciaPasoActual) throws Exception {

		ResponseEntity<ResponseDTO> respuesta = null;
		ResponseDTO responseDTO = null;
		PgimPasoProcesoDTO pgimDocEtiquetaNotifDTO = null;
		try {
			pgimDocEtiquetaNotifDTO = this.documentoService.etiquetarNotificacion(idInstanciaPasoActual);
		} catch (PgimException e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("error", e.getMensaje(), 0));
		}
		
		responseDTO = new ResponseDTO("success", pgimDocEtiquetaNotifDTO,
				"El paso a sido recuperado");

		respuesta = ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		return respuesta;
	}

	@PostMapping("/etiquetaDocNotif") 
	public ResponseEntity<ResponseDTO> etiquetaDocNotif(@Valid @RequestBody PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifDTO) throws Exception {

		PgimDocEtiquetaNotif pgimDocEtiquetaNotifActual = null;
		PgimDocEtiquetaNotifDTO pgimDocEtiquetado = null;
		ResponseDTO responseDTO = null;
		String mensaje;

		if(pgimDocEtiquetaNotifDTO.getIdDocEtiquetaNotif() != null){
			try {
				pgimDocEtiquetaNotifActual = this.documentoService.obtenerEtiquetaDocNotif(pgimDocEtiquetaNotifDTO.getIdDocEtiquetaNotif());
				String mensajeAux = "Se obtuvo los datos de etiqueta documento";          
				responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimDocEtiquetado, mensajeAux);
			} catch (DataAccessException e) {
				String mensajeAux = "Ocurrió un error al intentar recuperar los datos de etiqueta documento";
				log.error(e.getMostSpecificCause().getMessage(), e);
				mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
				responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);
	
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
			}
		}	 

		try {
			pgimDocEtiquetado = this.documentoService.etiquetaDocNotif(pgimDocEtiquetaNotifDTO,pgimDocEtiquetaNotifActual, this.obtenerAuditoria());
			String mensajeAux = "El documento ha sido etiquetado para notificación";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimDocEtiquetado, mensajeAux);
		} catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar etiquetar el documento para notificación";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}

	@PostMapping("/quitarEtiquetaDocNotif") 
	public ResponseEntity<ResponseDTO> quitarEtiquetaDocNotif(@Valid @RequestBody PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifDTO) throws Exception {

		PgimDocEtiquetaNotif pgimDocEtiquetaNotifActual = null;
		PgimDocEtiquetaNotifDTO pgimDocEtiquetado = null;
		ResponseDTO responseDTO = null;
		String mensaje;

		try {
			pgimDocEtiquetaNotifActual = this.documentoService.obtenerEtiquetaDocNotif(pgimDocEtiquetaNotifDTO.getIdDocEtiquetaNotif());
			String mensajeAux = "Se obtuvo los datos de etiqueta documento";          
			responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimDocEtiquetado, mensajeAux);
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar recuperar los datos de etiqueta documento";
			log.error(e.getMostSpecificCause().getMessage(), e);
			mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
			responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		
		try {
			pgimDocEtiquetado = this.documentoService.quitarEtiquetaDocNotif(pgimDocEtiquetaNotifDTO,pgimDocEtiquetaNotifActual, this.obtenerAuditoria());
			String mensajeAux = "Se ha quitado la etiqueta de notificación al documento ";          
            responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimDocEtiquetado, mensajeAux);
		} catch (PgimException e) {
			log.error(e.getMessage(), e);

            TipoResultado tipoResultado;
            if (e.getTipoResultado() == null){
                tipoResultado = TipoResultado.WARNING;
            } else{
                tipoResultado = e.getTipoResultado();
            }

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(tipoResultado, e.getMensaje(), 0));		
		} catch (DataAccessException e) {
			String mensajeAux = "Ocurrió un error al intentar etiquetar el documento para notificación";
			log.error(e.getMostSpecificCause().getMessage(), e);
            mensaje = String.format(mensajeAux, e.getMostSpecificCause().getMessage());            
            responseDTO = new ResponseDTO(TipoResultado.ERROR, mensaje);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}



	// @PreAuthorize("hasAnyAuthority('es-lista_AC')")
	@PostMapping("/listarIndicadorGeotecnico")
	public ResponseEntity<Page<PgimIndicadorGeotecnicoAuxDTO>> listarIndicadorGeotecnico(@RequestBody PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO, Pageable paginador) throws Exception {

		Page<PgimIndicadorGeotecnicoAuxDTO> lPgimIndicadorGeotecnicoAuxDTO = this.documentoService.listarIndicadorGeotecnico(filtroIndicadorGeotecnicoAuxDTO, paginador);
		return new ResponseEntity<Page<PgimIndicadorGeotecnicoAuxDTO>>(lPgimIndicadorGeotecnicoAuxDTO, HttpStatus.OK);
	}

	@PostMapping("/descargarReporteIndicadorGeotecnico")
	public ResponseEntity<byte[]> descargarReporteIndicadorGeotecnico(@Valid @RequestBody PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO) throws Exception {

		byte[] archivo = documentoService.generarReporteIndicadorGeotecnicoEXCEL(filtroIndicadorGeotecnicoAuxDTO);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroIndicadorGeotecnicoAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroIndicadorGeotecnicoAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/verificarUOrganicaYNumerar")
	public ResponseEntity<ResponseDTO> verificarUOrganicaYNumerar(@RequestBody PgimDocumentoDTO pgimDocumentoDTO) throws Exception {
		ResponseDTO responseDTO = null;

		String estadoVerificacion = "";
		try {
			// Obteniendo los datos de la auditoría:
			AuditoriaDTO auditoriaDTO = this.obtenerAuditoria();
			estadoVerificacion = this.documentoService.verificarUOrganicaYNumerar(pgimDocumentoDTO, auditoriaDTO);

		} catch (PgimException e) {
			// Excepcion controlada que deberá ser manejada por el frontend
			log.error(e.getMensaje(), e);
						
			TipoResultado tipoResultado;
			if (e.getTipoResultado() == null) tipoResultado = TipoResultado.WARNING;
			else tipoResultado = e.getTipoResultado();

			responseDTO = new ResponseDTO(tipoResultado, e.getMensaje());
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseDTO = new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al validar la unidad orgánica: "+e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, pgimDocumentoDTO, estadoVerificacion));
		
	}
	
	@PostMapping("/descargarReporteIndicadorGeotecnicoCSV_v3")
    public void exportToCSV_v3(@Valid @RequestBody PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO,
	HttpServletResponse response) throws IOException, Exception {

		// Aquí deberías obtener los datos que quieres exportar.
		List<PgimIndicadorGeotecnicoAuxDTO> lPgimIndicadorGeotecnicoAuxDTO = this.indicadorGeotecnicoRepository .listarReporteIndicadorGeotecnico(filtroIndicadorGeotecnicoAuxDTO.getDescNoUnidadMinera(),
						filtroIndicadorGeotecnicoAuxDTO.getDescFeInicio(),
							filtroIndicadorGeotecnicoAuxDTO.getDescFeFin());
		
		/* int pageNo = 0; 
		int pageSize = (Integer) filtroIndicadorGeotecnicoAuxDTO.getCantidadRegistros();

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<PgimIndicadorGeotecnicoAuxDTO> pagedResult = this.documentoService.listarIndicadorGeotecnico(filtroIndicadorGeotecnicoAuxDTO, paging);

		List<PgimIndicadorGeotecnicoAuxDTO> lPgimIndicadorGeotecnicoAuxDTO = pagedResult.getContent(); */

		// Establecer el tipo de contenido y el encabezado de la respuesta
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=" + filtroIndicadorGeotecnicoAuxDTO.getDescNoArchivo() + ".csv");

		BufferedWriter bw = new BufferedWriter(response.getWriter());
				
		bw.write("ANOPRO, MES, ID_CLIENTE, RUC, TITULAR_MINERO, ESTRATO, CODIGO, UNIDAD, GRUPO, INDICADOR, VALOR, UNIDAD_MEDIDA");
		bw.newLine(); 

	// Escribe los datos CSV
	for (PgimIndicadorGeotecnicoAuxDTO pgimIndicadorGeotecnicoAuxDTO : lPgimIndicadorGeotecnicoAuxDTO) {
			bw.write(pgimIndicadorGeotecnicoAuxDTO.getAnioPro());	
			bw.write(",");
			bw.write(pgimIndicadorGeotecnicoAuxDTO.getMes());
			bw.write(",");
			bw.write(String.valueOf(pgimIndicadorGeotecnicoAuxDTO.getIdCliente()));
			bw.write(",");
			bw.write(pgimIndicadorGeotecnicoAuxDTO.getRuc());
			bw.write(",");
			bw.write(pgimIndicadorGeotecnicoAuxDTO.getTitularMinero());
			bw.write(",");
			bw.write(pgimIndicadorGeotecnicoAuxDTO.getEstrato());
			bw.write(",");
			bw.write(pgimIndicadorGeotecnicoAuxDTO.getCodigo());
			bw.write(",");

			if(pgimIndicadorGeotecnicoAuxDTO.getUnidad() != null){
				bw.write(Character.toChars(34));
				bw.write(pgimIndicadorGeotecnicoAuxDTO.getUnidad());
				bw.write(Character.toChars(34));
			} else {
				bw.write(Character.toChars(34));
				bw.write(Character.toChars(34));
			}

			bw.write(",");

			if(pgimIndicadorGeotecnicoAuxDTO.getGrupo() != null){
				bw.write(Character.toChars(34));
				bw.write(pgimIndicadorGeotecnicoAuxDTO.getGrupo());
				bw.write(Character.toChars(34));
			} else {
				bw.write(Character.toChars(34));
				bw.write(Character.toChars(34));
			}

			bw.write(",");

			if(pgimIndicadorGeotecnicoAuxDTO.getIndicador() != null){
				bw.write(Character.toChars(34));
				bw.write(pgimIndicadorGeotecnicoAuxDTO.getIndicador());
				bw.write(Character.toChars(34));
			} else {
				bw.write(Character.toChars(34));
				bw.write(Character.toChars(34));
			}

			bw.write(",");

			if(pgimIndicadorGeotecnicoAuxDTO.getValor() != null){
				bw.write(Character.toChars(34));
				bw.write(pgimIndicadorGeotecnicoAuxDTO.getValor());
				bw.write(Character.toChars(34));
			} else {
				bw.write(Character.toChars(34));
				bw.write(Character.toChars(34));
			}

			bw.write(",");

			if(pgimIndicadorGeotecnicoAuxDTO.getUnidadMedida() != null){
				bw.write(Character.toChars(34));
				bw.write(pgimIndicadorGeotecnicoAuxDTO.getUnidadMedida());
				bw.write(Character.toChars(34));
			} else {
				bw.write(Character.toChars(34));
				bw.write(Character.toChars(34));
			}

			bw.newLine(); 	

		}

		// Cerrar la escritura CSV
		bw.close();

	}
	
	@PostMapping("/descargarReporteIndicadorGeotecnico_v2")
	public ResponseEntity<byte[]> descargarReporteIndicadorGeotecnico_v2(@Valid @RequestBody PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO) throws Exception {

		byte[] archivo = documentoService.generarReporteIndicadorGeotecnicoEXCEL_v2(filtroIndicadorGeotecnicoAuxDTO);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroIndicadorGeotecnicoAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroIndicadorGeotecnicoAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/listarDetalleFiscalizaciones")
	public ResponseEntity<Page<PgimFiscaDetalleAuxDTO>> listarDetalleFiscalizaciones(@RequestBody PgimFiscaDetalleAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimFiscaDetalleAuxDTO> lPgimFiscaDetalleAuxDTO = this.documentoService.listarDetalleFiscalizaciones(filtro, paginador);
		
		return new ResponseEntity<Page<PgimFiscaDetalleAuxDTO>>(lPgimFiscaDetalleAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/generarReporteDetalleFiscalizaciones")
	public ResponseEntity<byte[]> generarReporteDetalleFiscalizaciones(@Valid @RequestBody PgimFiscaDetalleAuxDTO filtro) throws Exception {

		byte[] archivo = documentoService.generarReporteDetalleFiscalizaciones(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/generarReporteUMFiscalizables")
	public ResponseEntity<byte[]> generarReporteUMFiscalizables(@Valid @RequestBody PgimSupervisionDTO filtro) throws Exception {

		byte[] archivo = documentoService.generarReporteUMFiscalizablesEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/generarReportePasUMFiscalizables")
	public ResponseEntity<byte[]> generarReportePasUMFiscalizables(@Valid @RequestBody PgimPasAuxDTO filtro) throws Exception {

		byte[] archivo = documentoService.generarReportePasUMFiscalizablesEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@GetMapping("/obtenerRelacionConstNotificable/{idSubcatDocumento}/{idInstanciaProceso}/{idInstanciaPaso}/{nuExpedienteSiged}")
    public ResponseEntity<ResponseDTO> obtenerRelacionConstNotificable(@PathVariable Long idSubcatDocumento, @PathVariable Long idInstanciaProceso, @PathVariable Long idInstanciaPaso, @PathVariable String nuExpedienteSiged) throws Exception {

		Map<String, Object> respuestaLog = new HashMap<>();
        List<PgimRelacionscNotifDTO> pgimRelacionscNotifDTO = null;
		List<PgimDestinatarioDocDTO> lPgimDestinatarioDocDTOLista = new ArrayList<>();

		if(idInstanciaProceso != null){
			respuestaLog = this.flujoTrabajoService.mostrarLogPorInstanciaPaso(idInstanciaProceso, idInstanciaPaso, this.obtenerAuditoria().getUsername());
		}else {
			respuestaLog.put("codigoObjeto", this.obtenerAuditoria().getUsername());
		}

        try {
            pgimRelacionscNotifDTO = this.documentoService.obtenerRelacionConstNotificable(idSubcatDocumento);
           
			for(PgimRelacionscNotifDTO dto: pgimRelacionscNotifDTO){
				
				Long idSubcatDocNotificable = dto.getIdSubcatDocNotificable();

				List<PgimDestinatarioDocDTO> lPgimDestinatarioDocDTO = this.documentoService.listarDestinatarioDoc(idSubcatDocNotificable, idInstanciaProceso, nuExpedienteSiged, this.obtenerAuditoria());

				for(PgimDestinatarioDocDTO pgimDestinatarioDocDTO: lPgimDestinatarioDocDTO){

						lPgimDestinatarioDocDTOLista.add(pgimDestinatarioDocDTO);
				}				
			}

        } catch (DataAccessException e) {

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMessage() + "]" );
            log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta del documento" + e.getMostSpecificCause().getMessage()));

        }catch (final PgimException e) {

			log.error(respuestaLog.get("codigoObjeto").toString() + ".[" + e.getMensaje() + "]" );
			log.error(e.getMensaje(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(e.getTipoResultado(), e.getMensaje()));

		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, lPgimDestinatarioDocDTOLista, "Genial, se obtuvo el documento notificable"));
    }

	@GetMapping("/obtenerDestinatarioDocNotificable/{idDocumentoConstancia}")
    public ResponseEntity<ResponseDTO> obtenerDestinatarioDocNotificable(@PathVariable Long idDocumentoConstancia) {

        PgimDestinatarioDocDTO pgimDestinatarioDocDTO = null;

        try {
            pgimDestinatarioDocDTO = this.documentoService.obtenerDestinatarioDocNotificable(idDocumentoConstancia);
           
        } catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta del documento constancia" + e.getMostSpecificCause().getMessage()));
        }

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, pgimDestinatarioDocDTO, "Genial, se obtuvo el documento constancia"));
    }

	@GetMapping("/obtenerConfigPasoSubcat/{idSubcatDocumento}/{idPasoProceso}")
    public ResponseEntity<ResponseDTO> obtenerConfigPasoSubcat(@PathVariable Long idSubcatDocumento, @PathVariable Long idPasoProceso) {

        PgimPasoSubcatDTO pgimPasoSubcatDTO = null;
		List<PgimPasoSubcatDTO> lPgimPasoSubcatDTO = null;
        try {
            pgimPasoSubcatDTO = this.documentoService.obtenerConfigPasoSubcat(idSubcatDocumento, idPasoProceso);
			lPgimPasoSubcatDTO = this.documentoService.listarConfigPasoSubcat();
        } catch (DataAccessException e) {

            log.error(e.getMostSpecificCause().getMessage(), e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al realizar la consulta de la configuración del paso de la subcategoria" + e.getMostSpecificCause().getMessage()));
        }

		Object[] objeto = {lPgimPasoSubcatDTO, pgimPasoSubcatDTO};

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.SUCCESS, objeto, "Genial, se obtuvo configuración del paso de la subcategoria"));
    }

	@PutMapping("/modificarDestinatarioDoc")
    public ResponseEntity<ResponseDTO> modificarDestinatarioDoc(@Valid @RequestBody PgimDestinatarioDocDTO pgimDestinatarioDocDTO, BindingResult resultadoValidacion) throws Exception {

        PgimDestinatarioDoc pgimDestinatarioDocActual = null;
        PgimDestinatarioDocDTO pgimDestinatarioDocDTOModificado = null;

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Se han encontrado inconsistencias para modificar El destinatario documento" +  errores.toString()));
        }

		/* Obtengo el id del documento destinatario anteriormente seleccionado y procedemos a actualizar como null */
		if(pgimDestinatarioDocDTO.getIdDestinatarioDoc() != null){

			try {
					pgimDestinatarioDocActual = this.documentoService.getByIdDestinatarioDoc(pgimDestinatarioDocDTO.getIdDestinatarioDoc());

				if (pgimDestinatarioDocActual == null) {

					return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, String.format("El destinatario documento %s que intenta actualizar no existe en la base de datos", pgimDestinatarioDocDTO.getIdPersona())));
				
				}
			} catch (DataAccessException e) {

				log.error(e.getMostSpecificCause().getMessage(), e);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar recuperar El destinatario documento a actualizar" + e.getMostSpecificCause().getMessage()));
			
			}

			try {
				
				pgimDestinatarioDocDTOModificado = this.documentoService.modificarDestinatarioDoc(pgimDestinatarioDocDTO, pgimDestinatarioDocActual,
						this.obtenerAuditoria());

			} catch (DataAccessException e) {

				log.error(e.getMostSpecificCause().getMessage(), e);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar modificar El destinatario documento" + e.getMostSpecificCause().getMessage()));
			
			}
		}

		/* Obtengo el id del destinatario del documento recien seleccionado en el frontend del combo y actualizo en la logica */
		if(pgimDestinatarioDocDTO.getIdDestinatarioDocActual() != null){

			try {
				pgimDestinatarioDocActual = this.documentoService.getByIdDestinatarioDoc(pgimDestinatarioDocDTO.getIdDestinatarioDocActual());
	
				if (pgimDestinatarioDocActual == null) {

					return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, String.format("El destinatario documento %s que intenta actualizar no existe en la base de datos", pgimDestinatarioDocDTO.getIdPersona())));

				}
			} catch (DataAccessException e) {

				log.error(e.getMostSpecificCause().getMessage(), e);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error intentar recuperar El destinatario documento a actualizar" + e.getMostSpecificCause().getMessage()));
			}
	
			try {

				pgimDestinatarioDocDTOModificado = this.documentoService.modificarDestinatarioDoc2(pgimDestinatarioDocDTO, pgimDestinatarioDocActual, this.obtenerAuditoria());

			} catch (DataAccessException e) {

				log.error(e.getMostSpecificCause().getMessage(), e);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(TipoResultado.ERROR, "Ocurrió un error al intentar modificar El destinatario documento" + e.getMostSpecificCause().getMessage()));
			
			}
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(TipoResultado.SUCCESS, pgimDestinatarioDocDTOModificado, "Genial, el destinatario documento ha sido modificada"));

    }
}
