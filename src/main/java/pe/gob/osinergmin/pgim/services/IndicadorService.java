package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimKpiGeneralDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicador;

public interface IndicadorService {
	
	/**
     * Permite listar los indicadores en general por idTipoIndicador
     * @return
     */
    List<PgimIndicadorDTO> listarIndicadorBycoClavetexto(String parametro);
    
    /**
     * Permite obtener un registro de indicador por id
     * @return
     */
    PgimIndicadorDTO obtenerIndicadorById(Long idIndicador);

    /**
     * Permite obtener un registro con los indicadores generales de Aprobación de Informe de Fiscalización
     * @return
     */
    PgimKpiGeneralDTO obtenerKpiAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por especialidad para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * @return
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorEspecialidadAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    
    /**
     * Permite obtener la data por tipo de fiscalización para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorTipoFiscalizacionAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por contrato para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorContratoAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por agente fiscalizado para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorAgenteFiscalizadoAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por división supervisora para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorDivisionSupervisoraAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por motivo de fiscalización para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorMotivoFiscalizacionAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por unidad fiscalizable para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorUnidadFiscalizableAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por sub-tipo de fiscalización para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorSubtipoFiscalizacionAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por empresa supervisora para la gráfica AMChart del dashboard de Aprobación de Informe de Fiscalización
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorEmpresaSupervisoraAprobacionInformeFiscalizacion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    
    
    
    
    
    
    
    
    
    /**
     * Permite obtener un registro con los indicadores generales de Emisión de Informe de Instrucción
     * @return
     */
    PgimKpiGeneralDTO obtenerKpiEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por especialidad para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * @return
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorEspecialidadEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    
    /**
     * Permite obtener la data por tipo de fiscalización para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorTipoFiscalizacionEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por contrato para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorContratoEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por agente fiscalizado para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorAgenteFiscalizadoEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por división supervisora para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorDivisionSupervisoraEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por motivo de fiscalización para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorMotivoFiscalizacionEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por unidad fiscalizable para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorUnidadFiscalizableEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por sub-tipo de fiscalización para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorSubtipoFiscalizacionEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    /**
     * Permite obtener la data por empresa supervisora para la gráfica AMChart del dashboard de Emisión de Informe de Instrucción
     * 
     */
    List<PgimGraficaResultadoDTO> obtenerDataPorEmpresaSupervisoraEmisionInformeInstruccion(FiltroIndicadorDTO filtroIndicadorDTO);
    
    
    
    /*---------------------------------------------------------
    	metodos para indicadores de flujos de trabajo
	--------------------------------------------------------- */
    
	/**
	 * Permite obtener el listado de indicadores de flujos de trabajo
	 * @param pgimIndicadorDTOFiltro
	 * @param paginador
	 * @return
	 */
	Page<PgimIndicadorDTO> listarIndicadoresFt(PgimIndicadorDTO pgimIndicadorDTOFiltro, Pageable paginador);
	
    /**
     * Permite validad sí el coClove ya existe registrado
     * @param pgimIndicadorDTO
     * @return
     */
	List<PgimIndicadorDTO> validarCoClaveUnicaIndicadorFt(PgimIndicadorDTO pgimIndicadorDTO);
	
     /**
      * Permite crear un indicador del flujo de trabajo
      * @param pgimIndicadorDTO
      * @param auditoriaDTO
      * @return
      * @throws Exception
      */
     PgimIndicadorDTO crearIndicadoresFt(PgimIndicadorDTO pgimIndicadorDTO, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/**
	 * Permite hacer modificaciones en el indicador de flujos de trabajo
	 * @param pgimIndicadorDTO
	 * @param pgimIndicadorDTOActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	PgimIndicadorDTO modificarIndicadoresFt(PgimIndicadorDTO pgimIndicadorDTO, PgimIndicador pgimIndicadorDTOActual, 
			AuditoriaDTO auditoriaDTO) throws Exception;
    
	/**
	 * Permite eliminar logicamente un indicador de flujos de trabajo
	 * @param pgimIndicadorDTOActual
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	void eliminarIndicadorFt(PgimIndicador pgimIndicadorDTOActual, 
			AuditoriaDTO auditoriaDTO) throws Exception;
	
     /**
	 * Permite obtener el indicador del flujo de trabajo por su identificador interno
	 * @param idIndicador
	 * @return
	 */
	PgimIndicadorDTO obtenerIndicadorPorId(Long idIndicador);
	
	/**
	 * Permite obtener el indicador del flujo de trabajo( Entity ) por su identificador interno
	 * @param idIndicador
	 * @return
	 */
	PgimIndicador getByIdIndicadorFt(Long idIndicador);

     /**
      * Permite obtener un listado de indicadores del flujos de trabajo filtrado por nombre o codigo
      * @param textoBuscador
      * @return
      */
	List<PgimIndicadorDTO> listarIndicadoresByPalabra(String textoBuscador);
}
