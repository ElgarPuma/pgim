package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAgenteSupervisado;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;

public interface AgenteSupervisadoService {

    /**
     * Permite obtener el objeto del agente supervisado.
     * @param idAgenteSupervisado
     * @return
     */
	PgimAgenteSupervisadoDTO obtenerAgenteSupervisado(Long idAgenteSupervisado);

    /**
     * Permite listar los agentes supervisados por palabra clave.
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de
     *                agentes supervisados.
     * @return
     */
    List<PgimAgenteSupervisadoDTO> listarPorPalabraClave(String palabra);
    
    PgimAgenteSupervisado getByIdAgenteSupervisado(Long idAgenteSupervisado);
    
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorId(Long idAgenteSupervisado);
    
    PgimAgenteSupervisadoDTO crearAgenteSupervisado(PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO, AuditoriaDTO auditoriaDTO);
    
    PgimAgenteSupervisadoDTO modificarAgenteSupervisado(PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO,
            PgimPersona pgimPersona,PgimAgenteSupervisado pgimAgenteSupervisadoActual
            , AuditoriaDTO auditoriaDTO);
    
    void eliminarAgenteSupervisado(PgimAgenteSupervisado pgimAgenteSupervisadoActual
    		, AuditoriaDTO auditoriaDTO);


    /**
     * Permite listar los agentes supervisados.
     */
    Page<PgimAgenteSupervisadoDTO> filtrar(PgimAgenteSupervisadoDTO filtroAgenteSupervisado, Pageable paginador);
    
    List<PgimAgenteSupervisadoDTO> existeAgenteSupervisado(Long idAgenteSupervisado, String coDocumentoIdentidad);
    
    PgimAgenteSupervisadoDTO obtenerAgenteSupervisadoPorInstancProceso(Long idInstanciaProceso);
    
    
}