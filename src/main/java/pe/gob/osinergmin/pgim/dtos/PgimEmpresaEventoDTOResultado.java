package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimEmpresaEventoDTOResultado extends PgimEmpresaEventoDTO {

	/**
	 * 
	 * @param idEmpresaEvento
	 * @param descEmpresaRuc
	 * @param descEmpresaRazonSocial
	 * @param descEmpresaTipo
	 * @param nuTrabajadoresF
	 * @param nuTrabajadoresM
	 * @param idTipoEmpresaInvolucrada
	 */
	public PgimEmpresaEventoDTOResultado(Long idEmpresaEvento, String descEmpresaRuc, String descEmpresaRazonSocial,
			String descEmpresaTipo, BigDecimal nuTrabajadoresF, BigDecimal nuTrabajadoresM,
			Long idTipoEmpresaInvolucrada) {
		super();

		this.setIdEmpresaEvento(idEmpresaEvento);
		this.setDescEmpresaRuc(descEmpresaRuc);
		this.setDescEmpresaRazonSocial(descEmpresaRazonSocial);
		this.setDescEmpresaTipo(descEmpresaTipo);
		this.setNuTrabajadoresF(nuTrabajadoresF);
		this.setNuTrabajadoresM(nuTrabajadoresM);
		this.setIdTipoEmpresaInvolucrada(idTipoEmpresaInvolucrada);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.EmpresaEventoRepository#listarEmpresaEventoPorSupervisionYtipo
	 * 
	 * @param idEmpresaEvento
	 * @param idPersona
	 * @param descEmpresaRuc
	 * @param descEmpresaRazonSocial
	 */
	public PgimEmpresaEventoDTOResultado(Long idEmpresaEvento, Long idPersona, String descEmpresaRuc, 
			String descEmpresaRazonSocial) {
		super();

		this.setIdEmpresaEvento(idEmpresaEvento);
		this.setIdPersona(idPersona);
		this.setDescEmpresaRuc(descEmpresaRuc);
		this.setDescEmpresaRazonSocial(descEmpresaRazonSocial);
	}

	public PgimEmpresaEventoDTOResultado(Long idPersona, String descEmpresaRuc, String descEmpresaRazonSocial) {
		super();

		this.setIdPersona(idPersona);
		this.setDescEmpresaRuc(descEmpresaRuc);
		this.setDescEmpresaRazonSocial(descEmpresaRazonSocial);
	}

	public PgimEmpresaEventoDTOResultado(Long idEmpresaEvento, Long idPersona, String descEmpresaRuc,
			String descEmpresaRazonSocial, String descEmpresaTipo, BigDecimal nuTrabajadoresF,
			BigDecimal nuTrabajadoresM, Long idTipoActvidadCiiu, String deActEconomicaPrincipal,
			Long idTipoEmpresaInvolucrada) {
		super();

		this.setIdEmpresaEvento(idEmpresaEvento);
		this.setIdPersona(idPersona);
		this.setDescEmpresaRuc(descEmpresaRuc);
		this.setDescEmpresaRazonSocial(descEmpresaRazonSocial);
		this.setDescEmpresaTipo(descEmpresaTipo);
		this.setNuTrabajadoresF(nuTrabajadoresF);
		this.setNuTrabajadoresM(nuTrabajadoresM);
		this.setIdTipoActvidadCiiu(idTipoActvidadCiiu);
		this.setDeActEconomicaPrincipal(deActEconomicaPrincipal);
		this.setIdTipoEmpresaInvolucrada(idTipoEmpresaInvolucrada);
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.EmpresaEventoRepository#listarEmpresaEventoPorPersona
	 * @param idEmpresaEvento
	 */
	public PgimEmpresaEventoDTOResultado(Long idEmpresaEvento) {
		super();

		this.setIdEmpresaEvento(idEmpresaEvento);
	}

}
