package pe.gob.osinergmin.pgim.dtos.alfresco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ErrorApiAlfrescoDTO {

	private int codigoEstado; // HttpStatusCode

	private String mensajeEstado;//Mensaje relacionado al codigoEstado

	private String codigoEstadoDetalle;//Código del error especifico al obtener el resultado solo si existe error

	private String mensajeEstadoDetalle;//Descripción del error especifico al obtener el resultado solo si existe error


}
