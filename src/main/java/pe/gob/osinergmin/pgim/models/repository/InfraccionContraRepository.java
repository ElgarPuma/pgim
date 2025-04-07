package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionContra;

/**
 * @descripción: Lógica de negocio de la entidad InfraccionContra (Contratista responsable de la infracción)
 * 
 * @author promero
 * @version: 1.0
 * @fecha_de_creación: 27/04/2023
 * @fecha_de_ultima_actualización: 27/04/2023
 *
 */
@Repository
public interface InfraccionContraRepository extends JpaRepository<PgimInfraccionContra, Long> {
	
	/**
	 * Permite listar los contratistass responsables de una infracción
	 * 
	 * @param idInfraccion
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTOResultado( "
			+ "ic.idInfraccionContra, ic.pgimInfraccion.idInfraccion, ic.infraccionContraOrigen.idInfraccionContra, "
			+ "ic.pgimPersona.idPersona, ic.flVigente, per.noRazonSocial, per.coDocumentoIdentidad ) "
            + "FROM PgimInfraccionContra ic "
			+ "INNER JOIN ic.pgimPersona per "
            + "WHERE ic.esRegistro = '1' "
            + "AND ic.pgimInfraccion.idInfraccion = :idInfraccion "
            + "ORDER BY per.noRazonSocial "
            )
	List<PgimInfraccionContraDTO> listarContratistaByIdInfraccion(@Param("idInfraccion") Long idInfraccion);
	
	/**
	 * Permite listar contratistas responsables de infracción de una instancia de proceso determinada
	 * 
	 * @param idInstanciaProceso
	 * @return
	 */
	@Query("SELECT DISTINCT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTOResultado( "
			+ "per.idPersona, per.noRazonSocial, per.coDocumentoIdentidad "
			+ ") "
            + "FROM PgimInfraccionContra ic "
			+ "INNER JOIN ic.pgimPersona per "
			+ "INNER JOIN ic.pgimInfraccion inf "
			+ "INNER JOIN inf.pgimInstanciaPaso ipas "
            + "WHERE 1=1 "
            + "AND ic.esRegistro = '1' AND ic.flVigente = '1' "
            + "AND inf.esRegistro = '1' AND inf.flVigente = '1' "
            + "AND ipas.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            )
	List<PgimInfraccionContraDTO> listarContratistaByIdInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);
	
	/**
	 * Permite obtener un contratista por su id contratista origen y su id infracción
	 * 
	 * @param idInfraccion
	 * @param idInfraccionContraOrigen
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTOResultado( "
			+ "ic.idInfraccionContra, ic.pgimInfraccion.idInfraccion, ic.infraccionContraOrigen.idInfraccionContra, "
			+ "ic.pgimPersona.idPersona, ic.flVigente ) "
            + "FROM PgimInfraccionContra ic "
            + "WHERE ic.esRegistro = '1' "
            + "AND ic.pgimInfraccion.idInfraccion = :idInfraccion "
            + "AND ic.infraccionContraOrigen.idInfraccionContra = :idInfraccionContraOrigen "
            )
	PgimInfraccionContraDTO obtenerContratistaByIdContratistaOrigen(
			@Param("idInfraccion") Long idInfraccion, 
			@Param("idInfraccionContraOrigen") Long idInfraccionContraOrigen);

}
