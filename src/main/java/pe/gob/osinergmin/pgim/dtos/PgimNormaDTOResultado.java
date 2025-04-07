package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimNormaDTOResultado extends PgimNormaDTO {	
		
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#obtenerNormaPorId(Long)
	 * @param idNorma
	 * @param idTipoNorma
	 * @param noCortoNorma
	 * @param noNorma
	 * @param fePublicacion
	 * @param flVigente
	 */
	public PgimNormaDTOResultado(Long idNorma, Long idTipoNorma, String noCortoNorma, String noNorma, Date fePublicacion, String flVigente) {
        super();
        this.setIdNorma(idNorma);
        this.setIdTipoNorma(idTipoNorma);
        this.setNoCortoNorma(noCortoNorma);
        this.setNoNorma(noNorma);
        this.setFePublicacion(fePublicacion);
        this.setFlVigente(flVigente);        
    }
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#listarPorPalabraClave
	 * @param idNorma
	 * @param noCortoNorma
	 * @param noNorma
	 */
	public PgimNormaDTOResultado(Long idNorma, String noCortoNorma, String noNorma) {
        super();
        this.setIdNorma(idNorma);
        this.setNoCortoNorma(noCortoNorma);
        this.setNoNorma(noNorma);
    }

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#listarTipificacion
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#obtenerTipificacionPorId
	 * @param idNorma
	 * @param noCortoNorma
	 * @param noNorma
	 */
	public PgimNormaDTOResultado(Long idNorma, String noCortoNorma, String noNorma, Date fePublicacion, String flVigente) {
        super();
        this.setIdNorma(idNorma);
        this.setNoCortoNorma(noCortoNorma);
        this.setNoNorma(noNorma);
		this.setFePublicacion(fePublicacion);
        this.setFlVigente(flVigente);  
    }

}
