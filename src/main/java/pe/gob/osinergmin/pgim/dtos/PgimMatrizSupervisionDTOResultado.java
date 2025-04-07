package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimMatrizSupervisionDTOResultado extends PgimMatrizSupervisionDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionRepository#listarMatrizSupervision()
     * 
     */
    public PgimMatrizSupervisionDTOResultado(Long idMatrizSupervision, String descNoEspecialidad,
            String deMatrizSupervision, Date feCreacion, Long idConfiguracionBase, String descNoConfiguracionBase, 
            Long idEspecialidad, String flActivo) {
        super();
        this.setIdMatrizSupervision(idMatrizSupervision);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDeMatrizSupervision(deMatrizSupervision);
        this.setFeCreacion(feCreacion);
        this.setIdConfiguracionBase(idConfiguracionBase);
        this.setDescNoConfiguracionBase(descNoConfiguracionBase);
        this.setIdEspecialidad(idEspecialidad);
        this.setFlActivo(flActivo);

    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionRepository#obtenerMatrizSupervisionPorId()
     * 
     */
    public PgimMatrizSupervisionDTOResultado(Long idMatrizSupervision, Long descIdEspecialidad, String descNoEspecialidad,
            String deMatrizSupervision, Date feCreacion, Long idConfiguracionBase, String descNoConfiguracionBase,
            Long descIdTipoConfiguracionBase, String descDeConfiguracionBase, String flActivo) {
        super();
        this.setIdMatrizSupervision(idMatrizSupervision);
        this.setDescIdEspecialidad(descIdEspecialidad);
        this.setDescNoEspecialidad(descNoEspecialidad);
        this.setDeMatrizSupervision(deMatrizSupervision);
        this.setFeCreacion(feCreacion);
        this.setIdConfiguracionBase(idConfiguracionBase);
        this.setDescNoConfiguracionBase(descNoConfiguracionBase);
        this.setDescIdTipoConfiguracionBase(descIdTipoConfiguracionBase);
        this.setDescDeConfiguracionBase(descDeConfiguracionBase);
        this.setFlActivo(flActivo);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionRepository#validarExisteCuadroVerificacion()
     * 
     */
    public PgimMatrizSupervisionDTOResultado(Long idMatrizSupervision, String deMatrizSupervision) {
        super();
        this.setIdMatrizSupervision(idMatrizSupervision);
        this.setDeMatrizSupervision(deMatrizSupervision);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.MatrizSupervisionRepository#listarMatrizSupervision()
     * @param idMatrizSupervision
     * @param deMatrizSupervision
     * @param idConfiguracionBase
     * @param feCreacion
     */
    public PgimMatrizSupervisionDTOResultado(Long idMatrizSupervision, String deMatrizSupervision, Long idConfiguracionBase, Date feCreacion) {
        super();
        this.setIdMatrizSupervision(idMatrizSupervision);
        this.setDeMatrizSupervision(deMatrizSupervision);
        this.setIdConfiguracionBase(idConfiguracionBase);
        this.setFeCreacion(feCreacion);
    }

}
