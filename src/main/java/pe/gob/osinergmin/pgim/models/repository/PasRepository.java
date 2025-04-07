package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimCorrelativoTablaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;

import java.util.List;

/**
 * Tiene como nombre Proceso administrativo sancionador.
 * 
 * @descripción: Logica de negocio de la entidad Pas
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface PasRepository extends JpaRepository<PgimPas, Long> {
            
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
            + "pas.idPas, pas.pgimSupervision.idSupervision, pas.pgimSupervision.coSupervision, pas.pgimSupervision.pgimUnidadMinera.noUnidadMinera " 
            + ") " 
            + "FROM PgimPas pas " 
            + "WHERE pas.esRegistro = '1' "
            + "AND (:coSupervision IS NULL OR LOWER(pas.pgimSupervision.coSupervision || pas.pgimSupervision.pgimUnidadMinera.noUnidadMinera) "
            + "LIKE LOWER(CONCAT('%', :coSupervision, '%')) " 
            + " )")
    List<PgimPasDTO> listarPorCoSupervision(@Param("coSupervision") String coSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
            + "pas.idPas, inst.idInstanciaProceso, inst.nuExpedienteSiged " 
            + ") " 
            + "FROM PgimPas pas " 
            + "LEFT OUTER JOIN pas.pgimInstanciaProces inst " 
            + "WHERE pas.esRegistro = '1' "
            + "AND (:palabraClave IS NULL OR LOWER(inst.nuExpedienteSiged) "
            + "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
            + " )")
    List<PgimPasDTO> listarPorNuExpedienteSiged(@Param("palabraClave") String palabraClave);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
            + "pas.idPas, pas_aux.idSupervision, inst.idInstanciaProceso, pas.coPas, pas_aux.coSupervision, "
            + "pas_aux.noUnidadMinera, inst.nuExpedienteSiged, pas_aux.feCreacionPas, "
            + "pas_aux.noEspecialidad, pas_aux.asNoRazonSocial, pas_aux.feCreacionPasAnio, " 
            + "pas_aux.nuAlertas, pas_aux.nuIntervaloAlertas ) "
            + "FROM PgimPasAux pas_aux " 
            + "INNER JOIN pas_aux.pgimPas pas " 
            + "INNER JOIN pas.pgimInstanciaProces inst "             
            + "WHERE 1 = 1 "
            + "AND pas.idPas = :idPas "
            + "AND pas_aux.idInstanciaPaso = :idInstanciaPaso "
//            + "AND pas_aux.flPasoActivo = '1' " //No considerar flPasoActivo para no afectar a la consulta de tareas enviadas.
            + "AND pas.esRegistro = '1' " 
            )
    PgimPasDTO obtenerPasPorId(@Param("idPas") Long idPas, @Param("idInstanciaPaso") Long idInstanciaPaso);   

        /**
         * Obtener el registro de correlativo (PgimCorrelativoTabla) para el año
         * correspondiente
         * 
         * @param anio
         * @return
         */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCorrelativoTablaDTOResultado( "
            + "co.idCorrelativoTabla, co.noTabla, co.nuAnioCorrelativo, co.seUltimoCorrelativo, "
            + "co.esRegistro, co.usCreacion, co.ipCreacion, "
            + "co.feCreacion, co.usActualizacion, co.ipActualizacion, co.feActualizacion ) "
            + "FROM PgimCorrelativoTabla co " 
            + "WHERE co.esRegistro = '1' "
            + "AND co.noTabla LIKE :noTabla "
            + "AND co.nuAnioCorrelativo = :anio ")
    PgimCorrelativoTablaDTO obtenerCorrelativoByAnio(@Param("noTabla") String noTabla, @Param("anio") Long anio);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
            + "pas.idPas, pas_aux.idSupervision, inst.idInstanciaProceso, pas.coPas, pas_aux.coSupervision, "
            + "pas_aux.noUnidadMinera, inst.nuExpedienteSiged, pas_aux.feCreacionPas, "
            + "pas_aux.noEspecialidad, pas_aux.asNoRazonSocial, pas_aux.feCreacionPasAnio, " 
            + "pas_aux.nuAlertas, pas_aux.nuIntervaloAlertas ) "
            + "FROM PgimPasAux pas_aux " 
            + "INNER JOIN pas_aux.pgimPas pas " 
            + "INNER JOIN pas.pgimInstanciaProces inst "             
            + "WHERE 1 = 1 "
            + "AND inst.idInstanciaProceso = :idInstanciaProceso "
            + "AND pas_aux.idInstanciaPaso = :idInstanciaPaso "
            + "AND pas_aux.flPasoActivo = '1' " 
            + "AND pas.esRegistro = '1' " 
            )
    PgimPasDTO obtenerPasByidInstanciaPaso(@Param("idInstanciaProceso") Long idInstanciaProceso, @Param("idInstanciaPaso") Long idInstanciaPaso);   
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
            + "pas.idPas, pas.pgimSupervision.idSupervision, pas.pgimSupervision.coSupervision, pas.pgimSupervision.pgimUnidadMinera.noUnidadMinera " 
            + ") " 
            + "FROM PgimPas pas " 
            + "WHERE 1=1 "
            + "AND pas.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "            
            + "AND pas.esRegistro = '1' "
            )
    PgimPasDTO obtenerPasByidInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
            + "pas.idPas, pas.pgimSupervision.idSupervision, pas.pgimSupervision.coSupervision, pas.pgimSupervision.pgimUnidadMinera.noUnidadMinera " 
            + ") " 
            + "FROM PgimPas pas " 
            + "WHERE 1=1 "
            + "AND pas.idPas = :idPas "
            + "AND pas.esRegistro = '1' "
            )
    PgimPasDTO obtenerPasPorIdPas(@Param("idPas") Long idPas);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
    		+ "pas.idPas, inst.idInstanciaProceso, inst.nuExpedienteSiged "  
            + ") " 
            + "FROM PgimPas pas " 
            + "LEFT OUTER JOIN pas.pgimInstanciaProces inst " 
            + "WHERE pas.esRegistro = '1' "
            + "AND pas.pgimSupervision.idSupervision = :idSupervision "
            )
    PgimPasDTO obtenerPasPorIdSupervision(@Param("idSupervision") Long idSupervision);  
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPasDTOResultado("
    		+ "pas.idPas, inst.idInstanciaProceso, inst.nuExpedienteSiged "  
            + ") " 
            + "FROM PgimPas pas " 
            + "LEFT OUTER JOIN pas.pgimInstanciaProces inst " 
            + "WHERE pas.esRegistro = '1' "
            + "AND inst.nuExpedienteSiged = :nuExpedienteSiged "
            )
    PgimPasDTO obtenerPasPorExpedienteSiged(@Param("nuExpedienteSiged") String nuExpedienteSiged);  
    

}