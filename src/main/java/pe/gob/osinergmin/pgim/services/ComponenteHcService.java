package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteHc;

public interface ComponenteHcService {
    
    List<PgimComponenteHcDTO> listarComponenteMineroHc(Long idHechoConstatado);

    PgimComponenteHcDTO obtenerComponenteMineroHcId(Long idComponenteHc);

    PgimComponenteHc getByIdComponenteHc(Long idComponenteHc);

}
