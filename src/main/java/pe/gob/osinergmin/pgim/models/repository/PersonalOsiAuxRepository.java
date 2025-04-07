package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonaosiAux;

import java.util.List;

/**
 * En ésta interface PersonalOsiAuxRepository esta conformado pos sus metodos de listar
 * personal osi.
 * 
 * @descripción: Lógica de negocio de la entidad Personal osi
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface PersonalOsiAuxRepository extends JpaRepository<PgimPersonaosiAux, Long> {

    /**
     * Permite obtener la lista de personas del Osinergmin que coincidan con los criterios la palabra filtro.
     * @param palabra Palabra clave empleada para realizar la búsqueda por aproximación. 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTOResultado(aux.idPersonalOsiAux, aux.idPersonalOsiAux, aux.pgimPersona.idPersona, "
            + "aux.noUsuario, aux.idTipoDocIdentidad, aux.noTipoDocIdentidad, "
            + "aux.coDocumentoIdentidad, aux.noPersona, aux.apPaterno, "
            + "aux.apMaterno, aux.noPersona || ' ' || aux.apPaterno || ' ' || aux.apMaterno) "
            + "FROM PgimPersonaosiAux aux "            
            + "WHERE aux.pgimPersonalOsi.flActivo = '1' AND (:palabraClave IS NULL OR LOWER(aux.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
            + "OR LOWER(aux.noUsuario) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(aux.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(aux.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(aux.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "            
            + ") "   
            + "ORDER BY aux.noPersona, aux.apPaterno, aux.apMaterno"
            )
    List<PgimPersonaosiAuxDTO> listarPersonalOsi(@Param("palabraClave") String palabra);
    
    
    
    /**
     * Permite obtener la lista de personas del Osinergmin que coincidan con los criterios la palabra filtro,
     * y que no se dupliquen con un rol, en el equipo de la instancia de un proceso
     * @param palabra Palabra clave empleada para realizar la búsqueda por aproximación.
     * @param idInstanciaProceso
     * @param idRolProceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTOResultado(aux.idPersonalOsiAux, aux.idPersonalOsiAux, aux.pgimPersona.idPersona, "
            + "aux.noUsuario, aux.idTipoDocIdentidad, aux.noTipoDocIdentidad, "
            + "aux.coDocumentoIdentidad, aux.noPersona, aux.apPaterno, "
            + "aux.apMaterno, aux.noPersona || ' ' || aux.apPaterno || ' ' || aux.apMaterno) "
            + "FROM PgimPersonaosiAux aux "            
            + "WHERE aux.pgimPersonalOsi.flActivo = '1' " 
            + "AND (:palabraClave IS NULL OR LOWER(aux.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
            + "OR LOWER(aux.noUsuario) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(aux.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(aux.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + "OR LOWER(aux.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + ") "
            + "AND aux.pgimPersonalOsi.idPersonalOsi not in ( "
            + "select case when eqp.pgimPersonalOsi.idPersonalOsi is null then 0 else eqp.pgimPersonalOsi.idPersonalOsi end "            
            + "from PgimEqpInstanciaPro eqp "
            + "where eqp.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            + "and eqp.pgimRolProceso.idRolProceso = :idRolProceso "
            + "and eqp.esRegistro = '1') "
            + "ORDER BY aux.noCompletoPersona " )
    List<PgimPersonaosiAuxDTO> listarPersonalOsiSinDuplicadosXrol(@Param("palabraClave") String palabra,@Param("idInstanciaProceso") Long idInstanciaProceso,@Param("idRolProceso") Long idRolProceso);

    /**
     * Permite obtener la lista de personas del Osinergmin que coincidan con los criterios la palabra filtro.
     * @param nombreUsuarioWindows
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTOResultado(aux.idPersonalOsiAux, aux.idPersonalOsiAux, aux.pgimPersona.idPersona, "
            + "aux.noUsuario, aux.idTipoDocIdentidad, aux.noTipoDocIdentidad, "
            + "aux.coDocumentoIdentidad, aux.noPersona, aux.apPaterno, "
            + "aux.apMaterno, aux.noPersona || ' ' || aux.apPaterno || ' ' || aux.apMaterno) "
            + "FROM PgimPersonaosiAux aux "            
            + "WHERE LOWER(aux.noUsuario) = LOWER(:nombreUsuarioWindows) "
            + "ORDER BY aux.noPersona, aux.apPaterno, aux.apMaterno"
            )
    PgimPersonaosiAuxDTO obtenerPersonalOsiNombreUsuarioWindows(String nombreUsuarioWindows);

}