package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimPrmtroXSupervDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPrmtroXSuperv;

/**
 * En ésta interface PrmtroXSupervRepository esta conformado por su(s) metodo(s) de obtener los parametros del instrumento de medición.
 * 
 * @descripción: Lógica de negocio de la entidad parametros del instrumento de medición.
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 01/12/2020
 * @fecha_de_ultima_actualización: 01/12/2020 
 */
public interface PrmtroXSupervRepository extends JpaRepository<PgimPrmtroXSuperv, Long>{

  /**
   * Permite obtener los parametros del instrumento de medición
   * @param idInstrmntoXSuperv
   * @return
   */
  @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimPrmtroXSupervDTOResultado( pasu.idPrmtroXSuperv, "
    + "insu.idInstrmntoXSuperv, tpxc.idTipopameXContrato, pasu.deRangoMedicion, "
    + "pasu.dePrecision, pasu.deExactitud, pasu.deResolucion, "
    + "tipa.noTipoParametro "
    + " ) "
    + "FROM PgimPrmtroXSuperv pasu "
    + "INNER JOIN pasu.pgimInstrmntoXSuperv insu "
    + "INNER JOIN pasu.pgimTipopameXContrato tpxc "
    + "INNER JOIN tpxc.pgimTprmtroXTinstrmnto tpti "
    + "INNER JOIN tpti.pgimTipoParametroMed tipa "    
    + "WHERE insu.idInstrmntoXSuperv = :idInstrmntoXSuperv "
    + "AND pasu.esRegistro = '1' "
    + "AND insu.esRegistro = '1' "
    + "AND tpxc.esRegistro = '1' "
    + "AND tpti.esRegistro = '1' "
    + "AND tipa.esRegistro = '1' "
    )
  List<PgimPrmtroXSupervDTO> obtenerParamInstrumentoSuperv(@Param("idInstrmntoXSuperv") Long idInstrmntoXSuperv);
  
  
}
