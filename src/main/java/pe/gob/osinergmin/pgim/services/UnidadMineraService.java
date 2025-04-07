package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCopiaDetalleUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMtdoExplotacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubactividadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSustanciaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSustanciaUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;

/**
 * Interfaz para la gestión de los servicios relacionados con las unidades
 * mineras.
 * 
 * @descripción: Unidad minera
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 */
public interface UnidadMineraService {

     /***
      * Permite listar las unidades mineras que cumplan con los criterios filtro
      * pre-establecidos.
      * 
      * @param filtroUnidadMinera
      * @param paginador
      * @return
      */
     Page<PgimUnidadMineraDTO> filtrar(PgimUnidadMineraDTO filtroUnidadMinera, Pageable paginador);

     /***
      * Permite listar las unidades mineras que cumplan con los criterios filtro
      * pre-establecidos, se emplea en el módulo de programas.
      * 
      * @param filtroUnidadMinera
      * @param paginador
      * @return
      */
     Page<PgimUnidadMineraAuxDTO> filtrarSeleccionablesParaPrograma(PgimUnidadMineraAuxDTO filtroUnidadMinera,
               Pageable paginador);

     /***
      * Permite listar las unidades mineras de uno o mas agentes supervisados que
      * cumplan con los criterios filtro pre-establecidos.
      * 
      * @param filtroUnidadMinera
      * @param paginador
      * @param idAgenteSupervisado
      * @return
      */
     Page<PgimUnidadMineraDTO> filtrarUnidadMineraAS(Long idAgenteSupervisado, PgimUnidadMineraDTO filtroUnidadMinera,
               Pageable paginador);

     /***
      * Permite listar los agentes supervisados por palabra clave.
      * 
      * @param palabra Palabra clave utilizada para buscar en la lista de unidades
      *                mineras.
      * @return
      */
     List<PgimUnidadMineraDTO> listarPorPalabraClave(String palabra);

     /**
      * Permite listar las plantas de beneficio por palabra clave.
      * 
      * @param palabra Palabra clave utilizada para buscara en el listado de unidades
      *                mineras cuando estas sean del tipo Plantas de beneficio.
      * @return
      */
     List<PgimUnidadMineraDTO> listarPlntasPorPalabraClave(String palabra);

     /***
      * Permite obtener una lista los métodos de explotación.
      * 
      * @return
      */
     List<PgimMtdoExplotacionDTO> listarMetodosExplotacion();

     /**
      * Permite obtener la lista de sustancias.
      * 
      * @return
      */
     List<PgimSustanciaDTO> listarSustancias();

     /***
      * Permite obtener la lista de sustancias configuradas para una determinada
      * unidad minera
      * 
      * @param idUnidadMinera Identificador interno de la unidad minera.
      * @return
      */
     List<PgimSustanciaUmineraDTO> listarSustanciasUM(Long idUnidadMinera);

     /***
      * Permite obtener la unidad minera que tiene el identificador pasado como
      * parámetro.
      * 
      * @param idUnidadMinera Identificador interno de la unidad minera.
      * @return
      */
     PgimUnidadMineraDTO obtenerUnidadMinera(Long idUnidadMinera);

     /***
      * Permite obtener un objeto entidad de la unidad minera con el valor
      * idUnidadMinera.
      * 
      * @param idUnidadMinera Identificador de la unidad minera.
      * @return
      */
     PgimUnidadMinera getByIdUnidadMinera(Long idUnidadMinera);

     /***
      * Permite crear una nueva unidad minera.
      * 
      * @param pgimUnidadMineraDTO
      * @return
      */
     PgimUnidadMineraDTO crearUnidadMinera(PgimUnidadMineraDTO pgimUnidadMineraDTO, AuditoriaDTO auditoriaDTO);

