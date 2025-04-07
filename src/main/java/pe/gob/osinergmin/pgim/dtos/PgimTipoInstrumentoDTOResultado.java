package pe.gob.osinergmin.pgim.dtos;

public class PgimTipoInstrumentoDTOResultado extends PgimTipoInstrumentoDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository#obtenerTipoInstrumentoPorIdContrato()
     * 
     */
    public PgimTipoInstrumentoDTOResultado(Long idTipoInstrumento, Long idEspecialidad, String coTipoInstrumento,
            String noTipoInstrumento) {
        super();
        this.setIdTipoInstrumento(idTipoInstrumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setCoTipoInstrumento(coTipoInstrumento);
        this.setNoTipoInstrumento(noTipoInstrumento);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.TipoInstrumentoRepository#listarTipoInstrumento()
     * 
     */
    public PgimTipoInstrumentoDTOResultado(Long idTipoInstrumento, Long idEspecialidad, String descNoEspecialidad, String coTipoInstrumento,
            String noTipoInstrumento, String deTipoInstrumento) {
        super();
        this.setIdTipoInstrumento(idTipoInstrumento);
        this.setIdEspecialidad(idEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setCoTipoInstrumento(coTipoInstrumento);
        this.setNoTipoInstrumento(noTipoInstrumento);
        this.setDeTipoInstrumento(deTipoInstrumento);
    }

}
