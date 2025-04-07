package pe.gob.osinergmin.pgim.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.DetalleExcepcionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionAccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.siged.ExpedienteOutRO;

/**
 * Interfaz para la gestión de los servicios relacionados con el flujo de
 * trabajo.
 * 
 * @descripción: Flujo de trabajo
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/08/2020
 */
public interface FlujoTrabajoService {

        /**
         * Permite obtener la relación inicial de paso del proceso.
         * 
         * @param idProceso Identificador interno del proceso.
         * @return
         */
        PgimRelacionPasoDTO obtenerRelacionPasoInicial(Long idProceso);

        /**
         * Permite obtener la relación de paso de acuerdo con la instancia de paso dada.
         * 
         * @param idInstanciaPaso
         * @return
         */
        PgimRelacionPasoDTO obtenerRelacionPaso(Long idInstanciaPaso);

        /**
         * Permite obtener la relación de pasos de acuerdo con el paso destino actual
         * 
         * @param idPasoProcesoActual        Identificador del paso actual en el que
         *                                   está la instancia del proceso.
         * @param pgimInstanciaPasoDTOActual
         * @return
         */
        List<PgimRelacionPasoDTO> obtenerPasosSiguientes(Long idPasoProcesoActual,
                        PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual);

        /**
         * Permite obtener la instancia del paso actual.
         * @param idInstanciaPaso
         * @return
         * @throws Exception
         */
        PgimInstanciaPasoDTO obtenerInstanciaPasoActualPorIdInstanciaPaso(Long idInstanciaPaso) throws Exception;

        /**
         * Permite obtener la instancia de paso auxiliar.
         * 
         * @param idInstanciaPaso Identificador interno del paso.
         * @return
         */
        PgimInstanPasoAuxDTO obtenerInstanciaPasoAuxPorId(Long idInstanciaPaso) throws Exception;

        /**
         * Permite obtener la lista de personas del Osinergmin asignables para una
         * supervisión.
         * 
         * @param palabra Palabra clave de búsqueda.
         * @return
         */
        List<PgimPersonaosiAuxDTO> listarPersonalOsi(String palabra);

