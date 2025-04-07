package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.PgimCmineroSprvsionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCmineroSprvsion;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;

public interface CmineroSprvsionService {

    List<PgimCmineroSprvsionDTO> listarComponenteMineroSupervision(Long idSupervision);

    PgimCmineroSprvsionDTO obtenerComponenteMineroSupervisionId(Long idCmineroSprvsion);

    PgimCmineroSprvsion getByIdComponenteMineroSupervision(Long idCmineroSprvsion);

    PgimCmineroSprvsionDTO agregarComponentesMineroSupHcNuevo(@Valid PgimCmineroSprvsionDTO PgimCmineroSprvsionDTO, AuditoriaDTO auditoriaDTO) 
            throws Exception;
}
