package pe.gob.osinergmin.pgim.dtos;

public class PgimOtroIndicadorAuxDTOResultado extends PgimOtroIndicadorAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.OtroIndicadorAuxRepository#listarOtrosIndicadores()
     */
    public PgimOtroIndicadorAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String proceso, String concepto,
            String valor, String unidadMedida, String explicacion) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCliente(idCliente);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setProceso(proceso);
        this.setConcepto(concepto);
        this.setValor(valor);
        this.setUnidadMedida(unidadMedida);
        this.setExplicacion(explicacion);
    }
}
