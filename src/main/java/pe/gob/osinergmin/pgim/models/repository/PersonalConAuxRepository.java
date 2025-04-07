package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonaconAux;

import java.util.List;

/**
 * En ésta interface PersonalConAuxRepository esta conformado pos sus metodos de listar
 * personal contrato.
 * 
 * @descripción: Lógica de negocio de la entidad Personal contrato
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface PersonalConAuxRepository extends JpaRepository<PgimPersonaconAux, Long>{

	
	/**
     * Permite obtener la lista de personas de la empresa supervisora 
     * @param idInstanciaProceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTOResultado(aux.idPersonalConAux, aux.pgimPersonalContrato.idPersonalContrato, aux.pgimPersona.idPersona, "
            + "aux.noUsuario, aux.idTipoDocIdentidad, aux.noTipoDocIdentidad, "
            + "aux.coDocumentoIdentidad, aux.noPersona, aux.apPaterno, "
            + "aux.apMaterno, aux.noCompletoPersona,aux.pgimContrato.idContrato) "
            + "FROM PgimPersonaconAux aux "            
            + "WHERE aux.pgimContrato.idContrato in ( "
            + "select conc.pgimContrato.idContrato from PgimSupervision sup, PgimConsumoContra conc "
            + "where sup.idSupervision=conc.pgimSupervision.idSupervision "
            + "and sup.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso) "
            + "AND aux.pgimPersonalContrato.idPersonalContrato not in ( "
            + "select case when eqp.pgimPersonalContrato.idPersonalContrato is null then 0 else eqp.pgimPersonalContrato.idPersonalContrato end "            
            + "from PgimEqpInstanciaPro eqp "
            + "where eqp.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            + "and eqp.pgimRolProceso.idRolProceso = :idRolProceso "
            + "and eqp.esRegistro = '1') "
            + "ORDER BY aux.noCompletoPersona"
            )
    List<PgimPersonaconAuxDTO> listarPersonalContratoSinDuplicadosXrol(@Param("idInstanciaProceso") Long idInstanciaProceso,@Param("idRolProceso") Long idRolProceso);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTOResultado(aux.idPersonalConAux, aux.pgimPersonalContrato.idPersonalContrato, aux.pgimPersona.idPersona, "
            + "aux.noUsuario, aux.idTipoDocIdentidad, aux.noTipoDocIdentidad, "
            + "aux.coDocumentoIdentidad, aux.noPersona, aux.apPaterno, "
            + "aux.apMaterno, aux.noCompletoPersona,aux.pgimContrato.idContrato) "
            + "FROM PgimPersonaconAux aux "            
            + "WHERE aux.pgimContrato.idContrato = :idContrato "
            + "AND aux.pgimPersonalContrato.idPersonalContrato NOT IN ( "
            + "SELECT CASE WHEN eqp.pgimPersonalContrato.idPersonalContrato IS NULL THEN 0 ELSE eqp.pgimPersonalContrato.idPersonalContrato END "
            + "FROM PgimEqpInstanciaPro eqp "
            + "where eqp.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            + "AND eqp.pgimRolProceso.idRolProceso = :idRolProceso "
            + "AND eqp.esRegistro = '1') "
            + "ORDER BY aux.noCompletoPersona"
            )
    List<PgimPersonaconAuxDTO> listarPersonalContratoSinDuplicadosXrolContrato(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idContrato") Long idContrato, @Param("idRolProceso") Long idRolProceso);
        
    /**
     * Permite obtener la lista de personas de la empresa supervisora x nombre de usaurio 
     * @param noUsuario. 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTOResultado(aux.idPersonalConAux, aux.pgimPersonalContrato.idPersonalContrato, aux.pgimPersona.idPersona, "
            + "aux.noUsuario, aux.idTipoDocIdentidad, aux.noTipoDocIdentidad, "
            + "aux.coDocumentoIdentidad, aux.noPersona, aux.apPaterno, "
            + "aux.apMaterno, aux.noCompletoPersona,aux.pgimContrato.idContrato) "
            + "FROM PgimPersonaconAux aux "            
            + "WHERE "
            + "aux.noUsuario = :noUsuario "            
            + "ORDER BY aux.noCompletoPersona"
            )
    List<PgimPersonaconAuxDTO> listarPersonalContratoXusuario(@Param("noUsuario") String noUsuario);

    
}
