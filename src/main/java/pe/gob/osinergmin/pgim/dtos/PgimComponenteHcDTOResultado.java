package pe.gob.osinergmin.pgim.dtos;

public class PgimComponenteHcDTOResultado extends PgimComponenteHcDTO{
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ComponenteHcRepository#listarComponenteMineroHc(Long)
     * @param idComponenteHc
     * @param idHechoConstatado
     * @param idCmineroSprvsion
     * @param descCoComponente
     * @param descNoComponente
     */
	public PgimComponenteHcDTOResultado(Long idComponenteHc, Long idHechoConstatado,
			Long idCmineroSprvsion, String descNoTipoComponente, String descCoComponente, String descNoComponente, String flAplica) {
		super();

		this.setIdComponenteHc(idComponenteHc);
		this.setIdHechoConstatado(idHechoConstatado);
		this.setIdCmineroSprvsion(idCmineroSprvsion);
        this.setDescNoTipoComponente(descNoTipoComponente);
		this.setDescCoComponente(descCoComponente);
		this.setDescNoComponente(descNoComponente);
		this.setFlAplica(flAplica);
	}

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ComponenteHcRepository#obtenerComponenteMineroHcId(Long)
     * @param idComponenteHc
     * @param idHechoConstatado
     * @param idCmineroSprvsion
     * @param descCoComponente
     * @param descNoComponente
     */
	public PgimComponenteHcDTOResultado(Long idComponenteHc, Long idHechoConstatado,
			Long idCmineroSprvsion, String descNoTipoComponente, String descCoComponente, String descNoComponente, String flAplica, boolean descSelected) {
		super();

		this.setIdComponenteHc(idComponenteHc);
		this.setIdHechoConstatado(idHechoConstatado);
		this.setIdCmineroSprvsion(idCmineroSprvsion);
        this.setDescNoTipoComponente(descNoTipoComponente);
		this.setDescCoComponente(descCoComponente);
		this.setDescNoComponente(descNoComponente);
		this.setFlAplica(flAplica);
        this.setDescSelected(descSelected);
	}
}
