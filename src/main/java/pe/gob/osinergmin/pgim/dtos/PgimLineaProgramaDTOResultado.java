package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimLineaProgramaDTOResultado extends PgimLineaProgramaDTO {

    /**
     * * @see pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository#obtenerLineaProgramaActual()
     * @param idLineaPrograma
     * @param idProgramaSupervision
     * @param idLineaProgramaEstado
     * @param feLineaPrograma
     */
    public PgimLineaProgramaDTOResultado(Long idLineaPrograma, Long idProgramaSupervision, Long idLineaProgramaEstado,
            Date feLineaPrograma) {
        super();

        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setIdLineaProgramaEstado(idLineaProgramaEstado);
        this.setFeLineaPrograma(feLineaPrograma);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository#listarLineaBase()
     * @see pe.gob.osinergmin.pgim.models.repository.LineaProgramaRepository#listarLineaBasePorEstado 
     * @param idProgramaSupervision
     * 
     */
    public PgimLineaProgramaDTOResultado(Long idLineaPrograma, Long idLineaProgramaEstado,String noLineaProgramaEstado
            ,Date feLineaPrograma, Long idProgramaSupervision) {
        super();
        
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdLineaProgramaEstado(idLineaProgramaEstado);
        this.setFeLineaProgramaDesc(noLineaProgramaEstado);
        this.setFeLineaPrograma(feLineaPrograma);
        this.setIdProgramaSupervision(idProgramaSupervision);
    }
    
    

}
