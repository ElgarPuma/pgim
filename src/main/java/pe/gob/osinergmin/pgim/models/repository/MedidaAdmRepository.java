package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMedidaAdm;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad medidas administrativas
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
@Repository
public interface MedidaAdmRepository extends JpaRepository<PgimMedidaAdm, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med.idMedidaAdministrativa, med.tipoMedidaAdministrativa.idValorParametro, med.pgimInstanciaProces.nuExpedienteSiged, "
            + "med.tipoMedidaAdministrativa.noValorParametro, med.coMedidaAdministrativa, med.deMedidaAdministrativa, med.feMedidaAdministrativa, " 
            + "med.tipoObjeto.noValorParametro, (um.coUnidadMinera || ' ' || um.noUnidadMinera), " 
            + "(sup.coSupervision || ' ' || um_sup.noUnidadMinera), (sup_pas.coSupervision || ' ' || um_sup_pas.noUnidadMinera) " 
            + ") "
            + "FROM PgimMedidaAdm med " 
            + "LEFT JOIN med.pgimUnidadMinera um "
            + "LEFT JOIN med.pgimSupervision sup "
            + "LEFT JOIN sup.pgimUnidadMinera um_sup  "
            + "LEFT JOIN med.pgimPas pas "
            + "LEFT JOIN pas.pgimSupervision sup_pas "
            + "LEFT JOIN sup_pas.pgimUnidadMinera um_sup_pas "
            + "WHERE med.esRegistro = '1' "
            + "AND (:idTipoObjeto IS NULL OR med.tipoObjeto.idValorParametro = :idTipoObjeto) "
            + "AND (:coMedidaAdministrativa IS NULL OR LOWER(med.coMedidaAdministrativa) LIKE LOWER(CONCAT('%', :coMedidaAdministrativa, '%')) ) "
            + "AND (:idValorParametro IS NULL OR med.tipoMedidaAdministrativa.idValorParametro = :idValorParametro) "
            + "AND (:descNuExpedienteSiged IS NULL OR LOWER(med.pgimInstanciaProces.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :descNuExpedienteSiged, '%')) ) "

            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(med.coMedidaAdministrativa) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " )) "
            )
    Page<PgimMedidaAdmDTO> listarMedidaAdministrativa_old(
            @Param("idTipoObjeto") Long idTipoObjeto, 
            @Param("coMedidaAdministrativa") String coMedidaAdministrativa, 
            @Param("idValorParametro") Long idValorParametro, 
            @Param("descNuExpedienteSiged") String descNuExpedienteSiged, 
            @Param("textoBusqueda") String textoBusqueda, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med_aux.idMedidaAdministrativa, med_aux.idTipoMedidaAdministrativa, med_aux.nuExpedienteSiged, "
            + "med_aux.tipoMedidaAdministrativa, med_aux.coMedidaAdministrativa, med_aux.deMedidaAdministrativa, "
            + "med_aux.feMedidaAdministrativa, med_aux.referidaA, (med_aux.coUnidadMinera || ' ' || med_aux.noUnidadMinera), " 
            + "(med_aux.coSupervision || ' ' || med_aux.noUnidadMineraSup), (med_aux.coSupervisionPas || ' ' || med_aux.noUnidadMineraPas), med_aux.noPersonaAsignada, "
            + "med_aux.noUsuarioAsignado, med_aux.idInstanciaPaso, med_aux.feInstanciaPaso, " 
            + "med_aux.deMensaje, med_aux.flPasoActivo " 
            + ") "
            + "FROM PgimMedidaAdmAux med_aux " 
            + "WHERE 1 = 1 "
            + "AND med_aux.flPasoActivo = '1' "
            + "AND (:idTipoObjeto IS NULL OR med_aux.idTipoObjeto = :idTipoObjeto) "
            + "AND (:coMedidaAdministrativa IS NULL OR LOWER(med_aux.coMedidaAdministrativa) LIKE LOWER(CONCAT('%', :coMedidaAdministrativa, '%')) ) "
            + "AND (:idValorParametro IS NULL OR med_aux.idTipoMedidaAdministrativa = :idValorParametro) "
            + "AND (:descNuExpedienteSiged IS NULL OR LOWER(med_aux.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :descNuExpedienteSiged, '%')) ) "

            + "AND ( "
            + " (:flagMisAsignaciones = '1' AND LOWER(med_aux.noUsuarioAsignado) = LOWER(:usuarioAsignado) AND med_aux.idTipoRelacion NOT IN (291, 292) " 
            + ") "
            + "OR "
            + " (:flagMisAsignaciones = '0' AND (:descPersonaDestino IS NULL OR LOWER(med_aux.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%'))) " 
            + ") "
            + "OR "
            + " (:flagMisAsignaciones = '2' AND (LOWER(med_aux.noUsuarioAsignado) != LOWER(:usuarioAsignado)) " 
                                         + "AND (:descPersonaDestino IS NULL OR LOWER(med_aux.noPersonaAsignada) LIKE LOWER(CONCAT('%', :descPersonaDestino, '%')) ) " 
                                         + "AND med_aux.idTipoRelacion NOT IN (291, 292) ) "
            + " ) "

            + "AND (:textoBusqueda IS NULL OR ( "
            + "LOWER(med_aux.coMedidaAdministrativa) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + " )) "
            )
    Page<PgimMedidaAdmDTO> listarMedidaAdministrativa(
            @Param("idTipoObjeto") Long idTipoObjeto, 
            @Param("coMedidaAdministrativa") String coMedidaAdministrativa, 
            @Param("idValorParametro") Long idValorParametro, 
            @Param("descNuExpedienteSiged") String descNuExpedienteSiged, 
            @Param("flagMisAsignaciones") String flagMisAsignaciones, 
            @Param("usuarioAsignado") String usuarioAsignado,
            @Param("descPersonaDestino") String descPersonaDestino,
            @Param("textoBusqueda") String textoBusqueda, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med.idMedidaAdministrativa, med.tipoMedidaAdministrativa.idValorParametro, med.pgimInstanciaProces.nuExpedienteSiged, "
            + "med.tipoMedidaAdministrativa.noValorParametro, med.coMedidaAdministrativa, med.deMedidaAdministrativa, med.feMedidaAdministrativa, "
            + "med.tipoObjeto.noValorParametro, (um.coUnidadMinera || ' ' || um.noUnidadMinera), " 
            + "(sup.coSupervision || ' ' || um_sup.noUnidadMinera), (sup_pas.coSupervision || ' ' || um_sup_pas.noUnidadMinera) "  
            + ") "
            + "FROM PgimMedidaAdm med " 
            + "LEFT JOIN med.pgimUnidadMinera um "
            + "LEFT JOIN med.pgimSupervision sup "
            + "LEFT JOIN sup.pgimUnidadMinera um_sup  "
            + "LEFT JOIN med.pgimPas pas "
            + "LEFT JOIN pas.pgimSupervision sup_pas "
            + "LEFT JOIN sup_pas.pgimUnidadMinera um_sup_pas "
            + "WHERE med.esRegistro = '1' "
            + "AND med.tipoObjeto.idValorParametro = :idTipoObjeto "
            )
    Page<PgimMedidaAdmDTO> listarMedidaAdministrativaSupervPas(@Param("idTipoObjeto") Long idTipoObjeto, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med_aux.idMedidaAdministrativa, med_aux.idTipoMedidaAdministrativa, med_aux.nuExpedienteSiged, "
            + "med_aux.tipoMedidaAdministrativa, med_aux.coMedidaAdministrativa, med_aux.deMedidaAdministrativa, "
            + "med_aux.feMedidaAdministrativa, med_aux.referidaA, (med_aux.coSupervision || ' - ' || med_aux.noUnidadMineraSup), " 
            + "med_aux.noPersonaAsignada, med_aux.idInstanciaPaso "
            + ") "
            + "FROM PgimMedidaAdmAux med_aux " 
            + "WHERE med_aux.idSupervision = :idSupervision "
            + "AND med_aux.flPasoActivo = '1' "
            )
    Page<PgimMedidaAdmDTO> listarMedidaAdministrativaSupervision(@Param("idSupervision") Long idSupervision, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med_aux.idMedidaAdministrativa, med_aux.idTipoMedidaAdministrativa, med_aux.nuExpedienteSiged, "
            + "med_aux.tipoMedidaAdministrativa, med_aux.coMedidaAdministrativa, med_aux.deMedidaAdministrativa, "
            + "med_aux.feMedidaAdministrativa, med_aux.referidaA, (med_aux.coSupervisionPas || ' - ' || med_aux.noUnidadMineraPas), "  
            + "med_aux.noPersonaAsignada, med_aux.idInstanciaPaso "
            + ") "
            + "FROM PgimMedidaAdmAux med_aux " 
            + "WHERE med_aux.idPas = :idPas "
            + "AND med_aux.flPasoActivo = '1' "
            )
    Page<PgimMedidaAdmDTO> listarMedidaAdministrativaPas(@Param("idPas") Long idPas, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med_aux.idMedidaAdministrativa, med_aux.idTipoMedidaAdministrativa, "
            + "med_aux.tipoMedidaAdministrativa, med_aux.coMedidaAdministrativa, med_aux.deMedidaAdministrativa, med_aux.feMedidaAdministrativa, "
            + "med_aux.referidaA, (med_aux.coUnidadMinera || ' - ' || med_aux.noUnidadMinera) " 
            + ", med_aux.noPersonaAsignada, med_aux.idInstanciaPaso "
            + ") "
            + "FROM PgimMedidaAdmAux med_aux " 
            + "WHERE med_aux.idUnidadMinera = :idUnidadMinera "
            + "AND med_aux.flPasoActivo = '1' "
            )
    Page<PgimMedidaAdmDTO> listarMedidaAdministrativaUm(@Param("idUnidadMinera") Long idUnidadMinera, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med.idMedidaAdministrativa, med.tipoMedidaAdministrativa.idValorParametro, " 
            + "iproc.idInstanciaProceso, "
            + "iproc.nuExpedienteSiged, " 
            + "med.tipoMedidaAdministrativa.noValorParametro, " 
            + "med.coMedidaAdministrativa, med.deMedidaAdministrativa, med.feMedidaAdministrativa, " 
            + "um.idUnidadMinera, um.noUnidadMinera, val.idValorParametro, " 
            + "sup.idSupervision, (sup.coSupervision || ' - ' || um_sup.noUnidadMinera), pas.idPas, (sup_pas.coSupervision || ' - ' || um_sup_pas.noUnidadMinera) " 
            + ") "
            + "FROM PgimMedidaAdm med, PgimInstanciaProces iproc, PgimValorParametro val " 
            + "LEFT JOIN med.pgimUnidadMinera um "
            + "LEFT JOIN med.pgimSupervision sup "
            + "LEFT JOIN sup.pgimUnidadMinera um_sup  "
            + "LEFT JOIN med.pgimPas pas "
            + "LEFT JOIN pas.pgimSupervision sup_pas "
            + "LEFT JOIN sup_pas.pgimUnidadMinera um_sup_pas "
            + "WHERE med.pgimInstanciaProces.idInstanciaProceso = iproc.idInstanciaProceso " 
            + "AND med.tipoObjeto.idValorParametro = val.idValorParametro " 
            + "AND med.esRegistro = '1' "
            + "AND med.idMedidaAdministrativa = :idMedidaAdministrativa "
            )
    PgimMedidaAdmDTO obtenerMedidaAdministrativaPorId(
            @Param("idMedidaAdministrativa") Long idMedidaAdministrativa);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med.idMedidaAdministrativa, " 
            + "pas.idPas, (sup_pas.coSupervision || ' - ' || um_sup_pas.noUnidadMinera) " 
            + ") "
            + "FROM PgimMedidaAdm med " 
            + "LEFT JOIN med.pgimPas pas "
            + "LEFT JOIN pas.pgimSupervision sup_pas "
            + "LEFT JOIN sup_pas.pgimUnidadMinera um_sup_pas "
            + "WHERE med.esRegistro = '1' "
            + "AND pas.idPas = :idPas "
            )
    PgimMedidaAdmDTO obtenerMedidaAdministrativaPorIdPas(
            @Param("idPas") Long idPas);


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado("
            + "med.idMedidaAdministrativa, med.coMedidaAdministrativa ) " 
            + "FROM PgimMedidaAdm med "
            + "WHERE med.esRegistro = '1' "
            + "AND (:palabraClave IS NULL OR LOWER(med.coMedidaAdministrativa) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
    List<PgimMedidaAdmDTO> listarPorCoMedidaAdministrativa(@Param("palabraClave") String palabraClave);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado("
            + "med.idMedidaAdministrativa, iproc.idInstanciaProceso, iproc.nuExpedienteSiged ) " 
            + "FROM PgimMedidaAdm med, PgimInstanciaProces iproc "
            + "WHERE med.esRegistro = '1' "
            + "AND med.pgimInstanciaProces.idInstanciaProceso = iproc.idInstanciaProceso "
            + "AND (:nuExpediente IS NULL OR LOWER(iproc.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :nuExpediente, '%'))  )")
    List<PgimMedidaAdmDTO> listarPorNumeroExpediente(@Param("nuExpediente") String nuExpediente);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTOResultado(" 
            + "med.idMedidaAdministrativa, med.tipoMedidaAdministrativa.idValorParametro, med.pgimInstanciaProces.nuExpedienteSiged, "
            + "med.tipoMedidaAdministrativa.noValorParametro, med.coMedidaAdministrativa, med.deMedidaAdministrativa, med.feMedidaAdministrativa, "
            + "med.tipoObjeto.noValorParametro, (um.coUnidadMinera || ' ' || um.noUnidadMinera), " 
            + "(sup.coSupervision || ' ' || um_sup.noUnidadMinera), (sup_pas.coSupervision || ' ' || um_sup_pas.noUnidadMinera) "  
            + ") "
            + "FROM PgimMedidaAdm med " 
            + "LEFT JOIN med.pgimUnidadMinera um "
            + "LEFT JOIN med.pgimSupervision sup "
            + "LEFT JOIN sup.pgimUnidadMinera um_sup  "
            + "LEFT JOIN med.pgimPas pas "
            + "LEFT JOIN pas.pgimSupervision sup_pas "
            + "LEFT JOIN sup_pas.pgimUnidadMinera um_sup_pas "
            + "WHERE med.esRegistro = '1' "
            + "AND um.idUnidadMinera = :idUnidadMinera "
            )
    List<PgimMedidaAdmDTO> listarMedidaAdministrativaPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
}
