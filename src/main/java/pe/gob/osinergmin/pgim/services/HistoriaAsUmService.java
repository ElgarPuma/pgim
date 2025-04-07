package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHistoriaAsUmDTO;

public interface HistoriaAsUmService {
    
    /**
     * Permite listar la historia de los titulares de la unidad minera pasada como parámetro.
     * @param idUnidadMinera
     * @return
     */
    List<PgimHistoriaAsUmDTO> listarHistoriasAS(Long idUnidadMinera);

    /**
     * Permite crear el registro de la historia del cambio del titular de una unidad minera.
     * @param pgimHistoriaAsUmDTO
     * @return
     */
    PgimHistoriaAsUmDTO crearHistoriaAsUm(PgimHistoriaAsUmDTO pgimHistoriaAsUmDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite obtener el registro de historia de titularidad de la UM.
     * @param idHistoriaAsUm Identificador interno del registro histórico de titularidad de la unidad minera.
     * @return
     */
    PgimHistoriaAsUmDTO obtenerHistoriaAsUm(Long idHistoriaAsUm);

    /**
     * Esta metodo permite validar la fecha de inicio del titular que sea posterior al 
	 * anterior titular de agente supervisado de la unidad minera
     * @param idHistoriaAsUm
     * @return
     */
    PgimHistoriaAsUmDTO validarFeInicioTitularidad(Long idHistoriaAsUm);

}