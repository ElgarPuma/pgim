package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimFichaObservacionDTOResultado extends PgimFichaObservacionDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaObservacionRepository#obtenerFichaObservacionPorId
	 * @see pe.gob.osinergmin.pgim.models.repository.FichaObservacionRepository#obtenerFichaObservacionPorIdFichaRevision
	 * @param idFichaObservacion
	 * @param idFichaRevision
	 * @param idTipoObservacionFicha
	 * @param idFichaObservacionOrigen
	 * @param feRevisionFicha
	 * @param caDiasParaPresentacion
	 * @param feDesdeParaPresentacion
	 * @param fePresentacion
	 * @param deParteInformeObservada
	 * @param deItemObservacion
	 * @param flSubsanada
	 * @param cmItemObservacion
	 * @param descTipoObservacionFicha
	 */
	public PgimFichaObservacionDTOResultado(Long idFichaObservacion, Long idFichaRevision,
			  Long idTipoObservacionFicha, Long idFichaObservacionOrigen, Date feRevisionFicha,
			  Integer caDiasParaPresentacion, Date feDesdeParaPresentacion, Date fePresentacion,
			  String deParteInformeObservada, String deItemObservacion, String flSubsanada,
			  String cmItemObservacion, String descTipoObservacionFicha, Date feCreacion) {
		  super();
	      this.setIdFichaObservacion(idFichaObservacion);
	      this.setIdFichaRevision(idFichaRevision);
	      this.setIdTipoObservacionFicha(idTipoObservacionFicha);
	      this.setIdFichaObservacionOrigen(idFichaObservacionOrigen);
	      this.setFeRevisionFicha(feRevisionFicha);
	      this.setCaDiasParaPresentacion(caDiasParaPresentacion);
	      this.setFeDesdeParaPresentacion(feDesdeParaPresentacion);
	      this.setFePresentacion(fePresentacion);
	      this.setDeParteInformeObservadaT(deParteInformeObservada);
	      this.setDeItemObservacionT(deItemObservacion);
	      this.setFlSubsanada(flSubsanada);
	      this.setCmItemObservacionT(cmItemObservacion);
	      this.setDescTipoObservacionFicha(descTipoObservacionFicha);
	      this.setFeCreacion(feCreacion);
    }
}