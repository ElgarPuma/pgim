package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;

import java.util.List;

/**
 * PersonaRepository incluye los metodos que nos enseña a obtener su tipo,
 * numero de razon social y el RUC.
 * 
 * @descripción: Logica de negocio de la entidad Persona.
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 19/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface PersonaRepository extends JpaRepository<PgimPersona, Long> {

	@Query("SELECT pers FROM PgimPersona pers WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.idValorParametro = :idTipoDocumentoIdentidad "
			+ "AND pers.coDocumentoIdentidad = :codDocumentoIdentidad")
	PgimPersona obtenerPorTipoYNumero(@Param("idTipoDocumentoIdentidad") Long idTipoDocumentoIdentidad,
			@Param("codDocumentoIdentidad") String codDocumentoIdentidad);

	@Query("SELECT pers FROM PgimPersona pers WHERE pers.esRegistro = '1' "
			+ "AND pers.noRazonSocial = :noRazonSocial ")
	PgimPersona obtenerPorTipoYRuc(@Param("noRazonSocial") String noRazonSocial);

	/**
	 * Permite obtener la persona natural en base a su identificador.
	 * 
	 * @param idPersona
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noPersona, " 
			+ "pers.apPaterno, pers.apMaterno) "
			+ "FROM PgimPersona pers " 
			+ "WHERE pers.idPersona = :idPersona " 
			+ "AND pers.esRegistro = '1' ")
	PgimPersonaDTO obtenerPersonaNaturalPorId(@Param("idPersona") Long idPersona);

	/**
	 * Permite obtener la persona jurídica en base a su identificador.
	 * 
	 * @param idPersona
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noRazonSocial " 
			+ ") "
			+ "FROM PgimPersona pers " 
			+ "WHERE pers.idPersona = :idPersona " 
			+ "AND pers.esRegistro = '1'")
	PgimPersonaDTO obtenerPersonaJuridicaPorId(@Param("idPersona") Long idPersona);

	/**
	 * Permite obtener la persona jurídica por búsqueda con palabra clave.
	 * 
	 * @param palabraClave
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noRazonSocial) "
			+ "FROM PgimPersona pers WHERE pers.esRegistro = '1' "
			+ "AND (?1 IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', ?1, '%'))  "
			+ "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', ?1, '%')) "
			+ "OR LOWER(pers.noCorto) LIKE LOWER(CONCAT('%', ?1, '%')) )")
	List<PgimPersonaDTO> listarPersonaJuridicaPorPalabraClave(String palabraClave);

	/**
	 * Permite obtener la persona jurídica por búsqueda con palabra clave.
	 * 
	 * @param palabraClave
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno, pers.noRazonSocial) "
			+ "FROM PgimPersona pers WHERE pers.esRegistro = '1' "
			+ "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad ||' - '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) "
			+ "LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
	List<PgimPersonaDTO> listarPorPersona(String palabraClave);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado(" 
	+ "pers.idPersona, "
			+ "pers.noRazonSocial, "
			+ "pers.tipoDocIdentidad.idValorParametro, pers.tipoDocIdentidad.noValorParametro, "
			+ "pers.coDocumentoIdentidad, " 
			+ "CASE " 
			+ "when pers.tiSexo = '1' then 'Masculino' "
			+ "when pers.tiSexo = '0' then 'Femenino' end, "
			+ "pers.noPersona, pers.apPaterno, pers.apMaterno, pers.flConsorcio " 
			+ " ) "
			+ "FROM PgimPersona pers WHERE pers.esRegistro = '1'  "
			+ "AND (:noRazonSocial IS NULL OR LOWER(pers.noRazonSocial || ' ' || pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
			+ " ) "
			+ "AND (:coDocumentoIdentidad IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
			+ "AND (:idTipoDocIdentidad IS NULL OR LOWER(pers.tipoDocIdentidad.idValorParametro) LIKE LOWER(CONCAT('%', :idTipoDocIdentidad, '%')) ) "
			+ "AND (:flConsorcio IS NULL OR COALESCE(pers.flConsorcio, '0') = :flConsorcio ) "

			+ "AND (:textoBusqueda IS NULL OR ( "
			+ "LOWER(pers.noRazonSocial || ' ' || pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ " ))")
	Page<PgimPersonaDTO> listarPersonas(@Param("noRazonSocial") String noRazonSocial,
			@Param("coDocumentoIdentidad") String coDocumentoIdentidad,
			@Param("idTipoDocIdentidad") Long idTipoDocIdentidad,
			@Param("flConsorcio") String flConsorcio,
			@Param("textoBusqueda") String textoBusqueda,
			Pageable paginador);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noRazonSocial) "
			+ "FROM PgimPersona pers WHERE pers.esRegistro = '1' "
			+ "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad) "
			+ "LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
	List<PgimPersonaDTO> listarPorCoDocumentoIdentidad(String palabraClave);

	/**
	 * Permite obtener la lista de personas cuya razón social o nombres completos coinciden por aproximación con la 
	 * palabra clave pasada como parámetro de entrada.
	 * @param palabraClave
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, " 
			+ "CASE "
			+ "when pers.noRazonSocial is NOT NULL then pers.noRazonSocial ELSE (pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) END, "
			+ "pers.noPersona, pers.apPaterno, pers.apMaterno ) " 
			+ "FROM PgimPersona pers WHERE pers.esRegistro = '1' "
			+ "AND (:palabraClave IS NULL OR LOWER(pers.noRazonSocial || ' ' || pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) "
			+ "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
			+ " )")
	List<PgimPersonaDTO> listarPorNoRazonSocial(String palabraClave);

	/**
	 * Permite obtener la lista de personas cuyo RUC, o razón social coinciden por aproximación con la palabra clave pasada como parámetro de entrada.
	 * @param palabraClave
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noRazonSocial || ' (' || pers.coDocumentoIdentidad || ')' "
			+ ") " 
			+ "FROM PgimPersona pers "
			+ "WHERE pers.tipoDocIdentidad.coClaveTexto = :DOIDE_RUC "
			+ "AND "
			+ "("
			+ ":palabraClave IS NULL "
			+ "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
			+ "OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
			+ " )"
			+ "AND pers.esRegistro = '1' "
			)	
	List<PgimPersonaDTO> listarPorRazonSocial(@Param("palabraClave") String palabraClave, @Param("DOIDE_RUC") String DOIDE_RUC);

	/**
	 * Obtener las propiedades de la persona por el idPersona
	 * 
	 * @param idPersona identificador de la persona
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			// Identificación
			+ "pers.idPersona, pers.tipoDocIdentidad.idValorParametro, pers.tipoDocIdentidad.noValorParametro, "
			+ "pers.coDocumentoIdentidad, TRIM(UPPER(pers.noRazonSocial)), pers.noCorto, UPPER(pers.noPersona), UPPER(pers.apPaterno), UPPER(pers.apMaterno), "
			+ "pers.noPrefijoPersona, pers.tiSexo, " 
			+ "pers.feNacimiento, " 
			+ "pers.deRestriccion, "
			+ "pers.fuentePersonaNatural.idValorParametro, "
			// Contacto
			+ "pers.deTelefono, pers.deTelefono2, pers.deCorreo, pers.deCorreo2, "
			// Ubicación
			+ "ubig.idUbigeo, "
			+ "((SELECT TRIM(ubde.noUbigeo) FROM PgimUbigeo ubde WHERE ubde.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 2) || '0000') || ', ' || "
			+ "(SELECT TRIM(ubdi.noUbigeo) FROM PgimUbigeo ubdi where ubdi.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 4) || '00') || ', ' || "
			+ "TRIM(ubig.noUbigeo)), pers.diPersona, "
			// Notificaciones electrónicas
			+ "pers.flAfiliadoNtfccionElctrnca, pers.deCorreoNtfccionElctrnca, pers.feAfiliadoDesde, "
			// Otros
			+ "pers.cmNota, pers.flConsorcio " 
			+ " ) "
			+ "FROM PgimPersona pers LEFT OUTER JOIN pers.pgimUbigeo ubig WHERE pers.esRegistro = '1'  "
			+ "AND pers.idPersona = :idPersona ")
	PgimPersonaDTO obtenerPersonalNatuJuriPorId(@Param("idPersona") Long idPersona);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.tipoDocIdentidad.idValorParametro, pers.tipoDocIdentidad.noValorParametro, "
			+ "pers.coDocumentoIdentidad "
			// + "pers.noPersona, pers.apPaterno, pers.apMaterno, "
			// + "pers.feNacimiento, pers.diPersona, "
			// + "pers.deTelefono "
			+ " ) " 
			+ "FROM PgimPersona pers " 
			+ "WHERE pers.esRegistro = '1' "
			+ "AND (:idPersona IS NULL OR pers.idPersona != :idPersona) "
			+ "AND pers.tipoDocIdentidad.idValorParametro = :idTipoDocumento "
			+ "AND pers.coDocumentoIdentidad = :numeroDocumento ")
	List<PgimPersonaDTO> existePersona(@Param("idPersona") Long idAccidentado,
			@Param("idTipoDocumento") Long idTipoDocumento, @Param("numeroDocumento") String numeroDocumento);

	/**
	 * Permite obtener las personas responsables por instancia de proceso
	 * 
	 * @param idInstanciaProceso
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno, pers.noRazonSocial) "
			+ "FROM PgimPersona pers WHERE pers.esRegistro = '1' " 
			+ "AND pers.idPersona in ( "
			+ "select posi.pgimPersona.idPersona from PgimPersonalOsi posi where posi.esRegistro='1' "
			+ "and posi.idPersonalOsi in ( "
			+ "select equi.pgimPersonalOsi.idPersonalOsi from PgimEqpInstanciaPro equi where equi.esRegistro='1' "
			+ "and equi.flEsResponsable='1' and  equi.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
			+ ") " 
			+ ") ")
	List<PgimPersonaDTO> listarResponsablesXinstanciaProc(@Param("idInstanciaProceso") Long idInstanciaProceso);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.tipoDocIdentidad.idValorParametro, pers.tipoDocIdentidad.noValorParametro, "
			+ "pers.coDocumentoIdentidad, TRIM(UPPER(pers.noRazonSocial)), pers.noCorto, UPPER(pers.noPersona), UPPER(pers.apPaterno), UPPER(pers.apMaterno), "
			+ "pers.noPrefijoPersona, pers.tiSexo, " 
			+ "pers.feNacimiento, " 
			+ "pers.deRestriccion, "
			+ "pers.fuentePersonaNatural.idValorParametro, "
			+ "pers.deTelefono, pers.deTelefono2, pers.deCorreo, pers.deCorreo2, " 
			+ "ubig.idUbigeo, "
			+ "((SELECT TRIM(ubde.noUbigeo) FROM PgimUbigeo ubde WHERE ubde.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 2) || '0000') || ', ' || "
			+ "(SELECT TRIM(ubdi.noUbigeo) FROM PgimUbigeo ubdi where ubdi.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 4) || '00') || ', ' || "
			+ "TRIM(ubig.noUbigeo)), pers.diPersona, "
			+ "pers.flAfiliadoNtfccionElctrnca, pers.deCorreoNtfccionElctrnca, pers.feAfiliadoDesde, "
			+ "pers.cmNota, pers.flConsorcio " 
			+ " ) " 
			+ "FROM PgimPersona pers "
			+ "LEFT OUTER JOIN pers.pgimUbigeo ubig " 
			+ "WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.coClaveTexto = :DOIDE_DNI " + "AND pers.idPersona = :idPersona ")
	PgimPersonaDTO obtenerPersonalNaturalPorId(@Param("idPersona") Long idPersona, @Param("DOIDE_DNI") String DOIDE_DNI);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, " 
			+ "CASE "
			+ "WHEN pers.noRazonSocial IS NOT NULL THEN pers.noRazonSocial ELSE (pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) END, "
			+ "pers.noPersona, pers.apPaterno, pers.apMaterno ) " 
			+ "FROM PgimPersona pers "
			+ "WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.coClaveTexto = :DOIDE_DNI "

			+ "AND NOT EXISTS (SELECT 1 " 
			+ "FROM PgimContactoAgente coag " 
			+ "WHERE coag.esRegistro = '1' "
			+ "AND coag.pgimAgenteSupervisado.esRegistro = '1' "
			+ "AND coag.pgimAgenteSupervisado.idAgenteSupervisado = :idAgenteSupervisado "
			+ "AND coag.pgimPersona = pers) "

			+ "AND (:palabraClave IS NULL OR LOWER(pers.noRazonSocial || ' ' || pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) "
			+ "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
			+ " )")
	List<PgimPersonaDTO> listarPersonalNaturalPorNoRazonSocial(Long idAgenteSupervisado, String palabraClave, @Param("DOIDE_DNI") String DOIDE_DNI);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, " 
			+ "CASE "
			+ "WHEN pers.noRazonSocial IS NOT NULL THEN pers.noRazonSocial ELSE (pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) END, "
			+ "pers.noPersona, pers.apPaterno, pers.apMaterno ) " 
			+ "FROM PgimPersona pers "
			+ "WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.coClaveTexto = :DOIDE_DNI "

			+ "AND NOT EXISTS (SELECT 1 " 
			+ "FROM PgimContactoSuper coag " 
			+ "WHERE coag.esRegistro = '1' "
			+ "AND coag.pgimEmpresaSupervisora.esRegistro = '1' "
			+ "AND coag.pgimEmpresaSupervisora.idEmpresaSupervisora = :idEmpresaSupervisora "
			+ "AND coag.pgimPersona = pers) "

			+ "AND (:palabraClave IS NULL OR LOWER(pers.noRazonSocial || ' ' || pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) "
			+ "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
			+ " )")
	List<PgimPersonaDTO> listarPersonalNaturalPorNoRazonSocialSuper(Long idEmpresaSupervisora, String palabraClave, @Param("DOIDE_DNI") String DOIDE_DNI);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado("
			+ "pers.idPersona, pers.coDocumentoIdentidad, " 
			+ "(pers.coDocumentoIdentidad || ': ' || TRIM(pers.apPaterno) || ' ' || TRIM(pers.apMaterno) || ', ' || TRIM(pers.noPersona) ), "
			+ "pers.noPersona, pers.apPaterno, pers.apMaterno ) " 
			+ "FROM PgimPersona pers "
			+ "WHERE pers.esRegistro = '1' "
			+ "AND pers.tipoDocIdentidad.coClaveTexto = :DOIDE_DNI "

			+ "AND NOT EXISTS (SELECT 1 " 
			+ "FROM PgimPersonalOsi peosi " 
			+ "WHERE peosi.esRegistro = '1' "
			+ "AND peosi.pgimPersona = pers) "

			+ "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad || ' ' || pers.apPaterno || ' ' || pers.apMaterno || ' ' || pers.noPersona ) "
			+ "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
			+ " )")
	List<PgimPersonaDTO> listarPersonaNaturalPorPersonalOsi(String palabraClave, @Param("DOIDE_DNI") String DOIDE_DNI);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaDTOResultado(" 
			+ "pers.idPersona, " 
			+ "TRIM(UPPER(pers.noRazonSocial)) " 
			+ " ) " 
			+ "FROM PgimPersona pers "
			+ "WHERE pers.esRegistro = '1' " 
			+ "AND (CASE " 
			+ "WHEN pers.flConsorcio = '0' THEN '0' " 
			+ "WHEN pers.flConsorcio is null THEN '0' " 
			+ "END = '0') "
			+ "AND (:idPersona IS NULL OR pers.idPersona != :idPersona) "
			+ "AND TRIM(UPPER(pers.noRazonSocial)) = :noRazonSocial "
			+ " ")
	List<PgimPersonaDTO> existePersonaJuridica(@Param("idPersona") Long idPersona,
			@Param("noRazonSocial") String noRazonSocial);
    
}