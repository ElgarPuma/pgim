package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimAlertaDetalleDTOResultado extends PgimAlertaDetalleDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AlertaDetalleRepository#obtenerAlertaDetalleByIdAlerta
	 * @see pe.gob.osinergmin.pgim.models.repository.AlertaDetalleRepository#obtenerAlertaDetalleById
	 * 
	 * @param idDetalleAlerta
	 * @param idAlerta
	 * @param idTipoDetalleAlerta
	 * @param noUsuarioDestino
	 * @param flLeido
	 * @param deDetalleAlerta
	 */
	public PgimAlertaDetalleDTOResultado(Long idDetalleAlerta, Long idAlerta, Long idTipoDetalleAlerta,
			String noUsuarioDestino, String flLeido, String deDetalleAlerta) {
		super();
		this.setIdDetalleAlerta(idDetalleAlerta);
		this.setIdAlerta(idAlerta);
		this.setIdTipoDetalleAlerta(idTipoDetalleAlerta);
		this.setNoUsuarioDestino(noUsuarioDestino);
		this.setFlLeido(flLeido);
		this.setDeDetalleAlerta(deDetalleAlerta);
	}

	/**
	 * 
	 * @see pe.gob.osinergmin.pgim.models.repository.AlertaDetalleRepository#listarAlertasByNoUsuarioDestino()
	 */
	public PgimAlertaDetalleDTOResultado(Long idDetalleAlerta, Long idAlerta, Long idTipoDetalleAlerta,
			String descNoTipoDetalleAlerta, String noUsuarioDestino, String flLeido, String deDetalleAlerta,
			String descNoAlerta, String descDeAlerta, Date descFeAlerta, String descDiEnlace,
			Long descIdTipoAlerta, String descNoTipoAlerta,
			Long descIdFaseProceso, String descNoFaseProceso,
			Long descIdPasoProceso, String descNoPasoProceso, Long descIdProceso
			) {
		super();

		this.setIdDetalleAlerta(idDetalleAlerta);
		this.setIdAlerta(idAlerta);
		this.setIdTipoDetalleAlerta(idTipoDetalleAlerta);
		this.setDescNoTipoDetalleAlerta(descNoTipoDetalleAlerta);
		this.setNoUsuarioDestino(noUsuarioDestino);
		this.setFlLeido(flLeido);
		this.setDeDetalleAlerta(deDetalleAlerta);
		this.setDescNoAlerta(descNoAlerta);
		this.setDescDeAlerta(descDeAlerta);
		this.setDescFeAlerta(descFeAlerta);
		this.setDescDiEnlace(descDiEnlace);
		this.setDescNoTipoAlerta(descNoTipoAlerta);
		this.setDescIdTipoAlerta(descIdTipoAlerta);
		this.setDescIdFaseProceso(descIdFaseProceso);
		this.setDescNoFaseProceso(descNoFaseProceso);
		this.setDescIdPasoProceso(descIdPasoProceso);
		this.setDescNoPasoProceso(descNoPasoProceso);
		this.setDescIdProceso(descIdProceso);
	}

	/**
	 * 
	 * @see pe.gob.osinergmin.pgim.models.repository.AlertaDetalleRepository#contarAlertasPendientes()
	 */
	public PgimAlertaDetalleDTOResultado(Long descCantidadAlerta) {
		super();

		this.setDescCantidadAlerta(descCantidadAlerta);
	}

	/**
	 * 
	 * @see pe.gob.osinergmin.pgim.models.repository.AlertaDetalleRepository#obtenerAlertaDetalleByInstanciaPaso(Long)
	 */
	public PgimAlertaDetalleDTOResultado(Long idDetalleAlerta, Long idAlerta, Long descIdInstanciaPaso, String flLeido, Long descIdTipoAlerta) {
		super();

		this.setIdDetalleAlerta(idDetalleAlerta);
		this.setIdAlerta(idAlerta);
		this.setDescIdInstanciaPaso(descIdInstanciaPaso);
		this.setFlLeido(flLeido);
		this.setDescIdTipoAlerta(descIdTipoAlerta);
	}

}