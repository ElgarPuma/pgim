package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.PgimRecursoExtraidoAuxDTO;

/**
 * @descripción: Servicio para recurso extraido (minerales metálicos)
 *
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 21/12/2022
 */
public interface RecursoExtraidoService {
    
    Page<PgimRecursoExtraidoAuxDTO> listarRecursosExtraidosPaginado(PgimRecursoExtraidoAuxDTO filtro, Pageable paginador);
}
