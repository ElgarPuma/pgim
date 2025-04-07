package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReporteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimReporte;

public interface ReporteService {

    /**
     * Lista de reportes
     * 
     * @param paginador
     * @return
     * @throws Exception
     */
    Page<PgimReporteDTO> listarReporte(PgimReporteDTO filtroReporteDTO, Pageable paginador) throws Exception;

    /**
     * Permite obtener la lista de reportes por el id.
     * 
     * @param idReporte
     * @return
     */
    PgimReporteDTO obtenerReportePorId(Long idReporte);

    /**
     * Permite modificar los reportes.
     * 
     * @param pgimReporteDTO
     * @param pgimReporte
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    PgimReporteDTO modificarReporte(@Valid PgimReporteDTO pgimReporteDTO, PgimReporte pgimReporte,
            AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite obtener los reportes de acuerdo con su identificador interno.
     * 
     * @param idReporte
     * @return
     */
    PgimReporte getByIdReporte(Long idReporte);

    /**
     * Me permite listar los reportes en general
     * @return
     */
    List<PgimReporteDTO> listarReporteGeneral(Long parametro);    

    /**
     * Me permite listar los reportes en general por coClaveTexto
     * @return
     */
    List<PgimReporteDTO> listarReporteGeneralByCoClaveTexto(String parametro);
}
