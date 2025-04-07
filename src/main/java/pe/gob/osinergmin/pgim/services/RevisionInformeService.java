package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoRelacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaObservacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.dtos.RevisionInforme;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumentoRelacion;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaObservacion;
import pe.gob.osinergmin.pgim.models.entity.PgimFichaRevision;

public interface RevisionInformeService {

	List<PgimDocumentoDTO> verDocumentosRevisionInforme(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento,  AuditoriaDTO auditoriaDTO) throws Exception;

	RevisionInforme crearAprobacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO) throws Exception;

	RevisionInforme crearObservacionInforme(RevisionInforme observacionInforme, AuditoriaDTO auditoriaDTO)
			throws Exception;

	PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorId(Long idDocumentoRelacion);

	PgimFichaRevisionDTO obtenerFichaRevisionPorId(Long idFichaRevision);

	PgimFichaObservacionDTO obtenerFichaObservacionPorId(Long idFichaObservacion);

	PgimDocumento getDocumentoByIdDocumento(Long idDocumento);

	void eliminarFichaObservacion(PgimDocumentoDTO pgimDocumentoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	void eliminarFichaAprobacion(PgimDocumentoDTO pgimDocumentoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

	PgimDocumentoRelacion getDocumentoRelacionByIdDocumentoRelacion(Long idDocumentoRelacion);

	RevisionInforme modificarObservacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO)
			throws Exception;

	RevisionInforme obtenerObservacionInforme(Long idDocumento, Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento, Long idContrato, String descFechaPresentacionActa) throws Exception;

	RevisionInforme obtenerAprobacionInforme(Long idDocumentoConformidad, Long idContrato) throws Exception;

	PgimDocumentoRelacionDTO obtenerDocumentoRelacionPorIdDocumento(Long idDocumento, Long tipoRelacionDocumento);

	PgimFichaRevisionDTO obtenerFichaRevisionPorIdDocumento(Long idDocumento);

	List<PgimFichaObservacionDTO> obtenerFichaObservacionPorIdFichaRevision(Long idFichaRevision);

	PgimDocumentoDTO obtenerDocumentoPorId(Long idDocumento);

	RevisionInforme obtenerObservacionInformeParaCrear(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento, Long idContrato, Long idDocumentoInformeFiscaliza)
			throws Exception;

	/**
	 * Permite obtener la ficha de aprobación para ser creada.
	 * @param idContrato
	 * @param idDocumentoPadre
	 * @return
	 * @throws Exception
	 */
	RevisionInforme obtenerFichaAprobacionParaCrear(Long idContrato, Long idDocumentoPadre) throws Exception;

	/**
	 * Permite realizar la modificación de la aprobación del informe.
	 * @param revisionInforme
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	RevisionInforme modificarAprobacionInforme(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite crear la justificación de un informe de fiscalización.
	 * @param pgimDocumentoPadreDTO
	 * @param pgimDocumentoDTO
	 * @param pgimInstanciaProcesDTO
	 * @param fileDocumento
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<ResponseDTO> crearJustificacionInforme(PgimDocumentoDTO pgimDocumentoPadreDTO,
			PgimDocumentoDTO pgimDocumentoDTO, PgimInstanciaProcesDTO pgimInstanciaProcesDTO,
			MultipartFile fileDocumento, AuditoriaDTO auditoriaDTO) throws Exception;

	String validacionPreviaInformeAprobacion(Long idDocumento, Long coTablaInstancia, Long idProceso, 
			Long idFase, Long idSubcatDocumento);
	
	PgimDocumentoDTO obtenerDocumentoMasReciente(Long coTablaInstancia, Long idProceso, Long idFase,
			Long idSubcatDocumento);

	PgimDocumentoRelacion generarPgimDocumentoRelacion(PgimDocumentoDTO pgimDocumentoDTO, PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO, AuditoriaDTO auditoriaDTO);

	PgimFichaObservacion generarPgimFichaObservacion(PgimFichaRevisionDTO pgimFichaRevisionDTO, PgimFichaObservacionDTO pgimFichaObservacionDTO, AuditoriaDTO auditoriaDTO, boolean eliminar);

	PgimFichaRevision generarPgimFichaRevisionObservacion(PgimDocumentoDTO pgimDocumentoDTO, PgimFichaRevisionDTO pgimFichaRevisionDTO, AuditoriaDTO auditoriaDTO);

	public List<PgimFichaObservacionDTO> subsanarTodasObservaciones(RevisionInforme revisionInforme, AuditoriaDTO auditoriaDTO) throws Exception;
}
