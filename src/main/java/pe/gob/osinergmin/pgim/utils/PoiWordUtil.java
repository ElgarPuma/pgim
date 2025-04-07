package pe.gob.osinergmin.pgim.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoiWordUtil {

	/**
	 * Permite crear celdas en una tabla
	 * @param table : tabla en la que se crea la celda
	 * @param tableRow : fila tabla creada o a ser creada
	 * @param tableRowVertAlign : alineaci´pon vertical de tabla
	 * @param height : alto de la fila 
	 * @param color : color de fondo de celda
	 * @param widthType : tipo de ancho de celda 
	 * @param widthValue : valor ancho de celda
	 * @param rowNumber : numero de fila
	 * @param cellNumber : numero de celda
	 * @param indentationLeft : sangria a la izquierda
	 * @param paragraphAlign : alineación del parrafo
	 * @param paragraphLineSpacingRule : regla de línea de espacio
	 * @param paragraphSpacingBefore : espacio antes de la línea
	 * @param paragraphSpacingAfter : espacio despues de la línea
	 * @param text : texto del parrafo
	 * @param fontSize : tamaño del texto
	 * @param isBold : valida si el texto es negrita
	 * @return
	 */
	public static XWPFTableRow createTableCell(XWPFTable table, XWPFTableRow tableRow, XWPFVertAlign tableRowVertAlign, 
			int height, String color, /*TableWidthType widthType,*/ int widthValue, int rowNumber, int cellNumber, 
			int indentationLeft, ParagraphAlignment paragraphAlign, LineSpacingRule paragraphLineSpacingRule, 
			int paragraphSpacingBefore, int paragraphSpacingAfter, String text, int fontSize, boolean isBold) {
		
		if(rowNumber == 0) {
			//Header: fila = 0 y la celda = 0
			if(cellNumber == 0) {
				//Obtiene la celda creada por defecto
				tableRow = table.getRow(rowNumber);	
			}else {
				//Se crea nueva celda
				tableRow.addNewTableCell();	
			}	
		}else {
			//Body
			if(cellNumber == 0) {
				//Se crea una nueva fila
				tableRow = table.createRow();	
			}
		}
		
		tableRow.setHeight(height);
		XWPFTableCell cell = tableRow.getCell(cellNumber);
		cell.setVerticalAlignment(tableRowVertAlign);
		cell.setColor(color);
		
		cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(widthValue));
		
//		cell.setWidthType(widthType);
//		cell.setWidth(widthValue)
		//Agregando contenido a la celda
		XWPFParagraph paragraph = tableRow.getCell(cellNumber).addParagraph();
		table.getRow(rowNumber).getCell(cellNumber).removeParagraph(0);
		paragraph.setIndentationLeft(indentationLeft);
		paragraph.setAlignment(paragraphAlign);
		paragraph.setSpacingLineRule(paragraphLineSpacingRule);
		paragraph.setSpacingBefore(paragraphSpacingBefore);
		paragraph.setSpacingAfter(paragraphSpacingAfter);
		//Aplicando estilos al contenido de la celda
		
		XWPFRun run = paragraph.createRun();
		
		if (text == null) {
			text = "";
		}
		
		if(text.contains("\n")) {
			String[] textSplit = text.split("\n");
			for (int j = 0; j < textSplit.length; j++) {
				if(j == 0) {
					run.setText(textSplit[j]);
					run.setFontSize(fontSize);
					run.setBold(isBold);
					run.setFontFamily("Arial");
					run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
				}else {
					run.addBreak();
					// run.addCarriageReturn();
					run.setText(textSplit[j]);
					run.setFontSize(fontSize);
					run.setBold(isBold);
					run.setFontFamily("Arial");
					run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
				}
			}
		}else {
			run.setText(text);
			run.setFontSize(fontSize);
			run.setBold(isBold);
			run.setFontFamily("Arial");
			run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
	}

		return tableRow;
	}
	

	/**
	 * ermite crear celdas en una tabla con texto enrequesido
	 * @param table : tabla en la que se crea la celda
	 * @param tableRow : fila tabla creada o a ser creada
	 * @param tableRowVertAlign : alineaci´pon vertical de tabla
	 * @param height : alto de la fila 
	 * @param color : color de fondo de celda
	 * @param widthValue : valor ancho de celda
	 * @param rowNumber : numero de fila
	 * @param cellNumber : numero de celda
	 * @param paragraphAlign : alineación del parrafo
	 * @param text : texto del parrafo
	 * @param fontSize : tamaño del texto
	 * @param document : documento word en que se creara la tabla
	 * @param idHtml : idenficador para insertar el texto enrequesido (html)
	 * @return
	 * @throws Exception
	 */
	public static XWPFTableRow createTableCellHtml(XWPFTable table, XWPFTableRow tableRow, XWPFVertAlign tableRowVertAlign, 
			int height, String color, int widthValue, int rowNumber, int cellNumber, String paragraphAlign,
			String text, int fontSize, XWPFDocument document, String idHtml) throws Exception {
		
			if(rowNumber == 0) {
				//Header: fila = 0 y la celda = 0
				if(cellNumber == 0) {
					//Obtiene la celda creada por defecto
					tableRow = table.getRow(rowNumber);	
				}else {
					//Se crea nueva celda
					tableRow.addNewTableCell();	
				}	
			}else {
				//Body
				if(cellNumber == 0) {
					//Se crea una nueva fila
					tableRow = table.createRow();	
				}
			}
			
			tableRow.setHeight(height);
			XWPFTableCell cell = tableRow.getCell(cellNumber);
			cell.setVerticalAlignment(tableRowVertAlign);
			cell.setColor(color);
			
			cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(widthValue));

			if (text == null || text.equals("")) {
				cell.setText("");
				return tableRow;
			}
			
			text = String.format("<!DOCTYPE html><html><head> <meta charset=\"utf-8\"> <style> body{text-align: %s; font-size: %spt; font-family:Arial, Helvetica, sans-serif } p{margin: 0;} table{vertical-align: middle; border-collapse: collapse; border-spacing: 0;} td{border: 1px solid #000;}</style><title></title></head><body>%s</body></html>", paragraphAlign, fontSize, text);

			//Agregando contenido a la celda
			XWPFParagraph paragraph = tableRow.getCell(cellNumber).addParagraph();
			XmlCursor cursor = paragraph.getCTP().newCursor();
			EscritorHtml.addHtmlInTableCell(document, idHtml, text, cursor);

			List<XWPFParagraph> listParagraph =  tableRow.getCell(cellNumber).getParagraphs();
			for (int i = 0; i <= listParagraph.size(); i++) {
				table.getRow(rowNumber).getCell(cellNumber).removeParagraph(0);
			}

			return tableRow;
	}


	/**
	 * Permite crear una tabla en un documento
	 * @param document : documento word en que se creara la tabla
	 * @param tableRowAling : alineación de la tabla
	 * @param tableWidthType : tipo de ancho de la tabla
	 * @param width : ancho de la tabla
	 * @return
	 */
	public static XWPFTable createTable(XWPFDocument document/*, TableRowAlign tableRowAling, TableWidthType tableWidthType, String width*/) {
		XWPFTable table = document.createTable();
//		table.setTableAlignment(tableRowAling);
//		table.setWidthType(tableWidthType);
//		table.setWidth(width);
		
		return table;
	}
	
	/**
	 * Permite transformar un documento POI docx em un PDF usando aspose y convertirlo en un array de bytes.
	 * @param myBytes
	 * @return
	 * @throws Exception
	 */
	/*public static byte[] doc2pdf(byte[] myBytes, Long idTipoExtensionGen) throws Exception {
		if (!checkLicense()) {
			throw new Exception("com.aspose.words lic ERROR!");
		}
		// Create a byte array inout stream.
		ByteArrayInputStream docStream = new ByteArrayInputStream(myBytes);
		// Create document from stream.
		Document doc = new Document(docStream);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		if(idTipoExtensionGen != null && idTipoExtensionGen.intValue() == ConstantesUtil.PARAM_SC_PDF.intValue()) {
			doc.save(outputStream, SaveFormat.PDF);	
		}else if(idTipoExtensionGen != null && idTipoExtensionGen.intValue() == ConstantesUtil.PARAM_SC_DOCX.intValue()) {
			doc.save(outputStream, SaveFormat.DOCX);
		}

		return outputStream.toByteArray();
	}*/
	
	/**
	 * Permite transformar un documento POI docx em un PDF usando aspose y convertirlo en un array de bytes.
	 * @param myBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] doc2pdf(byte[] myBytes, Long idTipoExtensionGen, String pathLicanciaAspose) throws Exception {
		if (!checkLicense(pathLicanciaAspose)) {
			throw new Exception("com.aspose.words lic ERROR!");
		}
		// Create a byte array inout stream.
		ByteArrayInputStream docStream = new ByteArrayInputStream(myBytes);
		// Create document from stream.
		Document doc = new Document(docStream);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		if(idTipoExtensionGen != null && idTipoExtensionGen.intValue() == ConstantesUtil.PARAM_SC_PDF.intValue()) {
			doc.save(outputStream, SaveFormat.PDF);	
		}else if(idTipoExtensionGen != null && idTipoExtensionGen.intValue() == ConstantesUtil.PARAM_SC_DOCX.intValue()) {
			doc.save(outputStream, SaveFormat.DOCX);
		}

		return outputStream.toByteArray();
	}
	
	/**
	 * Permite crear un parrafo en un documento 
	 * @param document : documento word en que se creara la tabla
	 * @param indentationLeft : sangria a la izquierda
	 * @param paragraphAlignment : alineación del parrafo
	 * @param paragraphLineSpacingRule : regla de línea de espacio
	 * @param paragraphSpacingBefore : espacio antes de la línea
	 * @param paragraphSpacingAfter : espacio despues de la línea
	 * @param text : texto del parrafo
	 * @param fontSize : tamaño del texto
	 * @param isBold : valida si el texto es negrita
	 * @param isBreakBefore : valida si existen saltos de línea antes de la línea actual
	 * @param breakBeforeNumber : cantidad de saltos de líneas antes
	 * @param isBreakAfter : valida si existen saltos de línea despues de la línea actual
	 * @param breakAfterNumber : cantidad de saltos de líneas despues
	 * @return
	 */
	public static XWPFParagraph createParagraph(XWPFDocument document, int indentationLeft, ParagraphAlignment paragraphAlignment, 
			LineSpacingRule paragraphLineSpacingRule, int paragraphSpacingBefore, int paragraphSpacingAfter,
			double paragraphSpacingBetweenLines, String text, int fontSize, boolean isBold, 
			boolean isBreakBefore, int breakBeforeNumber, boolean isBreakAfter, int breakAfterNumber) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setIndentationLeft(indentationLeft);
		paragraph.setAlignment(paragraphAlignment);
		paragraph.setSpacingLineRule(paragraphLineSpacingRule);
		paragraph.setSpacingBefore(paragraphSpacingBefore);
		paragraph.setSpacingAfter(paragraphSpacingAfter);
