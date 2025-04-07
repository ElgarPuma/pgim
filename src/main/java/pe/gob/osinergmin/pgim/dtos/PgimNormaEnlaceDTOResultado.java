package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimNormaEnlaceDTOResultado extends PgimNormaEnlaceDTO {
	
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.NormaEnlaceRepository#listarNormaEnlace
	 * @param idNormaEnlace
	 * @param idNorma
	 * @param deEnlace
	 */
	public PgimNormaEnlaceDTOResultado(Long idNormaEnlace, Long idNorma, String deTitulo, String deEnlace) {
        super();
        this.setIdNormaEnlace(idNormaEnlace);
        this.setIdNorma(idNorma);
        this.setDeTitulo(deTitulo);
        this.setDeEnlace(deEnlace);
    }
}
