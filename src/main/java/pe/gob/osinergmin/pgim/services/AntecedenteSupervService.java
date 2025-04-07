package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAntecedenteSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAddAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSelectAntecedenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAntecedenteSuperv;
public interface AntecedenteSupervService {
	
	/**
	 * Permite obtener el listado de documentos que serán usados como antecedentes, las cuales ya se encuentran registradas en sus respectivas tablas maestras
	 * que serán utilizados en el frontend en la  selección de antecedentes
	 * @param pgimSupervisionDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimSelectAntecedenteAuxDTO> listarSelecccionAntecedente(PgimSupervisionDTO pgimSupervisionDTO,  Pageable paginador);

	/**
	 * Permite crear un antecedente desde adjuntar un documento de categoria antecedente
	 * @param pgimAntecedenteSupervDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimAntecedenteSupervDTO crearAntecedente(PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite crear un antecedente a partir de la selección de antecedentes
	 * @param pgimSelectAntecedenteAddAuxDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimSelectAntecedenteAuxDTO crearAntecedenteDesdeSelectAntecedente(PgimSelectAntecedenteAddAuxDTO pgimSelectAntecedenteAddAuxDTO, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite obtener un AntecedenteSupervDTO por el identificador del AntecedenteSupervDTO
	 * @param idAntecedenteSuperv
	 * @return
	 */
	PgimAntecedenteSupervDTO obtenerAntecedenteSupervDTOPorId(Long idAntecedenteSuperv);
	
	/**
	 * Permite obtener un AntecedenteSuperv por el identificador del AntecedenteSuperv
	 * @param idAntecedenteSuperv
	 * @return
	 */
	PgimAntecedenteSuperv obtenerAntecedenteSupervPorId(Long idAntecedenteSuperv);	
	
	/**
	 * Permite el listado de los antecedentes ya registrados
	 * @param idSupervisión
	 * @param paginador
	 * @return
	 */
	Page<PgimAntecedenteAuxDTO> listarAntecedentes(Long idSupervisión,  Pageable paginador, AuditoriaDTO auditoriaDTO) throws Exception ;
	
	/**
	 * Permite modificar un antecedente
	 * @param pgimAntecedenteSupervDTO
	 * @param pgimAntecedenteSuperv
	 * @param auditoriaDTO
	 * @return
	 */
	PgimAntecedenteSupervDTO modificarAntecedente(PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO, PgimAntecedenteSuperv pgimAntecedenteSuperv, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite eliminar el antecedente
	 * @param idAntecedenteSuperv
	 * @param obtenerAuditoria
	 * @return
	 */
	Long eliminarAntecedente(Long idAntecedenteSuperv, AuditoriaDTO obtenerAuditoria);
	
	/**
	 * validar que todos los antecedentes han sido revisados
	 * @param idSupervisión
	 * @return
	 */
	Integer validarRevisionAntecedente(Long idSupervisión);
	
	/**
	 * permite obtener la cantidad de antecedentes que tiene la supervision
	 * @param idSupervisión
	 * @return
	 */
	Integer cantidadAntecedentes(Long idSupervisión);

	PgimAntecedenteSupervDTO modificarAntecedenteAdjuntado(PgimAntecedenteSupervDTO pgimAntecedenteSupervDTO,
			PgimAntecedenteSuperv pgimAntecedenteSuperv, AuditoriaDTO auditoriaDTO);

	
}
