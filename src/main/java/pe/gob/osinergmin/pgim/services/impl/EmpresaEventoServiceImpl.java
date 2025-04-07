package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaEventoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaEvento;
import pe.gob.osinergmin.pgim.models.entity.PgimEvento;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.EmpresaEventoRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.EmpresaEventoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Empresa Evento
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 30/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020 
 */
@Service
@Transactional(readOnly = true)
public class EmpresaEventoServiceImpl implements EmpresaEventoService {

	@Autowired
	private EmpresaEventoRepository empresaEventoRepository;
	
	@Autowired
	private ValorParametroRepository valorParametroRepository;
	
	@Override
	public List<PgimEmpresaEventoDTO> listarEmpresaEvento(final Long idEvento) {

		return this.empresaEventoRepository.listarEmpresaEvento(idEvento);
	}	

	@Override
	public List<PgimEmpresaEventoDTO> listarPorPalabraClave(Long idEvento, String palabra) {

		return this.empresaEventoRepository.listarPorPalabraClave(idEvento, palabra, EValorParametro.DOIDE_RUC.toString());
	}

	@Override
	public PgimEmpresaEventoDTO obtenerEmpresaEvento(Long idEmpresaEvento) {
		return this.empresaEventoRepository.obtenerEmpresaEvento(idEmpresaEvento);
	}

	@Override
	public List<PgimEmpresaEventoDTO> listarEmpresaEventoSelecResponsableInfraccion(final Long idSupervision, 
			final Long idTipoEvento, final Long idInfraccion) {
		
		List<PgimEmpresaEventoDTO> lstPgimEmpresaEventoDTO = this.empresaEventoRepository.listarEmpresaEventoSelecResponsableInfraccion(idSupervision, idTipoEvento, idInfraccion);
		
		// Depuramos empresas repetidas 		
		List<PgimEmpresaEventoDTO> lstEmpresasDepurada = new ArrayList<PgimEmpresaEventoDTO>();

		for (PgimEmpresaEventoDTO pgimEmpresaEventoDTO : lstPgimEmpresaEventoDTO) {
			Boolean encontrado = false;
			for (PgimEmpresaEventoDTO empresaAux : lstEmpresasDepurada) {
				if (empresaAux.getIdPersona().equals(pgimEmpresaEventoDTO.getIdPersona())) {
					encontrado = true;
					break;
				}				
			}
			if(!encontrado) lstEmpresasDepurada.add(pgimEmpresaEventoDTO);
		}
		
		 return lstEmpresasDepurada;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimEmpresaEventoDTO crearEmpresaEvento(@Valid PgimEmpresaEventoDTO pgimEmpresaEventoDTO, AuditoriaDTO auditoriaDTO) {

		PgimEmpresaEvento pgimEmpresaEvento = new PgimEmpresaEvento();

		this.configurarValores(pgimEmpresaEventoDTO, pgimEmpresaEvento);

		pgimEmpresaEvento.setTipoEmpresaInvolucrada(new PgimValorParametro());
		pgimEmpresaEvento.getTipoEmpresaInvolucrada().setIdValorParametro(this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.EMINV_CNTRTSTA.toString()));

		pgimEmpresaEvento.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		/*pgimEmpresaEvento.setFeCreacion(new Date());
		pgimEmpresaEvento.setUsCreacion("admin");
		pgimEmpresaEvento.setIpCreacion("127.0.0.1");*/
		pgimEmpresaEvento.setFeCreacion(auditoriaDTO.getFecha());
		pgimEmpresaEvento.setUsCreacion(auditoriaDTO.getUsername());
		pgimEmpresaEvento.setIpCreacion(auditoriaDTO.getTerminal());


		PgimEmpresaEvento pgimEmpresaEventoCreado = this.empresaEventoRepository.save(pgimEmpresaEvento);

		PgimEmpresaEventoDTO pgimEmpresaEventoDTOCreado = this
				.obtenerEmpresaEvento(pgimEmpresaEventoCreado.getIdEmpresaEvento());

		return pgimEmpresaEventoDTOCreado;
	}

	private void configurarValores(PgimEmpresaEventoDTO pgimEmpresaEventoDTO, PgimEmpresaEvento pgimEmpresaEvento) {
		pgimEmpresaEvento.setPgimPersona(new PgimPersona());
		pgimEmpresaEvento.getPgimPersona().setIdPersona(pgimEmpresaEventoDTO.getIdPersona());

		pgimEmpresaEvento.setPgimEvento(new PgimEvento());
		pgimEmpresaEvento.getPgimEvento().setIdEvento(pgimEmpresaEventoDTO.getIdEvento());

		pgimEmpresaEvento.setNuTrabajadoresM(pgimEmpresaEventoDTO.getNuTrabajadoresM());
		pgimEmpresaEvento.setNuTrabajadoresF(pgimEmpresaEventoDTO.getNuTrabajadoresF());
		pgimEmpresaEvento.setDeActEconomicaPrincipal(pgimEmpresaEventoDTO.getDeActEconomicaPrincipal());

		pgimEmpresaEvento.setTipoActvidadCiiu(new PgimValorParametro());
		pgimEmpresaEvento.getTipoActvidadCiiu().setIdValorParametro(pgimEmpresaEventoDTO.getIdTipoActvidadCiiu());
	}

	@Override
	public PgimEmpresaEvento getByIdEmpresaEvento(Long idEmpresaEvento) {
		return this.empresaEventoRepository.findById(idEmpresaEvento).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimEmpresaEventoDTO modificarEmpresaEvento(@Valid PgimEmpresaEventoDTO pgimEmpresaEventoDTO, AuditoriaDTO auditoriaDTO) {

		PgimEmpresaEvento pgimEmpresaEvento = null;
		Optional<PgimEmpresaEvento> empresaEvento = empresaEventoRepository
				.findById(pgimEmpresaEventoDTO.getIdEmpresaEvento());
		pgimEmpresaEvento = empresaEvento.get();

		this.configurarValores(pgimEmpresaEventoDTO, pgimEmpresaEvento);

		/*pgimEmpresaEvento.setFeActualizacion(new Date());
		pgimEmpresaEvento.setUsActualizacion("admin");
		pgimEmpresaEvento.setIpActualizacion("127.0.0.1");*/
		pgimEmpresaEvento.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEmpresaEvento.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEmpresaEvento.setIpActualizacion(auditoriaDTO.getTerminal());


		this.empresaEventoRepository.save(pgimEmpresaEvento);

		PgimEmpresaEventoDTO pgimEmpresaEventoDTOModificada = obtenerEmpresaEvento(
				pgimEmpresaEvento.getIdEmpresaEvento());

		return pgimEmpresaEventoDTOModificada;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarEmpresaEvento(PgimEmpresaEvento pgimEmpresaEventoActual, AuditoriaDTO auditoriaDTO) {
		pgimEmpresaEventoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		/*pgimEmpresaEventoActual.setFeActualizacion(new Date());
		pgimEmpresaEventoActual.setUsActualizacion("admin");
		pgimEmpresaEventoActual.setIpActualizacion("127.0.0.1");*/
		pgimEmpresaEventoActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimEmpresaEventoActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimEmpresaEventoActual.setIpActualizacion(auditoriaDTO.getTerminal());


		this.empresaEventoRepository.save(pgimEmpresaEventoActual);

	}

}
