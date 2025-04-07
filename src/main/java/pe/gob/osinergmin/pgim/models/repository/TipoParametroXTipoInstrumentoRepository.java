package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTprmtroXTinstrmnto;

import java.util.List;

/**
 * Interace para la implementación de consultas personalizadas a la entidad
 * <code>PgimTprmtroXTinstrmnto<code/>
 * 
 * @descripción: Permite la implementación de las consultas personalizadas que
 *               se requieren realizar sobre la tabla PGIM_TM_TPRMTRO_X_TINSTRMNTO
 * 
 * @author hdiaz
 * @version: 1.0
 * @fecha_de_creación: 07/08/2022
 */
@Repository
public interface TipoParametroXTipoInstrumentoRepository extends JpaRepository<PgimTprmtroXTinstrmnto, Long> {
    
    /**
     * 
     * @param idEspecialidad
     * @param idContrato
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado("
            + "tpti.idTprmtroXTinstrmnto, tiin.idTipoInstrumento, tipm.idTipoParametroMed, "
            + "tpti.deRangoMedicion, tpti.dePrecision, tpti.deExactitud, "
            + "tpti.deResolucion, tiin.coTipoInstrumento, tiin.noTipoInstrumento, "
            + "tipm.coTipoParametro, tipm.noTipoParametro "
            + ") "
            + "FROM PgimTprmtroXTinstrmnto tpti "
            + "     INNER JOIN tpti.pgimTipoInstrumento tiin "
            + "     INNER JOIN tpti.pgimTipoParametroMed tipm "
            + "     INNER JOIN tiin.pgimEspecialidad espe "            
            + "WHERE espe.idEspecialidad = :idEspecialidad "
            + "AND NOT EXISTS "
            + "( "
            + "SELECT 1 "
            + "FROM PgimTipopameXContrato tpco "
            + "WHERE tpco.pgimTprmtroXTinstrmnto.idTprmtroXTinstrmnto = tpti.idTprmtroXTinstrmnto "
            + "AND tpco.pgimContrato.idContrato = :idContrato "
            + "AND tpco.esRegistro = '1' "
            + ") "
            + "AND tpti.esRegistro = '1' "
            + "AND tiin.esRegistro = '1' "
            + "AND tipm.esRegistro = '1' "
            + "AND espe.esRegistro = '1' "
            + "ORDER BY tiin.coTipoInstrumento, tipm.coTipoParametro "
            )
    List<PgimTprmtroXTinstrmntoDTO> listarInstrumentosYParametros(@Param("idEspecialidad") Long idEspecialidad, @Param("idContrato") Long idContrato);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado(" 
        + "pxi.idTprmtroXTinstrmnto,tipo.idTipoInstrumento, "
        + "tipo.coTipoInstrumento, tipo.noTipoInstrumento, param.idTipoParametroMed, "
        + "param.coTipoParametro, param.noTipoParametro, COUNT(param.coTipoParametro), "
        + "pxi.deRangoMedicion, pxi.dePrecision, pxi.deExactitud, pxi.deResolucion) "
        + "FROM PgimTprmtroXTinstrmnto pxi "
        + "INNER JOIN pxi.pgimTipoInstrumento tipo "
        + "INNER JOIN pxi.pgimTipoParametroMed param "
        + "WHERE pxi.esRegistro = '1' "
        + "AND tipo.idTipoInstrumento = :idTipoInstrumento "
        + "GROUP BY (pxi.idTprmtroXTinstrmnto, tipo.idTipoInstrumento, tipo.coTipoInstrumento, "
        + "tipo.noTipoInstrumento, param.idTipoParametroMed, param.coTipoParametro, param.noTipoParametro, " 
        + "pxi.deRangoMedicion, pxi.dePrecision, pxi.deExactitud, pxi.deResolucion) "
        )
    Page<PgimTprmtroXTinstrmntoDTO> listarParamXinstrumento(
        @Param("idTipoInstrumento") Long idTipoInstrumento,
        Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado(" 
        + "pxi.idTprmtroXTinstrmnto,tipo.idTipoInstrumento, "
        + "tipo.coTipoInstrumento, tipo.noTipoInstrumento, param.idTipoParametroMed, "
        + "param.coTipoParametro, param.noTipoParametro, COUNT(param.coTipoParametro), "
        + "pxi.deRangoMedicion, pxi.dePrecision, pxi.deExactitud, pxi.deResolucion) "
        + "FROM PgimTprmtroXTinstrmnto pxi "
        + "INNER JOIN pxi.pgimTipoInstrumento tipo "
        + "INNER JOIN pxi.pgimTipoParametroMed param "
        + "WHERE pxi.esRegistro = '1' "
        + "AND tipo.idTipoInstrumento = :idTipoInstrumento "
        + "GROUP BY (pxi.idTprmtroXTinstrmnto, tipo.idTipoInstrumento, tipo.coTipoInstrumento, "
        + "tipo.noTipoInstrumento, param.idTipoParametroMed, param.coTipoParametro, param.noTipoParametro, " 
        + "pxi.deRangoMedicion, pxi.dePrecision, pxi.deExactitud, pxi.deResolucion) "
        )
    List<PgimTprmtroXTinstrmntoDTO> lParamXinstrumento(
        @Param("idTipoInstrumento") Long idTipoInstrumento);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado(" 
        + "pxi.idTprmtroXTinstrmnto, "
        + "tipo.idTipoInstrumento, param.idTipoParametroMed) "
        + "FROM PgimTprmtroXTinstrmnto pxi "
        + "INNER JOIN pxi.pgimTipoInstrumento tipo "
        + "INNER JOIN pxi.pgimTipoParametroMed param "
        + "WHERE pxi.esRegistro = '1' "
        + "AND tipo.idTipoInstrumento = :idTipoInstrumento "
        + "AND param.idTipoParametroMed = :idTipoParametroMed "
        )
    PgimTprmtroXTinstrmntoDTO obtenerParamXinstrExistente(
        @Param("idTipoInstrumento") Long idTipoInstrumento,
        @Param("idTipoParametroMed") Long idTipoParametroMed);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado(" 
        + "pxi.idTprmtroXTinstrmnto, "
        + "tipo.idTipoInstrumento, param.idTipoParametroMed) "
        + "FROM PgimTprmtroXTinstrmnto pxi "
        + "INNER JOIN pxi.pgimTipoInstrumento tipo "
        + "INNER JOIN pxi.pgimTipoParametroMed param "
        + "WHERE pxi.esRegistro = '0' "
        + "AND pxi.idTprmtroXTinstrmnto = :idTprmtroXTinstrmnto "
        )
    PgimTprmtroXTinstrmntoDTO obtenerParamXinstrXidQuitado(
        @Param("idTprmtroXTinstrmnto") Long idTprmtroXTinstrmnto);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado(" 
        + "pxi.idTprmtroXTinstrmnto, "
        + "tipo.idTipoInstrumento, param.idTipoParametroMed) "
        + "FROM PgimTprmtroXTinstrmnto pxi "
        + "INNER JOIN pxi.pgimTipoInstrumento tipo "
        + "INNER JOIN pxi.pgimTipoParametroMed param "
        + "WHERE pxi.esRegistro = '1' "
        + "AND pxi.idTprmtroXTinstrmnto = :idTprmtroXTinstrmnto "
        )
    PgimTprmtroXTinstrmntoDTO obtenerParamXinstrXid(
        @Param("idTprmtroXTinstrmnto") Long idTprmtroXTinstrmnto);


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado("
        + "tpxc.deRangoMedicion, tpxc.dePrecision, "
        + "tpxc.deExactitud, tpxc.deResolucion, tiin.idTipoInstrumento, "
        + "tiin.coTipoInstrumento, tiin.noTipoInstrumento, tipm.coTipoParametro, "
        + "tipm.noTipoParametro, cont.nuContrato "
        + ") "
        + "FROM PgimTipopameXContrato tpxc "
        + "     INNER JOIN tpxc.pgimContrato cont "
        + "     INNER JOIN tpxc.pgimTprmtroXTinstrmnto tpxi "
        + "     INNER JOIN tpxi.pgimTipoInstrumento tiin "
        + "     INNER JOIN tpxi.pgimTipoParametroMed tipm "
        + "WHERE tiin.idTipoInstrumento = :idTipoInstrumento AND tipm.idTipoParametroMed = :idTipoParametroMed "
        + "AND tpxc.esRegistro = '1' "
        + "AND cont.esRegistro = '1' "
        + "AND tpxi.esRegistro = '1' "
        + "AND tiin.esRegistro = '1' "
        + "AND tipm.esRegistro = '1' "
        + "ORDER BY tiin.coTipoInstrumento, tipm.coTipoParametro "
        )
    PgimTprmtroXTinstrmntoDTO obtenerTPramXinstrConContratoXid(@Param("idTipoInstrumento") Long idTipoInstrumento,@Param("idTipoParametroMed") Long idTipoParametroMed);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado("
        + "tpxc.deRangoMedicion, tpxc.dePrecision, "
        + "tpxc.deExactitud, tpxc.deResolucion, tiin.idTipoInstrumento, "
        + "tiin.coTipoInstrumento, tiin.noTipoInstrumento, tipm.coTipoParametro, "
        + "tipm.noTipoParametro, cont.nuContrato "
        + ") "
        + "FROM PgimTipopameXContrato tpxc "
        + "     INNER JOIN tpxc.pgimContrato cont "
        + "     INNER JOIN tpxc.pgimTprmtroXTinstrmnto tpxi "
        + "     INNER JOIN tpxi.pgimTipoInstrumento tiin "
        + "     INNER JOIN tpxi.pgimTipoParametroMed tipm "
        + "WHERE tiin.idTipoInstrumento = :idTipoInstrumento "
        + "AND tpxc.esRegistro = '1' "
        + "AND cont.esRegistro = '1' "
        + "AND tpxi.esRegistro = '1' "
        + "AND tiin.esRegistro = '1' "
        + "AND tipm.esRegistro = '1' "
        + "ORDER BY tiin.coTipoInstrumento, tipm.coTipoParametro "
        )
    List<PgimTprmtroXTinstrmntoDTO> obtenerTInstrConContratoXid(@Param("idTipoInstrumento") Long idTipoInstrumento);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTOResultado("
        + "tpxc.deRangoMedicion, tpxc.dePrecision, "
        + "tpxc.deExactitud, tpxc.deResolucion, tiin.idTipoInstrumento, "
        + "tiin.coTipoInstrumento, tiin.noTipoInstrumento, tipm.coTipoParametro, "
        + "tipm.noTipoParametro, cont.nuContrato "
        + ") "
        + "FROM PgimTipopameXContrato tpxc "
        + "     INNER JOIN tpxc.pgimContrato cont "
        + "     INNER JOIN tpxc.pgimTprmtroXTinstrmnto tpxi "
        + "     INNER JOIN tpxi.pgimTipoInstrumento tiin "
        + "     INNER JOIN tpxi.pgimTipoParametroMed tipm "
        + "WHERE tipm.idTipoParametroMed = :idTipoParametroMed "
        + "AND tpxc.esRegistro = '1' "
        + "AND cont.esRegistro = '1' "
        + "AND tpxi.esRegistro = '1' "
        + "AND tiin.esRegistro = '1' "
        + "AND tipm.esRegistro = '1' "
        + "ORDER BY tiin.coTipoInstrumento, tipm.coTipoParametro "
        )
    List<PgimTprmtroXTinstrmntoDTO> obtenerTPramUseXid(@Param("idTipoParametroMed") Long idTipoParametroMed);

    

}
