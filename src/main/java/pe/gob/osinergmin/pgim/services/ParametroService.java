package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimParametroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadOrganicaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;

/**
 * Interfaz para la gestión de los servicios relacionados con los parámetros.
 * 
 * @descripción: Parámetro
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 */
public interface ParametroService {

    /**
     * Permite obtener una lista de valores de parámetros de acuerdo con el
     * parámetro.
     * 
     * @param nombre Nombre del parámetro del que se requiere obtener sus valores.
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreParametro(String nombre);
    
    /**
     * Permite obtener una lista de valores de parámetros por Cuadro de acuerdo con el
     * parámetro.
     * 
     * @param nombre Nombre del parámetro del que se requiere obtener sus valores.
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreParametroPorCuadro(String nombre);
    
    /**
     * Permite obtener una lista de valores de parámetros por Hecho de acuerdo con el
     * parámetro.
     * 
     * @param nombre Nombre del parámetro del que se requiere obtener sus valores.
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreParametroPorHecho(String nombre);
    

    /**
     * Permite obtener una lista de valores de parámetros de acuerdo con el
     * parámetro.
     * 
     * @param nombre           Nombre del parámetro del que se requiere obtener sus
     *                         valores.
     * @param idValorParametro IdValor del parametro requerido
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreParametro(String nombre, Long idValorParametro);

    PgimValorParametroDTO obtenerValorParametroPorID(Long idValorParametro);

    /**
     * Permite filtrar el tipo de involucrado para los representantes de AS y de los
     * trabajadores
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreTipoInvolucrado(String nombre);

    /**
     * Permite filtrar el tipo de estado de configuracion del ranking de riesgo
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreTipEstadoConfig(String nombre);

    /**
     * Permite filtrar el tipo de prefijos para los representantes de AS y de los
     * trabajadores
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreTipoPrefijo(String nombre);

    /**
     * Permite filtrar el tipo de documento de identidad de la persona natural y
     * juridica
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorTipoDocIdentidad(String nombre);

    List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadRuc(String nombre);

    List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDniCe(String nombre);

    List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDni(String nombre);

    List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadCe(String nombre);

    List<PgimEspecialidadDTO> filtrarPorNombreEspecialidad(String nombre);

    /**
     * Me permite filtrar por el nombre de tipo de denuncia
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorMedioDenuncia(String nombre);

    List<PgimEstratoDTO> filtrarPorNombreEstrato(String nombre);

    List<PgimContratoDTO> filtrarPorCodigoContrato(String nombre);

    // List<PgimSubtipoSupervisionDTO> filtrarPorNombreTipoSupervision(String
    // nombre);
    List<PgimValorParametroDTO> filtrarPorNombreTipoSupervision(String nombre);

    List<PgimValorParametroDTO> filtrarPorNombreTipoAlerta(String nombre);

    /**
     * Filtrar Por nombre de especialidad
     * 
     * @param nombre
     * @return
     */
    List<PgimPrgrmSupervisionDTO> filtrarPorNombreProgramaSupervision(String nombre);

    List<PgimContratoDTO> filtrarPorNombreContratoSupervision(String nombre);

    List<PgimFaseProcesoDTO> filtrarPorNombreFaseProceso(String nombre);

    List<PgimPasoProcesoDTO> filtrarPorNombrePasoProceso(String nombre);

    /**
     * Me permite listar por el nombre de tipo de norma
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorTipoNorma(String nombre);

    /**
     * Me permite listar por el nombre de la division de item de normas
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorDivisionItem(String nombre);

    /**
     * Permite obtener la lista de los registro de la tabla grupo riesgo
     * 
     * @return
     */
    List<PgimGrupoRiesgoDTO> filtrarPorGrupoRiesgo();

    /**
     * Me permite listar por el tipo de medidas administrativas
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreTipoMedidaAdm(String nombre);

    /**
     * Me permite listar por el tipo de objeto para las medidas administrativas
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorNombreTipoObjeto(String nombre);

    /**
     * Permite filtrar por tipo de documento de identidad de las personas
     * denunciantes que son naturales y jurídicas
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDenuncia(String nombre);

    /**
     * Me permite filtrar la lista por tipo de materia.
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorTipoMateria(String nombre);

    /**
     * Me permite filtrar la lista por tipo de reporte.
     * 
     * @param nombre
     * @return
     */
    List<PgimValorParametroDTO> filtrarPorTipoReporte(String nombre);

    List<PgimParametroDTO> listarParametros();

    List<PgimValorParametroDTO> listarValoresActivosParametro(Long idParametro);

    List<PgimValorParametroDTO> listarValoresNoActivosParametro(Long idParametro);

    PgimValorParametroDTO crearValorParametro(PgimValorParametroDTO pgimValorParametroDTO, AuditoriaDTO auditoriaDTO);

    PgimValorParametro getByIdValorParametro(Long idValorParametro);

    PgimValorParametroDTO modificarValorParametro(PgimValorParametroDTO pgimValorParametroDTO,
            PgimValorParametro pgimValorParametro, AuditoriaDTO auditoriaDTO);

    PgimValorParametroDTO obtenerValorParametroPorId(Long idValorParametro);

    void desactivarValor(PgimValorParametro pgimValorParametro, AuditoriaDTO auditoriaDTO);

    void activarValor(PgimValorParametro pgimValorParametro, AuditoriaDTO auditoriaDTO);

    Long obtenerMaximoCoClave(Long idParametro);

    /**
     * Permite obtener el identificador interno del valor de parámetro en base a su coClaveTexto
     * @param coClaveTexto
     * @return
     */
    Long obtenerIdValorParametroDesdeClaveTexto(String coClaveTexto);
    
    List<PgimValorParametroDTO> listarValoresParametro(Long idParametro);
    
    List<PgimValorParametroDTO> existeCoClaveTexto(Long idValorParametro, String coClaveTexto);

    /**
     * Me permite listar las unidades orgánicas
     * @return
     */
    List<PgimUnidadOrganicaDTO> listarUnidadOrganica();
    
    /**
     * Permite mostrar el número de cargos principales por cada personal de Osinergmin
     * @param idPersonalOsiCargo
     * @return
     */
    Integer cantidadCargoPrincipalPorPersonalOsi(Long idPersonalOsi);
    
    
    /**
     * Permite obtener una lista de valores de parámetros de acuerdo con el
     * parámetro coClaveTexto.
     * 
     * @param coClaveTexto.
     * @return
     */
    List<PgimValorParametroDTO> listarParametrosBycoClaveTexto(String parametro);
}