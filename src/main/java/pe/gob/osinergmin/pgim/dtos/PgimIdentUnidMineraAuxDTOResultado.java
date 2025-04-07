package pe.gob.osinergmin.pgim.dtos;

public class PgimIdentUnidMineraAuxDTOResultado extends PgimIdentUnidMineraAuxDTO {

    /**
	 * @see pe.gob.osinergmin.pgim.models.repository.IdentUnidMineraAuxRepository#listarIdentUnidMineras()
	 */
    public PgimIdentUnidMineraAuxDTOResultado(Long idCliente, String anioPro, String mes, String ruc,
            String titularMinero, String estrato, String codigo, String unidad, String idClaseSustancia,
            String idSituacionup, String desSituacionup) {
        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setIdClaseSustancia(idClaseSustancia);
        this.setIdSituacionup(idSituacionup);
        this.setDesSituacionup(desSituacionup);
    }
}
