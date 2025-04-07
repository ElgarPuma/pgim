package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimRankingSupervisionDTOResultado extends PgimRankingSupervisionDTO {
	
	
	/**
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.RankingSupervisionRepository#obtenerRankingSupervisionPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingSupervisionRepository#obtenerRankingSupervisionPorIdSupervision()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingSupervisionRepository#obtenerRankingsSupervisionPorConfiguracionRiesgo()
     * @see pe.gob.osinergmin.pgim.models.repository.RankingSupervisionRepository#listarRankingSupervisionPorUmConfig()
     */
    public PgimRankingSupervisionDTOResultado(
        Long idRankingSupervision,        
        Long idSupervision,         
        Long idConfiguraRiesgo, 
        BigDecimal moPuntaje, 
        Long descIdEspecialidad,        
        String descNoEspecialidad,
        Long descIdUnidadMinera,
        String descNoUnidadMinera
    ) {
        super();
        this.setIdRankingSupervision(idRankingSupervision);
        this.setIdSupervision(idSupervision);
        this.setIdConfiguraRiesgo(idConfiguraRiesgo);
        this.setMoPuntaje(moPuntaje);
        
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescIdUnidadMinera(descIdUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        
    }

}
