package pe.gob.osinergmin.pgim.dtos;

public class PgimPrmtroXSupervDTOResultado extends PgimPrmtroXSupervDTO{

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.PrmtroXSupervRepository#obtenerParamInstrumentoSuperv()
   * @param idPrmtroXSuperv
   * @param idInstrmntoXSuperv
   * @param idTipopameXContrato
   * @param deRangoMedicion
   * @param dePrecision
   * @param deExactitud
   * @param deResolucion
   */
  public PgimPrmtroXSupervDTOResultado(Long idPrmtroXSuperv, Long idInstrmntoXSuperv, Long idTipopameXContrato, 
    String deRangoMedicion, String dePrecision, String deExactitud, String deResolucion, String descNoTprmtroXTinstrmnto
  ){
    super();
    
    this.setIdPrmtroXSuperv(idPrmtroXSuperv);
    this.setIdInstrmntoXSuperv(idInstrmntoXSuperv);
    this.setIdTipopameXContrato(idTipopameXContrato);
    this.setDeRangoMedicion(deRangoMedicion);
    this.setDePrecision(dePrecision);
    this.setDeExactitud(deExactitud);
    this.setDeResolucion(deResolucion);
    this.setDescNoTprmtroXTinstrmnto(descNoTprmtroXTinstrmnto);
  }

  
}
