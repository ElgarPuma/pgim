package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimParametroDTOResultado extends PgimParametroDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ParametroRepository#listarPorPalabraClave
	 * @param idParametro
	 * @param coParametro
	 * @param deParametro
	 * @param nuOrden
	 */
	public PgimParametroDTOResultado(Long idParametro, String coParametro, String deParametro, Long nuOrden) {
        super();
        this.setIdParametro(idParametro);
        this.setCoParametro(coParametro);
        this.setDeParametro(deParametro);
        this.setNuOrden(nuOrden);
    }
}
