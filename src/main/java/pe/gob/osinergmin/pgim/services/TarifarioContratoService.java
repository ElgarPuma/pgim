package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.dtos.Tarifario;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioContrato;

public interface TarifarioContratoService {

	List<PgimTarifarioContratoDTO> listarTarifarioContrato(Long idContrato);
	
    PgimTarifarioContratoDTO obtenerTarifarioContratoPorId(Long idTarifarioContrato);
    
    Tarifario crearTarifario(Tarifario tarifario, AuditoriaDTO auditoriaDTO);
    
    Tarifario obtenerTarifario(Long idTarifarioContrato);
    
    Tarifario modificarTarifario(Tarifario tarifario, AuditoriaDTO auditoriaDTO);
    
    PgimTarifarioContrato getByIdTarifarioContrato(Long idTarifarioContrato);
    
    void eliminarTarifario(PgimTarifarioContrato pgimTarifarioContrato, AuditoriaDTO auditoriaDTO);

    /**
     * Permite listar la lista de los tarifarios que cumplen con la regla definida por los parámetros enviados.
     * @param idContrato    Identificador interno del contrato en cuestión.
     * @param idSubtipoSupervision Subtipo de supervisión.
     * @param idMotivoSupervision Motivo de la supervisión.
     * @return
     */
    List<PgimTarifarioReglaDTO> listarTarifariosCumplenRegla(Long idContrato, Long idSubtipoSupervision, Long idMotivoSupervision);

    List<PgimTarifarioReglaDTO> validarCrearTarifario(Tarifario tarifario);
    
    List<PgimTarifarioReglaDTO> validarModificarTarifario(Tarifario tarifario);
}
