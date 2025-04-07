package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;

import java.util.List;

/**
 * En ésta interface PersonalContratoRepository esta conformado pos sus metodos
 * de obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Personal contrato
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
@Repository
public interface PersonalContratoRepository extends JpaRepository<PgimPersonalContrato, Long> {

        /**
         * Permite obtener la lista de personal del Contrato en base al nombre del
         * usuario windows.
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado("
                        + "peco.idPersonalContrato, peco.pgimPersona.idPersona, peco.noUsuario, "
                        + "peco.coUsuarioSiged) " 
                        + "FROM PgimPersonalContrato peco "
                        + "WHERE peco.noUsuario = :noUsuario " 
                        + "AND peco.esRegistro = '1'")
        List<PgimPersonalContratoDTO> obtenerPersonalContratoPorUsuario(@Param("noUsuario") String noUsuario);

        /**
         * Permite obtener la lista de personal del Contrato en base al nombre del
         * usuario windows.
         * @param noUsuario Nombre de usuario Windows.
         * @param idContrato Identificador interno del contrato.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado("
                        + "peco.idPersonalContrato, peco.pgimPersona.idPersona, peco.noUsuario, "
                        + "peco.coUsuarioSiged) " 
                        + "FROM PgimPersonalContrato peco "
                        + "WHERE peco.noUsuario = :noUsuario " 
                        + "AND peco.pgimContrato.idContrato = :idContrato " 
                        + "AND peco.esRegistro = '1'")        
        List<PgimPersonalContratoDTO> obtenerPersonalContratoPorUsuario(String noUsuario, Long idContrato);

        /**
         * Permite listar el personal del Contrato asociado a un Contrato
         *
         * @param idContrato
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado("
                        + "peco.idPersonalContrato, peco.pgimContrato.idContrato, peco.pgimPersona.idPersona, "
                        + "peco.pgimPersona.noPersona || ' ' || peco.pgimPersona.apPaterno || ' ' || peco.pgimPersona.apMaterno, "
                        + "peco.pgimPersona.coDocumentoIdentidad, peco.pgimPersona.tipoDocIdentidad.noValorParametro,"
                        + "case when peco.pgimContrato.flEstado = '1' then 'Vigente' else 'No vigente' end , "
                        + "peco.noUsuario, peco.coUsuarioSiged, peco.noCargoEnContrato, peco.noPerfilEnContrato, rol.idRolProceso, rol.noRolProceso, dsup.deValorParametro " 
                        + ") " 
                        + "FROM PgimPersonalContrato peco "
                        + "LEFT OUTER JOIN peco.pgimRolProceso rol "
                        + "LEFT OUTER JOIN peco.pgimValorParametro dsup "
                        + "WHERE peco.pgimContrato.idContrato = :idContrato "
                        + "AND peco.esRegistro = '1' ORDER BY peco.pgimPersona.noPersona ")
        Page<PgimPersonalContratoDTO> listarPersonalContrato(@Param("idContrato") Long idContrato, Pageable paginador);

        /**
         * Permite obtener las propeidades necesarias del personal de contrato
         * 
         * @param idPersonalContrato
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado("
                        + "peco.idPersonalContrato, peco.pgimContrato.idContrato, peco.pgimPersona.idPersona, "
                        + "peco.pgimPersona.noPersona, peco.pgimPersona.apPaterno, peco.pgimPersona.apMaterno, "
                        + "peco.pgimPersona.coDocumentoIdentidad ||' '|| peco.pgimPersona.noPersona ||' '|| peco.pgimPersona.apPaterno ||' '|| peco.pgimPersona.apMaterno, peco.pgimPersona.tipoDocIdentidad.idValorParametro, "
                        + "peco.pgimPersona.tipoDocIdentidad.noValorParametro, ubig.idUbigeo,"
                        + "((SELECT TRIM(ubde.noUbigeo) FROM PgimUbigeo ubde WHERE ubde.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 2) || '0000') || ', ' || "
                        + "(SELECT TRIM(ubdi.noUbigeo) FROM PgimUbigeo ubdi where ubdi.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 4) || '00') || ', ' || "
                        + "TRIM(ubig.noUbigeo)), "
                        + "case when peco.pgimContrato.flEstado = '1' then 'Vigente' else 'No vigente' end , "
                        + "peco.noUsuario, peco.coUsuarioSiged, peco.noCargoEnContrato, peco.noPerfilEnContrato, per.noPrefijoPersona, rol.idRolProceso, dsup.idValorParametro ) " 
                        + "FROM PgimPersonalContrato peco "
                        + "LEFT OUTER JOIN peco.pgimPersona.pgimUbigeo ubig "
                        + "LEFT OUTER JOIN peco.pgimValorParametro dsup "
                        + "LEFT OUTER JOIN peco.pgimPersona per "
                        + "LEFT OUTER JOIN peco.pgimRolProceso rol "
                        + "WHERE peco.esRegistro = '1' AND peco.idPersonalContrato = :idPersonalContrato")
        PgimPersonalContratoDTO obtenerPersonalContrato(@Param("idPersonalContrato") Long idPersonalContrato);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado(" 
                        + "pers.idPersona, "
                        + "pers.coDocumentoIdentidad ||' '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno ) "
                        + "FROM PgimPersona pers " 
                        + "WHERE pers.esRegistro = '1' "
                        + "AND NOT pers.tipoDocIdentidad.coClaveTexto = :DOIDE_RUC "
                        // + "AND NOT EXISTS (SELECT 1 " 
                        // + "FROM PgimPersonalContrato peco "
                        // + "WHERE peco.esRegistro = '1' AND peco.pgimContrato.esRegistro = '1' AND peco.pgimContrato.flEstado = '1' "
                        // + "AND peco.pgimContrato.idContrato = :idContrato " 
                        // + "AND peco.pgimPersona = pers) "
                        + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad ||' '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) "
                        + "LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
        List<PgimPersonalContratoDTO> listarPorPersona(String palabraClave, @Param("DOIDE_RUC") String DOIDE_RUC);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado(" 
                        + "pers.idPersona, " 
                        + "peco.idPersonalContrato, "
                        + "UPPER(peco.noUsuario) " 
                        + " ) "
                        + "FROM PgimPersona pers " 
                        + "LEFT OUTER JOIN PgimPersonalContrato peco ON peco.pgimPersona.idPersona = pers.idPersona "
                        + "LEFT OUTER JOIN PgimPersonalOsi peos ON peos.pgimPersona.idPersona = pers.idPersona "
                        + "WHERE pers.esRegistro = '1' AND peco.esRegistro = '1' "
                        + "AND (:idPersonalContrato IS NULL OR peco.idPersonalContrato != :idPersonalContrato) "
                        + "AND (:noUsuario IS NULL OR UPPER(peos.noUsuario) = :noUsuario ) " 
                        + "OR (:noUsuario IS NULL OR UPPER(peos.noUsuario) = :noUsuario ) "
                        + " ")
        List<PgimPersonalContratoDTO> existeNoUsuario(@Param("idPersonalContrato") Long idPersonalContrato, @Param("noUsuario") String noUsuario);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado(" 
                        + "peco.idPersonalContrato, peco.pgimContrato.idContrato, peco.pgimPersona.idPersona, " 
                        + "peco.pgimPersona.coDocumentoIdentidad ) "
                        + "FROM PgimPersonalContrato peco " 
                        + "WHERE peco.esRegistro = '1' "
                        + "AND peco.pgimContrato.flEstado = '1' "
                        + "AND (:idContrato IS NULL OR peco.pgimContrato.idContrato = :idContrato) " 
                        + "AND (:idPersonalContrato IS NULL OR peco.idPersonalContrato <> :idPersonalContrato) "
                        + "AND (:idPersona IS NULL OR peco.pgimPersona.idPersona = :idPersona ) ")
        List<PgimPersonalContratoDTO> existePersonalContrato(@Param("idContrato") Long idContrato, @Param("idPersona") Long idPersona, @Param("idPersonalContrato") Long idPersonalContrato);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado(" 
                + "peco.idPersonalContrato ) "
                + "FROM PgimPersonalContrato peco " 
                + "WHERE peco.esRegistro = '1' "
                + "AND peco.pgimContrato.idContrato = :idContrato ")
        List<PgimPersonalContratoDTO> listarPersonalContratoPorContrato(@Param("idContrato") Long idContrato);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado(" 
                + "peco.idPersonalContrato, peco.pgimContrato.idContrato, peco.pgimPersona.idPersona, peco.noUsuario, " 
                + "(peco.pgimPersona.noPersona || ' ' || peco.pgimPersona.apPaterno || ' ' || peco.pgimPersona.apMaterno), peco.pgimPersona.coDocumentoIdentidad "
                + " ) "
                + "FROM PgimPersonalContrato peco " 
                + "WHERE peco.esRegistro = '1' "
                + "AND peco.pgimContrato.idContrato = :idContrato ")
        List<PgimPersonalContratoDTO> listarUsuarioPersonalContratoPorContrato(@Param("idContrato") Long idContrato);

        /**
	 * STORY: PGIM-7277: Abogado/a y Coordinador/a de empresa supervisora por defecto
	 */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTOResultado("
                        + "peco.idPersonalContrato, per.idPersona, rol.idRolProceso, peco.noCargoEnContrato, peco.noPerfilEnContrato, dsup.idValorParametro, (dsup.noValorParametro || ' - ' || dsup.deValorParametro), "
                        + "UPPER(peco.pgimPersona.noPersona || ' ' || peco.pgimPersona.apPaterno || ' ' || peco.pgimPersona.apMaterno) " 
                        + ") " 
                        + "FROM PgimPersonalContrato peco "
                        + "LEFT OUTER JOIN peco.pgimPersona per "
                        + "LEFT OUTER JOIN peco.pgimRolProceso rol "
                        + "LEFT OUTER JOIN peco.pgimValorParametro dsup "
                        + "WHERE peco.pgimContrato.idContrato = :idContrato " 
                        + "AND rol.idRolProceso = :idRolProceso " 
                        + "AND dsup.idValorParametro = :idDivisionSupervisora " 
                        + "AND peco.esRegistro = '1'"
                        )        
        List<PgimPersonalContratoDTO> listarPersonalContratoPorRoles(@Param("idContrato") Long idContrato, @Param("idRolProceso") Long idRolProceso, @Param("idDivisionSupervisora") Long idDivisionSupervisora );
}