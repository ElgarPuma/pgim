package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;

import java.util.List;

/**
 * Ésta interface InstanciaProcesRepository incluye los metodos que nos
 * permitirá obtener las propiedades de la instancia y obtener los expedientes
 * del Siged y del Pgim.
 * 
 * @descripción: Logica de negocio de la entidad Instancia proceso
 * 
 * @author: barrantesluis
 * @version: 1.0
 * @fecha_de_creación: 29/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface InstanciaProcesRepository extends JpaRepository<PgimInstanciaProces, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
		   + "inpr.idInstanciaProceso, inpr.instanciaProcesoPadre.idInstanciaProceso, inpr.pgimProceso.idProceso, "
		   + "inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) " 
		   + "FROM PgimInstanciaProces inpr "
		   + "WHERE inpr.esRegistro = '1' " 
		   + "AND inpr.idInstanciaProceso = :idInstanciaProceso ")
	PgimInstanciaProcesDTO obtenerInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "ins.idInstanciaProceso, ins.instanciaProcesoPadre.idInstanciaProceso, "
			+ "ins.pgimProceso.idProceso, ins.noTablaInstancia, "
			+ "ins.coTablaInstancia, ins.nuExpedienteSiged, ins.esRegistro) " 
			+ "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' " 
			+ "AND ins.coTablaInstancia = :coTablaInstancia "
			+ "AND ins.pgimProceso.idProceso = :idProceso ")
	List<PgimInstanciaProcesDTO> obtenerInstanciasProceso(@Param("idProceso") Long idProceso,
			@Param("coTablaInstancia") Long coTablaInstancia);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "ins.idInstanciaProceso, ins.instanciaProcesoPadre.idInstanciaProceso, ins.pgimProceso.idProceso, ins.noTablaInstancia, "
			+ "ins.coTablaInstancia,ins.nuExpedienteSiged,ins.esRegistro) " + "FROM PgimInstanciaProces ins "
			+ "inner join PgimFaseProceso fase on ins.pgimProceso.idProceso = fase.pgimProceso.idProceso "
			+ "and  fase.idFaseProceso = :idFase " + "WHERE ins.esRegistro = '1' "
			+ "AND ins.coTablaInstancia = :coTablaInstancia " + "AND ins.pgimProceso.idProceso = :idProceso ")
	List<PgimInstanciaProcesDTO> obtenerInstanciasProcesoFase(@Param("idProceso") Long idProceso,
			@Param("idFase") Long idFase, @Param("coTablaInstancia") Long coTablaInstancia);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "ins.idInstanciaProceso, ins.instanciaProcesoPadre.idInstanciaProceso, ins.pgimProceso.idProceso, ins.noTablaInstancia, "
			+ "ins.coTablaInstancia,ins.nuExpedienteSiged,ins.esRegistro) " + "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' " + "AND ins.idInstanciaProceso = :idInstanciaProceso ")
	List<PgimInstanciaProcesDTO> findInstanciaDTOById(@Param("idInstanciaProceso") Long idInstanciaProceso);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "ins.idInstanciaProceso, ins.instanciaProcesoPadre.idInstanciaProceso, ins.pgimProceso.idProceso, ins.noTablaInstancia, "
			+ "ins.coTablaInstancia,ins.nuExpedienteSiged,ins.esRegistro) " + "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' " + "AND ins.nuExpedienteSiged IS NOT NULL ")
	List<PgimInstanciaProcesDTO> obtenerExpedientes();

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) "
			+ " from PgimSupervision supr"
			+ " inner join PgimInstanciaProces inpr on supr.pgimInstanciaProces.idInstanciaProceso = inpr.idInstanciaProceso and inpr.esRegistro = '1'"
			+ " inner join PgimConsumoContra coco on supr.idSupervision = coco.pgimSupervision.idSupervision and coco.esRegistro = '1'"
			+ " inner join PgimContrato cont on coco.pgimContrato.idContrato = cont.idContrato and coco.esRegistro = '1'"
			+ " left join PgimPas pasa on supr.idSupervision = pasa.pgimSupervision.idSupervision and pasa.esRegistro = '1'"
			+ " where supr.idSupervision = :idSupervision")
	List<PgimInstanciaProcesDTO> obtenerExpedientesByIdSupervision(@Param("idSupervision") Long idSupervision);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) "
			+ " from PgimSupervision supr "
			+ " INNER JOIN PgimInstanciaProces inpr on supr.pgimInstanciaProces.idInstanciaProceso = inpr.idInstanciaProceso "
			+ " and inpr.noTablaInstancia = 'PGIM_TC_SUPERVISION' and inpr.esRegistro = '1' and inpr.nuExpedienteSiged IS NOT NULL "
			+ " where supr.idSupervision = :idSupervision")
	List<PgimInstanciaProcesDTO> obtenerExpPgimSupervision(@Param("idSupervision") Long idSupervision);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) "
			+ " FROM PgimLiquidacion liqu "
			+ " INNER JOIN liqu.pgimInstanciaProces inpr "
			+ " WHERE liqu.idLiquidacion = :idLiquidacion "
			+ " AND inpr.esRegistro = '1' " 
			+ " AND inpr.nuExpedienteSiged IS NOT NULL")
	List<PgimInstanciaProcesDTO> obtenerExpPgimLiquidacion(@Param("idLiquidacion") Long idLiquidacion);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) "
			+ " FROM PgimSupervision supr "
			+ " INNER JOIN PgimPas pasa on supr.idSupervision = pasa.pgimSupervision.idSupervision and pasa.esRegistro = '1' "
			+ " INNER JOIN PgimInstanciaProces inpr on pasa.pgimInstanciaProces.idInstanciaProceso = inpr.idInstanciaProceso "
			+ " AND inpr.noTablaInstancia = 'PGIM_TC_PAS' and inpr.esRegistro = '1' and inpr.nuExpedienteSiged IS NOT NULL "
			+ " WHERE supr.idSupervision = :idSupervision ")
	List<PgimInstanciaProcesDTO> obtenerExpPgimPas(@Param("idSupervision") Long idSupervision);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) "
			+ " FROM PgimPas pasa  "
			+ " INNER JOIN pasa.pgimInstanciaProces inpr "
			+ " WHERE inpr.nuExpedienteSiged IS NOT NULL "
			+ " AND pasa.idPas = :idPas ")
	List<PgimInstanciaProcesDTO> obtenerExpPgimPasPorIdPas(@Param("idPas") Long idPas);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged) "
			+ " from PgimSupervision supr "
			+ " INNER JOIN PgimConsumoContra coco on supr.idSupervision = coco.pgimSupervision.idSupervision and coco.esRegistro = '1' "
			+ " INNER JOIN PgimContrato cont on coco.pgimContrato.idContrato = cont.idContrato and coco.esRegistro = '1' "
			+ " INNER JOIN PgimInstanciaProces inpr on cont.pgimInstanciaProces.idInstanciaProceso = inpr.idInstanciaProceso "
			+ " and inpr.noTablaInstancia = 'PGIM_TC_CONTRATO' and inpr.esRegistro = '1' and inpr.nuExpedienteSiged IS NOT NULL "
			+ " where supr.idSupervision = :idSupervision")
	List<PgimInstanciaProcesDTO> obtenerExpPgimContrato(@Param("idSupervision") Long idSupervision);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado("
			+ " inpr.idInstanciaProceso, inpr.noTablaInstancia, inpr.coTablaInstancia, inpr.nuExpedienteSiged)"
			+ " FROM PgimContrato cont INNER JOIN cont.pgimInstanciaProces inpr"
			+ " WHERE cont.idContrato = :idContrato"
			+ " AND cont.esRegistro = '1'"
			+ " AND inpr.esRegistro = '1'"
			+ " and inpr.nuExpedienteSiged IS NOT NULL")
	List<PgimInstanciaProcesDTO> obtenerExpPgimContratoXIdContrato(@Param("idContrato") Long idContrato);	

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "ins.idInstanciaProceso, ins.pgimProceso.idProceso, ins.noTablaInstancia, "
			+ "ins.coTablaInstancia, ins.nuExpedienteSiged) " + "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' " + "AND ins.pgimProceso.idProceso = :idProceso "
			+ "AND ins.noTablaInstancia = :noTablaInstancia " + "AND ins.coTablaInstancia = :coTablaInstancia")
	PgimInstanciaProcesDTO obtenerInstanciaProceso(@Param("idProceso") Long idProceso,
			@Param("noTablaInstancia") String noTablaInstancia, @Param("coTablaInstancia") Long coTablaInstancia);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "ins.idInstanciaProceso, ins.pgimProceso.idProceso, ins.noTablaInstancia, "
			+ "ins.coTablaInstancia, ins.nuExpedienteSiged) " 
			+ "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' " 
			+ "AND ins.pgimProceso.idProceso = :idProceso " )
	List<PgimInstanciaProcesDTO> listarInstanciaProcesPorProceso(@Param("idProceso") Long idProceso);

	/***
	 * Permite obtener la lista de instancia de procesos por numero de expediente
	 * que coinciden con la palabra clave.
	 * 
	 * @param palabraClave
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado("
			+ "ins.idInstanciaProceso, ins.nuExpedienteSiged) " + "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' AND (:palabraClave IS NULL OR LOWER(ins.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  ) ")
	// + " ) ORDER BY ins.nuExpedienteSiged")
	List<PgimInstanciaProcesDTO> listarPorPalabraClave(@Param("palabraClave") String palabraClave);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado("
			+ "ins.idInstanciaProceso, ins.nuExpedienteSiged ) " + "FROM PgimInstanciaProces ins "
			+ "WHERE ins.esRegistro = '1' "
			// + "AND contr.idContrato = :idContrato "
			+ "AND (:idInstanciaProceso IS NULL OR ins.idInstanciaProceso != :idInstanciaProceso) "
			+ "AND LOWER(ins.nuExpedienteSiged) = LOWER(:descNuExpedienteSiged)")
	List<PgimInstanciaProcesDTO> existeNuExpediente(@Param("idInstanciaProceso") Long idInstanciaProceso,
			@Param("descNuExpedienteSiged") String descNuExpedienteSiged);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado( "
			+ "inpr.idInstanciaProceso, inpr.instanciaProcesoPadre.idInstanciaProceso, proc.idProceso, inpr.noTablaInstancia, "
			+ "inpr.coTablaInstancia, inpr.nuExpedienteSiged, inpr.esRegistro) " 
			+ "FROM PgimInstanciaProces inpr "
			+ "INNER JOIN inpr.pgimProceso proc "
			+ "INNER JOIN PgimFaseProceso fapr ON (proc.idProceso = fapr.pgimProceso.idProceso) "
			+ "WHERE inpr.esRegistro = '1' "
			+ "AND proc.idProceso = :idProceso "
			+ "AND inpr.coTablaInstancia = :coTablaInstancia " 
			+ "AND fapr.idFaseProceso = :idFase "
			)
	PgimInstanciaProcesDTO obtenerInstanciaProcesoFase(@Param("idProceso") Long idProceso, @Param("idFase") Long idFase,
			@Param("coTablaInstancia") Long coTablaInstancia);	
	
	/**
	 * Permite obtener la instancia de proceso por numero de expediente
	 * @param nuExpedienteSiged
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTOResultado("
			+ "ins.idInstanciaProceso, ins.nuExpedienteSiged " 
			+ ") " 
			+ "FROM PgimInstanciaProces ins "
			+ "WHERE 1 = 1 "
			+ "AND ins.esRegistro = '1' "
			+ "AND LOWER(ins.nuExpedienteSiged) = LOWER(:nuExpedienteSiged)")
	PgimInstanciaProcesDTO obtenerInstanciaProcesoPorNuExpediente(@Param("nuExpedienteSiged") String nuExpedienteSiged);

}
