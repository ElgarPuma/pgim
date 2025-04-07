package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PgimAgenteSupervisadoDTOResultado extends PgimAgenteSupervisadoDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository #obtenerAgenteSupervisadoPorRuc()
     * @see pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository #existeAgenteSupervisado()
     * @see pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository #listarPorPalabraClave()
     * @see pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository #obtenerAgenteSupervisadoPorIdAs()
     * 
     * @param idAgenteSupervisado
     * @param descCoDocumentoIdentidad
     * @param descNoRazonSocial
     * @param idPersona
     */
    public PgimAgenteSupervisadoDTOResultado(Long idAgenteSupervisado, String descCoDocumentoIdentidad,
                                             String descNoRazonSocial, Long idPersona) {
        super();
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setIdPersona(idPersona);
    }

    public PgimAgenteSupervisadoDTOResultado(
    		Long idAgenteSupervisado,Long idPersona,String descCoDocumentoIdentidad,String descNoRazonSocial,
    		Long idEstrato,Long idTamanioEmpresa,String descDeTelefono,String descDeTelefono2,
    		String descDeCorreo,String descDeCorreo2,String descDeDireccion,Long idUbigeo, 
    		BigDecimal nuCapacidadTotalInstalada,String noUbigeo,String flAfiliadoNtfccionElctrnca,
    		Date feAfiliadoDesde,String deCorreoNtfccionElctrnca,String descNoCorto,
    		String dePrincipalActividadEcnmca) {
        super();
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setIdPersona(idPersona);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setIdEstrato(idEstrato);
        this.setIdTamanioEmpresa(idTamanioEmpresa);
        this.setDescDeTelefono(descDeTelefono);
        this.setDescDeTelefono2(descDeTelefono2);
        this.setDescDeCorreo(descDeCorreo);
        this.setDescDeCorreo2(descDeCorreo2);
        this.setDescDeDireccion(descDeDireccion);
        this.setIdUbigeo(idUbigeo);
        this.setNuCapacidadTotalInstalada(nuCapacidadTotalInstalada);
        this.setNoUbigeo(noUbigeo);
        this.setFlAfiliadoNtfccionElctrnca(flAfiliadoNtfccionElctrnca);
        this.setFeAfiliadoDesde(feAfiliadoDesde);
        this.setDeCorreoNtfccionElctrnca(deCorreoNtfccionElctrnca); 
        this.setDescNoCorto(descNoCorto);
        this.setDePrincipalActividadEcnmca(dePrincipalActividadEcnmca);
    }
    
    public PgimAgenteSupervisadoDTOResultado(Long idAgenteSupervisado, String descCoDocumentoIdentidad,
                                             String descNoRazonSocial, String descNoEstrato, BigDecimal nuCapacidadTotalInstalada) {
        super();
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescNoEstrato(descNoEstrato);
        this.setNuCapacidadTotalInstalada(nuCapacidadTotalInstalada);
    }

    /**
     * Constructor para la lista de agente supervisado
     * @param idAgenteSupervisado
     * @param descNoRazonSocial
     * @param descCoDocumentoIdentidad
     * @param descNoEstrato
     * @param descNoTamanioEmpresa
     * @param descDeTelefono
     * @param descDeTelefono2
     * @param descDeCorreo
     * @param descDeCorreo2
     * @param nuCantidadUniMinera
     */
	public PgimAgenteSupervisadoDTOResultado(
	        Long idAgenteSupervisado,
	        String descNoRazonSocial, 
	        String descCoDocumentoIdentidad,
	        String descNoEstrato,
	        String descNoTamanioEmpresa,
	        String descDeTelefono,
	        String descDeTelefono2,
	        String descDeCorreo,
	        String descDeCorreo2,
            Long nuCantidadUniMinera
            // BigDecimal nuCapacidadTotalInstalada
	            ) {
	        super();
	        this.setIdAgenteSupervisado(idAgenteSupervisado);
	        this.setDescNoRazonSocial(descNoRazonSocial);
	        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
	        this.setDescNoEstrato(descNoEstrato);
	        this.setDescNoTamanioEmpresa(descNoTamanioEmpresa);
	        this.setDescDeTelefono(descDeTelefono);
	        this.setDescDeTelefono2(descDeTelefono2);
	        this.setDescDeCorreo(descDeCorreo);
	        this.setDescDeCorreo2(descDeCorreo2);
            this.setNuCantidadUniMinera(nuCantidadUniMinera);
            // this.setNuCapacidadTotalInstalada(nuCapacidadTotalInstalada);
	        
	    }
	public PgimAgenteSupervisadoDTOResultado(
	        Long idAgenteSupervisado,
	        String descNoRazonSocial, 
	        String descCoDocumentoIdentidad,
	        String descNoEstrato,
	        String descNoTamanioEmpresa,
	        String descDeTelefono,
	        String descDeTelefono2,
	        String descDeCorreo,
	        String descDeCorreo2,
            Long nuCantidadUniMinera,
            BigDecimal nuCapacidadTotalInstalada
	            ) {
	        super();
	        this.setIdAgenteSupervisado(idAgenteSupervisado);
	        this.setDescNoRazonSocial(descNoRazonSocial);
	        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
	        this.setDescNoEstrato(descNoEstrato);
	        this.setDescNoTamanioEmpresa(descNoTamanioEmpresa);
	        this.setDescDeTelefono(descDeTelefono);
	        this.setDescDeTelefono2(descDeTelefono2);
	        this.setDescDeCorreo(descDeCorreo);
	        this.setDescDeCorreo2(descDeCorreo2);
            this.setNuCantidadUniMinera(nuCantidadUniMinera);
            this.setNuCapacidadTotalInstalada(nuCapacidadTotalInstalada);
	        
	    }

    public PgimAgenteSupervisadoDTOResultado(
        Long idAgenteSupervisado,
        Long nuCantidadUniMinera
            ) {
        super();
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setNuCantidadUniMinera(nuCantidadUniMinera);
        
    }
    
    public PgimAgenteSupervisadoDTOResultado(Long idAgenteSupervisado, String descCoDocumentoIdentidad,
                                             String descNoRazonSocial, String descNoTamanioEmpresa) {
        super();
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setDescCoDocumentoIdentidad(descCoDocumentoIdentidad);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescNoTamanioEmpresa(descNoTamanioEmpresa);
    }
    
    /**
     * see pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository#listarAgenteSupervisadoPorPersona
     * @param idAgenteSupervisado
     */
    public PgimAgenteSupervisadoDTOResultado(Long idAgenteSupervisado) {
        super();
        this.setIdAgenteSupervisado(idAgenteSupervisado);
    }

}