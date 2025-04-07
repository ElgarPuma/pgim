package pe.gob.osinergmin.pgim.dtos;

public class PgimTitularAuxDTOResultado extends PgimTitularAuxDTO {

    /**
	 * @see pe.gob.osinergmin.pgim.models.repository.TitularAuxRepository#listarTitular()
	 */
    public PgimTitularAuxDTOResultado(Long idCliente, String anioPro, String mes, String ruc, String titularMinero,
            String estrato, Long idRepresentante, String nombreRepresentanteLegal, String email,
            String cargoRepresentante, String nombreresponsable, Long cargoResponsable, String descripcion,
            String respNoDocumento, String respEmail, String respTelefono, String respTelefonoMovil, Long idCargo) {
        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setIdRepresentante(idRepresentante);
        this.setNombreRepresentanteLegal(nombreRepresentanteLegal);
        this.setEmail(email);
        this.setCargoRepresentante(cargoRepresentante);
        this.setNombreresponsable(nombreresponsable);
        this.setCargoResponsable(cargoResponsable);
        this.setDescripcion(descripcion);
        this.setRespNoDocumento(respNoDocumento);
        this.setRespEmail(respEmail);
        this.setRespTelefono(respTelefono);
        this.setRespTelefonoMovil(respTelefonoMovil);
        this.setIdCargo(idCargo);
    }
}
