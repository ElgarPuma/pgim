package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import gob.osinergmin.sym.remote.rest.ro.in.SymExpedienteReporteFiltersInRO;
import gob.osinergmin.sym.remote.rest.ro.in.SymInfraccionInRO;
import gob.osinergmin.sym.remote.rest.ro.out.list.ListSymExpedienteReporteOutRO;
import gob.osinergmin.sym.remote.rest.ro.out.list.ListSymInfraccionOutRO;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEspAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEvaluadorAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasEstResolAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerfaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExpPerpaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasPendientesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasdsuAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppasespeMesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppastipactiAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExppastipsustAnioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;

public interface PasService {

        /**
         * Permite listar PAS (Proceso administrativo de sanción)
         * 
         * @param filtroPas
         * @param paginador
         * @param auditoriaDTO
         * @return
         */
        Page<PgimPasAuxDTO> filtrar(PgimPasAuxDTO filtroPasaAux, Pageable paginador, AuditoriaDTO auditoriaDTO);

        /***
         * Permite listar por número de expediente siged
         * 
         * @param palabra Palabra clave utilizada para buscar en la lista de PAS
         * @return
         */
        List<PgimPasDTO> listarPorNuExpedienteSiged(String palabra);

        /**
         * Permite obtener las propiedades necesarias del PAS por su ID de instancia de paso 
         * @param idPas
         * @param idInstanciaPaso
         * @return
         */
        PgimPasDTO obtenerPasPorId(Long idPas, Long idInstanciaPaso);
        
        /**
         * Permite obtener las propiedades necesarias del PAS por su Id Pas
         * @param idPas
         * @return
         */
        PgimPasDTO obtenerPasPorIdPas(Long idPas);

