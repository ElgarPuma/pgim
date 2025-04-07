package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimReglaBase;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ConfiguracionBaseRepository;
import pe.gob.osinergmin.pgim.models.repository.ReglaBaseRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ConfiguracionBaseService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de las entidad de configuración base
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 04/04/2022
 */

@Service
@Transactional(readOnly = true)
public class ConfiguracionBaseServiceImpl implements ConfiguracionBaseService {

	@Autowired
	ConfiguracionBaseRepository configuracionBaseRepository;

	@Autowired
	ReglaBaseRepository reglaBaseRepository;

	@Autowired
	ValorParametroRepository valorParametroRepository;

	@Override
	public Page<PgimConfiguracionBaseDTO> listar(PgimConfiguracionBaseDTO filtro, Pageable paginador) {
		Page<PgimConfiguracionBaseDTO> pPgimConfiguracionBaseDTO = this.configuracionBaseRepository.listar(
				filtro.getIdTipoConfiguracionBase(), filtro.getIdEspecialidad(), filtro.getNoCofiguracionBase(),
				filtro.getTextoBusqueda(), paginador);
		return pPgimConfiguracionBaseDTO;
	}

	@Override
	public PgimConfiguracionBaseDTO obtenerCfgBasePorId(Long idConfiguracionBase) {
		PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.obtenerCfgBasePorId(idConfiguracionBase);
		return pgimConfiguracionBaseDTO;
	}

