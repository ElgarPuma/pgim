package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfracciontop15AuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespMesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxtitularAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxumAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionContra;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;


public interface InfraccionService {

	/**
	 * Permite obtener la lista preparada de cantidad de infracciones por titular minero y año 
	 * 
	 * @param filtroPgimInfraccionxtitularAuxDTO
	 * @return
	 * @throws Exception
	 */
	List<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitular(PgimInfraccionxtitularAuxDTO filtroPgimInfraccionxtitularAuxDTO) throws Exception;
	
	/**
	 * Permite obtener la lista preparada de cantidad de infracciones por titular minero y año de manera paginada
	 * 
	 * @param filtroPgimInfraccionxtitularAuxDTO
	 * @param paginador
	 * @return
	 * @throws Exception
	 */
	Page<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitularPaginado(PgimInfraccionxtitularAuxDTO filtroPgimInfraccionxtitularAuxDTO, Pageable paginador) throws Exception;
	
	
	/**
     * Permite obtener la lista preparada de infracciones por especialidad y año usado en reporte correspondiente     
     * @param filtroPgimInfraccionxespAuxDTO
     * @return
     * @throws Exception
     */
    List<PgimInfraccionxespAuxDTO> listarReporteInfraccionxesp(PgimInfraccionxespAuxDTO filtroPgimInfraccionxespAuxDTO) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones por especialidad y año usado en reporte correspondiente de manera paginada
     * @param filtroPgimInfraccionxespAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimInfraccionxespAuxDTO> listarReporteInfraccionxespPaginado(PgimInfraccionxespAuxDTO filtroPgimInfraccionxespAuxDTO, Pageable paginador) throws Exception;
    
    
    /**
     * Permite obtener la lista preparada de infracciones detalladas usado en reporte correspondiente
     * paginada
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesPaginado(PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones detalladas por agente supervisado usado en reporte correspondiente
     * paginada
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesASPaginado(PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones detalladas por unidad minera usado en reporte correspondiente
     * paginada
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesUMPaginado(PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones detalladas por division supervisora usado en reporte correspondiente
     * paginada
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesDSPaginado( PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones detalladas por especialidad usado en reporte correspondiente
     * paginada
     * @param filtroPgimDetaInfraccionesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesEspecPaginado(PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones mas recurrentes (top15) usado en reporte correspondiente
     * @param filtroPgimExppasdsuAnioAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimInfracciontop15AuxDTO> listarReporteInfraccionesTop15(PgimInfracciontop15AuxDTO filtroPgimExppasdsuAnioAuxDTO, Pageable paginador) throws Exception;
    
    
    /**
     * Permite obtener la lista preparada de infracciones por especialidad y mes usado en reporte correspondiente     
     * @param filtroPgimInfraccionxespMesAuxDTO
     * @return
     * @throws Exception
     */
    List<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMes(PgimInfraccionxespMesAuxDTO filtroPgimInfraccionxespMesAuxDTO) throws Exception;
    
    /**
     * Permite obtener la lista preparada de infracciones por especialidad y mes usado en reporte correspondiente de manera paginada
     * @param filtroPgimInfraccionxespMesAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMesPaginado(PgimInfraccionxespMesAuxDTO filtroPgimInfraccionxespMesAuxDTO, Pageable paginador) throws Exception;
    
    
    /**
     * Permite obtener la lista preparada de infracciones por UM y año usado en reporte correspondiente de manera paginada
     * @param filtroPgimInfraccionxumAuxDTO
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimInfraccionxumAuxDTO> listarReporteInfraccionesUmAnioPaginado(PgimInfraccionxumAuxDTO filtroPgimInfraccionxumAuxDTO, Pageable paginador) throws Exception;

    /**
     * Permite obtener el historial de una infracción dada.
     * @param idInfraccion
     * @return
     */
    List<PgimInfraccionDTO> listarHistorialInfraccion(Long idInfraccion);

    /**
     * Permite obtener el objeto infracción de acuerdo con su identificador.
     * @param idInfraccion
     * @return
     */
    PgimInfraccionDTO obtenerInfraccionPorId(Long idInfraccion);
    
