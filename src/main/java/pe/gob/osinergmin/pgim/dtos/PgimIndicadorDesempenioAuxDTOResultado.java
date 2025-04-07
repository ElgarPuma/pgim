package pe.gob.osinergmin.pgim.dtos;

public class PgimIndicadorDesempenioAuxDTOResultado extends PgimIndicadorDesempenioAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorDesempenioRepository#listarIndicadoresDesempenioOperativo()
     */
    public PgimIndicadorDesempenioAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String coeficiente, String unidadM,
            String descripcion, String cantidad, String explicacion) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCliente(idCliente);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setCoeficiente(coeficiente);
        this.setUnidadM(unidadM);
        this.setDescripcion(descripcion);
        this.setCantidad(cantidad);
        this.setExplicacion(explicacion);
    }
}
