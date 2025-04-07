package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;

import java.util.List;

/**
 * En ésta interface EquipoInstanciaProcesoRepository esta conformado por sus metodos de listar
 * los personales asignados de osinergmin por el rol y
 * obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Equipo instancia proceso
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
public interface EquipoInstanciaProcesoRepository extends JpaRepository<PgimEqpInstanciaPro, Long> {

        /**
         * Permite obtener la personal del equipo de supervisión por id.
         * 
         * @param idEquipoInstanciaPro
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, equi.pgimInstanciaProces.idInstanciaProceso, equi.pgimRolProceso.idRolProceso, "
                        + "peco.idPersonalContrato, peos.idPersonalOsi) "
                        + "FROM PgimEqpInstanciaPro equi " 
                        + "LEFT OUTER JOIN equi.pgimPersonalOsi peos " 
                        + "LEFT OUTER JOIN equi.pgimPersonalContrato peco "
                        + "WHERE equi.esRegistro = '1' "
                        + "AND equi.idEquipoInstanciaPro = :idEquipoInstanciaPro")
        PgimEqpInstanciaProDTO obtenerPorId(@Param("idEquipoInstanciaPro") Long idEquipoInstanciaPro);        

        /**
         * Permite obtener la lista de personal del equipo de una instancia de proceso.
         * @param idInstanciaProceso Identitificador de la instancia del proceso.
         * @param idPersonalOsi Identificador interno del personal del Osinergmin.
         * @param idRolProceso Identificador del rol del proceso.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, equi.pgimInstanciaProces.idInstanciaProceso, equi.pgimRolProceso.idRolProceso, "
                        + "equi.pgimPersonalOsi.idPersonalOsi, 1) " 
                        + "FROM PgimEqpInstanciaPro equi  "
                        + "WHERE equi.esRegistro = '1' "
                        + "AND equi.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                        + "AND equi.pgimPersonalOsi.idPersonalOsi = :idPersonalOsi "
                        + "AND equi.pgimRolProceso.idRolProceso = :idRolProceso")
        List<PgimEqpInstanciaProDTO> obtenerPersonaOsiEqpPorRol(@Param("idInstanciaProceso") Long idInstanciaProceso,
                        @Param("idPersonalOsi") Long idPersonalOsi, @Param("idRolProceso") Long idRolProceso);

        /**
         * Permite obtener la lista de personal del equipo de una instancia de proceso.
         * @param idInstanciaProceso Identitificador de la instancia del proceso.
         * @param idPersonalContrato Identificador interno del personal del Osinergmin.
         * @param idRolProceso Identificador del rol del proceso.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, equi.pgimInstanciaProces.idInstanciaProceso, equi.pgimRolProceso.idRolProceso, "
                        + "equi.pgimPersonalContrato.idPersonalContrato) " 
                        + "FROM PgimEqpInstanciaPro equi  "
                        + "WHERE equi.esRegistro = '1' "
                        + "AND equi.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                        + "AND equi.pgimPersonalContrato.idPersonalContrato = :idPersonalContrato "
                        + "AND equi.pgimRolProceso.idRolProceso = :idRolProceso")
        List<PgimEqpInstanciaProDTO> obtenerPersonaContraEqpPorRol(@Param("idInstanciaProceso") Long idInstanciaProceso,
                        @Param("idPersonalContrato") Long idPersonalContrato, @Param("idRolProceso") Long idRolProceso);

        /**
         * Permite obtener la lista de personas del equipo de la instancia del proceso que cumplen con un determinado rol como personal del Osinergmin.
         * @param idInstanciaProceso
         * @param idRolProceso
         * @param palabraClave
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, inpr.idInstanciaProceso, peos.idPersonalOsi, "
                        + "pers.idPersona, peos.noUsuario, tido.idValorParametro, "
                        + "tido.noValorParametro, pers.coDocumentoIdentidad, pers.noPersona, "
                        + "pers.apPaterno, pers.apMaterno, pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno, "
                        + "ropr.idRolProceso, ropr.noRolProceso, 1"
                        + ") "
                        + "FROM PgimEqpInstanciaPro equi "
                        + "INNER JOIN equi.pgimInstanciaProces inpr "
                        + "INNER JOIN equi.pgimRolProceso ropr "
                        + "INNER JOIN equi.pgimPersonalOsi peos "
                        + "INNER JOIN peos.pgimPersona pers "
                        + "INNER JOIN pers.tipoDocIdentidad tido "
                        + "WHERE equi.esRegistro = '1' " 
                        + "AND inpr.esRegistro = '1' "
                        + "AND ropr.esRegistro = '1' "
                        + "AND peos.esRegistro = '1' "
                        + "AND peos.flActivo = '1' "
                        + "AND pers.esRegistro = '1' "
                        + "AND tido.esRegistro = '1' "
                        + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
                        + "AND ropr.idRolProceso = :idRolProceso "
                        + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(peos.noUsuario) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + ") "
                        + "ORDER BY pers.noPersona, pers.apPaterno, pers.apMaterno"
                        )
        List<PgimEqpInstanciaProDTO> listarPersonalAsignableOsiConRol(
                        @Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idRolProceso") Long idRolProceso,
                        @Param("palabraClave") String palabraClave);

        /**
         * Permite obtener la lista de personas del equipo del Osinbergmin responsables de la instancia del proceso.
         * @param idInstanciaProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, inpr.idInstanciaProceso, peos.idPersonalOsi, "
                        + "pers.idPersona, peos.noUsuario, tido.idValorParametro, "
                        + "tido.noValorParametro, pers.coDocumentoIdentidad, pers.noPersona, "
                        + "pers.apPaterno, pers.apMaterno, pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno, "
                        + "ropr.idRolProceso, ropr.noRolProceso, 1"
                        + ") "
                        + "FROM PgimEqpInstanciaPro equi "
                        + "INNER JOIN equi.pgimInstanciaProces inpr "
                        + "INNER JOIN equi.pgimRolProceso ropr "
                        + "INNER JOIN equi.pgimPersonalOsi peos "
                        + "INNER JOIN peos.pgimPersona pers "
                        + "INNER JOIN pers.tipoDocIdentidad tido "
                        + "WHERE equi.flEsResponsable = '1' " 
                        + "AND equi.esRegistro = '1' " 
                        + "AND inpr.esRegistro = '1' "
                        + "AND ropr.esRegistro = '1' "
                        + "AND peos.esRegistro = '1' "
                        + "AND pers.esRegistro = '1' "
                        + "AND tido.esRegistro = '1' "
                        + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
                        + "ORDER BY pers.noPersona, pers.apPaterno, pers.apMaterno"
                        )
        List<PgimEqpInstanciaProDTO> listarPersonalOsiResponsable(
                        @Param("idInstanciaProceso") Long idInstanciaProceso);

        /**
         * Permite obtener la lista de personas del equipo del Osinbergmin interesado de la instancia del proceso.
         * @param idInstanciaProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, inpr.idInstanciaProceso, peos.idPersonalOsi, "
                        + "pers.idPersona, peos.noUsuario, tido.idValorParametro, "
                        + "tido.noValorParametro, pers.coDocumentoIdentidad, pers.noPersona, "
                        + "pers.apPaterno, pers.apMaterno, pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno, "
                        + "ropr.idRolProceso, ropr.noRolProceso, 1"
                        + ") "
                        + "FROM PgimEqpInstanciaPro equi "
                        + "INNER JOIN equi.pgimInstanciaProces inpr "
                        + "INNER JOIN equi.pgimRolProceso ropr "
                        + "INNER JOIN equi.pgimPersonalOsi peos "
                        + "INNER JOIN peos.pgimPersona pers "
                        + "INNER JOIN pers.tipoDocIdentidad tido "
                        + "WHERE equi.flEsResponsable <> '1' " 
                        + "AND equi.esRegistro = '1' " 
                        + "AND inpr.esRegistro = '1' "
                        + "AND ropr.esRegistro = '1' "
                        + "AND peos.esRegistro = '1' "
                        + "AND pers.esRegistro = '1' "
                        + "AND tido.esRegistro = '1' "
                        + "AND ropr.coRolProceso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PROCESO_CO_ROL_INTERESADO "
                        + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
                        + "ORDER BY pers.noPersona, pers.apPaterno, pers.apMaterno"
                        )
        List<PgimEqpInstanciaProDTO> listarPersonalOsiInteresado(
                        @Param("idInstanciaProceso") Long idInstanciaProceso);
        
        /**
         * Permite obtener la lista de personas del equipo del Osinergmin que no cumplen el rol especificado, es decir aún no forman parte del equipo
         * de la instancia del proceso, al menos no con el rol señalado.
         * @param idInstanciaProceso Identificador interno de la instancia del proceso.
         * @param idRolProceso Identificador del rol requerido.
         * @param palabraClave Palabra clave de búsqueda de la persona.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "peos.idPersonalOsi, "
                        + "pers.idPersona, peos.noUsuario, tido.idValorParametro, "
                        + "tido.noValorParametro, pers.coDocumentoIdentidad, pers.noPersona, "
                        + "pers.apPaterno, pers.apMaterno, pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno, "
                        + "0) "
                        + "FROM PgimPersonalOsi peos "
                        + "INNER JOIN peos.pgimPersona pers "
                        + "INNER JOIN pers.tipoDocIdentidad tido "
                        + "WHERE peos.esRegistro = '1' "
                        + "AND pers.esRegistro = '1' "
                        + "AND tido.esRegistro = '1' "
                        + "AND peos.flActivo = '1' "
                        + "AND NOT EXISTS (SELECT 1 "
                        + "FROM PgimEqpInstanciaPro equi "
                        + "INNER JOIN equi.pgimInstanciaProces inpr "
                        + "INNER JOIN equi.pgimRolProceso ropr "                        
                        + "WHERE equi.pgimPersonalOsi.idPersonalOsi = peos.idPersonalOsi " 
                        + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
                        + "AND ropr.idRolProceso = :idRolProceso "
                        + "AND equi.esRegistro = '1' " 
                        + "AND inpr.esRegistro = '1' "
                        + "AND ropr.esRegistro = '1' "
                        + "AND peos.flActivo = '1' "
                        + ") "
                        + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(peos.noUsuario) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + ") "
                        + "ORDER BY pers.noPersona, pers.apPaterno, pers.apMaterno"
                        )                        
        List<PgimEqpInstanciaProDTO> listarPersonalAsignableOsiSinRol(
                @Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idRolProceso") Long idRolProceso,
                @Param("palabraClave") String palabraClave);
        
        
        /**
    	 * Se utiliza para visualizar la lista de participantes de la instancia de proceso
    	 * @return
    	 */
    	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado("
                + "es.idEquipoInstanciaPro, es.pgimInstanciaProces.idInstanciaProceso, rp.idRolProceso, "
    			+ "rp.noRolProceso, pc.idPersonalContrato, po.idPersonalOsi, "
    			+ "ppo.noPersona || ' ' || ppo.apPaterno || ' ' || ppo.apMaterno, "
                + "ppc.noPersona || ' ' || ppc.apPaterno || ' ' || ppc.apMaterno, "
                + "UPPER(TRIM(ppc.apPaterno)) || '_' || SUBSTR(TRIM(ppc.noPersona), 1, 1), "
    			+ "CASE WHEN es.pgimPersonalOsi.idPersonalOsi IS NULL THEN 'Supervisora' ELSE 'Osinergmin' END, "
    			+ "es.flEsResponsable, ppc.idPersona, (ppc.idPersona || '.' || 'pdf'), " 
                + "CASE WHEN es.pgimPersonalOsi.idPersonalOsi IS NULL THEN pc.noUsuario ELSE po.noUsuario END, "
                + "es.noCargoPersonaEquipo, es.noPrefijoPersonaEquipo, es.noPerfilPersonaEquipo, ppo.idPersona, ppo.tiSexo "
                + " ) "
                + "FROM PgimEqpInstanciaPro es "
                + "LEFT OUTER JOIN es.pgimRolProceso rp "
                + "LEFT OUTER JOIN es.pgimPersonalOsi po "
                + "LEFT OUTER JOIN po.pgimPersona ppo "
                
                + "LEFT OUTER JOIN es.pgimPersonalContrato pc "
                + "LEFT OUTER JOIN pc.pgimPersona ppc "
                + "WHERE es.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND es.esRegistro = '1' "
                + "ORDER BY ppo.noPersona, ppo.apPaterno, ppo.apMaterno,ppc.noPersona, ppc.apPaterno, ppc.apMaterno"
                )
    	List<PgimEqpInstanciaProDTO> obtenerParticipantesInstanciaPro(@Param("idInstanciaProceso") Long idInstanciaProceso);

    	/**
    	 * Se utiliza para visualizar la lista de participantes de la instancia de proceso con un rol determinado 
    	 * @return
    	 */
    	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado("
                + "nvl(po.idPersonalOsi, pc.idPersonalContrato), nvl(ppo.idPersona, ppc.idPersona), nvl(ppo.coDocumentoIdentidad, ppc.coDocumentoIdentidad) "
                + ",UPPER(TRIM(ppo.noPersona) || ' ' || TRIM(ppo.apPaterno) || ' ' || TRIM(ppo.apMaterno))"
    			+ ",UPPER(TRIM(ppo.apPaterno)) || '_' || SUBSTR(TRIM(ppo.noPersona),1,1) "
    			+ ",UPPER(TRIM(ppc.noPersona) || ' ' || TRIM(ppc.apPaterno) || ' ' || TRIM(ppc.apMaterno))"
                + ",UPPER(TRIM(ppc.apPaterno)) || '_' || SUBSTR(TRIM(ppc.noPersona),1,1) "
    			+ ",case when es.pgimPersonalOsi.idPersonalOsi is null then 'Supervisora' else 'Osinergmin' end "
    			+ ",es.noCargoPersonaEquipo, es.noPrefijoPersonaEquipo, es.noPerfilPersonaEquipo, rp.idRolProceso, nvl(tdppo.noValorParametro,tdppc.noValorParametro) "
    			+ ",tdppo.noValorParametro "
    			+ ",CASE WHEN es.pgimPersonalOsi.idPersonalOsi IS NULL THEN pc.noUsuario ELSE po.noUsuario END "
    			+ ") "
                + "FROM PgimEqpInstanciaPro es "
                + "LEFT OUTER JOIN es.pgimRolProceso rp "
                + "LEFT OUTER JOIN es.pgimPersonalOsi po "
                + "LEFT OUTER JOIN po.pgimPersona ppo "
                + "LEFT OUTER JOIN ppo.tipoDocIdentidad tdppo "
                + "LEFT OUTER JOIN es.pgimPersonalContrato pc "
                + "LEFT OUTER JOIN pc.pgimPersona ppc "
                + "LEFT OUTER JOIN ppo.tipoDocIdentidad tdppo "
                + "LEFT OUTER JOIN ppc.tipoDocIdentidad tdppc "
                + "WHERE es.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND es.esRegistro = '1' AND rp.idRolProceso = :idRolProceso "
                + "ORDER BY ppo.noPersona, ppo.apPaterno, ppo.apMaterno,ppc.noPersona, ppc.apPaterno, ppc.apMaterno"
                )
    	List<PgimEqpInstanciaProDTO> obtenerParticipantesInstanciaProXRol(@Param("idInstanciaProceso") Long idInstanciaProceso,
    			 @Param("idRolProceso") Long idRolProceso);


        /**
         * Permite obtener la lista de personas del equipo de la instancia del proceso que cumplen con un determinado rol como personal del contrato.
         * @param idInstanciaProceso
         * @param idRolProceso
         * @param palabraClave
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "equi.idEquipoInstanciaPro, inpr.idInstanciaProceso, peco.idPersonalContrato, "
                        + "pers.idPersona, peco.noUsuario, tido.idValorParametro, "
                        + "tido.noValorParametro, pers.coDocumentoIdentidad, pers.noPersona, "
                        + "pers.apPaterno, pers.apMaterno, pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno, "
                        + "ropr.idRolProceso, ropr.noRolProceso"
                        + ") "
                        + "FROM PgimEqpInstanciaPro equi "
                        + "INNER JOIN equi.pgimInstanciaProces inpr "
                        + "INNER JOIN equi.pgimRolProceso ropr "
                        + "INNER JOIN equi.pgimPersonalContrato peco "
                        + "INNER JOIN peco.pgimPersona pers "
                        + "INNER JOIN pers.tipoDocIdentidad tido "
                        + "WHERE equi.esRegistro = '1' " 
                        + "AND inpr.esRegistro = '1' "
                        + "AND ropr.esRegistro = '1' "
                        + "AND peco.esRegistro = '1' "
                        + "AND pers.esRegistro = '1' "
                        + "AND tido.esRegistro = '1' "
                        + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
                        + "AND ropr.idRolProceso = :idRolProceso "
                        + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(peco.noUsuario) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + ") "
                        + "ORDER BY pers.noPersona, pers.apPaterno, pers.apMaterno"
                        )        
        List<PgimEqpInstanciaProDTO> listarPersonalAsignableContraConRol(@Param("idInstanciaProceso") Long idInstanciaProceso,
        @Param("idRolProceso") Long idRolProceso, @Param("palabraClave") String palabraClave);

        /**
         * Permite obtener la lista de personas del contrato que no cumplen el rol especificado, es decir aún no forman parte del equipo
         * de la instancia del proceso, al menos no con el rol señalado.
         * @param idInstanciaProceso
         * @param idContrato
         * @param idRolProceso
         * @param palabraClave
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado( "
                        + "peco.idPersonalContrato, "
                        + "pers.idPersona, peco.noUsuario, tido.idValorParametro, "
                        + "tido.noValorParametro, pers.coDocumentoIdentidad, pers.noPersona, "
                        + "pers.apPaterno, pers.apMaterno, pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno"
                        + ") "
                        + "FROM PgimPersonalContrato peco "
                        + "INNER JOIN peco.pgimContrato cont "
                        + "INNER JOIN peco.pgimPersona pers "
                        + "INNER JOIN pers.tipoDocIdentidad tido "
                        + "WHERE cont.idContrato = :idContrato "
                        + "AND peco.esRegistro = '1' "
                        + "AND cont.esRegistro = '1' "
                        + "AND pers.esRegistro = '1' "
                        + "AND tido.esRegistro = '1' "
                        + "AND NOT EXISTS (SELECT 1 "
                        + "FROM PgimEqpInstanciaPro equi "
                        + "INNER JOIN equi.pgimInstanciaProces inpr "
                        + "INNER JOIN equi.pgimRolProceso ropr "                        
                        + "WHERE equi.pgimPersonalContrato.idPersonalContrato = peco.idPersonalContrato " 
                        + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
                        + "AND ropr.idRolProceso = :idRolProceso "
                        + "AND equi.esRegistro = '1' " 
                        + "AND inpr.esRegistro = '1' "
                        + "AND ropr.esRegistro = '1' "
                        + ") "
                        + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                        + "OR LOWER(peco.noUsuario) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + "OR LOWER(pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                        + ") "
                        + "ORDER BY pers.noPersona, pers.apPaterno, pers.apMaterno"
                        )               
        List<PgimEqpInstanciaProDTO> listarPersonalAsignableContraSinRol(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idContrato") Long idContrato,
        @Param("idRolProceso") Long idRolProceso, @Param("palabraClave") String palabraClave);


        /**
         * Permite obtener la lista de personal del equipo de una instancia de proceso dada que cumple con un determinado rol.
         * @param idInstanciaProceso Identificador de la instancia del proceso.
         * @param idPersonaEqpDestino Identificador de la personal del equipo de la cual se desea constatar el rol
         * @param idRolProceso Identificador interno del rol.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado(peeq.idEquipoInstanciaPro) "
                        + "FROM PgimEqpInstanciaPro peeq "
                        + "WHERE peeq.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                        + "AND peeq.idEquipoInstanciaPro = :idPersonaEqpDestino "
                        + "AND peeq.pgimRolProceso.idRolProceso = :idRolProceso  "
                        + "AND peeq.esRegistro = '1'"
                        )               
        List<PgimEqpInstanciaProDTO> listarPersEqpPorInstanProcYrol(@Param("idInstanciaProceso") Long idInstanciaProceso, 
        @Param("idPersonaEqpDestino") Long idPersonaEqpDestino, @Param("idRolProceso") Long idRolProceso);
        
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado("
                + "es.idEquipoInstanciaPro,es.pgimInstanciaProces.idInstanciaProceso, rp.idRolProceso, "
    			+ "pc.idPersonalContrato,po.idPersonalOsi) "
                + "FROM PgimEqpInstanciaPro es "
                + "LEFT OUTER JOIN es.pgimRolProceso rp "
                + "LEFT OUTER JOIN es.pgimPersonalOsi po "
                + "LEFT OUTER JOIN es.pgimPersonalContrato pc "
                + "WHERE es.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND es.esRegistro = '1' "
                + "AND rp.idRolProceso = :idRolProceso "
                )
    	List<PgimEqpInstanciaProDTO> obtenerParticipantesRolSupervisor(@Param("idInstanciaProceso") Long idInstanciaProceso,
    			@Param("idRolProceso") Long idRolProceso);
        

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado("
        		+ "UPPER(TRIM(pposi.noPersona)), UPPER(TRIM(pposi.apPaterno)), UPPER(TRIM(pposi.apMaterno)), "
        		+ "pposi.coDocumentoIdentidad, eqpip.pgimRolProceso.noRolProceso, eqpip.noCargoPersonaEquipo, eqpip.noPrefijoPersonaEquipo, eqpip.noPerfilPersonaEquipo, "
        		+ "posi.noUsuario "
        		+ ") "
				+ "FROM PgimEqpInstanciaPro eqpip "
                + "LEFT JOIN PgimPersonalOsi posi on eqpip.pgimPersonalOsi.idPersonalOsi = posi.idPersonalOsi "
                + "LEFT JOIN PgimPersona pposi on posi.pgimPersona.idPersona = pposi.idPersona "
                + "WHERE eqpip.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND eqpip.pgimRolProceso.idRolProceso = :idRolProceso "
                + "AND eqpip.esRegistro = '1' "
                + "AND pposi.noPersona IS NOT NULL ")
        List<PgimEqpInstanciaProDTO> obtenerPersonalXRolOsi(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idRolProceso") Long idRolProceso);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado("
                + "UPPER(TRIM(ppcon.noPersona)), UPPER(TRIM(ppcon.apPaterno)), UPPER(TRIM(ppcon.apMaterno)), "
                + "ppcon.coDocumentoIdentidad, eqpip.pgimRolProceso.noRolProceso, eqpip.noCargoPersonaEquipo, eqpip.noPrefijoPersonaEquipo, eqpip.noPerfilPersonaEquipo, "
                + "pcon.noUsuario "
                + ") "
                + "FROM PgimEqpInstanciaPro eqpip "
                + "LEFT JOIN PgimPersonalContrato pcon on eqpip.pgimPersonalContrato.idPersonalContrato = pcon.idPersonalContrato "
                + "LEFT JOIN PgimPersona ppcon on pcon.pgimPersona.idPersona = ppcon.idPersona "
                + "WHERE eqpip.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND eqpip.pgimRolProceso.idRolProceso = :idRolProceso "
                + "AND eqpip.esRegistro = '1' "
                + "AND ppcon.noPersona IS NOT NULL ")
        List<PgimEqpInstanciaProDTO> obtenerPersonalXRolContrato(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idRolProceso") Long idRolProceso);   

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTOResultado("
                + "UPPER(TRIM(pposi.noPersona)), UPPER(TRIM(pposi.apPaterno)), UPPER(TRIM(pposi.apMaterno)), "
                + "pposi.coDocumentoIdentidad, eqpip.pgimRolProceso.noRolProceso, eqpip.noCargoPersonaEquipo, eqpip.noPrefijoPersonaEquipo, eqpip.noPerfilPersonaEquipo, "
                + "posi.noUsuario ) "
                + "FROM PgimEqpInstanciaPro eqpip "
                + "LEFT JOIN PgimPersonalOsi posi on eqpip.pgimPersonalOsi.idPersonalOsi = posi.idPersonalOsi "
                + "LEFT JOIN PgimPersona pposi on posi.pgimPersona.idPersona = pposi.idPersona "
                + "WHERE eqpip.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND eqpip.pgimRolProceso.idRolProceso = :idRolProceso "
                + "AND eqpip.flEsResponsable = '1' "
                + "AND eqpip.esRegistro = '1' "
                + "AND pposi.noPersona IS NOT NULL ")
        List<PgimEqpInstanciaProDTO> obtenerPersonalResponsableXRolOsi(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idRolProceso") Long idRolProceso);
     
}