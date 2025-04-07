package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCmineroSprvsion;

public interface CmineroSprvsionRepository extends JpaRepository<PgimCmineroSprvsion, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTOResultado( "
            + "cmins.idCmineroSprvsion, sup.idSupervision, sup.coSupervision, cmin.idComponenteMinero, "
            + "tcom.noValorParametro, cmin.coComponente, cmin.noComponente, true "
            + ") " 
            + "FROM PgimCmineroSprvsion cmins "
            + "INNER JOIN cmins.pgimSupervision sup " 
            + "INNER JOIN cmins.pgimComponenteMinero cmin " 
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE cmins.esRegistro = '1' "
            + "AND sup.idSupervision = :idSupervision "
            + "ORDER BY cmins.idCmineroSprvsion ASC"
    )
    List<PgimCmineroSprvsionDTO> listarComponenteMineroSupervision(@Param("idSupervision") Long idSupervision);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTOResultado( "
            + "cmins.idCmineroSprvsion, sup.idSupervision, sup.coSupervision, cmin.idComponenteMinero, "
            + "tcom.noValorParametro, cmin.coComponente, cmin.noComponente, true "
            + ") "
            + "FROM PgimCmineroSprvsion cmins "
            + "INNER JOIN cmins.pgimSupervision sup "
            + "INNER JOIN cmins.pgimComponenteMinero cmin "
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE cmins.esRegistro = '1' "
            + "AND cmins.idCmineroSprvsion = :idCmineroSprvsion "
            )
    PgimCmineroSprvsionDTO obtenerComponenteMineroSupervisionId(@Param("idCmineroSprvsion") Long idCmineroSprvsion);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTOResultado( "
            + "cmins.idCmineroSprvsion, sup.idSupervision, sup.coSupervision, cmin.idComponenteMinero, "
            + "tcom.noValorParametro, cmin.coComponente, cmin.noComponente, true "
            + ") "
            + "FROM PgimCmineroSprvsion cmins "
            + "INNER JOIN cmins.pgimSupervision sup "
            + "INNER JOIN cmins.pgimComponenteMinero cmin "
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE cmins.esRegistro = '1' "
            + "AND cmin.idComponenteMinero = :idComponenteMinero "
            )
    PgimCmineroSprvsionDTO obtenerComponenteMineroSupervisionIdComponenteMinero(@Param("idComponenteMinero") Long idComponenteMinero);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTOResultado( "
            + "cmins.idCmineroSprvsion, sup.idSupervision, sup.coSupervision, cmin.idComponenteMinero, "
            + "tcom.noValorParametro, cmin.coComponente, cmin.noComponente, true "
            + ") "
            + "FROM PgimCmineroSprvsion cmins "
            + "INNER JOIN cmins.pgimSupervision sup "
            + "INNER JOIN cmins.pgimComponenteMinero cmin "
            + "INNER JOIN cmin.tipoComponenteMinero tcom "
            + "WHERE cmins.esRegistro = '1' "
            + "AND cmin.idComponenteMinero = :idComponenteMinero "
            + "AND sup.idSupervision = :idSupervision "
            )
    PgimCmineroSprvsionDTO obtenerComponenteMineroSupervisionIdCompMineroIdSup(@Param("idComponenteMinero") Long idComponenteMinero, 
                    @Param("idSupervision") Long idSupervision);
}
