package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimAccidentadoDTOResultado extends PgimAccidentadoDTO {

	public PgimAccidentadoDTOResultado(Long idAccidentado, String descAccidentadoDnice, String descAccidentadoNombres,
			String descAccidentadoApPaterno, String descAccidentadoApMaterno, Date feFallecimiento,
			String descEmpresaRuc, String descEmpresaRazonSocial, String descEmpresaTipo) {
		super();

		this.setIdAccidentado(idAccidentado);
		this.setDescAccidentadoDnice(descAccidentadoDnice);
		this.setDescAccidentadoNombres(descAccidentadoNombres);
		this.setDescAccidentadoApPaterno(descAccidentadoApPaterno);
		this.setDescAccidentadoApMaterno(descAccidentadoApMaterno);
		this.setFeFallecimiento(feFallecimiento);
		this.setDescEmpresaRuc(descEmpresaRuc);
		this.setDescEmpresaRazonSocial(descEmpresaRazonSocial);
		this.setDescEmpresaTipo(descEmpresaTipo);
	}

	public PgimAccidentadoDTOResultado(Long idAccidentado, Long descAccidentadoIdTipoDi, String descAccidentadoTipoDi,
			String descAccidentadoDnice, String descAccidentadoNombres, String descAccidentadoApPaterno,
			String descAccidentadoApMaterno, Date descAccidentadoFeNacimiento, String descAccidentadoDomicilio, String descAccidentadoSexo,
			Long descAccidentadoIdUbigeo, String descAccidentadoUbigeo, String descAccidentadoTelefono, Date feFallecimiento,
			Long idCategoriaOcupacional, Long idEmpresaEvento) {
		super();

		this.setIdAccidentado(idAccidentado);
		this.setDescAccidentadoIdTipoDi(descAccidentadoIdTipoDi);
		this.setDescAccidentadoTipoDi(descAccidentadoTipoDi);
		this.setDescAccidentadoDnice(descAccidentadoDnice);
		this.setDescAccidentadoNombres(descAccidentadoNombres);
		this.setDescAccidentadoApPaterno(descAccidentadoApPaterno);
		this.setDescAccidentadoApMaterno(descAccidentadoApMaterno);
		this.setDescAccidentadoFeNacimiento(descAccidentadoFeNacimiento);
		this.setDescAccidentadoDomicilio(descAccidentadoDomicilio);
		this.setDescAccidentadoSexo(descAccidentadoSexo);
		this.setDescAccidentadoIdUbigeo(descAccidentadoIdUbigeo);
		this.setDescAccidentadoUbigeo(descAccidentadoUbigeo);
		this.setDescAccidentadoTelefono(descAccidentadoTelefono);
		this.setFeFallecimiento(feFallecimiento);
		this.setIdCategoriaOcupacional(idCategoriaOcupacional);
		this.setIdEmpresaEvento(idEmpresaEvento);
		;
	}

	public PgimAccidentadoDTOResultado(Long idAccidentado, Long descAccidentadoIdTipoDi, String descAccidentadoTipoDi,
			String descAccidentadoDnice, String descAccidentadoNombres, String descAccidentadoApPaterno,
			String descAccidentadoApMaterno, Date descAccidentadoFeNacimiento, String descAccidentadoDomicilio,
			String descAccidentadoTelefono, Date feFallecimiento, Long idCategoriaOcupacional, Long idEmpresaEvento) {
		super();

		this.setIdAccidentado(idAccidentado);
		this.setDescAccidentadoIdTipoDi(descAccidentadoIdTipoDi);
		this.setDescAccidentadoTipoDi(descAccidentadoTipoDi);
		this.setDescAccidentadoDnice(descAccidentadoDnice);
		this.setDescAccidentadoNombres(descAccidentadoNombres);
		this.setDescAccidentadoApPaterno(descAccidentadoApPaterno);
		this.setDescAccidentadoApMaterno(descAccidentadoApMaterno);
		this.setDescAccidentadoFeNacimiento(descAccidentadoFeNacimiento);
		this.setDescAccidentadoDomicilio(descAccidentadoDomicilio);
		this.setDescAccidentadoTelefono(descAccidentadoTelefono);
		this.setFeFallecimiento(feFallecimiento);
		this.setIdCategoriaOcupacional(idCategoriaOcupacional);
		this.setIdEmpresaEvento(idEmpresaEvento);
		;
	}
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AccidentadoRepository#listarAccidentadoPorPersona()
	 * @param idAccidentado
	 */
	public PgimAccidentadoDTOResultado(Long idAccidentado) {
		super();
		this.setIdAccidentado(idAccidentado);
	}
}
