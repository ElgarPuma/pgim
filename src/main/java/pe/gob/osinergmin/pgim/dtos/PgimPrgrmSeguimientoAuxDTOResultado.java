package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimPrgrmSeguimientoAuxDTOResultado extends PgimPrgrmSeguimientoAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSeguimientoAuxRepository#listarProgramaSeguimiento
   	 *
	 * @param idSupervisionAux
	 * @param idSupervision
	 * @param idProgramaSupervision
	 * @param idLineaPrograma
	 * @param noUnidadMinera
	 * @param noTipoSupervision
	 * @param feInicioSupervision
	 * @param feFinSupervision
	 * @param feInicioSupervisionReal
	 * @param feFinSupervisionReal
	 * @param idItemProgramaSupe
	 * @param noFaseActual
	 * @param noPasoActual
	 * @param noResponsable
	 * @param feInicioRealOprogramada
	 * @param feFinRealOprogramada
	 * @param costo
	 * @param feMesEstimado
	 * @param fechaIniFinMesEstimado
	 * @param idInstanciaPasoActual
	 */
    public PgimPrgrmSeguimientoAuxDTOResultado(Long idSupervisionAux, Long idSupervision, Long idProgramaSupervision,
            Long idLineaPrograma, String noUnidadMinera, String noTipoSupervision, Date feInicioSupervision,
            Date feFinSupervision, Date feInicioSupervisionReal, Date feFinSupervisionReal, Long idItemProgramaSupe,
            String noFaseActual, String noPasoActual, String noResponsable, Date feInicioRealOprogramada,
            Date feFinRealOprogramada, BigDecimal costo, Date feMesEstimado, String fechaIniFinMesEstimado,
            Long idInstanciaPasoActual ) {
        super();
        this.setIdSupervisionAux(idSupervisionAux);
        this.setIdSupervision(idSupervision);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setIdItemProgramaSupe(idItemProgramaSupe);
        this.setNoFaseActual(noFaseActual);
        this.setNoPasoActual(noPasoActual);
        this.setNoResponsable(noResponsable);
        this.setFeInicioRealOprogramada(feInicioRealOprogramada);
        this.setFeFinRealOprogramada(feFinRealOprogramada);
        this.setCosto(costo);

        this.setFeMesEstimado(feMesEstimado);
        this.setFechaIniFinMesEstimado(fechaIniFinMesEstimado);
        
        this.setIdInstanciaPasoActual(idInstanciaPasoActual);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSeguimientoAuxRepository#obtenerSeguimientoPorSupervisionId(Long,
     *      Long)
     * @see pe.gob.osinergmin.pgim.models.repository.PrgrmSeguimientoAuxRepository#obtenerSeguimientoPorItemProgramaId(Long,
     *      Long)
     * @param
     * @return
     * 
     */
    public PgimPrgrmSeguimientoAuxDTOResultado(Long idSupervisionAux, Long idSupervision, Long idUnidadMinera,
            Long idProgramaSupervision, Long idLineaPrograma, Long idTipoSupervision, String noUnidadMinera,
            String noTipoSupervision, Long idItemProgramaSupe, String noPasoActual, 
            Long idInstanciaPasoActual, String noResponsable,
            BigDecimal costo, String deSubtipoSupervision, BigDecimal moConsumoContrato,
            BigDecimal moCostoEstimadoSupervision, String situacion, String agente, String coSupervision,
            Date feInicioRealOprogramada, Date feFinRealOprogramada, Date feMesEstimado,
            String fechaIniFinMesEstimado) {
        super();
        this.setIdSupervisionAux(idSupervisionAux);
        this.setIdSupervision(idSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdTipoSupervision(idTipoSupervision);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setIdItemProgramaSupe(idItemProgramaSupe);
        this.setNoPasoActual(noPasoActual);
        this.setNoResponsable(noResponsable);
        this.setCosto(costo);
        this.setDeSubtipoSupervision(deSubtipoSupervision);
        this.setMoConsumoContrato(moConsumoContrato);
        this.setMoCostoEstimadoSupervision(moCostoEstimadoSupervision);
        this.setSituacion(situacion);
        this.setAgente(agente);
        this.setCoSupervision(coSupervision);
        this.setFeInicioRealOprogramada(feInicioRealOprogramada);
        this.setFeFinRealOprogramada(feFinRealOprogramada);
        this.setFeMesEstimado(feMesEstimado);
        this.setFechaIniFinMesEstimado(fechaIniFinMesEstimado);
        
        this.setIdInstanciaPasoActual(idInstanciaPasoActual);
    }
}
