package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimProduccionNoMetalicaAuxDTOResultado extends PgimProduccionNoMetalicaAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProduccionNoMetalicaRepository#listarProduccionNoMetalica()
     */
    public PgimProduccionNoMetalicaAuxDTOResultado(Long idCliente, String anioPro, String mes, String ruc, String titularMinero,
            String estrato, String categoria, String codigo, String unidad, String codigoIntegranteUea,
            String unidadIntegrante, String region, String provincia, String distrito, String productoExtraido, BigDecimal cantidadExtraido, 
            String productoFinal, BigDecimal cantidadProductoFinal, String idUnidadMedida, String descripcion) {
            super();
            this.setIdCliente(idCliente);
            this.setAnioPro(anioPro);
            this.setMes(mes);
            this.setRuc(ruc);
            this.setTitularMinero(titularMinero);
            this.setEstrato(estrato);
            this.setCategoria(categoria);
            this.setCodigo(codigo);
            this.setUnidad(unidad);
            this.setCodigoIntegranteUea(codigoIntegranteUea);
            this.setUnidadIntegrante(unidadIntegrante);
            this.setRegion(region);
            this.setProvincia(provincia);
            this.setDistrito(distrito);
            this.setProductoExtraido(productoExtraido);
            this.setCantidadExtraido(cantidadExtraido);
            this.setProductoFinal(productoFinal);
            this.setCantidadProductoFinal(cantidadProductoFinal);
            this.setIdUnidadMedida(idUnidadMedida);
            this.setDescripcion(descripcion);

    }
}