     /***
      * Permite modificar una nueva unidad minera.
      * 
      * @param pgimUnidadMineraDTO Unidad minera DT que porta los datos con los
      *                            nuevos valores para la actualización.
      * @param pgimUnidadMinera    Unidad minera a la que se actualizarán los nuevos
      *                            valores para la actualización.
      * @return
      */
     PgimUnidadMineraDTO modificarUnidadMinera(PgimUnidadMineraDTO pgimUnidadMineraDTO,
               PgimUnidadMinera pgimUnidadMinera, AuditoriaDTO auditoriaDTO);

     /***
      * Permite eliminar una unidad minera dado su id.
      * 
      * @param pgimUnidadMinerActual
      */
     void eliminarUnidadMinera(PgimUnidadMinera pgimUnidadMinerActual, AuditoriaDTO auditoriaDTO);

     /***
      * Permite identificar si existe o no una unidad minera dado su id (para una
      * edición) o dado su nombre para una creación.
      * 
      * @param idUnidadMinera Identificador de la unidad minera. Con valor > 0 para
      *                       una edición.
      * @param coUnidadMinera Código de la unidad minera que se desea verificar.
      * @return
      */
     List<PgimUnidadMineraDTO> existeUnidadMinera(Long idUnidadMinera, String coUnidadMinera);

     PgimItemProgramaSupeDTO crearUnidadMineraPrograma(PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO,
               AuditoriaDTO obtenerAuditoria);
     
     /**
      * Permite listar las unidades fiscalizables de acuerdo con la palabra clave y el agente fiscalizado
      * 
      * @param palabra
      * @param idAgenteSupervisado
      * @return
      */
     List<PgimUnidadMineraDTO> listarPorPalabraClaveYAs(String palabra, Long idAgenteSupervisado);

     /**
      * Permite listar las unidades mineras de acuerdo con la palabra clave y la
      * división supervisora
      * 
      * @param palabra
      * @param idDivisionSupervisora
      * @return
      */
     List<PgimUnidadMineraDTO> listarPorPalabraClaveYDs(String palabra, Long idDivisionSupervisora);

     /***
      * Permite obtener la lista preparada de expedientes con PAS por persona
      * asignada, paso y año usado en reporte correspondiente paginado
      * 
      * @param filtroUnidadMinera
      * @param paginador
      * @return
      */
     Page<PgimUnidadMineraAuxDTO> listarReporteUMPaginado(PgimUnidadMineraAuxDTO filtroUnidadMinera,
               Pageable paginador);

     /**
      * Permite actualizar el alias de anonimización de cada una de las unidades mineras activas al momento
      * de la invocación.
      * @param obtenerAuditoria
      */
     void actualizarAliasUM(AuditoriaDTO obtenerAuditoria);

     /***
      * Permite obtener la lista preparada de el historial de cambios de la UM correspondiente paginado
      * 
      * @param filtroUnidadMinera
      * @param paginador
      * @return
      */
     Page<PgimCopiaDetalleUmDTO> listarHistoricoUMPaginado(PgimCopiaDetalleUmDTO filtroUnidadMinera,
               Pageable paginador);
     
     /**
      * Permite obtener la unidad minera a partir del codigo
      * @param coUm
      * @return
      */
     PgimUnidadMineraDTO obtenerUmPorCodigo(String coUm);

     /**
      * Permite listar las subactividades de la UM
      * @return
      */
     List<PgimSubactividadDTO> listarSubActividad();
     
     /***
      * Permite consultar la lista preparada de unidades mineras (vista PgimUnidadMineraAux) 
      * que aún no pertenecen a un rankig de riesgo determinado
      * de acuerdo con los criterios filtro y de manera paginada.
      * 
      * @param filtroUnidadMinera
      * @param paginador
      * @return
      */
     Page<PgimUnidadMineraAuxDTO> listarUmAuxDisponiblePorRankingRPaginado(PgimUnidadMineraAuxDTO filtroUnidadMinera,
               Pageable paginador);

     /**
      * Permite listar las unidades mineras asociadas por el tipo de unidad minera y la unidad minera destino como parametros
      * @param idPlntaBeneficioDestino
      * @param idTipoUnidadMinera
      * @return
      */
     List<PgimUnidadMineraDTO> listarUmAsociadas(Long idPlntaBeneficioDestino);

}
