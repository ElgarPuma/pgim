package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimResponsableSuperDTO;

public interface ResponsableSuperService {
    
    /***
     * Permite listar las responsables por palabra clave.
     * 
     * @param palabra Palabra clave responsables.
     * @return
     */
    List<PgimResponsableSuperDTO> listarPorPalabraClave(String palabra);
}