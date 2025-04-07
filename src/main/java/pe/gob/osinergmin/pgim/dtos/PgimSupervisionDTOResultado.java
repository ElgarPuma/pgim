package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimSupervisionDTOResultado extends PgimSupervisionDTO {

    /**
     * Este constructor sirve para la lista principal de supervisiones. En el
     * repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listar()
     * 
     * @param idSupervision
     * @param descCoUnidadMinera
     * @param descNoUnidadMinera
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param descNoRazonSocial
     * @param descCoDocumentoIdentidad
     * @param descNoEspecialidad
     * @param descSubtipoSupervision
     * @param descNuExpedienteSiged
     * @param descIdAgenteSupervisado
     * @param descContrato
     */
    public PgimSupervisionDTOResultado(Long idSupervision, String descCoUnidadMinera, String descNoUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, String descNoRazonSocial,
            String descCoDocumentoIdentidad, String descNoEspecialidad, String descPersonaDestino,
            String descNoPasoProcesoDestino, String descSubtipoSupervision,
            String descNuExpedienteSiged, Long descIdAgenteSupervisado, String descContrato) {
        super();
        this.setIdSupervision(idSupervision);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescPersonaDestino(descPersonaDestino);
        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
        this.setDescSubtipoSupervision(descSubtipoSupervision);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescContrato(descContrato);
    }

    /***
     * Este constructor sirve para filtrar por palabra clave de la supervisión. En
     * el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarPorPalabraClave()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesFechaInicioReal()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesTraslapadas()
     * 
     * @param idSupervision
     * @param coSupervision
     * @param idInstanciaProceso
     */
    public PgimSupervisionDTOResultado(Long idSupervision, String coSupervision, Long idInstanciaProceso) {
        super();

        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
    }
    
    /***
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesTraslapadas()
     * 
     * @param idSupervision
     * @param coSupervision
     * @param idInstanciaProceso
     * @param idInstanciaPaso
     */
    public PgimSupervisionDTOResultado(Long idSupervision, String coSupervision, Long idInstanciaProceso,
    		Long idInstanciaPaso) {
        super();

        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescIdInstanciaPaso(idInstanciaPaso);
    }

    public PgimSupervisionDTOResultado(Long idSupervision, String coSupervision, String descNoUnidadMinera) {
        super();
        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
    }

    /**
     * Este constructor sirve para filtrar por palabra clave de la supervisión. En
     * el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarPorPalabraClaveNuExpedienteSiged()
     * 
     * @param idSupervision
     * @param coSupervision
     */
    public PgimSupervisionDTOResultado(Long idSupervision, Long idInstanciaProceso, String descNuExpedienteSiged) {
        super();

        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }

    /**
     * Sirve para obtener el detalle de la supervision. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionByIdSupervision()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionByIdRankingUmGrupo()
     * 
     * @param idSupervision
     * @param idInstanciaProceso
     * @param idUnidadMinera
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param idEspecialidad
     * @param flPropia
     * @param idSubtipoSupervision
     * @param idProgramaSupervision
     * @param idInstanciaPaso
     */
    public PgimSupervisionDTOResultado(Long idSupervision, Long idInstanciaProceso, Long idUnidadMinera,
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, Long idEspecialidad, String flPropia,
            Long idSubtipoSupervision, Long idProgramaSupervision, Long idInstanciaPaso) {
        super();
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setDescIdEspecialidad(idEspecialidad);
        this.setFlPropia(flPropia);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setIdProgramaSupervision(idProgramaSupervision);   
        this.setDescIdInstanciaPaso(idInstanciaPaso);
    }

    /**
     * Usado para obtener el último coSupervision. En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerUltimoCoSupervision()
     * 
     * @param coSupervision
     */
    public PgimSupervisionDTOResultado(String coSupervision) {
        super();
        this.setCoSupervision(coSupervision);
    }

    /**
     * Sirve para obtener el detalle de la supervision. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSeguimientoPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionPorUnidadMinera()
     * 
     * @param idSupervision
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     * @param idUnidadMinera
     * @param descNoUnidadMinera
     * @param descNoRazonSocial
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param feInicioSupervisionReal
     * @param feFinSupervisionReal
     * @param descPrograma
     * @param descIdContrato
     * @param descDeContrato
     * @param flPropia
     * @param idSubtipoSupervision
     * @param descSubTipoSupervision
     * @param idProgramaSupervision
     * @param descIdAgenteSupervisado
     * @param descIdEspecialidad
     * @param deTdrObjetivoTexto
     * @param deTdrAlcanceTexto
     * @param deTdrMetodologiaTexto
     * @param deTdrInformeSupervTexto
     * @param deTdrHonorariosProf
     * @param deObservacionInicioSuper
     * @param deObservacionFinSuperT
     * @param idMotivoSupervision
     * @param descDeMotivoSupervision
     * @param descNoEspecialidad
     * @param descTipoSupervision
     * @param descIdTipoSupervision
     * @param flRegistraRiesgos
     * @param flFeEnvio
     * @param nuAlertas
     * @param nuIntervaloAlertas
     * @param idInstanciaPaso
     * @param coDocumentoIdentidad
     */
    public PgimSupervisionDTOResultado(Long idSupervision, Long idInstanciaProceso, String descNuExpedienteSiged,
            Long idUnidadMinera, String descNoUnidadMinera, String descNoRazonSocial, String coSupervision,
            Date feInicioSupervision, Date feFinSupervision, Date feInicioSupervisionReal, Date feFinSupervisionReal,
            String descPrograma, Long descIdContrato, String descDeContrato,String flPropia,
            Long idSubtipoSupervision, String descSubTipoSupervision, Long idProgramaSupervision, Long descIdAgenteSupervisado,
            Long descIdEspecialidad, String deTdrObjetivoTexto, String deTdrAlcanceTexto, String deTdrMetodologiaTexto, String deTdrInformeSupervTexto,
            String deTdrHonorariosProf, String deObservacionInicioSuper, String deObservacionFinSuperT,
            Long idMotivoSupervision,String descDeMotivoSupervision, String descNoEspecialidad, 
            String descTipoSupervision, Long descIdTipoSupervision, String flRegistraRiesgos,
            String flFeEnvio, Long nuAlertas, Long nuIntervaloAlertas, 
            Long idInstanciaPaso, String coDocumentoIdentidad, String flNoIniciadaAfiscalizado
            ) {
        super();
        
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setDescPrograma(descPrograma);
        this.setDescIdContrato(descIdContrato);
        this.setDescDeContrato(descDeContrato);
        this.setFlPropia(flPropia);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubTipoSupervision);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setDeTdrObjetivoTexto(deTdrObjetivoTexto);
        this.setDeTdrAlcanceTexto(deTdrAlcanceTexto); 
        this.setDeTdrMetodologiaTexto(deTdrMetodologiaTexto);
        this.setDeTdrInformeSupervTexto(deTdrInformeSupervTexto);
        this.setDeTdrHonorariosProf(deTdrHonorariosProf);
        this.setDeObservacionInicioSuperT(deObservacionInicioSuper);
        this.setDeObservacionFinSuperT(deObservacionFinSuperT);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDescDeMotivoSupervision(descDeMotivoSupervision);
        this.setDescNoEspecialidad(descNoEspecialidad); 
        this.setDescTipoSupervision(descTipoSupervision);
        this.setDescIdTipoSupervision(descIdTipoSupervision);
        this.setFlRegistraRiesgos(flRegistraRiesgos);
        this.setFlFeEnvio(flFeEnvio);
        this.setNuAlertas(nuAlertas);
        this.setNuIntervaloAlertas(nuIntervaloAlertas);
        this.setDescIdInstanciaPaso(idInstanciaPaso);
        this.setDescCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setFlNoIniciadaAfiscalizado(flNoIniciadaAfiscalizado);
    }

    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerFiscalizacionPorUnidadMinera()
     * 
     * @param idSupervision
     * @param idUnidadMinera
     * @param descNoUnidadMinera
     * @param coSupervision
     * @param feInicioSupervisionReal
     * @param feFinSupervisionReal
     * @param descPrograma
     * @param descIdContrato
     * @param idSubtipoSupervision
     * @param descSubTipoSupervision
     * @param idMotivoSupervision
     * @param descDeMotivoSupervision
     * @param descNoEspecialidad
     * @param descTipoSupervision
     * @param descDeNuContrato
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param descPersonaDestino
     * @param descNoFaseProceso
     * @param descNoPasoProcesoDestino
     */
    public PgimSupervisionDTOResultado(Long idSupervision, 
            Long idUnidadMinera, String descNoUnidadMinera, String coSupervision,
            Date feInicioSupervisionReal, Date feFinSupervisionReal,
            String descPrograma, Long descIdContrato, 
            Long idSubtipoSupervision, String descSubTipoSupervision,
            Long idMotivoSupervision,String descDeMotivoSupervision, String descNoEspecialidad, 
            String descTipoSupervision, String descDeNuContrato, String descEmpresaSupervisoraNoRazonSocial, 
            String descPersonaDestino, String descNoFaseProceso, String descNoPasoProcesoDestino
            ) {
        super();
        
        this.setIdSupervision(idSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setDescPrograma(descPrograma);
        this.setDescIdContrato(descIdContrato);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubTipoSupervision);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDescDeMotivoSupervision(descDeMotivoSupervision);
        this.setDescNoEspecialidad(descNoEspecialidad); 
        this.setDescTipoSupervision(descTipoSupervision);
        this.setDescDeNuContrato(descDeNuContrato);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setDescPersonaDestino(descPersonaDestino);
        this.setDescNoFaseProceso(descNoFaseProceso);
        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerFiscalizacionPorUnidadMineraPaginado()
     * 
     * @param idSupervision
     * @param idUnidadMinera
     * @param descNoUnidadMinera
     * @param coSupervision
     * @param feInicioSupervisionReal
     * @param feFinSupervisionReal
     * @param descPrograma
     * @param descIdContrato
     * @param idSubtipoSupervision
     * @param descSubTipoSupervision
     * @param idMotivoSupervision
     * @param descDeMotivoSupervision
     * @param descNoEspecialidad
     * @param descTipoSupervision
     * @param descDeNuContrato
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param descPersonaDestino
     * @param descNoFaseProceso
     * @param descNoPasoProcesoDestino
     * @param descIdInstanciaPaso
     */
    public PgimSupervisionDTOResultado(Long idSupervision, 
            Long idUnidadMinera, String descNoUnidadMinera, String coSupervision,
            Date feInicioSupervisionReal, Date feFinSupervisionReal,
            String descPrograma, Long descIdContrato, 
            Long idSubtipoSupervision, String descSubTipoSupervision,
            Long idMotivoSupervision,String descDeMotivoSupervision, String descNoEspecialidad, 
            String descTipoSupervision, String descDeNuContrato, String descEmpresaSupervisoraNoRazonSocial, 
            String descPersonaDestino, String descNoFaseProceso, String descNoPasoProcesoDestino,
            Long descIdInstanciaPaso
            ) {
        super();
        
        this.setIdSupervision(idSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setDescPrograma(descPrograma);
        this.setDescIdContrato(descIdContrato);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubTipoSupervision);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDescDeMotivoSupervision(descDeMotivoSupervision);
        this.setDescNoEspecialidad(descNoEspecialidad); 
        this.setDescTipoSupervision(descTipoSupervision);
        this.setDescDeNuContrato(descDeNuContrato);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setDescPersonaDestino(descPersonaDestino);
        this.setDescNoFaseProceso(descNoFaseProceso);
        this.setDescNoPasoProcesoDestino(descNoPasoProcesoDestino);
        this.setDescIdInstanciaPaso(descIdInstanciaPaso);
    }

    /**
     * Sirve para obtener el detalle de la supervision. En el repositorio usa el
     * método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionByidInstanciaProceso()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionByIdSupervisionCompleta()
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionesPorInstanciaProceso()
     * 
     * @param idSupervision
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     * @param idUnidadMinera
     * @param descNoUnidadMinera
     * @param descCoUnidadMinera
     * @param descNoTipoUnidadMinera
     * @param coDocumentoIdentidad
     * @param descNoRazonSocial
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param feInicioSupervisionReal
     * @param feFinSupervisionReal
     * @param descPrograma
     * @param descIdContrato
     * @param descDeContrato
     * @param flPropia
     * @param idSubtipoSupervision
     * @param descSubTipoSupervision
     * @param idProgramaSupervision
     * @param descIdAgenteSupervisado
     * @param descNoEspecialidad
     * @param descIdEspecialidad
     * @param idMotivoSupervision
     * @param flNoIniciadaAfiscalizado
     * @param flPreexistenteF2F1
     */
    public PgimSupervisionDTOResultado(Long idSupervision, Long idInstanciaProceso, String descNuExpedienteSiged,
            Long idUnidadMinera, String descNoUnidadMinera, String descCoUnidadMinera, 
            String descNoTipoUnidadMinera, String coDocumentoIdentidad, String descNoRazonSocial, 
            String coSupervision, Date feInicioSupervision, Date feFinSupervision, 
            Date feInicioSupervisionReal, Date feFinSupervisionReal, String descPrograma, 
            Long descIdContrato, String descDeContrato,String flPropia,
            Long idSubtipoSupervision, String descSubTipoSupervision, Long idProgramaSupervision, 
            Long descIdAgenteSupervisado, String descNoEspecialidad, Long descIdEspecialidad, 
            Long idMotivoSupervision, String flNoIniciadaAfiscalizado, String flPreexistenteF2F1) {
        super();
        
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescNoTipoUnidadMinera(descNoTipoUnidadMinera);
        this.setDescCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setDescPrograma(descPrograma);
        this.setDescIdContrato(descIdContrato);
        this.setDescDeContrato(descDeContrato);
        this.setFlPropia(flPropia);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubTipoSupervision);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setFlNoIniciadaAfiscalizado(flNoIniciadaAfiscalizado);
        this.setFlPreexistenteF2F1(flPreexistenteF2F1);
    }

    /**
     * Sirve para obtener el detalle de la supervision para la tarjeta informativa.
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervision()
     * 
     * @param idSupervision
     * @param descNuExpedienteSiged
     * @param idUnidadMinera
     * @param descNoUnidadMinera
     * @param descNoRazonSocial
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param descSubTipoSupervision
     * @param descDeContrato
     * @param descPrograma
     * @param deObservacionInicioSuper
     * @param deObservacionFinSuperT
     * @param descIdTipoSupervision
     * @param flFeEnvio
     * @param flPropia
     * @param descIdContrato
     * @param descIdAgenteSupervisado
     * @param idMotivoSupervision
     * @param flNoIniciadaAfiscalizado
     */
    public PgimSupervisionDTOResultado(Long idSupervision, String descNuExpedienteSiged, Long idUnidadMinera, 
            String descNoUnidadMinera, String descNoRazonSocial, String coSupervision, 
            Date feInicioSupervision, Date feFinSupervision, String descSubTipoSupervision, 
            String descDeContrato, String descPrograma, String deObservacionInicioSuper, 
            String deObservacionFinSuperT, Long descIdTipoSupervision, String flFeEnvio, 
            String flPropia, Long descIdContrato, Long descIdAgenteSupervisado, 
            Long idMotivoSupervision, String flNoIniciadaAfiscalizado
            ) {
        super();
        this.setIdSupervision(idSupervision);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setDescSubtipoSupervision(descSubTipoSupervision);
        this.setDescDeContrato(descDeContrato);
        this.setDescPrograma(descPrograma);
        this.setDeObservacionInicioSuperT(deObservacionInicioSuper);
        this.setDeObservacionFinSuperT(deObservacionFinSuperT);
        this.setDescIdTipoSupervision(descIdTipoSupervision);
        this.setFlFeEnvio(flFeEnvio);
        this.setFlPropia(flPropia);
        this.setDescIdContrato(descIdContrato);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setFlNoIniciadaAfiscalizado(flNoIniciadaAfiscalizado);

    }

    /**
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerAsignacionSupervisionPorId()
     *
     * @param coSupervision
     * @param idSupervision
     * @param idInstanciaProceso
     */
    public PgimSupervisionDTOResultado(String coSupervision, Long idSupervision, Long idInstanciaProceso) {
        super();

        this.setCoSupervision(coSupervision);
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerValoresTDR()
     * @param descTipoSupervision
     * @param descNoEspecialidad
     * @param descAgenteSupervisadoNoRazonSocial
     * @param descUnidadMineraNoUnidadMinera
     * @param descDeNuContrato
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param feInicioSupervisionDesc
     * @param descLNProfesionales
     * @param deTdrObjetivoTexto
     * @param deTdrAlcanceTexto
     * @param deTdrMetodologiaTexto
     * @param deTdrInformeSupervTexto
     * @param deTdrHonorariosProf
     * @param descPlntaBeneficioDestinoNoUnidadMinera
     */
    public PgimSupervisionDTOResultado(String descTipoSupervision, String descNoEspecialidad,
    		String descAgenteSupervisadoNoRazonSocial, String descUnidadMineraNoUnidadMinera,
    		String descDeNuContrato, String descEmpresaSupervisoraNoRazonSocial,
    		String feInicioSupervisionDesc, long descLNProfesionales,
    		String deTdrObjetivoTexto, String deTdrAlcanceTexto, String deTdrMetodologiaTexto, 
    		String deTdrInformeSupervTexto, String deTdrHonorariosProf, String descPlntaBeneficioDestinoNoUnidadMinera) {
        super();
        this.setDescTipoSupervision(descTipoSupervision);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescAgenteSupervisadoNoRazonSocial(descAgenteSupervisadoNoRazonSocial);
        this.setDescUnidadMineraNoUnidadMinera(descUnidadMineraNoUnidadMinera);
        this.setDescDeNuContrato(descDeNuContrato);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setFeInicioSupervisionDesc(feInicioSupervisionDesc);
        this.setDescLnProfesionales(descLNProfesionales);
        this.setDeTdrObjetivoTexto(deTdrObjetivoTexto); 
        this.setDeTdrAlcanceTexto(deTdrAlcanceTexto); 
        this.setDeTdrMetodologiaTexto(deTdrMetodologiaTexto); 
        this.setDeTdrInformeSupervTexto(deTdrInformeSupervTexto); 
        this.setDeTdrHonorariosProf(deTdrHonorariosProf);
        this.setDescPlntaBeneficioDestinoNoUnidadMinera(descPlntaBeneficioDestinoNoUnidadMinera);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerValoresActaInicioSupervision()
     * @param descAgenteSupervisadoNoRazonSocial
     * @param descNoUnidadMinera
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param feInicioSupervisionDesc
     * @param descHoraInicioSupervisionDesc
     * @param descPlntaBeneficioDestinoNoUnidadMinera
     * @param deObservacionInicioSuper
     * @param descNuExpedienteSiged
     * @param descNoPersona
     * @param descApPaterno
     * @param descApMaterno
     * @param descCoDocumentoIdentidad
     * @param descDeCargo
     */
    public PgimSupervisionDTOResultado(String descAgenteSupervisadoNoRazonSocial, 
    		String descNoUnidadMinera, String descEmpresaSupervisoraNoRazonSocial,
    		String feInicioSupervisionDesc, String descHoraInicioSupervisionDesc,  
    		String descPlntaBeneficioDestinoNoUnidadMinera, String deObservacionInicioSuper, 
    		String descNuExpedienteSiged) {
        super();
        this.setDescAgenteSupervisadoNoRazonSocial(descAgenteSupervisadoNoRazonSocial);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setFeInicioSupervisionDesc(feInicioSupervisionDesc);
        this.setDescHoraInicioSupervisionDesc(descHoraInicioSupervisionDesc);
        this.setDescPlntaBeneficioDestinoNoUnidadMinera(descPlntaBeneficioDestinoNoUnidadMinera);
        this.setDeObservacionInicioSuperT(deObservacionInicioSuper);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged); 
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerValoresActaSupervision()
     * @param descAgenteSupervisadoNoRazonSocial
     * @param descUnidadMineraNoUnidadMinera
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param feInicioSupervisionDesc
     * @param feFinSupervisionDesc
     * @param descHoraFinSupervisionDesc
     * @param descPlntaBeneficioDestinoNoUnidadMinera
     * @param deObservacionInicioSuper
     * @param deObservacionFinSuperT
     */
    public PgimSupervisionDTOResultado(String descAgenteSupervisadoNoRazonSocial, 
    		String descNoUnidadMinera, String descEmpresaSupervisoraNoRazonSocial,
    		String feInicioSupervisionRealDesc, String feFinSupervisionRealDesc, 
    		String horaInicioSupervisionRealDesc,String horaFinSupervisionRealDesc,  
    		String descPlntaBeneficioDestinoNoUnidadMinera, String deObservacionFinSuperT,
    		String descNuExpedienteSiged) {
        super();
        this.setDescAgenteSupervisadoNoRazonSocial(descAgenteSupervisadoNoRazonSocial);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setFeInicioSupervisionRealDesc(feInicioSupervisionRealDesc);
        this.setFeFinSupervisionRealDesc(feFinSupervisionRealDesc);
        this.setHoraInicioSupervisionRealDesc(horaInicioSupervisionRealDesc);
        this.setHoraFinSupervisionRealDesc(horaFinSupervisionRealDesc);
        this.setDescPlntaBeneficioDestinoNoUnidadMinera(descPlntaBeneficioDestinoNoUnidadMinera);
        this.setDeObservacionFinSuperT(deObservacionFinSuperT);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged); 
    }
 
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerValoresFichaHechosConstatados()
     * @param descAgenteSupervisadoNoRazonSocial
     * @param descUnidadMineraNoUnidadMinera
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param descNuExpedienteSiged
     * @param idUnidadMinera
     */
    public PgimSupervisionDTOResultado(String descAgenteSupervisadoNoRazonSocial, String descAgenteSupervisadoCoDocumento,
    		String descCoUnidadMinera, String descUnidadMineraNoUnidadMinera, String descEmpresaSupervisoraNoRazonSocial,
            String descEmpresaSupervisoraCoDocumento, String descNuExpedienteSiged, Long idUnidadMinera) {
        super();
        this.setDescAgenteSupervisadoNoRazonSocial(descAgenteSupervisadoNoRazonSocial);
        this.setDescCoDocumentoIdentidad(descAgenteSupervisadoCoDocumento);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescUnidadMineraNoUnidadMinera(descUnidadMineraNoUnidadMinera);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setDescDocSupervisora(descEmpresaSupervisoraCoDocumento);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerActasPresentadasPendienteLiquidar()
     * @param descNoUnidadMinera
     * @param descNoRazonSocial
     * @param coSupervision
     * @param descSubtipoSupervision
     * @param descTipoSupervision
     * @param idSupervision
     * @param descIdUnidadMinera
     * @param descIdDocumento
     */
    public PgimSupervisionDTOResultado(String descNoUnidadMinera, String descNoRazonSocial, String coSupervision,
    		String descSubtipoSupervision, String descTipoSupervision, Long idSupervision, Long descIdUnidadMinera,
    		Long descIdDocumento) {
        super();
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setCoSupervision(coSupervision);
        this.setDescSubtipoSupervision(descSubtipoSupervision);
        this.setDescTipoSupervision(descTipoSupervision);
        this.setIdSupervision(idSupervision);
        this.setDescIdUnidadMinera(descIdUnidadMinera);
        this.setDescIdDocumento(descIdDocumento);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarSupervisionPorUnidadMinera()
     * @param idSupervision
     */
    public PgimSupervisionDTOResultado(Long idSupervision) {
        super();
        this.setIdSupervision(idSupervision);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionRevisionAntecedente()
     * 
     * @param descTipoSupervision
     * @param descNoEspecialidad
     * @param descAgenteSupervisadoNoRazonSocial
     * @param descUnidadMineraNoUnidadMinera
     * @param descDeNuContrato
     * @param descEmpresaSupervisoraNoRazonSocial
     * @param idInstanciaProceso
     */
    public PgimSupervisionDTOResultado(String descTipoSupervision, String descNoEspecialidad,
    		String descAgenteSupervisadoNoRazonSocial, String descUnidadMineraNoUnidadMinera,
    		String descDeNuContrato, String descEmpresaSupervisoraNoRazonSocial, Long idInstanciaProceso, String descNuExpedienteSiged) {
        super();
        this.setDescTipoSupervision(descTipoSupervision);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescAgenteSupervisadoNoRazonSocial(descAgenteSupervisadoNoRazonSocial);
        this.setDescUnidadMineraNoUnidadMinera(descUnidadMineraNoUnidadMinera);
        this.setDescDeNuContrato(descDeNuContrato);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }


    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#obtenerSupervisionPorIdAux()
     * @param idSupervision
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     * @param idUnidadMinera
     * @param descNoUnidadMinera
     * @param descCoUnidadMinera
     * @param coDocumentoIdentidad
     * @param descNoRazonSocial
     * @param coSupervision
     * @param feInicioSupervision
     * @param feFinSupervision
     * @param feInicioSupervisionReal
     * @param feFinSupervisionReal
     * @param descPrograma
     * @param descIdContrato
     * @param descDeContrato
     * @param flPropia
     * @param idSubtipoSupervision
     * @param descSubTipoSupervision
     * @param idProgramaSupervision
     * @param descIdAgenteSupervisado
     * @param descNoEspecialidad
     * @param descIdEspecialidad
     * @param idMotivoSupervision
     * @param descTipoDocCoodinador
     * @param descDocCoodinador
     * @param descNoCoordinador
     * @param descDocSupervisora
     * @param descEmpresaSupervisoraNoRazonSocial
     */
    public PgimSupervisionDTOResultado(Long idSupervision, Long idInstanciaProceso, String descNuExpedienteSiged,
            Long idUnidadMinera, String descNoUnidadMinera, String descCoUnidadMinera, String tipoUnidadMinera,
            String coDocumentoIdentidad, String descNoRazonSocial, String coSupervision,
            Date feInicioSupervision, Date feFinSupervision, Date feInicioSupervisionReal, Date feFinSupervisionReal,
            String descPrograma, Long descIdContrato, String descDeContrato,String flPropia,
            Long idSubtipoSupervision, String descSubTipoSupervision, Long idProgramaSupervision, 
            Long descIdAgenteSupervisado, String descNoEspecialidad, Long descIdEspecialidad, Long idMotivoSupervision, Long descIdPersonaEmpSuperv,
            String descDocSupervisora, String descEmpresaSupervisoraNoRazonSocial, Long idPasoActual) {
        super();
        
        this.setIdSupervision(idSupervision);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(descNoUnidadMinera);
        this.setDescCoUnidadMinera(descCoUnidadMinera);
        this.setDescNoTipoUnidadMinera(tipoUnidadMinera);
        this.setDescCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setCoSupervision(coSupervision);
        this.setFeInicioSupervision(feInicioSupervision);
        this.setFeFinSupervision(feFinSupervision);
        this.setFeInicioSupervisionReal(feInicioSupervisionReal);
        this.setFeFinSupervisionReal(feFinSupervisionReal);
        this.setDescPrograma(descPrograma);
        this.setDescIdContrato(descIdContrato);
        this.setDescDeContrato(descDeContrato);
        this.setFlPropia(flPropia);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setDescSubtipoSupervision(descSubTipoSupervision);
        this.setIdProgramaSupervision(idProgramaSupervision);
        this.setDescIdAgenteSupervisado(descIdAgenteSupervisado);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setDescIdPersonaEmpSuperv(descIdPersonaEmpSuperv);
        this.setDescDocSupervisora(descDocSupervisora);
        this.setDescEmpresaSupervisoraNoRazonSocial(descEmpresaSupervisoraNoRazonSocial);
        this.setDescIdPasoActual(idPasoActual);
    }


    /**
     * @see pe.gob.osinergmin.pgim.models.repository.SupervisionRepository#listarPorCodSupervision(String)
     * @param idSupervision
     * @param coSupervision
     * @param idUnidadMinera
     * @param noUnidadMinera
     */
    public PgimSupervisionDTOResultado(Long idSupervision, String coSupervision, Long idUnidadMinera, String noUnidadMinera, String coUnidadMinera) {
        super();

        this.setIdSupervision(idSupervision);
        this.setCoSupervision(coSupervision);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setDescNoUnidadMinera(noUnidadMinera);
        this.setDescCoUnidadMinera(coUnidadMinera);

    }
}