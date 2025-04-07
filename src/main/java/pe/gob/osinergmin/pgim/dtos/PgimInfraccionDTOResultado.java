package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PgimInfraccionDTOResultado extends PgimInfraccionDTO {

    /**
     * Permite listar las infracciones asociadas a una obligación normativa Método
     * del repository:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#listarInfraccionXobligacionNorma()
     * 
     * @param idOblgcnNrmtvaHchoc
     * 
     */
    public PgimInfraccionDTOResultado(Long idInfraccion, Long idOblgcnNrmtvaHchoc, String flIncluirEnPas,
            String deSustento) {
        super();
        this.setIdInfraccion(idInfraccion);
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        this.setFlIncluirEnPas(flIncluirEnPas);
        this.setDeSustento(deSustento);
    }

    /**
     * Permite obtener el registro de una infracción a través del ID Método del
     * repository:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#obtenerInfraccionPorId()
     * 
     * @param idInfraccion
     * @param idOblgcnNrmtvaHchoc
     * @param flIncluirEnPas
     * @param deSustento
     * @param descIdHechoConstatado
     * @param descIdCriterioSprvsion
     * @param noItemTipificacion
     * @param descSancionUit
     * @param descCodigo
     * @param flVigente
     * @param moMultaUit
     * @param feComisionDeteccion
     */
    public PgimInfraccionDTOResultado(Long idInfraccion, Long idOblgcnNrmtvaHchoc, String flIncluirEnPas,
            String deSustento, Long descIdHechoConstatado, Long descIdCriterioSprvsion, String noItemTipificacion,
            String descSancionUit, String descCodigo, String flVigente, BigDecimal moMultaUit,
            Date feComisionDeteccion) {

        super();
        this.setIdInfraccion(idInfraccion);
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        this.setFlIncluirEnPas(flIncluirEnPas);
        this.setDeSustento(deSustento);
        this.setDescIdHechoConstatado(descIdHechoConstatado);
        this.setDescIdCriterioSprvsion(descIdCriterioSprvsion);
        this.setDescInfraccion(noItemTipificacion);
        this.setDescSancionUit(descSancionUit);
        this.setDescCodigo(descCodigo);
        this.setFlVigente(flVigente);
        this.setMoMultaUit(moMultaUit);
        this.setFeComisionDeteccion(feComisionDeteccion);
    }

    /**
     * Permite obtener el registro de una infracción a través del ID Método del
     * repository:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.InfraccionRepository#obtenerInfraccionPorIdHistorial()
     * 
     * @param idInfraccion
     * @param idOblgcnNrmtvaHchoc
     * @param flIncluirEnPas
     * @param deSustento
     * @param descIdHechoConstatado
     * @param descIdCriterioSprvsion
     * @param noItemTipificacion
     * @param descSancionUit
     * @param descCodigo
     * @param flVigente
     * @param moMultaUit
     * @param feComisionDeteccion
     * @param descNoFaseProceso
     * @param descNoRolProceso
     * @param descResponsable
     * @param descFeCreacion
     */
    public PgimInfraccionDTOResultado(Long idInfraccion, Long idOblgcnNrmtvaHchoc, String flIncluirEnPas,
            String deSustento, Long descIdHechoConstatado, Long descIdCriterioSprvsion, String noItemTipificacion,
            String descSancionUit, String descCodigo, String flVigente, BigDecimal moMultaUit, Date feComisionDeteccion,
            String descNoFaseProceso, String descNoPasoProceso, String descNoRolProceso, String descResponsable, Date descFeCreacion, String noProceso) {

        super();
        this.setIdInfraccion(idInfraccion);
        this.setIdOblgcnNrmtvaHchoc(idOblgcnNrmtvaHchoc);
        this.setFlIncluirEnPas(flIncluirEnPas);
        this.setDeSustento(deSustento);
        this.setDescIdHechoConstatado(descIdHechoConstatado);
        this.setDescIdCriterioSprvsion(descIdCriterioSprvsion);
        this.setDescInfraccion(noItemTipificacion);
        this.setDescSancionUit(descSancionUit);
        this.setDescCodigo(descCodigo);
        this.setFlVigente(flVigente);
        this.setMoMultaUit(moMultaUit);
        this.setFeComisionDeteccion(feComisionDeteccion);
        this.setDescNoFaseProceso(descNoFaseProceso);
        this.setDescNoPasoProceso(descNoPasoProceso);
        this.setDescNoRolProceso(descNoRolProceso);
        this.setDescResponsable(descResponsable);
        this.setDescFeCreacion(descFeCreacion);
        this.setDescNoProceso(noProceso);
    }

}
