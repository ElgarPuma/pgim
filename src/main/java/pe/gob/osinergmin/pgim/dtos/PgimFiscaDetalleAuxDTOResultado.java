package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimFiscaDetalleAuxDTOResultado extends PgimFiscaDetalleAuxDTO {

    /**
	 * @see pe.gob.osinergmin.pgim.models.repository.FiscaDetalleAuxRepository#listarDetalleFiscalizaciones()
     * 
     * @param nuAnioPlan
     * @param noEspecialidad
     * @param noDivisionSupervisora
     * @param coFiscalizacion
     * @param nuExpedienteFisca
     * @param feInicioPrevistaFisca
     * @param feFinPrevistaFisca
     * @param feInicioRealFisca
     * @param feFinRealFisca
     * @param esPropia
     * @param noTipoFiscaliza
     * @param noSubtipoFiscaliza
     * @param noMotivoFiscaliza
     * @param coUnidadFiscalizable
     * @param noUnidadFiscalizable
     * @param rucAgenteFiscalizado
     * @param noAgenteFiscalizado
     * @param noFaseOrigen
     * @param noTareaOrigen
     * @param noFaseDestino
     * @param noTareaDestino
     * @param feAsignaTarea
     * @param deMensajeTarea
     * @param noTipoTransicion
     * @param noTipoPersonaOrigen
     * @param noPersonaOrigen
     * @param noTipoPersonaDestino
     * @param noPersonaDestino
     * @param nuContrato
     * @param rucEmpresaSupervisora
     * @param noEmpresaSupervisora
     */
    public PgimFiscaDetalleAuxDTOResultado(Long nuAnioPlan, String noEspecialidad, String noDivisionSupervisora,
            String coFiscalizacion, String nuExpedienteFisca, Date feInicioPrevistaFisca, Date feFinPrevistaFisca,
            Date feInicioRealFisca, Date feFinRealFisca, String esPropia, String noTipoFiscaliza,
            String noSubtipoFiscaliza, String noMotivoFiscaliza, String coUnidadFiscalizable,
            String noUnidadFiscalizable, String rucAgenteFiscalizado, String noAgenteFiscalizado, String noFaseOrigen,
            String noTareaOrigen, String noFaseDestino, String noTareaDestino, Date feAsignaTarea,
            String deMensajeTarea, String noTipoTransicion, String noTipoPersonaOrigen, String noPersonaOrigen,
            String noTipoPersonaDestino, String noPersonaDestino, String nuContrato, String rucEmpresaSupervisora,
            String noEmpresaSupervisora) {
        super();

        this.setNuAnioPlan(nuAnioPlan);
        this.setNoEspecialidad(noEspecialidad);
        this.setNoDivisionSupervisora(noDivisionSupervisora);
        this.setCoFiscalizacion(coFiscalizacion);
        this.setNuExpedienteFisca(nuExpedienteFisca);
        this.setFeInicioPrevistaFisca(feInicioPrevistaFisca);
        this.setFeFinPrevistaFisca(feFinPrevistaFisca);
        this.setFeInicioRealFisca(feInicioRealFisca);
        this.setFeFinRealFisca(feFinRealFisca);
        this.setEsPropia(esPropia);
        this.setNoTipoFiscaliza(noTipoFiscaliza);
        this.setNoSubtipoFiscaliza(noSubtipoFiscaliza);
        this.setNoMotivoFiscaliza(noMotivoFiscaliza);
        this.setCoUnidadFiscalizable(coUnidadFiscalizable);
        this.setNoUnidadFiscalizable(noUnidadFiscalizable);
        this.setRucAgenteFiscalizado(rucAgenteFiscalizado);
        this.setNoAgenteFiscalizado(noAgenteFiscalizado);
        this.setNoFaseOrigen(noFaseOrigen);
        this.setNoTareaOrigen(noTareaOrigen);
        this.setNoFaseDestino(noFaseDestino);
        this.setNoTareaDestino(noTareaDestino);
        this.setFeAsignaTarea(feAsignaTarea);
        this.setDeMensajeTarea(deMensajeTarea);
        this.setNoTipoTransicion(noTipoTransicion);
        this.setNoTipoPersonaOrigen(noTipoPersonaOrigen);
        this.setNoPersonaOrigen(noPersonaOrigen);
        this.setNoTipoPersonaDestino(noTipoPersonaDestino);
        this.setNoPersonaDestino(noPersonaDestino);
        this.setNuContrato(nuContrato);
        this.setRucEmpresaSupervisora(rucEmpresaSupervisora);
        this.setNoEmpresaSupervisora(noEmpresaSupervisora);
    }
}