	@Override
	public PgimConfiguracionBase obtenerCfgBaseById(Long idConfiguracionBase) {
		return this.configuracionBaseRepository.findById(idConfiguracionBase).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimConfiguracionBaseDTO crearCfgBase(PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimConfiguracionBase pgimConfiguracionBase = new PgimConfiguracionBase();

		pgimConfiguracionBase.setTipoConfiguracionBase(new PgimValorParametro());
		pgimConfiguracionBase.getTipoConfiguracionBase()
				.setIdValorParametro(pgimConfiguracionBaseDTO.getIdTipoConfiguracionBase());

		pgimConfiguracionBase.setPgimEspecialidad(new PgimEspecialidad());
		pgimConfiguracionBase.getPgimEspecialidad().setIdEspecialidad(pgimConfiguracionBaseDTO.getIdEspecialidad());

		pgimConfiguracionBase.setNoCofiguracionBase(pgimConfiguracionBaseDTO.getNoCofiguracionBase());
		pgimConfiguracionBase.setDeCofiguracionBase(pgimConfiguracionBaseDTO.getDeCofiguracionBase());

		// registramos el id de la configuración base origen
		if(pgimConfiguracionBaseDTO.getIdConfiguracionBase() != null){
			pgimConfiguracionBase.setPgimConfiguracionBaseOrigen(new PgimConfiguracionBase());
			pgimConfiguracionBase.getPgimConfiguracionBaseOrigen().setIdConfiguracionBase(pgimConfiguracionBaseDTO.getIdConfiguracionBase());
		}

		pgimConfiguracionBase.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimConfiguracionBase.setFeCreacion(auditoriaDTO.getFecha());
		pgimConfiguracionBase.setUsCreacion(auditoriaDTO.getUsername());
		pgimConfiguracionBase.setIpCreacion(auditoriaDTO.getTerminal());

		PgimConfiguracionBase pgimConfiguracionBaseCreado = this.configuracionBaseRepository
				.save(pgimConfiguracionBase);

		PgimConfiguracionBaseDTO pgimConfiguracionBaseDTOResultado = this
				.obtenerCfgBasePorId(pgimConfiguracionBaseCreado.getIdConfiguracionBase());

		return pgimConfiguracionBaseDTOResultado;

	}

	@Transactional(readOnly = false)
	@Override
	public PgimConfiguracionBaseDTO modificarCfgBase(PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO,
			PgimConfiguracionBase pgimConfiguracionBase, AuditoriaDTO auditoriaDTO) {

		pgimConfiguracionBase.setTipoConfiguracionBase(new PgimValorParametro());
		pgimConfiguracionBase.getTipoConfiguracionBase()
				.setIdValorParametro(pgimConfiguracionBaseDTO.getIdTipoConfiguracionBase());

		pgimConfiguracionBase.setPgimEspecialidad(new PgimEspecialidad());
		pgimConfiguracionBase.getPgimEspecialidad().setIdEspecialidad(pgimConfiguracionBaseDTO.getIdEspecialidad());

		pgimConfiguracionBase.setNoCofiguracionBase(pgimConfiguracionBaseDTO.getNoCofiguracionBase());
		pgimConfiguracionBase.setDeCofiguracionBase(pgimConfiguracionBaseDTO.getDeCofiguracionBase());

		pgimConfiguracionBase.setFeActualizacion(auditoriaDTO.getFecha());
		pgimConfiguracionBase.setUsActualizacion(auditoriaDTO.getUsername());
		pgimConfiguracionBase.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimConfiguracionBase pgimConfiguracionBaseModificado = this.configuracionBaseRepository
				.save(pgimConfiguracionBase);

		PgimConfiguracionBaseDTO pgimConfiguracionBaseDTOResultado = this
				.obtenerCfgBasePorId(pgimConfiguracionBaseModificado.getIdConfiguracionBase());

		return pgimConfiguracionBaseDTOResultado;

	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarCfgBase(PgimConfiguracionBase pgimConfiguracionBaseActual, AuditoriaDTO auditoriaDTO) {

		List<PgimReglaBaseDTO> lPgimReglaBaseDTO = this.listarReglasPorCfgBase(pgimConfiguracionBaseActual.getIdConfiguracionBase());

		if(lPgimReglaBaseDTO != null ){
			for (PgimReglaBaseDTO pgimReglaBaseDTO : lPgimReglaBaseDTO) {
				PgimReglaBase pgimReglaBaseActual = this.obtenerReglaCfgBaseById(pgimReglaBaseDTO.getIdReglaBase());
				pgimReglaBaseActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				pgimReglaBaseActual.setFeActualizacion(auditoriaDTO.getFecha());
				pgimReglaBaseActual.setUsActualizacion(auditoriaDTO.getUsername());
				pgimReglaBaseActual.setIpActualizacion(auditoriaDTO.getTerminal());
			}
		}
		pgimConfiguracionBaseActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimConfiguracionBaseActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimConfiguracionBaseActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimConfiguracionBaseActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.configuracionBaseRepository.save(pgimConfiguracionBaseActual);

	}

	@Override
	public List<PgimReglaBaseDTO> listarReglasPorCfgBase(Long idConfiguracionBase) {
		List<PgimReglaBaseDTO> lPgimReglaBaseDTO = this.reglaBaseRepository.listarReglasPorCfgBase(idConfiguracionBase);
		return lPgimReglaBaseDTO;
	}

	@Override
	public PgimReglaBaseDTO obtenerReglaCfgBasePorId(Long idReglaBase) {
		PgimReglaBaseDTO pgimReglaBaseDTO = this.reglaBaseRepository.obtenerReglaBasePorId(idReglaBase);
		return pgimReglaBaseDTO;
	}

	@Override
	public PgimReglaBase obtenerReglaCfgBaseById(Long idConfiguracionBase) {
		return this.reglaBaseRepository.findById(idConfiguracionBase).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimReglaBaseDTO crearReglaCfgBase(PgimReglaBaseDTO pgimReglaBaseDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimReglaBase pgimReglaBase = new PgimReglaBase();

		pgimReglaBase.setPgimConfiguracionBase(new PgimConfiguracionBase());
		pgimReglaBase.getPgimConfiguracionBase().setIdConfiguracionBase(pgimReglaBaseDTO.getIdConfiguracionBase());

		pgimReglaBase.setDivisionSupervisora(new PgimValorParametro());
		pgimReglaBase.getDivisionSupervisora().setIdValorParametro(pgimReglaBaseDTO.getIdDivisionSupervisora());

		pgimReglaBase.setPgimMotivoSupervision(new PgimMotivoSupervision());
		pgimReglaBase.getPgimMotivoSupervision().setIdMotivoSupervision(pgimReglaBaseDTO.getIdMotivoSupervision());

		pgimReglaBase.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
		pgimReglaBase.getPgimSubtipoSupervision().setIdSubtipoSupervision(pgimReglaBaseDTO.getIdSubtipoSupervision());

		pgimReglaBase.setMetodoMinado(new PgimValorParametro());
		pgimReglaBase.getMetodoMinado().setIdValorParametro(pgimReglaBaseDTO.getIdMetodoMinado());

		pgimReglaBase.setFlPropia(pgimReglaBaseDTO.getFlPropia());
		pgimReglaBase.setFlConPiques(pgimReglaBaseDTO.getFlConPiques());

		pgimReglaBase.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimReglaBase.setFeCreacion(auditoriaDTO.getFecha());
		pgimReglaBase.setUsCreacion(auditoriaDTO.getUsername());
		pgimReglaBase.setIpCreacion(auditoriaDTO.getTerminal());

		PgimReglaBase pgimReglaBaseCreado = this.reglaBaseRepository.save(pgimReglaBase);

		PgimReglaBaseDTO pgimReglaBaseDTOResultado = this
				.obtenerReglaCfgBasePorId(pgimReglaBaseCreado.getIdReglaBase());

		return pgimReglaBaseDTOResultado;

	}

	@Transactional(readOnly = false)
	@Override
	public PgimReglaBaseDTO modificarReglaCfgBase(PgimReglaBaseDTO pgimReglaBaseDTO,
			PgimReglaBase pgimReglaBase, AuditoriaDTO auditoriaDTO) {

		pgimReglaBase.setDivisionSupervisora(new PgimValorParametro());
		pgimReglaBase.getDivisionSupervisora().setIdValorParametro(pgimReglaBaseDTO.getIdDivisionSupervisora());

		pgimReglaBase.setPgimMotivoSupervision(new PgimMotivoSupervision());
		pgimReglaBase.getPgimMotivoSupervision().setIdMotivoSupervision(pgimReglaBaseDTO.getIdMotivoSupervision());

		pgimReglaBase.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
		pgimReglaBase.getPgimSubtipoSupervision().setIdSubtipoSupervision(pgimReglaBaseDTO.getIdSubtipoSupervision());

		pgimReglaBase.setMetodoMinado(new PgimValorParametro());
		pgimReglaBase.getMetodoMinado().setIdValorParametro(pgimReglaBaseDTO.getIdMetodoMinado());

		pgimReglaBase.setFlPropia(pgimReglaBaseDTO.getFlPropia());
		pgimReglaBase.setFlConPiques(pgimReglaBaseDTO.getFlConPiques());

		pgimReglaBase.setFeActualizacion(auditoriaDTO.getFecha());
		pgimReglaBase.setUsActualizacion(auditoriaDTO.getUsername());
		pgimReglaBase.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimReglaBase pgimReglaBaseModificado = this.reglaBaseRepository.save(pgimReglaBase);

		PgimReglaBaseDTO pgimReglaBaseDTOResultado = this
				.obtenerReglaCfgBasePorId(pgimReglaBaseModificado.getIdReglaBase());

		return pgimReglaBaseDTOResultado;

	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarReglaCfgBase(PgimReglaBase pgimReglaBaseActual, AuditoriaDTO auditoriaDTO) {

		pgimReglaBaseActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimReglaBaseActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimReglaBaseActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimReglaBaseActual.setIpActualizacion(auditoriaDTO.getTerminal());

		this.reglaBaseRepository.save(pgimReglaBaseActual);

	}

	@Override
	public List<PgimReglaBaseDTO> listarReglasTraslapadas(PgimReglaBaseDTO pgimReglaBaseDTO) {
		List<PgimReglaBaseDTO> listarReglasTraslapadas = null;

		Long idTipoCfgBaseCuadroVerificacion = this.valorParametroRepository
				.obtenerIdValorParametro(EValorParametro.CUADROS_BASE_VERIFICACION.toString());

		if(pgimReglaBaseDTO.getDescIdTipoConfiguracionBase().equals(idTipoCfgBaseCuadroVerificacion)){
			listarReglasTraslapadas = this.reglaBaseRepository.listarReglasTraslapadasCuadroVerificacion(
					pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
					pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
					pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(), pgimReglaBaseDTO.getIdReglaBase(),
					pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());

			/**
			 * Se muestra la cantidad de traslapes de reglas de la configuración base para "Cuadro de verificación" en la que se encuentra
			 */
			int cantidadReglasTraslapadas = listarReglasTraslapadas.size();

			if (cantidadReglasTraslapadas == 0) {
				listarReglasTraslapadas = this.reglaBaseRepository.listarReglasTraslapadas(
						pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
						pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
						pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(),
						pgimReglaBaseDTO.getIdReglaBase(),
						pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());
			} 

		}else{

			listarReglasTraslapadas = this.reglaBaseRepository.listarReglasTraslapadas(
					pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
					pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
					pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(), pgimReglaBaseDTO.getIdReglaBase(),
					pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());
		}

		return listarReglasTraslapadas;
	}

	@Override
	public List<PgimConfiguracionBaseDTO> existeCfg(String noConfiguracionBase, Long idConfiguracionBase) {
		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.existeCfg(idConfiguracionBase, noConfiguracionBase);

		return lPgimConfiguracionBaseDTO;
	}

	@Override
	public PgimReglaBaseDTO obtenerReglaPorSupervision(PgimReglaBaseDTO pgimReglaBaseDTO) {

		PgimReglaBaseDTO pgimReglaBaseDTOResultado = new PgimReglaBaseDTO();
		Long idTipoCfgBaseCuadroVerificacion = this.valorParametroRepository
				.obtenerIdValorParametro(EValorParametro.CUADROS_BASE_VERIFICACION.toString());

		if(pgimReglaBaseDTO.getDescIdTipoConfiguracionBase().equals(idTipoCfgBaseCuadroVerificacion)){
				pgimReglaBaseDTOResultado = this.reglaBaseRepository.obtenerReglaPorSupervisionCuadroVerificacion(
				pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
				pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
				pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(),
				pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());
		}else{
				pgimReglaBaseDTOResultado = this.reglaBaseRepository.obtenerReglaPorSupervision(
				pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
				pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
				pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(),
				pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());
		}

		return pgimReglaBaseDTOResultado;

	}

	@Override
	public List<PgimReglaBaseDTO> obtenerReglaPorSupervisionCompatible(PgimReglaBaseDTO pgimReglaBaseDTO) {

		List<PgimReglaBaseDTO> pgimReglaBaseDTOResultado = null;

		pgimReglaBaseDTOResultado = this.reglaBaseRepository.obtenerListaReglaPorSupervision(
		pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
		pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
		pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(),
		pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());

		return pgimReglaBaseDTOResultado;

	}

	@Override
	public List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBase(Long idTipoConfiguracionBase) {
		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.listaCfgBasePorIdTipoCfgBase(idTipoConfiguracionBase);

		return lPgimConfiguracionBaseDTO;

	}

	@Override
	public List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseTDR(Long idTipoConfiguracionBase) {
		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.listaCfgBasePorIdTipoCfgBaseTDR(idTipoConfiguracionBase);

		return lPgimConfiguracionBaseDTO;

	}

	@Override
	public List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseCVERIF(Long idTipoConfiguracionBase) {
		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.listaCfgBasePorIdTipoCfgBaseCVERIF(idTipoConfiguracionBase);

		return lPgimConfiguracionBaseDTO;
	}

	@Override
	public List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseSOLBD(Long idTipoConfiguracionBase) {
		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.listaCfgBasePorIdTipoCfgBaseSOLBD(idTipoConfiguracionBase);

		return lPgimConfiguracionBaseDTO;

	}

	@Override
	public List<PgimConfiguracionBaseDTO> listaCfgBasePorTipoCfgBaseYEspecialidad(Long idTipoConfiguracionBase,
			Long idEspecialidad) {
		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
				.listaCfgBasePorTipoCfgBaseYEspecialidad(idTipoConfiguracionBase, idEspecialidad);

		return lPgimConfiguracionBaseDTO;
	}

	@Override
	public List<PgimReglaBaseDTO> validarTraslapesReglasMatrizSupervision(PgimMatrizSupervisionDTO pgimMatrizSupervisionDTO) {
		List<PgimReglaBaseDTO> listarReglasTraslapadas = new ArrayList<PgimReglaBaseDTO>();		
		List<PgimReglaBaseDTO> lPgimReglaBaseDTO = this.reglaBaseRepository.listarReglasPorCfgBase(pgimMatrizSupervisionDTO.getIdConfiguracionBase());

		for (PgimReglaBaseDTO pgimReglaBaseDTO : lPgimReglaBaseDTO) {
				List<PgimReglaBaseDTO> listarReglasTraslapadasAux = this.reglaBaseRepository.listarReglasTraslapadasCuadroVerificacion(
				pgimReglaBaseDTO.getIdDivisionSupervisora(), pgimReglaBaseDTO.getIdMotivoSupervision(),
				pgimReglaBaseDTO.getIdSubtipoSupervision(), pgimReglaBaseDTO.getIdMetodoMinado(),
				pgimReglaBaseDTO.getFlPropia(), pgimReglaBaseDTO.getFlConPiques(), pgimReglaBaseDTO.getIdReglaBase(),
				pgimReglaBaseDTO.getDescIdTipoConfiguracionBase());

			if(listarReglasTraslapadasAux.size() > 0){								
				listarReglasTraslapadas.addAll(listarReglasTraslapadasAux);
			}			
		}

		return listarReglasTraslapadas;
	}

	@Override
	public List<PgimConfiguracionBaseDTO> validarEliminacionCfgBase(PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO) {

		List<PgimConfiguracionBaseDTO> lPgimConfiguracionBaseDTO = new ArrayList<PgimConfiguracionBaseDTO>();
		if(pgimConfiguracionBaseDTO.getDescCoClaveTextoTipoConfiguracionBase().equals(EValorParametro.TDR_BASE.toString())){
				lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
					.existeCfgBEnTDR(pgimConfiguracionBaseDTO.getIdConfiguracionBase());
		}if(pgimConfiguracionBaseDTO.getDescCoClaveTextoTipoConfiguracionBase().equals(EValorParametro.CUADROS_BASE_VERIFICACION.toString())){
				lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
					.existeCfgBEnCVERIF(pgimConfiguracionBaseDTO.getIdConfiguracionBase());
		}if(pgimConfiguracionBaseDTO.getDescCoClaveTextoTipoConfiguracionBase().equals(EValorParametro.DOCUMENTOS_BASE_SOLICITUD.toString())){
				lPgimConfiguracionBaseDTO = this.configuracionBaseRepository
					.existeCfgBEnSOLBD(pgimConfiguracionBaseDTO.getIdConfiguracionBase());
		}

		return lPgimConfiguracionBaseDTO;
	}


}
