package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;

public interface RelacionPasoService {

    /**
     * La función "listarRelacionPasoPorIdProceso" recupera una página de objetos
     * "PgimRelacionPasoDTO"
     * basándose en los parámetros "idProceso" y "page" dados.
     * 
     * @param idProceso El ID del proceso para el cual desea listar la relación
     *                  paso.
     * @param page      El número de página y el tamaño para la paginación.
     * @return El método devuelve un objeto Page que contiene objetos
     *         PgimRelacionPasoDTO.
     */
    Page<PgimRelacionPasoDTO> listarRelacionPasoPorIdProceso(Long idProceso, Pageable page);

}