//		paragraph.setSpacingBetween(paragraphSpacingBetweenLines);
        
		XWPFRun run = paragraph.createRun();
		
		//Colocar salto de linea antes del texto
		if(isBreakBefore) {
			for (int i = 0; i < breakBeforeNumber; i++) {
				run.addBreak();
			}
		}
		
		run.setText(text);
		run.setFontSize(fontSize);
		run.setBold(isBold);
		run.setFontFamily("Arial");
		run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
		
		//Colocar salto de linea despues del texto
		if(isBreakAfter) {
			for (int i = 0; i < breakAfterNumber; i++) {
				run.addBreak();
			}
		}
		
		return paragraph;
	}
	
	/**
	 * Reemplaza un texto en word
	 * @param doc
	 * @param findText
	 * @param replaceText
	 * @return
	 */
	public static XWPFDocument replaceTextFor(XWPFDocument doc, String findText, String replaceText){
        doc.getParagraphs().forEach(p ->{
            p.getRuns().forEach(run -> {
            	
                String text = run.getText(run.getTextPosition());
                if(text != null && text.contains(findText)) {
					if(replaceText.contains("\n")) {
						String[] textSplit = replaceText.split("\n");
						for (int j = 0; j < textSplit.length; j++) {
							if(j == 0) {
								run.setText(text.replace(findText, textSplit[j]), 0);
								run.setFontFamily("Arial");
								run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
							}else {
								run.addCarriageReturn();
								run.setText(textSplit[j], j);
								run.setFontFamily("Arial");
								run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
							}
						}
					}else {
						run.setText(text.replace(findText, replaceText), 0);
						run.setFontFamily("Arial");
						run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
					}
            	}
            });
        });

        return doc;
    }
	
	/**
	 * STORY: PGIM-7013: Configuración de número de documento en reemplazo de archivo
	 * Busca la etiqueta «osinumero» en el archivo y coloca el número previamente generado por el Siged
	 * @param doc
	 * @param findText
	 * @param numeroDocumento
	 * @return
	 */
	public static boolean existTextFindNumeroDoc(XWPFDocument doc, String findText, String numeroDocumento) {
		boolean exist = false;

		if(numeroDocumento.equals(".s/n")){
			return exist = false;
		}

		List<XWPFParagraph> list = doc.getParagraphs();

		for (XWPFParagraph paragraph : list) {

			if (paragraph.getText() != null && paragraph.getText().contains("«" + findText + "»")) {
				for (XWPFRun xwpfRun : paragraph.getRuns()) {
					String text = xwpfRun.getText(xwpfRun.getTextPosition());
					if (text != null && text.contains("«" + findText + "»")) {
						exist = true;
						// replacement and setting position
						text = text.replace("«" + findText + "»", numeroDocumento);
						xwpfRun.setText(text, 0);
						break;
					}
				}
			}
		}
		return exist;
	}

	/**
	 * STORY: PGIM-7013: Configuración de número de documento en reemplazo de archivo
	 * Busca la etiqueta «osiexpediente» en el archivo y coloca el número de expediente previamente generado por el Siged
	 * @param doc
	 * @param findText
	 * @param nuExpedienteSiged
	 * @return
	 */
	public static boolean existTextFindOsiExpediente(XWPFDocument doc, String findText, String nuExpedienteSiged) {
		boolean exist = false;

		List<XWPFParagraph> list = doc.getParagraphs();

		for (XWPFParagraph paragraph : list) {

			if (paragraph.getText() != null && paragraph.getText().contains("«" + findText + "»")) {
				for (XWPFRun xwpfRun : paragraph.getRuns()) {
					String text = xwpfRun.getText(xwpfRun.getTextPosition());
					if (text != null && text.contains("«" + findText + "»")) {
						exist = true;
						// replacement and setting position
						text = text.replace("«" + findText + "»", nuExpedienteSiged);
						xwpfRun.setText(text, 0);
						break;
					}
				}
			}
		}
		return exist;
	}

	/**
	 * Reemplaza los valores mergeField del word en el documento, no considera las tablas
	 * @param doc
	 * @param list
	 * @return
	 */
	public static XWPFDocument replaceJSONArray(XWPFDocument doc, JSONArray list) {
		validarDatosJSONObject(list);

		doc.getParagraphs().forEach(paragraph ->{
        	paragraph.getRuns().forEach(run -> {
        		
				String text = run.getText(run.getTextPosition());
				if (text != null) {
					for (int i = 0; i < list.length(); i++) {
						try {
							JSONObject obj = list.getJSONObject(i);
							String findText = obj.getString("mergeField");
							String replaceText = obj.getString("value");
							if(text.contains(findText)) {
								if(replaceText.contains("\n")) {
									String[] textSplit = replaceText.split("\n");
									for (int j = 0; j < textSplit.length; j++) {
										if(j == 0) {
											run.setText(text.replace("«"+findText+"»", textSplit[j]), 0);
											run.setFontFamily("Arial");
											run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
										}else {
//											run.addBreak();
											run.addCarriageReturn();
											run.setText(textSplit[j], j);
											run.setFontFamily("Arial");
											run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
										}
									}
								}else {
									run.setText(text.replace("«"+findText+"»", replaceText), 0);
									run.setFontFamily("Arial");
									run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
								}
		                	}
						} catch (JSONException e) {
							log.error(e.getMessage(), e);
							e.printStackTrace();
						}
					}
				}
        	});
        });

        return doc;
    }
	
	/**
	 * Reemplaza los valores mergeField del word encontrados dentro de las tablas
	 * @param doc
	 * @param list
	 * @return
	 */
	public static XWPFDocument replaceJSONArrayInTabla(XWPFDocument doc, JSONArray list) {
		validarDatosJSONObject(list);

		doc.getTables().forEach(table ->{
			table.getRows().forEach(row ->{
				row.getTableCells().forEach(cells ->{
					cells.getParagraphs().forEach(paragraph ->{
						paragraph.getRuns().forEach(run -> {
							
                            String text = run.getText(run.getTextPosition());
                            if (text != null) {
                            	for (int i = 0; i < list.length(); i++) {
                					try {
                						JSONObject obj = list.getJSONObject(i);
                						String findText = obj.getString("mergeField");
                						String replaceText = obj.getString("value");
                						if(text.contains(findText)) {
                							if(replaceText.contains("\n")) {
                								String[] textSplit = replaceText.split("\n");
                								for (int j = 0; j < textSplit.length; j++) {
                									if(j == 0) {
                										run.setText(text.replace("«"+findText+"»", textSplit[j]), 0);
                										run.setFontFamily("Arial");
                										run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
                									}else {
                										run.addBreak();
                										run.setText(textSplit[j], j);
                										run.setFontFamily("Arial");
                										run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
                									}
                								}
                							}else {
                								run.setText(text.replace("«"+findText+"»", replaceText), 0);
                								run.setFontFamily("Arial");
                								run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
                							}
                	                	}
                					} catch (JSONException e) {
										log.error(e.getMessage(), e);
                						e.printStackTrace();
                					}
                				}
                            }
                        });
                    });
                });
            });
		});
        
        return doc;
    }
	
	/**
	 * Permit crear una tabla en una posicion especifica del documento
	 * @param paragraph
	 * @param document
	 * @param tableRowAling
	 * @param tableWidthType
	 * @param width
	 * @return
	 */
	public static XWPFTable createTableInSpecificPosition(XWPFParagraph paragraph, XWPFDocument document, 
			/*TableRowAlign tableRowAling, TableWidthType tableWidthType,*/ 
			int width) {
		XmlCursor cursor = paragraph.getCTP().newCursor();
		XWPFTable table = document.insertNewTbl(cursor);
//		table.setTableAlignment(tableRowAling);
//		table.setWidthType(tableWidthType);
		table.setWidth(width);
		table.setRowBandSize(10000);
		table.setColBandSize(10000);
//		table.setTopBorder(XWPFBorderType.SINGLE, 1, 1, "000000");
		return table;
	}
	
	public static XWPFTable createFullTableInSpecificPosition(XWPFParagraph paragraph, XWPFDocument document/*, 
			TableRowAlign tableRowAling, TableWidthType tableWidthType, String width*/, XWPFBorderType topBorder, XWPFBorderType bottonBoder, 
			XWPFBorderType leftBorder, XWPFBorderType rightBorder, XWPFBorderType insideHBorder, XWPFBorderType insideVBorder) {
		XmlCursor cursor = paragraph.getCTP().newCursor();
		XWPFTable table = document.insertNewTbl(cursor);
//		table.setTableAlignment(tableRowAling);
//		table.setWidthType(tableWidthType);
//		table.setWidth(width);
//		table.setTopBorder(topBorder, 1, 1, "000000");
//		table.setBottomBorder(bottonBoder, 1, 1, "000000");
//		table.setLeftBorder(leftBorder, 1, 1, "000000");
//		table.setRightBorder(rightBorder, 1, 1, "000000");
		table.setInsideHBorder(insideHBorder, 1, 1, "000000");
		table.setInsideVBorder(insideVBorder, 1, 1, "000000");
		return table;
	}
	
	/**
	 * Permit crear una tabla en una posicion especifica de la tabla de un documento
	 * @param paragraph
	 * @param document
	 * @param tableRowAling
	 * @param tableWidthType
	 * @param width
	 * @return
	 */
	public static XWPFTable createSubTableInSpecificPosition(XWPFParagraph paragraph, XWPFDocument document/*, 
			TableRowAlign tableRowAling, TableWidthType tableWidthType, 
			String width*/) {
		XWPFParagraph paragraph1 = null;
		XmlCursor cursor = paragraph.getCTP().newCursor();
		boolean thereWasParagraphAfter = false;
		thereWasParagraphAfter = cursor.toNextSibling(); 
		if (thereWasParagraphAfter) {
		    paragraph1 = document.insertNewParagraph(cursor);
		   } else {
		    paragraph1 = document.createParagraph();
		   }
		cursor = paragraph1.getCTP().newCursor();
		XWPFTable table = document.insertNewTbl(cursor);
//		table.setTableAlignment(tableRowAling);
//		table.setWidthType(tableWidthType);
//		table.setWidth(width);
		
		return table;
	}
	
	/**
	 * Permite realizar rowspan desde la fila origen a la celda destino
	 * @param table
	 * @param col
	 * @param fromRow
	 * @param toRow
	 */
	public static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			CTVMerge vmerge = CTVMerge.Factory.newInstance();
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				vmerge.setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				vmerge.setVal(STMerge.CONTINUE);
				// and the content should be removed
				for (int i = cell.getParagraphs().size(); i > 0; i--) {
					cell.removeParagraph(0);
				}
				cell.addParagraph();
			}
			// Try getting the TcPr. Not simply setting an new one every time.
			CTTcPr tcPr = cell.getCTTc().getTcPr();
			if (tcPr == null)
				tcPr = cell.getCTTc().addNewTcPr();
			tcPr.setVMerge(vmerge);
		}
	}

	/**
	 * Permite realizar colspan desde la columna origen a la columna destino
	 * @param table
	 * @param row
	 * @param fromCol
	 * @param toCol
	 */
	public static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
		XWPFTableCell cell = table.getRow(row).getCell(fromCol);
		// Try getting the TcPr. Not simply setting an new one every time.
		CTTcPr tcPr = cell.getCTTc().getTcPr();
		if (tcPr == null)
			tcPr = cell.getCTTc().addNewTcPr();
		// The first merged cell has grid span property set
		if (tcPr.isSetGridSpan()) {
			tcPr.getGridSpan().setVal(BigInteger.valueOf(toCol - fromCol + 1));
		} else {
			tcPr.addNewGridSpan().setVal(BigInteger.valueOf(toCol - fromCol + 1));
		}
		// Cells which join (merge) the first one, must be removed
		for (int colIndex = toCol; colIndex > fromCol; colIndex--) {
			// table.getRow(row).getCtRow().removeTc(colIndex); // use this for apache poi versions up to 3
			table.getRow(row).removeCell(colIndex);
		}
	}
	 
	/**
	 * Permite asignar el width a la columa
	 * @param table
	 * @param row
	 * @param col
	 * @param width
	 */
	public static void setColumnWidth(XWPFTable table, int row, int col, int width) {
		CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
		tblWidth.setW(BigInteger.valueOf(width));
		tblWidth.setType(STTblWidth.DXA);
		CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
		if (tcPr != null) {
			tcPr.setTcW(tblWidth);
		} else {
			tcPr = CTTcPr.Factory.newInstance();
			tcPr.setTcW(tblWidth);
			table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
		}
	}
	/*
	public static boolean checkLicense() {
		boolean result = false;
		
		InputStream is;
		
		try {
			//InputStream is =  PoiWordUtil.class.getClassLoader().getResourceAsStream("licencia/Aspose.Words.lic");			
			FileInputStream inStream = new FileInputStream("C:/dev/osi.pgim.repo/Aspose.Words.lic");			
			is =  inStream;
			
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
			
			is.close();			
			System.out.println("licencia aspose ok..");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	public static boolean checkLicense(String pathLicenciaAspose) {
		boolean result = false;
		
		InputStream is;
		
		try {
			//InputStream is =  PoiWordUtil.class.getClassLoader().getResourceAsStream("licencia/Aspose.Words.lic");			
			FileInputStream inStream = new FileInputStream(pathLicenciaAspose);
			System.out.println("pathLicenciaAspose: "+pathLicenciaAspose);			
			is =  inStream;
			
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
			
			is.close();			
			System.out.println("licencia aspose ok..");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Permite cambiar el tipo de letra en todos los parrafos del documento.
	 * @param doc
	 * @return
	 */
	public static XWPFDocument changeFontFamily(XWPFDocument doc) {
		doc.getParagraphs().forEach(paragraph ->{
        	paragraph.getRuns().forEach(run -> {
        		
        		run.setFontFamily("Arial");
				run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
				
        	});
        });

        return doc;
    }
	
	/**
	 * Permite cambiar el tipo de letra en todas las tablas del documento.
	 * @param doc
	 * @return
	 */
	public static XWPFDocument changeFontFamilyInTabla(XWPFDocument doc) {
		doc.getTables().forEach(table ->{
			table.getRows().forEach(row ->{
				row.getTableCells().forEach(cells ->{
					cells.getParagraphs().forEach(paragraph ->{
						paragraph.getRuns().forEach(run -> {
							
							run.setFontFamily("Arial");
							run.getCTR().getRPr().getRFontsArray(0).setHAnsi("Arial");
							
                        });
                    });
                });
            });
		});
        
        return doc;
    }
	
	public static void removeBorderCell(XWPFTable table, int row, int col) {
		XWPFTableCell cell = table.getRow(row).getCell(col);
		CTTcBorders tblBorders = cell.getCTTc().getTcPr().addNewTcBorders();

		//remover borde derecho
		tblBorders.addNewRight().setVal(STBorder.NIL);
		//remover borde izquierdo
		tblBorders.addNewLeft().setVal(STBorder.NIL);
		//remover borde abajo
		tblBorders.addNewBottom().setVal(STBorder.NIL);
	}

	public static void removeAllCellBorders(XWPFTable table, int row, int col) {
		XWPFTableCell cell = table.getRow(row).getCell(col);
		CTTcBorders tblBorders = cell.getCTTc().getTcPr().addNewTcBorders();

		//remover borde derecho
		tblBorders.addNewRight().setVal(STBorder.NIL);
		//remover borde izquierdo
		tblBorders.addNewLeft().setVal(STBorder.NIL);
		//remover borde arriba
		tblBorders.addNewTop().setVal(STBorder.NIL);
		//remover borde abajo
		tblBorders.addNewBottom().setVal(STBorder.NIL);
	}

	public static void removeCellBorderTB(XWPFTable table, int row, int col) {
		XWPFTableCell cell = table.getRow(row).getCell(col);
		CTTcBorders tblBorders = cell.getCTTc().getTcPr().addNewTcBorders();
		//remover borde arriba
		tblBorders.addNewTop().setVal(STBorder.NIL);
		//remover borde abajo
		tblBorders.addNewBottom().setVal(STBorder.NIL);
	}

	public static void removeCellBorderLR(XWPFTable table, int row, int col) {
		XWPFTableCell cell = table.getRow(row).getCell(col);
		CTTcBorders tblBorders = cell.getCTTc().getTcPr().addNewTcBorders();
		//remover borde izquierdo
		tblBorders.addNewLeft().setVal(STBorder.NIL);
		//remover borde derecho
		tblBorders.addNewRight().setVal(STBorder.NIL);
	}
	

	/**
	 * Permite agregar un MergeField en un paragraph
	 * @param paragraph
	 * @param fieldName
	 */
	public static void addMergeField(XWPFParagraph paragraph, String fieldName) {
	    CTSimpleField ctSimpleField = paragraph.getCTP().addNewFldSimple();
	    ctSimpleField.setInstr(" MERGEFIELD " + fieldName + " \\* MERGEFORMAT ");
	    ctSimpleField.addNewR().addNewT().setStringValue("«" + fieldName + "»");
	}
	
	/**
	 * Permite agregar un MergeField en una celda
	 * @param tableFirmantesRow
	 * @param cellNumber
	 * @param fieldName
	 */
	public static void addMergeFieldInTable(XWPFTableRow tableFirmantesRow, int cellNumber, String fieldName) {
		tableFirmantesRow.getCell(cellNumber).getParagraphs().forEach(paragraph ->{
			CTSimpleField ctSimpleField = paragraph.getCTP().addNewFldSimple();
		    ctSimpleField.setInstr(" MERGEFIELD " + fieldName + " \\* MERGEFORMAT ");
		    ctSimpleField.addNewR().addNewT().setStringValue("«" + fieldName + "»");	
		});
	}
	
	public static XWPFDocument replaceIMGInTabla(XWPFDocument doc, String base64Img, String mergeField,
			 int width, int height) {
		doc.getTables().forEach(table ->{
			table.getRows().forEach(row ->{
				row.getTableCells().forEach(cells ->{
					cells.getParagraphs().forEach(paragraph ->{
						paragraph.getRuns().forEach(run -> {
							
                            String text = run.getText(run.getTextPosition());
                            if (text != null) {
        						if(text.contains(mergeField)) {
    								run.setText(text.replace("«"+mergeField+"»", ""), 0);
    								try {
	    								byte[] dataImg = DatatypeConverter.parseBase64Binary(base64Img);
	    								InputStream pic = new ByteArrayInputStream(dataImg);
	    								run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, "graficoReporte", Units.toEMU(width), Units.toEMU(height));
	    								pic.close();
    								
									} catch (IOException | InvalidFormatException e) {
										log.error(e.getMessage(), e);
										e.printStackTrace();
									}

        	                	}
                            }
                        });
                    });
                });
            });
		});
		
		return doc;
	}

	/**
	 * Crea una nueva ejecución, obtiene el objeto CTR (ejecutar), crea un nuevo objeto CTFldChar,
	 * establece el tipo de carácter de campo para comenzar y luego establece la matriz de caracteres de
	 * campo de CTR en el nuevo objeto CTFldChar
	 * 
	 * @param paragraph El párrafo al que se agregará la ejecución.
	 * @return Un objeto de ejecución.
	 */
	public static XWPFRun createRunFldCharTypeBegin(XWPFParagraph paragraph) {
		XWPFRun run = paragraph.createRun();
		org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR cTR = run.getCTR();
		org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar cTFldChar = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar.Factory
				.newInstance();
		cTFldChar.setFldCharType(org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType.BEGIN);
		cTR.setFldCharArray(new org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar[] { cTFldChar });
		return run;
	}

	/**
	 * Crea una ejecución con el texto de instrucción dado.
	 * 
	 * @param paragraph El párrafo al que se agregará la ejecución.
	 * @param instrText El texto que se insertará en el documento.
	 * @return Un objeto de ejecución.
	 */
	public static XWPFRun createRunInstrText(XWPFParagraph paragraph, String instrText) {
		XWPFRun run = paragraph.createRun();
		org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR cTR = run.getCTR();
		org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText cTText = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText.Factory
				.newInstance();
		cTText.setStringValue(instrText);
		cTR.setInstrTextArray(new org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText[] { cTText });
		return run;
	}

	/**
	 * Crea una corrida con un carácter de campo de tipo fin
	 * 
	 * @param paragraph El párrafo al que se agregará la ejecución.
	 * @return Un objeto de ejecución.
	 */
	public static XWPFRun createRunFldCharTypeEnd(XWPFParagraph paragraph) {
		XWPFRun run = paragraph.createRun();
		org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR cTR = run.getCTR();
		org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar cTFldChar = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar.Factory
				.newInstance();
		cTFldChar.setFldCharType(org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType.END);
		cTR.setFldCharArray(new org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar[] { cTFldChar });
		return run;
	}

	public static void validarDatosJSONObject(JSONArray list) {
		for (int i = 0; i < list.length(); i++) {
			try {
				JSONObject obj = list.getJSONObject(i);

				if (!obj.has("mergeField")) {
					obj.put("mergeField", "");
				}

				if (!obj.has("value")) {
					obj.put("value", "");
				}
				
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}

}
