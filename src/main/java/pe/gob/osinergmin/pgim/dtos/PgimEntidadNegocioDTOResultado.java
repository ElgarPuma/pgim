package pe.gob.osinergmin.pgim.dtos;

public class PgimEntidadNegocioDTOResultado  extends PgimEntidadNegocioDTO{

    public PgimEntidadNegocioDTOResultado(Long idEntidadNegocio, String noTablaEntidadNegocio, String deEtiquetaTablaNegocio){

        super();
        this.setIdEntidadNegocio(idEntidadNegocio);
        this.setNoTablaEntidadNegocio(noTablaEntidadNegocio);
        this.setDeEtiquetaTablaNegocio(deEtiquetaTablaNegocio);

    }
    

}
