package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimMineralRecibPlantaAuxDTOResultado extends PgimMineralRecibPlantaAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MineralRecibPlantaAuxRepository#listarMineralRecibPlanta()
     */
    public PgimMineralRecibPlantaAuxDTOResultado(
            String anioPro, String mes, Long idCliente, String ruc, String titularMinero, String estrato, String codigo,
            String unidad, String proceso, String region, String procedencia, String obtenidoDe, String unidadObtenido,
            Long rucProveedor, String nombreProveedor, String producto, String idUnidadMedida, String descripcion,
            BigDecimal cantidadRecibida, BigDecimal cantidadProcesada, String pctCu, String pctPb, String pctZn,
            String agOzTc, String auGrTm, String pctFe, String pctMo, String pctSn, String pctCd, String pctWo3,
            String pctSb, String pctAs, String pctMn, String pctBi, String pcHg, String pctIn, String pctSe,
            String pctTe, String h2so4, String u, String pctNi, String pctMg) {

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
        this.setObtenidoDe(obtenidoDe);
        this.setUnidadObtenido(unidadObtenido);
        this.setRucProveedor(rucProveedor);
        this.setNombreProveedor(nombreProveedor);
        this.setProducto(producto);
        this.setIdUnidadMedida(idUnidadMedida);
        this.setDescripcion(descripcion);
        this.setCantidadRecibida(cantidadRecibida);
        this.setCantidadProcesada(cantidadProcesada);
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
        this.setU(u);
        this.setPctNi(pctNi);
        this.setPctMg(pctMg);
    }
}
