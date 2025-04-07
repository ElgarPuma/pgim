package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimLineaPrograma;

/**
 * 
 * @descripción: Logica de negocio de la entidad línea base del programa
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 01/12/2020
 * @fecha_de_ultima_actualización:
 */
@Repository
public interface LineaProgramaRepository extends JpaRepository<PgimLineaPrograma, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTOResultado(" +
           "liba.idLineaPrograma, prsu.idProgramaSupervision, liba.lineaProgramaEstado.idValorParametro, liba.feLineaPrograma) " +
           "FROM PgimLineaPrograma liba INNER JOIN liba.pgimPrgrmSupervision prsu " +
           "WHERE prsu.idProgramaSupervision = :idProgramaSupervision " +
           "AND liba.feLineaPrograma = (SELECT MAX(libai.feLineaPrograma) " +
           "FROM PgimLineaPrograma libai " +
           "WHERE libai.pgimPrgrmSupervision.idProgramaSupervision = :idProgramaSupervision " +
           "AND libai.esRegistro = '1' AND libai.lineaProgramaEstado.idValorParametro in (343L,344L,345L,355L) ) " +
           "AND liba.esRegistro = '1' " +
           "AND prsu.esRegistro = '1'"
           )
	PgimLineaProgramaDTO obtenerLineaProgramaActual(@Param("idProgramaSupervision") Long idProgramaSupervision);
    
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTOResultado( "
            + "liba.idLineaPrograma, liba.lineaProgramaEstado.idValorParametro,liba.lineaProgramaEstado.noValorParametro "
            + ", liba.feLineaPrograma, liba.pgimPrgrmSupervision.idProgramaSupervision "
            + ") "
            + "FROM PgimLineaPrograma liba " 
            + "WHERE "
            + "liba.pgimPrgrmSupervision.idProgramaSupervision = :idProgramaSupervision " 
            + "AND liba.esRegistro = '1' " 
            + "AND liba.lineaProgramaEstado.idValorParametro in (343L,344L,345L,355L) "
            + "ORDER BY liba.feLineaPrograma desc"
            )
 	List<PgimLineaProgramaDTO> listarLineaBase(@Param("idProgramaSupervision") Long idProgramaSupervision);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTOResultado( "
            + "liba.idLineaPrograma, liba.lineaProgramaEstado.idValorParametro,liba.lineaProgramaEstado.noValorParametro "
            + ", liba.feLineaPrograma, liba.pgimPrgrmSupervision.idProgramaSupervision "
            + ") "
            + "FROM PgimLineaPrograma liba " 
            + "WHERE "
            + "liba.idLineaPrograma = :idLineaPrograma " 
            + "AND liba.esRegistro = '1' " 
            )
 	PgimLineaProgramaDTO obtenerLineaProgramaPorId(@Param("idLineaPrograma") Long idLineaPrograma);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimLineaProgramaDTOResultado( "
            + "liba.idLineaPrograma, liba.lineaProgramaEstado.idValorParametro,liba.lineaProgramaEstado.noValorParametro "
            + ", liba.feLineaPrograma, liba.pgimPrgrmSupervision.idProgramaSupervision "
            + ") "
            + "FROM PgimLineaPrograma liba " 
            + "WHERE "
            + "liba.pgimPrgrmSupervision.idProgramaSupervision = :idProgramaSupervision " 
            + "AND liba.esRegistro = '1' " 
            + "AND liba.lineaProgramaEstado.idValorParametro = :idLineaProgramaEstado "
            + "ORDER BY liba.feLineaPrograma desc"
            )
 	List<PgimLineaProgramaDTO> listarLineaBasePorEstado(@Param("idProgramaSupervision") Long idProgramaSupervision,@Param("idLineaProgramaEstado") Long idLineaProgramaEstado);

}
