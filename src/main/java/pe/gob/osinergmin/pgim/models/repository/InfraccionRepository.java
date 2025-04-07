/**
 * 
 */
package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccion;

/**
 * Ésta interface FichaRevisionRepository incluye los metodos de listar y obtener
 * la Infracción
 * 
 * @descripción: Lógica de negocio de la entidad Infracción
 * 
 * @author Luis Barrantes
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 *
 */
@Repository
public interface InfraccionRepository extends JpaRepository<PgimInfraccion, Long>{
	
    /**
     * Permite listar las infracciones vigentes asociadas a una obligación normativa
     * 
     * @param idOblgcnNrmtvaHchoc
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTOResultado( "
                    + "inf.idInfraccion, inf.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc "
                    + ", inf.flIncluirEnPas, inf.deSustento) " 
                    + "FROM PgimInfraccion inf "                    
                    + "WHERE inf.esRegistro = '1' and inf.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc= :idOblgcnNrmtvaHchoc "
                    + "AND inf.flVigente = '1' "
                    )
    List<PgimInfraccionDTO> listarInfraccionXobligacionNorma(@Param("idOblgcnNrmtvaHchoc") Long idOblgcnNrmtvaHchoc);
    
    
    /**
     * Permite obtener el registro de una infracción a través del ID
     * 
     * @param idInfraccion
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTOResultado( "
                    + "inf.idInfraccion, inf.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc "
                    + ", inf.flIncluirEnPas, inf.deSustento "
                    + ", inf.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.idHechoConstatado "
                    + ", inf.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.pgimCriterioSprvsion.idCriterioSprvsion "
                    + ", inf.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimItemTipificacion.noItemTipificacion "
                    + ", inf.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimItemTipificacion.deSancionPecuniariaUit "
                    + ", inf.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimItemTipificacion.coTipificacion "
                    + ", inf.flVigente "
                    + ", inf.moMultaUit "
                    + ", inf.feComisionDeteccion "
                    + ") " 
                    + "FROM PgimInfraccion inf "                    
                    + "WHERE inf.esRegistro = '1' "
                    + "and inf.idInfraccion = :idInfraccion "
                    )
    PgimInfraccionDTO obtenerInfraccionPorId(@Param("idInfraccion") Long idInfraccion);

    /**
     * Permite obtener el registro de una infracción a través del ID
     * 
     * @param idInfraccion
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTOResultado( "
                    + "infr.idInfraccion, infr.pgimOblgcnNrmtvaHchoc.idOblgcnNrmtvaHchoc "
                    + ", infr.flIncluirEnPas, infr.deSustento "
                    + ", infr.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.idHechoConstatado "
                    + ", infr.pgimOblgcnNrmtvaHchoc.pgimHechoConstatado.pgimCriterioSprvsion.idCriterioSprvsion "
                    + ", infr.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimItemTipificacion.noItemTipificacion "
                    + ", infr.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimItemTipificacion.deSancionPecuniariaUit "
                    + ", infr.pgimOblgcnNrmtvaHchoc.pgimOblgcnNrmaCrtrio.pgimNormaObligacion.pgimItemTipificacion.coTipificacion "
                    + ", infr.flVigente "
                    + ", infr.moMultaUit "
                    + ", infr.feComisionDeteccion "
                    + ", CASE WHEN fapr.noFaseProceso IS NOT NULL THEN fapr.noFaseProceso ELSE '0. Fiscalización' END "
                    + ", pade.noPasoProceso "
                    + ", ropr.noRolProceso "                    
                    + ", CASE "
                    + "     WHEN peco.noUsuario IS NOT NULL THEN "
                    + "          CONCAT(cope.noPersona, ' ', cope.apPaterno,  ' ', cope.apMaterno, ' (',  peco.noUsuario, ')') "
                    + "     WHEN peos.noUsuario IS NOT NULL THEN "
                    + "          CONCAT(ospe.noPersona, ' ', ospe.apPaterno,  ' ', ospe.apMaterno, ' (',  peos.noUsuario, ')') "
                    + "     ELSE '' "
                    + "  END "
                    + ", infr.feCreacion "
                    + ", pr.noProceso "
                    + ") " 
                    + "FROM PgimInfraccion infr "
                    + "LEFT OUTER JOIN infr.pgimInstanciaPaso inpa "
                    + "LEFT OUTER JOIN inpa.personaEqpDestino eqip "
                    + "LEFT OUTER JOIN eqip.pgimPersonalContrato peco "
                    + "LEFT OUTER JOIN peco.pgimPersona cope "
                    + "LEFT OUTER JOIN eqip.pgimPersonalOsi peos "
                    + "LEFT OUTER JOIN peos.pgimPersona ospe "
                    + "LEFT OUTER JOIN inpa.pasoProcesoDestino pade "
                    + "LEFT OUTER JOIN pade.pgimRolProceso ropr "
                    + "LEFT OUTER JOIN pade.pgimFaseProceso fapr "
                    + "LEFT OUTER JOIN fapr.pgimProceso pr "
                    + "WHERE infr.esRegistro = '1' "
                    + "AND infr.idInfraccion = :idInfraccion "
                    )
    PgimInfraccionDTO obtenerInfraccionPorIdHistorial(@Param("idInfraccion") Long idInfraccion);
   
}
