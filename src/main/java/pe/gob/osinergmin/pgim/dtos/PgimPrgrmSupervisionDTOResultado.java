package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimPrgrmSupervisionDTOResultado extends PgimPrgrmSupervisionDTO{
	
	/**
	@see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#obtenerPrgrmSupervision()
	@see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#obtenerProgramaParaAsignacion()
	*/
	public PgimPrgrmSupervisionDTOResultado(Long idProgramaSupervision, String noProgramaSupervision, Long idEspecialidad, Long idDivisionSupervisora) {
        super();

        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setNoProgramaSupervision(noProgramaSupervision);
        this.setIdEspecialidad(idEspecialidad);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
    }

	
	public PgimPrgrmSupervisionDTOResultado(Long idProgramaSupervision, Long idEspecialidad, String descDeEspecialidad,
			Long idDivisionSupervisora, String descNoDivisionSupervisora, String noProgramaSupervision, Long descNuAnio, 
			BigDecimal moPartida, BigDecimal descMoCosto) {
        super();

        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setNoProgramaSupervision(noProgramaSupervision);
        this.setIdEspecialidad(idEspecialidad);
        this.setDescDeEspecialidad(descDeEspecialidad);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
        this.setDescNuAnio(descNuAnio);
        this.setMoPartida(moPartida);
        this.setDescMoCosto(descMoCosto);
        
    }
	
	/**
	@see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#obtenerPrograma()	
	*/
	public PgimPrgrmSupervisionDTOResultado(Long idProgramaSupervision, Long idEspecialidad, String descDeEspecialidad,
			Long idDivisionSupervisora, String descNoDivisionSupervisora, String noProgramaSupervision, Long descNuAnio, 
			BigDecimal moPartida
			//, BigDecimal descMoCosto
			,Long idInstanciaProceso) {
        super();

        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setNoProgramaSupervision(noProgramaSupervision);
        this.setIdEspecialidad(idEspecialidad);
        this.setDescDeEspecialidad(descDeEspecialidad);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
        this.setDescNuAnio(descNuAnio);
        this.setMoPartida(moPartida);
        //this.setDescMoCosto(descMoCosto);
        this.setIdInstanciaProceso(idInstanciaProceso);
        
    }
	
	/**
	 * Me permite filtrar el nombre de programa de supervision
	 * el metodo es: filtrarPorNombreProgramaSupervision
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#filtrarPorNombreProgramaSupervision()	
	 * @param idProgramaSupervision
	 * @param deProgramaSupervision
	 */
	public PgimPrgrmSupervisionDTOResultado(
        Long idProgramaSupervision
        , String deProgramaSupervision) {
        super();

        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setDeProgramaSupervision(deProgramaSupervision);
    }

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository#listarProgramasSupervPlan	
	 */
	public PgimPrgrmSupervisionDTOResultado(
        Long idProgramaSupervision
        , Long idPlanSupervision
        , Long descIdLineaPrograma
        , String descNoDivisionSupervisora
        , String descDeEspecialidad
        , String descEstado
        , BigDecimal moPartida
        , BigDecimal descMoCosto
        ) {
        super();
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setIdPlanSupervision(idPlanSupervision);
        this.setDescIdLineaPrograma(descIdLineaPrograma);
        this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
        this.setDescDeEspecialidad(descDeEspecialidad);
        this.setDescEstado(descEstado);
        this.setMoPartida(moPartida);
        this.setDescMoCosto(descMoCosto);
    }
}