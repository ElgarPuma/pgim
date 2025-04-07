package pe.gob.osinergmin.pgim.dtos;

public class PgimProgramaInversionAuxDTOResultado extends PgimProgramaInversionAuxDTO {

     /**
     * @see pe.gob.osinergmin.pgim.models.repository.ProgramaInversionAuxRepository#listarReporteProgramaInversiones()
     */
    public PgimProgramaInversionAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato, String codigo,
            String unidad, String tipoInversion, Integer nivelDetalle, Integer orden, String otrosDescripcion,
            Integer cantidadProgramada, Integer cantidadEjecutada,
            Integer capexSostenimiento, Integer capexCrecimiento) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCliente(idCliente);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setTipoInversion(tipoInversion);
        this.setNivelDetalle(nivelDetalle);
        this.setOrden(orden);
        this.setOtrosDescripcion(otrosDescripcion);
        this.setCantidadProgramada(cantidadProgramada);
        this.setCantidadEjecutada(cantidadEjecutada);
        this.setCapexSostenimiento(capexSostenimiento);
        this.setCapexCrecimiento(capexCrecimiento);
    }
}
