package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado;
import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;

public interface DemarcacionUMineraService {

	Long registrarDemarcacionUMinera(PgimDemarcacionUmineraDTO demarcacionUmineraDTO, AuditoriaDTO auditoriaDTO);
	
	PgimDemarcacionUmineraDTO obtenerDemarcacionUMinera(Long idDemarcacion);
	
	List<PgimUbigeoDTO> listarUbigeoPorNombre(String noUbigeo);
	
	List<PgimUbigeoDTO> listarUbigeo();
	
	Long modificarUnidadMineraUbicacion(PgimUnidadMineraDTO unidadMineraDTO, AuditoriaDTO auditoriaDTO);

	List<PgimDemarcacionUmineraDTOResultado> listarDemarcacionesPorUm(Long id);
	
	Long eliminarDemarcacionUMinera(Long idDemarcacionUMinera, AuditoriaDTO auditoriaDTO);

	List<PgimDemarcacionUmineraDTO> existeDemarcacionUMinera(Long idUnidadMinera, Long idUbigeo, Long idDemarcacionUm);
	
	PgimDemarcacionUmineraDTO excedeLimiteDemarcacionUMinera(Long idUnidadMinera, Long idDemarcacionUm);
	
    Page<PgimDemarcacionAuxDTO> listarReporteDemarcacionesUMineraPaginado(PgimDemarcacionAuxDTO filtro, Pageable paginador);
}
