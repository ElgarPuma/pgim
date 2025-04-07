package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguracionBaseDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMatrizSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimReglaBaseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimConfiguracionBase;
import pe.gob.osinergmin.pgim.models.entity.PgimReglaBase;

public interface ConfiguracionBaseService {
	
	/**
	 * permite obtener un listado de forma pagina de las configuraciones base
	 * @param filtro
	 * @param paginador
	 * @return
	 */
    Page<PgimConfiguracionBaseDTO> listar(PgimConfiguracionBaseDTO filtro , Pageable paginador);
    
    /**
     * permite obtener una configuración base por su identificador
     * @param idConfiguracionBase
     * @return
     */
    PgimConfiguracionBaseDTO obtenerCfgBasePorId(Long idConfiguracionBase);
    
    /**
     * permite obtener una configuración base por su identificador
     * @param idConfiguracionBase
     * @return
     */
    PgimConfiguracionBase obtenerCfgBaseById(Long idConfiguracionBase);
    
    
    /**
     * permite obtener un listado de configuraciones base según el tipo de configuración
     * @param idTipoConfiguracionBase
     * @return
     */
    List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBase(Long idTipoConfiguracionBase);
    
    /**
     * Permite la creación una configuración base
     * @param pgimConfiguracionBaseDTO
     * @param auditoriaDTO
     * @return
     */
    PgimConfiguracionBaseDTO crearCfgBase(PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite la modificación una configuración base
     * @param pgimConfiguracionBaseDTO
     * @param pgimConfiguracionBase
     * @param auditoriaDTO
     * @return
     */
    PgimConfiguracionBaseDTO modificarCfgBase(PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO, PgimConfiguracionBase pgimConfiguracionBase, AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite la eliminación una configuración base
     * @param pgimConfiguracionBaseActual
     * @param auditoriaDTO
     */
    void eliminarCfgBase(PgimConfiguracionBase pgimConfiguracionBaseActual, AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite obtener un listado de reglas de una determinada configuración base
     * @param idConfiguracionBase
     * @return
     */
    List<PgimReglaBaseDTO> listarReglasPorCfgBase(Long idConfiguracionBase);
    
    /**
     * Permite obtener una regla según su identificador
     * @param idReglaBase
     * @return
     */
    PgimReglaBaseDTO obtenerReglaCfgBasePorId(Long idReglaBase);
    
    /**
     * Permite obtener una regla según su identificador
     * @param idReglaBase
     * @return
     */
    PgimReglaBase obtenerReglaCfgBaseById(Long idReglaBase);
    
    /**
     * Permite crear una regla 
     * @param pgimReglaBaseDTO
     * @param auditoriaDTO
     * @return
     */
    PgimReglaBaseDTO crearReglaCfgBase(PgimReglaBaseDTO pgimReglaBaseDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite modificar una regla
     * @param pgimReglaBaseDTO
     * @param pgimReglaBase
     * @param auditoriaDTO
     * @return
     */
    PgimReglaBaseDTO modificarReglaCfgBase(PgimReglaBaseDTO pgimReglaBaseDTO, PgimReglaBase pgimReglaBase, AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite la eliminación de una regla
     * @param pgimReglaBaseActual
     * @param auditoriaDTO
     */
    void eliminarReglaCfgBase( PgimReglaBase pgimReglaBaseActual, AuditoriaDTO auditoriaDTO);
    
    /**
     * Permite obtener las reglas que se traslapen
     * @param pgimReglaBaseDTO
     * @return
     */
    List<PgimReglaBaseDTO> listarReglasTraslapadas(PgimReglaBaseDTO pgimReglaBaseDTO);
    
    /**
     * Permite obtener las configuraciones que tienen el mismo nombre para hacer la validación de ser únicos
     * @param noConfiguracionBase
     * @param idConfiguracionBase
     * @return
     */
    List<PgimConfiguracionBaseDTO> existeCfg( String noConfiguracionBase, Long idConfiguracionBase);
    
    /**
     * Permite obtener la regla que es aplicable para una fiscalización
     * @param pgimReglaBaseDTO
     * @return
     */
    PgimReglaBaseDTO obtenerReglaPorSupervision(PgimReglaBaseDTO pgimReglaBaseDTO);

    /**
     * Permite obtener la regla que es aplicable para una fiscalización independientemente de si esta ya cuenta con un cuadro de verificación asociado.
     * @param pgimReglaBaseDTO
     * @return
     */
    List<PgimReglaBaseDTO> obtenerReglaPorSupervisionCompatible(PgimReglaBaseDTO pgimReglaBaseDTO);
    
    /**
     * permite obtener un listado de configuraciones base según el tipo de configuración
     * 
     * @param idTipoConfiguracionBase
     * @return
     */
    List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseTDR(Long idTipoConfiguracionBase);

    /**
     * permite obtener un listado de configuraciones base según el tipo de configuración
     * 
     * @param idTipoConfiguracionBase
     * @return
     */
    List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseCVERIF(Long idTipoConfiguracionBase);

    /**
     * Permite obtener un listado de configuraciones base según el tipo de configuración y la especialidad
     * @param idTipoConfiguracionBase
     * @param idEspecialidad
     * @return
     */
    List<PgimConfiguracionBaseDTO> listaCfgBasePorTipoCfgBaseYEspecialidad(Long idTipoConfiguracionBase, Long idEspecialidad);
    
    /**
     * permite obtener un listado de configuraciones base según el tipo de configuración
     * 
     * @param idTipoConfiguracionBase
     * @return
     */
    List<PgimConfiguracionBaseDTO> listaCfgBasePorIdTipoCfgBaseSOLBD(Long idTipoConfiguracionBase);

    /**
     * Permite obtener las reglas que se traslapen
     * @param pgimReglaBaseDTO
     * @return
     */
    List<PgimReglaBaseDTO> validarTraslapesReglasMatrizSupervision(PgimMatrizSupervisionDTO PgimMatrizSupervisionDTO);

    /**
     * Permite validar si la configuración base se puede eliminar
     * se puede eliminar siempre y cuando esta no este ningún vinculo con las plantillas de
     * TDR, solicitud de documentos o cuadro de verificación
     * @param PgimConfiguracionBaseDTO
     * @return
     */
    List<PgimConfiguracionBaseDTO> validarEliminacionCfgBase(PgimConfiguracionBaseDTO pgimConfiguracionBaseDTO);
}
