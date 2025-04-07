package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNivelRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmFactorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingUmGrupoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.Ranking;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimRankingRiesgo;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;

public interface RankingRiesgoService {

        /**
         * Permite listar los Ranking de riesgos
         * 
         * @param filtroRankingRiesgo
         * @param paginador
         * @return
         */
        Page<PgimRankingRiesgoDTO> filtrar(PgimRankingRiesgoDTO filtroRankingRiesgo, Pageable paginador,
                        AuditoriaDTO auditoriaDTO);

        /***
         * Permite listar por número de expediente siged
         * 
         * @param palabra Palabra clave utilizada para buscar en la lista de ranking de
         *                riesgo
         * @return
         */
        List<PgimRankingRiesgoDTO> listarPorNuExpedienteSiged(String palabra);

        /**
         * Permite obtener las propiedades necesarias del ranking de riesgo
         * 
         * @param idRankingRiesgo
         * @return
         */
        PgimRankingRiesgoDTO obtenerRankingRiesgoPorId(Long idRankingRiesgo);

        /**
         * Permite obtener la lista de configuraciones de riesgo para asignación
         * 
         * @param
         * @return
         */
        List<PgimConfiguraRiesgoDTO> obtenerConfiguracionParaAsignacion();

        /**
         * Permite asignar un ranking, usado en el dialogo Asignar Ranking de Riesgos
         * 
         * @param pgimRankingRiesgoDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimRankingRiesgoDTO asignarRanking(PgimRankingRiesgoDTO pgimRankingRiesgoDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        List<PgimRankingUmFactorDTO> listarRankingUmFactor(Long idRankingUmGrupo, Long idGrupoRiesgo);

        Ranking modificarRankingUmFactor(Ranking ranking, AuditoriaDTO auditoriaDTO);

        /**
         * Permite obtenber el listado de niveles de riesgo a partir del identificador
         * del grupo de riesgo de la configuración correspondiente.
         * 
         * @param idCfgGrupoRiesgo
         * @return
         */
        List<PgimCfgNivelRiesgoDTO> listarCfgNivelRiesgo(Long idCfgGrupoRiesgo);

        /**
         * Permite listar los niveles de riesgos maestros
         * 
         * @return
         */
        List<PgimNivelRiesgoDTO> listarNivelRiesgo();

        /***
         * Permite modificar un contrato.
         * 
         * @param pgimContratoDTO Contrato DT que porta los datos con los nuevos valores
         *                        para la actualización.
         * @param pgimContrato    Contrato a la que se actualizarán los nuevos valores
         *                        para la actualización.
         * @return
         */
        PgimRankingRiesgoDTO modificarRankingRiesgo(PgimRankingRiesgoDTO pgimRankingRiesgoDTO,
                        PgimRankingRiesgo pgimRankingRiesgo, AuditoriaDTO auditoriaDTO);

        /***
         * Permite obtener un objeto entidad del contrato con el valor idContrato.
         * 
         * @param idContrato Identificador del contrato.
         * @return
         */
        PgimRankingRiesgo getByIdRankingRiesgo(Long idRankingRiesgo);

        PgimRankingUmGrupoDTO obtenerUnidadMineraPorRankingGrupoUmAndGrupoRiesgo(Long idRankingUmGrupo,
                        Long idGrupoRiesgo);

        PgimRankingUmDTO obtenerRankingUMPorId(Long idRankingUm);

        PgimRankingUmGrupoDTO obtenerRankingUMGrupoPorId(Long idRankingUmGrupo);

        PgimRankingUmFactorDTO obtenerRankingUMFactorPorId(Long IdRankingUmFactor);

