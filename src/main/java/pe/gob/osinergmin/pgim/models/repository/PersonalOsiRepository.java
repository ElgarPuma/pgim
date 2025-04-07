package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsi;

/**
 * En ésta interface PersonalOsiRepository esta conformado pos sus metodos de
 * obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Personal osi
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
@Repository
public interface PersonalOsiRepository extends JpaRepository<PgimPersonalOsi, Long> {

        /**
         * Permite obtener la lista de personal del Osinergmin en base al nombre del
         * usuario windows.
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTOResultado("
                        + "peos.idPersonalOsi, per.idPersona, peos.noUsuario, "
                        + "peos.coUsuarioSiged, per.tiSexo " 
                        + ") "
                        + "FROM PgimPersonalOsi peos "
                        + "INNER JOIN peos.pgimPersona per "
                        + "WHERE peos.noUsuario = :noUsuario "
                        + "AND peos.esRegistro = '1' " 
                        + "AND per.esRegistro = '1' " 
                        + "AND peos.flActivo = '1' "
                        )
        List<PgimPersonalOsiDTO> obtenerPersonalOsiPorUsuario(@Param("noUsuario") String noUsuario);

        /**
         * Permite obtener la lista de personal del Osinergmin en base a su
         * identificador.
         * 
         * @param idPersonalOsi
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTOResultado("
                        + "peos.idPersonalOsi, per.idPersona, peos.noUsuario, "
                        + "peos.coUsuarioSiged, per.tiSexo " 
                        + ") "
                        + "FROM PgimPersonalOsi peos "
                        + "INNER JOIN peos.pgimPersona per "
                        + "WHERE peos.idPersonalOsi = :idPersonalOsi")
        PgimPersonalOsiDTO obtenerPersonalOsiPorId(@Param("idPersonalOsi") Long idPersonalOsi);

        /**
         * Permite listar los todos los personales de Osinergmin
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTOResultado( "
                        + "osi.idPersonalOsi, osi.pgimPersona.idPersona, osi.noUsuario, "
                        + "osi.coUsuarioSiged, "
                        + "(pers.coDocumentoIdentidad || ': ' || TRIM(upper(pers.noPersona)) " 
                        + "|| ' ' || TRIM(upper(pers.apPaterno)) || ' ' || TRIM(upper(pers.apMaterno))), "
                        + "pers.noPersona, pers.apPaterno, pers.apMaterno, pers.coDocumentoIdentidad, " 
                        + "val.noValorParametro, pers.deCorreo, pers.deTelefono, osi.flActivo, "
                        + "pers.noPrefijoPersona "
                        + ") "
                        + "FROM PgimPersonalOsi osi, PgimPersona pers, PgimValorParametro val "
                        + "WHERE osi.pgimPersona = pers "
                        + "AND pers.tipoDocIdentidad = val "
                        + "AND osi.esRegistro = '1' " 
                        + "AND (:flActivo IS NULL OR osi.flActivo = :flActivo ) "
                        + "AND (:textoBusqueda IS NULL OR ( "
			+ "LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
			+ "OR LOWER(pers.noPersona) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(pers.apPaterno) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(pers.apMaterno) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(pers.apPaterno || ' ' || pers.apMaterno || ' ' || pers.noPersona) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
			+ "OR LOWER(osi.noUsuario) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
                        + ") )  "
                        +"")
        Page<PgimPersonalOsiDTO> listarPersonalOsi(@Param("flActivo") String flActivo, @Param("textoBusqueda") String textoBusqueda, Pageable paginador);

        /**
         * Permite obtener la lista de personal del Osinergmin en base a su
         * identificador.
         * 
         * @param idPersonalOsi
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTOResultado("
                        + "peos.idPersonalOsi, peos.pgimPersona.idPersona, peos.noUsuario, "
                        + "peos.coUsuarioSiged, "
                        + "(pers.coDocumentoIdentidad || ': ' || TRIM(upper(pers.apPaterno)) " 
                        + "|| ' ' || TRIM(upper(pers.apMaterno)) || ', ' ||  TRIM(upper(pers.noPersona)) ), "
                        + "pers.noPersona, pers.apPaterno, pers.apMaterno, pers.coDocumentoIdentidad, " 
                        + "val.noValorParametro, pers.deCorreo, pers.deTelefono, peos.flActivo, "
                        + "pers.noPrefijoPersona "
                        + ") "
                        + "FROM PgimPersonalOsi peos, PgimPersona pers, PgimValorParametro val "
                        + "WHERE peos.pgimPersona = pers "
                        + "AND pers.tipoDocIdentidad = val "
                        + "AND peos.idPersonalOsi = :idPersonalOsi")
        PgimPersonalOsiDTO obtenerPersonalOsigPorId(@Param("idPersonalOsi") Long idPersonalOsi);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTOResultado(" 
			+ "peos.idPersonalOsi, " 
			+ "TRIM(UPPER(peos.noUsuario)) " 
			+ " ) " 
			+ "FROM PgimPersonalOsi peos "
			+ "WHERE peos.esRegistro = '1' " 
			+ "AND (:idPersonalOsi IS NULL OR peos.idPersonalOsi != :idPersonalOsi) "
			+ "AND TRIM(UPPER(peos.noUsuario)) = :noUsuario "
			+ " ")
	List<PgimPersonalOsiDTO> existeUsuario(@Param("idPersonalOsi") Long idPersonalOsi,
			@Param("noUsuario") String noUsuario);
}