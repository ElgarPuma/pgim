package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEmpresaSupervisora;

import java.util.List;

/**
 * Éste interface EmpresaSupervisoraRepository
 * que aplica los filtros por nombres de la empresa supervisora y
 * mostrar una lista de la emrpesas supervisoras.
 * 
 * @descripción: Logica de negocio de la entidad Empresa supervisora
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface EmpresaSupervisoraRepository extends JpaRepository<PgimEmpresaSupervisora, Long> {

	/**
	 * Permite listar los agentes supervisados de acuerdo con los criterios filtro
	 * pre-establecidos.
	 * 
	 * @param coDocumentoIdentidad
	 * @param noRazonSocial
	 * @param textoBusqueda
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTOResultado( "
			+ "emsu_aux.idEmpresaSupervisoraAux, emsu_aux.noRazonSocial, emsu_aux.coDocumentoIdentidad, "
			+ "emsu_aux.deTelefono, "
			+ "emsu_aux.deTelefono2, emsu_aux.deCorreo, emsu_aux.deCorreo2, emsu_aux.nuContratoVigente " 
			+ ")  "
			+ "FROM PgimEmpresaSupervisorAux emsu_aux " 
			+ "WHERE (:coDocumentoIdentidad IS NULL OR LOWER(emsu_aux.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
			+ "AND (:noRazonSocial IS NULL OR LOWER(emsu_aux.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
			+ "OR LOWER(emsu_aux.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
			+ "AND (:textoBusqueda IS NULL OR ( "
			+ "LOWER(emsu_aux.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(emsu_aux.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) ) )  ")
	Page<PgimEmpresaSupervisoraDTO> listar(@Param("coDocumentoIdentidad") String coDocumentoIdentidad,
			@Param("noRazonSocial") String noRazonSocial, @Param("textoBusqueda") String textoBusqueda,
			Pageable paginador);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTOResultado("
			+ "emsu.idEmpresaSupervisora, pers.idPersona, " 
			+ "(CASE "
			+ "WHEN pers.coDocumentoIdentidad != null THEN (pers.coDocumentoIdentidad || ': ' ) "
			+ "END), " 
			+ "upper(pers.noRazonSocial), pers.flConsorcio " 
			+ ") "
			+ "FROM PgimEmpresaSupervisora emsu, PgimPersona pers " 
			+ "WHERE emsu.esRegistro = '1' "
			+ "AND emsu.pgimPersona = pers "
			+ "AND (?1 IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', ?1, '%'))  "
			+ "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', ?1, '%')) ) ")
	List<PgimEmpresaSupervisoraDTO> listarPorPalabraClave(String palabraClave);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTOResultado("
			+ "pers.idPersona, " 
			+ "(CASE " 
			+ "WHEN pers.coDocumentoIdentidad != null THEN (pers.coDocumentoIdentidad || ': ' || upper(pers.noRazonSocial)) " 
			+ "WHEN pers.coDocumentoIdentidad = null THEN (upper(pers.noRazonSocial)) "
			+ "END) "
			+ ") "
			+ "FROM PgimPersona pers " 
			+ "WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.coClaveTexto = ?2 "
			+ "AND NOT EXISTS (SELECT 1 " 
			+ "FROM PgimEmpresaSupervisora peco " 
			+ "WHERE peco.esRegistro = '1' " 
			+ "AND peco.pgimPersona = pers) "
			+ "AND (?1 IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', ?1, '%'))  "
			+ "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', ?1, '%')) ) ")
	List<PgimEmpresaSupervisoraDTO> listarPorPersonaJuridica(String palabraClave, String DOIDE_RUC);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTOResultado("
			+ "emsu.idEmpresaSupervisora, pers.idPersona, " 
			+ "(CASE "
			+ "WHEN pers.coDocumentoIdentidad != null THEN (pers.coDocumentoIdentidad || ': ' || upper(pers.noRazonSocial)) "
			+ "WHEN pers.coDocumentoIdentidad = null THEN (upper(pers.noRazonSocial)) " 
			+ "END), "
			+ "upper(pers.noRazonSocial), pers.flConsorcio " 
			+ ") "
			+ "FROM PgimEmpresaSupervisora emsu, PgimPersona pers " 
			+ "WHERE emsu.esRegistro = '1' " 
			+ "AND emsu.pgimPersona = pers "
			+ "AND emsu.idEmpresaSupervisora = ?1 ")
	PgimEmpresaSupervisoraDTO obtenerPorId(Long idEmpresaSupervisora);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEmpresaSupervisoraDTOResultado("
			+ "emsu.idEmpresaSupervisora ) "
			+ "FROM PgimEmpresaSupervisora emsu " 
			+ "WHERE emsu.esRegistro ='1' "
			+ "AND emsu.pgimPersona.idPersona = :idPersona ")
	List<PgimEmpresaSupervisoraDTO> listarEmpresaSupervisoraPorPersona(@Param("idPersona") Long idPersona);
	
}
