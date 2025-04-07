package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;

public class PgimNivelRiesgoResultadoDTO extends PgimNivelRiesgoDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.NivelRiesgoRepository#listarNivelRiesgo
     * @param idNivelRiesgo
     * @param noNivelRiesgo
     * @param deNivelRiesgo
     * @param nuOrden
     * @param nuValor
     * @param nuValorInferior
     * @param nuValorSuperior
     * @param coNivelRiesgo
     * @param coColorHexadecimal
     */
    public PgimNivelRiesgoResultadoDTO(Long idNivelRiesgo, String noNivelRiesgo, String deNivelRiesgo, Long nuOrden,
            BigDecimal nuValor, BigDecimal nuValorInferior, BigDecimal nuValorSuperior, String coNivelRiesgo,
            String coColorHexadecimal) {
        super();

        this.setIdNivelRiesgo(idNivelRiesgo);
        this.setNoNivelRiesgo(noNivelRiesgo);
        this.setDeNivelRiesgo(deNivelRiesgo);
        this.setNuOrden(nuOrden);
        this.setNuValor(nuValor);
        this.setNuValorInferior(nuValorInferior); 
        this.setNuValorSuperior(nuValorSuperior);
        this.setCoNivelRiesgo(coNivelRiesgo);
        this.setCoColorHexadecimal(coColorHexadecimal);
    }

}
