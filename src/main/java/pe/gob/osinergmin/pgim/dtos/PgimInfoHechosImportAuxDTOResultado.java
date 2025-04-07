package pe.gob.osinergmin.pgim.dtos;

public class PgimInfoHechosImportAuxDTOResultado extends PgimInfoHechosImportAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.InfoHechosImportAuxRepository#listarInfoHechosImport()
     */
    public PgimInfoHechosImportAuxDTOResultado(
            Long idCliente, String anioPro, String mes, String ruc, String titularMinero, String estrato,
            String hechosAfectanDeclaracion, String infoGestionSocial) {
        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setHechosAfectanDeclaracion(hechosAfectanDeclaracion);
        this.setInfoGestionSocial(infoGestionSocial);
    }
}
