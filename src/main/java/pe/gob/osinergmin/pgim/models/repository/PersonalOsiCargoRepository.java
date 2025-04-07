package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsiCargo;

import java.util.List;

/**
 * Tiene como nombre Cargos del personal de Osinergmin.
 * 
 * @descripción: Logica de negocio de la entidad Cargos del personal de Osinergmin.
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/01/2023
 * @fecha_de_ultima_actualización: 20/01/2023
 */
public interface PersonalOsiCargoRepository extends JpaRepository<PgimPersonalOsiCargo, Long> {

        /**
         * Me permite listar los cargos del personal de Osinergmin
         * 
         * @param idPersonalOsi
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTOResultado("
                        + "poc.idPersonalOsiCargo, po.idPersonalOsi, uo.idUnidadOrganica, poc.noCargo, "
                        + "poc.feInicio, poc.feFin, poc.flPrincipal, uo.coUnidadOrganica, uo.noUnidadOrganica, poc.nuExpedienteSiged, poc.coTipoDocumentoSiged, poc.nuDocumentoSiged "
                        + ") "
                        + "FROM PgimPersonalOsiCargo poc "
                        + "INNER JOIN poc.pgimPersonalOsi po "
                        + "INNER JOIN poc.pgimUnidadOrganica uo "
                        + "WHERE po.idPersonalOsi = :idPersonalOsi "
                        + "AND poc.esRegistro = '1' "
                        + "AND po.esRegistro = '1' "
                        + "AND uo.esRegistro = '1' "
                        + "ORDER BY poc.flPrincipal DESC, poc.feInicio DESC ")
        List<PgimPersonalOsiCargoDTO> listarPersonalOsiCargo(@Param("idPersonalOsi") Long idPersonalOsi);

        /**
         * Quiero obtener una lista de objetos de una tabla, pero solo quiero obtener
         * los campos que
         * necesito, así que creo un nuevo objeto con los campos que necesito y devuelvo
         * ese objeto.
         * 
         * @param idPersonalOsiCargo Largo
         * @return Una lista de objetos de tipo PgimPersonalOsiCargoDTOResultado
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTOResultado("
                        + "poc.idPersonalOsiCargo, po.idPersonalOsi, uo.idUnidadOrganica, poc.noCargo, "
                        + "poc.feInicio, poc.feFin, poc.flPrincipal, uo.coUnidadOrganica, uo.noUnidadOrganica, poc.nuExpedienteSiged, poc.coTipoDocumentoSiged, poc.nuDocumentoSiged "
                        + ") "
                        + "FROM PgimPersonalOsiCargo poc "
                        + "INNER JOIN poc.pgimPersonalOsi po "
                        + "INNER JOIN poc.pgimUnidadOrganica uo "
                        + "WHERE poc.idPersonalOsiCargo = :idPersonalOsiCargo "
                        + "AND poc.esRegistro = '1' "
                        + "AND po.esRegistro = '1' "
                        + "AND uo.esRegistro = '1' ")
        PgimPersonalOsiCargoDTO obtenerPersonalOsiCargoPorId(@Param("idPersonalOsiCargo") Long idPersonalOsiCargo);

        /**
         * Permite mostrar el número de cargos principales por cada personal de
         * Osinergmin
         * 
         * @param idPersonalOsi
         * @return
         */
        @Query("SELECT "
                        + "COUNT(*) "
                        + "FROM PgimPersonalOsiCargo poc "
                        + "INNER JOIN poc.pgimPersonalOsi po "
                        + "INNER JOIN poc.pgimUnidadOrganica uo "
                        + "WHERE po.idPersonalOsi = :idPersonalOsi "
                        + "AND poc.flPrincipal = '1' "
                        + "AND poc.esRegistro = '1' "
                        + "AND po.esRegistro = '1' "
                        + "AND uo.esRegistro = '1' ")
        Integer cantidadCargoPrincipalPorPersonalOsi(@Param("idPersonalOsi") Long idPersonalOsi);

        /**
         * Permite obtener las encargaturas que tiene un personal del osinegmin según la unidad organica
         * y la vigencia de su encargatura
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTOResultado("
                + "poc.idPersonalOsiCargo, po.idPersonalOsi, uo.idUnidadOrganica, poc.noCargo, "
                + "poc.feInicio, poc.feFin, poc.flPrincipal, uo.coUnidadOrganica, uo.noUnidadOrganica, poc.nuExpedienteSiged, poc.coTipoDocumentoSiged, poc.nuDocumentoSiged "
                + ") "
                + "FROM PgimPersonalOsiCargo poc "
                + "INNER JOIN poc.pgimPersonalOsi po "
                + "INNER JOIN PgimPersona pers ON po.pgimPersona = pers "
                + "INNER JOIN poc.pgimUnidadOrganica uo "
                + "WHERE pers.idPersona = :idPersonal "
                + "AND TRUNC( SYSDATE ) >= TRUNC( poc.feInicio ) "
                + "AND (poc.flPrincipal = '1' OR TRUNC( poc.feFin ) >= TRUNC( SYSDATE ) ) "
                + "AND uo.idUnidadOrganica = :idUnidadOrganica "
                + "AND poc.esRegistro = '1' "
                + "AND po.esRegistro = '1' "
                + "AND uo.esRegistro = '1' "
                + "AND pers.esRegistro = '1' "
                + "ORDER BY poc.flPrincipal DESC, poc.feFin DESC "
                )
        List<PgimPersonalOsiCargoDTO> listarPersonalOsiEncargaturas(@Param("idPersonal") Long idPersonal, @Param("idUnidadOrganica") Long idUnidadOrganica);
}
