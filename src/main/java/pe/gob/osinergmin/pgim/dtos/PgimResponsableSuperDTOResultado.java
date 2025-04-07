package pe.gob.osinergmin.pgim.dtos;

public class PgimResponsableSuperDTOResultado extends PgimResponsableSuperDTO {
    
    /**
     * Sirve para listar por palabra clave "Nombre del responsable"
     * @param idResponsableSuper
     * @param descNoResponsable
     */
    public PgimResponsableSuperDTOResultado 
    (
        Long idResponsableSuper
        ,String descNoResponsable
    )
    {
        super();
        this.setIdResponsableSuper(idResponsableSuper);
        // this.setDescNoResponsable(descNoResponsable);
    }
}