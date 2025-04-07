package pe.gob.osinergmin.pgim.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.PgimCarboniferaDestinoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestRecursoExtAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestinoProduccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstadisticoIncidenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExploDesarrolloAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIdentUnidMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProgramaInversionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTitularAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDesempenioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfoHechosImportAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMineralRecibPlantaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOtroIndicadorAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrincipalProyectoInversionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProdCarbonObtenidaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProduccionNoMetalicaAuxDTO;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface EstaminService {

        /**
         * Permite listar el reportes de producción obtenida utilizando paginación
         * 
         * @param filtroProduccionObtAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimProduccionObtAuxDTO> ListarProduccionObtenida(PgimProduccionObtAuxDTO filtroProduccionObtAuxDTO,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener en formato xlsx el reporte "Producción obtenida"
         * 
         * @param filtroProduccionObtAuxDTO
         * @return
         * @throws Exception
         */
        byte[] generarReporteProduccionObtenidaEXCEL(PgimProduccionObtAuxDTO filtroProduccionObtAuxDTO)
                        throws Exception;

        /**
         * Permite listar el reportes de destino de minerales externos utilizando
         * paginación
         * 
         * @param filtroDestRecursoExtAuxDTO
         * @param paginador
         * @return
         * @throws Exception
         */
        Page<PgimDestRecursoExtAuxDTO> ListarDestinoMineralesMetalicos(
                        PgimDestRecursoExtAuxDTO filtroDestRecursoExtAuxDTO,
                        Pageable paginador) throws Exception;

        /**
         * Permite obtener en formato xlsx el reporte "Destino de minerales externos"
         * 
         * @param filtroDestRecursoExtAuxDTO
         * @return
         * @throws Exception
         */
        byte[] generarReporteDestinoMineralesMetalicosEXCEL(PgimDestRecursoExtAuxDTO filtroDestRecursoExtAuxDTO)
                        throws Exception;

        
        /**
         * Permite mostrar la lista de indicadores de desempeño operativo que está paginado y contiene filtros de busqueda mediante periodos
         * @param periodoInicial
         * @param periodoFinal
         * @param paginador
         * @return
         */
        Page<PgimIndicadorDesempenioAuxDTO> listarIndicadoresDesempenioOperativo(PgimIndicadorDesempenioAuxDTO filtro, Pageable paginador);

        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteIndicadoresDesempenioOperativoEXCEL(PgimIndicadorDesempenioAuxDTO filtro) throws Exception;
        
        /**
         * Permite mostrar la lista de pruducción no metálica que está paginado y contiene filtros de busqueda mediante periodos
         * @param periodoInicial
         * @param periodoFinal
         * @param paginador
         * @return
         */
        Page<PgimProduccionNoMetalicaAuxDTO> listarProduccionNoMetalica(PgimProduccionNoMetalicaAuxDTO filtro, Pageable paginador);

        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteProduccionNoMetalicaEXCEL(PgimProduccionNoMetalicaAuxDTO filtro) throws Exception;

        /**
         * Permite mostrar la lista de otros indicadores que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @param paginador
         * @return
         */
        Page<PgimOtroIndicadorAuxDTO> listarOtrosIndicadores(PgimOtroIndicadorAuxDTO filtro, Pageable paginador);
        
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteOtrosIndicadoresEXCEL(PgimOtroIndicadorAuxDTO filtro) throws Exception;

        /**
         * Permite mostrar la lista de reporte estadisticos de incidentes que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @param paginador
         * @return
         */
        Page<PgimEstadisticoIncidenteAuxDTO> listarEstadisticosIncidente(PgimEstadisticoIncidenteAuxDTO filtro, Pageable paginador);

        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteEstadisticosIncidenteEXCEL(PgimEstadisticoIncidenteAuxDTO filtro) throws Exception;

        /**
         * Permite mostrar la lista de reporte estadisticos de incidentes que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @param paginador
         * @return
         */
        Page<PgimProgramaInversionAuxDTO> listarReporteProgramaInversiones(PgimProgramaInversionAuxDTO filtro, Pageable paginador);

        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteProgramaInversionesEXCEL(PgimProgramaInversionAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de principales proyectos de inversión que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @param paginador
         * @return
         */
        Page<PgimPrincipalProyectoInversionAuxDTO> listarReportePrincipalesProyectosInvers(PgimPrincipalProyectoInversionAuxDTO filtro, Pageable paginador);

        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReportePrincipalesProyectosInversEXCEL(PgimPrincipalProyectoInversionAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de cumplimiento del programa de exploración y desarrollo que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimExploDesarrolloAuxDTO> listarExploDesarrollo(PgimExploDesarrolloAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteExploDesarrolloEXCEL(PgimExploDesarrolloAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de identificación de concesiones y/o UEA que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimIdentUnidMineraAuxDTO> listarIdentUnidMineras(PgimIdentUnidMineraAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteIdentUnidMineraEXCEL(PgimIdentUnidMineraAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de destino de la producción que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimDestinoProduccionAuxDTO> listarDestinoProduccion(PgimDestinoProduccionAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteDestinoProduccionEXCEL(PgimDestinoProduccionAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de identificación y ubicación del titular que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimTitularAuxDTO> listarTitular(PgimTitularAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteTitularEXCEL(PgimTitularAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de mineral recibido en planta que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimMineralRecibPlantaAuxDTO> listarMineralRecibPlanta(PgimMineralRecibPlantaAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteMineralRecibPlantaEXCEL(PgimMineralRecibPlantaAuxDTO filtro) throws Exception;

        /**
         *  Permite mostrar la lista de reporte de información de hechos de importancia que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimInfoHechosImportAuxDTO> listarInfoHechosImport(PgimInfoHechosImportAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteInfoHechosImportEXCEL(PgimInfoHechosImportAuxDTO filtro) throws Exception;
     
        /**
         *  Permite mostrar la lista de reporte de producción carbonífera que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimProdCarbonObtenidaAuxDTO> listarProdCarbonObtenida(PgimProdCarbonObtenidaAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteProdCarbonObtenidaEXCEL(PgimProdCarbonObtenidaAuxDTO filtro) throws Exception;
      
        /**
         *  Permite mostrar la lista de reporte de destino de la producción carbonífera que está paginado y contiene filtros de busqueda mediante periodos
         * @param filtro
         * @return
         * @throws Exception
         */
        Page<PgimCarboniferaDestinoAuxDTO> listarCarboniferaDestino(PgimCarboniferaDestinoAuxDTO filtro, Pageable paginador);
     
        /**
         * Permite exportar en formato excel
         * @param filtro
         * @return
         * @throws Exception
         */
        byte[] generarReporteCarboniferaDestinoEXCEL(PgimCarboniferaDestinoAuxDTO filtro) throws Exception;
     
        /**
         * Agregar imagen y ademas contiene la nueva función para la optimización de la descarga de reportes masivos
         * @param workbook
         * @param sheet
         * @param base64Img
         * @param cell
         * @param row
         * @param escala
         * @param indexImg
         * @throws Exception
         */
        public void addImgExcelSXSSF(Workbook workbook, SXSSFSheet sheet, String base64Img, Integer cell, Integer row, Double escala, Integer indexImg) throws Exception;



}
