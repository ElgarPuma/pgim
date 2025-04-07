package pe.gob.osinergmin.pgim.dtos;

public class PgimReporteDTOResultado extends PgimReporteDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ReporteRepository#listarReporte()
     * @see pe.gob.osinergmin.pgim.models.repository.ReporteRepository#listarReporteGeneral()
     */
    public PgimReporteDTOResultado(Long idReporte, String deTitulo, String deReporte, Long idTipoReporte,
            String descNoTipoReporte, String deObjetivo, Long idMateria, String descNoMateria, Long idGrupoReporte,
            String descNoGrupoReporte, String deUrlRelativo, String coReporte) {
        this.setIdReporte(idReporte);
        this.setDeTitulo(deTitulo);
        this.setDeReporte(deReporte);
        this.setIdTipoReporte(idTipoReporte);
        this.setDescNoTipoReporte(descNoTipoReporte);
        this.setDeObjetivo(deObjetivo);
        this.setIdMateria(idMateria);
        this.setDescNoMateria(descNoMateria);
        this.setIdGrupoReporte(idGrupoReporte);
        this.setDescNoGrupoReporte(descNoGrupoReporte);
        this.setDeUrlRelativo(deUrlRelativo);
        this.setCoReporte(coReporte);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ReporteRepository#obtenerReportePorId()
     */
    public PgimReporteDTOResultado(Long idReporte, String deTitulo, String deReporte, Long idTipoReporte,
            String descNoTipoReporte, String deObjetivo, Long idMateria, String descNoMateria, Long idGrupoReporte,
            String descNoGrupoReporte, String deUrlRelativo) {
        this.setIdReporte(idReporte);
        this.setDeTitulo(deTitulo);
        this.setDeReporte(deReporte);
        this.setIdTipoReporte(idTipoReporte);
        this.setDescNoTipoReporte(descNoTipoReporte);
        this.setDeObjetivo(deObjetivo);
        this.setIdMateria(idMateria);
        this.setDescNoMateria(descNoMateria);
        this.setIdGrupoReporte(idGrupoReporte);
        this.setDescNoGrupoReporte(descNoGrupoReporte);
        this.setDeUrlRelativo(deUrlRelativo);
    }
}
