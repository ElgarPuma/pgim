package pe.gob.osinergmin.pgim.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCriterioSprvsionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFichaRevisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSeguimientoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAgolDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.utils.ETipoAccionCrud;

public interface SupervisionService {

        /**
         * Permite listar las supervisiones que cumplan con los criterios filtro
         * pre-establecidos.
         * 
         * @param filtroSupervision
         * @param paginador
         * @return
         */
        Page<PgimSupervisionAuxDTO> filtrar(PgimSupervisionDTO filtroSupervision, Pageable paginador,
                        AuditoriaDTO auditoriaDTO);

        public List<PgimSupervisionAuxDTO> listarFiscalizacionesParaCalendario(Date inicio, Date fin);
        
        /**
         * Permite listar las supervisiones que cumplan con los criterios filtro
         * pre-establecidos.
         * 
         * @param filtroSupervision
         * @param paginador
         * @return
         */
        Page<PgimSupervisionAuxDTO> listarSeguimientosSupervisiones(Long idContrato,
                        PgimSupervisionDTO filtroSupervision, Pageable paginador, AuditoriaDTO auditoriaDTO);

        /***
         * Permite listar las supervisiones por palabra clave.
         * 
         * @param palabra Palabra clave utilizada para buscar en la lista de
         *                supervisiones
         * @return
         */
        List<PgimSupervisionDTO> listarPorPalabraClave(String palabra);

        /**
         * 
         * @param coSupervision
         * @return
         */
        List<PgimSupervisionDTO> listarPorCoSupervision(String coSupervision);

        /***
         * Permite listar las supervisiones por palabra clave.
         * 
         * @param palabra Palabra clave utilizada para buscar en la lista de
         *                supervisiones
         * @return
         */
        List<PgimSupervisionDTO> listarPorPalabraClaveNuExpedienteSiged(String palabra);

        PgimSupervisionDTO obtenerSupervisionByIdSupervision(Long idSupervision);

        PgimSupervisionDTO obtenerAsignacionSupervisionPorId(Long idSupervision);

        PgimSupervisionDTO obtenerSupervisionPorId(Long idAgenteSupervisado) throws Exception;

        /**
         * Obtener las propiedades de la supervision por el idSupervision
         * "Dialogo"
         * 
         * @param idSupervision
         * @param idLineaPrograma
         * @return
         */
        PgimPrgrmSeguimientoAuxDTO obtenerSeguimientoPorSupervisionId(Long idSupervision, Long idLineaPrograma);

        /**
         * Obtener las propiedades de la supervision por el id de item del programa
         * "Dialogo"
         * 
         * @param idItemProgramaSupe
         * @param idLineaPrograma
         * @return
         */
        PgimPrgrmSeguimientoAuxDTO obtenerSeguimientoPorItemProgramaId(Long idItemProgramaSupe, Long idLineaPrograma);

        PgimSupervisionDTO obtenerSupervisionByidInstanciaProceso(Long idInstanciaProceso);

        PgimSupervisionDTO obtenerSupervisionPorIdSupervision(Long idAgenteSupervisado);

        /**
         * Permite asignar una supervision, usado en el dialogo Asignar supervisión
         * 
         * @param pgimSupervisionDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimSupervisionDTO asignarSupervision(PgimSupervisionDTO pgimSupervisionDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /***
         * Permite obtener la lista de supervisiones que se traslapan con la fecha de
         * supervisión de la unidad minera dada.
         * 
         * @param idUnidadMinera      Identificador de la unidad minera.
         * @param feInicioSupervision Fecha de inicio de la supervisión que se quiere
         *                            asignar.
         * @param feFinSupervision    Fecha fin de la supervisión que se quiere asignar.
         * @return
         * @throws Exception
         */
        List<PgimSupervisionDTO> listarSupervisionesTraslapadas(Long idUnidadMinera, Date feInicioSupervision,
                        Date feFinSupervision) throws Exception;

        PgimSupervision getByIdSupervision(Long idSupervision);

        PgimSupervisionDTO registrarFechasRealesSupervision(PgimSupervisionDTO pgimSupervisionDTO,
                        PgimSupervision pgimSupervision, AuditoriaDTO auditoriaDTO);

        List<PgimSupervisionDTO> listarSupervisionesFechaInicioReal(Long idSupervision, Date feInicioRealSupervision);

        PgimSupervisionDTO redefinirFechaSupervision(PgimSupervisionDTO pgimSupervisionDTO,
                        PgimSupervision pgimSupervision, AuditoriaDTO auditoriaDTO);

