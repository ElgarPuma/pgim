package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTipopameXContrato;

/**
 * Interace para la implementación de consultas personalizadas a la entidad
 * <code>PgimTipopameXContrato<code/>
 * 
 * @descripción: Permite la implementación de las consultas personalizadas que
 *               se requieren realizar sobre la tabla
 *               PGIM_TD_TIPOPAME_X_CONTRATO
 * 
 * @author hdiaz
 * @version: 1.0
 * @fecha_de_creación: 07/08/2022
 * @fecha_de_ultima_actualización: 07/08/2022
 */
@Repository
public interface TipoParametroMedicionPorContratoRepository extends JpaRepository<PgimTipopameXContrato, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTOResultado("
            + "tpxc.idTipopameXContrato, tpxc.deRangoMedicion, tpxc.dePrecision, "
            + "tpxc.deExactitud, tpxc.deResolucion, tiin.idTipoInstrumento, "
            + "tiin.coTipoInstrumento, tiin.noTipoInstrumento, tipm.coTipoParametro, "
            + "tipm.noTipoParametro "
            + ") "
            + "FROM PgimTipopameXContrato tpxc "
            + "     INNER JOIN tpxc.pgimContrato cont "
            + "     INNER JOIN tpxc.pgimTprmtroXTinstrmnto tpxi "
            + "     INNER JOIN tpxi.pgimTipoInstrumento tiin "
            + "     INNER JOIN tpxi.pgimTipoParametroMed tipm "
            + "WHERE cont.idContrato = :idContrato "
            + "AND tpxc.esRegistro = '1' "
            + "AND cont.esRegistro = '1' "
            + "AND tpxi.esRegistro = '1' "
            + "AND tiin.esRegistro = '1' "
            + "AND tipm.esRegistro = '1' "
            + "ORDER BY tiin.coTipoInstrumento, tipm.coTipoParametro "
            )
    List<PgimTipopameXContratoDTO> listarParametrosPorContrato(@Param("idContrato") Long idContrato);

}
