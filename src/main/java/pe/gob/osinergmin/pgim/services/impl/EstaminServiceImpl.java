package pe.gob.osinergmin.pgim.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.PgimCarboniferaDestinoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestRecursoExtAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestinoProduccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstadisticoIncidenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExploDesarrolloAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIdentUnidMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDesempenioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfoHechosImportAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMineralRecibPlantaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOtroIndicadorAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrincipalProyectoInversionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProdCarbonObtenidaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProduccionNoMetalicaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProgramaInversionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTitularAuxDTO;
import pe.gob.osinergmin.pgim.models.repository.CarboniferaDestinoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.DestRecursoExtRepository;
import pe.gob.osinergmin.pgim.models.repository.DestinoProduccionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.EstadisticoIncidenteAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ExploDesarrolloAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.IdentUnidMineraAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.IndicadorDesempenioRepository;
import pe.gob.osinergmin.pgim.models.repository.InfoHechosImportAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.MineralRecibPlantaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.OtroIndicadorAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PrincipalProyectoInversionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ProdCarbonObtenidaAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ProduccionNoMetalicaRepository;
import pe.gob.osinergmin.pgim.models.repository.ProduccionObtRepository;
import pe.gob.osinergmin.pgim.models.repository.ProgramaInversionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.TitularAuxRepository;
import pe.gob.osinergmin.pgim.services.EstaminService;
import org.springframework.data.domain.PageRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.PoiExcelUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de las entidades que esto conforma son:
 *               Reporte de producción obtenida, destino de minerales metalicos
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Service
@Transactional(readOnly = true)
public class EstaminServiceImpl implements EstaminService {

        @Autowired
        private ProduccionObtRepository produccionObtRepository;

        @Autowired
        private DestRecursoExtRepository destRecursoExtRepository;

        @Autowired
        private PropertiesConfig propertiesConfig;

        @Autowired
        private IndicadorDesempenioRepository indicadoresDesempenioRepository;

        @Autowired
        private ProduccionNoMetalicaRepository produccionNoMetalicaRepository;

        @Autowired
        private OtroIndicadorAuxRepository otrosIndicadoresAuxRepository;

        @Autowired
        private EstadisticoIncidenteAuxRepository estadisticoIncidenteAuxRepository;

        @Autowired
        private ProgramaInversionAuxRepository programaInversionAuxRepository;

        @Autowired
        private PrincipalProyectoInversionAuxRepository principalProyectoInversionAuxRepository;

        @Autowired
        private ExploDesarrolloAuxRepository exploDesarrolloAuxRepository;
        
        @Autowired
        private IdentUnidMineraAuxRepository identUnidMineraAuxRepository;
        
        @Autowired
        private DestinoProduccionAuxRepository destinoProduccionAuxRepository;
        
        @Autowired
        private TitularAuxRepository titularAuxRepository;
        
        @Autowired
        private MineralRecibPlantaAuxRepository mineralRecibPlantaAuxRepository;
        
        @Autowired
        private InfoHechosImportAuxRepository infoHechosImportAuxRepository;
        
        @Autowired
        private ProdCarbonObtenidaAuxRepository prodCarbonObtenidaAuxRepository;
        
        @Autowired
        private CarboniferaDestinoAuxRepository carboniferaDestinoAuxRepository;

        @Override
        public Page<PgimProduccionObtAuxDTO> ListarProduccionObtenida(PgimProduccionObtAuxDTO filtroProduccionObtAuxDTO,
                        Pageable paginador) throws Exception {

                Page<PgimProduccionObtAuxDTO> lPgimProduccionObtAuxDTO = this.produccionObtRepository
                                .ListarProduccionObtenida(
                                                filtroProduccionObtAuxDTO.getDescNoUnidadMinera(),
                                                filtroProduccionObtAuxDTO.getDescFeInicio(),
                                                filtroProduccionObtAuxDTO.getDescFeFin(),
                                                paginador);

                return lPgimProduccionObtAuxDTO;
        }

        @Override
        public byte[] generarReporteProduccionObtenidaEXCEL(PgimProduccionObtAuxDTO filtroProduccionObtAuxDTO)
                        throws Exception {
                List<PgimProduccionObtAuxDTO> pgimProduccionObtAuxDTO = produccionObtRepository
                                .listarReporteProduccionObtenida(
                                                filtroProduccionObtAuxDTO.getDescNoUnidadMinera(),
                                                filtroProduccionObtAuxDTO.getDescFeInicio(),
                                                filtroProduccionObtAuxDTO.getDescFeFin());

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("MV_OSIN_PRODUCCION_OBTEN");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14,
                                IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10,
                                IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtroProduccionObtAuxDTO.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtroProduccionObtAuxDTO.getDescFeInicio() + " - Periodo final: " + filtroProduccionObtAuxDTO.getDescFeFin());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("PROCESO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProceso = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrProceso);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("REGION");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRegion = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrRegion);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("PROCEDENCIA");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProcedencia = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrProcedencia);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("NOMBRE_VENDEDOR");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombreVendedor = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrNombreVendedor);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("PRODUCTO");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProducto = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrProducto);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("RATIO");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRatio = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrRatio);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdUnidadMedida = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrIdUnidadMedida);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("DESCRIPCION");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("CANTIDAD");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidad = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrCantidad);

                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("PCT_CU");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctCu = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrPctCu);

                Cell tc1Cell18 = tablaCabecera1.createCell(18);
                tc1Cell18.setCellValue("PCT_PB");
                tc1Cell18.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctPb = new CellRangeAddress(7, 8, 18, 18);
                sheet.addMergedRegion(mrPctPb);

                Cell tc1Cell19 = tablaCabecera1.createCell(19);
                tc1Cell19.setCellValue("PCT_ZN");
                tc1Cell19.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctZn = new CellRangeAddress(7, 8, 19, 19);
                sheet.addMergedRegion(mrPctZn);

                Cell tc1Cell20 = tablaCabecera1.createCell(20);
                tc1Cell20.setCellValue("AG_OZ_TC");
                tc1Cell20.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAgOzTc = new CellRangeAddress(7, 8, 20, 20);
                sheet.addMergedRegion(mrAgOzTc);

                Cell tc1Cell21 = tablaCabecera1.createCell(21);
                tc1Cell21.setCellValue("AU_GR_TM");
                tc1Cell21.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAuGrTm = new CellRangeAddress(7, 8, 21, 21);
                sheet.addMergedRegion(mrAuGrTm);

                Cell tc1Cell22 = tablaCabecera1.createCell(22);
                tc1Cell22.setCellValue("PCT_FE");
                tc1Cell22.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctFe = new CellRangeAddress(7, 8, 22, 22);
                sheet.addMergedRegion(mrPctFe);

                Cell tc1Cell23 = tablaCabecera1.createCell(23);
                tc1Cell23.setCellValue("PCT_MO");
                tc1Cell23.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMo = new CellRangeAddress(7, 8, 23, 23);
                sheet.addMergedRegion(mrPctMo);

                Cell tc1Cell24 = tablaCabecera1.createCell(24);
                tc1Cell24.setCellValue("PCT_SN");
                tc1Cell24.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSn = new CellRangeAddress(7, 8, 24, 24);
                sheet.addMergedRegion(mrPctSn);

                Cell tc1Cell25 = tablaCabecera1.createCell(25);
                tc1Cell25.setCellValue("PCT_CD");
                tc1Cell25.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctCd = new CellRangeAddress(7, 8, 25, 25);
                sheet.addMergedRegion(mrPctCd);

                Cell tc1Cell26 = tablaCabecera1.createCell(26);
                tc1Cell26.setCellValue("PCT_WO3");
                tc1Cell26.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctWo3 = new CellRangeAddress(7, 8, 26, 26);
                sheet.addMergedRegion(mrPctWo3);

                Cell tc1Cell27 = tablaCabecera1.createCell(27);
                tc1Cell27.setCellValue("PCT_SB");
                tc1Cell27.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSb = new CellRangeAddress(7, 8, 27, 27);
                sheet.addMergedRegion(mrPctSb);

                Cell tc1Cell28 = tablaCabecera1.createCell(28);
                tc1Cell28.setCellValue("PCT_AS");
                tc1Cell28.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctAs = new CellRangeAddress(7, 8, 28, 28);
                sheet.addMergedRegion(mrPctAs);

                Cell tc1Cell29 = tablaCabecera1.createCell(29);
                tc1Cell29.setCellValue("PCT_MN");
                tc1Cell29.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMn = new CellRangeAddress(7, 8, 29, 29);
                sheet.addMergedRegion(mrPctMn);

                Cell tc1Cell30 = tablaCabecera1.createCell(30);
                tc1Cell30.setCellValue("PCT_BI");
                tc1Cell30.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctBi = new CellRangeAddress(7, 8, 30, 30);
                sheet.addMergedRegion(mrPctBi);

                Cell tc1Cell31 = tablaCabecera1.createCell(31);
                tc1Cell31.setCellValue("PCT_HG");
                tc1Cell31.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctHg = new CellRangeAddress(7, 8, 31, 31);
                sheet.addMergedRegion(mrPctHg);

                Cell tc1Cell32 = tablaCabecera1.createCell(32);
                tc1Cell32.setCellValue("PCT_IN");
                tc1Cell32.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctIn = new CellRangeAddress(7, 8, 32, 32);
                sheet.addMergedRegion(mrPctIn);

                Cell tc1Cell33 = tablaCabecera1.createCell(33);
                tc1Cell33.setCellValue("PCT_SE");
                tc1Cell33.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSe = new CellRangeAddress(7, 8, 33, 33);
                sheet.addMergedRegion(mrPctSe);

                Cell tc1Cell34 = tablaCabecera1.createCell(34);
                tc1Cell34.setCellValue("PCT_TE");
                tc1Cell34.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctTe = new CellRangeAddress(7, 8, 34, 34);
                sheet.addMergedRegion(mrPctTe);

                Cell tc1Cell35 = tablaCabecera1.createCell(35);
                tc1Cell35.setCellValue("H2SO4");
                tc1Cell35.setCellStyle(tableHeaderCS);
                CellRangeAddress mrH2So4 = new CellRangeAddress(7, 8, 35, 35);
                sheet.addMergedRegion(mrH2So4);

                Cell tc1Cell36 = tablaCabecera1.createCell(36);
                tc1Cell36.setCellValue("PCT_U");
                tc1Cell36.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctU = new CellRangeAddress(7, 8, 36, 36);
                sheet.addMergedRegion(mrPctU);

                Cell tc1Cell37 = tablaCabecera1.createCell(37);
                tc1Cell37.setCellValue("PCT_NI");
                tc1Cell37.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctNi = new CellRangeAddress(7, 8, 37, 37);
                sheet.addMergedRegion(mrPctNi);

