package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimPenalidadAuxDTOResultado extends PgimPenalidadAuxDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PenalidadAuxRepository#listarReportePenalidadesPeriodoContratoSupervisora()
	 * @param idPenalidadAux
	 * @param idLiquidacion
	 * @param nuLiquidacion
	 * @param idContrato
	 * @param nuContrato
	 * @param idEspecialidad
	 * @param noEspecialidad
	 * @param feCreacion
	 * @param idFaseActual
	 * @param noFaseActual
	 * @param idPasoActual
	 * @param noPasoActual
	 * @param moItemConsumo
	 * @param moPenalidadPlazo
	 * @param moPenalidadReincidencia
	 * @param moPenalidadSinEpp
	 * @param moPenalidad
	 * @param descNoRazonSocial
	 */
	public PgimPenalidadAuxDTOResultado(Long idPenalidadAux, Long idLiquidacion, String nuLiquidacion, Long idContrato,     
			String nuContrato, Long idEspecialidad, String noEspecialidad, Date feCreacion, Long idFaseActual,
			String noFaseActual, Long idPasoActual, String noPasoActual, BigDecimal moItemConsumo, BigDecimal moPenalidadPlazo,
			BigDecimal moPenalidadReincidencia, BigDecimal moPenalidadSinEpp, BigDecimal moPenalidad,
			String descNoRazonSocial) {
        super();
        
        this.setIdPenalidadAux(idPenalidadAux);
        this.setIdLiquidacion(idLiquidacion);
        this.setNuLiquidacion(nuLiquidacion);
        this.setIdContrato(idContrato);     
        this.setNuContrato(nuContrato);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setFeCreacion(feCreacion);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setMoItemConsumo(moItemConsumo);
        this.setMoPenalidadPlazo(moPenalidadPlazo);
        this.setMoPenalidadReincidencia(moPenalidadReincidencia);        
        this.setMoPenalidadSinEpp(moPenalidadSinEpp);        
        this.setMoPenalidad(moPenalidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
    }
	
}
