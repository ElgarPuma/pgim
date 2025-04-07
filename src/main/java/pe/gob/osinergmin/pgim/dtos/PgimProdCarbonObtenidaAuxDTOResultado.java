package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimProdCarbonObtenidaAuxDTOResultado extends PgimProdCarbonObtenidaAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProdCarbonObtenidaAuxRepository#listarProdCarbonObtenida()
     */
    public PgimProdCarbonObtenidaAuxDTOResultado(
            Long idCliente, String anioPro, String mes, String ruc, String titularMinero, String estrato,
            String categoria, String codigo, String unidad, String codigoIntegranteUea, String unidadIntegrante,
            String region, String provincia, String distrito, String producto, String idUnidadMedida,
            String descripcion, BigDecimal cantidad) {

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
            this.setProducto(producto);
            this.setIdUnidadMedida(idUnidadMedida);
            this.setDescripcion(descripcion); 
            this.setCantidad(cantidad);
    }
}
