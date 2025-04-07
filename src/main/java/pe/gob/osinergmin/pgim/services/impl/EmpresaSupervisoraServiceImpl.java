package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.repository.EmpresaSupervisoraRepository;
import pe.gob.osinergmin.pgim.services.EmpresaSupervisoraService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Empresa Supervisora
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
@Service
@Transactional(readOnly = true)
public class EmpresaSupervisoraServiceImpl implements EmpresaSupervisoraService {

	@Autowired
	private EmpresaSupervisoraRepository empresaSupervisoraRepository;

	@Override
	public Page<PgimEmpresaSupervisoraDTO> filtrar(PgimEmpresaSupervisoraDTO filtroEmpresaSupervisora,
			Pageable paginador) {
		Page<PgimEmpresaSupervisoraDTO> pPgimEmpresaSupervisoraDTO = this.empresaSupervisoraRepository.listar(
				filtroEmpresaSupervisora.getDescCoDocumentoIdentidad(), filtroEmpresaSupervisora.getDescNoRazonSocial(),
				filtroEmpresaSupervisora.getTextoBusqueda(), paginador);

		return pPgimEmpresaSupervisoraDTO;
	}

	@Override
	public List<PgimEmpresaSupervisoraDTO> listarPorPalabraClave(String palabra) {
		List<PgimEmpresaSupervisoraDTO> lPgimEmpresaSupervisoraDTO = this.empresaSupervisoraRepository
				.listarPorPalabraClave(palabra);

		return lPgimEmpresaSupervisoraDTO;
	}

	@Override
	public PgimEmpresaSupervisoraDTO obtenerPorId(Long idEmpresaSupervisora) {
		PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO = this.empresaSupervisoraRepository
				.obtenerPorId(idEmpresaSupervisora);

		return pgimEmpresaSupervisoraDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimEmpresaSupervisoraDTO crearEmpresaSupervisora(PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO,
			AuditoriaDTO auditoriaDTO) throws Exception {

		PgimEmpresaSupervisora pgimEmpresaSupervisora = new PgimEmpresaSupervisora();

		pgimEmpresaSupervisora.setPgimPersona(new PgimPersona());
		pgimEmpresaSupervisora.getPgimPersona().setIdPersona(pgimEmpresaSupervisoraDTO.getIdPersona());

		pgimEmpresaSupervisora.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimEmpresaSupervisora.setFeCreacion(auditoriaDTO.getFecha());
		pgimEmpresaSupervisora.setUsCreacion(auditoriaDTO.getUsername());
		pgimEmpresaSupervisora.setIpCreacion(auditoriaDTO.getTerminal());

		PgimEmpresaSupervisora pgimEmpresaSupervisoraCreado = empresaSupervisoraRepository.save(pgimEmpresaSupervisora);

		PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTOCreado = this
				.obtenerPorId(pgimEmpresaSupervisoraCreado.getIdEmpresaSupervisora());

		return pgimEmpresaSupervisoraDTOCreado;
	}

	@Override
	public PgimEmpresaSupervisora getByIdEmpresaSupervisora(Long idEmpresaSupervisora) {
		return this.empresaSupervisoraRepository.findById(idEmpresaSupervisora).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimEmpresaSupervisoraDTO modificarEmpresaSupervisora(PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTO,
			PgimEmpresaSupervisora pgimEmpresaSupervisora, AuditoriaDTO auditoriaDTO) {

		pgimEmpresaSupervisora.setPgimPersona(new PgimPersona());
		pgimEmpresaSupervisora.getPgimPersona().setIdPersona(pgimEmpresaSupervisoraDTO.getIdPersona());

		pgimEmpresaSupervisora.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEmpresaSupervisora.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEmpresaSupervisora.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimEmpresaSupervisora pgimEmpresaSupervisoraModificado = empresaSupervisoraRepository
				.save(pgimEmpresaSupervisora);

		PgimEmpresaSupervisoraDTO pgimEmpresaSupervisoraDTOResultado = obtenerPorId(
				pgimEmpresaSupervisoraModificado.getIdEmpresaSupervisora());

		return pgimEmpresaSupervisoraDTOResultado;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarEmpresaSupervisora(PgimEmpresaSupervisora pgimEmpresaSupervisoraActual,
			AuditoriaDTO auditoriaDTO) {
		pgimEmpresaSupervisoraActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimEmpresaSupervisoraActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEmpresaSupervisoraActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEmpresaSupervisoraActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.empresaSupervisoraRepository.save(pgimEmpresaSupervisoraActual);
	}

	@Override
	public List<PgimEmpresaSupervisoraDTO> listarPorPersonaJuridica(String palabraClave) {
		return this.empresaSupervisoraRepository.listarPorPersonaJuridica(palabraClave, EValorParametro.DOIDE_RUC.toString());
	}

}
