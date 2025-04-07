package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimAlertaDTOResultado extends PgimAlertaDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.AlertaRepository -> listarAlerta
	 * @param idAlerta
	 * @param idTipoAlerta
	 * @param noAlerta
	 * @param deAlerta
	 * @param feAlerta
	 * @param diEnlace
	 */
    public PgimAlertaDTOResultado(Long idAlerta, Long idTipoAlerta, String descNoTipoAlerta, String noAlerta,
    		String deAlerta, Date feAlerta, String diEnlace, String descFlLeido) {
        super();
        this.setIdAlerta(idAlerta);
        this.setIdTipoAlerta(idTipoAlerta);
        this.setNoAlerta(noAlerta);
        this.setDeAlerta(deAlerta);
        this.setFeAlerta(feAlerta);
        this.setDiEnlace(diEnlace);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.AlertaRepository -> listarAlertasByNoUsuarioDestino
     * @param idAlerta
     * @param idTipoAlerta
     * @param descNoTipoAlerta
     * @param noAlerta
     * @param deAlerta
     * @param feAlerta
     * @param diEnlace
     * @param descidDetalleAlerta
     * @param descNoUsuarioDestino
     * @param descFlLeido
     * @param descDeDetalleAlerta
     * @param descFlRegistro
     */
    public PgimAlertaDTOResultado(Long idAlerta, Long idTipoAlerta, String descNoTipoAlerta, String noAlerta,
    		String deAlerta, Date feAlerta, String diEnlace, Long descidDetalleAlerta, String descNoUsuarioDestino,
    		String descFlLeido, String descDeDetalleAlerta, String descFlRegistro) {
        super();
        this.setIdAlerta(idAlerta);
        this.setIdTipoAlerta(idTipoAlerta);
        this.setNoAlerta(noAlerta);
        this.setDeAlerta(deAlerta);
        this.setFeAlerta(feAlerta);
        this.setDiEnlace(diEnlace);
    }

}