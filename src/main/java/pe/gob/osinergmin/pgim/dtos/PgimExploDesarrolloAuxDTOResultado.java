package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimExploDesarrolloAuxDTOResultado extends PgimExploDesarrolloAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ExploDesarrolloAuxRepository#listarExploDesarrollo()
     */
    public PgimExploDesarrolloAuxDTOResultado(Long idCliente, String anioPro, String mes,
            String ruc, String titularMinero, String estrato, String codigo, String unidad, String labor,
            BigDecimal exploracionCantidadEjecutada, BigDecimal exploracionNroLabores, BigDecimal desarrolloCantidadEjecutada,
            BigDecimal desarrolloNroLabores, BigDecimal preparacionCantidadEjecutada, BigDecimal preparacionNroLabores,
            BigDecimal explotacionCantidadEjecutada, BigDecimal explotacionNroLabores) {
        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setLabor(labor);
        this.setExploracionCantidadEjecutada(exploracionCantidadEjecutada);
        this.setExploracionNroLabores(exploracionNroLabores);
        this.setDesarrolloCantidadEjecutada(desarrolloCantidadEjecutada);
        this.setDesarrolloNroLabores(desarrolloNroLabores);
        this.setPreparacionCantidadEjecutada(preparacionCantidadEjecutada);
        this.setPreparacionNroLabores(preparacionNroLabores);
        this.setExplotacionCantidadEjecutada(explotacionCantidadEjecutada);
        this.setExplotacionNroLabores(explotacionNroLabores);
    }

}
