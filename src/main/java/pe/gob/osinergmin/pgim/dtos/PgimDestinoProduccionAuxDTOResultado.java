package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimDestinoProduccionAuxDTOResultado extends PgimDestinoProduccionAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DestinoProduccionAuxRepository#listarDestinoProduccion()
     */
    public PgimDestinoProduccionAuxDTOResultado(Long idCliente, String anioPro, String mes, String ruc,
            String titularMinero, String estrato, String codigo, String plantaOrigen, String destino, String regionpais,
            String dua, String producto, String idUnidadMedida, String descripcion, BigDecimal cantidad,
            BigDecimal valor) {
        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setPlantaOrigen(plantaOrigen);
        this.setDestino(destino);
        this.setRegionpais(regionpais);
        this.setDua(dua);
        this.setProducto(producto);
        this.setIdUnidadMedida(idUnidadMedida);
        this.setDescripcion(descripcion);
        this.setCantidad(cantidad);
        this.setValor(valor);
    }
}
