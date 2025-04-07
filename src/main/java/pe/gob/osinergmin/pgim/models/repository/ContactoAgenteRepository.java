package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoAgente;

/** 
 * @descripción: Lógica de negocio de la entidad Contacto agente
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/11/2020
 */

public interface ContactoAgenteRepository extends JpaRepository<PgimContactoAgente, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTOResultado( "
            + "coag.idContactoAgente, coag.pgimAgenteSupervisado.idAgenteSupervisado, coag.pgimPersona.idPersona, coag.noCargo, "
            + "coag.pgimPersona.noPersona, coag.pgimPersona.apPaterno, coag.pgimPersona.apMaterno, "
            + "coag.pgimPersona.deTelefono, coag.pgimPersona.deCorreo, coag.pgimPersona.noRazonSocial "
            + ") "
            + "FROM PgimContactoAgente coag "
            + "WHERE coag.esRegistro = '1' "
            + "AND coag.pgimAgenteSupervisado.idAgenteSupervisado = :idAgenteSupervisado ")
    Page<PgimContactoAgenteDTO> listar(@Param("idAgenteSupervisado") Long idAgenteSupervisado,
            Pageable paginador);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTOResultado( "
            + "coag.idContactoAgente, coag.pgimAgenteSupervisado.idAgenteSupervisado, coag.pgimPersona.idPersona, coag.noCargo "
            + ") "
            + "FROM PgimContactoAgente coag "
            + "WHERE coag.esRegistro = '1' "
            + "AND coag.idContactoAgente = :idContactoAgente ")
    PgimContactoAgenteDTO obtenerContactoAgentePorId(@Param("idContactoAgente") Long idContactoAgente);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContactoAgenteDTOResultado( "
            + "coag.idContactoAgente ) "
            + "FROM PgimContactoAgente coag "
            + "WHERE coag.esRegistro = '1' "
            + "AND coag.pgimPersona.idPersona = :idPersona ")
    List<PgimContactoAgenteDTO> listarContactoAgentePorPersona(@Param("idPersona") Long idPersona);
}
