package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;

import java.util.List;

/**
 * Ésta interface FichaRevisionRepository incluye los metodos de listar y
 * paginar el programa de supervision
 * 
 * @descripción: Lógica de negocio de la entidad Programa supervisión
 * 
 * @author Luis Barrantes
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 *
 */
@Repository
public interface ItemProgramaSupeRepository extends JpaRepository<PgimItemProgramaSupe, Long> {

        /**
         * Retorna una lista de ítems de un programa de supervisión dado.
         * 
         * @param idLineaPrograma
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTOResultado("
                        + "ipro.idItemProgramaSupe, pros.idProgramaSupervision, "
                        + "stsu.idSubtipoSupervision, stsu.deSubtipoSupervision, "
                        + "unm.idUnidadMinera, unm.coUnidadMinera, unm.noUnidadMinera, "
                        + "ipro.feMesEstimado, ipro.moCostoEstimadoSupervision, "
                        + "ags.idAgenteSupervisado, pers.noRazonSocial, situ.noValorParametro, "
                        + "disu.noValorParametro, memi.noValorParametro, tiac.noValorParametro, " 
                        + "esta.noValorParametro " 
                        + ") "
                        + "FROM PgimItemProgramaSupe ipro "
                        + "INNER JOIN ipro.pgimLineaPrograma liba " 
                        + "INNER JOIN liba.pgimPrgrmSupervision pros " 
                        + "INNER JOIN ipro.pgimSubtipoSupervision stsu "
                        + "INNER JOIN ipro.pgimUnidadMinera unm " 
                        + "INNER JOIN unm.divisionSupervisora disu " 
                        + "INNER JOIN unm.metodoMinado memi " 
                        + "INNER JOIN unm.pgimAgenteSupervisado ags "
                        + "INNER JOIN ags.pgimPersona pers "
                        + "INNER JOIN unm.situacion situ "
                        + "INNER JOIN unm.tipoActividad tiac " 
                        + "INNER JOIN unm.estadoUm esta " 
                        + "WHERE ipro.pgimSupervision.idSupervision IS NULL " 
                        + "AND liba.idLineaPrograma = :idLineaPrograma "
                        + "AND liba.lineaProgramaEstado.coClaveTexto = :PREST_APRBDA " 
                        + "AND liba.esRegistro = '1' " 
                        + "AND ipro.esRegistro = '1' " 
                        + "AND pros.esRegistro = '1' " 
                        + "AND pros.esRegistro = '1' ")
        Page<PgimItemProgramaSupeDTO> listarItemsProgramaPendientes(
        				@Param("idLineaPrograma") Long idLineaPrograma, Pageable paginador, @Param("PREST_APRBDA") String PREST_APRBDA);

        /**
         * Me permite listar los Items de Programas
         * @param idLineaPrograma
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTOResultado("
                        + "ipro.idItemProgramaSupe, pros.idProgramaSupervision, liba.idLineaPrograma, "
                        + "stsu.idSubtipoSupervision, stsu.deSubtipoSupervision, "
                        + "unm.idUnidadMinera, unm.coUnidadMinera, unm.noUnidadMinera, "
                        + "ipro.feMesEstimado, ipro.moCostoEstimadoSupervision, "
                        + "ags.idAgenteSupervisado, pers.noRazonSocial, " 
                        + "CASE WHEN ipro.pgimSupervision IS NULL THEN 'No' ELSE 'Sí' END,"
                        + "ipro.pgimSupervision.idSupervision, disu.noValorParametro, memi.noValorParametro, "
                        + "situ.noValorParametro, tiac.noValorParametro, esta.noValorParametro, "
                        + "unm.flRegistraRiesgos, iproa.deConfiguracionesRiesgo "
                        + ") "
                        + "FROM PgimItemProgramaSupe ipro "
                        + "INNER JOIN PgimItemProgramaSupeAux iproa ON (ipro.idItemProgramaSupe = iproa.idItemProgramaSupeAux) "
                        + "INNER JOIN ipro.pgimLineaPrograma liba "
                        + "INNER JOIN liba.pgimPrgrmSupervision pros " 
                        + "INNER JOIN ipro.pgimSubtipoSupervision stsu "
                        + "INNER JOIN ipro.pgimUnidadMinera unm " 
                        + "INNER JOIN unm.pgimAgenteSupervisado ags "
                        + "INNER JOIN ags.pgimPersona pers "                         
                        + "LEFT OUTER JOIN unm.divisionSupervisora disu "
                        + "LEFT OUTER JOIN unm.metodoMinado memi "
                        + "LEFT OUTER JOIN unm.situacion situ "
                        + "LEFT OUTER JOIN unm.tipoActividad tiac "
                        + "LEFT OUTER JOIN unm.estadoUm esta "
                        + "WHERE liba.idLineaPrograma = :idLineaPrograma "
                        + "AND ipro.esRegistro = '1' "
                        )
        List<PgimItemProgramaSupeDTO> listarItemsProgramas(@Param("idLineaPrograma") Long idLineaPrograma);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTOResultado("
                        + "ipro.idItemProgramaSupe, pros.idProgramaSupervision, liba.idLineaPrograma, "
                        + "stsu.idSubtipoSupervision, stsu.deSubtipoSupervision, "
                        + "unm.idUnidadMinera, unm.coUnidadMinera, unm.noUnidadMinera, "
                        + "ipro.feMesEstimado, ipro.moCostoEstimadoSupervision, "
                        + "iproa.idAgenteSupervisado, iproa.noRazonSocialAs, " 
                        + "CASE WHEN ipro.pgimSupervision IS NULL THEN 'No' ELSE 'Sí' END,"
                        + "ipro.pgimSupervision.idSupervision, disu.noValorParametro, memi.noValorParametro, "
                        + "situ.noValorParametro, tiac.noValorParametro, esta.noValorParametro, "
                        + "unm.flRegistraRiesgos, iproa.deConfiguracionesRiesgo "
                        + ") "
                        + "FROM PgimItemProgramaSupe ipro "
                        + "INNER JOIN PgimItemProgramaSupeAux iproa ON (ipro.idItemProgramaSupe = iproa.idItemProgramaSupeAux) "
                        + "INNER JOIN ipro.pgimLineaPrograma liba "
                        + "INNER JOIN liba.pgimPrgrmSupervision pros " 
                        + "INNER JOIN ipro.pgimSubtipoSupervision stsu "
                        + "INNER JOIN ipro.pgimUnidadMinera unm " 
                        + "LEFT OUTER JOIN unm.divisionSupervisora disu "
                        + "LEFT OUTER JOIN unm.metodoMinado memi "
                        + "LEFT OUTER JOIN unm.situacion situ "
                        + "LEFT OUTER JOIN unm.tipoActividad tiac "
                        + "LEFT OUTER JOIN unm.estadoUm esta "                        
                        + "WHERE liba.idLineaPrograma = :idLineaPrograma "
                        + "AND ipro.esRegistro = '1' "
                        )
        Page<PgimItemProgramaSupeDTO> listarItemsProgramas(@Param("idLineaPrograma") Long idLineaPrograma, Pageable paginador);
        
        
        /**
         * Permite listar los Items de Programas
         * @param idLineaPrograma
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTOResultado("
                        + "ipro.idItemProgramaSupe, pros.idProgramaSupervision, "
                        + "stsu.idSubtipoSupervision, stsu.deSubtipoSupervision, "
                        + "unm.idUnidadMinera, unm.coUnidadMinera, unm.noUnidadMinera, "
                        + "ipro.feMesEstimado, ipro.moCostoEstimadoSupervision, "
                        + "ags.idAgenteSupervisado, pers.noRazonSocial, " 
                        + "CASE WHEN ipro.pgimSupervision IS NULL THEN 'No' ELSE 'Sí' END, "
                        + "ipro.pgimSupervision.idSupervision,ipro.deItemPrograma) " 
                        + "FROM PgimItemProgramaSupe ipro "
                        + "INNER JOIN ipro.pgimLineaPrograma liba "
                        + "INNER JOIN liba.pgimPrgrmSupervision pros " 
                        + "INNER JOIN ipro.pgimSubtipoSupervision stsu "
                        + "INNER JOIN ipro.pgimUnidadMinera unm " 
                        + "INNER JOIN unm.pgimAgenteSupervisado ags "
                        + "INNER JOIN ags.pgimPersona pers "                         
                        + "WHERE liba.idLineaPrograma = :idLineaPrograma "
                        + "AND ipro.esRegistro = '1' "
                        + "ORDER BY ipro.feMesEstimado ASC"
                        )
        List<PgimItemProgramaSupeDTO> listarItemsPorLB(@Param("idLineaPrograma") Long idLineaPrograma);
        
        /**
         * Permite obtener un objeto de la entidad PgimItemProgramaSupeDTO por idItemProgramaSupe
         * @param idItemProgramaSupe
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTOResultado("
                        + "ipro.idItemProgramaSupe, pros.idProgramaSupervision, "
                        + "stsu.idSubtipoSupervision, stsu.deSubtipoSupervision, "
                        + "unm.idUnidadMinera, unm.coUnidadMinera, unm.noUnidadMinera, "
                        + "ipro.feMesEstimado, ipro.moCostoEstimadoSupervision, "
                        + "ags.idAgenteSupervisado, pers.noRazonSocial, " 
                        + "CASE WHEN ipro.pgimSupervision IS NULL THEN 'No' ELSE 'Sí' END, "
                        + "ipro.pgimSupervision.idSupervision,ipro.deItemPrograma) " 
                        + "FROM PgimItemProgramaSupe ipro "
                        + "INNER JOIN ipro.pgimLineaPrograma liba "
                        + "INNER JOIN liba.pgimPrgrmSupervision pros " 
                        + "INNER JOIN ipro.pgimSubtipoSupervision stsu "
                        + "INNER JOIN ipro.pgimUnidadMinera unm " 
                        + "INNER JOIN unm.pgimAgenteSupervisado ags "
                        + "INNER JOIN ags.pgimPersona pers "                         
                        + "WHERE ipro.idItemProgramaSupe = :idItemProgramaSupe "                        
                        )
        PgimItemProgramaSupeDTO getItemProgramaSupeById(@Param("idItemProgramaSupe") Long idItemProgramaSupe);


        @Query("SELECT COUNT(*) "
                        + "FROM PgimItemProgramaSupe ipro "
                        + "INNER JOIN PgimItemProgramaSupeAux iproa ON (ipro.idItemProgramaSupe = iproa.idItemProgramaSupeAux) "
                        + "INNER JOIN ipro.pgimLineaPrograma liba "
                        + "INNER JOIN liba.pgimPrgrmSupervision pros " 
                        + "INNER JOIN ipro.pgimSubtipoSupervision stsu "
                        + "INNER JOIN ipro.pgimUnidadMinera unm " 
                        + "LEFT OUTER JOIN unm.divisionSupervisora disu "
                        + "LEFT OUTER JOIN unm.metodoMinado memi "
                        + "LEFT OUTER JOIN unm.situacion situ "
                        + "LEFT OUTER JOIN unm.tipoActividad tiac "
                        + "LEFT OUTER JOIN unm.estadoUm esta "                        
                        + "WHERE liba.idLineaPrograma = :idLineaPrograma "
                        + "AND ipro.pgimSupervision IS NOT NULL "
                        + "AND ipro.esRegistro = '1' "
                        )
        Integer existeFiscalizacionAsignadaPrograma(@Param("idLineaPrograma") Long idLineaPrograma);

}
