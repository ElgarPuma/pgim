package pe.gob.osinergmin.pgim.dtos;

public class PgimReglaBaseDTOResultado extends PgimReglaBaseDTO{

	/**
	 * 
	 * @see pe.gob.osinergmin.pgim.models.repository.ReglaBaseRepository#obtenerReglaPorSupervision(Long)
	 * @see pe.gob.osinergmin.pgim.models.repository.ReglaBaseRepository#obtenerReglaBasePorId(Long)
	 * 
	 * @param idReglaBase
	 * @param idConfiguracionBase
	 * @param descNoConfiguracionBase
	 * @param idDivisionSupervisora
	 * @param descNoDivisionSupervisora
	 * @param idMotivoSupervision
	 * @param descNoMotivoSupervision
	 * @param idSubtipoSupervision
	 * @param descNoSubtipoSupervision
	 * @param idMetodoMinado
	 * @param descNoMetodoMinado
	 * @param flPropia
	 * @param flConPiques
	 */
	public PgimReglaBaseDTOResultado(Long idReglaBase, Long idConfiguracionBase, String descNoConfiguracionBase, 
			Long idDivisionSupervisora, String descNoDivisionSupervisora, Long idMotivoSupervision, String descNoMotivoSupervision, 
			Long idSubtipoSupervision, String descNoSubtipoSupervision, Long idMetodoMinado, String descNoMetodoMinado, String flPropia, String flConPiques) {
		super();
		
		this.setIdReglaBase(idReglaBase);
		this.setIdConfiguracionBase(idConfiguracionBase);
		this.setDescNoConfiguracionBase(descNoConfiguracionBase);
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
		this.setIdMotivoSupervision(idMotivoSupervision);
		this.setDescNoMotivoSupervision(descNoMotivoSupervision);
		this.setIdSubtipoSupervision(idSubtipoSupervision);
		this.setDescNoSubtipoSupervision(descNoSubtipoSupervision);
		this.setIdMetodoMinado(idMetodoMinado);
		this.setDescNoMetodoMinado(descNoMetodoMinado);
		this.setFlPropia(flPropia);
		this.setFlConPiques(flConPiques);

	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ReglaBaseRepository#listarReglasPorCfgBase(Long)
	 * 
	 * @param idReglaBase
	 * @param idConfiguracionBase
	 * @param descNoConfiguracionBase
	 * @param idDivisionSupervisora
	 * @param descNoDivisionSupervisora
	 * @param idMotivoSupervision
	 * @param descNoMotivoSupervision
	 * @param idSubtipoSupervision
	 * @param descNoSubtipoSupervision
	 * @param idMetodoMinado
	 * @param descNoMetodoMinado
	 * @param flPropia
	 * @param flConPiques
	 * @param descNoTipoSupervision
	 * @param descIdTipoSupervision
	 * @param desNoEspecialidad
	 * @param desIdEspecialidad
	 */
	public PgimReglaBaseDTOResultado(Long idReglaBase, Long idConfiguracionBase, String descNoConfiguracionBase, 
			Long idDivisionSupervisora, String descNoDivisionSupervisora, Long idMotivoSupervision, String descNoMotivoSupervision, 
			Long idSubtipoSupervision, String descNoSubtipoSupervision, Long idMetodoMinado, String descNoMetodoMinado, String flPropia, 
			String flConPiques, String descNoTipoSupervision, Long descIdTipoSupervision, String desNoEspecialidad,  Long desIdEspecialidad,
			Long idTipoConfiguracionBase) {
		super();
		
		this.setIdReglaBase(idReglaBase);
		this.setIdConfiguracionBase(idConfiguracionBase);
		this.setDescNoConfiguracionBase(descNoConfiguracionBase);
		this.setIdDivisionSupervisora(idDivisionSupervisora);
		this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
		this.setIdMotivoSupervision(idMotivoSupervision);
		this.setDescNoMotivoSupervision(descNoMotivoSupervision);
		this.setIdSubtipoSupervision(idSubtipoSupervision);
		this.setDescNoSubtipoSupervision(descNoSubtipoSupervision);
		this.setIdMetodoMinado(idMetodoMinado);
		this.setDescNoMetodoMinado(descNoMetodoMinado);
		this.setFlPropia(flPropia);
		this.setFlConPiques(flConPiques);
		
		this.setDescNoEspecialidad(desNoEspecialidad); 
		this.setDescIdEspecialidad(desIdEspecialidad); 
		this.setDescNoTipoSupervision(descNoTipoSupervision); 
		this.setDescIdTipoSupervision(descIdTipoSupervision); 
		this.setDescIdTipoConfiguracionBase(idTipoConfiguracionBase);

	}
}
