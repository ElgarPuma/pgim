package pe.gob.osinergmin.pgim.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimArchivoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.dtos.RevisionInforme;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimArchivo;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumentoRelacion;
import pe.gob.osinergmin.pgim.models.entity.PgimFaseProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaObservacion;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaRevision;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ArchivoRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRelacionRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.FichaObservacionRepository;
import pe.gob.osinergmin.pgim.models.repository.FichaRevisionRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.ArchivoService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.EqpInstanciaProService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.RevisionInformeService;
import pe.gob.osinergmin.pgim.siged.Documento;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularInRO;
import pe.gob.osinergmin.pgim.siged.DocumentoAnularOutRO;
import pe.gob.osinergmin.pgim.siged.DocumentoNuevo;
import pe.gob.osinergmin.pgim.siged.DocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.Documentos;
import pe.gob.osinergmin.pgim.siged.ExpedienteSiged;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstSubCategoriaDocumento;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;
import pe.gob.osinergmin.pgim.utils.FechaFeriado;
import pe.gob.osinergmin.pgim.siged.ExpedienteDocOutRO;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la en tidad revision informe
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class RevisionInformeServiceImpl implements RevisionInformeService {

	@Autowired
	private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private DocumentoRelacionRepository documentoRelacionRepository;

	@Autowired
	private FichaRevisionRepository fichaRevisionRepository;

	@Autowired
	private FichaObservacionRepository fichaObservacionRepository;

	@Autowired
	private FaseProcesoRepository faseProcesoRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private ArchivoService archivoService;

	@Autowired
	private InstanciaProcesService instanciaProcesService;

	@Autowired
	private ArchivoRepository archivoRepository;

	@Autowired
	private SigedSoapService sigedSoapService;

	@Autowired
	private SupervisionRepository supervisionRepository;

	@Autowired
	private SubcategoriaDocRepository subcategoriaDocRepository;

	@Autowired
	private EqpInstanciaProService eqpInstanciaProService;

	@Override
	public List<PgimDocumentoDTO> verDocumentosRevisionInforme(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento, AuditoriaDTO auditoriaDTO) throws Exception {

		List<PgimDocumentoDTO> lPgimDocumentoDTOResultado = new ArrayList<PgimDocumentoDTO>();

		PgimInstanciaProcesDTO pgimInstanciaProcesDTO = this.instanciaProcesRepository
				.obtenerInstanciaProcesoFase(idProceso, idFase, coTablaInstancia);

		List<PgimDocumentoDTO> lPgimDocumentoDTO = new LinkedList<PgimDocumentoDTO>();

		List<PgimDocumentoDTO> lPgimDocumentoDTOBase = this.documentoRepository
				.obtenerDocumentosDescendentes(pgimInstanciaProcesDTO.getIdInstanciaProceso(), idSubcatDocumento);

		int numero = 0;
		if (lPgimDocumentoDTOBase != null && lPgimDocumentoDTOBase.size() > 0) {
			numero = lPgimDocumentoDTOBase.size();
		}

		for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTOBase) {
			boolean esObservacionActual = false;
			if (numero == lPgimDocumentoDTOBase.size()) {
				esObservacionActual = true;
			}
			PgimDocumentoDTO documentoObservacion = this.documentoRepository.obtenerDocumentoObsAprob(
					pgimDocumentoDTO.getIdDocumento(), ConstantesUtil.PARAM_SC_FICHA_OBSERVACION_INFORME_FISCALIZACION);

			PgimDocumentoDTO documentoConformidad = this.documentoRepository.obtenerDocumentoObsAprob(
					pgimDocumentoDTO.getIdDocumento(), ConstantesUtil.PARAM_SC_FICHA_CONFORMIDAD_INFORME_FISCALIZACION);

			PgimDocumentoDTO documentoJustificacion = this.documentoRepository.obtenerDocumentoObsAprob(
					pgimDocumentoDTO.getIdDocumento(), ConstantesUtil.PARAM_SC_FICHA_JUSTIFICACION);

			String estadoInforme = "";
			if (documentoObservacion != null) {
				estadoInforme = "Observado";
				documentoObservacion.setTipoRegistro("O");
			}

			if (documentoConformidad != null) {
				estadoInforme = "Aprobado";
				documentoConformidad.setNoSubcatDocumento("Ficha de conformidad del informe de fiscalización");
				documentoConformidad.setTipoRegistro("A");
			}

			if (documentoJustificacion != null) {
				estadoInforme = "Aprobado";
				documentoJustificacion.setNoSubcatDocumento("Informe de justificación");
				documentoJustificacion.setTipoRegistro("J");
			}
			
			if(pgimDocumentoDTO.getFeRechazo() != null ) {
				estadoInforme = "Rechazado";
			}

			pgimDocumentoDTO.setIdSubcatDocumento(pgimDocumentoDTO.getIdSubcatDocumento());
			pgimDocumentoDTO.setDescEstadoInforme(estadoInforme);
			pgimDocumentoDTO.setTipoRegistro("I");
			pgimDocumentoDTO.setDescEsObservacionActual(esObservacionActual);// para idenificarlo como informe actual
			
			String penalidadClave = ConstantesUtil.FL_IND_NO;
			List<PgimDocumentoDTO> lPgimDocConformidad = this.documentoService.obtenerDocumentosPorSubcategoria(pgimInstanciaProcesDTO.getIdInstanciaProceso(), ConstSubCategoriaDocumento.FICHA_CONFORMIDAD_FISCALIZACION);
			if(lPgimDocConformidad.size() > 0){
				Long idDocumento = lPgimDocConformidad.get(0).getIdDocumento();
				PgimFichaRevisionDTO pgimFichaRevisionDTO =  this.fichaRevisionRepository.obtenerFichaRevisionPorIdDocumento(idDocumento);
				if(pgimFichaRevisionDTO != null && pgimFichaRevisionDTO.getDescFlOtrasPenalidades().equals(ConstantesUtil.FL_IND_SI)) penalidadClave = ConstantesUtil.FL_IND_SI;
			}
			pgimDocumentoDTO.setDescPenalidadesClave(penalidadClave);

			lPgimDocumentoDTO.add(pgimDocumentoDTO);

			if (documentoJustificacion != null) {
				documentoJustificacion.setDescEsObservacionActual(esObservacionActual);
				lPgimDocumentoDTO.add(documentoJustificacion);
			}

			if (documentoConformidad != null) {
				
				documentoConformidad.setDescEstadoInforme(estadoInforme);
				documentoConformidad.setDescEsObservacionActual(esObservacionActual);
				lPgimDocumentoDTO.add(documentoConformidad);
			}

			if (documentoObservacion != null) {
				documentoObservacion.setDescEstadoInforme(estadoInforme);
				documentoObservacion.setDescEsObservacionActual(esObservacionActual);
				lPgimDocumentoDTO.add(documentoObservacion);
			}

			numero--;
		}

		if (pgimInstanciaProcesDTO.getNuExpedienteSiged() != null
				&& !pgimInstanciaProcesDTO.getNuExpedienteSiged().equals("")) {

			Documentos documentosYArchivosSiged = documentoService
					.obtenerExpedienteDocumentoSiged(pgimInstanciaProcesDTO.getNuExpedienteSiged(), "1", auditoriaDTO);

			if (documentosYArchivosSiged.getListaDocumento() == null
					|| documentosYArchivosSiged.getListaDocumento().size() == 0) {
				
				lPgimDocumentoDTOResultado = lPgimDocumentoDTO;

			} else {

				PgimDocumentoDTO pgimDocumentoDTOResultado = null;
				for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO) {

					pgimDocumentoDTOResultado = pgimDocumentoDTO;

					if (pgimDocumentoDTO.getCoDocumentoSiged() == null) {
						continue;
					}

					for (Documento documentoSiged : documentosYArchivosSiged.getListaDocumento()) {
						if (!pgimDocumentoDTO.getCoDocumentoSiged().toString().equals(documentoSiged.getIdDocumento())) {
							continue;
						}
						pgimDocumentoDTOResultado.setNumeroDocumento(documentoSiged.getNroDocumento());
						pgimDocumentoDTOResultado.setNombreTipoDocumento(documentoSiged.getNombreTipoDocumento());
						pgimDocumentoDTOResultado.setAsuntoSiged(documentoSiged.getAsunto());
						pgimDocumentoDTOResultado.setFechaDocumentoSiged(documentoSiged.getFechaDocumento());
						pgimDocumentoDTOResultado.setFechaCreacionSiged(documentoSiged.getFechaCreacion());
						pgimDocumentoDTOResultado.setFechaLimiteAtencionSiged(documentoSiged.getFechaLimiteAtencion());
						pgimDocumentoDTOResultado.setFechaNumeracionSiged(documentoSiged.getFechaNumeracion());
						pgimDocumentoDTOResultado
								.setNuExpedienteSiged(pgimInstanciaProcesDTO.getNuExpedienteSiged());

						lPgimDocumentoDTOResultado.add(pgimDocumentoDTOResultado);
						break;
					}
				}

			}

		} else {
			lPgimDocumentoDTOResultado = lPgimDocumentoDTO;
		}

		return lPgimDocumentoDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public RevisionInforme crearObservacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO)
			throws Exception {

		Long idDocInformeFisc = revisionInforme.getPgimDocumentoRelacionDTO().getIdDocumentoPadre();
				
		RevisionInforme revisionInformeCreado = new RevisionInforme();

		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTOCreado = new LinkedList<PgimFichaObservacionDTO>();

		PgimDocumentoDTO pgimDocumentoDTO = revisionInforme.getPgimDocumentoDTO();

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = new PgimInstanciaProcesDTO();
		pgimInstanciaProcesDTOActual.setIdInstanciaProceso(pgimDocumentoDTO.getIdInstanciaProceso());
		pgimInstanciaProcesDTOActual.setCoTablaInstancia(pgimDocumentoDTO.getCoTablaInstancia());
		pgimInstanciaProcesDTOActual.setIdProceso(ConstantesUtil.PARAM_PROCESO_SUPERVISION);

		PgimDocumentoDTO pgimDocumentoDTOActual = new PgimDocumentoDTO();
		pgimDocumentoDTOActual.setCoTipoDocumentoSiged(ConstantesUtil.PARAM_TIPO_DOC_OTROS);
		pgimDocumentoDTOActual.setDeAsuntoDocumento(pgimDocumentoDTO.getDeAsuntoDocumento());
		pgimDocumentoDTOActual.setFeOrigenDocumento(revisionInforme.getPgimFichaRevisionDTO().getFeRevisionFicha());
		pgimDocumentoDTOActual.setFlNumeradoPorSiged("N");
		pgimDocumentoDTOActual.setIdFaseProceso(ConstantesUtil.PARAM_SUPERVISION_REV_INFO_SUPERVISION);
		pgimDocumentoDTOActual.setIdSubcatDocumento(ConstantesUtil.PARAM_SC_FICHA_OBSERVACION_INFORME_FISCALIZACION);
		pgimDocumentoDTOActual.setIdTipoOrigenDocumento(ConstantesUtil.PARAM_DOI_INTERNO);
		pgimDocumentoDTOActual.setDescIdInstanciaPasoActual(pgimDocumentoDTO.getDescIdInstanciaPasoActual());

		String numeroDocumento = this.documentoService.generarNumeroDocFormateado(pgimDocumentoDTOActual, pgimInstanciaProcesDTOActual, auditoriaDTO);			
		if(numeroDocumento == null) numeroDocumento = pgimDocumentoDTO.getNumeroDocumento();
		
		pgimDocumentoDTOActual.setNumeroDocumento(numeroDocumento);

		// validar que solamente se encuentre un especialista técnico responsable
		List<PgimEqpInstanciaProDTO> listaEspecialistasOsi = eqpInstanciaProService.obtenerPersonalResponsableXRolOsi(
			pgimDocumentoDTO.getIdInstanciaProceso(), ConstantesUtil.PROCESO_ROL_ESP_TECNICO);

		if(listaEspecialistasOsi.size() != 1){
			String errorMsj = "Para generar el documento se requiere que en el equipo solo exista un especialista técnico como responsable; actualmente existen "
					+ listaEspecialistasOsi.size() + ", por favor seleccione solo un responsable por rol";
			throw new PgimException(TipoResultado.ERROR, errorMsj);
		}

		ResponseEntity<ResponseDTO> respuestaGeneracionDoc = this.documentoService.procesarGenerarDocumento(pgimDocumentoDTOActual,
			pgimInstanciaProcesDTOActual, auditoriaDTO);
		
		// Verificación de la respuesta de la generación del documento
		ResponseDTO responseDTO = respuestaGeneracionDoc.getBody();
		if(responseDTO == null) {
			String errorMsj = "Ocurrió un error al intentar generar el documento de observación al informe";
			TipoResultado tipoResultado = TipoResultado.WARNING;
			throw new PgimException(tipoResultado, errorMsj);
		}

		if(!responseDTO.getTipoResultado().equals(TipoResultado.SUCCESS)) {
			String errorMsj = responseDTO.getMensaje();
			TipoResultado tipoResultado = responseDTO.getTipoResultado() == null ? TipoResultado.WARNING : responseDTO.getTipoResultado();
			throw new PgimException(tipoResultado, errorMsj);
		}

		PgimDocumentoDTO pgimDocumentoDTOCreado = this.documentoService.obtenerDocumentoPgimById(responseDTO.getId());

		// Modificamos el documento PGIM
		PgimDocumento pgimDocumento = this.documentoRepository.findById(pgimDocumentoDTOCreado.getIdDocumento())
				.orElse(null);

		pgimDocumento.setFeOrigenDocumento(revisionInforme.getPgimFichaRevisionDTO().getFeRevisionFicha());
		pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
		pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());
		pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());

		this.documentoRepository.save(pgimDocumento);

		PgimDocumentoRelacion pgimDocumentoRelacion = this.generarPgimDocumentoRelacion(pgimDocumentoDTOCreado,
				revisionInforme.getPgimDocumentoRelacionDTO(), auditoriaDTO);

		PgimDocumentoRelacion pgimDocumentoRelacionCreado = this.documentoRelacionRepository
				.save(pgimDocumentoRelacion);

		PgimDocumentoRelacionDTO pgimDocumentoRelacionDTOCreado = this
				.obtenerDocumentoRelacionPorId(pgimDocumentoRelacionCreado.getIdDocumentoRelacion());

		PgimFichaRevision pgimFichaRevision = this.generarPgimFichaRevisionObservacion(pgimDocumentoDTOCreado,
				revisionInforme.getPgimFichaRevisionDTO(), auditoriaDTO);

		PgimFichaRevision pgimFichaRevisionCreado = this.fichaRevisionRepository.save(pgimFichaRevision);

		PgimFichaRevisionDTO pgimFichaRevisionCreadoDTO = this
				.obtenerFichaRevisionPorId(pgimFichaRevisionCreado.getIdFichaRevision());

		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO = revisionInforme.getlPgimFichaObservacionDTO();
		for (PgimFichaObservacionDTO fichaObservacionDTO : lPgimFichaObservacionDTO) {
			fichaObservacionDTO.setIdFichaRevision(pgimFichaRevisionCreadoDTO.getIdFichaRevision());
			fichaObservacionDTO.setCaDiasParaPresentacion(pgimFichaRevisionCreadoDTO.getCaDiasParaPresentacion());
			fichaObservacionDTO.setFeDesdeParaPresentacion(pgimFichaRevisionCreadoDTO.getFeDesdeParaPresentacion());
			fichaObservacionDTO.setFePresentacion(pgimFichaRevisionCreadoDTO.getFePresentacion());
			fichaObservacionDTO.setFeRevisionFicha(pgimFichaRevisionCreadoDTO.getFeRevisionFicha());

			PgimFichaObservacion pgimFichaObservacion = generarPgimFichaObservacion(pgimFichaRevisionCreadoDTO,
					fichaObservacionDTO, auditoriaDTO, false);
			PgimFichaObservacion pgimFichaObservacionCreado = fichaObservacionRepository.save(pgimFichaObservacion);
			PgimFichaObservacionDTO pgimFichaObservacionDTOCreado = this
					.obtenerFichaObservacionPorId(pgimFichaObservacionCreado.getIdFichaObservacion());
			lPgimFichaObservacionDTOCreado.add(pgimFichaObservacionDTOCreado);
		}

		// Reemplazar el documento generado con los valores de las observaciones
		// agregados.
		pgimDocumentoDTOActual.setNumeroDocumento(numeroDocumento);		
		respuestaGeneracionDoc = this.documentoService.generarFichaObsInfSupCompleta(pgimDocumentoDTOActual, pgimInstanciaProcesDTOActual,
		auditoriaDTO, lPgimFichaObservacionDTOCreado, idDocInformeFisc);

		revisionInformeCreado.setPgimDocumentoDTO(pgimDocumentoDTOCreado);
		revisionInformeCreado.setPgimDocumentoRelacionDTO(pgimDocumentoRelacionDTOCreado);
		revisionInformeCreado.setPgimFichaRevisionDTO(pgimFichaRevisionCreadoDTO);
		revisionInformeCreado.setlPgimFichaObservacionDTO(lPgimFichaObservacionDTOCreado);

		return revisionInformeCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public RevisionInforme modificarObservacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO)
			throws Exception {

		RevisionInforme revisionInformeModificado = new RevisionInforme();

		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTOModificado = new LinkedList<PgimFichaObservacionDTO>();

		PgimFichaRevision pgimFichaRevision = this.generarPgimFichaRevisionObservacion(
				revisionInforme.getPgimDocumentoDTO(),
				revisionInforme.getPgimFichaRevisionDTO(), auditoriaDTO);

		PgimFichaRevision pgimFichaRevisionModificado = this.fichaRevisionRepository.save(pgimFichaRevision);

		PgimFichaRevisionDTO pgimFichaRevisionDTOModificado = this
				.obtenerFichaRevisionPorId(pgimFichaRevisionModificado.getIdFichaRevision());

		// Crear o modificar
		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO = revisionInforme.getlPgimFichaObservacionDTO();
		for (PgimFichaObservacionDTO obj : lPgimFichaObservacionDTO) {
			PgimFichaObservacion pgimFichaObservacion = this.generarPgimFichaObservacion(pgimFichaRevisionDTOModificado,
					obj,
					auditoriaDTO, false);
			PgimFichaObservacion pgimFichaObservacionModificado = fichaObservacionRepository.save(pgimFichaObservacion);
			PgimFichaObservacionDTO pgimFichaObservacionDTOModificado = obtenerFichaObservacionPorId(
					pgimFichaObservacionModificado.getIdFichaObservacion());
			lPgimFichaObservacionDTOModificado.add(pgimFichaObservacionDTOModificado);
		}

		// Eliminar registros costo unitario
		List<PgimFichaObservacionDTO> listaTotalPgimFichaObservacionDTO = this
				.obtenerFichaObservacionPorIdFichaRevision(
						pgimFichaRevisionDTOModificado.getIdFichaRevision());
		for (PgimFichaObservacionDTO listaTotal : listaTotalPgimFichaObservacionDTO) {
			boolean soloExisteEnTotal = true;
			for (PgimFichaObservacionDTO listaModificada : lPgimFichaObservacionDTOModificado) {
				if (listaTotal.getIdFichaObservacion().intValue() == listaModificada.getIdFichaObservacion()
						.intValue()) {
					soloExisteEnTotal = false;
					break;
				}
			}

			if (soloExisteEnTotal) {
				PgimFichaObservacion pgimFichaObservacion = generarPgimFichaObservacion(pgimFichaRevisionDTOModificado,
						listaTotal, auditoriaDTO, true);
				this.fichaObservacionRepository.save(pgimFichaObservacion);
			}
		}

		if (revisionInforme.getFlGenerarDocumento().equals("1")) {

			// Reemplazar el documento generado con los valores de las observaciones
			// agregadas.
			PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = new PgimInstanciaProcesDTO();
			pgimInstanciaProcesDTOActual
					.setIdInstanciaProceso(revisionInforme.getPgimDocumentoDTO().getIdInstanciaProceso());
			pgimInstanciaProcesDTOActual
					.setCoTablaInstancia(revisionInforme.getPgimDocumentoDTO().getCoTablaInstancia());
			pgimInstanciaProcesDTOActual.setIdProceso(ConstantesUtil.PARAM_PROCESO_SUPERVISION);

			PgimDocumentoDTO pgimDocumentoDTOActual = revisionInforme.getPgimDocumentoDTO();
			pgimDocumentoDTOActual.setFeOrigenDocumento(revisionInforme.getPgimFichaRevisionDTO().getFeRevisionFicha());
			pgimDocumentoDTOActual.setCoTipoDocumentoSiged(ConstantesUtil.PARAM_TIPO_DOC_OTROS);
			pgimDocumentoDTOActual.setFlNumeradoPorSiged("N");
			pgimDocumentoDTOActual.setIdFaseProceso(ConstantesUtil.PARAM_SUPERVISION_REV_INFO_SUPERVISION);
			pgimDocumentoDTOActual
					.setIdSubcatDocumento(ConstantesUtil.PARAM_SC_FICHA_OBSERVACION_INFORME_FISCALIZACION);
			pgimDocumentoDTOActual.setIdTipoOrigenDocumento(ConstantesUtil.PARAM_DOI_INTERNO);

			PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = this.obtenerDocumentoRelacionPorIdDocumento(
				pgimDocumentoDTOActual.getIdDocumento(),
				ConstantesUtil.PARAM_INFORME_OBSERVADO);

			this.documentoService.generarFichaObsInfSupCompleta(pgimDocumentoDTOActual, pgimInstanciaProcesDTOActual,
					auditoriaDTO, lPgimFichaObservacionDTOModificado, pgimDocumentoRelacionDTO.getIdDocumentoPadre());

		}

		// Modificamos el documento PGIM
		PgimDocumento pgimDocumento = this.documentoRepository
				.findById(revisionInforme.getPgimDocumentoDTO().getIdDocumento()).orElse(null);

		pgimDocumento.setFeOrigenDocumento(revisionInforme.getPgimFichaRevisionDTO().getFeRevisionFicha());

		pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
		pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());
		pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());

		this.documentoRepository.save(pgimDocumento);

		revisionInformeModificado.setPgimDocumentoDTO(revisionInforme.getPgimDocumentoDTO());
		revisionInformeModificado.setPgimDocumentoRelacionDTO(revisionInforme.getPgimDocumentoRelacionDTO());
		revisionInformeModificado.setPgimFichaRevisionDTO(pgimFichaRevisionDTOModificado);
		revisionInformeModificado.setlPgimFichaObservacionDTO(lPgimFichaObservacionDTOModificado);

		return revisionInformeModificado;
	}

	@Transactional(readOnly = false)
	@Override
	public List<PgimFichaObservacionDTO> subsanarTodasObservaciones(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO) throws Exception {

		/* Obtengo la lista de observaciones */
		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO = revisionInforme.getlPgimFichaObservacionDTO();
		
		PgimFichaObservacion pgimFichaObservacion = new PgimFichaObservacion();

		for(PgimFichaObservacionDTO pgimFichaObservacionDTO : lPgimFichaObservacionDTO){

			/* Las observaciones de tipo revisión y que estan por subsanar entra a la condicional */
			if(pgimFichaObservacionDTO.getIdTipoObservacionFicha().equals(ConstantesUtil.PARAM_TIPO_REVISION) && pgimFichaObservacionDTO.getFlSubsanada().equals("0")){

				pgimFichaObservacion = this.fichaObservacionRepository.findById(pgimFichaObservacionDTO.getIdFichaObservacion()).orElse(null);

				pgimFichaObservacionDTO.setFlSubsanada("1");

				pgimFichaObservacion.setFlSubsanada(pgimFichaObservacionDTO.getFlSubsanada());

				this.fichaObservacionRepository.save(pgimFichaObservacion);
			}
		}

		return lPgimFichaObservacionDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public RevisionInforme crearAprobacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO)
			throws Exception {

		Long idDocInformeFisc = revisionInforme.getPgimDocumentoRelacionDTO().getIdDocumentoPadre();

		PgimDocumentoDTO pgimDocumentoDTOAprobacion = revisionInforme.getPgimDocumentoDTO();

		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
				.findById(pgimDocumentoDTOAprobacion.getIdInstanciaProceso()).orElse(null);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = new PgimInstanciaProcesDTO();
		pgimInstanciaProcesDTOActual.setIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());
		pgimInstanciaProcesDTOActual.setCoTablaInstancia(pgimInstanciaProces.getCoTablaInstancia());
		pgimInstanciaProcesDTOActual.setIdProceso(pgimInstanciaProces.getPgimProceso().getIdProceso());

		PgimFichaRevisionDTO pgimFichaRevisionDTOCrear = revisionInforme.getPgimFichaRevisionDTO();

		pgimDocumentoDTOAprobacion.setCoTipoDocumentoSiged(ConstantesUtil.PARAM_TIPO_DOC_ACTA_CONFORMIDAD);
		pgimDocumentoDTOAprobacion.setDeAsuntoDocumento("Conformidad del informe de fiscalización");
		pgimDocumentoDTOAprobacion.setFeOrigenDocumento(pgimFichaRevisionDTOCrear.getFeRevisionFicha());
		pgimDocumentoDTOAprobacion.setFlNumeradoPorSiged("N");
		pgimDocumentoDTOAprobacion.setIdFaseProceso(ConstantesUtil.PARAM_SUPERVISION_REV_INFO_SUPERVISION);
		pgimDocumentoDTOAprobacion.setIdSubcatDocumento(ConstantesUtil.PARAM_SC_FICHA_CONFORMIDAD_INFORME_FISCALIZACION);
		pgimDocumentoDTOAprobacion.setIdTipoOrigenDocumento(ConstantesUtil.PARAM_DOI_INTERNO);

		// validar que solamente se encuentre un especialista técnico responsable
		List<PgimEqpInstanciaProDTO> listaEspecialistasOsi = eqpInstanciaProService.obtenerPersonalResponsableXRolOsi(
			pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PROCESO_ROL_ESP_TECNICO);

		if(listaEspecialistasOsi.size() != 1){
			String errorMsj = "Para generar el documento se requiere que en el equipo solo exista un especialista técnico como responsable; actualmente existen "+listaEspecialistasOsi.size()+", por favor seleccione solo un responsable por rol";
			throw new PgimException(TipoResultado.ERROR, errorMsj);
		}

		ResponseEntity<ResponseDTO> respuesta = this.documentoService.procesarGenerarDocumento(
				pgimDocumentoDTOAprobacion,
				pgimInstanciaProcesDTOActual, auditoriaDTO);
		
		// Verificación de la respuesta de la generación del documento
		if(respuesta.getBody() == null || !respuesta.getBody().getTipoResultado().equals(TipoResultado.SUCCESS)) {
			String errorMsj = (respuesta.getBody() == null) ? "Ocurrió un error al intentar generar el documento de observación al informe" : respuesta.getBody().getMensaje();
			TipoResultado tipoResultado = (respuesta.getBody() == null || respuesta.getBody().getTipoResultado() == null ) ? TipoResultado.WARNING : respuesta.getBody().getTipoResultado();
			throw new PgimException(tipoResultado, errorMsj);
		}

		PgimDocumentoDTO pgimDocumentoDTOAprobacionCreado = this.documentoService
				.obtenerDocumentoPgimById(respuesta.getBody().getId());

		// Modificamos el documento PGIM
		PgimDocumento pgimDocumento = this.documentoRepository
				.findById(pgimDocumentoDTOAprobacionCreado.getIdDocumento())
				.orElse(null);

		pgimDocumento.setFeOrigenDocumento(pgimFichaRevisionDTOCrear.getFeRevisionFicha());
		pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
		pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());
		pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());

		this.documentoRepository.save(pgimDocumento);

		PgimDocumentoRelacion pgimDocumentoRelacion = this.generarPgimDocumentoRelacionAprobacion(
				pgimDocumentoDTOAprobacionCreado,
				revisionInforme.getPgimDocumentoRelacionDTO(), auditoriaDTO);

		PgimDocumentoRelacion pgimDocumentoRelacionCreado = this.documentoRelacionRepository
				.save(pgimDocumentoRelacion);

		/**
        * ---------------------------------------------------------------------------------------
        * Validamos la relación del documento que se van a duplicar en la aprobación del informe
        * ---------------------------------------------------------------------------------------
        */
		// Permite la sincronización de los cambios en el contexto de persistencia con la base de datos antes de que la transacción finalice
		this.documentoRelacionRepository.flush();

		List<PgimDocumentoRelacionDTO> lPgimDocumentoRelacionDTO = this.documentoRelacionRepository.obtenerListDocRelacionPorIdDocumentoPadre(pgimDocumentoRelacionCreado.getDocumentoPadre().getIdDocumento(), ConstantesUtil.PARAM_INFORME_APROBADO);
		
		if(lPgimDocumentoRelacionDTO.size() > 1){
			throw new PgimException(TipoResultado.WARNING, "La aprobación del informe ya ha sido creada previamente, por favor actualice la lista de revisiones del informe de la pestaña «CONFORMIDAD».");
		}
		/** * Fin validación */

		PgimDocumentoRelacionDTO pgimDocumentoRelacionDTOCreado = this
				.obtenerDocumentoRelacionPorId(pgimDocumentoRelacionCreado.getIdDocumentoRelacion());

		// Se generar la ficha de revisión que aprueba el informe.
		PgimFichaRevision pgimFichaRevision = this.generarPgimFichaRevisionAprobacion(pgimDocumentoDTOAprobacionCreado,
				pgimFichaRevisionDTOCrear, auditoriaDTO);

		this.fichaRevisionRepository.save(pgimFichaRevision);

		RevisionInforme revisionInformeCreado = new RevisionInforme();
		revisionInformeCreado.setPgimDocumentoDTO(pgimDocumentoDTOAprobacionCreado);
		revisionInformeCreado.setPgimDocumentoRelacionDTO(pgimDocumentoRelacionDTOCreado);
		revisionInformeCreado.setPgimFichaRevisionDTO(pgimFichaRevisionDTOCrear);

		// obtenemos el contrato
		PgimContratoDTO pgimContratoDTO = this.contratoRepository
				.obtenerContratoPorIdSupervision(revisionInforme.getPgimDocumentoDTO().getCoTablaInstancia());
		revisionInformeCreado.setPgimContratoDTO(pgimContratoDTO);

		// Reemplazar el documento generado con los valores de la aprobación.
		respuesta = this.documentoService.generarFichaConfInfSupCompleta(pgimDocumentoDTOAprobacion,
				pgimInstanciaProcesDTOActual,
				auditoriaDTO, revisionInformeCreado,idDocInformeFisc);

		return revisionInformeCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public RevisionInforme modificarAprobacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO)
			throws Exception {
		
		PgimDocumentoDTO pgimDocumentoDTOAprobacion = revisionInforme.getPgimDocumentoDTO();
		pgimDocumentoDTOAprobacion.setCoTipoDocumentoSiged(ConstantesUtil.PARAM_TIPO_DOC_ACTA_CONFORMIDAD); // Pendiente de
																									// corregir se
																									// debería obtener
																									// desde la
																									// configuración

		PgimFichaRevisionDTO pgimFichaRevisionDTOModificar = revisionInforme.getPgimFichaRevisionDTO();

		PgimDocumento pgimDocumentoAprobacion = this.documentoRepository
				.findById(pgimDocumentoDTOAprobacion.getIdDocumento()).orElse(null);

		PgimFichaRevision pgimFichaRevision = this.generarPgimFichaRevisionAprobacion(pgimDocumentoDTOAprobacion,
				revisionInforme.getPgimFichaRevisionDTO(), auditoriaDTO);

		this.fichaRevisionRepository.save(pgimFichaRevision);

		RevisionInforme revisionInformeModificado = new RevisionInforme();
		revisionInformeModificado.setPgimDocumentoDTO(pgimDocumentoDTOAprobacion);
		revisionInformeModificado.setPgimDocumentoRelacionDTO(new PgimDocumentoRelacionDTO());
		revisionInformeModificado.setPgimFichaRevisionDTO(pgimFichaRevisionDTOModificar);

		// Reemplazar el documento generado con los valores de las observaciones
		// agregados.
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
				.findById(pgimDocumentoAprobacion.getPgimInstanciaProces().getIdInstanciaProceso()).orElse(null);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = new PgimInstanciaProcesDTO();
		pgimInstanciaProcesDTOActual.setIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());
		pgimInstanciaProcesDTOActual.setCoTablaInstancia(pgimInstanciaProces.getCoTablaInstancia());
		pgimInstanciaProcesDTOActual.setIdProceso(pgimInstanciaProces.getPgimProceso().getIdProceso());

		// Obtenemos el contrato
		PgimContratoDTO pgimContratoDTO = this.contratoRepository
				.obtenerContratoPorIdSupervision(revisionInforme.getPgimDocumentoDTO().getCoTablaInstancia());

		revisionInformeModificado.setPgimContratoDTO(pgimContratoDTO);

		PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = this.obtenerDocumentoRelacionPorIdDocumento(
			pgimDocumentoDTOAprobacion.getIdDocumento(),
				ConstantesUtil.PARAM_INFORME_APROBADO);

		this.documentoService.generarFichaConfInfSupCompleta(pgimDocumentoDTOAprobacion, pgimInstanciaProcesDTOActual,
				auditoriaDTO, revisionInformeModificado,pgimDocumentoRelacionDTO.getIdDocumentoPadre());

		// Modificamos el documento PGIM
		pgimDocumentoAprobacion.setFeOrigenDocumento(revisionInforme.getPgimFichaRevisionDTO().getFeRevisionFicha());

		pgimDocumentoAprobacion.setFeActualizacion(auditoriaDTO.getFecha());
		pgimDocumentoAprobacion.setIpActualizacion(auditoriaDTO.getTerminal());
		pgimDocumentoAprobacion.setUsActualizacion(auditoriaDTO.getUsername());

		this.documentoRepository.save(pgimDocumentoAprobacion);

		return revisionInformeModificado;
	}

	@Override
	public PgimDocumentoRelacion generarPgimDocumentoRelacion(PgimDocumentoDTO pgimDocumentoDTO,
			PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO, AuditoriaDTO auditoriaDTO) {
		PgimDocumentoRelacion pgimDocumentoRelacion = new PgimDocumentoRelacion();

		if (pgimDocumentoRelacionDTO.getIdDocumentoRelacion() != null) {
			pgimDocumentoRelacion = documentoRelacionRepository
					.findById(pgimDocumentoRelacionDTO.getIdDocumentoRelacion()).orElse(null);

			pgimDocumentoRelacion.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumentoRelacion.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumentoRelacion.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {
			PgimDocumento documentoPadre = new PgimDocumento();
			documentoPadre.setIdDocumento(pgimDocumentoRelacionDTO.getIdDocumentoPadre());
			pgimDocumentoRelacion.setDocumentoPadre(documentoPadre);

			PgimDocumento documento = new PgimDocumento();
			documento.setIdDocumento(pgimDocumentoDTO.getIdDocumento());
			pgimDocumentoRelacion.setPgimDocumento(documento);

			PgimValorParametro tipoRelacionDocumento = new PgimValorParametro();

			if (pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_FICHA_JUSTIFICACION)) {
				tipoRelacionDocumento.setIdValorParametro(ConstantesUtil.PARAM_INFORME_JUSTIFICADO);
			} else {
				tipoRelacionDocumento.setIdValorParametro(ConstantesUtil.PARAM_INFORME_OBSERVADO);
			}

			pgimDocumentoRelacion.setTipoRelacionDocumento(tipoRelacionDocumento);

			pgimDocumentoRelacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimDocumentoRelacion.setFeCreacion(auditoriaDTO.getFecha());
			pgimDocumentoRelacion.setUsCreacion(auditoriaDTO.getUsername());
			pgimDocumentoRelacion.setIpCreacion(auditoriaDTO.getTerminal());
		}

		return pgimDocumentoRelacion;
	}

	/**
	 * Aprobacion
	 * 
	 * @param pgimDocumentoDTO
	 * @param pgimDocumentoRelacionDTO
	 * @param auditoriaDTO
	 * @return
	 */
	private PgimDocumentoRelacion generarPgimDocumentoRelacionAprobacion(PgimDocumentoDTO pgimDocumentoDTO,
			PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO, AuditoriaDTO auditoriaDTO) {
		PgimDocumentoRelacion pgimDocumentoRelacion = new PgimDocumentoRelacion();

		if (pgimDocumentoRelacionDTO.getIdDocumentoRelacion() != null) {
			pgimDocumentoRelacion = documentoRelacionRepository
					.findById(pgimDocumentoRelacionDTO.getIdDocumentoRelacion()).orElse(null);

			pgimDocumentoRelacion.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumentoRelacion.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumentoRelacion.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {
			PgimDocumento documentoPadre = new PgimDocumento();
			documentoPadre.setIdDocumento(pgimDocumentoRelacionDTO.getIdDocumentoPadre());
			pgimDocumentoRelacion.setDocumentoPadre(documentoPadre);

			PgimDocumento documento = new PgimDocumento();
			documento.setIdDocumento(pgimDocumentoDTO.getIdDocumento());
			pgimDocumentoRelacion.setPgimDocumento(documento);

			PgimValorParametro tipoRelacionDocumento = new PgimValorParametro();
			tipoRelacionDocumento.setIdValorParametro(ConstantesUtil.PARAM_INFORME_APROBADO);
			pgimDocumentoRelacion.setTipoRelacionDocumento(tipoRelacionDocumento);

			pgimDocumentoRelacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimDocumentoRelacion.setFeCreacion(auditoriaDTO.getFecha());
			pgimDocumentoRelacion.setUsCreacion(auditoriaDTO.getUsername());
			pgimDocumentoRelacion.setIpCreacion(auditoriaDTO.getTerminal());
		}

		return pgimDocumentoRelacion;
	}

	@Override
	public PgimFichaRevision generarPgimFichaRevisionObservacion(PgimDocumentoDTO pgimDocumentoDTO,
			PgimFichaRevisionDTO pgimFichaRevisionDTO, AuditoriaDTO auditoriaDTO) {

		PgimFichaRevision pgimFichaRevision = new PgimFichaRevision();

		if (pgimFichaRevisionDTO.getIdFichaRevision() == null) {
			// Es una nueva ficha
			PgimDocumento pgimDocumento = new PgimDocumento();
			pgimDocumento.setIdDocumento(pgimDocumentoDTO.getIdDocumento());
			pgimFichaRevision.setPgimDocumento(pgimDocumento);

			PgimValorParametro tipoConformidad = new PgimValorParametro();
			tipoConformidad.setIdValorParametro(ConstantesUtil.PARAM_FR_FICHA_OBSERVACIONES);
			pgimFichaRevision.setTipoConformidad(tipoConformidad);

			pgimFichaRevision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimFichaRevision.setFeCreacion(auditoriaDTO.getFecha());
			pgimFichaRevision.setUsCreacion(auditoriaDTO.getUsername());
			pgimFichaRevision.setIpCreacion(auditoriaDTO.getTerminal());
		} else {
			// Es una ficha existente

			pgimFichaRevision = this.fichaRevisionRepository.findById(pgimFichaRevisionDTO.getIdFichaRevision())
					.orElse(null);

			pgimFichaRevision.setFeActualizacion(auditoriaDTO.getFecha());
			pgimFichaRevision.setUsActualizacion(auditoriaDTO.getUsername());
			pgimFichaRevision.setIpActualizacion(auditoriaDTO.getTerminal());
		}

		pgimFichaRevision.setFeRevisionFicha(pgimFichaRevisionDTO.getFeRevisionFicha());
		pgimFichaRevision.setCaDiasParaPresentacion(pgimFichaRevisionDTO.getCaDiasParaPresentacion());
		pgimFichaRevision.setFeDesdeParaPresentacion(pgimFichaRevisionDTO.getFeDesdeParaPresentacion());
		pgimFichaRevision.setFePresentacion(pgimFichaRevisionDTO.getFePresentacion());
		pgimFichaRevision.setCaDiasDemoraCalculados(pgimFichaRevisionDTO.getCaDiasDemoraCalculados());
		pgimFichaRevision.setFlAplicaPenalidad("0");
		pgimFichaRevision.setCaDiasDemoraEstablecidos(null);
		pgimFichaRevision.setCmPenalidad(null);
		pgimFichaRevision.setFlObservacionEpp(null);
		pgimFichaRevision.setCmObservacionEpp(null);
		pgimFichaRevision.setCaDiasPlazoPresentacion(pgimFichaRevisionDTO.getCaDiasPlazoPresentacion());
		pgimFichaRevision.setFeHastaParaPresentacion(pgimFichaRevisionDTO.getFeHastaParaPresentacion());

		return pgimFichaRevision;
	}

	/**
	 * Aprobacion
	 * 
	 * @param pgimDocumentoDTOAprobacion
	 * @param pgimFichaRevisionDTO
	 * @param auditoriaDTO
	 * @return
	 */
	private PgimFichaRevision generarPgimFichaRevisionAprobacion(PgimDocumentoDTO pgimDocumentoDTOAprobacion,
			PgimFichaRevisionDTO pgimFichaRevisionDTO, AuditoriaDTO auditoriaDTO) {
		PgimFichaRevision pgimFichaRevision = new PgimFichaRevision();

		if (pgimFichaRevisionDTO.getIdFichaRevision() == null) {
			// Aun no existe la ficha de aprobación
			// ====================================

			PgimDocumento pgimDocumento = new PgimDocumento();
			pgimDocumento.setIdDocumento(pgimDocumentoDTOAprobacion.getIdDocumento());
			pgimFichaRevision.setPgimDocumento(pgimDocumento);

			PgimValorParametro tipoConformidad = new PgimValorParametro();
			tipoConformidad.setIdValorParametro(ConstantesUtil.PARAM_TIPO_REVISION);
			pgimFichaRevision.setTipoConformidad(tipoConformidad);

			pgimFichaRevision.setFePresentacion(pgimFichaRevisionDTO.getFePresentacion());

			pgimFichaRevision.setFlAplicaPenalidad(pgimFichaRevisionDTO.getFlAplicaPenalidad());
			pgimFichaRevision.setCmPenalidad(pgimFichaRevisionDTO.getCmPenalidad());

			// Por realizar fiscalización sin EPP
			pgimFichaRevision.setFlObservacionEpp(pgimFichaRevisionDTO.getFlObservacionEpp());
			pgimFichaRevision.setCmObservacionEpp(pgimFichaRevisionDTO.getCmObservacionEpp());

			// Por usar equipos de protección personal (EPP) del agente fiscalizado
			pgimFichaRevision.setFlEppAfiscalizado(pgimFichaRevisionDTO.getFlEppAfiscalizado());
			pgimFichaRevision.setCmEppFiscalizado(pgimFichaRevisionDTO.getCmEppFiscalizado());

			// Contar con equipos defectuosos
			pgimFichaRevision.setFlEquiposDefectuosos(pgimFichaRevisionDTO.getFlEquiposDefectuosos());
			pgimFichaRevision.setCmEquiposDefectuosos(pgimFichaRevisionDTO.getCmEquiposDefectuosos());

			// Contar con instrumentos de medición defectuosos
			pgimFichaRevision.setFlEqpMedicionDefectuosos(pgimFichaRevisionDTO.getFlEqpMedicionDefectuosos());
			pgimFichaRevision.setCmEqpMedicionDefectuosos(pgimFichaRevisionDTO.getCmEqpMedicionDefectuosos());

			// Sin contar con equipos
			pgimFichaRevision.setFlSinEquipos(pgimFichaRevisionDTO.getFlSinEquipos());
			pgimFichaRevision.setCmSinEquipos(pgimFichaRevisionDTO.getCmSinEquipos());

			// Equipos sin certificado de calibración vigente
			pgimFichaRevision.setFlEqpCalibracionNvigente(pgimFichaRevisionDTO.getFlEqpCalibracionNvigente());
			pgimFichaRevision.setCmEqpCalibracionNvigente(pgimFichaRevisionDTO.getCmEqpCalibracionNvigente());

			// Sin contar con instrumentos de medición
			pgimFichaRevision.setFlSinInstrumentos(pgimFichaRevisionDTO.getFlSinInstrumentos());
			pgimFichaRevision.setCmSinInstrumentos(pgimFichaRevisionDTO.getCmSinInstrumentos());

			// Instrumentos de medición sin certificado de calibración vigente
			pgimFichaRevision.setFlInsCalibracionNvigente(pgimFichaRevisionDTO.getFlInsCalibracionNvigente());
			pgimFichaRevision.setCmInsCalibracionNvigente(pgimFichaRevisionDTO.getCmInsCalibracionNvigente());

			// Por alterar los formatos de las actas proporcionados
			pgimFichaRevision.setFlAlterarFormatos(pgimFichaRevisionDTO.getFlAlterarFormatos());
			pgimFichaRevision.setCmAlterarFormatos(pgimFichaRevisionDTO.getCmAlterarFormatos());

			// Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)
			pgimFichaRevision.setFlFrustrarFiscalizacion(pgimFichaRevisionDTO.getFlFrustrarFiscalizacion());
			pgimFichaRevision.setCmFrustrarFiscalizacion(pgimFichaRevisionDTO.getCmFrustrarFiscalizacion());

			pgimFichaRevision.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimFichaRevision.setFeCreacion(auditoriaDTO.getFecha());
			pgimFichaRevision.setUsCreacion(auditoriaDTO.getUsername());
			pgimFichaRevision.setIpCreacion(auditoriaDTO.getTerminal());
		} else {
			// Ya existe la ficha de aprobación
			// ================================

			pgimFichaRevision = this.fichaRevisionRepository.findById(pgimFichaRevisionDTO.getIdFichaRevision())
					.orElse(null);

			pgimFichaRevision.setFlAplicaPenalidad(pgimFichaRevisionDTO.getFlAplicaPenalidad());
			pgimFichaRevision.setCmPenalidad(pgimFichaRevisionDTO.getCmPenalidad());

			// Por realizar fiscalización sin EPP
			pgimFichaRevision.setFlObservacionEpp(pgimFichaRevisionDTO.getFlObservacionEpp());
			pgimFichaRevision.setCmObservacionEpp(pgimFichaRevisionDTO.getCmObservacionEpp());

			// Por usar equipos de protección personal (EPP) del agente fiscalizado
			pgimFichaRevision.setFlEppAfiscalizado(pgimFichaRevisionDTO.getFlEppAfiscalizado());
			pgimFichaRevision.setCmEppFiscalizado(pgimFichaRevisionDTO.getCmEppFiscalizado());

			// Contar con equipos defectuosos
			pgimFichaRevision.setFlEquiposDefectuosos(pgimFichaRevisionDTO.getFlEquiposDefectuosos());
			pgimFichaRevision.setCmEquiposDefectuosos(pgimFichaRevisionDTO.getCmEquiposDefectuosos());

			// Contar con instrumentos de medición defectuosos
			pgimFichaRevision.setFlEqpMedicionDefectuosos(pgimFichaRevisionDTO.getFlEqpMedicionDefectuosos());
			pgimFichaRevision.setCmEqpMedicionDefectuosos(pgimFichaRevisionDTO.getCmEqpMedicionDefectuosos());

			// Sin contar con equipos
			pgimFichaRevision.setFlSinEquipos(pgimFichaRevisionDTO.getFlSinEquipos());
			pgimFichaRevision.setCmSinEquipos(pgimFichaRevisionDTO.getCmSinEquipos());

			// Equipos sin certificado de calibración vigente
			pgimFichaRevision.setFlEqpCalibracionNvigente(pgimFichaRevisionDTO.getFlEqpCalibracionNvigente());
			pgimFichaRevision.setCmEqpCalibracionNvigente(pgimFichaRevisionDTO.getCmEqpCalibracionNvigente());

			// Sin contar con instrumentos de medición
			pgimFichaRevision.setFlSinInstrumentos(pgimFichaRevisionDTO.getFlSinInstrumentos());
			pgimFichaRevision.setCmSinInstrumentos(pgimFichaRevisionDTO.getCmSinInstrumentos());

			// Instrumentos de medición sin certificado de calibración vigente
			pgimFichaRevision.setFlInsCalibracionNvigente(pgimFichaRevisionDTO.getFlInsCalibracionNvigente());
			pgimFichaRevision.setCmInsCalibracionNvigente(pgimFichaRevisionDTO.getCmInsCalibracionNvigente());

			// Por alterar los formatos de las actas proporcionados
			pgimFichaRevision.setFlAlterarFormatos(pgimFichaRevisionDTO.getFlAlterarFormatos());
			pgimFichaRevision.setCmAlterarFormatos(pgimFichaRevisionDTO.getCmAlterarFormatos());

			// Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)
			pgimFichaRevision.setFlFrustrarFiscalizacion(pgimFichaRevisionDTO.getFlFrustrarFiscalizacion());
			pgimFichaRevision.setCmFrustrarFiscalizacion(pgimFichaRevisionDTO.getCmFrustrarFiscalizacion());

			pgimFichaRevision.setFeActualizacion(auditoriaDTO.getFecha());
			pgimFichaRevision.setUsActualizacion(auditoriaDTO.getUsername());
			pgimFichaRevision.setIpActualizacion(auditoriaDTO.getTerminal());
		}

		pgimFichaRevision.setFeDesdeParaPresentacion(pgimFichaRevisionDTO.getFeRevisionFicha());
		pgimFichaRevision.setFeRevisionFicha(pgimFichaRevisionDTO.getFeRevisionFicha());
		pgimFichaRevision.setCaDiasDemoraCalculados(pgimFichaRevisionDTO.getCaDiasDemoraCalculados());
		pgimFichaRevision.setCaDiasDemoraEstablecidos(pgimFichaRevisionDTO.getCaDiasDemoraEstablecidos());
		pgimFichaRevision.setCaDiasParaPresentacion(0);
		pgimFichaRevision.setCaDiasPlazoPresentacion(null);

		return pgimFichaRevision;
	}

	@Override
	public PgimFichaObservacion generarPgimFichaObservacion(PgimFichaRevisionDTO pgimFichaRevisionDTO,
			PgimFichaObservacionDTO pgimFichaObservacionDTO, AuditoriaDTO auditoriaDTO, boolean eliminar) {
		PgimFichaObservacion pgimFichaObservacion = new PgimFichaObservacion();

		if (pgimFichaObservacionDTO.getIdFichaObservacion() != null) {
			pgimFichaObservacion = fichaObservacionRepository.findById(pgimFichaObservacionDTO.getIdFichaObservacion())
					.orElse(null);

			pgimFichaObservacion.setFeActualizacion(auditoriaDTO.getFecha());
			pgimFichaObservacion.setUsActualizacion(auditoriaDTO.getUsername());
			pgimFichaObservacion.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {
			if (pgimFichaObservacionDTO.getIdFichaObservacionOrigen() != null) {
				PgimFichaObservacion fichaObservacionOrigen = new PgimFichaObservacion();
				fichaObservacionOrigen.setIdFichaObservacion(pgimFichaObservacionDTO.getIdFichaObservacionOrigen());
				pgimFichaObservacion.setFichaObservacionOrigen(fichaObservacionOrigen);
			}

			PgimFichaRevision pgimFichaRevision = new PgimFichaRevision();
			pgimFichaRevision.setIdFichaRevision(pgimFichaRevisionDTO.getIdFichaRevision());
			pgimFichaObservacion.setPgimFichaRevision(pgimFichaRevision);

			if (pgimFichaObservacionDTO.getIdTipoObservacionFicha() != null) {
				PgimValorParametro tipoObservacionFicha = new PgimValorParametro();
				tipoObservacionFicha.setIdValorParametro(pgimFichaObservacionDTO.getIdTipoObservacionFicha());
				pgimFichaObservacion.setTipoObservacionFicha(tipoObservacionFicha);
			} else {
				PgimValorParametro tipoObservacionFicha = new PgimValorParametro();
				tipoObservacionFicha.setIdValorParametro(ConstantesUtil.PARAM_TIPO_NUEVA);
				pgimFichaObservacion.setTipoObservacionFicha(tipoObservacionFicha);
			}

			pgimFichaObservacion.setCaDiasParaPresentacion(pgimFichaRevisionDTO.getCaDiasParaPresentacion());
			pgimFichaObservacion.setFeDesdeParaPresentacion(pgimFichaRevisionDTO.getFeDesdeParaPresentacion());
			pgimFichaObservacion.setFePresentacion(pgimFichaRevisionDTO.getFePresentacion());

			pgimFichaObservacion.setFeCreacion(auditoriaDTO.getFecha());
			pgimFichaObservacion.setUsCreacion(auditoriaDTO.getUsername());
			pgimFichaObservacion.setIpCreacion(auditoriaDTO.getTerminal());
		}

		pgimFichaObservacion.setFeRevisionFicha(pgimFichaRevisionDTO.getFeRevisionFicha());

		pgimFichaObservacion.setDeParteInformeObservadaT(pgimFichaObservacionDTO.getDeParteInformeObservadaT());
		pgimFichaObservacion.setDeItemObservacionT(pgimFichaObservacionDTO.getDeItemObservacionT());
		pgimFichaObservacion.setFlSubsanada(pgimFichaObservacionDTO.getFlSubsanada());
		pgimFichaObservacion.setCmItemObservacionT(pgimFichaObservacionDTO.getCmItemObservacionT());

		if (eliminar) {
			pgimFichaObservacion.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		} else {
			pgimFichaObservacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		}

		return pgimFichaObservacion;
	}

	@Override
	public PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorId(Long idDocumentoRelacion) {
		return this.documentoRelacionRepository.obtenerDocumentoRelacionPorId(idDocumentoRelacion);
	}

	@Override
	public PgimFichaRevisionDTO obtenerFichaRevisionPorId(Long idFichaRevision) {
		return this.fichaRevisionRepository.obtenerFichaRevisionPorId(idFichaRevision);
	}

	@Override
	public PgimFichaObservacionDTO obtenerFichaObservacionPorId(Long idFichaObservacion) {
		return this.fichaObservacionRepository.obtenerFichaObservacionPorId(idFichaObservacion);
	}

	@Override
	public PgimDocumento getDocumentoByIdDocumento(Long idDocumento) {
		return this.documentoRepository.findById(idDocumento).orElse(null);
	}

	@Override
	public PgimDocumentoRelacion getDocumentoRelacionByIdDocumentoRelacion(Long idDocumentoRelacion) {
		return this.documentoRelacionRepository.findById(idDocumentoRelacion).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarFichaObservacion(PgimDocumentoDTO pgimDocumentoDTO, AuditoriaDTO auditoriaDTO)
			throws Exception {

		DocumentoAnularInRO documentoAnularInRO = new DocumentoAnularInRO();
		DocumentoAnularOutRO documentoAnularOutRO = null;

		documentoAnularInRO.setNroExpediente(pgimDocumentoDTO.getNuExpedienteSiged());
		documentoAnularInRO.setIdDocumento(pgimDocumentoDTO.getCoDocumentoSiged().toString());
		documentoAnularInRO.setMotivo("Observación no válida");
		documentoAnularInRO.setIdUsuarioAnulacion(auditoriaDTO.getCoUsuarioSiged());

		PgimDocumento pgimDocumento = this.documentoRepository.findById(pgimDocumentoDTO.getIdDocumento()).orElse(null);

		PgimDocumentoRelacion pgimDocumentoRelacion = this.documentoRelacionRepository
				.findById(pgimDocumentoDTO.getDescIdDocumentoRelacion()).orElse(null);

		PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
				.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

		documentoAnularOutRO = this.documentoService.anularDocumentoSiged(documentoAnularInRO,
				pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

		if (documentoAnularOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			// Eliminar el documento PGIM
			pgimDocumento.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());

			this.documentoRepository.save(pgimDocumento);

			// Eliminar la relación PGIM
			pgimDocumentoRelacion.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimDocumentoRelacion.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumentoRelacion.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumentoRelacion.setIpActualizacion(auditoriaDTO.getTerminal());

			this.documentoRelacionRepository.save(pgimDocumentoRelacion);

			// Eliminar la ficha de revisión
			PgimFichaRevisionDTO pgimFichaRevisionDTO = this.fichaRevisionRepository
					.obtenerFichaRevisionPorIdDocumento(pgimDocumento.getIdDocumento());

			PgimFichaRevision pgimFichaRevision = this.fichaRevisionRepository
					.findById(pgimFichaRevisionDTO.getIdFichaRevision()).orElse(null);

			pgimFichaRevision.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimFichaRevision.setFeActualizacion(auditoriaDTO.getFecha());
			pgimFichaRevision.setUsActualizacion(auditoriaDTO.getUsername());
			pgimFichaRevision.setIpActualizacion(auditoriaDTO.getTerminal());

			this.fichaRevisionRepository.save(pgimFichaRevision);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarFichaAprobacion(PgimDocumentoDTO pgimDocumentoDTO, AuditoriaDTO auditoriaDTO) throws Exception {

		DocumentoAnularInRO documentoAnularInRO = new DocumentoAnularInRO();
		DocumentoAnularOutRO documentoAnularOutRO = null;

		documentoAnularInRO.setNroExpediente(pgimDocumentoDTO.getNuExpedienteSiged());
		documentoAnularInRO.setIdDocumento(pgimDocumentoDTO.getCoDocumentoSiged().toString());
		documentoAnularInRO.setMotivo("Ficha de aprobación no válida");
		documentoAnularInRO.setIdUsuarioAnulacion(auditoriaDTO.getCoUsuarioSiged());

		PgimDocumento pgimDocumento = this.documentoRepository.findById(pgimDocumentoDTO.getIdDocumento()).orElse(null);

		PgimDocumentoRelacion pgimDocumentoRelacion = this.documentoRelacionRepository
				.findById(pgimDocumentoDTO.getDescIdDocumentoRelacion()).orElse(null);

		PgimFaseProceso pgimFaseProceso = this.faseProcesoRepository
				.findById(pgimDocumento.getPgimFaseProceso().getIdFaseProceso()).orElse(null);

		documentoAnularOutRO = this.documentoService.anularDocumentoSiged(documentoAnularInRO,
				pgimFaseProceso.getPgimProceso().getIdProceso(), auditoriaDTO);

		if (documentoAnularOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			// Eliminar el documento PGIM
			pgimDocumento.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());

			this.documentoRepository.save(pgimDocumento);

			// Eliminar la relación PGIM
			pgimDocumentoRelacion.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimDocumentoRelacion.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumentoRelacion.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumentoRelacion.setIpActualizacion(auditoriaDTO.getTerminal());

			this.documentoRelacionRepository.save(pgimDocumentoRelacion);

			// Eliminar la ficha de revisión
			PgimFichaRevisionDTO pgimFichaRevisionDTO = this.fichaRevisionRepository
					.obtenerFichaRevisionPorIdDocumento(pgimDocumento.getIdDocumento());

			PgimFichaRevision pgimFichaRevision = this.fichaRevisionRepository
					.findById(pgimFichaRevisionDTO.getIdFichaRevision()).orElse(null);

			pgimFichaRevision.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimFichaRevision.setFeActualizacion(auditoriaDTO.getFecha());
			pgimFichaRevision.setUsActualizacion(auditoriaDTO.getUsername());
			pgimFichaRevision.setIpActualizacion(auditoriaDTO.getTerminal());

			this.fichaRevisionRepository.save(pgimFichaRevision);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public RevisionInforme obtenerObservacionInforme(Long idDocumentoFichaObservacion, Long coTablaInstancia, Long idProceso,
			Long idFase, Long idSubcatDocumento, Long idContrato, String descFechaPresentacionActa) throws Exception {

		PgimDocumentoDTO pgimDocumentoDTO = this.obtenerDocumentoPorId(idDocumentoFichaObservacion);
		PgimDocumento pgimDocumentoObservado = this.documentoRepository.findById(idDocumentoFichaObservacion).orElse(null);
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
				.findById(pgimDocumentoObservado.getPgimInstanciaProces().getIdInstanciaProceso()).orElse(null);

		PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = this.obtenerDocumentoRelacionPorIdDocumento(
				idDocumentoFichaObservacion,
				ConstantesUtil.PARAM_INFORME_OBSERVADO);

		PgimFichaRevisionDTO pgimFichaRevisionDTO = this.obtenerFichaRevisionPorIdDocumento(idDocumentoFichaObservacion);

		if (pgimFichaRevisionDTO == null) {
			throw new PgimException("error", "No se ha podido encontrar los datos de esta observación");
		}

		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO = null;

		try {
			lPgimFichaObservacionDTO = this.obtenerFichaObservacionPorIdFichaRevision(
					pgimFichaRevisionDTO.getIdFichaRevision());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new PgimException("error", "Ocurrió un problema al momento de obtener la ficha de observaciones");
		}

		RevisionInforme revisionInforme = new RevisionInforme();

		revisionInforme.setPgimDocumentoDTO(pgimDocumentoDTO);
		revisionInforme.setPgimDocumentoRelacionDTO(pgimDocumentoRelacionDTO);
		revisionInforme.setlPgimFichaObservacionDTO(lPgimFichaObservacionDTO);

		PgimFichaRevision pgimFichaRevision = null;

		if (pgimFichaRevisionDTO.getCaDiasPlazoPresentacion() == null
				|| pgimFichaRevisionDTO.getFeHastaParaPresentacion() == null
				|| pgimFichaRevisionDTO.getFeFirmaActaFiscaliza() == null) {
			pgimFichaRevision = this.fichaRevisionRepository.findById(pgimFichaRevisionDTO.getIdFichaRevision())
					.orElse(null);
		}

		List<PgimFichaRevisionDTO> lPgimFichaRevisionDTOPrimeraObs = this.fichaRevisionRepository
				.obtenerFichasRevisionObsDescendentes(pgimDocumentoDTO.getIdInstanciaProceso(),
						EValorParametro.REDOC_OBSRVCION.toString());

		if (pgimFichaRevisionDTO.getFeFirmaActaFiscaliza() == null) {

			PgimDocumentoDTO pgiDocumentoDTOActaFiscaliza = this.obtenerDocumentoMasReciente(
					pgimInstanciaProces.getCoTablaInstancia(),
					pgimInstanciaProces.getPgimProceso().getIdProceso(),
					ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION);

			if (lPgimFichaRevisionDTOPrimeraObs.size() > 0) {
				PgimFichaRevisionDTO pgimFichaRevisionDTOPrimeraObs = lPgimFichaRevisionDTOPrimeraObs.get(0);

				if (pgimFichaRevisionDTOPrimeraObs.getIdDocumento().equals(idDocumentoFichaObservacion)) {
					pgimFichaRevisionDTO.setFeFirmaActaFiscaliza(pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento());
					pgimFichaRevision.setFeFirmaActaFiscaliza(pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento());
				}
			}

			pgimFichaRevisionDTO.setFeFirmaActaFiscaliza(pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento());
		}

		if (pgimFichaRevisionDTO.getFeHastaParaPresentacion() == null) {
			// Por compatibilidad se debe calcular la fecha hasta la cual se esperó para la
			// presentación del informe que ha sido observado.

			PgimFichaRevisionDTO pgimFichaRevisionDTOAnterior = this
					.obtenerFichaRevisionInmediadaAnterior(idDocumentoFichaObservacion);
			LocalDate ldFechaDesdeParaPresentacion = null;

			int iDia = 0;
			int iMes = 0;
			int iAnio = 0;
			int diasAdicionales = 1;
			int plazoReal = 0;
			int factorResta = 1;

			if (pgimFichaRevisionDTOAnterior == null) {
				// Se trata de la primera observación

				if (pgimFichaRevisionDTO.getCaDiasPlazoPresentacion() == null) {
					// Por compatibilidad se debe calcular la cantidad de días calendario para la
					// presentación del informe que ha sido observado.
					PgimContrato pgimContrato = this.contratoRepository.findById(idContrato).orElse(null);
					pgimFichaRevisionDTO.setCaDiasPlazoPresentacion(pgimContrato.getNuDiasEntregaInforme());
					pgimFichaRevision.setCaDiasPlazoPresentacion(pgimFichaRevisionDTO.getCaDiasPlazoPresentacion());
				}

				String[] fecha = descFechaPresentacionActa.split("-");

				iDia = Integer.valueOf(fecha[0]);
				iMes = Integer.valueOf(fecha[1]);
				iAnio = Integer.valueOf(fecha[2]);

			} else {
				// No es la primera observacion
				PgimDocumento pgimDocumentoFichaRevisionAnterior = this.documentoRepository.findById(pgimFichaRevisionDTOAnterior.getIdDocumento()).orElse(null);				

				pgimFichaRevisionDTO.setCaDiasPlazoPresentacion(pgimFichaRevisionDTOAnterior.getCaDiasParaPresentacion());
				pgimFichaRevision.setCaDiasPlazoPresentacion(pgimFichaRevisionDTO.getCaDiasPlazoPresentacion());

				Calendar calFeEnvioObservacionesInmediataAnterior = Calendar.getInstance();

				PgimSupervision pgimSupervision = this.supervisionRepository.findById(coTablaInstancia).orElse(null);

				if (pgimSupervision.getFlFeEnvio().equals("1")) {
					calFeEnvioObservacionesInmediataAnterior.setTime(pgimDocumentoFichaRevisionAnterior.getFeEnvioDocumento());
				} else {
					calFeEnvioObservacionesInmediataAnterior.setTime(pgimFichaRevisionDTOAnterior.getFeRevisionFicha());
				}				

				iDia = calFeEnvioObservacionesInmediataAnterior.get(Calendar.DAY_OF_MONTH);
				iMes = calFeEnvioObservacionesInmediataAnterior.get(Calendar.MONTH) + 1;
				iAnio = calFeEnvioObservacionesInmediataAnterior.get(Calendar.YEAR);
			}

			ldFechaDesdeParaPresentacion = this.calcularFechasHabiles(iDia, iMes, iAnio, diasAdicionales);

			if (pgimFichaRevisionDTO.getCaDiasPlazoPresentacion() > 0) {
				plazoReal = pgimFichaRevisionDTO.getCaDiasPlazoPresentacion() - factorResta;
			}

			LocalDate ldFeHastaParaPresentacion = this.calcularFechasCalendario(
					ldFechaDesdeParaPresentacion.getDayOfMonth(),
					ldFechaDesdeParaPresentacion.getMonthValue(), ldFechaDesdeParaPresentacion.getYear(), plazoReal);

			/**
			 * ----------------------------------------
			 * --------------- INICIO DE LA HISTORIA ---------------------
			 * ----------------------------------------
			 */
			LocalDate ldFechaHastaParaPresentacion = this.calcularFechasHabilesMasUnDia(ldFeHastaParaPresentacion);
			/**
			 * ----------------------------------------
			 * --------------- FIN DE LA HISTORIA ---------------------
			 * ----------------------------------------
			 */
			
			ZoneId zonaTiempoSistema = ZoneId.systemDefault();
			// ZonedDateTime fechaTiempoZonificado = ldFeHastaParaPresentacion.atStartOfDay(zonaTiempoSistema);
			ZonedDateTime fechaTiempoZonificado = ldFechaHastaParaPresentacion.atStartOfDay(zonaTiempoSistema);
			Date dFechaMaxPresentacion = Date.from(fechaTiempoZonificado.toInstant());

			pgimFichaRevisionDTO.setFeHastaParaPresentacion(dFechaMaxPresentacion);
			pgimFichaRevision.setFeHastaParaPresentacion(pgimFichaRevisionDTO.getFeHastaParaPresentacion());

		}

		if (pgimFichaRevision != null) {
			this.fichaRevisionRepository.save(pgimFichaRevision);
		}

		revisionInforme.setPgimFichaRevisionDTO(pgimFichaRevisionDTO);

		return revisionInforme;
	}

	private PgimFichaRevisionDTO obtenerFichaRevisionInmediadaAnterior(Long idDocumentoObservado) {
		List<PgimFichaRevisionDTO> lPgimFichaRevisionDTO = this.fichaRevisionRepository
				.obtenerFichasRevisionAnteriores(idDocumentoObservado);

		PgimFichaRevisionDTO pgimFichaRevisionDTOInmediataAnterior = null;

		if (lPgimFichaRevisionDTO.size() > 0) {
			pgimFichaRevisionDTOInmediataAnterior = lPgimFichaRevisionDTO.get(0);
		}

		return pgimFichaRevisionDTOInmediataAnterior;
	}

	@Override
	public RevisionInforme obtenerFichaAprobacionParaCrear(Long idContrato, Long idDocumentoInformeFiscaliza)
			throws Exception {

		RevisionInforme revisionInforme = new RevisionInforme();

		PgimDocumento pgimDocumentoInfConforme = this.documentoRepository.findById(idDocumentoInformeFiscaliza)
				.orElse(null);
		Long idInstanciaProceso = pgimDocumentoInfConforme.getPgimInstanciaProces().getIdInstanciaProceso();

		/**
		 * ---------------------------------------------------
		 * -----------------------PGIM-11133----------------------------
		 * ---------------------------------------------------
		 */
  		List<PgimDocumentoDTO> lDocumentoInforme = this.documentoRepository.obtenerDocumentoInformeSupervisionAux(
				idInstanciaProceso, ConstantesUtil.PARAM_SC_INFORME_SUPERVISION);

		List<PgimDocumentoDTO> documentosFichaObsFiltradosff = lDocumentoInforme.stream()
                                .filter(documentoOrden -> "1".equals(documentoOrden.getFlIncumplimientoET()))
                                .collect(Collectors.toList());

		PgimDocumentoDTO pgimDocumentoDTO = null;
		if(documentosFichaObsFiltradosff.size() != 0){
			pgimDocumentoDTO = documentosFichaObsFiltradosff.get(0);
		}
		/**
		 * ---------------------------------------------------
		 * -----------------------Fin----------------------------
		 * ---------------------------------------------------
		 */

		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso)
				.orElse(null);

		Long idSupervision = pgimInstanciaProces.getCoTablaInstancia();

		PgimSupervision pgimSupervision = this.supervisionRepository.findById(idSupervision).orElse(null);

		List<PgimFichaRevisionDTO> lPgimFichaRevisionDTOPrimeraObs = this.fichaRevisionRepository
				.obtenerFichasRevisionObsDescendentes(idInstanciaProceso,
						EValorParametro.REDOC_OBSRVCION.toString());

		// ====================================================================
		// 1. SE CALCULA CUÁNTO SE HA DEMORADO EN LA PRESENTACIÓN DEL INFORME 1
		// ====================================================================
		Integer caDiasPlazoPresentacionInforme1 = 0;
		int caDiasParaPresentacionAbsObservaciones1 = 0;

		int iAnioDesdeParaPresentacion1Inf = 0;
		int iMesDesdeParaPresentacion1Inf = 0;
		int iDiaDesdeParaPresentacion1Inf = 0;

		PgimFichaRevisionDTO pgimFichaRevisionDTO1ObservacionInf = null;

		LocalDate ldFechaFirmaActaFiscalizacion = null;
		LocalDate ldFeDesdeParaPresentacionInf1 = null;
		LocalDate ldFechaPresentacionInf1 = null;
		LocalDate ldFechaHastaParaPresentacionInforme1 = null;

		PgimDocumento documentoFichaObsInforme1 = null;

		Calendar feEnvioDocumentoInf1 = Calendar.getInstance();

		Date feOrigenDocumentoActaFiscaliza = null;

		if (lPgimFichaRevisionDTOPrimeraObs.size() == 0) {
			// Si no existen observaciones la cantidad del plazo para la presentación y la
			// cantidad de días para la absolución se
			// obtienen del contrato.

			PgimContrato pgimContrato = this.contratoRepository.findById(idContrato).orElse(null);

			caDiasPlazoPresentacionInforme1 = pgimContrato.getNuDiasEntregaInforme();
			caDiasParaPresentacionAbsObservaciones1 = pgimContrato.getNuDiasAbsolucionInforme();

			PgimDocumentoDTO pgiDocumentoDTOActaFiscaliza = this.obtenerDocumentoMasReciente(
					pgimInstanciaProces.getCoTablaInstancia(), pgimInstanciaProces.getPgimProceso().getIdProceso(),
					ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION);

			feOrigenDocumentoActaFiscaliza = pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento();

			Calendar calFeOrigenDocumentoActa = Calendar.getInstance();
			calFeOrigenDocumentoActa.setTime(feOrigenDocumentoActaFiscaliza);

			iDiaDesdeParaPresentacion1Inf = calFeOrigenDocumentoActa.get(Calendar.DAY_OF_MONTH);
			iMesDesdeParaPresentacion1Inf = calFeOrigenDocumentoActa.get(Calendar.MONTH) + 1;
			iAnioDesdeParaPresentacion1Inf = calFeOrigenDocumentoActa.get(Calendar.YEAR);

			ldFeDesdeParaPresentacionInf1 = this.calcularFechasHabiles(iDiaDesdeParaPresentacion1Inf,
					iMesDesdeParaPresentacion1Inf, iAnioDesdeParaPresentacion1Inf, 1);

			if (pgimSupervision.getFlFeEnvio().equals("1")) {
				feEnvioDocumentoInf1.setTime(pgimDocumentoInfConforme.getFeEnvioDocumento());
			} else {
				feEnvioDocumentoInf1.setTime(pgimDocumentoInfConforme.getFeOrigenDocumento());
			}

		} else {
			// Sí existen observaciones la cantidad del plazo para la presentación y la
			// cantidad de días para la absolución se obtienen de los datos respectivos persistidos en la 
			// primera observación.

			pgimFichaRevisionDTO1ObservacionInf = lPgimFichaRevisionDTOPrimeraObs.get(0);
			feOrigenDocumentoActaFiscaliza = pgimFichaRevisionDTO1ObservacionInf.getFeFirmaActaFiscaliza();

			if (feOrigenDocumentoActaFiscaliza == null) {
				PgimDocumentoDTO pgiDocumentoDTOActaFiscaliza = this.obtenerDocumentoMasReciente(
						pgimInstanciaProces.getCoTablaInstancia(), pgimInstanciaProces.getPgimProceso().getIdProceso(),
						ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION);

				feOrigenDocumentoActaFiscaliza = pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento();
			}

			PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = this.documentoRelacionRepository
					.obtenerDocumentoRelacionPorIdDocumentoHijo(
							pgimFichaRevisionDTO1ObservacionInf.getIdDocumento(),
							EValorParametro.REDOC_OBSRVCION.toString());

			PgimDocumento documentoInforme1Observado = this.documentoRepository
					.findById(pgimDocumentoRelacionDTO.getIdDocumentoPadre()).orElse(null);

			documentoFichaObsInforme1 = this.documentoRepository
					.findById(pgimDocumentoRelacionDTO.getIdDocumento()).orElse(null);

			caDiasParaPresentacionAbsObservaciones1 = pgimFichaRevisionDTO1ObservacionInf.getCaDiasParaPresentacion();
			caDiasPlazoPresentacionInforme1 = pgimFichaRevisionDTO1ObservacionInf.getCaDiasPlazoPresentacion();

			Calendar calFeDesdeParaPresentacion1Inf = Calendar.getInstance();
			calFeDesdeParaPresentacion1Inf.setTime(pgimFichaRevisionDTO1ObservacionInf.getFeDesdeParaPresentacion());

			iAnioDesdeParaPresentacion1Inf = calFeDesdeParaPresentacion1Inf.get(Calendar.YEAR);
			iMesDesdeParaPresentacion1Inf = calFeDesdeParaPresentacion1Inf.get(Calendar.MONTH) + 1;
			iDiaDesdeParaPresentacion1Inf = calFeDesdeParaPresentacion1Inf.get(Calendar.DAY_OF_MONTH);

			ldFeDesdeParaPresentacionInf1 = LocalDate.of(iAnioDesdeParaPresentacion1Inf, iMesDesdeParaPresentacion1Inf,
					iDiaDesdeParaPresentacion1Inf);

			if (pgimSupervision.getFlFeEnvio().equals("1")) {
				feEnvioDocumentoInf1.setTime(documentoInforme1Observado.getFeEnvioDocumento());
			} else {
				feEnvioDocumentoInf1.setTime(documentoInforme1Observado.getFeOrigenDocumento());
			}
		}

		int iDiaInf1 = feEnvioDocumentoInf1.get(Calendar.DAY_OF_MONTH);
		int iMesInf1 = feEnvioDocumentoInf1.get(Calendar.MONTH) + 1;
		int iAnioInf1 = feEnvioDocumentoInf1.get(Calendar.YEAR);

		ldFechaPresentacionInf1 = LocalDate.of(iAnioInf1, iMesInf1, iDiaInf1);

		Calendar calFeOrigenDocumentoActaFiscaliza = Calendar.getInstance();
		calFeOrigenDocumentoActaFiscaliza.setTime(feOrigenDocumentoActaFiscaliza);

		iDiaDesdeParaPresentacion1Inf = calFeOrigenDocumentoActaFiscaliza.get(Calendar.DAY_OF_MONTH);
		iMesDesdeParaPresentacion1Inf = calFeOrigenDocumentoActaFiscaliza.get(Calendar.MONTH) + 1;
		iAnioDesdeParaPresentacion1Inf = calFeOrigenDocumentoActaFiscaliza.get(Calendar.YEAR);

		ldFechaFirmaActaFiscalizacion = LocalDate.of(iAnioDesdeParaPresentacion1Inf, iMesDesdeParaPresentacion1Inf,
				iDiaDesdeParaPresentacion1Inf);

		int plazoEntregaInforme1Real = 0;
		if (caDiasPlazoPresentacionInforme1 > 0) {
			plazoEntregaInforme1Real = caDiasPlazoPresentacionInforme1 - 1;
		}

		LocalDate ldFeHastaParaPresentacionInforme1 = this.calcularFechasCalendario(
				ldFeDesdeParaPresentacionInf1.getDayOfMonth(),
				ldFeDesdeParaPresentacionInf1.getMonthValue(), ldFeDesdeParaPresentacionInf1.getYear(),
				plazoEntregaInforme1Real);

		/**
		 * ----------------------------------------------------------
		 * --------------- INICIO DE LA HISTORIA ---------------------
		 * ----------------------------------------------------------
		 */
		ldFechaHastaParaPresentacionInforme1 = this.calcularFechasHabilesMasUnDia(ldFeHastaParaPresentacionInforme1);
		Date dateFechaHastaParaPresentacionInforme1 = CommonsUtil.convertirADate(ldFechaHastaParaPresentacionInforme1);
		/**
		 * ----------------------------------------------------------
		 * --------------- FIN DE LA HISTORIA ---------------------
		 * ----------------------------------------------------------
		 */

		Integer caDiasDemoraInforme1 = this.obtenerCantidadDiasDemora(ldFechaPresentacionInf1, ldFechaHastaParaPresentacionInforme1);

		// ==================================================
		// 2. AHORA SE PROCESA LA EXISTENCIA DE OBSERVACIONES
		// ==================================================
		Integer caDiasDemoraDesde1ObsHastaInfConforme = null;
		LocalDate ldFeObservacion1 = null;
		Integer caDiasDemoraDesde1ObsHastaInfConformeReal = 0;

		if (lPgimFichaRevisionDTOPrimeraObs.size() > 0) {
			// Sí existen observaciones
			// ========================
			Calendar feOrigenDocumentoInfConforme = Calendar.getInstance();
			if (pgimSupervision.getFlFeEnvio().equals("1")) {
				feOrigenDocumentoInfConforme.setTime(pgimDocumentoInfConforme.getFeEnvioDocumento());
			} else {
				feOrigenDocumentoInfConforme.setTime(pgimDocumentoInfConforme.getFeOrigenDocumento());
			}

			int iDiaInfConforme = feOrigenDocumentoInfConforme.get(Calendar.DAY_OF_MONTH);
			int iMesInfConforme = feOrigenDocumentoInfConforme.get(Calendar.MONTH) + 1;
			int iAnioInfConforme = feOrigenDocumentoInfConforme.get(Calendar.YEAR);

			LocalDate fechaPresentacionInfConforme = LocalDate.of(iAnioInfConforme, iMesInfConforme, iDiaInfConforme);

			int plazo1SubsanacionObs1Real = 0;
			if (caDiasParaPresentacionAbsObservaciones1 > 0) {
				plazo1SubsanacionObs1Real = caDiasParaPresentacionAbsObservaciones1 - 1;
			}

			Calendar calFeEnvioObservacion1 = Calendar.getInstance();

			if (pgimSupervision.getFlFeEnvio().equals("1")) {
				calFeEnvioObservacion1.setTime(documentoFichaObsInforme1.getFeEnvioDocumento());
			} else {
				calFeEnvioObservacion1.setTime(pgimFichaRevisionDTO1ObservacionInf.getFeRevisionFicha());
			}			

			int iAnioFeFichaRevisionObservacion1 = calFeEnvioObservacion1.get(Calendar.YEAR);
			int iMesFeFichaRevisionObservacion1 = calFeEnvioObservacion1.get(Calendar.MONTH) + 1;
			int iDiaFeFichaRevisionObservacion1 = calFeEnvioObservacion1.get(Calendar.DAY_OF_MONTH);

			ldFeObservacion1 = LocalDate.of(iAnioFeFichaRevisionObservacion1, iMesFeFichaRevisionObservacion1, iDiaFeFichaRevisionObservacion1);

			LocalDate ldFeDesdeParaPresentacionSubsanacion = this.calcularFechasHabiles(iDiaFeFichaRevisionObservacion1, iMesFeFichaRevisionObservacion1, iAnioFeFichaRevisionObservacion1, 1);

			LocalDate feHastaPresentacionSubsanacionObs1Local = this.calcularFechasCalendario(ldFeDesdeParaPresentacionSubsanacion.getDayOfMonth(), ldFeDesdeParaPresentacionSubsanacion.getMonthValue(), ldFeDesdeParaPresentacionSubsanacion.getYear(), plazo1SubsanacionObs1Real);
			
			caDiasDemoraDesde1ObsHastaInfConforme = this.obtenerCantidadDiasDemora(fechaPresentacionInfConforme, feHastaPresentacionSubsanacionObs1Local);

			/**
			 * ---------------------------------------------------
			 * -----------------------PGIM-11133----------------------------
			 * ---------------------------------------------------
			 */
			if(pgimDocumentoDTO != null && pgimDocumentoDTO.getFlIncumplimientoET() != null){

				Calendar fePresentacionInforme = Calendar.getInstance();
				fePresentacionInforme.setTime(pgimDocumentoDTO.getFeEnvioDocumento());

				int iDiaInforme = fePresentacionInforme.get(Calendar.DAY_OF_MONTH);
				int iMesInforme = fePresentacionInforme.get(Calendar.MONTH) + 1;
				int iAnioInforme = fePresentacionInforme.get(Calendar.YEAR);

				LocalDate fechaPresentacionInforme = LocalDate.of(iAnioInforme, iMesInforme, iDiaInforme);

				caDiasDemoraDesde1ObsHastaInfConformeReal = this.obtenerCantidadDiasDemora(fechaPresentacionInforme, feHastaPresentacionSubsanacionObs1Local);
			}
			/**
			 * ---------------------------------------------------
			 * -----------------------Fin----------------------------
			 * ---------------------------------------------------
			 */
		}

		// ================================================
		// 3. COMPLETAMOS LOS DATOS DE LA FICHA DE REVISIÓN
		// ================================================

		PgimFichaRevisionDTO pgimFichaRevisionDTO = new PgimFichaRevisionDTO();

		pgimFichaRevisionDTO.setDescFeFirmaActaFiscalizacion(CommonsUtil.convertirADate(ldFechaFirmaActaFiscalizacion));
		pgimFichaRevisionDTO
				.setDescFeDesdeParaPresentacionInforme1(CommonsUtil.convertirADate(ldFeDesdeParaPresentacionInf1));
		pgimFichaRevisionDTO
				.setDescCaDiasPlazoPresentacionInforme1(CommonsUtil.convertirALong(caDiasPlazoPresentacionInforme1));
		pgimFichaRevisionDTO.setCaDiasPlazoPresentacion(0);
		pgimFichaRevisionDTO.setDescFeHastaParaPresentacionInforme1(dateFechaHastaParaPresentacionInforme1);
		pgimFichaRevisionDTO.setDescFePresentacionInforme1(CommonsUtil.convertirADate(ldFechaPresentacionInf1));
		pgimFichaRevisionDTO.setDescCaDiasDemoraInforme1(CommonsUtil.convertirALong(caDiasDemoraInforme1));
		pgimFichaRevisionDTO.setDescFeObservacionInforme1(CommonsUtil.convertirADate(ldFeObservacion1));
		pgimFichaRevisionDTO.setDescCaDiasDemoraDesde1ObsHastaInfConforme(
				CommonsUtil.convertirALong(caDiasDemoraDesde1ObsHastaInfConforme));

		if (ldFeObservacion1 != null) {
			pgimFichaRevisionDTO.setDescCaDiasParaPresentacionInforme2(caDiasParaPresentacionAbsObservaciones1);

			Date feEnvioDocumentoInfConforme = null;

			if (pgimSupervision.getFlFeEnvio().equals("1")) {
				feEnvioDocumentoInfConforme = pgimDocumentoInfConforme.getFeEnvioDocumento();
			} else {
				feEnvioDocumentoInfConforme= pgimDocumentoInfConforme.getFeOrigenDocumento();
			}

			pgimFichaRevisionDTO.setFePresentacion(feEnvioDocumentoInfConforme);

		} else {
			pgimFichaRevisionDTO.setDescCaDiasParaPresentacionInforme2(null);
			pgimFichaRevisionDTO.setFePresentacion(null);
		}

		Integer totalDiasDemora = caDiasDemoraInforme1 + (caDiasDemoraDesde1ObsHastaInfConforme == null ? 0 : caDiasDemoraDesde1ObsHastaInfConforme);

		/**
		 * ---------------------------------------------------
		 * -----------------------PGIM-11133----------------------------
		 * ---------------------------------------------------
		 */
		if(caDiasDemoraDesde1ObsHastaInfConformeReal != 0){
			Integer totalDiasDemoraReal = caDiasDemoraInforme1 + (caDiasDemoraDesde1ObsHastaInfConformeReal == null ? 0 : caDiasDemoraDesde1ObsHastaInfConformeReal);
			pgimFichaRevisionDTO.setCaDiasDemoraEstablecidos(totalDiasDemoraReal); // ajustar
		}else{
			pgimFichaRevisionDTO.setCaDiasDemoraEstablecidos(totalDiasDemora); // ajustar
		}
		/**
		 * ---------------------------------------------------
		 * -----------------------Fin----------------------------
		 * ---------------------------------------------------
		 */

		pgimFichaRevisionDTO.setFeRevisionFicha(null);
		pgimFichaRevisionDTO.setCaDiasDemoraCalculados(totalDiasDemora);
		// pgimFichaRevisionDTO.setCaDiasDemoraEstablecidos(totalDiasDemora); // ajustar

		if ((totalDiasDemora) > 0) {
			pgimFichaRevisionDTO.setFlAplicaPenalidad("1");
		}
		pgimFichaRevisionDTO.setFlObservacionEpp("0");
		pgimFichaRevisionDTO.setCmObservacionEpp(null);

		revisionInforme.setPgimFichaRevisionDTO(pgimFichaRevisionDTO);

		return revisionInforme;
	}

	@Override
	public RevisionInforme obtenerAprobacionInforme(Long idDocumentoConformidad, Long idContrato)
			throws Exception {

		PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = this.obtenerDocumentoRelacionPorIdDocumento(
				idDocumentoConformidad,
				ConstantesUtil.PARAM_INFORME_APROBADO);

		PgimDocumento pgimDocumentoInformeFiscaliza = this.documentoRepository
				.findById(pgimDocumentoRelacionDTO.getIdDocumentoPadre())
				.orElse(null);

		PgimDocumentoDTO pgimDocumentoInforme = this.documentoService
				.obtenerDocumentoById(pgimDocumentoInformeFiscaliza.getIdDocumento());

		PgimFichaRevisionDTO pgimFichaRevisionDTO = this.obtenerFichaRevisionPorIdDocumento(idDocumentoConformidad);

		RevisionInforme revisionInforme = this.obtenerFichaAprobacionParaCrear(idContrato,
				pgimDocumentoInformeFiscaliza.getIdDocumento());

		PgimFichaRevisionDTO pgimFichaRevisionDTOPreparado = revisionInforme.getPgimFichaRevisionDTO();

		pgimFichaRevisionDTOPreparado.setIdFichaRevision(pgimFichaRevisionDTO.getIdFichaRevision());
		pgimFichaRevisionDTOPreparado.setCaDiasPlazoPresentacion(pgimFichaRevisionDTO.getCaDiasPlazoPresentacion());
		pgimFichaRevisionDTOPreparado.setFeRevisionFicha(pgimFichaRevisionDTO.getFeRevisionFicha());
		pgimFichaRevisionDTOPreparado.setCaDiasDemoraCalculados(pgimFichaRevisionDTO.getCaDiasDemoraCalculados());
		pgimFichaRevisionDTOPreparado.setCaDiasDemoraEstablecidos(pgimFichaRevisionDTO.getCaDiasDemoraEstablecidos());
		pgimFichaRevisionDTOPreparado.setFlAplicaPenalidad(pgimFichaRevisionDTO.getFlAplicaPenalidad());
		pgimFichaRevisionDTOPreparado.setCmPenalidad(pgimFichaRevisionDTO.getCmPenalidad());

		// Por realizar fiscalización sin EPP
		pgimFichaRevisionDTOPreparado.setFlObservacionEpp(pgimFichaRevisionDTO.getFlObservacionEpp());
		pgimFichaRevisionDTOPreparado.setCmObservacionEpp(pgimFichaRevisionDTO.getCmObservacionEpp());

		// Por usar equipos de protección personal (EPP) del agente fiscalizado
		pgimFichaRevisionDTOPreparado.setFlEppAfiscalizado(pgimFichaRevisionDTO.getFlEppAfiscalizado());
		pgimFichaRevisionDTOPreparado.setCmEppFiscalizado(pgimFichaRevisionDTO.getCmEppFiscalizado());

		// Contar con equipos defectuosos
		pgimFichaRevisionDTOPreparado.setFlEquiposDefectuosos(pgimFichaRevisionDTO.getFlEquiposDefectuosos());
		pgimFichaRevisionDTOPreparado.setCmEquiposDefectuosos(pgimFichaRevisionDTO.getCmEquiposDefectuosos());

		// Contar con instrumentos de medición defectuosos
		pgimFichaRevisionDTOPreparado.setFlEqpMedicionDefectuosos(pgimFichaRevisionDTO.getFlEqpMedicionDefectuosos());
		pgimFichaRevisionDTOPreparado.setCmEqpMedicionDefectuosos(pgimFichaRevisionDTO.getCmEqpMedicionDefectuosos());

		// Sin contar con equipos
		pgimFichaRevisionDTOPreparado.setFlSinEquipos(pgimFichaRevisionDTO.getFlSinEquipos());
		pgimFichaRevisionDTOPreparado.setCmSinEquipos(pgimFichaRevisionDTO.getCmSinEquipos());

		// Equipos sin certificado de calibración vigente
		pgimFichaRevisionDTOPreparado.setFlEqpCalibracionNvigente(pgimFichaRevisionDTO.getFlEqpCalibracionNvigente());
		pgimFichaRevisionDTOPreparado.setCmEqpCalibracionNvigente(pgimFichaRevisionDTO.getCmEqpCalibracionNvigente());

		// Sin contar con instrumentos de medición
		pgimFichaRevisionDTOPreparado.setFlSinInstrumentos(pgimFichaRevisionDTO.getFlSinInstrumentos());
		pgimFichaRevisionDTOPreparado.setCmSinInstrumentos(pgimFichaRevisionDTO.getCmSinInstrumentos());

		// Instrumentos de medición sin certificado de calibración vigente
		pgimFichaRevisionDTOPreparado.setFlInsCalibracionNvigente(pgimFichaRevisionDTO.getFlInsCalibracionNvigente());
		pgimFichaRevisionDTOPreparado.setCmInsCalibracionNvigente(pgimFichaRevisionDTO.getCmInsCalibracionNvigente());

		// Por alterar los formatos de las actas proporcionados
		pgimFichaRevisionDTOPreparado.setFlAlterarFormatos(pgimFichaRevisionDTO.getFlAlterarFormatos());
		pgimFichaRevisionDTOPreparado.setCmAlterarFormatos(pgimFichaRevisionDTO.getCmAlterarFormatos());

		// Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)
		pgimFichaRevisionDTOPreparado.setFlFrustrarFiscalizacion(pgimFichaRevisionDTO.getFlFrustrarFiscalizacion());
		pgimFichaRevisionDTOPreparado.setCmFrustrarFiscalizacion(pgimFichaRevisionDTO.getCmFrustrarFiscalizacion());

		revisionInforme.setPgimFichaRevisionDTO(pgimFichaRevisionDTOPreparado);
		revisionInforme.setPgimDocumentoDTO(pgimDocumentoInforme);
		revisionInforme.setPgimDocumentoRelacionDTO(pgimDocumentoRelacionDTO);

		return revisionInforme;
	}

	@Override
	public PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorIdDocumento(Long idDocumento,
			Long tipoRelacionDocumento) {
		return this.documentoRelacionRepository.obtenerDocumentoRelacionPorIdDocumento(idDocumento,
				tipoRelacionDocumento);
	}

	@Override
	public PgimFichaRevisionDTO obtenerFichaRevisionPorIdDocumento(Long idDocumento) {
		return this.fichaRevisionRepository.obtenerFichaRevisionPorIdDocumento(idDocumento);
	}

	@Override
	public List<PgimFichaObservacionDTO> obtenerFichaObservacionPorIdFichaRevision(Long idFichaRevision) {
		return this.fichaObservacionRepository.obtenerFichaObservacionPorIdFichaRevision(idFichaRevision);
	}

	@Override
	public PgimDocumentoDTO obtenerDocumentoPorId(Long idDocumento) {
		return this.documentoRepository.obtenerDocumentoPorId(idDocumento);
	}

	@Override
	public RevisionInforme obtenerObservacionInformeParaCrear(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento, Long idContrato, Long idDocumentoInformeFiscaliza)
			throws Exception {

		RevisionInforme revisionInformeParaCrear = new RevisionInforme();

		List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO = new LinkedList<PgimFichaObservacionDTO>();

		PgimDocumentoDTO penultimoPgimDocumentoDTO = this.obtenerPenultimoDocumentoDeSubcategoria(coTablaInstancia,
				idProceso,
				idFase, idSubcatDocumento, null);

		PgimDocumento pgimDocumentoInformeFiscaliza = this.documentoRepository.findById(idDocumentoInformeFiscaliza)
				.orElse(null);

		List<PgimDocumentoDTO> lDocumentoInforme = this.documentoRepository.obtenerDocumentoInformeSupervisionAux(pgimDocumentoInformeFiscaliza.getPgimInstanciaProces().getIdInstanciaProceso(), ConstantesUtil.PARAM_SC_INFORME_SUPERVISION);

		LocalDate ldFeDesdeParaPresentacion = null;
		LocalDate ldFeHastaParaPresentacion = null;
		LocalDate ldFeOrigenDocumentoPadre = null;
		LocalDate ldFechaHastaParaPresentacion = null;

		int nuDiasParaEntregarInformeActual = 0;
		int nuDiasParaAbsolverObservaciones = 0;
		int caDiasDemoraCalculados = 0;

		PgimFichaRevisionDTO pgimFichaRevisionDTOAnterior = null;
		PgimFichaRevisionDTO pgimFichaRevisionDTOParaCrear = new PgimFichaRevisionDTO();

		int iDia = 0;
		int iMes = 0;
		int iAnio = 0;

		int plazoReal = 0;

		PgimDocumentoDTO pgiDocumentoDTOActaFiscaliza = this.obtenerDocumentoMasReciente(
				coTablaInstancia, idProceso,
				ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION);

		Calendar caFeOrigenDocumentoActaFiscaliza;
		caFeOrigenDocumentoActaFiscaliza = Calendar.getInstance();
		caFeOrigenDocumentoActaFiscaliza.setTime(pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento());

		PgimSupervision pgimSupervision = this.supervisionRepository.findById(coTablaInstancia).orElse(null);

		if (penultimoPgimDocumentoDTO != null) {
			// Quiere decir que sí se ha registrado una observación sobre el informe en
			// cuestión.

			PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = this.documentoRelacionRepository
					.obtenerDocumentoRelacionPorIdDocumentoPadre(penultimoPgimDocumentoDTO.getIdDocumento(),
							ConstantesUtil.PARAM_INFORME_OBSERVADO);

			pgimFichaRevisionDTOAnterior = this.fichaRevisionRepository
					.obtenerFichaRevisionPorIdDocumento(pgimDocumentoRelacionDTO.getIdDocumento());

			lPgimFichaObservacionDTO = this.fichaObservacionRepository
					.obtenerFichaObservacionPorIdFichaRevision(pgimFichaRevisionDTOAnterior.getIdFichaRevision());

			nuDiasParaEntregarInformeActual = pgimFichaRevisionDTOAnterior.getCaDiasParaPresentacion();
			nuDiasParaAbsolverObservaciones = nuDiasParaEntregarInformeActual;

			PgimDocumento pgimDocumento = this.documentoRepository.findById(pgimDocumentoRelacionDTO.getIdDocumento())
					.orElse(null);

			Calendar feFechaEnvioDocumento = Calendar.getInstance();

			if (pgimSupervision.getFlFeEnvio().equals("1")) {
				feFechaEnvioDocumento.setTime(pgimDocumento.getFeEnvioDocumento());
			} else {
				feFechaEnvioDocumento.setTime(pgimDocumento.getFeOrigenDocumento());
			}

			iDia = feFechaEnvioDocumento.get(Calendar.DAY_OF_MONTH);
			iMes = feFechaEnvioDocumento.get(Calendar.MONTH) + 1;
			iAnio = feFechaEnvioDocumento.get(Calendar.YEAR);

			ldFeDesdeParaPresentacion = this.calcularFechasHabiles(iDia, iMes, iAnio, 1);

			// Se valida si el plazo es 0 no se realice resta de dias.
			if (nuDiasParaEntregarInformeActual > 0) {
				plazoReal = nuDiasParaEntregarInformeActual - 1;
			}

		} else {
			// Quiere decir que no se ha registrado documento como parte de la revisión del
			// informe en cuestión.

			PgimContrato pgimContrato = this.contratoRepository.findById(idContrato).orElse(null);
			nuDiasParaEntregarInformeActual = pgimContrato.getNuDiasEntregaInforme();
			nuDiasParaAbsolverObservaciones = pgimContrato.getNuDiasAbsolucionInforme();

			iDia = caFeOrigenDocumentoActaFiscaliza.get(Calendar.DAY_OF_MONTH);
			iMes = caFeOrigenDocumentoActaFiscaliza.get(Calendar.MONTH) + 1;
			iAnio = caFeOrigenDocumentoActaFiscaliza.get(Calendar.YEAR);

			ldFeDesdeParaPresentacion = this.calcularFechasHabiles(iDia, iMes, iAnio, 1);

			if (pgimContrato.getNuDiasEntregaInforme() > 0) {
				plazoReal = pgimContrato.getNuDiasEntregaInforme() - 1;
			}
		}

		ldFeHastaParaPresentacion = this.calcularFechasCalendario(ldFeDesdeParaPresentacion.getDayOfMonth(),
				ldFeDesdeParaPresentacion.getMonthValue(), ldFeDesdeParaPresentacion.getYear(), plazoReal);

		Calendar feEnvioDocumentoPadre = Calendar.getInstance();

		if (pgimSupervision.getFlFeEnvio().equals("1")) {
			feEnvioDocumentoPadre.setTime(pgimDocumentoInformeFiscaliza.getFeEnvioDocumento());
		} else {
			feEnvioDocumentoPadre.setTime(pgimDocumentoInformeFiscaliza.getFeOrigenDocumento());
		}

		int iDiaPadre = feEnvioDocumentoPadre.get(Calendar.DAY_OF_MONTH);
		int iMesPadre = feEnvioDocumentoPadre.get(Calendar.MONTH) + 1;
		int iAnioPadre = feEnvioDocumentoPadre.get(Calendar.YEAR);

		ldFeOrigenDocumentoPadre = LocalDate.of(iAnioPadre, iMesPadre, iDiaPadre);
		Date dfeDesdeParaPresentacion = CommonsUtil.convertirADate(ldFeDesdeParaPresentacion);

		/**
		 * ----------------------------------------
		 * --------------- INICIO DE LA HISTORIA ---------------------
		 * ----------------------------------------
		 */
		ldFechaHastaParaPresentacion = this.calcularFechasHabilesMasUnDia(ldFeHastaParaPresentacion);
		caDiasDemoraCalculados = this.obtenerCantidadDiasDemora(ldFeOrigenDocumentoPadre, ldFechaHastaParaPresentacion);
		Date dateFechaHastaParaPresentacion = CommonsUtil.convertirADate(ldFechaHastaParaPresentacion);
		
		// A partir de la segunda observación, el tiempo de subsanación será por defecto
		// 0 y esta no debe ser editable.
		if (lDocumentoInforme.size() != 1) {
			nuDiasParaAbsolverObservaciones = 0;
		}
		/**
		* ----------------------------------------
		* --------------- FIN DE LA HISTORIA ---------------------
		* ----------------------------------------
		*/

		pgimFichaRevisionDTOParaCrear.setFeFirmaActaFiscaliza(pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento());
		pgimFichaRevisionDTOParaCrear.setCaDiasParaPresentacion(nuDiasParaAbsolverObservaciones);
		pgimFichaRevisionDTOParaCrear.setFeDesdeParaPresentacion(dfeDesdeParaPresentacion);
		pgimFichaRevisionDTOParaCrear.setFeHastaParaPresentacion(dateFechaHastaParaPresentacion);
		pgimFichaRevisionDTOParaCrear.setIdTipoConformidad(ConstantesUtil.PARAM_INFORME_OBSERVADO);
		pgimFichaRevisionDTOParaCrear.setCaDiasDemoraCalculados(caDiasDemoraCalculados);
		pgimFichaRevisionDTOParaCrear.setFeRevisionFicha(null);
		pgimFichaRevisionDTOParaCrear.setCaDiasPlazoPresentacion(nuDiasParaEntregarInformeActual);
		pgimFichaRevisionDTOParaCrear.setDescCaDiasParaPresentacionInforme2(nuDiasParaAbsolverObservaciones);

		revisionInformeParaCrear.setPgimFichaRevisionDTO(pgimFichaRevisionDTOParaCrear);
		revisionInformeParaCrear.setlPgimFichaObservacionDTO(lPgimFichaObservacionDTO);

		return revisionInformeParaCrear;
	}

	/**
	 * Permite obtener el informe de fiscalización anterior al actual.
	 * 
	 * @param coTablaInstancia
	 * @param idProceso
	 * @param idFase
	 * @param idSubcatDocumento
	 * @param idDocumento:      Identificador del documento padre, usado para la
	 *                          modificacion y consulta
	 * @return
	 */
	private PgimDocumentoDTO obtenerPenultimoDocumentoDeSubcategoria(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento, Long idDocumento) {
		// obtenemos la instancia del proceso
		PgimInstanciaProcesDTO pgimInstanciaProcesDTO = this.instanciaProcesRepository
				.obtenerInstanciaProcesoFase(idProceso, idFase, coTablaInstancia);

		// Obtenemos la lista de documentos de tipo informe de supervisión
		List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository
				.obtenerDocumentoInformeSupervisionAux(pgimInstanciaProcesDTO.getIdInstanciaProceso(),
						idSubcatDocumento);

		PgimDocumentoDTO penultimoPgimDocumentoDTO = null;

		for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO) {

			if (pgimDocumentoDTO.getDescIdDocumentoRelacion() == null) {
				continue;
			} else {
				penultimoPgimDocumentoDTO = pgimDocumentoDTO;
				break;
			}
		}

		return penultimoPgimDocumentoDTO;
	}

	/**
	 * Permite el calculo de la fecha ingresada(dia, mes, año) + cantidad de dias,
	 * no considera sabados, domingos y feriados.
	 * 
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param cantDias
	 * @return
	 * @throws Exception
	 */
	private LocalDate calcularFechasHabiles(int dia, int mes, int anio, int cantDias) throws Exception {
		LocalDate fechaActual = LocalDate.of(anio, mes, dia);
		for (int i = 0; i < cantDias; i++) {
			fechaActual = fechaActual.plusDays(1);
			fechaActual = validarSabadosDomingosFeriados(fechaActual);
		}
		return fechaActual;
	}
	
	/**
	 * Permite el calculo de la fecha ingresada(dia, mes, año),
	 * no considera sabados, domingos y feriados.
	 * 
	 * @param ldFeHastaParaPresentacion
	 * @return
	 * @throws Exception
	 */
	private LocalDate calcularFechasHabilesMasUnDia(LocalDate ldFeHastaParaPresentacion) throws Exception {

		Date dFechaHastaParaPresentacion = CommonsUtil.convertirADate(ldFeHastaParaPresentacion);

		Calendar feHastaParaPresentacion = Calendar.getInstance();

		feHastaParaPresentacion.setTime(dFechaHastaParaPresentacion);

		int iDiaF = feHastaParaPresentacion.get(Calendar.DAY_OF_MONTH);
		int iMesF = feHastaParaPresentacion.get(Calendar.MONTH) + 1;
		int iAnioF = feHastaParaPresentacion.get(Calendar.YEAR);

		LocalDate fechaActual = LocalDate.of(iAnioF, iMesF, iDiaF);
		fechaActual = validarSabadosDomingosFeriados(fechaActual);
		return fechaActual;
	}

	/**
	 * Permite el calculo de la fecha ingresada(dia, mes, año) + cantidad de dias,
	 * si considera sabados, domingos y feriados.
	 * 
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param cantDias
	 * @return
	 * @throws Exception
	 */
	private LocalDate calcularFechasCalendario(int dia, int mes, int anio, int cantDias) throws Exception {
		LocalDate fechaActual = LocalDate.of(anio, mes, dia);
		LocalDate fechaPlusDays = fechaActual.plusDays(cantDias);
		return fechaPlusDays;
	}

	/**
	 * Valida si la fecha ingresada es sabado, domingo o feriado.
	 * 
	 * @param fecha
	 * @return
	 * @throws Exception
	 */
	private LocalDate validarSabadosDomingosFeriados(LocalDate fecha) throws Exception {
		LocalDate fechaModificada = null;
		if (fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			LocalDate fechaPlusDay = fecha.plusDays(1);
			fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
		} else if (fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			LocalDate fechaPlusDay = fecha.plusDays(1);
			fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
		} else {
			String cadenaFecha = completarConCeros(fecha.getDayOfMonth()) + "/"
					+ completarConCeros(fecha.getMonthValue()) + "/" + fecha.getYear();
			// FechaFeriado esFeriado = CommonsUtil.esFeriado(cadenaFecha,
			// propertiesConfig.getUrlSigedRestOld());
			FechaFeriado esFeriado = this.sigedSoapService.esFeriado(cadenaFecha);
			if (esFeriado.getMensajeFeriado().equals("Es Feriado")) {
				LocalDate fechaPlusDay = fecha.plusDays(1);
				fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
			} else {
				fechaModificada = fecha;
			}
		}

		return fechaModificada;
	}

	/**
	 * Permite completar con cero si el numero es de 1 al 9
	 * 
	 * @param diaMes
	 * @return
	 */
	private String completarConCeros(int diaMes) {
		String cadenaDiaMes = "";
		switch (diaMes) {
			case 1:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 2:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 3:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 4:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 5:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 6:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 7:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 8:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 9:
				cadenaDiaMes = "0" + diaMes;
				break;
			default:
				cadenaDiaMes = String.valueOf(diaMes);
				break;
		}

		return cadenaDiaMes;
	}

	/**
	 * Permite obtener la diferencia entre dos fechas, resultado es en dias.
	 * 
	 * @param fechaActual
	 * @param fechaMaxPresentacion
	 * @return
	 */
	private int obtenerCantidadDiasDemora(LocalDate fechaActual, LocalDate fechaMaxPresentacion) {
		int cantidadDiasDemora = 0;
		if (fechaActual.isEqual(fechaMaxPresentacion)) {
			cantidadDiasDemora = 0;
		} else if (fechaActual.isBefore(fechaMaxPresentacion)) {
			cantidadDiasDemora = 0;
		} else if (fechaActual.isAfter(fechaMaxPresentacion)) {
			Long cantDias = ChronoUnit.DAYS.between(fechaMaxPresentacion, fechaActual);
			cantidadDiasDemora = Math.toIntExact(cantDias);
		}

		return cantidadDiasDemora;
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<ResponseDTO> crearJustificacionInforme(PgimDocumentoDTO pgimDocumentoPadreDTO,
			PgimDocumentoDTO pgimDocumentoDTO, PgimInstanciaProcesDTO pgimInstanciaProcesDTO,
			MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO) throws Exception {

		// Obtener correlativo
		Long correlativoArchivo = archivoService.obtenerCorrelativoCodNombre();

		// Obtener el nombre original del archivo
		String noOriginalArchivo = fileDocumento.getOriginalFilename();

		if (noOriginalArchivo.equals("")) {
			noOriginalArchivo = fileDocumento.getName();
		}

		// validar que el archivo tenga la extension permitida.
		String extensionesPermitidas = this.subcategoriaDocRepository
				.obtenerExtensionesPermitidasByIdSubcatDocumento(pgimDocumentoDTO.getIdSubcatDocumento());

		if (!CommonsUtil.esExtensionArchivoValida(noOriginalArchivo, extensionesPermitidas)) {
			String mensaje = "No es posible adjuntar el documento porque el archivo no tiene la extensión permitida.";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("no_success", mensaje, 0));
		}

		// Obtener el nombre codificado del archivo
		String nombreCodificado = this.archivoService.codificarArchivoPgim(noOriginalArchivo,
				pgimDocumentoDTO.getIdSubcatDocumento(), correlativoArchivo);

		// Cambiando nombre al archivo cargado
		MultipartFile multipartFile = new MockMultipartFile(nombreCodificado, fileDocumento.getInputStream());

		// Obtener la istancia del proceso
		List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService.asegurarInstanciasProceso(
				pgimInstanciaProcesDTO.getIdProceso(), pgimInstanciaProcesDTO.getCoTablaInstancia(), auditoriaDTO);

		PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

		String nuExpedienteSiged = null;
		Long idDocumentoSiged = null;
		Long idInstanciaProcesoFinal = pgimInstanciaProcesDTOActual.getIdInstanciaProceso();

		List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
				idInstanciaProcesoFinal, ConstSubCategoriaDocumento.INFORME_DEJUSTIFICACION);

		if (lPgimDocumentoDTO.size() > 0 && pgimDocumentoDTO.getIdSubcatDocumento().equals(ConstSubCategoriaDocumento.INFORME_DEJUSTIFICACION)) {
			String mensaje = "No es posible adjuntar este informe de justificación porque ya existe uno. Puede reemplazar el respectivo archivo desde la respectiva pestaña 'DOCUMENTOS'";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("no_success", mensaje, 0));
		}

		nuExpedienteSiged = pgimInstanciaProcesDTOActual.getNuExpedienteSiged();

		// Ya existe expediente:
		if (idDocumentoSiged == null) {

			// Validación del propietario del expediente
			ExpedienteSiged expedienteSiged = new ExpedienteSiged();
			expedienteSiged.setIdPropietario(Long.parseLong(auditoriaDTO.getCoUsuarioSiged()));
			expedienteSiged.setNumeroExpediente(nuExpedienteSiged);

			ExpedienteDocOutRO objResultado = documentoService.validarUsuarioPropietarioExpedienteSiged(expedienteSiged,
					pgimInstanciaProcesDTOActual.getIdProceso(), "Agregar documento", auditoriaDTO);

			if (!objResultado.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				throw new PgimException(TipoResultado.ERROR, objResultado.getMessage());
			}
						
			DocumentoNuevo docNuevoSiged = new DocumentoNuevo(nuExpedienteSiged, multipartFile.getName(),
					pgimDocumentoDTO.getDeAsuntoDocumento(), String.valueOf(pgimDocumentoDTO.getCoTipoDocumentoSiged()),
					pgimDocumentoDTO.getNumeroDocumento(), pgimDocumentoDTO.getFlNumeradoPorSiged(),
					auditoriaDTO.getCoUsuarioSiged());

			// Registrar documento Siged (WS)
			DocumentoOutRO documentoOutRO = documentoService.agregarDocumentoSiged_old(docNuevoSiged, multipartFile);

			// Si hay un error en el consumo del servicio SIGED, debe enviar el error
			if (!documentoOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				throw new PgimException(TipoResultado.ERROR, "Error al adjuntar / reemplazar el informe. Error con el servicio REST-OLD: "+ documentoOutRO.getMessage());
			}

			idDocumentoSiged = Long.valueOf(documentoOutRO.getCodigoDocumento());

			// Enumerar documento Siged (WS)
			/*
			 * if (pgimDocumentoDTO.getFlNumeradoPorSiged().equals("S")) {
			 * EnumerarDocumentoOutRO enumerarDocumentoOutRO =
			 * documentoService.enumeraDocumentoSiged(
			 * Long.parseLong(documentoOutRO.getCodigoDocumento()), auditoriaDTO);
			 * 
			 * if (!enumerarDocumentoOutRO.getResultCode().equals(ConstantesUtil.
			 * PARAM_RESULTADO_SUCCESS)) {
			 * String mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
			 * "enumerar el documento",
			 * enumerarDocumentoOutRO.getResultCode(), enumerarDocumentoOutRO.getMessage());
			 * log.error(mensajeErrorSiged);
			 * }
			 * }
			 */

		}

		// Registrar documento PGIM
		pgimDocumentoDTO.setCoDocumentoSiged(idDocumentoSiged);
		pgimDocumentoDTO.setSeDocumento(correlativoArchivo);
		pgimDocumentoDTO.setIdInstanciaProceso(idInstanciaProcesoFinal);

		PgimDocumento pgimDocumento = generarPgimDocumento(pgimDocumentoDTO, auditoriaDTO);
		PgimDocumento pgimDocumentoCreado = documentoRepository.save(pgimDocumento);

		Long idDocumento = pgimDocumentoCreado.getIdDocumento();

		pgimDocumentoDTO.setIdDocumento(idDocumento);

		// Registrar documento relación PGIM
		PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO = new PgimDocumentoRelacionDTO();
		pgimDocumentoRelacionDTO.setIdDocumentoPadre(pgimDocumentoPadreDTO.getIdDocumento());
		PgimDocumentoRelacion pgimDocumentoRelacion = generarPgimDocumentoRelacion(pgimDocumentoDTO,
				pgimDocumentoRelacionDTO, auditoriaDTO);
		documentoRelacionRepository.save(pgimDocumentoRelacion);

		// Registrar archivo PGIM (Log)
		PgimArchivoDTO pgimArchivoDTO = new PgimArchivoDTO();
		pgimArchivoDTO.setIdDocumento(idDocumento);
		pgimArchivoDTO.setNoOriginalArchivo(noOriginalArchivo);
		pgimArchivoDTO.setNoNuevoArchivo(nombreCodificado);
		pgimArchivoDTO.setSeArchivo(correlativoArchivo);

		PgimArchivo pgimArchivo = generarPgimArchivo(pgimArchivoDTO, auditoriaDTO);
		archivoRepository.save(pgimArchivo);

		//ResponseDTO respuesta = new ResponseDTO("success", "El documento ha sido registrado", idDocumento);
		//respuesta.setData(pgimDocumentoDTO);
		//return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
		
		ResponseDTO responseDTO = new ResponseDTO(TipoResultado.SUCCESS, pgimDocumentoDTO, "El documento ha sido registrado"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	private PgimDocumento generarPgimDocumento(PgimDocumentoDTO pgimDocumentoDTO, AuditoriaDTO auditoriaDTO) {
		PgimDocumento pgimDocumento = new PgimDocumento();

		if (pgimDocumentoDTO.getIdDocumento() != null) {
			pgimDocumento = documentoRepository.findById(pgimDocumentoDTO.getIdDocumento()).orElse(null);

			pgimDocumento.setFeActualizacion(auditoriaDTO.getFecha());
			pgimDocumento.setUsActualizacion(auditoriaDTO.getUsername());
			pgimDocumento.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {

			pgimDocumento.setPgimInstanciaProces(new PgimInstanciaProces());
			pgimDocumento.getPgimInstanciaProces().setIdInstanciaProceso(pgimDocumentoDTO.getIdInstanciaProceso());

			pgimDocumento.setPgimSubcategoriaDoc(new PgimSubcategoriaDoc());
			pgimDocumento.getPgimSubcategoriaDoc().setIdSubcatDocumento(pgimDocumentoDTO.getIdSubcatDocumento());

			pgimDocumento.setTipoOrigenDocumento(new PgimValorParametro());
			pgimDocumento.getTipoOrigenDocumento().setIdValorParametro(pgimDocumentoDTO.getIdTipoOrigenDocumento());

			pgimDocumento.setPgimFaseProceso(new PgimFaseProceso());
			pgimDocumento.getPgimFaseProceso().setIdFaseProceso(pgimDocumentoDTO.getIdFaseProceso());

			pgimDocumento.setCoDocumentoSiged(pgimDocumentoDTO.getCoDocumentoSiged());
			pgimDocumento.setSeDocumento(pgimDocumentoDTO.getSeDocumento());
			pgimDocumento.setDeAsuntoDocumento(pgimDocumentoDTO.getDeAsuntoDocumento());
			pgimDocumento.setFeOrigenDocumento(pgimDocumentoDTO.getFeOrigenDocumento());
			pgimDocumento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimDocumento.setFeCreacion(auditoriaDTO.getFecha());
			pgimDocumento.setUsCreacion(auditoriaDTO.getUsername());
			pgimDocumento.setIpCreacion(auditoriaDTO.getTerminal());
		}

		return pgimDocumento;
	}

	private PgimArchivo generarPgimArchivo(PgimArchivoDTO pgimArchivoDTO, AuditoriaDTO auditoriaDTO) {
		PgimArchivo pgimArchivo = new PgimArchivo();

		if (pgimArchivoDTO.getIdArchivo() != null) {
			pgimArchivo = archivoRepository.findById(pgimArchivoDTO.getIdArchivo()).orElse(null);

			pgimArchivo.setFeActualizacion(auditoriaDTO.getFecha());
			pgimArchivo.setUsActualizacion(auditoriaDTO.getUsername());
			pgimArchivo.setIpActualizacion(auditoriaDTO.getTerminal());
		} else {

			pgimArchivo.setPgimDocumento(new PgimDocumento());
			pgimArchivo.getPgimDocumento().setIdDocumento(pgimArchivoDTO.getIdDocumento());

			pgimArchivo.setNoOriginalArchivo(pgimArchivoDTO.getNoOriginalArchivo());
			pgimArchivo.setNoNuevoArchivo(pgimArchivoDTO.getNoNuevoArchivo());
			pgimArchivo.setSeArchivo(pgimArchivoDTO.getSeArchivo());
			pgimArchivo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimArchivo.setFeCreacion(auditoriaDTO.getFecha());
			pgimArchivo.setUsCreacion(auditoriaDTO.getUsername());
			pgimArchivo.setIpCreacion(auditoriaDTO.getTerminal());
		}

		return pgimArchivo;
	}

	public String validacionPreviaInformeAprobacion(Long idDocumento, Long coTablaInstancia, Long idProceso,
			Long idFase, Long idSubcatDocumento) {

		String mensaje = "";

		List<PgimDocumentoRelacionDTO> lpgimDocumentoRelacionDTO = this.documentoRelacionRepository
				.obtenerListDocRelacionPorIdDocumentoPadre(idDocumento, ConstantesUtil.PARAM_INFORME_OBSERVADO);

		if (lpgimDocumentoRelacionDTO.size() > 0) {

			mensaje = "OK";

			for (PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO : lpgimDocumentoRelacionDTO) {

				PgimFichaRevisionDTO fichaRevision = this.fichaRevisionRepository
						.obtenerFichaRevisionPorIdDocumento(pgimDocumentoRelacionDTO.getIdDocumento());

				if (fichaRevision != null) {

					List<PgimFichaObservacionDTO> lFichaObservacion = this.fichaObservacionRepository
							.obtenerFichaObservacionPorIdFichaRevision(fichaRevision.getIdFichaRevision());

					for (PgimFichaObservacionDTO fichaObservacion : lFichaObservacion) {

						if (!fichaObservacion.getFlSubsanada().equals("1")) {
							mensaje = "Para generar la ficha de conformidad, <b>todas las observaciones deben encontrarse subsanadas</b> en la ficha de observaciones";
						}

					}

				}
			}

		} else {
			PgimDocumentoDTO penultimoPgimDocumentoDTO = obtenerPenultimoDocumentoDeSubcategoria(coTablaInstancia,
					idProceso,
					idFase, idSubcatDocumento, 0L);

			if (penultimoPgimDocumentoDTO != null) {

				List<PgimDocumentoRelacionDTO> lpenultimoPgimDocumentoRelacionDTO = this.documentoRelacionRepository
						.obtenerListDocRelacionPorIdDocumentoPadre(penultimoPgimDocumentoDTO.getIdDocumento(),
								ConstantesUtil.PARAM_INFORME_OBSERVADO);

				if (lpenultimoPgimDocumentoRelacionDTO.size() > 0) {

					mensaje = "OK";

					for (PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO : lpenultimoPgimDocumentoRelacionDTO) {

						PgimFichaRevisionDTO fichaRevision = this.fichaRevisionRepository
								.obtenerFichaRevisionPorIdDocumento(pgimDocumentoRelacionDTO.getIdDocumento());

						if (fichaRevision != null) {

							List<PgimFichaObservacionDTO> lFichaObservacion = this.fichaObservacionRepository
									.obtenerFichaObservacionPorIdFichaRevision(fichaRevision.getIdFichaRevision());

							for (PgimFichaObservacionDTO fichaObservacion : lFichaObservacion) {

								if (!fichaObservacion.getFlSubsanada().equals("1")) {
									mensaje = "Para generar la ficha de conformidad, <b>todas las observaciones deben encontrarse subsanadas</b> en la ficha de observaciones";
								}

							}

						}
					}

				} else {
					// mensaje="Para generar la ficha de conformidad, primero tiene que <b>registrar
					// la ficha de observaciones</b> al informe de supervisión";
					List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesRepository
							.obtenerInstanciasProceso(idProceso, coTablaInstancia);

					if (lPgimInstanciaProcesDTO.size() == 1) {
						List<PgimFichaRevisionDTO> lPgimFichaRevisionDTO = this.fichaRevisionRepository
								.obtenerFichaRevisionPorInstanciaProcess(
										lPgimInstanciaProcesDTO.get(0).getIdInstanciaProceso());
						if (lPgimFichaRevisionDTO.size() > 0) {
							mensaje = "Ya existe una ficha de conformidad del informe de esta fiscalización, solo puede existir una";

						} else {
							mensaje = "OK";
						}
					} else {
						String msgExcepcion = String.format(
								"Se ha encontrado más de una instancia de proceso para el id de proceso d% y código de instancia d% ",
								idProceso, coTablaInstancia);
						throw new PgimException("error", msgExcepcion);
					}

				}
			} else {
				mensaje = "OK";
			}

		}

		return mensaje;

	}

	@Override
	public PgimDocumentoDTO obtenerDocumentoMasReciente(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento) {

		PgimDocumentoDTO pgimDocumentoDTO = new PgimDocumentoDTO();

		// obtenemos la instancia del proceso
		PgimInstanciaProcesDTO pgimInstanciaProcesDTO = this.instanciaProcesRepository
				.obtenerInstanciaProcesoFase(idProceso, idFase, coTablaInstancia);

		// obtenemos la lista de documentos de tipo informe de supervisión
		List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository
				.obtenerDocumentosDescendentes(pgimInstanciaProcesDTO.getIdInstanciaProceso(), idSubcatDocumento);

		if (lPgimDocumentoDTO.size() > 0) {
			pgimDocumentoDTO = lPgimDocumentoDTO.get(0);
		}

		return pgimDocumentoDTO;
	}

}
