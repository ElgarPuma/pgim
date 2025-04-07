package pe.gob.osinergmin.pgim.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad Archivo de PGIM_TD_DOCUMENTO: 
* @descripción: Archivos que conforman el Documento
*
* @author: lbarrantes
* @version: 1.0
* @fecha_de_creación: 28/06/2020
*/

@Getter
@Setter
@NoArgsConstructor
public class ArchivoSigedDTO  {	
		
	private Long idArchivo;	

	private String nombre;

	private String rutaAlfresco;
	
}
