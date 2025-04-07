package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimCarboniferaDestinoAuxDTOResultado extends PgimCarboniferaDestinoAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.CarboniferaDestinoAuxRepository#listarCarboniferaDestino()
     */
    public PgimCarboniferaDestinoAuxDTOResultado(Long idCliente, String anioPro, String mes, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String recursoExtraido, String destino,
            String pais, String idUnidadMedida, String descripcion, BigDecimal cantidad, BigDecimal valor,
            String idMoneda) {

        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setRecursoExtraido(recursoExtraido);
        this.setDestino(destino);
        this.setPais(pais);
        this.setIdUnidadMedida(idUnidadMedida);
        this.setDescripcion(descripcion);
        this.setCantidad(cantidad);
        this.setValor(valor);
        this.setIdMoneda(idMoneda);
    }
}
