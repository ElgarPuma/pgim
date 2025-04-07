package pe.gob.osinergmin.pgim.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;

public class PoiExcelUtil {

	/**
	 * Permite crear un estilo para una celda
	 * @param workbook
	 * @param boldWeight
	 * @param fontHeightInPoints
	 * @param color
	 * @return
	 */
	public static Font createFont(Workbook workbook, Boolean boldWeight, short fontHeightInPoints, short color) {
	    Font font = workbook.createFont();
			font.setBold(boldWeight);
	    font.setFontHeightInPoints(fontHeightInPoints);
	    font.setColor(color);
	
		return font;
	}
	
	/**
	 * Permite crear celdas en la fila seleccionada
	 * @param row
	 * @param columns
	 * @param cellStyle
	 */
	public static void createCellsInRow(Row row, String[] columns, CellStyle cellStyle) {
		for (int i = 0; i < columns.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
			if(cellStyle != null) {
				cell.setCellStyle(cellStyle);	
			}
		}
	}
	
	/**
	 * Permite ajustar todas las columnas de la hoja seleccionada
	 * @param sheet
	 * @param columns
	 */
	public static void resizeAllColumnsInSheet(Sheet sheet, String[] columns) {
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
	    }
	}
	
	/**
	 * Permite agregar bordes a las celdas fusionadas
	 * @param border
	 * @param region
	 * @param sheet
	 * @param workbook
	 */
	public static void setBorderToMergeCell(BorderStyle border, CellRangeAddress region, Sheet sheet, Workbook workbook) {
		RegionUtil.setBorderBottom(border, region, sheet);
	    RegionUtil.setBorderTop(border, region, sheet);
	    RegionUtil.setBorderLeft(border, region, sheet);
	    RegionUtil.setBorderRight(border, region, sheet);
	}
	
	/**
	 * Permite ajustar todas las columnas de la hoja seleccionada con la nueva clase XSSFSheet
	 * @param sheet
	 * @param columns
	 */
	public static void resizeAllColumnsInXSSFSheet(SXSSFSheet sheet, String[] columns) {
		for (int i = 0; i < columns.length; i++) {
			// sheet.autoSizeColumn(i);
			sheet.trackColumnForAutoSizing(i);
			sheet.autoSizeColumn(i);
	    }
	}
	
	/**
	 * Permite agregar bordes a las celdas fusionadas
	 * 
	 * @param border
	 * @param region
	 * @param sheet
	 * @param workbook
	 */
	public static void setBorderToMergeCell_v2(BorderStyle border, CellRangeAddress region, SXSSFSheet sheet,
			Workbook workbook) {
		RegionUtil.setBorderBottom(border, region, sheet);
		RegionUtil.setBorderTop(border, region, sheet);
		RegionUtil.setBorderLeft(border, region, sheet);
		RegionUtil.setBorderRight(border, region, sheet);
	}
	public static void setBorderToMergeCellSXSSF(BorderStyle border, CellRangeAddress region, SXSSFSheet sheet,
			Workbook workbook) {
		RegionUtil.setBorderBottom(border, region, sheet);
		RegionUtil.setBorderTop(border, region, sheet);
		RegionUtil.setBorderLeft(border, region, sheet);
		RegionUtil.setBorderRight(border, region, sheet);
	}
}
