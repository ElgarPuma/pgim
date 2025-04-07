package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PgimSupervisionAuxDTOResultado extends PgimSupervisionAuxDTO {
	
    /**
     * Este constructor sirve para la lista principal de supervisiones. En el
     * repositorio usa el método(s):
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisiones()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesReporte()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionxAnioEspecReporte()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesList()
     * 
     * @param idSupervision
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param asNoRazonSocial
     * @param asCoDocumentoIdentidad
     * @param noEspecialidad
     * @param personaAsignada
     * @param usuarioAsignado
     * @param noPasoActual
     * @param noTipoSupervision
     * @param deSubTipoSupervision
     * @param nuExpedienteSiged
     * @param idAgenteSupervisado
     * @param descContrato
     * @param noDivisionSupervisora
     * @param noFlPropia
     * @param flFeEnvio
     * @param flLeido
     * @param feLectura
     * @param feInstanciaPaso
     * @param noPersonaOrigen
     * @param flPasoActivo
     * @param deMensaje
     * @param nuAlertas
     * @param nuIntervaloAlertas
     * @param idInstanciaPaso
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, 
            String asNoRazonSocial, String asCoDocumentoIdentidad, String noEspecialidad, 
            String personaAsignada, String usuarioAsignado, String noPasoActual, 
            String noTipoSupervision, String deSubTipoSupervision, String nuExpedienteSiged, 
            Long idAgenteSupervisado, String descContrato, String noDivisionSupervisora, 
            String noFlPropia, String flFeEnvio, String flLeido, 
            Date feLectura, Date feInstanciaPaso, String noPersonaOrigen, 
            String flPasoActivo, String deMensaje, Long nuAlertas, 
            Long nuIntervaloAlertas, Long idInstanciaPaso, String flListoContinuar) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setDeSubtipoSupervision(deSubTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setNoFlPropia(noFlPropia);
        this.setFlFeEnvio(flFeEnvio);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setFlPasoActivo(flPasoActivo);
        this.setDeMensaje(deMensaje);
        this.setNuAlertas(nuAlertas);
        this.setNuIntervaloAlertas(nuIntervaloAlertas);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setFlListoContinuar(flListoContinuar);
        
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarFiscalizacionesParaCalendario()
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, 
            String asNoRazonSocial, String asCoDocumentoIdentidad, String noEspecialidad, 
            String personaAsignada, String usuarioAsignado, String noPasoActual, 
            String noTipoSupervision, String deSubTipoSupervision, String nuExpedienteSiged, 
            Long idAgenteSupervisado, String descContrato, String noDivisionSupervisora, 
            String noFlPropia, String flFeEnvio, String flLeido, 
            Date feLectura, Date feInstanciaPaso, String noPersonaOrigen, 
            String flPasoActivo, String deMensaje, Long nuAlertas, 
            Long nuIntervaloAlertas, Long idInstanciaPaso, Long idEspecialidad, Date feInicioSupervisionReal, Date feFinSupervisionReal
            , Long idUnidadMinera) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setDeSubtipoSupervision(deSubTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setNoFlPropia(noFlPropia);
        this.setFlFeEnvio(flFeEnvio);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setFlPasoActivo(flPasoActivo);
        this.setDeMensaje(deMensaje);
        this.setNuAlertas(nuAlertas);
        this.setNuIntervaloAlertas(nuIntervaloAlertas);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdEspecialidad(idEspecialidad);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setIdUnidadMinera(idUnidadMinera);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerlistaFiscalizacionesTraslapadas()
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, 
            String asNoRazonSocial, String asCoDocumentoIdentidad, String noEspecialidad, 
            String personaAsignada, String usuarioAsignado, String noPasoActual, 
            String noTipoSupervision, String deSubTipoSupervision, String nuExpedienteSiged, 
            Long idAgenteSupervisado, String descContrato, String noDivisionSupervisora, 
            String noFlPropia, String flFeEnvio, String flLeido, 
            Date feLectura, Date feInstanciaPaso, String noPersonaOrigen, 
            String flPasoActivo, String deMensaje, Long nuAlertas, 
            Long nuIntervaloAlertas, Long idInstanciaPaso, Long idEspecialidad, Long idUnidadMinera) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setUsuarioAsignado(usuarioAsignado);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setDeSubtipoSupervision(deSubTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setNoFlPropia(noFlPropia);
        this.setFlFeEnvio(flFeEnvio);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setFlPasoActivo(flPasoActivo);
        this.setDeMensaje(deMensaje);
        this.setNuAlertas(nuAlertas);
        this.setNuIntervaloAlertas(nuIntervaloAlertas);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdEspecialidad(idEspecialidad);
        this.setIdUnidadMinera(idUnidadMinera);
    }

	/**
     * Este constructor sirve para la lista de supervisiones del contrato. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSeguimientosSupervisiones()
     * 
     * @param idSupervision
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param asNoRazonSocial
     * @param asCoDocumentoIdentidad
     * @param noEspecialidad
     * @param personaAsignada
     * @param noPasoActual
     * @param noTipoSupervision
     * @param nuExpedienteSiged
     * @param idAgenteSupervisado
     * @param descContrato
     * @param idContrato
     * @param idInstanciaPaso
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, String asNoRazonSocial,
            String asCoDocumentoIdentidad, String noEspecialidad, String personaAsignada,
            String noPasoActual, String noTipoSupervision,
            String nuExpedienteSiged, Long idAgenteSupervisado, String descContrato, Long idContrato, 
            Long idInstanciaPaso) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setIdContrato(idContrato);        
        this.setIdInstanciaPaso(idInstanciaPaso);  
    }
    
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesPrecomprometidasXcontrato()
     * 
     * @param idSupervision
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param asNoRazonSocial
     * @param asCoDocumentoIdentidad
     * @param noEspecialidad
     * @param personaAsignada
     * @param noPasoActual
     * @param noTipoSupervision
     * @param nuExpedienteSiged
     * @param idAgenteSupervisado
     * @param descContrato
     * @param descPrograma
     * @param deSubtipoSupervision
     * @param moConsumoContrato
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, String asNoRazonSocial,
            String asCoDocumentoIdentidad, String noEspecialidad, String personaAsignada,
            String noPasoActual, String noTipoSupervision,
            String nuExpedienteSiged, Long idAgenteSupervisado, String descContrato,
            String descPrograma,String deSubtipoSupervision,BigDecimal moConsumoContrato,
            Long idInstanciaProceso) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setDescPrograma(descPrograma);
        this.setDeSubtipoSupervision(deSubtipoSupervision);
        this.setMoConsumoContrato(moConsumoContrato);
        this.setIdInstanciaProceso(idInstanciaProceso);
        
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        this.setFeInicioSupervisionDesc(sdfr.format(feInicioSupervision));
        this.setFeFinSupervisionDesc(sdfr.format(feFinSupervision));
        
        //Calcular cantidad de días entre feInicioSupervision y feFinSupervision 
        TimeZone timeZona = TimeZone.getTimeZone("America/Lima");
        Calendar calendarioIni = Calendar.getInstance(timeZona);
        calendarioIni.setTime(feInicioSupervision);
        Calendar calendarioFin = Calendar.getInstance(timeZona);        
        calendarioFin.setTime(feFinSupervision);
        LocalDate fechaLocalInicial = LocalDate.of(calendarioIni.get(Calendar.YEAR), calendarioIni.get(Calendar.MONTH) + 1,
                calendarioIni.get(Calendar.DAY_OF_MONTH));
        LocalDate fechaLocalFinal = LocalDate.of(calendarioFin.get(Calendar.YEAR), calendarioFin.get(Calendar.MONTH) + 1,
                calendarioFin.get(Calendar.DAY_OF_MONTH));        
        Long cantidadDiasEntre = ChronoUnit.DAYS.between(fechaLocalInicial, fechaLocalFinal);
        
        this.setCantidadDias(cantidadDiasEntre);
        
        
    }
    
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerCabecSupervisionPorExpediente
     * @param idSupervision
     * @param nuExpedienteSiged
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param asNoRazonSocial
     * @param asCoDocumentoIdentidad
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String nuExpedienteSiged, Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            String asNoRazonSocial, String asCoDocumentoIdentidad) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);  
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);           
    }
    
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, String asNoRazonSocial,
            String asCoDocumentoIdentidad, String noEspecialidad, String personaAsignada,
            String noPasoActual, String noTipoSupervision, Long idTipoSupervision,
            String nuExpedienteSiged, Long idAgenteSupervisado, String descContrato) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setIdTipoSupervision(idTipoSupervision);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesReporteExportar()
     */
    public PgimSupervisionAuxDTOResultado(Long idSupervision, String coUnidadMinera, String noUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, 
            String asNoRazonSocial, String asCoDocumentoIdentidad, String noEspecialidad, 
            String personaAsignada, String noPasoActual, String noTipoSupervision,
            String nuExpedienteSiged, Long idAgenteSupervisado, String descContrato,
            String noDivisionSupervisora, String noFlPropia, Date descFeInicioReal, Date descFeFinReal ) {
        super();
        this.setIdSupervisionAux(idSupervision);
        this.setIdSupervision(idSupervision);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);        
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);                
        this.setNoEspecialidad(noEspecialidad);
        this.setPersonaAsignada(personaAsignada);
        this.setNoPasoActual(noPasoActual);
        this.setNoTipoSupervision(noTipoSupervision);
        this.setNuExpedienteSiged(nuExpedienteSiged);
        this.setIdAgenteSupervisado(idAgenteSupervisado);        
        this.setDescContrato(descContrato);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setNoFlPropia(noFlPropia);
        this.setDescFeInicioReal(descFeInicioReal);
        this.setDescFeFinReal(descFeFinReal);
    }

}
