package pe.gob.osinergmin.pgim.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.namespace.QName;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTBodyImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EscritorHtml {

    /**
     * Permite insertar texto enriquecido al final del documento
     * @param xwpDocument
     * @param identificador
     * @param textoHtml
     * @throws Exception
     */
    public static void escribirFragmentoHtml(XWPFDocument xwpDocument, String identificador, String textoHtml) throws Exception {
        String cadenHtml = String.format("<!DOCTYPE html><html><head><style> body{ font-size: 9pt; font-family:Arial, Helvetica, sans-serif } p{margin: 0;} table {vertical-align: middle; border-collapse: collapse; border-spacing: 0;} td{border: 1px solid #000;}</style><title></title></head><body>%s</body></html>", textoHtml);
        addHtmlInDoc(xwpDocument, identificador, cadenHtml);
    }

    /** 
     * Permite insertar texto enriquecido al final del documento
     * @param xwpfDocument
     * @param identificador
     * @param cadenaHtml
     * @throws Exception
    */
    static void addHtmlInDoc(XWPFDocument xwpfDocument, String identificador, String cadenaHtml) throws Exception {
        
        OPCPackage oPCPackage = xwpfDocument.getPackage();
        PackagePartName partName = PackagingURIHelper.createPartName("/word/" + identificador + ".html");

        PackagePart part = oPCPackage.createPart(partName, "text/html");

        class HtmlRelation extends POIXMLRelation {
            private HtmlRelation() {
                super("text/html",
                        "http://schemas.openxmlformats.org/officeDocument/2006/relationships/aFChunk",
                        "/word/htmlDoc#.html");
            }
        }

        class HtmlDocumentPart extends POIXMLDocumentPart {
            private HtmlDocumentPart(PackagePart part) throws Exception {
                super(part);
            }

            @Override
            protected void commit() throws IOException {
                try (OutputStream out = part.getOutputStream()) {
                    try (Writer writer = new OutputStreamWriter(out, "UTF-8")) {
                        writer.write(cadenaHtml);
                    }
                }
            }
        }

        HtmlDocumentPart documentPart = new HtmlDocumentPart(part);
        xwpfDocument.addRelation(identificador, new HtmlRelation(), documentPart);

        CTBodyImpl ctBodyImplB = (CTBodyImpl) xwpfDocument.getDocument().getBody();
        QName qNameAltChunk = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "altChunk");
        XmlComplexContentImpl xmlComplexContentImplAltChunk = (XmlComplexContentImpl) ctBodyImplB.get_store().add_element_user(qNameAltChunk);
        QName qNameId = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
        SimpleValue simpleValueTarget = (SimpleValue) xmlComplexContentImplAltChunk.get_store().add_attribute_user(qNameId);

        simpleValueTarget.setStringValue(identificador);
    }

    /** 
     * Permite reemplazar los valores mergeField del word por texto enriquecido(html) en el documento, no considera las tablas
     * @param xwpDocument
     * @param list
     * @throws Exception
    */
    public static void replaceToHtmlJSONArray(XWPFDocument xwpDocument, JSONArray list) throws Exception {
        PoiWordUtil.validarDatosJSONObject(list);
        for (int i = 0; i < list.length(); i++) {
            try {
                JSONObject obj = list.getJSONObject(i);
                String identificador = "chunk" + i;
				String findText = "«" + obj.getString("mergeField") + "»";
				String textoHtml = obj.getString("value");
                
                String cadenHtml = String.format("<!DOCTYPE html><html><head> <meta charset=\"utf-8\"> <style> body{ font-size: 9pt; font-family:Arial, Helvetica, sans-serif } p{margin: 0;} table {vertical-align: middle; border-collapse: collapse; border-spacing: 0;} td{border: 1px solid #000;}</style><title></title></head><body>%s</body></html>", textoHtml);
                replaceMergeFieldToHtml(xwpDocument, findText, identificador, cadenHtml);
                
            }catch (JSONException e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }

    }

    /** 
     * Permite reemplazar el valor mergeField del word por texto enriquecido en el documento, no considera las tablas
     * @param xwpfDocument
     * @param findText
     * @param identificador
     * @param cadenaHtml
     * @throws Exception
    */
    static void replaceMergeFieldToHtml(XWPFDocument xwpfDocument, String findText, String identificador, String cadenaHtml) throws Exception {
        
        OPCPackage oPCPackage = xwpfDocument.getPackage();
        PackagePartName partName = PackagingURIHelper.createPartName("/word/" + identificador + ".html");

        PackagePart part = oPCPackage.createPart(partName, "text/html");

        class HtmlRelation extends POIXMLRelation {
            private HtmlRelation() {
                super("text/html",
                        "http://schemas.openxmlformats.org/officeDocument/2006/relationships/aFChunk",
                        "/word/htmlDoc#.html");
            }
        }

        class HtmlDocumentPart extends POIXMLDocumentPart {
            private HtmlDocumentPart(PackagePart part) throws Exception {
                super(part);
            }

            @Override
            protected void commit() throws IOException {
                try (OutputStream out = part.getOutputStream()) {
                    try (Writer writer = new OutputStreamWriter(out, "UTF-8")) {
                        writer.write(cadenaHtml);
                    }
                }
            }
        }

        HtmlDocumentPart documentPart = new HtmlDocumentPart(part);
        xwpfDocument.addRelation(identificador, new HtmlRelation(), documentPart);

        int pos = 0;
        for (IBodyElement bodyElement : xwpfDocument.getBodyElements()) {
         if (bodyElement instanceof XWPFParagraph) {
          XWPFParagraph paragraph = (XWPFParagraph)bodyElement;
          String text = paragraph.getText();
          
          if (text != null && text.contains(findText)) {
           
            //create XmlCursor at this paragraph
           XmlCursor cursor = paragraph.getCTP().newCursor();

           QName ALTCHUNK = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "altChunk");
           QName ID = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
           cursor.beginElement(ALTCHUNK);
           cursor.insertAttributeWithValue(ID, identificador);
           cursor.close();
      
           //now remove the found IBodyElement
           xwpfDocument.removeBodyElement(pos);
      
           break; //break for each loop
          }
         }
         pos++;
        }

    }

    /**
     * Permite insertar texto enriquecido dentro de una celda de una tabla
     * @param xwpfDocument
     * @param identificador
     * @param cadenaHtml
     * @param cursor
     * @throws Exception
    */
    static void addHtmlInTableCell(XWPFDocument xwpfDocument, String identificador, String cadenaHtml, XmlCursor cursor) throws Exception {
        
        OPCPackage oPCPackage = xwpfDocument.getPackage();
        PackagePartName partName = PackagingURIHelper.createPartName("/word/" + identificador + ".html");

        PackagePart part = oPCPackage.createPart(partName, "text/html");

        class HtmlRelation extends POIXMLRelation {
            private HtmlRelation() {
                super("text/html",
                        "http://schemas.openxmlformats.org/officeDocument/2006/relationships/aFChunk",
                        "/word/htmlDoc#.html");
            }
        }

        class HtmlDocumentPart extends POIXMLDocumentPart {
            private HtmlDocumentPart(PackagePart part) throws Exception {
                super(part);
            }

            @Override
            protected void commit() throws IOException {
                try (OutputStream out = part.getOutputStream()) {
                    try (Writer writer = new OutputStreamWriter(out, "UTF-8")) {
                        writer.write(cadenaHtml);
                    }
                }
            }
        }

        HtmlDocumentPart documentPart = new HtmlDocumentPart(part);
        xwpfDocument.addRelation(identificador, new HtmlRelation(), documentPart);

        QName ALTCHUNK = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "altChunk");
        QName ID = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
        cursor.beginElement(ALTCHUNK);
        cursor.insertAttributeWithValue(ID, identificador);
        cursor.close();

    }

    public static String replaceLineBreakToPHtml(String cadena){
        String cadenaReplace = "";
        if(cadena != null && !cadena.equals("")){
            if(!cadena.contains("<p>")) {
                if(cadena.contains("\n")) {
                    String[] textSplit = cadena.split("\n");
                    for (int l = 0; l < textSplit.length; l++) {
                        cadenaReplace += "<p>" + textSplit[l] + "</p>";
                    }
                }else{
                    cadenaReplace += "<p>" + cadena + "</p>";
                }
                return cadenaReplace;
            }else{
            	//validamos si el texto enriquecido tiene un listado
            	if(cadena.contains("<ol")) {
            		String[] textSplit = cadena.split("<ol");
            		boolean inicioConListado = false;
                    for (int l = 0; l < textSplit.length; l++) {
                    	//validamos si el texto enriquecido comienza con un listado, sí es el caso se debe de añadir un <p></p>
                    	//para evitar la continuidad del listado de otros texto enriquecido registrado anteriormente. 
                    	if(l==0 && textSplit[l].trim().equals("")) {
                    		inicioConListado = true;
                    	}
                    	
                    	if(l!=0) {
                    		if(inicioConListado) {
                    			cadenaReplace += "<p style=\"text-align: justify\">&nbsp;</p><ol" + textSplit[l];
                    			inicioConListado = false;
                    		}else
                    			cadenaReplace += "<ol" + textSplit[l];
                    	}else
                    		cadenaReplace += textSplit[l];
                    }
            		
            	}else {
            		cadenaReplace = cadena;
            	}            	
                return cadenaReplace;
            }
        }else{
            return cadenaReplace;
        }
    };
}