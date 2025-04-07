package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioEntidad;

/**
 * @descripción: Consultas de la entidad BiblioEntidad
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 19/12/2023
 *
 */
@Repository
public interface BiblioEntidadRepository extends JpaRepository<PgimBiblioEntidad, Long> {

	/**
	 * Permite listar los registros biblio-entidad por Id documento, se muestran los campos básicos
	 * 
	 * @param idBiblioDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTOResultado("
			+ "bent.idBiblioEntidad, bent.pgimEntidadNegocio.idEntidadNegocio, bent.pgimBiblioDocumento.idBiblioDocumento, "
			+ "bent.idRegistroEntidadNegocio "
			+ ") "
			+ "FROM PgimBiblioEntidad bent "
			+ "WHERE 1 = 1 "
			+ "AND bent.esRegistro = '1' "
			+ "AND bent.pgimBiblioDocumento.idBiblioDocumento = :idBiblioDocumento "
		)
	List<PgimBiblioEntidadDTO> listarBiblioEntidadPorIddocBasic(@Param("idBiblioDocumento") Long idBiblioDocumento);
	
	/**
	 * Permite listar los registros biblio-entidad por Id documento
	 * 
	 * @param idBiblioDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTOResultado("
		+ "bent.idBiblioEntidad, ent.idEntidadNegocio, bdoc.idBiblioDocumento, "
		+ "bent.idRegistroEntidadNegocio, ent.deEtiquetaTablaNegocio, "
		+ "(CASE "
		+ "WHEN ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO THEN (per.coDocumentoIdentidad || ' - ' || per.noRazonSocial) "
		+ "WHEN ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA THEN (um.coUnidadMinera || ' - ' || um.noUnidadMinera) "
		+ "WHEN ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO THEN (compe.coComponente || ' - ' || compe.noComponente) "
		+ "END) "
		+ ") "
		+ "FROM PgimBiblioDocumento bdoc "
		+ "INNER JOIN PgimBiblioEntidad bent ON (bdoc.idBiblioDocumento = bent.pgimBiblioDocumento.idBiblioDocumento) "
		+ "INNER JOIN PgimEntidadNegocio ent ON (bent.pgimEntidadNegocio.idEntidadNegocio = ent.idEntidadNegocio) "
		+ "LEFT JOIN PgimAgenteSupervisado asup ON (bent.idRegistroEntidadNegocio = asup.idAgenteSupervisado AND ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO AND asup.esRegistro = '1') "
		+ "LEFT JOIN PgimPersona per ON (asup.pgimPersona.idPersona = per.idPersona) "
		+ "LEFT JOIN PgimUnidadMinera um ON (bent.idRegistroEntidadNegocio = um.idUnidadMinera AND ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA AND um.esRegistro = '1' ) "
		+ "LEFT JOIN PgimComponenteMinero compe ON (bent.idRegistroEntidadNegocio = compe.idComponenteMinero AND ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO AND compe.esRegistro = '1') "
		+ "WHERE bdoc.idBiblioDocumento = :idBiblioDocumento "
		+ "AND bdoc.esRegistro = '1' "
		+ "AND bent.esRegistro = '1' "
	)
	List<PgimBiblioEntidadDTO> listaBiblioEntidadPorIddoc(@Param("idBiblioDocumento") Long idBiblioDocumento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTOResultado("
		+ "agsu.idAgenteSupervisado, pers.coDocumentoIdentidad || ' - ' || pers.noRazonSocial) "
		+ "FROM PgimAgenteSupervisado agsu, PgimPersona pers WHERE agsu.esRegistro = '1' AND agsu.pgimPersona = pers "
		+ "AND (?1 IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', ?1, '%'))  "
		+ "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', ?1, '%')) ) ")
    List<PgimBiblioEntidadDTO> listarAgenteSuperPorPalabraClave(String palabraClave);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTOResultado("
	+ "umin.idUnidadMinera, umin.coUnidadMinera || ' - ' || umin.noUnidadMinera) "
	+ "FROM PgimUnidadMinera umin "
	+ "WHERE umin.esRegistro = '1' AND (:palabraClave IS NULL OR LOWER(umin.coUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
	+ "OR LOWER(umin.noUnidadMinera) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) " 
	+ "ORDER BY umin.noUnidadMinera")
    List<PgimBiblioEntidadDTO> listarUMPorPalabraClave(@Param("palabraClave") String palabraClave);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTOResultado( "
            + "comi.idComponenteMinero, comi.coComponente || ' - ' || comi.noComponente " 
            + " ) " 
            + "FROM PgimComponenteMinero comi "
            + "INNER JOIN comi.tipoComponenteMinero tico " 
            + "WHERE 1 = 1 "
            + "AND comi.esRegistro = '1' "
            + "AND (:palabraClave IS NULL OR LOWER(comi.coComponente) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
            + "OR LOWER(comi.noComponente) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) "
            + "ORDER BY comi.noComponente"
            + " ")
    List<PgimBiblioEntidadDTO> listarComponenteMineroPorPalabraClave(@Param("palabraClave") String palabraClave);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioEntidadDTOResultado("
		+ "bent.idBiblioEntidad, ent.idEntidadNegocio, bdoc.idBiblioDocumento, "
		+ "bent.idRegistroEntidadNegocio, ent.deEtiquetaTablaNegocio, "
		+ "(CASE "
		+ "WHEN ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO THEN (per.coDocumentoIdentidad || ' - ' || per.noRazonSocial) "
		+ "WHEN ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA THEN (um.coUnidadMinera || ' - ' || um.noUnidadMinera) "
		+ "WHEN ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO THEN (compe.coComponente || ' - ' || compe.noComponente) "
		+ "END) "
		+ ") "
		+ "FROM PgimBiblioDocumento bdoc "
		+ "INNER JOIN PgimBiblioEntidad bent ON (bdoc.idBiblioDocumento = bent.pgimBiblioDocumento.idBiblioDocumento) "
		+ "INNER JOIN PgimEntidadNegocio ent ON (bent.pgimEntidadNegocio.idEntidadNegocio = ent.idEntidadNegocio) "
		+ "LEFT JOIN PgimAgenteSupervisado asup ON (bent.idRegistroEntidadNegocio = asup.idAgenteSupervisado AND ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO AND asup.esRegistro = '1') "
		+ "LEFT JOIN PgimPersona per ON (asup.pgimPersona.idPersona = per.idPersona) "
		+ "LEFT JOIN PgimUnidadMinera um ON (bent.idRegistroEntidadNegocio = um.idUnidadMinera AND ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA AND um.esRegistro = '1' ) "
		+ "LEFT JOIN PgimComponenteMinero compe ON (bent.idRegistroEntidadNegocio = compe.idComponenteMinero AND ent.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO AND compe.esRegistro = '1') "
		+ "WHERE bent.idBiblioEntidad = :idBiblioEntidad "
		+ "AND bdoc.esRegistro = '1' "
		+ "AND bent.esRegistro = '1' "
		+ "AND ent.esRegistro = '1' "
	)
	PgimBiblioEntidadDTO obtenerBiblioEntidadPorId(@Param("idBiblioEntidad") Long idBiblioEntidad);

}
