package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimPrincipalProyectoInversionAuxDTOResultado extends PgimPrincipalProyectoInversionAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PrincipalProyectoInversionAuxRepository#listarReportePrincipalesProyectosInvers()
     */
    public PgimPrincipalProyectoInversionAuxDTOResultado(String anioPro, String mes, Long idCliente, String ruc,
            String titularMinero, String estrato,
            String nombreProyecto, Date fechaInicio, Date fechaTermino, String region, String etapaProyecto,
            BigDecimal aniosVidaUtil,
            BigDecimal presupuestoGlobal, BigDecimal presupuestoAnualEstimado, BigDecimal inversionAcu2018,
            BigDecimal inversionMesAnterior, BigDecimal inversionMes,
            BigDecimal produccionAnual, String porcAvanceFisico, String proyectoCarteraMinem, String mineralPrincipal,
            String otrosMinerales, String empleoOperacion, String empleoConstruccion,
            String nombreInversionista, String concesionMinera) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCliente(idCliente);
        this.setRuc(ruc);
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setNombreProyecto(nombreProyecto);
        this.setFechaInicio(fechaInicio);
        this.setFechaTermino(fechaTermino);
        this.setRegion(region);
        this.setEtapaProyecto(etapaProyecto);
        this.setAniosVidaUtil(aniosVidaUtil);
        this.setPresupuestoGlobal(presupuestoGlobal);
        this.setPresupuestoAnualEstimado(presupuestoAnualEstimado);
        this.setInversionAcu2018(inversionAcu2018);
        this.setInversionMesAnterior(inversionMesAnterior);
        this.setInversionMes(inversionMes);
        this.setProduccionAnual(produccionAnual);
        this.setPorcAvanceFisico(porcAvanceFisico);
        this.setProyectoCarteraMinem(proyectoCarteraMinem);
        this.setMineralPrincipal(mineralPrincipal);
        this.setOtrosMinerales(otrosMinerales);
        this.setEmpleoOperacion(empleoOperacion);
        this.setEmpleoConstruccion(empleoConstruccion);
        this.setNombreInversionista(nombreInversionista);
        this.setConcesionMinera(concesionMinera);
    }
}
