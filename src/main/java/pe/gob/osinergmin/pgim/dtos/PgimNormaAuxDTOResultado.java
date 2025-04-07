package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimNormaAuxDTOResultado extends PgimNormaAuxDTO {
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#listar
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#obtenerNormaAuxPorId
	 * 
	 * @param idNormaAux
	 * @param idNorma
	 * @param noCortoNorma
	 * @param noNorma
	 * @param flVigente
	 * @param fePublicacion
	 * @param idTipoNorma
	 * @param noTipoNorma
	 */
	public PgimNormaAuxDTOResultado(Long idNorma,  String noCortoNorma, String noNorma, String flVigente, Date fePublicacion, Long idTipoNorma, String noTipoNorma) {
        super();
        //this.setIdNormaAux(idNormaAux);
        this.setIdNorma(idNorma);        
        this.setNoCortoNorma(noCortoNorma);
        this.setNoNorma(noNorma);
        this.setFlVigente(flVigente);
        this.setFePublicacion(fePublicacion);
        this.setIdTipoNorma(idTipoNorma);
        this.setNoTipoNorma(noTipoNorma);
        
    }
	
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.NormaRepository#listarNormasDeCuadroVerificacion()
     * 
     * @param idNorma
     * @param descNoGrupoNorma
     * @param noCortoNorma
     * @param noNorma
     * @param idTipoNorma
     * @param noTipoNorma
     * @param fePublicacion
     * @param flVigente
     */
	public PgimNormaAuxDTOResultado(Long idNorma, String descNoGrupoNorma, String noCortoNorma, String noNorma, Long idTipoNorma, String noTipoNorma, Date fePublicacion, String flVigente) {
	
        super();
        this.setIdNorma(idNorma);
        this.setDescNoGrupoNorma(descNoGrupoNorma);
        this.setNoCortoNorma(noCortoNorma);
        this.setNoNorma(noNorma);
        this.setIdTipoNorma(idTipoNorma);
        this.setNoTipoNorma(noTipoNorma);
        this.setFePublicacion(fePublicacion);
        this.setFlVigente(flVigente);
        
    }

}
