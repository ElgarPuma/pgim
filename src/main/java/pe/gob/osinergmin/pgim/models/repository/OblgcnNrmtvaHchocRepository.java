package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmtvaHchoc;

/** 
 * @descripción: Lógica de negocio de la entidad Obligación Normativa por Hecho Constatado
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 11/10/2020
 * @fecha_de_ultima_actualización: 11/10/2020
*/
@Repository
public interface OblgcnNrmtvaHchocRepository extends JpaRepository<PgimOblgcnNrmtvaHchoc, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTOResultado "
        + "(obn.idOblgcnNrmtvaHchoc,obn.pgimHechoConstatado.idHechoConstatado, "
        + "obn.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio, obn.esAplica "       
        + ")  " 
        + "FROM PgimOblgcnNrmtvaHchoc obn "
        + "where obn.pgimHechoConstatado.idHechoConstatado = :idHechoConstatado " 
        + "and obn.esRegistro = '1' "
        )
    List<PgimOblgcnNrmtvaHchocDTO> obtenerOblgNormativaPorHechoDTO(
        @Param("idHechoConstatado") Long idHechoConstatado);
    
    /**
     * Permite listar las obligaciones normativas fiscalizadas de un criterio de fiscalización determinado.
     * Se considera solo de los hechos verificados vigentes 
     *  
     * @param idCriterioSprvsion
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaHchocDTOResultado "
            + "(obn.idOblgcnNrmtvaHchoc,obn.pgimHechoConstatado.idHechoConstatado, "
            + "obn.pgimOblgcnNrmaCrtrio.idOblgcnNrmaCrtrio, obn.esAplica "       
            + ")  " 
            + "FROM PgimOblgcnNrmtvaHchoc obn "
            + "INNER JOIN obn.pgimHechoConstatado hc "
            + "WHERE hc.pgimCriterioSprvsion.idCriterioSprvsion = :idCriterioSprvsion " 
            + "AND hc.pgimCriterioSprvsion.idCriterioSprvsion = :idCriterioSprvsion " 
            + "AND obn.esRegistro = '1' "
            + "AND hc.esRegistro = '1' "
            + "AND hc.esVigente = 'V' "
            )
    List<PgimOblgcnNrmtvaHchocDTO> listarOblgFiscalizadasPorCriterioSuperv(
            @Param("idCriterioSprvsion") Long idCriterioSprvsion);

}
