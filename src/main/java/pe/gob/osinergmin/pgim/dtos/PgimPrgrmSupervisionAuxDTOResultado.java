package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimPrgrmSupervisionAuxDTOResultado extends PgimPrgrmSupervisionAuxDTO{
	
	
	/**
     * 
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionAuxRepository#listarProgramasAux()
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionAuxRepository#obtenerProgramaAuxById()
	 * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionAuxRepository#obtenerProgramaAuxByIdInstanciaProceso()
     * 
     * @param idProgramaSupervisionAux
     * @param idProgramaSupervision
     * @param idEspecialidad
     * @param noEspecialidad
     * @param idDivisionSupervisora
     * @param deDivisionSupervisora
     * @param noProgramaSupervision
     * @param nuAnio
     * @param moPartida
     * @param moCostoTotal
     * @param personaAsignada
     * @param usuarioAsignado
     * @param idFaseActual
     * @param noFaseActual
     * @param idPasoActual
     * @param noPasoActual
     * @param idInstanciaProceso
     * @param idRelacionPaso
     * @param idTipoRelacion
     * @param noTipoRelacion
     * @param idLineaPrograma
     * @param feLineaPrograma
     * @param idLineaProgramaEstado
     * @param estadoLineaBase
     * @param flLeido
     * @param feLectura
     * @param feInstanciaPaso
     * @param noPersonaOrigen
     * @param flPasoActivo
     * @param deMensaje
     * @param idInstanciaPaso
     */
	public PgimPrgrmSupervisionAuxDTOResultado(Long idProgramaSupervisionAux, Long idProgramaSupervision, Long idEspecialidad, 
            String noEspecialidad, Long idDivisionSupervisora, String deDivisionSupervisora, 
            String noProgramaSupervision, Long nuAnio, BigDecimal moPartida, 
            BigDecimal moCostoTotal, String personaAsignada, String usuarioAsignado,
			Long idFaseActual, String noFaseActual, Long idPasoActual, 
            String noPasoActual, Long idInstanciaProceso, Long idRelacionPaso, 
            Long idTipoRelacion, String noTipoRelacion, Long idLineaPrograma, 
            Date feLineaPrograma, Long idLineaProgramaEstado, String estadoLineaBase, 
            String flLeido, Date feLectura, Date feInstanciaPaso, 
            String noPersonaOrigen, String flPasoActivo, String deMensaje,
            Long idInstanciaPaso
			) {
        super();
        
        this.setIdProgramaSupervisionAux(idProgramaSupervisionAux);
        this.setIdProgramaSupervision(idProgramaSupervision);        
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDeDivisionSupervisora(deDivisionSupervisora);
        this.setNoProgramaSupervision(noProgramaSupervision);
        this.setNuAnio(nuAnio);
        this.setMoPartida(moPartida);
        this.setMoCostoTotal(moCostoTotal);        
        this.setPersonaAsignada(personaAsignada);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdTipoRelacion(idTipoRelacion);
        this.setNoTipoRelacion(noTipoRelacion);        
        this.setIdLineaPrograma(idLineaPrograma);
        this.setFeLineaPrograma(feLineaPrograma);
        this.setIdLineaProgramaEstado(idLineaProgramaEstado);
        this.setEstadoLineaBase(estadoLineaBase);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);

        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setFlPasoActivo(flPasoActivo);
        this.setDeMensaje(deMensaje);

        this.setIdInstanciaPaso(idInstanciaPaso);
    }

}
