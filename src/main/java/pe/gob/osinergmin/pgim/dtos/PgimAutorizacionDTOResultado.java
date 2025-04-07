package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimAutorizacionDTOResultado extends PgimAutorizacionDTO{

	/**
	 * Este constructor sirve para obtener un registro de Autorizacion a través de su ID. 
     * En el repositorio se usa en el método:
	 * @see pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository#getAutorizacionById()
	 * 
	 * @param idAutorizacion
	 * @param idUnidadMinera
	 * @param idTipoAutorizacion
	 * @param descTipoAutorizacion
	 * @param nuDocumento
	 * @param idPersona
	 * @param descNoRazonSocial
	 * @param feInicioAutorizacion
	 * @param feFinAutorizacion
	 * @param deNota
	 * @param idInstanciaProceso
	 * @param esRegistro
	 * @param usCreacion
	 * @param ipCreacion
	 * @param feCreacion
	 * @param usActualizacion
	 * @param ipActualizacion
	 * @param feActualizacion
	 * @param descNuExpedienteSiged
	 */
	public PgimAutorizacionDTOResultado(Long idAutorizacion, Long idUnidadMinera, Long idTipoAutorizacion, String descTipoAutorizacion,
			String nuDocumento, Long idPersona, String descNoRazonSocial, Date feInicioAutorizacion, Date feFinAutorizacion,
			String deNota, Long idInstanciaProceso, String esRegistro,
			String usCreacion, String ipCreacion, Date feCreacion, String usActualizacion, String ipActualizacion,
			Date feActualizacion, String descNuExpedienteSiged) {

		super();

		this.setIdAutorizacion(idAutorizacion);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setIdTipoAutorizacion(idTipoAutorizacion);
		this.setDescTipoAutorizacion(descTipoAutorizacion);
		this.setNuDocumento(nuDocumento);
		this.setIdPersona(idPersona);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setFeInicioAutorizacion(feInicioAutorizacion);
		this.setFeFinAutorizacion(feFinAutorizacion);
		this.setDeNota(deNota);
		this.setIdInstanciaProceso(idInstanciaProceso);		
		this.setEsRegistro(esRegistro);
		this.setUsCreacion(usCreacion);
		this.setIpCreacion(ipCreacion);
		this.setFeCreacion(feCreacion);
		this.setUsActualizacion(usActualizacion);
		this.setIpActualizacion(ipActualizacion);
		this.setFeActualizacion(feActualizacion);
		this.setDescNuExpedienteSiged(descNuExpedienteSiged);

	}

    /**
     * Permite portar estoas propiedades a la lista de autorización
     * Método del repository:
     * @see pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository#listarAutorizacion()
     * 
     * @param idAutorizacion
     * @param nuDocumento
     * @param descTipoAutorizacion
     * @param descNoRazonSocial
     * @param descNoCorto
     * @param feInicioAutorizacion
     */
    public PgimAutorizacionDTOResultado(
        Long idAutorizacion
        , String nuDocumento
        , String descTipoAutorizacion
        , String descNoRazonSocial
        , String descNoCorto
        , Date feInicioAutorizacion){
        super();
        this.setIdAutorizacion(idAutorizacion);
        this.setNuDocumento(nuDocumento);
        this.setDescTipoAutorizacion(descTipoAutorizacion);
        this.setDescNoRazonSocial(descNoRazonSocial);
        this.setDescNoCorto(descNoCorto);
        this.setFeInicioAutorizacion(feInicioAutorizacion);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository#getAutorizacionPorUnidadMinera()
     * @param idAutorizacion
     * @param idUnidadMinera
     * @param idTipoAutorizacion
     * @param descTipoAutorizacion
     * @param nuDocumento
     * @param idPersona
     * @param descNoRazonSocial
     * @param feInicioAutorizacion
     * @param feFinAutorizacion
     * @param deNota
     * @param idInstanciaProceso
     */
    public PgimAutorizacionDTOResultado(Long idAutorizacion, Long idUnidadMinera, Long idTipoAutorizacion, String descTipoAutorizacion,
			String nuDocumento, Long idPersona, String descNoRazonSocial, Date feInicioAutorizacion, Date feFinAutorizacion,
			String deNota, Long idInstanciaProceso) {

		super();

		this.setIdAutorizacion(idAutorizacion);
		this.setIdUnidadMinera(idUnidadMinera);
		this.setIdTipoAutorizacion(idTipoAutorizacion);
		this.setDescTipoAutorizacion(descTipoAutorizacion);
		this.setNuDocumento(nuDocumento);
		this.setIdPersona(idPersona);
		this.setDescNoRazonSocial(descNoRazonSocial);
		this.setFeInicioAutorizacion(feInicioAutorizacion);
		this.setFeFinAutorizacion(feFinAutorizacion);
		this.setDeNota(deNota);
		this.setIdInstanciaProceso(idInstanciaProceso);		
	}
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository#listarAutorizacionPorUnidadMinera()
     * @param idAutorizacion
     */
    public PgimAutorizacionDTOResultado(Long idAutorizacion) {

		super();
		this.setIdAutorizacion(idAutorizacion);		
	}
}