        /**
         * Permite crear una nueva instancia de paso.
         * 
         * @param pgimInstanciaProcesActual
         * @param pgimInstanciaPasoDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimInstanciaPasoDTO crearInstanciaPasoInicial(PgimInstanciaProces pgimInstanciaProcesActual,
                        PgimInstanciaPasoDTO pgimInstanciaPasoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite asegurar la existencia del personal de Osinergmin o de un contrato
         * como miembro del equipo de la instancia del proceso.
         * 
         * @param idInstanciaProceso  Identificador interno de la instancia del proceso.
         * @param idPersonal          Identificador interno del personal, puede ser del
         *                            Osinergmin o del Contrato.
         * @param esOsinergmin        Valor lógico que señala en caso positivo que el
         *                            idPersonal se trata de personal del Osinergmin.
         * @param idRolProceso        Identificador interno del rol del proceso.
         * @param esAsignacionInicial Valor lógico que señala si el aseguramiento se
         *                            encuentra en un proceso de asignación inicial; se
         *                            espera un valor verdadero cuando se requiere se
         *                            trata del aseguramiento del destinatario de la
         *                            asignación del paso.
         * @param auditoriaDTO        Datos de autoría.
         * @return
         * @throws Exception
         */
        PgimEqpInstanciaProDTO asegurarPersonalInstanciaProceso(Long idInstanciaProceso, Long idPersonal,
                        boolean esOsinergmin, Long idRolProceso, Boolean esAsignacionInicial, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite listar el personal asignable a la instancia de proceso.
         * 
         * @param idInstanciaProceso
         * @param idContrato
         * @param idRelacionPaso
         * @param palabra
         * @return
         */
        List<PgimEqpInstanciaProDTO> listarPersonalAsignable(Long idInstanciaProceso, Long idContrato,
                        Long idRelacionPaso, String palabra);

        /**
         * Permite asginar el paso del proceso.
         * 
         * @param pgimInstanciaPasoDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimInstanciaPaso asignarPasoProceso(PgimInstanciaPasoDTO pgimInstanciaPasoDTO, String metodoPadre, AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite enviar el expedienge Siged en una transición de paso del flujo del
         * proceso.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        ExpedienteOutRO enviarExpedienteSiged(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO, boolean reasignacion)
                        throws Exception;

        /**
         * Permite validar la transición de pasos en un proceso dado.
         * 
         * @param pgimRelacionPaso     Objeto de la relación del paso.
         * @param pgimInstanciaPaso    Objeto de la instancia del paso.
         * @param pgimInstanciaPasoDTO
         */
        void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso,
                        PgimInstanciaPasoDTO pgimInstanciaPasoDTO, PgimInstanciaProces pgimInstanciaProces,
                        AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite obtener la persona específica que calce con el rol solicitado por el
         * paso implicado en la relación
         * 
         * @param idInstanciaProceso Identificador interno de la instancia del proceso.
         * @param idRelacionPaso     Identificador interno de la relación del paso.
         * @param idContrato         Identificador del contrato.
         * @return
         */
        PgimEqpInstanciaProDTO obtenerPersonaEqpPorRolDestino(Long idInstanciaProceso, Long idRelacionPaso,
                        Long idContrato);

        /**
         * Permite obtener la trazabilidad de acuerdo con la instancia de proceso.
         * 
         * @param idInstanciaProceso Identificador interno de la instancia del proceso.
         * 
         */
        Page<PgimInstanPasoAuxDTO> obtenerInstanciaPasoAuxPorInstanciaProceso(Long idInstanciaProceso,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener el código de usuario del Siged a partir del nombre de usuario
         * Windows.
         * 
         * Permite listar los nombres completos de la persona destino
         * 
         * @param palabra
         * @return
         */
        List<PgimInstanPasoAuxDTO> listarPorPersonaDestino(String palabra);

        /*
         * Permite obtener el código de usuario del Siged a partir del nombre de usuario
         * Windows.
         * 
         * @param noUsuario Nombre de usuario Windows.
         * 
         * @return
         * 
         * @throws Exception
         */
        String obtenerCoUsuarioSigedPorNombreUsuario(String noUsuario) throws Exception;

        /**
         * Permite obtener la persona de acuerdo con su nombre de usuario Windows.
         * 
         * @param noUsuario Nombre de usuario Windows.
         * @return
         * @throws Exception
         */
        PgimPersonaDTO obtenerPersonaPorNombreUsuario(String noUsuario) throws Exception;

        /**
         * Permite asegurar el interés de la persona destino de la instancia de paso
         * sobre la instancia del proceso.
         * 
         * @param idInstanciaPaso         Identificador interno de la instancia del
         *                                paso.
         * @param idPersonaEqpDestino     Identificador interno de la persona del equipo
         *                                de la instancia del proceso.
         * @param flPersDestinoInteresada Valor flag que cuando es '1' señala que la
         *                                persona sí está interesada y cuando es '0' que
         *                                no.
         * @param idInstanciaPaso
         * @param idPersonaEqpDestino
         * @param flPersDestinoInteresada
         * @param auditoriaDTO
         * @throws Exception
         */
        void asegurarInteresEnInstanciaProceso(Long idInstanciaPaso, Long idPersonaEqpDestino,
                        String flPersDestinoInteresada, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite obtener la lista de personal del equipo que está interesado en la
         * instancia del proceso.
         * 
         * @param idInstanciaProceso
         * @param noUsuario
         * @return
         * @throws Exception
         */
        List<PgimEqpInstanciaProDTO> obtenerPersonalEqpInteresado(Long idInstanciaProceso, String noUsuario)
                        throws Exception;

        /**
         * Permite verificar si la persona está interesada o no en la instancia del
         * proceso.
         * 
         * @param idInstanciaProceso
         * @param noUsuario
         * @return
         * @throws Exception
         */
        Boolean personaEstaInteresada(Long idInstanciaProceso, String noUsuario) throws Exception;

        /**
         * Permite reasignar el paso del proceso
         * 
         * @param pgimInstanciaPasoDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimInstanciaPaso reasignarPasoProceso(final PgimInstanciaPasoDTO pgimInstanciaPasoDTO,
                        final AuditoriaDTO auditoriaDTO) throws Exception;
        
        /**
         * Permite marcar una asignación (instancia de paso) como "Leído" o "No leído"
         * 
         * @param idInstanciaPaso
         * @param flLeido
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimInstanciaPaso marcarFlLecturaInstanciaPaso(Long idInstanciaPaso, String flLeido, 
        		AuditoriaDTO auditoriaDTO) throws Exception;
        
        /**
         * Permite finalizar una tarea (instancia de paso) desactivándola,
         * usado para los casos en que llegan a converger los sub-flujos paralelos en determinada tarea, 
         * momento en que no se podrá continuar con el flujo hasta que solo haya una tarea activa en cuestión.
         * 
         * @param idInstanciaPaso
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimInstanciaPaso finalizarInstanciaPaso(Long idInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite enviar las alertas correspondientes de acuerdo con el proceso.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         */
        void enviarAlerta(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite enviar la alerta de la transición del paso.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @return lNoUsuario
         */
        Map<String, String> enviarAlertaTransicionPaso(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO);

        /**
         * Permite enviar la alerta de la transición del paso para los responsables de
         * la instancia del proceso.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         */
        void enviarAlertaParaResponsables(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO, Map<String, String> lNoUsuarios);

        /**
         * Permite enviar la alerta de la transición del paso para los interesados de la
         * instancia del proceso.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @param detalleAlerta
         */
        public void enviarAlertaParaInteresados(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO,
                        Map<String, String> lNoUsuarios);

        /**
         * Permite obtener las distintas fases por las que transitó la instancia del
         * proceso.
         * 
         * @param idInstanciaProceso Identificador interno de la instancia del proceso.
         * @return
         */
        List<PgimFaseProcesoDTO> obtenerFasesInstanciaProceso(Long idInstanciaProceso);

        /**
         * Permite depurar la relación de la subcategorías por la especialidad.
         * 
         * @param lPgimRelacionSubcatDTO
         * @param idEspecialidad
         * @return
         */
        List<PgimRelacionSubcatDTO> depurarPorEspecialidad(List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO,
                        Long idEspecialidad);
        
        /**
         * Permite depurar la obligatoriedad de carga de subcategorías para casos específicos
         * 
         * @param lPgimRelacionSubcatDTO
         * @param idInstanciaProceso
         * @return
         */
        List<PgimRelacionSubcatDTO> depurarSubcatCasosEspecificos(Long idRelacionPaso, Long idInstanciaProceso, 
        		List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO);

        /**
         * Permite validar la posible existencia de alguna excepción que señala que no
         * se ha cargado un documento esperado por la transición de un flujo de trabajo
         * dado.
         * 
         * @param lPgimRelacionSubcatDTO
         * @param alMenosUno
         */
        void analizarPosibleExcepcion(List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO, boolean alMenosUno);

        /**
         * Permite obtener los datos de personal contrato a partir de su nombre de
         * usuario
         * 
         * @param noUsuario
         * @return
         */
        PgimPersonalContratoDTO obtenerPersonaContratoPorUsuario(String noUsuario);

        /**
         * Permite crear la instancia de paso final para la metodologia de riesgo
         * 
         * @param pgimInstanciaProcesActual
         * @param pgimInstanciaPasoDTO
         * @param auditoriaDTO
         * @return
         * @throws Exception
         */
        PgimInstanciaPasoDTO crearInstanciaPasoFinalConfiguraRiesgo(final PgimInstanciaProces pgimInstanciaProcesActual,
                        final PgimInstanciaPasoDTO pgimInstanciaPasoDTO, final AuditoriaDTO auditoriaDTO)
                        throws Exception;

        /**
         * Permite obtener una persona del Osinergmin de acuerdo con su nombre de usuario Windows.
         * @param nombreUsuarioWindows
         * @return
         */
        PgimPersonaosiAuxDTO obtenerPersonalOsiNombreUsuarioWindows(final String nombreUsuarioWindows);

        /**
         * Permite obtener la instancia de proceso de acuerdo con su identificador interno.
         * @param idInstanciaProceso
         * @return
         */
        PgimInstanciaProcesDTO obtenerInstanciaProceso(Long idInstanciaProceso);
        
            	
    	/**
    	 * Permite verificar la existencia de warnings para mostrarle al usuario antes de
    	 * que este confirme la asignación de paso. Ejm. documentos sin firmar
    	 * 
    	 * @param idRelacionPaso
    	 * @param idInstanciaProces
    	 * @param auditoriaDTO
    	 * @return
    	 * @throws Exception
    	 */
    	String verificarWarningsAsignacion(Long idRelacionPaso, Long idInstanciaProces, 
    			AuditoriaDTO auditoriaDTO) throws Exception;
        
        /**
         * Permite verificar la existencia de contratistas responsables asociados a las infracciones
         * @param idRelacionPaso
         * @param idInstanciaProces
         * @param auditoriaDTO
         * @return
         */
        List<PgimInfraccionAuxDTO> verificarContratistas(Long idRelacionPaso, Long idInstanciaProces, 
        AuditoriaDTO auditoriaDTO);

        /**
         * Permite mostrar las acciones que se realiza mediante la transición del paso.
         * @param idRelacionPaso
         * @return
         */
        List<PgimRelacionAccionDTO> obtenerRelacionAccion(final Long idRelacionPaso);

        /**
         * 
         */
        PgimEqpInstanciaProDTO asegurarPersonalContratoInstanciaProceso(final Long idInstanciaProceso, final Long idPersonal, final Long idProgramaSupervision, final AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite obtener el personal del Osinergmin de acuerdo con su nombre de
         * usuario windows.
         * 
         * @param noUsuario
         * @return
         * @throws Exception
         */
        PgimPersonalOsiDTO obtenerPersonaOsiPorUsuario(String noUsuario) throws Exception;
        
        /**
        * Me permite mapear el codigo objeto para los manejos de errores
        * @param idInstanciaProceso
        * @param username
        * @return
        */
        public Map<String, Object> mostrarLog(Long idInstanciaProceso, String username);
        
        /**
         * Permite mapear el codigo objeto para los manejos de errores por instancia de paso,
         * para soportar tareas paralelas (subflujo)
         * 
         * @param idInstanciaProceso
         * @param idInstanciaPaso
         * @param username
         * @return
         */
        public Map<String, Object> mostrarLogPorInstanciaPaso(Long idInstanciaProceso, Long idInstanciaPaso, String username);


        /**
		 * Permite realizar el reenvío con subflujo del expediente Siged,
		 * haciendo uso del método reenviarSubflujo del Siged-Rest-Old
		 * 
		 * @param numeroExpedienteSiged Número del expediente Siged
		 * @param noUsuarioSigedRemitente Nombre de usuario windows del remitente
		 * @param noUsuarioSigedDestinatario	Nombre de usuario windows del destinatario del flujo principal
		 * @param lUsuarioSigedDestinatarioSF	Lista de nombres de usuario windows de los destinatarios de subflujos secundarios
		 * @param asuntoAsignacion	Título del envío a través del Siged
		 * @param mensajeAsignacion Mensaje de la asignación a través del Siged
		 * @param auditoriaDTO
		 * @throws Exception
		 */
		public void reenviarSubflujoExpediente(String numeroExpedienteSiged, String noUsuarioSigedRemitente,
	        String noUsuarioSigedDestinatario, List<String> lUsuarioSigedDestinatarioSF, String asuntoAsignacion, 
	        String mensajeAsignacion, AuditoriaDTO auditoriaDTO) throws Exception;

         /**
         * Permite listar los nombres completos de la persona destino que son del contrato de acuerdo al filtro seleccionado en el reporte detallado de las fiscalizaciones
         * 
         * @param palabraClave
         * @return
         */
        public List<PgimInstanPasoAuxDTO> listarPorPersonaDestinoDelRepDetalladoFisc(Long idContrato, String palabra);

        /**
         * Permite listar los nombres completos de la persona destino del Osinergmin de acuerdo al filtro seleccionado en el reporte detallado de las fiscalizaciones
         * 
         * @param palabraClave
         * @return
         */
        public List<PgimInstanPasoAuxDTO> listarPorPersonaOsiDestinoDelRepDetalladoFisc(String palabra);
        
        /**
         * Permite complementar un objeto detalle de excepcion con información como: 
         * objeto de trabajo, nombre del método de ubicación del problema, mensaje resumen del problema, 
         * y lista de acciones posibles.  
         * 
         * @param pgimInstanciaProces
         * @param detalleExcepcionDTOPevio
         * @param metodo
         * @param mensajeInfraResumen
         * @param lstAcciones
         * @return
         */
        public DetalleExcepcionDTO obtenerDetalleExcepcion(PgimInstanciaProces pgimInstanciaProces, DetalleExcepcionDTO detalleExcepcionDTOPevio, 
    			String metodo, String mensajeInfraResumen, List<String> lstAcciones );
        
        /**
         * Permite obtener una etiqueta que identifica a un objeto de trabajo por su idInstanciaProceso
         * 
         * @param idInstanciaProceso
         * @return
         */
        String obtenerEtiquetaObjetoTrabajo(Long idInstanciaProceso);

        public List<PgimRelacionSubcatDTO> obtenerSubcategoriaDocParaFiscPropia(final Long idInstanciaProces, final List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes);

        List<PgimRelacionPasoDTO> obtenerTodosRelacionPaso();
}