        /**
         * Permite validar la transición de un paso a otro.
         * 
         * @param pgimRelacionPaso  Objeto entidad de la relación de paso involucrada.
         * @param pgimInstanciaPaso Objeto de instancia de paso involucrada.
         */
        void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso);

        /**
         * Permite crear la data base para el registro de riesgos de la supervisión
         * @param pgimSupervisionDTO
         * @param pgimUnidadMinera
         * @param auditoriaDTO
         * @throws Exception
         */
        void crearRankingRiesgoSupervision(PgimSupervisionDTO pgimSupervisionDTO, PgimUnidadMinera pgimUnidadMinera, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite obtener la lista de grupos de factores de riesgo x supervisión
         * 
         * @param idSupervision
         * @return
         */
        List<PgimRankingUmGrupoDTO> listarRankingUmGrupoPorSupervision(Long idSupervision) throws Exception;

        /**
         * Permite obtener un registro de la tabla PGIM_TD_RANKING_SUPERVISION a través
         * de su ID
         * 
         * @param idRankingSupervision
         * @return
         */
        PgimRankingSupervisionDTO obtenerRankingSupervisionPorId(Long idRankingSupervision);

        /**
         * Permite listar los registros de la tabla PGIM_TD_RANKING_UM_FACTOR a través
         * del ID_RANKING_UM_GRUPO
         * 
         * @param idRankingSupervision
         * @return
         */
        List<PgimRankingUmFactorDTO> listarRankingUMFactorPorRankingUmGrupo(Long idRankingUmGrupo);
        
        /**
         * Permite listar los registros RANKING_UM_FACTOR por Id del RANKING_UM
         * 
         * @param idRankingUm
         * @return
         */
        List<PgimRankingUmFactorDTO> listarRankingUMFactorPorRankingUm(Long idRankingUm);

        /**
         * Permite obtener el registro de supervisión por idRankingUmGrupo
         * 
         * @param idRankingUmGrupo
         * @return
         */
        PgimSupervisionDTO obtenerSupervisionPorIdRankingUmGrupo(Long idRankingUmGrupo);

        List<PgimNivelRiesgoDTO> listarNivelRiesgoDesc();

        /**
         * Permite obtener la lista de grupos de riesgo de acuerdo con identificador del
         * grupo de riesgo de la configuración.
         * 
         * @param idRankingUmGrupo
         * @param idGrupoRiesgo
         * @return
         */
        List<PgimCfgGrupoRiesgoDTO> listarCfgGrupoRiesgo(Long idRankingUmGrupo, Long idGrupoRiesgo);

        /**
         * Permite obtener la lista de grupos de riesgo de un determinado ranking y tipo de grupo de riesgo.
         * 
         * @param idRankingUm
         * @param idGrupoRiesgo
         * @return
         */
        List<PgimRankingUmGrupoDTO> listarGruposRiesgoPorRanking(Long idRankingUm, Long idGrupoRiesgo);

        /**
         * Permite obtener la lista de grupos de riesgo de una determinada supervisión y tipo de grupo de riesgo.
         * @param idRankingSupervision
         * @param idGrupoRiesgo
         * @return
         */
        List<PgimRankingUmGrupoDTO> listarGruposRiesgoPorSupervision(Long idRankingSupervision, Long idGrupoRiesgo);

        /**
         * Permite listar los rankings de riesgos que pertenecen a la configuración
         * @param idConfiguraRiesgo
         * @param paginador
         * @param auditoriaDTO
         * @return
         */
        Page<PgimRankingRiesgoDTO> listarConfiguracionRankingRiesgo(Long idConfiguraRiesgo, Pageable paginador, AuditoriaDTO auditoriaDTO);
        
        
        /**
         * Permite crear una lista de entidades de Ranking UM. Es decir, añade una o más UM a un ranking de riesgo
         * 
         * @param lPgimRankingUmDTO Lista de objetos portadores de los datos para la creación de los ranking-UM
         * @param auditoriaDTO    Portador de meta-datos de auditoría
         * @return 
         * @throws Exception 
         */
        List<PgimRankingUmDTO> crearRankingUm(List<PgimRankingUmDTO> lPgimRankingUmDTO, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite eliminar un ranking de una supervisión
         * @param idRankingSupervision
         * @param auditoriaDTO
         */
        void eliminarRankingSupervision(Long idRankingSupervision, AuditoriaDTO auditoriaDTO);

        /**
         * Permite obtener un ranking de supervisón a partir del identificador de la supervión
         * @param idSupervision
         * @return
         */
        PgimRankingSupervisionDTO obtenerRankingSupervisionPorIdSupervision(Long idSupervision);
        
        /**
         * Permite obtener la cantidad de rankings de riesgo pendientes de atención por el usuario en sesión
         * 
         * @param auditoriaDTO
         * @return
         */
        Integer contarRankingRiesgoPendientes(AuditoriaDTO auditoriaDTO);
}
