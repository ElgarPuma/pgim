package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonaConsorcio;

/**
 * @descripción: Logica de negocio de la entidad persona consorcio.
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
public interface PersonaConsorcioRepository extends JpaRepository<PgimPersonaConsorcio, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTOResultado("
            + "cons.idPersonaConsorcio, cons.personaJuridicaConsorcio.idPersona, cons.pgimPersona.idPersona, " 
            + "(cons.pgimPersona.coDocumentoIdentidad || ': ' || upper(cons.pgimPersona.noRazonSocial)), "
            + "upper(cons.pgimPersona.noRazonSocial), cons.flEsPrincipal "
            + ") "
            + "FROM PgimPersonaConsorcio cons " 
            + "WHERE cons.esRegistro = '1' "
            + "AND cons.personaJuridicaConsorcio.idPersona = :idPersonaJuridicaConsorcio "
        )
    Page<PgimPersonaConsorcioDTO> listarConsorcios(@Param("idPersonaJuridicaConsorcio") Long idPersonaJuridicaConsorcio, Pageable paginador);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTOResultado("
            + "cons.idPersonaConsorcio, cons.personaJuridicaConsorcio.idPersona, cons.pgimPersona.idPersona, " 
            + "(cons.pgimPersona.coDocumentoIdentidad || ': ' || upper(cons.pgimPersona.noRazonSocial)), "
            + "upper(cons.pgimPersona.noRazonSocial), cons.flEsPrincipal "
            + ") "
            + "FROM PgimPersonaConsorcio cons " 
            + "WHERE cons.idPersonaConsorcio = :idPersonaConsorcio " 
            + "AND cons.esRegistro = '1'")
    PgimPersonaConsorcioDTO obtenerConsorcioPorId(@Param("idPersonaConsorcio") Long idPersonaConsorcio);


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTOResultado("
            + "pers.idPersona, (pers.coDocumentoIdentidad || ': ' || upper(pers.noRazonSocial)) " 
            + ") "
            + "FROM PgimPersona pers " 
            + "WHERE pers.esRegistro = '1' "
            + "AND (CASE " 
            + "WHEN pers.flConsorcio = '0' THEN '0' " 
            + "WHEN pers.flConsorcio is null THEN '0' "
            + "END = '0') "
            + "AND NOT EXISTS (SELECT 1 "
            + "FROM PgimPersonaConsorcio peco " 
            + "WHERE peco.esRegistro = '1' AND peco.personaJuridicaConsorcio.esRegistro = '1' " 
            + "AND peco.personaJuridicaConsorcio.idPersona = :idPersona " 
            + "AND peco.pgimPersona = pers " 
            + ") "
            + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
            + "OR LOWER(pers.noRazonSocial) LIKE LOWER(CONCAT('%', :palabraClave, '%')) ) "
            
            )
    List<PgimPersonaConsorcioDTO> listarPorPersonaJuridica(@Param("idPersona") Long idPersona, String palabraClave);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTOResultado( " 
            + "peco.idPersonaConsorcio ) "
            + "FROM PgimPersonaConsorcio peco " 
            + "WHERE peco.esRegistro = '1' "
            + "AND peco.pgimPersona.idPersona = :idPersona ")
    List<PgimPersonaConsorcioDTO> listarConsorciosPorPersona(@Param("idPersona") Long idPersona);
    
    /**
     * Permite obtener la lista de personas del consorcio que fungen el rol de integrante principal. Se debe resaltar
     * que no debería existir más de una persona como principal.
     * @param idPersonaConsorcio
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTOResultado( " 
            + "peco.idPersonaConsorcio, peco.personaJuridicaConsorcio.idPersona, peco.pgimPersona.idPersona, " 
            + "(peco.pgimPersona.coDocumentoIdentidad || ': ' || upper(peco.pgimPersona.noRazonSocial)), "
            + "UPPER(peco.pgimPersona.noRazonSocial), peco.flEsPrincipal "
            + ") "
            + "FROM PgimPersonaConsorcio peco " 
            + "WHERE peco.esRegistro = '1' "
            + "AND peco.flEsPrincipal = '1' "
            + "AND peco.personaJuridicaConsorcio.idPersona = :idPersonaConsorcio ")
    List<PgimPersonaConsorcioDTO> obtenerIntegrantePrincipalConsorcio(@Param("idPersonaConsorcio") Long idPersonaConsorcio);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTOResultado("
            + "cons.idPersonaConsorcio, cons.personaJuridicaConsorcio.idPersona, cons.pgimPersona.idPersona, " 
            + "(cons.pgimPersona.coDocumentoIdentidad || ': ' || upper(cons.pgimPersona.noRazonSocial)), "
            + "upper(cons.pgimPersona.noRazonSocial), cons.flEsPrincipal "
            + ") "
            + "FROM PgimPersonaConsorcio cons " 
            + "WHERE cons.personaJuridicaConsorcio.idPersona = :idPersona " 
            + "AND cons.flEsPrincipal = '1' "
            + "AND cons.esRegistro = '1'")
    PgimPersonaConsorcioDTO obtenerConsorcioPrincipal(@Param("idPersona") Long idPersona);

}
