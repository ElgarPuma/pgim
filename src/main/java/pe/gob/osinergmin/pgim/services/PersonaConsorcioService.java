package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTO;

public interface PersonaConsorcioService {

    Page<PgimPersonaConsorcioDTO> listarConsorcios(PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO, Pageable paginador);

    PgimPersonaConsorcioDTO obtenerConsorcioPorId(Long idPersonaConsorcio);

    List<PgimPersonaConsorcioDTO> listarPorConsorcio(String palabraClave);

}
