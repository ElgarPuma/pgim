package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimEstadisticoIncidenteAuxDTOResultado extends PgimEstadisticoIncidenteAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EstadisticoIncidenteAuxRepository#listarEstadisticosIncidente()
     */
    public PgimEstadisticoIncidenteAuxDTOResultado(Long idCliente, String anioPro, String mes, Integer idCorrelativo,
            String ruc,
            String estrato, String titularMinero, String codigo, String unidad, Integer trabajadoresCia,
            Integer trabajadoresCm, Integer trabajadoresOtros, Integer trabajadoresTotal, Integer incidentesMes,
            Integer incidentesAcumalada, Integer accidLevesMes, Integer accidLevesAcum, Integer accidIncapacitMes,
            Integer accidIncapacitAcum,
            Integer accidMortalesMes, Integer accidMortalesAcum, Integer diasperdidosMes, Integer diasperdidosAcum,
            Integer horhombresTrabMes,
            Integer horhombresTrabAcum, BigDecimal indiceFrecuenciaMes, BigDecimal indiceFrecuenciaAcum,
            BigDecimal indiceSeveridadMes, BigDecimal indiceSeveridadAcum,
            BigDecimal indiceAccidentesMes, BigDecimal indiceAccidentesAcum, String usuIngreso, Date fecIngreso,
            String ipIngreso, String usuModifica,
            Date fecModifica, String ipModifica) {
        super();
        this.setIdCliente(idCliente);
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCorrelativo(idCorrelativo);
        this.setRuc(ruc);
        this.setEstrato(estrato);
        this.setTitularMinero(titularMinero);
        this.setCodigo(codigo);
        this.setUnidad(unidad);
        this.setTrabajadoresCia(trabajadoresCia);
        this.setTrabajadoresCm(trabajadoresCm);
        this.setTrabajadoresOtros(trabajadoresOtros);
        this.setTrabajadoresTotal(trabajadoresTotal);
        this.setIncidentesMes(incidentesMes);
        this.setIncidentesAcumalada(incidentesAcumalada);
        this.setAccidLevesMes(accidLevesMes);
        this.setAccidLevesAcum(accidLevesAcum);
        this.setAccidIncapacitMes(accidIncapacitMes);
        this.setAccidIncapacitAcum(accidIncapacitAcum);
        this.setAccidMortalesMes(accidMortalesMes);
        this.setAccidMortalesAcum(accidMortalesAcum);
        this.setDiasperdidosMes(diasperdidosMes);
        this.setDiasperdidosAcum(diasperdidosAcum);
        this.setHorhombresTrabMes(horhombresTrabMes);
        this.setHorhombresTrabAcum(horhombresTrabAcum);
        this.setIndiceFrecuenciaMes(indiceFrecuenciaMes);
        this.setIndiceFrecuenciaAcum(indiceFrecuenciaAcum);
        this.setIndiceSeveridadMes(indiceSeveridadMes);
        this.setIndiceSeveridadAcum(indiceSeveridadAcum);
        this.setIndiceAccidentesMes(indiceAccidentesMes);
        this.setIndiceAccidentesAcum(indiceAccidentesAcum);
        this.setUsuIngreso(usuIngreso);
        this.setFecIngreso(fecIngreso);
        this.setIpIngreso(ipIngreso);
        this.setUsuModifica(usuModifica);
        this.setFecModifica(fecModifica);
        this.setIpModifica(ipModifica);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.EstadisticoIncidenteAuxRepository#listaEstadisticosIncidenteUM()
     * @param anioPro
     * @param mes
     * @param indiceFrecuenciaAcum
     * @param indiceSeveridadAcum
     * @param indiceAccidentesAcum
     */
    public PgimEstadisticoIncidenteAuxDTOResultado(String anioPro, String mes, BigDecimal indiceFrecuenciaAcum,
            BigDecimal indiceSeveridadAcum, BigDecimal indiceAccidentesAcum ) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIndiceFrecuenciaAcum(indiceFrecuenciaAcum);
        this.setIndiceSeveridadAcum(indiceSeveridadAcum);
        this.setIndiceAccidentesAcum(indiceAccidentesAcum);
    }
}
