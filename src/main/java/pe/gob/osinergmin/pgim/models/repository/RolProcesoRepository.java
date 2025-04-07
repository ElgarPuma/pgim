package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;

import java.util.List;

/**
 * Interfaz que permite especificar las diferentes acciones sobre la base de
 * datos.
 * 
 * @descripción: Acceso a datos para los roles del proceso.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 13/08/2020
 * @fecha_de_ultima_actualización: 13/08/2020
 */
@Repository
public interface RolProcesoRepository extends JpaRepository<PgimRolProceso, Long> {

    /**
     * Permite obtener el rol interesado por instancia de proceso.
     * @param idInstanciaProceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTOResultado(ropr.idRolProceso) "
            + "FROM PgimRolProceso ropr, "
            + "PgimProceso proc, "
            + "PgimInstanciaProces inpr "
            + "WHERE ropr.coRolProceso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PROCESO_CO_ROL_INTERESADO "
            + "AND ropr.pgimProceso.idProceso = proc.idProceso "
            + "AND proc.idProceso = inpr.pgimProceso.idProceso "
            + "AND inpr.idInstanciaProceso = :idInstanciaProceso "
            + "AND ropr.esRegistro = '1' "
            + "AND proc.esRegistro = '1' "
            + "AND inpr.esRegistro = '1' "
            )
    PgimRolProcesoDTO obtenerRolInteresadoPorInstanciaProceso(
            @Param("idInstanciaProceso") Long idInstanciaProceso);
    
    
    /**
     * Permite obtener los roles de proceso vigentes.
     * @param 
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTOResultado(ropr.idRolProceso "
    		+ ",ropr.noRolProceso,ropr.coRolProceso,ropr.pgimProceso.idProceso,ropr.flSoloOsinergmin, ropr.deRolProceso, uo.idUnidadOrganica, uo.noUnidadOrganica) "
            + "FROM PgimRolProceso ropr "            
            + "LEFT JOIN ropr.pgimUnidadOrganica uo "
            + "WHERE ropr.esRegistro = '1' "
            + "AND ropr.pgimProceso.idProceso = :idProceso "
            )
    List<PgimRolProcesoDTO> obtenerRolesProceso(@Param("idProceso") Long idProceso, Sort sort);
    
    /**
     * Permite obtener los roles de proceso especificando el campo flSoloOsinergmin.
     * @param flSoloOsinergmin
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTOResultado(ropr.idRolProceso "
    		+ ",ropr.noRolProceso,ropr.coRolProceso,ropr.pgimProceso.idProceso,ropr.flSoloOsinergmin, ropr.deRolProceso) "
            + "FROM PgimRolProceso ropr "            
            + "WHERE ropr.esRegistro = '1' "
            + "AND ropr.flSoloOsinergmin IN ('0', :flSoloOsinergmin) "
            + "AND ropr.pgimProceso.idProceso = :idProceso "
            + "ORDER BY ropr.noRolProceso "
            )
    List<PgimRolProcesoDTO> obtenerRolesProcesoPorAmbito(@Param("flSoloOsinergmin") String flSoloOsinergmin, @Param("idProceso") Long idProceso);
    
    /**
     * STORY: PGIM-7276: Relación de personal con roles del proceso de fiscalización
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTOResultado(ropr.idRolProceso "
    		+ ",ropr.noRolProceso,ropr.coRolProceso,ropr.pgimProceso.idProceso,ropr.flSoloOsinergmin, ropr.deRolProceso) "
            + "FROM PgimRolProceso ropr "            
            + "WHERE ropr.esRegistro = '1' "
            + "AND ropr.flSoloOsinergmin IN ('0') "
            + "AND ropr.idRolProceso IN (34,36) "
            + "ORDER BY ropr.noRolProceso "
            )
    List<PgimRolProcesoDTO> listarRolesPersonalContrato();

    /**
     * Permite obtener el rol de Coordinador de la empresa supervisora en el paso Reprogramación(Fase 2)
     * STORY:PGIM-7233: Validación de existencia de rol "coordinador de empresa supervisora" en el equipo
     * @param idRolProceso
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTOResultado(ropr.idRolProceso "
    		+ ",ropr.noRolProceso,ropr.coRolProceso,ropr.pgimProceso.idProceso,ropr.flSoloOsinergmin, ropr.deRolProceso) "
            + "FROM PgimRolProceso ropr "            
            + "WHERE ropr.esRegistro = '1' "
            + "AND ropr.idRolProceso = :idRolProceso "
            + "ORDER BY ropr.noRolProceso "
            )
    List<PgimRolProcesoDTO> obtenerRolCoordinadorESParaReprogramacion(@Param("idRolProceso") Long idRolProceso);

    /**
     * Permite obtener un rol a partir de su identificador 
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTOResultado(ropr.idRolProceso "
    		+ ",ropr.noRolProceso,ropr.coRolProceso,ropr.pgimProceso.idProceso,ropr.flSoloOsinergmin, uo.idUnidadOrganica, uo.noUnidadOrganica) "
            + "FROM PgimRolProceso ropr "
            + "LEFT JOIN ropr.pgimUnidadOrganica uo "
            + "WHERE ropr.esRegistro = '1' "
            + "AND ropr.idRolProceso = :idRolProceso "
            + "ORDER BY ropr.noRolProceso "
            )
    PgimRolProcesoDTO obtenerRolPorID(@Param("idRolProceso") Long idRolProceso);
}