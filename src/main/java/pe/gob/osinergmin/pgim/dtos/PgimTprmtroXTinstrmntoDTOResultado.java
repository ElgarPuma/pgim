package pe.gob.osinergmin.pgim.dtos;

public class PgimTprmtroXTinstrmntoDTOResultado extends PgimTprmtroXTinstrmntoDTO {

    /**
     * 
     * @param idTprmtroXTinstrmnto
     * @param idTipoInstrumento
     * @param idTipoParametroMed
     * @param deRangoMedicion
     * @param dePrecision
     * @param deExactitud
     * @param deResolucion
     * @param descCoTipoInstrumento
     * @param descNoTipoInstrumento
     * @param descCoTipoParametro
     * @param descNoTipoParametro
     */
    public PgimTprmtroXTinstrmntoDTOResultado(Long idTprmtroXTinstrmnto, Long idTipoInstrumento,
            Long idTipoParametroMed,
            String deRangoMedicion, String dePrecision, String deExactitud,
            String deResolucion, String descCoTipoInstrumento, String descNoTipoInstrumento,
            String descCoTipoParametro, String descNoTipoParametro) {
        super();

        this.setIdTprmtroXTinstrmnto(idTprmtroXTinstrmnto);
        this.setIdTipoInstrumento(idTipoInstrumento);
        this.setIdTipoParametroMed(idTipoParametroMed);
        this.setDeRangoMedicion(deRangoMedicion);
        this.setDePrecision(dePrecision);
        this.setDeExactitud(deExactitud);
        this.setDeResolucion(deResolucion);
        this.setDescCoTipoInstrumento(descCoTipoInstrumento);
        this.setDescNoTipoInstrumento(descNoTipoInstrumento);
        this.setDescCoTipoParametro(descCoTipoParametro);
        this.setDescNoTipoParametro(descNoTipoParametro);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PgimTipoParametroMedRepository#listarParamXinstrumento()
     * 
     * @param idTipoInstrumento
     * @param descCoTipoInstrumento
     * @param descNoTipoInstrumento
     * @param descCoTipoParametro
     * @param descNoTipoParametro
     * @param descNumUsos
     */
    public PgimTprmtroXTinstrmntoDTOResultado( Long idTprmtroXTinstrmnto,
        Long idTipoInstrumento, String descCoTipoInstrumento, String descNoTipoInstrumento,
        Long idTipoParametroMed, String descCoTipoParametro, String descNoTipoParametro, Long descNumUsos, 
        String deRangoMedicion, String dePrecision, String deExactitud, String deResolucion){
        super();
        this.setIdTprmtroXTinstrmnto(idTprmtroXTinstrmnto);
        this.setIdTipoInstrumento(idTipoInstrumento);
        this.setDescCoTipoInstrumento(descCoTipoInstrumento);
        this.setDescNoTipoInstrumento(descNoTipoInstrumento);
        this.setIdTipoParametroMed(idTipoParametroMed);
        this.setDescCoTipoParametro(descCoTipoParametro);
        this.setDescNoTipoParametro(descNoTipoParametro);
        this.setDescNumUsos(descNumUsos);
        this.setDeRangoMedicion(deRangoMedicion);
        this.setDePrecision(dePrecision);
        this.setDeExactitud(deExactitud);
        this.setDeResolucion(deResolucion);


    }

    public PgimTprmtroXTinstrmntoDTOResultado(
        Long idTprmtroXTinstrmnto, Long idTipoInstrumento, Long idTipoParametroMed){
        super();
        this.setIdTprmtroXTinstrmnto(idTprmtroXTinstrmnto);
        this.setIdTipoInstrumento(idTipoInstrumento);
        this.setIdTipoParametroMed(idTipoParametroMed);
    }

    public PgimTprmtroXTinstrmntoDTOResultado(String deRangoMedicion, String dePrecision,
      String deExactitud, String deResolucion, Long descIdTipoInstrumento,
      String descCoTipoInstrumento, String descNoTipoInstrumento, String descCoTipoParametro,
      String descNoTipoParametro, String nuContrato) {
    super();

    this.setDeRangoMedicion(deRangoMedicion);
    this.setDePrecision(dePrecision);
    this.setDeExactitud(deExactitud);
    this.setDeResolucion(deResolucion);
    this.setDescIdTipoInstrumento(descIdTipoInstrumento);
    this.setDescCoTipoInstrumento(descCoTipoInstrumento);
    this.setDescNoTipoInstrumento(descNoTipoInstrumento);
    this.setDescCoTipoParametro(descCoTipoParametro);
    this.setDescNoTipoParametro(descNoTipoParametro);
    this.setDescNuContrato(nuContrato);
  }
}
