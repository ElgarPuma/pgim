package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInvolucradoSuperv;

import java.util.List;

/**
 * Ésta interface InvolucradoSupervRepository incluye los metodos que nos permitirá obtener, listar y filtrar las propiedades
 * de la persona involucrada de una supervision.
 * 
 * @descripción: Logica de negocio de la entidad Involucrada superv
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 29/06/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface InvolucradoSupervRepository extends JpaRepository<PgimInvolucradoSuperv, Long> {

    /**
     * Permite listar la Persona involucrada de la supervision
     *
     * @param idValorParametro
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTOResultado("
            + "insup.idInvolucradoSuperv, insup.pgimSupervision.idSupervision, insup.pgimPersona.idPersona, "
            + "valp.idValorParametro, insup.tipoInvolucrado.idValorParametro, "
            + "insup.tipoPrefijoInvolucrado.idValorParametro, insup.deCargo, "
            + "insup.tipoInvolucrado.noValorParametro, insup.pgimPersona.coDocumentoIdentidad, insup.pgimPersona.tipoDocIdentidad.noValorParametro,"
            + "insup.pgimPersona.noPersona || ' ' || insup.pgimPersona.apPaterno || ' ' || insup.pgimPersona.apMaterno, "
            + "insup.tipoPrefijoInvolucrado.noValorParametro ) " 
            + "FROM PgimInvolucradoSuperv insup, PgimValorParametro valp "
            + "WHERE insup.tipoActaInvolucrado = valp " 
            + "AND insup.esRegistro = '1' "
            + "AND insup.pgimSupervision.idSupervision = :idSupervision "
            + "AND (:idValorParametro IS NULL OR valp.idValorParametro = :idValorParametro) " 
            + "ORDER BY insup.idInvolucradoSuperv ")
    List<PgimInvolucradoSupervDTO> listarInvolucradoSupervision(@Param("idSupervision") Long idSupervision, @Param("idValorParametro") Long idValorParametro);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTOResultado("
            + "insup.idInvolucradoSuperv, insup.pgimSupervision.idSupervision, insup.pgimPersona.idPersona, "
            + "insup.tipoActaInvolucrado.idValorParametro, insup.tipoInvolucrado.idValorParametro, "
            + "insup.tipoPrefijoInvolucrado.idValorParametro, insup.deCargo, "
            + "insup.tipoInvolucrado.noValorParametro, "
            + "insup.pgimPersona.coDocumentoIdentidad || ' ' || insup.pgimPersona.noPersona || ' ' || insup.pgimPersona.apPaterno || ' ' || insup.pgimPersona.apMaterno, "
            + "insup.pgimPersona.tipoDocIdentidad.noValorParametro, "
            + "insup.tipoPrefijoInvolucrado.noValorParametro ) " 
            + "FROM PgimInvolucradoSuperv insup "
            + "WHERE insup.esRegistro = '1' AND insup.idInvolucradoSuperv = :idInvolucradoSuperv")
    PgimInvolucradoSupervDTO obtenerInvolucradoSuperv(@Param("idInvolucradoSuperv") Long idInvolucradoSuperv);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTOResultado(" 
        + "pers.idPersona, "
        + "pers.coDocumentoIdentidad ||' '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno ) "
        + "FROM PgimPersona pers " 
        + "WHERE pers.esRegistro = '1' "
        + "AND NOT pers.tipoDocIdentidad.coClaveTexto = :DOIDE_RUC "
        + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad ||' '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) "
        + "LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
    List<PgimInvolucradoSupervDTO> listarPorInvolucradoAiAs(@Param("palabraClave") String palabraClave, @Param("DOIDE_RUC") String DOIDE_RUC);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTOResultado(" 
            + "insup.idInvolucradoSuperv, insup.pgimSupervision.idSupervision, insup.pgimPersona.idPersona, insup.tipoActaInvolucrado.idValorParametro, " 
            + "insup.pgimPersona.coDocumentoIdentidad ||' '|| insup.pgimPersona.noPersona ||' '|| insup.pgimPersona.apPaterno ||' '|| insup.pgimPersona.apMaterno ) "
            + "FROM PgimInvolucradoSuperv insup "
            + "WHERE insup.esRegistro = '1' "
            + "AND insup.pgimPersona.esRegistro = '1' " 
            + "AND insup.tipoActaInvolucrado.idValorParametro = :idValorParametro "
            + "AND insup.pgimSupervision.idSupervision = :idSupervision "
            + "AND LOWER(insup.pgimPersona.coDocumentoIdentidad ||' '|| insup.pgimPersona.noPersona ||' '|| insup.pgimPersona.apPaterno ||' '|| insup.pgimPersona.apMaterno) = LOWER(:coDocumentoIdentidad)")
    List<PgimInvolucradoSupervDTO> existeRepresentanteAi(@Param("idValorParametro") Long idValorParametro, @Param("idSupervision") Long idSupervision, 
    		@Param("coDocumentoIdentidad") String coDocumentoIdentidad);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTOResultado("
    		+ "inv.pgimPersona.noPersona,inv.pgimPersona.apPaterno,inv.pgimPersona.apMaterno, "
    		+ "inv.pgimPersona.coDocumentoIdentidad, inv.deCargo, inv.tipoPrefijoInvolucrado.noValorParametro ) " 
            + "FROM PgimInvolucradoSuperv inv " 
            + "WHERE inv.esRegistro = '1' "
            + "AND inv.tipoInvolucrado.idValorParametro <> 311 "
            + "AND inv.tipoActaInvolucrado.idValorParametro = :tipoActaInvolucrado "
            + "AND inv.pgimSupervision.idSupervision = :idSupervision ") 
    List<PgimInvolucradoSupervDTO> obtenerRepresentantesAgenteSupervisado(@Param("idSupervision") Long idSupervision,
    		@Param("tipoActaInvolucrado") Long tipoActaInvolucrado);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInvolucradoSupervDTOResultado("
    		+ "inv.pgimPersona.noPersona,inv.pgimPersona.apPaterno,inv.pgimPersona.apMaterno, "
    		+ "inv.pgimPersona.coDocumentoIdentidad, inv.deCargo, inv.tipoPrefijoInvolucrado.noValorParametro ) " 
            + "FROM PgimInvolucradoSuperv inv " 
            + "WHERE inv.esRegistro = '1' "
            + "AND inv.tipoInvolucrado.idValorParametro = 311 "
            + "AND inv.tipoActaInvolucrado.idValorParametro = :tipoActaInvolucrado "
            + "AND inv.pgimSupervision.idSupervision = :idSupervision " 
            + "ORDER BY inv.idInvolucradoSuperv ") 
    List<PgimInvolucradoSupervDTO> obtenerRepresentantesTrabajadores(@Param("idSupervision") Long idSupervision,
    		@Param("tipoActaInvolucrado") Long tipoActaInvolucrado);
}
