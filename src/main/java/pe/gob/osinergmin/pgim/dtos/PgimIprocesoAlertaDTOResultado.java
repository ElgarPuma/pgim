package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimIprocesoAlertaDTOResultado extends PgimIprocesoAlertaDTO {

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.IprocesoAlertaRepository#obtenerIProcesoAlerta
   * @see pe.gob.osinergmin.pgim.models.repository.IprocesoAlertaRepository#obtenerIProcesoAlertaActivos
   * @param idIprocesoAlerta
   * @param idTipoProcesoAlerta
   * @param idIprocesoAlertaPadre
   * @param idInstanciaProceso
   * @param flActivo
   * @param feIntervalo1Inicial
   * @param feIntervalo1Final
   * @param feIntervalo2Inicial
   * @param feIntervalo2Final
   * @param feIntervalo3Inicial
   * @param feIntervalo3Final
   */
  public PgimIprocesoAlertaDTOResultado(Long idIprocesoAlerta,
      Long idTipoProcesoAlerta, Long idIprocesoAlertaPadre, Long idInstanciaProceso, String flActivo,
      Date feIntervalo1Inicial, Date feIntervalo1Final, Date feIntervalo2Inicial, Date feIntervalo2Final,
      Date feIntervalo3Inicial, Date feIntervalo3Final) {
    super();
    this.setIdIprocesoAlerta(idIprocesoAlerta);
    this.setIdTipoProcesoAlerta(idTipoProcesoAlerta);
    this.setIdIprocesoAlertaPadre(idIprocesoAlertaPadre);
    this.setIdInstanciaProceso(idInstanciaProceso);
    this.setFeIntervalo1Inicial(feIntervalo1Inicial);
    this.setFeIntervalo1Final(feIntervalo1Final);
    this.setFeIntervalo2Inicial(feIntervalo2Inicial);
    this.setFeIntervalo2Final(feIntervalo2Final);
    this.setFeIntervalo3Inicial(feIntervalo3Inicial);
    this.setFeIntervalo3Final(feIntervalo3Final);
    this.setFlActivo(flActivo);
    
  }

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.IprocesoAlertaRepository#listarAlertasCumpleByNoUsuarioDestino
   * 
   * @param idIprocesoAlerta
   * @param idTipoProcesoAlerta
   * @param idIprocesoAlertaPadre
   * @param idInstanciaProceso
   * @param flActivo
   * @param feIntervalo1Inicial
   * @param feIntervalo1Final
   * @param feIntervalo2Inicial
   * @param feIntervalo2Final
   * @param feIntervalo3Inicial
   * @param feIntervalo3Final
   * @param descNoEtiquetaOtrabajo
   * @param noUsuarioDestino
   */
  public PgimIprocesoAlertaDTOResultado(Long idIprocesoAlerta,
      Long idTipoProcesoAlerta, Long idIprocesoAlertaPadre, Long idInstanciaProceso, String flActivo,
      Date feIntervalo1Inicial, Date feIntervalo1Final, Date feIntervalo2Inicial, Date feIntervalo2Final,
      Date feIntervalo3Inicial, Date feIntervalo3Final, String descNoEtiquetaOtrabajo, String descNoTipoProcesoAlerta, String diEnlace) {
        
    super();
    this.setIdIprocesoAlerta(idIprocesoAlerta);
    this.setIdTipoProcesoAlerta(idTipoProcesoAlerta);
    this.setIdIprocesoAlertaPadre(idIprocesoAlertaPadre);
    this.setIdInstanciaProceso(idInstanciaProceso);
    this.setFeIntervalo1Inicial(feIntervalo1Inicial);
    this.setFeIntervalo1Final(feIntervalo1Final);
    this.setFeIntervalo2Inicial(feIntervalo2Inicial);
    this.setFeIntervalo2Final(feIntervalo2Final);
    this.setFeIntervalo3Inicial(feIntervalo3Inicial);
    this.setFeIntervalo3Final(feIntervalo3Final);
    this.setFlActivo(flActivo);
    this.setDescNoEtiquetaOtrabajo(descNoEtiquetaOtrabajo);
    this.setDescNoTipoProcesoAlerta(descNoTipoProcesoAlerta);
    this.setDiEnlace(diEnlace);
    
  }


  /**
   * @see pe.gob.osinergmin.pgim.models.repository.IprocesoAlertaRepository#listarIProcesoAlertaPorIdInstancia
   * 
   * @param idIprocesoAlerta
   * @param idTipoProcesoAlerta
   * @param idIprocesoAlertaPadre
   * @param idInstanciaProceso
   * @param flActivo
   * @param feIntervalo1Inicial
   * @param feIntervalo1Final
   * @param feIntervalo2Inicial
   * @param feIntervalo2Final
   * @param feIntervalo3Inicial
   * @param feIntervalo3Final
   * @param descNoTipoProcesoAlerta
   * @param descNoAlerta
   * @param descDeAlerta
   * @param descDetAlerta
   */
  public PgimIprocesoAlertaDTOResultado(Long idIprocesoAlerta,
      Long idTipoProcesoAlerta, Long idIprocesoAlertaPadre, Long idInstanciaProceso, String flActivo,
      Date feIntervalo1Inicial, Date feIntervalo1Final, Date feIntervalo2Inicial, Date feIntervalo2Final,
      Date feIntervalo3Inicial, Date feIntervalo3Final, String descNoTipoProcesoAlerta, String descNoAlerta, String descDeAlerta, String descDetAlerta) {
        
    super();
    this.setIdIprocesoAlerta(idIprocesoAlerta);
    this.setIdTipoProcesoAlerta(idTipoProcesoAlerta);
    this.setIdIprocesoAlertaPadre(idIprocesoAlertaPadre);
    this.setIdInstanciaProceso(idInstanciaProceso);
    this.setFeIntervalo1Inicial(feIntervalo1Inicial);
    this.setFeIntervalo1Final(feIntervalo1Final);
    this.setFeIntervalo2Inicial(feIntervalo2Inicial);
    this.setFeIntervalo2Final(feIntervalo2Final);
    this.setFeIntervalo3Inicial(feIntervalo3Inicial);
    this.setFeIntervalo3Final(feIntervalo3Final);
    this.setFlActivo(flActivo);
    this.setDescNoTipoProcesoAlerta(descNoTipoProcesoAlerta);
    this.setDescNoAlerta(descNoAlerta);
    this.setDescDeAlerta(descDeAlerta);
    this.setDescDetAlerta(descDetAlerta);
    
  }

}
