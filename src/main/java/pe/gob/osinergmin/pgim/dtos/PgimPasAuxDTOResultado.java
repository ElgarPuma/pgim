package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimPasAuxDTOResultado extends PgimPasAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#obtenerPasPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#obtenerPasPorIdInstanciaPaso()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#filtrar()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPasPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPas()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#filtrarExpedientePorPersonaAsignada()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#obtenerPasAuxEvaluador()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#filtrarReporte()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarTareasPasActivasPorId()
     * 
     * @param idPasAux
     * @param idPas
     * @param feCreacionPas
     * @param feCreacionPasAnio
     * @param idSupervision
     * @param coSupervision
     * @param idEspecialidad
     * @param noEspecialidad
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param idAgenteSupervisado
     * @param asCoDocumentoIdentidad
     * @param asNoRazonSocial
     * @param nuExpedienteSigedPas
     * @param nuExpedienteSigedSup
     * @param flPropia
     * @param noPersonaAsignada
     * @param noUsuarioAsignado
     * @param idFaseActual
     * @param noFaseActual
     * @param idPasoActual
     * @param noPasoActual
     * @param idRelacionPaso
     * @param idTipoRelacion
     * @param noDivisionSupervisora
     * @param flLeido
     * @param feLectura
     * @param feInstanciaPaso
     * @param noPersonaOrigen
     * @param flPasoActivo
     * @param deMensaje
     * @param nuAlertas
     * @param nuIntervaloAlertas
     * @param idInstanciaProcesoPas
     * @param idInstanciaPaso
     */
    public PgimPasAuxDTOResultado(Long idPasAux, Long idPas, String coPas, Date feCreacionPas,
            String feCreacionPasAnio, Long idSupervision, String coSupervision,
            Long idEspecialidad, String noEspecialidad, Long idUnidadMinera,
            String coUnidadMinera, String noUnidadMinera, Long idAgenteSupervisado,
            String asCoDocumentoIdentidad, String asNoRazonSocial, String nuExpedienteSigedPas,
            String nuExpedienteSigedSup, String flPropia, String noPersonaAsignada,
            String noUsuarioAsignado, Long idFaseActual, String noFaseActual,
            Long idPasoActual, String noPasoActual, Long idRelacionPaso,
            Long idTipoRelacion, String noDivisionSupervisora, String flLeido,
            Date feLectura, Date feInstanciaPaso, String noPersonaOrigen,
            String flPasoActivo, String deMensaje, Long nuAlertas,
            Long nuIntervaloAlertas, Long idInstanciaProcesoPas, Long idInstanciaPaso) {
        super();

        this.setIdPasAux(idPasAux);
        this.setIdPas(idPas);
        this.setDescCoPas(coPas);
        this.setFeCreacionPas(feCreacionPas);
        this.setFeCreacionPasAnio(feCreacionPasAnio);
        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setNuExpedienteSigedPas(nuExpedienteSigedPas);
        this.setNuExpedienteSigedSup(nuExpedienteSigedSup);
        this.setFlPropia(flPropia);
        this.setNoPersonaAsignada(noPersonaAsignada);
        this.setNoUsuarioAsignado(noUsuarioAsignado);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdTipoRelacion(idTipoRelacion);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setFlLeido(flLeido);
        this.setFeLectura(feLectura);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setFlPasoActivo(deMensaje);
        this.setDeMensaje(deMensaje);
        this.setNuAlertas(nuAlertas);
        this.setNuIntervaloAlertas(nuIntervaloAlertas);

        this.setIdInstanciaProcesoPas(idInstanciaProcesoPas);
        this.setIdInstanciaPaso(idInstanciaPaso);
    }

    public PgimPasAuxDTOResultado(String feCreacionPasAnio) {
        super();
        this.setFeCreacionPasAnio(feCreacionPasAnio);

    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPasProcesoPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarReporteExpPasProceso()
     * @param idPasAux
     * @param idPas
     * @param feCreacionPas
     * @param feCreacionPasAnio
     * @param idSupervision
     * @param coSupervision
     * @param idEspecialidad
     * @param noEspecialidad
     * @param idUnidadMinera
     * @param noUnidadMinera
     * @param idAgenteSupervisado
     * @param asCoDocumentoIdentidad
     * @param asNoRazonSocial
     * @param nuExpedienteSigedPas
     * @param nuExpedienteSigedSup
     * @param noPersonaAsignada
     * @param noUsuarioAsignado
     * @param idFaseActual
     * @param noFaseActual
     * @param idPasoActual
     * @param noPasoActual
     * @param idRelacionPaso
     * @param idTipoRelacion
     * @param feInstanciaPaso
     * @param diasTranscurridos
     */
    public PgimPasAuxDTOResultado(Long idPasAux, Long idPas, Date feCreacionPas, String feCreacionPasAnio,
            Long idSupervision,
            String coSupervision, Long idEspecialidad, String noEspecialidad, Long idUnidadMinera,
            String coUnidadMinera,
            String noUnidadMinera, Long idAgenteSupervisado, String asCoDocumentoIdentidad, String asNoRazonSocial,
            String nuExpedienteSigedPas, String nuExpedienteSigedSup, String noPersonaAsignada,
            String noUsuarioAsignado, Long idFaseActual, String noFaseActual, Long idPasoActual, String noPasoActual,
            Long idRelacionPaso, Long idTipoRelacion, Date feInstanciaPaso, Long diasTranscurridos,
            String noDivisionSupervisora) {
        super();

        this.setIdPasAux(idPasAux);
        this.setIdPas(idPas);
        this.setFeCreacionPas(feCreacionPas);
        this.setFeCreacionPasAnio(feCreacionPasAnio);
        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setNuExpedienteSigedPas(nuExpedienteSigedPas);
        this.setNuExpedienteSigedSup(nuExpedienteSigedSup);
        this.setNoPersonaAsignada(noPersonaAsignada);
        this.setNoUsuarioAsignado(noUsuarioAsignado);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setIdRelacionPaso(idRelacionPaso);
        this.setIdTipoRelacion(idTipoRelacion);
        this.setFeInstanciaPaso(feInstanciaPaso);
        this.setDiasTranscurridos(diasTranscurridos);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#obtenerCabecPasPorExpediente
     * @param idPas
     * @param nuExpedienteSiged
     * @param idUnidadMinera
     * @param noUnidadMinera
     * @param asNoRazonSocial
     * @param asCoDocumentoIdentidad
     */
    public PgimPasAuxDTOResultado(Long idPas, String nuExpedienteSiged, Long idUnidadMinera, String noUnidadMinera,
            String asNoRazonSocial, String asCoDocumentoIdentidad) {
        super();
        this.setIdPasAux(idPas);
        this.setIdPas(idPas);
        this.setNuExpedienteSigedPas(nuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarPasEnGeneralPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.PasAuxRepository#listarPasEnGeneral()
     * 
     */
    public PgimPasAuxDTOResultado(Long idPasAux, Long idPas, Long idInstanciaPaso, Long idSupervision,
            String coSupervision, String descCoPas, Date feOrigenDocumento, 
            Long incumplimientos, Long infracciones, Long idProgramaSupervision, String descPrograma, 
            Long idEspecialidad, String noEspecialidad, Long idUnidadMinera,
            String coUnidadMinera, String noUnidadMinera, Long idAgenteSupervisado,
            String asCoDocumentoIdentidad, String asNoRazonSocial, Long idDivisionSupervisora, 
            String noDivisionSupervisora, String nuExpedienteSigedPas, String noPersonaAsignada,
            String noUsuarioAsignado, Long idFaseActual, String noFaseActual,
            Long idPasoActual, String noPasoActual, Long idInstanciaProcesoPas,
            String noPersonaOrigen, String noUsuarioOrigen, String flPasoActivo, Long idMotivoSupervision, 
            String deMotivoSupervision
    ) {
        super();
        this.setIdPasAux(idPasAux);
        this.setIdPas(idPas);
        this.setIdInstanciaPaso(idInstanciaPaso);
        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setDescCoPas(descCoPas);
        this.setFeOrigenDocumento(feOrigenDocumento);
        this.setIncumplimientos(incumplimientos);
        this.setInfracciones(infracciones);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setDescPrograma(descPrograma);
        this.setIdEspecialidad(idEspecialidad);
        this.setNoEspecialidad(noEspecialidad);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setAsCoDocumentoIdentidad(asCoDocumentoIdentidad);
        this.setAsNoRazonSocial(asNoRazonSocial);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setNuExpedienteSigedPas(nuExpedienteSigedPas);
        this.setNoPersonaAsignada(noPersonaAsignada);
        this.setNoUsuarioAsignado(noUsuarioAsignado);
        this.setIdFaseActual(idFaseActual);
        this.setNoFaseActual(noFaseActual);
        this.setIdPasoActual(idPasoActual);
        this.setNoPasoActual(noPasoActual);
        this.setIdInstanciaProcesoPas(idInstanciaProcesoPas);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setNoUsuarioOrigen(noUsuarioOrigen);
        this.setFlPasoActivo(flPasoActivo);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDeMotivoSupervision(deMotivoSupervision);
    }
}