    /**
     * Permite copiar las infracciones a partir de las existentes de una supervisión dada y a estas últimas
     * marcarlas como no vigentes o mantenerlas vigentes según el parámetro respectivo ingresado.
     * 
     * @param idSupervision					Id de la supervisión origen de la cual se obtendrá las infracciones a copiar
     * @param pgimInstanciaPasoDTO			Objeto de Instancia de paso para las nuevas infracciones
     * @param pgimPas						Objeto Pas al cual se copiarán las nuevas infracciones. Puede ser null, en caso se copie a la fiscalización misma.
     * @param hacerNoVigenteInfraccionBase	Flag que indica si se debe hacer "No vigente" a las infracciones base copiadas
     * @param auditoriaDTO					Objeto de auditoría
     */
    void copiarInfraccionesSupervision(Long idSupervision, PgimInstanciaPasoDTO pgimInstanciaPasoDTO, PgimPas pgimPas, 
    		Boolean hacerNoVigenteInfraccionBase, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite copiar las infracciones a partir de las existentes de un PAS dado y a estas últimas
     * marcarlas como no vigentes.
     * 
     * @param pgimInstanciaPasoDTO	Objeto de Instancia de paso para las nuevas infracciones
     * @param pgimPas				Objeto Pas origen y destino para la copia de las infracciones 
     * @param auditoriaDTO			Objeto de auditoría
     */
    void copiarInfraccionesPas( PgimInstanciaPasoDTO pgimInstanciaPasoDTO, PgimPas pgimPas,
    		AuditoriaDTO auditoriaDTO) throws Exception;
    
    /**
     * Permite procesar la copia de una infracción dada, la misma que pasa a ser no vigente
     * o se mantiene como vigente según el parámetro respectivo ingresado.
     * 
     * @param pgimInfraccionAuxDTO			Infracción que será copiada
     * @param idInstanciaPasoActual			Id de instancia de paso para las nuevas infracciones
     * @param idPas							Id del Pas al cual corresponde la infracción. Puede ser null
     * @param hacerNoVigenteInfraccionBase	Flag que indica si se debe hacer "No vigente" a la infracción base copiada
     * @param auditoriaDTO					Objeto de auditoría
     * @throws Exception
     */
    PgimInfraccionDTO realizarCopiaInfraccion(PgimInfraccionAuxDTO pgimInfraccionAuxDTO, Long idInstanciaPasoActual,
            Long idPas, Boolean hacerNoVigenteInfraccionBase, AuditoriaDTO auditoriaDTO) throws Exception;
    	
	/**
	 * Permite obtener un entity infraccionContra por su Id
	 * 
	 * @param idInfraccionContra
	 * @return
	 */
	PgimInfraccionContra getByIdInfraccionContra(Long idInfraccionContra);
    
    /**
     * Permite listar responsable(s) de una infracción 
     * @param idInfraccion
     * @return
     */
    List<PgimInfraccionContraDTO> listarInfraccionResponsables(Long idInfraccion);
    
    /**
     * Permite crear contratista(s) responsable(s) de una infracción 
     * 
     * @param idInfraccion
     * @param lstPgimInfraccionContraDTO
     * @param idInstanciaPasoActual
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
	PgimInfraccionDTO crearLstInfraccionContratistas(Long idInfraccion, List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO, 
			Long idInstanciaPasoActual, AuditoriaDTO auditoriaDTO) throws Exception;
    

	/**
	 * Permite eliminar un contratista responsable de infracción
	 * 
	 * @param pgimInfraccionContraActual
	 * @param idInstanciaPasoActual
	 * @param auditoriaDTO
	 */
	PgimInfraccionDTO eliminarInfraccionContratista(PgimInfraccionContra pgimInfraccionContraActual, 
			Long idInstanciaPasoActual, AuditoriaDTO auditoriaDTO) throws Exception;

	/**
	 * Permite registrar las infraciones como no vigente asi como sus tablas hijas (contratistas responsables) 
	 * @param idInfraccion
	 * @param auditoriaDTO
	 */
	void registrarInfracionNoVigente(Long idOblgcnNrmtvaHchoc, AuditoriaDTO auditoriaDTO);
}
