package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimValorParametroDTOResultado extends PgimValorParametroDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository#obtenerValorParametroPorID() 
	 */
    public PgimValorParametroDTOResultado(Long idValorParametro, Long coClave, String noValorParametro,
            String deValorParametro, BigDecimal nuOrden, BigDecimal nuValorNumerico) {
        super();
        this.setIdValorParametro(idValorParametro);
        this.setCoClave(coClave);
        this.setNoValorParametro(noValorParametro);
        this.setDeValorParametro(deValorParametro);
        this.setNuOrden(nuOrden);

        this.setNuValorNumerico(nuValorNumerico);

    }

    /**
     * De prueba
     * @param idValorParametro
     * @param noValorParametro
     */
    public PgimValorParametroDTOResultado
    (
        Long idValorParametro, 
        String noValorParametro
        ) 
        {
        super();
        this.setIdValorParametro(idValorParametro);
        this.setNoValorParametro(noValorParametro);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository#filtrarPorNombreParametro()
     * @param idValorParametro
     * @param coClave
     * @param noValorParametro
     * @param deValorParametro
     * @param nuOrden
     * @param nuValorNumerico
     * @param deValorAlfanum
     */
    public PgimValorParametroDTOResultado(Long idValorParametro, Long coClave, String noValorParametro,
            String deValorParametro, BigDecimal nuOrden, BigDecimal nuValorNumerico, String deValorAlfanum, String coClaveTexto) {
        super();
        this.setIdValorParametro(idValorParametro);
        this.setCoClave(coClave);
        this.setNoValorParametro(noValorParametro);
        this.setDeValorParametro(deValorParametro);
        this.setNuOrden(nuOrden);
        this.setNuValorNumerico(nuValorNumerico);
        this.setDeValorAlfanum(deValorAlfanum);
        this.setCoClaveTexto(coClaveTexto);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository#filtrarPorNombreParametro()
     * @param idValorParametro
     * @param coClave
     * @param noValorParametro
     * @param deValorParametro
     * @param nuOrden
     * @param nuValorNumerico
     * @param deValorAlfanum
     * @param coClaveTexto
     * @param flActivo
     */
    public PgimValorParametroDTOResultado(Long idValorParametro, Long coClave, String noValorParametro,
            String deValorParametro, BigDecimal nuOrden, BigDecimal nuValorNumerico, String deValorAlfanum, String coClaveTexto, String flActivo) {
        super();
        this.setIdValorParametro(idValorParametro);
        this.setCoClave(coClave);
        this.setNoValorParametro(noValorParametro);
        this.setDeValorParametro(deValorParametro);
        this.setNuOrden(nuOrden);
        this.setNuValorNumerico(nuValorNumerico);
        this.setDeValorAlfanum(deValorAlfanum);
        this.setCoClaveTexto(coClaveTexto);
        this.setFlActivo(flActivo);
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository#filtrarPorNombreParametro()
     * @param idValorParametro
     * @param idParametro
     * @param coClave
     * @param noValorParametro
     * @param deValorParametro
     * @param nuOrden
     * @param nuValorNumerico
     * @param deValorAlfanum
     * @param coClaveTexto
     * @param flActivo
     */
    public PgimValorParametroDTOResultado(Long idValorParametro, Long idParametro, Long coClave, String noValorParametro,
            String deValorParametro, BigDecimal nuOrden, BigDecimal nuValorNumerico, String deValorAlfanum, String coClaveTexto, String flActivo) {
        super();
        this.setIdValorParametro(idValorParametro);
        this.setIdParametro(idParametro);
        this.setCoClave(coClave);
        this.setNoValorParametro(noValorParametro);
        this.setDeValorParametro(deValorParametro);
        this.setNuOrden(nuOrden);
        this.setNuValorNumerico(nuValorNumerico);
        this.setDeValorAlfanum(deValorAlfanum);
        this.setCoClaveTexto(coClaveTexto);
        this.setFlActivo(flActivo);
    }
}