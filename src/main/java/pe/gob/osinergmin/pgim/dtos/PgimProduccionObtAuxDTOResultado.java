package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimProduccionObtAuxDTOResultado extends PgimProduccionObtAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProduccionObtRepository#ListarProduccionObtenida()
     * @see pe.gob.osinergmin.pgim.models.repository.ProduccionObtRepository#listarReporteProduccionObtenida()
     * @param anioPro
     * @param mes
     * @param idCliente
     * @param ruc
     * @param titularMinero
     * @param estrato
     * @param codigo
     * @param unidad
     * @param proceso
     * @param region
     * @param procedencia
     * @param nombreVendedor
     * @param producto
     * @param ratio
     * @param idUnidadMedida
     * @param descripcion
     * @param cantidad
     * @param pctCu
     * @param pctPb
     * @param pctZn
     * @param agOzTc
     * @param auGrTm
     * @param pctFe
     * @param pctMo
     * @param pctSn
     * @param pctCd
     * @param pctWo3
     * @param pctSb
     * @param pctAs
     * @param pctMn
     * @param pctBi
     * @param pcHg
     * @param pctIn
     * @param pctSe
     * @param pctTe
     * @param h2so4
     * @param pctu
     * @param pctNi
     * @param pctMg
     */
    public PgimProduccionObtAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String proceso, String region,
            String procedencia, String nombreVendedor, String producto, BigDecimal ratio, String idUnidadMedida,
            String descripcion, BigDecimal cantidad, String pctCu, String pctPb, String pctZn, String agOzTc,
            String auGrTm, String pctFe, String pctMo, String pctSn, String pctCd, String pctWo3, String pctSb,
            String pctAs, String pctMn, String pctBi, String pcHg, String pctIn, String pctSe, String pctTe,
            String h2so4, String pctu, String pctNi, String pctMg) {
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
        this.setRegion(region);
        this.setProcedencia(procedencia);
        this.setNombreVendedor(nombreVendedor);
        this.setProducto(producto);
        this.setRatio(ratio);
        this.setIdUnidadMedida(idUnidadMedida);
        this.setDescripcion(descripcion);
        this.setCantidad(cantidad);
        this.setPctCu(pctCu);
        this.setPctPb(pctPb);
        this.setPctZn(pctZn);
        this.setAgOzTc(agOzTc);
        this.setAuGrTm(auGrTm);
        this.setPctFe(pctFe);
        this.setPctMo(pctMo);
        this.setPctSn(pctSn);
        this.setPctCd(pctCd);
        this.setPctWo3(pctWo3);
        this.setPctSb(pctSb);
        this.setPctAs(pctAs);
        this.setPctMn(pctMn);
        this.setPctBi(pctBi);
        this.setPcHg(pcHg);
        this.setPctIn(pctIn);
        this.setPctSe(pctSe);
        this.setPctTe(pctTe);
        this.setH2so4(h2so4);
        this.setPctu(pctu);
        this.setPctNi(pctNi);
        this.setPctMg(pctMg);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProduccionObtRepository#listarProduccionObtenidaUM()
     * @param producto
     * @param anioPro
     * @param mes
     * @param proceso
     * @param cantidad
     * @param descripcion
     */
    public PgimProduccionObtAuxDTOResultado(String producto, String anioPro, String mes, String proceso, BigDecimal cantidad, String descripcion) {
        super();
        this.setProducto(producto);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setProceso(proceso);
        this.setCantidad(cantidad);
        this.setDescripcion(descripcion);
    }
    
}