                Cell tc1Cell38 = tablaCabecera1.createCell(38);
                tc1Cell38.setCellValue("PCT_MG");
                tc1Cell38.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMg = new CellRangeAddress(7, 8, 38, 38);
                sheet.addMergedRegion(mrPctMg);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrProceso, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrRegion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrProcedencia, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrNombreVendedor, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrProducto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrRatio, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrIdUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrCantidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctCu, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctPb, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctZn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrAgOzTc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrAuGrTm, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctFe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctMo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctSn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctCd, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctWo3, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctSb, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctAs, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctMn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctBi, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctHg, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctIn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctSe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctTe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrH2So4, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctU, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctNi, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctMg, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimProduccionObtAuxDTO obj : pgimProduccionObtAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getProceso());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getRegion());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getProcedencia());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getNombreVendedor());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getProducto());
                        cell12.setCellStyle(tableBodyCS);

                        if (obj.getRatio() == null) {
                                obj.setRatio(new BigDecimal(0));
                        }
                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getRatio().doubleValue());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getIdUnidadMedida());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getDescripcion());
                        cell15.setCellStyle(tableBodyCS);

                        if (obj.getCantidad() == null) {
                                obj.setCantidad(new BigDecimal(0));
                        }
                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getCantidad().doubleValue());
                        cell16.setCellStyle(tableBodyCS);

                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getPctCu());
                        cell17.setCellStyle(tableBodyCS);

                        Cell cell18 = row.createCell(18);
                        cell18.setCellValue(obj.getPctPb());
                        cell18.setCellStyle(tableBodyCS);

                        Cell cell19 = row.createCell(19);
                        cell19.setCellValue(obj.getPctZn());
                        cell19.setCellStyle(tableBodyCS);

                        Cell cell20 = row.createCell(20);
                        cell20.setCellValue(obj.getAgOzTc());
                        cell20.setCellStyle(tableBodyCS);

                        Cell cell21 = row.createCell(21);
                        cell21.setCellValue(obj.getAuGrTm());
                        cell21.setCellStyle(tableBodyCS);

                        Cell cell22 = row.createCell(22);
                        cell22.setCellValue(obj.getPctFe());
                        cell22.setCellStyle(tableBodyCS);

                        Cell cell23 = row.createCell(23);
                        cell23.setCellValue(obj.getPctMo());
                        cell23.setCellStyle(tableBodyCS);

                        Cell cell24 = row.createCell(24);
                        cell24.setCellValue(obj.getPctSn());
                        cell24.setCellStyle(tableBodyCS);

                        Cell cell25 = row.createCell(25);
                        cell25.setCellValue(obj.getPctCd());
                        cell25.setCellStyle(tableBodyCS);

                        Cell cell26 = row.createCell(26);
                        cell26.setCellValue(obj.getPctWo3());
                        cell26.setCellStyle(tableBodyCS);

                        Cell cell27 = row.createCell(27);
                        cell27.setCellValue(obj.getPctSb());
                        cell27.setCellStyle(tableBodyCS);

                        Cell cell28 = row.createCell(28);
                        cell28.setCellValue(obj.getPctAs());
                        cell28.setCellStyle(tableBodyCS);

                        Cell cell29 = row.createCell(29);
                        cell29.setCellValue(obj.getPctMn());
                        cell29.setCellStyle(tableBodyCS);

                        Cell cell30 = row.createCell(30);
                        cell30.setCellValue(obj.getPctBi());
                        cell30.setCellStyle(tableBodyCS);

                        Cell cell31 = row.createCell(31);
                        cell31.setCellValue(obj.getPcHg());
                        cell31.setCellStyle(tableBodyCS);

                        Cell cell32 = row.createCell(32);
                        cell32.setCellValue(obj.getPctIn());
                        cell32.setCellStyle(tableBodyCS);

                        Cell cell33 = row.createCell(33);
                        cell33.setCellValue(obj.getPctSe());
                        cell33.setCellStyle(tableBodyCS);

                        Cell cell34 = row.createCell(34);
                        cell34.setCellValue(obj.getPctTe());
                        cell34.setCellStyle(tableBodyCS);

                        Cell cell35 = row.createCell(35);
                        cell35.setCellValue(obj.getH2so4());
                        cell35.setCellStyle(tableBodyCS);

                        Cell cell36 = row.createCell(36);
                        cell36.setCellValue(obj.getPctu());
                        cell36.setCellStyle(tableBodyCS);

                        Cell cell37 = row.createCell(37);
                        cell37.setCellValue(obj.getPctNi());
                        cell37.setCellStyle(tableBodyCS);

                        Cell cell38 = row.createCell(38);
                        cell38.setCellValue(obj.getPctMg());
                        cell38.setCellStyle(tableBodyCS);
                }

                String[] columns = new String[38];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.autoSizeColumn(0, true);
                sheet.autoSizeColumn(1, true);
                sheet.autoSizeColumn(2, true);
                sheet.autoSizeColumn(3, true);
                sheet.autoSizeColumn(4, true);
                sheet.autoSizeColumn(5, true);
                sheet.autoSizeColumn(6, true);
                sheet.autoSizeColumn(7, true);
                sheet.autoSizeColumn(8, true);
                sheet.autoSizeColumn(9, true);
                sheet.autoSizeColumn(10, true);
                sheet.autoSizeColumn(11, true);
                sheet.autoSizeColumn(12, true);
                sheet.autoSizeColumn(13, true);
                sheet.autoSizeColumn(14, true);
                sheet.autoSizeColumn(15, true);
                sheet.autoSizeColumn(16, true);
                sheet.autoSizeColumn(17, true);
                sheet.autoSizeColumn(18, true);
                sheet.autoSizeColumn(19, true);
                sheet.autoSizeColumn(20, true);
                sheet.autoSizeColumn(21, true);
                sheet.autoSizeColumn(22, true);
                sheet.autoSizeColumn(23, true);
                sheet.autoSizeColumn(24, true);
                sheet.autoSizeColumn(25, true);
                sheet.autoSizeColumn(26, true);
                sheet.autoSizeColumn(27, true);
                sheet.autoSizeColumn(28, true);
                sheet.autoSizeColumn(29, true);
                sheet.autoSizeColumn(30, true);
                sheet.autoSizeColumn(31, true);
                sheet.autoSizeColumn(32, true);
                sheet.autoSizeColumn(33, true);
                sheet.autoSizeColumn(34, true);
                sheet.autoSizeColumn(35, true);
                sheet.autoSizeColumn(36, true);
                sheet.autoSizeColumn(37, true);
                sheet.autoSizeColumn(38, true);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcel(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                //Cerrar el libro de trabajo
                workbook.close();

                return archivo;
        }

        public void addImgExcel(Workbook workbook, Sheet sheet, String base64Img, Integer cell, Integer row,
                        Double escala,
                        Integer indexImg) throws Exception {

                CreationHelper helper = workbook.getCreationHelper();

                // Insertar imagen
                byte[] bytesImg = java.util.Base64.getDecoder().decode(base64Img);

                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytesImg));
                int imgWidth = img.getWidth();
                int rangeCellImg = 0;
                int widthColumnDefault = sheet.getDefaultColumnWidth();

                if (indexImg > 1) {
                        rangeCellImg = (int) ((imgWidth * escala)
                                        / (widthColumnDefault * Units.DEFAULT_CHARACTER_WIDTH));
                }

                cell += rangeCellImg;

                // Adds a picture to the workbook
                int pictureIdx = workbook.addPicture(bytesImg, Workbook.PICTURE_TYPE_PNG);

                // Creates the top-level drawing patriarch.
                Drawing drawing = sheet.createDrawingPatriarch();

                // Create an anchor that is attached to the worksheet
                ClientAnchor anchor = helper.createClientAnchor();
                anchor.setAnchorType(AnchorType.MOVE_DONT_RESIZE);
                // set top-left corner for the image
                anchor.setCol1(cell);
                anchor.setRow1(row);

                // Creates a picture
                Picture pict = drawing.createPicture(anchor, pictureIdx);
                // Reset the image to the original size
                pict.resize(escala);
                // Fin Insertar imagen
        }

        @Override
        public Page<PgimDestRecursoExtAuxDTO> ListarDestinoMineralesMetalicos(
                        PgimDestRecursoExtAuxDTO filtroDestRecursoExtAuxDTO, Pageable paginador) throws Exception {

                Page<PgimDestRecursoExtAuxDTO> lPgimDestRecursoExtAuxDTO = this.destRecursoExtRepository
                                .ListarDestinoMineralesMetalicos(filtroDestRecursoExtAuxDTO.getDescNoUnidadMinera(), filtroDestRecursoExtAuxDTO.getDescFeInicio(),
                                                filtroDestRecursoExtAuxDTO.getDescFeFin(), paginador);

                return lPgimDestRecursoExtAuxDTO;
        }

        @Override
        public byte[] generarReporteDestinoMineralesMetalicosEXCEL(PgimDestRecursoExtAuxDTO filtroDestRecursoExtAuxDTO)
                        throws Exception {
                List<PgimDestRecursoExtAuxDTO> pgimDestRecursoExtAuxDTO = destRecursoExtRepository
                                .listarReporteDestinoMineralesMetalicos(
                                                filtroDestRecursoExtAuxDTO.getDescNoUnidadMinera(),
                                                filtroDestRecursoExtAuxDTO.getDescFeInicio(),
                                                filtroDestRecursoExtAuxDTO.getDescFeFin());

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("MV_OSIN_DEST_RECURSO_EXT");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14,
                                IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10,
                                IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtroDestRecursoExtAuxDTO.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtroDestRecursoExtAuxDTO.getDescFeInicio() + " - Periodo final: " + filtroDestRecursoExtAuxDTO.getDescFeFin());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("RECURSO_EXTRAIDO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRecursoExtraido = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrRecursoExtraido);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("DESTINO");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDestino = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrDestino);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("CODIGO_PLANTA");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigoPlanta = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrCodigoPlanta);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("NOMBRE_PLANTA");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombrePlanta = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrNombrePlanta);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("PAIS");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPais = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrPais);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidadMedida = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrUnidadMedida);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("DESCRIPCION");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("CANTIDAD_TM");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadTm = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrCantidadTm);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("PCT_CU");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctCu = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrPctCu);

                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("PCT_PB");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctPb = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrPctPb);

                Cell tc1Cell18 = tablaCabecera1.createCell(18);
                tc1Cell18.setCellValue("PCT_ZN");
                tc1Cell18.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctZn = new CellRangeAddress(7, 8, 18, 18);
                sheet.addMergedRegion(mrPctZn);

                Cell tc1Cell19 = tablaCabecera1.createCell(19);
                tc1Cell19.setCellValue("AG_OZ_TC");
                tc1Cell19.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAgOzTc = new CellRangeAddress(7, 8, 19, 19);
                sheet.addMergedRegion(mrAgOzTc);

                Cell tc1Cell20 = tablaCabecera1.createCell(20);
                tc1Cell20.setCellValue("AU_GR_TM");
                tc1Cell20.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAuGrTm = new CellRangeAddress(7, 8, 20, 20);
                sheet.addMergedRegion(mrAuGrTm);

                Cell tc1Cell21 = tablaCabecera1.createCell(21);
                tc1Cell21.setCellValue("PCT_FE");
                tc1Cell21.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctFe = new CellRangeAddress(7, 8, 21, 21);
                sheet.addMergedRegion(mrPctFe);

                Cell tc1Cell22 = tablaCabecera1.createCell(22);
                tc1Cell22.setCellValue("PCT_MO");
                tc1Cell22.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMo = new CellRangeAddress(7, 8, 22, 22);
                sheet.addMergedRegion(mrPctMo);

                Cell tc1Cell23 = tablaCabecera1.createCell(23);
                tc1Cell23.setCellValue("PCT_SN");
                tc1Cell23.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSn = new CellRangeAddress(7, 8, 23, 23);
                sheet.addMergedRegion(mrPctSn);

                Cell tc1Cell24 = tablaCabecera1.createCell(24);
                tc1Cell24.setCellValue("PCT_CD");
                tc1Cell24.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctCd = new CellRangeAddress(7, 8, 24, 24);
                sheet.addMergedRegion(mrPctCd);

                Cell tc1Cell25 = tablaCabecera1.createCell(25);
                tc1Cell25.setCellValue("PCT_WO3");
                tc1Cell25.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctWo3 = new CellRangeAddress(7, 8, 25, 25);
                sheet.addMergedRegion(mrPctWo3);

                Cell tc1Cell26 = tablaCabecera1.createCell(26);
                tc1Cell26.setCellValue("PCT_SB");
                tc1Cell26.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSb = new CellRangeAddress(7, 8, 26, 26);
                sheet.addMergedRegion(mrPctSb);

                Cell tc1Cell27 = tablaCabecera1.createCell(27);
                tc1Cell27.setCellValue("PCT_AS");
                tc1Cell27.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctAs = new CellRangeAddress(7, 8, 27, 27);
                sheet.addMergedRegion(mrPctAs);

                Cell tc1Cell28 = tablaCabecera1.createCell(28);
                tc1Cell28.setCellValue("PCT_MN");
                tc1Cell28.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMn = new CellRangeAddress(7, 8, 28, 28);
                sheet.addMergedRegion(mrPctMn);

                Cell tc1Cell29 = tablaCabecera1.createCell(29);
                tc1Cell29.setCellValue("PCT_BI");
                tc1Cell29.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctBi = new CellRangeAddress(7, 8, 29, 29);
                sheet.addMergedRegion(mrPctBi);

                Cell tc1Cell30 = tablaCabecera1.createCell(30);
                tc1Cell30.setCellValue("PCT_HG");
                tc1Cell30.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctHg = new CellRangeAddress(7, 8, 30, 30);
                sheet.addMergedRegion(mrPctHg);

                Cell tc1Cell31 = tablaCabecera1.createCell(31);
                tc1Cell31.setCellValue("PCT_IN");
                tc1Cell31.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctIn = new CellRangeAddress(7, 8, 31, 31);
                sheet.addMergedRegion(mrPctIn);

                Cell tc1Cell32 = tablaCabecera1.createCell(32);
                tc1Cell32.setCellValue("PCT_SE");
                tc1Cell32.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSe = new CellRangeAddress(7, 8, 32, 32);
                sheet.addMergedRegion(mrPctSe);

                Cell tc1Cell33 = tablaCabecera1.createCell(33);
                tc1Cell33.setCellValue("PCT_TE");
                tc1Cell33.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctTe = new CellRangeAddress(7, 8, 33, 33);
                sheet.addMergedRegion(mrPctTe);

                Cell tc1Cell34 = tablaCabecera1.createCell(34);
                tc1Cell34.setCellValue("H2SO4");
                tc1Cell34.setCellStyle(tableHeaderCS);
                CellRangeAddress mrH2So4 = new CellRangeAddress(7, 8, 34, 34);
                sheet.addMergedRegion(mrH2So4);

                Cell tc1Cell35 = tablaCabecera1.createCell(35);
                tc1Cell35.setCellValue("PCT_U");
                tc1Cell35.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctU = new CellRangeAddress(7, 8, 35, 35);
                sheet.addMergedRegion(mrPctU);

                Cell tc1Cell36 = tablaCabecera1.createCell(36);
                tc1Cell36.setCellValue("PCT_NI");
                tc1Cell36.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctNi = new CellRangeAddress(7, 8, 36, 36);
                sheet.addMergedRegion(mrPctNi);

                Cell tc1Cell37 = tablaCabecera1.createCell(37);
                tc1Cell37.setCellValue("PCT_MG");
                tc1Cell37.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMg = new CellRangeAddress(7, 8, 37, 37);
                sheet.addMergedRegion(mrPctMg);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrRecursoExtraido, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrDestino, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrCodigoPlanta, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrNombrePlanta, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPais, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrCantidadTm, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctCu, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctPb, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctZn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrAgOzTc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrAuGrTm, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctFe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctMo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctSn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctCd, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctWo3, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctSb, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctAs, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctMn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctBi, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctHg, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctIn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctSe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctTe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrH2So4, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctU, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctNi, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCell(BorderStyle.THIN, mrPctMg, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimDestRecursoExtAuxDTO obj : pgimDestRecursoExtAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getRecursoExtraido());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getDestino());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getCodigoPlanta());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getNombrePlanta());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getPais());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getIdUnidadMedida());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getDescripcion());
                        cell14.setCellStyle(tableBodyCS);

                        if (obj.getCantidadTm() == null) {
                                obj.setCantidadTm(new BigDecimal(0));
                        }
                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getCantidadTm().doubleValue());
                        cell15.setCellStyle(tableBodyCS);

                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getPctCu());
                        cell16.setCellStyle(tableBodyCS);

                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getPctPb());
                        cell17.setCellStyle(tableBodyCS);

                        Cell cell18 = row.createCell(18);
                        cell18.setCellValue(obj.getPctZn());
                        cell18.setCellStyle(tableBodyCS);

                        Cell cell19 = row.createCell(19);
                        cell19.setCellValue(obj.getAgOzTc());
                        cell19.setCellStyle(tableBodyCS);

                        Cell cell20 = row.createCell(20);
                        cell20.setCellValue(obj.getAuGrTm());
                        cell20.setCellStyle(tableBodyCS);

                        Cell cell21 = row.createCell(21);
                        cell21.setCellValue(obj.getPctFe());
                        cell21.setCellStyle(tableBodyCS);

                        Cell cell22 = row.createCell(22);
                        cell22.setCellValue(obj.getPctMo());
                        cell22.setCellStyle(tableBodyCS);

                        Cell cell23 = row.createCell(23);
                        cell23.setCellValue(obj.getPctSn());
                        cell23.setCellStyle(tableBodyCS);

                        Cell cell24 = row.createCell(24);
                        cell24.setCellValue(obj.getPctCd());
                        cell24.setCellStyle(tableBodyCS);

                        Cell cell25 = row.createCell(25);
                        cell25.setCellValue(obj.getPctWo3());
                        cell25.setCellStyle(tableBodyCS);

                        Cell cell26 = row.createCell(26);
                        cell26.setCellValue(obj.getPctSb());
                        cell26.setCellStyle(tableBodyCS);

                        Cell cell27 = row.createCell(27);
                        cell27.setCellValue(obj.getPctAs());
                        cell27.setCellStyle(tableBodyCS);

                        Cell cell28 = row.createCell(28);
                        cell28.setCellValue(obj.getPctMn());
                        cell28.setCellStyle(tableBodyCS);

                        Cell cell29 = row.createCell(29);
                        cell29.setCellValue(obj.getPctBi());
                        cell29.setCellStyle(tableBodyCS);

                        Cell cell30 = row.createCell(30);
                        cell30.setCellValue(obj.getPcHg());
                        cell30.setCellStyle(tableBodyCS);

                        Cell cell31 = row.createCell(31);
                        cell31.setCellValue(obj.getPctIn());
                        cell31.setCellStyle(tableBodyCS);

                        Cell cell32 = row.createCell(32);
                        cell32.setCellValue(obj.getPctSe());
                        cell32.setCellStyle(tableBodyCS);

                        Cell cell33 = row.createCell(33);
                        cell33.setCellValue(obj.getPctTe());
                        cell33.setCellStyle(tableBodyCS);

                        Cell cell34 = row.createCell(34);
                        cell34.setCellValue(obj.getH2so4());
                        cell34.setCellStyle(tableBodyCS);

                        Cell cell35 = row.createCell(35);
                        cell35.setCellValue(obj.getPctu());
                        cell35.setCellStyle(tableBodyCS);

                        Cell cell36 = row.createCell(36);
                        cell36.setCellValue(obj.getPctNi());
                        cell36.setCellStyle(tableBodyCS);

                        Cell cell37 = row.createCell(37);
                        cell37.setCellValue(obj.getPctMg());
                        cell37.setCellStyle(tableBodyCS);
                }

                String[] columns = new String[37];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.autoSizeColumn(0, true);
                sheet.autoSizeColumn(1, true);
                sheet.autoSizeColumn(2, true);
                sheet.autoSizeColumn(3, true);
                sheet.autoSizeColumn(4, true);
                sheet.autoSizeColumn(5, true);
                sheet.autoSizeColumn(6, true);
                sheet.autoSizeColumn(7, true);
                sheet.autoSizeColumn(8, true);
                sheet.autoSizeColumn(9, true);
                sheet.autoSizeColumn(10, true);
                sheet.autoSizeColumn(11, true);
                sheet.autoSizeColumn(12, true);
                sheet.autoSizeColumn(13, true);
                sheet.autoSizeColumn(14, true);
                sheet.autoSizeColumn(15, true);
                sheet.autoSizeColumn(16, true);
                sheet.autoSizeColumn(17, true);
                sheet.autoSizeColumn(18, true);
                sheet.autoSizeColumn(19, true);
                sheet.autoSizeColumn(20, true);
                sheet.autoSizeColumn(21, true);
                sheet.autoSizeColumn(22, true);
                sheet.autoSizeColumn(23, true);
                sheet.autoSizeColumn(24, true);
                sheet.autoSizeColumn(25, true);
                sheet.autoSizeColumn(26, true);
                sheet.autoSizeColumn(27, true);
                sheet.autoSizeColumn(28, true);
                sheet.autoSizeColumn(29, true);
                sheet.autoSizeColumn(30, true);
                sheet.autoSizeColumn(31, true);
                sheet.autoSizeColumn(32, true);
                sheet.autoSizeColumn(33, true);
                sheet.autoSizeColumn(34, true);
                sheet.autoSizeColumn(35, true);
                sheet.autoSizeColumn(36, true);
                sheet.autoSizeColumn(37, true);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcel(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                //Cerrar el libro de trabajo
                workbook.close();
                
                return archivo;
        }

        @Override
        public Page<PgimIndicadorDesempenioAuxDTO> listarIndicadoresDesempenioOperativo(
                        PgimIndicadorDesempenioAuxDTO filtro, Pageable paginador) {

                Page<PgimIndicadorDesempenioAuxDTO> lPgimIndicadorDesempenioAuxDTO = this.indicadoresDesempenioRepository
                                .listarIndicadoresDesempenioOperativo(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(),
                                                filtro.getDescPeriodoFinal(), paginador);

                return lPgimIndicadorDesempenioAuxDTO;
        }

        @Override
        public byte[] generarReporteIndicadoresDesempenioOperativoEXCEL(PgimIndicadorDesempenioAuxDTO filtro)
                        throws Exception {

                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimIndicadorDesempenioAuxDTO> pagedResult = this.listarIndicadoresDesempenioOperativo(filtro,
                                paging);

                List<PgimIndicadorDesempenioAuxDTO> lPgimIndicadorDesempenioAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_IND_DESEMP_OPERAT");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);
                
                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("COEFICIENTE");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCoeficiente = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrCoeficiente);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("UNIDAD_M");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidadM = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrUnidadM);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("DESCRIPCION");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("CANTIDAD");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidad = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrCantidad);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("EXPLICACION");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrExplicacion = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrExplicacion);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCoeficiente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidadM, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrExplicacion, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 8;
                for (PgimIndicadorDesempenioAuxDTO obj : lPgimIndicadorDesempenioAuxDTO) {
                        Row row = sheet.createRow(rowNum + 1);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getCoeficiente());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getUnidadM());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getDescripcion());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getCantidad());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getExplicacion());
                        cell12.setCellStyle(tableBodyCS);

                        // controlar manualmente cómo se vacían las filas en el disco
                        if (rowNum % 200 == 0) {
                                // retener 200 últimas filas y eliminar todas las demás
                                ((SXSSFSheet) sheet).flushRows(200);
                        }

                        rowNum++;
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[12];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 2000);
                sheet.setColumnWidth(2, 3000);
                sheet.setColumnWidth(3, 4000);
                sheet.setColumnWidth(4, 18000);
                sheet.setColumnWidth(5, 11000);
                sheet.setColumnWidth(6, 3000);
                sheet.setColumnWidth(7, 8000);
                sheet.setColumnWidth(8, 11000);
                sheet.setColumnWidth(9, 5000);
                sheet.setColumnWidth(10, 15000);
                sheet.setColumnWidth(11, 5000);
                sheet.setColumnWidth(12, 55000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public void addImgExcelSXSSF(Workbook workbook, SXSSFSheet sheet, String base64Img, Integer cell, Integer row,
                        Double escala, Integer indexImg) throws Exception {

                CreationHelper helper = workbook.getCreationHelper();

                // Insertar imagen
                byte[] bytesImg = java.util.Base64.getDecoder().decode(base64Img);

                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytesImg));
                int imgWidth = img.getWidth();
                int rangeCellImg = 0;
                int widthColumnDefault = sheet.getDefaultColumnWidth();

                if (indexImg > 1) {
                        rangeCellImg = (int) ((imgWidth * escala)
                                        / (widthColumnDefault * Units.DEFAULT_CHARACTER_WIDTH));
                }

                cell += rangeCellImg;

                // Adds a picture to the workbook
                int pictureIdx = workbook.addPicture(bytesImg, Workbook.PICTURE_TYPE_PNG);

                // Creates the top-level drawing patriarch.
                Drawing drawing = sheet.createDrawingPatriarch();

                // Create an anchor that is attached to the worksheet
                ClientAnchor anchor = helper.createClientAnchor();
                anchor.setAnchorType(AnchorType.MOVE_DONT_RESIZE);
                // set top-left corner for the image
                anchor.setCol1(cell);
                anchor.setRow1(row);

                // Creates a picture
                Picture pict = drawing.createPicture(anchor, pictureIdx);
                // Reset the image to the original size
                pict.resize(escala);
                // Fin Insertar imagen
        }

        @Override
        public Page<PgimProduccionNoMetalicaAuxDTO> listarProduccionNoMetalica(PgimProduccionNoMetalicaAuxDTO filtro,
                        Pageable paginador) {

                Page<PgimProduccionNoMetalicaAuxDTO> lPgimProduccionNoMetalicaAuxDTO = this.produccionNoMetalicaRepository
                                .listarProduccionNoMetalica(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), 
                                                filtro.getDescPeriodoFinal(), paginador);

                return lPgimProduccionNoMetalicaAuxDTO;
        }

        @Override
        public byte[] generarReporteProduccionNoMetalicaEXCEL(PgimProduccionNoMetalicaAuxDTO filtro) throws Exception {

                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimProduccionNoMetalicaAuxDTO> pagedResult = this.produccionNoMetalicaRepository
                                .listarProduccionNoMetalica(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(),
                                                filtro.getDescPeriodoFinal(), paging);

                List<PgimProduccionNoMetalicaAuxDTO> lPgimProduccionNoMetalicaAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                // Nombre de la hoja(Vista)
                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_PRODUC_NO_METALIC");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12, IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CATEGORIA");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCategoria = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCategoria);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("CODIGO");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("UNIDAD");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("CODIGO_INTEGRANTE_UEA");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigoIntegranteUea = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrCodigoIntegranteUea);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("UNIDAD_INTEGRANTE");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidadIntegrante = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrUnidadIntegrante);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("REGION");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRegion = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrRegion);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("PROVINCIA");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProvincia = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrProvincia);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("DISTRITO");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDistrito = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrDistrito);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("PRODUCTO_EXTRAIDO");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProductoExtraido = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrProductoExtraido);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("CANTIDAD_EXTRAIDO");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadExtraido = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrCantidadExtraido);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("PRODUCTO_FINAL");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProductoFinal = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrProductoFinal);

                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("CANTIDAD_PRODUCTO_FINAL");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadProductoFinal = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrCantidadProductoFinal);

                Cell tc1Cell18 = tablaCabecera1.createCell(18);
                tc1Cell18.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell18.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdUnidadMedida = new CellRangeAddress(7, 8, 18, 18);
                sheet.addMergedRegion(mrIdUnidadMedida);

                Cell tc1Cell19 = tablaCabecera1.createCell(19);
                tc1Cell19.setCellValue("DESCRIPCION");
                tc1Cell19.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 19, 19);
                sheet.addMergedRegion(mrDescripcion);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCategoria, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigoIntegranteUea, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidadIntegrante, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRegion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProvincia, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDistrito, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProductoExtraido, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidadExtraido, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProductoFinal, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidadProductoFinal, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 8;
                for (PgimProduccionNoMetalicaAuxDTO obj : lPgimProduccionNoMetalicaAuxDTO) {
                        Row row = sheet.createRow(rowNum + 1);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCategoria());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getCodigo());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getUnidad());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getCodigoIntegranteUea());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getUnidadIntegrante());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getRegion());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getProvincia());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getDistrito());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getProductoExtraido());
                        cell14.setCellStyle(tableBodyCS);

                        if (obj.getCantidadExtraido() == null) {
                                obj.setCantidadExtraido(new BigDecimal(0));
                        }
                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getCantidadExtraido().doubleValue());
                        cell15.setCellStyle(tableBodyCS);

                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getProductoFinal());
                        cell16.setCellStyle(tableBodyCS);

                        if (obj.getCantidadProductoFinal() == null) {
                                obj.setCantidadProductoFinal(new BigDecimal(0));
                        }
                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getCantidadProductoFinal().doubleValue());
                        cell17.setCellStyle(tableBodyCS);

                        Cell cell18 = row.createCell(18);
                        cell18.setCellValue(obj.getIdUnidadMedida());
                        cell18.setCellStyle(tableBodyCS);

                        Cell cell19 = row.createCell(19);
                        cell19.setCellValue(obj.getDescripcion());
                        cell19.setCellStyle(tableBodyCS);

                        // controlar manualmente cómo se vacían las filas en el disco
                        if (rowNum % 200 == 0) {
                                // retener 200 últimas filas y eliminar todas las demás
                                ((SXSSFSheet) sheet).flushRows(200);
                        }

                        rowNum++;
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[19];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 2000);
                sheet.setColumnWidth(2, 3000);
                sheet.setColumnWidth(3, 4000);
                sheet.setColumnWidth(4, 18000);
                sheet.setColumnWidth(5, 11000);
                sheet.setColumnWidth(6, 8000);
                sheet.setColumnWidth(7, 10000);
                sheet.setColumnWidth(8, 11000);
                sheet.setColumnWidth(9, 8000);
                sheet.setColumnWidth(10, 10000);
                sheet.setColumnWidth(11, 4000);
                sheet.setColumnWidth(12, 5000);
                sheet.setColumnWidth(13, 8000);
                sheet.setColumnWidth(14, 8000);
                sheet.setColumnWidth(15, 7000);
                sheet.setColumnWidth(16, 7000);
                sheet.setColumnWidth(17, 8000);
                sheet.setColumnWidth(18, 7000);
                sheet.setColumnWidth(19, 8000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimOtroIndicadorAuxDTO> listarOtrosIndicadores(PgimOtroIndicadorAuxDTO filtro,
                        Pageable paginador) {

                Page<PgimOtroIndicadorAuxDTO> lPgimOtroIndicadorAuxDTO = this.otrosIndicadoresAuxRepository
                                .listarOtrosIndicadores(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(),
                                                paginador);

                return lPgimOtroIndicadorAuxDTO;
        }

        @Override
        public byte[] generarReporteOtrosIndicadoresEXCEL(PgimOtroIndicadorAuxDTO filtro) throws Exception {

                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimOtroIndicadorAuxDTO> pagedResult = this.otrosIndicadoresAuxRepository.listarOtrosIndicadores(
                                filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimOtroIndicadorAuxDTO> lPgimOtroIndicadorAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_OTROS_INDICADORES");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("PROCESO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProceso = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrProceso);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("CONCEPTO");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrConcepto = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrConcepto);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("VALOR");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrValor = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrValor);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("UNIDAD_MEDIDA");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidadMedida = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrUnidadMedida);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("EXPLICACION");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrExplicacion = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrExplicacion);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProceso, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrConcepto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrValor, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrExplicacion, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 8;
                for (PgimOtroIndicadorAuxDTO obj : lPgimOtroIndicadorAuxDTO) {
                        Row row = sheet.createRow(rowNum + 1);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getProceso());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getConcepto());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getValor());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getUnidadMedida());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getExplicacion());
                        cell12.setCellStyle(tableBodyCS);

                        // controlar manualmente cómo se vacían las filas en el disco
                        if (rowNum % 200 == 0) {
                                // retener 200 últimas filas y eliminar todas las demás
                                ((SXSSFSheet) sheet).flushRows(200);
                        }

                        rowNum++;
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[12];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 2000);
                sheet.setColumnWidth(2, 3000);
                sheet.setColumnWidth(3, 4000);
                sheet.setColumnWidth(4, 23000);
                sheet.setColumnWidth(5, 11000);
                sheet.setColumnWidth(6, 4000);
                sheet.setColumnWidth(7, 11000);
                sheet.setColumnWidth(8, 9000);
                sheet.setColumnWidth(9, 5000);
                sheet.setColumnWidth(10, 5000);
                sheet.setColumnWidth(11, 5000);
                sheet.setColumnWidth(12, 15000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimEstadisticoIncidenteAuxDTO> listarEstadisticosIncidente(PgimEstadisticoIncidenteAuxDTO filtro,
                        Pageable paginador) {

                Page<PgimEstadisticoIncidenteAuxDTO> lPgimEstadisticoIncidenteAuxDTO = this.estadisticoIncidenteAuxRepository
                                .listarEstadisticosIncidente(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), 
                                                filtro.getDescPeriodoFinal(), paginador);

                return lPgimEstadisticoIncidenteAuxDTO;
        }

        @Override
        public byte[] generarReporteEstadisticosIncidenteEXCEL(PgimEstadisticoIncidenteAuxDTO filtro) throws Exception {

                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimEstadisticoIncidenteAuxDTO> pagedResult = this.estadisticoIncidenteAuxRepository
                                .listarEstadisticosIncidente(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(),
                                                filtro.getDescPeriodoFinal(), paging);

                List<PgimEstadisticoIncidenteAuxDTO> lPgimEstadisticoIncidenteAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSINER_INDFRECUE");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);
                
                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ID_CORRELATIVO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCorrelativo = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrIdCorrelativo);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("ANOPRO");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("MES");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("ID_CLIENTE");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("RUC");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("TITULAR_MINERO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("CODIGO");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("UNIDAD");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("TRABAJADORES_CIA");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTrabajadoresCia = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrTrabajadoresCia);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("TRABAJADORES_CM");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTrabajadoresCm = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrTrabajadoresCm);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("TRABAJADORES_OTROS");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTrabajadoresOtros = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrTrabajadoresOtros);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("TRABAJADORES_TOTAL");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTrabajadoresTotal = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrTrabajadoresTotal);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("INCIDENTES_MES");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIncidentesMes = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrIncidentesMes);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("INCIDENTES_ACUMALADA");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIncidentesAcumalada = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrIncidentesAcumalada);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("ACCID_LEVES_MES");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAccidLevesMes = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrAccidLevesMes);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("ACCID_LEVES_ACUM");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAccidLevesAcum = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrAccidLevesAcum);

                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("ACCID_INCAPACIT_MES");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAccidIncapacitMes = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrAccidIncapacitMes);

                Cell tc1Cell18 = tablaCabecera1.createCell(18);
                tc1Cell18.setCellValue("ACCID_INCAPACIT_ACUM");
                tc1Cell18.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAccidIncapacitAcum = new CellRangeAddress(7, 8, 18, 18);
                sheet.addMergedRegion(mrAccidIncapacitAcum);

                Cell tc1Cell19 = tablaCabecera1.createCell(19);
                tc1Cell19.setCellValue("ACCID_MORTALES_MES");
                tc1Cell19.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAccidMortalesMes = new CellRangeAddress(7, 8, 19, 19);
                sheet.addMergedRegion(mrAccidMortalesMes);

                Cell tc1Cell20 = tablaCabecera1.createCell(20);
                tc1Cell20.setCellValue("ACCID_MORTALES_ACUM");
                tc1Cell20.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAccidMortalesAcum = new CellRangeAddress(7, 8, 20, 20);
                sheet.addMergedRegion(mrAccidMortalesAcum);

                Cell tc1Cell21 = tablaCabecera1.createCell(21);
                tc1Cell21.setCellValue("DIASPERDIDOS_MES");
                tc1Cell21.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDiasperdidosMes = new CellRangeAddress(7, 8, 21, 21);
                sheet.addMergedRegion(mrDiasperdidosMes);

                Cell tc1Cell22 = tablaCabecera1.createCell(22);
                tc1Cell22.setCellValue("DIASPERDIDOS_ACUM");
                tc1Cell22.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDiasperdidosAcum = new CellRangeAddress(7, 8, 22, 22);
                sheet.addMergedRegion(mrDiasperdidosAcum);

                Cell tc1Cell23 = tablaCabecera1.createCell(23);
                tc1Cell23.setCellValue("HORHOMBRES_TRAB_MES");
                tc1Cell23.setCellStyle(tableHeaderCS);
                CellRangeAddress mrHorhombresTrabMes = new CellRangeAddress(7, 8, 23, 23);
                sheet.addMergedRegion(mrHorhombresTrabMes);

                Cell tc1Cell24 = tablaCabecera1.createCell(24);
                tc1Cell24.setCellValue("HORHOMBRES_TRAB_ACUM");
                tc1Cell24.setCellStyle(tableHeaderCS);
                CellRangeAddress mrHorhombresTrabAcum = new CellRangeAddress(7, 8, 24, 24);
                sheet.addMergedRegion(mrHorhombresTrabAcum);

                Cell tc1Cell25 = tablaCabecera1.createCell(25);
                tc1Cell25.setCellValue("INDICE_FRECUENCIA_MES");
                tc1Cell25.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIndiceFrecuenciaMes = new CellRangeAddress(7, 8, 25, 25);
                sheet.addMergedRegion(mrIndiceFrecuenciaMes);

                Cell tc1Cell26 = tablaCabecera1.createCell(26);
                tc1Cell26.setCellValue("INDICE_FRECUENCIA_ACUM");
                tc1Cell26.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIndiceFrecuenciaAcum = new CellRangeAddress(7, 8, 26, 26);
                sheet.addMergedRegion(mrIndiceFrecuenciaAcum);

                Cell tc1Cell27 = tablaCabecera1.createCell(27);
                tc1Cell27.setCellValue("INDICE_SEVERIDAD_MES");
                tc1Cell27.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIndiceSeveridadMes = new CellRangeAddress(7, 8, 27, 27);
                sheet.addMergedRegion(mrIndiceSeveridadMes);

                Cell tc1Cell28 = tablaCabecera1.createCell(28);
                tc1Cell28.setCellValue("INDICE_SEVERIDAD_ACUM");
                tc1Cell28.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIndiceSeveridadAcum = new CellRangeAddress(7, 8, 28, 28);
                sheet.addMergedRegion(mrIndiceSeveridadAcum);

                Cell tc1Cell29 = tablaCabecera1.createCell(29);
                tc1Cell29.setCellValue("INDICE_ACCIDENTES_MES");
                tc1Cell29.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIndiceAccidentesMes = new CellRangeAddress(7, 8, 29, 29);
                sheet.addMergedRegion(mrIndiceAccidentesMes);

                Cell tc1Cell30 = tablaCabecera1.createCell(30);
                tc1Cell30.setCellValue("INDICE_ACCIDENTES_ACUM");
                tc1Cell30.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIndiceAccidentesAcum = new CellRangeAddress(7, 8, 30, 30);
                sheet.addMergedRegion(mrIndiceAccidentesAcum);

                Cell tc1Cell31 = tablaCabecera1.createCell(31);
                tc1Cell31.setCellValue("USU_INGRESO");
                tc1Cell31.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUsuIngreso = new CellRangeAddress(7, 8, 31, 31);
                sheet.addMergedRegion(mrUsuIngreso);

                Cell tc1Cell32 = tablaCabecera1.createCell(32);
                tc1Cell32.setCellValue("FEC_INGRESO");
                tc1Cell32.setCellStyle(tableHeaderCS);
                CellRangeAddress mrFecIngreso = new CellRangeAddress(7, 8, 32, 32);
                sheet.addMergedRegion(mrFecIngreso);

                Cell tc1Cell33 = tablaCabecera1.createCell(33);
                tc1Cell33.setCellValue("IP_INGRESO");
                tc1Cell33.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIpIngreso = new CellRangeAddress(7, 8, 33, 33);
                sheet.addMergedRegion(mrIpIngreso);

                Cell tc1Cell34 = tablaCabecera1.createCell(34);
                tc1Cell34.setCellValue("USU_MODIFICA");
                tc1Cell34.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUsuModifica = new CellRangeAddress(7, 8, 34, 34);
                sheet.addMergedRegion(mrUsuModifica);

                Cell tc1Cell35 = tablaCabecera1.createCell(35);
                tc1Cell35.setCellValue("FEC_MODIFICA");
                tc1Cell35.setCellStyle(tableHeaderCS);
                CellRangeAddress mrFecModifica = new CellRangeAddress(7, 8, 35, 35);
                sheet.addMergedRegion(mrFecModifica);

                Cell tc1Cell36 = tablaCabecera1.createCell(36);
                tc1Cell36.setCellValue("IP_MODIFICA");
                tc1Cell36.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIpModifica = new CellRangeAddress(7, 8, 36, 36);
                sheet.addMergedRegion(mrIpModifica);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCorrelativo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTrabajadoresCia, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTrabajadoresCm, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTrabajadoresOtros, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTrabajadoresTotal, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIncidentesMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIncidentesAcumalada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAccidLevesMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAccidLevesAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAccidIncapacitMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAccidIncapacitAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAccidMortalesMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAccidMortalesAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDiasperdidosMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDiasperdidosAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrHorhombresTrabMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrHorhombresTrabAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIndiceFrecuenciaMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIndiceFrecuenciaAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIndiceSeveridadMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIndiceSeveridadAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIndiceAccidentesMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIndiceAccidentesAcum, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUsuIngreso, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrFecIngreso, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIpIngreso, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUsuModifica, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrFecModifica, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIpModifica, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimEstadisticoIncidenteAuxDTO obj : lPgimEstadisticoIncidenteAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getIdCorrelativo());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getAnioPro());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getMes());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getIdCliente());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getRuc());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getTitularMinero());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getCodigo());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getUnidad());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getTrabajadoresCia());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getTrabajadoresCm());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getTrabajadoresOtros());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getTrabajadoresTotal());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getIncidentesMes());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getIncidentesAcumalada());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getAccidLevesMes());
                        cell15.setCellStyle(tableBodyCS);

                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getAccidLevesAcum());
                        cell16.setCellStyle(tableBodyCS);

                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getAccidIncapacitMes());
                        cell17.setCellStyle(tableBodyCS);

                        Cell cell18 = row.createCell(18);
                        cell18.setCellValue(obj.getAccidIncapacitAcum());
                        cell18.setCellStyle(tableBodyCS);

                        Cell cell19 = row.createCell(19);
                        cell19.setCellValue(obj.getAccidMortalesMes());
                        cell19.setCellStyle(tableBodyCS);

                        Cell cell20 = row.createCell(20);
                        cell20.setCellValue(obj.getAccidMortalesAcum());
                        cell20.setCellStyle(tableBodyCS);

                        Cell cell21 = row.createCell(21);
                        cell21.setCellValue(obj.getDiasperdidosMes());
                        cell21.setCellStyle(tableBodyCS);

                        Cell cell22 = row.createCell(22);
                        cell22.setCellValue(obj.getDiasperdidosAcum());
                        cell22.setCellStyle(tableBodyCS);

                        Cell cell23 = row.createCell(23);
                        cell23.setCellValue(obj.getHorhombresTrabMes());
                        cell23.setCellStyle(tableBodyCS);

                        Cell cell24 = row.createCell(24);
                        cell24.setCellValue(obj.getHorhombresTrabAcum());
                        cell24.setCellStyle(tableBodyCS);

                        if (obj.getIndiceFrecuenciaMes() == null) {
                                obj.setIndiceFrecuenciaMes(new BigDecimal(0));
                        }
                        Cell cell25 = row.createCell(25);
                        cell25.setCellValue(obj.getIndiceFrecuenciaMes().doubleValue());
                        cell25.setCellStyle(tableBodyCS);

                        if (obj.getIndiceFrecuenciaAcum() == null) {
                                obj.setIndiceFrecuenciaAcum(new BigDecimal(0));
                        }
                        Cell cell26 = row.createCell(26);
                        cell26.setCellValue(obj.getIndiceFrecuenciaAcum().doubleValue());
                        cell26.setCellStyle(tableBodyCS);

                        if (obj.getIndiceSeveridadMes() == null) {
                                obj.setIndiceSeveridadMes(new BigDecimal(0));
                        }
                        Cell cell27 = row.createCell(27);
                        cell27.setCellValue(obj.getIndiceSeveridadMes().doubleValue());
                        cell27.setCellStyle(tableBodyCS);

                        if (obj.getIndiceSeveridadAcum() == null) {
                                obj.setIndiceSeveridadAcum(new BigDecimal(0));
                        }
                        Cell cell28 = row.createCell(28);
                        cell28.setCellValue(obj.getIndiceSeveridadAcum().doubleValue());
                        cell28.setCellStyle(tableBodyCS);

                        if (obj.getIndiceAccidentesMes() == null) {
                                obj.setIndiceAccidentesMes(new BigDecimal(0));
                        }
                        Cell cell29 = row.createCell(29);
                        cell29.setCellValue(obj.getIndiceAccidentesMes().doubleValue());
                        cell29.setCellStyle(tableBodyCS);

                        if (obj.getIndiceAccidentesAcum() == null) {
                                obj.setIndiceAccidentesAcum(new BigDecimal(0));
                        }
                        Cell cell30 = row.createCell(30);
                        cell30.setCellValue(obj.getIndiceAccidentesAcum().doubleValue());
                        cell30.setCellStyle(tableBodyCS);

                        Cell cell31 = row.createCell(31);
                        cell31.setCellValue(obj.getUsuIngreso());
                        cell31.setCellStyle(tableBodyCS);

                        Cell cell32 = row.createCell(32);
                        cell32.setCellValue(obj.getFecIngreso());
                        cell32.setCellStyle(tableBodyCS);

                        Cell cell33 = row.createCell(33);
                        cell33.setCellValue(obj.getIpIngreso());
                        cell33.setCellStyle(tableBodyCS);

                        Cell cell34 = row.createCell(34);
                        cell34.setCellValue(obj.getUsuModifica());
                        cell34.setCellStyle(tableBodyCS);

                        Cell cell35 = row.createCell(35);
                        cell35.setCellValue(obj.getFecModifica());
                        cell35.setCellStyle(tableBodyCS);

                        Cell cell36 = row.createCell(36);
                        cell36.setCellValue(obj.getIpModifica());
                        cell36.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[37];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 5000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 5000);
                sheet.setColumnWidth(5, 5000);
                sheet.setColumnWidth(6, 23000);
                sheet.setColumnWidth(7, 10000);
                sheet.setColumnWidth(8, 13000);
                sheet.setColumnWidth(9, 9000);
                sheet.setColumnWidth(10, 9000);
                sheet.setColumnWidth(11, 9000);
                sheet.setColumnWidth(12, 9000);
                sheet.setColumnWidth(13, 9000);
                sheet.setColumnWidth(14, 9000);
                sheet.setColumnWidth(15, 9000);
                sheet.setColumnWidth(16, 9000);
                sheet.setColumnWidth(17, 9000);
                sheet.setColumnWidth(18, 9000);
                sheet.setColumnWidth(19, 9000);
                sheet.setColumnWidth(20, 9000);
                sheet.setColumnWidth(21, 9000);
                sheet.setColumnWidth(22, 9000);
                sheet.setColumnWidth(23, 9000);
                sheet.setColumnWidth(24, 9000);
                sheet.setColumnWidth(25, 9000);
                sheet.setColumnWidth(26, 9000);
                sheet.setColumnWidth(27, 9000);
                sheet.setColumnWidth(28, 9000);
                sheet.setColumnWidth(29, 9000);
                sheet.setColumnWidth(30, 9000);
                sheet.setColumnWidth(31, 5000);
                sheet.setColumnWidth(32, 5000);
                sheet.setColumnWidth(33, 5000);
                sheet.setColumnWidth(34, 5000);
                sheet.setColumnWidth(35, 5000);
                sheet.setColumnWidth(36, 5000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimProgramaInversionAuxDTO> listarReporteProgramaInversiones(PgimProgramaInversionAuxDTO filtro,
                        Pageable paginador) {

                Page<PgimProgramaInversionAuxDTO> lPgimProgramaInversionAuxDTO = this.programaInversionAuxRepository
                                .listarReporteProgramaInversiones(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(),
                                                filtro.getDescPeriodoFinal(), paginador);

                return lPgimProgramaInversionAuxDTO;
        }

        @Override
        public byte[] generarReporteProgramaInversionesEXCEL(PgimProgramaInversionAuxDTO filtro) throws Exception {

                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimProgramaInversionAuxDTO> pagedResult = this.programaInversionAuxRepository
                                .listarReporteProgramaInversiones(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(),
                                                filtro.getDescPeriodoFinal(), paging);

                List<PgimProgramaInversionAuxDTO> lPgimProgramaInversionAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_PROGRAMA_INVERS");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("TIPO_INVERSION");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTipoInversion = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrTipoInversion);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("NIVEL_DETALLE");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNivelDetalle = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrNivelDetalle);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("ORDEN");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrOrden = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrOrden);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("OTROS_DESCRIPCION");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrOtrosDescripcion = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrOtrosDescripcion);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("CANTIDAD_PROGRAMADA");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadProgramada = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrCantidadProgramada);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("CANTIDAD_EJECUTADA");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadEjecutada = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrCantidadEjecutada);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("CAPEX_SOSTENIMIENTO");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCapexSostenimiento = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrCapexSostenimiento);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("CAPEX_CRECIMIENTO");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCapexCrecimiento = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrCapexCrecimiento);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTipoInversion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrNivelDetalle, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrOrden, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrOtrosDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidadProgramada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidadEjecutada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCapexSostenimiento, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCapexCrecimiento, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimProgramaInversionAuxDTO obj : lPgimProgramaInversionAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getTipoInversion());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getNivelDetalle());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getOrden());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getOtrosDescripcion());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getCantidadProgramada());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getCantidadEjecutada());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getCapexSostenimiento());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getCapexCrecimiento());
                        cell15.setCellStyle(tableBodyCS);

                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[16];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 4000);
                sheet.setColumnWidth(1, 4000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 23000);
                sheet.setColumnWidth(5, 10000);
                sheet.setColumnWidth(6, 5000);
                sheet.setColumnWidth(7, 12000);
                sheet.setColumnWidth(8, 16000);
                sheet.setColumnWidth(9, 7000);
                sheet.setColumnWidth(10, 7000);
                sheet.setColumnWidth(11, 35000);
                sheet.setColumnWidth(12, 9000);
                sheet.setColumnWidth(13, 9000);
                sheet.setColumnWidth(14, 9000);
                sheet.setColumnWidth(15, 9000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimPrincipalProyectoInversionAuxDTO> listarReportePrincipalesProyectosInvers(PgimPrincipalProyectoInversionAuxDTO filtro, Pageable paginador) {
                
                Page<PgimPrincipalProyectoInversionAuxDTO> lPgimPrincipalProyectoInversionAuxDTO = this.principalProyectoInversionAuxRepository.listarReportePrincipalesProyectosInvers(filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimPrincipalProyectoInversionAuxDTO;
        }

        @Override
        public byte[] generarReportePrincipalesProyectosInversEXCEL(PgimPrincipalProyectoInversionAuxDTO filtro) throws Exception {

                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimPrincipalProyectoInversionAuxDTO> pagedResult = this.principalProyectoInversionAuxRepository.listarReportePrincipalesProyectosInvers(filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimPrincipalProyectoInversionAuxDTO> lPgimPrincipalProyectoInversionAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_PRINCI_PROY_INVER");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12, IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea
                
                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                SimpleDateFormat transformarFecha = new SimpleDateFormat("dd'/'MM'/'yyyy");

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("NOMBRE_PROYECTO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombreProyecto = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrNombreProyecto);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("FECHA_INICIO");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrFechaInicio = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrFechaInicio);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("FECHA_TERMINO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrFechaTermino = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrFechaTermino);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("REGION");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRegion = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrRegion);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("ETAPA_PROYECTO");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEtapaProyecto = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrEtapaProyecto);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("ANIOS_VIDA_UTIL");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAniosVidaUtil = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrAniosVidaUtil);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("PRESUPUESTO_GLOBAL");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPresupuestoGlobal = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrPresupuestoGlobal);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("PRESUPUESTO_ANUAL_ESTIMADO");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPresupuestoAnualEstimado = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrPresupuestoAnualEstimado);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("INVERSION_ACU_2018");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrInversionAcu2018 = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrInversionAcu2018);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("INVERSION_MES_ANTERIOR");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrInversionMesAnterior = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrInversionMesAnterior);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("INVERSION_MES");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrInversionMes = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrInversionMes);

                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("PRODUCCION_ANUAL");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProduccionAnual = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrProduccionAnual);

                Cell tc1Cell18 = tablaCabecera1.createCell(18);
                tc1Cell18.setCellValue("PORC_AVANCE_FISICO");
                tc1Cell18.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPorcAvanceFisico = new CellRangeAddress(7, 8, 18, 18);
                sheet.addMergedRegion(mrPorcAvanceFisico);

                Cell tc1Cell19 = tablaCabecera1.createCell(19);
                tc1Cell19.setCellValue("PROYECTO_CARTERA_MINEM");
                tc1Cell19.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProyectoCarteraMinem = new CellRangeAddress(7, 8, 19, 19);
                sheet.addMergedRegion(mrProyectoCarteraMinem);

                Cell tc1Cell20 = tablaCabecera1.createCell(20);
                tc1Cell20.setCellValue("MINERAL_PRINCIPAL");
                tc1Cell20.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMineralPrincipal = new CellRangeAddress(7, 8, 20, 20);
                sheet.addMergedRegion(mrMineralPrincipal);

                Cell tc1Cell21 = tablaCabecera1.createCell(21);
                tc1Cell21.setCellValue("OTROS_MINERALES");
                tc1Cell21.setCellStyle(tableHeaderCS);
                CellRangeAddress mrOtrosMinerales = new CellRangeAddress(7, 8, 21, 21);
                sheet.addMergedRegion(mrOtrosMinerales);

                Cell tc1Cell22 = tablaCabecera1.createCell(22);
                tc1Cell22.setCellValue("EMPLEO_OPERACION");
                tc1Cell22.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEmpleoOperacion = new CellRangeAddress(7, 8, 22, 22);
                sheet.addMergedRegion(mrEmpleoOperacion);

                Cell tc1Cell23 = tablaCabecera1.createCell(23);
                tc1Cell23.setCellValue("EMPLEO_CONSTRUCCION");
                tc1Cell23.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEmpleoConstruccion = new CellRangeAddress(7, 8, 23, 23);
                sheet.addMergedRegion(mrEmpleoConstruccion);

                Cell tc1Cell24 = tablaCabecera1.createCell(24);
                tc1Cell24.setCellValue("NOMBRE_INVERSIONISTA");
                tc1Cell24.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombreInversionista = new CellRangeAddress(7, 8, 24, 24);
                sheet.addMergedRegion(mrNombreInversionista);

                Cell tc1Cell25 = tablaCabecera1.createCell(25);
                tc1Cell25.setCellValue("CONCESION_MINERA");
                tc1Cell25.setCellStyle(tableHeaderCS);
                CellRangeAddress mrConcesionMinera = new CellRangeAddress(7, 8, 25, 25);
                sheet.addMergedRegion(mrConcesionMinera);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrNombreProyecto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrFechaInicio, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrFechaTermino, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRegion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEtapaProyecto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAniosVidaUtil, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPresupuestoGlobal, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPresupuestoAnualEstimado, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrInversionAcu2018, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrInversionMesAnterior, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrInversionMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProduccionAnual, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPorcAvanceFisico, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProyectoCarteraMinem, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMineralPrincipal, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrOtrosMinerales, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEmpleoOperacion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEmpleoConstruccion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrNombreInversionista, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrConcesionMinera, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimPrincipalProyectoInversionAuxDTO obj : lPgimPrincipalProyectoInversionAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getNombreProyecto());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(transformarFecha.format(obj.getFechaInicio()));
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(transformarFecha.format(obj.getFechaTermino()));
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getRegion());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getEtapaProyecto());
                        cell10.setCellStyle(tableBodyCS);

                        if (obj.getAniosVidaUtil() == null) {
                                obj.setAniosVidaUtil(new BigDecimal(0));
                        }
                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getAniosVidaUtil().doubleValue());
                        cell11.setCellStyle(tableBodyCS);

                        if (obj.getPresupuestoGlobal() == null) {
                                obj.setPresupuestoGlobal(new BigDecimal(0));
                        }
                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getPresupuestoGlobal().doubleValue());
                        cell12.setCellStyle(tableBodyCS);

                        if (obj.getPresupuestoAnualEstimado() == null) {
                                obj.setPresupuestoAnualEstimado(new BigDecimal(0));
                        }
                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getPresupuestoAnualEstimado().doubleValue());
                        cell13.setCellStyle(tableBodyCS);

                        if (obj.getInversionAcu2018() == null) {
                                obj.setInversionAcu2018(new BigDecimal(0));
                        }
                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getInversionAcu2018().doubleValue());
                        cell14.setCellStyle(tableBodyCS);

                        if (obj.getInversionMesAnterior() == null) {
                                obj.setInversionMesAnterior(new BigDecimal(0));
                        }
                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getInversionMesAnterior().doubleValue());
                        cell15.setCellStyle(tableBodyCS);

                        if (obj.getInversionMes() == null) {
                                obj.setInversionMes(new BigDecimal(0));
                        }
                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getInversionMes().doubleValue());
                        cell16.setCellStyle(tableBodyCS);

                        if (obj.getProduccionAnual() == null) {
                                obj.setProduccionAnual(new BigDecimal(0));
                        }
                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getProduccionAnual().doubleValue());
                        cell17.setCellStyle(tableBodyCS);

                        Cell cell18 = row.createCell(18);
                        cell18.setCellValue(obj.getPorcAvanceFisico());
                        cell18.setCellStyle(tableBodyCS);

                        Cell cell19 = row.createCell(19);
                        cell19.setCellValue(obj.getProyectoCarteraMinem());
                        cell19.setCellStyle(tableBodyCS);

                        Cell cell20 = row.createCell(20);
                        cell20.setCellValue(obj.getMineralPrincipal());
                        cell20.setCellStyle(tableBodyCS);

                        Cell cell21 = row.createCell(21);
                        cell21.setCellValue(obj.getOtrosMinerales());
                        cell21.setCellStyle(tableBodyCS);

                        Cell cell22 = row.createCell(22);
                        cell22.setCellValue(obj.getEmpleoOperacion());
                        cell22.setCellStyle(tableBodyCS);

                        Cell cell23 = row.createCell(23);
                        cell23.setCellValue(obj.getEmpleoConstruccion());
                        cell23.setCellStyle(tableBodyCS);

                        Cell cell24 = row.createCell(24);
                        cell24.setCellValue(obj.getNombreInversionista());
                        cell24.setCellStyle(tableBodyCS);

                        Cell cell25 = row.createCell(25);
                        cell25.setCellValue(obj.getConcesionMinera());
                        cell25.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[25];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 18000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 65000);//65000
                sheet.setColumnWidth(7, 5000);
                sheet.setColumnWidth(8, 5000);
                sheet.setColumnWidth(9, 5000);
                sheet.setColumnWidth(10, 7000);
                sheet.setColumnWidth(11, 8000);
                sheet.setColumnWidth(12, 9000);
                sheet.setColumnWidth(13, 9000);
                sheet.setColumnWidth(14, 8000);
                sheet.setColumnWidth(15, 9000);
                sheet.setColumnWidth(16, 8000);
                sheet.setColumnWidth(17, 8000);
                sheet.setColumnWidth(18, 8000);
                sheet.setColumnWidth(19, 9000);
                sheet.setColumnWidth(20, 7000);
                sheet.setColumnWidth(21, 7000);
                sheet.setColumnWidth(22, 8000);
                sheet.setColumnWidth(23, 8000);
                sheet.setColumnWidth(24, 40000); //40000
                sheet.setColumnWidth(25, 65000); //65000

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimExploDesarrolloAuxDTO> listarExploDesarrollo(PgimExploDesarrolloAuxDTO filtro, Pageable paginador) {

                Page<PgimExploDesarrolloAuxDTO> lPgimExploDesarrolloAuxDTO = this.exploDesarrolloAuxRepository.listarExploDesarrollo(filtro.getDescNoUnidadMinera() ,filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimExploDesarrolloAuxDTO;
        }

        @Override
        public byte[] generarReporteExploDesarrolloEXCEL(PgimExploDesarrolloAuxDTO filtro) throws Exception {
               
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimExploDesarrolloAuxDTO> pagedResult = this.exploDesarrolloAuxRepository.listarExploDesarrollo(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimExploDesarrolloAuxDTO> lPgimExploDesarrolloAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_EXPLO_DESAR");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12, IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea
                
                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("LABOR");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrLabor = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrLabor);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("EXPLORACION_CANTIDAD_EJECUTADA");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrExploracionCantidadEjecutada = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrExploracionCantidadEjecutada);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("EXPLORACION_NRO_LABORES");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrExploracionNroLabores = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrExploracionNroLabores);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("DESARROLLO_CANTIDAD_EJECUTADA");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDesarrolloCantidadEjecutada = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrDesarrolloCantidadEjecutada);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("DESARROLLO_NRO_LABORES");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDesarrolloNroLabores = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrDesarrolloNroLabores);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("PREPARACION_CANTIDAD_EJECUTADA");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPreparacionCantidadEjecutada = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrPreparacionCantidadEjecutada);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("PREPARACION_NRO_LABORES");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPreparacionNroLabores = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrPreparacionNroLabores);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("EXPLOTACION_CANTIDAD_EJECUTADA");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrExplotacionCantidadEjecutada = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrExplotacionCantidadEjecutada);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("EXPLOTACION_NRO_LABORES");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrExplotacionNroLabores = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrExplotacionNroLabores);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrLabor, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrExploracionCantidadEjecutada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrExploracionNroLabores, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDesarrolloCantidadEjecutada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDesarrolloNroLabores, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPreparacionCantidadEjecutada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPreparacionNroLabores, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrExplotacionCantidadEjecutada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrExplotacionNroLabores, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimExploDesarrolloAuxDTO obj : lPgimExploDesarrolloAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getLabor());
                        cell8.setCellStyle(tableBodyCS);

                        if (obj.getExploracionCantidadEjecutada() == null) {
                                obj.setExploracionCantidadEjecutada(new BigDecimal(0));
                        }
                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getExploracionCantidadEjecutada().doubleValue());
                        cell9.setCellStyle(tableBodyCS);

                        if (obj.getExploracionNroLabores() == null) {
                                obj.setExploracionNroLabores(new BigDecimal(0));
                        }
                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getExploracionNroLabores().doubleValue());
                        cell10.setCellStyle(tableBodyCS);

                        if (obj.getDesarrolloCantidadEjecutada() == null) {
                                obj.setDesarrolloCantidadEjecutada(new BigDecimal(0));
                        }
                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getDesarrolloCantidadEjecutada().doubleValue());
                        cell11.setCellStyle(tableBodyCS);

                        if (obj.getDesarrolloNroLabores() == null) {
                                obj.setDesarrolloNroLabores(new BigDecimal(0));
                        }
                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getDesarrolloNroLabores().doubleValue());
                        cell12.setCellStyle(tableBodyCS);

                        if (obj.getPreparacionCantidadEjecutada() == null) {
                                obj.setPreparacionCantidadEjecutada(new BigDecimal(0));
                        }
                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getPreparacionCantidadEjecutada().doubleValue());
                        cell13.setCellStyle(tableBodyCS);

                        if (obj.getPreparacionNroLabores() == null) {
                                obj.setPreparacionNroLabores(new BigDecimal(0));
                        }
                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getPreparacionNroLabores().doubleValue());
                        cell14.setCellStyle(tableBodyCS);

                        if (obj.getExplotacionCantidadEjecutada() == null) {
                                obj.setExplotacionCantidadEjecutada(new BigDecimal(0));
                        }
                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getExplotacionCantidadEjecutada().doubleValue());
                        cell15.setCellStyle(tableBodyCS);

                        if (obj.getExplotacionNroLabores() == null) {
                                obj.setExplotacionNroLabores(new BigDecimal(0));
                        }
                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getExplotacionNroLabores().doubleValue());
                        cell16.setCellStyle(tableBodyCS);

                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[17];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 18000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 5000);//65000
                sheet.setColumnWidth(7, 15000);
                sheet.setColumnWidth(8, 13000);
                sheet.setColumnWidth(9, 11000);
                sheet.setColumnWidth(10, 9000);
                sheet.setColumnWidth(11, 11000);
                sheet.setColumnWidth(12, 9000);
                sheet.setColumnWidth(13, 11000);
                sheet.setColumnWidth(14, 9000);
                sheet.setColumnWidth(15, 11000);
                sheet.setColumnWidth(16, 9000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimIdentUnidMineraAuxDTO> listarIdentUnidMineras(PgimIdentUnidMineraAuxDTO filtro, Pageable paginador) {
                
                Page<PgimIdentUnidMineraAuxDTO> lPgimIdentUnidMineraAuxDTO = this.identUnidMineraAuxRepository.listarIdentUnidMineras(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimIdentUnidMineraAuxDTO;
        }

        @Override
        public byte[] generarReporteIdentUnidMineraEXCEL(PgimIdentUnidMineraAuxDTO filtro) throws Exception {
               
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimIdentUnidMineraAuxDTO> pagedResult = this.identUnidMineraAuxRepository.listarIdentUnidMineras(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimIdentUnidMineraAuxDTO> lPgimIdentUnidMineraAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_IDENT_UNID_MINERA");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12, IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea
                
                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("ID_CLASE_SUSTANCIA");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdClaseSustancia = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrIdClaseSustancia);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("ID_SITUACIONUP");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdSituacionup = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrIdSituacionup);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("DES_SITUACIONUP");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDesSituacionup = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrDesSituacionup);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdClaseSustancia, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdSituacionup, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDesSituacionup, sheet, workbook);
              
                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimIdentUnidMineraAuxDTO obj : lPgimIdentUnidMineraAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getIdClaseSustancia());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getIdSituacionup());
                        cell9.setCellStyle(tableBodyCS);
                    
                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getDesSituacionup());
                        cell10.setCellStyle(tableBodyCS);

                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[11];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 23000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 5000);//65000
                sheet.setColumnWidth(7, 12000);
                sheet.setColumnWidth(8, 9000);
                sheet.setColumnWidth(9, 5000);
                sheet.setColumnWidth(10, 10000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimDestinoProduccionAuxDTO> listarDestinoProduccion(PgimDestinoProduccionAuxDTO filtro, Pageable paginador) {

                Page<PgimDestinoProduccionAuxDTO> lPgimDestinoProduccionAuxDTO = this.destinoProduccionAuxRepository.listarDestinoProduccion(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimDestinoProduccionAuxDTO;
        }

        @Override
        public byte[] generarReporteDestinoProduccionEXCEL(PgimDestinoProduccionAuxDTO filtro) throws Exception {
                
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimDestinoProduccionAuxDTO> pagedResult = this.destinoProduccionAuxRepository.listarDestinoProduccion(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimDestinoProduccionAuxDTO> lPgimDestinoProduccionAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_DESTINO_PRODUCCI");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12, IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea
                
                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("PLANTA_ORIGEN");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPlantaOrigen = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrPlantaOrigen);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("DESTINO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDestino = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrDestino);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("REGIONPAIS");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRegionpais = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrRegionpais);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("DUA");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDua = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrDua);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("PRODUCTO");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProducto = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrProducto);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdUnidadMedida = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrIdUnidadMedida);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("DESCRIPCION");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("CANTIDAD");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidad = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrCantidad);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("VALOR");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrValor = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrValor);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPlantaOrigen, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDestino, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRegionpais, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDua, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProducto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrValor, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimDestinoProduccionAuxDTO obj : lPgimDestinoProduccionAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getPlantaOrigen());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getDestino());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getRegionpais());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getDua());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getProducto());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getIdUnidadMedida());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getDescripcion());
                        cell13.setCellStyle(tableBodyCS);

                        if (obj.getCantidad() == null) {
                                obj.setCantidad(new BigDecimal(0));
                        }
                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getCantidad().doubleValue());
                        cell14.setCellStyle(tableBodyCS);

                        if (obj.getValor() == null) {
                                obj.setValor(new BigDecimal(0));
                        }
                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getValor().doubleValue());
                        cell15.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[16];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 22000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 5000);//65000
                sheet.setColumnWidth(7, 15000);
                sheet.setColumnWidth(8, 13000);
                sheet.setColumnWidth(9, 11000);
                sheet.setColumnWidth(10, 16000);
                sheet.setColumnWidth(11, 11000);
                sheet.setColumnWidth(12, 9000);
                sheet.setColumnWidth(13, 11000);
                sheet.setColumnWidth(14, 9000);
                sheet.setColumnWidth(15, 11000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimTitularAuxDTO> listarTitular(PgimTitularAuxDTO filtro, Pageable paginador) {
                
                Page<PgimTitularAuxDTO> lPgimTitularAuxDTO = this.titularAuxRepository.listarTitular(filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimTitularAuxDTO;
        }

        @Override
        public byte[] generarReporteTitularEXCEL(PgimTitularAuxDTO filtro) throws Exception {
               
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimTitularAuxDTO> pagedResult = this.titularAuxRepository.listarTitular(filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimTitularAuxDTO> lPgimTitularAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_TITULAR");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14, IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12, IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10, IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // REVIEW: Estilo para saltos de lineas de acuerdo a la cantidad de carateres de
                // algunas celdas
                CellStyle tableBodyCSEspecial = workbook.createCellStyle();
                tableBodyCSEspecial.setFont(bodyFont);
                tableBodyCSEspecial.setBorderBottom(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderTop(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderRight(BorderStyle.THIN);
                tableBodyCSEspecial.setBorderLeft(BorderStyle.THIN);
                tableBodyCSEspecial.setWrapText(true); // salto de linea
                
                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnioPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnioPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("ID_REPRESENTANTE");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdRepresentante = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrIdRepresentante);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("NOMBRE_REPRESENTANTE_LEGAL");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombreRepresentanteLegal = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrNombreRepresentanteLegal);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("EMAIL");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEmail = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrEmail);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("CARGO_REPRESENTANTE");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCargoRepresentante = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrCargoRepresentante);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("NOMBRERESPONSABLE");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombreresponsable = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrNombreresponsable);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("CARGO_RESPONSABLE");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCargoResponsable = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrCargoResponsable);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("DESCRIPCION");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("RESP_NO_DOCUMENTO");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRespNoDocumento = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrRespNoDocumento);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("RESP_EMAIL");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRespEmail = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrRespEmail);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("RESP_TELEFONO");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRespTelefono = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrRespTelefono);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("RESP_TELEFONO_MOVIL");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRespTelefonoMovil = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrRespTelefonoMovil);

                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("ID_CARGO");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCargo = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrIdCargo);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnioPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdRepresentante, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrNombreRepresentanteLegal, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEmail, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCargoRepresentante, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrNombreresponsable, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCargoResponsable, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRespNoDocumento, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRespEmail, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRespTelefono, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRespTelefonoMovil, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCargo, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimTitularAuxDTO obj : lPgimTitularAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        if (obj.getIdRepresentante() == null) {
                                obj.setIdRepresentante(0L);
                        }
                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getIdRepresentante());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getNombreRepresentanteLegal());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getEmail());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getCargoRepresentante());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getNombreresponsable());
                        cell10.setCellStyle(tableBodyCS);

                        if (obj.getCargoResponsable() == null) {
                                obj.setCargoResponsable(0L);
                        }
                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getCargoResponsable());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getDescripcion());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getRespNoDocumento());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getRespEmail());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getRespTelefono());
                        cell15.setCellStyle(tableBodyCS);

                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getRespTelefonoMovil());
                        cell16.setCellStyle(tableBodyCS);

                        if (obj.getIdCargo() == null) {
                                obj.setIdCargo(0L);
                        }
                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getIdCargo());
                        cell17.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[18];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 3000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 18000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 8000);//65000
                sheet.setColumnWidth(7, 15000);
                sheet.setColumnWidth(8, 15000);
                sheet.setColumnWidth(9, 15000);
                sheet.setColumnWidth(10, 15000);
                sheet.setColumnWidth(11, 8000);
                sheet.setColumnWidth(12, 15000);
                sheet.setColumnWidth(13, 9000);
                sheet.setColumnWidth(14, 15000);
                sheet.setColumnWidth(15, 9000);
                sheet.setColumnWidth(16, 8000);
                sheet.setColumnWidth(17, 8000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimMineralRecibPlantaAuxDTO> listarMineralRecibPlanta(PgimMineralRecibPlantaAuxDTO filtro, Pageable paginador) {

                Page<PgimMineralRecibPlantaAuxDTO> lPgimMineralRecibPlantaAuxDTO = this.mineralRecibPlantaAuxRepository.listarMineralRecibPlanta(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimMineralRecibPlantaAuxDTO;
        }

        @Override
        public byte[] generarReporteMineralRecibPlantaEXCEL(PgimMineralRecibPlantaAuxDTO filtro) throws Exception {
                
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimMineralRecibPlantaAuxDTO> pagedResult = this.mineralRecibPlantaAuxRepository.listarMineralRecibPlanta(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimMineralRecibPlantaAuxDTO> lPgimMineralRecibPlantaAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_MINERAL_RECIB_PLA");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14,
                                IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10,
                                IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9,
                                IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("PROCESO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProceso = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrProceso);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("REGION");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRegion = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrRegion);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("PROCEDENCIA");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProcedencia = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrProcedencia);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("OBTENIDO_DE");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrObtenidoDe = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrObtenidoDe);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("UNIDAD_OBTENIDO");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidadObtenido = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrUnidadObtenido);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("RUC_PROVEEDOR");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRucProveedor = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrRucProveedor);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("NOMBRE_PROVEEDOR");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrNombreProveedor = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrNombreProveedor);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("PRODUCTO");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProducto = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrProducto);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdUnidadMedida = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrIdUnidadMedida);
              
                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("DESCRIPCION");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell18 = tablaCabecera1.createCell(18);
                tc1Cell18.setCellValue("CANTIDAD_RECIBIDA");
                tc1Cell18.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadRecibida = new CellRangeAddress(7, 8, 18, 18);
                sheet.addMergedRegion(mrCantidadRecibida);

                Cell tc1Cell19 = tablaCabecera1.createCell(19);
                tc1Cell19.setCellValue("CANTIDAD_PROCESADA");
                tc1Cell19.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidadProcesada = new CellRangeAddress(7, 8, 19, 19);
                sheet.addMergedRegion(mrCantidadProcesada);

                Cell tc1Cell20 = tablaCabecera1.createCell(20);
                tc1Cell20.setCellValue("PCT_CU");
                tc1Cell20.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctCu = new CellRangeAddress(7, 8, 20, 20);
                sheet.addMergedRegion(mrPctCu);

                Cell tc1Cell21 = tablaCabecera1.createCell(21);
                tc1Cell21.setCellValue("PCT_PB");
                tc1Cell21.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctPb = new CellRangeAddress(7, 8, 21, 21);
                sheet.addMergedRegion(mrPctPb);

                Cell tc1Cell22 = tablaCabecera1.createCell(22);
                tc1Cell22.setCellValue("PCT_ZN");
                tc1Cell22.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctZn = new CellRangeAddress(7, 8, 22, 22);
                sheet.addMergedRegion(mrPctZn);

                Cell tc1Cell23 = tablaCabecera1.createCell(23);
                tc1Cell23.setCellValue("AG_OZ_TC");
                tc1Cell23.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAgOzTc = new CellRangeAddress(7, 8, 23, 23);
                sheet.addMergedRegion(mrAgOzTc);

                Cell tc1Cell24 = tablaCabecera1.createCell(24);
                tc1Cell24.setCellValue("AU_GR_TM");
                tc1Cell24.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAuGrTm = new CellRangeAddress(7, 8, 24, 24);
                sheet.addMergedRegion(mrAuGrTm);

                Cell tc1Cell25 = tablaCabecera1.createCell(25);
                tc1Cell25.setCellValue("PCT_FE");
                tc1Cell25.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctFe = new CellRangeAddress(7, 8, 25, 25);
                sheet.addMergedRegion(mrPctFe);

                Cell tc1Cell26 = tablaCabecera1.createCell(26);
                tc1Cell26.setCellValue("PCT_MO");
                tc1Cell26.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMo = new CellRangeAddress(7, 8, 26, 26);
                sheet.addMergedRegion(mrPctMo);

                Cell tc1Cell27 = tablaCabecera1.createCell(27);
                tc1Cell27.setCellValue("PCT_SN");
                tc1Cell27.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSn = new CellRangeAddress(7, 8, 27, 27);
                sheet.addMergedRegion(mrPctSn);

                Cell tc1Cell28 = tablaCabecera1.createCell(28);
                tc1Cell28.setCellValue("PCT_CD");
                tc1Cell28.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctCd = new CellRangeAddress(7, 8, 28, 28);
                sheet.addMergedRegion(mrPctCd);

                Cell tc1Cell29 = tablaCabecera1.createCell(29);
                tc1Cell29.setCellValue("PCT_WO3");
                tc1Cell29.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctWo3 = new CellRangeAddress(7, 8, 29, 29);
                sheet.addMergedRegion(mrPctWo3);

                Cell tc1Cell30 = tablaCabecera1.createCell(30);
                tc1Cell30.setCellValue("PCT_SB");
                tc1Cell30.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSb = new CellRangeAddress(7, 8, 30, 30);
                sheet.addMergedRegion(mrPctSb);

                Cell tc1Cell31 = tablaCabecera1.createCell(31);
                tc1Cell31.setCellValue("PCT_AS");
                tc1Cell31.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctAs = new CellRangeAddress(7, 8, 31, 31);
                sheet.addMergedRegion(mrPctAs);

                Cell tc1Cell32 = tablaCabecera1.createCell(32);
                tc1Cell32.setCellValue("PCT_MN");
                tc1Cell32.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMn = new CellRangeAddress(7, 8, 32, 32);
                sheet.addMergedRegion(mrPctMn);

                Cell tc1Cell33 = tablaCabecera1.createCell(33);
                tc1Cell33.setCellValue("PCT_BI");
                tc1Cell33.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctBi = new CellRangeAddress(7, 8, 33, 33);
                sheet.addMergedRegion(mrPctBi);

                Cell tc1Cell34 = tablaCabecera1.createCell(34);
                tc1Cell34.setCellValue("PCT_HG");
                tc1Cell34.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctHg = new CellRangeAddress(7, 8, 34, 34);
                sheet.addMergedRegion(mrPctHg);

                Cell tc1Cell35 = tablaCabecera1.createCell(35);
                tc1Cell35.setCellValue("PCT_IN");
                tc1Cell35.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctIn = new CellRangeAddress(7, 8, 35, 35);
                sheet.addMergedRegion(mrPctIn);

                Cell tc1Cell36 = tablaCabecera1.createCell(36);
                tc1Cell36.setCellValue("PCT_SE");
                tc1Cell36.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctSe = new CellRangeAddress(7, 8, 36, 36);
                sheet.addMergedRegion(mrPctSe);

                Cell tc1Cell37 = tablaCabecera1.createCell(37);
                tc1Cell37.setCellValue("PCT_TE");
                tc1Cell37.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctTe = new CellRangeAddress(7, 8, 37, 37);
                sheet.addMergedRegion(mrPctTe);

                Cell tc1Cell38 = tablaCabecera1.createCell(38);
                tc1Cell38.setCellValue("H2SO4");
                tc1Cell38.setCellStyle(tableHeaderCS);
                CellRangeAddress mrH2So4 = new CellRangeAddress(7, 8, 38, 38);
                sheet.addMergedRegion(mrH2So4);

                Cell tc1Cell39 = tablaCabecera1.createCell(39);
                tc1Cell39.setCellValue("U");
                tc1Cell39.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctU = new CellRangeAddress(7, 8, 39, 39);
                sheet.addMergedRegion(mrPctU);

                Cell tc1Cell40 = tablaCabecera1.createCell(40);
                tc1Cell40.setCellValue("PCT_NI");
                tc1Cell40.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctNi = new CellRangeAddress(7, 8, 40, 40);
                sheet.addMergedRegion(mrPctNi);

                Cell tc1Cell41 = tablaCabecera1.createCell(41);
                tc1Cell41.setCellValue("PCT_MG");
                tc1Cell41.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPctMg = new CellRangeAddress(7, 8, 41, 41);
                sheet.addMergedRegion(mrPctMg);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProceso, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRegion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProcedencia, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrObtenidoDe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidadObtenido, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRucProveedor, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrNombreProveedor, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProducto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidadRecibida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidadProcesada, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctCu, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctPb, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctZn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAgOzTc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAuGrTm, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctFe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctMo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctSn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctCd, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctWo3, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctSb, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctAs, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctMn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctBi, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctHg, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctIn, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctSe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctTe, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrH2So4, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctU, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctNi, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPctMg, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimMineralRecibPlantaAuxDTO obj : lPgimMineralRecibPlantaAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getProceso());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getRegion());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getProcedencia());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getObtenidoDe());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getUnidadObtenido());
                        cell12.setCellStyle(tableBodyCS);

                        if (obj.getRucProveedor() == null) {
                                obj.setRucProveedor(0L);
                        }
                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getRucProveedor());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getNombreProveedor());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getProducto());
                        cell15.setCellStyle(tableBodyCS);

                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getIdUnidadMedida());
                        cell16.setCellStyle(tableBodyCS);

                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getDescripcion());
                        cell17.setCellStyle(tableBodyCS);

                        if (obj.getCantidadRecibida() == null) {
                                obj.setCantidadRecibida(new BigDecimal(0));
                        }
                        Cell cell18 = row.createCell(18);
                        cell18.setCellValue(obj.getCantidadRecibida().doubleValue());
                        cell18.setCellStyle(tableBodyCS);

                        if (obj.getCantidadProcesada() == null) {
                                obj.setCantidadProcesada(new BigDecimal(0));
                        }
                        Cell cell19 = row.createCell(19);
                        cell19.setCellValue(obj.getCantidadProcesada().doubleValue());
                        cell19.setCellStyle(tableBodyCS);

                        Cell cell20 = row.createCell(20);
                        cell20.setCellValue(obj.getPctCu());
                        cell20.setCellStyle(tableBodyCS);

                        Cell cell21 = row.createCell(21);
                        cell21.setCellValue(obj.getPctPb());
                        cell21.setCellStyle(tableBodyCS);

                        Cell cell22 = row.createCell(22);
                        cell22.setCellValue(obj.getPctZn());
                        cell22.setCellStyle(tableBodyCS);

                        Cell cell23 = row.createCell(23);
                        cell23.setCellValue(obj.getAgOzTc());
                        cell23.setCellStyle(tableBodyCS);

                        Cell cell24 = row.createCell(24);
                        cell24.setCellValue(obj.getAuGrTm());
                        cell24.setCellStyle(tableBodyCS);

                        Cell cell25 = row.createCell(25);
                        cell25.setCellValue(obj.getPctFe());
                        cell25.setCellStyle(tableBodyCS);

                        Cell cell26 = row.createCell(26);
                        cell26.setCellValue(obj.getPctMo());
                        cell26.setCellStyle(tableBodyCS);

                        Cell cell27 = row.createCell(27);
                        cell27.setCellValue(obj.getPctSn());
                        cell27.setCellStyle(tableBodyCS);

                        Cell cell28 = row.createCell(28);
                        cell28.setCellValue(obj.getPctCd());
                        cell28.setCellStyle(tableBodyCS);

                        Cell cell29 = row.createCell(29);
                        cell29.setCellValue(obj.getPctWo3());
                        cell29.setCellStyle(tableBodyCS);

                        Cell cell30 = row.createCell(30);
                        cell30.setCellValue(obj.getPctSb());
                        cell30.setCellStyle(tableBodyCS);

                        Cell cell31 = row.createCell(31);
                        cell31.setCellValue(obj.getPctAs());
                        cell31.setCellStyle(tableBodyCS);

                        Cell cell32 = row.createCell(32);
                        cell32.setCellValue(obj.getPctMn());
                        cell32.setCellStyle(tableBodyCS);

                        Cell cell33 = row.createCell(33);
                        cell33.setCellValue(obj.getPctBi());
                        cell33.setCellStyle(tableBodyCS);

                        Cell cell34 = row.createCell(34);
                        cell34.setCellValue(obj.getPcHg());
                        cell34.setCellStyle(tableBodyCS);

                        Cell cell35 = row.createCell(35);
                        cell35.setCellValue(obj.getPctIn());
                        cell35.setCellStyle(tableBodyCS);

                        Cell cell36 = row.createCell(36);
                        cell36.setCellValue(obj.getPctSe());
                        cell36.setCellStyle(tableBodyCS);

                        Cell cell37 = row.createCell(37);
                        cell37.setCellValue(obj.getPctTe());
                        cell37.setCellStyle(tableBodyCS);

                        Cell cell38 = row.createCell(38);
                        cell38.setCellValue(obj.getH2so4());
                        cell38.setCellStyle(tableBodyCS);

                        Cell cell39 = row.createCell(39);
                        cell39.setCellValue(obj.getU());
                        cell39.setCellStyle(tableBodyCS);

                        Cell cell40 = row.createCell(40);
                        cell40.setCellValue(obj.getPctNi());
                        cell40.setCellStyle(tableBodyCS);

                        Cell cell41 = row.createCell(41);
                        cell41.setCellValue(obj.getPctMg());
                        cell41.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[42];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 5000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 16000);
                sheet.setColumnWidth(5, 6000);
                sheet.setColumnWidth(6, 5000);
                sheet.setColumnWidth(7, 15000);
                sheet.setColumnWidth(8, 8000);
                sheet.setColumnWidth(9, 8000);
                sheet.setColumnWidth(10, 8000);
                sheet.setColumnWidth(11, 8000);
                sheet.setColumnWidth(12, 15000);
                sheet.setColumnWidth(13, 8000);
                sheet.setColumnWidth(14, 19000);
                sheet.setColumnWidth(15, 9000);
                sheet.setColumnWidth(16, 9000);
                sheet.setColumnWidth(17, 10000);
                sheet.setColumnWidth(18, 10000);
                sheet.setColumnWidth(19, 10000);
                sheet.setColumnWidth(20, 5000);
                sheet.setColumnWidth(21, 5000);
                sheet.setColumnWidth(22, 5000);
                sheet.setColumnWidth(23, 5000);
                sheet.setColumnWidth(24, 5000);
                sheet.setColumnWidth(25, 5000);
                sheet.setColumnWidth(26, 5000);
                sheet.setColumnWidth(27, 5000);
                sheet.setColumnWidth(28, 5000);
                sheet.setColumnWidth(29, 5000);
                sheet.setColumnWidth(30, 5000);
                sheet.setColumnWidth(31, 5000);
                sheet.setColumnWidth(32, 5000);
                sheet.setColumnWidth(33, 5000);
                sheet.setColumnWidth(34, 5000);
                sheet.setColumnWidth(35, 5000);
                sheet.setColumnWidth(36, 5000);
                sheet.setColumnWidth(37, 5000);
                sheet.setColumnWidth(38, 5000);
                sheet.setColumnWidth(39, 5000);
                sheet.setColumnWidth(40, 5000);
                sheet.setColumnWidth(41, 5000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimInfoHechosImportAuxDTO> listarInfoHechosImport(PgimInfoHechosImportAuxDTO filtro, Pageable paginador) {
                
                Page<PgimInfoHechosImportAuxDTO> lPgimInfoHechosImportAuxDTO = this.infoHechosImportAuxRepository.listarInfoHechosImport(filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimInfoHechosImportAuxDTO;
        }

        @Override
        public byte[] generarReporteInfoHechosImportEXCEL(PgimInfoHechosImportAuxDTO filtro) throws Exception {
                
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimInfoHechosImportAuxDTO> pagedResult = this.infoHechosImportAuxRepository.listarInfoHechosImport(filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimInfoHechosImportAuxDTO> lPgimInfoHechosImportAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_HECHOS_IMPORTAN");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14,
                                IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10,
                                IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));
                        
                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("HECHOS_AFECTAN_DECLARACION");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrHechosAfectanDeclaracion = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrHechosAfectanDeclaracion);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("INFO_GESTION_SOCIAL");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrInfoGestionSocial = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrInfoGestionSocial);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrHechosAfectanDeclaracion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrInfoGestionSocial, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimInfoHechosImportAuxDTO obj : lPgimInfoHechosImportAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getHechosAfectanDeclaracion());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getInfoGestionSocial());
                        cell7.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[8];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 5000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 15000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 50000);
                sheet.setColumnWidth(7, 50000);
             
                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();

                return archivo;
        }

        @Override
        public Page<PgimProdCarbonObtenidaAuxDTO> listarProdCarbonObtenida(PgimProdCarbonObtenidaAuxDTO filtro,
                        Pageable paginador) {
                
                Page<PgimProdCarbonObtenidaAuxDTO> lPgimProdCarbonObtenidaAuxDTO = this.prodCarbonObtenidaAuxRepository.listarProdCarbonObtenida(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimProdCarbonObtenidaAuxDTO;
        }

        @Override
        public byte[] generarReporteProdCarbonObtenidaEXCEL(PgimProdCarbonObtenidaAuxDTO filtro) throws Exception {
                
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimProdCarbonObtenidaAuxDTO> pagedResult = this.prodCarbonObtenidaAuxRepository.listarProdCarbonObtenida(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimProdCarbonObtenidaAuxDTO> lPgimProdCarbonObtenidaAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_PRODUC_CARBON_OBT");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14,
                                IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10,
                                IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));

                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CATEGORIA");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCategoria = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCategoria);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("CODIGO");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("UNIDAD");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("CODIGO_INTEGRANTE_UEA");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigoIntegranteUea = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrCodigoIntegranteUea);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("UNIDAD_INTEGRANTE");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidadIntegrante = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrUnidadIntegrante);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("REGION");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRegion = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrRegion);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("PROVINCIA");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProvincia = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrProvincia);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("DISTRITO");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDistrito = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrDistrito);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("PRODUCTO");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrProducto = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrProducto);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdUnidadMedida = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrIdUnidadMedida);

                Cell tc1Cell16 = tablaCabecera1.createCell(16);
                tc1Cell16.setCellValue("DESCRIPCION");
                tc1Cell16.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 16, 16);
                sheet.addMergedRegion(mrDescripcion);
              
                Cell tc1Cell17 = tablaCabecera1.createCell(17);
                tc1Cell17.setCellValue("CANTIDAD");
                tc1Cell17.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidad = new CellRangeAddress(7, 8, 17, 17);
                sheet.addMergedRegion(mrCantidad);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCategoria, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigoIntegranteUea, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidadIntegrante, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRegion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProvincia, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDistrito, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrProducto, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidad, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimProdCarbonObtenidaAuxDTO obj : lPgimProdCarbonObtenidaAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCategoria());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getCodigo());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getUnidad());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getCodigoIntegranteUea());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getUnidadIntegrante());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getRegion());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getProvincia());
                        cell12.setCellStyle(tableBodyCS);

                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getDistrito());
                        cell13.setCellStyle(tableBodyCS);

                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getProducto());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getIdUnidadMedida());
                        cell15.setCellStyle(tableBodyCS);
                 
                        Cell cell16 = row.createCell(16);
                        cell16.setCellValue(obj.getDescripcion());
                        cell16.setCellStyle(tableBodyCS);

                        if (obj.getCantidad() == null) {
                                obj.setCantidad(new BigDecimal(0));
                        }
                        Cell cell17 = row.createCell(17);
                        cell17.setCellValue(obj.getCantidad().doubleValue());
                        cell17.setCellStyle(tableBodyCS);

                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[42];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 5000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 15000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 8000);
                sheet.setColumnWidth(7, 6000);
                sheet.setColumnWidth(8, 8000);
                sheet.setColumnWidth(9, 8000);
                sheet.setColumnWidth(10, 8000);
                sheet.setColumnWidth(11, 5000);
                sheet.setColumnWidth(12, 5000);
                sheet.setColumnWidth(13, 5000);
                sheet.setColumnWidth(14, 5000);
                sheet.setColumnWidth(15, 8000);
                sheet.setColumnWidth(16, 8000);
                sheet.setColumnWidth(17, 5000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();
                
                return archivo;
        }

        @Override
        public Page<PgimCarboniferaDestinoAuxDTO> listarCarboniferaDestino(PgimCarboniferaDestinoAuxDTO filtro,
                        Pageable paginador) {
               
                Page<PgimCarboniferaDestinoAuxDTO> lPgimCarboniferaDestinoAuxDTO = this.carboniferaDestinoAuxRepository.listarCarboniferaDestino(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paginador);

                return lPgimCarboniferaDestinoAuxDTO;
        }

        @Override
        public byte[] generarReporteCarboniferaDestinoEXCEL(PgimCarboniferaDestinoAuxDTO filtro) throws Exception {
                
                int pageNo = 0;

                int pageSize = (Integer) filtro.getCantidadRegistros();

                Pageable paging = PageRequest.of(pageNo, pageSize);

                Page<PgimCarboniferaDestinoAuxDTO> pagedResult = this.carboniferaDestinoAuxRepository.listarCarboniferaDestino(filtro.getDescNoUnidadMinera(), filtro.getDescPeriodoInicial(), filtro.getDescPeriodoFinal(), paging);

                List<PgimCarboniferaDestinoAuxDTO> lPgimCarboniferaDestinoAuxDTO = pagedResult.getContent();

                // Crear una nueva instancia de SXSSFWorkbook
                SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

                SXSSFSheet sheet = workbook.createSheet("MV_OSIN_CARBO_DESTINO");

                // Crea las fuentes para los estilos
                Font titleFont = PoiExcelUtil.createFont(workbook, true, (short) 14,
                                IndexedColors.BLACK.getIndex());
                Font tableHeaderFont = PoiExcelUtil.createFont(workbook, true, (short) 12,
                                IndexedColors.BLACK.getIndex());
                Font bodyFont = PoiExcelUtil.createFont(workbook, false, (short) 10,
                                IndexedColors.BLACK.getIndex());

                // Creando los estilos para las celdas
                CellStyle titleCS = workbook.createCellStyle();
                titleCS.setFont(titleFont);
                titleCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle subTitleCS = workbook.createCellStyle();
                subTitleCS.setFont(tableHeaderFont);
                subTitleCS.setAlignment(HorizontalAlignment.LEFT);

                CellStyle subTitleCS2 = workbook.createCellStyle();
                subTitleCS2.setFont(bodyFont);
                subTitleCS2.setAlignment(HorizontalAlignment.LEFT);

                CellStyle tableHeaderCS = workbook.createCellStyle();
                tableHeaderCS.setFont(tableHeaderFont);
                tableHeaderCS.setBorderBottom(BorderStyle.THIN);
                tableHeaderCS.setBorderTop(BorderStyle.THIN);
                tableHeaderCS.setBorderRight(BorderStyle.THIN);
                tableHeaderCS.setBorderLeft(BorderStyle.THIN);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);
                tableHeaderCS.setVerticalAlignment(VerticalAlignment.CENTER);
                tableHeaderCS.setAlignment(HorizontalAlignment.CENTER);

                CellStyle tableBodyCS = workbook.createCellStyle();
                tableBodyCS.setFont(bodyFont);
                tableBodyCS.setBorderBottom(BorderStyle.THIN);
                tableBodyCS.setBorderTop(BorderStyle.THIN);
                tableBodyCS.setBorderRight(BorderStyle.THIN);
                tableBodyCS.setBorderLeft(BorderStyle.THIN);

                // Crea el titulo
                Row headerRow = sheet.createRow(4);
                Cell titleCell = headerRow.createCell(0);
                titleCell.setCellValue(filtro.getDeTituloReporte());
                titleCell.setCellStyle(titleCS);
                sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 6));
                
                // Subtitulo de los periodos iniciales y finales
                Row headerRowPeriodos = sheet.createRow(5);
                Cell subTitleCell = headerRowPeriodos.createCell(0);
                subTitleCell.setCellValue("Criterios de filtro aplicado: Periodo inicial: " + filtro.getDescPeriodoInicial() + " - Periodo final: " + filtro.getDescPeriodoFinal());
                sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 6));

                // Fecha de generación
                Font fechaGeneracionFont = PoiExcelUtil.createFont(workbook, false, (short) 9, IndexedColors.BLACK.getIndex());
                CellStyle fechaGeneracionStyle = workbook.createCellStyle();
                fechaGeneracionStyle.setFont(fechaGeneracionFont);
                fechaGeneracionStyle.setAlignment(HorizontalAlignment.CENTER);

                java.util.Date fechaActual = new Date();
                SimpleDateFormat sdfg = new SimpleDateFormat("dd'/'MM'/'yyyy HH:mm:ss");
                String fechaDeGeneracion = "Generado por la PGIM el: " + sdfg.format(fechaActual);

                Cell fechaGenCell = headerRow.createCell(7);
                fechaGenCell.setCellValue(fechaDeGeneracion);
                fechaGenCell.setCellStyle(fechaGeneracionStyle);
                subTitleCell.setCellStyle(fechaGeneracionStyle);

                // Crea un espacio en blanco
                Row emptyRow = sheet.createRow(6);
                Cell emptyCell = emptyRow.createCell(0);
                emptyCell.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 10));

                // Creamos las cabeceras de la tabla
                Row tablaCabecera1 = sheet.createRow(7);

                Cell tc1Cell0 = tablaCabecera1.createCell(0);
                tc1Cell0.setCellValue("ANOPRO");
                tc1Cell0.setCellStyle(tableHeaderCS);
                CellRangeAddress mrAnoPro = new CellRangeAddress(7, 8, 0, 0);
                sheet.addMergedRegion(mrAnoPro);

                Cell tc1Cell1 = tablaCabecera1.createCell(1);
                tc1Cell1.setCellValue("MES");
                tc1Cell1.setCellStyle(tableHeaderCS);
                CellRangeAddress mrMes = new CellRangeAddress(7, 8, 1, 1);
                sheet.addMergedRegion(mrMes);

                Cell tc1Cell2 = tablaCabecera1.createCell(2);
                tc1Cell2.setCellValue("ID_CLIENTE");
                tc1Cell2.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdCliente = new CellRangeAddress(7, 8, 2, 2);
                sheet.addMergedRegion(mrIdCliente);

                Cell tc1Cell3 = tablaCabecera1.createCell(3);
                tc1Cell3.setCellValue("RUC");
                tc1Cell3.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRuc = new CellRangeAddress(7, 8, 3, 3);
                sheet.addMergedRegion(mrRuc);

                Cell tc1Cell4 = tablaCabecera1.createCell(4);
                tc1Cell4.setCellValue("TITULAR_MINERO");
                tc1Cell4.setCellStyle(tableHeaderCS);
                CellRangeAddress mrTitularMinero = new CellRangeAddress(7, 8, 4, 4);
                sheet.addMergedRegion(mrTitularMinero);

                Cell tc1Cell5 = tablaCabecera1.createCell(5);
                tc1Cell5.setCellValue("ESTRATO");
                tc1Cell5.setCellStyle(tableHeaderCS);
                CellRangeAddress mrEstrato = new CellRangeAddress(7, 8, 5, 5);
                sheet.addMergedRegion(mrEstrato);

                Cell tc1Cell6 = tablaCabecera1.createCell(6);
                tc1Cell6.setCellValue("CODIGO");
                tc1Cell6.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCodigo = new CellRangeAddress(7, 8, 6, 6);
                sheet.addMergedRegion(mrCodigo);

                Cell tc1Cell7 = tablaCabecera1.createCell(7);
                tc1Cell7.setCellValue("UNIDAD");
                tc1Cell7.setCellStyle(tableHeaderCS);
                CellRangeAddress mrUnidad = new CellRangeAddress(7, 8, 7, 7);
                sheet.addMergedRegion(mrUnidad);

                Cell tc1Cell8 = tablaCabecera1.createCell(8);
                tc1Cell8.setCellValue("RECURSO_EXTRAIDO");
                tc1Cell8.setCellStyle(tableHeaderCS);
                CellRangeAddress mrRecursoExtraido = new CellRangeAddress(7, 8, 8, 8);
                sheet.addMergedRegion(mrRecursoExtraido);

                Cell tc1Cell9 = tablaCabecera1.createCell(9);
                tc1Cell9.setCellValue("DESTINO");
                tc1Cell9.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDestino = new CellRangeAddress(7, 8, 9, 9);
                sheet.addMergedRegion(mrDestino);

                Cell tc1Cell10 = tablaCabecera1.createCell(10);
                tc1Cell10.setCellValue("PAIS");
                tc1Cell10.setCellStyle(tableHeaderCS);
                CellRangeAddress mrPais = new CellRangeAddress(7, 8, 10, 10);
                sheet.addMergedRegion(mrPais);

                Cell tc1Cell11 = tablaCabecera1.createCell(11);
                tc1Cell11.setCellValue("ID_UNIDAD_MEDIDA");
                tc1Cell11.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdUnidadMedida = new CellRangeAddress(7, 8, 11, 11);
                sheet.addMergedRegion(mrIdUnidadMedida);

                Cell tc1Cell12 = tablaCabecera1.createCell(12);
                tc1Cell12.setCellValue("DESCRIPCION");
                tc1Cell12.setCellStyle(tableHeaderCS);
                CellRangeAddress mrDescripcion = new CellRangeAddress(7, 8, 12, 12);
                sheet.addMergedRegion(mrDescripcion);

                Cell tc1Cell13 = tablaCabecera1.createCell(13);
                tc1Cell13.setCellValue("CANTIDAD");
                tc1Cell13.setCellStyle(tableHeaderCS);
                CellRangeAddress mrCantidad = new CellRangeAddress(7, 8, 13, 13);
                sheet.addMergedRegion(mrCantidad);

                Cell tc1Cell14 = tablaCabecera1.createCell(14);
                tc1Cell14.setCellValue("VALOR");
                tc1Cell14.setCellStyle(tableHeaderCS);
                CellRangeAddress mrValor = new CellRangeAddress(7, 8, 14, 14);
                sheet.addMergedRegion(mrValor);

                Cell tc1Cell15 = tablaCabecera1.createCell(15);
                tc1Cell15.setCellValue("ID_MONEDA");
                tc1Cell15.setCellStyle(tableHeaderCS);
                CellRangeAddress mrIdMoneda = new CellRangeAddress(7, 8, 15, 15);
                sheet.addMergedRegion(mrIdMoneda);

                // Aplicamos los bordes a las celdas fusionadas
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrAnoPro, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrMes, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdCliente, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRuc, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrTitularMinero, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrEstrato, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCodigo, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrUnidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrRecursoExtraido, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDestino, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrPais, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdUnidadMedida, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrDescripcion, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrCantidad, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrValor, sheet, workbook);
                PoiExcelUtil.setBorderToMergeCellSXSSF(BorderStyle.THIN, mrIdMoneda, sheet, workbook);

                // Creamos el detalle de la tabla
                int rowNum = 9;
                for (PgimCarboniferaDestinoAuxDTO obj : lPgimCarboniferaDestinoAuxDTO) {
                        Row row = sheet.createRow(rowNum++);

                        Cell cell0 = row.createCell(0);
                        cell0.setCellValue(obj.getAnioPro());
                        cell0.setCellStyle(tableBodyCS);

                        Cell cell1 = row.createCell(1);
                        cell1.setCellValue(obj.getMes());
                        cell1.setCellStyle(tableBodyCS);

                        Cell cell2 = row.createCell(2);
                        cell2.setCellValue(obj.getIdCliente());
                        cell2.setCellStyle(tableBodyCS);

                        Cell cell3 = row.createCell(3);
                        cell3.setCellValue(obj.getRuc());
                        cell3.setCellStyle(tableBodyCS);

                        Cell cell4 = row.createCell(4);
                        cell4.setCellValue(obj.getTitularMinero());
                        cell4.setCellStyle(tableBodyCS);

                        Cell cell5 = row.createCell(5);
                        cell5.setCellValue(obj.getEstrato());
                        cell5.setCellStyle(tableBodyCS);

                        Cell cell6 = row.createCell(6);
                        cell6.setCellValue(obj.getCodigo());
                        cell6.setCellStyle(tableBodyCS);

                        Cell cell7 = row.createCell(7);
                        cell7.setCellValue(obj.getUnidad());
                        cell7.setCellStyle(tableBodyCS);

                        Cell cell8 = row.createCell(8);
                        cell8.setCellValue(obj.getRecursoExtraido());
                        cell8.setCellStyle(tableBodyCS);

                        Cell cell9 = row.createCell(9);
                        cell9.setCellValue(obj.getDestino());
                        cell9.setCellStyle(tableBodyCS);

                        Cell cell10 = row.createCell(10);
                        cell10.setCellValue(obj.getPais());
                        cell10.setCellStyle(tableBodyCS);

                        Cell cell11 = row.createCell(11);
                        cell11.setCellValue(obj.getIdUnidadMedida());
                        cell11.setCellStyle(tableBodyCS);

                        Cell cell12 = row.createCell(12);
                        cell12.setCellValue(obj.getDescripcion());
                        cell12.setCellStyle(tableBodyCS);

                        
                        if (obj.getCantidad() == null) {
                                obj.setCantidad(new BigDecimal(0));
                        }
                        Cell cell13 = row.createCell(13);
                        cell13.setCellValue(obj.getCantidad().doubleValue());
                        cell13.setCellStyle(tableBodyCS);

                        if (obj.getValor() == null) {
                                obj.setValor(new BigDecimal(0));
                        }
                        Cell cell14 = row.createCell(14);
                        cell14.setCellValue(obj.getValor().doubleValue());
                        cell14.setCellStyle(tableBodyCS);

                        Cell cell15 = row.createCell(15);
                        cell15.setCellValue(obj.getIdMoneda());
                        cell15.setCellStyle(tableBodyCS);
                }

                sheet.trackAllColumnsForAutoSizing();
                String[] columns = new String[16];
                // Resize a todas las columnas
                PoiExcelUtil.resizeAllColumnsInXSSFSheet(sheet, columns);

                // Resize a las columnas con merge field
                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 5000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 15000);
                sheet.setColumnWidth(5, 8000);
                sheet.setColumnWidth(6, 5000);
                sheet.setColumnWidth(7, 10000);
                sheet.setColumnWidth(8, 9000);
                sheet.setColumnWidth(9, 9000);
                sheet.setColumnWidth(10, 5000);
                sheet.setColumnWidth(11, 6000);
                sheet.setColumnWidth(12, 10000);
                sheet.setColumnWidth(13, 5000);
                sheet.setColumnWidth(14, 5000);
                sheet.setColumnWidth(15, 5000);

                // obtener la imagen y convertirlo a base64
                String ruta = propertiesConfig.getFilesRepo() + ConstantesUtil.PARAM_LOGO_OSI;
                InputStream iSteamReader = new FileInputStream(ruta);
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);

                // Insertar imagen logotipo
                this.addImgExcelSXSSF(workbook, sheet, base64, 1, 1, 0.22, 1);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                byte[] archivo = baos.toByteArray();

                // cerrar el InputStream y ByteArrayOutputStream
                iSteamReader.close();
                baos.close();

                // Cerrar el libro de trabajo
                workbook.dispose();
                
                return archivo;
        }
}
