package pe.gob.osinergmin.pgim.dtos;

public class PgimInfraccionxtitularAuxDTOResultado extends PgimInfraccionxtitularAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository#listarInfraccionxtitular()
	 * @see pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository#listarInfraccionxtitularPaginado()
	 * 
	 * @param idAgenteSupervisadoAux
	 * @param coDocumentoIdentidad
	 * @param noRazonSocial
	 * @param noCortoAgenteSupervisado
	 * @param idEstrato
	 * @param noEstrato
	 * @param noCortoEstrato
	 * @param nroInfraccionP1
	 * @param nroInfraccionP2
	 * @param nroInfraccionP3
	 * @param nroInfraccionP4
	 * @param nroInfraccionP5
	 * @param nroInfraccionP6
	 * @param nroInfraccionTotal
	 */
	public PgimInfraccionxtitularAuxDTOResultado(
        	Long idAgenteSupervisadoAux            
            , String coDocumentoIdentidad
            , String noRazonSocial
            , String noCortoAgenteSupervisado
            , Long idEstrato
            , String noEstrato
            , String noCortoEstrato
            , Long nroInfraccionP1
            , Long nroInfraccionP2
            , Long nroInfraccionP3
            , Long nroInfraccionP4
            , Long nroInfraccionP5
            , Long nroInfraccionP6
            , Long nroInfraccionTotal
			){
            super();
            this.setIdAgenteSupervisadoAux(idAgenteSupervisadoAux);   
            
            this.setCoDocumentoIdentidad(coDocumentoIdentidad);
            this.setNoRazonSocial(noRazonSocial);
            this.setNoCortoAgenteSupervisado(noCortoAgenteSupervisado);
            
            this.setIdEstrato(idEstrato);
            this.setNoEstrato(noEstrato);
            this.setNoCortoEstrato(noCortoEstrato);
            
            this.setNroInfraccionP1(nroInfraccionP1);
            this.setNroInfraccionP2(nroInfraccionP2);
            this.setNroInfraccionP3(nroInfraccionP3);
            this.setNroInfraccionP4(nroInfraccionP4);
            this.setNroInfraccionP5(nroInfraccionP5);
            this.setNroInfraccionP6(nroInfraccionP6);
            this.setNroInfraccionTotal(nroInfraccionTotal);
            
        }

}
