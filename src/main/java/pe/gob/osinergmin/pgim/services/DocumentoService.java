package pe.gob.osinergmin.pgim.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import gob.osinergmin.siged.remote.rest.ro.base.BaseOutRO;
import gob.osinergmin.siged.remote.rest.ro.in.ReenviarSubFlujoInRO;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado;
import pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFiscaDetalleAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorGeotecnicoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoSubcatDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionscNotifDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.ReporteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.dtos.RevisionInforme;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimDestinatarioDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimDocEtiquetaNotif;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.siged.ArchivoAnularInRO;
import pe.gob.osinergmin.pgim.siged.ArchivoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.Archivos;
import pe.gob.osinergmin.pgim.siged.ClienteConsulta;
import pe.gob.osinergmin.pgim.siged.ClienteConsultaOutRO;
import pe.gob.osinergmin.pgim.siged.ConsultaListadoDocumento;
import pe.gob.osinergmin.pgim.siged.DatosRevertirFirmaDigitalInRO2;
import pe.gob.osinergmin.pgim.siged.DescargaArchivo;
import pe.gob.osinergmin.pgim.siged.DevolverExpedienteInRO;
import pe.gob.osinergmin.pgim.siged.DevolverExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.Documento;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularInRO;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.DocumentoConsultaOutRO;
import pe.gob.osinergmin.pgim.siged.DocumentoNuevo;
import pe.gob.osinergmin.pgim.siged.DocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.Documentos;
import pe.gob.osinergmin.pgim.siged.EnumerarDocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.Expediente;
import pe.gob.osinergmin.pgim.siged.ExpedienteAprobadoIn;
import pe.gob.osinergmin.pgim.siged.ExpedienteDocOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteListarPorUsuarioOut;
import pe.gob.osinergmin.pgim.siged.ExpedienteNuevo;
import pe.gob.osinergmin.pgim.siged.ExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteReenvio;
import pe.gob.osinergmin.pgim.siged.ExpedienteSiged;
import pe.gob.osinergmin.pgim.siged.ResultadoRevertirFirmaDigital2;
import pe.gob.osinergmin.pgim.siged.Tiposdocumento;
import pe.gob.osinergmin.pgim.siged.TrazabilidadDocumentoDTO;
import pe.gob.osinergmin.pgim.siged.UnidadesOutRo;
import pe.gob.osinergmin.pgim.utils.ClaveValorUtil;
import pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ListarTrazabilidadDocumentosResponse;
import pe.gob.osinergmin.soa.service.expediente.documentos.revertirfirma.v1.RevertirFirmaResponse;

/**
 * @author Luis Barrantes
 *
 */
public interface DocumentoService {

	/**
	 * Permite obtener el documento de acuerdo con su identificador interno.
	 * @param idDocumento
	 * @return
	 */
	public PgimDocumentoDTO obtenerDocumentoById(Long idDocumento);

