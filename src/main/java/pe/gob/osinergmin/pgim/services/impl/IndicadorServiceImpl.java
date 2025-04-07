package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimKpiGeneralDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedicionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicador;
import pe.gob.osinergmin.pgim.models.entity.PgimMedicion;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository;
import pe.gob.osinergmin.pgim.models.repository.IndPasArchivadoRepository;
import pe.gob.osinergmin.pgim.models.repository.IndicadorRepository;
import pe.gob.osinergmin.pgim.services.IndicadorService;
import pe.gob.osinergmin.pgim.services.MedicionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Indicador
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 28/02/2023
 * @fecha_de_ultima_actualización: 
 */
@Service
@Transactional(readOnly = true)
public class IndicadorServiceImpl implements IndicadorService {
	
	@Autowired
    private IndicadorRepository indicadorRepository;
	
	@Autowired
    private IndAprobacionInformeRepository indAprobacionInformeRepository;
	
	@Autowired
    private IndPasArchivadoRepository indPasArchivadoRepository;

	@Autowired
    private MedicionService medicionService;
	
	@Override
    public List<PgimIndicadorDTO> listarIndicadorBycoClavetexto(String parametro) {
        List<PgimIndicadorDTO> lista = this.indicadorRepository.listarIndicadorBycoClavetexto(parametro);
        return lista;
    }
	
	@Override
	public PgimIndicadorDTO obtenerIndicadorById(Long idIndicador) {
		PgimIndicadorDTO indicadorDTO = this.indicadorRepository.obtenerIndicadorById(idIndicador);		
		return indicadorDTO;
	}
	
	/***
	 * Aprobación de Informe de Fiscalización - Inicio
	 */
		
	
	@Override
	public PgimKpiGeneralDTO obtenerKpiAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
		
