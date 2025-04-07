package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimContratoDTOResultado extends PgimContratoDTO {

    /***
     * Permite obtener contratos a los que está asociado el usuario personal de empresa supervisora
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerContratos()
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerContratos(@Param("userName") String userName)
     * 
     * @param idContrato
     * @param nuContrato
     * @param deContrato
     * @param idEspecialidad
     * @param flEstado
     */
    public PgimContratoDTOResultado(Long idContrato, String nuContrato, String deContrato, Long idEspecialidad,
            String flEstado) {
        super();

        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setDeContrato(deContrato);
        this.setIdEspecialidad(idEspecialidad);
        this.setFlEstado(flEstado);
    }

    /**
     * Me permite filtrar por nombre de contrato de la supervision el metodo es:
     * filtrarPorNombreContratoSupervision
     * 
     * @param idContrato
     * @param deContrato
     */
    public PgimContratoDTOResultado(Long idContrato, String deContrato) {
        super();

        this.setIdContrato(idContrato);
        this.setDeContrato(deContrato);
    }

    /**
     * Envia las propiedades al metodo del repositorio:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#listarContratos()
     * 
     * @param idContrato
     * @param nuContrato
     * @param deContrato
     * @param feInicioContrato
     * @param feFinContrato
     * @param descNoRazonSocial
     * @param descNoEspecialidad
     * @param descNuExpedienteSiged
     * @param moImporteContrato
     * @param flEstado
     */
    public PgimContratoDTOResultado(Long idContrato, String nuContrato, String deContrato, Date feInicioContrato,
            Date feFinContrato, String descNoRazonSocial, String descNoEspecialidad, String descNuExpedienteSiged,
            BigDecimal moImporteContrato, String flEstado) {
        super();

        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setDeContrato(deContrato);
        this.setFeInicioContrato(feInicioContrato);
        this.setFeFinContrato(feFinContrato);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setMoImporteContrato(moImporteContrato);
        this.setFlEstado(flEstado);
    }

    /**
     * Envia las propiedades al metodo del repositorio:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#listarPorCodigoContrato()
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerContratoPorIdLiquidacion()
     * @param idContrato
     * @param nuContrato
     * @param deContrato
     * @param idInstanciaProceso
     */
    public PgimContratoDTOResultado(Long idContrato, String nuContrato, String deContrato, Long idInstanciaProceso) {
        super();

        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setDeContrato(deContrato);
        this.setIdInstanciaProceso(idInstanciaProceso);
    }

    /**
     * Envia las propiedades al metodo del repositorio:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#listarPorNumeroExpediente()
     * @param idContrato
     * @param nuContrato
     * @param descNuExpedienteSiged
     * @param deContrato
     */
    public PgimContratoDTOResultado(Long idContrato, Long idInstanciaProceso, String descNuExpedienteSiged) {
        super();

        this.setIdContrato(idContrato);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
    }

    /**
     * Envia las propiedades al metodo del repositorio:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerContratoPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerContratoPorIdSupervision()
     * @param idContrato
     * @param nuContrato
     * @param feInicioContrato
     * @param feFinContrato
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     * @param idEmpresaSupervisora
     * @param descNoRazonSocial
     * @param deContrato
     * @param idEspecialidad
     * @param descNoEspecialidad
     * @param pcEntregableActa
     * @param pcEntregableInforme
     * @param pcEntregableFinal
     * @param moImporteContrato
     * @param flEstado
     */
    public PgimContratoDTOResultado(Long idContrato, Long descIdPersona, String nuContrato, Date feInicioContrato, Date feFinContrato,
            Long idInstanciaProceso, String descNuExpedienteSiged, Long idEmpresaSupervisora, String descNoRazonSocial,
            String deContrato, Long idEspecialidad, String descNoEspecialidad, Long pcEntregableActa,
            Long pcEntregableInforme, Long pcEntregableFinal, BigDecimal moImporteContrato, String flEstado,
            Integer nuDiasEntregaInforme, Integer nuDiasAbsolucionInforme, String descFlConsorcio, Integer nuDiasRevisionInforme, String noUsuarioXDefecto) {
        super();

        this.setIdContrato(idContrato);
        this.setDescIdPersona(descIdPersona);
        this.setNuContrato(nuContrato);
        this.setFeInicioContrato(feInicioContrato);
        this.setFeFinContrato(feFinContrato);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setIdEmpresaSupervisora(idEmpresaSupervisora);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDeContrato(deContrato);
        this.setIdEspecialidad(idEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setPcEntregableActa(pcEntregableActa);
        this.setPcEntregableInforme(pcEntregableInforme);
        this.setPcEntregableFinal(pcEntregableFinal);
        this.setMoImporteContrato(moImporteContrato);
        this.setFlEstado(flEstado);

        this.setNuDiasEntregaInforme(nuDiasEntregaInforme);
        this.setNuDiasAbsolucionInforme(nuDiasAbsolucionInforme);
        this.setDescFlConsorcio(descFlConsorcio);
        this.setNuDiasRevisionInforme(nuDiasRevisionInforme);
        this.setNoUsuarioXDefecto(noUsuarioXDefecto);
    }

    /**
     * Envia las propiedades al metodo del repositorio:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerContratos()
     * @param idContrato
     * @param nuContrato
     * @param descNuExpedienteSiged
     * @param deContrato
     */
    public PgimContratoDTOResultado(Long idContrato, String nuContrato, String descNoRazonSocial,
            String descNoEspecialidad, String flEstado) {
        super();

        this.setIdContrato(idContrato);
        this.setNuContrato(nuContrato);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setFlEstado(flEstado);
    }

    public PgimContratoDTOResultado(Long pcEntregableActa) {
        super();

        this.setPcEntregableActa(pcEntregableActa);
    }

    /**
     * Envia las propiedades al metodo del repositorio:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#existeNuExpediente()
     * @param idContrato
     * @param idInstanciaProceso
     * @param descNuExpedienteSiged
     * @param deContrato
     */
    public PgimContratoDTOResultado(Long idContrato, Long idInstanciaProceso, String descNuExpedienteSiged,
            String deContrato) {
        super();

        this.setIdContrato(idContrato);
        this.setIdInstanciaProceso(idInstanciaProceso);
        this.setDescNuExpedienteSiged(descNuExpedienteSiged);
        this.setDeContrato(deContrato);
    }
    
    
    /**
     * Permite obtener el Resumen del consumo del contrato S/
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerMontoConsumoContrato()
     * @see pe.gob.osinergmin.pgim.models.repository.ContratoRepository#obtenerMontoConsumoContratoPorEstadio()
     * @param idContrato
     * @param descResumenConsumoContrato
     * 
     * 
     */
    public PgimContratoDTOResultado(Long idContrato, BigDecimal descResumenConsumoContrato) {
        super();

        this.setIdContrato(idContrato);
        this.setDescResumenConsumoContrato(descResumenConsumoContrato);
        
    }
    
}