        /**
         * Permite validar la transición de un paso a otro.
         * 
         * @param pgimRelacionPaso  Objeto entidad de la relación de paso involucrada.
         * @param pgimInstanciaPaso Objeto de instancia de paso involucrada.
         */
        void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso);

        PgimSupervisionDTO modificarSupervisionTDR(PgimSupervisionDTO pgimSupervisionDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        PgimSupervisionDTO obtenerValoresTDR(Long idSupervision);

        PgimSupervisionDTO obtenerSupervisionPorIdAux(Long idSupervision);

        PgimSupervisionDTO obtenerValoresActaSupervision(Long idSupervision);

        PgimSupervisionDTO obtenerValoresActaInicioSupervision(Long idSupervision);

        PgimSupervisionDTO obtenerValoresFichaHechosConstatados(Long idSupervision);

        BigDecimal obtenerMontoConsumo(Long idSupervision);

        List<PgimSupervisionDTO> obtenerActasPresentadasPendienteLiquidar();

        /**
         * Permite actualizar el monto de consumo del contrato
         * 
         */
        void actualizarConsumoContrato(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite filtrar los pasos siguientes de acuerdo con la lógica de negocio de
         * la supervisión.
         * 
         * @param lPgimRelacionPasoDTOSiguientes
         * @param pgimInstanciaPasoActual
         * @param pgimRelacionPasoActual
         * @return
         */
        List<PgimRelacionPasoDTO> filtrarPasosSiguientes(List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual);

        /**
         * Permite actualizar las fechas reales de la supervisión por transición de
         * pasos.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         */
        void actualizarFechasReales(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite completar la supervisión iniciando el registro del PAS
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @throws Exception
         */
        void completarConInicioPAS(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite realizar acciones durante la transición de pasos.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @throws Exception
         */
        void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite realizar el registro o actualización de la supervisión en la BD del
         * Agol
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @throws Exception
         */
        /*
         * void registrarActualizarSupervisionAgol(PgimInstanciaProces
         * pgimInstanciaProces,
         * PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws
         * Exception;
         */

        /**
         * Permite listar las supervisiones correspondientes a un contrato cuyos montos
         * de consumo de contrato se encuentran en el estadio "pre-comprometido"
         * 
         * @param idContrato
         * @param paginador
         * @return
         */
        Page<PgimSupervisionAuxDTO> listarSupervisionesPrecomprometidasXcontrato(Long idContrato, Pageable paginador);

        /**
         * Permite obtener la ficha de revisión asociada a la liquidación del tipo
         * informe de supervisión.
         * 
         * @param idItemLiquidacion
         * @param idTipoEntregable
         * @return
         */
        PgimFichaRevisionDTO obtenerFichaRevision(Long idItemLiquidacion, Long idTipoEntregable);

        /**
         * Permite obtener el ítem de consumo asociado al ítem de la liquidación
         * 
         * @param idItemLiquidacion
         * @return
         */
        PgimItemConsumoDTO obtenerItemConsumo(Long idItemLiquidacion);

        /**
         * Permite filtrar las categorías de documentos de acuewrdo con la instyancia de
         * proceso y paso actual.
         * 
         * @param coTablaInstancia
         * @param lPgimSubcategoriaDocDTO
         * @return
         */
        List<PgimSubcategoriaDocDTO> filtrarSubCategoriasDoc(Long coTablaInstancia,
                        List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO) throws Exception;

        PgimContratoDTO obtenerContrato(Long idItemLiquidacion);

        /**
         * Permite procesar acciones adicionales para la supervisión relacionadas con la
         * gestión de documentos
         * 
         * @param pgimDocumentoDTO
         * @param idDocumento
         * @param auditoriaDTO
         */
        void procesarAccionesAdicionales(PgimDocumentoDTO pgimDocumentoDTO, Long idDocumento,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite registrar una nueva supervisión en la base de datos del aplicativo
         * Survey (Pgim)
         * 
         * @param supervisionAgol
         * 
         */
        /*
         * AddResults registrarSupervisionSurvey(SupervisionAgol supervisionNuevo)
         * throws Exception;
         */

        /**
         * Permite actualizar una supervisión existente en la base de datos del
         * aplicativo Survey (Pgim)
         * 
         * @param supervisionAgol
         * 
         */
        /*
         * UpdateResults actualizarSupervisionSurvey(SupervisionAgol supervisionAgol)
         * throws Exception;
         */

        /**
         * Permite filtrar la lista de seguimiento del programa
         * 
         * @param idProgramaSupervision
         * @param filtroPrgrmSeguimientoAuxDTO
         * @param paginador
         * @return
         */
        Page<PgimPrgrmSeguimientoAuxDTO> listarProgramaSeguimiento(Long idLineaPrograma,
                        PgimPrgrmSeguimientoAuxDTO filtroPrgrmSeguimientoAuxDTO, Pageable paginador);

        /**
         * Permite registrar la entidad supervisión Agol
         * 
         * @param pgimSupervisionAgolDTO
         * @param flActivo
         * @return
         * @throws Exception
         */
        PgimSupervisionAgolDTO insertarSupervisionAgol(PgimSupervisionAgolDTO pgimSupervisionAgolDTO, String flActivo,
                        AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de supervisiones-aux usado en reporte
         * correspondiente de manera paginada
         * 
         * @param filtroPgimSupervisionAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimSupervisionAuxDTO> listarReporteSupervisionPaginado(PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de supervisiones-aux usado en reporte
         * correspondiente
         * 
         * @param filtroPgimSupervisionAuxDTO
         * @return
         * @throws Exception
         */
        List<PgimSupervisionAuxDTO> listarReporteSupervision(PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de supervisiones-aux por anio de
         * supervisión usado en reporte correspondiente
         * 
         * @param filtroPgimSupervisionAuxDTO
         * @return
         * @throws Exception
         */
        List<PgimSupervisionAuxDTO> listarSupervisionxAnioReporte(PgimSupervisionAuxDTO filtroPgimSupervisionAuxDTO)
                        throws Exception;

        /**
         * Permite obtener la lista de entregables de la supervisión.
         * 
         * @param idSupervision
         * @return
         */
        List<PgimItemConsumoDTO> obtenerEntregablesSupervision(Long idSupervision);

        /**
         * Permite obtener datos de cabecera para el formato de revision antecedente
         * 
         * @param idSupervision
         * @return
         */
        PgimSupervisionDTO obtenerSupervisionRevisionAntecedente(Long idSupervision);
        
        /**
         * Permite obtener el listado de las fiscalizaciones que pertenece a una unidad minera
         * 
         * @param filtroSupervisionDTO
         * @param paginador
         * @return
         */
		    Page<PgimSupervisionDTO> obtenerFiscalizacionPorUnidadMineraPaginado(PgimSupervisionDTO filtroSupervisionDTO, Pageable paginador) throws Exception;

        /**
         * Permite agregar criterios a una fiscalización
         * 
         * @param listaPgimCriterioSprvsionDTO
         * @param pgimSupervisionDTO
         * @param auditoriaDTO
         *                                     return ResponseDTO
         */
        ResponseEntity<ResponseDTO> agregarCriteriosFiscalizacion(
                        PgimCriterioSprvsionDTO[] listaPgimCriterioSprvsionDTO, PgimSupervisionDTO pgimSupervisionDTO,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite generar el cuadro de verificación para una determinada fiscalización
         * a partir de la configuración base compatible
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         */
        void generarCuadroVerificacion(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite generar el caudro de verificación para una fiscalización dada,
         * siempre que esta no tenga criterios de verificación asociados.
         * 
         * @param pgimSupervisionDTO
         * @param auditoriaDTO
         */
        void generarCuadroVerificacion(PgimSupervisionDTO pgimSupervisionDTO, Long idPasoActual, AuditoriaDTO auditoriaDTO);

        /**
         * Permite asegurar si es popsible modificar el documento que está relacionado
         * con la aprobación de informes.
         * @param idDocumento
         * @param tipoAccionCrud
         */
        void asegurarConsistenciaCambiosDocumento(Long idDocumento, ETipoAccionCrud tipoAccionCrud);
        
        /**
         * Permite obtener la cantidad de supervisiones pendientes de atención por el usuario en sesión
         * 
         * @param auditoriaDTO
         * @return
         */
        Integer contarSupervPendientes(AuditoriaDTO auditoriaDTO);

		/**
		 * Permite agregar un criterios del
		 * cuadro de verificación a una fiscalización en particular
		 * 
		 * @param PgimCriterioSprvsionDTO
		 * @param pgimSupervisionDTO
		 * @param auditoriaDTO
		 * return ResponseDTO
		 */
		PgimCriterioSprvsionDTO agregarCriterioFiscalizacion(PgimCriterioSprvsionDTO pgimCriterioSprvsionDTO,
				PgimSupervisionDTO pgimSupervisionDTO, AuditoriaDTO auditoriaDTO);

        /**
         * Permite actualizar la condición de impedimento por parte del agente fiscalización sobre la ejecución de la fiscalización en campo
         * @param idSupervision
         * @param supervisionImpedida
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        ResponseEntity<ResponseDTO> actualizarImpedimentoAgenteFiscalizado(Long idSupervision,
                boolean supervisionImpedida, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite validar que los hechos verificados de incumplimiento tengan al menos una obligacion fiscalizada seleccionada
         * @param idSupervision
         * @param idRol
         * @return
         */
        Map<String, String> validarObligacionesSeccionadasPorHC(Long idSupervision, Long idRol);
}