package pe.gob.osinergmin.pgim.dtos;

public class PgimIndicadorGeotecnicoAuxDTOResultado extends PgimIndicadorGeotecnicoAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorGeotecnicoRepository#listarIndicadorGeotecnico()
     */
    public PgimIndicadorGeotecnicoAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String grupo, String indicador,
            String valor, String unidadMedida) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCliente(idCliente);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setGrupo(grupo);
        this.setIndicador(indicador);
        this.setValor(valor);
        this.setUnidadMedida(unidadMedida);
    }
}
