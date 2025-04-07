package pe.gob.osinergmin.pgim.dtos;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO auxiliar para setear el filtro correspondiente a un reporte y la lista de columnas a mostrar
 * 
 * @author promero
 *
 */

@Getter
@Setter
@NoArgsConstructor
public class ReporteAuxDTO<T> {

	private T filtroDTO;

    private ArrayList<ReporteColumnDTO> columnasAExportar;
    
    private ArrayList<CampoMergeDTO> camposFiltro;
    
    private ArrayList<ImgReporteDTO> imgsReporte;
    
    private String tituloReporte;

    private String nombreArchivo;
    
    private String codigoReporte;

    private String orderByReporte;
    
    
}