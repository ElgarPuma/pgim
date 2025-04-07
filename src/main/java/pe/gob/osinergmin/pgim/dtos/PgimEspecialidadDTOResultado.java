package pe.gob.osinergmin.pgim.dtos;

public class PgimEspecialidadDTOResultado extends PgimEspecialidadDTO{
    
    public PgimEspecialidadDTOResultado (Long idEspecialidad, String noEspecialidad) 
    {
        super();
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EspecialidadRepository#listarEspecialidadPorDivisionSupervisora
     * @param idEspecialidad
     * @param noEspecialidad
     * @param descIdValorParametro
     * @param descNoValorParametro
     */
    public PgimEspecialidadDTOResultado (Long idEspecialidad, String noEspecialidad,
    		Long descIdValorParametro, String descNoValorParametro){
        super();
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setDescIdValorParametro(descIdValorParametro);
        this.setDescNoValorParametro(descNoValorParametro);
    }
}