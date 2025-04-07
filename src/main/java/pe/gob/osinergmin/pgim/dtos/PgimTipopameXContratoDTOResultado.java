package pe.gob.osinergmin.pgim.dtos;

public class PgimTipopameXContratoDTOResultado extends PgimTipopameXContratoDTO {

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository#obtenerTipoParamInstrumentoContrato()
   * @param idTipopameXContrato
   * @param idTprmtroXTinstrmnto
   * @param idContrato
   * @param deRangoMedicion
   * @param dePrecision
   * @param deExactitud
   * @param deResolucion
   * @param descNoTipoInstrumento
   * @param descIdTipoInstrumento
   */
  public PgimTipopameXContratoDTOResultado(Long idTipopameXContrato, Long idTprmtroXTinstrmnto, Long idContrato,
      String deRangoMedicion, String dePrecision, String deExactitud, String deResolucion,
      String descNoTipoInstrumento, Long descIdTipoInstrumento) {
    super();

    this.setIdTipopameXContrato(idTipopameXContrato);
    this.setIdTprmtroXTinstrmnto(idTprmtroXTinstrmnto);
    this.setIdContrato(idContrato);
    this.setDeRangoMedicion(deRangoMedicion);
    this.setDePrecision(dePrecision);
    this.setDeExactitud(deExactitud);
    this.setDeResolucion(deResolucion);
    this.setDescNoTipoInstrumento(descNoTipoInstrumento);
    this.setDescIdTipoInstrumento(descIdTipoInstrumento);
  }

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.TipoParametroMedicionPorContratoRepository#listarParametrosPorContrato()
   * @param idTipopameXContrato
   * @param deRangoMedicion
   * @param dePrecision
   * @param deExactitud
   * @param deResolucion
   * @param descIdTipoInstrumento
   * @param descCoTipoInstrumento
   * @param descNoTipoInstrumento
   * @param descCoTipoParametro
   * @param descNoTipoParametro
   */
  public PgimTipopameXContratoDTOResultado(Long idTipopameXContrato, String deRangoMedicion, String dePrecision,
      String deExactitud, String deResolucion, Long descIdTipoInstrumento,
      String descCoTipoInstrumento, String descNoTipoInstrumento, String descCoTipoParametro,
      String descNoTipoParametro) {
    super();

    this.setIdTipopameXContrato(idTipopameXContrato);
    this.setDeRangoMedicion(deRangoMedicion);
    this.setDePrecision(dePrecision);
    this.setDeExactitud(deExactitud);
    this.setDeResolucion(deResolucion);
    this.setDescIdTipoInstrumento(descIdTipoInstrumento);
    this.setDescCoTipoInstrumento(descCoTipoInstrumento);
    this.setDescNoTipoInstrumento(descNoTipoInstrumento);
    this.setDescCoTipoParametro(descCoTipoParametro);
    this.setDescNoTipoParametro(descNoTipoParametro);
  }

}
