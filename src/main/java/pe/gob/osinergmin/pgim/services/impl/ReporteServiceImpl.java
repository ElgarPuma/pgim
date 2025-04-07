package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReporteDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimReporte;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ReporteRepository;
import pe.gob.osinergmin.pgim.services.ReporteService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Reporte
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public Page<PgimReporteDTO> listarReporte(PgimReporteDTO filtroReporteDTO, Pageable paginador) throws Exception {
        Page<PgimReporteDTO> lPgimReporteDTO = this.reporteRepository.listarReporte(filtroReporteDTO.getIdMateria(),
                filtroReporteDTO.getIdTipoReporte(), filtroReporteDTO.getIdGrupoReporte(),
                filtroReporteDTO.getTextoBusqueda(), paginador);

        return lPgimReporteDTO;
    }

    @Override
    public PgimReporteDTO obtenerReportePorId(Long idReporte) {
        return this.reporteRepository.obtenerReportePorId(idReporte);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimReporteDTO modificarReporte(@Valid PgimReporteDTO pgimReporteDTO, PgimReporte pgimReporte,
            AuditoriaDTO auditoriaDTO) throws Exception {
        this.configurarValores(pgimReporteDTO, pgimReporte);

        pgimReporte.setFeActualizacion(auditoriaDTO.getFecha());
        pgimReporte.setUsActualizacion(auditoriaDTO.getUsername());
        pgimReporte.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimReporte pgimDenunciaModificado = this.reporteRepository.save(pgimReporte);

        PgimReporteDTO pgimDenunciaDTOModificado = this.obtenerReportePorId(pgimDenunciaModificado.getIdReporte());

        return pgimDenunciaDTOModificado;
    }

    @Transactional(readOnly = false)
    private PgimReporte configurarValores(PgimReporteDTO pgimReporteDTO, PgimReporte pgimReporte) {

        // Tipo de reporte (Consolidado y/o Detallado)
        if (pgimReporteDTO.getIdTipoReporte() != null) {
            PgimValorParametro pgimValorParametro = new PgimValorParametro();
            pgimValorParametro.setIdValorParametro(pgimReporteDTO.getIdTipoReporte());
            pgimReporte.setTipoReporte(pgimValorParametro);
        } else {
            pgimReporte.setTipoReporte(null);
        }

        // Tipo de materia
        if (pgimReporteDTO.getIdMateria() != null) {
            PgimValorParametro pgimValorParametro = new PgimValorParametro();
            pgimValorParametro.setIdValorParametro(pgimReporteDTO.getIdMateria());
            pgimReporte.setMateria(pgimValorParametro);
        } else {
            pgimReporte.setMateria(null);
        }

        pgimReporte.setDeTitulo(pgimReporteDTO.getDeTitulo());
        pgimReporte.setDeReporte(pgimReporteDTO.getDeReporte());
        pgimReporte.setDeObjetivo(pgimReporteDTO.getDeObjetivo());

        return pgimReporte;
    }

    @Override
    public PgimReporte getByIdReporte(Long idReporte) {
        return this.reporteRepository.findById(idReporte).orElse(null);
    }

    @Override
    public List<PgimReporteDTO> listarReporteGeneral(Long parametro) {
        List<PgimReporteDTO> lista = this.reporteRepository.listarReporteGeneral(parametro);
        return lista;
    }
    
    @Override
    public List<PgimReporteDTO> listarReporteGeneralByCoClaveTexto(String parametro) {
        List<PgimReporteDTO> lista = this.reporteRepository.listarReporteGeneralByCoClaveTexto(parametro);
        return lista;
    }
}