	/**
	 * Permite recuperar la lista de documentos y archivos asociados a una instancia
	 * de paso de un flujo dado
	 * 
	 * @param coTablaInstancia
	 * @param idProceso
	 * @param idFaseProceso
	 * @param idSubcatDocumento
	 * @param sort
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> verDocumentos(Long coTablaInstancia, Long idProceso, Long idFaseProceso,
			Long idSubcatDocumento, Sort sort, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * 
	 */
	public Map<String, Object> verDocumentos(PgimDocumentoDTO pgimDocumento, Sort sort, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite obtener el listado de destinatarios de documentos a notificar
	 * @param pgimDocumento
	 * @return
	 * @throws Exception
	 */
	List<PgimInfraccionContraDTO> listarDestinatarios(PgimDocumentoDTO pgimDocumento)	throws Exception;

	/**
	 * Permite definir los destinatarios de documentos a notificar.
	 * @param lPgimDestinatarioDocDTO
	 * @return
	 * @throws Exception
	 */
	List<PgimDestinatarioDocDTO> definirDestinatarios(PgimDestinatarioDocDTO[] lPgimDestinatarioDocDTO, Long idDocumento, AuditoriaDTO auditoriaDTO)	throws Exception;

	/**
	 * Obtiene los datos de una instancia proceso por su identificador
	 * 
	 * @param idInstanciaProceso
	 * @return
	 */
	PgimInstanciaProces obtenerInstanciaProceso(Long idInstanciaProceso);

	/**
	 * graba los datos de un documento
	 * 
	 * @param documentoDTO
	 * @param auditoriaDTO
	 * @return
	 */
	Long registrarDocumento(PgimDocumentoDTO documentoDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite guardar o incluir documento
	 * 
	 * @param documentoDTO
	 * @return
	 */
	Long incluirDocumento(PgimDocumentoDTO documentoDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Listar Documentos de Expediente Permitira obtener el detalle de todos los
	 * documentos(no anulados) que pertenecen a un expediente, con files=1 mostrara
	 * adicionalmente el detalle de los archivos(no anulados) de cada documento.
	 * 
	 * @param nroexp: Número del expediente
	 * @param files:  puede tomar 1 o 0. 1:incluir archivos de documentos, 0: omitir
	 *                archivos de documentos
	 * @return
	 * @throws Exception
	 */

	public DocumentoOutRO agregarDocumentoSiged(DocumentoNuevo docNuevoSiged, MultipartFile multipartFile,
			AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite agregar un nuevo documento a un expediente Siged directamente a través de los servicios del Siged-Rest-old 
	 * 
	 * @param docNuevoSiged
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	public DocumentoOutRO agregarDocumentoSiged_old(DocumentoNuevo docNuevoSiged, MultipartFile multipartFile) throws Exception;

	/**
	 * Permite obtener los documentos que contienen un expediente Siged, para
	 * incluir archivo el parámetro files = "1" de lo contrario "0"
	 * 
	 * @param nroExp
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public Documentos obtenerExpedienteDocumentoSiged(String nroExp, String files, AuditoriaDTO auditoriaDTO)
			throws Exception;

	// long obtenerTrazabilidad(String nroExp, AuditoriaDTO auditoriaDTO)
	// throws Exception;
	/**
	 * Permite listar la trazabilidad de un documento SIGED
	 * 
	 * 
	 * @param sIdDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public ListarTrazabilidadDocumentosResponse listarTrazabilidad(String sIdDocumento, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite obtener los documentos partenecientes al PGIM, por el identificador
	 * de la instancia del proceso
	 * 
	 * @param idInstanciaProceso
	 * @return
	 */
	List<PgimDocumentoDTO> obtenerDocumentosPgim(Long idInstanciaProceso);

	/**
	 * Permite crear un expediente Siged, requiere un archivo adjunto que será el
	 * primer documento
	 * 
	 * @param expNuevoSiged
	 * @param multipartFile
	 * @return
	 */
	public ExpedienteOutRO expedienteCrearSiged(ExpedienteNuevo expNuevoSiged, MultipartFile multipartFile,
			AuditoriaDTO auditoriaDTO);

	/**
	 * Permite crear un documento en PGIM
	 * 
	 * @param documentoDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimDocumentoDTO crearDocumentoPGIM(PgimDocumentoDTO documentoDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * ermite obtener los expedoentes de acuerdo al identificados de la supervisión
	 * 
	 * @param idSupervision
	 * @return
	 */
	List<PgimInstanciaProcesDTO> obtenerExpedientes(Long idSupervision);
	
	/**
	 * Permite descargar un archivo directamente desde los servicios del Siged-Rest-old con el id de
	 * archivo en ese mismo sistema
	 * 
	 * @param idarchivo
	 * @return
	 * @throws IOException
	 */
	public DescargaArchivo descargaArchivo_old(String idarchivo) throws IOException;

	/**
	 * Permikte descargar un archivo desde los servicios del Siged con el id de
	 * archivo en ese mismo sistema
	 * 
	 * @param idarchivo
	 * @return
	 * @throws IOException
	 */
	public DescargaArchivo descargaArchivo(String idarchivo, AuditoriaDTO auditoriaDTO) throws IOException;

	/**
	 * Perite obtener la lista de documentos existentes en el Siged a partir del
	 * identificador de un documento PGIM
	 * 
	 * @param idDocumento
	 * @return
	 * @throws Exception
	 */
	public Archivos obtenerArhivosSiged(String idDocumento, AuditoriaDTO auditoriaDTO) throws Exception;	
	
	/**
	 * Permite obtener la lista de archivos con sus versiones existentes en el Siged a partir del
	 * identificador del documento
	 * 
	 * @param idDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public Archivos obtenerArhivosConVersionesSiged(String idDocumento, AuditoriaDTO auditoriaDTO) throws Exception;	
	
	/**
	 * Permite agregar un nuevo archivo a un expediente Siged directamente a través de los servicios del Siged-Rest-old 
	 * @param documento
	 * @param versionar
	 * @param multipartFile
	 * @param NuExpedienteSiged
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public Archivos agregarArchivoSiged_old(Documento documento, String versionar, MultipartFile multipartFile,
			String NuExpedienteSiged, Long idProceso, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite agregar un nuevo archivo a un expedientes Siged
	 * 
	 * @param documento
	 * @param versionar
	 * @param multipartFile
	 * @param NuExpedienteSiged
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public Archivos agregarArchivoSiged(Documento documento, String versionar, MultipartFile multipartFile,
			String NuExpedienteSiged, Long idProceso, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Anula o elimina un documentos Siged
	 * 
	 * @param inRO
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public DocumentoAnularOutRO anularDocumentoSiged(DocumentoAnularInRO inRO, Long idProceso,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Anula o elimina un archivo de un documento Siged, si es el único archivo del
	 * documento presenta error.
	 * 
	 * @param archivoAnularInRO
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public ArchivoAnularOutRO anularArchivoSIGED(ArchivoAnularInRO archivoAnularInRO, Long idProceso,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite eliminar un documento de la PGIM
	 * 
	 * @param pgimDocumentoActual
	 * @param auditoriaDTO
	 */
	void eliminarDocumentoPgim(PgimDocumento pgimDocumentoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite registrar el el documento a sido firmado o revertido la firma.
	 * @param pgimDocumento
	 * @param fgFirmado
	 * @param auditoriaDTO
	 */
	public void registrarFlFirmadoDoc(PgimDocumento pgimDocumento, String fgFirmado, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener un documento de la PGIM por su identificador.
	 * 
	 * @param idDocumento
	 * @return
	 */
	PgimDocumento obtenerDocumentoPgim(Long idDocumento);

	/**
	 * Permite actualizar un documento de la PGIM
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProces
	 * @param pgimDocumento
	 * @param auditoriaDTO
	 * @return
	 */
	PgimDocumentoDTO modificarDocumento(PgimDocumentoDTO pgimDocumentoDTO, PgimInstanciaProces pgimInstanciaProces,
			PgimDocumento pgimDocumento, AuditoriaDTO auditoriaDTO);
	
	/**
	 * Permite actualizar un entity de documento
	 * 
	 * @param pgimDocumento
	 * @param auditoriaDTO
	 * @return
	 */
	PgimDocumento modificarDocumentoEntity(PgimDocumento pgimDocumento, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite eliminar el documento PGIM.
	 * 
	 * @param idDocumentoPGIM Identificador interno del documento PGIM.
	 */
	void eliminarDocumento(Long idDocumentoPGIM, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener un documento de la PGIM por el identificador del documento
	 * 
	 * @param idDocumento
	 * @return
	 */
	PgimDocumentoDTO obtenerDocumentoPgimById(Long idDocumento);

	List<PgimDocumentoDTO> obtenerDocumentosLiquidacion(Long idSupervision, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite procesar la creación de un nuevo documento Siged al mismo tiempo que,
	 * de ser preciso, se crea un nuevo expediente.
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param fileDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarAdjuntadoDocumento(PgimDocumentoDTO pgimDocumentoDTO,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO, MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite procesar el reenvío de un expedinete Siged
	 * 
	 * @param expedienteReenvio: Objeto con los parámetros para el reenvío
	 * @param ExpedienteOutRO:   Resultados obtenido desde el servicio SIGED
	 * 
	 * @return
	 * @throws Exception
	 */
	ExpedienteOutRO reenviarExpedienteSiged(ExpedienteReenvio expedienteReenvio, AuditoriaDTO auditoriaDTO)
			throws Exception;	

	/**
	 * Permite procesar el reenvío de un expediente SIGED con subflujo
	 * uso directo del Siged-Rest-old 
	 * 
	 * @param reenviarSubFlujoInRO	Objeto con los parámetros para el reenvío con subflujo
	 * @param auditoriaDTO			Objeto auditoría
	 * @return	BaseOutRO			Resultados obtenido desde el servicio SIGED
	 * @throws Exception
	 */
	BaseOutRO reenviarSubflujoExpedienteSIGED_old(ReenviarSubFlujoInRO  reenviarSubFlujoInRO, AuditoriaDTO auditoriaDTO) 
			throws Exception;

	/**
	 * Permite validar que el usuario que realiza una acciòn sea el propietario
	 * actual del expediente en el Siged
	 * 
	 * @param expedienteSiged : Objeto con datos del especiente
	 * @param accion          : Acción que se va aejecutar, se usa para devolver una
	 *                        respuesta personalizada en caso de no ser el
	 *                        propietario
	 * @param idProceso       : Id del tipo de proceso de la instancias de proceso
	 * 
	 * @return
	 * @throws Exception
	 */
	ExpedienteDocOutRO validarUsuarioPropietarioExpedienteSiged(ExpedienteSiged expedienteSiged, Long idProceso,
			String accion, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener el plazo de aprobación de acuerdo a la instancia del proceso,
	 * categoría del documentos y relación de paso
	 * 
	 * @param idInstanciaProceso
	 * @param idSubcatDocumento
	 * @param idRelacionPaso
	 * @return
	 * @throws Exception
	 */
	PgimDocumentoDTO obtenerAprobacionResultadoPlazo(Long idInstanciaProceso, Long idSubcatDocumento,
			Long idRelacionPaso) throws Exception;

	/**
	 * Permite enumerar los documentos en el Siged
	 * 
	 * @param nroExp      : Número de expediente Siged
	 * @param idDocumento : Codigo del documentos Siged que será numerado
	 * @param idUsuario   : Codigo de usuario Siged que realiza la acción
	 * 
	 * @return
	 * @throws Exception
	 */
	// public EnumerarDocumentoOutRO enumeraDocumentoSiged(String nroExp, String
	// idDocumento, String idUsuario)
	public EnumerarDocumentoOutRO enumeraDocumentoSiged(Long idDocumento, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite procesar el reemplazo del archivo principal de un documento Siged ,
	 * aplica para los casos de documentos generados desde la PGIM. Toma el archivo
	 * desde el mismo servidor conforme al id o código del documento, el documento
	 * debe ser de tipo PDF
	 * 
	 * @param documentoNuevo
	 * @param idDocumentoSiged
	 * @param idProceso
	 * @param fileDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarReemplazoDocumentoSiged(DocumentoNuevo documentoNuevo, String idDocumentoSiged,
			Long idProceso, AuditoriaDTO auditoriaDTO, ExpedienteDocOutRO elementosExpediente) throws Exception;	

	/** 
	 * Permite agregar una nueva versión de un documento al Siged, 
	 * es decir el documento mantiene el id archivo
	 * uso directo del Siged-Rest-old 
	 * 
	 * @param docNuevoSiged
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	DocumentoOutRO agregarReemplazoDocumentoSiged_old(DocumentoNuevo docNuevoSiged, MultipartFile multipartFile)
			throws Exception;

	/**
	 * Permite agregar una nueva versión de un documento al Siged, es decir el
	 * documento mantiene el id archivo
	 * 
	 * @param docNuevoSiged
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	DocumentoOutRO agregarReemplazoDocumentoSiged(DocumentoNuevo docNuevoSiged, MultipartFile multipartFile,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite procesar el reemplazo del archivo principal de un documento Siged ,
	 * aplica para los casos de documentos generados desde la PGIM. requiere el
	 * archivo emviado por el usuario desde la PGIM, el documento debe ser de tipo
	 * PDF
	 *
	 * @param docNuevoSiged
	 * @param idDocumentoSiged
	 * @param idProceso
	 * @param auditoriaDTO
	 * @param fileDocumento
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarReemplazoDocumentoSiged(DocumentoNuevo docNuevoSiged, String idDocumentoSiged,
			Long idProceso, AuditoriaDTO auditoriaDTO, MultipartFile fileDocumento,
			ExpedienteDocOutRO elementosExpediente) throws Exception;

	/**
	 * Permite la generación de documentos de acuerdo a sus características y del
	 * flujo al cual pertenecen, incluye su carga en el Siged
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarGenerarDocumento(PgimDocumentoDTO pgimDocumentoDTO,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite la generación de documentos de acuerdo a sus características y del
	 * flujo al cual pertenecen, incluye su carga en el Siged - Liquidación
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarGenerarDocumentoLiquidacion(PgimDocumentoDTO pgimDocumentoDTO,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	// STORY: PGIM-6244: Generación y descarga de formato editable y procesado por la PGIM
	public byte[] generarDJInstrumMedicion(String ruta, PgimSupervisionDTO pgimSupervisionDTO, List<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO,
			Long idTipoExtensionGen, PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO) throws IOException, JSONException, XmlException;

	// STORY: PGIM-6244: Generación y descarga de formato editable y procesado por la PGIM
	public byte[] generarDocumentoCredencial(List<PgimEqpInstanciaProDTO> lstPgimEqpInstanciaProDTO,
			PgimUnidadMineraDTO pgimUnidadMineraDTO, PgimSupervisionDTO pgimSupervisionDTO,
			PgimInstanciaProces pgimInstanciaProces, String strSupervisor,
			List<PgimDemarcacionUmineraDTOResultado> demarcaciones, Long idTipoExtensionGen) throws Exception;

	/**
	 * Permite la generación de la ficha de observaciones al informe de supervisión,
	 * se ejecuta luego del método generico "procesarGenerarDocumento" y permite
	 * completar el documento con los datos completos de la observación, tambié se
	 * usa en la actualización de la ficha.
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> generarFichaObsInfSupCompleta(PgimDocumentoDTO pgimDocumentoDTO,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO,
			List<PgimFichaObservacionDTO> lPgimFichaObservacionDTOCreado, Long idDocInformeFisc) throws Exception;

	/**
	 * Permite la generación de la ficha de observaciones al informe de supervisión,
	 * se ejecuta luego del método generico "procesarGenerarDocumento" y permite
	 * completar el documento con los datos completos de la observación, tambié se
	 * usa en la actualización de la ficha.
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<ResponseDTO> generarFichaConfInfSupCompleta(PgimDocumentoDTO pgimDocumentoDTOActual,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual, AuditoriaDTO auditoriaDTO,
			RevisionInforme revisionInformeCreado, Long idDocInformeFisc) throws Exception;

	/**
	 * Permite obtener los permisos que tiene un usuario Siged dentro de un
	 * expediente
	 * 
	 * @param coTablaInstancia
	 * @param idProceso
	 * @param idFase
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> obtenerPermisosExpediente(Long coTablaInstancia, Long idProceso, Long idFase,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener la lista de Fichas de revisión (ficha de conformidad) por Id
	 * de instancia de proceso
	 */
	List<PgimFichaRevisionDTO> obtenerListFichaConformidadPorInstanciaProc(Long idInstanciaProceso);

	/**
	 * Permite obtener la lista de Fichas de revisión (ficha de observación con
	 * observaciones NO subsanadas) por Id de instancia de proceso
	 */
	List<PgimFichaRevisionDTO> obtenerListFichaObservacionNoSubsanadasPorInstanciaProc(Long idInstanciaProceso);

	byte[] generarMatrizSupervisionPlantilla(String ruta, FiltroMatrizSupervisionDTO filtroMatrizSupervisionDTO,
			Long idTipoExtensionGen) throws Exception;

	public byte[] generarFichaHecCons(String ruta, PgimSupervisionDTO pgimSupervisionDTO, List<PgimHechoConstatadoDTO> listaHechos, List<PgimHechoConstatadoDTO> listaHechosIncumplimientos, String tipoHC, Long idTipoExtensionGen) throws IOException, JSONException, XmlException, Exception;

	byte[] generarDocRecibidosPlantilla(String ruta, PgimSupervisionDTO pgimSupervisionDTO, Long idTipoExtensionGen)
			throws Exception;

	byte[] generarDocRequerimientoPlantilla(String ruta, PgimSupervisionDTO pgimSupervisionDTO, Long idTipoExtensionGen)
			throws Exception;
			
	byte[] generarFichaObsInfSupCompleta(String ruta, PgimDocumentoDTO pgimDocumentoDTO, PgimSupervisionDTO pgimSupervisionDTO, PgimInstanciaProces pgimInstanciaProces, PgimUnidadMineraDTO pgimUnidadMineraDTO, List<PgimFichaObservacionDTO> lPgimFichaObservacionDTOCreado, Long idTipoExtensionGen, String nuInforme) throws IOException, JSONException, XmlException, Exception;

	/**
	 * Permite revertir la firma digital de un documento Siged
	 * 
	 * @param inRo
	 * @param idDocumentoSiged
	 * @param nroExpedienteSiged
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 * @throws PgimException
	 */
	public ResultadoRevertirFirmaDigital2 revertirFirmaDocumentoSiged(DatosRevertirFirmaDigitalInRO2 inRo,
			String nroExpedienteSiged, String idDocumentoSiged, Long idProceso, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite revertir la firma digital de un documento Siged por la selección de
	 * un archivo determinado
	 * 
	 * @param inRo
	 * @param nroExpedienteSiged
	 * @param idArchivoSiged
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 */
	public ResultadoRevertirFirmaDigital2 revertirFirmaArchivoSiged_old(DatosRevertirFirmaDigitalInRO2 inRo,
			String nroExpedienteSiged, String idArchivoSiged, Long idProceso, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite revertir la firma digital de un documento Siged por la selección de
	 * un archivo determinado
	 * 
	 * @param nroExpedienteSiged
	 * @param idArchivoSiged
	 * @param motivoReversion
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 */
	public RevertirFirmaResponse revertirFirmaArchivoSiged(Long idDocumentoPGIM, String nroExpedienteSiged, Long idArchivoSiged,
			String motivoReversion, Long idProceso, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite devolver un expediente del Siged
	 * 
	 * @param devolverExpedienteInRO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public DevolverExpedienteOutRO devolverExpedienteSiged(DevolverExpedienteInRO devolverExpedienteInRO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite aprobar un expediente del Siged
	 * 
	 * @param expedienteAprobadoIn
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public ExpedienteOutRO aprobarExpedienteSiged(ExpedienteAprobadoIn expedienteAprobadoIn, AuditoriaDTO auditoriaDTO)
			throws Exception;
	
	/**
	 * Permite archivar un expediente Siged directamente a través de los servicios del Siged-Rest-old
	 * 
	 * @param expediente
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public ExpedienteOutRO archivarExpedienteSiged_old(Expediente expediente, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite descargar una versión de determinado archivo
	 * 
	 * @param idArchivo
	 * @param labelVersion
	 * @return
	 * @throws IOException
	 */
	public DescargaArchivo descargarVersionArchivo_old(String idArchivo, String labelVersion) throws IOException;
	
	PgimSubcategoriaDocDTO obtenerSubcategoriaDocPorId(Long idSubcatDocumento);

	byte[] generarDocumentoRanking(Long idRankingRiesgo, String coAnonimizacion) throws Exception;

	/**
	 * Permite generar el formato de riesgos de la supervisión
	 * 
	 * @param ruta
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarFormatoRiesgos(String ruta, PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO, Long idTipoExtensionGen)
			throws Exception;

	/**
	 * Permite notificar un documento dado al agente supervisado relacionado con él.
	 * 
	 * @param pgimDocumentoDTO
	 * @param obtenerAuditoria
	 * @return
	 */
	public ResponseEntity<ResponseDTO> notificarDocumentoPersonaJuridica(PgimDocumentoDTO pgimDocumentoDTO,
			AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite la generación del formato de riesgos de la supervisión, incluye su
	 * carga en el Siged - Supervisión
	 * 
	 * @param pgimRankingUmGrupoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarGeneracionFormatoRiesgos(PgimRankingUmGrupoDTO pgimRankingUmGrupoDTO,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO) throws Exception;
	

	/**
	 * Permite consultar el cliente desde el SIGED-REST-OLD
	 * 
	 * @param clienteConsulta: Objeto con los parámetros para la consulta del
	 *                         cliente
	 * 
	 * @return clienteConsultaOutRO
	 * @throws Exception
	 */
	ClienteConsultaOutRO buscarCliente(ClienteConsulta clienteConsulta)// , AuditoriaDTO auditoriaDTO)
			throws Exception;

	ExpedienteListarPorUsuarioOut listarExpedientePorUsuario(String estado, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite listar los expedientes de un usuario desde el Siged-rest-old, de acuerdo con los parámetros de entrada.
	 * Hace uso del servicio "Listar Expedientes por Usuario (V2)"
	 * 
	 * @param estado
	 * @param idProceso
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ExpedienteListarPorUsuarioOut listarExpedientePorUsuarioV2_old(String estado, String idProceso, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite obtener la lista paginada de expedientes de un usuario haciendo uso del método de integración con el Siged-rest-old correspondiente, 
	 * de acuerdo con los parámetros filtro
	 * 
	 * @param filtro
	 * @param paginador
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> obtenerListaExpedientePorUsuario(ExpedienteListarPorUsuarioOut filtro, Pageable paginador,
			AuditoriaDTO auditoriaDTO) throws Exception;

	Map<String, Object> obtenerDocumentoSigedPorExpediente(String nroExp, String files, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite listar la trzabilidad por el número de expediente
	 */
	List<TrazabilidadDocumentoDTO> listarTrazabilidadPorExpediente(String nroExp, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite listar las unidades del SIGED
	 */
	UnidadesOutRo listarAreasSiged() throws Exception;

	/**
	 * 
	 * Permite generar un reporte tabular estándar de listado de entidades en
	 * formato pdf
	 * 
	 * @param reporteAuxDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarReporteListadoPdf(ReporteAuxDTO<?> reporteAuxDTO) throws Exception;

	/**
	 * Permite generar un reporte tabular estándar de listado de entidades en
	 * formato excel
	 * 
	 * @param reporteAuxDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarReporteListadoExcel(ReporteAuxDTO<?> reporteAuxDTO) throws Exception;

	/**
	 * Permite obtener el documento de ficha informativa de la unidad minera
	 * 
	 * @param ruta
	 * @param pgimUnidadMineraDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws Exception
	 */
	byte[] generarFichaInformativaUnidadMinera(String ruta, PgimUnidadMineraDTO pgimUnidadMineraDTO,
			Long idTipoExtensionGen) throws Exception;

	/**
	 * Permite obtener el reporte de componentes de la unidad minera
	 * 
	 * @param ruta
	 * @param pgimUnidadMineraDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws Exception
	 */
	byte[] generarFichaComponentesUnidadMinera(String ruta, PgimUnidadMineraDTO pgimUnidadMineraDTO,
			Long idTipoExtensionGen) throws Exception;

	/**
	 * Permite obtener en formato pdf el reporte "Penalidades por periodo contrato y
	 * supervisora"
	 * 
	 * @param ruta
	 * @param filtroPenalidadAuxDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws XmlException
	 */
	byte[] generarReportePenalidadPeriodoContratoSupervisoraPDF(String ruta, PgimPenalidadAuxDTO filtroPenalidadAuxDTO,
			Long idTipoExtensionGen) throws IOException, JSONException, XmlException;

	/**
	 * Permite obtener en formato pdf el reporte "Control de saldos por contrato y
	 * supervisora"
	 * 
	 * @param ruta
	 * @param filtroContratoSegumntoAuxDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws XmlException
	 */
	byte[] generarReporteControlSaldoContratoSupervisoraPDF(String ruta,
			PgimContratoSegumntoAuxDTO filtroContratoSegumntoAuxDTO, Long idTipoExtensionGen)
			throws IOException, JSONException, XmlException;

	/**
	 * Permite obtener en formato pdf el reporte "Ejecucion presupuestal"
	 * 
	 * @param ruta
	 * @param filtroContratoSiafAuxDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws XmlException
	 */
	byte[] generarReporteEjecucionPresupuestalPDF(String ruta, PgimContratoSiafAuxDTO filtroContratoSiafAuxDTO,
			Long idTipoExtensionGen) throws IOException, JSONException, XmlException;

	/**
	 * Permite obtener en formato xlsx el reporte "Ejecucion presupuestal"
	 * 
	 * @param filtroContratoSiafAuxDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarReporteEjecucionPresupuestalEXCEL(PgimContratoSiafAuxDTO filtroContratoSiafAuxDTO) throws Exception;

	/**
	 * Permite obtener en formato xlsx el reporte "Control de saldos por contrato y
	 * supervisora"
	 * 
	 * @param filtroContratoSegumntoAuxDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarReporteControlSaldoContratoSupervisoraEXCEL(PgimContratoSegumntoAuxDTO filtroContratoSegumntoAuxDTO)
			throws Exception;

	/**
	 * Permite obtener en formato xlsx el reporte "Penalidades por periodo contrato
	 * y supervisora"
	 * 
	 * @param filtroPenalidadAuxDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarReportePenalidadPeriodoContratoSupervisoraEXCEL(PgimPenalidadAuxDTO filtroPenalidadAuxDTO)
			throws Exception;

	/**
	 * Permite obtener en formato xlsx el reporte "Presupuesto y gasto de
	 * supervisión"
	 * 
	 * @param filtroPrspstoGastoSuperDTO
	 * @return
	 * @throws Exception
	 */
	byte[] generarReportePresupuestoGastoSupervisionEXCEL(PgimPrspstoGastoSuperDTO filtroPrspstoGastoSuperDTO)
			throws Exception;

	/**
	 * Permite obtener en formato pdf el reporte "Presupuesto y gasto de
	 * supervisión"
	 * 
	 * @param ruta
	 * @param filtroPrspstoGastoSuperDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws XmlException
	 */
	byte[] generarReportePresupuestoGastoSupervisionPDF(String ruta,
			PgimPrspstoGastoSuperDTO filtroPrspstoGastoSuperDTO, Long idTipoExtensionGen)
			throws IOException, JSONException, XmlException;

	/**
	 * Permite filtrar los documentos de acuerdo con los criterios filtro
	 * especificados en la propiedad pgimFiltroDocumento
	 * 
	 * @param pgimDocumentoDTO
	 * @param lPgimDocumentoDTOResultado
	 * @return
	 */
	List<PgimDocumentoDTO> filtrarDocumentos(PgimDocumentoDTO pgimDocumentoDTO,
			List<PgimDocumentoDTO> lPgimDocumentoDTOResultado);
	
	/**
	 * Permite la generación y descarga del formato de revisón de antecedente
	 * 
	 * @param ruta
	 * @param pgimUnidadMineraDTO
	 * @param idTipoExtensionGen
	 * @return
	 * @throws Exception
	 */
	byte[] generarRevisionAntecedente(String ruta, Long idSupervision, Long idTipoExtensionGen,AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * 
	 * Permite procesar la creación de un nuevo documento Siged al mismo tiempo que,
	 * de ser preciso, se crea un nuevo expediente.
	 * 
	 * @param listaPgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> procesarCopiadoDocumento(PgimDocumentoDTO[] listaPgimDocumentoDTO, 
														 PgimInstanciaProcesDTO pgimInstanciaProcesDTO,
														 AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite procesar la creación de un nuevo documento Siged al mismo tiempo que,
	 * de ser preciso, se crea un nuevo expediente.
	 * @param listaPgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	void procesarCopiadoDocumento_old(PgimDocumentoDTO[] listaPgimDocumentoDTO, 
														 PgimInstanciaProcesDTO pgimInstanciaProcesDTO,
														 AuditoriaDTO auditoriaDTO)
			throws Exception;

	/**
	 * Permite procesar la copia de un documento existente en el Siged al mismo tiempo que,
	 * de ser preciso, se crea un nuevo expediente.
	 * @param pgimDocumentoDTOACopiar
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	void procesarCopiadoDocumentoAntecedenteSiged(PgimDocumentoDTO pgimDocumentoDTOACopiar, PgimDocumentoDTO pgimDocumentoDTO,
		PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO)
			throws Exception;

	/***
	 * Permite obtener la persona jurídica correspondiente con el agente fiscalizado involucrado en el Fiscalización o PAS
	 * @param idDocumento
	 * @return
	 * @throws Exception
	 */
    public PgimPersonaDTO obtenerPersonaAgenteFiscalizado(Long idDocumento) throws Exception;

	/***
	 * Permite obtener la lista de subcateogrías firmadas por la persona fiscalizadora, esto de acuerdo con la configuración 
	 * del flujo de trabajo de la fiscalización.
	 * @param idInstanciaProceso
	 * @return
	 */
    public List<PgimSubcategoriaDocDTO> listarSubcategoriasFirmadasPorFiscalizador(Long idInstanciaProceso);
        
    /**
     * Permite generar un número formateado para la numeración propia de determinado documento, 
     * en el marco de una intancia de proceso. 
     * 
     * @param pgimDocumentoDTO
     * @param pgimInstanciaProcesDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    public String generarNumeroDocFormateado(PgimDocumentoDTO pgimDocumentoDTO,
			PgimInstanciaProcesDTO pgimInstanciaProcesDTO, AuditoriaDTO auditoriaDTO)
			throws Exception;
       
    /**
     * Permite enumerar documentos utilizando la numeración oficial del Siged, en una transición de pasos del flujo
     * 
     * @param pgimInstanciaProces
     * @param pgimInstanciaPaso
     * @param auditoriaDTO
     * @throws Exception
     */
    void enumerarDocumentoSigedEnFlujo(PgimInstanciaProces pgimInstanciaProces,
            PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception;
    
    /**
     * Permite llamar al método que archiva un expediente Siged, en determinadas transiciones de paso del flujo
     * 
     * @param pgimInstanciaProces
     * @param pgimInstanciaPaso
     * @param auditoriaDTO
     * @throws Exception
     */
    void archivarExpedienteEnFlujo(PgimInstanciaProces pgimInstanciaProces,
            PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Agrega un pie de página al documento.
	 * 
	 * @param documento El documento al que se agregará el pie de página.
	 * @param fechaDeGeneracion La fecha en que se generó el documento.
	 * @param espacioAdicional Este es el espacio que se agregará al final de la página.
	 * @param orientacion "retrato" o "paisaje"
	 */
	public void getPieDePagina(XWPFDocument documento, String fechaDeGeneracion, Integer espacioAdicional, String orientacion) throws IOException, XmlException;

	/**
	 * Agrega un pie de página al documento, la fecha de generación y mejora en la posición de alineamiento de ambas propiedades.
	 * 
	 * @param documento El documento al que se agregará el pie de página.
	 * @param fechaDeGeneracion La fecha en que se generó el documento.
	 * @param espacioAdicional Este es el espacio que se agregará al final de la página.
	 * @param orientacion "retrato" o "paisaje"
	 */
	public void getFooterAlignBottomDocs(XWPFDocument documento, String fechaDeGeneracion, String orientacion, Integer alignBottom, Integer separador) throws IOException, XmlException;

	/**
	 * Permite obtener el paso en donde se debe permitir el etiquetado de documentos para ser notificados
	 * @param idInstanciaPasoActual
	 * @return
	 */
	public PgimPasoProcesoDTO etiquetarNotificacion(Long idInstanciaPasoActual);

	/**
	 * Permite obtener entity PgimDocEtiquetaNotif
	 * @param idDocEtiquetaNotif
	 * @return
	 */
	public PgimDocEtiquetaNotif obtenerEtiquetaDocNotif(Long idDocEtiquetaNotif);

	/**
	 * Permite etiquetar un documento para ser notificado
	 * @param pgimDocEtiquetaNotifDTO
	 * @param pgimDocEtiquetadoActual
	 * @param auditoriaDTO
	 * @return
	 */
	public PgimDocEtiquetaNotifDTO etiquetaDocNotif(PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifDTO, PgimDocEtiquetaNotif pgimDocEtiquetadoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite quitar etiqueta de notificación a un documento
	 * @param pgimDocEtiquetaNotifDTO
	 * @param pgimDocEtiquetadoActual
	 * @param auditoriaDTO
	 * @return
	 */
	public PgimDocEtiquetaNotifDTO quitarEtiquetaDocNotif(PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifDTO, PgimDocEtiquetaNotif pgimDocEtiquetadoActual, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite verificar a que unidad organica pertence el usuario firmante y proceder a enumerar el documento si lo es necesario.
	 * 
	 * @param pgimDocumentoDTO
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public String verificarUOrganicaYNumerar(PgimDocumentoDTO pgimDocumentoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

  /**
	 * Permite listar el reportes de Indicadores geotécnicos utilizando paginación
	 * @param filtroIndicadorGeotecnicoAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
    Page<PgimIndicadorGeotecnicoAuxDTO> listarIndicadorGeotecnico(PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO, Pageable paginador) throws Exception;

    /**
     * Permite obtener en formato xlsx el reporte "Indicadores geotécnicos"
     * 
     * @param filtroIndicadorGeotecnicoAuxDTO
     * @return
     * @throws Exception
     */
    byte[] generarReporteIndicadorGeotecnicoEXCEL(PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO) throws Exception;

	byte[] generarReporteIndicadorGeotecnicoEXCEL_v2(PgimIndicadorGeotecnicoAuxDTO filtroIndicadorGeotecnicoAuxDTO) throws Exception;

	/**
	 * Me permite listar los detalles de las fiscalizaciones que estan paginadas y contiene criterios de filtros avanzados
	 * @param pgimFiscaDetalleAuxDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimFiscaDetalleAuxDTO> listarDetalleFiscalizaciones (PgimFiscaDetalleAuxDTO pgimFiscaDetalleAuxDTO, Pageable paginador) throws Exception;

	/**
	 * Me permite generar el reporte de detalles de las fiscalizaciones que estan paginadas y criterios de filtros ya filtrados
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	public byte[] generarReporteDetalleFiscalizaciones(PgimFiscaDetalleAuxDTO filtro) throws Exception;

	/**
	 * Permite obtener un documento de la subcategoria constancia notificable
	 * 
	 * @param idSubcatDocumento
	 * @return
	 */
	List<PgimRelacionscNotifDTO> obtenerRelacionConstNotificable(Long idSubcatDocumento); 

	/**
	 * Me permite listar los documentos que se van a asociar las constancias a los documentos notificables
	 * @param idSubcatDocumento
	 * @param idInstanciaProceso
	 * @return
	 */
	List<PgimDestinatarioDocDTO> listarDestinatarioDoc(Long idSubcatDocumento, Long idInstanciaProceso, String nuExpedienteSiged, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Listar destinatarios definidos para un documento dado
	 * 
	 * @param idDocumento
	 * @return
	 */
	List<PgimDestinatarioDocDTO> listarDestinatarioPorIdDoc(Long idDocumento);
	
	/**
	 * Modificar destinatario de la constancias
	 * @param pgimDestinatarioDocDTO
	 * @param pgimDestinatarioDoc
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public PgimDestinatarioDocDTO modificarDestinatarioDoc(PgimDestinatarioDocDTO pgimDestinatarioDocDTO, PgimDestinatarioDoc pgimDestinatarioDoc, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Modificar destinatario de la constancias
	 * @param pgimDestinatarioDocDTO
	 * @param pgimDestinatarioDoc
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public PgimDestinatarioDocDTO modificarDestinatarioDoc2(PgimDestinatarioDocDTO pgimDestinatarioDocDTO, PgimDestinatarioDoc pgimDestinatarioDoc, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Obtener destinario documento por id
	 * @param idDestinatarioDoc
	 * @return
	 * @throws Exception
	 */
	public PgimDestinatarioDoc getByIdDestinatarioDoc(Long idDestinatarioDoc) throws Exception;

	/**
	 * Obtener el objeto del destinatarioa del documento notificable por id
	 * @param idDocumentoConstancia
	 * @return
	 */
	PgimDestinatarioDocDTO obtenerDestinatarioDocNotificable(Long idDocumentoConstancia);

	/*
	 * Verifica si es posible eliminar o no el documento.
	 * @param idDocumento
	 * @param idInstanciaPaso
	 * @return
	 */
    ClaveValorUtil<Boolean, String> sePuedeEliminar(Long idDocumento, Long idInstanciaPaso);

	/**
	 * Permite obtener la configuración de un paso-subcategoría  
     * por idSubcategoria y idPasoProceso
	 * @param idSubcatDocumento
	 * @param idPasoProceso
	 * @return
	 */
	PgimPasoSubcatDTO obtenerConfigPasoSubcat(Long idSubcatDocumento, Long idPasoProceso);

	/**
	 * Obtiene el consorcio principal
	 * @param idPersona
	 * @return
	 */
	PgimPersona obtenerConsorcioPrincipal(Long idPersona);

	/**
	 * Me permite listar la configuración de pasos de subcategoria las que se van a permitir asociar con un documento notificado
	 * @return
	 */
	List<PgimPasoSubcatDTO> listarConfigPasoSubcat();

	/**
	 * Permite obtener los tipos de documentos que tiene Siged
	 * @return
	 * @throws Exception
	 */
	Tiposdocumento listarTipoDocsSiged() throws Exception;

	/**
	 * Permite obtener el listado de los antecedente registrado en el siged
	 * @param consultaListadoDocumento
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> listarDocumentosSiged(ConsultaListadoDocumento consultaListadoDocumento) throws Exception;

	/**
	* Permite exportar en formato excel las fiscalizaciones asociadas a la unidad minera en formato EXCEL
	* @param filtro
	* @return
	* @throws Exception
	*/
	byte[] generarReporteUMFiscalizablesEXCEL(PgimSupervisionDTO filtro) throws Exception;
	
	/**
	* Permite exportar en formato excel todos los PAS asociadas a la unidad minera en formato EXCEL
	* @param filtro
	* @return
	* @throws Exception
	*/
	public byte[] generarReportePasUMFiscalizablesEXCEL(PgimPasAuxDTO filtro) throws Exception;

	/**
	 * Permite obtener la lista de documentos de la instancia del proceso y de la subcategoría dada.
	 * @param idInstanciaProceso
	 * @param idSubcategoriaDocumento
	 * @return
	 */
    public List<PgimDocumentoDTO> obtenerDocumentosPorSubcategoria(Long idInstanciaProceso,
            Long idSubcategoriaDocumento);

	DocumentoConsultaOutRO listarArchivosConVersiones_old(String idDocumento, AuditoriaDTO auditoriaDTO) throws Exception;
}
