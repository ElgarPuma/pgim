package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteHc;

/**
 * En ésta interface PgimComponenteMinRepository incluye los metodos que
 * permitrá listar sus componentes de las unidades mineras.
 * 
 * @descripción: Logica de negocio de la entidad Componentes de hechos verificados
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 17/12/2024
 * @fecha_de_ultima_actualización: 18/12/2024
 */
public interface ComponenteHcRepository extends JpaRepository<PgimComponenteHc, Long> {
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTOResultado( "
            + "chc.idComponenteHc, hcon.idHechoConstatado, cmis.idCmineroSprvsion, tcom.noValorParametro, "
            + "cmis.pgimComponenteMinero.coComponente, cmis.pgimComponenteMinero.noComponente, chc.flAplica "
            + ") " 
            + "FROM PgimComponenteHc chc "
            + "INNER JOIN chc.pgimHechoConstatado hcon " 
            + "INNER JOIN chc.pgimCmineroSprvsion cmis " 
            + "INNER JOIN cmis.pgimComponenteMinero cmin "
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE chc.esRegistro = '1' "
            + "AND hcon.idHechoConstatado = :idHechoConstatado "
            + "ORDER BY chc.idComponenteHc ASC"
    )
    List<PgimComponenteHcDTO> listarComponenteMineroHc(@Param("idHechoConstatado") Long idHechoConstatado);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTOResultado( "
            + "chc.idComponenteHc, hcon.idHechoConstatado, cmis.idCmineroSprvsion, tcom.noValorParametro, " 
            + "cmis.pgimComponenteMinero.coComponente, cmis.pgimComponenteMinero.noComponente, chc.flAplica, true "
            + ") "
            + "FROM PgimComponenteHc chc "
            + "INNER JOIN chc.pgimHechoConstatado hcon "
            + "INNER JOIN chc.pgimCmineroSprvsion cmis "
            + "INNER JOIN cmis.pgimComponenteMinero cmin "
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE chc.esRegistro = '1' "
            + "AND chc.idComponenteHc = :idComponenteHc "
            )
    PgimComponenteHcDTO obtenerComponenteMineroHcId(@Param("idComponenteHc") Long idComponenteHc);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTOResultado( "
            + "chc.idComponenteHc, hcon.idHechoConstatado, cmis.idCmineroSprvsion, tcom.noValorParametro, " 
            + "cmis.pgimComponenteMinero.coComponente, cmis.pgimComponenteMinero.noComponente, chc.flAplica, true "
            + ") "
            + "FROM PgimComponenteHc chc "
            + "INNER JOIN chc.pgimHechoConstatado hcon "
            + "INNER JOIN chc.pgimCmineroSprvsion cmis "
            + "INNER JOIN cmis.pgimComponenteMinero cmin "
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE chc.esRegistro = '1' "
            + "AND cmin.idComponenteMinero = :idComponenteMinero "
            + "AND hcon.idHechoConstatado = :idHechoConstatado "
            )
    PgimComponenteHcDTO obtenerComponenteMineroHcIdComponenteMinero(@Param("idComponenteMinero") Long idComponenteMinero, 
                    @Param("idHechoConstatado") Long idHechoConstatado);
}
