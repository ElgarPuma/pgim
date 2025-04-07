package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimDestRecursoExtAuxDTOResultado extends PgimDestRecursoExtAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.DestRecursoExtRepository#ListarDestinoMineralesMetalicos()
     * @param anioPro
     * @param mes
     * @param idCliente
     * @param ruc
     * @param titularMinero
     * @param estrato
     * @param codigo
     * @param unidad
     * @param recursoExtraido
     * @param destino
     * @param codigoPlanta
     * @param nombrePlanta
     * @param pais
     * @param idUnidadMedida
     * @param descripcion
     * @param cantidadTm
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
    public PgimDestRecursoExtAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String recursoExtraido, String destino,
            String codigoPlanta, String nombrePlanta, String pais,String idUnidadMedida,
            String descripcion, BigDecimal cantidadTm, String pctCu, String pctPb, String pctZn, String agOzTc,
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
        this.setRecursoExtraido(recursoExtraido);
        this.setDestino(destino);
        this.setCodigoPlanta(codigoPlanta);
        this.setNombrePlanta(nombrePlanta);
        this.setPais(pais);
        this.setIdUnidadMedida(idUnidadMedida);
        this.setDescripcion(descripcion);
        this.setCantidadTm(cantidadTm);
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
}