		PgimKpiGeneralDTO objKPI = this.indAprobacionInformeRepository.obtenerKpiAprobacionInformeFiscalizacion(
				filtroIndicadorDTO.getFeDesde(), 
				filtroIndicadorDTO.getFeHasta(), 
				filtroIndicadorDTO.getIdEspecialidad(), 
				filtroIndicadorDTO.getIdDivisionSupervisora(), 
				filtroIndicadorDTO.getIdTipoSupervision(), 
				filtroIndicadorDTO.getIdSubTipoSupervision(), 
				filtroIndicadorDTO.getIdMotivoSupervision(), 
				filtroIndicadorDTO.getNoAgenteSupervisado(), 
				filtroIndicadorDTO.getNoUnidadMinera(), 
				filtroIndicadorDTO.getNuContrato(), 
				filtroIndicadorDTO.getNoEmpresaSupervisora()
				);
		return objKPI;
	}
	
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorEspecialidadAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
		
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorEspecialidadAprobacionInformeFiscalizacion(
				filtroIndicadorDTO.getFeDesde(), 
				filtroIndicadorDTO.getFeHasta(), 
				filtroIndicadorDTO.getIdEspecialidad(), 
				filtroIndicadorDTO.getIdDivisionSupervisora(), 
				filtroIndicadorDTO.getIdTipoSupervision(), 
				filtroIndicadorDTO.getIdSubTipoSupervision(), 
				filtroIndicadorDTO.getIdMotivoSupervision(), 
				filtroIndicadorDTO.getNoAgenteSupervisado(), 
				filtroIndicadorDTO.getNoUnidadMinera(), 
				filtroIndicadorDTO.getNuContrato(), 
				filtroIndicadorDTO.getNoEmpresaSupervisora()
				);
		return data;
	}
	
	
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorTipoFiscalizacionAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
		
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorTipoFiscalizacionAprobacionInformeFiscalizacion(
				filtroIndicadorDTO.getFeDesde(), 
				filtroIndicadorDTO.getFeHasta(), 
				filtroIndicadorDTO.getIdEspecialidad(), 
				filtroIndicadorDTO.getIdDivisionSupervisora(), 
				filtroIndicadorDTO.getIdTipoSupervision(), 
				filtroIndicadorDTO.getIdSubTipoSupervision(), 
				filtroIndicadorDTO.getIdMotivoSupervision(), 
				filtroIndicadorDTO.getNoAgenteSupervisado(), 
				filtroIndicadorDTO.getNoUnidadMinera(), 
				filtroIndicadorDTO.getNuContrato(), 
				filtroIndicadorDTO.getNoEmpresaSupervisora()
				);
		return data;
	}
	
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorContratoAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
			
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorContratoAprobacionInformeFiscalizacion(
					filtroIndicadorDTO.getFeDesde(), 
					filtroIndicadorDTO.getFeHasta(), 
					filtroIndicadorDTO.getIdEspecialidad(), 
					filtroIndicadorDTO.getIdDivisionSupervisora(), 
					filtroIndicadorDTO.getIdTipoSupervision(), 
					filtroIndicadorDTO.getIdSubTipoSupervision(), 
					filtroIndicadorDTO.getIdMotivoSupervision(), 
					filtroIndicadorDTO.getNoAgenteSupervisado(), 
					filtroIndicadorDTO.getNoUnidadMinera(), 
					filtroIndicadorDTO.getNuContrato(), 
					filtroIndicadorDTO.getNoEmpresaSupervisora()
					);
		return data;
	}
		
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorAgenteFiscalizadoAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorAgenteFiscalizadoAprobacionInformeFiscalizacion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorDivisionSupervisoraAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorDivisionSupervisoraAprobacionInformeFiscalizacion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
	
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorMotivoFiscalizacionAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorMotivoFiscalizacionAprobacionInformeFiscalizacion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
				
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorUnidadFiscalizableAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorUnidadFiscalizableAprobacionInformeFiscalizacion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
		return data;
	}
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorSubtipoFiscalizacionAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorSubtipoFiscalizacionAprobacionInformeFiscalizacion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
				
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorEmpresaSupervisoraAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indAprobacionInformeRepository.obtenerDataPorEmpresaSupervisoraAprobacionInformeFiscalizacion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
		return data;
	}
	
	
	/***
	 * Aprobación de Informe de Fiscalización - Fin
	 */
	
	
	
	
	
	/***
	 * Emisión de Informe de Instrucción - Inicio
	 */
	
	@Override
	public PgimKpiGeneralDTO obtenerKpiEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
		
		PgimKpiGeneralDTO objKPI = this.indPasArchivadoRepository.obtenerKpiEmisionInformeInstruccion(
				filtroIndicadorDTO.getFeDesde(), 
				filtroIndicadorDTO.getFeHasta(), 
				filtroIndicadorDTO.getIdEspecialidad(), 
				filtroIndicadorDTO.getIdDivisionSupervisora(), 
				filtroIndicadorDTO.getIdTipoSupervision(), 
				filtroIndicadorDTO.getIdSubTipoSupervision(), 
				filtroIndicadorDTO.getIdMotivoSupervision(), 
				filtroIndicadorDTO.getNoAgenteSupervisado(), 
				filtroIndicadorDTO.getNoUnidadMinera(), 
				filtroIndicadorDTO.getNuContrato(), 
				filtroIndicadorDTO.getNoEmpresaSupervisora()
				);
		return objKPI;
	}
	
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorEspecialidadEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
		
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorEspecialidadEmisionInformeInstruccion(
				filtroIndicadorDTO.getFeDesde(), 
				filtroIndicadorDTO.getFeHasta(), 
				filtroIndicadorDTO.getIdEspecialidad(), 
				filtroIndicadorDTO.getIdDivisionSupervisora(), 
				filtroIndicadorDTO.getIdTipoSupervision(), 
				filtroIndicadorDTO.getIdSubTipoSupervision(), 
				filtroIndicadorDTO.getIdMotivoSupervision(), 
				filtroIndicadorDTO.getNoAgenteSupervisado(), 
				filtroIndicadorDTO.getNoUnidadMinera(), 
				filtroIndicadorDTO.getNuContrato(), 
				filtroIndicadorDTO.getNoEmpresaSupervisora()
				);
		return data;
	}
	
	
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorTipoFiscalizacionEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
		
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorTipoFiscalizacionEmisionInformeInstruccion(
				filtroIndicadorDTO.getFeDesde(), 
				filtroIndicadorDTO.getFeHasta(), 
				filtroIndicadorDTO.getIdEspecialidad(), 
				filtroIndicadorDTO.getIdDivisionSupervisora(), 
				filtroIndicadorDTO.getIdTipoSupervision(), 
				filtroIndicadorDTO.getIdSubTipoSupervision(), 
				filtroIndicadorDTO.getIdMotivoSupervision(), 
				filtroIndicadorDTO.getNoAgenteSupervisado(), 
				filtroIndicadorDTO.getNoUnidadMinera(), 
				filtroIndicadorDTO.getNuContrato(), 
				filtroIndicadorDTO.getNoEmpresaSupervisora()
				);
		return data;
	}
	
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorContratoEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
			
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorContratoEmisionInformeInstruccion(
					filtroIndicadorDTO.getFeDesde(), 
					filtroIndicadorDTO.getFeHasta(), 
					filtroIndicadorDTO.getIdEspecialidad(), 
					filtroIndicadorDTO.getIdDivisionSupervisora(), 
					filtroIndicadorDTO.getIdTipoSupervision(), 
					filtroIndicadorDTO.getIdSubTipoSupervision(), 
					filtroIndicadorDTO.getIdMotivoSupervision(), 
					filtroIndicadorDTO.getNoAgenteSupervisado(), 
					filtroIndicadorDTO.getNoUnidadMinera(), 
					filtroIndicadorDTO.getNuContrato(), 
					filtroIndicadorDTO.getNoEmpresaSupervisora()
					);
		return data;
	}
		
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorAgenteFiscalizadoEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorAgenteFiscalizadoEmisionInformeInstruccion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorDivisionSupervisoraEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorDivisionSupervisoraEmisionInformeInstruccion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
	
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorMotivoFiscalizacionEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorMotivoFiscalizacionEmisionInformeInstruccion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
				
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorUnidadFiscalizableEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorUnidadFiscalizableEmisionInformeInstruccion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
		return data;
	}
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorSubtipoFiscalizacionEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorSubtipoFiscalizacionEmisionInformeInstruccion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
					return data;
	}
				
				
	@Override
	public List<PgimGraficaResultadoDTO> obtenerDataPorEmpresaSupervisoraEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO) {
					
		List<PgimGraficaResultadoDTO> data = this.indPasArchivadoRepository.obtenerDataPorEmpresaSupervisoraEmisionInformeInstruccion(
							filtroIndicadorDTO.getFeDesde(), 
							filtroIndicadorDTO.getFeHasta(), 
							filtroIndicadorDTO.getIdEspecialidad(), 
							filtroIndicadorDTO.getIdDivisionSupervisora(), 
							filtroIndicadorDTO.getIdTipoSupervision(), 
							filtroIndicadorDTO.getIdSubTipoSupervision(), 
							filtroIndicadorDTO.getIdMotivoSupervision(), 
							filtroIndicadorDTO.getNoAgenteSupervisado(), 
							filtroIndicadorDTO.getNoUnidadMinera(), 
							filtroIndicadorDTO.getNuContrato(), 
							filtroIndicadorDTO.getNoEmpresaSupervisora()
							);
		return data;
	}
	
	/***
	 * Emisión de Informe de Instrucción - Fin
	 */
	
	
	/*---------------------------------------------------------
    	metodos para indicadores de flujos de trabajo
	--------------------------------------------------------- */

	@Override
	public Page<PgimIndicadorDTO> listarIndicadoresFt(PgimIndicadorDTO pgimIndicadorDTOFiltro, Pageable paginador){
		
		return this.indicadorRepository.listarIndicadoresFt(EValorParametro.TIND_TIEMPOS_PROCESAMIENTO.toString(), pgimIndicadorDTOFiltro.getTextoBusqueda(), paginador);
	}
    
	@Override
	public List<PgimIndicadorDTO> validarCoClaveUnicaIndicadorFt(PgimIndicadorDTO pgimIndicadorDTO) {
		
		List<PgimIndicadorDTO> data = this.indicadorRepository.validarCoClaveUnicaIndicadorFt(pgimIndicadorDTO.getCoClaveIndicador().trim(), pgimIndicadorDTO.getIdIndicador());
		return data;
	}
	
	@Transactional(readOnly = false)
	@Override
	public PgimIndicadorDTO crearIndicadoresFt(PgimIndicadorDTO pgimIndicadorDTO, AuditoriaDTO auditoriaDTO) throws Exception{

		//Generar el nuevo código
		String ultimoCoCorrelativo = this.indicadorRepository.obtenerUltimoCoCorrelativoIndicarFt(EValorParametro.TIND_TIEMPOS_PROCESAMIENTO.toString());
		if(ultimoCoCorrelativo == null) ultimoCoCorrelativo = "IND-0000";
		int nuevoCorrelativo = Integer.parseInt(ultimoCoCorrelativo.split("-")[1]) + 1;
		String nuevoCorrelativoAux = String.format("%04d", nuevoCorrelativo);
		String codigo =  "IND-" + nuevoCorrelativoAux;
		//fin		
    	
		PgimIndicador pgimIndicador = new PgimIndicador();
		
		pgimIndicador.setTipoIndicador(new PgimValorParametro());
		pgimIndicador.getTipoIndicador().setIdValorParametro(pgimIndicadorDTO.getIdTipoIndicador());
		
		pgimIndicador.setTipoUnidadMedida(new PgimValorParametro());
		pgimIndicador.getTipoUnidadMedida().setIdValorParametro(pgimIndicadorDTO.getIdTipoUnidadMedida());
								
		pgimIndicador.setPgimProceso(new PgimProceso());
		pgimIndicador.getPgimProceso().setIdProceso(pgimIndicadorDTO.getIdProceso());
				
		PgimRelacionPaso relacionPasoOrigen = new PgimRelacionPaso();
		relacionPasoOrigen.setIdRelacionPaso(pgimIndicadorDTO.getIdRelacionPasoOrigen());
		pgimIndicador.setRelacionPasoOrigen(relacionPasoOrigen);

		PgimRelacionPaso relacionPasoDestino = new PgimRelacionPaso();
		relacionPasoDestino.setIdRelacionPaso(pgimIndicadorDTO.getIdRelacionPasoDestino());
		pgimIndicador.setRelacionPasoDestino(relacionPasoDestino);
		
		pgimIndicador.setTipoAgrupadoPor(new PgimValorParametro());
		pgimIndicador.getTipoAgrupadoPor().setIdValorParametro(pgimIndicadorDTO.getIdTipoAgrupadoPor());
		
		if(pgimIndicadorDTO.getIdTipoActorNegocio() != null) {
			pgimIndicador.setTipoActorNegocio(new PgimValorParametro());
				pgimIndicador.getTipoActorNegocio().setIdValorParametro(pgimIndicadorDTO.getIdTipoActorNegocio());	
		}
		
		if(pgimIndicadorDTO.getIdTipoCaracterFisc() != null) {
			pgimIndicador.setTipoCaracterFisc(new PgimValorParametro());
				pgimIndicador.getTipoCaracterFisc().setIdValorParametro(pgimIndicadorDTO.getIdTipoCaracterFisc());
		}
					
		pgimIndicador.setCoIndicador(codigo);
		pgimIndicador.setNoIndicador(pgimIndicadorDTO.getNoIndicador());
		pgimIndicador.setDeIndicador(pgimIndicadorDTO.getDeIndicador());
		pgimIndicador.setDeFormula(pgimIndicadorDTO.getDeFormula());
		pgimIndicador.setCoClaveIndicador(pgimIndicadorDTO.getCoClaveIndicador().trim());
		pgimIndicador.setEsIndicador(pgimIndicadorDTO.getEsIndicador());

		pgimIndicador.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimIndicador.setFeCreacion(auditoriaDTO.getFecha());
		pgimIndicador.setUsCreacion(auditoriaDTO.getUsername());
		pgimIndicador.setIpCreacion(auditoriaDTO.getTerminal());
		
		PgimIndicador pgimIndicadorCreado = this.indicadorRepository.save(pgimIndicador);
		PgimIndicadorDTO pgimIndicadorDTOCreado = this.obtenerIndicadorPorId(pgimIndicadorCreado.getIdIndicador());
		    	    	
		return pgimIndicadorDTOCreado;
	}


	@Transactional(readOnly = false)
	@Override
	public PgimIndicadorDTO modificarIndicadoresFt(PgimIndicadorDTO pgimIndicadorDTO, PgimIndicador pgimIndicadorActual, 
			AuditoriaDTO auditoriaDTO) throws Exception{
	
		pgimIndicadorActual.setTipoIndicador(new PgimValorParametro());
		pgimIndicadorActual.getTipoIndicador().setIdValorParametro(pgimIndicadorDTO.getIdTipoIndicador());
		
		pgimIndicadorActual.setTipoUnidadMedida(new PgimValorParametro());
		pgimIndicadorActual.getTipoUnidadMedida().setIdValorParametro(pgimIndicadorDTO.getIdTipoUnidadMedida());
								
		pgimIndicadorActual.setPgimProceso(new PgimProceso());
		pgimIndicadorActual.getPgimProceso().setIdProceso(pgimIndicadorDTO.getIdProceso());
		
		PgimRelacionPaso relacionPasoOrigen = new PgimRelacionPaso();
		relacionPasoOrigen.setIdRelacionPaso(pgimIndicadorDTO.getIdRelacionPasoOrigen());
		pgimIndicadorActual.setRelacionPasoOrigen(relacionPasoOrigen);
		
		PgimRelacionPaso relacionPasoDestino = new PgimRelacionPaso();
		relacionPasoDestino.setIdRelacionPaso(pgimIndicadorDTO.getIdRelacionPasoDestino());
		pgimIndicadorActual.setRelacionPasoDestino(relacionPasoDestino);

		pgimIndicadorActual.setTipoAgrupadoPor(new PgimValorParametro());
		pgimIndicadorActual.getTipoAgrupadoPor().setIdValorParametro(pgimIndicadorDTO.getIdTipoAgrupadoPor());
		
		if(pgimIndicadorDTO.getIdTipoActorNegocio() != null) {
			pgimIndicadorActual.setTipoActorNegocio(new PgimValorParametro());
				pgimIndicadorActual.getTipoActorNegocio().setIdValorParametro(pgimIndicadorDTO.getIdTipoActorNegocio());	
		}
		
		if(pgimIndicadorDTO.getIdTipoCaracterFisc() != null) {
			pgimIndicadorActual.setTipoCaracterFisc(new PgimValorParametro());
				pgimIndicadorActual.getTipoCaracterFisc().setIdValorParametro(pgimIndicadorDTO.getIdTipoCaracterFisc());
		}else {
			pgimIndicadorActual.setTipoCaracterFisc(null);
		}
					
		pgimIndicadorActual.setNoIndicador(pgimIndicadorDTO.getNoIndicador());
		pgimIndicadorActual.setDeIndicador(pgimIndicadorDTO.getDeIndicador());
		pgimIndicadorActual.setDeFormula(pgimIndicadorDTO.getDeFormula());
		pgimIndicadorActual.setCoClaveIndicador(pgimIndicadorDTO.getCoClaveIndicador().trim());
		pgimIndicadorActual.setEsIndicador(pgimIndicadorDTO.getEsIndicador());

		pgimIndicadorActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimIndicadorActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimIndicadorActual.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimIndicador pgimIndicadorCreado = this.indicadorRepository.save(pgimIndicadorActual);
		PgimIndicadorDTO pgimIndicadorDTOCreado = this.obtenerIndicadorPorId(pgimIndicadorCreado.getIdIndicador());
		    	    	
		return pgimIndicadorDTOCreado;
	}
    
	@Transactional(readOnly = false)
	@Override
	public void eliminarIndicadorFt(PgimIndicador pgimIndicadorActual, 
			AuditoriaDTO auditoriaDTO) throws Exception{
		
		pgimIndicadorActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimIndicadorActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimIndicadorActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimIndicadorActual.setIpActualizacion(auditoriaDTO.getTerminal());
				
		List<PgimMedicionDTO> lPgimMedicionDTO = this.medicionService.obtenerMedicionesPorIdIndicador(pgimIndicadorActual.getIdIndicador());
		for (PgimMedicionDTO pgimMedicionDTO : lPgimMedicionDTO) {
			PgimMedicion pgimMedicionActual = this.medicionService.getByIdMedicion(pgimMedicionDTO.getIdMedicion());
			this.medicionService.eliminarMedicion(pgimMedicionActual, auditoriaDTO);
		}

		this.indicadorRepository.save(pgimIndicadorActual);
		
	}
    
	@Override
	public PgimIndicadorDTO obtenerIndicadorPorId(Long idIndicador) {
		return this.indicadorRepository.obtenerIndicadorFtById(idIndicador);
	}
	
	@Override
	public PgimIndicador getByIdIndicadorFt(Long idIndicador) {
		return this.indicadorRepository.findById(idIndicador).orElse(null);
	}

	@Override
	public List<PgimIndicadorDTO> listarIndicadoresByPalabra(String textoBuscador) {
		
		List<PgimIndicadorDTO> data = this.indicadorRepository.listarIndicadoresByPalabra(EValorParametro.TIND_TIEMPOS_PROCESAMIENTO.toString(), textoBuscador);
		return data;
	}
	
}
