package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimUnidadMineraDTOResultado extends PgimUnidadMineraDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#listar()
	 * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#listarUnidadesMinerasAS()
	 * 
	 * @param idUnidadMinera
	 * @param coUnidadMinera
	 * @param noUnidadMinera
	 * @param descCoDocumentoIdentidad
	 * @param descNoRazonSocial
	 * @param descIdTipoActividad
	 * @param descIdDivisonSupervisora
	 * @param descIdTipoSituacion
	 * @param descIdTipoUnidadMinera
	 * @param descNoMetodoMinado
	 * @param descNoEstadoUm
	 */
    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            String descCoDocumentoIdentidad, String descNoRazonSocial, String descIdTipoActividad,
            String descIdDivisonSupervisora, String descIdTipoSituacion, String descIdTipoUnidadMinera, 
            String descNoMetodoMinado, String descNoEstadoUm) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescIdTipoActividad(descIdTipoActividad);
        this.setDescIdDivisonSupervisora(descIdDivisonSupervisora);
        this.setDescIdTipoSituacion(descIdTipoSituacion);
        this.setDescIdTipoUnidadMinera(descIdTipoUnidadMinera);
        this.setDescNoMetodoMinado(descNoMetodoMinado);
        this.setDescNoEstadoUm(descNoEstadoUm);
    }

    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            String descCoDocumentoIdentidad, String descNoRazonSocial, String descIdTipoActividad,
            String descIdDivisonSupervisora, String descIdTipoSituacion, String descIdTipoUnidadMinera, String descUbigeo) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescIdTipoActividad(descIdTipoActividad);
        this.setDescIdDivisonSupervisora(descIdDivisonSupervisora);
        this.setDescIdTipoSituacion(descIdTipoSituacion);
        this.setDescIdTipoUnidadMinera(descIdTipoUnidadMinera);
        this.setDescUbigeo(descUbigeo);
    }

    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#listarPorPalabraClaveYAs()
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#listarPorPalabraClave()
     * 
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param descCoDocumentoIdentidad
     * @param descNoRazonSocial
     */
    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera, 
    		String descCoDocumentoIdentidad, String descNoRazonSocial ) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#obtenerUnidadMineraPorId()
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#obtenerUmPorCodigo()
     */
    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            Long idAgenteSupervisado, String descCoDocumentoIdentidad, String descNoRazonSocial, Date feInicioTitularidad,
            String descIdTipoActividad, String descIdDivisonSupervisora, String descIdTipoSituacion,
            String descIdTipoUnidadMinera, String coAnonimizacion, Long idDivisionSupervisora, Long idSituacion,
            Long idTipoActividad, Long idTipoUnidadMinera, BigDecimal nuCpcdadInstldaPlanta, Long idMetodoMinado, String descNoMetodoMinado,
            Long idMetodoExplotacion, String flCmraSubtrraneaGas, BigDecimal nuProfundidad, BigDecimal nuAlturaMinima,
            BigDecimal nuAlturaMaxima, Long idTipoYacimiento, Long idTipoSustancia, Long idPlntaBeneficioDestino,
            String descIdPlntaBeneficioDestino, String deUbicacionAcceso, String esRegistro,
            String flRegistraRiesgos, Long idSubactividad, Long idEstadoUm, String flConPiques, Long descNuCantPiques) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCoAnonimizacion(coAnonimizacion);

        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setDescIdDivisonSupervisora(descIdDivisonSupervisora);

        this.setIdSituacion(idSituacion);
        this.setDescIdTipoSituacion(descIdTipoSituacion);

        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);

        this.setFeInicioTitularidad(feInicioTitularidad);

        this.setIdTipoActividad(idTipoActividad);
        this.setDescIdTipoActividad(descIdTipoActividad);

        this.setIdTipoUnidadMinera(idTipoUnidadMinera);
        this.setDescIdTipoUnidadMinera(descIdTipoUnidadMinera);

        this.setNuCpcdadInstldaPlanta(nuCpcdadInstldaPlanta);

        this.setIdMetodoMinado(idMetodoMinado);
        this.setDescNoMetodoMinado(descNoMetodoMinado);

        this.setIdMetodoExplotacion(idMetodoExplotacion);

        this.setFlCmraSubtrraneaGas(flCmraSubtrraneaGas);
        this.setNuProfundidad(nuProfundidad);
        this.setNuAlturaMinima(nuAlturaMinima);
        this.setNuAlturaMaxima(nuAlturaMaxima);
        this.setIdTipoYacimiento(idTipoYacimiento);
        this.setIdTipoSustancia(idTipoSustancia);

        this.setIdPlntaBeneficioDestino(idPlntaBeneficioDestino);
        this.setDescIdPlntaBeneficioDestino(descIdPlntaBeneficioDestino);

        this.setDeUbicacionAcceso(deUbicacionAcceso);

        this.setEsRegistro(esRegistro);
        this.setFlRegistraRiesgos(flRegistraRiesgos);
        this.setIdSubactividad(idSubactividad);
        this.setIdEstadoUm(idEstadoUm);
        this.setFlConPiques(flConPiques);
        this.setDescNuCantPiques(descNuCantPiques);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#obtenerFichaInformativaUM()
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param descNoEstrato
     * @param descNoDivisionSupervisora
     * @param descNoTipoUnidadMinera
     * @param descNoSituacion
     * @param descNoPlntaBeneficioDestino
     * @param descNoTipoMinado
     * @param flCmraSubtrraneaGas
     * @param descNoTipoYacimiento
     * @param descNoTipoSustancia
     * @param nuProfundidad
     * @param nuAlturaMinima
     * @param nuAlturaMaxima
     * @param idAgenteSupervisado
     * @param idTipoUnidadMinera
     * @param deUbicacionAcceso
     */
    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
    		String descNoEstrato, String descNoDivisionSupervisora, String descNoTipoUnidadMinera, String descNoSituacion, 
    		String descNoPlntaBeneficioDestino, String descNoTipoMinado, String descNoMetodoExplotacion, String flCmraSubtrraneaGas,
    		String descNoTipoYacimiento, String descNoTipoSustancia, BigDecimal nuProfundidad,
    		BigDecimal nuAlturaMinima, BigDecimal nuAlturaMaxima, Long idAgenteSupervisado, Long idTipoUnidadMinera,
    		String deUbicacionAcceso) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setDescNoEstrato(descNoEstrato);
        this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
        this.setDescNoTipoUnidadMinera(descNoTipoUnidadMinera);
        this.setDescNoSituacion(descNoSituacion);
        this.setDescNoPlntaBeneficioDestino(descNoPlntaBeneficioDestino);
        this.setDescNoTipoMinado(descNoTipoMinado);
        this.setDescNoMetodoExplotacion(descNoMetodoExplotacion);
        this.setFlCmraSubtrraneaGas(flCmraSubtrraneaGas);
        this.setDescNoTipoYacimiento(descNoTipoYacimiento);
        this.setDescNoTipoSustancia(descNoTipoSustancia);
        this.setNuProfundidad(nuProfundidad);
        this.setNuAlturaMinima(nuAlturaMinima);
        this.setNuAlturaMaxima(nuAlturaMaxima);
        this.setIdAgenteSupervisado(idAgenteSupervisado); 
        this.setIdTipoUnidadMinera(idTipoUnidadMinera);
        this.setDeUbicacionAcceso(deUbicacionAcceso);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository#listarUnidadMineraPorAgenteSupervisado()
     * @param idUnidadMinera
     */
    public PgimUnidadMineraDTOResultado(Long idUnidadMinera) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
    }

    public PgimUnidadMineraDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            String descCoDocumentoIdentidad, String descNoRazonSocial, String descIdTipoActividad,
            String descIdDivisonSupervisora, String descIdTipoSituacion, String descIdTipoUnidadMinera, 
            String descUbigeoDemarcacion,String descFlPrincipalDemarcacion, BigDecimal descPorcentajeDemarcacion) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescIdTipoActividad(descIdTipoActividad);
        this.setDescIdDivisonSupervisora(descIdDivisonSupervisora);
        this.setDescIdTipoSituacion(descIdTipoSituacion);
        this.setDescIdTipoUnidadMinera(descIdTipoUnidadMinera);
        this.setDescUbigeoDemarcacion(descUbigeoDemarcacion);
        this.setDescFlPrincipalDemarcacion(descFlPrincipalDemarcacion);
        this.setDescPorcentajeDemarcacion(descPorcentajeDemarcacion);
    }
    
}
