package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimDocumentoAuxDTOResultado extends PgimDocumentoAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#PgimDocumentoAuxDTOResultado
	 * 
	 * @param nuExpedienteSiged
	 * @param noFaseProceso
	 * @param coCategoriaDocumento
	 * @param noCategoriaDocumento
	 * @param coSubcatDocumento
	 * @param noSubcatDocumento
	 * @param deAsuntoDocumento
	 * @param noTipoOrigenDocumento
	 * @param noUsuario
	 * @param feCreacion
	 */
	public PgimDocumentoAuxDTOResultado(
			String nuExpedienteSiged
			, String noFaseProceso
			, String coCategoriaDocumento
			, String noCategoriaDocumento
			, String coSubcatDocumento
			, String noSubcatDocumento
			, String deAsuntoDocumento
			, String noTipoOrigenDocumento
			, String noUsuario
			, Date feCreacion
	) {
		super();
		this.setNuExpedienteSiged(nuExpedienteSiged);
		this.setNoFaseProceso(noFaseProceso);
		this.setCoCategoriaDocumento(coCategoriaDocumento);
		this.setNoCategoriaDocumento(noCategoriaDocumento);
		this.setCoSubcatDocumento(coSubcatDocumento);
		this.setNoSubcatDocumento(noSubcatDocumento);
		this.setDeAsuntoDocumento(deAsuntoDocumento);
		this.setNoTipoOrigenDocumento(noTipoOrigenDocumento);
		this.setNoUsuario(noUsuario);
		this.setFeCreacion(feCreacion);
		
	}

}
