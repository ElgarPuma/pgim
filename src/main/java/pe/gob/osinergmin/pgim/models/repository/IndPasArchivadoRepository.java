package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimKpiGeneralDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimIndPasArchivado;

/**
 * @descripción: Lógica de negocio de la entidad PGIM_VW_IND_PAS_ARCHIVADO
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 06/03/2023
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface IndPasArchivadoRepository extends JpaRepository<PgimIndPasArchivado, Long> {
	
	/**
     * Permite obtener un registro con los indicadores generales de Emisión de Informe de instrucción.
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimKpiGeneralDTOResultado( "
			+ " COUNT(*),MAX(ind.caDiasDemora),MIN(ind.caDiasDemora),AVG(ind.caDiasDemora) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
			)
	PgimKpiGeneralDTO obtenerKpiEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	/**
     * Permite obtener la data por especialidad para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.noEspecialidad,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.noEspecialidad "
			+ "ORDER BY COUNT(*) DESC"
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorEspecialidadEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	/**
     * Permite obtener la data por tipo de fiscalización para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.noTipoSupervision,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.noTipoSupervision"
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorTipoFiscalizacionEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	
	/**
     * Permite obtener la data por contrato para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.nuContrato,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.nuContrato "
			+ "ORDER BY COUNT(*) "
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorContratoEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	
	/**
     * Permite obtener la data por agente fiscalizado para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.noAgenteSupervisado,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.noAgenteSupervisado "
			+ "ORDER BY COUNT(*) "
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorAgenteFiscalizadoEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	
	
	/**
     * Permite obtener la data por división supervisora para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.noDivisionSupervisora,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.noDivisionSupervisora"
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorDivisionSupervisoraEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	/**
     * Permite obtener la data por motivo de fiscalización para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.deMotivoSupervision,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.deMotivoSupervision "
			+ "ORDER BY COUNT(*) DESC "
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorMotivoFiscalizacionEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	/**
     * Permite obtener la data por unidad fiscalizable para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.noUnidadMinera,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.noUnidadMinera "
			+ "ORDER BY COUNT(*) "
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorUnidadFiscalizableEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	/**
     * Permite obtener la data por sub-tipo de fiscalización para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.deSubtipoSupervision,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.deSubtipoSupervision "
			+ "ORDER BY COUNT(*) "
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorSubtipoFiscalizacionEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);
	
	
	/**
     * Permite obtener la data por empresa supervisora para la gráfica AMChart del dashboard
     * 
     */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimGraficaResultadoDTOResultado( "
			+ " ind.noEmpresaSupervisora,COUNT(*) "
			+ ") "	
			+ "FROM PgimIndPasArchivado ind "			
			+ "WHERE (:feDesde IS NULL OR ( :feDesde <= TRUNC(ind.feApruebaInforme ))) "
			+ "AND (:feHasta IS NULL OR (:feHasta >= TRUNC(ind.feApruebaInforme))) "
            + "AND (:idEspecialidad IS NULL OR ind.idEspecialidad = :idEspecialidad) "
            + "AND (:idDivisionSupervisora IS NULL OR ind.idDivisionSupervisora = :idDivisionSupervisora) "
            + "AND (:idTipoFiscalizacion IS NULL OR ind.idTipoSupervision = :idTipoFiscalizacion) "
            + "AND (:idSubtipoFiscalizacion IS NULL OR ind.idSubtipoSupervision = :idSubtipoFiscalizacion) "
            + "AND (:idMotivo IS NULL OR ind.idMotivoSupervision = :idMotivo) "
            + "AND (:noAgenteSupervisado IS NULL OR LOWER(ind.rucAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) "
            + "OR LOWER(ind.noAgenteSupervisado) LIKE LOWER(CONCAT('%', :noAgenteSupervisado, '%')) ) "
            + "AND (:noUnidadMinera IS NULL OR LOWER(ind.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
            + "OR LOWER(ind.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(ind.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:noEmpresaSupervisora IS NULL OR LOWER(ind.rucEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) "
			+ "OR LOWER(ind.noEmpresaSupervisora) LIKE LOWER(CONCAT('%', :noEmpresaSupervisora, '%')) ) "
            
			+ "GROUP BY ind.noEmpresaSupervisora "
			+ "ORDER BY COUNT(*) "
			)
	List<PgimGraficaResultadoDTO> obtenerDataPorEmpresaSupervisoraEmisionInformeInstruccion(
			@Param("feDesde") Date feDesde,
			@Param("feHasta") Date feHasta,
			@Param("idEspecialidad") Long idEspecialidad,
			@Param("idDivisionSupervisora") Long idDivisionSupervisora,
			@Param("idTipoFiscalizacion") Long idTipoFiscalizacion,
			@Param("idSubtipoFiscalizacion") Long idSubtipoFiscalizacion,
			@Param("idMotivo") Long idMotivo,
			@Param("noAgenteSupervisado") String noAgenteSupervisado,
			@Param("noUnidadMinera") String noUnidadMinera,
			@Param("nuContrato") String nuContrato,
			@Param("noEmpresaSupervisora") String noEmpresaSupervisora
			);

}
