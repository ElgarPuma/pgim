package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimCorrelativoSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;

/**
 * Ésta interface SupervisionRepository nos permite mostrar la supervisión
 * 
 * @descripción: Logica de negocio de la entidad Supervisión
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 14/07/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface SupervisionRepository extends JpaRepository<PgimSupervision, Long> {

        /**
         * Permite listar las supervisiones de acuerdo con los criterios filtro  especificados.
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idEspecialidad
         * @param idtipoSupervision
         * @param idDivisionSupervisora
         * @param flPropia
         * @param coSupervision
         * @param nuExpedienteSiged
         * @param idFaseProceso
         * @param idPasoProceso
         * @param descPersonaDestino
         * @param descNoResponsable
         * @param usuarioAsignado
         * @param usuarioInteres
         * @param flagMisAsignaciones
         * @param textoBusqueda
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera," 
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.usuarioAsignado, aux.noFaseActual || ' - ' || aux.noPasoActual, "
                        + "aux.noTipoSupervision, aux.deSubtipoSupervision, aux.nuExpedienteSiged, "
                        + "aux.idAgenteSupervisado, aux.descContrato, aux.noDivisionSupervisora, "
                        + "aux.noFlPropia, aux.flFeEnvio, aux.flLeido, "
                        + "aux.feLectura, aux.feInstanciaPaso, aux.noPersonaOrigen, "
                        + "aux.flPasoActivo, aux.deMensaje, aux.nuAlertas, "
                        + "aux.nuIntervaloAlertas, aux.idInstanciaPaso, aux.flListoContinuar "
                        + ") "
                        + "FROM PgimSupervisionAux aux " 
                        + "WHERE "
                        + "(:coUnidadMinera IS NULL OR aux.coUnidadMinera = :coUnidadMinera) "
                        + "AND (:noUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
                        + "OR LOWER(aux.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
                        + "AND (:coDocumentoIdentidad IS NULL OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
                        + "OR LOWER(aux.asNoRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                        + "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                        + "AND (:idtipoSupervision IS NULL OR aux.idTipoSupervision = :idtipoSupervision) "
                        + "AND (:idDivisionSupervisora IS NULL OR aux.idDivisionSupervisora = :idDivisionSupervisora) "
                        + "AND (:flPropia IS NULL OR aux.flPropia = :flPropia) "
                        + "AND (:coSupervision IS NULL OR aux.coSupervision = :coSupervision) "
                        + "AND (:nuExpedienteSiged IS NULL OR aux.nuExpedienteSiged = :nuExpedienteSiged) "
                        + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                        + "AND (:idPasoProceso IS NULL OR aux.idPasoActual = :idPasoProceso) "
                        + "AND (:descNoResponsable is null OR  aux.idInstanciaProceso in (select eipx.pgimInstanciaProces.idInstanciaProceso from PgimEqpInstanciaPro eipx, PgimPersonalOsi posix "
                        + ", PgimPersona perx where eipx.flEsResponsable='1' and eipx.esRegistro = '1' and posix.esRegistro = '1' and perx.esRegistro = '1' "
                        + "and eipx.pgimPersonalOsi.idPersonalOsi =posix.idPersonalOsi and posix.pgimPersona.idPersona = perx.idPersona "
                        + "and LOWER(perx.noPersona || ' ' || perx.apPaterno || ' ' || perx.apMaterno) LIKE LOWER(CONCAT('%', :descNoResponsable, '%'))"
                        + ")  ) "
                        + "AND (:usuarioInteres IS NULL OR aux.idInstanciaProceso IN " 
                        +       "( " 
                        +       "SELECT eipy.pgimInstanciaProces.idInstanciaProceso " 
                        +       "FROM PgimEqpInstanciaPro eipy "
                        +       "WHERE eipy.pgimRolProceso.idRolProceso=6 " 
                        +       "AND eipy.esRegistro='1' " 
                        +       "AND ( " 
                        +               "eipy.pgimPersonalOsi.idPersonalOsi IN " 
                        +               "( " 
                        +               "SELECT posiy.idPersonalOsi " 
                        +               "FROM PgimPersonalOsi posiy " 
                        +               "WHERE posiy.esRegistro = '1' " 
                        +               "AND posiy.noUsuario = :usuarioInteres " 
                        +               ") "
                        +       "OR eipy.pgimPersonalContrato.idPersonalContrato IN " 
                        +               "( " 
                        +               "select pcony.idPersonalContrato " 
                        +               "FROM PgimPersonalContrato pcony " 
                        +               "WHERE pcony.esRegistro = '1' " 
                        +               "AND pcony.noUsuario = :usuarioInteres " 
                        +               ") " 
                        +           ") "
                        +       ") " 
                        +   ") "

                        // Mis asignaciones
                        + "AND ("
                        //- Todas las tareas = '0'
                        + "        (:flagMisAsignaciones = '0' AND aux.flPasoActivo = '1' AND (:descPersonaDestino IS NULL OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) )) "                         
                        //- Mis tareas = '1'
                        + "     OR (:flagMisAsignaciones = '1' AND aux.flPasoActivo = '1' AND LOWER(aux.usuarioAsignado) = LOWER(:usuarioAsignado) AND aux.idTipoRelacion NOT IN (291, 292)) " 
                        //- Tareas enviadas = '2'
                        + "     OR ("
                        + "         :flagMisAsignaciones = '2' " 
                        + "          AND LOWER(aux.noUsuarioOrigen) = LOWER(:usuarioOrigen) "
                        + "          AND ("
                        + "               :descPersonaDestino IS NULL "
                        + "               OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
                        + "              ) "
                        + "         )" 
                        + " ) "
                        //
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.asNoRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noTipoSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.coSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noFaseActual) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))"
                        + "OR LOWER(aux.noPasoActual) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))"
                        + "OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))" 
                        + " )) "
        )
        Page<PgimSupervisionAuxDTO> listarSupervisiones(@Param("coUnidadMinera") String coUnidadMinera,
                        @Param("noUnidadMinera") String noUnidadMinera,
                        @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                        @Param("noRazonSocial") String noRazonSocial,
                        @Param("idEspecialidad") Long idEspecialidad,
                        @Param("idtipoSupervision") Long idtipoSupervision,
                        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
                        @Param("flPropia") String flPropia,
                        @Param("coSupervision") String coSupervision,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged,
                        @Param("idFaseProceso") Long idFaseProceso,
                        @Param("idPasoProceso") Long idPasoProceso,
                        @Param("descPersonaDestino") String descPersonaDestino,
                        @Param("descNoResponsable") String descNoResponsable,
                        @Param("usuarioOrigen") String usuarioOrigen,
                        @Param("usuarioAsignado") String usuarioAsignado,
                        @Param("usuarioInteres") String usuarioInteres,
                        @Param("flagMisAsignaciones") String flagMisAsignaciones,
                        @Param("textoBusqueda") String textoBusqueda, 
                        Pageable paginador);


        /**
         * Listar todas las fiscalizaciones para el calendario de vista semanal
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera," 
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.usuarioAsignado, aux.noFaseActual || ' - ' || aux.noPasoActual, "
                        + "aux.noTipoSupervision, aux.deSubtipoSupervision, aux.nuExpedienteSiged, "
                        + "aux.idAgenteSupervisado, aux.descContrato, aux.noDivisionSupervisora, "
                        + "aux.noFlPropia, aux.flFeEnvio, aux.flLeido, "
                        + "aux.feLectura, aux.feInstanciaPaso, aux.noPersonaOrigen, "
                        + "aux.flPasoActivo, aux.deMensaje, aux.nuAlertas, "
                        + "aux.nuIntervaloAlertas, aux.idInstanciaPaso, aux.idEspecialidad, supe.feInicioSupervisionReal, supe.feFinSupervisionReal "
                        + ", aux.idUnidadMinera "
                        + ") "
                        + "FROM PgimSupervisionAux aux " 
                        + "INNER JOIN aux.pgimSupervision supe " 
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND (( " 
                        + "             (:inicio IS NULL OR (aux.feInicioSupervision) BETWEEN :inicio AND :fin) "
                        + "             OR (:inicio IS NULL OR (aux.feFinSupervision) BETWEEN :inicio AND :fin) " 
                      
                        + "     ) "
                        + "OR ( " 
                        + "             (:inicio IS NULL OR (supe.feInicioSupervisionReal) BETWEEN :inicio AND :fin) " 
                        + "             OR (:inicio IS NULL OR (supe.feFinSupervisionReal) BETWEEN :inicio AND :fin) " 
                      
                        + "     )) "
                        + "ORDER BY aux.idUnidadMinera"
                        
        )
        List<PgimSupervisionAuxDTO> listarFiscalizacionesParaCalendario(@Param("inicio") Date inicio, @Param("fin") Date fin);

        /**
         * Permite listar las supervisiones de los contratos de acuerdo con los
         * criterios filtro especificados.
         * 
         * @param idContrato
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idProgramaSupervision
         * @param idtipoSupervision
         * @param coSupervision
         * @param nuExpedienteSiged
         * @param idFaseProceso
         * @param idPasoProceso
         * @param descPersonaDestino
         * @param descNoResponsable
         * @param usuarioAsignado
         * @param usuarioInteres
         * @param flagMisAsignaciones
         * @param textoBusqueda
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera,"
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.noFaseActual || ' - ' || aux.noPasoActual, aux.noTipoSupervision, "
                        + "aux.nuExpedienteSiged, aux.idAgenteSupervisado, aux.descContrato, aux.pgimContrato.idContrato, "
                        + "aux.idInstanciaPaso ) "
                        + "FROM PgimSupervisionAux aux " 
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND (:idContrato IS NULL OR aux.pgimContrato.idContrato = :idContrato) AND "
                        + "(:coUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :coUnidadMinera, '%')) ) "
                        + "AND (:noUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
                        + "OR LOWER(aux.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
                        + "AND (:coDocumentoIdentidad IS NULL OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
                        + "OR LOWER(aux.asNoRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "

                        + "AND (:idProgramaSupervision IS NULL OR aux.idEspecialidad = :idProgramaSupervision) "
                        + "AND (:idtipoSupervision IS NULL OR aux.idTipoSupervision = :idtipoSupervision) "

                        + "AND (:coSupervision IS NULL OR aux.coSupervision = :coSupervision) "
                        + "AND (:nuExpedienteSiged IS NULL OR aux.nuExpedienteSiged = :nuExpedienteSiged) "
                        + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                        + "AND (:idPasoProceso IS NULL OR aux.idPasoActual = :idPasoProceso) "

                        + "AND (:descPersonaDestino IS NULL OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) ) "

                        + "AND (:usuarioAsignado IS NULL OR LOWER(aux.usuarioAsignado) LIKE LOWER(CONCAT('%', :usuarioAsignado, '%')) ) "

                        + "AND (:descNoResponsable is null OR  aux.idInstanciaProceso in (select eipx.pgimInstanciaProces.idInstanciaProceso from PgimEqpInstanciaPro eipx, PgimPersonalOsi posix "
                        + ", PgimPersona perx where eipx.flEsResponsable='1' and eipx.esRegistro = '1' and posix.esRegistro = '1' and perx.esRegistro = '1' "
                        + "and eipx.pgimPersonalOsi.idPersonalOsi =posix.idPersonalOsi and posix.pgimPersona.idPersona = perx.idPersona "
                        + "and LOWER(perx.noPersona || ' ' || perx.apPaterno || ' ' || perx.apMaterno) LIKE LOWER(CONCAT('%', :descNoResponsable, '%'))"
                        + ")  ) "

                        + "AND (:usuarioInteres IS null OR aux.idInstanciaProceso in (select eipy.pgimInstanciaProces.idInstanciaProceso from PgimEqpInstanciaPro eipy "
                        + "where eipy.pgimRolProceso.idRolProceso=6 and eipy.esRegistro='1' and (eipy.pgimPersonalOsi.idPersonalOsi in (select posiy.idPersonalOsi from PgimPersonalOsi posiy where posiy.esRegistro = '1' and posiy.noUsuario=:usuarioInteres ) "
                        + "or eipy.pgimPersonalContrato.idPersonalContrato in (select pcony.idPersonalContrato from PgimPersonalContrato pcony where pcony.esRegistro = '1' and pcony.noUsuario=:usuarioInteres)) "
                        + ")) "
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.asNoRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noTipoSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.coSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noFaseActual) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))"
                        + "OR LOWER(aux.noPasoActual) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))"
                        + "OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))" + " )) "

        )
        Page<PgimSupervisionAuxDTO> listarSeguimientosSupervisiones(@Param("idContrato") Long idContrato,
                        @Param("coUnidadMinera") String coUnidadMinera, @Param("noUnidadMinera") String noUnidadMinera,
                        @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                        @Param("noRazonSocial") String noRazonSocial,
                        @Param("idProgramaSupervision") Long idProgramaSupervision,
                        @Param("idtipoSupervision") Long idtipoSupervision,
                        @Param("coSupervision") String coSupervision,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged,
                        @Param("idFaseProceso") Long idFaseProceso, @Param("idPasoProceso") Long idPasoProceso,
                        @Param("descPersonaDestino") String descPersonaDestino,
                        @Param("descNoResponsable") String descNoResponsable,
                        @Param("usuarioAsignado") String usuarioAsignado,
                        @Param("usuarioInteres") String usuarioInteres,
                        // @Param("flagMisAsignaciones") String flagMisAsignaciones,
                        @Param("textoBusqueda") String textoBusqueda, Pageable paginador);

        /**
         * Permite obtener la lista de supervisiones que coinciden con la palabra clave.
         * 
         * @param palabraClave Código de la supervisión.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.coSupervision, supe.pgimInstanciaProces.idInstanciaProceso ) " 
                        + "FROM PgimSupervision supe "
                        + "WHERE supe.esRegistro = '1' "
                        + "AND (:palabraClave IS NULL OR LOWER(supe.coSupervision) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
        List<PgimSupervisionDTO> listarPorPalabraClave(@Param("palabraClave") String palabraClave);

        /**
         * Permite obtener la lista de supervisiones que coinciden con la palabra clave.
         * 
         * @param palabraClave Código de la supervisión.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.coSupervision, supe.pgimUnidadMinera.noUnidadMinera ) "
                        + "FROM PgimSupervision supe "
                        + "WHERE supe.esRegistro = '1' "
                        + "AND (:coSupervision IS NULL OR LOWER(supe.coSupervision || supe.pgimUnidadMinera.noUnidadMinera) LIKE LOWER(CONCAT('%', :coSupervision, '%'))  )")
        List<PgimSupervisionDTO> listarPorCoSupervision(@Param("coSupervision") String coSupervision);

        /***
         * Permite obtener la lista de instancia de procesos por numero de expediente
         * que coinciden con la palabra clave.
         * 
         * @param palabraClave
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, inst.idInstanciaProceso, inst.nuExpedienteSiged ) "
                        + "FROM PgimSupervision supe, PgimInstanciaProces inst  "
                        + "WHERE supe.esRegistro = '1' AND supe.pgimInstanciaProces = inst AND (?1 IS NULL OR LOWER(inst.nuExpedienteSiged) LIKE LOWER(CONCAT('%', ?1, '%'))  "
                        + " ) ORDER BY inst.nuExpedienteSiged")
        List<PgimSupervisionDTO> listarPorPalabraClaveNuExpedienteSiged(@Param("palabraClave") String palabraClave);

        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "ps.idSupervision, ps.pgimInstanciaProces.idInstanciaProceso, ps.pgimUnidadMinera.idUnidadMinera, "
                        + "ps.coSupervision, ps.feInicioSupervision, ps.feFinSupervision, ps.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad, "
                        + "ps.flPropia, ps.pgimSubtipoSupervision.idSubtipoSupervision, ps.pgimPrgrmSupervision.idProgramaSupervision, "
                        + "ipaso.idInstanciaPaso ) " 
                        + "FROM PgimSupervision ps "                         
                        + "LEFT JOIN ps.pgimInstanciaProces ipro "
                        + "LEFT JOIN PgimInstanciaPaso ipaso ON (ipro = ipaso.pgimInstanciaProces "
                        + "		AND ipaso.flEsPasoActivo = '1' AND ipaso.esRegistro = '1' ) "
                        + "WHERE ps.esRegistro = '1' "
                        + "AND ps.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerSupervisionByIdSupervision(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado(" + "ps.coSupervision ) "
                        + "FROM PgimSupervision ps " + "WHERE ps.esRegistro = '1' "
                        + "AND SUBSTR(ps.coSupervision, 4, 4) = :anio "
                        // + "and rownum = 1 "
                        + "order by ps.idSupervision desc ")
        List<PgimSupervisionDTO> obtenerListaCoSupervision(@Param("anio") String anio);

        /**
         * Obtener las propiedades de la supervision por el idSupervision
         * 
         * @param idSupervision identificador de la supervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.pgimInstanciaProces.idInstanciaProceso, supe.pgimInstanciaProces.nuExpedienteSiged, "
                        + "supe.pgimUnidadMinera.idUnidadMinera, supe.pgimUnidadMinera.noUnidadMinera, aux.asNoRazonSocial, "
                        + "supe.coSupervision, supe.feInicioSupervision, supe.feFinSupervision, "
                        + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                        + "aux.pgimContrato.idContrato, aux.descContrato, supe.flPropia, "
                        + "supe.pgimSubtipoSupervision.idSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro || ' - ' || supe.pgimSubtipoSupervision.deSubtipoSupervision, supe.pgimPrgrmSupervision.idProgramaSupervision, "
                        + "aux.idAgenteSupervisado, supe.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad, supe.deTdrObjetivoTexto, "
                        + "supe.deTdrAlcanceTexto, supe.deTdrMetodologiaTexto, supe.deTdrInformeSupervTexto, "
                        + "supe.deTdrHonorariosProf, supe.deObservacionInicioSuperT, supe.deObservacionFinSuperT, "
                        + "mosu.idMotivoSupervision, mosu.deMotivoSupervision, supe.pgimPrgrmSupervision.pgimEspecialidad.noEspecialidad, "
                        + "supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, aux.idTipoSupervision, supe.flRegistraRiesgos, "
                        + "supe.flFeEnvio, aux.nuAlertas, aux.nuIntervaloAlertas, "
                        + "aux.idInstanciaPaso, aux.asCoDocumentoIdentidad, "
                        + "supe.flNoIniciadaAfiscalizado "
                        + ") "
                        + "FROM PgimSupervisionAux aux "
                        + "     INNER JOIN aux.pgimSupervision supe "
                        + "     LEFT OUTER JOIN supe.pgimMotivoSupervision mosu "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND supe.esRegistro = '1' "
                        + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerSupervisionPorId(@Param("idSupervision") Long idSupervision);


        /**
         * Obtener las propiedades de la asignacion de la supervisión por el
         * idSupervision
         * 
         * @param idSupervision identificador de la supervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                + "supe.coSupervision, supe.idSupervision, supe.pgimInstanciaProces.idInstanciaProceso "
                + ") "
                + "FROM PgimSupervision supe " 
                + "WHERE 1 = 1 "
                + "AND supe.esRegistro = '1' "
                + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerAsignacionSupervisionPorId(@Param("idSupervision") Long idSupervision);

        /**
         * Obtener las propiedades para la tarjeta de información de la supervision en
         * el FrontEnd
         * 
         * @param idSupervision identificador de la supervisión.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, inpr.nuExpedienteSiged, unmi.idUnidadMinera, "
                        + "unmi.noUnidadMinera, aux.asNoRazonSocial, supe.coSupervision, "
                        + "supe.feInicioSupervision, supe.feFinSupervision, tisu.noValorParametro, "
                        + "aux.descContrato, aux.descPrograma, supe.deObservacionInicioSuperT, "
                        + "supe.deObservacionFinSuperT, aux.idTipoSupervision, aux.flFeEnvio, "
                        + "supe.flPropia, aux.pgimContrato.idContrato, aux.idAgenteSupervisado, "
                        + "supe.pgimMotivoSupervision.idMotivoSupervision, supe.flNoIniciadaAfiscalizado "
                        + ") "
                        + "FROM PgimSupervisionAux aux "
                        + "     INNER JOIN aux.pgimSupervision supe "
                        + "     INNER JOIN supe.pgimInstanciaProces inpr "
                        + "     INNER JOIN supe.pgimUnidadMinera unmi "
                        + "     INNER JOIN supe.pgimSubtipoSupervision.tipoSupervision tisu "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND supe.esRegistro = '1' "
                        + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerSupervision(@Param("idSupervision") Long idSupervision);

        /**
         * Obtener el registro de correlativo (PgimCorrelativoSuperv) para el año
         * correspondiente
         * 
         * @param anio
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCorrelativoSupervDTOResultado( "
                        + "co.idCorrelativoSuperv, co.nuAnioCorrelativo, co.seUltimoCorrelativo "
                        + ",co.esRegistro, co.usCreacion, co.ipCreacion, "
                        + "co.feCreacion, co.usActualizacion, co.ipActualizacion, co.feActualizacion ) "
                        + "FROM PgimCorrelativoSuperv co " + "WHERE co.esRegistro = '1' "
                        + "AND co.nuAnioCorrelativo = :anio ")
        PgimCorrelativoSupervDTO obtenerCorrelativoByAnio(@Param("anio") Long anio);

        /***
         * permite obtener la lista de supervisiones traslapadas que estén aún en la fase de Fiscalización de campo.
         * @param idUnidadMinera
         * @param feInicioSupervision
         * @param feFinSupervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.coSupervision, supe.pgimInstanciaProces.idInstanciaProceso, "
                        + "ipaso.idInstanciaPaso ) " 
                        + "FROM PgimSupervision supe "
                        + "INNER JOIN supe.pgimInstanciaProces ipro "
                        + "INNER JOIN PgimInstanciaPaso ipaso ON (ipro = ipaso.pgimInstanciaProces "
                        + "		AND ipaso.flEsPasoActivo = '1' AND ipaso.esRegistro = '1' ) "
                        + "WHERE supe.esRegistro = '1' " 
                        + "AND supe.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
                        + "AND (supe.feInicioSupervision BETWEEN :feInicioSupervision AND :feFinSupervision  "
                        + "OR supe.feFinSupervision BETWEEN :feInicioSupervision AND :feFinSupervision) ")
        List<PgimSupervisionDTO> listarSupervisionesTraslapadas(@Param("idUnidadMinera") Long idUnidadMinera,
                        @Param("feInicioSupervision") Date feInicioSupervision,
                        @Param("feFinSupervision") Date feFinSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.coSupervision, supe.pgimInstanciaProces.idInstanciaProceso ) " 
                        + "FROM PgimSupervision supe "
                        + "WHERE supe.esRegistro = '1' " 
                        + "AND supe.idSupervision = :idSupervision "
                        + "AND supe.feInicioSupervision > :feInicioRealSupervision ")
        List<PgimSupervisionDTO> listarSupervisionesFechaInicioReal(@Param("idSupervision") Long idSupervision,
                        @Param("feInicioRealSupervision") Date feInicioRealSupervision);

        /**
         * Obtener registro de supervisión a través del idInstanciaProceso
         * 
         * @param idInstanciaProceso.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.pgimInstanciaProces.idInstanciaProceso, supe.pgimInstanciaProces.nuExpedienteSiged, "
                        + "supe.pgimUnidadMinera.idUnidadMinera, supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.coUnidadMinera, "
                        + "supe.pgimUnidadMinera.tipoUnidadMinera.noValorParametro, aux.asCoDocumentoIdentidad, aux.asNoRazonSocial, "
                        + "supe.coSupervision, supe.feInicioSupervision, supe.feFinSupervision,  "
                        + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                        + "aux.pgimContrato.idContrato, aux.descContrato, supe.flPropia, "
                        + "supe.pgimSubtipoSupervision.idSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, supe.pgimPrgrmSupervision.idProgramaSupervision, "
                        + "aux.idAgenteSupervisado, supe.pgimPrgrmSupervision.pgimEspecialidad.noEspecialidad, supe.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad, "
                        + "supe.pgimMotivoSupervision.idMotivoSupervision, supe.flNoIniciadaAfiscalizado, supe.flPreexistenteF2F1 "
                        + ") "
                        + "FROM PgimSupervisionAux aux "
                        + "     INNER JOIN aux.pgimSupervision supe "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND supe.esRegistro = '1' "
                        + "AND supe.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso ")
        PgimSupervisionDTO obtenerSupervisionByidInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);

        /**
         * Obtener registro de supervisión a través del identificador interno de la supervisión
         * 
         * @param idSupervision.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.pgimInstanciaProces.idInstanciaProceso, supe.pgimInstanciaProces.nuExpedienteSiged, "
                        + "supe.pgimUnidadMinera.idUnidadMinera, supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.coUnidadMinera, "
                        + "supe.pgimUnidadMinera.tipoUnidadMinera.noValorParametro, aux.asCoDocumentoIdentidad, aux.asNoRazonSocial, "
                        + "supe.coSupervision, supe.feInicioSupervision, supe.feFinSupervision,  "
                        + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                        + "aux.pgimContrato.idContrato, aux.descContrato, supe.flPropia, "
                        + "supe.pgimSubtipoSupervision.idSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, supe.pgimPrgrmSupervision.idProgramaSupervision, "
                        + "aux.idAgenteSupervisado, supe.pgimPrgrmSupervision.pgimEspecialidad.noEspecialidad, supe.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad, "
                        + "supe.pgimMotivoSupervision.idMotivoSupervision, supe.flNoIniciadaAfiscalizado, supe.flPreexistenteF2F1 "
                        + ") "
                        + "FROM PgimSupervisionAux aux "
                        + "     INNER JOIN aux.pgimSupervision supe "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND supe.esRegistro = '1' "
                        + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerSupervisionByIdSupervisionCompleta(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.idSupervision, supe.pgimInstanciaProces.idInstanciaProceso, supe.pgimInstanciaProces.nuExpedienteSiged, "
                        + "supe.pgimUnidadMinera.idUnidadMinera, supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.coUnidadMinera, "
                        + "supe.pgimUnidadMinera.tipoUnidadMinera.noValorParametro, aux.asCoDocumentoIdentidad, aux.asNoRazonSocial, "
                        + "supe.coSupervision, supe.feInicioSupervision, supe.feFinSupervision,  "
                        + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                        + "aux.pgimContrato.idContrato, aux.descContrato, supe.flPropia, "
                        + "supe.pgimSubtipoSupervision.idSubtipoSupervision, supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, supe.pgimPrgrmSupervision.idProgramaSupervision, "
                        + "aux.idAgenteSupervisado, supe.pgimPrgrmSupervision.pgimEspecialidad.noEspecialidad, supe.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad, "
                        + "supe.pgimMotivoSupervision.idMotivoSupervision, supe.flNoIniciadaAfiscalizado, supe.flPreexistenteF2F1 "
                        + ") "
                        + "FROM PgimSupervisionAux aux "
                        + "     INNER JOIN aux.pgimSupervision supe "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND supe.esRegistro = '1' "
                        + "AND supe.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso ")
        List<PgimSupervisionDTO> listarSupervisionesPorInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "UPPER(supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro), UPPER(supe.pgimPrgrmSupervision.pgimEspecialidad.noEspecialidad), "
                        + "ppas.noRazonSocial, pum.noUnidadMinera, pcto.nuContrato, ppes.noRazonSocial, "
                        + "TO_CHAR(supe.feInicioSupervision, 'DD/MM/YYYY'), "
                        + "(SELECT COUNT(*) FROM PgimEqpInstanciaPro peip "
                        + "WHERE peip.pgimInstanciaProces.idInstanciaProceso = supe.pgimInstanciaProces.idInstanciaProceso "
                        + "AND peip.pgimRolProceso.idRolProceso = 4 AND peip.esRegistro = '1'), "
                        + "supe.deTdrObjetivoTexto,supe.deTdrAlcanceTexto,supe.deTdrMetodologiaTexto,supe.deTdrInformeSupervTexto,supe.deTdrHonorariosProf, "
                        + "(CASE when supe.pgimUnidadMinera.plntaBeneficioDestino.idUnidadMinera is null  "
                        + "THEN ' ' " + "ELSE (SELECT ssum.noUnidadMinera FROM PgimUnidadMinera ssum "
                        + "WHERE ssum.idUnidadMinera = supe.pgimUnidadMinera.plntaBeneficioDestino.idUnidadMinera) END) ) "
                        + "FROM PgimSupervision supe "
                        + "LEFT JOIN PgimUnidadMinera pum ON supe.pgimUnidadMinera.idUnidadMinera = pum.idUnidadMinera "
                        + "LEFT JOIN PgimAgenteSupervisado pas ON pum.pgimAgenteSupervisado.idAgenteSupervisado = pas.idAgenteSupervisado "
                        + "LEFT JOIN PgimPersona ppas ON pas.pgimPersona.idPersona = ppas.idPersona "
                        + "LEFT JOIN PgimConsumoContra pcc ON supe.idSupervision = pcc.pgimSupervision.idSupervision "
                        + "LEFT JOIN PgimContrato pcto ON pcc.pgimContrato.idContrato = pcto.idContrato "
                        + "LEFT JOIN PgimEmpresaSupervisora pes ON pcto.pgimEmpresaSupervisora.idEmpresaSupervisora = pes.idEmpresaSupervisora "
                        + "LEFT JOIN PgimPersona ppes ON pes.pgimPersona.idPersona = ppes.idPersona "
                        + "WHERE supe.esRegistro = '1' " + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerValoresTDR(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "ppas.noRazonSocial, pum.noUnidadMinera, ppes.noRazonSocial, "
                        + "TO_CHAR(supe.feInicioSupervisionReal, 'DD/MM/YYYY'), TO_CHAR(supe.feFinSupervisionReal, 'DD/MM/YYYY'), "
                        + "TO_CHAR(supe.feInicioSupervisionReal, 'HH24:MI:SS'), TO_CHAR(supe.feFinSupervisionReal, 'HH24:MI:SS'),"
                        + "(CASE when supe.pgimUnidadMinera.plntaBeneficioDestino.idUnidadMinera IS NULL  "
                        + "THEN ' ' " + "ELSE (SELECT ssum.noUnidadMinera FROM PgimUnidadMinera ssum "
                        + "WHERE ssum.idUnidadMinera = supe.pgimUnidadMinera.plntaBeneficioDestino.idUnidadMinera) END), "
                        + "supe.deObservacionFinSuperT, NVL(supe.pgimInstanciaProces.nuExpedienteSiged,' ') " + " ) "
                        + "FROM PgimSupervision supe "
                        + "LEFT JOIN PgimUnidadMinera pum ON supe.pgimUnidadMinera.idUnidadMinera = pum.idUnidadMinera "
                        + "LEFT JOIN PgimAgenteSupervisado pas ON pum.pgimAgenteSupervisado.idAgenteSupervisado = pas.idAgenteSupervisado "
                        + "LEFT JOIN PgimPersona ppas ON pas.pgimPersona.idPersona = ppas.idPersona "
                        + "LEFT JOIN PgimConsumoContra pcc ON supe.idSupervision = pcc.pgimSupervision.idSupervision "
                        + "LEFT JOIN PgimContrato pcto ON pcc.pgimContrato.idContrato = pcto.idContrato "
                        + "LEFT JOIN PgimEmpresaSupervisora pes ON pcto.pgimEmpresaSupervisora.idEmpresaSupervisora = pes.idEmpresaSupervisora "
                        + "LEFT JOIN PgimPersona ppes ON pes.pgimPersona.idPersona = ppes.idPersona "
                        + "WHERE supe.esRegistro = '1' " + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerValoresActaSupervision(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "ppas.noRazonSocial, pum.noUnidadMinera, ppes.noRazonSocial, "
                        + "TO_CHAR(supe.feInicioSupervisionReal, 'DD/MM/YYYY'), TO_CHAR(supe.feInicioSupervisionReal, 'HH24:MI:SS'), "
                        + "(CASE when supe.pgimUnidadMinera.plntaBeneficioDestino.idUnidadMinera IS NULL  "
                        + "THEN ' ' " + "ELSE (SELECT ssum.noUnidadMinera FROM PgimUnidadMinera ssum "
                        + "WHERE ssum.idUnidadMinera = supe.pgimUnidadMinera.plntaBeneficioDestino.idUnidadMinera) END), "
                        + "supe.deObservacionInicioSuperT, supe.pgimInstanciaProces.nuExpedienteSiged " + ") "
                        + "FROM PgimSupervision supe "
                        + "LEFT JOIN PgimUnidadMinera pum ON supe.pgimUnidadMinera.idUnidadMinera = pum.idUnidadMinera "
                        + "LEFT JOIN PgimAgenteSupervisado pas ON pum.pgimAgenteSupervisado.idAgenteSupervisado = pas.idAgenteSupervisado "
                        + "LEFT JOIN PgimPersona ppas ON pas.pgimPersona.idPersona = ppas.idPersona "
                        + "LEFT JOIN PgimConsumoContra pcc ON supe.idSupervision = pcc.pgimSupervision.idSupervision "
                        + "LEFT JOIN PgimContrato pcto ON pcc.pgimContrato.idContrato = pcto.idContrato "
                        + "LEFT JOIN PgimEmpresaSupervisora pes ON pcto.pgimEmpresaSupervisora.idEmpresaSupervisora = pes.idEmpresaSupervisora "
                        + "LEFT JOIN PgimPersona ppes ON pes.pgimPersona.idPersona = ppes.idPersona "
                        + "WHERE supe.esRegistro = '1' " + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerValoresActaInicioSupervision(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "supe.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.noRazonSocial, " 
                        + "supe.pgimUnidadMinera.pgimAgenteSupervisado.pgimPersona.coDocumentoIdentidad, "
                        + "supe.pgimUnidadMinera.coUnidadMinera,supe.pgimUnidadMinera.noUnidadMinera, "
                        + "pper.noRazonSocial, pper.coDocumentoIdentidad, supe.pgimInstanciaProces.nuExpedienteSiged, "
                        + "supe.pgimUnidadMinera.idUnidadMinera ) "
                        + "FROM PgimSupervision supe "
                        + "LEFT JOIN PgimConsumoContra pcc ON supe.idSupervision = pcc.pgimSupervision.idSupervision "
                        + "LEFT JOIN PgimContrato pcto ON pcc.pgimContrato.idContrato = pcto.idContrato "
                        + "LEFT JOIN PgimEmpresaSupervisora pes ON pcto.pgimEmpresaSupervisora.idEmpresaSupervisora = pes.idEmpresaSupervisora "
                        + "LEFT JOIN PgimPersona pper ON pes.pgimPersona.idPersona = pper.idPersona "
                        + "WHERE supe.esRegistro = '1' " + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerValoresFichaHechosConstatados(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "pum.noUnidadMinera,pum.pgimAgenteSupervisado.pgimPersona.noRazonSocial, ps.coSupervision, "
                        + "psts.deSubtipoSupervision, pts.noValorParametro, ps.idSupervision, pum.idUnidadMinera, pd.idDocumento "
                        + ") " + "FROM PgimSupervision ps "
                        + "left join PgimSubtipoSupervision psts on ps.pgimSubtipoSupervision = psts "
                        + "left join PgimValorParametro pts on psts.tipoSupervision = pts "
                        + "left join PgimInstanciaProces pip on ps.pgimInstanciaProces = pip "
                        + "left join PgimDocumento pd on pd.pgimInstanciaProces = pip "
                        + "left join PgimUnidadMinera pum on ps.pgimUnidadMinera = pum "
                        + "left join PgimInstanciaPaso ppa on pip = ppa.pgimInstanciaProces "
                        + "WHERE ps.esRegistro = '1' " + "and pip.esRegistro = '1' " + "and pd.esRegistro = '1' "
                        + "and ppa.pgimRelacionPaso.idRelacionPaso = 25L ")
        List<PgimSupervisionDTO> obtenerActasPresentadasPendienteLiquidar();

        /**
         * Permite listar las supervisiones correspondientes a un contrato cuyos montos
         * de consumo de contrato se encuentran en el estadio "pre-comprometido"
         * 
         * @param idContrato
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, " + "aux.coUnidadMinera, aux.noUnidadMinera,"
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, " + "aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.noFaseActual || ' - ' || aux.noPasoActual, "
                        + "aux.noTipoSupervision, "
                        + "aux.nuExpedienteSiged, aux.idAgenteSupervisado, aux.descContrato, "
                        + "aux.descPrograma,aux.deSubtipoSupervision,aux.moConsumoContrato,"
                        + "aux.idInstanciaProceso ) "
                        + "FROM PgimSupervisionAux aux " 
                        + "WHERE aux.flPasoActivo = '1' " 
                        + "AND aux.pgimContrato.idContrato = :idContrato "
                        + "AND EXISTS ( " 
                        + "SELECT 1 " 
                        + "FROM PgimItemConsumo icon "
                        + "WHERE icon.pgimConsumoContra.idConsumoContra = aux.pgimConsumoContra.idConsumoContra "
                        + "AND icon.tipoEstadioConsumo.coClaveTexto = :ESCON_PRE_CMPRMTDO "
                        + "AND icon.esRegistro = '1' AND icon.esVigente = '1' " 
                        + ") "
        )
        Page<PgimSupervisionAuxDTO> listarSupervisionesPrecomprometidasXcontrato(@Param("idContrato") Long idContrato,
        				Pageable paginador, @Param("ESCON_PRE_CMPRMTDO") String ESCON_PRE_CMPRMTDO);
        
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "ps.idSupervision, ps.pgimInstanciaProces.idInstanciaProceso, ps.pgimUnidadMinera.idUnidadMinera, "
                        + "ps.coSupervision, ps.feInicioSupervision, ps.feFinSupervision, ps.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad, "
                        + "ps.flPropia, ps.pgimSubtipoSupervision.idSubtipoSupervision, ps.pgimPrgrmSupervision.idProgramaSupervision, "
                        + "ipaso.idInstanciaPaso "
                        + ") "
                        + "FROM PgimSupervision ps "
                        + "LEFT JOIN ps.pgimInstanciaProces ipro "
                        + "LEFT JOIN PgimInstanciaPaso ipaso ON (ipro = ipaso.pgimInstanciaProces "
                        + "		AND ipaso.flEsPasoActivo = '1' AND ipaso.esRegistro = '1' ) "
                        + "WHERE 1 = 1 "
                        + "AND ps.idSupervision = ( "
                        + "SELECT super.idSupervision "
                        + "FROM PgimRankingUmGrupo rkgr "
                        + "     INNER JOIN rkgr.pgimRankingSupervision rksp "
                        + "     INNER JOIN rksp.pgimSupervision super "
                        + "WHERE 1 = 1 "
                        + "AND rkgr.idRankingUmGrupo = :idRankingUmGrupo "
                        + "AND rkgr.esRegistro='1' "
                        + "AND rksp.esRegistro='1' "
                        + "AND super.esRegistro='1' "
                        + ") "
                        + "AND ps.esRegistro = '1' ")
        PgimSupervisionDTO obtenerSupervisionByIdRankingUmGrupo(@Param("idRankingUmGrupo") Long idRankingUmGrupo);

        /**
         * Permite listar las supervisiones según los criterios de filtro aplicados,
         * usado en la tabla frontend del reporte correspondiente, de manera paginada
         * 
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idEspecialidad
         * @param idtipoSupervision
         * @param idFaseProceso
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.coUnidadMinera || ' - ' || aux.noUnidadMinera,"
                + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                + "aux.personaAsignada, aux.noFaseActual || ' - ' || aux.noPasoActual, aux.noTipoSupervision, "
                + "aux.nuExpedienteSiged, aux.idAgenteSupervisado, aux.descContrato, "
                + "aux.noDivisionSupervisora, aux.noFlPropia, supe.feInicioSupervisionReal, supe.feFinSupervisionReal "
                + " ) "
                + "FROM PgimSupervisionAux aux "
                + "     INNER JOIN aux.pgimSupervision supe " 
                + "WHERE aux.flPasoActivo = '1' "
                + "AND (:coUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) = LOWER(:coUnidadMinera) ) "
                + "AND (:noUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) = LOWER(:noUnidadMinera)  "
                + "OR LOWER(aux.noUnidadMinera) = LOWER(:noUnidadMinera))  "
                + "AND (:coDocumentoIdentidad IS NULL OR LOWER(aux.asCoDocumentoIdentidad) = LOWER(:coDocumentoIdentidad) ) "
                + "AND (:noRazonSocial IS NULL OR LOWER(aux.asCoDocumentoIdentidad) = LOWER(:noRazonSocial) "
                + "OR LOWER(aux.asNoRazonSocial) = LOWER(:noRazonSocial) ) "
				+ "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                + "AND (:idtipoSupervision IS NULL OR aux.idTipoSupervision = :idtipoSupervision) "
                + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "                
                + "AND aux.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO, :REPAS_FNLZAR_FLJO) "
		)
		Page<PgimSupervisionAuxDTO> listarSupervisionesReportePaginado(
				@Param("coUnidadMinera") String coUnidadMinera,
                @Param("noUnidadMinera") String noUnidadMinera,
                @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                @Param("noRazonSocial") String noRazonSocial,
                @Param("idEspecialidad") Long idEspecialidad,
                @Param("idtipoSupervision") Long idtipoSupervision,
                @Param("idFaseProceso") Long idFaseProceso, 
                Pageable paginador, 
                @Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO,
                @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO);
        
        
        /**
         * Permite listar las supervisiones según los criterios de filtro aplicados,
         * usado en la descarga del reporte correspondiente
         * 
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idEspecialidad
         * @param idtipoSupervision
         * @param idFaseProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera,"
                + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                + "aux.personaAsignada, aux.usuarioAsignado, aux.noFaseActual || ' - ' || aux.noPasoActual, "
                + "aux.noTipoSupervision, aux.deSubtipoSupervision, aux.nuExpedienteSiged, "
                + "aux.idAgenteSupervisado, aux.descContrato, aux.noDivisionSupervisora, "
                + "aux.noFlPropia, aux.flFeEnvio, aux.flLeido, "
                + "aux.feLectura, aux.feInstanciaPaso, aux.noPersonaOrigen, "
                + "aux.flPasoActivo, aux.deMensaje, aux.nuAlertas, "
                + "aux.nuIntervaloAlertas, aux.idInstanciaPaso, aux.flListoContinuar "
                + ") "
                + "FROM PgimSupervisionAux aux " 
                + "WHERE aux.flPasoActivo = '1' "
                + "AND (:coUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) = LOWER(:coUnidadMinera) ) "
                + "AND (:noUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) = LOWER(:noUnidadMinera)  "
                + "OR LOWER(aux.noUnidadMinera) = LOWER(:noUnidadMinera))  "
                + "AND (:coDocumentoIdentidad IS NULL OR LOWER(aux.asCoDocumentoIdentidad) = LOWER(:coDocumentoIdentidad) ) "
                + "AND (:noRazonSocial IS NULL OR LOWER(aux.asCoDocumentoIdentidad) = LOWER(:noRazonSocial) "
                + "OR LOWER(aux.asNoRazonSocial) = LOWER(:noRazonSocial) ) "

				+ "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                + "AND (:idtipoSupervision IS NULL OR aux.idTipoSupervision = :idtipoSupervision) "

                + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                
				+ "AND aux.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO, "
				+ "								  :REPAS_FNLZAR_FLJO) "				
                + "ORDER BY aux.noEspecialidad asc, aux.coSupervision desc "
		)
		List<PgimSupervisionAuxDTO> listarSupervisionesReporte(
				@Param("coUnidadMinera") String coUnidadMinera,
                @Param("noUnidadMinera") String noUnidadMinera,
                @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                @Param("noRazonSocial") String noRazonSocial,
                @Param("idEspecialidad") Long idEspecialidad,
                @Param("idtipoSupervision") Long idtipoSupervision,
                @Param("idFaseProceso") Long idFaseProceso,
                @Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO,
                @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO);
        
        /**
         * Permite listar las supervisiones según los criterios de filtro aplicados,
         * usado en la descarga del reporte correspondiente
         * 
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idEspecialidad
         * @param idtipoSupervision
         * @param idFaseProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.coUnidadMinera || ' - ' || aux.noUnidadMinera, "
                + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                + "aux.personaAsignada, aux.noFaseActual || ' - ' || aux.noPasoActual, aux.noTipoSupervision, "
                + "aux.nuExpedienteSiged, aux.idAgenteSupervisado, aux.descContrato,"
                + "aux.noDivisionSupervisora, aux.noFlPropia, supe.feInicioSupervisionReal, supe.feFinSupervisionReal"
                + " ) "
                + "FROM PgimSupervisionAux aux " 
                + "INNER JOIN aux.pgimSupervision supe "
                + "WHERE aux.flPasoActivo = '1' "
                + "AND (:coUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) = LOWER(:coUnidadMinera) ) "
                + "AND (:noUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) = LOWER(:noUnidadMinera)  "
                + "OR LOWER(aux.noUnidadMinera) = LOWER(:noUnidadMinera))  "
                + "AND (:coDocumentoIdentidad IS NULL OR LOWER(aux.asCoDocumentoIdentidad) = LOWER(:coDocumentoIdentidad) ) "
                + "AND (:noRazonSocial IS NULL OR LOWER(aux.asCoDocumentoIdentidad) = LOWER(:noRazonSocial) "
                + "OR LOWER(aux.asNoRazonSocial) = LOWER(:noRazonSocial) ) "

				+ "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                + "AND (:idtipoSupervision IS NULL OR aux.idTipoSupervision = :idtipoSupervision) "

                + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                
				+ "AND aux.idTipoRelacion NOT IN (:REPAS_CNCLAR_FLJO, "
				+ "								  :REPAS_FNLZAR_FLJO) "
		)
		List<PgimSupervisionAuxDTO> listarSupervisionesReporteExportar(
				@Param("coUnidadMinera") String coUnidadMinera,
                @Param("noUnidadMinera") String noUnidadMinera,
                @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                @Param("noRazonSocial") String noRazonSocial,
                @Param("idEspecialidad") Long idEspecialidad,
                @Param("idtipoSupervision") Long idtipoSupervision,
                @Param("idFaseProceso") Long idFaseProceso,
                Sort sort,
                @Param("REPAS_CNCLAR_FLJO") Long REPAS_CNCLAR_FLJO,
                @Param("REPAS_FNLZAR_FLJO") Long REPAS_FNLZAR_FLJO);
        
        
        /**
         * Permite listar las supervisiones según el año ingresado,
         * usado en la tabla frontend del reporte correspondiente
         * 
         * @param anio
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera,"
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.noFaseActual || ' - ' || aux.noPasoActual, aux.noTipoSupervision, "
                        + "aux.idTipoSupervision, aux.nuExpedienteSiged, aux.idAgenteSupervisado, "
                        + "aux.descContrato"
                        + " ) "
                        + "FROM PgimSupervisionAux aux "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND (YEAR(aux.feInicioSupervision)||'' = :anio ) "
                        + "ORDER BY aux.noEspecialidad asc, aux.coSupervision desc ")
        List<PgimSupervisionAuxDTO> listarSupervisionxAnioReporte(
                        @Param("anio") String anio);

        /**
         * Permite obtener una lista preparada de supervisiones por especialidad,
         * según el año ingresado, usado en la descarga del reporte correspondiente
         * 
         * @param anio
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera,"
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.usuarioAsignado, aux.noFaseActual || ' - ' || aux.noPasoActual, "
                        + "aux.noTipoSupervision, aux.deSubtipoSupervision, aux.nuExpedienteSiged, "
                        + "aux.idAgenteSupervisado, aux.descContrato, aux.noDivisionSupervisora, "
                        + "aux.noFlPropia, aux.flFeEnvio, aux.flLeido, "
                        + "aux.feLectura, aux.feInstanciaPaso, aux.noPersonaOrigen, "
                        + "aux.flPasoActivo, aux.deMensaje, aux.nuAlertas, "
                        + "aux.nuIntervaloAlertas, aux.idInstanciaPaso, aux.flListoContinuar "
                        + ") "
                        + "FROM PgimSupervisionAux aux "
                        + "WHERE aux.flPasoActivo = '1' "
                        + "AND (YEAR(aux.feInicioSupervision)||'' = :anio ) "
                        + "ORDER BY aux.noEspecialidad asc, aux.coSupervision desc ")
        List<?> listarSupervisionxAnioEspecReporte(
                        @Param("anio") String anio);

        /**
         * Permite listar las supervisiones de acuerdo con los criterios filtro
         * especificados, todos (sin paginar).
         * 
         * @param coUnidadMinera
         * @param noUnidadMinera
         * @param coDocumentoIdentidad
         * @param noRazonSocial
         * @param idProgramaSupervision
         * @param idtipoSupervision
         * @param coSupervision
         * @param nuExpedienteSiged
         * @param idFaseProceso
         * @param idPasoProceso
         * @param descPersonaDestino
         * @param descNoResponsable
         * @param usuarioAsignado
         * @param usuarioInteres
         * @param flagMisAsignaciones
         * @param textoBusqueda
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                        + "aux.pgimSupervision.idSupervision, aux.coUnidadMinera, aux.noUnidadMinera," 
                        + "aux.coSupervision, aux.feInicioSupervision, aux.feFinSupervision, "
                        + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad, aux.noEspecialidad, "
                        + "aux.personaAsignada, aux.usuarioAsignado, aux.noFaseActual || ' - ' || aux.noPasoActual, "
                        + "aux.noTipoSupervision, aux.deSubtipoSupervision, aux.nuExpedienteSiged, "
                        + "aux.idAgenteSupervisado, aux.descContrato, aux.noDivisionSupervisora, "
                        + "aux.noFlPropia, aux.flFeEnvio, aux.flLeido, "
                        + "aux.feLectura, aux.feInstanciaPaso, aux.noPersonaOrigen, "
                        + "aux.flPasoActivo, aux.deMensaje, aux.nuAlertas, "
                        + "aux.nuIntervaloAlertas, aux.idInstanciaPaso, aux.flListoContinuar "
                        + ") "
                        + "FROM PgimSupervisionAux aux " 
                        + "WHERE "
                        + "(:coUnidadMinera IS NULL OR aux.coUnidadMinera = :coUnidadMinera) "
                        + "AND (:noUnidadMinera IS NULL OR LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%'))  "
                        + "OR LOWER(aux.noUnidadMinera) LIKE LOWER(CONCAT('%', :noUnidadMinera, '%')) ) "
                        + "AND (:coDocumentoIdentidad IS NULL OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :coDocumentoIdentidad, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) "
                        + "OR LOWER(aux.asNoRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                        + "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                        + "AND (:idtipoSupervision IS NULL OR aux.idTipoSupervision = :idtipoSupervision) "
                        + "AND (:idDivisionSupervisora IS NULL OR aux.idDivisionSupervisora = :idDivisionSupervisora) "
                        + "AND (:flPropia IS NULL OR aux.flPropia = :flPropia) "
                        + "AND (:coSupervision IS NULL OR aux.coSupervision = :coSupervision) "
                        + "AND (:nuExpedienteSiged IS NULL OR aux.nuExpedienteSiged = :nuExpedienteSiged) "
                        + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                        + "AND (:idPasoProceso IS NULL OR aux.idPasoActual = :idPasoProceso) "
                        + "AND (:descNoResponsable is null OR  aux.idInstanciaProceso in (select eipx.pgimInstanciaProces.idInstanciaProceso from PgimEqpInstanciaPro eipx, PgimPersonalOsi posix "
                        + ", PgimPersona perx where eipx.flEsResponsable='1' and eipx.esRegistro = '1' and posix.esRegistro = '1' and perx.esRegistro = '1' "
                        + "and eipx.pgimPersonalOsi.idPersonalOsi =posix.idPersonalOsi and posix.pgimPersona.idPersona = perx.idPersona "
                        + "and LOWER(perx.noPersona || ' ' || perx.apPaterno || ' ' || perx.apMaterno) LIKE LOWER(CONCAT('%', :descNoResponsable, '%'))"
                        + ")  ) "
                        + "AND (:usuarioInteres IS NULL OR aux.idInstanciaProceso IN " 
                        +       "( " 
                        +       "SELECT eipy.pgimInstanciaProces.idInstanciaProceso " 
                        +       "FROM PgimEqpInstanciaPro eipy "
                        +       "WHERE eipy.pgimRolProceso.idRolProceso=6 " 
                        +       "AND eipy.esRegistro='1' " 
                        +       "AND ( " 
                        +               "eipy.pgimPersonalOsi.idPersonalOsi IN " 
                        +               "( " 
                        +               "SELECT posiy.idPersonalOsi " 
                        +               "FROM PgimPersonalOsi posiy " 
                        +               "WHERE posiy.esRegistro = '1' " 
                        +               "AND posiy.noUsuario = :usuarioInteres " 
                        +               ") "
                        +       "OR eipy.pgimPersonalContrato.idPersonalContrato IN " 
                        +               "( " 
                        +               "select pcony.idPersonalContrato " 
                        +               "FROM PgimPersonalContrato pcony " 
                        +               "WHERE pcony.esRegistro = '1' " 
                        +               "AND pcony.noUsuario = :usuarioInteres " 
                        +               ") " 
                        +           ") "
                        +       ") " 
                        +   ") "

                        // Mis asignaciones
                        + "AND ("
                        //- Todas las tareas = '0'
                        + "        (:flagMisAsignaciones = '0' AND aux.flPasoActivo = '1' AND (:descPersonaDestino IS NULL OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) )) "                         
                        //- Mis tareas = '1'
                        + "     OR (:flagMisAsignaciones = '1' AND aux.flPasoActivo = '1' AND LOWER(aux.usuarioAsignado) = LOWER(:usuarioAsignado) AND aux.idTipoRelacion NOT IN (291, 292)) " 
                        //- Tareas enviadas = '2'
                        + "     OR ("
                        + "         :flagMisAsignaciones = '2' " 
                        + "          AND LOWER(aux.noUsuarioOrigen) = LOWER(:usuarioOrigen) "
                        + "          AND ("
                        + "               :descPersonaDestino IS NULL "
                        + "               OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) "
                        + "              ) "
                        + "         )" 
                        + " ) "

                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(aux.coUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noUnidadMinera) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.asCoDocumentoIdentidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.asNoRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noTipoSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.coSupervision) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(aux.noFaseActual) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))"
                        + "OR LOWER(aux.noPasoActual) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))"
                        + "OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :textoBusqueda,'%'))" + " )) "

        )
        List<PgimSupervisionAuxDTO> listarSupervisionesList(@Param("coUnidadMinera") String coUnidadMinera,
                        @Param("noUnidadMinera") String noUnidadMinera,
                        @Param("coDocumentoIdentidad") String coDocumentoIdentidad,
                        @Param("noRazonSocial") String noRazonSocial,
                        @Param("idEspecialidad") Long idEspecialidad,
                        @Param("idtipoSupervision") Long idtipoSupervision,
                        @Param("idDivisionSupervisora") Long idDivisionSupervisora,
                        @Param("flPropia") String flPropia,
                        @Param("coSupervision") String coSupervision,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged,
                        @Param("idFaseProceso") Long idFaseProceso,
                        @Param("idPasoProceso") Long idPasoProceso,
                        @Param("descPersonaDestino") String descPersonaDestino,
                        @Param("descNoResponsable") String descNoResponsable,
                        @Param("usuarioOrigen") String usuarioOrigen,
                        @Param("usuarioAsignado") String usuarioAsignado,
                        @Param("usuarioInteres") String usuarioInteres,
                        @Param("flagMisAsignaciones") String flagMisAsignaciones,
                        @Param("textoBusqueda") String textoBusqueda,
                        Sort sort);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                + "supe.idSupervision, inpro.idInstanciaProceso, inpro.nuExpedienteSiged, "
                + "umi.idUnidadMinera, umi.noUnidadMinera, aux.asNoRazonSocial, "
                + "supe.coSupervision, supe.feInicioSupervision, supe.feFinSupervision,  "
                + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                + "aux.pgimContrato.idContrato, aux.descContrato, supe.flPropia, "
                + "stsup.idSubtipoSupervision, stsup.tipoSupervision.noValorParametro || ' - ' || stsup.deSubtipoSupervision, prsup.idProgramaSupervision, "
                + "aux.idAgenteSupervisado, prsup.pgimEspecialidad.idEspecialidad, supe.deTdrObjetivoTexto,supe.deTdrAlcanceTexto, "
                + "supe.deTdrMetodologiaTexto, supe.deTdrInformeSupervTexto, supe.deTdrHonorariosProf, "
                + "supe.deObservacionInicioSuperT, supe.deObservacionFinSuperT, mosu.idMotivoSupervision, "
                + "mosu.deMotivoSupervision, prsup.pgimEspecialidad.noEspecialidad, stsup.tipoSupervision.noValorParametro, "
                + "aux.idTipoSupervision, supe.flRegistraRiesgos, supe.flFeEnvio, "
                + "aux.nuAlertas, aux.nuIntervaloAlertas, aux.idInstanciaPaso, "
                + "aux.asCoDocumentoIdentidad, supe.flNoIniciadaAfiscalizado "
                + ") "
                + "FROM PgimSupervisionAux aux "
                + "     INNER JOIN aux.pgimSupervision supe "
                + "     LEFT OUTER JOIN supe.pgimMotivoSupervision mosu "
                + "     LEFT JOIN PgimInstanciaProces inpro ON inpro = supe.pgimInstanciaProces "
                + "     LEFT JOIN PgimUnidadMinera umi ON umi = supe.pgimUnidadMinera "
                + "     LEFT JOIN PgimSubtipoSupervision stsup ON stsup = supe.pgimSubtipoSupervision "
                + "     LEFT JOIN PgimPrgrmSupervision prsup ON prsup = supe.pgimPrgrmSupervision "
                + "WHERE aux.flPasoActivo = '1' " 
                + "AND supe.esRegistro = '1' " 
                + "AND umi.idUnidadMinera = :idUnidadMinera ")
        List<PgimSupervisionDTO> obtenerSupervisionPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                + "supe.idSupervision, aux.idUnidadMinera, aux.noUnidadMinera, aux.coSupervision, "
                + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                + "contr.idContrato, psubt.idSubtipoSupervision, psubt.deSubtipoSupervision, "
                + "mosu.idMotivoSupervision, mosu.deMotivoSupervision, aux.noEspecialidad, "
                + "aux.noTipoSupervision, aux.nuContrato, per.noRazonSocial, "
                + "aux.personaAsignada, aux.noFaseActual, aux.noPasoActual "
                + ") "
                + "FROM PgimSupervisionAux aux "
                + "     LEFT OUTER JOIN aux.pgimSupervision supe "
                + "     LEFT OUTER JOIN aux.pgimSubtipoSupervision psubt "
                + "     LEFT OUTER JOIN aux.pgimContrato contr "
                + "     LEFT OUTER JOIN contr.pgimEmpresaSupervisora esup "
                + "     LEFT OUTER JOIN esup.pgimPersona per "
                + "     LEFT OUTER JOIN supe.pgimMotivoSupervision mosu "
                + "WHERE aux.flPasoActivo = '1' " 
                + "AND supe.esRegistro = '1' " 
                + "AND aux.idUnidadMinera = :idUnidadMinera "
                + "AND (:coSupervision IS NULL OR aux.coSupervision = :coSupervision) "
                + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                + "AND (:idPasoProceso IS NULL OR aux.idPasoActual = :idPasoProceso) "
                + "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                + "AND (:descPersonaDestino IS NULL OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) )"
                + "AND (:nuContrato IS NULL OR LOWER(contr.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
                + "AND ( "
                + "      (((:feInicioSupervisionReal IS NULL AND :feFinSupervisionReal IS NULL) OR supe.feInicioSupervisionReal BETWEEN :feInicioSupervisionReal AND :feFinSupervisionReal)" 
                + "      OR ((:feInicioSupervisionReal IS NULL AND :feFinSupervisionReal IS NULL) OR supe.feFinSupervisionReal BETWEEN :feInicioSupervisionReal AND :feFinSupervisionReal) ) "
                + ") "
                + "ORDER BY aux.coSupervision DESC "
                )
        List<PgimSupervisionDTO> obtenerFiscalizacionPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera,
        		@Param("coSupervision") String coSupervision,
        		@Param("idEspecialidad") Long idEspecialidad,
        		@Param("nuContrato") String nuContrato,
        		@Param("feInicioSupervisionReal") Date feInicioSupervisionReal,
        		@Param("feFinSupervisionReal") Date feFinSupervisionReal,
        		@Param("descPersonaDestino") String descPersonaDestino,
        		@Param("idFaseProceso") Long idFaseProceso,
        		@Param("idPasoProceso") Long idPasoProceso);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                + "supe.idSupervision, aux.idUnidadMinera, aux.noUnidadMinera, aux.coSupervision, "
                + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                + "contr.idContrato, psubt.idSubtipoSupervision, psubt.deSubtipoSupervision, "
                + "mosu.idMotivoSupervision, mosu.deMotivoSupervision, aux.noEspecialidad, "
                + "aux.noTipoSupervision, aux.nuContrato, per.noRazonSocial, "
                + "aux.personaAsignada, aux.noFaseActual, aux.noPasoActual, aux.idInstanciaPaso "
                + ") "
                + "FROM PgimSupervisionAux aux "
                + "     LEFT OUTER JOIN aux.pgimSupervision supe "
                + "     LEFT OUTER JOIN aux.pgimSubtipoSupervision psubt "
                + "     LEFT OUTER JOIN aux.pgimContrato contr "
                + "     LEFT OUTER JOIN contr.pgimEmpresaSupervisora esup "
                + "     LEFT OUTER JOIN esup.pgimPersona per "
                + "     LEFT OUTER JOIN supe.pgimMotivoSupervision mosu "
                + "WHERE aux.flPasoActivo = '1' " 
                + "AND supe.esRegistro = '1' " 
                + "AND aux.idUnidadMinera = :idUnidadMinera "
                + "AND (:coSupervision IS NULL OR aux.coSupervision = :coSupervision) "
                + "AND (:idFaseProceso IS NULL OR aux.idFaseActual = :idFaseProceso ) "
                + "AND (:idPasoProceso IS NULL OR aux.idPasoActual = :idPasoProceso) "
                + "AND (:idEspecialidad IS NULL OR aux.idEspecialidad = :idEspecialidad) "
                + "AND (:descPersonaDestino IS NULL OR LOWER(aux.personaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) )"
                + "AND (:nuContrato IS NULL OR LOWER(contr.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
                + "AND ( "
                + "      (((:feInicioSupervisionReal IS NULL AND :feFinSupervisionReal IS NULL) OR supe.feInicioSupervisionReal BETWEEN :feInicioSupervisionReal AND :feFinSupervisionReal)" 
                + "      OR ((:feInicioSupervisionReal IS NULL AND :feFinSupervisionReal IS NULL) OR supe.feFinSupervisionReal BETWEEN :feInicioSupervisionReal AND :feFinSupervisionReal) ) "
                + ") "
                )
        Page<PgimSupervisionDTO> obtenerFiscalizacionPorUnidadMineraPaginado(
        		@Param("idUnidadMinera") Long idUnidadMinera,
        		@Param("coSupervision") String coSupervision,
        		@Param("idEspecialidad") Long idEspecialidad,
        		@Param("nuContrato") String nuContrato,
        		@Param("feInicioSupervisionReal") Date feInicioSupervisionReal,
        		@Param("feFinSupervisionReal") Date feFinSupervisionReal,
        		@Param("descPersonaDestino") String descPersonaDestino,
        		@Param("idFaseProceso") Long idFaseProceso,
        		@Param("idPasoProceso") Long idPasoProceso,
                Pageable paginador
        		);        

        /**
         * Permite obtener datos de cabecera de una supervisión buscada por numero de
         * expediente
         * 
         * @param nuExpedienteSiged
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTOResultado("
                + "aux.pgimSupervision.idSupervision, "
                + "aux.nuExpedienteSiged, "
                + "aux.idUnidadMinera, aux.coUnidadMinera, aux.noUnidadMinera, "
                + "aux.asNoRazonSocial, aux.asCoDocumentoIdentidad )"
                + "FROM PgimSupervisionAux aux "
                + "     INNER JOIN aux.pgimSupervision supe "
                + "WHERE aux.flPasoActivo = '1' "
                + "AND supe.esRegistro = '1' "
                + "AND aux.nuExpedienteSiged = :nuExpedienteSiged ")
        PgimSupervisionAuxDTO obtenerCabecSupervisionPorExpediente(
                        @Param("nuExpedienteSiged") String nuExpedienteSiged);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado( "
                        + "supe.idSupervision ) "
                        + "FROM PgimSupervision supe "
                        + "INNER JOIN PgimInstanciaProces inpro ON supe.pgimInstanciaProces = inpro "
                        + "INNER JOIN PgimInstanciaPaso inpa ON inpro = inpa.pgimInstanciaProces AND inpa.flEsPasoActivo = '1' AND inpa.esRegistro = '1' "
                        + "WHERE supe.esRegistro = '1' "
                        + "AND supe.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
                        + "AND inpa.pgimRelacionPaso.tipoRelacion.idValorParametro <> 291L ")
        List<PgimSupervisionDTO> listarSupervisionPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);

        /**
         * Permite obtener datos de cabecera para el formato de revision antecedente
         * 
         * @param idSupervision
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                        + "UPPER(supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro), UPPER(supe.pgimPrgrmSupervision.pgimEspecialidad.noEspecialidad), "
                        + "ppas.noRazonSocial, pum.noUnidadMinera, pcto.nuContrato, ppes.noRazonSocial, supe.pgimInstanciaProces.idInstanciaProceso, supe.pgimInstanciaProces.nuExpedienteSiged ) "
                        + "FROM PgimSupervision supe "
                        + "LEFT JOIN PgimUnidadMinera pum ON supe.pgimUnidadMinera.idUnidadMinera = pum.idUnidadMinera "
                        + "LEFT JOIN PgimAgenteSupervisado pas ON pum.pgimAgenteSupervisado.idAgenteSupervisado = pas.idAgenteSupervisado "
                        + "LEFT JOIN PgimPersona ppas ON pas.pgimPersona.idPersona = ppas.idPersona "
                        + "LEFT JOIN PgimConsumoContra pcc ON supe.idSupervision = pcc.pgimSupervision.idSupervision "
                        + "LEFT JOIN PgimContrato pcto ON pcc.pgimContrato.idContrato = pcto.idContrato "
                        + "LEFT JOIN PgimEmpresaSupervisora pes ON pcto.pgimEmpresaSupervisora.idEmpresaSupervisora = pes.idEmpresaSupervisora "
                        + "LEFT JOIN PgimPersona ppes ON pes.pgimPersona.idPersona = ppes.idPersona "
                        + "WHERE supe.esRegistro = '1' " 
                        + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerSupervisionRevisionAntecedente(@Param("idSupervision") Long idSupervision);


        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                + "supe.idSupervision, supe.pgimInstanciaProces.idInstanciaProceso, "
                + "supe.pgimInstanciaProces.nuExpedienteSiged, "
                + "supe.pgimUnidadMinera.idUnidadMinera, supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.coUnidadMinera, supe.pgimUnidadMinera.tipoUnidadMinera.noValorParametro, "
                + "aux.asCoDocumentoIdentidad, aux.asNoRazonSocial, "
                + "supe.coSupervision, supe.feInicioSupervision, supe.feFinSupervision,  "
                + "supe.feInicioSupervisionReal, supe.feFinSupervisionReal, aux.descPrograma, "
                + "aux.pgimContrato.idContrato, aux.descContrato, "
                + "supe.flPropia, supe.pgimSubtipoSupervision.idSubtipoSupervision, "
                + "supe.pgimSubtipoSupervision.tipoSupervision.noValorParametro, "
                + "prog.idProgramaSupervision, "
                + "aux.idAgenteSupervisado, "
                + "prog.pgimEspecialidad.noEspecialidad, "
                + "prog.pgimEspecialidad.idEspecialidad, "
                + "mosu.idMotivoSupervision, perses.idPersona, "
                + "perses.coDocumentoIdentidad, perses.noRazonSocial, aux.idPasoActual) "
                + "FROM PgimSupervisionAux aux "
                + "     INNER JOIN aux.pgimSupervision supe "
                + "     LEFT JOIN supe.pgimPrgrmSupervision prog "
                + "     LEFT JOIN supe.pgimMotivoSupervision mosu "
                + "     LEFT JOIN PgimConsumoContra pcc ON supe.idSupervision = pcc.pgimSupervision.idSupervision "
                + "     LEFT JOIN PgimContrato pcto ON pcc.pgimContrato.idContrato = pcto.idContrato "
                + "     LEFT JOIN pcto.pgimEmpresaSupervisora pes "
                + "     LEFT JOIN pes.pgimPersona perses "
                + "WHERE aux.flPasoActivo = '1' "
                + "AND supe.esRegistro = '1' "
                + "AND supe.idSupervision = :idSupervision ")
        PgimSupervisionDTO obtenerSupervisionPorIdAux(@Param("idSupervision") Long idSupervision);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
        +  "supe.idSupervision, supe.coSupervision, supe.pgimInstanciaProces.idInstanciaProceso) "
        + "FROM PgimSupervision supe "
        + "WHERE 1 = 1 "
        + "AND EXISTS "
        + "( "
        + "SELECT 1 "
        + "FROM PgimPrmtroXSuperv pasu "
        + "     INNER JOIN pasu.pgimInstrmntoXSuperv insu "
        + "     INNER JOIN pasu.pgimTipopameXContrato paco "
        + "     INNER JOIN paco.pgimContrato coni "
        + "     INNER JOIN paco.pgimTprmtroXTinstrmnto pain "
        + "WHERE insu.pgimSupervision.idSupervision = supe.idSupervision "
        + "AND coni.idContrato =  :idContrato "
        + "AND pain.idTprmtroXTinstrmnto = :idTprmtroXTinstrmnto "
        + "AND pasu.esRegistro = '1' "
        + "AND insu.esRegistro = '1' "
        + "AND paco.esRegistro = '1' "
        + "AND coni.esRegistro = '1' "
        + "AND pain.esRegistro = '1' "
        + ") "
        + "AND supe.esRegistro = '1' "
        )
        List<PgimSupervisionDTO> obtenerFiscalizacionesAfectadasPorTipoParametro(@Param("idContrato") Long idContrato, @Param("idTprmtroXTinstrmnto") Long idTprmtroXTinstrmnto);
        
        
      /**
       * Permite obtener la cantidad de supervisiones pendientes de atención que tiene un usuario
       * 
       * @param usuarioAsignado
       * @return
       */
       @Query("SELECT COUNT(aux.idSupervisionAux)"
	            + "FROM PgimSupervisionAux aux " 
	            + "WHERE "
	            + "aux.flPasoActivo = '1' "
	            + "AND LOWER(aux.usuarioAsignado) = LOWER(:usuarioAsignado) "
	            + "AND aux.idTipoRelacion NOT IN (291, 292) "
    		   )
        Integer contarSupervisionesPendientes(
                @Param("usuarioAsignado") String usuarioAsignado                        
                );

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTOResultado("
                + "supe.idSupervision, supe.coSupervision, supe.pgimUnidadMinera.idUnidadMinera, supe.pgimUnidadMinera.noUnidadMinera, supe.pgimUnidadMinera.coUnidadMinera ) "
                + "FROM PgimSupervision supe "
                + "WHERE supe.esRegistro = '1' "
                + "and supe.pgimPrgrmSupervision.pgimEspecialidad.idEspecialidad = 2 "
                + "AND EXISTS ( " 
                        + "SELECT 1 " 
                        + "FROM PgimInstanciaProces pip "
                        + "left join PgimInstanciaPaso ppa on pip = ppa.pgimInstanciaProces "
                        + "left join ppa.pgimRelacionPaso prp "
                        + "left join prp.pasoProcesoDestino ppd "
                        + "WHERE pip.esRegistro = '1' " 
                        + "and ppa.esRegistro = '1' "
                        + "and ppd.idPasoProceso = 21 "
                        + "and supe.pgimInstanciaProces = pip " 
                        + ") "
                + "order by supe.coSupervision asc "
                )                
        List<PgimSupervisionDTO> listarSupervisionConfirmarHvGeotecnia();
        
}