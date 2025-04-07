package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimComponenteMineroDTOResultado extends PgimComponenteMineroDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarComponenteMinero()
	 * @param idComponenteMinero
	 * @param noComponente
	 * @param descIdTipoComponenteMiniero
	 * @param nuCapacidadPlanta
	 * @param coComponente
	 * @param idTipoComponenteMinero
	 * @param descNuValorNumerico
	 * @param idComponenteMineroPadre
	 */
	public PgimComponenteMineroDTOResultado(Long idComponenteMinero, String noComponente,
			String descIdTipoComponenteMiniero, BigDecimal nuCapacidadPlanta, String coComponente,
			Long idTipoComponenteMinero, String descCoTipocomponente, BigDecimal descNuValorNumerico, Long idComponenteMineroPadre, 
			String descNoTipoSubcomponente, String descCoTipoSubcomponente) {
		super();

		this.setIdComponenteMinero(idComponenteMinero);
		this.setNoComponente(noComponente);
		this.setDescIdTipoComponenteMinero(descIdTipoComponenteMiniero);
		this.setNuCapacidadPlanta(nuCapacidadPlanta);
		this.setCoComponente(coComponente);
		this.setIdTipoComponenteMinero(idTipoComponenteMinero);
		this.setDescCoTipocomponente(descCoTipocomponente);
		this.setIdComponenteMineroPadre(idComponenteMineroPadre);
		this.setDescNoTipoSubcomponente(descNoTipoSubcomponente);
		this.setDescCoTipoSubcomponente(descCoTipoSubcomponente);
		if (descNuValorNumerico != null)
			this.setDescNuValorNumerico(descNuValorNumerico.longValue());

		// valores por defecto para lista de selección
		this.setDescNombreCompleto(coComponente + " - " + descIdTipoComponenteMiniero + " - " + noComponente);
		this.setDescColor("primary");
		this.setDescSeleccionado(false);

	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#obtenerComponenteMineroPorId
	 * @param idComponenteMinero
	 * @param noComponente
	 * @param descIdTipoComponenteMiniero
	 * @param nuCapacidadPlanta
	 * @param coComponente
	 * @param idTipoComponenteMinero
	 */
	public PgimComponenteMineroDTOResultado(Long idComponenteMinero, Long idUnidadMinera, Long idTipoSubcomponente,
			String descNoTipoSubcomponente, String noComponente,
			String descIdTipoComponenteMinero, BigDecimal nuCapacidadPlanta, String coComponente,
			Long idTipoComponenteMinero, BigDecimal descNuValorNumerico, Long idComponenteMineroPadre, Long nvlAceptacion, String ipCreacion, String ipActualizacion) {
		super();

		this.setIdComponenteMinero(idComponenteMinero);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setIdTipoSubcomponente(idTipoSubcomponente);
		this.setDescNoTipoSubcomponente(descNoTipoSubcomponente);
		this.setNoComponente(noComponente);
		this.setDescIdTipoComponenteMinero(descIdTipoComponenteMinero);
		this.setNuCapacidadPlanta(nuCapacidadPlanta);
		this.setCoComponente(coComponente);
		this.setIdTipoComponenteMinero(idTipoComponenteMinero);
		this.setDescNuValorNumerico(descNuValorNumerico.longValue());
		this.setIdComponenteMineroPadre(idComponenteMineroPadre);
		this.setNvlAceptacion(nvlAceptacion);
		this.setIpCreacion(ipCreacion);
		this.setIpActualizacion(ipActualizacion);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarComponentePadrePorUM
	 * @param idComponenteMinero
	 * @param idUnidadMinera
	 * @param descNombreCompleto
	 */
	public PgimComponenteMineroDTOResultado(Long idComponenteMinero, Long idUnidadMinera, Long idComponenteMineroPadre,
			String descNombreCompleto) {
		super();

		this.setIdComponenteMinero(idComponenteMinero);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setIdComponenteMineroPadre(idComponenteMineroPadre);
		this.setDescNombreCompleto(descNombreCompleto);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#obtenerComponenteMineroPorIdUnidadMinera
	 * @param idComponenteMinero
	 * @param idUnidadMinera
	 * @param noComponente
	 * @param descIdTipoComponenteMinero
	 * @param nuCapacidadPlanta
	 * @param coComponente
	 * @param idTipoComponenteMinero
	 */
	public PgimComponenteMineroDTOResultado(Long idComponenteMinero, Long idUnidadMinera, String noComponente,
			String descIdTipoComponenteMinero, BigDecimal nuCapacidadPlanta, String coComponente,
			Long idTipoComponenteMinero) {
		super();

		this.setIdComponenteMinero(idComponenteMinero);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setNoComponente(noComponente);
		this.setDescIdTipoComponenteMinero(descIdTipoComponenteMinero);
		this.setNuCapacidadPlanta(nuCapacidadPlanta);
		this.setCoComponente(coComponente);
		this.setIdTipoComponenteMinero(idTipoComponenteMinero);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarComponenteMineroPorUnidadMinera
	 * @param idComponenteMinero
	 */
	public PgimComponenteMineroDTOResultado(Long idComponenteMinero) {
		super();

		this.setIdComponenteMinero(idComponenteMinero);
	}

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarReporteComponenteUMPaginado
	 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarReporteComponenteUM
	 * @param idComponenteMinero
	 * @param noComponente
	 * @param descIdTipoComponenteMinero
	 * @param coComponente
	 * @param idTipoComponenteMinero
	 * @param idUnidadMinera
	 * @param descCoUnidadMinera
	 * @param descUnidadMinera
	 * @param descRucAgenteFiscalizado
	 * @param descAgenteFiscalizado
	 */
	public PgimComponenteMineroDTOResultado(Long idComponenteMinero, String noComponente, String coComponente,
			Long idTipoComponenteMinero, String descIdTipoComponenteMinero, Long idUnidadMinera, String descCoUnidadMinera, 
			String descUnidadMinera, String descRucAgenteFiscalizado, String descAgenteFiscalizado) {
		super();

		this.setIdComponenteMinero(idComponenteMinero);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setDescCoUnidadMinera(descCoUnidadMinera);
		this.setNoComponente(noComponente);
		this.setDescIdTipoComponenteMinero(descIdTipoComponenteMinero);
		this.setCoComponente(coComponente);
		this.setIdTipoComponenteMinero(idTipoComponenteMinero);
		this.setDescUnidadMinera(descUnidadMinera);
		this.setDescAgenteFiscalizado(descAgenteFiscalizado);
		this.setDescRucAgenteFiscalizado(descRucAgenteFiscalizado);

	}
	
	  
		/**
		 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarComponentes()
		 * @see pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository#listarPorPalabraClaveYUm()
		 * 
		 * @param idComponenteMinero
		 * @param noComponente
		 * @param descIdTipoComponenteMiniero
		 * @param nuCapacidadPlanta
		 * @param coComponente
		 * @param idTipoComponenteMinero
		 * @param descNuValorNumerico
		 * @param idComponenteMineroPadre
		 * @param idUnidadMinera
		 */
		public PgimComponenteMineroDTOResultado(Long idComponenteMinero, String noComponente,
				String descIdTipoComponenteMiniero, String coComponente,
				Long idTipoComponenteMinero, Long idComponenteMineroPadre, Long idUnidadMinera, String coUM, 
				String noUnidadMinera) {
			super();
		
			this.setIdUnidadMinera(idUnidadMinera);
			this.setDescCoUnidadMinera(coUM);
			this.setDescUnidadMinera(noUnidadMinera);
			this.setIdComponenteMinero(idComponenteMinero);
			this.setNoComponente(noComponente);
			this.setCoComponente(coComponente);
			this.setIdComponenteMineroPadre(idComponenteMineroPadre);
			this.setDescIdTipoComponenteMinero(descIdTipoComponenteMiniero);
			this.setIdTipoComponenteMinero(idTipoComponenteMinero);
			
			// valores por defecto para lista de selección
			this.setDescNombreCompleto(coComponente + " - " + descIdTipoComponenteMiniero + " - " + noComponente);

	
		}

}