        /**
         * Permite iniciar el PAS a partir de la supervisión.
         * 
         * @param idPersonalOsi
         * @param coTablaInstancia
         * @param auditoriaDTO
         * @throws Exception
         */
        void iniciarPAS(Long idPersonalOsi, Long coTablaInstancia, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Permite obtener el PAS auxiliar de acuerdo con la instancia de paso respectiva.
         * 
         * @param idInstanciaPaso
         * @return
         */
        PgimPasAuxDTO obtenerPasAuxPorIdInstanciaPaso(Long idInstanciaPaso);

        /**
         * Permite filtrar los pasos siguientes de acuerdo con la lógica de negocio de
         * la fiscalización.
         * 
         * @param lPgimRelacionPasoDTOSiguientes
         * @param pgimInstanciaPasoActual
         * @param pgimRelacionPasoActual
         * @return
         */
        List<PgimRelacionPasoDTO> filtrarPasosSiguientes(List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual);

        /**
         * Me permite filtrar por el codigp de supervision y la unidad minera
         * 
         * @param coSupervision
         * @return
         */
        List<PgimPasDTO> listarPorCoSupervision(String coSupervision);

        /**
         * Genera la url para el inicio del SYM
         * 
         * @param nroExpediente
         * @param auditoriaDTO
         * @return
         */
        String generarUrlInicioSym(String nroExpediente, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Genera la url para acceder a la pantalla "Más Información" del SYM
         * 
         * @param nroExpediente
         * @param auditoriaDTO
         * @return
         */
        String generarUrlMasInformacionSym(String nroExpediente, AuditoriaDTO auditoriaDTO) throws Exception;

        /**
         * Listar expedientes SYM
         * 
         * @param filtersInRO
         * @return
         */
        ListSymExpedienteReporteOutRO listarExpedienteSYM(SymExpedienteReporteFiltersInRO filtersInRO);

        /**
         * Listar infracciones del SYM
         * 
         * @param symInfraccionInRO
         * @return
         */
        ListSymInfraccionOutRO buscarInfraccion(SymInfraccionInRO symInfraccionInRO);

        /**
         * reportes Permite obtener los anos de PAS auxiliar
         * 
         * @return
         */
        List<String> obtenerAniosPasAux();

        /**
         * Permite obtener la lista preparada de expedientes detallados con PAS usado en
         * reporte correspondiente
         * 
         * @param filtroPgimPasAuxDTO
         * @param paginador
         * @return
         */
        Page<PgimPasAuxDTO> ListarReporteExpPasPaginado(PgimPasAuxDTO filtroPgimPasAuxDTO, Pageable paginador);

        /**
         * Permite obtener la lista preparada de expedientes con PAS por año usado en
         * reporte correspondiente
         * 
         * @param filtroPgimExppasAnioAuxDTO
         * @return
         * @throws Exception
         */
        List<PgimExppasAnioAuxDTO> listarReporteExppasAnio(PgimExppasAnioAuxDTO filtroPgimExppasAnioAuxDTO)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por año usado en
         * reporte correspondiente de manera paginada
         * 
         * @param filtroPgimExppasAnioAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExppasAnioAuxDTO> listarReporteExppasAnioPaginado(PgimExppasAnioAuxDTO filtroPgimExppasAnioAuxDTO,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por división
         * supervisora y año usado en reporte correspondiente
         * 
         * @param filtroPgimExppasdsuAnioAuxDTO
         * @return
         * @throws Exception
         */
        List<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnio(PgimExppasdsuAnioAuxDTO filtroPgimExppasdsuAnioAuxDTO)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por división
         * supervisora y año usado en reporte correspondiente de manera paginada
         * 
         * @param filtroPgimExppasdsuAnioAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExppasdsuAnioAuxDTO> listarReporteExppasdsuAnioPaginado(
                        PgimExppasdsuAnioAuxDTO filtroPgimExppasdsuAnioAuxDTO, Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por especialidad y
         * mes usado en reporte correspondiente
         * 
         * @param filtroPgimExppasespeMesAuxDTO
         * @return
         * @throws Exception
         */
        List<PgimExppasespeMesAuxDTO> listarReporteExppasespeMes(PgimExppasespeMesAuxDTO filtroPgimExppasespeMesAuxDTO)
                        throws Exception;

        Page<PgimExppasespeMesAuxDTO> listarReporteExppasespeMesPaginado(
                        PgimExppasespeMesAuxDTO filtroPgimExppasespeMesAuxDTO, Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes detallados con PAS por
         * persona asiganda usado en reporte correspondiente paginado
         * 
         * @param filtroPgimExppasPendientesAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExppasPendientesAuxDTO> listarReporteExpPersonaAsignadaPaginado(
                        PgimExppasPendientesAuxDTO filtroPgimExppasPendientesAuxDTO, Pageable paginador)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por persona
         * asignada, fase y año usado en reporte correspondiente paginado
         * 
         * @param filtroPgimExpPerfaAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExpPerfaAuxDTO> listarReporteExpPasPerfaseAnioPaginado(PgimExpPerfaAuxDTO filtroPgimExpPerfaAuxDTO,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por persona
         * asignada, paso y año usado en reporte correspondiente
         * 
         * @param filtroPgimExpPerpaAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExpPerpaAuxDTO> listarReporteExpPasPerPasoAnioPaginado(PgimExpPerpaAuxDTO filtroPgimExpPerpaAuxDTO,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por tipo de
         * sustancia de UM y año usado en reporte correspondiente de manera paginada
         * 
         * @param filtroPgimExppastipsustAnioAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExppastipsustAnioAuxDTO> listarReporteExppastipsustAnioPaginado(
                        PgimExppastipsustAnioAuxDTO filtroPgimExppastipsustAnioAuxDTO, Pageable paginador)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por especialidad y
         * año usado en reporte correspondiente
         * 
         * @param filtroPgimExppasEspAnioAuxDTO
         * @return
         * @throws Exception
         */
        Page<PgimExppasEspAnioAuxDTO> listarReporteExpPasEspecAnio(
                        PgimExppasEspAnioAuxDTO filtroPgimExppasEspAnioAuxDTO, Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS asignado a
         * evaluador por DS y especialidad usado en reporte correspondiente
         * 
         * @param filtroPgimExppasEspAnioAuxDTO
         * @return
         * @throws Exception
         */
        Page<PgimExppasEvaluadorAuxDTO> listarReporteExpPasPerDsEspecPaginado(
                        PgimExppasEvaluadorAuxDTO filtroPgimExppasEvaluadorAuxDTO, Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes detallados con PAS usado en
         * reporte correspondiente
         * 
         * @param filtroPgimPasAuxDTO
         * @param paginador
         * @return
         */
        Page<PgimPasAuxDTO> ListarReporteExpPasProcesoPaginado(PgimPasAuxDTO filtroPgimPasAuxDTO, Pageable paginador);

        /**
         * Permite obtener la lista preparada de expedientes con PAS por tipo de
         * actividad y año usado en reporte correspondiente de manera paginada
         * 
         * @param filtroPgimExppastipactiAnioAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExppastipactiAnioAuxDTO> listarReporteExppastipactiAnioPaginado(
                        PgimExppastipactiAnioAuxDTO filtroPgimExppastipactiAnioAuxDTO, Pageable paginador)
                        throws Exception;

        /**
         * Permite obtener la lista preparada de expedientes con PAS por estado de
         * resolución, ds y especialidad usado en reporte correspondiente de manera
         * paginada
         * 
         * @param filtroPgimExppasEstResolAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimExppasEstResolAuxDTO> listarReporteExppasEstResolPaginado(
                        PgimExppasEstResolAuxDTO filtroPgimExppasEstResolAuxDTO, Pageable paginador) throws Exception;

        /**
         * Permite obtener la lista preparada de documentos por expediente usado en
         * reporte correspondiente de manera paginada
         * 
         * @param filtroPgimDocumentoAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimDocumentoAuxDTO> listarReporteExpDocsPaginado(PgimDocumentoAuxDTO filtroPgimDocumentoAuxDTO,
                        Pageable paginador) throws Exception;

        
        /**
         * Permite validar la transición de un paso a otro.
         * 
         * @param pgimRelacionPaso  Objeto entidad de la relación de paso involucrada.
         * @param pgimInstanciaPaso Objeto de instancia de paso involucrada.
         */
        void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso);

        /**
         * Permite realizar las acciones requeridas dada las transiciones en el proceso
         * administrativo sancionador.
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @throws Exception
         */
        void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
                        AuditoriaDTO auditoriaDTO) throws Exception;
        
        /**
         * Permite obtener la cantidad de PAS pendientes de atención por el usuario en sesión
         * 
         * @param auditoriaDTO
         * @return
         */
        Integer contarPasPendientes(AuditoriaDTO auditoriaDTO);

        /**
         * Permite obtener el listado de las fiscalizaciones que pertenece a una unidad minera
         * 
         * @param filtroPasDTO
         * @param paginador
         * @return
         */
        Page<PgimPasAuxDTO> obtenerFiscalizacionPasPorUnidadMineraPaginado(PgimPasAuxDTO filtroPasDTO, Pageable paginador) throws Exception;
}
