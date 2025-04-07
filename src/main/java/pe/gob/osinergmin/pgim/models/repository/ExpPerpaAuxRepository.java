package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerpaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimExpPerpaAux;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad expedientes pendientes de atender, por paso, según año
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface ExpPerpaAuxRepository extends JpaRepository<PgimExpPerpaAux, Long>{
	
	/**
	 * Permite obtener la lista preparada de expedientes con PAS por persona asignada, paso y año usado en reporte correspondiente de manera paginada
	 * @param anioSupervision
	 * @param paginador
	 * @return
	 */
		@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExpPerpaAuxDTOResultado( expe.idPersona, expe.deEntidadPersona, expe.etiquetaPersona, "
				+ "SUM(expe.f1Asignarsupe), "
				+ "SUM(expe.f1Coordinarlasupe), "
				+ "SUM(expe.f1Firmardji), "
				+ "SUM(expe.f1Generarcredencialytdr), "
				+ "SUM(expe.f1Aprobartdrycredencial), "
				+ "SUM(expe.f1Gestionarsaldo), "
				+ "SUM(expe.f1Definirantecedentesarevisar), "
				+ "SUM(expe.f1Revisarantecedentesdelaum), "
				+ "SUM(expe.f1Aprobarrevantecedentes), "
				+ "SUM(expe.f1Completarsupecomocancelada), "
				+ "SUM(expe.f2Iniciarsupedecampo), "
				+ "SUM(expe.f2Solicitardocumentacionalaum), "
				+ "SUM(expe.f2Realizarmedyconstatarhechos), "
				+ "SUM(expe.f2Presentaractadesupe), "
				+ "SUM(expe.f2Reprogramarsupe), "
				+ "SUM(expe.f3Elaborarinfdesupefallida), "
				+ "SUM(expe.f3Elaborarypresentarinfdesupe), "
				+ "SUM(expe.f4Revisaryaprobarinfsupe), "
				+ "SUM(expe.f4Elaborarmemodesupefallida), "
				+ "SUM(expe.f4Aprobarmemosupefallida), "
				+ "SUM(expe.f4Completarsupecomofallida), "
				+ "SUM(expe.f5Confirmahconstataeinfrac), "
				+ "SUM(expe.f5Continuarconelpas), "
				+ "SUM(expe.f5Supecompletadaconiniciopas), "
				+ "SUM(expe.f5Continuarconelarchivado), "
				+ "SUM(expe.f5Aprobarocafarch), "
				+ "SUM(expe.f5Completarsupearchivandola), "
				+ "SUM(expe.f5Solicitarampliaciondeip), "
				+ "SUM(expe.f5Firmarsoldeampliacionplazo), "
				+ "SUM(expe.f5Aprobarsolampliaciondeplazo), "
				+ "SUM(expe.f5Visarhechosconstatados) "
				+") "
	            + "FROM PgimExpPerpaAux expe "
	            + "WHERE expe.anioSupervision = :anioSupervision "
	        + "GROUP BY expe.idPersona, expe.etiquetaPersona, expe.deEntidadPersona "
	        )
	Page<PgimExpPerpaAuxDTO> listarReporteExpPasPerPasoAnioPaginado(@Param("anioSupervision") Long anioSupervision, Pageable paginador);
	
	
	/**
	 * Permite obtener la lista preparada de expedientes con PAS por persona asignada, fase y año usado en reporte correspondiente
	 * @param anioSupervision
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimExpPerpaAuxDTOResultado( expe.idPersona, expe.deEntidadPersona, expe.etiquetaPersona, "
			+ "SUM(expe.f1Asignarsupe), "
			+ "SUM(expe.f1Coordinarlasupe), "
			+ "SUM(expe.f1Firmardji), "
			+ "SUM(expe.f1Generarcredencialytdr), "
			+ "SUM(expe.f1Aprobartdrycredencial), "
			+ "SUM(expe.f1Gestionarsaldo), "
			+ "SUM(expe.f1Definirantecedentesarevisar), "
			+ "SUM(expe.f1Revisarantecedentesdelaum), "
			+ "SUM(expe.f1Aprobarrevantecedentes), "
			+ "SUM(expe.f1Completarsupecomocancelada), "
			+ "SUM(expe.f2Iniciarsupedecampo), "
			+ "SUM(expe.f2Solicitardocumentacionalaum), "
			+ "SUM(expe.f2Realizarmedyconstatarhechos), "
			+ "SUM(expe.f2Presentaractadesupe), "
			+ "SUM(expe.f2Reprogramarsupe), "
			+ "SUM(expe.f3Elaborarinfdesupefallida), "
			+ "SUM(expe.f3Elaborarypresentarinfdesupe), "
			+ "SUM(expe.f4Revisaryaprobarinfsupe), "
			+ "SUM(expe.f4Elaborarmemodesupefallida), "
			+ "SUM(expe.f4Aprobarmemosupefallida), "
			+ "SUM(expe.f4Completarsupecomofallida), "
			+ "SUM(expe.f5Confirmahconstataeinfrac), "
			+ "SUM(expe.f5Continuarconelpas), "
			+ "SUM(expe.f5Supecompletadaconiniciopas), "
			+ "SUM(expe.f5Continuarconelarchivado), "
			+ "SUM(expe.f5Aprobarocafarch), "
			+ "SUM(expe.f5Completarsupearchivandola), "
			+ "SUM(expe.f5Solicitarampliaciondeip), "
			+ "SUM(expe.f5Firmarsoldeampliacionplazo), "
			+ "SUM(expe.f5Aprobarsolampliaciondeplazo), "

			+ "SUM(expe.f5Visarhechosconstatados) "
			+") "
	        + "FROM PgimExpPerpaAux expe "
	        + "WHERE expe.anioSupervision = :anioSupervision "
	    + "GROUP BY expe.idPersona, expe.etiquetaPersona, expe.deEntidadPersona "
	    )			
	List<PgimExpPerpaAuxDTO> listarReporteExpPasPerPasoAnio(@Param("anioSupervision") Long anioSupervision,
			Sort sort);
	
}
